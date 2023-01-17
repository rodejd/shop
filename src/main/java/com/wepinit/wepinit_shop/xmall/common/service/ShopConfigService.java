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
package com.wepinit.wepinit_shop.xmall.common.service;

import com.wepinit.wepinit_shop.xmall.common.dao.ShopConfigMapper;
import com.wepinit.wepinit_shop.xmall.common.vo.GdConfSet;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopConfigService {
	
	@Resource
	ShopConfigMapper mapper;
	
	public GdConfSet getShopConfigProperty(String conf_cd, String dim_set, String dim1_var){
		Map<String, String> param = new HashMap<String, String>();
		
		param.put("conf_cd", conf_cd);
		param.put("dim_set", dim_set);
		param.put("dim1_var", dim1_var);
		
		return mapper.getShopConfigProperty(param);
	}
	
	public List<GdConfSet> load(Map<String, String> param){
		return mapper.load(param);
	}
	
	public List<Map<String, Object>> getProperties2(Map<String, String> param){
		return mapper.getProperties2(param);
	}
	
	public int setShopConfigProperty(Map<String, String> param){
		return mapper.setShopConfigProperty(param);
	}
	
	public int setShopConfigProperty3(Map<String, String> param){
		return mapper.setShopConfigProperty3(param);
	}

	public List<Map<String,Object>> getConfValueArry(String confCd, String dimSet, String dim1Var) {
		return mapper.getConfValueArry(confCd, dimSet, dim1Var);
	}
	
	public void setConfValue(String confCd, String dimSet, String dim1Var, String dimVal) {
		mapper.setConfValue(confCd, dimSet, dim1Var, dimVal);
	}
	
	public String getConfValue(String confCd, String dimSet, String dim1Var) {
	return (String)mapper.getConfValue(confCd, dimSet, dim1Var).get("dim_val");
	}
}
