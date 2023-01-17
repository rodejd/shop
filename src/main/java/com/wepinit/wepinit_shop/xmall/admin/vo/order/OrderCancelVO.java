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

import java.util.Arrays;

/**
 * 주문 취소/반품 및 수정 입력VO
 */
public class OrderCancelVO {
	
	// 아이템 영역
	private String ordno = "";
	private String mode = "";			
	private String[] sno;				// 취소/반품 대상 상품 번호
	private String[] ea;				// 취소/반품 대상 상품 갯수
	private String memo = "";			// 상세사유
	private String pName = "";			// 처리담당자
	private String code = "";			// 사유
	private String bankcode = "";		// 환불 은행코드
	private String bankaccount = "";	// 환불 은행계좌
	private String bankuser = "";		// 환불 은행주
	
	// 주문정보 영역
	//20191212 사용자 아이디 추가 - 회원할인정보 가져오기위한 유저아이디 
	private String userNm = "";
	private String step = "";
	private String step2 = "";
	private String[] price;			// 상품가격
	private String[] supply;		// 매입가
	private String[] dvno;			// 택배사번호
	private String[] dvcode;		// 송장코드
	private String[] goodsno;		// 상품별 goodsno
	private String enuri = "";		// 에누리
	private String zipcode = "";	// 수령자 우편번호
	private String address = "";	// 수령자 주소
	private String address2 = "";	// 수령자 주소
	private String city = "";	// 수령자 주소
	private String state = "";	// 수령자 주소
	private String customs_num = "";	// 수령자 주소
	private String adminmemo = "";	// 고객상담메모
	private String settlelog = "";	// 고객상담메모
	private String bankSender = "";		// 입금자
	private String nameReceiver = "";	// 수령자
	private String phoneReceiver = "";	// 수령자 연락처
	private String mobileReceiver = "";	// 수령자 연락처 모바일
	private String mobileReceiverType = "";	// 수령자 연락처 구분
	private String deliveryno = "";		// 배송정보 > 택배사
	private String deliverycode = "";	// 배송정보 > 송장번호
	private String country = "";	// 국가정보
	private String email = "";
	private String mno = "";
	
	private String [] opt ;
	
	private String sellerNm; //판매사명

	
	
	@Override
	public String toString() {
		return "OrderCancelVO [ordno=" + ordno + ", mode=" + mode + ", sno=" + Arrays.toString(sno) + ", ea="
				+ Arrays.toString(ea) + ", memo=" + memo + ", pName=" + pName + ", code=" + code + ", bankcode="
				+ bankcode + ", bankaccount=" + bankaccount + ", bankuser=" + bankuser + ", userNm=" + userNm
				+ ", step=" + step + ", step2=" + step2 + ", price=" + Arrays.toString(price) + ", supply="
				+ Arrays.toString(supply) + ", dvno=" + Arrays.toString(dvno) + ", dvcode=" + Arrays.toString(dvcode)
				+ ", goodsno=" + Arrays.toString(goodsno) + ", enuri=" + enuri + ", zipcode=" + zipcode + ", address="
				+ address + ", adminmemo=" + adminmemo + ", settlelog=" + settlelog + ", bankSender=" + bankSender
				+ ", nameReceiver=" + nameReceiver + ", phoneReceiver=" + phoneReceiver + ", mobileReceiver="
				+ mobileReceiver + ", deliveryno=" + deliveryno + ", deliverycode=" + deliverycode + ", email=" + email
				+ ", opt=" + Arrays.toString(opt) + ", sellerNm=" + sellerNm + "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String[] getOpt() {
		return opt;
	}
	public void setOpt(String[] opt) {
		this.opt = opt;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
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
	public String[] getSno() {
		return sno;
	}
	public void setSno(String[] sno) {
		this.sno = sno;
	}
	public String[] getEa() {
		return ea;
	}
	public void setEa(String[] ea) {
		this.ea = ea;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBankcode() {
		return bankcode;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	public String getBankaccount() {
		return bankaccount;
	}
	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}
	public String getBankuser() {
		return bankuser;
	}
	public void setBankuser(String bankuser) {
		this.bankuser = bankuser;
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

	public String[] getPrice() {
		return price;
	}

	public void setPrice(String[] price) {
		this.price = price;
	}

	public String[] getSupply() {
		return supply;
	}

	public void setSupply(String[] supply) {
		this.supply = supply;
	}

	public String[] getDvno() {
		return dvno;
	}

	public void setDvno(String[] dvno) {
		this.dvno = dvno;
	}

	public String[] getDvcode() {
		return dvcode;
	}

	public void setDvcode(String[] dvcode) {
		this.dvcode = dvcode;
	}

	public String getEnuri() {
		return enuri;
	}

	public void setEnuri(String enuri) {
		this.enuri = enuri;
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

	public String getAdminmemo() {
		return adminmemo;
	}

	public void setAdminmemo(String adminmemo) {
		this.adminmemo = adminmemo;
	}

	public String getSettlelog() {
		return settlelog;
	}

	public void setSettlelog(String settlelog) {
		this.settlelog = settlelog;
	}

	public String getBankSender() {
		return bankSender;
	}

	public void setBankSender(String bankSender) {
		this.bankSender = bankSender;
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

	public void setMobileReceiver(String mobileReceiver) {
		this.mobileReceiver = mobileReceiver;
	}

	public String getDeliveryno() {
		return deliveryno;
	}

	public void setDeliveryno(String deliveryno) {
		this.deliveryno = deliveryno;
	}

	public String getDeliverycode() {
		return deliverycode;
	}

	public void setDeliverycode(String deliverycode) {
		this.deliverycode = deliverycode;
	}

	public String[] getGoodsno() {
		return goodsno;
	}

	public String getSellerNm() {
		return sellerNm;
	}

	public void setSellerNm(String sellerNm) {
		this.sellerNm = sellerNm;
	}

	public void setGoodsno(String[] goodsno) {
		this.goodsno = goodsno;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getMobileReceiverType() {
		return mobileReceiverType;
	}
	public void setMobileReceiverType(String mobileReceiverType) {
		this.mobileReceiverType = mobileReceiverType;
	}
	public String getMno() {
		return mno;
	}
	public void setMno(String mno) {
		this.mno = mno;
	}
}
