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
package com.wepinit.wepinit_shop.xmall.front.dao.service;

import com.wepinit.wepinit_shop.xmall.admin.vo.board.CooperationVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCode;
import com.wepinit.wepinit_shop.xmall.common.vo.GdFaq;
import com.wepinit.wepinit_shop.xmall.common.vo.GdListBank;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface FrontServiceMapper {
	
	// 매개변수는 하나 이상인 경우 Map 형태로 작성
//	public List<TSample> getFrontServiceList(Map<String, Object> param);
//	public TSample getServiceSample(String sample_no);
//	public int setFrontServiceReg(Map<String, Object> param);
//	public int setFrontServiceMod(Map<String, Object> param);
//	public int getFrontServiceRowCount(Map<String, Object> param);
	public int setCooperation(CooperationVO cooperationVO);
	public List<GdListBank> bankUseList();
	public List<GdFaq> faqRecentList(Map<String, Object> map); 
	public int frontFaqlistCount(Map<String, Object> map);
	public List<GdFaq> frontFaqlistSelect(Map<String, Object> map);
	public List<GdCode> faq3thCategorySelect();
}
