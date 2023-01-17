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

import com.wepinit.wepinit_shop.xmall.common.vo.GdListDelivery;

import java.util.List;

public class SellerDeliveryPolicyInfoVO {
	
	private String sellerCd;			//판매사 코드
	
	/** Input & Output */
	// 관련 DB : gd_seller_delivery_policy 
	private String defaultPolicyNm;		//기본 배송정책
	private String defaultPolicyFreePrice;	//기본 배송정책 무료적용 기준 금액
	private String defaultPolicyPayment;//기본 배송정책 선/후불 여부
	private String defaultPolicyPrice;		//기본 배송정책 배송비(선불시 노출)
	private String defaultPolicyMsg;	//기본 배송정책 메세지(후불시 노출)
	private String freeByGoods;			//상품별 배송정책 무료배송 상품 적용 기준 (0, 1)
	private String deliveryPriceByGoods;//상품별 배송정책 상품별 배송비 적용 기준 (0, 1)
	private String useDeliveryComp;		//판매사별 이용 택배사 코드
	
	// 관련 DB : gd_seller_delivery_over_policy
	private List<SellerDeliveryOverPolicyVO> deliveryOverPoicyList;
	/** Only Output */

	// 관련 DB : gd_list_delivery - 전체 택배사 목록
	private List<GdListDelivery> deliveryCompanyList;
	
	// 관련 DB : gd_list_delivery - 판매사가 사용중인 택배사 목록
	private List<GdListDelivery> sellerUseDeliveryCompanyList;
	
	/** Only Input */
	private int[] addDeliveryPrices;	//지역별 배송비 금액들
	private String[] addDeliveryCities;	//지역별 배송비 지역들
	
	
	public String getSellerCd() {
		return sellerCd;
	}

	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}

	public String getDefaultPolicyNm() {
		return defaultPolicyNm;
	}

	public void setDefaultPolicyNm(String defaultPolicyNm) {
		this.defaultPolicyNm = defaultPolicyNm;
	}

	public String getDefaultPolicyPayment() {
		return defaultPolicyPayment;
	}

	public void setDefaultPolicyPayment(String defaultPolicyPayment) {
		this.defaultPolicyPayment = defaultPolicyPayment;
	}

	public String getDefaultPolicyMsg() {
		return defaultPolicyMsg;
	}

	public void setDefaultPolicyMsg(String defaultPolicyMsg) {
		this.defaultPolicyMsg = defaultPolicyMsg;
	}
	
	public String getFreeByGoods() {
		return freeByGoods;
	}

	public void setFreeByGoods(String freeByGoods) {
		this.freeByGoods = freeByGoods;
	}

	public String getDeliveryPriceByGoods() {
		return deliveryPriceByGoods;
	}

	public void setDeliveryPriceByGoods(String deliveryPriceByGoods) {
		this.deliveryPriceByGoods = deliveryPriceByGoods;
	}

	public String getUseDeliveryComp() {
		return this.useDeliveryComp == null ? "" : this.useDeliveryComp;
	}

	public void setUseDeliveryComp(String useDeliveryComp) {
		this.useDeliveryComp = useDeliveryComp;
	}

	public List<SellerDeliveryOverPolicyVO> getDeliveryOverPoicyList() {
		return deliveryOverPoicyList;
	}

	public void setDeliveryOverPoicyList(
			List<SellerDeliveryOverPolicyVO> deliveryOverPoiyList) {
		this.deliveryOverPoicyList = deliveryOverPoiyList;
	}
	
	public List<GdListDelivery> getDeliveryCompanyList() {
		return deliveryCompanyList;
	}

	public void setDeliveryCompanyList(List<GdListDelivery> deliveryCompanyList) {
		this.deliveryCompanyList = deliveryCompanyList;
	}
	
	public List<GdListDelivery> getSellerUseDeliveryCompanyList() {
		return sellerUseDeliveryCompanyList;
	}

	public void setSellerUseDeliveryCompanyList(
			List<GdListDelivery> sellerUseDeliveryCompanyList) {
		this.sellerUseDeliveryCompanyList = sellerUseDeliveryCompanyList;
	}

	public int[] getAddDeliveryPrices() {
		return addDeliveryPrices == null ? new int[0] : this.addDeliveryPrices;
	}

	public void setAddDeliveryPrices(int[] addDeliveryPrices) {
		this.addDeliveryPrices = addDeliveryPrices;
	}

	public String[] getAddDeliveryCities() {
		return addDeliveryCities;
	}

	public void setAddDeliveryCities(String[] addDeliveryCities) {
		this.addDeliveryCities = addDeliveryCities;
	}

	public String getDefaultPolicyFreePrice() {
		return defaultPolicyFreePrice;
	}

	public void setDefaultPolicyFreePrice(String defaultPolicyFreePrice) {
		this.defaultPolicyFreePrice = defaultPolicyFreePrice;
	}

	public String getDefaultPolicyPrice() {
		return defaultPolicyPrice;
	}

	public void setDefaultPolicyPrice(String defaultPolicyPrice) {
		this.defaultPolicyPrice = defaultPolicyPrice;
	}

	@Override
	public String toString() {
		return "SellerDeliveryPolicyInfoVO [sellerCd=" + sellerCd
				+ ", defaultPolicyNm=" + defaultPolicyNm
				+ ", defaultPolicyFreePrice=" + defaultPolicyFreePrice
				+ ", defaultPolicyPayment=" + defaultPolicyPayment
				+ ", defaultPolicyPrice=" + defaultPolicyPrice
				+ ", defaultPolicyMsg=" + defaultPolicyMsg + ", freeByGoods="
				+ freeByGoods + ", deliveryPriceByGoods="
				+ deliveryPriceByGoods + ", useDeliveryComp=" + useDeliveryComp
				+ ", deliveryOverPoicyList=" + deliveryOverPoicyList
				+ ", deliveryCompanyList=" + deliveryCompanyList
				+ ", sellerUseDeliveryCompanyList="
				+ sellerUseDeliveryCompanyList + ", addDeliveryPrices="
				+ addDeliveryPrices + ", addDeliveryCities="
				+ addDeliveryCities + "]";
	}
}
