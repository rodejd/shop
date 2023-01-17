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

import com.inicis.std.util.SignatureUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.common.CommonVO;
import com.wepinit.wepinit_shop.xmall.common.session.ShopSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.GdDeliveryPolicy;
import com.wepinit.wepinit_shop.xmall.common.vo.join.CouponapplyApplymemberCouponCategoryGoodsno;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class FrontOrderVO extends CommonVO {
	
	private String mode;
	private String goodsno;
	private String goodsCoupon;
	private String optionsList;
	private String goodsType;
	private String m_no;
	private String opt1 = "";
	private String opt2 = "";
	private String goods_category;
	private String guest;
	private String mode2;
	private String zipcode;
	private String address;
	private String address2;
	private String address_sub;
	private String emoney_base;
	private String emoney;
	private String price;
	private String ordno;
	private String goodsnm;
	private String goodsnmKR;
	private String goodsnmCN;
	private String name;
	private String name1;
	private String name2;
	private String phone;
	private String[] mobile;
	private String email;
	private String email_1;
	private String email_2;
	
	private TotalDeliveryInfoVO totalDeliveryInfoVO;			//	배송비 총합
	private  List<GdDeliveryPolicy> rtDelivery;
	private String priceSum;
	private String dcprice;
	private String emoney_hold;
	private String emoney_min;
	private String emoney_max;
	
	private String[] use;
	private String e_max;
	private String delivery_goodsDelivery;
	private String delivery_shippingFee;
	
	private String orderitem_mode;
	private List<FrontOrderCartMapVO> cartList;
	private String dc;
	private String grp_sno;
	private String couponPrice;
	private List<CouponapplyApplymemberCouponCategoryGoodsno> usedCouponInfoList;
	
	//동적 쿼리
	private String strWhere;
	
	private String applysno;
	private String priceSum2;
	private String reserveSum;
	
	private String range;
	private String coupon_use;
	private List<Map<String, String>> listCou;  //사용가능 쿠폰 리스트
	
	//error Message
	private String errorMsg;
	
	//deliveryPolicy ajax
	private int deliPoli;
	private int coupon;
	private String level;
	
	private int delSum;
	private String deli_msg;
	private String deliType;
	private String freeDeli;
	private String unableMenu;
	
	//settle
	private String coupon_emoney;
	private String nameOrder;
	private String[] phoneOrder;
	private String[] mobileOrder;
	private String nameReceiver;
	private String[] phoneReceiver;
	private String[] apply_coupon;
	private String memo;
	private String escrow;
	private String settlekind;
	private String delimsg;
	private String paper_settlement;
	private String deliprice;
	private String totalDeliprice;
	private String dcPrice;
	private String e1;
	private String e2;
	private String emailRecceiver;
	private String zip;
	private String add;
	private String add_sub;
	private String item_apply_coupon;

	private String addreserve;
	private String tmp_bank;
	private String settleprice;
	private String total_price;
	
	private String apply_coupon_0;
	private String mobileReceiver_0;
	private String phoneReceiver_0;
	private String phoneOrder_0;
	private String mobileOrder_0;
	
	private int settle_dcprice;
	private String settle_dc;
	private int settle_priceSum;
	private List<Map<String, String>> settle_cartList;
	private String mid;
	private String oid;
	private int settle_emoney;
	private int settle_coupon;
	private int settle_deliprice;
	private int settle_price;
	private String siteDomain;
	private String signKey;
	private String timestamp;
	private Map<String, String> signParam;
	private String signature;
	private String cardQuotaBase;
	private String mKey;
	private String customsnum = "";
	private String diszipcode = "";
	private String disaddress = "";
	private String disaddresssub = "";
	
	private String submitStr;

	private String deliveryNo;
	private String totalDelivery;
	
	private String addopt;
	private String couponSno;
	// 추가배송비정보
	Map<String, String> deliveryOverInfo;
	
	private String addDelivery = "0";
	private String sellerAddDelivery;
	private String ukey = "";
	
	// 추가변수
	private int amount = 0;
	private String nameOrder1 = "";
	private String nameOrder2 = "";
	private String email1 = "";
	private String email2 = "";
	private String mobileReceiver = "";
	private String mobileReceiver1 = "";
	private String mobileReceiver2= "";
	private String mobileReceiver3 = "";
	private String country = "";
	private String customs_num = "";
	private String goodsNmArr[];
	private String[] goodsImgArr;
	private String[] eaArr;
	private int[] priceArr;
	
	private String orgPriceSum;

	private FrontOrderSettleVO settleVO = new FrontOrderSettleVO();
	
	
	public String getReserveSum() {
		return reserveSum;
	}
	public void setReserveSum(String reserveSum) {
		this.reserveSum = reserveSum;
	}
	public String getOrgPriceSum() {
		return orgPriceSum;
	}
	public void setOrgPriceSum(String orgPriceSum) {
		this.orgPriceSum = orgPriceSum;
	}
	public String getDelivery_shippingFee() {
		return delivery_shippingFee;
	}
	public void setDelivery_shippingFee(String delivery_shippingFee) {
		this.delivery_shippingFee = delivery_shippingFee;
	}
	public String getUkey() {
		return ukey;
	}
	public void setUkey(String ukey) {
		this.ukey = ukey;
	}
	public void setSettleVO(FrontOrderSettleVO settleVO) {
		this.settleVO = settleVO;
	}	
	public String getSellerAddDelivery() {
		return sellerAddDelivery;
	}
	public void setSellerAddDelivery(String sellerAddDelivery) {
		this.sellerAddDelivery = sellerAddDelivery;
	}
	public List<CouponapplyApplymemberCouponCategoryGoodsno> getUsedCouponInfoList() {
		return usedCouponInfoList;
	}
	public void setUsedCouponInfoList(
			List<CouponapplyApplymemberCouponCategoryGoodsno> usedCouponInfoList) {
		this.usedCouponInfoList = usedCouponInfoList;
	}
	public String getCouponPrice() {
		return couponPrice;
	}
	public void setCouponPrice(String couponPrice) {
		this.couponPrice = couponPrice;
	}
	public String getTotalDeliprice() {
		return totalDeliprice;
	}
	public void setTotalDeliprice(String totalDeliprice) {
		this.totalDeliprice = totalDeliprice;
	}
	public String getSubmitStr() {
		return submitStr;
	}
	public void setSubmitStr(String submitStr) {
		this.submitStr = submitStr;
	}
	public String getSiteDomain() {
		return siteDomain;
	}
	public void setSiteDomain(String siteDomain) {
		this.siteDomain = siteDomain;
	}
	public int getSettle_dcprice() {
		return settle_dcprice;
	}
	public void setSettle_dcprice(int settle_dcprice) {
		this.settle_dcprice = settle_dcprice;
	}
	public String getSettle_dc() {
		return settle_dc;
	}
	public void setSettle_dc(String settle_dc) {
		this.settle_dc = settle_dc;
	}
	public int getSettle_priceSum() {
		return settle_priceSum;
	}
	public void setSettle_priceSum(int settle_priceSum) {
		this.settle_priceSum = settle_priceSum;
	}
	public List<Map<String, String>> getSettle_cartList() {
		return settle_cartList;
	}
	public void setSettle_cartList(List<Map<String, String>> settle_cartList2) {
		this.settle_cartList = settle_cartList2;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public int getSettle_emoney() {
		return settle_emoney;
	}
	public void setSettle_emoney(int settle_emoney) {
		this.settle_emoney = settle_emoney;
	}
	public int getSettle_coupon() {
		return settle_coupon;
	}
	public void setSettle_coupon(int settle_coupon) {
		this.settle_coupon = settle_coupon;
	}
	public int getSettle_deliprice() {
		return settle_deliprice;
	}
	public void setSettle_deliprice(int settle_deliprice) {
		this.settle_deliprice = settle_deliprice;
	}
	public int getSettle_price() {
		return settle_price;
	}
	public void setSettle_price(int settle_price) {
		this.settle_price = settle_price;
	}
	public String getSignKey() {
		return signKey;
	}
	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public Map<String, String> getSignParam() {
		return signParam;
	}
	public void setSignParam(Map<String, String> signParam) {
		this.signParam = signParam;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getCardQuotaBase() {
		return cardQuotaBase;
	}
	public void setCardQuotaBase(String cardQuotaBase) {
		this.cardQuotaBase = cardQuotaBase;
	}
	public String getmKey() {
		return mKey;
	}
	public void setmKey(String mKey) {
		this.mKey = mKey;
	}
	public String getItem_apply_coupon() {
		return item_apply_coupon;
	}
	public void setItem_apply_coupon(String item_apply_coupon) {
		this.item_apply_coupon = item_apply_coupon;
	}
	public String getApply_coupon_0() {
		return apply_coupon_0;
	}
	public void setApply_coupon_0(String apply_coupon_0) {
		this.apply_coupon_0 = apply_coupon_0;
	}
	public String getMobileReceiver_0() {
		return mobileReceiver_0;
	}
	public void setMobileReceiver_0(String mobileReceiver_0) {
		this.mobileReceiver_0 = mobileReceiver_0;
	}
	public String getPhoneReceiver_0() {
		return phoneReceiver_0;
	}
	public void setPhoneReceiver_0(String phoneReceiver_0) {
		this.phoneReceiver_0 = phoneReceiver_0;
	}
	public String getPhoneOrder_0() {
		return phoneOrder_0;
	}
	public void setPhoneOrder_0(String phoneOrder_0) {
		this.phoneOrder_0 = phoneOrder_0;
	}
	public String getMobileOrder_0() {
		return mobileOrder_0;
	}
	public void setMobileOrder_0(String mobileOrder_0) {
		this.mobileOrder_0 = mobileOrder_0;
	}
	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}
	public String getTotal_price() {
		return total_price;
	}
	public String getAdd_sub() {
		return add_sub;
	}
	public String getAddreserve() {
		return addreserve;
	}
	public void setAddreserve(String addreserve) {
		this.addreserve = addreserve;
	}
	public String getTmp_bank() {
		return tmp_bank;
	}
	public void setTmp_bank(String tmp_bank) {
		this.tmp_bank = tmp_bank;
	}
	public String getSettleprice() {
		return settleprice;
	}
	public void setSettleprice(String settleprice) {
		this.settleprice = settleprice;
	}
	public void setAdd_sub(String add_sub) {
		this.add_sub = add_sub;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getEmailRecceiver() {
		return emailRecceiver;
	}
	public void setEmailRecceiver(String emailRecceiver) {
		this.emailRecceiver = emailRecceiver;
	}
	public String getE1() {
		return e1;
	}
	public void setE1(String e1) {
		this.e1 = e1;
	}
	public String getE2() {
		return e2;
	}
	public void setE2(String e2) {
		this.e2 = e2;
	}
	public String getDcPrice() {
		return dcPrice;
	}
	public void setDcPrice(String dcPrice) {
		this.dcPrice = dcPrice;
	}
	public String getPaper_settlement() {
		return paper_settlement;
	}
	public void setPaper_settlement(String paper_settlement) {
		this.paper_settlement = paper_settlement;
	}
	public String getDeliprice() {
		return deliprice;
	}
	public void setDeliprice(String deliprice) {
		this.deliprice = deliprice;
	}
	public String getDelimsg() {
		return delimsg;
	}
	public void setDelimsg(String delimsg) {
		this.delimsg = delimsg;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getEscrow() {
		return escrow;
	}
	public void setEscrow(String escrow) {
		this.escrow = escrow;
	}
	public String getSettlekind() {
		return settlekind;
	}
	public void setSettlekind(String settlekind) {
		this.settlekind = settlekind;
	}
	public String[] getApply_coupon() {
		return apply_coupon;
	}
	public void setApply_coupon(String[] apply_coupon) {
		this.apply_coupon = apply_coupon;
	}
	public String[] getPhoneReceiver() {
		return phoneReceiver;
	}
	public void setPhoneReceiver(String[] phoneReceiver) {
		this.phoneReceiver = phoneReceiver;
	}
	public String getNameReceiver() {
		return nameReceiver;
	}
	public void setNameReceiver(String nameReceiver) {
		this.nameReceiver = nameReceiver;
	}
	public String[] getPhoneOrder() {
		return phoneOrder;
	}
	public void setPhoneOrder(String[] phoneOrder) {
		this.phoneOrder = phoneOrder;
	}
	public String[] getMobileOrder() {
		return mobileOrder;
	}
	public void setMobileOrder(String[] mobileOrder) {
		this.mobileOrder = mobileOrder;
	}
	public String getNameOrder() {
		return nameOrder;
	}
	public void setNameOrder(String nameOrder) {
		this.nameOrder = nameOrder;
	}
	public String getCoupon_emoney() {
		return coupon_emoney;
	}
	public void setCoupon_emoney(String coupon_emoney) {
		this.coupon_emoney = coupon_emoney;
	}
	public int getDelSum() {
		return delSum;
	}
	public void setDelSum(int delSum) {
		this.delSum = delSum;
	}
	public String getDeli_msg() {
		return deli_msg;
	}
	public void setDeli_msg(String deli_msg) {
		this.deli_msg = deli_msg;
	}
	public String getDeliType() {
		return deliType;
	}
	public void setDeliType(String deliType) {
		this.deliType = deliType;
	}
	public String getFreeDeli() {
		return freeDeli;
	}
	public void setFreeDeli(String freeDeli) {
		this.freeDeli = freeDeli;
	}
	public String getUnableMenu() {
		return unableMenu;
	}
	public void setUnableMenu(String unableMenu) {
		this.unableMenu = unableMenu;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getDeliPoli() {
		return deliPoli;
	}
	public void setDeliPoli(int deliPoli) {
		this.deliPoli = deliPoli;
	}
	public int getCoupon() {
		return coupon;
	}
	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getCoupon_use() {
		return coupon_use;
	}
	public void setCoupon_use(String coupon_use) {
		this.coupon_use = coupon_use;
	}
	public List<Map<String, String>> getListCou() {
		return listCou;
	}
	public void setListCou(List<Map<String, String>> listCou) {
		this.listCou = listCou;
	}
	public String getPriceSum2() {
		return priceSum2;
	}
	public void setPriceSum2(String priceSum2) {
		this.priceSum2 = priceSum2;
	}
	public String getApplysno() {
		return applysno;
	}
	public void setApplysno(String applysno) {
		this.applysno = applysno;
	}

	public String getStrWhere() {
		return strWhere;
	}

	public void setStrWhere(String strWhere) {
		this.strWhere = strWhere;
	}

	public String getGrp_sno() {
		return grp_sno;
	}

	public void setGrp_sno(String grp_sno) {
		this.grp_sno = grp_sno;
	}

	public String getOrderitem_mode() {
		return orderitem_mode;
	}

	public void setOrderitem_mode(String orderitem_mode) {
		this.orderitem_mode = orderitem_mode;
	}

	public List<FrontOrderCartMapVO> getCartList() {
		return cartList;
	}

	public void setCartList(List<FrontOrderCartMapVO> cartList2) {
		this.cartList = cartList2;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public String[] getUse() {
		return use;
	}

	public void setUse(String[] use) {
		this.use = use;
	}

	public String getE_max() {
		return e_max;
	}

	public void setE_max(String e_max) {
		this.e_max = e_max;
	}

	public String getDelivery_goodsDelivery() {
		return delivery_goodsDelivery;
	}

	public void setDelivery_goodsDelivery(String delivery_goodsDelivery) {
		this.delivery_goodsDelivery = delivery_goodsDelivery;
	}

	public  List<GdDeliveryPolicy> getRtDelivery() {
		return rtDelivery;
	}

	public void setRtDelivery( List<GdDeliveryPolicy> rtDelivery) {
		this.rtDelivery = rtDelivery;
	}

	public String getPriceSum() {
		return priceSum;
	}

	public void setPriceSum(String priceSum) {
		this.priceSum = priceSum;
	}

	public String getDcprice() {
		return dcprice;
	}

	public void setDcprice(String dcprice) {
		this.dcprice = dcprice;
	}

	public String getEmoney_hold() {
		return emoney_hold;
	}

	public void setEmoney_hold(String emoney_hold) {
		this.emoney_hold = emoney_hold;
	}

	public String getEmoney_min() {
		return emoney_min;
	}

	public void setEmoney_min(String emoney_min) {
		this.emoney_min = emoney_min;
	}

	public String getEmoney_max() {
		return emoney_max;
	}

	public void setEmoney_max(String emoney_max) {
		this.emoney_max = emoney_max;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail_1() {
		return email_1;
	}

	public void setEmail_1(String email_1) {
		this.email_1 = email_1;
	}

	public String getEmail_2() {
		return email_2;
	}

	public void setEmail_2(String email_2) {
		this.email_2 = email_2;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
	public String getMobileReceiver() {
		return mobileReceiver;
	}
	public String getAddress_sub() {
		return address_sub;
	}

	public void setAddress_sub(String address_sub) {
		this.address_sub = address_sub;
	}

	public String getEmoney_base() {
		return emoney_base;
	}

	public void setEmoney_base(String emoney_base) {
		this.emoney_base = emoney_base;
	}

	public String getEmoney() {
		return emoney;
	}

	public void setEmoney(String emoney) {
		this.emoney = emoney;
	}

	public String getPrice() {
		return this.price == null ? "0" : this.price;
	}

	public void setPrice(String price) {
		this.price = price;
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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String[] getMobile() {
		return mobile;
	}

	public void setMobile(String[] mobile) {
		this.mobile = mobile;
	}

	public String getMode2() {
		return mode2;
	}

	public void setMode2(String mode2) {
		this.mode2 = mode2;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	
	public String getGoodsType() {
		return goodsType;
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

	public String getGoodsno() {
		return this.goodsno == null ? "" : this.goodsno;
	}

	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}

	public void setOptionsList(String optionsList) throws UnsupportedEncodingException {
		this.optionsList = URLDecoder.decode(optionsList, "UTF-8");
	}
	
	public String getOptionsList() {
		return optionsList;
	}
	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getM_no() {
		return m_no;
	}

	public void setM_no(String m_no) {
		this.m_no = m_no;
	}
	public String getGoodsCoupon() {
		return goodsCoupon;
	}
	public void setGoodsCoupon(String goodsCoupon) {
		this.goodsCoupon = goodsCoupon;
	}
	public String getDeliveryNo() {
		return deliveryNo;
	}
	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}
	public void setTotalDelivery(String totalDelivery) {
		this.totalDelivery = totalDelivery;
	}
	public String getTotalDelivery() {
		return totalDelivery;
	}
	public void setTotalDeliveryInfoVO(TotalDeliveryInfoVO totalDeliveryInfoVO) {
		this.totalDeliveryInfoVO = totalDeliveryInfoVO;
	}
	public TotalDeliveryInfoVO getTotalDeliveryInfoVO() {
		return totalDeliveryInfoVO;
	}
	public Map<String, String> getDeliveryOverInfo() {
		return deliveryOverInfo;
	}
	public void setDeliveryOverInfo(Map<String, String> deliveryOverInfo) {
		this.deliveryOverInfo = deliveryOverInfo;
	}
	public String getAddDelivery() {
		return addDelivery;
	}
	public void setAddDelivery(String addDelivery) {
		this.addDelivery = addDelivery;
	}
	public void setAddopt(String addopt) {
		this.addopt = addopt;
	}
	public String getAddopt() {
		return addopt;
	}
	public void setCouponSno(String couponSno) {
		this.couponSno = couponSno;
	}
	public String getCouponSno() {
		return couponSno;
	}
	public String getCustomsnum() {
		return customsnum;
	}
	public void setCustomsnum(String customsnum) {
		this.customsnum = customsnum;
	}
	public String getDiszipcode() {
		return diszipcode;
	}
	public void setDiszipcode(String diszipcode) {
		this.diszipcode = diszipcode;
	}
	public String getDisaddress() {
		return disaddress;
	}
	public void setDisaddress(String disaddress) {
		this.disaddress = disaddress;
	}
	public String getDisaddresssub() {
		return disaddresssub;
	}
	public void setDisaddresssub(String disaddresssub) {
		this.disaddresssub = disaddresssub;
	}
	public FrontOrderSettleVO getSettleVO() {
		return settleVO;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCustoms_num() {
		return customs_num;
	}
	public void setCustoms_num(String customs_num) {
		this.customs_num = customs_num;
	}
	public String[] getGoodsNmArr() {
		return goodsNmArr;
	}
	public void setGoodsNmArr(String[] goodsNmArr) {
		this.goodsNmArr = goodsNmArr;
	}
	public String[] getGoodsImgArr() {
		return goodsImgArr;
	}
	public void setGoodsImgArr(String[] goodsImgArr) {
		this.goodsImgArr = goodsImgArr;
	}
	public String[] getEaArr() {
		return eaArr;
	}
	public void setEaArr(String[] eaArr) {
		this.eaArr = eaArr;
	}
	public int[] getPriceArr() {
		return priceArr;
	}
	public void setPriceArr(int[] priceArr) {
		this.priceArr = priceArr;
	}
	public void setSettleVOParams(HttpServletRequest req) throws NoSuchAlgorithmException {
		int totalPrice = StringUtil.N2I(this.priceSum) - StringUtil.N2I(this.emoney) - StringUtil.N2I(this.dcprice) - this.coupon + StringUtil.N2I(this.deliprice) + StringUtil.N2I(this.addDelivery);
		
		this.settleVO.setCST_PLATFORM("test");
		this.settleVO.setCST_MID("autoshop");
		this.settleVO.setLGD_MID(("test".equals(settleVO.getCST_PLATFORM().trim())?"t":"") + settleVO.getCST_MID());
		this.settleVO.setLGD_OID(this.ordno);
		this.settleVO.setLGD_BUYER(this.nameOrder);
		this.settleVO.setLGD_PRODUCTINFO(StringUtil.N2S(this.cartList.get(0).getGoodsnm(),"주문"));
		this.settleVO.setLGD_AMOUNT(StringUtil.N2S(String.valueOf(totalPrice),""));
		this.settleVO.setLGD_BUYEREMAIL(StringUtil.N2S(this.email, ""));
		this.settleVO.setLGD_CUSTOM_SKIN("red");
		this.settleVO.setLGD_WINDOW_VER("2.5");
		this.settleVO.setLGD_CUSTOM_PROCESSTYPE("TWOTR");
		this.settleVO.setLGD_TIMESTAMP(SignatureUtil.getTimestamp());
		this.settleVO.setLGD_BUYERIP(req.getRemoteAddr());
		this.settleVO.setLGD_BUYERID(ShopSessionObject.getSessionObject(req).getUserInfo().getUserId());
		this.settleVO.setLGD_CASNOTEURL("http://xmall.gnujava.com/shop/order/cas_noteurl.jsp");
		String LGD_MERTKEY		= "09c2757fc17ee47d8ea08831201b5498";
		StringBuffer sb = new StringBuffer();
	    sb.append(this.settleVO.getLGD_MID());
	    sb.append(this.settleVO.getLGD_OID());
	    sb.append(this.settleVO.getLGD_AMOUNT());
	    sb.append(this.settleVO.getLGD_TIMESTAMP());
	    sb.append(LGD_MERTKEY);
		byte[] bNoti = sb.toString().getBytes();
	    MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] digest = md.digest(bNoti);
		
	    StringBuffer strBuf = new StringBuffer();
	    for (int ix=0 ; ix < digest.length ; ix++) {
	        int c = digest[ix] & 0xff;
	        if (c <= 15){
	            strBuf.append("0");
	        }
	        strBuf.append(Integer.toHexString(c));
	    }
	    this.settleVO.setLGD_HASHDATA(strBuf.toString());
	}
	
	
	@Override
	public String toString() {
		return "FrontOrderVO [mode=" + mode + ", goodsno=" + goodsno
				+ ", goodsCoupon=" + goodsCoupon + ", optionsList="
				+ optionsList + ", goodsType=" + goodsType + ", m_no=" + m_no
				+ ", opt1=" + opt1 + ", opt2=" + opt2 + ", goods_category="
				+ goods_category + ", guest=" + guest + ", mode2=" + mode2
				+ ", zipcode=" + zipcode + ", address=" + address
				+ ", address_sub=" + address_sub + ", emoney_base="
				+ emoney_base + ", emoney=" + emoney + ", price=" + price
				+ ", ordno=" + ordno + ", goodsnm=" + goodsnm + ", name="
				+ name + ", phone=" + phone + ", mobile="
				+ Arrays.toString(mobile) + ", email=" + email + ", email_1="
				+ email_1 + ", email_2=" + email_2 + ", totalDeliveryInfoVO="
				+ totalDeliveryInfoVO + ", rtDelivery=" + rtDelivery
				+ ", priceSum=" + priceSum + ", dcprice=" + dcprice
				+ ", emoney_hold=" + emoney_hold + ", emoney_min=" + emoney_min
				+ ", emoney_max=" + emoney_max + ", use="
				+ Arrays.toString(use) + ", e_max=" + e_max
				+ ", delivery_goodsDelivery=" + delivery_goodsDelivery
				+ ", orderitem_mode=" + orderitem_mode + ", cartList="
				+ cartList + ", dc=" + dc + ", grp_sno=" + grp_sno
				+ ", strWhere=" + strWhere + ", applysno=" + applysno
				+ ", priceSum2=" + priceSum2 + ", range=" + range
				+ ", coupon_use=" + coupon_use + ", listCou=" + listCou
				+ ", errorMsg=" + errorMsg + ", deliPoli=" + deliPoli
				+ ", coupon=" + coupon + ", level=" + level + ", delSum="
				+ delSum + ", deli_msg=" + deli_msg + ", deliType=" + deliType
				+ ", freeDeli=" + freeDeli + ", unableMenu=" + unableMenu
				+ ", coupon_emoney=" + coupon_emoney + ", nameOrder="
				+ nameOrder + ", phoneOrder=" + Arrays.toString(phoneOrder)
				+ ", mobileOrder=" + Arrays.toString(mobileOrder)
				+ ", nameReceiver=" + nameReceiver + ", phoneReceiver="
				+ Arrays.toString(phoneReceiver) + ", apply_coupon="
				+ Arrays.toString(apply_coupon) + ", memo=" + memo
				+ ", escrow=" + escrow + ", settlekind=" + settlekind
				+ ", delimsg=" + delimsg + ", paper_settlement="
				+ paper_settlement + ", deliprice=" + deliprice + ", dcPrice="
				+ dcPrice + ", e1=" + e1 + ", e2=" + e2 + ", emailRecceiver="
				+ emailRecceiver + ", zip=" + zip + ", add=" + add
				+ ", add_sub=" + add_sub + ", item_apply_coupon="
				+ item_apply_coupon + ", addreserve=" + addreserve
				+ ", tmp_bank=" + tmp_bank + ", settleprice=" + settleprice
				+ ", total_price=" + total_price + ", apply_coupon_0="
				+ apply_coupon_0 + ", mobileReceiver_0=" + mobileReceiver_0
				+ ", phoneReceiver_0=" + phoneReceiver_0 + ", phoneOrder_0="
				+ phoneOrder_0 + ", mobileOrder_0=" + mobileOrder_0
				+ ", settle_dcprice=" + settle_dcprice + ", settle_dc="
				+ settle_dc + ", settle_priceSum=" + settle_priceSum
				+ ", settle_cartList=" + settle_cartList + ", mid=" + mid
				+ ", oid=" + oid + ", settle_emoney=" + settle_emoney
				+ ", settle_coupon=" + settle_coupon + ", settle_deliprice="
				+ settle_deliprice + ", settle_price=" + settle_price
				+ ", siteDomain=" + siteDomain + ", signKey=" + signKey
				+ ", timestamp=" + timestamp + ", signParam=" + signParam
				+ ", signature=" + signature + ", cardQuotaBase="
				+ cardQuotaBase + ", mKey=" + mKey + ", submitStr=" + submitStr
				+ ", deliveryNo=" + deliveryNo + ", totalDelivery="
				+ totalDelivery + ", addopt=" + addopt + ", couponSno="
				+ couponSno + ", deliveryOverInfo=" + deliveryOverInfo
				+ ", addDelivery=" + addDelivery + "]";
	}
	
	
}
