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
package com.wepinit.wepinit_shop.xmall.seller.member.vo;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

import java.util.List;

public class SellerEmailFM extends PageMaker {
	
	//input

	//SMS주소록
	private String sort = "regdt desc";
	private String skey;
	private String sword;
	private String sToName;
	private String sToEmail;
	private String sFromName;
	private String sFromEmail;
	private String sSubject;
	private String sContents;
	
	//SMS문자예제 등록/수정
	private int sno;
	private String subject;
	private String msg;
	private String type;
	private String phone;
	
	//회원그룹정보
	private int klevel;
	//회신전화번호
	private String callbackphone;

	private String[] smsMobile; // 핸드폰번호
	private String grpChk;			// 주소록추가모드
	private String smsGrp;			// 주소록 그룹
	private String smsGrpNew;	// 주소록신규이름
	private String smsName;		// 이름
	private String smsEtc;			// 비고
	private String mode;			// 주소록 신규/수정 모드
	private String chk;				// 주소록 삭제
	
	private String[] sregdt; // 가입일_검색 날짜
	private String slevel; // SMS주소록 그룹
	private String sex; // 성별
	
	
	//output
	private List<SellerEmailVO> sellerEmailList;
//	private List<GdMemberGrp> gdMemberGrpList;
//	private List<GdMember> gdMemberList;
//	private List<GdSmsAddress> gdSmsAddressList;
//	private List<GdSmsSample> gdSmsSampleList;

//	private GdSmsSample smsSampleObj;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
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
	
	public String getSToName() {
		return sToName;
	}

	public void setSToName(String sToName) {
		this.sToName = sToName;
	}
	
	public String getSToEmail() {
		return sToEmail;
	}

	public void setSToEmail(String sToEmail) {
		this.sToEmail = sToEmail;
	}

	public String getSFromName() {
		return sFromName;
	}

	public void setSFromName(String sFromName) {
		this.sFromName = sFromName;
	}
	
	public String getSFromEmail() {
		return sFromEmail;
	}

	public void setSFromEmail(String sFromEmail) {
		this.sFromEmail = sFromEmail;
	}
	
	public String getSSubject() {
		return sSubject;
	}

	public void setSSubject(String sSubject) {
		this.sSubject = sSubject;
	}
	
	public String getSContents() {
		return sContents;
	}

	public void setSContents(String sContents) {
		this.sContents = sContents;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getKlevel() {
		return klevel;
	}

	public void setKlevel(int klevel) {
		this.klevel = klevel;
	}
	
	public String getCallbackphone() {
		return callbackphone;
	}

	public void setCallbackphone(String callbackphone) {
		this.callbackphone = callbackphone;
	}
	
	public String[] getSmsMobile() {
		return smsMobile;
	}

	public void setSmsMobile(String[] smsMobile) {
		this.smsMobile = smsMobile;
	}

	public String getGrpChk() {
		return grpChk;
	}

	public void setGrpChk(String grpChk) {
		this.grpChk = grpChk;
	}

	public String getSmsGrp() {
		return smsGrp;
	}

	public void setSmsGrp(String smsGrp) {
		this.smsGrp = smsGrp;
	}

	public String getSmsGrpNew() {
		return smsGrpNew;
	}

	public void setSmsGrpNew(String smsGrpNew) {
		this.smsGrpNew = smsGrpNew;
	}

	public String getSmsName() {
		return smsName;
	}

	public void setSmsName(String smsName) {
		this.smsName = smsName;
	}

	public String getSmsEtc() {
		return smsEtc;
	}

	public void setSmsEtc(String smsEtc) {
		this.smsEtc = smsEtc;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}

	public String[] getSregdt() {
		return sregdt;
	}

	public void setSregdt(String[] sregdt) {
		this.sregdt = sregdt;
	}

	public String getSlevel() {
		return slevel;
	}

	public void setSlevel(String slevel) {
		this.slevel = slevel;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public List<SellerEmailVO> getSellerEmailList() {
		return sellerEmailList;
	}

	public void setSellerEmailList(List<SellerEmailVO> sellerEmailList) {
		this.sellerEmailList = sellerEmailList;
	}
	
	@Override
	public String toString() {
		return "SellerMailFM [sort=" + sort + ", skey=" + skey + ", sword="
				+ sword  + "]";
	}

											
}
