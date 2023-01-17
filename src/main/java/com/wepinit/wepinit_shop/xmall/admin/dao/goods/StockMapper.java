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

import com.wepinit.wepinit_shop.xmall.admin.vo.goods.StockVO;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdGoodsGoodsoptionGoodslink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StockMapper {
//	public List<GdGoodsGoodsoptionGoodslink> getGoodsList(@Param("addColumn")String addColumn,@Param("schCategory")String schCategory, @Param("schWord")String schWord, @Param("schOpen")String schOpen, @Param("schStock")String schStock, @Param("schEtc")String  schEtc, @Param("orderBy")String orderBy, @Param("groupBy")String groupBy, @Param("rowStart")int includeCount,@Param("pageSize") int pageSize);
	public int getGoodsCount(
			@Param("schCategory")String schCategory
			, @Param("schWord")String schWord
			, @Param("schOpen")String schOpen
			, @Param("schStock")String schStock
			, @Param("schEtc")String schEtc
			, @Param("joinTable") String joinTable
			, @Param("schGoodsNm") String schGoodsNm
			, @Param("schSellerNm") String schSellerNm
		);   // out : int cnt
	public void setStockPriceUpdate(@Param("price")int price, @Param("consumer")int consumer, @Param("supply")int supply, @Param("goodsno")int goodsno);
	public void setStockStockUpdate(@Param("stock")int stock, @Param("sno")int sno);
	
	public List<GdGoodsGoodsoptionGoodslink> getGoodsList(StockVO vo);
}
