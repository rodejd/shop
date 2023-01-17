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

import com.wepinit.wepinit_shop.xmall.seller.member.vo.SellerEmailFM;
import com.wepinit.wepinit_shop.xmall.seller.member.vo.SellerEmailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SellerEmailMapper {

	
	/**
	 * 이메일 발송리스트 (카운트)
	 * @param sellerEmailFM
	 * @return
	 */
	public int getSellerEmailListCount(SellerEmailFM sellerEmailFM);

	/**
	 * 이메일 발송리스트
	 * @param sellerMailFM
	 * @return
	 */
	public List<SellerEmailVO> getSellerEmailList(SellerEmailFM sellerEmailFM);

	/**
	 * 이메일 발송이력 저장
	 * @param param
	 */
	public void insertGdEmailer(Map<String, Object> param);
}
