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
package com.wepinit.wepinit_shop.xmall.admin.controller.order;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.order.RepayService;
import com.wepinit.wepinit_shop.xmall.admin.vo.order.RepayVO;
import com.wepinit.wepinit_shop.xmall.common.vo.join.OrdercancelOrderitemMember;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/shop/admin/order/*")
public class RepayController {

	private static final Logger logger = LoggerFactory.getLogger(RepayController.class);

	
	@Resource
	RepayService service;
	
	@RequestMapping(value="repay")
	public String list(@ModelAttribute("repayVO") RepayVO repayVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

			if (logger.isDebugEnabled()) {
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>repay list!!!" + repayVO);
			}

			//총건수
			repayVO.setRowCount(service.getRepayCount());		
			List<OrdercancelOrderitemMember> repayList = service.getRepayList(repayVO);
			
			//판매사 배송비 중복적용을 체크를 위한 flagMap
			Map<String,Object> flagMap = new HashMap<String,Object>();
			//배송비 
			Map<String,Object> delivery = new HashMap<String,Object>();
			
			for(int i =0;i<repayList.size();i++){
				OrdercancelOrderitemMember oom = repayList.get(i);
				oom.setCcnt(service.getRepayList2(oom));
				oom.setOiocObject(service.getRepayList3(oom.getoCordno()));
				
				/*
				 * flagMap : sellerCd = 판매사 배송비 중복 적용 방지를 위한 판매사코드
				   			 refundPrice = 환불금액  */
				flagMap = service.calculateRefundPrice(repayList.get(i), flagMap);
				repayList.get(i).setRefundPrice(StringUtil.N2I(String.valueOf(flagMap.get("refundPrice"))));
				//delivery 총 배송비
				delivery = service.deliverySetting(repayList.get(i).getoIordno());
				repayList.get(i).setoRdelivery(
						repayList.get(i).getoRdelivery()+StringUtil.N2I(String.valueOf(delivery.get("delivery"))));
				repayList.get(i).setoRaddDelivery(
						repayList.get(i).getoRaddDelivery()+StringUtil.N2I(String.valueOf(delivery.get("addDelivery"))));
			}
			repayVO.setRepayList(repayList);		
		return "shop/admin/order/repay";
	}
	@RequestMapping(value="repay/indb")
	public String repaydb(@ModelAttribute("repayVO") RepayVO repayVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>repay indb!!!" + repayVO);
		}
		service.repaydb(repayVO,req);

		String returnUrl = "redirect:/shop/admin/order/repay";
		
		return returnUrl;
	}
}