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

import com.wepinit.wepinit_shop.xmall.admin.vo.basic.EmoneyVO;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/shop/admin/basic/*")
public class EmoneyContoller {
	private static final Logger logger = LoggerFactory.getLogger(EmoneyContoller.class); 
	
	@RequestMapping(value = "emoney")
	public String Emoney(@ModelAttribute("emoneyVO") EmoneyVO emoneyVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		emoneyVO.setUseyn(ShopConfig.getProperty("emoney_useyn"));						// 적립금 지급여부
		emoneyVO.setLimit(ShopConfig.getProperty("emoney_limit"));						// 상품적립금 지급여부
		emoneyVO.setHold(ShopConfig.getProperty("emoney_hold"));							// 적립금사용가능액
		emoneyVO.setMin(ShopConfig.getProperty("emoney_min"));							// 최소한도액
		emoneyVO.setMax2(ShopConfig.getProperty("emoney_max"), 0);						// 최대한도액
		emoneyVO.setDelivery(ShopConfig.getProperty("emoney_delivery"));					// 적립금사용기준
		emoneyVO.setCut(ShopConfig.getProperty("emoney_cut"));							// 기본자리수
		emoneyVO.setGoodsEmoney2(ShopConfig.getProperty("emoney_goods_emoney"), 0);		// 상품적립금
		emoneyVO.setChkGoodsEmoney(ShopConfig.getProperty("emoney_chk_goods_emoney"));	// % or 통화
		
		logger.debug("@@ emoney " + emoneyVO.toString());
			
		return "/shop/admin/basic/emoney";
	}
	
	@RequestMapping(value = "emoney/indb")
	public String EmoneyIndb(@ModelAttribute("emoneyVO") EmoneyVO emoneyVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ShopConfig.setProperty("emoney_useyn",emoneyVO.getUseyn());
		ShopConfig.setProperty("emoney_limit", emoneyVO.getLimit());
		ShopConfig.setProperty("emoney_hold",emoneyVO.getHold());
		ShopConfig.setProperty("emoney_min", emoneyVO.getMin());
		ShopConfig.setProperty("emoney_delivery", emoneyVO.getEmoneyDelivery());
		
		if( emoneyVO.getCut().equals("")){
			ShopConfig.setProperty("emoney_cut" , "3");
		}else{
			ShopConfig.setProperty("emoney_cut" , emoneyVO.getCut());	
		}

		if(emoneyVO.getMax() != null){
			if("0".equals(emoneyVO.getkMax())){
				ShopConfig.setProperty("emoney_max", emoneyVO.getMax()[0]);
			} else{
				ShopConfig.setProperty("emoney_max", emoneyVO.getMax()[1]+"%");
			}
		}
		
		if(emoneyVO.getGoodsEmoney2(0) !=null && !emoneyVO.getGoodsEmoney2(0).equals("")){
			ShopConfig.setProperty("emoney_goods_emoney",emoneyVO.getGoodsEmoney2(0));
		}else{
			ShopConfig.setProperty("emoney_goods_emoney",emoneyVO.getGoodsEmoney2(1));
		}
		ShopConfig.setProperty("emoney_chk_goods_emoney",emoneyVO.getChkGoodsEmoney());		
		logger.debug("@@ emoney indb " + emoneyVO.toString());
		
		return "redirect:/shop/admin/basic/emoney";
	}
	
}
