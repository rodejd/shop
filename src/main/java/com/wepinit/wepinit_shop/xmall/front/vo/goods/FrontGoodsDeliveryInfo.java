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
package com.wepinit.wepinit_shop.xmall.front.vo.goods;

/**프론트 상품상세 배송 기본정책 정보
 * */
public class FrontGoodsDeliveryInfo {
	private int no;				// 0=기본배송정책 첫번째, 1 이상=기본배송정책 하단 추가된 정책들
	private String delivery;	// 관리자가 입력한 정책명
	private String free;		// 배송비 무료 최소조건
	private String deliType;	// 선불, 후불
	private String rDefault;	// 배송비
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public String getFree() {
		return free;
	}
	public void setFree(String free) {
		this.free = free;
	}
	public String getDeliType() {
		return deliType;
	}
	public void setDeliType(String deliType) {
		this.deliType = deliType;
	}
	public String getrDefault() {
		return rDefault;
	}
	public void setrDefault(String rDefault) {
		this.rDefault = rDefault;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getNo() {
		return no;
	}
	
}
