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
package com.wepinit.wepinit_shop.xmall.common.service;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.common.dao.CategoryBoxMapper;
import com.wepinit.wepinit_shop.xmall.common.vo.CategoryBoxVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value="categoryBoxService")
public class CategoryBoxService {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryBoxService.class);
	
	@Resource
	private CategoryBoxMapper mapper;


	public List<Map<String, String>> getCategoryBoxStr(CategoryBoxVO categoryBoxVO){
		
		int i = 0;
		int idx = 0;
		int j = 0;
		String strWhere 	= "";
		String strResult	= "";
		String tmp			= "";
		String val = "";
		
		String[] category	=  null;
		Map<String, Object> map = null;
		List<GdCategory> categoryList = null;
		List<Map<String,String>> retCategoryList	=  null;
		Map<String, String> retMap = null;
		
		map = new HashMap<String, Object>();
		val = StringUtil.N2S(categoryBoxVO.getVal());	// $_GET[val];
		
		if ( !"".equals(val) ){
			i = 0;
			category = new String[val.length()/3];	// $category = array();
			for (i=0; i < val.length()/3; i++ ) {
				category[i] = val.substring(0, i*3);
			}
		}else{
			category = new String[]{StringUtil.N2S(categoryBoxVO.getCategory())};
		}
		
		idx = categoryBoxVO.getIdx() ;
	
		// 정렬순서 셋팅
		map.put("order_by", "sort");
		
		retCategoryList = new ArrayList<Map<String, String>>();
		
		for ( i=0 ; i < category.length ; i++){
			strResult = "";
			retMap = new HashMap<String, String>();
			if ( 0 == idx || !"".equals(StringUtil.N2S(category[i])) ) {
				if ( "user".equals(categoryBoxVO.getMode()) ){
					 map.put("andHidden", "hidden=0");
				}
				
				map.put("category_like", "category like '" + category[i] + "%'");
				map.put("category_lendth", "length(category)= " + ((!"".equals(category[i])) ? String.valueOf(category[i].length() + 3) : "3"));
				
				categoryList = mapper.getCategoryBoxList(map);
				
				if( null != categoryList ){
					for ( j=0 ; j < categoryList.size() ; j++ ){
						strResult +=  categoryList.get(j).getCatnm()  + "|" + categoryList.get(j).getCategory() + "||";
					}
				}
				
				if ( !"".equals(strResult) ) {
					strResult = strResult.substring(0, strResult.length()-2);
				}
				
				
				retMap.put("strResult", strResult);
				retMap.put("category", category[i]);
				retMap.put("val", val);
				
				retCategoryList.add(retMap);
			}
		}
		
		return retCategoryList;
	}
}
