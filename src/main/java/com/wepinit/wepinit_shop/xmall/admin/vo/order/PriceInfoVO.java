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

import java.util.List;

// 총 결제금액 정보
public class PriceInfoVO {
	// 결제 상품목록
	private List<EachPriceInfoVO> eachPriceInfoList;
	
	// 총 주문금액 합산 (할인 전 토탈금액) - 주문상세 > 결제금액정보 > 주문금액
	private int totOrderPrice;
	
	// 총 결제금액( + 배송비) - 주문상세 > 결제금액정보 > 결제금액
	private int totalPaymentPrice;
	
	// 결제시 총 배송비 - 정책에 따라 변경되게끔 수정
	private int totDeleveryPrice;
	
	// 총 상품원가(옵션가 제외) - 주문상세 > 결제금액정보 > 상품가격
	private int totPrice;
	
	// 총 할인액(모든 할인 사항을 더한 총 금액) - 주문상세 > 결제금액정보 > 할인액
	private int totDcPrice;
	
	public void setEachPriceInfoList(List<EachPriceInfoVO> eachPriceInfoList) {

		this.eachPriceInfoList = eachPriceInfoList;
		
		int sumDeleveryPrice = 0;
		int sumOrderPrice = 0;
		int sumPrice = 0;
		int sumDcPrice = 0;
		for(EachPriceInfoVO priceInfo : eachPriceInfoList) {
			sumDeleveryPrice 	+= priceInfo.getDeleveryPrice();	// 상품별 배송비 합산
			sumOrderPrice 		+= priceInfo.getOneOrderPrice();	// 상품별 주문금액(할인 전) 합산
			sumPrice			+= priceInfo.getPrice();			// 상품원가(옵션제외) 합산
			sumDcPrice			+= (priceInfo.getMemberDcPrice() + priceInfo.getCouponPrice() + priceInfo.getReservePrice());
																	// 상품별 할인가 합산
		}
		this.setTotDeleveryPrice(sumDeleveryPrice);	// 총 배송비 세팅
		this.setTotOrderPrice(sumOrderPrice);		// 총 주문금액 세팅
		this.setTotPrice(sumPrice);					// 총 상품원가 세팅(옵션제외)
		this.setTotDcPrice(sumDcPrice);				// 총 할인가 세팅
	}
	
	
	
	public List<EachPriceInfoVO> getEachPriceInfoList() {
		return eachPriceInfoList;
	}
	public void setTotDeleveryPrice(int totDeleveryPrice) {
		this.totDeleveryPrice = totDeleveryPrice;
	}
	public int getTotDeleveryPrice() {
		return totDeleveryPrice;
	}
	public int getTotOrderPrice() {
		return totOrderPrice;
	}
	public void setTotOrderPrice(int totOrderPrice) {
		this.totOrderPrice = totOrderPrice;
	}
	public int getTotalPaymentPrice() {
		return totalPaymentPrice;
	}
	public void setTotalPaymentPrice(int totalPaymentPrice) {
		this.totalPaymentPrice = totalPaymentPrice;
	}
	public void setTotPrice(int totPrice) {
		this.totPrice = totPrice;
	}
	public int getTotPrice() {
		return totPrice;
	}
	public int getTotDcPrice() {
		return totDcPrice;
	}
	public void setTotDcPrice(int totDcPrice) {
		this.totDcPrice = totDcPrice;
	}
	
}
