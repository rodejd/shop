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

import com.wepinit.wepinit_shop.xmall.admin.vo.acount.AcountVO;
import com.wepinit.wepinit_shop.xmall.common.session.SellerSessionObject;
import com.wepinit.wepinit_shop.xmall.seller.basic.service.SellerAcountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/shop/seller/basic")
public class SellerAcountController {
	
	@Resource SellerAcountService service;
	

	private static final Logger logger = LoggerFactory.getLogger(SellerAcountController.class);

	//정산세부내역리스트
	@RequestMapping(value="/acountList")
	public String acountList(@ModelAttribute("acountVO") AcountVO acountVO,HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug("SellerAcountController acountList start ####################");
		}
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		String sellerCd = sellerSO.getUserInfo().getSellerCd();
		acountVO.setSellerCd(sellerCd);
		
		acountVO.setTotalCnt(service.getSellerAcountListTotalCount(sellerCd));
		
		acountVO.setRowCount(service.getSellerAcountListCount(acountVO));
		
		acountVO.setTotal(service.getSellerAcountListData(acountVO));
		
		if(0 < acountVO.getRowCount()){
			acountVO.setAccountList(service.getSellerAcountInfo(acountVO));
		}
		
		return "seller/basic/acountList";
	}
	
}
