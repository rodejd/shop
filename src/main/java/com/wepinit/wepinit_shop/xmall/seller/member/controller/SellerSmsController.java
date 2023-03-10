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
	 * 3-1. SMS?????? > SMS????????? 
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

		// ?????? ????????? ??????
		sellerSmsFM.setGdMemberGrpList(service.getGdMemberGrpList(sellerSmsFM)); // ?????? ????????? ??????

		return "seller/member/sendSms";
	}
	
	/**
	 * 3-2. SMS?????? > SMS????????? (??????????????? ??????)
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

		// ????????????
		param.put("schType", sellerSmsFM.getSchType());
		
		// ?????????
		param.put("schWord", sellerSmsFM.getSchWord());
		
		//?????????
		sellerSmsFM.setRowCount(memberService.getMemberSrchCount(param));
		
		// ????????? ?????? ?????????
		param.put(CommonConstants.PAGE_SIZE, sellerSmsFM.getPageSize());
		
		// ?????? ROW ??????
		param.put(CommonConstants.ROW_START, sellerSmsFM.getRowStart());
		
		// ?????? ????????? ??????
		sellerSmsFM.setGdMemberList(memberService.getMemberSrchList(param)); // ?????????????????????

		return "/shop/seller/member/popup_srch_member";
	}
	
	/**
	 * 3-3. SMS?????? > SMS????????? (SMS????????? ??????)
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
		
		// ????????????
		param.put("skey", smsSendVO.getSkey());
		
		// ?????????
		param.put("sword", smsSendVO.getSword());

		
		//?????????
		smsSendVO.setRowCount(smsService.getGdSmsAddressCount(param));
		
		// ????????? ?????? ?????????
		param.put(CommonConstants.PAGE_SIZE, smsSendVO.getPageSize());
		
		// ?????? ROW ??????
		param.put(CommonConstants.ROW_START, smsSendVO.getRowStart());
		
		// ?????? ????????? ??????
		smsSendVO.setGdSmsAddressList(smsService.getGdSmsAddressList(param)); 	
		// SMS???????????????
		

		return "/shop/seller/member/popup_srch_address";
	}
	
	/**
	 * 3-4. SMS?????? > SMS????????? (SMS?????????????????????)
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
		
		// SMS???????????? ????????????
		param.put("category", sellerSmsFM.getCategory());
		
		
		//?????????
		sellerSmsFM.setRowCount(service.getGdSmsSampleCount(param));
		
		// ????????? ?????? ?????????
		param.put(CommonConstants.PAGE_SIZE, 8);
		
		// ?????? ROW ??????
		param.put(CommonConstants.ROW_START, sellerSmsFM.getRowStart());
		
		// ?????? ????????? ??????
		sellerSmsFM.setGdSmsSampleList(service.getGdSmsSampleList(param)); 								// SMS????????????
		
		return "seller/member/sendSms";
	}*/
	
	/**
	 * 3-5. SMS?????? > SMS?????? ??????/?????? (SMS?????????????????????)
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

		// ??????/?????? ???
		if( "modify".equals(sellerSmsFM.getMode()) ){
			sellerSmsFM.setSmsSampleObj( service.getSmsSampleInfo(sellerSmsFM));
		}

		return "seller/member/register";
	}
	
	/**
	 * 3-6. SMS?????? > SMS?????????(???????????? ??????/?????? ??????????????????)
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
	 * 3-7. SMS?????? > SMS?????????(SMS????????? ??????????????????)
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
		//?????? ???????????????
		int total = service.insertSendSms(sellerSmsFM, sellerCd);
		model.addAttribute("total", total);

		return "dojson";
	}
	
	/**
	 * 3-4. SMS?????? > SMS????????? (SMS?????????????????????)
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
		
		// SMS???????????? ????????????
		param.put("category", sellerSmsFM.getCategory());
		
		
		//?????????
		sellerSmsFM.setRowCount(service.getGdSmsSampleCount(param));
		
		// ????????? ?????? ?????????
		param.put(CommonConstants.PAGE_SIZE, 8);
		
		// ?????? ROW ??????
		param.put(CommonConstants.ROW_START, sellerSmsFM.getRowStart());
		
		// ?????? ????????? ??????
		sellerSmsFM.setGdSmsSampleList(service.getGdSmsSampleList(param)); 								// SMS????????????
		
		return "/shop/seller/member/examList";
	}
	
	
}
