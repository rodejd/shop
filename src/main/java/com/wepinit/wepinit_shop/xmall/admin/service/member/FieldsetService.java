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

import com.wepinit.wepinit_shop.xmall.admin.dao.member.FieldsetMapper;
import com.wepinit.wepinit_shop.xmall.common.vo.GdConfSet;
import com.wepinit.wepinit_shop.xmall.common.vo.join.MemberMemberGrp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FieldsetService {

	@Resource
	FieldsetMapper mapper;
	
	public List<GdConfSet> getFieldSetProperty(GdConfSet gd){
		return mapper.getFieldSetProperty(gd);
	}
	public List<MemberMemberGrp> getMemberGroupLIST(String level){
		return mapper.getMemberGroupLIST(level);
	}
}
