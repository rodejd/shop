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

import com.wepinit.wepinit_shop.xmall.common.vo.GdListDelivery;

import java.util.List;

public class OrderDeliveryVO {
	private String ordno;			// 주문번호
	private String sellerNm;		// 판매사명
	private String sellerCd;		// 판매사코드
	private String goodsnm;			// 상품명(영문)
	private String goodsnmKR;		// 상품명(국문)
	private String goodsnmCN;		// 상품명(중문)
	private String goodsno;			// 상품코드
	private String deliveryPrice;	// 배송비
	private String addDeliveryPrice;// 추가배송비
	private String paymentTerms;	// 0:무료/1:선불/2:착불
	private String invoice;			// 송장번호
	private String deliveryStatus;	// 배송상태
	private String deliverySdt;		// 최근 배송이력 일자
	private String deliveryEdt;		// 최근 배송이력 일자
	private String deliveryCompCd;	// 택배사 코드
	private String level;			// 배송상태 레벨(6:배송완료)
	private String step; 			// 상품 주문상태
	private String step2; 			// 상품 주문상태
	private String istep; 			// 상품 주문상태
	private String opt;				// 상품옵션번호
	private String returnDeliveryCompCd;	// 반품 택배사 코드
	private String returnDeliveryCompNm;	// 반품 택배사명
	private String returnInvoice;			// 반품 송장번호
	
	private String myritzNm;		// 판매사명
	private String myritzCd;		// 판매사코드
	
	private List<GdListDelivery> deliveryCompList;

	
	
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	public String getIstep() {
		return istep;
	}
	public void setIstep(String istep) {
		this.istep = istep;
	}
	public String getSellerNm() {
		return sellerNm;
	}
	public void setSellerNm(String sellerNm) {
		this.sellerNm = sellerNm;
	}
	public String getGoodsnm() {
		return goodsnm;
	}
	public void setGoodsnm(String goodsnm) {
		this.goodsnm = goodsnm;
	}
	public String getGoodsnmKR() {
		return goodsnmKR;
	}
	public void setGoodsnmKR(String goodsnmKR) {
		this.goodsnmKR = goodsnmKR;
	}
	public String getGoodsnmCN() {
		return goodsnmCN;
	}
	public void setGoodsnmCN(String goodsnmCN) {
		this.goodsnmCN = goodsnmCN;
	}
	public String getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(String deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	public String getAddDeliveryPrice() {
		return addDeliveryPrice;
	}
	public void setAddDeliveryPrice(String addDeliveryPrice) {
		this.addDeliveryPrice = addDeliveryPrice;
	}	
	public String getPaymentTerms() {
		return paymentTerms;
	}
	public void setPaymentTerms(int paymentTerms) {
		switch (paymentTerms) {
		case 0 :
			this.paymentTerms = "무료";
			break;
		case 1 :
			this.paymentTerms = "선불";
			break;
		case 2 :
			this.paymentTerms = "착불";
			break;
		default:
			break;
		}
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public String getDeliveryEdt() {
		return deliveryEdt;
	}
	public void setDeliveryEdt(String deliveryEdt) {
		this.deliveryEdt = deliveryEdt;
	}
	public String getDeliveryCompCd() {
		return deliveryCompCd;
	}
	public void setDeliveryCompCd(String deliveryCompCd) {
		this.deliveryCompCd = deliveryCompCd;
	}
	public String getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}
	public String getOrdno() {
		return ordno;
	}
	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	public List<GdListDelivery> getDeliveryCompList() {
		return deliveryCompList;
	}
	public void setDeliveryCompList(List<GdListDelivery> deliveryCompList) {
		this.deliveryCompList = deliveryCompList;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getReturnDeliveryCompCd() {
		return returnDeliveryCompCd;
	}
	public void setReturnDeliveryCompCd(String returnDeliveryCompCd) {
		this.returnDeliveryCompCd = returnDeliveryCompCd;
	}
	public String getReturnDeliveryCompNm() {
		return returnDeliveryCompNm;
	}
	public void setReturnDeliveryCompNm(String returnDeliveryCompNm) {
		this.returnDeliveryCompNm = returnDeliveryCompNm;
	}
	public String getReturnInvoice() {
		return returnInvoice;
	}
	public void setReturnInvoice(String returnInvoice) {
		this.returnInvoice = returnInvoice;
	}
	public String getDeliverySdt() {
		return deliverySdt;
	}
	public void setDeliverySdt(String deliverySdt) {
		this.deliverySdt = deliverySdt;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getStep2() {
		return step2;
	}
	public void setStep2(String step2) {
		this.step2 = step2;
	}
	public String getMyritzNm() {
		return myritzNm;
	}
	public void setMyritzNm(String myritzNm) {
		this.myritzNm = myritzNm;
	}
	public String getMyritzCd() {
		return myritzCd;
	}
	public void setMyritzCd(String myritzCd) {
		this.myritzCd = myritzCd;
	}
	@Override
	public String toString() {
		return "OrderDeliveryVO [ordno=" + ordno + ", sellerNm=" + sellerNm
				+ ", sellerCd=" + sellerCd + ", goodsnm=" + goodsnm
				+ ", goodsno=" + goodsno + ", deliveryPrice=" + deliveryPrice
				+ ", addDeliveryPrice=" + addDeliveryPrice + ", paymentTerms="
				+ paymentTerms + ", invoice=" + invoice + ", deliveryStatus="
				+ deliveryStatus + ", deliveryEdt=" + deliveryEdt
				+ ", deliveryCompCd=" + deliveryCompCd + ", level=" + level
				+ ", istep=" + istep + ", myritzNm=" + myritzNm + ", myritzCd=" + myritzCd + ", deliveryCompList=" + deliveryCompList
				+ "]";
	}
	

}
