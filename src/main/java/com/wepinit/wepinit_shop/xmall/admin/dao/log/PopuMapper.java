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
package com.wepinit.wepinit_shop.xmall.admin.dao.log;

import com.wepinit.wepinit_shop.xmall.admin.vo.log.PopuCategoryVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.log.PopuGoodsVO;
import com.wepinit.wepinit_shop.xmall.common.vo.PopuCategoryOutVO;
import com.wepinit.wepinit_shop.xmall.common.vo.PopuGoodsOutVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PopuMapper {
	
	/*
	 * 2.상품분석
	 * 
	 * */
	
	//인기카테고리분석
	public List<PopuCategoryOutVO> getpopuCategoryList(PopuCategoryVO popuCategoryVO);	
	public PopuCategoryOutVO getpopuCategoryList2(PopuCategoryVO popuCategoryVO);	
	public List<PopuCategoryOutVO> getpopuCategoryRet(String category);
	
	//인기상품분석
	public int getPopuGoodsCount(PopuGoodsVO PopuGoodsVO);
	public List<PopuGoodsOutVO> getPopuGoodsList(Map<String, Object> param);
	
	
}
