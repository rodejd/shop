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
package com.wepinit.wepinit_shop.xmall.admin.service.goods;

import com.wepinit.wepinit_shop.xmall.admin.dao.goods.CategoryMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.CategoryVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.SortVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCategory;
import com.wepinit.wepinit_shop.xmall.common.vo.XmCategoryBrandInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {
	
	@Resource
	CategoryMapper mapper;
	
	public GdCategory getGoodsCategoryList(CategoryVO vo){
		return mapper.getGoodsCategoryList(vo);
	}
	public List<Map<String, Object>> getGoodsCategoryExcelList(CategoryVO vo){
		return mapper.getGoodsCategoryExcelList(vo);
	}
	public int getGoodsLinkCount(String category){
		return mapper.getGoodsLinkCount(category);
	}
	public List<Map<String, Object>> getGoodsDisplayList(String category){
		return mapper.getGoodsDisplayList(category);
	}
	public XmCategoryBrandInfo getGoodsCategoryBrandInfo(String category){
		return mapper.getGoodsCategoryBrandInfo(category);
	}
	public List<GdCategory> getCategoryList(CategoryVO vo){
		return mapper.getCategoryList(vo);
	}
	public Map<String, Object> getGoodsCategoryMax(CategoryVO vo){
		return mapper.getGoodsCategoryMax(vo);
	}
	public void insertGoodsCategory(CategoryVO vo){
		mapper.insertGoodsCategory(vo);
	}
	public void updateGoodsCategory(CategoryVO vo){
		mapper.updateGoodsCategory(vo);
	}
	public void insertGoodsCategoryBrandInfo(CategoryVO vo){
		mapper.insertGoodsCategoryBrandInfo(vo);
	}
	public void updateGoodsCategoryBrandInfo(CategoryVO vo){
		mapper.updateGoodsCategoryBrandInfo(vo);
	}
	public void deleteGoodsDisplay(CategoryVO vo){
		mapper.deleteGoodsDisplay(vo);
	}
	public void insertGoodsDisplay(CategoryVO vo){
		mapper.insertGoodsDisplay(vo);
	}
	public void updateGoodsCategorySub(String prtcategory, String category){
		Map<String, String> param = new HashMap<String, String>();
		param.put("prtcategory", prtcategory);
		param.put("category", category);
		
		mapper.updateGoodsCategorySub(param);
	}
	public int getGoodsCategoryBrandInfoSub(String category){
		return mapper.getGoodsCategoryBrandInfoSub(category);
	}
	public void insertGoodsCategoryBrandInfoSub(String prtcategory, String category){
		Map<String, String> param = new HashMap<String, String>();
		param.put("prtcategory", prtcategory);
		param.put("category", category);
		
		mapper.insertGoodsCategoryBrandInfoSub(param);
	}
	public void updateGoodsCategoryBrandInfoSub(String prtcategory, String category){
		Map<String, String> param = new HashMap<String, String>();
		param.put("prtcategory", prtcategory);
		param.put("category", category);
		
		mapper.updateGoodsCategoryBrandInfoSub(param);
	}
	public void updateGoodsCategoryHidden(String category, int hidden){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("category", category);
		param.put("hidden", hidden);
		
		mapper.updateGoodsCategoryHidden(param);
	}
	public void deleteCategory(String category){
		mapper.deleteGoodsCategory(category);
		mapper.deleteGoodsCategoryLink(category);
		mapper.deleteGoodsXmCategory(category);
		mapper.deleteGoodsCategoryDisplay(category);
	}
	public List<Map<String, Object>> getGoodsLinkSortList(SortVO vo){
		//카테고리로 조회되는 총 개수를 구한다
		vo.setTotalCnt(mapper.getGoodsLinkCount(vo.getCategory()));
		return mapper.getGoodsLinkSortList(vo);
	}
	public void updateGoodsLinkSort(Map<String, Object> param){
		mapper.updateGoodsLinkSort(param);
	}
	public void updateGoodsCategoryHiddenUpdate(String category, int hidden){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("category", category);
		param.put("hidden", hidden);
		
		mapper.updateGoodsCategoryHiddenUpdate(param);
	}

}
