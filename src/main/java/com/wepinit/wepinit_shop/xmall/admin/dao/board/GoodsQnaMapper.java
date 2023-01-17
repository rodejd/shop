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

import com.wepinit.wepinit_shop.xmall.admin.vo.board.GoodsQnaVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsQna;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsQnaMapper {
	
	public int getGoodsQnaTotalCount();
	public List<String> getGoodsQnaGoodsNo(@Param("sword")String sword);
	public List<String> getGoodsQnaGoodsParent(Map<String, Object> param);
	public int getGoodsQnaGoodsSearchCount(@Param("schparent")String schparent);
	public List<GdGoodsQna> getGoodsQnaGoodsSearchList(Map<String, Object> param);
	public GdGoodsQna getGoodsQnaInfo(int sno);
	public List<GdMember> getGoodsQnaAdminMemberList();
	public void deleteGoodsQna(int sno);
	public void updateGoodsQna(GoodsQnaVO vo);
	public void insertGoodsQnaReply(GoodsQnaVO vo);
	public void deleteGoodsQnaParent(int sno);
	
}
