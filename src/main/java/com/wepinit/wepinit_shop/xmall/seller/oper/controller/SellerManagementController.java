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
import com.wepinit.wepinit_shop.xmall.seller.oper.service.SellerManagementService;
import com.wepinit.wepinit_shop.xmall.seller.oper.vo.SellerManagementFM;
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

@Controller
@RequestMapping(value="/shop/seller/oper")
public class SellerManagementController {
	
	private static final Logger logger = LoggerFactory.getLogger(SellerManagementController.class);
	
	@Resource
	SellerManagementService service;

	/**
	 * 업체정보관리
	 * @param SellerManagementVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sellerManagement")
	public String sellerManagement(@ModelAttribute("managementFM")SellerManagementFM managementFM,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		
		//세션에서 관리자코드받아오기
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		
		String sellerCd = sellerSO.getUserInfo().getSellerCd();
		
		if("".equals(sellerCd) || null == sellerCd) {
			
		} else {
		
			managementFM.setSellerCd(sellerCd);
		
			model.addAttribute("managementFM", service.getSellerManagementView(managementFM));

		}
		
		return "seller/oper/sellerManagement";
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
	@RequestMapping(value = "/resetPwd", method=RequestMethod.POST)
	public String updatePwd(@ModelAttribute("managementFM")SellerManagementFM managementFM, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		//세션에서 관리자코드받아오기
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);

		String user = sellerSO.getUserInfo().getUserId();

		managementFM.setDiv("R"); // 초기화 : R 변경 : C
		managementFM.setModuser(user);

		service.updatePwd(managementFM);

		return "dojson";
	}
	
	/**
	 * 비밀번호 변경
	 * @param managementFM
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/changePwd", method=RequestMethod.POST)
	public String changePwd(@ModelAttribute("managementFM")SellerManagementFM managementFM, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		//세션에서 관리자코드받아오기
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);

		String user = sellerSO.getUserInfo().getUserId();

		managementFM.setDiv("C"); // 초기화 : R 변경 : C
		managementFM.setModuser(user);

		service.updatePwd(managementFM);

		return "dojson";
	}
	

	/**
	 * 승인요청
	 * @param managementFM
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/reqApprov", method=RequestMethod.POST)
	public String reqApprov(@ModelAttribute("managementFM")SellerManagementFM managementFM,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		
		if(logger.isDebugEnabled()){
			logger.debug("@@sellerManagement indb");
		}

		//세션에서 관리자코드받아오기
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		
		String sellerCd = sellerSO.getUserInfo().getSellerCd();
		String user = sellerSO.getUserInfo().getUserId();

		managementFM.setStatus("R");
		managementFM.setModuser(user);
		managementFM.setSellerCd(sellerCd);

		service.getSellerManagementModify(managementFM);
		
		model.addAttribute("managementFM", service.getSellerManagementView(managementFM));

		return "redirect:/shop/seller/oper/sellerManagement";
	}
	
	/**
	 * 판매사 수정
	 * @param managementFM
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/Modify", method=RequestMethod.POST)
	public String modify(@ModelAttribute("managementFM")SellerManagementFM managementFM,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		
		if(logger.isDebugEnabled()){
			logger.debug("@@sellerManagement indb");
		}

		//세션에서 관리자코드받아오기
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		
		String sellerCd = sellerSO.getUserInfo().getSellerCd();
		String user = sellerSO.getUserInfo().getUserId();

		managementFM.setModuser(user);
		managementFM.setSellerCd(sellerCd);

		service.getSellerManagementModify(managementFM);
		
		model.addAttribute("managementFM", service.getSellerManagementView(managementFM));

		return "redirect:/shop/seller/oper/sellerManagement";
	}

	
	
}
