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
package com.wepinit.wepinit_shop.xmall.admin.dao.acount;

import com.wepinit.wepinit_shop.xmall.admin.vo.acount.AcountVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AcountMapper {

	public List<AcountVO> getAcountInfo(AcountVO acountVO);
	
	public int getAcountListTotalCount();
	
	public int getAcountListCount(AcountVO acountVO);
	
	public void updateFlag(Map<String, Object> paramsDB);
	
	public List<AcountVO> getAcountListData(AcountVO acountVO);
}
