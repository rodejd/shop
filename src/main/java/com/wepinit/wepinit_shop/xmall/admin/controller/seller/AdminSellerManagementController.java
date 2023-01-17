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

import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.seller.AdminSellerManagementService;
import com.wepinit.wepinit_shop.xmall.admin.vo.seller.AdminSellerManagementFM;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
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
public class AdminSellerManagementController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminSellerManagementController.class);
	
	@Resource
	AdminSellerManagementService service;

	/**
	 * 판매사 목록 조회 (신청)
	 * @param adminSellerManagementFM
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="list")
	public String SellerManagement(@ModelAttribute("managementFM") AdminSellerManagementFM managementFM,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		/*
		 * 판매사 관리권한 
		 *
		 */
		ShopLibFunction.menuAuthPermissionCheck(req, res, "seller");
		
		managementFM.setStatus("W"); // 상태(S:승인, R:승인요청, W:대기, X:탈퇴)
		
		model.addAttribute("managementFM", service.getSellerManagementList(managementFM));
		
		return "/shop/admin/seller/sellerManagementList";
	}

	/**
	 * 상품문의관리 등록/수정 페이지
	 * @param adminSellerManagementVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="register")
	public String ManagementRegister(@ModelAttribute("managementFM")AdminSellerManagementFM managementFM,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		
		model.addAttribute("managementFM", service.getSellerManagementView(managementFM));

		return "/shop/admin/seller/sellerManagementRegister";
	}
	
	/**
	 * 아이디 중복체크 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "chkId")
	public String chkId(@ModelAttribute("managementFM")AdminSellerManagementFM managementFM,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		service.getDuplicationIdCount(managementFM);
		
		return "/shop/admin/seller/sellerManagementRegister";
	}
	
	/**
	 * 판매자코드 중복체크 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "chkSellerCd")
	public String chkSellerCd(@ModelAttribute("managementFM")AdminSellerManagementFM managementFM,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		service.getDuplicationSellerCdCount(managementFM);
		
		return "/shop/admin/seller/sellerManagementRegister";
	}
	
	/**
	 * 비밀번호 초기화
	 * @param managementFM
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "resetPwd")
	public String resetPwd(@ModelAttribute("managementFM")AdminSellerManagementFM managementFM,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		service.updatePwd(managementFM);

		return "/shop/admin/seller/sellerManagementRegister";
	}

	/**
	 * 등록, 수정, 삭제
	 * @param adminSellerManagementVO
	 * @param req
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="indb")
	public String indb(@ModelAttribute("managementFM")AdminSellerManagementFM managementFM,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		
		String returnUrl = "";
		
		managementFM.setReguser(AdminSessionObject.getSessionObject().getUserInfo().getUserId());
		managementFM.setModuser(AdminSessionObject.getSessionObject().getUserInfo().getUserId());
		
		returnUrl = service.getSellerManagementProc(managementFM);
		
		return returnUrl;
	}

	/**
	 * 판매자 목록 리스트
	 * @param managementFM
	 * @param req
	 * @param res
	 * @param mode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="confirmList")
	public String ConfirmSellerManagement(@ModelAttribute("managementFM")AdminSellerManagementFM managementFM,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		managementFM.setStatus("S"); // 상태(S:승인, R:승인요청, W:대기, X:탈퇴)
		
		model.addAttribute("managementFM", service.getSellerManagementList(managementFM));

		return "/shop/admin/seller/sellerManagementConfirm";
	}

	/**
	 * 탈퇴목록 리스트
	 * @param adminSellerManagementVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="leaveList")
	public String LeaveSellerManagement(@ModelAttribute("managementFM")AdminSellerManagementFM managementFM,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		managementFM.setStatus("X"); // 상태(S:승인, R:승인요청, W:대기, X:탈퇴)
		
		model.addAttribute("managementFM", service.getSellerManagementList(managementFM));

		return "/shop/admin/seller/sellerManagementLeave";
	}

	/**
	 * 아이디 발급저장
	 * @param adminSellerManagementVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveId")
	public String saveId(@ModelAttribute("managementFM")AdminSellerManagementFM managementFM,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		String returnUrl = "";
		
		returnUrl = service.getSaveId(managementFM);
				
		return returnUrl;
	}

	/**
	 * 팝업 판매자
	 * @param sellerCd
	 * @param adminSellerManagementVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="sellerManagementPopup")
	public String PopupSellerManagement(@ModelAttribute("managementFM")AdminSellerManagementFM managementFM,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("@@SellerManagement ==> get" + managementFM);
		}

		managementFM = service.getSellerManagementView(managementFM);
		
		model.addAttribute("managementFM", managementFM);
		
		return "/shop/admin/seller/sellerManagementPopup";
	}
	
	
	/**
	 * 엑셀 download
	 * @param orderExcelVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "sellerExcelDown")
	public String orderExcelDown(@ModelAttribute("managementFM")AdminSellerManagementFM managementFM,
			HttpServletRequest req, HttpServletResponse res, Model model)
					throws Exception {
		
		res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		req.setCharacterEncoding("UTF-8");
		res.setHeader("Content-Disposition","attachment;filename=GDSeller_"+ DateUtil.getDate("yyyyMMddHHmm")+".xls");
		res.setHeader("Content-Description", "JSP Generated Data");
		res.setHeader("Content-Description", "style=mso-number-format:'\\@'");

		managementFM.setStatus("W"); // 상태(S:승인, R:승인요청, W:대기, X:탈퇴)
		
		managementFM.setSellerList(service.getSellerXls(managementFM));

		return "/shop/admin/seller/sellerDnXls";
	}
	
	
}
