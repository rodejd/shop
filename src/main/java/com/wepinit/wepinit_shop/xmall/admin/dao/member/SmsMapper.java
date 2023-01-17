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
package com.wepinit.wepinit_shop.xmall.admin.dao.member;

import com.wepinit.wepinit_shop.xmall.admin.vo.member.SmsAddressVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.SmsAutoVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.SmsSendVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.seller.AdminSellerManagementVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMemberGrp;
import com.wepinit.wepinit_shop.xmall.common.vo.GdSmsAddress;
import com.wepinit.wepinit_shop.xmall.common.vo.GdSmsAutoSet;
import com.wepinit.wepinit_shop.xmall.common.vo.GdSmsSample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SmsMapper {
	
	/*
	 * 회원관리 > SMS설정 > SMS자동발송/설정
	 * 
	 * */
	// SMS자동발송/설정 조회
	public List<GdSmsAutoSet> getGdSmsAutoSetList(SmsAutoVO smsAutoVO);
	
	// SMS자동발송/설정 수정
	public void updateSmsAutoSet(SmsAutoVO smsAutoVO);
	
	
	/*
	 * 회원관리 > SMS설정 > SMS주소록
	 * 
	 * */
	
	// SMS주소록
	public List<GdSmsAddress> getSmsAddressGroupList();
	//public int getSmsAddressCount(Map<String, Object> param);
	//public List<GdSmsAddress> getSmsAddressList(Map<String, Object> param);
	public GdSmsAddress getGdSmsAddressInfo(SmsAddressVO smsAddressVO );
	public void updateSmsAddress(Map<String, Object> param);
	public void insertSmsAddress(Map<String, Object> param);
	public void deleteSmsAddress(Map<String, Object> param);
	
	public List<GdSmsAddress> getSmsAddressList(Map<String, Object> param);
	
	
	/*
	 * 회원관리 > SMS설정 > SMS보내기
	 * 
	 * */
	
	// SMS보내기(그룹리스트 조회)
	public List<GdMemberGrp> getGdMemberGrpList(SmsSendVO smsSendVO);
	
	//SMS주소록
	public int getGdSmsAddressCount(Map<String, Object> param);
	public List<GdSmsAddress> getGdSmsAddressList(Map<String, Object> param);
	
	//SMS문자예제
	public int getGdSmsSampleCount(Map<String, Object> param);
	public List<GdSmsSample> getGdSmsSampleList(Map<String, Object> param);
	public GdSmsSample getSmsSampleInfo(SmsSendVO smsSendVO);
	public void updateSmsSample(SmsSendVO smsSendVO);
	public void insertSmsSample(SmsSendVO smsSendVO);
	
	// SMS보내기(그룹리스트 조회)
	public GdMemberGrp getGdMemberGrpInfo(SmsSendVO smsSendVO);	
	
	// SMS발송
	public void smsSend(@Param("callTo")String callTo, @Param("callFrom")String callFrom, @Param("smsTxt")String smsTxt, @Param("smsType")String smsType);
	
	// SMS이력저장
	//public void smsLog(@Param("msg")String msg, @Param("toTran")String toTran, @Param("type")String type, @Param("cnt")String cnt, @Param("sellerCd")String sellerCd);
	public void smsLog(Map<String, Object> smsparam);
	
	// 판매사 주소록
	public int getSellerAddressGroupListCount(Map<String, Object> param);
	public List<AdminSellerManagementVO> getSellerAddressGroupList(Map<String, Object> param);
	
}
