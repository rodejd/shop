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
package com.wepinit.wepinit_shop.xmall.admin.service.log;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.dao.log.PopuMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.log.PopuCategoryVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.log.PopuGoodsVO;
import com.wepinit.wepinit_shop.xmall.common.vo.PopuCategoryOutVO;
import com.wepinit.wepinit_shop.xmall.common.vo.PopuGoodsOutVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PopuService {
	
	private static final Logger logger = LoggerFactory.getLogger(PopuService.class);
	
	@Resource
	PopuMapper mapper;
	

	/**
	 * 
	 * 상품분석 > 인기카테고리분석
	 * 
	 * @param statSettlekindVO
	 * @return
	 * @throws Exception
	 */
	public List<PopuCategoryOutVO> getPopuCategoryList(PopuCategoryVO popuCategoryVO) throws Exception {
		
		PopuCategoryOutVO vo = null; 
		PopuCategoryOutVO vo1 = null; 
		
		if("".equals( StringUtil.nvl(popuCategoryVO.getCategory(),"") )){
			popuCategoryVO.setSchWhere("length(category) = '3'");
		}else{
			int len = popuCategoryVO.getCategory().length() +3;
			if(len > 12){len = 12;}
			popuCategoryVO.setSchWhere( "length(category) = '"+len+"' " + "and category like '"+ popuCategoryVO.getCategory()+ "%'" );
		}
		
		List<PopuCategoryOutVO> list = mapper.getpopuCategoryList(popuCategoryVO);
		
		// 카테고리별 구매자수, 구매수량 조회 
		for(int i=0; i<list.size(); i++){
			vo = list.get(i);
			popuCategoryVO.setCategory( vo.getCategory() );
			vo1 = mapper.getpopuCategoryList2(popuCategoryVO);
			
			vo.setEa(vo1.getEa());
			vo.setCnt(vo1.getCnt());
			
			list.set(i, vo);
		}
		
		return list;
	}
	
	/**
	 * 
	 * 상품분석 > 인기카테고리분석(카테고리 링크 html)
	 * 
	 * @param statSettlekindVO
	 * @return
	 * @throws Exception
	 */
	public List<PopuCategoryOutVO> getPopuCategoryRet(String category) throws Exception {
		return  mapper.getpopuCategoryRet(category);
		
	}
	
	/**
	 * 상품분석 > 인기상품분석(Count)
	 * 
	 * @param popuGoodsVO
	 * @return
	 * @throws Exception
	 */
	public int getPopuGoodsCount(PopuGoodsVO popuGoodsVO) throws Exception {
		return  mapper.getPopuGoodsCount(popuGoodsVO);
		
	}
	
	/**
	 * 상품분석 > 인기상품분석(List)
	 * 
	 * @param popuGoodsVO
	 * @return
	 * @throws Exception
	 */
	public List<PopuGoodsOutVO> getPopuGoodsList(Map<String, Object> param) throws Exception {
		return  mapper.getPopuGoodsList(param);
		
	}
	
}
