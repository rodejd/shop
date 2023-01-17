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
package com.wepinit.wepinit_shop.xmall.admin.controller.common;

import com.wepinit.wepinit_shop.xmall.admin.service.common.AdminSellerSearchService;
import com.wepinit.wepinit_shop.xmall.admin.vo.common.AdminSellerSearchFM;
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
@RequestMapping("/shop/admin/common/")
public class AdminSellerSearchController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminSellerSearchController.class);
	
	@Resource
	private AdminSellerSearchService service;
	
	@RequestMapping(value="sellerSearchPopup")
	public String sellerSearchPopup(@ModelAttribute("adminSellerSearchFM") AdminSellerSearchFM adminSellerSearchFM
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
		// #####
		// # 판매사 총건수 조회
		// #####
		adminSellerSearchFM.setRowCount(service.getSellerSearchCount(adminSellerSearchFM));
		
		// #####
		// # 판매사 리스트 조회
		// #####
		if( 0 < adminSellerSearchFM.getRowCount() ){
			adminSellerSearchFM.setSellerSearchList(service.getSellerSearchList(adminSellerSearchFM));
		}
		
		return "/shop/admin/common/seller_search_popup";
	}
}
