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

import com.wepinit.wepinit_shop.xmall.admin.dao.board.MemberQnaMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.board.MemberQnaVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMemberQna;
import com.wepinit.wepinit_shop.xmall.common.vo.join.MemberMemberqna;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MemberQnaService {
	
	@Resource
	MemberQnaMapper mapper;
	
	public int getMemberQnaTotalCount(){
		return mapper.getMemberQnaTotalCount();
	}
	public int getMemberQnaCount(MemberQnaVO vo){
		return mapper.getMemberQnaCount(vo);
	}
	public List<MemberMemberqna> getMemberQnaList(MemberQnaVO vo){	
		return mapper.getMemberQnaList(vo);
	}
	public GdMemberQna getMemberQnaInfo(int sno){
		return mapper.getMemberQnaInfo(sno);
	}
	public String getMemberQnaID(int mno){
		return mapper.getMemberQnaID(mno);
	}
	public List<GdMember> getMemberInfo(){
		return mapper.getMemberInfo();
	}
	public GdMemberQna getMemberQnaParentInfo(int parent){
		return mapper.getMemberQnaParentInfo(parent);
	}
	public void deleteMemberQna(int sno){
		mapper.deleteMemberQna(sno);
	}
	public void updateMemberQna(MemberQnaVO vo){
		mapper.updateMemberQna(vo);
	}
	public void insertMemberQnaReply(MemberQnaVO vo){
		mapper.insertMemberQnaReply(vo);
	}
	public GdMemberQna getMemberQnaTitleAndContent(int sno) {
		return mapper.getMemberQnaTitleAndContent(sno);
	}

}
