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
package com.wepinit.wepinit_shop.xmall.admin.service.seller;

import com.wepinit.wepinit_shop.xmall.admin.dao.seller.AdminSellerGoodsMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.BrandVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminSellerGoodsService {

	@Resource
	AdminSellerGoodsMapper mapper;

	public List<BrandVO> getSellerBrandList(Map<String, Object> param){
		return mapper.getSellerBrandList(param);
	}
	
	public int getSellerBrandListCount(BrandVO brandvo){
		return mapper.getSellerBrandListCount(brandvo);
	}
	
}
