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

import com.wepinit.wepinit_shop.xmall.admin.dao.goods.BrandMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.BrandVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsBrand;
import com.wepinit.wepinit_shop.xmall.common.vo.XmCategoryBrandInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BrandService {
	
	@Resource
	BrandMapper mapper;
	
	public List<GdGoodsBrand> getGoodsBrandList(BrandVO vo){
		return mapper.getGoodsBrandList(vo);
	}
	public int getGoodsBrandCount(BrandVO vo){
		return mapper.getGoodsBrandCount(vo);
	}
	public GdGoodsBrand getGoodsBrandInfo(int sno){
		return mapper.getGoodsBrandInfo(sno);
	}
	public XmCategoryBrandInfo getCategoryBrandInfo(int brandno){
		return mapper.getCategoryBrandInfo(brandno);
	}
	public void updateGoodsBrand(BrandVO vo){
		mapper.updateGoodsBrand(vo);
	}
	public int insertGoodsBrand(BrandVO vo){
		return mapper.insertGoodsBrand(vo);
	}
	public void updateGoodsCategoryBrandInfo(BrandVO vo){
		mapper.updateGoodsCategoryBrandInfo(vo);
	}
	public void insertGoodsCategoryBrandInfo(BrandVO vo){
		mapper.insertGoodsCategoryBrandInfo(vo);
	}
	public void deleteGoodsBrand(int sno){
		mapper.deleteGoodsBrand(sno);
		if(mapper.getXmCategoryBrandInfoCount(sno) != 0) {
			mapper.deleteXmCategoryBrandInfo(sno);
		}
	}
	public int updateBrandByImage(BrandVO vo){
		return mapper.updateBrandByImage(vo);
	}
	public GdGoodsBrand getGoodsBrandCountList(){
		return mapper.getGoodsBrandCountList();
	}

}
