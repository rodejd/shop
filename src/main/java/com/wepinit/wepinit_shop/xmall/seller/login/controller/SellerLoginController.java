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
package com.wepinit.wepinit_shop.xmall.seller.login.controller;

import com.wepinit.wepinit_shop.xmall.admin.service.login.LoginService;
import com.wepinit.wepinit_shop.xmall.admin.vo.login.LoginVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.session.SellerSessionObject;
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
@RequestMapping("/shop/seller")
public class SellerLoginController {

	private static final Logger logger = LoggerFactory.getLogger(SellerLoginController.class); 
	
	@Resource
	LoginService service;
	
	@RequestMapping(value={"/login",""})
	public String login(@ModelAttribute("loginVO") LoginVO loginVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
		return "shop/seller/login/login";
	}
	
	@RequestMapping(value="/login_check.dojson" , method=RequestMethod.POST)
	public String login_check(@ModelAttribute("loginVO") LoginVO loginVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);

		sellerSO.setSessionObject(service.process(loginVO, req));
		
		return "dojson";
	}
	
	@RequestMapping(value="/logout")
	public String logout(@ModelAttribute("loginVO") LoginVO loginVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
		if(logger.isDebugEnabled()){
			logger.debug("uclee logout");
		}
		
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		
		sellerSO.removeSession();

		return "redirect:" + CommonConstants.LOGIN_SELLER_URL;
	}
}
