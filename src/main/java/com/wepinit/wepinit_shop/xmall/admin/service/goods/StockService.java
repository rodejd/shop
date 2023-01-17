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

import com.wepinit.wepinit_shop.xmall.admin.dao.goods.StockMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.StockVO;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdGoodsGoodsoptionGoodslink;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StockService {
	@Resource
	StockMapper mapper;
	/*public List<GdGoodsGoodsoptionGoodslink> getGoodsList(String add_column,String sch_category, String sch_word, String sch_open, String sch_stock, String  sch_etc, String order_by, String group_by, int include_count, int page_size){
		return mapper.getGoodsList(add_column, sch_category, sch_word, sch_open, sch_stock, sch_etc, order_by, group_by, include_count, page_size);
	}*/
	public int getGoodsCount(StockVO stockVO){
		return mapper.getGoodsCount(
				stockVO.getSchCategory()
				, stockVO.getSchWord()
				, stockVO.getSchOpen()
				, stockVO.getSchStock()
				, stockVO.getSchEtc()
				, stockVO.getJoinTable()
				, stockVO.getSchGoodsNm()
				, stockVO.getSchSellerNm()
			);
	}
	public void setStockPriceUpdate(int price, int consumer, int supply, int goodsno){
		mapper.setStockPriceUpdate(price, consumer, supply, goodsno);
	}
	public void setStockStockUpdate(int stock, int sno){
		mapper.setStockStockUpdate(stock, sno);
	}
	
	public List<GdGoodsGoodsoptionGoodslink> getGoodsList(StockVO vo){
		return mapper.getGoodsList(vo);
	}
}
