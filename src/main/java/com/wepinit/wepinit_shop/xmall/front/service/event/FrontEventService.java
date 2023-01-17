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
package com.wepinit.wepinit_shop.xmall.front.service.event;

import com.wepinit.wepinit_shop.xmall.admin.vo.event.SurveyVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdEvent;
import com.wepinit.wepinit_shop.xmall.common.vo.GdEventMemo;
import com.wepinit.wepinit_shop.xmall.front.dao.event.FrontEventMapper;
import com.wepinit.wepinit_shop.xmall.front.vo.event.FrontEventVO;
import com.wepinit.wepinit_shop.xmall.front.vo.event.FrontSurveyCommentVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FrontEventService {
	
	Logger logger = LoggerFactory.getLogger(FrontEventService.class);
	
	@Resource
	FrontEventMapper mapper;
	
	public int getFrontEventCount(Map<String, Object> param) throws Exception {
		return mapper.getFrontEventCount(param);
	}

	public List<GdEvent> getFrontEventList(Map<String, Object> param) throws Exception {
		return mapper.getFrontEventList(param);
	}
	
	public int getFrontEventEndCount(Map<String, Object> param) throws Exception {
		return mapper.getFrontEventEndCount(param);
	}

	public List<GdEvent> getFrontEventEndList(Map<String, Object> param) throws Exception {
		return mapper.getFrontEventEndList(param);
	}
	
	public GdEvent getFrontEventObj(FrontEventVO frontEventVO) throws Exception {
		return mapper.getFrontEventObj(frontEventVO);
	}
	
	public int getFrontEventMemoCount(Map<String, Object> param) throws Exception {
		return mapper.getFrontEventMemoCount(param);
	}
	
	public List<GdEventMemo> getFrontEventMemoList(Map<String, Object> param) throws Exception {
		return mapper.getFrontEventMemoList(param);
	}
	
	public void deleteEventMemo(Map<String, Object> param) throws Exception {
		mapper.deleteEventMemo(param);
	}
	
	public void insertEventMemo(Map<String, Object> param) throws Exception {
		mapper.insertEventMemo(param);
	}
	
	public void updateEventMemo(Map<String, Object> param) throws Exception {
		mapper.updateEventMemo(param);
	}
	
	public List<SurveyVO> getFrontSurveyList(Map<String,Object> param) throws Exception{
		return mapper.getFrontSurveyList(param);
	}
	public List<SurveyVO> getFrontSurveyEndList(Map<String,Object> param) throws Exception{
		return mapper.getFrontSurveyEndList(param);
	}
	
	public void updateSurveyCnt(Map<String,Object> param) throws Exception{
		mapper.updateSurveyCnt(param);
	}
	public void updateSurveyMember(Map<String,Object> param) throws Exception{
		mapper.updateSurveyMember(param);
	}
	
	public int surveyOnOff(String sno ,int mno){
		return mapper.surveyOnOff(sno ,mno);
	}
	
	public List<FrontSurveyCommentVO> getFrontSurveyCommentList(String sno){
		return mapper.getFrontSurveyCommentList(sno);
	}
	public void insertSurveyMemo(Map<String,Object> param){
		mapper.insertSurveyMemo(param);
	}
	
	public void updateSurveyMemo(Map<String,Object> param){
		mapper.updateSurveyMemo(param);
	}
	public void deleteSurveyMemo(String sno){
		mapper.deleteSurveyMemo(sno);
	}
	

}