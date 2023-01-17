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
package com.wepinit.wepinit_shop.xmall.admin.service.member;

import com.wepinit.wepinit_shop.xmall.admin.dao.member.GroupMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.GroupVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCode;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMemberGrp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
@Service
public class GroupService {

	@Resource
	GroupMapper mapper;
	
	public List<GdMemberGrp> groupList() throws Exception {
		return mapper.getMemberGroupList();
	}
	public List<GdMemberGrp> groupAdminList() throws Exception {
		return mapper.getMemberAdminGroupList();
	}
	public List<GdMemberGrp> popupGroupList(int sno) throws Exception{
		return mapper.getMemberPopupGroupList(sno);
	}
	public List<GdMemberGrp> popupSelectBoxList() throws Exception{
		return mapper.getMemberPopupSelectBoxList();
	}
	public List<GdMemberGrp> getLevelCount(int k_level) throws Exception{
		return mapper.getMemberLevelCount(k_level);
	}
	public void insertGroup(GroupVO vo) throws Exception{
		mapper.insertGroup(vo);
	}
	public void updateGroup(GroupVO vo) throws Exception{
		mapper.updateGroup(vo);
	}
	public void deleteGroup(int sno) throws Exception{
		mapper.deleteGroup(sno);
	}
	public HashMap<String, String> getCodeGroupItemLIST(String groupCd, String itemCd, String orderBy){
		List<GdCode> list =mapper.getCodeGroupItemLIST(groupCd, itemCd, orderBy);
		HashMap<String, String> map = new HashMap<String,String>();
		if(list != null && list.size() >0){
			for( GdCode s :list){
				map.put(s.getItemcd(), s.getItemnm());
			}
		}
		return map;
	}
}
