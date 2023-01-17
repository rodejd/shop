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
package com.wepinit.wepinit_shop.xmall.admin.service.basic;

import com.wepinit.wepinit_shop.xmall.admin.dao.basic.ExchangeMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.basic.ExchangeVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExchangeService {
	
	@Resource
	ExchangeMapper mapper;
	
	public List<ExchangeVO> exchangeList(ExchangeVO exchangeVO) throws Exception {
		return mapper.getExchangeList(exchangeVO);
	}
	
	public int exchangeCount(ExchangeVO exchangeVO) throws Exception {
		return mapper.getExchangeCount(exchangeVO);
	}
	
	public void insertExchange(ExchangeVO vo) throws Exception {
		mapper.insertExchange(vo);
	}

}
