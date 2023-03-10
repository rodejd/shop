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
	 * 1. SMS?????? > SMS????????????/??????
	 * 
	 * */
	
	/**
	 * 1-1. SMS?????? > SMS????????????/??????(??????)
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

		// SMS??????????????????
		smsAutoVO.setGdSmsAutoSetList(service.getGdSmsAutoSetList(smsAutoVO));
		return "/shop/admin/member/sms/auto";
	}
	
	/**
	 * 1-2. SMS?????? > SMS????????????/??????(??????????????????)
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

		//1. SMS??????????????? ??????
		ShopConfig.setProperty("smsPass", req.getParameter("smsPass"));
		
		//2. ????????????
		ShopConfig.setProperty("smsRecall", StringUtil.implode("-", req.getParameterValues("smsRecall[]")));
		
		//3. ??????????????????
		ShopConfig.setProperty("smsAdmin", StringUtil.implode("-", req.getParameterValues("smsAdmin[]")));
		
		//4.???????????????
		String[] smsAddAdmin1 = req.getParameterValues("smsAddAdmin1[]");
		String[] smsAddAdmin2 = req.getParameterValues("smsAddAdmin2[]");
		String[] smsAddAdmin3 = req.getParameterValues("smsAddAdmin3[]");
		String[] smsAddAdmin = new String[smsAddAdmin1.length];
		for(int i=0;i<smsAddAdmin1.length;i++){
			smsAddAdmin[i] = smsAddAdmin1[i] + "-" + smsAddAdmin2[i] + "-" + smsAddAdmin3[i]; 
		}
		
		ShopConfig.setProperty("smsAddAdmin", StringUtil.implode("|",smsAddAdmin));
		
		//5.SMS????????????/????????????
		
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
	 * 2. SMS?????? > SMS?????????
	 * 
	 * */
	
	/**
	 * 2-1. SMS????????? ?????????
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
		
		// SMS????????? ????????????
		smsAddressVO.setSmsAddressGroupList(service.getSmsAddressGroupList()); 
		

		Map<String, Object> param = new HashMap<String, Object>();

		// ????????????
		param.put("skey", smsAddressVO.getSkey());
		
		// ?????????
		param.put("sword", smsAddressVO.getSword());
		
		// ??????
		param.put("slevel", smsAddressVO.getSlevel());
		
		// ?????????
		param.put("sregdt_0", smsAddressVO.getSregdt() != null ? smsAddressVO.getSregdt()[0] : "" );
		param.put("sregdt_1", smsAddressVO.getSregdt() != null ? smsAddressVO.getSregdt()[1] : "" );
		
		// ??????
		param.put("sex", smsAddressVO.getSex());
		
		// sort
		param.put("sort", smsAddressVO.getSort());
		
		
		//?????????
		smsAddressVO.setRowCount(service.getGdSmsAddressCount(param));
		
		// ????????? ?????? ?????????
		param.put(CommonConstants.PAGE_SIZE, smsAddressVO.getPageSize());
		
		// ?????? ROW ??????
		param.put(CommonConstants.ROW_START, smsAddressVO.getRowStart());
		
		// SMS ????????? ?????????
		smsAddressVO.setSmsAddressList(service.getGdSmsAddressList(param)); 								// ????????????????????? 

		return "/shop/admin/member/sms/address";
	}
	
	@RequestMapping(value = "popup_sms_address")
	public String popupSmsAddress(@ModelAttribute("smsAddressVO") SmsAddressVO smsAddressVO,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms popup_sms_address!!!");
		}
		
		// SMS????????? ????????????
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
	 * 3. SMS?????? > SMS?????????
	 * 
	 * */
	
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
	@RequestMapping(value = "send")
	public String send(@ModelAttribute("smsSendVO") SmsSendVO smsSendVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms send!!!");
		}

		// ?????? ????????? ??????
		smsSendVO.setGdMemberGrpList(service.getGdMemberGrpList(smsSendVO)); 														// ?????? ????????? ??????

		return "/shop/admin/member/sms/send";
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
	public String popupSrchMember(@ModelAttribute("smsSendVO") SmsSendVO smsSendVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms popup_srch_member!!!" + smsSendVO);
		}
		
		Map<String, Object> param = new HashMap<String, Object>();

		// ????????????
		param.put("schType", smsSendVO.getSchType());
		
		// ?????????
		param.put("schWord", smsSendVO.getSchWord());
		
		
		//?????????
		smsSendVO.setRowCount(memberService.getMemberSrchCount(param));
		
		// ????????? ?????? ?????????
		param.put(CommonConstants.PAGE_SIZE, smsSendVO.getPageSize());
		
		// ?????? ROW ??????
		param.put(CommonConstants.ROW_START, smsSendVO.getRowStart());
		
		// ?????? ????????? ??????
		smsSendVO.setGdMemberList(memberService.getMemberSrchList(param)); 								// ?????????????????????
		

		return "/shop/admin/member/sms/popup_srch_member";
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
		smsSendVO.setRowCount(service.getGdSmsAddressCount(param));
		
		// ????????? ?????? ?????????
		param.put(CommonConstants.PAGE_SIZE, smsSendVO.getPageSize());
		
		// ?????? ROW ??????
		param.put(CommonConstants.ROW_START, smsSendVO.getRowStart());
		
		// ?????? ????????? ??????
		smsSendVO.setGdSmsAddressList(service.getGdSmsAddressList(param)); 								// SMS???????????????
		

		return "/shop/admin/member/sms/popup_srch_address";
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
	@RequestMapping(value = "list")
	public String list(@ModelAttribute("smsSendVO") SmsSendVO smsSendVO,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms list!!!");
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		// SMS???????????? ????????????
		param.put("category", smsSendVO.getCategory());
		
		
		//?????????
		smsSendVO.setRowCount(service.getGdSmsSampleCount(param));
		
		// ????????? ?????? ?????????
		param.put(CommonConstants.PAGE_SIZE, 8);
		
		// ?????? ROW ??????
		param.put(CommonConstants.ROW_START, smsSendVO.getRowStart());
		
		// ?????? ????????? ??????
		smsSendVO.setGdSmsSampleList(service.getGdSmsSampleList(param)); 								// SMS????????????
		
		return "/shop/admin/member/sms/list";
	}
	
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
	public String sampleReg(@ModelAttribute("smsSendVO") SmsSendVO smsSendVO,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		// ??????/?????? ???
		if( "modify".equals(smsSendVO.getMode()) ){
			smsSendVO.setSmsSampleObj( service.getSmsSampleInfo(smsSendVO));
		}
		
		
		
		return "/shop/admin/member/sms/register";
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
	public String sendSms(@ModelAttribute("smsSendVO") SmsSendVO smsSendVO,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sendSms!!!" + smsSendVO);
		}
		
		
		//????????? ??????
		String adminCd = ""; 
		
		//?????? ????????? ??????
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

		// ????????????
		param.put("schType", sellerAddressVO.getSchType());
		
		// ?????????
		param.put("schWord", sellerAddressVO.getSchWord());
		
		
		//?????????
		sellerAddressVO.setRowCount(service.getSellerAddressGroupListCount(param));
		
		// ????????? ?????? ?????????
		param.put(CommonConstants.PAGE_SIZE, sellerAddressVO.getPageSize());
		
		// ?????? ROW ??????
		param.put(CommonConstants.ROW_START, sellerAddressVO.getRowStart());
		
		// ????????? ??????
		sellerAddressVO.setSellerAddressGroupList(service.getSellerAddressGroupList(param));

		return "/shop/admin/member/sms/popup_srch_seller";
	}
	
}
