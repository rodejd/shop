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
package com.wepinit.wepinit_shop.xmall.seller.member.service;

import com.wepinit.wepinit_shop.xmall.seller.member.dao.SellerEmailMapper;
import com.wepinit.wepinit_shop.xmall.seller.member.vo.SellerEmailFM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class SellerEmailService {
	
	private static final Logger logger = LoggerFactory.getLogger(SellerEmailService.class);
	
	@Resource
	SellerEmailMapper mapper;
	
	public SellerEmailFM getSellerEmailList(SellerEmailFM sellerEmailFM) throws Exception {
		
		sellerEmailFM.setRowCount(mapper.getSellerEmailListCount(sellerEmailFM)); //총 갯수

		sellerEmailFM.setSellerEmailList(mapper.getSellerEmailList(sellerEmailFM)); // 목록
		
//		noticeFM.setSchText(noticeFM.getTmpText());
//		noticeFM.setSchType(noticeFM.getTmpType());

		return sellerEmailFM;
		
	}	

	public void insertGdEmailer(Map<String, Object> param){
		mapper.insertGdEmailer(param);
	}
	
}
