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

import com.wepinit.wepinit_shop.xmall.common.vo.GdDeliveryPolicy;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsOption;
import org.springframework.util.StringUtils;

import java.util.List;

public class FrontGoodsCartsInnerVO {
	private String goodsnm;
	private String goodsnmKR;
	private String goodsnmCN;
	private String brandnm;
	private String img;
	private String consumer;				// 원가
	private String price;					// 원가
	private String priceRate;				// 원가
	private String reserve;
	private String useEmoney;
	private String goodsno;
	private String vipYn;
	private String addopt;
	private String ea;
	private String opt1;
	private String opt2;
	private String goodsCategory;
	private String priceSum;				// 원가 + 옵션가의 총액
	private String deliveryType;			// 상품별 배송비 설정값(0=기본배송정책사용, 1=무료배송, 2=상품별배송비, 3=착불배송비)
	private String goodsDelivery;			// 상품별 배송비 설정인 경우 세팅(배송비)
	private GdDeliveryPolicy deliveryPolicy;// deliveryType 이 0=기본배송정책사용 인 경우 세팅
	private String deliveryPolicyNo;
	private int sno;	// 상품보관함 리스트의 구분자 옵션추가로인해 추가함 	gd_goods_cart의 sno
	private String optno;
	private String optNm;
	private String opttype;
	private List<GdGoodsOption> optionList;
	private List<GdGoodsOption> option2List;
	
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
	public String getBrandnm() {
		return brandnm;
	}
	public void setBrandnm(String brandnm) {
		this.brandnm = brandnm;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = StringUtils.hasText(img) ? img.substring(img.indexOf("|") + 1) : "";
	}
	public String getConsumer() {
		return consumer;
	}
	public void setConsumer(String consumer) {
		this.consumer = consumer;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPriceRate() {
		return priceRate;
	}
	public void setPriceRate(String priceRate) {
		this.priceRate = priceRate;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public String getGoodsDelivery() {
		return goodsDelivery;
	}
	public void setGoodsDelivery(String goodsDelivery) {
		this.goodsDelivery = goodsDelivery;
	}
	public String getUseEmoney() {
		return useEmoney;
	}
	public void setUseEmoney(String useEmoney) {
		this.useEmoney = useEmoney;
	}
	public String getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}
	public String getVipYn() {
		return vipYn;
	}
	public void setVipYn(String vipYn) {
		this.vipYn = vipYn;
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
	public String getGoodsCategory() {
		return goodsCategory;
	}
	public void setGoodsCategory(String goodsCategory) {
		this.goodsCategory = goodsCategory;
	}
	public void setPriceSum(String priceSum) {
		this.priceSum = priceSum;
	}
	public String getPriceSum() {
		return priceSum;
	}
	public GdDeliveryPolicy getDeliveryPolicy() {
		return deliveryPolicy;
	}
	public void setDeliveryPolicy(GdDeliveryPolicy deliveryPolicy) {
		this.deliveryPolicy = deliveryPolicy;
	}
	public void setDeliveryPolicyNo(String deliveryPolicyNo) {
		this.deliveryPolicyNo = deliveryPolicyNo;
	}
	public String getDeliveryPolicyNo() {
		return deliveryPolicyNo;
	}

	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getOptno() {
		return optno;
	}
	public void setOptno(String optno) {
		this.optno = optno;
	}
	public String getOptNm() {
		return optNm;
	}
	public void setOptNm(String optNm) {
		this.optNm = optNm;
	}
	public String getOpttype() {
		return opttype;
	}
	public void setOpttype(String opttype) {
		this.opttype = opttype;
	}	
	public List<GdGoodsOption> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<GdGoodsOption> optionList) {
		this.optionList = optionList;
	}
	public List<GdGoodsOption> getOption2List() {
		return option2List;
	}
	public void setOption2List(List<GdGoodsOption> option2List) {
		this.option2List = option2List;
	}
	
	@Override
	public String toString() {
		return "FrontGoodsCartsInnerVO [goodsnm=" + goodsnm + ", img=" + img
				+ ", price=" + price + ", reserve=" + reserve
				+ ", deliveryType=" + deliveryType + ", goodsDelivery="
				+ goodsDelivery + ", useEmoney=" + useEmoney + ", goodsno="
				+ goodsno + ", addopt=" + addopt + ", ea=" + ea + ", opt1="
				+ opt1 + ", opt2=" + opt2 + ", goodsCategory=" + goodsCategory
				+ "]";
	}
}

