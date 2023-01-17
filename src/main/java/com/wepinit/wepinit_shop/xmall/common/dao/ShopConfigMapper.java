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
package com.wepinit.wepinit_shop.xmall.common.dao;

import com.wepinit.wepinit_shop.xmall.common.vo.GdConfSet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShopConfigMapper {

	public GdConfSet getShopConfigProperty(Map<String, String> param);
	public List<GdConfSet> load(Map<String, String> param);
	public List<Map<String, Object>> getProperties2(Map<String, String> param);
	public int setShopConfigProperty(Map<String, String> param);
	public int setShopConfigProperty3(Map<String, String> param);
	public void setConfValue(@Param("confCd")String confCd, @Param("dimSet")String dimSet, @Param("dim1Var")String dim1Var, @Param("dimVal")String dimVal);
	public List<Map<String,Object>> getConfValueArry(@Param("confCd")String confCd, @Param("dimSet")String dimSet, @Param("dim1Var")String dim1Var);
	public Map<String,Object> getConfValue(@Param("confCd")String confCd, @Param("dimSet")String dimSet, @Param("dim1Var")String dim1Var);
}
