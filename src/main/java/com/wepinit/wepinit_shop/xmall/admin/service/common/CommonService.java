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
package com.wepinit.wepinit_shop.xmall.admin.service.common;

import com.wepinit.wepinit_shop.xmall.admin.dao.common.CommonMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.common.MenuVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdConfSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value="commonService")
public class CommonService {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonService.class);
	
	@Resource
	private CommonMapper commonMapper;
	
	public List<MenuVO> headerList() throws Exception {	
		logger.debug("service!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		logger.debug("lsit size : "+commonMapper.getHeaderMenuList().size());
		return commonMapper.getHeaderMenuList();
	}
	
	public List<MenuVO> leftList(String menuKey) throws Exception {
		return commonMapper.getLeftMenuList(menuKey);
	}
	
	public List<Map<String,Object>> getNavi(String url) throws Exception {
		return commonMapper.getNavi(url);
	}
	
	public GdConfSet getConfSet(GdConfSet vo) throws Exception {
		return commonMapper.getConfSet(vo);
	}
	
	public int selectRownum() throws Exception {
		return commonMapper.selectRownum();
	}

}
