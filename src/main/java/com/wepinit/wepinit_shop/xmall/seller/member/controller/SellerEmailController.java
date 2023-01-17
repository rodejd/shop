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

import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import com.wepinit.wepinit_shop.xmall.seller.member.service.SellerEmailService;
import com.wepinit.wepinit_shop.xmall.seller.member.vo.SellerEmailFM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/shop/seller/member")
public class SellerEmailController {

	private static final Logger logger = LoggerFactory.getLogger(SellerEmailController.class); 

	@Resource
	SellerEmailService service;

	/**
	 * 이메일발송리스트
	 * @param sellerEmailFM
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "emailList")
	public String emailList(@ModelAttribute("sellerEmailFM") SellerEmailFM sellerEmailFM
			, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>mail list!!!" + sellerEmailFM);
		}
		
		model.addAttribute("sellerEmailFM", service.getSellerEmailList(sellerEmailFM)); // Email리스트

		return "seller/member/emailList";
	}
	
	/**
	 * 이메일발송 페이지
	 * @param sellerEmailFM
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "sendEmail")
	public String sendEmail(@ModelAttribute("sellerEmailFM") SellerEmailFM sellerEmailFM
			, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sendEmail!!!" + sellerEmailFM);
		}
		
//		model.addAttribute("sellerEmailFM", service.getSellerEmailList(sellerEmailFM)); // Email리스트

		return "seller/member/sendEmail";
	}
	
	/**
	 * 메일보내기
	 * @param sellerEmailFM
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("testMail")
	public String testMail(@ModelAttribute("sellerEmailFM") SellerEmailFM sellerEmailFM
			, HttpServletRequest req, HttpServletResponse res, Model model)  throws Exception {
		
		String type = "direct";//req.getParameter("type");
		String size = req.getParameter("size");
		String toEmail = req.getParameter("toEmail");
		String toEmailArray = req.getParameter("toEmail");

		String[] toEmails = toEmail.split(",");
		for(int ix=0;ix<toEmails.length;ix++){
			if(ix==0){
				toEmailArray="'"+toEmails[ix]+"'";
			}else{
				toEmailArray+=",'"+toEmails[ix]+"'";
			}
		}

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("toEmail", toEmailArray);

		String subject = req.getParameter("subject");
		String contents = req.getParameter("contents");
		String errorMsg = "";

		List<GdMember> list = null;
		
		param.put("subject", subject);
		param.put("contents", contents);
		param.put("fromname", ShopConfig.getProperty("shopName"));
		param.put("fromemail", ShopConfig.getProperty("adminEmail"));
		
		if("direct".equals(type)){
			String[] emailArr = toEmail.split(",");
			for(int i = 0; i<emailArr.length; i++){
				// 회원 이메일인지 체크
//				list = memberService.getEmailChkList(emailArr[i]);
//				if (list.size() <= 0){
//					errorMsg=emailArr[i]+"은 유효하지 않은 메일입니다";
//				} else {
					param.put("toemail", emailArr[i]);
//					param.put("toname", list.get(0).getName());
					param.put("toname", "test@test.co.kr");
					service.insertGdEmailer(param);
//				}
				// toEmail(CSV) - hyunsoo@naver.com,naiive@hotmail.com,hyunsoo@gmail.com (검증된 회원이메일)
			}
		}
//		else{
//			//회원번호 받음
//			//회원번호 이메일 조회
//			//이메일로 이메일 발생
//			list = memberService.getEmailChkListMno(param);
//			int i = list.size(); // 1부터 시작
//			if(i > 0){
//				toEmail = "";
//				for(int ixl=0;ixl<i;ixl++){
//					if(ixl == 0){
//						toEmail += list.get(ixl).getEmail(); // 0부터 시작
//					}else{
//						toEmail += ","+list.get(ixl).getEmail();	
//					}
//					param.put("toemail", list.get(ixl).getEmail());
//					param.put("toname", list.get(ixl).getName());
//					memberService.insertGdEmailer(param);
//				}	
//			}
//			
//		}
		
			// toEmail(CSV) - hyunsoo@naver.com,naiive@hotmail.com,hyunsoo@gmail.com (검증된 회원이메일)
		// subject, contents 를 받음
	logger.debug("subject==>"+subject);
	logger.debug("contents==>"+contents);
	model.addAttribute("errorMsg", errorMsg);
	model.addAttribute("toEmail", toEmail);
	model.addAttribute("type", type);
	model.addAttribute("size", size);
	model.addAttribute("ListToString", req.getParameter("toEmail"));
	model.addAttribute("subject", subject);
	model.addAttribute("contents", contents);
		return "seller/member/sendEmail";
	}
	
}
