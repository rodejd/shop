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
package com.wepinit.wepinit_shop.xmall.admin.dao.board;

import com.wepinit.wepinit_shop.xmall.admin.vo.board.BoardVO;
import com.wepinit.wepinit_shop.xmall.common.vo.XmBoardSetup;
import com.wepinit.wepinit_shop.xmall.common.vo.join.BoardBoardinf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
	public List<BoardBoardinf> getboardListJoinBDINF(BoardVO vo);
	public XmBoardSetup getBoardCONFIG(@Param("id") String id);
	public int getBoardCount();
	public void setBoardLISTDELETE(@Param("id")String id);
	public void setBoardLISTNFDELETE(@Param("id")String id);
	public void setBoardXMBOARDDELETE(@Param("id")String id);
	public void setBoardDROP(@Param("id")String id);
	public List getBoardINFSEARCH(BoardVO vo);
	public void setBoardCREATE(@Param("id")String id);
	public void setBoardLISTADD(BoardVO vo);
	public void setBoardXMBOARDSETADD(BoardVO vo);
	public List<XmBoardSetup>getBoardSEARCHCORRECT1(@Param("id")String id);
	public List<XmBoardSetup>getBoardSEARCHCORRECT2(@Param("id")String id);
	public void setBoardXMBOARDSETUPDATE(BoardVO vo);
	
	public int getBoardCheckId(@Param("id")String id);
	
}
