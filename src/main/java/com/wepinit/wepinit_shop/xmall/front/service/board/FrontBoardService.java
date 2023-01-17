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
package com.wepinit.wepinit_shop.xmall.front.service.board;

import com.wepinit.wepinit_shop.xmall.common.vo.GdBdBoard;
import com.wepinit.wepinit_shop.xmall.common.vo.GdBoardMemo;
import com.wepinit.wepinit_shop.xmall.common.vo.XmBoardSetup;
import com.wepinit.wepinit_shop.xmall.common.vo.join.BdBoardmemo;
import com.wepinit.wepinit_shop.xmall.front.dao.board.FrontBoardMapper;
import com.wepinit.wepinit_shop.xmall.front.vo.board.FrontBoardCmtVO;
import com.wepinit.wepinit_shop.xmall.front.vo.board.FrontBoardVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class FrontBoardService {
	
	Logger logger = LoggerFactory.getLogger(FrontBoardService.class);
	
	@Resource
	FrontBoardMapper mapper;	

	public XmBoardSetup getFrontBoardConfig(String id){
		return mapper.getFrontBoardConfig(id);
	}
	public String getFrontBoardListCheckDesc(String id){
		return mapper.getFrontBoardListCheckDesc(id);
	}
	public List<BdBoardmemo> getFrontBoardListWithRecentReply(FrontBoardVO vo){
		//총건수
		vo.setRowCount(mapper.getFrontBoardDataCount(vo));
		return mapper.getFrontBoardListWithRecentReply(vo);
	}
	public List<GdBdBoard> getFrontBoardNotiList(FrontBoardVO vo){
		return mapper.getFrontBoardList(vo);
	}
	public List<GdBdBoard> getFrontBoardList(FrontBoardVO vo){
		//총건수
		vo.setRowCount(mapper.getFrontBoardDataCount(vo));
		return mapper.getFrontBoardList(vo);
	}
	public void updateAddHitCount(FrontBoardVO vo){
		mapper.updateAddHitCount(vo);
	}
	public GdBdBoard getFrontBoardInfo(FrontBoardVO vo){
		return mapper.getFrontBoardInfo(vo);
	}
	public List<GdBoardMemo> getFrontBoardReplyList(FrontBoardVO vo){
		//총건수
		vo.setRowCount(mapper.getFrontBoardReplyCount(vo));
		return mapper.getFrontBoardReplyList(vo);
	}
	public void insertFrontBoardReply(FrontBoardCmtVO vo){
		mapper.insertFrontBoardReply(vo);
		//add count
		mapper.updateFrontBoardReplyAddCount(vo);
	}
	public void updateFrontBoardReplyAddCount(FrontBoardCmtVO vo){
		mapper.updateFrontBoardReplyAddCount(vo);
	}
	public void deleteFrontBoardReply(FrontBoardCmtVO vo){
		mapper.deleteFrontBoardReply(vo.getSno());
		//sub count
		mapper.updateFrontBoardReplySubCount(vo);
	}
	public void updateFrontBoardReplySubCount(FrontBoardCmtVO vo){
		mapper.updateFrontBoardReplySubCount(vo);
	}
	public int getFrontBoardReplyPassword(FrontBoardCmtVO vo){
		return mapper.getFrontBoardReplyPassword(vo);
	}
	public int getFrontBoardDataCount(FrontBoardVO vo){
		return mapper.getFrontBoardDataCount(vo);
	}
	public GdBdBoard getFrontBoardIndex(FrontBoardVO vo){
		return mapper.getFrontBoardIndex(vo);
	}
	public int getFrontBoardIndexCount(FrontBoardVO vo){
		return mapper.getFrontBoardIndexCount(vo);
	}
	public int getFrontBoardNotiCount(FrontBoardVO vo){
		return mapper.getFrontBoardNotiCount(vo);
	}
	public int getFrontBoardInfCount(FrontBoardVO vo){
		return mapper.getFrontBoardInfCount(vo);
	}
	public void insertFrontBoardInf(FrontBoardVO vo){
		mapper.insertFrontBoardInf(vo);
	}
	public void updateFrontBoardNotiIdx(FrontBoardVO vo){
		mapper.updateFrontBoardNotiIdx(vo);
	}
	public void updateFrontBoardInfNum(FrontBoardVO vo){
		mapper.updateFrontBoardInfNum(vo);
	}
	public void updateFrontBoardInfNum2(FrontBoardVO vo){
		mapper.updateFrontBoardInfNum2(vo);
	}
	public int getFrontBoardSearchNo(FrontBoardVO vo){
		return mapper.getFrontBoardSearchNo(vo);
	}
	public void updateFrontBoardInfo(FrontBoardVO vo){
		mapper.updateFrontBoardInfo(vo);
	}
	public void updateFrontBoardInfAddNum(FrontBoardVO vo){
		mapper.updateFrontBoardInfAddNum(vo);
	}
	public void insertFrontBoardInfo(FrontBoardVO vo){
		mapper.insertFrontBoardInfo(vo);
	}
	public int getFrontBoardPasswordChk(FrontBoardCmtVO vo){
		return mapper.getFrontBoardPasswordChk(vo);
	}
//	public GdBdBoard getFrontBoardDetailInfo(FrontBoardVO vo){
//		return mapper.getFrontBoardDetailInfo(vo);
//	}
	public void deleteBoardMemo(int no){
		mapper.deleteBoardMemo(no);
	}
	public void updateBoardInfSubNum(String id){
		mapper.updateBoardInfSubNum(id);
	}
	public void deleteFrontBoardInfo(FrontBoardVO vo){
		mapper.deleteFrontBoardInfo(vo);
	}
	public int getFrontBoardTotalCount(FrontBoardVO vo){
		return mapper.getFrontBoardTotalCount(vo);
	}
	
}