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
package com.wepinit.wepinit_shop.xmall.admin.controller.member;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.member.MemberService;
import com.wepinit.wepinit_shop.xmall.admin.service.member.SmsService;

import com.wepinit.wepinit_shop.xmall.admin.vo.member.SellerAddressVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.SmsAddressVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.SmsAutoVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.SmsSendVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.vo.GdSmsAutoSet;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shop/admin/member/sms/*")
public class SmsController {

	private static final Logger logger = LoggerFactory.getLogger(SmsController.class);

	@Resource
	SmsService service;
	
	@Resource
	MemberService memberService;
	
	/*
	 * 1. SMS설정 > SMS자동발송/설정
	 * 
	 * */
	
	/**
	 * 1-1. SMS설정 > SMS자동발송/설정(조회)
	 * 
	 * @param smsAutoVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "auto")
	public String auto(@ModelAttribute("smsAutoVO") SmsAutoVO smsAutoVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms auto!!!");
		}

		// SMS자동발송설정
		smsAutoVO.setGdSmsAutoSetList(service.getGdSmsAutoSetList(smsAutoVO));
		return "/shop/admin/member/sms/auto";
	}
	
	/**
	 * 1-2. SMS설정 > SMS자동발송/설정(프로세스처리)
	 * 
	 * @param smsAutoVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "auto_indb")
	public String autoIndb(@ModelAttribute("smsAutoVO") SmsAutoVO smsAutoVO,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms indb!!!");
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms req!!!" + req.getParameter("smsPass"));
			//logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms smsAutoVO!!!" + smsAutoVO);
		}

		//1. SMS관리자정보 입력
		ShopConfig.setProperty("smsPass", req.getParameter("smsPass"));
		
		//2. 회신전화
		ShopConfig.setProperty("smsRecall", StringUtil.implode("-", req.getParameterValues("smsRecall[]")));
		
		//3. 관리자핸드폰
		ShopConfig.setProperty("smsAdmin", StringUtil.implode("-", req.getParameterValues("smsAdmin[]")));
		
		//4.추가관리자
		String[] smsAddAdmin1 = req.getParameterValues("smsAddAdmin1[]");
		String[] smsAddAdmin2 = req.getParameterValues("smsAddAdmin2[]");
		String[] smsAddAdmin3 = req.getParameterValues("smsAddAdmin3[]");
		String[] smsAddAdmin = new String[smsAddAdmin1.length];
		for(int i=0;i<smsAddAdmin1.length;i++){
			smsAddAdmin[i] = smsAddAdmin1[i] + "-" + smsAddAdmin2[i] + "-" + smsAddAdmin3[i]; 
		}
		
		ShopConfig.setProperty("smsAddAdmin", StringUtil.implode("|",smsAddAdmin));
		
		//5.SMS자동발송/문구설정
		
		List<GdSmsAutoSet> list = service.getGdSmsAutoSetList(smsAutoVO);
		
		for(int i=0;i<list.size();i++){
			GdSmsAutoSet vo = list.get(i);
			
			smsAutoVO.setSmsType( vo.getSmsType() );			
			smsAutoVO.setMsgC( req.getParameter("auto["+vo.getSmsType()+"]['msg_c']") );			
			smsAutoVO.setSendC( req.getParameter("auto["+vo.getSmsType()+"]['send_c']") );			
			smsAutoVO.setMsgA( req.getParameter("auto["+vo.getSmsType()+"]['msg_a']") );			
			smsAutoVO.setSendA( req.getParameter("auto["+vo.getSmsType()+"]['send_a']") );			
			smsAutoVO.setSendM( req.getParameter("auto["+vo.getSmsType()+"]['send_m']") );			
						
			service.updateSmsAutoSet(smsAutoVO);
		}

		return "redirect:/shop/admin/member/sms/auto";
	}
	
	/*
	 * 2. SMS설정 > SMS주소록
	 * 
	 * */
	
	/**
	 * 2-1. SMS주소록 리스트
	 * 
	 * @param smsAddressVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "address")
	public String address(@ModelAttribute("smsAddressVO") SmsAddressVO smsAddressVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms address!!!");
		}
		
		// SMS주소록 그룹조회
		smsAddressVO.setSmsAddressGroupList(service.getSmsAddressGroupList()); 
		

		Map<String, Object> param = new HashMap<String, Object>();

		// 검색타입
		param.put("skey", smsAddressVO.getSkey());
		
		// 검색어
		param.put("sword", smsAddressVO.getSword());
		
		// 그룹
		param.put("slevel", smsAddressVO.getSlevel());
		
		// 등록일
		param.put("sregdt_0", smsAddressVO.getSregdt() != null ? smsAddressVO.getSregdt()[0] : "" );
		param.put("sregdt_1", smsAddressVO.getSregdt() != null ? smsAddressVO.getSregdt()[1] : "" );
		
		// 성별
		param.put("sex", smsAddressVO.getSex());
		
		// sort
		param.put("sort", smsAddressVO.getSort());
		
		
		//총건수
		smsAddressVO.setRowCount(service.getGdSmsAddressCount(param));
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, smsAddressVO.getPageSize());
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, smsAddressVO.getRowStart());
		
		// SMS 주소록 리스트
		smsAddressVO.setSmsAddressList(service.getGdSmsAddressList(param)); 								// 회원주소록조회 

		return "/shop/admin/member/sms/address";
	}
	
	@RequestMapping(value = "popup_sms_address")
	public String popupSmsAddress(@ModelAttribute("smsAddressVO") SmsAddressVO smsAddressVO,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms popup_sms_address!!!");
		}
		
		// SMS주소록 그룹조회
		smsAddressVO.setSmsAddressGroupList(service.getSmsAddressGroupList()); 
		
		if( "modify".equals( smsAddressVO.getMode() ) ){
			smsAddressVO.setSmsAddressObj(service.getGdSmsAddressInfo(smsAddressVO));
		}
		
		
		return "/shop/admin/member/sms/popup_sms_address";
	}
	
	@RequestMapping(value = "address_indb")
	public String addressIndb(@ModelAttribute("smsAddressVO") SmsAddressVO smsAddressVO,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		String url = "";

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms address_indb!!!" + smsAddressVO);
		}
		
		
		if( "register".equals(smsAddressVO.getMode()) ){
			service.insertSmsAddress(smsAddressVO);
			
			url = "/shop/admin/member/sms/popup_sms_address";
			
		}else if( "modify".equals(smsAddressVO.getMode()) ){
			service.updateSmsAddress(smsAddressVO);
			
			url = "/shop/admin/member/sms/popup_sms_address";
		}else if( "delete".equals(smsAddressVO.getMode()) ){
			service.deleteSmsAddress(smsAddressVO);
			
			url = "/shop/admin/member/sms/address";
		}
		
		model.addAttribute("result", 1);
		
		return url;
	}
	
	/*@RequestMapping(value = "sendSmsAddress.dojson" , method=RequestMethod.POST)
	public String sendSmsAddress(@ModelAttribute("smsAddressVO") SmsAddressVO smsAddressVO,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sendSmsAddress!!!" + smsAddressVO);
		}
		
		int total = service.insertSendSms(smsAddressVO);
		
		model.addAttribute("total", total);

		return "dojson";
	}*/
	
	
	/*
	 * 3. SMS설정 > SMS보내기
	 * 
	 * */
	
	/**
	 * 3-1. SMS설정 > SMS보내기 
	 * 
	 * @param smsSendVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "send")
	public String send(@ModelAttribute("smsSendVO") SmsSendVO smsSendVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms send!!!");
		}

		// 회원 그룹별 조회
		smsSendVO.setGdMemberGrpList(service.getGdMemberGrpList(smsSendVO)); 														// 회원 그룹별 조회

		return "/shop/admin/member/sms/send";
	}
	
	/**
	 * 3-2. SMS설정 > SMS보내기 (회원주소록 팝업)
	 * 
	 * @param smsSendVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "popup_srch_member")
	public String popupSrchMember(@ModelAttribute("smsSendVO") SmsSendVO smsSendVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms popup_srch_member!!!" + smsSendVO);
		}
		
		Map<String, Object> param = new HashMap<String, Object>();

		// 검색타입
		param.put("schType", smsSendVO.getSchType());
		
		// 검색어
		param.put("schWord", smsSendVO.getSchWord());
		
		
		//총건수
		smsSendVO.setRowCount(memberService.getMemberSrchCount(param));
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, smsSendVO.getPageSize());
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, smsSendVO.getRowStart());
		
		// 회원 그룹별 조회
		smsSendVO.setGdMemberList(memberService.getMemberSrchList(param)); 								// 회원주소록조회
		

		return "/shop/admin/member/sms/popup_srch_member";
	}
	
	/**
	 * 3-3. SMS설정 > SMS보내기 (SMS주소록 팝업)
	 * 
	 * @param smsSendVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "popup_srch_address")
	public String popupSrchAddress(@ModelAttribute("smsSendVO") SmsSendVO smsSendVO,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms popup_srch_address!!!");
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		// 검색타입
		param.put("skey", smsSendVO.getSkey());
		
		// 검색어
		param.put("sword", smsSendVO.getSword());

		
		//총건수
		smsSendVO.setRowCount(service.getGdSmsAddressCount(param));
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, smsSendVO.getPageSize());
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, smsSendVO.getRowStart());
		
		// 회원 그룹별 조회
		smsSendVO.setGdSmsAddressList(service.getGdSmsAddressList(param)); 								// SMS주소록조회
		

		return "/shop/admin/member/sms/popup_srch_address";
	}
	
	/**
	 * 3-4. SMS설정 > SMS보내기 (SMS문자메세지예제)
	 * 
	 * @param smsSendVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list")
	public String list(@ModelAttribute("smsSendVO") SmsSendVO smsSendVO,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms list!!!");
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		// SMS문자예제 카테고리
		param.put("category", smsSendVO.getCategory());
		
		
		//총건수
		smsSendVO.setRowCount(service.getGdSmsSampleCount(param));
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, 8);
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, smsSendVO.getRowStart());
		
		// 회원 그룹별 조회
		smsSendVO.setGdSmsSampleList(service.getGdSmsSampleList(param)); 								// SMS문자예제
		
		return "/shop/admin/member/sms/list";
	}
	
	/**
	 * 3-5. SMS설정 > SMS예제 등록/수정 (SMS문자메세지예제)
	 * 
	 * @param smsSendVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "register")
	public String sampleReg(@ModelAttribute("smsSendVO") SmsSendVO smsSendVO,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		// 등록/수정 폼
		if( "modify".equals(smsSendVO.getMode()) ){
			smsSendVO.setSmsSampleObj( service.getSmsSampleInfo(smsSendVO));
		}
		
		
		
		return "/shop/admin/member/sms/register";
	}
	
	/**
	 * 3-6. SMS설정 > SMS보내기(예제추가 등록/수정 프로세스처리)
	 * 
	 * @param smsAutoVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "sample_indb")
	public String sampleIndb(@ModelAttribute("smsSendVO") SmsSendVO smsSendVO,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms sample_indb!!!");
		}

		if( "register".equals(smsSendVO.getMode()) ){
			service.insertSmsSample(smsSendVO);
		}else if( "modify".equals(smsSendVO.getMode()) ){
			service.updateSmsSample(smsSendVO);
		}
		
		model.addAttribute("result", 1);

		return "/shop/admin/member/sms/register";
	}
	
	/**
	 * 3-7. SMS설정 > SMS보내기(SMS보내기 프로세스처리)
	 * 
	 * @param smsAutoVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "send.dojson" , method=RequestMethod.POST)
	public String sendSms(@ModelAttribute("smsSendVO") SmsSendVO smsSendVO,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sendSms!!!" + smsSendVO);
		}
		
		
		//관리자 코드
		String adminCd = ""; 
		
		//문자 보내기 기능
		int total = service.insertSendSms(smsSendVO,adminCd);
		model.addAttribute("total", total);

		return "dojson";
	}
	
	@RequestMapping(value = "popup_srch_seller")
	public String popupSrchSeller(@ModelAttribute("sellerAddressVO") SellerAddressVO sellerAddressVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms popup_srch_seller!!!" + sellerAddressVO);
		}
		
		Map<String, Object> param = new HashMap<String, Object>();

		// 검색타입
		param.put("schType", sellerAddressVO.getSchType());
		
		// 검색어
		param.put("schWord", sellerAddressVO.getSchWord());
		
		
		//총건수
		sellerAddressVO.setRowCount(service.getSellerAddressGroupListCount(param));
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, sellerAddressVO.getPageSize());
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, sellerAddressVO.getRowStart());
		
		// 판매사 조회
		sellerAddressVO.setSellerAddressGroupList(service.getSellerAddressGroupList(param));

		return "/shop/admin/member/sms/popup_srch_seller";
	}
	
}
