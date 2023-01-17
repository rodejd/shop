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
import com.wepinit.wepinit_shop.xmall.common.vo.GdSmsAddress;
import com.wepinit.wepinit_shop.xmall.common.vo.GdSmsSample;
import com.wepinit.wepinit_shop.xmall.seller.member.vo.SellerSmsFM;
import com.wepinit.wepinit_shop.xmall.seller.member.vo.SellerSmsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SellerSmsMapper {

	
	/**
	 * SMS 발송리스트 (카운트)
	 * @param sellerSmsFM
	 * @return
	 */
	public int getSellerSmsListCount(SellerSmsFM sellerSmsFM);

	/**
	 * SMS 발송리스트
	 * @param sellerSmsFM
	 * @return
	 */
	public List<SellerSmsVO> getSellerSmsList(SellerSmsFM sellerSmsFM);

	/**
	 * 회원 그룹별 조회
	 * @param sellerMemberFM
	 * @return
	 */
	public List<GdMemberGrp> getGdMemberGrpList(SellerSmsFM sellerSmsFM);
	
	/**
	 * SMS 주소록
	 * @param sellerSmsFM
	 * @return
	 */
	public int getGdSmsAddressCount(SellerSmsFM sellerSmsFM);
	
	/**
	 * SMS 주소록
	 * @param sellerSmsFM
	 * @return
	 */
	public List<GdSmsAddress> getGdSmsAddressList(SellerSmsFM sellerSmsFM);
	
	/**
	 * SMS문자예제
	 * @param param
	 * @return
	 */
	public int getGdSmsSampleCount(Map<String, Object> param);

	public List<GdSmsSample> getGdSmsSampleList(Map<String, Object> param);
	
	public GdSmsSample getSmsSampleInfo(SellerSmsFM sellerSmsFM);
	
	public void insertSmsSample(SellerSmsFM sellerSmsFM);
	
	public void updateSmsSample(SellerSmsFM sellerSmsFM);
	
	// SMS발송
	public void smsSend(@Param("callTo")String callTo, @Param("callFrom")String callFrom, @Param("smsTxt")String smsTxt, @Param("smsType")String smsType);

	// SMS이력저장
	public void smsLog(@Param("msg")String msg, @Param("toTran")String toTran, @Param("type")String type, @Param("cnt")String cnt);

	public List<GdSmsAddress> getSmsAddressList(Map<String, Object> param);
	
}
