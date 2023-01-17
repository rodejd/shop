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

import com.wepinit.wepinit_shop.xmall.seller.basic.vo.SellerDefaultFM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/shop/seller/basic")
public class SellerDefaultController {
	
	private static final Logger logger = LoggerFactory.getLogger(SellerDefaultController.class);
	
	@RequestMapping(value="/default") //쇼핑몰기본관리_기본관리_기본정보설정 디비에서 데이터 받아와서 표시
	public String basicDefault(@ModelAttribute SellerDefaultFM sellerDefaultFM, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		return "seller/basic/default";
	}
	
	
}
