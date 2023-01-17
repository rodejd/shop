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

import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.PopupOrder1;
import com.wepinit.wepinit_shop.xmall.common.vo.join.PopupOrder2;
import com.wepinit.wepinit_shop.xmall.common.vo.join.PopupOrder3;
import com.wepinit.wepinit_shop.xmall.common.vo.join.PopupOrder4;

import java.util.Arrays;
import java.util.List;

public class PopupOrderVO {
	private String popup = "popup";
	private List<PopupOrder1> rtList;
	private int rtListSize;
	private String tmp;
	private String tmpRt;
	private String tmpRt1;
	private int tmpRt6;
	private List<GdListBank> tmpBank;
	private int tmpBankSize;
	private List<GdListDelivery> tmpDeli;
	private List<GdListDelivery> deliveryCompList;
	private List<PopupOrder3> tmpRt8;
	private int tmpRt8Size;
	private List<PopupOrder4> tmpRt9;
	private int tmpRt9Size;
	private List<GdOrderItem> tmpRt10;
	private int tmpRt10Size;
	private PopupOrder2 rtData;
	private List<GdTax> rtTax;
	private int rtTaxSize;
	private String dcMemberDc;
	private String dcCoupon;
	private String dcEmoney;
	private String dcEnuri;
	private int iCnt;
	private int goodsPrice;
	private int iCancel;
	private int memberDc;
	private int coupon;
	private int cntDv;
	private int discount;
	private int settleprice;
	
	private String referer;
	private long ordno;
	
	private String deliveryBasis;
	
	private String wskin;
	private String selectOption;
	private String stepMsg;
	private String[] newordno;
	private String deliverySelectOption;
	private List<GdOrderCancel> tmpRt11;
	
	private int cancel;
	private int cnt;
	private String formTarget;
	
	private String memberdc;
	
	private String sweetTrackerAPIKey;
	
	// 2017-09-06 : 옵션 합산가 추가
	private int totalOptionPrice = 0;
	
	private List<OrderDeliveryVO> deliveryList;
	private OrderDeliveryVO deliveryInfo;
	
	public List<PopupOrder1> getRtList() {
		return rtList;
	}
	public void setRtList(List<PopupOrder1> rtList) {
		this.rtList = rtList;
		this.rtListSize = rtList.size();
	}
	public String getTmp() {
		return tmp;
	}
	public void setTmp(String tmp) {
		this.tmp = tmp;
	}
	public String getTmpRt() {
		return tmpRt;
	}
	public void setTmpRt(String tmpRt) {
		this.tmpRt = tmpRt;
	}
	public String getTmpRt1() {
		return tmpRt1;
	}
	public void setTmpRt1(String tmpRt1) {
		this.tmpRt1 = tmpRt1;
	}
	public void setTmpBank(List<GdListBank> tmpBank) {
		this.tmpBank = tmpBank;
		this.tmpBankSize = tmpBank.size();
	}
	public List<GdListBank> getTmpBank() {
		return tmpBank;
	}
	public List<GdListDelivery> getTmpDeli() {
		return tmpDeli;
	}
	public void setTmpDeli(List<GdListDelivery> tmpDeli) {
		this.tmpDeli = tmpDeli;
	}
	public PopupOrder2 getRtData() {
		return rtData;
	}
	public void setRtData(PopupOrder2 rtData) {
		this.rtData = rtData;
	}
	public void setRtTax(List<GdTax> rtTax) {
		this.rtTax = rtTax;
		this.rtTaxSize = rtTax.size();
	}
	public List<GdTax> getRtTax() {
		return rtTax;
	}
	public String getDcMemberDc() {
		return dcMemberDc;
	}
	public void setDcMemberDc(String dcMemberDc) {
		this.dcMemberDc = dcMemberDc;
	}
	public String getDcCoupon() {
		return dcCoupon;
	}
	public void setDcCoupon(String dcCoupon) {
		this.dcCoupon = dcCoupon;
	}
	public String getDcEmoney() {
		return dcEmoney;
	}
	public void setDcEmoney(String dcEmoney) {
		this.dcEmoney = dcEmoney;
	}
	public String getDcEnuri() {
		return dcEnuri;
	}
	public void setDcEnuri(String dcEnuri) {
		this.dcEnuri = dcEnuri;
	}
	public int getiCnt() {
		return iCnt;
	}
	public void setiCnt(int iCnt) {
		this.iCnt = iCnt;
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public int getiCancel() {
		return iCancel;
	}
	public void setiCancel(int iCancel) {
		this.iCancel = iCancel;
	}
	public int getMemberDc() {
		return memberDc;
	}
	public void setMemberDc(int memberDc) {
		this.memberDc = memberDc;
	}
	public int getCoupon() {
		return coupon;
	}
	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}
	public int getCntDv() {
		return cntDv;
	}
	public void setCntDv(int cntDv) {
		this.cntDv = cntDv;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getSettleprice() {
		return settleprice;
	}
	public void setSettleprice(int settleprice) {
		this.settleprice = settleprice;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public void setOrdno(long ordno) {
		this.ordno = ordno;
	}
	public long getOrdno() {
		return ordno;
	}
	public String getDeliveryBasis() {
		return deliveryBasis;
	}
	public void setDeliveryBasis(String deliveryBasis) {
		this.deliveryBasis = deliveryBasis;
	}
	public String getWskin() {
		return wskin;
	}
	public void setWskin(String wskin) {
		this.wskin = wskin;
	}
	public String getSelectOption() {
		return selectOption;
	}
	public void setSelectOption(String selectOption) {
		this.selectOption = selectOption;
	}
	public String getStepMsg() {
		return stepMsg;
	}
	public void setStepMsg(String stepMsg) {
		this.stepMsg = stepMsg;
	}
	public int getTmpRt6() {
		return tmpRt6;
	}
	public void setTmpRt6(int i) {
		this.tmpRt6 = i;
	}
	public String[] getNewordno() {
		return newordno;
	}
	public void setNewordno(String[] newordno) {
		this.newordno = newordno;
	}
	public List<PopupOrder3> getTmpRt8() {
		return tmpRt8;
	}
	public void setTmpRt8(List<PopupOrder3> tmpRt8) {
		this.tmpRt8 = tmpRt8;
		this.tmpRt8Size = tmpRt8.size();
	}
	public List<PopupOrder4> getTmpRt9() {
		return tmpRt9;
	}
	public void setTmpRt9(List<PopupOrder4> tmpRt9) {
		this.tmpRt9 = tmpRt9;
		this.tmpRt9Size = tmpRt9.size();
	}
	public void setTmpRt10(List<GdOrderItem> tmpRt10) {
		this.tmpRt10 = tmpRt10;
		this.tmpRt10Size = tmpRt10.size();
	}
	public List<GdOrderItem> getTmpRt10() {
		return tmpRt10;
	}
	public void setDeliverySelectOption(String deliverySelectOption) {
		this.deliverySelectOption = deliverySelectOption;
	}
	public String getDeliverySelectOption() {
		return deliverySelectOption;
	}
	public void setTmpRt11(List<GdOrderCancel> tmpRt11) {
		this.tmpRt11 = tmpRt11;
	}
	public List<GdOrderCancel> getTmpRt11() {
		return tmpRt11;
	}
	public int getCancel() {
		return cancel;
	}
	public void setCancel(int cancel) {
		this.cancel = cancel;
	}
	public int getRtListSize() {
		return rtListSize;
	}
	public int getTmpRt10Size() {
		return tmpRt10Size;
	}
	public int getTmpBankSize() {
		return tmpBankSize;
	}
	public int getRtTaxSize() {
		return rtTaxSize;
	}
	public String getPopup() {
		return popup;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getCnt() {
		return cnt;
	}
	public int getTmpRt8Size() {
		return tmpRt8Size;
	}
	public int getTmpRt9Size() {
		return tmpRt9Size;
	}
	public String getFormTarget() {
		return formTarget;
	}
	public void setFormTarget(String formTarget) {
		this.formTarget = formTarget;
	}
	public void setMemberdc(String memberdc) {
		this.memberdc = memberdc;
	}
	public String getMemberdc() {
		return memberdc;
	}
	public int getTotalOptionPrice() {
		return totalOptionPrice;
	}
	public void setTotalOptionPrice(int totalOptionPrice) {
		this.totalOptionPrice = totalOptionPrice;
	}
	public void setSweetTrackerAPIKey(String sweetTrackerAPIKey) {
		this.sweetTrackerAPIKey = sweetTrackerAPIKey;
	}
	public String getSweetTrackerAPIKey() {
		return sweetTrackerAPIKey;
	}
	public List<OrderDeliveryVO> getDeliveryList() {
		return deliveryList;
	}
	public void setDeliveryList(List<OrderDeliveryVO> deliveryList) {
		this.deliveryList = deliveryList;
	}
	public OrderDeliveryVO getDeliveryInfo() {
		return deliveryInfo;
	}
	public void setDeliveryInfo(OrderDeliveryVO deliveryInfo) {
		this.deliveryInfo = deliveryInfo;
	}
	public List<GdListDelivery> getDeliveryCompList() {
		return deliveryCompList;
	}
	public void setDeliveryCompList(List<GdListDelivery> deliveryCompList) {
		this.deliveryCompList = deliveryCompList;
	}
	@Override
	public String toString() {
		return "PopupOrderVO [popup=" + popup + ", rtList=" + rtList
				+ ", rtListSize=" + rtListSize + ", tmp=" + tmp + ", tmpRt="
				+ tmpRt + ", tmpRt1=" + tmpRt1 + ", tmpRt6=" + tmpRt6
				+ ", tmpBank=" + tmpBank + ", tmpBankSize=" + tmpBankSize
				+ ", tmpDeli=" + tmpDeli + ", tmpRt8=" + tmpRt8
				+ ", tmpRt8Size=" + tmpRt8Size + ", tmpRt9=" + tmpRt9
				+ ", tmpRt9Size=" + tmpRt9Size + ", tmpRt10=" + tmpRt10
				+ ", tmpRt10Size=" + tmpRt10Size + ", rtData=" + rtData
				+ ", rtTax=" + rtTax + ", rtTaxSize=" + rtTaxSize
				+ ", dcMemberDc=" + dcMemberDc + ", dcCoupon=" + dcCoupon
				+ ", dcEmoney=" + dcEmoney + ", dcEnuri=" + dcEnuri + ", iCnt="
				+ iCnt + ", goodsPrice=" + goodsPrice + ", iCancel=" + iCancel
				+ ", memberDc=" + memberDc + ", coupon=" + coupon + ", cntDv="
				+ cntDv + ", discount=" + discount + ", settleprice="
				+ settleprice + ", referer=" + referer + ", ordno=" + ordno
				+ ", deliveryBasis=" + deliveryBasis
				+ ", wskin=" + wskin + ", selectOption=" + selectOption
				+ ", stepMsg=" + stepMsg + ", newordno="
				+ Arrays.toString(newordno) + ", deliverySelectOption="
				+ deliverySelectOption + ", tmpRt11=" + tmpRt11 + ", cancel="
				+ cancel + ", cnt=" + cnt + ", formTarget=" + formTarget
				+ ", memberdc=" + memberdc + ", sweetTrackerAPIKey="
				+ sweetTrackerAPIKey + ", totalOptionPrice=" + totalOptionPrice
				+ ", deliveryList=" + deliveryList + "]";
	}
}
