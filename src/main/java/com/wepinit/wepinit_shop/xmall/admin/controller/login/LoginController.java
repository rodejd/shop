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
package com.wepinit.wepinit_shop.xmall.admin.controller.login;

import com.wepinit.wepinit_shop.xmall.admin.service.login.LoginService;
import com.wepinit.wepinit_shop.xmall.admin.vo.login.LoginVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.session.AdminSessionObject;
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
@RequestMapping("/shop/admin")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class); 
	
	@Resource
	LoginService service;
	
	@RequestMapping(value={"/login/login",""})
	public String login(@ModelAttribute("loginVO") LoginVO loginVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
		if(logger.isDebugEnabled()){
			logger.debug("uclee login");
		}
		
		return "shop/admin/login/login";
	}
	
	@RequestMapping(value="/login/login_check.dojson" , method=RequestMethod.POST)
	public String login_check(@ModelAttribute("loginVO") LoginVO loginVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
		if(logger.isDebugEnabled()){
			logger.debug("# login_check.dojson");
		}
		
		AdminSessionObject admSO = AdminSessionObject.getSessionObject(req);

		admSO.setSessionObject(service.process(loginVO, req));
		
		return "dojson";
	}
	
	@RequestMapping(value="/login/logout")
	public String logout(@ModelAttribute("loginVO") LoginVO loginVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("uclee logout");
		}
		
		AdminSessionObject admSO = AdminSessionObject.getSessionObject(req);
		
		admSO.removeSession();

		return /*"redirect:" + */CommonConstants.LOGIN_ADMIN_URL;
	}
}
