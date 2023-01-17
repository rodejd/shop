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
package com.wepinit.wepinit_shop.xmall.seller.member.controller;

import com.wepinit.wepinit_shop.xmall.common.session.SellerSessionObject;
import com.wepinit.wepinit_shop.xmall.seller.member.service.SellerMemberService;
import com.wepinit.wepinit_shop.xmall.seller.member.vo.SellerMemberFM;
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
@RequestMapping("/shop/seller/member")
public class SellerMemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(SellerMemberController.class);
	
	@Resource
	SellerMemberService service;
	
	@RequestMapping(value="/list")
	public String memberList(@ModelAttribute SellerMemberFM sellerMemberFM, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		return "seller/member/list";
	}
	@RequestMapping(value="/orderMemberList")
	public String ordermemberlist(@ModelAttribute SellerMemberFM sellerMemberFM, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		//세션에서 판매자코드 받아오기
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		String sellerCd=sellerSO.getUserInfo().getSellerCd();
		sellerMemberFM.setSellerCd(sellerCd);
		
		//구매자 카운트
		sellerMemberFM.setRowCount(service.getSellerOrderMemberCount(sellerMemberFM));
		
		//구매자 리스트
		sellerMemberFM.setSellerordermemberlist(service.getSellerOrderMemberList(sellerMemberFM));
		
		
		return "seller/member/orderMemberList";
	}
}
