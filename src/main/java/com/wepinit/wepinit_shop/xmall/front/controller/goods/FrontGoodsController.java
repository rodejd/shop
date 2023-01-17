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
/********************************************************************************
* <pre>
* 업무구분명			:	Community
* 세부업무구분명	: 	사용자 > Community
* 작성자 				: 	이병환
* 설명 				: 	사용자 Community Controller
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170208		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.controller.goods;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xcube.util.WebUtil;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.UserInfo;
import com.wepinit.wepinit_shop.xmall.common.session.ShopSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCategory;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsBrand;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsOption;
import com.wepinit.wepinit_shop.xmall.common.vo.join.OrderMember;
import com.wepinit.wepinit_shop.xmall.front.service.goods.FrontGoodsService;
import com.wepinit.wepinit_shop.xmall.front.vo.goods.*;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/shop/goods/")
public class FrontGoodsController {

	private static final Logger logger = LoggerFactory.getLogger(FrontGoodsController.class); 
	
	@Resource
	FrontGoodsService service;
	
	/**
	 * 브랜드 목록	
	 * @param frontGoodsVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="goods_brand")
	public String goods_brand(@ModelAttribute("frontGoodsVO") FrontGoodsVO frontGoodsVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("goods_brand>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>frontGoodsVO" + frontGoodsVO);
		}
		//2020.04.29 상품별 회원정보에 따른 찜한상품 가져오기위한 유저정보 
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		frontGoodsVO.setM_no(userInfo.getM_no());
		//브랜드별 기본정보 조회
		frontGoodsVO.setGdGoodsBrandObj( service.getGdGoodsBrandObj(frontGoodsVO));
		
		//브랜드별 상세정보 조회(상세정보는 없을 경우도 있기때문에 기본정보와 별도로 조회한다.)
		//frontGoodsVO.setXmCategoryBrandInfoObj( service.getXmCategoryBrandInfo(frontGoodsVO) );
		
		//카테고리 리스트
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("brandno", frontGoodsVO.getBrandno());
		param.put("category", "");
		List<GdCategory> categoryList = service.getFrontGoodsGoodsCategoryList(param);
		frontGoodsVO.setFrontGoodsGoodsCategoryList(categoryList);
		
		
		//브랜드별 상품목록
		param = new HashMap<String, Object>();
		
		//상품브랜드일련번호
		param.put("brandno", frontGoodsVO.getBrandno());
		
		//카테고리
		param.put("category", frontGoodsVO.getCategory());
		
		//사이즈(옵션)
		param.put("options", "".equals(StringUtil.N2S(frontGoodsVO.getOptions())) ? "" : frontGoodsVO.getOptions().split(","));
		
		// sort
		if ("".equals( StringUtil.N2S(frontGoodsVO.getSort())))
			frontGoodsVO.setSort("goodsno desc");
		param.put("sort", frontGoodsVO.getSort());
		
		//총건수
		frontGoodsVO.setRowCount( service.getFrontGoodsBrandCount(param) );
				
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, frontGoodsVO.getPageSize() );
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, frontGoodsVO.getRowStart());
		
		// 회원 그룹별 조회
		frontGoodsVO.setFrontGoodsBrandList( service.getFrontGoodsBrandList(param) );
		
		return "/shop/goods/goods_brand";
	}
	
	/**
	 * 상품카테고리 목록
	 * 
	 * @param frontGoodsVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="goods_list")
	public String goods_list(@ModelAttribute("frontGoodsVO") FrontGoodsVO frontGoodsVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("goods_list>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>frontGoodsVO" + frontGoodsVO);
		}
		
		//상단 카테고리 뎁스 표시
		String currPosition = ShopLibFunction.getCurrPosition(frontGoodsVO.getCategory(), "1", ConfigClass.getSkin(req));
		model.addAttribute("currPosition", currPosition);
		
		//카테고리 리스트
		String category = frontGoodsVO.getCategory();
		if (category.length() > 6)
			category = category.substring(0, 6);
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("category", category);
		List<GdCategory> categoryList = service.getFrontGoodsGoodsCategoryList(param);
		frontGoodsVO.setFrontGoodsGoodsCategoryList(categoryList);
		
		//카테고리 코드
		param = new HashMap<String, Object>();
		param.put("category", StringUtil.nvl(frontGoodsVO.getCategory(), ""));
		
		//카테고리 정보
		GdCategory cateInfo = service.getFrontGoodsGoodsCategoryInfo(param);
		model.addAttribute("cateInfo", cateInfo);
		
		//브랜드 리스트
		frontGoodsVO.setFrontGoodsGoodsBrandList(service.getFrontGoodsGoodsBrandList(param));
		
		//브랜드
		param.put("brands", "".equals(StringUtil.N2S(frontGoodsVO.getBrands())) ? "" : frontGoodsVO.getBrands().split(","));
		
		//사이즈(옵션)
		param.put("options", "".equals(StringUtil.N2S(frontGoodsVO.getOptions())) ? "" : frontGoodsVO.getOptions().split(","));
		
		// sort
		if ("".equals( StringUtil.N2S(frontGoodsVO.getSort())))
			frontGoodsVO.setSort("goodsno desc");
		param.put("sort", frontGoodsVO.getSort());
		
		//총건수
		frontGoodsVO.setRowCount( service.getFrontGoodsGoodsCount(param) );
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, frontGoodsVO.getPageSize() );
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, frontGoodsVO.getRowStart());
		
		// 회원, 비회원 구분
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		param.put("m_no", userInfo.getM_no());
		
		// 회원 그룹별 조회
		frontGoodsVO.setFrontGoodsGoodsList( service.getFrontGoodsGoodsList(param));
		
		return "/shop/goods/goods_list";
	}

	/**
	 * 상품 상세정보 조회
	 * @param frontGoodsVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="goods_view")
	public String goods_view(@ModelAttribute("frontGoodsVO") FrontGoodsVO frontGoodsVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("FrontGoodsController > goods_view");
		}
		
		int grpEmeony = 0;
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		if(sessObject.isShopLogin()){
			UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
			model.addAttribute("userInfo", userInfo);
			
			frontGoodsVO.setM_no(userInfo.getM_no());
			grpEmeony = (int) userInfo.getXkey().get("add_emoney");
		}
		
		//Skin 설정
		frontGoodsVO.setSkin(ConfigClass.getSkin(req));
		
		// 카테고리 코드 없을때
		if ("".equals(StringUtil.nullConv(frontGoodsVO.getCategory(), ""))) {
			GdCategory gdCategory = service.getFrontGoodsGoodsCategoryByGoodsno(frontGoodsVO.getGoodsno());
			if (gdCategory != null)
				frontGoodsVO.setCategory(gdCategory.getCategory());
		}

		//상단 카테고리 뎁스 표시
		String currPosition = ShopLibFunction.getCurrPosition(frontGoodsVO.getCategory(), "1", "en"/*frontGoodsVO.getSkin()*/);
		model.addAttribute("currPosition", currPosition);
		
		//상품 상세내역 조회
		service.getFrontGoodsView(frontGoodsVO);
		
		if(logger.isDebugEnabled()){
			logger.debug("FrontGoodsController > goods_view > frontGoodsVO :{}",frontGoodsVO.toString());
		}
		//상품옵션 조회
		model.addAttribute("opt2List",service.getGoodsOptionList(frontGoodsVO));
		
		//예상 적립금
		frontGoodsVO.getGoodsView().setReserve(service.getReserve(frontGoodsVO.getGoodsView().getPrice(), grpEmeony));
		
		//오늘본 상품 cookie 설정
		Map<String, String> goodsMap = new HashMap<String, String>();
		goodsMap.put("goodsno", frontGoodsVO.getGoodsno());
		goodsMap.put("goodsnm", frontGoodsVO.getGoodsView().getGoodsnm());
		goodsMap.put("goodsnmKR", frontGoodsVO.getGoodsView().getGoodsnmKR());
		goodsMap.put("goodsnmCN", frontGoodsVO.getGoodsView().getGoodsnmCN());
		//goodsMap.put("price", String.valueOf(frontGoodsVO.getGoodsOptionList().get(0).getPrice()));
		goodsMap.put("price", Integer.toString(frontGoodsVO.getGoodsView().getPrice()));
		goodsMap.put("img", frontGoodsVO.getGoodsView().getImgs());
		goodsMap.put("category", frontGoodsVO.getCategory());
		WebUtil.setTodayGoodsCookie(req, res, goodsMap);
		if(logger.isDebugEnabled()){
			logger.debug("@@ setTodayGoodsCookie Map >> "+goodsMap.toString());
		}
		
		// 2017-08-24 : 추가배송비정보 세팅 (지은정)
		frontGoodsVO.setOverDeliveryInfo(ShopLibFunction.getOverDeliveryInfo(frontGoodsVO.getGoodsView().getSellerCd()));
				
		return "/shop/goods/goods_view";
	}
	
	@RequestMapping(value="popup_goods_review")
	public String popup_goods_review(@ModelAttribute("frontGoodsVO") FrontGoodsVO frontGoodsVO,  HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		Map<String, Object> param = new HashMap<>();
		param.put("m_no", userInfo.getM_no());
		param.put("goodsno", frontGoodsVO.getGoodsno());
		param.put("ordno", frontGoodsVO.getOrdno());
		
		OrderMember reviewInfo = service.selectFrontReviewInfo(param);
		model.addAttribute("reviewInfo", reviewInfo);
		
		return "/shop/goods/popup_goods_review";
	}
	
	/**
	 * 상품평 파일업로드
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="popup_goods_review_file.doJson")
	public String popup_goods_review_file(MultipartHttpServletRequest request, Model model) throws Exception {
		Iterator<String> files = request.getFileNames();
		
		String awsPath = "review_temp/";
		if(files.hasNext()){
			String uploadFile = files.next();
			MultipartFile mFile = request.getFile(uploadFile);
			
			try {
				//aws 파일등록
				String imgUrl = FileUtil.awsUploadFile(mFile.getOriginalFilename(), mFile.getBytes(), awsPath);
				
				//임시파일 등록
				if(!StringUtils.isEmpty(imgUrl)) {
					ShopSessionObject shop_so = ShopSessionObject.getSessionObject(request);
					
					FrontGoodsReviewVO frontGoodsReviewVO = new FrontGoodsReviewVO();
					frontGoodsReviewVO.setImg(imgUrl);
					frontGoodsReviewVO.setRegid(shop_so.getUserInfo().getUserId());
					service.insertReviewTempFile(frontGoodsReviewVO);
					
					if( frontGoodsReviewVO.getFno() != 0 ) {
						model.addAttribute("result", true);
						model.addAttribute("fno", frontGoodsReviewVO.getFno());
						model.addAttribute("img", imgUrl);
					}
				}
				
			} catch(Exception e) {
				e.printStackTrace();
				
				model.addAttribute("result", false);
			}
		}
		
		return "dojson";
	}
	
	/**
	 * 상품별 상품평 조회
	 * @param frontGoodsReviewVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="ajaxMarketReview")
	public String ajaxMarketReview(@ModelAttribute("frontGoodsReviewVO") FrontGoodsReviewVO frontGoodsReviewVO, HttpServletRequest req , HttpServletResponse res, Model model) throws Exception {
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		if(sessObject.isShopLogin()){
			frontGoodsReviewVO.setMno(sessObject.getUserInfo().getM_no());
		}
		
		//상품별 상품평 조회
		service.getFrontMarketReview(frontGoodsReviewVO);
		return "/shop/goods/ajaxMarketReview";
	}
	
	/**
	 * 상품별 상품평 등록,수정,삭제
	 * @param frontGoodsReviewVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="ajaxGoodsReviewWrite.doJson")
	public String ajaxGoodsReviewWrite(@ModelAttribute("frontGoodsReviewVO") FrontGoodsReviewVO frontGoodsReviewVO, HttpServletRequest req , HttpServletResponse res, Model model) throws Exception {
		//상품별 상품평 등록,수정,삭제
		int result = service.setFrontGoodsReviewWrite(frontGoodsReviewVO,req);
		
		model.addAttribute("result", result);
		return "doJson";
	}
	
	
	/**
	 * 상품별 QnA 리스트 조회
	 * @param frontGoodsQnAVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="ajaxGoodsQna")
	public String ajaxGoodsQna(@ModelAttribute("frontGoodsQnAVO") FrontGoodsQnAVO frontGoodsQnAVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("FrontGoodsController > ajaxGoodsQna");
		}
		
		//상품별 QnA 리스트 조회
		service.getFrontGoodsQnAList(frontGoodsQnAVO);
		
		 
		if(logger.isDebugEnabled()){
			logger.debug("FrontGoodsController > ajaxGoodsQna > frontGoodsQnAVO :{}",frontGoodsQnAVO.toString());
		}
		
		return "/shop/goods/ajaxGoodsQna";
	}

	@RequestMapping(value="ajaxGoodsQnaWrite.doJson")
	public String ajaxGoodsQnaWrite(@ModelAttribute("frontGoodsQnAVO") FrontGoodsQnAVO frontGoodsQnAVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
		//상품별 QnA 등록,수정,삭제
		service.setFrontGoodsQnAWrite(frontGoodsQnAVO,req);
		
		return "doJson";
	}
	
	
	/**
	 * 상품검색
	 * @param goodsSearchVO
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="goods_search")
	public String goodsSearch(@ModelAttribute("goodsSearchVO")FrontGoodsSearchVO goodsSearchVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception{

		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		if(sessObject.isShopLogin()){
			goodsSearchVO.setMno(sessObject.getUserInfo().getM_no());
			goodsSearchVO.setLevel(Integer.parseInt(String.valueOf(sessObject.getUserInfo().getXkey().get("level"))));
		}
		
		String[] swords = null;
		if (!"".equals(StringUtil.nvl(goodsSearchVO.getSword(), ""))) {
			swords = goodsSearchVO.getSword().split(" ");
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("skey", "all");
		param.put("swords", swords);
		
		//카테고리 리스트
		param.put("category", "");
		List<GdCategory> categoryList = service.getFrontGoodsGoodsCategoryList(param);
		goodsSearchVO.setFrontGoodsGoodsCategoryList(categoryList);
		
		//브랜드 리스트
		param.put("category", goodsSearchVO.getCategory());
		goodsSearchVO.setFrontGoodsGoodsBrandList(service.getFrontGoodsGoodsBrandList(param));
		
		//회원정보
		param.put("mno", goodsSearchVO.getMno());
		param.put("level", goodsSearchVO.getLevel());
		//브랜드
		param.put("brands", "".equals(StringUtil.N2S(goodsSearchVO.getBrands())) ? "" : goodsSearchVO.getBrands().split(","));
		//사이즈(옵션)
		param.put("options", "".equals(StringUtil.N2S(goodsSearchVO.getOptions())) ? "" : goodsSearchVO.getOptions().split(","));
		
		// sort
		if ("".equals( StringUtil.N2S(goodsSearchVO.getSort())))
			goodsSearchVO.setSort("goodsno desc");
		param.put("sort", goodsSearchVO.getSort());
		
		//상품 총카운트 조회
		goodsSearchVO.setRowCount(service.getFrontTopGoodsSearchListCount(param));
				
		// 페이징
		param.put(CommonConstants.PAGE_SIZE, goodsSearchVO.getPageSize());
		param.put(CommonConstants.ROW_START, goodsSearchVO.getRowStart());
		
		//상품 목록 조회
		goodsSearchVO.setGoodsList(service.getFrontTopGoodsSearchList(param));
		
		//검색어 쿠키에 저장 
		WebUtil.setRecentWordCookie(req, res, goodsSearchVO.getSword());
		
		return "/shop/goods/goods_search";
	}

	@RequestMapping(value="ajaxSearchWordDel.doJson")
	public String ajaxSearchWordDel(@ModelAttribute("sword") String sword,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		WebUtil.delRecentWordCookie(req, res, sword);
		
		return "doJson";
	}
	
	
	@RequestMapping(value="goods_cart")
	public String goodsCarts(@ModelAttribute("cartVO") FrontGoodsCartsVO cartVO,
			HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model) throws Exception {
		
		String referer = cartVO.getReferer() == null ? req.getHeader("referer") : cartVO.getReferer();
		
		if(logger.isDebugEnabled()) {
			logger.debug("FrontGoodsController >> goods_cart");
		}
		
		WebUtil.setCookies(res, "gd_isDirect", "", -1);
		String mode = cartVO.getMode();
		String returnUrl = "/shop/goods/goods_cart";
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		
		//2020-02-11 이현빈 비회원 장바구니기능 세션키 추가
		if(session.getAttribute("nonMemberKey") == null && userInfo.getM_no() == 0){
			this.service.getNonMemberKey(session);
		}
		
		userInfo.setUkey(StringUtil.nvl(session.getAttribute("nonMemberKey"), ""));
		switch(mode) {
			case "addItem" :
		         this.service.goodsCartsAddItem(cartVO, req, res, session, userInfo);
		         cartVO.setMode("list");
		         return "redirect:/shop/goods/goods_cart";
			case "list" :
				this.service.goodsCartsList(cartVO, req);	// goodsCartsList() 는 타는데 그 내부 메소드는 타지 않는다. 결국 이 메소드 안에서 하는게 없다.
				break;
			case "empty" :
				this.service.goodsCartsEmpty(req, res, userInfo);
				break;
			case "delItem" :
				this.service.goodsCartsDelItem(userInfo, cartVO, req, res);
				break;
			case "modItem" :
				this.service.goodsCartsModItem(userInfo, cartVO, req, res);
				break;
			case "selItem" :
				this.service.goodsCartsSelItem(cartVO, req, res);
				break;
			default :
		}
		
		// 로그인시 비로그인시 화면에 보이는 리스트 수정
		this.service.goodsCartsLoginY(userInfo, cartVO);
		
		cartVO.setDeliveryDefault(ShopConfig.getProperty("delivery_default"));
		cartVO.setDeliveryFreeDelivery(ShopConfig.getProperty("delivery_freeDelivery"));
		cartVO.setDeliberyGoodsDelibery(ShopConfig.getProperty("delivery_goodsDelivery"));
		
		model.addAttribute("referer", referer);
		model.addAttribute("cartVO", cartVO);
		model.addAttribute("userInfo" ,userInfo);

		// 알럿 띄울 메세지는 없고 리다이렉트될 페이지만 있는 경우
		if(StringUtils.hasText(cartVO.getRedirectPage()) && !StringUtils.hasText(cartVO.getAlertMessage())) {
			returnUrl = "redirect:" + cartVO.getRedirectPage();
		}
		
		return returnUrl;
	}
	
	@RequestMapping(value="ajaxGoodsCartAdd.doJson")
	public String ajaxGoodsCartAdd(@ModelAttribute("cartVO") FrontGoodsCartsVO cartVO,
			HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model) throws Exception {
		
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		
		//2020-02-11 이현빈 비회원 장바구니기능 세션키 추가
		if(session.getAttribute("nonMemberKey") == null && userInfo.getM_no() == 0){
			this.service.getNonMemberKey(session);
		}
		
		userInfo.setUkey(StringUtil.nvl(session.getAttribute("nonMemberKey"), ""));
		this.service.goodsCartsAddItem(cartVO, req, res, session, userInfo);

		return "doJson";
	}
	

	/**
	 * 쿠폰 다운로드 로그인 여부 확인
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="checkLogin.dojson")
	public String ajaxCheckLogin(HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@ checkLogin Ajax >> ");
		}
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		if(sessObject.isShopLogin()){
			model.addAttribute("result", 1);
		} else {
			model.addAttribute("result", 0);
		}
		
		return "dojson";
	}
	
	/**
	 * 회원직접 쿠폰 다운로드 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="downCoupon.dojson")
	public String ajaxDownCoupon(@ModelAttribute("couponVO")FrontGoodsCouponVO couponVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@ downCoupon Ajax >> "+couponVO.getCouponcd()+" goodsno >> " +couponVO.getGoodsno());
		}
		ShopSessionObject sessObject = ShopSessionObject.getSessionObject(req);
		
		couponVO.setMno(sessObject.getUserInfo().getM_no());
		couponVO.setGrpsno(String.valueOf(sessObject.getUserInfo().getXkey().get("grp_sno")));
		
		model.addAttribute("result", service.setFrontCouponApply(couponVO));
		
		return "dojson";
	}
	
	
	/**
	 * 추가옵션 확인 여부
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="likeGoods.dojson")
	public String ajaxLikeGoods(@ModelAttribute(value="goodsno") String goodsno , @ModelAttribute(value="like") boolean like ,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@ likeGoods Ajax >> goodsno >> " + goodsno + "like >> " + like); 
		}
		//2020.02.12 이현빈 비회원 상품주문시 회원,비회원 구분
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		logger.debug("m_no:" + userInfo.getM_no());
		userInfo.getM_no();
		
		service.likeGoods(goodsno, like, userInfo.getM_no());
		
		return "dojson";
	}
	
	/*
	 * 자동검색 완성 ajax
	 * 
	 */
	@RequestMapping(value="ajaxAutoComplete.dojson" ,method=RequestMethod.POST)
	@ResponseBody
	public String[] ajaxAutoComplete(Model model, @RequestBody String check ) {

		if(logger.isDebugEnabled()){
			logger.debug("@@ [AutoComplete.doJson Ajax] check value : " + check); 
		}
		String [] list = null ;
		switch(check) {
			case "goodsnm=":
				list = service.getGoodsNameInfo();
				break;
			case "brand=" :
				list = service.getBrandNameInfo();
				break;
			case "all=" :
				String [] goodsnm = service.getGoodsNameInfo();
				String [] brand = service.getBrandNameInfo();
				list = new String[goodsnm.length + brand.length]; 
				
				System.arraycopy(goodsnm, 0, list, brand.length, goodsnm.length);
				System.arraycopy(brand, 0, list, goodsnm.length, brand.length);
				break;
		}
		 
		return list;
	}
	
	@RequestMapping(value="ajaxOpt2List.dojson")
	public String ajaxOpt2List(@ModelAttribute("gdGoodsOption") GdGoodsOption gdGoodsOption, Model model) {
		gdGoodsOption.setOpt1(StringEscapeUtils.unescapeHtml(gdGoodsOption.getOpt1()));
		List<GdGoodsOption> secondOption = service.getOpt2List(gdGoodsOption);
		model.addAttribute("secondOption", secondOption);
			
		return "dojson";
	}
	
	@RequestMapping(value="ajaxReviewFileList.dojson")
	public String ajaxReviewFileList(@ModelAttribute("frontGoodsReviewVO") FrontGoodsReviewVO frontGoodsReviewVO, Model model) {
		List<FrontGoodsReviewVO> reviewFileList = service.getFrontGoodsReviewFileList(frontGoodsReviewVO);
		model.addAttribute("reviewFileList", reviewFileList);
			
		return "dojson";
	}
	
	
	/**
	 * DESIGNER
	 * @param frontGoodsVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="designer")
	public String designer(@ModelAttribute("frontGoodsBrandVO") FrontGoodsBrandVO frontGoodsBrandVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		//총건수
		frontGoodsBrandVO.setRowCount(service.getGdGoodsBrandListCount(frontGoodsBrandVO));
		
		//브랜드 목록
		List<GdGoodsBrand> brandList = service.getGdGoodsBrandList(frontGoodsBrandVO);
		model.addAttribute("brandList", brandList);
		
		//베스트 브랜드
		frontGoodsBrandVO.setSbest("Y");
		List<GdGoodsBrand> bestList = service.getGdGoodsBrandList(frontGoodsBrandVO);
		model.addAttribute("bestList", bestList);
		
		return "/shop/goods/designer";
	}
	
	/**
	 * HOT100
	 * @param frontGoodsVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="hot")
	public String hot(@ModelAttribute("frontGoodsVO") FrontGoodsVO frontGoodsVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		frontGoodsVO.setM_no(userInfo.getM_no());
		frontGoodsVO.setSchSpec("H");
		frontGoodsVO.setPageSize(100);
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schSpec", frontGoodsVO.getSchSpec());
		
		//상단 슬라이드
		param.put("category", "");
		param.put("sort", "RAND()");
		param.put(CommonConstants.PAGE_SIZE, 5);
		param.put(CommonConstants.ROW_START, 0);
		frontGoodsVO.setSlideGoodsList(service.getFrontGoodsList(param));
		
		//브랜드 리스트
		param.put("schType", StringUtil.N2S(frontGoodsVO.getSchType()));
		frontGoodsVO.setFrontGoodsGoodsBrandList(service.getFrontGoodsGoodsBrandList(param));
		
		//회원번호
		param.put("m_no", frontGoodsVO.getM_no());
		//브랜드
		param.put("brands", "".equals(StringUtil.N2S(frontGoodsVO.getBrands())) ? "" : frontGoodsVO.getBrands().split(","));
		//사이즈(옵션)
		param.put("options", "".equals(StringUtil.N2S(frontGoodsVO.getOptions())) ? "" : frontGoodsVO.getOptions().split(","));
		// sort
		if ("".equals( StringUtil.N2S(frontGoodsVO.getSort())))
			frontGoodsVO.setSort("goodsno desc");
		param.put("sort", frontGoodsVO.getSort());
		
		//상품 총카운트 조회
		frontGoodsVO.setRowCount(service.getFrontGoodsTotCnt(param));
				
		// 페이징
		param.put(CommonConstants.PAGE_SIZE, frontGoodsVO.getPageSize());
		param.put(CommonConstants.ROW_START, frontGoodsVO.getRowStart());
		
		//상품 목록 조회
		frontGoodsVO.setFrontGoodsList(service.getFrontGoodsList(param));
		
		return "/shop/goods/hot";
	}
	
	/**
	 * VIP
	 * @param frontGoodsVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="vip")
	public String vip(@ModelAttribute("frontGoodsVO") FrontGoodsVO frontGoodsVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		frontGoodsVO.setM_no(userInfo.getM_no());
		frontGoodsVO.setSchSpec("V");
		if (frontGoodsVO.getCategory() == null || "".equals(frontGoodsVO.getCategory())) {
			frontGoodsVO.setCategory("002");
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schSpec", frontGoodsVO.getSchSpec());
		
		//카테고리 리스트
		String category = frontGoodsVO.getCategory();
		if (category.length() > 6)
			category = category.substring(0, 6);
		
		param.put("category", category);
		List<GdCategory> categoryList = service.getFrontGoodsGoodsCategoryList(param);
		frontGoodsVO.setFrontGoodsGoodsCategoryList(categoryList);
		
		//카테고리 정보
		param.put("category", StringUtil.N2S(frontGoodsVO.getCategory()));
		GdCategory cateInfo = service.getFrontGoodsGoodsCategoryInfo(param);
		model.addAttribute("cateInfo", cateInfo);
		
		//브랜드 리스트
		frontGoodsVO.setFrontGoodsGoodsBrandList(service.getFrontGoodsGoodsBrandList(param));
		
		//회원번호
		param.put("m_no", frontGoodsVO.getM_no());
		//브랜드
		param.put("brands", "".equals(StringUtil.N2S(frontGoodsVO.getBrands())) ? "" : frontGoodsVO.getBrands().split(","));
		//사이즈(옵션)
		param.put("options", "".equals(StringUtil.N2S(frontGoodsVO.getOptions())) ? "" : frontGoodsVO.getOptions().split(","));
		// sort
		if ("".equals( StringUtil.N2S(frontGoodsVO.getSort())))
			frontGoodsVO.setSort("goodsno desc");
		param.put("sort", frontGoodsVO.getSort());
		
		//상품 총카운트 조회
		frontGoodsVO.setRowCount(service.getFrontGoodsTotCnt(param));
				
		// 페이징
		param.put(CommonConstants.PAGE_SIZE, frontGoodsVO.getPageSize());
		param.put(CommonConstants.ROW_START, frontGoodsVO.getRowStart());
		
		//상품 목록 조회
		frontGoodsVO.setFrontGoodsList(service.getFrontGoodsList(param));
		
		return "/shop/goods/vip";
	}
	
	/**
	 * SALE
	 * @param frontGoodsVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="sale")
	public String sale(@ModelAttribute("frontGoodsVO") FrontGoodsVO frontGoodsVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		frontGoodsVO.setM_no(userInfo.getM_no());
		if (frontGoodsVO.getCategory() == null || "".equals(frontGoodsVO.getCategory())) {
			frontGoodsVO.setCategory("002");
		}
		if (frontGoodsVO.getSale() == null || "".equals(frontGoodsVO.getSale())) {
			frontGoodsVO.setSale("0");
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schSpec", frontGoodsVO.getSchSpec());
		
		//카테고리 리스트
		String category = frontGoodsVO.getCategory();
		if (category.length() > 6)
			category = category.substring(0, 6);
		
		param.put("category", category);
		List<GdCategory> categoryList = service.getFrontGoodsGoodsCategoryList(param);
		frontGoodsVO.setFrontGoodsGoodsCategoryList(categoryList);
		
		//카테고리 정보
		param.put("category", StringUtil.N2S(frontGoodsVO.getCategory()));
		GdCategory cateInfo = service.getFrontGoodsGoodsCategoryInfo(param);
		model.addAttribute("cateInfo", cateInfo);
		
		//브랜드 리스트
		param.put("sale", frontGoodsVO.getSale());
		frontGoodsVO.setFrontGoodsGoodsBrandList(service.getFrontGoodsGoodsBrandList(param));
		
		//회원번호
		param.put("m_no", frontGoodsVO.getM_no());
		//브랜드
		param.put("brands", "".equals(StringUtil.N2S(frontGoodsVO.getBrands())) ? "" : frontGoodsVO.getBrands().split(","));
		//사이즈(옵션)
		param.put("options", "".equals(StringUtil.N2S(frontGoodsVO.getOptions())) ? "" : frontGoodsVO.getOptions().split(","));
		// sort
		if ("".equals( StringUtil.N2S(frontGoodsVO.getSort())))
			frontGoodsVO.setSort("priceRate desc");
		param.put("sort", frontGoodsVO.getSort());
		
		//상품 총카운트 조회
		frontGoodsVO.setRowCount(service.getFrontGoodsTotCnt(param));
				
		// 페이징
		param.put(CommonConstants.PAGE_SIZE, frontGoodsVO.getPageSize());
		param.put(CommonConstants.ROW_START, frontGoodsVO.getRowStart());
		
		//상품 목록 조회
		frontGoodsVO.setFrontGoodsList(service.getFrontGoodsList(param));
		
		return "/shop/goods/sale";
	}
	
	/**
	 * NEW
	 * @param frontGoodsVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="new")
	public String newArrival(@ModelAttribute("frontGoodsVO") FrontGoodsVO frontGoodsVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		frontGoodsVO.setM_no(userInfo.getM_no());
		
		if ("".equals(frontGoodsVO.getSchSpec())) {
			frontGoodsVO.setSchSpec("N");
		}
		
		//카테고리 리스트
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schSpec", frontGoodsVO.getSchSpec());
		
		param.put("category", "");
		List<GdCategory> categoryList = service.getFrontGoodsGoodsCategoryList(param);
		frontGoodsVO.setFrontGoodsGoodsCategoryList(categoryList);
		
		//브랜드 리스트
		param.put("category", frontGoodsVO.getCategory());
		frontGoodsVO.setFrontGoodsGoodsBrandList(service.getFrontGoodsGoodsBrandList(param));
		
		//회원번호
		param.put("m_no", frontGoodsVO.getM_no());
		//브랜드
		param.put("brands", "".equals(StringUtil.N2S(frontGoodsVO.getBrands())) ? "" : frontGoodsVO.getBrands().split(","));
		//사이즈(옵션)
		param.put("options", "".equals(StringUtil.N2S(frontGoodsVO.getOptions())) ? "" : frontGoodsVO.getOptions().split(","));
		// sort
		if ("".equals( StringUtil.N2S(frontGoodsVO.getSort())))
			frontGoodsVO.setSort("goodsno desc");
		param.put("sort", frontGoodsVO.getSort());
		
		//상품 총카운트 조회
		frontGoodsVO.setRowCount(service.getFrontGoodsTotCnt(param));
				
		// 페이징
		param.put(CommonConstants.PAGE_SIZE, frontGoodsVO.getPageSize());
		param.put(CommonConstants.ROW_START, frontGoodsVO.getRowStart());
		
		//상품 목록 조회
		frontGoodsVO.setFrontGoodsList(service.getFrontGoodsList(param));
		
		return "/shop/goods/new";
	}
	
}
