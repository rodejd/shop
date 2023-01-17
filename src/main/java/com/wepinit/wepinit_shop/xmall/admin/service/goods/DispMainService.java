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

import com.wepinit.wepinit_shop.xmall.admin.dao.goods.DispMainMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.DispMainVO;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GoodsdisplayGoodsGoodsoption;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DispMainService {
	
	@Resource
	DispMainMapper mapper;

	public List<GoodsdisplayGoodsGoodsoption> getGoodsDisplayList(DispMainVO dispMainVO) {
		return mapper.getGoodsDisplayList(dispMainVO);
	}

	public void deleteGoodsDisplay(DispMainVO dispMainVO) {
		mapper.deleteGoodsDisplay(dispMainVO);
	}

	public void insertGoodsDisplay(DispMainVO dispMainVO) {
		mapper.insertGoodsDisplay(dispMainVO);
		
	}
}
