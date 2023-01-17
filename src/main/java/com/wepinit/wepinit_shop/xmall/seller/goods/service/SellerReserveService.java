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
package com.wepinit.wepinit_shop.xmall.seller.goods.service;

import com.wepinit.wepinit_shop.xmall.common.session.SellerSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdGoodsGoodsoptionGoodslink;
import com.wepinit.wepinit_shop.xmall.seller.goods.dao.SellerReserveMapper;
import com.wepinit.wepinit_shop.xmall.seller.goods.vo.SellerReserveVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;



@Service
public class SellerReserveService {
	
	@Resource
	SellerReserveMapper mapper;
	public List<GdGoodsGoodsoptionGoodslink>getReserveList(SellerReserveVO vo){
		
		vo.setSellerCd(SellerSessionObject.getFuncLoginValue("SELLER_CD"));
		
		return mapper.getReserveList(vo);
	}
	public int getGoodsAllCount(SellerReserveVO vo){
		
		vo.setSellerCd(SellerSessionObject.getFuncLoginValue("SELLER_CD"));
		
		return mapper.getGoodsAllCount(vo);
	}
	public void setReserveGoodsOptionUpdate(int sno, String priceColumn){
		mapper.setReserveGoodsOptionUpdate(sno, priceColumn);
	}
	public int getReserveTotalList() {
		return mapper.getReserveTotalList();
	}
}
