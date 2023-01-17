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
/********************************************************************************
* <pre>
* 업무구분명			:	Community
* 세부업무구분명	: 	사용자 > Community
* 작성자 				: 	이병환
* 설명 				: 	사용자 Community Controller
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170208		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.controller.main;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xmall.front.service.main.FrontMainService;
import com.wepinit.wepinit_shop.xmall.front.vo.main.FrontMainVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/")
public class FrontMainController {

	private static final Logger logger = LoggerFactory.getLogger(FrontMainController.class); 
	
	@Resource
	FrontMainService service;
	
	@GetMapping(value="/test")
	public String test(@ModelAttribute("frontMainVO") FrontMainVO frontMainVO,
			HttpServletRequest req , HttpServletResponse res, Model model) throws Exception {

		logger.info("TEST");
		
		return "home";
	}

	@RequestMapping(value={"/shop/main/index", "/"})
	public String main(@ModelAttribute("frontMainVO") FrontMainVO frontMainVO,
			HttpServletRequest req , HttpServletResponse res, Model model) throws Exception {

		frontMainVO.setSkin(ConfigClass.getSkin(req));
		service.getMainService(frontMainVO);
		if(logger.isDebugEnabled()){
			logger.debug("bh3211>>>>>>>>>>>>>>>>>>>> frontMainVO.toString():{}",frontMainVO.toString());
		}

		return "shop/front/skin/kr/main/index";
	}
/*
	@RequestMapping(value={"/shop/main/index2", "/"})
	public String main2(@ModelAttribute("frontMainVO") FrontMainVO frontMainVO, 
			HttpServletRequest req , HttpServletResponse res, Model model) throws Exception {
		
		frontMainVO.setSkin(ConfigClass.getSkin(req));
		service.getMainService(frontMainVO);
		if(logger.isDebugEnabled()){
			logger.debug("bh3211>>>>>>>>>>>>>>>>>>>> frontMainVO.toString():{}",frontMainVO.toString());
		}
		
		return "/shop/main/index2";
	}*/
	
}
