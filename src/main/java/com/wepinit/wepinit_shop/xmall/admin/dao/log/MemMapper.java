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
package com.wepinit.wepinit_shop.xmall.admin.dao.log;

import com.wepinit.wepinit_shop.xmall.admin.vo.log.MemAgeVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.log.MemAreaVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.log.MemNewVO;
import com.wepinit.wepinit_shop.xmall.common.vo.MemAgeOutVO;
import com.wepinit.wepinit_shop.xmall.common.vo.MemAreaOutVO;
import com.wepinit.wepinit_shop.xmall.common.vo.MemNewOutVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemMapper {
	
	/*
	 * 3.회원분석
	 * 
	 * */
	//신규회원분석
	public List<MemNewOutVO> getMemNewList(MemNewVO memNewVO);
	
	//연령별회원분석
	public List<MemAgeOutVO> getMemAgeList(MemAgeVO memAgeVO);
	
	//지역별회원분석
	public List<MemAreaOutVO> getMemAreaList(MemAreaVO memAreaVO);
	
}
