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
package com.wepinit.wepinit_shop.xmall.sample.service;

import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.sample.dao.SampleMapper;
import com.wepinit.wepinit_shop.xmall.sample.vo.SampleFM;
import com.wepinit.wepinit_shop.xmall.sample.vo.SampleViewVO;
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
public class SampleService {
	
	Logger logger = LoggerFactory.getLogger(SampleService.class);
	
	@Resource
	SampleMapper mapper;
	
	public List<SampleViewVO> getSampleList(SampleFM sampleVO){
		Map<String, Object> param = null;
		param = new HashMap<String, Object>();
		
		param.put("sample_no", sampleVO.getSample_no());
		param.put("title", sampleVO.getTitle());
		param.put("description", sampleVO.getDescription());
		
		// 총건수 
		sampleVO.setRowCount(mapper.getSampleRowCount(param));
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, sampleVO.getPageSize());
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, sampleVO.getRowStart());
		
		return mapper.getSampleList(param);
	}
	
	public SampleViewVO getSample(String sample_no){
		return mapper.getSample(sample_no);
	}
	
	public int setSampleReg(Map<String, Object> param){
		return mapper.setSampleReg(param);
	}
	
	public int setSampleMod(Map<String, Object> param){
		return mapper.setSampleMod(param);
	}
}
