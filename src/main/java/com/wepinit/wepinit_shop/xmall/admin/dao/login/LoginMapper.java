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
package com.wepinit.wepinit_shop.xmall.admin.dao.login;

import com.wepinit.wepinit_shop.xmall.admin.vo.login.LoginVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import com.wepinit.wepinit_shop.xmall.common.vo.join.MemberMemberGrp;
import com.wepinit.wepinit_shop.xmall.seller.login.vo.SellerLoginProcVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface LoginMapper {

	public GdMember front_login_check_ID(Map<String, String> map);
	public GdMember front_login_check_password(Map<String, String> map);
	public int member_last_login(Map<String, String> map);
	
	public MemberMemberGrp front_my_Box_List_1(Map<String, String> map);
	public int front_mypage_coupon_list_count(Map<String, String> map);
	
	// #####
	// # 판매사 추가
	// #####
	public SellerLoginProcVO getSellerLoginCheck(LoginVO loginVO);
	
	public int getGdSellerMngCount(SellerLoginProcVO sellerLoginProcVO);
	public int getGdSellerAccCount(SellerLoginProcVO sellerLoginProcVO);
	
	
}
