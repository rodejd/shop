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
package com.wepinit.wepinit_shop.xmall.front.vo.order;

/**
 * front 화면에서 주문시 관리자의 쇼핑몰 기본설정 정보에 따라 화면에 출력해주기 위하여
 * 관리자가 설정한 기본 설정정보값을 저장합니다.
 * */
public class FrontOrderAdminSettingVO {
	//0 : 사용 , 1 : 사용하지 않음
	private String couponUseYn;
	//0 : 하나의 주문에 여러 쿠폰 사용가능 , 1 : 하나의 주문에는 오직 한개의 쿠폰만 사용
	private String couponUseMultiple;
	
	public void setCouponUseYn(String couponUseYn) {
		this.couponUseYn = couponUseYn;
	}
	
	public String getCouponUseYn() {
		return couponUseYn;
	}
	
	public String getCouponUseMultiple() {
		return couponUseMultiple;
	}
	
	public void setCouponUseMultiple(String couponUseMultiple) {
		this.couponUseMultiple = couponUseMultiple;
	}
}
