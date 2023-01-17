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
/********************************************************************************
* <pre>
* 업무구분명			:	Community
* 세부업무구분명	: 	사용자 > Community
* 작성자 				: 	이병환
* 설명 				: 	사용자 Community VO
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170208		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.vo.order;

import com.wepinit.wepinit_shop.xmall.common.CommonVO;


public class FrontOrderSettleVO extends CommonVO{
	private	String	dbChk = "";
	private	String	ordno = "";
	private	String	goodsnm = "";
	private String goodsnmKR = "";			
	private String goodsnmCN = "";
	private	String	item_apply_coupon = "";
	private	String	nameOrder = "";
	private	String	nameOrder1 = "";
	private	String	nameOrder2 = "";
	private	String	phoneOrder = "";
	private	String	mobileOrder = "";
	private	String	email = "";
	private	String	email1 = "";
	private	String	email2 = "";
	private	String	nameReceiver = "";
	private	String	nameReceiver1 = "";
	private	String	nameReceiver2 = "";
	private	String	phoneReceiver = "";
	private	String	mobileReceiverType = "";
	private	String	mobileReceiver = "";
	private	String	mobileReceiver1 = "";
	private	String	mobileReceiver2 = "";
	private	String	mobileReceiver3 = "";
	private	String	country = "";
	private	String	zipcode = "";
	private	String	address = "";
	private	String	address2 = "";
	private	String	city = "";
	private	String	state = "";
	private	String	customs_num = "";
	private	String	customsNum = "";
	private	String	memo = "";
	private	String	deliPoli = "";
	private	String	emoney_max = "";
	private	String	escrow = "";
	private	String	deliprice = "";
	private	String	coupon = "";
	private	String	coupon_emoney = "";
	private String  coupon_sno = "";
	private	String	addemoney = "";
	private	String	emoney = "";
	private	String[] apply_coupon;
	private	String	deliverycode = "";
	private	String	emailReceiver = "";
	private	String	emailReceiver1 = "";
	private	String	emailReceiver2 = "";
	private	String	dcprice = "";
	private	String	dc = "";
	private	String	priceSum = "";
	private	String	settlekind = "";
	private	String	emailRecceiver = "";
	private	String	settleprice = "";
	private	String	reserveSum = "";
	private	String	totalPrice = "";
	private	String	version = "";
	private	String	mid = "";
	private	String	goodname = "";
	private	String	oid = "";
	private	String	price = "";
	private	String	currency = "";
	private	String	buyername = "";
	private	String	buyertel = "";
	private	String	buyeremail = "";
	private	String	timestamp = "";
	private	String	signature = "";
	private	String	returnUrl = "";
	private	String	mKey = "";
	private	String	gopaymethod = "";
	private	String	offerPeriod = "";
	private	String	acceptmethod = "";
	private	String	languageView = "";
	private	String	charset = "";
	private	String	payViewType = "";
	private	String	closeUrl = "";
	private	String	popupUrl = "";
	private	String	quotabase = "";
	private	String	ini_cardcode = "";
	private	String	ansim_quota = "";
	private	String	vbankRegNo = "";
	private	String	merchantData = "";
	private	String	P_GOODS = "";
	private	String	P_AMT = "";
	private	String	P_UNAME = "";
	private	String	P_MNAME = "";
	private	String	P_MOBILE = "";
	private	String	P_EMAIL = "";
	private	String	paymethod = "";
	private	String	P_MID = "";
	private	String	P_NEXT_URL = "";
	private	String	P_OID = "";
	private	String	P_NOTI_URL = "";
	private	String	P_RETURN_URL = "";
	private	String	P_RESERVED = "";
	private	String	P_CHARSET = "";
	private	String	P_HPP_METHOD = "";
	//jsp page 이외의 데이터
	private String remoteAddr = "";
	private String cashbag = "";
	private String timestemp = "";
	private String bankAccount = "";
	private String bankSender = "";
	private String remoteAdd = "";
	private String refere = "";
	private String m_no = "";
	private String orderMember = "";
	private String level = "";
	private String dcPrice = "";
	private String goodsno = "";
	private String opt1 = "";
	private String opt2 = "";
	
	private String prn_settleprice = "";
	private String goodsprice = "";
	private String deli_title = "";
	private String delivery = "";
	private String deli_type = "";
	private String deli_msg = "";
	private String memberdc = "";
	private String reserve = "";
	private String eggFee = "";
//	private String bankAccount = ""; 
	private String ip = "";
	private String referer = "";
//	private String memo = "";
//	private String coupon_emoney = "";
	private String cashbagcard = "";
//	private String timestamp = "";
	private String sno = "";
	private String goods_cou_no = "";
	private String couponnm = "";
	private String ea = "";
	private String stockyn = "";
	private String supply = "";
	private String brandnm = "";
	private String brandnmCN = "";
	private String brandnmKR = "";
	private String tax = "";
	private String addopt = "";
	private String agent = "";
	private String step = "";
	private String step2 = "";
	private String istep = "";
	private String cyn = "";
	
	private String applysno =  ""; //쿠폰 적용일련번호
	private String couponcd =  ""; //쿠폰 코드
	private String couponDcCode =  ""; //할인코드 
	private String dcEmoneyAmount =  ""; //적립금 사용금액
	private String dcCouponAmount =  ""; //쿠폰 사용금액
	private String dcCodeAmount = ""; // 할인코드 사용금액
	private String dcCouponTotal = ""; // 쿠폰사용금액 + 할인코드 사용금액
	
	
	private int amount = 0;
	private String[] goodsnoArr = null;
	private String[] optSnoArr = null;
	private int[] priceArr;
	
	
	
	
	//에러 메시지
	private String errorMsg;
	
	//dacom
	private String CST_PLATFORM = "";
	private String LGD_BUYER = "";
	private String LGD_BUYERIP = "";
	private String LGD_BUYERID = "";
	private String LGD_PRODUCTINFO = "";
	private String LGD_AMOUNT = "";
	private String LGD_BUYEREMAIL = "";
	private String LGD_OID = "";
	private String CST_MID = "";
	private String 	LGD_MID = "";
	private String LGD_CUSTOM_SKIN = "";
	private String LGD_WINDOW_VER = "";
	private String LGD_CUSTOM_PROCESSTYPE = "";
	private String LGD_TIMESTAMP = "";
	private String LGD_HASHDATA = "";
	private String LGD_CASNOTEURL = "";

	private String addDelivery = "";
	private String sellerAddDelivery = "";

	private String inicisTid = "";
	
	private int deliveryPrice;
	private int paymentTerms;
	private int addDeliveryPrice;

	private String talkGoodsNm = "";
	private String talkOptNm = "";
	
	
	public String getIstep() {
		return istep;
	}
	public void setIstep(String istep) {
		this.istep = istep;
	}
	public String getCyn() {
		return cyn;
	}
	public void setCyn(String cyn) {
		this.cyn = cyn;
	}
	public String getInicisTid() {
		return inicisTid;
	}
	public void setInicisTid(String inicisTid) {
		this.inicisTid = inicisTid;
	}
	public int getAddDeliveryPrice() {
		return addDeliveryPrice;
	}
	public void setAddDeliveryPrice(int addDeliveryPrice) {
		this.addDeliveryPrice = addDeliveryPrice;
	}
	public String getSellerAddDelivery() {
		return sellerAddDelivery;
	}
	public void setSellerAddDelivery(String sellerAddDelivery) {
		this.sellerAddDelivery = sellerAddDelivery;
	}
	public String getCoupon_sno() {
		return coupon_sno;
	}
	public void setCoupon_sno(String coupon_sno) {
		this.coupon_sno = coupon_sno;
	}
	public String getCST_PLATFORM() {
		return CST_PLATFORM;
	}
	public void setCST_PLATFORM(String cST_PLATFORM) {
		CST_PLATFORM = cST_PLATFORM;
	}
	public String getLGD_BUYER() {
		return LGD_BUYER;
	}
	public void setLGD_BUYER(String lGD_BUYER) {
		LGD_BUYER = lGD_BUYER;
	}
	public String getLGD_BUYERIP() {
		return LGD_BUYERIP;
	}
	public void setLGD_BUYERIP(String lGD_BUYERIP) {
		LGD_BUYERIP = lGD_BUYERIP;
	}
	public String getLGD_BUYERID() {
		return LGD_BUYERID;
	}
	public void setLGD_BUYERID(String lGD_BUYERID) {
		LGD_BUYERID = lGD_BUYERID;
	}
	public String getLGD_PRODUCTINFO() {
		return LGD_PRODUCTINFO;
	}
	public void setLGD_PRODUCTINFO(String lGD_PRODUCTINFO) {
		LGD_PRODUCTINFO = lGD_PRODUCTINFO;
	}
	public String getLGD_AMOUNT() {
		return LGD_AMOUNT;
	}
	public void setLGD_AMOUNT(String lGD_AMOUNT) {
		LGD_AMOUNT = lGD_AMOUNT;
	}
	public String getLGD_BUYEREMAIL() {
		return LGD_BUYEREMAIL;
	}
	public void setLGD_BUYEREMAIL(String lGD_BUYEREMAIL) {
		LGD_BUYEREMAIL = lGD_BUYEREMAIL;
	}
	public String getLGD_OID() {
		return LGD_OID;
	}
	public void setLGD_OID(String lGD_OID) {
		LGD_OID = lGD_OID;
	}
	public String getCST_MID() {
		return CST_MID;
	}
	public void setCST_MID(String cST_MID) {
		CST_MID = cST_MID;
	}
	public String getLGD_MID() {
		return LGD_MID;
	}
	public void setLGD_MID(String lGD_MID) {
		LGD_MID = lGD_MID;
	}
	public String getLGD_CUSTOM_SKIN() {
		return LGD_CUSTOM_SKIN;
	}
	public void setLGD_CUSTOM_SKIN(String lGD_CUSTOM_SKIN) {
		LGD_CUSTOM_SKIN = lGD_CUSTOM_SKIN;
	}
	public String getLGD_WINDOW_VER() {
		return LGD_WINDOW_VER;
	}
	public void setLGD_WINDOW_VER(String lGD_WINDOW_VER) {
		LGD_WINDOW_VER = lGD_WINDOW_VER;
	}
	public String getLGD_CUSTOM_PROCESSTYPE() {
		return LGD_CUSTOM_PROCESSTYPE;
	}
	public void setLGD_CUSTOM_PROCESSTYPE(String lGD_CUSTOM_PROCESSTYPE) {
		LGD_CUSTOM_PROCESSTYPE = lGD_CUSTOM_PROCESSTYPE;
	}
	public String getLGD_TIMESTAMP() {
		return LGD_TIMESTAMP;
	}
	public void setLGD_TIMESTAMP(String lGD_TIMESTAMP) {
		LGD_TIMESTAMP = lGD_TIMESTAMP;
	}
	public String getLGD_HASHDATA() {
		return LGD_HASHDATA;
	}
	public void setLGD_HASHDATA(String lGD_HASHDATA) {
		LGD_HASHDATA = lGD_HASHDATA;
	}
	public String getLGD_CASNOTEURL() {
		return LGD_CASNOTEURL;
	}
	public void setLGD_CASNOTEURL(String lGD_CASNOTEURL) {
		LGD_CASNOTEURL = lGD_CASNOTEURL;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	public String getCashbag() {
		return cashbag;
	}
	public void setCashbag(String cashbag) {
		this.cashbag = cashbag;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getGoods_cou_no() {
		return goods_cou_no;
	}
	public void setGoods_cou_no(String goods_cou_no) {
		this.goods_cou_no = goods_cou_no;
	}
	public String getCouponnm() {
		return couponnm;
	}
	public void setCouponnm(String couponnm) {
		this.couponnm = couponnm;
	}
	public String getEa() {
		return ea;
	}
	public void setEa(String ea) {
		this.ea = ea;
	}
	public String getStockyn() {
		return stockyn;
	}
	public void setStockyn(String stockyn) {
		this.stockyn = stockyn;
	}
	public String getSupply() {
		return supply;
	}
	public void setSupply(String supply) {
		this.supply = supply;
	}
	public String getBrandnm() {
		return brandnm;
	}
	public void setBrandnm(String brandnm) {
		this.brandnm = brandnm;
	}
	public String getBrandnmCN() {
		return brandnmCN;
	}
	public void setBrandnmCN(String brandnmCN) {
		this.brandnmCN = brandnmCN;
	}
	public String getBrandnmKR() {
		return brandnmKR;
	}
	public void setBrandnmKR(String brandnmKR) {
		this.brandnmKR = brandnmKR;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getAddopt() {
		return addopt;
	}
	public void setAddopt(String addopt) {
		this.addopt = addopt;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankSender() {
		return bankSender;
	}
	public void setBankSender(String bankSender) {
		this.bankSender = bankSender;
	}
	public String getRemoteAdd() {
		return remoteAdd;
	}
	public void setRemoteAdd(String remoteAdd) {
		this.remoteAdd = remoteAdd;
	}
	public String getRefere() {
		return refere;
	}
	public void setRefere(String refere) {
		this.refere = refere;
	}
	public String getM_no() {
		return m_no;
	}
	public void setM_no(String m_no) {
		this.m_no = m_no;
	}
	public String getOrderMember() {
		return orderMember;
	}
	public void setOrderMember(String orderMember) {
		this.orderMember = orderMember;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getDcPrice() {
		return dcPrice;
	}
	public void setDcPrice(String dcPrice) {
		this.dcPrice = dcPrice;
	}
	public String getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
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
	public String getPrn_settleprice() {
		return prn_settleprice;
	}
	public void setPrn_settleprice(String prn_settleprice) {
		this.prn_settleprice = prn_settleprice;
	}
	public String getGoodsprice() {
		return goodsprice;
	}
	public void setGoodsprice(String goodsprice) {
		this.goodsprice = goodsprice;
	}
	public String getDeli_title() {
		return deli_title;
	}
	public void setDeli_title(String deli_title) {
		this.deli_title = deli_title;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public String getDeli_type() {
		return deli_type;
	}
	public void setDeli_type(String deli_type) {
		this.deli_type = deli_type;
	}
	public String getDeli_msg() {
		return deli_msg;
	}
	public void setDeli_msg(String deli_msg) {
		this.deli_msg = deli_msg;
	}
	public String getMemberdc() {
		return memberdc;
	}
	public void setMemberdc(String memberdc) {
		this.memberdc = memberdc;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	public String getEggFee() {
		return eggFee;
	}
	public void setEggFee(String eggFee) {
		this.eggFee = eggFee;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public String getCashbagcard() {
		return cashbagcard;
	}
	public void setCashbagcard(String cashbagcard) {
		this.cashbagcard = cashbagcard;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getTimestemp() {
		return timestemp;
	}
	public void setTimestemp(String timestemp) {
		this.timestemp = timestemp;
	}
	public String getDbChk() {
		return dbChk;
	}
	public void setDbChk(String dbChk) {
		this.dbChk = dbChk;
	}
	public String getOrdno() {
		return ordno;
	}
	public void setOrdno(String ordno) {
		this.ordno = ordno;
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
	public String getItem_apply_coupon() {
		return item_apply_coupon;
	}
	public void setItem_apply_coupon(String item_apply_coupon) {
		this.item_apply_coupon = item_apply_coupon;
	}
	public String getNameOrder() {
		return nameOrder;
	}
	public void setNameOrder(String nameOrder) {
		this.nameOrder = nameOrder;
	}
	public String getPhoneOrder() {
		return phoneOrder;
	}
	public void setPhoneOrder(String phoneOrder) {
		this.phoneOrder = phoneOrder;
	}
	public String getMobileOrder() {
		return mobileOrder;
	}
	public void setMobileOrder(String mobileOrder) {
		this.mobileOrder = mobileOrder;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getNameReceiver() {
		return nameReceiver;
	}
	public void setNameReceiver(String nameReceiver) {
		this.nameReceiver = nameReceiver;
	}
	public String getPhoneReceiver() {
		return phoneReceiver;
	}
	public void setPhoneReceiver(String phoneReceiver) {
		this.phoneReceiver = phoneReceiver;
	}
	public String getMobileReceiver() {
		return mobileReceiver;
	}
	public String getMobileReceiverType() {
		return mobileReceiverType;
	}
	public void setMobileReceiverType(String mobileReceiverType) {
		this.mobileReceiverType = mobileReceiverType;
	}
	public void setMobileReceiver(String mobileReceiver) {
		this.mobileReceiver = mobileReceiver;
	}
	public String getMobileReceiver1() {
		return mobileReceiver1;
	}
	public void setMobileReceiver1(String mobileReceiver1) {
		this.mobileReceiver1 = mobileReceiver1;
	}
	public String getMobileReceiver2() {
		return mobileReceiver2;
	}
	public void setMobileReceiver2(String mobileReceiver2) {
		this.mobileReceiver2 = mobileReceiver2;
	}
	public String getMobileReceiver3() {
		return mobileReceiver3;
	}
	public void setMobileReceiver3(String mobileReceiver3) {
		this.mobileReceiver3 = mobileReceiver3;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCustoms_num() {
		return customs_num;
	}
	public void setCustoms_num(String customs_num) {
		this.customs_num = customs_num;
	}
	public String getCustomsNum() {
		return customsNum;
	}
	public void setCustomsNum(String customsNum) {
		this.customsNum = customsNum;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getDeliPoli() {
		return deliPoli;
	}
	public void setDeliPoli(String deliPoli) {
		this.deliPoli = deliPoli;
	}
	public String getAddemoney() {
		return addemoney;
	}
	public void setAddemoney(String addemoney) {
		this.addemoney = addemoney;
	}
	public String getEmoney_max() {
		return emoney_max;
	}
	public void setEmoney_max(String emoney_max) {
		this.emoney_max = emoney_max;
	}
	public String getEscrow() {
		return escrow;
	}
	public void setEscrow(String escrow) {
		this.escrow = escrow;
	}
	public String getDeliprice() {
		return deliprice;
	}
	public void setDeliprice(String deliprice) {
		this.deliprice = deliprice;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public String getCoupon_emoney() {
		return coupon_emoney;
	}
	public void setCoupon_emoney(String coupon_emoney) {
		this.coupon_emoney = coupon_emoney;
	}
	public String getEmoney() {
		return emoney;
	}
	public void setEmoney(String emoney) {
		this.emoney = emoney;
	}
	public String[] getApply_coupon() {
		return apply_coupon;
	}
	public void setApply_coupon(String[] apply_coupon) {
		this.apply_coupon = apply_coupon;
	}
	public String getDeliverycode() {
		return deliverycode;
	}
	public void setDeliverycode(String deliverycode) {
		this.deliverycode = deliverycode;
	}
	public String getDcprice() {
		return dcprice;
	}
	public void setDcprice(String dcprice) {
		this.dcprice = dcprice;
	}
	public String getDc() {
		return dc;
	}
	public void setDc(String dc) {
		this.dc = dc;
	}
	public String getPriceSum() {
		return priceSum;
	}
	public void setPriceSum(String priceSum) {
		this.priceSum = priceSum;
	}
	public String getSettlekind() {
		return settlekind;
	}
	public void setSettlekind(String settlekind) {
		this.settlekind = settlekind;
	}
	public String getEmailRecceiver() {
		return emailRecceiver;
	}
	public void setEmailRecceiver(String emailRecceiver) {
		this.emailRecceiver = emailRecceiver;
	}
	public String getSettleprice() {
		return settleprice;
	}
	public void setSettleprice(String settleprice) {
		this.settleprice = settleprice;
	}
	public String getReserveSum() {
		return reserveSum;
	}
	public void setReserveSum(String reserveSum) {
		this.reserveSum = reserveSum;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getGoodname() {
		return goodname;
	}
	public void setGoodname(String goodname) {
		this.goodname = goodname;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getBuyername() {
		return buyername;
	}
	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}
	public String getBuyertel() {
		return buyertel;
	}
	public void setBuyertel(String buyertel) {
		this.buyertel = buyertel;
	}
	public String getBuyeremail() {
		return buyeremail;
	}
	public void setBuyeremail(String buyeremail) {
		this.buyeremail = buyeremail;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getmKey() {
		return mKey;
	}
	public void setmKey(String mKey) {
		this.mKey = mKey;
	}
	public String getGopaymethod() {
		return gopaymethod;
	}
	public void setGopaymethod(String gopaymethod) {
		this.gopaymethod = gopaymethod;
	}
	public String getOfferPeriod() {
		return offerPeriod;
	}
	public void setOfferPeriod(String offerPeriod) {
		this.offerPeriod = offerPeriod;
	}
	public String getAcceptmethod() {
		return acceptmethod;
	}
	public void setAcceptmethod(String acceptmethod) {
		this.acceptmethod = acceptmethod;
	}
	public String getLanguageView() {
		return languageView;
	}
	public void setLanguageView(String languageView) {
		this.languageView = languageView;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getPayViewType() {
		return payViewType;
	}
	public void setPayViewType(String payViewType) {
		this.payViewType = payViewType;
	}
	public String getCloseUrl() {
		return closeUrl;
	}
	public void setCloseUrl(String closeUrl) {
		this.closeUrl = closeUrl;
	}
	public String getPopupUrl() {
		return popupUrl;
	}
	public void setPopupUrl(String popupUrl) {
		this.popupUrl = popupUrl;
	}
	public String getQuotabase() {
		return quotabase;
	}
	public void setQuotabase(String quotabase) {
		this.quotabase = quotabase;
	}
	public String getIni_cardcode() {
		return ini_cardcode;
	}
	public void setIni_cardcode(String ini_cardcode) {
		this.ini_cardcode = ini_cardcode;
	}
	public String getAnsim_quota() {
		return ansim_quota;
	}
	public void setAnsim_quota(String ansim_quota) {
		this.ansim_quota = ansim_quota;
	}
	public String getVbankRegNo() {
		return vbankRegNo;
	}
	public void setVbankRegNo(String vbankRegNo) {
		this.vbankRegNo = vbankRegNo;
	}
	public String getMerchantData() {
		return merchantData;
	}
	public void setMerchantData(String merchantData) {
		this.merchantData = merchantData;
	}
	public String getP_GOODS() {
		return P_GOODS;
	}
	public void setP_GOODS(String p_GOODS) {
		P_GOODS = p_GOODS;
	}
	public String getP_AMT() {
		return P_AMT;
	}
	public void setP_AMT(String p_AMT) {
		P_AMT = p_AMT;
	}
	public String getP_UNAME() {
		return P_UNAME;
	}
	public void setP_UNAME(String p_UNAME) {
		P_UNAME = p_UNAME;
	}
	public String getP_MNAME() {
		return P_MNAME;
	}
	public void setP_MNAME(String p_MNAME) {
		P_MNAME = p_MNAME;
	}
	public String getP_MOBILE() {
		return P_MOBILE;
	}
	public void setP_MOBILE(String p_MOBILE) {
		P_MOBILE = p_MOBILE;
	}
	public String getP_EMAIL() {
		return P_EMAIL;
	}
	public void setP_EMAIL(String p_EMAIL) {
		P_EMAIL = p_EMAIL;
	}
	public String getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}
	public String getP_MID() {
		return P_MID;
	}
	public void setP_MID(String p_MID) {
		P_MID = p_MID;
	}
	public String getP_NEXT_URL() {
		return P_NEXT_URL;
	}
	public void setP_NEXT_URL(String p_NEXT_URL) {
		P_NEXT_URL = p_NEXT_URL;
	}
	public String getP_OID() {
		return P_OID;
	}
	public void setP_OID(String p_OID) {
		P_OID = p_OID;
	}
	public String getP_NOTI_URL() {
		return P_NOTI_URL;
	}
	public void setP_NOTI_URL(String p_NOTI_URL) {
		P_NOTI_URL = p_NOTI_URL;
	}
	public String getP_RETURN_URL() {
		return P_RETURN_URL;
	}
	public void setP_RETURN_URL(String p_RETURN_URL) {
		P_RETURN_URL = p_RETURN_URL;
	}
	public String getP_RESERVED() {
		return P_RESERVED;
	}
	public void setP_RESERVED(String p_RESERVED) {
		P_RESERVED = p_RESERVED;
	}
	public String getP_CHARSET() {
		return P_CHARSET;
	}
	public void setP_CHARSET(String p_CHARSET) {
		P_CHARSET = p_CHARSET;
	}
	public String getP_HPP_METHOD() {
		return P_HPP_METHOD;
	}
	public void setP_HPP_METHOD(String p_HPP_METHOD) {
		P_HPP_METHOD = p_HPP_METHOD;
	}
	public String getAddDelivery() {
		return addDelivery;
	}
	public void setAddDelivery(String addDelivery) {
		this.addDelivery = addDelivery;
	}
	public int getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(int deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	public int getPaymentTerms() {
		return paymentTerms;
	}
	public void setPaymentTerms(int paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getNameOrder1() {
		return nameOrder1;
	}
	public void setNameOrder1(String nameOrder1) {
		this.nameOrder1 = nameOrder1;
	}
	public String getNameOrder2() {
		return nameOrder2;
	}
	public void setNameOrder2(String nameOrder2) {
		this.nameOrder2 = nameOrder2;
	}
	public String getNameReceiver1() {
		return nameReceiver1;
	}
	public void setNameReceiver1(String nameReceiver1) {
		this.nameReceiver1 = nameReceiver1;
	}
	public String getNameReceiver2() {
		return nameReceiver2;
	}
	public void setNameReceiver2(String nameReceiver2) {
		this.nameReceiver2 = nameReceiver2;
	}
	public String getEmailReceiver() {
		return emailReceiver;
	}
	public void setEmailReceiver(String emailReceiver) {
		this.emailReceiver = emailReceiver;
	}
	public String getEmailReceiver1() {
		return emailReceiver1;
	}
	public void setEmailReceiver1(String emailReceiver1) {
		this.emailReceiver1 = emailReceiver1;
	}
	public String getEmailReceiver2() {
		return emailReceiver2;
	}
	public void setEmailReceiver2(String emailReceiver2) {
		this.emailReceiver2 = emailReceiver2;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String[] getGoodsnoArr() {
		return goodsnoArr;
	}
	public void setGoodsnoArr(String[] goodsnoArr) {
		this.goodsnoArr = goodsnoArr;
	}
	public int[] getPriceArr() {
		return priceArr;
	}
	public void setPriceArr(int[] priceArr) {
		this.priceArr = priceArr;
	}
	public String[] getOptSnoArr() {
		return optSnoArr;
	}
	public void setOptSnoArr(String[] optSnoArr) {
		this.optSnoArr = optSnoArr;
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
	public String getDcCouponTotal() {
		return dcCouponTotal;
	}
	public void setDcCouponTotal(String dcCouponTotal) {
		this.dcCouponTotal = dcCouponTotal;
	}
	public String getApplysno() {
		return applysno;
	}
	public void setApplysno(String applysno) {
		this.applysno = applysno;
	}
	public String getCouponcd() {
		return couponcd;
	}
	public void setCouponcd(String couponcd) {
		this.couponcd = couponcd;
	}
	public String getCouponDcCode() {
		return couponDcCode;
	}
	public void setCouponDcCode(String couponDcCode) {
		this.couponDcCode = couponDcCode;
	}
	public String getDcEmoneyAmount() {
		return dcEmoneyAmount;
	}
	public void setDcEmoneyAmount(String dcEmoneyAmount) {
		this.dcEmoneyAmount = dcEmoneyAmount;
	}
	public String getDcCouponAmount() {
		return dcCouponAmount;
	}
	public void setDcCouponAmount(String dcCouponAmount) {
		this.dcCouponAmount = dcCouponAmount;
	}
	public String getDcCodeAmount() {
		return dcCodeAmount;
	}
	public void setDcCodeAmount(String dcCodeAmount) {
		this.dcCodeAmount = dcCodeAmount;
	}
	public String getTalkGoodsNm() {
		return talkGoodsNm;
	}
	public void setTalkGoodsNm(String talkGoodsNm) {
		this.talkGoodsNm = talkGoodsNm;
	}
	public String getTalkOptNm() {
		return talkOptNm;
	}
	public void setTalkOptNm(String talkOptNm) {
		this.talkOptNm = talkOptNm;
	}
}
