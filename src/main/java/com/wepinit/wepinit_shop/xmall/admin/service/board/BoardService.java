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
package com.wepinit.wepinit_shop.xmall.admin.service.board;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xmall.admin.dao.board.BoardMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.board.BoardVO;
import com.wepinit.wepinit_shop.xmall.common.vo.XmBoardSetup;
import com.wepinit.wepinit_shop.xmall.common.vo.join.BoardBoardinf;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service
public class BoardService {

	@Resource
	BoardMapper mapper;
	public List<BoardBoardinf> getboardListJoinBDINF(BoardVO vo){
		return mapper.getboardListJoinBDINF(vo);
	}
	public XmBoardSetup getBoardCONFIG(String id){
		return mapper.getBoardCONFIG(id);
	}
	public int getBoardCount(){
		return mapper.getBoardCount();
	}
	public void setBoardLISTDELETE(String id){
		mapper.setBoardLISTDELETE(id);
	}
	public void setBoardLISTNFDELETE(String id){
		mapper.setBoardLISTNFDELETE(id);
	}
	public void setBoardXMBOARDDELETE(String id){
		mapper.setBoardXMBOARDDELETE(id);
	}
	public void setBoardDROP(String id){
		mapper.setBoardDROP(id);
	}
	public List getBoardINFSEARCH(BoardVO vo){
		return mapper.getBoardINFSEARCH(vo);
	}
	
	public void setBoardCREATE(String id){
		mapper.setBoardCREATE(id);
	}
	public void setBoardLISTADD(BoardVO vo){
		mapper.setBoardLISTADD(vo);
	}
	public void setBoardXMBOARDSETADD(BoardVO vo){
		mapper.setBoardXMBOARDSETADD(vo);
	}
	public List<XmBoardSetup>getBoardSEARCHCORRECT1(String id){
		return mapper.getBoardSEARCHCORRECT1(id);
		
	}
	public List<XmBoardSetup>getBoardSEARCHCORRECT2(String id){
		return mapper.getBoardSEARCHCORRECT2(id);
	}
	public void setBoardXMBOARDSETUPDATE(BoardVO vo){
		mapper.setBoardXMBOARDSETUPDATE(vo);
	}
	
	public int getBoardCheckId(String id){
		return mapper.getBoardCheckId(id);
	}
	
	public void getDirectoryMkdir(String id){
	String path = ConfigClass.FILE_PATH + "gd_bd_"+id; //폴더 경로
		File Folder = new File(path);
		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		if (!Folder.exists()) {
			try {
				Folder.mkdir(); // 폴더 생성합니다.
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}
}

