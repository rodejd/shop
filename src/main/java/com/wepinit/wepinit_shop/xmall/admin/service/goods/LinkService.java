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

import com.wepinit.wepinit_shop.xmall.admin.dao.goods.LinkMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.LinkVO;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdEventGoodslinkGoodsdisplay;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdGoodsGoodsoptionGoodslink;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LinkService {

	@Resource
	LinkMapper mapper;
	
	public int goodsAllCount(){
		return mapper.goodsAllCount();
	}
	public int goodsCount(LinkVO vo){
		return mapper.goodsCount(vo);
	}
	public List<GdGoodsGoodsoptionGoodslink> getLinkList(LinkVO vo){
		//검색 건 수
		vo.setRowCount(mapper.geetLinkListSearchCount(vo));
		return mapper.getLinkList(vo);
	}
	public int getGoodsCategoryHiddenState(String category){
		return mapper.getGoodsCategoryHiddenState(category);
	}
	public List<GdEventGoodslinkGoodsdisplay>getGoodsLinkEventConnectLIST(String goodsno){
		return mapper.getGoodsLinkEventConnectLIST(goodsno);
	}
	
	public void setGoodsCategoryINSERT01(int goodsno,String category, int hidden){
		mapper.setGoodsCategoryINSERT01(goodsno,category,hidden);
	}
	public void setGoodsInfoUPDATE(int goodsno){
		mapper.setGoodsInfoUPDATE(goodsno);
	}
//	public void setGoodsDisplayINSERT(GdGoodsDisplay vo){
//		mapper.setGoodsDisplayINSERT(vo);
//	}
	
	public int getLinkListTotalCount(){
		return mapper.getLinkListTotalCount();
	}
	
	public void setGoodsDisplayINSERT(int goodsno, String mode, int sort){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("goodsno", goodsno);
		param.put("mode", mode);
		param.put("sort", ++sort);
		
		mapper.setGoodsDisplayINSERT(param);
	}
	
	public void updateGoodsLink(String category, int hidden, int goodsno, String orgCategory){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("category", category);
		param.put("hidden", hidden);
		param.put("goodsno", goodsno);
		param.put("orgCategory", orgCategory);
		
		mapper.updateGoodsLink(param);
	}
	
}
