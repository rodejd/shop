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
package com.wepinit.wepinit_shop.xmall.seller.basic.controller;

import com.wepinit.wepinit_shop.xmall.common.session.SellerSessionObject;
import com.wepinit.wepinit_shop.xmall.seller.basic.service.SellerDeliveryPolicyService;
import com.wepinit.wepinit_shop.xmall.seller.basic.vo.SellerDeliveryPolicyFM;
import com.wepinit.wepinit_shop.xmall.seller.basic.vo.SellerDeliveryPolicyInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shop/seller/basic")
public class SellerDeliveryPolicyController {
	private static final Logger logger = LoggerFactory.getLogger(SellerDeliveryPolicyController.class);
	
	@Resource
	SellerDeliveryPolicyService service;
	
	@RequestMapping(value="/delivery")
	public String delivery(@ModelAttribute(value="fm") SellerDeliveryPolicyFM selDeliveryPolicyFM, HttpServletRequest req) {
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		String sellerCd = sellerSO.getUserInfo().getSellerCd();
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("AdminSellerOrderController >> delivery()");
			logger.debug("sellerCd check >> " + sellerCd);
		}
		
		SellerDeliveryPolicyInfoVO deliveryPolicyInfoVO = this.service.getSellerDeliveryPolicyInfo(sellerCd);
		selDeliveryPolicyFM.setInfoVO(deliveryPolicyInfoVO);

		return "seller/basic/selDelivery";
	}
	
	@RequestMapping(value="/deliveryIndb")
	public String deliveryIndb(SellerDeliveryPolicyFM adminSellerOrderFM, HttpServletRequest req) {
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		String sellerCd = sellerSO.getUserInfo().getSellerCd();
		adminSellerOrderFM.getInfoVO().setSellerCd(sellerCd);
		
		this.service.setSellerDeliveryPolicy(adminSellerOrderFM.getInfoVO());
	
		return "redirect:delivery";
	}
}
