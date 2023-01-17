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
package com.wepinit.wepinit_shop.xmall.seller.basic.vo;

public class SellerDeliveryOverPolicyVO {
	private int sno;
	private String sellerCd;		// 판매사 코드
	private String cities;			// 추가배송비 지정 도시
	private int addDeliveryPrice;	// 추가배송비
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	public String getCities() {
		return cities;
	}
	public void setCities(String cities) {
		this.cities = cities;
	}
	public int getAddDeliveryPrice() {
		return addDeliveryPrice;
	}
	public void setAddDeliveryPrice(int addDeliveryPrice) {
		this.addDeliveryPrice = addDeliveryPrice;
	}
	@Override
	public String toString() {
		return "GdSellerDeliveryOverPolicyVO [sno=" + sno + ", sellerCd="
				+ sellerCd + ", cities=" + cities + ", addDeliveryPrice="
				+ addDeliveryPrice + "]";
	}
}
