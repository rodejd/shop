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
package com.wepinit.wepinit_shop.xmall.front.dao.board;

import com.wepinit.wepinit_shop.xmall.common.vo.GdBdBoard;
import com.wepinit.wepinit_shop.xmall.common.vo.GdBoardMemo;
import com.wepinit.wepinit_shop.xmall.common.vo.XmBoardSetup;
import com.wepinit.wepinit_shop.xmall.common.vo.join.BdBoardmemo;
import com.wepinit.wepinit_shop.xmall.front.vo.board.FrontBoardCmtVO;
import com.wepinit.wepinit_shop.xmall.front.vo.board.FrontBoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface FrontBoardMapper {
	
	// 매개변수는 하나 이상인 경우 Map 형태로 작성
//	public List<TSample> getFrontBoardList(Map<String, Object> param);
//	public TSample getBoardSample(String sample_no);
//	public int setFrontBoardReg(Map<String, Object> param);
//	public int setFrontBoardMod(Map<String, Object> param);
//	public int getFrontBoardRowCount(Map<String, Object> param);
	
	public XmBoardSetup getFrontBoardConfig(String id);
	public String getFrontBoardListCheckDesc(String id);
	public int getFrontBoardDataCount(FrontBoardVO vo);
	public List<BdBoardmemo> getFrontBoardListWithRecentReply(FrontBoardVO vo);
	public List<GdBdBoard> getFrontBoardList(FrontBoardVO vo);
	public void updateAddHitCount(FrontBoardVO vo);
	public GdBdBoard getFrontBoardInfo(FrontBoardVO vo);
	public int getFrontBoardReplyCount(FrontBoardVO vo);
	public List<GdBoardMemo> getFrontBoardReplyList(FrontBoardVO vo);
	public void insertFrontBoardReply(FrontBoardCmtVO vo);
	public void updateFrontBoardReplyAddCount(FrontBoardCmtVO vo);
	public void deleteFrontBoardReply(int sno);
	public void updateFrontBoardReplySubCount(FrontBoardCmtVO vo);
	public int getFrontBoardReplyPassword(FrontBoardCmtVO vo);
	public GdBdBoard getFrontBoardIndex(FrontBoardVO vo);
	public int getFrontBoardIndexCount(FrontBoardVO vo);
	public int getFrontBoardNotiCount(FrontBoardVO vo);
	public int getFrontBoardInfCount(FrontBoardVO vo);
	public void insertFrontBoardInf(FrontBoardVO vo);
	public void updateFrontBoardNotiIdx(FrontBoardVO vo);
	public void updateFrontBoardInfNum(FrontBoardVO vo);
	public void updateFrontBoardInfNum2(FrontBoardVO vo);
	public int getFrontBoardSearchNo(FrontBoardVO vo);
	public void updateFrontBoardInfo(FrontBoardVO vo);
	public void updateFrontBoardInfAddNum(FrontBoardVO vo);
	public void insertFrontBoardInfo(FrontBoardVO vo);
	public int getFrontBoardPasswordChk(FrontBoardCmtVO vo);
//	public GdBdBoard getFrontBoardDetailInfo(FrontBoardVO vo);
	public void deleteBoardMemo(int no);
	public void updateBoardInfSubNum(@Param("id")String id);
	public void deleteFrontBoardInfo(FrontBoardVO vo);
	public int getFrontBoardTotalCount(FrontBoardVO vo);
	
}
