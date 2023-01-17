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
package com.wepinit.wepinit_shop.xmall.front.dao.event;

import com.wepinit.wepinit_shop.xmall.admin.vo.event.SurveyVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdEvent;
import com.wepinit.wepinit_shop.xmall.common.vo.GdEventMemo;
import com.wepinit.wepinit_shop.xmall.front.vo.event.FrontEventVO;
import com.wepinit.wepinit_shop.xmall.front.vo.event.FrontSurveyCommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface FrontEventMapper {
	
	//이벤트목록
	public int getFrontEventCount(Map<String, Object> param);
	public List<GdEvent> getFrontEventList(Map<String, Object> param);
	public GdEvent getFrontEventObj(FrontEventVO frontEventVO);
	
	//종료이벤트목록
	public int getFrontEventEndCount(Map<String, Object> param);
	public List<GdEvent> getFrontEventEndList(Map<String, Object> param);
	
	//이벤트메모
	public int getFrontEventMemoCount(Map<String, Object> param);
	public List<GdEventMemo> getFrontEventMemoList(Map<String, Object> param);
	
	//이벤트댓글(추가,수정,삭제)
	public void deleteEventMemo(Map<String, Object> param);
	public void insertEventMemo(Map<String, Object> param);
	public void updateEventMemo(Map<String, Object> param);
	
	//설문조사 목록
	public List<SurveyVO> getFrontSurveyList(Map<String,Object> param);
	public List<SurveyVO> getFrontSurveyEndList(Map<String,Object> param);
	
	//설문조사 투표 
	public void updateSurveyCnt(Map<String,Object> param);
	public void updateSurveyMember(Map<String,Object> param);
	
	public int surveyOnOff(@Param("surveySno") String sno ,@Param("m_no")int mno);
	
	public List<FrontSurveyCommentVO> getFrontSurveyCommentList(String sno);
	
	public void insertSurveyMemo(Map<String,Object> param);
	public void deleteSurveyMemo(String sno);
	public void updateSurveyMemo(Map<String,Object> param);
	
}


