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
package com.wepinit.wepinit_shop.xmall.seller.goods.dao;

import com.wepinit.wepinit_shop.xmall.common.vo.join.GdEventGoodslinkGoodsdisplay;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdGoodsGoodsoptionGoodslink;
import com.wepinit.wepinit_shop.xmall.seller.goods.vo.SellerLinkVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface SellerLinkMapper {
	public int goodsAllCount();
	public int goodsCount(SellerLinkVO vo);
	public List<GdGoodsGoodsoptionGoodslink> getLinkList(SellerLinkVO vo);
	public int getGoodsCategoryHiddenState(@Param("category")String category);
	public List<GdEventGoodslinkGoodsdisplay>getGoodsLinkEventConnectLIST(@Param("goodsno")String goodsno);
	public void setGoodsCategoryINSERT01(@Param("goodsno")int goodsno,@Param("category")String category, @Param("hidden")int hidden);
	public void setGoodsInfoUPDATE(@Param("goodsno") int goodsno);
//	public void setGoodsDisplayINSERT(GdGoodsDisplay vo);
	
	public int getLinkListTotalCount();
	public int geetLinkListSearchCount(SellerLinkVO vo);
	public void setGoodsDisplayINSERT(Map<String, Object> param);
	public void updateGoodsLink(Map<String, Object> param);
}
