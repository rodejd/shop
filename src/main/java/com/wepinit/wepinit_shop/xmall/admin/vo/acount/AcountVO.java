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
package com.wepinit.wepinit_shop.xmall.admin.vo.acount;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

import java.util.Arrays;
import java.util.List;

public class AcountVO extends PageMaker {
	
	String ordno;				//주문번호
	int goodsno;				//상품번호
	String goodsnm;				//상품명(영문)
	String goodsnmKR;			//상품명(국문)
	String goodsnmCN;			//상품명(중문)
	String sellernm;			//판매사명
	String sellerCd;			//판매사코드
	String search; 				//검색
	String skey;				//검색
	String s_orddt[];			//검색날짜
	String s_ord0;
	String s_ord1;
	String sflag;				//정산상태 검색
	
	String orddt;				//주문일시
	int ea;						//수량
	int price;					//판매가격
	String addopt;				//추가옵션
	int deliveryPrice;			//배송비
	int addDeliveryPrice;		//추가배송비
	int fees;					//수수료
	int coupon;					//쿠폰가격
	String accountFlag;			//정산상태
	String accountDate;			//정산일자
	
	int accountPrice;			//정산금액
	int applyfees;				//수수료계산
	int salePrice;				//판매금액

	int [] total ;				//총 금액

	int totalCnt; 				//총 건수

	String myritznm;			//판매사명
	String myritzCd;			//판매사코드
	
	
	
	public String getMyritznm() {
		return myritznm;
	}
	public void setMyritznm(String myritznm) {
		this.myritznm = myritznm;
	}
	public String getMyritzCd() {
		return myritzCd;
	}
	public void setMyritzCd(String myritzCd) {
		this.myritzCd = myritzCd;
	}
	public void setAcountList(List<AcountVO> acountList) {
		this.acountList = acountList;
	}
	public int[] getTotal() {
		return total;
	}
	public void setTotal(int[] total) {
		this.total = total;
	}

	public String getSflag() {
		return sflag;
	}
	public void setSflag(String sflag) {
		this.sflag = sflag;
	}
	public String getS_ord0() {
		if(s_orddt != null && s_orddt.length > 0){
			return s_orddt[0];
		}else{
			return s_ord0;
		}
	}
	public void setS_ord0(String s_ord0) {
		this.s_ord0 = s_ord0;
	}
	public String getS_ord1() {
		if(s_orddt != null && s_orddt.length > 1){
			return s_orddt[1];
		}else{
			return s_ord1;
		}
	}
	public void setS_ord1(String s_ord1) {
		this.s_ord1 = s_ord1;
	}
	public String[] getS_orddt() {
		return s_orddt;
	}
	public void setS_orddt(String[] s_orddt) {
		this.s_orddt = s_orddt;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public int getApplyfees() {
		return applyfees;
	}
	public void setApplyfees(int applyfees) {
		this.applyfees = applyfees;
	}

	List<AcountVO> acountList = null;
	
	
	public List<AcountVO> getAcountList() {
		return acountList;
	}
	public void setAccountList(List<AcountVO> acountList) {
		this.acountList = acountList;
	}
	public String getOrdno() {
		return ordno;
	}
	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}
	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
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
	public String getSellernm() {
		return sellernm;
	}
	public void setSellernm(String sellernm) {
		this.sellernm = sellernm;
	}
	public String getOrddt() {
		return orddt;
	}
	public void setOrddt(String orddt) {
		this.orddt = orddt;
	}
	public int getEa() {
		return ea;
	}
	public void setEa(int ea) {
		this.ea = ea;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getAddopt() {
		return addopt;
	}
	public void setAddopt(String addopt) {
		this.addopt = addopt;
	}
	public int getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(int deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	public int getAddDeliveryPrice() {
		return addDeliveryPrice;
	}
	public void setAddDeliveryPrice(int addDeliveryPrice) {
		this.addDeliveryPrice = addDeliveryPrice;
	}
	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	public int getCoupon() {
		return coupon;
	}
	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}
	public int getAccountPrice() {
		return accountPrice;
	}
	public void setAccountPrice(int accountPrice) {
		this.accountPrice = accountPrice;
	}
	public String getAccountFlag() {
		return accountFlag;
	}
	public void setAccountFlag(String accountFlag) {
		this.accountFlag = accountFlag;
	}
	public String getAccountDate() {
		return accountDate;
	}
	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}
	@Override
	public String toString() {
		return "AccountVO [ordno=" + ordno + ", goodsno=" + goodsno
				+ ", goodsnm=" + goodsnm + ", sellernm=" + sellernm
				+ ", sellerCd=" + sellerCd + ", search=" + search + ", skey="
				+ skey + ", s_orddt=" + Arrays.toString(s_orddt) + ", s_ord0="
				+ s_ord0 + ", s_ord1=" + s_ord1 + ", sflag=" + sflag
				+ ", orddt=" + orddt + ", ea=" + ea + ", price=" + price
				+ ", addopt=" + addopt + ", deliveryPrice=" + deliveryPrice
				+ ", addDeliveryPrice=" + addDeliveryPrice + ", fees=" + fees
				+ ", coupon=" + coupon + ", accountFlag=" + accountFlag
				+ ", accountDate=" + accountDate + ", accountPrice="
				+ accountPrice + ", applyfees=" + applyfees + ", salePrice="
				+ salePrice + ", total=" + Arrays.toString(total)
				+ ", totalCnt=" + totalCnt + ", accountList=" + acountList
				+ "]";
	}
	
	

}
