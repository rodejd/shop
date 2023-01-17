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
package com.wepinit.wepinit_shop.xmall.admin.dao.board;

import com.wepinit.wepinit_shop.xmall.admin.vo.board.GoodsReviewVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsReview;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import com.wepinit.wepinit_shop.xmall.common.vo.join.MemberGoodsrevwGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsReviewMapper {
	
	public int getGoodsReviewTotalCount(GoodsReviewVO goodsRevwVO);
	public List<String> getGoodsReviewGoodsNo(String sword);
	public List<String> getGoodsReviewGoodsParent(Map<String, Object>param);
	public int getGoodsReviewSearchCount(Map<String, Object> param);
	public List<MemberGoodsrevwGoods> getGoodsReviewSearchList(Map<String, Object> param);
	public GdGoodsReview getGoodsReviewInfo(int sno);
	public Map<String, Object> getGoodsReviewGoodsInfo(int goodsno);
	public List<GdMember> getGoodsReviewAuthMember();
	public GdMember getGoodsReviewMemberInfo(int mno);
	public void deleteGoodsReview(int sno);
	public void updateGoodsReview(GoodsReviewVO vo);
	public void insertGoodsReviewReply(GoodsReviewVO vo);

}
