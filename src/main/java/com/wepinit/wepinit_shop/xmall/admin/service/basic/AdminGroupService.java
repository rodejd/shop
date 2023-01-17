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
package com.wepinit.wepinit_shop.xmall.admin.service.basic;

import com.wepinit.wepinit_shop.xmall.admin.dao.basic.AdminGroupMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.basic.AdminGroupVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMemberGrp;
import com.wepinit.wepinit_shop.xmall.common.vo.join.MemberMemberGrp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminGroupService {

	@Resource
	AdminGroupMapper mapper;
	public List<GdMemberGrp> getMemberGroupSelectboxLIST(){
		return mapper.getMemberGroupSelectboxLIST();
	}
	public int getMemberCount(){
		return mapper.getMemberAllCount();
	}
	public List<MemberMemberGrp>getAdminMemberGroupMemberLIST1(AdminGroupVO vo){
		return mapper.getAdminMemberGroupMemberLIST1(vo);
	}
	public List<MemberMemberGrp>getUserMemberGroupMemberLIST1(AdminGroupVO vo){
		return mapper.getUserMemberGroupMemberLIST1(vo);
	}
	public List<MemberMemberGrp>getAdminMemberGroupMemberLIST2(AdminGroupVO vo){
		return mapper.getAdminMemberGroupMemberLIST2(vo);
	}
	public List<MemberMemberGrp>getUserMemberGroupMemberLIST2(AdminGroupVO vo){
		return mapper.getUserMemberGroupMemberLIST2(vo);
	}
	public void setMemberLevelUPDATE(int level, int mno){
		mapper.setMemberLevelUPDATE(level, mno);
	}
	
	public int getAdminMemberSearchCount(AdminGroupVO vo){
		return mapper.getAdminMemberSearchCount(vo);
	}
	public int getUserMemberSearchCount(AdminGroupVO vo){
		return mapper.getUserMemberSearchCount(vo);
	}
}
