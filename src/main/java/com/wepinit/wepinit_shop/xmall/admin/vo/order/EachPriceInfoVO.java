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
package com.wepinit.wepinit_shop.xmall.admin.vo.order;


// 관리자 결제가격 출력시 한 상품에 대한 결제정보
public class EachPriceInfoVO {
	private int ea;					// 상품 갯수
	private int price;				// 상품 원가
	private int optPrice;			// 옵션가(들)
	private int oneOrderPrice;		// 주문가격(상품원가 + 옵션가's) 한개에 대한 금액
	private int totalOrderPrice;	// 총 주문가격(상품갯수 * (상품원가 + 옵션가's)) : 주문상세에서는 소계
	
	private int supplyPrice;		// 공급가 (금액계산시 사용되지 않음)
	private int memberDcPrice;		// 회원 할인가
	private int couponPrice;		// 쿠폰 할인가
	private int reservePrice;		// 적립금 사용가
	private int totDiscountPrice;	// 총 할인가(회원할인 + 쿠폰할인 + 적립금)
	private int deleveryPrice;		// (배송비정책이 상품당이라면) ? 배송비 : 0 
	private int paymentPrice;		// 결제 가격((총 주문가격 - 총 할인가) + (배송비정책이 상품당이라면)배송비)
	
	public int getEa() {
		return ea;
	}
	public void setEa(int ea) {
		this.ea = ea;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getOptPrice() {
		return optPrice;
	}
	public void setOptPrice(int... optPrice) {
		for(int price : optPrice) {
			this.optPrice += price;
		}
	}
	public int getOneOrderPrice() {
		return oneOrderPrice;
	}
	public void setOneOrderPrice(int oneOrderPrice) {
		this.oneOrderPrice = oneOrderPrice;
	}
	public int getTotalOrderPrice() {
		return totalOrderPrice;
	}
	public void setTotalOrderPrice(int totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}
	public int getSupplyPrice() {
		return supplyPrice;
	}
	public void setSupplyPrice(int supplyPrice) {
		this.supplyPrice = supplyPrice;
	}
	public int getMemberDcPrice() {
		return memberDcPrice;
	}
	public void setMemberDcPrice(int memberDcPrice) {
		this.memberDcPrice = memberDcPrice;
	}
	public int getCouponPrice() {
		return couponPrice;
	}
	public void setCouponPrice(int couponPrice) {
		this.couponPrice = couponPrice;
	}
	public int getReservePrice() {
		return reservePrice;
	}
	public void setReservePrice(int reservePrice) {
		this.reservePrice = reservePrice;
	}
	public int getTotDiscountPrice() {
		return totDiscountPrice;
	}
	public void setTotDiscountPrice(int totDiscountPrice) {
		this.totDiscountPrice = totDiscountPrice;
	}
	public int getDeleveryPrice() {
		return deleveryPrice;
	}
	public void setDeleveryPrice(int deleveryPrice) {
		this.deleveryPrice = deleveryPrice;
	}
	public int getPaymentPrice() {
		return paymentPrice;
	}
	public void setPaymentPrice(int paymentPrice) {
		this.paymentPrice = paymentPrice;
	}
}
