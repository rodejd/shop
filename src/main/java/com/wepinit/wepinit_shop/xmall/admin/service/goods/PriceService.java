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

import com.wepinit.wepinit_shop.xmall.admin.dao.goods.PriceMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.PriceVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdEvent;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdGoodsGoodsoptionGoodslink;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PriceService {
	
	@Resource
	PriceMapper mapper;
	
	public List<GdGoodsGoodsoptionGoodslink> getPriceList(PriceVO vo){
		return mapper.getPriceList(vo);
	}
	public int getGoodsAllCount(PriceVO vo){
		return mapper.getGoodsAllCount(vo);
	}
	public void setPriceColumnUpdate(int sno, String priceColumn){
		mapper.setPriceColumnUpdate(sno, priceColumn);
	}
	public List<GdEvent> getEventSELECT(PriceVO vo){
		return mapper.getEventSELECT(vo);
	}
	public int getEventCOUNT(){
		return mapper.getEventCOUNT();
	}
}
