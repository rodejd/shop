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
package com.wepinit.wepinit_shop.xmall.seller.member.dao;

import com.wepinit.wepinit_shop.xmall.common.vo.GdMemberGrp;
import com.wepinit.wepinit_shop.xmall.seller.member.vo.SellerMemberFM;
import com.wepinit.wepinit_shop.xmall.seller.member.vo.SellerOrderMemeberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellerMemberMapper {
	
	public int getSellerOrderMemberCount(SellerMemberFM sellerMemberFM);	// SMS 발송리스트(카운트)
	public List<SellerOrderMemeberVO> getSellerOrderMemberList(SellerMemberFM sellerMemberFM);	//SMS 발송리스트

	// SMS보내기(그룹리스트 조회)
	public List<GdMemberGrp> getGdMemberGrpList(SellerMemberFM sellerMemberFM);
	
}
