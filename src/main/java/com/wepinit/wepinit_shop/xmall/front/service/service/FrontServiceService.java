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
package com.wepinit.wepinit_shop.xmall.front.service.service;

import com.wepinit.wepinit_shop.xmall.admin.vo.board.CooperationVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCode;
import com.wepinit.wepinit_shop.xmall.common.vo.GdFaq;
import com.wepinit.wepinit_shop.xmall.common.vo.GdListBank;
import com.wepinit.wepinit_shop.xmall.front.dao.service.FrontServiceMapper;
import com.wepinit.wepinit_shop.xmall.front.vo.service.FrontServiceFaqVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FrontServiceService {
	
	Logger logger = LoggerFactory.getLogger(FrontServiceService.class);
	
	@Resource
	FrontServiceMapper mapper;	

	public int setCooperation(CooperationVO cooperationVO) throws Exception {
		return mapper.setCooperation(cooperationVO);
	}
	
	public List<GdListBank> bankUseList() {
		return mapper.bankUseList();
	} 
	
	public List<GdFaq> faqRecentList(Map<String, Object> map) {
		return mapper.faqRecentList(map);
	}
	
	public int frontFaqlistCount(Map<String, Object> map) {
		return mapper.frontFaqlistCount(map);
	}
	
	public List<GdFaq> frontFaqlistSelect(Map<String, Object> map) {
		return mapper.frontFaqlistSelect(map);
	}
	
	public List<GdCode> faq3thCategorySelect() {
		return mapper.faq3thCategorySelect();
	}

	public Map<String, Object> getParamMap(FrontServiceFaqVO faqVO) {
		String sitemcd = faqVO.getSitemcd();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("faqSword", faqVO.getSword());		// 검색어
		map.put("faqSitemcd", (StringUtils.hasText(sitemcd) ? sitemcd :  "01"));	// 선택 카테코리 기본값 01
		map.put("sitemcd", (StringUtils.hasText(sitemcd) ? sitemcd : "itemcd"));
		map.put("sword", "%" + faqVO.getSword() + "%");
		if(StringUtils.hasText(faqVO.getCname())) {
			map.put("sitemcd", "itemcd");
			map.put("sword", "%" + faqVO.getCname() + "%");
			map.put("faqSword", faqVO.getCname());
		}
		if(StringUtils.hasText(faqVO.getCname())) {
			map.put("sitemcd", "itemcd");
		}
		
		faqVO.setRowCount(this.frontFaqlistCount(map));
		
		map.put(CommonConstants.ROW_START, faqVO.getRowStart());
		map.put(CommonConstants.PAGE_SIZE, faqVO.getPageSize());
		return map;
	}
	
	public Map<String, String> getCodeMap() throws Exception {
		List<GdCode> gdCodeList = ShopLibFunction.codeitem("faq");
		Map<String, String> codeMap = new HashMap<String, String>();
		for (GdCode gdCode : gdCodeList) {
			codeMap.put(gdCode.getItemcd(), gdCode.getItemnm());
		}
		return codeMap;
	}
}