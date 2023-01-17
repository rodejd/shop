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

import java.util.List;

public class FrontGoodsCartsVO {
	// in
	private String mode = "list";
	private String optionsList;
	private String goodsType;
	private String category = "";
	private String sno;			// 상품보관함 checkbox sno 정보 여기로 들어오는지?
	private String idx;
	private String arrIdx;
	private String paramEa;
	private String guestSel;
	private String referer;
	private String goodsno; 	// 상품보관함에 들어있는 모든 상품의 goodsno
	private String opt;			// 상품보관함에 들어있는 모든 상품의 opt
	private String addopt;		// 상품보관함에 들어있는 모든 상품의 addopt;
	private String deliveryNo;	// 상품상세페이지의 기본배송정책리스트 중 선택된 정보 (0 = 기본배송정책리스트 첫번째, 1 이후 = gd_delivery_policy 리스트, null = 상품별 배송정책일 시)
	private String optionSno;	// 상품보관함 리스트의 구분자 옵션추가로인해 추가함 	gd_goods_cart의 sno
	
	// out
	private String alertMessage;
	private String redirectPage;
	private List<FrontGoodsCartsInnerVO> inVOList;
	private String deliveryDefault;
	private String deliveryFreeDelivery;
	private String deliberyGoodsDelibery;
	private int totalPrice;		// 총금액
	
	// param
	private String opt1;
	private String opt2;
	
	public String getOptionSno() {
		return optionSno;
	}
	public void setOptionSno(String optionSno) {
		this.optionSno = optionSno;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public void setOptionsList(String optionsList) {
		this.optionsList = optionsList;
	}
	public String getOptionsList() {
		return optionsList;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public String getAddopt() {
		return addopt;
	}
	public void setAddopt(String addopt) {
		this.addopt = addopt;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getAlertMessage() {
		return alertMessage;
	}
	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}
	public void setRedirectPage(String redirectPage) {
		this.redirectPage = redirectPage;
	}
	public String getRedirectPage() {
		return redirectPage;
	}
	public void setInVOList(List<FrontGoodsCartsInnerVO> inVOList) {
		this.inVOList = inVOList;
	}
	public List<FrontGoodsCartsInnerVO> getInVOList() {
		return inVOList;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getIdx() {
		return idx;
	}
	public void setArrIdx(String arrIdx) {
		this.arrIdx = arrIdx;
	}
	public String getArrIdx() {
		return arrIdx;
	}
	
	public String getParamEa() {
		return paramEa;
	}
	public void setParamEa(String paramEa) {
		this.paramEa = paramEa;
	}
	public void setGuestSel(String guestSel) {
		this.guestSel = guestSel;
	}
	public String getGuestSel() {
		return guestSel;
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
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public String getReferer() {
		return referer;
	}
	public String getDeliveryDefault() {
		return deliveryDefault;
	}
	public void setDeliveryDefault(String deliveryDefault) {
		this.deliveryDefault = deliveryDefault;
	}
	public String getDeliveryFreeDelivery() {
		return deliveryFreeDelivery;
	}
	public void setDeliveryFreeDelivery(String deliveryFreeDelivery) {
		this.deliveryFreeDelivery = deliveryFreeDelivery;
	}
	public String getDeliberyGoodsDelibery() {
		return deliberyGoodsDelibery;
	}
	public void setDeliberyGoodsDelibery(String deliberyGoodsDelibery) {
		this.deliberyGoodsDelibery = deliberyGoodsDelibery;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}
	public String getDeliveryNo() {
		return deliveryNo;
	}
	@Override
	public String toString() {
		return "FrontGoodsCartsVO [mode=" + mode + ", optionsList="
				+ optionsList + ", goodsType=" + goodsType + ", category="
				+ category + ", sno=" + sno + ", idx=" + idx + ", arrIdx="
				+ arrIdx + ", paramEa=" + paramEa + ", guestSel=" + guestSel
				+ ", referer=" + referer + ", goodsno=" + goodsno + ", opt="
				+ opt + ", addopt=" + addopt + ", deliveryNo=" + deliveryNo
				+ ", optionSno=" + optionSno + ", alertMessage=" + alertMessage
				+ ", redirectPage=" + redirectPage + ", inVOList=" + inVOList
				+ ", deliveryDefault=" + deliveryDefault
				+ ", deliveryFreeDelivery=" + deliveryFreeDelivery
				+ ", deliberyGoodsDelibery=" + deliberyGoodsDelibery
				+ ", totalPrice=" + totalPrice + ", opt1=" + opt1 + ", opt2="
				+ opt2 + "]";
	}
	
	
	
}	
