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

import com.wepinit.wepinit_shop.xmall.common.vo.GdListBank;
import com.wepinit.wepinit_shop.xmall.common.vo.GdListDelivery;
import com.wepinit.wepinit_shop.xmall.common.vo.GdOrderCancel;
import com.wepinit.wepinit_shop.xmall.common.vo.GdTax;
import com.wepinit.wepinit_shop.xmall.common.vo.join.PopupOrder1;
import com.wepinit.wepinit_shop.xmall.common.vo.join.PopupOrder2;
import com.wepinit.wepinit_shop.xmall.common.vo.join.PopupOrder4;
import com.wepinit.wepinit_shop.xmall.common.vo.join.PopupOrdersRecovery1;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OrderPrintVO {
	// in
	private String listType;
	private String type;
	private String[] regdt;
	private String step;
	private String ordnos;
	private String settlekind;
	private Long ordno;
	private String referer;
	private String sellerCd;
	
	// out report 주문내역서
	private String[] paperOrders;
	private int cnt;
	private PopupOrder2 rtData;
	private int goodsPrice;
	private int memberDC;
	private int coupon;
	private int cntDV;
	private int discount;
	private int settleprice;
	private String deliveryBasis;
	private String stepSelectOption;
	private String deliSelectOption;
	private List<PopupOrder4> tmpRt;
	private int tmpRtSize;
	private List<GdOrderCancel> orderCancelList;
	private int orderCancelListSize;
	private Map<String, String> codeMap;
	private List<OrderDeliveryVO> delivery;
	
	//use report 주문내역서
	private List<GdListDelivery> tmpDeli;
	private List<GdListBank> tmpBank;
	private int tmpBankSize;
	private List<PopupOrder1> rtList;
	private int rtListSize;
	private List<GdTax> rtTax;
	private int rtTaxSize;
	private int iCnt;
	
	// reception 간이영수증
	private int[] price;
	private int itemPrice;
	private String[] classids;
	private String[] headuser;
	private PopupOrdersRecovery1 orderItem0;
	private List<PopupOrdersRecovery1> orderItemList;
	private String compSerial;
	private String compName;
	private String ceoName;
	private String address;
	private String service;
	private String item;
	
	private String myritzCd;
	
	public String getMyritzCd() {
		return myritzCd;
	}
	public void setMyritzCd(String myritzCd) {
		this.myritzCd = myritzCd;
	}
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String[] getRegdt() {
		return regdt;
	}
	public void setRegdt(String[] regdt) {
		this.regdt = regdt;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getOrdnos() {
		return ordnos;
	}
	public void setOrdnos(String ordnos) {
		this.ordnos = ordnos;
	}
	public String getSettlekind() {
		return settlekind;
	}
	public void setSettlekind(String settlekind) {
		this.settlekind = settlekind;
	}
	public void setPaperOrders(String[] paperOrders) {
		this.paperOrders = paperOrders;
	}
	public String[] getPaperOrders() {
		return paperOrders;
	}
	public void setOrdno(Long ordno) {
		this.ordno = ordno;
	}
	public Long getOrdno() {
		return ordno;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public List<GdListDelivery> getTmpDeli() {
		return tmpDeli;
	}
	public void setTmpDeli(List<GdListDelivery> tmpDeli) {
		this.tmpDeli = tmpDeli;
	}
	public List<GdListBank> getTmpBank() {
		return tmpBank;
	}
	public void setTmpBank(List<GdListBank> tmpBank) {
		this.tmpBank = tmpBank;
		this.tmpBankSize = tmpBank.size();
	}
	public List<PopupOrder1> getRtList() {
		return rtList;
	}
	public void setRtList(List<PopupOrder1> rtList) {
		this.rtList = rtList;
		this.rtListSize = rtList.size();
	}
	public PopupOrder2 getRtData() {
		return rtData;
	}
	public void setRtData(PopupOrder2 rtData) {
		this.rtData = rtData;
	}
	public int getiCnt() {
		return iCnt;
	}
	public void setiCnt(int iCnt) {
		this.iCnt = iCnt;
	}
	public List<GdTax> getRtTax() {
		return rtTax;
	}
	public void setRtTax(List<GdTax> rtTax) {
		this.rtTax = rtTax;
		this.rtTaxSize = rtTax.size();
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public int getMemberDC() {
		return memberDC;
	}
	public void setMemberDC(int memberDC) {
		this.memberDC = memberDC;
	}
	public int getCoupon() {
		return coupon;
	}
	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}
	public int getCntDV() {
		return cntDV;
	}
	public void setCntDV(int cntDV) {
		this.cntDV = cntDV;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public void setSettleprice(int settleprice) {
		this.settleprice = settleprice;
	}
	public int getSettleprice() {
		return settleprice;
	}
	public void setDeliveryBasis(String deliveryBasis) {
		this.deliveryBasis = deliveryBasis;
	}
	public String getDeliveryBasis() {
		return deliveryBasis;
	}
	public String getStepSelectOption() {
		return stepSelectOption;
	}
	public void setStepSelectOption(String stepSelectOption) {
		this.stepSelectOption = stepSelectOption;
	}
	public void setTmpRt(List<PopupOrder4> tmpRt) {
		this.tmpRt 		= tmpRt;
		this.tmpRtSize 	= tmpRt.size();
	}
	public List<PopupOrder4> getTmpRt() {
		return tmpRt;
	}
	public int getTmpRtSize() {
		return tmpRtSize;
	}
	public int getTmpBankSize() {
		return tmpBankSize;
	}
	public int getRtTaxSize() {
		return rtTaxSize;
	}
	public void setDeliSelectOption(String deliSelectOption) {
		this.deliSelectOption = deliSelectOption;
	}
	public String getDeliSelectOption() {
		return deliSelectOption;
	}
	public void setOrderCancelList(List<GdOrderCancel> orderCancelList) {
		this.orderCancelList = orderCancelList;
		this.orderCancelListSize = orderCancelList.size();
	}
	public List<GdOrderCancel> getOrderCancelList() {
		return orderCancelList;
	}
	public int getOrderCancelListSize() {
		return orderCancelListSize;
	}
	public Map<String, String> getCodeMap() {
		return codeMap;
	}
	public void setCodeMap(Map<String, String> codeMap) {
		this.codeMap = codeMap;
	}
	public int getRtListSize() {
		return rtListSize;
	}
	public int[] getPrice() {
		return price;
	}
	public void setPrice(int[] price) {
		this.price = price;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	public void setClassids(String[] classids) {
		this.classids = classids;
	}
	public String[] getClassids() {
		return classids;
	}
	public void setHeaduser(String[] headuser) {
		this.headuser = headuser;
	}
	public String[] getHeaduser() {
		return headuser;
	}
	public void setOrderItemList(List<PopupOrdersRecovery1> orderItemList) {
		this.orderItemList = orderItemList;
		this.orderItem0 = new PopupOrdersRecovery1();
		if(orderItemList.size() > 0) {
			this.orderItem0 = orderItemList.get(0);
		}
	}
	public PopupOrdersRecovery1 getOrderItem0() {
		return orderItem0;
	}
	public List<PopupOrdersRecovery1> getOrderItemList() {
		return orderItemList;
	}
	public void setCompSerial(String compSerial) {
		this.compSerial = compSerial;
	}
	public String getCompSerial() {
		return compSerial;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getCeoName() {
		return ceoName;
	}
	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public List<OrderDeliveryVO> getDelivery() {
		return delivery;
	}
	public void setDelivery(List<OrderDeliveryVO> delivery) {
		this.delivery = delivery;
	}	
	@Override
	public String toString() {
		return "OrderPrintVO [listType=" + listType + ", type=" + type
				+ ", regdt=" + Arrays.toString(regdt) + ", step=" + step
				+ ", ordnos=" + ordnos + ", settlekind=" + settlekind
				+ ", ordno=" + ordno + ", referer=" + referer
				+ ", paperOrders=" + Arrays.toString(paperOrders) + ", cnt="
				+ cnt + ", rtData=" + rtData
				+ ", goodsPrice=" + goodsPrice + ", memberDC=" + memberDC
				+ ", coupon=" + coupon + ", cntDV=" + cntDV + ", discount="
				+ discount + ", settleprice=" + settleprice
				+ ", deliveryBasis=" + deliveryBasis + ", stepSelectOption="
				+ stepSelectOption + ", deliSelectOption=" + deliSelectOption
				+ ", tmpRt=" + tmpRt + ", tmpRtSize=" + tmpRtSize
				+ ", orderCancelList=" + orderCancelList
				+ ", orderCancelListSize=" + orderCancelListSize + ", codeMap="
				+ codeMap + ", tmpDeli=" + tmpDeli + ", tmpBank=" + tmpBank
				+ ", tmpBankSize=" + tmpBankSize + ", rtList=" + rtList
				+ ", rtListSize=" + rtListSize + ", rtTax=" + rtTax
				+ ", rtTaxSize=" + rtTaxSize + ", iCnt=" + iCnt + ", price="
				+ Arrays.toString(price) + ", itemPrice=" + itemPrice
				+ ", classids=" + Arrays.toString(classids) + ", headuser="
				+ Arrays.toString(headuser) + ", orderItem0=" + orderItem0
				+ ", orderItemList=" + orderItemList + ", compSerial="
				+ compSerial + ", compName=" + compName + ", ceoName="
				+ ceoName + ", address=" + address + ", service=" + service
				+ ", item=" + item + "]";
	}
	
}
