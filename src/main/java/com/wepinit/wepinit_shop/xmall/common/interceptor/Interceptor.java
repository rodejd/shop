/*******************************************************************
                     PMGroupKorea Co., Ltd.
Copyright PMGroupKorea Co., Ltd. 2005. All rights reserved.
No part of this work covered by the copyright hereon may be reproduced,
stored in a retrieval system, in any form or by any means, electronic,
mechanical, photocopying, recording or otherwise, without the prior
written permission of PMGroupKorea Co., Ltd.
SOLUTION    :   XMaLL4 for Spring
FILE_NAME   :
DATE        :   2018.1.29
AUTHOR      :   PMGK S/W Engineer   <contact@gmail.com>
DESCRIPTION :
FUNCTIONS   :
HISTORY     :
REMARKS     :
******************************************************************/
/***********************************************
* <pre>
* 업무구분명		:	Interceptor
* 세부업무구분명	: 	Interceptor
* 작성자 			: 	이의창
* 설명 				: 	Interceptor
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO 	날짜					작성자		내용
* 1		20150109				이의창		최초작성
*
* </pre>
***********************************************/
package com.wepinit.wepinit_shop.xmall.common.interceptor;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xcube.util.WebUtil;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.session.AdminSessionObject;
import com.wepinit.wepinit_shop.xmall.common.session.SellerSessionObject;
import com.wepinit.wepinit_shop.xmall.common.session.ShopSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMainCampaign;
import com.wepinit.wepinit_shop.xmall.front.service.common.FrontCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.*;

@Component
public class Interceptor extends HandlerInterceptorAdapter{

	Logger logger = LoggerFactory.getLogger(Interceptor.class);
	
	@Resource
	private FrontCommonService frontCommonService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String request_URI = request.getRequestURI();
//		logger.info("..... interceptor preHandle :: {}", request_URI);
		String ip = request.getHeader("X-FORWARDED-FOR");
		/*
		String getCookie = response.getHeader("Set-Cookie");
		String url = request.getRequestURI();
		if(url.indexOf("/shop/") >= 0 ) {
			logger.debug("SET-COOKIE : {}", getCookie);
			response.addHeader("Set-Cookie",getCookie + "SameSite=None; Secure");
		}
		*/
		
        /******************************************************
		 * 프록시나 Load Balancer 같은것을 겨쳐 오게 되는 경우
		 ******************************************************/
		if("".equals(StringUtil.N2S(ip))) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if("".equals(StringUtil.N2S(ip))) {
			ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
		}

		if("".equals(StringUtil.N2S(ip))) {
			ip = request.getRemoteAddr();
		}
		
		// api인 경우
		if( request_URI.startsWith("/api") ){
			return super.preHandle(request, response, handler);
    	}
		
		// sample인 경우
		if( request_URI.startsWith("/sample") ){
			return super.preHandle(request, response, handler);
    	}
		
		// 관리자인 경우
		//TODO interceptor를 spring 설정 interceptor로 관리자와 사용자로 나눌지 고민해 볼것
		//에디터는 사용자 화면에서도 사용 제외추가
		if(request_URI.startsWith(request.getContextPath() +"/shop/admin") && request_URI.indexOf("imgRegister") == -1) {
			return adminPreHandle(request, response, handler, request_URI, ip);
		// 판매사인 경우
		}else if(request_URI.startsWith(request.getContextPath() +"/shop/seller")){
			return sellerPreHandle(request, response, handler, request_URI, ip);
		// 사용자인 경우	
		}else{
			return frontPreHandle(request, response, handler, request_URI, ip);
		}
	}
	
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	String request_URI = request.getRequestURI();

//		logger.info("..... interceptor postHandle :: {}", request_URI);
    	
    	// api인 경우
    	if( request_URI.startsWith("/api") ){
    		return ;
    	}
    	
    	// sample인 경우
    	if( request_URI.startsWith("/sample") ){
    		return ;
    	}
    	
    	// 관리자인 경우
    	//TODO interceptor를 spring 설정 interceptor로 관리자와 사용자로 나눌지 고민해 볼것
		if(request_URI.startsWith(request.getContextPath() +"/shop/admin")){
			adminPostHandle(request, response, handler, modelAndView, request_URI);
		// 판매사인 경우
		}else if(request_URI.startsWith(request.getContextPath() +"/shop/seller")){	
			sellerPostHandle(request, response, handler, modelAndView, request_URI);
		// 사용자인 경우	
		}else{
			frontPostHandle(request, response, handler, modelAndView, request_URI);
		}
    }
    
    /**
     * 관리자 preHandle
     * @param request
     * @param response
     * @param handler
     * @param request_URI
     * @param ip
     * @return
     * @throws Exception
     */
    private boolean adminPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler, String request_URI, String ip) throws Exception {
    	
		boolean isAdmLoginExceptUrl= false;
		String[] admLoginExceptUrlArr = null;
		AdminSessionObject admSO = null;	// 관리자 세션
		PrintWriter out = null;
		
    	admSO = AdminSessionObject.getSessionObject(request);
		admLoginExceptUrlArr = CommonConstants.ADMIN_LOGIN_EXCEPT_URL;
		
		/*if(logger.isDebugEnabled()) {
			logger.debug("##################################");
			logger.debug("# = 관리자 =");
			logger.debug("##################################");
			logger.debug("# 관리자 로그인 여부 : " + admSO.isLogin());
			logger.debug("# 요청URL : " + request.getRequestURL());
			logger.debug("# 요청URI : " + request_URI);
			logger.debug("# 요청IP : " + ip);
			if(admSO.isLogin()) {
				logger.debug("# 요청회원정보 : " + admSO.getUserInfo().toString());
			}
			logger.info("##################################");
		}*/
		
		
		for(int i=0; i < admLoginExceptUrlArr.length ; i++){
			if(request_URI.equals(admLoginExceptUrlArr[i])){
				isAdmLoginExceptUrl = true;
				break;
			}
		}

//		logger.info(".............. [adminPreHandle] request_URI :: {}", request_URI);
		
		if(!admSO.isLogin() && !isAdmLoginExceptUrl){
			// 로그인 하지 않았으면 로그인 페이지로 이동
			if(logger.isDebugEnabled()){
				logger.debug("# 로그인페이지로 강제던짐");
			}
			
			// ajax 일경우 Exception 발생 후 jquery.ajax.js errorHandler에서 처리. 
			if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
				throw new BizException("common.00002", CommonConstants.LOGIN_ADMIN_URL);	// 세션이 만료되어 로그인 페이지로 이동합니다.
			}
			
			try{
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				out = response.getWriter();
				out.print(WebUtil.getAlertRedirect("로그인후 이용 가능합니다.", CommonConstants.LOGIN_ADMIN_URL));
				out.flush();
			}catch(Exception e){
				throw e;
			} finally{
				if( null != out ){
					out.close();
				}
			}
			
			//response.sendRedirect(CommonConstants.LOGIN_ADMIN_URL);
            return false;
		}
		
		return super.preHandle(request, response, handler);
    }
    
	/**
	 * 판매사 preHandle
	 * @param request
	 * @param response
	 * @param handler
	 * @param request_URI
	 * @param ip
	 * @return
	 * @throws Exception
	 */
	private boolean sellerPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler, String request_URI, String ip) throws Exception {
//		logger.info(".............. [sellerPreHandle] request_URI :: {}", request_URI);
		boolean isSellerLoginExceptUrl= false;
		String[] sellerLoginExceptUrlArr = null;
		SellerSessionObject sellerSO = null;	// 관리자 세션
		PrintWriter out = null;
	
		sellerSO = SellerSessionObject.getSessionObject(request);
		sellerLoginExceptUrlArr = CommonConstants.SELLER_LOGIN_EXCEPT_URL;
		
		/*if(logger.isDebugEnabled()) {
			logger.debug("##################################");
			logger.debug("# = 판매사 =");
			logger.debug("##################################");
			logger.debug("# 판매사로그인 여부 : " + sellerSO.isLogin());
			logger.debug("# 요청URL : " + request.getRequestURL());
			logger.debug("# 요청URI : " + request_URI);
			logger.debug("# 요청IP : " + ip);
			if(sellerSO.isLogin()) {
				logger.debug("# 요청회원정보 : " + sellerSO.getUserInfo().toString());
			}
			logger.info("##################################");
		}*/
		
		for(int i=0; i < sellerLoginExceptUrlArr.length ; i++){
			if(request_URI.equals(sellerLoginExceptUrlArr[i])){
				isSellerLoginExceptUrl = true;
				break;
			}
		}
		
		if(!sellerSO.isLogin() && !isSellerLoginExceptUrl){
			// 로그인 하지 않았으면 로그인 페이지로 이동
			if(logger.isDebugEnabled()){
				logger.debug("# 로그인페이지로 강제던짐");
			}
			
			// ajax 일경우 Exception 발생 후 jquery.ajax.js errorHandler에서 처리. 
			if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
				throw new BizException("common.00002", CommonConstants.LOGIN_SELLER_URL);	// 세션이 만료되어 로그인 페이지로 이동합니다.
			}
			
			try{
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				out = response.getWriter();
				out.print(WebUtil.getAlertRedirect("로그인후 이용 가능합니다.", CommonConstants.LOGIN_SELLER_URL));
				out.flush();
			}catch(Exception e){
				throw e;
			} finally{
				if( null != out ){
					out.close();
				}
			}
			
			return false;
		}
			
		return super.preHandle(request, response, handler);
	}
    
    /**
     * 사용자 preHandle
     * @param request
     * @param response
     * @param handler
     * @param request_URI
     * @param ip
     * @return
     * @throws Exception
     */
    private boolean frontPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler, String request_URI, String ip) throws Exception {
//		logger.info(".............. [frontPreHandle] request_URI :: {}", request_URI);
    	boolean isfrontLoginExceptUrl	= false;			//로그인필용한 Url 여부
		String[] frontLoginExceptUrlArr 	= null;			//로그인이 필요한 Url 정보
		ShopSessionObject shopSO 		= null;			// 사용자 세션
		PrintWriter out = null;
		
		shopSO = ShopSessionObject.getSessionObject(request);
		frontLoginExceptUrlArr = CommonConstants.SHOP_LOGIN_URL;
		
		/*if(logger.isDebugEnabled()) {
			logger.debug("##################################");
			logger.debug("# = 사용자 =");
			logger.debug("##################################");
			logger.debug("# 사용자 로그인 여부 : " + shopSO.isShopLogin());
			logger.debug("# 요청URL : " + request.getRequestURL());
			logger.debug("# 요청URI : " + request_URI);
			logger.debug("# 요청IP : " + ip);
			if(shopSO.isShopLogin()) {
				logger.debug("# 요청사용자회원정보 : " + shopSO.getUserInfo().toString());
			}
			logger.info("##################################");
		}*/
		
		
		for(int i=0; i < frontLoginExceptUrlArr.length ; i++){
			if(request_URI.equals(frontLoginExceptUrlArr[i])){
				isfrontLoginExceptUrl = true;
				break;
			}
		}
		
		if(!shopSO.isShopLogin() && isfrontLoginExceptUrl){
			// 로그인 하지 않았으면 로그인 페이지로 이동
			if(logger.isDebugEnabled()){
				logger.debug("# 사용자 로그인페이지로 강제던짐");
			}
			
			// ajax 일경우 Exception 발생 후 jquery.ajax.js errorHandler에서 처리. 
			if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
				throw new BizException("common.00002", CommonConstants.LOGIN_FRONT_URL);	// 세션이 만료되어 로그인 페이지로 이동합니다.
			}
			
			try{
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				request.getSession().setAttribute("referer", request_URI);
				//String host = request.getScheme() + "://" + request.getServerName() + ":" + (request.getServerPort() != 80 ? request.getServerPort() : "");
				//request.getSession().setAttribute("referer", request.getHeader("Referer").replace(host, ""));
				
				
				out = response.getWriter();
				out.print(WebUtil.getAlertRedirect("로그인후 이용 가능합니다.", CommonConstants.LOGIN_FRONT_URL));
				out.flush();
			}catch(Exception e){
				throw e;
			} finally{
				if( null != out ){
					out.close();
				}
			}
			
			//response.sendRedirect(CommonConstants.LOGIN_ADMIN_URL);
            return false;
		}
		
		return super.preHandle(request, response, handler);
    }
    
    /**
     * 관리자 postHandle
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @param request_URI
     * @throws Exception
     */
    private void adminPostHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView, String request_URI) throws Exception {
//		logger.info(".............. [adminPostHandle] viewName :: {}", modelAndView != null ? modelAndView.getViewName() : "null");
    }
    
    
	/**
	 * 판매사 postHandle
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @param request_URI
	 * @throws Exception
	 */
    private void sellerPostHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView, String request_URI) throws Exception {
    	
    	String viewName = "";
    	String key = "";
		Object obj = null;
		Map<String, Object> modelMap = modelAndView.getModel();
		Iterator<String> iter = modelMap.keySet().iterator();
		
    	// 팝업 관련 세팅
    	if(modelAndView != null){
    		viewName = modelAndView.getViewName();
    		modelMap = modelAndView.getModel();
    		iter = modelMap.keySet().iterator();
    		
    		while( iter.hasNext() ) {
    			key = String.valueOf(iter.next());
    			if( -1 < key.indexOf("FM") ){
    				obj = modelMap.get(key);
    				
    				if(obj instanceof PageMaker && "Y".equals(((PageMaker)obj).getPopView())){
    					viewName += "/popup";
    				}
    				
    				modelAndView.setViewName(viewName);
    				break;
    			}
    		}
    		// 판매사 login session 설정
    		modelAndView.addObject("shop_seller", SellerSessionObject.getSessionObject(request));
    	}

//		logger.info("............ [sellerPostHandle] viewName :: {}", viewName);
    }
     
    /**
     * 사용자 postHandle
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @param request_URI
     * @throws Exception
     */
    private void frontPostHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView, String request_URI) throws Exception {
//		logger.info("..... interceptor frontPostHandle");

    	/*//모바일 화면을 위한 변수	
    	boolean isMob = false;	// 모바일 여부
    	Device device = null;	// 현재 접속 하고 있는 디바이스 객체
    	
    	device = DeviceUtils.getCurrentDevice(request);
    	isMob = ConfigClass.MOB_FLAG && device.isMobile();*/
 	
    	//json or xml 호출(ajax)일 경우 제외처리
    	//"XMLHttpRequest".equals(request.getHeader("X-Requested-With"))
    	
    	/*if( logger.isDebugEnabled() ){
    		logger.debug("post Handle!!");
			logger.debug("bh3211>>>>>>>>>>>> modelAndView:{}", modelAndView);
			logger.debug("bh3211>>>>>>>>>>>> request.getHeader(X-Requested-With):{}", request.getHeader("X-Requested-With"));
    	}*/
    	
    	
    	//response.addHeader("Set-Cookie", "cookie_name=cookie_value; SameSite=None; Secure");

//		logger.info(".........  {}", request.getRequestURI());
		Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
		boolean firstHeader = true;
		for (String header : headers) {
			if (firstHeader) {
				response.setHeader(HttpHeaders.SET_COOKIE,
						String.format("%s; Secure; %s", header, "SameSite=None"));
				firstHeader = false;
				continue;
			}
			//Chrome SameSite 이슈로 인한 SameSite None설정  
			response.addHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; %s", header, "SameSite=None"));
		}
    	
    	if(modelAndView != null || "XMLHttpRequest".equals(request.getHeader("X-Requested-With")) ){
    		if(modelAndView != null) {
	    		String viewName	= modelAndView.getViewName();
	        	if( logger.isDebugEnabled() ){
	    			logger.debug("bh3211>>>>>>>>>>>> first viewname:{}", viewName);
	    		}
	
	        	if(!viewName.startsWith("redirect") && !viewName.startsWith("foward")){
		        	//viewName 이 "/"로 시작하지 않을 경우 viewName에 "/"를 붙여준다
		        	if(!viewName.startsWith("/")){
		        		viewName = "/" + viewName;
		        	}
		        	
		        	// #####
		        	// # Config 파일 MOB_FLAG가 true 이고, 모바일 기기에서 접속 시 모바일용 타일즈 적용
		        	// #####
					//if(isMob){
					//	viewName = "/m/" + ConfigClass.MOB_SKIN + viewName;
					//} else {
					//	viewName = "/" + ConfigClass.WEB_SKIN + viewName;
					//}
//					viewName = "/" + ConfigClass.getSkin(request) + viewName;

					if (!viewName.contains(ConfigClass.getSkin(request))) {
						viewName = "/" + ConfigClass.getSkin(request) + viewName;
					}
		    		//modelAndView.setViewName( "shop/skin/"+ConfigClass.WEB_SKIN + modelAndView.getViewName());
		    		modelAndView.setViewName(viewName);
//					logger.info("......... viewName :: {}", viewName);
	        	}
	    		
	    		/*******************************************************************
	    		 * front 메뉴 설정(popup, layer popup 호출의 경우 예외처리 필요)
	    		 *******************************************************************/
	    		// 카테고리 목록(front 상품 카테코리 left menu)
	    		List appMenuCategoryList = null;			// 카테고리 목록
	    		appMenuCategoryList = frontCommonService.getFrontCommonCategoryList();
	    		modelAndView.addObject("appMenuCategoryList", appMenuCategoryList);
	    		
	    		// 브랜드 목록(front 상품 브랜드 목록)
	    		List appMenuBrandList = null;				// 브랜드 목록
	    		appMenuBrandList = frontCommonService.getFrontCommonBrandList();
	    		modelAndView.addObject("appMenuBrandList", appMenuBrandList);
	    		
	    		// 커뮤니티 게시판 목록(front 게시판 Id, Name 가져오기)
	    		List appMenuBoardList = null;				// 커뮤니티 게시판 목록
	    		appMenuBoardList = frontCommonService.getFrontCommonBoardList();
	    		modelAndView.addObject("appMenuBoardList", appMenuBoardList);
	    		
	    		//최근상품 목록 조회
	    		ArrayList todayGoodsList = WebUtil.getTodayGoodsCookie(request, response);
	    		ArrayList headerTodayGoodsList = new ArrayList();
	    		for(int i=todayGoodsList.size()-1; i>=0; i--){
	    			headerTodayGoodsList.add(todayGoodsList.get(i));
	    		}
	    		modelAndView.addObject("headerTodayGoodsList", headerTodayGoodsList);
	    		
	    		//최근 검색어 목록 조회
	    		ArrayList recentWordList = WebUtil.getRecentWordCookie(request, response);
	    		ArrayList headerRecentWordList = new ArrayList();
	    		for(int i=recentWordList.size()-1; i>=0; i--){
	    			headerRecentWordList.add(recentWordList.get(i));
	    		}
	    		modelAndView.addObject("headerRecentWordList", headerRecentWordList);
	    		
	    		//skin type 설정
	    		modelAndView.addObject("wskin", ConfigClass.getSkin(request));
	    		modelAndView.addObject("mskin", ConfigClass.getSkin(request));
	    		
	    		//메인 캠페인리스트
	    		List<GdMainCampaign> mainCampaignList = frontCommonService.getFrontCommonMainCampaignList(ConfigClass.getSkin(request));
	    		modelAndView.addObject("mainCampaignList", mainCampaignList);
	    		
	    		//사용자 login session 설정
	    		modelAndView.addObject("shop_so", ShopSessionObject.getSessionObject(request));
	    		
	    		if( logger.isDebugEnabled() ){
	        		logger.debug("bh3211>>>>>>>>>>>>>>  Last viewname: {}", viewName);
	        	}
	    		
	    		HttpSession session = request.getSession();
	    		String nonMemberOrdno = (String) session.getAttribute("nonMemberkey");
	    		modelAndView.addObject("nonMemberOrdno", nonMemberOrdno);

//				logger.info("............ [frontPostHandle] viewName :: {}", viewName);
	    	}
    	}   
    }
}
