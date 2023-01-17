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
package com.wepinit.wepinit_shop.xmall.admin.controller.basic;

import com.wepinit.wepinit_shop.xmall.admin.service.basic.ExchangeService;
import com.wepinit.wepinit_shop.xmall.admin.vo.basic.ExchangeVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/shop/admin/basic/*")
public class ExchangeController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExchangeController.class);
	
	@Resource
	ExchangeService service;
	
	@RequestMapping(value = "exchange")
	public String exchange(@ModelAttribute("exchangeVO") ExchangeVO exchangeVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		exchangeVO.setKr(ShopConfig.getProperty("exchange_kr"));
		exchangeVO.setEn(ShopConfig.getProperty("exchange_en"));
		exchangeVO.setCn(ShopConfig.getProperty("exchange_cn"));
		
		Map<String, Object> param = new HashMap<String, Object>();
		ArrayList<String> sregdt = exchangeVO.getSregdt();
		
		if (sregdt != null && sregdt.size() > 0) {
			for (int i = 0; i < sregdt.size(); i++) {
				if (i == 0) {
					if (sregdt.get(i).length() > 0) {
						exchangeVO.setSregdt0(sregdt.get(i));	
					}
				} else {
					if (sregdt.get(i).length( ) >0) {
						exchangeVO.setSregdt1(sregdt.get(i));
					}
				}
			}
		}
		
		//총건수
		exchangeVO.setRowCount(service.exchangeCount(exchangeVO));		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, exchangeVO.getPageSize());		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, exchangeVO.getRowStart());

		exchangeVO.setExchangeList(service.exchangeList(exchangeVO));
		
		logger.debug("@@ exchange " + exchangeVO.toString());
		
		return "/shop/admin/basic/exchange";
	}
	
	@RequestMapping(value = "exchange/indb")
	public String exchangeIndb(@ModelAttribute("exchangeVO") ExchangeVO exchangeVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ShopConfig.setProperty("exchange_kr", exchangeVO.getKr());
		ShopConfig.setProperty("exchange_en", exchangeVO.getEn());
		ShopConfig.setProperty("exchange_cn", exchangeVO.getCn());
		
		service.insertExchange(exchangeVO);	
		logger.debug("@@ exchangeIndb " + exchangeVO.toString());
		
		return "redirect:/shop/admin/basic/exchange?menuKey=170";
	}
	
}
