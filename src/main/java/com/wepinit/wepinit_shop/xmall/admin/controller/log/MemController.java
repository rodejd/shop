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
package com.wepinit.wepinit_shop.xmall.admin.controller.log;

import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.log.MemService;
import com.wepinit.wepinit_shop.xmall.admin.vo.log.MemAgeVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.log.MemAreaVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.log.MemNewVO;
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
@RequestMapping("/shop/admin/log/mem/*")
public class MemController {

	private static final Logger logger = LoggerFactory.getLogger(MemController.class);

	@Resource
	MemService service;
	
	@RequestMapping(value = "new")
	public String new_(@ModelAttribute("memNewVO") MemNewVO memNewVO,
			HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>mem.new!!!");
		}
		
		memNewVO.setYear(StringUtil.nvl(memNewVO.getYear(), DateUtil.getDateString().substring(0, 4))); 				//기간설정(년)
		memNewVO.setMonth(StringUtil.nvl(memNewVO.getMonth(), DateUtil.getDateString().substring(4, 6)));			//기간설정(월)
		//memNewVO.setLast(Integer.parseInt(DateUtil.getLastDay(memNewVO.getYear() + memNewVO.getMonth())));	//조회월 마지막일
		
		memNewVO.setMemNewList(service.getMemNewList(memNewVO));													//신규 회원분석LIST

		return "/shop/admin/log/mem/new";
	}
	
	@RequestMapping(value = "age")
	public String age(@ModelAttribute("memAgeVO") MemAgeVO memAgeVO,
			HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>mem.age!!!");
		}

		memAgeVO.setYear(StringUtil.nvl(memAgeVO.getYear(), DateUtil.getDateString().substring(0, 4))); 				// 기간설정(년)
		memAgeVO.setMonth(StringUtil.nvl(memAgeVO.getMonth(), DateUtil.getDateString().substring(4, 6))); 			// 기간설정(월)

		memAgeVO.setMemAgeList(service.getMemAgeList(memAgeVO)); 														// 연령별매출분석LIST

		return "/shop/admin/log/mem/age";
	}
	
	@RequestMapping(value = "area")
	public String area(@ModelAttribute("memAreaVO") MemAreaVO memAreaVO,
			HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>mem.age!!!");
		}

		memAreaVO.setYear(StringUtil.nvl(memAreaVO.getYear(), DateUtil.getDateString().substring(0, 4))); 				// 기간설정(년)
		memAreaVO.setMonth(StringUtil.nvl(memAreaVO.getMonth(), DateUtil.getDateString().substring(4, 6)));			// 기간설정(월)

		memAreaVO.setMemAreaList(service.getMemAreaList(memAreaVO)); 													// 연령별매출분석LIST

		return "/shop/admin/log/mem/area";
	}
	
}
