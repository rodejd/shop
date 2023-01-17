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

import com.wepinit.wepinit_shop.xmall.common.vo.join.CouponapplyApplymemberCouponCategoryGoodsno;

import java.util.List;

// 원래 cartMap 에 저장중이던 정보를 vo로 바꾸어 클래스명을 FrontOrderCartMapVO 로 만듦
public class FrontOrderCartMapVO {
	private String goodsnm;			
	private String goodsnmKR;			
	private String goodsnmCN;			
	private String img;				
	private String consumer;			
	private String dcPrice;			
	private String price;			
	private String reserve;			
	private String delivery_type;	
	private String goods_delivery;	
	private String addPriceSum;		
	private String use_emoney;		
	private String goodsno;			
	private String addopt;			
	private String ea;				
	private String goods_category;	
	private String opt1;			
	private String opt2;			
	private String usestock;		
	private String priceSum;		
	private String delivery;		
	private String deliveryNo;		
	private List<CouponapplyApplymemberCouponCategoryGoodsno> couponInfoList;
	private String category;
	private String sellerCd;
	private String optionSum;
	private String optName;
	private int brandno = 0;					//브랜드번호
	
	private String cpCouponcd; // 쿠폰번호			
	private String cpCouponprice;	// 쿠폰금액
	private String cpMaxprice;	// 최대 할인액
	private String cpCouponskin;	// 쿠폰적용 스킨
	private String cpOrgPrice;	// 할인전 금액
	private String cpOrgPriceSum;	// 할인전 합계금액

	
	public String getCpOrgPriceSum() {
		return cpOrgPriceSum;
	}
	public void setCpOrgPriceSum(String cpOrgPriceSum) {
		this.cpOrgPriceSum = cpOrgPriceSum;
	}
	public String getCpOrgPrice() {
		return cpOrgPrice;
	}
	public void setCpOrgPrice(String cpOrgPrice) {
		this.cpOrgPrice = cpOrgPrice;
	}
	public String getCpCouponcd() {
		return cpCouponcd;
	}
	public void setCpCouponcd(String cpCouponcd) {
		this.cpCouponcd = cpCouponcd;
	}
	public String getCpCouponprice() {
		return cpCouponprice;
	}
	public void setCpCouponprice(String cpCouponprice) {
		this.cpCouponprice = cpCouponprice;
	}
	public String getCpMaxprice() {
		return cpMaxprice;
	}
	public void setCpMaxprice(String cpMaxprice) {
		this.cpMaxprice = cpMaxprice;
	}
	public String getCpCouponskin() {
		return cpCouponskin;
	}
	public void setCpCouponskin(String cpCouponskin) {
		this.cpCouponskin = cpCouponskin;
	}
	public String getOptName() {
		return optName;
	}
	public void setOptName(String optName) {
		this.optName = optName;
	}
	public String getOptionSum() {
		return optionSum;
	}
	public void setOptionSum(String optionSum) {
		this.optionSum = optionSum;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getConsumer() {
		return consumer;
	}
	public void setConsumer(String consumer) {
		this.consumer = consumer;
	}
	public String getDcPrice() {
		return dcPrice;
	}
	public void setDcPrice(String dcPrice) {
		this.dcPrice = dcPrice;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	public String getDelivery_type() {
		return delivery_type;
	}
	public void setDelivery_type(String delivery_type) {
		this.delivery_type = delivery_type;
	}
	public String getGoods_delivery() {
		return goods_delivery;
	}
	public void setGoods_delivery(String goods_delivery) {
		this.goods_delivery = goods_delivery;
	}
	public String getAddPriceSum() {
		return addPriceSum;
	}
	public void setAddPriceSum(String addPriceSum) {
		this.addPriceSum = addPriceSum;
	}
	public String getUse_emoney() {
		return use_emoney;
	}
	public void setUse_emoney(String use_emoney) {
		this.use_emoney = use_emoney;
	}
	public String getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}
	public String getAddopt() {
		return addopt;
	}
	public void setAddopt(String addopt) {
		this.addopt = addopt;
	}
	public String getEa() {
		return ea;
	}
	public void setEa(String ea) {
		this.ea = ea;
	}
	public String getGoods_category() {
		return goods_category;
	}
	public void setGoods_category(String goods_category) {
		this.goods_category = goods_category;
	}
	public String getOpt1() {
		return opt1;
	}
	public void setOpt1(String opt1) {
		this.opt1 = opt1;
	}
	public String getOpt2() {
		return opt2;
	}
	public void setOpt2(String opt2) {
		this.opt2 = opt2;
	}
	public String getUsestock() {
		return usestock;
	}
	public void setUsestock(String usestock) {
		this.usestock = usestock;
	}
	public String getPriceSum() {
		return priceSum;
	}
	public void setPriceSum(String priceSum) {
		this.priceSum = priceSum;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public String getDeliveryNo() {
		return deliveryNo;
	}
	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}
	public void setCouponInfoList(
			List<CouponapplyApplymemberCouponCategoryGoodsno> couponInfoList) {
		this.couponInfoList = couponInfoList;
	}
	public List<CouponapplyApplymemberCouponCategoryGoodsno> getCouponInfoList() {
		return couponInfoList;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	public int getBrandno() {
		return brandno;
	}
	public void setBrandno(int brandno) {
		this.brandno = brandno;
	}
	@Override
	public String toString() {
		return "FrontOrderCartMapVO [goodsnm=" + goodsnm + ", img=" + img
				+ ", price=" + price + ", reserve=" + reserve
				+ ", delivery_type=" + delivery_type + ", goods_delivery="
				+ goods_delivery + ", addPriceSum=" + addPriceSum
				+ ", use_emoney=" + use_emoney + ", goodsno=" + goodsno
				+ ", addopt=" + addopt + ", ea=" + ea + ", goods_category="
				+ goods_category + ", opt1=" + opt1 + ", opt2=" + opt2
				+ ", usestock=" + usestock + ", priceSum=" + priceSum
				+ ", delivery=" + delivery + ", deliveryNo=" + deliveryNo
				+ ", cpCouponcd=" + cpCouponcd + ", cpCouponprice=" + cpCouponprice
				+ ", cpMaxprice=" + cpMaxprice + ", cpCouponskin=" + cpCouponskin
				+ ", cpOrgPrice=" + cpOrgPrice + ", cpOrgPriceSum=" + cpOrgPriceSum
				+ ", couponInfoList=" + couponInfoList + ", category="
				+ category + ", sellerCd=" + sellerCd + "]";
	}
}
