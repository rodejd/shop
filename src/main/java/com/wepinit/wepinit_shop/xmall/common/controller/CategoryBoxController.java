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
package com.wepinit.wepinit_shop.xmall.common.controller;

import com.wepinit.wepinit_shop.xmall.common.service.CategoryBoxService;
import com.wepinit.wepinit_shop.xmall.common.vo.CategoryBoxVO;
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
@RequestMapping("/shop/common/")
public class CategoryBoxController {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryBoxController.class);
	
	@Resource
	private CategoryBoxService service;
	
	
	@RequestMapping(value="categoryBox.dojson")
	public String categoryBox(@ModelAttribute("categoryBoxVO") CategoryBoxVO categoryBoxVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
		categoryBoxVO.setRetCategoryList(service.getCategoryBoxStr(categoryBoxVO));
		
		return "dojson";
	}
}
