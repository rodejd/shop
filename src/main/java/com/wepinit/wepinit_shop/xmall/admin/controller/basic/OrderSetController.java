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

import com.wepinit.wepinit_shop.xmall.admin.vo.basic.OrderSetVO;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/shop/admin/basic/*")
public class OrderSetController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(OrderSetController.class);

	@RequestMapping(value = "order_set") //쇼핑몰기본관리_기본관리_주문설정 db에서읽어와서 뿌려줌
	public String OrderSet(@ModelAttribute("orderVO") OrderSetVO orderVO, HttpServletRequest req, HttpServletResponse res, Model model ) throws Exception{
		logger.debug("@@ order  :" + orderVO.toString());
		
		// 1 , 재고 삭감단계
		orderVO.setStepStock(ShopConfig.getProperty("stepStock"));
		// 2 , 주문자동취소 설정 
		orderVO.setAutoCancel(ShopConfig.getProperty("autoCancel"));
		// 3 ,  송장입력방법설정
		orderVO.setDeliveryBasis(ShopConfig.getProperty("delivery_basis"));
		// 4 , 결제 시도/실패 재결재 가능시간
		orderVO.setAutoCancelFail(ShopConfig.getProperty("autoCancelFail"));
		// 5 , 주문리스트 기본조회기간 설정
		orderVO.setOrderPeriod(ShopConfig.getProperty("orderPeriod"));
		// 6 , 주문리스트의 주문일 출력수  display:none 되어있음
		orderVO.setOrderPageNum(ShopConfig.getProperty("orderPageNum"));
		
		model.addAttribute("orderVO", orderVO);
		logger.debug("@@ order  :" + orderVO.toString());
		
		return "/shop/admin/basic/order_set";
	}
	@RequestMapping(value = "order_set/indb")
	public String OrderIndb(@ModelAttribute("orderVO") OrderSetVO orderVO,HttpServletRequest req, HttpServletResponse res, Model model ) throws Exception{
		
			ShopConfig.setProperty("stepStock",orderVO.getStepStock());
			ShopConfig.setProperty("delivery_basis",orderVO.getDeliveryBasis());
			ShopConfig.setProperty("autoCancel", orderVO.getAutoCancel());
			ShopConfig.setProperty("autoCancelFail", orderVO.getAutoCancelFail());
			ShopConfig.setProperty("orderPeriod", orderVO.getOrderPeriod());
			ShopConfig.setProperty("orderPageNum", orderVO.getOrderPageNum());
			
		return "redirect:/shop/admin/basic/order_set";
	}
	
		
}
