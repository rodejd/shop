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
/********************************************************************************
* <pre>
* 업무구분명			:	Community
* 세부업무구분명	: 	사용자 > Community
* 작성자 				: 	이병환
* 설명 				: 	사용자 Community Service
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170208		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.service.main;

import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.vo.GdBanner;
import com.wepinit.wepinit_shop.xmall.front.dao.goods.FrontGoodsMapper;
import com.wepinit.wepinit_shop.xmall.front.dao.main.FrontMainMapper;
import com.wepinit.wepinit_shop.xmall.front.dao.promotion.FrontPromotionMapper;
import com.wepinit.wepinit_shop.xmall.front.vo.main.FrontMainVO;
import com.wepinit.wepinit_shop.xmall.front.vo.promotion.FrontPromotionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FrontMainService {
	
	Logger logger = LoggerFactory.getLogger(FrontMainService.class);
	
	@Resource
	FrontMainMapper mapper;	
	
	@Resource
	FrontGoodsMapper frontGoodsMapper;	
	
	@Resource
	FrontPromotionMapper frontPromotionMapper;	

	
	public void getMainService(FrontMainVO frontMainVO){
		
		//Today, HOT100 목록
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sort", "a.no");
		param.put(CommonConstants.ROW_START, 0);
		param.put(CommonConstants.PAGE_SIZE, 18);
		param.put("mode", "0");
		frontMainVO.setHotGoods1List(frontGoodsMapper.getFrontMainGoodsList(param));
		param.put("mode", "1");
		frontMainVO.setHotGoods2List(frontGoodsMapper.getFrontMainGoodsList(param));
		param.put("mode", "2");
		frontMainVO.setHotGoods3List(frontGoodsMapper.getFrontMainGoodsList(param));
				
		//What's New 목록
		param.put("mode", "3");
		frontMainVO.setNewGoodsList(frontGoodsMapper.getFrontMainGoodsList(param));
		
		//Sale this week 목록
		param.put("mode", "4");
		frontMainVO.setSaleGoodsList(frontGoodsMapper.getFrontMainGoodsList(param));
		
		
		//유효한 팝업목록 조회
		//requestSet.setProperty("poptype", "poptype = 'noname'");
		frontMainVO.setPoptype("noname");
		frontMainVO.setWinPopList(mapper.getPopupUsableList(frontMainVO));
		
		//requestSet.setProperty("poptype", "poptype = 'fix'");
		frontMainVO.setPoptype("fix");
		frontMainVO.setFixPopList(mapper.getPopupUsableList(frontMainVO));
		
		//requestSet.setProperty("poptype", "poptype = 'move'");
		frontMainVO.setPoptype("move");
		frontMainVO.setMovePopList(mapper.getPopupUsableList(frontMainVO));
		
		//배너 조회
		List<GdBanner> main_banner_list = mapper.getFrontMainBannerList(frontMainVO.getSkin());
		if (main_banner_list != null) {
			for (int i=0; i<main_banner_list.size(); i++) {
				GdBanner gbBanner = (GdBanner) main_banner_list.get(i);
				if ("1".equals(gbBanner.getLoccd())) {
					frontMainVO.getSlideBannerList().add(gbBanner);
				} else if ("2".equals(gbBanner.getLoccd())) {
					frontMainVO.getBottomBannerList().add(gbBanner);
				}
			}
		}

		//프로모션 조회
		FrontPromotionVO frontPromotionVO = new FrontPromotionVO();
		frontPromotionVO.setSkin(frontMainVO.getSkin());
		frontPromotionVO.setLoccd("1");
		frontMainVO.setPromotion1(frontPromotionMapper.selectFrontPromotion(frontPromotionVO));
		frontPromotionVO.setLoccd("2");
		frontMainVO.setPromotion2(frontPromotionMapper.selectFrontPromotion(frontPromotionVO));
		frontPromotionVO.setLoccd("3");
		frontMainVO.setPromotion3(frontPromotionMapper.selectFrontPromotion(frontPromotionVO));
		frontPromotionVO.setLoccd("4");
		frontMainVO.setPromotion4(frontPromotionMapper.selectFrontPromotion(frontPromotionVO));
	}
}