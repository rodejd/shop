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
package com.wepinit.wepinit_shop.xmall.admin.controller.log;

import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.log.PopuService;
import com.wepinit.wepinit_shop.xmall.admin.vo.log.PopuCategoryVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.log.PopuGoodsVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.vo.PopuCategoryOutVO;
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
@RequestMapping("/shop/admin/log/popu/*")
public class PopuController {

	private static final Logger logger = LoggerFactory.getLogger(PopuController.class);

	@Resource
	PopuService service;
	
	
	@RequestMapping(value = "category")
	public String category(@ModelAttribute("popuCategoryVO") PopuCategoryVO popuCategoryVO,
			HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>popu.category!!!");
		}
		
		String category = StringUtil.nvl(popuCategoryVO.getCategory(),"");
		
		popuCategoryVO.setYear(StringUtil.nvl(popuCategoryVO.getYear(), DateUtil.getDateString().substring(0, 4))); 						//기간설정(년)
		popuCategoryVO.setMonth(StringUtil.nvl(popuCategoryVO.getMonth(), DateUtil.getDateString().substring(4, 6)));					//기간설정(월)
		//popuCategoryVO.setCategory(StringUtil.nvl(popuCategoryVO.getCategory(),""));															//Category
		
		List<PopuCategoryOutVO> resultList = service.getPopuCategoryList(popuCategoryVO); 												// 인기카테고리분석LIST
		
		//카테고리링크
		List<PopuCategoryOutVO> retList = service.getPopuCategoryRet(category);
		
		String ret = "";
		for(int i=0; i< retList.size(); i++){
			ret += "> <a class='ver8' href='category?category="+ retList.get(i).getCategory() + "'>"+ retList.get(i).getCatnm() +"</a> ";
		}
		popuCategoryVO.setRet(ret);
		
		// 인기카테고리분석 LIST 세팅
		popuCategoryVO.setPopuCategoryList(resultList);

		return "/shop/admin/log/popu/category";
	}
	
	@RequestMapping(value = "goods")
	public String goods(@ModelAttribute("popuGoodsVO") PopuGoodsVO popuGoodsVO,
			HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>popu.goods!!!");
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		popuGoodsVO.setYear(StringUtil.nvl(popuGoodsVO.getYear(), DateUtil.getDateString().substring(0, 4))); 						//기간설정(년)
		popuGoodsVO.setMonth(StringUtil.nvl(popuGoodsVO.getMonth(), DateUtil.getDateString().substring(4, 6)));					//기간설정(월)
		
		//총건수
		popuGoodsVO.setRowCount(service.getPopuGoodsCount(popuGoodsVO));
		
		//조회(년) 세팅
		param.put("year", popuGoodsVO.getYear());
		
		//조회(월) 세팅
		param.put("month", popuGoodsVO.getMonth());		
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, popuGoodsVO.getPageSize());
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, popuGoodsVO.getRowStart());
		
		popuGoodsVO.setPopuGoodsList(service.getPopuGoodsList(param));
		

		return "/shop/admin/log/popu/goods";
	}
	
}
