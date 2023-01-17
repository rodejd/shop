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

import com.wepinit.wepinit_shop.xmall.admin.vo.board.CooperationVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCooperation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CooperationMapper {
	
	public int getCooperationTotalCount();
	public int getCooperationCount(@Param("vo") CooperationVO vo);
	public List<GdCooperation> getCooperList(@Param("vo")CooperationVO vo);
	public GdCooperation getCooperView(int sno);
	public void deleteCooperation(int sno);
	public void updateCooperModify(CooperationVO cooperVO);
	public void updateCooperDateModify(@Param("vo")CooperationVO vo);
	public void updateCooperAllModify(Map<String, Object> param);

}
