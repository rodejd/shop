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

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.join.OrderMember;

import java.util.Arrays;
import java.util.List;


public class OrderVO extends PageMaker{
	private String ordno = "";
	private String mode = "";				//주문보기(주문일,주문흐름)
	private String period = "";				//초기기간조회구분
	private String first = "";				//초기값설정
	private String skey = "";				//주문검색(key)
	private String sword = "";				//주문검색(word)
	private String sgkey = "";				//상품검색(key)
	private String sgword = "";				//상품검색(word)
	private String sagent = "";				//주문처
	private String scountry = "";			//배송국가
	private String step = "";				//주문상태1
	private String step2 = "";				//주문상태2
	private String step2_60 = "";			//주문상태2(60:교환완료)
	private String dtkind = "";				//검색일자종류
	private String[] sregdt;				//검색일자
	private String settlekind;				//결제방법
	private String couponyn;				//쿠폰사용여부
	private String cashreceipt;				//현금영수증사용여부
	private String mno = "";				//회원번호
	
	
	
	
	private String[] sno;
	private String opt1;
	
	private List<OrderMember> orderList = null;

	/*판매사명*/
	private String schSellerNm;
	private String schSellerCd;
	
	
	public String getOpt1() {
		return opt1;
	}

	public void setOpt(String opt1) {
		this.opt1 = opt1;
	}

	public String getOrdno() {
		return ordno;
	}

	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getSkey() {
		return skey;
	}

	public void setSkey(String skey) {
		this.skey = skey;
	}

	public String getSword() {
		return sword;
	}

	public void setSword(String sword) {
		this.sword = sword;
	}

	public String getSgkey() {
		return sgkey;
	}

	public void setSgkey(String sgkey) {
		this.sgkey = sgkey;
	}

	public String getSgword() {
		return sgword;
	}

	public void setSgword(String sgword) {
		this.sgword = sgword;
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

	public String getStep2_60() {
		return step2_60;
	}

	public void setStep2_60(String step2_60) {
		this.step2_60 = step2_60;
	}

	public String getDtkind() {
		return dtkind;
	}

	public void setDtkind(String dtkind) {
		this.dtkind = dtkind;
	}

	public String[] getSregdt() {
		return sregdt;
	}

	public void setSregdt(String[] sregdt) {
		this.sregdt = sregdt;
	}

	public String getSettlekind() {
		return settlekind;
	}

	public void setSettlekind(String settlekind) {
		this.settlekind = settlekind;
	}

	public String getCouponyn() {
		return couponyn;
	}

	public void setCouponyn(String couponyn) {
		this.couponyn = couponyn;
	}

	public String getCashreceipt() {
		return cashreceipt;
	}

	public void setCashreceipt(String cashreceipt) {
		this.cashreceipt = cashreceipt;
	}

	public String[] getSno() {
		return sno;
	}

	public void setSno(String[] sno) {
		this.sno = sno;
	}

	public List<OrderMember> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderMember> orderList) {
		this.orderList = orderList;
	}

	

	public String getSchSellerNm() {
		return schSellerNm;
	}

	public void setSchSellerNm(String schSellerNm) {
		this.schSellerNm = schSellerNm;
	}

	public String getSchSellerCd() {
		return schSellerCd;
	}

	public void setSchSellerCd(String schSellerCd) {
		this.schSellerCd = schSellerCd;
	}

	public String getSagent() {
		return sagent;
	}

	public void setSagent(String sagent) {
		this.sagent = sagent;
	}

	public String getScountry() {
		return scountry;
	}

	public void setScountry(String scountry) {
		this.scountry = scountry;
	}
	
	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	@Override
	public String toString() {
		return "OrderVO [ordno=" + ordno + ", mode=" + mode + ", period="
				+ period + ", first=" + first + ", skey=" + skey + ", sword="
				+ sword + ", sgkey=" + sgkey + ", sgword=" + sgword + ", step="
				+ step + ", step2=" + step2 + ", step2_60=" + step2_60
				+ ", dtkind=" + dtkind + ", sregdt=" + Arrays.toString(sregdt)
				+ ", settlekind=" + settlekind + ", couponyn=" + couponyn
				+ ", cashreceipt=" + cashreceipt + ", sno="
				+ Arrays.toString(sno) + ", orderList=" + orderList
				+ ", schSellerNm=" + schSellerNm + ", schSellerCd="
				+ schSellerCd + "]";
	}
}
