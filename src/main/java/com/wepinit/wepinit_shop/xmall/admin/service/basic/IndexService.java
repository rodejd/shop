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
package com.wepinit.wepinit_shop.xmall.admin.service.basic;

import com.wepinit.wepinit_shop.xmall.admin.controller.basic.IndexController;
import com.wepinit.wepinit_shop.xmall.admin.dao.basic.IndexMapper;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.IndexRecentGoodsReg;
import com.wepinit.wepinit_shop.xmall.common.vo.join.IndexRegularMember;
import com.wepinit.wepinit_shop.xmall.common.vo.join.IndexWeekSoldGoods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IndexService {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Resource
	IndexMapper mapper;
	
	public IndexSalepr getIndexSalepr() throws Exception{
		return mapper.getIndexSalepr();
	}
	
	public IndexReview getIndexReview() throws Exception{
		return mapper.getIndexReview();
	}
	
	public IndexQna getIndexQna() throws Exception{
		return mapper.getIndexQna();
	}
	
	public IndexMqna getIndexMqna() throws Exception{
		return mapper.getIndexMqna();
	}
	
	public IndexMember getIndexMember() throws Exception{
		return mapper.getIndexMember();
	}
	
	public List<GdMemberQna> getIndexMqnaList() throws Exception{
		return mapper.getIndexMqnaList();
	}
	
	public List<GdCooperation> getIndexCooperationList() throws Exception{
		return mapper.getIndexCooperationList();
	}
	
	public List<GdGoodsReview> getIndexGoodsReviewList() throws Exception{
		return mapper.getIndexGoodsReviewList();
	}
	
	public List<IndexRecentGoodsReg> getIndexRecentGoodsRegList() throws Exception{
		return mapper.getIndexRecentGoodsRegList();
	}
	
	public List<GdEvent> getIndexEventList() throws Exception{
		return mapper.getIndexEventList();
	}
	
	public List<IndexWeekSoldGoods> getIndexWeekSoldGoodsList() throws Exception{
		return mapper.getIndexWeekSoldGoodsList( Long.toString(System.currentTimeMillis()-(7*24*60*60*1000)) );
	}
	
	public List<IndexRegularMember> getIndexRegularMemberList() throws Exception{
		return mapper.getIndexRegularMemberList( Long.toString(System.currentTimeMillis()-(90*24*60*60*1000L)) );
	}
	
	public int getAutoCancel() throws Exception{
		return mapper.getAutoCancel();
	}
}
