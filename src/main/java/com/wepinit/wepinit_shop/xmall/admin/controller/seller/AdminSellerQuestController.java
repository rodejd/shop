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
package com.wepinit.wepinit_shop.xmall.admin.controller.seller;

import com.wepinit.wepinit_shop.xmall.admin.service.seller.AdminSellerQuestService;

import com.wepinit.wepinit_shop.xmall.admin.vo.seller.AdminSellerQuestAnswerFM;
import com.wepinit.wepinit_shop.xmall.common.session.AdminSessionObject;
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
@RequestMapping(value="shop/admin/seller/*")
public class AdminSellerQuestController {
	private static final Logger logger = LoggerFactory.getLogger(AdminSellerQuestController.class);
	@Resource
	AdminSellerQuestService service;
	
	@RequestMapping(value = "sellerQuestList")
	public String sellerQuestList(@ModelAttribute AdminSellerQuestAnswerFM adminSellerQuestAnswerFM, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		String returnUrl = "";
		returnUrl = service.sellerQuestList(adminSellerQuestAnswerFM);
		
		return returnUrl;
	}
	@RequestMapping(value ="questDelete")
	public String questDelete(@ModelAttribute AdminSellerQuestAnswerFM adminSellerQuestAnswerFM,String[] delcheck, HttpServletRequest req, HttpServletResponse res, Model model)  throws Exception {
		
		String returnUrl = "";
		returnUrl = service.questDelete(adminSellerQuestAnswerFM,delcheck);
		
		return returnUrl;
	}
	@RequestMapping(value = "adminanswer")
	public String adminanswer(@ModelAttribute AdminSellerQuestAnswerFM adminSellerQuestAnswerFM,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		String returnUrl = "";
		returnUrl = service.adminanswer(adminSellerQuestAnswerFM);
			
		return returnUrl;
	}
		
	@RequestMapping(value = "adminQuest")
	public String adminQuest(@ModelAttribute AdminSellerQuestAnswerFM adminSellerQuestAnswerFM ,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		

		return "/shop/admin/seller/adminQuest";
		
	}
	
	@RequestMapping(value = "adminQuestindb")
	public String adminQuestindb(@ModelAttribute AdminSellerQuestAnswerFM adminSellerQuestAnswerFM ,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		AdminSessionObject adminSO = AdminSessionObject.getSessionObject(req);
		//관리자 아이디 세션에서 가져온다
		String userId=adminSO.getUserInfo().getUserId();
		adminSellerQuestAnswerFM.getQuestionVO().setRegId(userId);
		
		String returnUrl = "";
		returnUrl =	service.insertAdminQuest(adminSellerQuestAnswerFM);

		return returnUrl;
	}
	@RequestMapping(value = "answerIndb")
	public String answerIndb(@ModelAttribute AdminSellerQuestAnswerFM adminSellerQuestAnswerFM , HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		AdminSessionObject adminSO = AdminSessionObject.getSessionObject(req);
		//관리자 아이디 세션에서 가져온다
		String adminId=adminSO.getUserInfo().getUserId();
		String returnUrl = "";
		returnUrl =	service.insertAdminAnswer(adminSellerQuestAnswerFM, adminId);
		
		return returnUrl;
		
	}
	
	
//	@RequestMapping(value = "answerindb")
//	public String answerindb(@ModelAttribute AdminSellerQuestAnswerFM adminSellerQuestAnswerFM ,String indbType,HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
//		//세션에서 관리자아이디 받아오기
//		int sellerCorrect = 0;
//		//판매자가 질문할 경우 sellerCd = admin
//		String sellerCd = "admin";
//		adminSellerQuestAnswerFM.setSellerCd(sellerCd);
//		
//		sellerCorrect =service.getSellerCorrect(adminSellerQuestAnswerFM);
//		
//		//경로가 올바르지 않을경우 익셉션처리
//		if(sellerCorrect==0){
//			throw new BizException("common.00004");
//		}
//		
//		AdminSessionObject adminSO = AdminSessionObject.getSessionObject(req);
//		String userId=adminSO.getUserInfo().getUserId();
//		adminSellerQuestAnswerFM.getAnswerVO().setRegId(userId);
//		//contents 내용, sellerNoticeFM.getSchType()  qno , sellerNoticeFM.getSchType() sellerNoticeFM.getSchText() 번호
//		if("save".equals(indbType)){
//			service.insertAdminAnswer(adminSellerQuestAnswerFM);
//		}else if("modi".equals(indbType)){
//			service.updateAdminAnswer(adminSellerQuestAnswerFM);
//		}else{
//			throw new BizException("common.00004");
//		}
//				
//		model.addAttribute(adminSellerQuestAnswerFM);
//		return "redirect:/shop/admin/seller/sellerQuestList";
//	}
	
	
}
