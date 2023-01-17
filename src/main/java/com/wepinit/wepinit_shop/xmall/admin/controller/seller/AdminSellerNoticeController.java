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

import com.wepinit.wepinit_shop.xmall.admin.service.seller.AdminSellerNoticeService;

import com.wepinit.wepinit_shop.xmall.admin.vo.seller.AdminSellerNoticeFM;
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
public class AdminSellerNoticeController {
	private static final Logger logger = LoggerFactory.getLogger(AdminSellerNoticeController.class);
	
	@Resource
	AdminSellerNoticeService service;
	
	/**
	 * 판매사 공지사항 목록
	 * @param noticeFM
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "noticeList")
	public String sellerNoticeList(@ModelAttribute("noticeFM") AdminSellerNoticeFM noticeFM,
			HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {
		if (logger.isDebugEnabled()) logger.debug("11111111111111===" + AdminSessionObject.getSessionObject().getLoginValue("USER_ID"));
		
		model.addAttribute("noticeFM", service.getSellerNoticeList(noticeFM));
		
		return "/shop/admin/seller/noticeList";
	}
	
	/**
	 * 판매사 공지사항 상세
	 * @param noticeFM
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "noticeView")
	public String sellerNoticeView(@ModelAttribute("noticeFM") AdminSellerNoticeFM noticeFM,
			HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {
		
		// validation check 고유번호가 없을 경우 오류처리
		noticeFM.validNoticeView();
		
		// 공지사항 정보 조회
		noticeFM = service.getSellerNoticeView(noticeFM);
		
		
		return "/shop/admin/seller/noticeView";
	}
	
	/**
	 * 판매사 공지사항 등록/수정
	 * @param noticeFM
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "noticeInput")
	public String sellerNoticeModify(@ModelAttribute("noticeFM") AdminSellerNoticeFM noticeFM,
			HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {
		
		// 공지사항 고유번가 있을 경우만 조회한다.
		if ( !"".equals(noticeFM.getNoticeVO().getSno()) ) {
			noticeFM = service.getSellerNoticeView(noticeFM);
		}
		
		model.addAttribute("noticeFM", noticeFM);
		
		return "/shop/admin/seller/noticeInput";
	}
	
	/**
	 * 판매사 공지사항 처리
	 * 	- I : insert
	 * 	- M : modify
	 * 	- D : delete
	 * @param noticeFM
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "noticeProc")
	public String sellerNoticeInsert(@ModelAttribute("noticeFM") AdminSellerNoticeFM noticeFM,
			HttpServletRequest req, HttpServletResponse res, Model model) 
			throws Exception {
		
		// validation check
		noticeFM.validNoticeProc();
		
		// 등록/수정/삭제 처리
		noticeFM.getNoticeVO().setRegId(AdminSessionObject.getSessionObject().getUserInfo().getUserId());
		service.getSellerNoticeProc(noticeFM);
		
		return "redirect:/shop/admin/seller/noticeList";
	}
}
