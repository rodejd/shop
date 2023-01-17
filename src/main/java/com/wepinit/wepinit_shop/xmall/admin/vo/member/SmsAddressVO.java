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
package com.wepinit.wepinit_shop.xmall.admin.vo.member;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdSmsAddress;

import java.util.Arrays;
import java.util.List;

public class SmsAddressVO extends PageMaker {
	
	//input

	//SMS주소록
	private String sort = "regdt desc";
	private String skey;
	private String sword;
	
	private long sno;
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
	private List<GdSmsAddress> smsAddressGroupList = null;
	private List<GdSmsAddress> smsAddressList = null;
	
	private GdSmsAddress smsAddressObj = null;

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

	public long getSno() {
		return sno;
	}

	public void setSno(long sno) {
		this.sno = sno;
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

	public List<GdSmsAddress> getSmsAddressGroupList() {
		return smsAddressGroupList;
	}

	public void setSmsAddressGroupList(List<GdSmsAddress> smsAddressGroupList) {
		this.smsAddressGroupList = smsAddressGroupList;
	}

	public List<GdSmsAddress> getSmsAddressList() {
		return smsAddressList;
	}

	public void setSmsAddressList(List<GdSmsAddress> smsAddressList) {
		this.smsAddressList = smsAddressList;
	}

	public GdSmsAddress getSmsAddressObj() {
		return smsAddressObj;
	}

	public void setSmsAddressObj(GdSmsAddress smsAddressObj) {
		this.smsAddressObj = smsAddressObj;
	}

	@Override
	public String toString() {
		return "SmsAddressVO [sort=" + sort + ", skey=" + skey + ", sword="
				+ sword + ", sno=" + sno + ", smsMobile="
				+ Arrays.toString(smsMobile) + ", grpChk=" + grpChk
				+ ", smsGrp=" + smsGrp + ", smsGrpNew=" + smsGrpNew
				+ ", smsName=" + smsName + ", smsEtc=" + smsEtc + ", mode="
				+ mode + ", chk=" + chk + ", sregdt=" + Arrays.toString(sregdt)
				+ ", slevel=" + slevel + ", sex=" + sex
				+ ", smsAddressGroupList=" + smsAddressGroupList
				+ ", smsAddressList=" + smsAddressList + ", smsAddressObj="
				+ smsAddressObj + "]";
	}

											
}
