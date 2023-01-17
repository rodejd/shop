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

import com.wepinit.wepinit_shop.xmall.admin.dao.board.GoodsQnaMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.board.GoodsQnaVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsQna;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsQnaService {
	
	@Resource
	GoodsQnaMapper mapper;
	
	public int getGoodsQnaTotalCount(){
		return mapper.getGoodsQnaTotalCount();
	}
	public List<String> getGoodsQnaGoodsNo(String sword){
		return mapper.getGoodsQnaGoodsNo(sword);
	}
	public List<String> getGoodsQnaGoodsParent(GoodsQnaVO vo, String schcate, String subtable){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("vo", vo);
		param.put("schcate", schcate);
		param.put("subtable", subtable);
		param.put("schSellerNm", vo.getSchSellerNm());
		
		return mapper.getGoodsQnaGoodsParent(param);
	}
	public int getGoodsQnaGoodsSearchCount(String schparent){
		return mapper.getGoodsQnaGoodsSearchCount(schparent);
	}
	public List<GdGoodsQna> getGoodsQnaGoodsSearchList(GoodsQnaVO vo, String schparent){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("vo", vo);
		param.put("schparent", schparent);
		
		return mapper.getGoodsQnaGoodsSearchList(param);
	}
	public GdGoodsQna getGoodsQnaInfo(int sno){
		return mapper.getGoodsQnaInfo(sno);
	}
	public List<GdMember> getGoodsQnaAdminMemberList(){
		return mapper.getGoodsQnaAdminMemberList();
	}
	public void deleteGoodsQna(int sno){
		mapper.deleteGoodsQna(sno);
		//부모글 삭제
		mapper.deleteGoodsQnaParent(sno);
	}
	public void updateGoodsQna(GoodsQnaVO vo){
		mapper.updateGoodsQna(vo);
	}
	public void insertGoodsQnaReply(GoodsQnaVO vo) {
		mapper.insertGoodsQnaReply(vo);
	}

}
