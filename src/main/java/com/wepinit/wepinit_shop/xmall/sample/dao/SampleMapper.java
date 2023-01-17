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
package com.wepinit.wepinit_shop.xmall.sample.dao;

import com.wepinit.wepinit_shop.xmall.sample.vo.SampleViewVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SampleMapper {
	
	// 매개변수는 하나 이상인 경우 Map 형태로 작성
	public List<SampleViewVO> getSampleList(Map<String, Object> param);
	public SampleViewVO getSample(String sample_no);
	public int setSampleReg(Map<String, Object> param);
	public int setSampleMod(Map<String, Object> param);
	public int getSampleRowCount(Map<String, Object> param);

}
