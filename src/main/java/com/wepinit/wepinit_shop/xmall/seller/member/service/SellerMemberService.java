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

import com.wepinit.wepinit_shop.xmall.seller.member.dao.SellerMemberMapper;
import com.wepinit.wepinit_shop.xmall.seller.member.vo.SellerMemberFM;
import com.wepinit.wepinit_shop.xmall.seller.member.vo.SellerOrderMemeberVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class SellerMemberService {
	@Resource
	SellerMemberMapper mapper;
	
	//구매회원리스트
	public List<SellerOrderMemeberVO> getSellerOrderMemberList(SellerMemberFM sellerMemberFM){
		return mapper.getSellerOrderMemberList(sellerMemberFM);
	}
	//총 구매회원
	public int getSellerOrderMemberCount(SellerMemberFM sellerMemberFM){
		return mapper.getSellerOrderMemberCount(sellerMemberFM);
	}

	
}
