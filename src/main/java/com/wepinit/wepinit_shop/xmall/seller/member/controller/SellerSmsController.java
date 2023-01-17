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
package com.wepinit.wepinit_shop.xmall.seller.member.controller;

import com.wepinit.wepinit_shop.xmall.admin.service.member.MemberService;
import com.wepinit.wepinit_shop.xmall.admin.service.member.SmsService;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.SmsSendVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.session.SellerSessionObject;
import com.wepinit.wepinit_shop.xmall.seller.member.service.SellerSmsService;
import com.wepinit.wepinit_shop.xmall.seller.member.vo.SellerSmsFM;
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
@RequestMapping("/shop/seller/member")
public class SellerSmsController {

	private static final Logger logger = LoggerFactory.getLogger(SellerSmsController.class);

	@Resource
	SellerSmsService service;
	
	@Resource
	MemberService memberService;
	
	@Resource
	SmsService smsService;

	@RequestMapping(value = "smsList")
	public String address(@ModelAttribute("sellerSmsFM") SellerSmsFM sellerSmsFM
			,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms address!!!");
		}

		model.addAttribute("sellerSmsFM", service.getSellerSmsList(sellerSmsFM));

		return "seller/member/smsList";
	}
	
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
	@RequestMapping(value = "sendSms")
	public String send(@ModelAttribute("sellerSmsFM") SellerSmsFM sellerSmsFM
			, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms send!!!");
		}

		// 회원 그룹별 조회
		sellerSmsFM.setGdMemberGrpList(service.getGdMemberGrpList(sellerSmsFM)); // 회원 그룹별 조회

		return "seller/member/sendSms";
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
	public String popupSrchMember(@ModelAttribute("sellerSmsFM") SellerSmsFM sellerSmsFM
			, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms popup_srch_member!!!" + sellerSmsFM);
		}
		
		Map<String, Object> param = new HashMap<String, Object>();

		// 검색타입
		param.put("schType", sellerSmsFM.getSchType());
		
		// 검색어
		param.put("schWord", sellerSmsFM.getSchWord());
		
		//총건수
		sellerSmsFM.setRowCount(memberService.getMemberSrchCount(param));
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, sellerSmsFM.getPageSize());
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, sellerSmsFM.getRowStart());
		
		// 회원 그룹별 조회
		sellerSmsFM.setGdMemberList(memberService.getMemberSrchList(param)); // 회원주소록조회

		return "/shop/seller/member/popup_srch_member";
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
		smsSendVO.setRowCount(smsService.getGdSmsAddressCount(param));
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, smsSendVO.getPageSize());
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, smsSendVO.getRowStart());
		
		// 회원 그룹별 조회
		smsSendVO.setGdSmsAddressList(smsService.getGdSmsAddressList(param)); 	
		// SMS주소록조회
		

		return "/shop/seller/member/popup_srch_address";
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
	/*@RequestMapping(value = "sendSms")
	public String list(@ModelAttribute("sellerSmsFM") SellerSmsFM sellerSmsFM, 
			,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms list!!!");
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		// SMS문자예제 카테고리
		param.put("category", sellerSmsFM.getCategory());
		
		
		//총건수
		sellerSmsFM.setRowCount(service.getGdSmsSampleCount(param));
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, 8);
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, sellerSmsFM.getRowStart());
		
		// 회원 그룹별 조회
		sellerSmsFM.setGdSmsSampleList(service.getGdSmsSampleList(param)); 								// SMS문자예제
		
		return "seller/member/sendSms";
	}*/
	
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
	public String sampleReg(@ModelAttribute("sellerSmsFM") SellerSmsFM sellerSmsFM
			,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		// 등록/수정 폼
		if( "modify".equals(sellerSmsFM.getMode()) ){
			sellerSmsFM.setSmsSampleObj( service.getSmsSampleInfo(sellerSmsFM));
		}

		return "seller/member/register";
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
	public String sampleIndb(@ModelAttribute("sellerSmsFM") SellerSmsFM sellerSmsFM
			,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms sample_indb!!!");
		}

		if( "register".equals(sellerSmsFM.getMode()) ){
			service.insertSmsSample(sellerSmsFM);
		}else if( "modify".equals(sellerSmsFM.getMode()) ){
			service.updateSmsSample(sellerSmsFM);
		}
		
		model.addAttribute("result", 1);

		return "seller/member/register";
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
	public String sendSms(@ModelAttribute("sellerSmsFM") SellerSmsFM sellerSmsFM
			,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sendSms!!!" + sellerSmsFM);
		}
		
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		String sellerCd=sellerSO.getUserInfo().getSellerCd();
		//문자 보내기기능
		int total = service.insertSendSms(sellerSmsFM, sellerCd);
		model.addAttribute("total", total);

		return "dojson";
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
	@RequestMapping(value = "examList")
	public String list(@ModelAttribute("sellerSmsFM") SellerSmsFM sellerSmsFM,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms list!!!");
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		// SMS문자예제 카테고리
		param.put("category", sellerSmsFM.getCategory());
		
		
		//총건수
		sellerSmsFM.setRowCount(service.getGdSmsSampleCount(param));
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, 8);
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, sellerSmsFM.getRowStart());
		
		// 회원 그룹별 조회
		sellerSmsFM.setGdSmsSampleList(service.getGdSmsSampleList(param)); 								// SMS문자예제
		
		return "/shop/seller/member/examList";
	}
	
	
}
