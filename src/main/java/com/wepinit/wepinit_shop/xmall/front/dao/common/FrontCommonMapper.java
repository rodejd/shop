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
* 설명 				: 	사용자 Community dao
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170208		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.dao.common;

import com.wepinit.wepinit_shop.xmall.common.vo.GdCategory;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsBrand;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMainCampaign;
import com.wepinit.wepinit_shop.xmall.common.vo.XmBoardSetup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface FrontCommonMapper {
	
		
	/**
	 * 카테고리 목록(front 상품 카테코리 left menu)
	 * @return
	 */
	public List<GdCategory> getFrontCommonCategoryList();
	
	/**
	 * 브랜드 목록(front 상품 브랜드 목록)
	 * @return
	 */
	public List<GdGoodsBrand> getFrontCommonBrandList();
	
	/**
	 * 커뮤니티 게시판 목록(front 게시판 Id, Name 가져오기)
	 * @return
	 */
	public List<XmBoardSetup> getFrontCommonBoardList();
	
	public List<GdMainCampaign> getFrontCommonMainCampaignList(String skin);


}
