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
package com.wepinit.wepinit_shop.xmall.front.controller.event;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.event.EventService;
import com.wepinit.wepinit_shop.xmall.admin.vo.event.SurveyVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.UserInfo;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.session.ShopSessionObject;
import com.wepinit.wepinit_shop.xmall.front.service.event.FrontEventService;
import com.wepinit.wepinit_shop.xmall.front.vo.event.FrontEventVO;
import com.wepinit.wepinit_shop.xmall.front.vo.event.FrontSurveyCommentVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/shop/event/")
public class FrontEventController {

	private static final Logger logger = LoggerFactory.getLogger(FrontEventController.class); 
	
	@Resource
	FrontEventService service;
	
	@Resource
	EventService eventService;
	
	@RequestMapping(value="eventList")
	public String eventList(@ModelAttribute("frontEventVO") FrontEventVO frontEventVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("eventList>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		
		
		//이벤트 목록
		Map<String, Object> param = new HashMap<String, Object>();
		
		//총건수
		frontEventVO.setRowCount( service.getFrontEventCount(param) );
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, frontEventVO.getPageSize() );
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, frontEventVO.getRowStart());
		
		//이벤트목록
		frontEventVO.setFrontEventList( service.getFrontEventList(param) );
		
		
		return "/shop/event/eventList";
	}
	
	@RequestMapping(value="eventDetail")
	public String eventDetail(@ModelAttribute("frontEventVO") FrontEventVO frontEventVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("eventDetail>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		//1.회원리스트조회
		
		/*ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		UserInfo oUserInfoLogin = null;
		
		if(logger.isDebugEnabled()){
			logger.debug("oUserInfoLogin" + oUserInfoLogin);
		}
		
		if (shopSO.isShopLogin()){
			oUserInfoLogin = shopSO.getUserInfo();
			
			cooperationVO.setName(oUserInfoLogin.getUserId());
			cooperationVO.setEmail((String)oUserInfoLogin.getXkey().get("email"));
		}*/
		
		//1.이벤트상세
		frontEventVO.setFrontEventObj( service.getFrontEventObj(frontEventVO) );
		
		frontEventVO.setPageSize(5);
		
		//2.이벤트메모
		Map<String, Object> param = new HashMap<String, Object>();
		
		//이벤트글번호
		param.put("sno", frontEventVO.getSno() );
		
		//이벤트메모 총건수
		frontEventVO.setRowCount( service.getFrontEventMemoCount(param) );
		
		//페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, frontEventVO.getPageSize() );
				
		//시작 ROW 번호
		param.put(CommonConstants.ROW_START, frontEventVO.getRowStart());
				
		//이벤트메모 목록
		frontEventVO.setFrontEventMemoList( service.getFrontEventMemoList(param) );
		
		return "/shop/event/eventDetail";
	}
	
	@RequestMapping(value="eventEndList")
	public String eventEndList(@ModelAttribute("frontEventVO") FrontEventVO frontEventVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("eventEndList>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		
		
		//종료이벤트 목록
		Map<String, Object> param = new HashMap<String, Object>();
		
		//총건수
		frontEventVO.setRowCount( service.getFrontEventEndCount(param) );
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, frontEventVO.getPageSize() );
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, frontEventVO.getRowStart());
		
		//종료이벤트목록
		frontEventVO.setFrontEventEndList( service.getFrontEventEndList(param) );
		
		
		return "/shop/event/eventEndList";
	}
	
	@RequestMapping(value="eventEndDetail")
	public String eventEndDetail(@ModelAttribute("frontEventVO") FrontEventVO frontEventVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("eventDetail>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		//1.회원리스트조회
		
		/*ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		UserInfo oUserInfoLogin = null;
		
		if(logger.isDebugEnabled()){
			logger.debug("oUserInfoLogin" + oUserInfoLogin);
		}
		
		if (shopSO.isShopLogin()){
			oUserInfoLogin = shopSO.getUserInfo();
			
			cooperationVO.setName(oUserInfoLogin.getUserId());
			cooperationVO.setEmail((String)oUserInfoLogin.getXkey().get("email"));
		}*/
		
		//1.이벤트상세
		frontEventVO.setFrontEventObj( service.getFrontEventObj(frontEventVO) );
		
		frontEventVO.setPageSize(5);
		
		//2.이벤트메모
		Map<String, Object> param = new HashMap<String, Object>();
		
		//이벤트글번호
		param.put("sno", frontEventVO.getSno() );
		
		//이벤트메모 총건수
		frontEventVO.setRowCount( service.getFrontEventMemoCount(param) );
		
		//페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, frontEventVO.getPageSize() );
				
		//시작 ROW 번호
		param.put(CommonConstants.ROW_START, frontEventVO.getRowStart());
				
		//이벤트메모 목록
		frontEventVO.setFrontEventMemoList( service.getFrontEventMemoList(param) );
		
		return "/shop/event/eventEndDetail";
	}
	
	@RequestMapping(value="indb.dojson", method=RequestMethod.POST)
	public String indb(@ModelAttribute("frontEventVO") FrontEventVO frontEventVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("indb>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		
		ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		
		if (!shopSO.isShopLogin()){
			throw new BizException("login.99999");
		}
		
		String mode = frontEventVO.getMode();
		
		//이벤트댓글처리
		Map<String, Object> param = new HashMap<String, Object>();
		
		//이벤트글번호
		param.put("sno", frontEventVO.getSno() );
		
		//코멘트 입력
		if("cmtAdd".equals(mode)){
			/*requestSet.setProperty("m_id", rtmemList.get("m_id"));
			requestSet.setProperty("profile", rtmemList.get("profile"));
			dbHandler.executeXmlUpdate("xmall_event/event_MEMO_INSERT", requestSet);*/
			
			//이벤트 글번호
			param.put("sno", frontEventVO.getSno() );
			
			//이벤트 댓글번호
			param.put("mid",  shopSO.getUserInfo().getUserId());
			
			//이벤트 댓글내용
			param.put("body", frontEventVO.getBody() );
			
			service.insertEventMemo(param);
		//코멘트 삭제
		}else if("cmtDel".equals(mode)){
			/*requestSet.setProperty("eno", requestSet.getProperty("eno",""));
			dbHandler.executeXmlUpdate("xmall_event/event_MEMO_DELETE", requestSet);*/
			
			//이벤트 댓글번호
			param.put("eno", frontEventVO.getEno() );
			
			service.deleteEventMemo(param);
		//코멘트 수정
		}else if("cmtMod".equals(mode)){
			/*requestSet.setProperty("eno", requestSet.getProperty("eno",""));
			String eno=requestSet.getProperty("eno","");
			requestSet.setProperty("profile", rtmemList.get("profile"));
			requestSet.setProperty("body1", requestSet.getProperty("body1_"+eno,""));
			dbHandler.executeXmlUpdate("xmall_event/event_MEMO_UPDATE", requestSet);*/
			
			
			//이벤트 댓글번호
			param.put("eno", frontEventVO.getEno() );
			
			//이벤트 변경댓글
			param.put("body1", frontEventVO.getBody1() );
			
			service.updateEventMemo(param);
		}
		
		model.addAttribute("sno", frontEventVO.getSno());
		
		return "dojson";
	}
	@RequestMapping(value="surveyList")
	public String eventList(@ModelAttribute("surveyVO") SurveyVO surveyVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("surveyList>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		
		
		//이벤트 목록
		Map<String, Object> param = new HashMap<String, Object>();
		
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, surveyVO.getPageSize());
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, surveyVO.getRowStart());
		
		//이벤트목록
		surveyVO.setSurveyList( service.getFrontSurveyList(param));
		//총건수
		surveyVO.setRowCount( surveyVO.getSurveyList().size() );
		
		
		return "/shop/event/surveyList";
	}
	
	@RequestMapping(value="surveyEndList" ,method=RequestMethod.GET)
	public String surveyEndList(@ModelAttribute("surveyVO") SurveyVO surveyVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("surveyEndList>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		
		
		//종료이벤트 목록
		Map<String, Object> param = new HashMap<String, Object>();
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, surveyVO.getPageSize() );
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, surveyVO.getRowStart());
		
		//종료이벤트목록
		surveyVO.setSurveyList( service.getFrontSurveyEndList(param) );
		//총건수
		logger.debug("size : " + surveyVO.getSurveyList().size());
		surveyVO.setRowCount(surveyVO.getSurveyList().size());
		
		
		return "/shop/event/surveyEndList";
	}
	
	@RequestMapping(value="surveyDetail" ,method=RequestMethod.GET)
	public String surveyDetail(@ModelAttribute("surveyVO") SurveyVO surveyVO, HttpServletRequest req, HttpServletResponse res ,Model model){
		
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		int mno = userInfo.getM_no();
		
		//parameter 로 넘어온 sno 는 SurveySno 설문번호에 해당하는 파라미터
		String sno = req.getParameter("sno");
		
		//설문 종료 페이지와 설문조사중인 페이지를 한페이지로 만들고 구분하기위해 mode에 설정
		/* mode detail = 설문조사중 
		 * mode endSurvey = 설문종료
		 */
		String mode = StringUtil.nvl(req.getParameter("mode"), "detail");
		
		//중복투표를 막기위한 투표여부 검사하기 위한 service
		int survey = service.surveyOnOff(sno,mno);
		boolean surveyOnOff = survey > 0 ?  false : true;
		logger.debug("surveyOnOff:" + surveyOnOff);
		
		surveyVO = eventService.getDetailSurvey(sno);
		
		//댓글 리스트 
		surveyVO.setFrontSurveyCommentList(service.getFrontSurveyCommentList(sno));
		
		model.addAttribute("surveyMode" ,mode);
		model.addAttribute("surveyVO", surveyVO);
		model.addAttribute("surveyOnOff" ,surveyOnOff);
		
		return "/shop/event/surveyDetail";
	}
	
	@RequestMapping(value="surveyEndDetail" ,method=RequestMethod.GET)
	public String surveyEndDetail(@ModelAttribute("surveyVO") SurveyVO surveyVO, HttpServletRequest req ,HttpServletResponse res ,Model model){
		
		return "/shop/event/surveyEndDetail";
	}
	
	@RequestMapping(value="sureyIndb" ,method=RequestMethod.POST)	
	public String sureyIndb(@ModelAttribute SurveyVO surveyVO ,HttpServletRequest req) throws Exception{
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		
		//중복투표가 가능하게 해야되는지 대해서도 생각을 해봐야 함.
		
		//form 안에 surveyNum은 하나값만 가지고 컨트롤러에 보내는데 
		//VO 객체에 담으면 surveyNum 이란 배열이 있기때문에 배열에 담기기 때문에 requestParam 으로 String에 담음.
		String surveyNum = req.getParameter("surveyNumber");
		//
		Map<String,Object> param = new HashMap<String,Object> ();
		
		param.put("surveySno",surveyVO.getSurveySno());
		param.put("surveyNum",surveyNum);
		param.put("userNo", userInfo.getM_no());
		
		/*
		 * param surveySno : 설문일련번호
		 * param surveyNum : 투표질문의 index
		 * param userNo    : 회원 구분을 위한 회원번호 
		 */
		
		//투표한 회원의 정보를 저장 gd_survey_member
		service.updateSurveyMember(param);
		
		//투표수 증가 gd_survey_question
		service.updateSurveyCnt(param);
		
		return "redirect:/shop/event/surveyDetail?sno=" + surveyVO.getSurveySno();
	}
	
	@RequestMapping(value="surveyIndb.dojson", method=RequestMethod.POST)
	public String indb(@ModelAttribute("frontEventVO") FrontSurveyCommentVO frontSurveyCommentVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("indb>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		
		ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		
		if (!shopSO.isShopLogin()){
			throw new BizException("login.99999");
		}
		//삽입,수정, 삭제
		String mode = frontSurveyCommentVO.getMode();
		//설문조사 댓글처리
		Map<String, Object> param = new HashMap<String, Object>();
		
		//설문조사 글 번호 
		param.put("surveySno", frontSurveyCommentVO.getSurveySno() );
		
		/*
		 * suerveySno = 설문조사 글 번호
		 * sno = 댓글번호 
		 */
		//코멘트 입력
		if("cmtAdd".equals(mode)){
			//설문조사 댓글 아이디
			param.put("id",  shopSO.getUserInfo().getUserId());
			//설문조사 댓글내용
			param.put("memo", frontSurveyCommentVO.getMemo() );
			service.insertSurveyMemo(param);
		//코멘트 삭제
		}else if("cmtDel".equals(mode)){
			//설문조사 댓글번호
			String sno = StringUtil.nvl(frontSurveyCommentVO.getSno(), "0");
			service.deleteSurveyMemo(sno);
			
		//코멘트 수정
		}else if("cmtMod".equals(mode)){
			//설문조사 댓글 번호
			param.put("sno", frontSurveyCommentVO.getSno() );
			//설문조사 수정 댓글
			param.put("modifyMemo", frontSurveyCommentVO.getModfiyMemo() );
			param.put("id", shopSO.getUserInfo().getUserId());
			service.updateSurveyMemo(param);
		}
		
		//surveyDetail 에 넘기는 설문조사 글 번호
		model.addAttribute("sno", frontSurveyCommentVO.getSurveySno());
		
		return "dojson";
	}
	
	
	@RequestMapping(value = "open")
	public String open(HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		

		return "/shop/event/open";
	}
}
