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

import com.wepinit.wepinit_shop.xmall.admin.vo.board.MemberQnaVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMemberQna;
import com.wepinit.wepinit_shop.xmall.common.vo.join.MemberMemberqna;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberQnaMapper {
	
	public int getMemberQnaTotalCount();
	public int getMemberQnaCount(@Param("vo") MemberQnaVO vo);
	public List<MemberMemberqna> getMemberQnaList(@Param("vo")MemberQnaVO vo);
	public GdMemberQna getMemberQnaInfo(@Param("sno")int sno);
	public String getMemberQnaID(@Param("mno")int mno);
	public List<GdMember> getMemberInfo();
	public GdMemberQna getMemberQnaParentInfo(@Param("parent")int parent);
	public void deleteMemberQna(@Param("sno")int sno);
	public void updateMemberQna(MemberQnaVO vo);
	public void insertMemberQnaReply(MemberQnaVO vo);
	public GdMemberQna getMemberQnaTitleAndContent(int sno);
}
