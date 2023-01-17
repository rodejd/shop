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
package com.wepinit.wepinit_shop.xmall.admin.dao.goods;

import com.wepinit.wepinit_shop.xmall.admin.vo.goods.CategoryVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.SortVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCategory;
import com.wepinit.wepinit_shop.xmall.common.vo.XmCategoryBrandInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CategoryMapper {
	
    public GdCategory getGoodsCategoryList(CategoryVO vo);
    public List<Map<String, Object>> getGoodsCategoryExcelList(CategoryVO vo);
	public int getGoodsLinkCount(@Param("category")String category);
	public List<Map<String, Object>> getGoodsDisplayList(@Param("category")String category);
	public XmCategoryBrandInfo getGoodsCategoryBrandInfo(@Param("category")String category);
	public List<GdCategory> getCategoryList(CategoryVO vo);
	public Map<String, Object> getGoodsCategoryMax(CategoryVO vo);
	public void insertGoodsCategory(CategoryVO vo);
	public void updateGoodsCategory(CategoryVO vo);
	public void insertGoodsCategoryBrandInfo(CategoryVO vo);
	public void updateGoodsCategoryBrandInfo(CategoryVO vo);
	public void deleteGoodsDisplay(CategoryVO vo);
	public void insertGoodsDisplay(CategoryVO vo);
	public void updateGoodsCategorySub(Map<String, String> param);
	public int getGoodsCategoryBrandInfoSub(@Param("category")String category);
	public void insertGoodsCategoryBrandInfoSub(Map<String, String> param);
	public void updateGoodsCategoryBrandInfoSub(Map<String, String> param);
	public void updateGoodsCategoryHidden(Map<String, Object> param);
	
	//카테고리 삭제
	public void deleteGoodsCategory(@Param("category")String category);
	public void deleteGoodsCategoryLink(@Param("category")String category);
	public void deleteGoodsXmCategory(@Param("category")String category);
	public void deleteGoodsCategoryDisplay(@Param("category")String category);
	
	//sort
	public int getGoodsLinkSortCount(@Param("category")String category);
	public List<Map<String, Object>> getGoodsLinkSortList(SortVO vo);
	public void updateGoodsLinkSort(Map<String, Object> param);
	
	public void updateGoodsCategoryHiddenUpdate(Map<String, Object> param);

}
