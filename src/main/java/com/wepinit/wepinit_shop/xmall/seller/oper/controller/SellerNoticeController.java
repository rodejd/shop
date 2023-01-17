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
package com.wepinit.wepinit_shop.xmall.seller.oper.controller;

import com.wepinit.wepinit_shop.xmall.common.session.SellerSessionObject;
import com.wepinit.wepinit_shop.xmall.seller.oper.service.SellerNoticeService;
import com.wepinit.wepinit_shop.xmall.seller.oper.vo.SellerNoticeFM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/shop/seller/oper")
public class SellerNoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(SellerNoticeController.class);
	
	@Resource
	SellerNoticeService service;
	
	@RequestMapping(value="/notice")
	public String operNotice(@ModelAttribute SellerNoticeFM sellerNoticeFM, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		return "seller/oper/notice";
	}
	@RequestMapping(value="/noticeList")
	public String noticeList(@ModelAttribute SellerNoticeFM sellerNoticeFM, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		String returnUrl="";
		returnUrl=service.getSellerNoticeList(sellerNoticeFM);

		return returnUrl;
	}
	@RequestMapping(value="/noticeView")
	public String noticeView(@ModelAttribute SellerNoticeFM sellerNoticeFM, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
	
		// validation check 고유번호가 없을 경우 오류처리
		sellerNoticeFM.validNoticeView();
				
		// 공지사항 정보 조회
		sellerNoticeFM = service.getSellerNoticeView(sellerNoticeFM);

		return "seller/oper/noticeView";
	}
	
	
	
	
	@RequestMapping(value="/sellerquest")
	public String sellerQuestion(@ModelAttribute SellerNoticeFM sellerNoticeFM, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		//세션에서 관리자코드받아오기
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		String sellerCd=sellerSO.getUserInfo().getSellerCd();
		
		String returnUrl="";
		
		returnUrl = service.sellerQuestion(sellerNoticeFM, sellerCd);
		
		
		return returnUrl;
	}
	
	@RequestMapping(value="/selleranswer")
	public String sellerAnswer(@ModelAttribute SellerNoticeFM sellerNoticeFM, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		//세션에서 관리자아이디 받아오기
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		String sellerCd=sellerSO.getUserInfo().getSellerCd();

		String returnUrl="";
		returnUrl=service.sellerAnswer(sellerNoticeFM,sellerCd);
		
		return returnUrl;
	}
	
	@RequestMapping(value="/answerindb")
	public String answerIndb(@ModelAttribute SellerNoticeFM sellerNoticeFM,String indbType, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		//세션에서 관리자아이디 받아오기
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		String userId=sellerSO.getUserInfo().getUserId();
		String sellerCd=sellerSO.getUserInfo().getSellerCd();
		
		String returnUrl="";

		returnUrl=service.answerIndb(sellerNoticeFM,userId,sellerCd);
		
		return returnUrl;
	}
	
	@RequestMapping(value="/sellerEmailQuest")
	public String sellerQuestionEmail(@ModelAttribute SellerNoticeFM sellerNoticeFM, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		//세션에서 관리자코드받아오기
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		String sellerCd=sellerSO.getUserInfo().getSellerCd();
		
		String returnUrl="";
		
		returnUrl = service.sellerEmailQuestion(sellerNoticeFM, sellerCd);
		
		logger.debug("판매자 이메일주소"+returnUrl);
		return returnUrl;
	}
	
	@RequestMapping(value="/emailSend")
	public String sellerEmailSend(@ModelAttribute SellerNoticeFM sellerNoticeFM, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		//세션에서 관리자코드받아오기
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		String sellerCd=sellerSO.getUserInfo().getSellerCd();
		
		String returnUrl="";
		
		returnUrl = service.sellerSendEmail(sellerNoticeFM, sellerCd);
		
		return returnUrl;
	}
}
