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

import com.wepinit.wepinit_shop.xmall.admin.vo.goods.GoodsOptionVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.GoodsVO;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.seller.goods.vo.SellerGoodsViewVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsMapper {
	

	public GdGoods getGoodsInfo(int goodsno);
	public GdGoods getSellerGoodsInfo(int goodsno);

	public void insertGoods(GoodsVO vo);
	public void deleteGoodsLinkAll(int goodsno);
	public void deleteGoodsOption(int goodsno);
	public int getGoodsGoodsNoMax();
	public void insertGoodsLink(Map<String, Object> map);
	public void insertSellerGoodsLink(Map<String, Object> map);
	public int insertGoodsAddOption(Map<String, Object> map);
	public List<Map<String, Object>> getGoodsCategoryList(Map<String, Object> map);
	public int getGoodsSearchCount(Map<String, Object> map);
	public List<Map<String, Object>> getGoodsSearchList(Map<String, Object> map);
	public void insertGoodsOption(GoodsOptionVO vo);
	public void deleteGoodsAddOption(int goodsno);
	public List<Map<String, Object>> getGoodsLinkEventConnectList(int goodsno);
	public List<Map<String, Object>> getGoodsDisplayCount(int goodsno);
	public List<Map<String, Object>> getGoodsDisplayCount(Map<String, Object> map);
	public void insertGoodsDisplay(Map<String, Object> map);
	public void updateGoods(HashMap params);
	public void updateSellerGoods(HashMap params);
	public List<GdGoodsAdd> getGoodsAddInfoList(String reqsno);
	
	public int getCommonContentsCount(String id);
	public GdCmContents getCommonContents(Map<String, Object> map);
	public void insertComminInsurance(GdCmContents cmContents);
	public void updateCommonInsurance(GdCmContents cmContents);
	
	public void insertCommonDeliveryInfo(GdCmContents cmContents);
	public void updateCommonDeliveryInfo(GdCmContents cmContents);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public List<GdGoods> getGoodsList(GoodsVO goodsVO);
	
	public int getGoodsListCount(GoodsVO goodsVO);
	public int getGoodsListTotalCount();
	
	public List<Map<String, Object>> selectGoodsExcelList(GoodsVO goodsVO);
	public int updateGoodsBySpec(HashMap<String, Object> dataMap);
	
	public void updateOpen(GoodsVO goodsVO);
//	public void deleteGoodsRecipe(String goodsno);
//	public void deleteGoodsTip(String goodsno);
	
	/////goods copy//////
	public List<GdGoods> getGoodsView2(int goodsno);
	public void insertGoodsViewCopy(GdGoods gdGoods);
	public int getGoodsMaxCount();
	public void insertGoodsAddCopy2(GdGoods gdGoods);
	public void insertGoodsOptionCopy2(GdGoods gdGoods);
	public void insertGoodsLinkCopy2(GdGoods gdGoods);
//	public void insertGoodsTipCopy2(GdGoods gdGoods);
//	public void insertGoodsRecipeCopy2(GdGoods gdGoods);
	public List<GdGoods> getGoodsView(String goodsno);
	public void deleteGoodsDisplayAll2(GdGoods gdGoods);
	public void deleteGoodsAddAll(GdGoods gdGoods);
	public void deleteGoodsOptionAll2(GdGoods gdGoods);
	public void deleteGoodsLinkAll(GdGoods gdGoods);
	public void deleteGoodsInfo2(GdGoods gdGoods);
	public void deleteMemberWishlistGoods2(GdGoods gdGoods);
	public void deleteSellerGoodsInfo2(GdGoods gdGoods);
	
	
	
	////수정//////
	public List<GdGoods> getOriginList();
	public List<GdGoodsBrand> getBrandList();
	public List<String> getSeasonList();
	public List<GdCategory> getCategoryLinkList(int goodsno);
	public List<GdCategory> getMSellerCategoryLinkList(int goodsno);
	public List<GdGoodsOption> getGoodsOption(int goodsno);
	public List<GdGoodsAdd> getGoodsAddOptionList(int goodsno);
	public List<Map<String, Object>> getGoodsRelationList(String[] relation);

	public void insertGoodsOption(Map<String, Object> map);
	public void updateGoodsOptionOptno(Map<String, Object> map);
	public void insertSellerGoodsOption(Map<String, Object> map);
	public void updateSellerGoodsOptionOptno(Map<String, Object> map);

	
	public void insertGoods(HashMap paramsDb);
	public void insertSellerGoods(HashMap paramsDb);
	
	public void updateOpen(HashMap paramsDb);
	public void updateApproval(HashMap paramsDb);
	public void updateSellerApproval(HashMap paramsDb);

	public void deleteGoodsAddAll(HashMap paramsDb);
	
	
	public List<SellerGoodsViewVO> getAdminGoodsNotiList(GoodsVO vo);
	public int setRegAdminGoodsNoti(GoodsVO vo);

	public int setDelGoodsNotiAll(int goodsno);
	public int setRegGoodsNoti(HashMap paramMap);

	public void setDelSellerGoodsNotiAll(int goodsNo);
	public void setRegSellerGoodsNoti(HashMap paramsDb);

	public List<SellerGoodsViewVO> getSellerGoods(HashMap paramsDb);
	public List<SellerGoodsViewVO> getSellerAddopt(HashMap paramsDb);
	
	public void deleteGoodsAddopt(HashMap paramsDb); 
	public void acceptUpdateGoods(SellerGoodsViewVO goods);
	public void insertGoodsAddopt(SellerGoodsViewVO goodsAddopt);
} 
