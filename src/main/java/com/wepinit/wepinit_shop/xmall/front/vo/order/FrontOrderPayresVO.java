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
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdOrderListBank;

import java.util.List;

public class FrontOrderPayresVO extends FrontOrderSettleVO {
	
	//  in
	private String CST_PLATFORM;  
	private String CST_MID     ;  
	private String LGD_MID     ;  
	private String LGD_PAYKEY  ;

	//  out
	private String ordno ;
	private String inis_tid;
	private String errCode;
	private String errMsg;
	
	//  status Message;
	private String resultMessage;
	
	//  orderEnd out
	private List<FrontOrderCartMapVO> cartList 		;
	private String dc				;
	private String order_info_1		;
	private String order_info_2		;
	private String receiver_info_1	;
	private String receiver_info_2	;
	private String memo 			;
	private GdOrderListBank orderListBank;
	private TotalDeliveryInfoVO totalDeliveryInfoVO;
	private String addDelivery;
	private List<CouponapplyApplymemberCouponCategoryGoodsno> usedCouponInfoList;
	
	//inicis 정보
	private String ini_resultCode ;
	private String ini_resultMsg ;
	private String ini_returnUrl;
	private String ini_authUrl;
	private String ini_charset;
	private String ini_cardnum;
	private String ini_orderNumber;
	private String ini_authToken;
	
	public String getIni_resultCode() {
		return ini_resultCode;
	}
	public void setIni_resultCode(String ini_resultCode) {
		this.ini_resultCode = ini_resultCode;
	}
	public String getIni_resultMsg() {
		return ini_resultMsg;
	}
	public void setIni_resultMsg(String ini_resultMsg) {
		this.ini_resultMsg = ini_resultMsg;
	}
	public String getIni_returnUrl() {
		return ini_returnUrl;
	}
	public void setIni_returnUrl(String ini_returnUrl) {
		this.ini_returnUrl = ini_returnUrl;
	}
	public String getIni_authUrl() {
		return ini_authUrl;
	}
	public void setIni_authUrl(String ini_authUrl) {
		this.ini_authUrl = ini_authUrl;
	}
	public String getIni_charset() {
		return ini_charset;
	}
	public void setIni_charset(String ini_charset) {
		this.ini_charset = ini_charset;
	}
	public String getIni_cardnum() {
		return ini_cardnum;
	}
	public void setIni_cardnum(String ini_cardnum) {
		this.ini_cardnum = ini_cardnum;
	}
	public String getIni_orderNumber() {
		return ini_orderNumber;
	}
	public void setIni_orderNumber(String ini_orderNumber) {
		this.ini_orderNumber = ini_orderNumber;
	}
	public String getIni_authToken() {
		return ini_authToken;
	}
	public void setIni_authToken(String ini_authToken) {
		this.ini_authToken = ini_authToken;
	}
	public String getIni_checkAckUrl() {
		return ini_checkAckUrl;
	}
	public void setIni_checkAckUrl(String ini_checkAckUrl) {
		this.ini_checkAckUrl = ini_checkAckUrl;
	}
	public String getIni_netCancelUrl() {
		return ini_netCancelUrl;
	}
	public void setIni_netCancelUrl(String ini_netCancelUrl) {
		this.ini_netCancelUrl = ini_netCancelUrl;
	}
	public String getIni_mid() {
		return ini_mid;
	}
	public void setIni_mid(String ini_mid) {
		this.ini_mid = ini_mid;
	}
	public String getIni_merchantData() {
		return ini_merchantData;
	}
	public void setIni_merchantData(String ini_merchantData) {
		this.ini_merchantData = ini_merchantData;
	}
	private String ini_checkAckUrl;
	private String ini_netCancelUrl;
	private String ini_mid;
	private String ini_merchantData;
	

	@Override
	public String getCST_PLATFORM() {
		return CST_PLATFORM;
	}
	@Override
	public void setCST_PLATFORM(String cST_PLATFORM) {
		CST_PLATFORM = cST_PLATFORM;
	}
	@Override
	public String getCST_MID() {
		return CST_MID;
	}
	@Override
	public void setCST_MID(String cST_MID) {
		CST_MID = cST_MID;
	}
	@Override
	public String getLGD_MID() {
		return LGD_MID;
	}
	@Override
	public void setLGD_MID(String lGD_MID) {
		LGD_MID = lGD_MID;
	}
	public String getLGD_PAYKEY() {
		return LGD_PAYKEY;
	}
	public void setLGD_PAYKEY(String lGD_PAYKEY) {
		LGD_PAYKEY = lGD_PAYKEY;
	}
	@Override
	public String getOrdno() {
		return ordno;
	}
	@Override
	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}
	public String getInis_tid() {
		return inis_tid;
	}
	public void setInis_tid(String inis_tid) {
		this.inis_tid = inis_tid;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public List<FrontOrderCartMapVO> getCartList() {
		return cartList;
	}
	public void setCartList(List<FrontOrderCartMapVO> cartList) {
		this.cartList = cartList;
	}
	public List<CouponapplyApplymemberCouponCategoryGoodsno> getUsedCouponInfoList() {
		return usedCouponInfoList;
	}
	public void setUsedCouponInfoList(
			List<CouponapplyApplymemberCouponCategoryGoodsno> usedCouponInfoList) {
		this.usedCouponInfoList = usedCouponInfoList;
	}
	@Override
	public String getDc() {
		return dc;
	}
	@Override
	public void setDc(String dc) {
		this.dc = dc;
	}
	public String getOrder_info_1() {
		return order_info_1;
	}
	public void setOrder_info_1(String order_info_1) {
		this.order_info_1 = order_info_1;
	}
	public String getOrder_info_2() {
		return order_info_2;
	}
	public void setOrder_info_2(String order_info_2) {
		this.order_info_2 = order_info_2;
	}
	public String getReceiver_info_1() {
		return receiver_info_1;
	}
	public void setReceiver_info_1(String receiver_info_1) {
		this.receiver_info_1 = receiver_info_1;
	}
	public String getReceiver_info_2() {
		return receiver_info_2;
	}
	public void setReceiver_info_2(String receiver_info_2) {
		this.receiver_info_2 = receiver_info_2;
	}
	@Override
	public String getMemo() {
		return memo;
	}
	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public GdOrderListBank getOrderListBank() {
		return orderListBank;
	}
	public void setOrderListBank(GdOrderListBank orderListBank) {
		this.orderListBank = orderListBank;
	}
	public void setTotalDeliveryInfoVO(TotalDeliveryInfoVO totalDeliveryInfoVO) {
		this.totalDeliveryInfoVO = totalDeliveryInfoVO;
	}
	public TotalDeliveryInfoVO getTotalDeliveryInfoVO() {
		return totalDeliveryInfoVO;
	}
	@Override
	public void setAddDelivery(String addDelivery) {
		this.addDelivery = addDelivery;
	}
	@Override
	public String getAddDelivery() {
		return addDelivery;
	}
	@Override
	public String toString() {
		return "FrontOrderPayresVO [CST_PLATFORM=" + CST_PLATFORM + ", CST_MID=" + CST_MID + ", LGD_MID=" + LGD_MID
				+ ", LGD_PAYKEY=" + LGD_PAYKEY + ", ordno=" + ordno + ", inis_tid=" + inis_tid + ", errCode=" + errCode
				+ ", errMsg=" + errMsg + ", resultMessage=" + resultMessage + ", cartList=" + cartList + ", dc=" + dc
				+ ", order_info_1=" + order_info_1 + ", order_info_2=" + order_info_2 + ", receiver_info_1="
				+ receiver_info_1 + ", receiver_info_2=" + receiver_info_2 + ", memo=" + memo + ", orderListBank="
				+ orderListBank + ", totalDeliveryInfoVO=" + totalDeliveryInfoVO + ", addDelivery=" + addDelivery
				+ ", usedCouponInfoList=" + usedCouponInfoList + ", ini_resultCode=" + ini_resultCode
				+ ", ini_resultMsg=" + ini_resultMsg + ", ini_returnUrl=" + ini_returnUrl + ", ini_authUrl="
				+ ini_authUrl + ", ini_charset=" + ini_charset + ", ini_cardnum=" + ini_cardnum + ", ini_orderNumber="
				+ ini_orderNumber + ", ini_authToken=" + ini_authToken + ", ini_checkAckUrl=" + ini_checkAckUrl
				+ ", ini_netCancelUrl=" + ini_netCancelUrl + ", ini_mid=" + ini_mid + ", ini_merchantData="
				+ ini_merchantData + "]";
	}

	
}
