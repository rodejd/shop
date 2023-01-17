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

import com.wepinit.wepinit_shop.xmall.admin.dao.board.CooperationMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.board.CooperationVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCooperation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CooperationService {
	
	@Resource
	CooperationMapper mapper;
	
	public int getCooperationTotalCount(){
		return mapper.getCooperationTotalCount();
	}
	public int getCooperationCount(CooperationVO vo){
		return mapper.getCooperationCount(vo);
	}
	public List<GdCooperation> getCooperList(CooperationVO vo){
		return mapper.getCooperList(vo);
	}
	public GdCooperation getCooperView(int sno) {
		return mapper.getCooperView(sno);
	}
	public void deleteCooperation(int sno) {
		mapper.deleteCooperation(sno);
	}
	public void updateCooperModify(CooperationVO cooperVO){
		mapper.updateCooperModify(cooperVO);
	}
	public void updateCooperDateModify(CooperationVO vo){
		mapper.updateCooperDateModify(vo);
	}
	public void updateCooperAllModify(String itemcd, int sno){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("itemcd", itemcd);
		param.put("sno", sno);
		
		mapper.updateCooperAllModify(param);
	}

}
