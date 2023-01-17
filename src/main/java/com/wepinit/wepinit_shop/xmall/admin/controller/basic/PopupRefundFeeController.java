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

import com.wepinit.wepinit_shop.xmall.admin.controller.promotion.PopUpController;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop/admin/basic/*")
public class PopupRefundFeeController {

	private static final Logger logger = LoggerFactory.getLogger(PopUpController.class);
	
	/**
	 * 환불 수수료 설정 팝업페이지 return (2017-09-07 추가)
	 * gd_conf_set > minrepayfee : 환불수수료
	 * */
	@RequestMapping(value="popup_refundFee")
	public String popupRefundFee(Model model) {
		if(logger.isDebugEnabled()) {
			logger.debug("PopupRefundFeeController >> popupRefundFee");
		}
		model.addAttribute("minrepayfee", ShopConfig.getProperty("minrepayfee"));
		return "/shop/admin/basic/popup_refundFee";
	}
	
	/**
	 * 환불 수수료 변경처리 (2017-09-07 추가)
	 * gd_conf_set > minrepayfee : 환불수수료
	 * */
	@RequestMapping(value="popupRefundFeeIndb")
	public String popupRefundFeeIndb(String minrepayfee) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("PopupRefundFeeController >> popupRefundFeeIndb");
		}
		
		ShopConfig.setProperty("minrepayfee", minrepayfee);
		
		return "redirect:/shop/admin/basic/popup_refundFee";
	}
}
