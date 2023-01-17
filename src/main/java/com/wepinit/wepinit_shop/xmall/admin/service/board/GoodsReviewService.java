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
package com.wepinit.wepinit_shop.xmall.admin.service.board;

import com.wepinit.wepinit_shop.xmall.admin.dao.board.GoodsReviewMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.board.GoodsReviewVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsReview;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import com.wepinit.wepinit_shop.xmall.common.vo.join.MemberGoodsrevwGoods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsReviewService {
	
	@Resource
	GoodsReviewMapper mapper;
	
	public int getGoodsReviewTotalCount(GoodsReviewVO goodsRevwVO){
		return mapper.getGoodsReviewTotalCount(goodsRevwVO);
	}
	public List<String> getGoodsReviewGoodsNo(String sword){
		return mapper.getGoodsReviewGoodsNo(sword);
	}
	public List<String> getGoodsReviewGoodsParent(GoodsReviewVO vo, String schcate, String subtable){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("vo", vo);
		param.put("schcate", schcate);
		param.put("subtable", subtable);
		param.put("schSellerNm", vo.getSchSellerNm());
		
		return mapper.getGoodsReviewGoodsParent(param);
	}
	public int getGoodsReviewSearchCount(GoodsReviewVO vo, String schparent){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("vo", vo);
		param.put("schparent", schparent);
		
		return mapper.getGoodsReviewSearchCount(param);
	}
	public List<MemberGoodsrevwGoods> getGoodsReviewSearchList(GoodsReviewVO vo, String schparent){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("vo", vo);
		param.put("schparent", schparent);
		
		return mapper.getGoodsReviewSearchList(param);
	}
	public GdGoodsReview getGoodsReviewInfo(int sno){
		return mapper.getGoodsReviewInfo(sno);
	}
	public Map<String, Object> getGoodsReviewGoodsInfo(int goodsno){
		return mapper.getGoodsReviewGoodsInfo(goodsno);
	}
	public List<GdMember> getGoodsReviewAuthMember(){
		return mapper.getGoodsReviewAuthMember();
	}
	public GdMember getGoodsReviewMemberInfo(int mno){
		return mapper.getGoodsReviewMemberInfo(mno);
	}
	public void deleteGoodsReview(int sno){
		mapper.deleteGoodsReview(sno);
	}
	public void updateGoodsReview(GoodsReviewVO vo){
		mapper.updateGoodsReview(vo);
	}
	public void insertGoodsReviewReply(GoodsReviewVO vo){
		mapper.insertGoodsReviewReply(vo);
	}

}
