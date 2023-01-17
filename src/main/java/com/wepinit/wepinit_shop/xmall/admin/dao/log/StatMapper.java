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

import com.wepinit.wepinit_shop.xmall.admin.vo.log.*;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatMapper {
	/*
	 * 1.매출분석
	 * 
	 * */
	
	//매출통계
	public List<StatSaleOutVO> getStatSaleList1(StatSaleVO statSaleVO);	
	public List<StatSaleOutVO> getStatSaleList2(StatSaleVO statSaleVO);	
	public List<StatSaleOutVO> getStatSaleList3(StatSaleVO statSaleVO);
	
	//결제수단분석
	public List<StatSettlekindOutVO> getStatSettlekindList(StatSettlekindVO statSettlekindVO);
	
	//연령별매출분석
	public List<StatAgeOutVO> getStatAgeList(StatAgeVO statAgeVO);
	
	//지역별매출분석
	public List<StatAreaOutVO> getStatAreaList(StatAreaVO statAreaVO);
	
	//성별매출분석
	public List<StatSexOutVO> getStatSexList(StatSexVO statSexVO);
	
}
