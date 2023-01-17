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
import com.wepinit.wepinit_shop.xmall.admin.service.log.StatService;
import com.wepinit.wepinit_shop.xmall.admin.vo.log.*;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
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
@RequestMapping("/shop/admin/log/stat/*")
public class StatController {

	private static final Logger logger = LoggerFactory.getLogger(StatController.class);

	@Resource
	StatService service;
	
	@RequestMapping(value = "sale")
	public String sale(@ModelAttribute("statSaleVO") StatSaleVO statSaleVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>stat.sale!!!"+ statSaleVO.toString());
		}
		/*
		 * 통계/데이터관리 권한
		 */
		ShopLibFunction.menuAuthPermissionCheck(req, res, "log");

		statSaleVO.setYear(StringUtil.nvl(statSaleVO.getYear(), DateUtil.getDateString().substring(0, 4))); 				// 기간설정(년)
		statSaleVO.setMonth(StringUtil.nvl(statSaleVO.getMonth(), DateUtil.getDateString().substring(4, 6))); 			// 기간설정(월)
		statSaleVO.setLast(Integer.parseInt(DateUtil.getLastDay(statSaleVO.getYear() + statSaleVO.getMonth()))); 	// 조회월 마지막일

		// 매출통계 LIST 세팅
		statSaleVO.setStatSaleList(service.getStatSaleList(statSaleVO)); 														// 매출통계LIST

		return "/shop/admin/log/stat/sale";
	}

	@RequestMapping(value = "settlekind")
	public String settlekind(@ModelAttribute("statSettlekindVO") StatSettlekindVO statSettlekindVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>stat.settlekind!!!");
		}

		statSettlekindVO.setYear(StringUtil.nvl(statSettlekindVO.getYear(), DateUtil.getDateString().substring(0, 4))); 					// 기간설정(년)
		statSettlekindVO.setMonth(StringUtil.nvl(statSettlekindVO.getMonth(), DateUtil.getDateString().substring(4, 6))); 				// 기간설정(월)
		statSettlekindVO.setLast(Integer.parseInt(DateUtil.getLastDay(statSettlekindVO.getYear()+ statSettlekindVO.getMonth()))); 	// 조회월 마지막일

		statSettlekindVO.setStatSettlekindList(service.getStatSettlekindList(statSettlekindVO));												// 결제수단분석LIST

		return "/shop/admin/log/stat/settlekind";
	}

	@RequestMapping(value = "age")
	public String age(@ModelAttribute("statAgeVO") StatAgeVO statAgeVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>stat.age!!!");
		}

		statAgeVO.setYear(StringUtil.nvl(statAgeVO.getYear(), DateUtil.getDateString().substring(0, 4))); 				// 기간설정(년)
		statAgeVO.setMonth(StringUtil.nvl(statAgeVO.getMonth(), DateUtil.getDateString().substring(4, 6))); 			// 기간설정(월)
		statAgeVO.setLast(Integer.parseInt(DateUtil.getLastDay(statAgeVO.getYear() + statAgeVO.getMonth()))); 	// 조회월 마지막일

		statAgeVO.setStatAgeList(service.getStatAgeList(statAgeVO)); 														// 연령별매출분석LIST

		return "/shop/admin/log/stat/age";
	}
	
	@RequestMapping(value = "area")
	public String area(@ModelAttribute("statAreaVO") StatAreaVO statAreaVO,
			HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>stat.area!!!");
		}

		statAreaVO.setYear(StringUtil.nvl(statAreaVO.getYear(), DateUtil.getDateString().substring(0, 4))); 					//기간설정(년)
		statAreaVO.setMonth(StringUtil.nvl(statAreaVO.getMonth(), DateUtil.getDateString().substring(4, 6)));				//기간설정(월)
		statAreaVO.setLast(Integer.parseInt(DateUtil.getLastDay(statAreaVO.getYear() + statAreaVO.getMonth())));		//조회월 마지막일

		statAreaVO.setStatAreaList(service.getStatAreaList(statAreaVO));															//지역별매출분석LIST


		return "/shop/admin/log/stat/area";
	}

	@RequestMapping(value = "sex")
	public String sex(@ModelAttribute("statSexVO") StatSexVO statSexVO,
			HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>stat.sex!!!");
		}

		statSexVO.setYear(StringUtil.nvl(statSexVO.getYear(), DateUtil.getDateString().substring(0, 4))); 				//기간설정(년)
		statSexVO.setMonth(StringUtil.nvl(statSexVO.getMonth(), DateUtil.getDateString().substring(4, 6)));			//기간설정(월)
		statSexVO.setLast(Integer.parseInt(DateUtil.getLastDay(statSexVO.getYear() + statSexVO.getMonth())));		//조회월 마지막일

		statSexVO.setStatSexList(service.getStatSexList(statSexVO));															//성별매출분석LIST
		

		return "/shop/admin/log/stat/sex";
	}
	
}
