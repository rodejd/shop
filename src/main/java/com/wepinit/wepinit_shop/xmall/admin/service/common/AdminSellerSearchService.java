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

import com.wepinit.wepinit_shop.xmall.admin.dao.common.AdminSellerSearchMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.common.AdminSellerSearchFM;
import com.wepinit.wepinit_shop.xmall.admin.vo.common.AdminSellerSearchListVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value="sellerSearchService")
public class AdminSellerSearchService {
	
	@Resource
	private AdminSellerSearchMapper mapper;


	public int getSellerSearchCount(AdminSellerSearchFM adminSellerSearchFM) {
		
		return mapper.getSellerSearchCount(adminSellerSearchFM);
	}
	
	public List<AdminSellerSearchListVO> getSellerSearchList(AdminSellerSearchFM adminSellerSearchFM) {
		
		return mapper.getSellerSearchList(adminSellerSearchFM);
	}
}
