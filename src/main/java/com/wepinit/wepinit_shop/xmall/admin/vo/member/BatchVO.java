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

import java.util.Arrays;


public class BatchVO extends PageMaker{
	//input
	
	//SMS일괄발송
	private String msg;
	private String type;
	private String callbackphone;
	private String func;
	private String emoney;
	private String memo;
	private String level;
	private String status;
	
	private String[] chk;
	private String skey;
	private String sword;
	private String sstatus;
	private String slevel;
	private String ssum_salemin;
	private String ssum_salemax;
	private String semoneymin;
	private String semoneymax;
	private String[] sregdt; // 가입일_검색 날짜
	private String[] slastdt; // 최종로그인_검색날짜
	private String sex;
	private String mailling;
	private String scnt_loginmin;
	private String scnt_loginmax;
	private String dormancy;
	private String birthtype;
	private String birthdatemin;
	private String birthdatemax;
	private String marriyn;
	private String marridatemin;
	private String marridatemax;
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
	public String getCallbackphone() {
		return callbackphone;
	}
	public void setCallbackphone(String callbackphone) {
		this.callbackphone = callbackphone;
	}
	public String getFunc() {
		return func;
	}
	public void setFunc(String func) {
		this.func = func;
	}
	public String getEmoney() {
		return emoney;
	}
	public void setEmoney(String emoney) {
		this.emoney = emoney;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
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
	public String getSstatus() {
		return sstatus;
	}
	public void setSstatus(String sstatus) {
		this.sstatus = sstatus;
	}
	public String getSlevel() {
		return slevel;
	}
	public void setSlevel(String slevel) {
		this.slevel = slevel;
	}
	public String getSsum_salemin() {
		return ssum_salemin;
	}
	public void setSsum_salemin(String ssum_salemin) {
		this.ssum_salemin = ssum_salemin;
	}
	public String getSsum_salemax() {
		return ssum_salemax;
	}
	public void setSsum_salemax(String ssum_salemax) {
		this.ssum_salemax = ssum_salemax;
	}
	public String getSemoneymin() {
		return semoneymin;
	}
	public void setSemoneymin(String semoneymin) {
		this.semoneymin = semoneymin;
	}
	public String getSemoneymax() {
		return semoneymax;
	}
	public void setSemoneymax(String semoneymax) {
		this.semoneymax = semoneymax;
	}
	public String[] getSregdt() {
		return sregdt;
	}
	public void setSregdt(String[] sregdt) {
		this.sregdt = sregdt;
	}
	public String[] getSlastdt() {
		return slastdt;
	}
	public void setSlastdt(String[] slastdt) {
		this.slastdt = slastdt;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMailling() {
		return mailling;
	}
	public void setMailling(String mailling) {
		this.mailling = mailling;
	}
	public String getScnt_loginmin() {
		return scnt_loginmin;
	}
	public void setScnt_loginmin(String scnt_loginmin) {
		this.scnt_loginmin = scnt_loginmin;
	}
	public String getScnt_loginmax() {
		return scnt_loginmax;
	}
	public void setScnt_loginmax(String scnt_loginmax) {
		this.scnt_loginmax = scnt_loginmax;
	}
	public String getDormancy() {
		return dormancy;
	}
	public void setDormancy(String dormancy) {
		this.dormancy = dormancy;
	}
	public String getBirthtype() {
		return birthtype;
	}
	public void setBirthtype(String birthtype) {
		this.birthtype = birthtype;
	}
	public String getBirthdatemin() {
		return birthdatemin;
	}
	public void setBirthdatemin(String birthdatemin) {
		this.birthdatemin = birthdatemin;
	}
	public String getBirthdatemax() {
		return birthdatemax;
	}
	public void setBirthdatemax(String birthdatemax) {
		this.birthdatemax = birthdatemax;
	}
	public String getMarriyn() {
		return marriyn;
	}
	public void setMarriyn(String marriyn) {
		this.marriyn = marriyn;
	}
	public String getMarridatemin() {
		return marridatemin;
	}
	public void setMarridatemin(String marridatemin) {
		this.marridatemin = marridatemin;
	}
	public String getMarridatemax() {
		return marridatemax;
	}
	public void setMarridatemax(String marridatemax) {
		this.marridatemax = marridatemax;
	}
	@Override
	public String toString() {
		return "BatchVO [msg=" + msg + ", type=" + type + ", callbackphone="
				+ callbackphone + ", func=" + func + ", emoney=" + emoney
				+ ", memo=" + memo + ", level=" + level + ", status=" + status
				+ ", chk=" + Arrays.toString(chk) + ", skey=" + skey
				+ ", sword=" + sword + ", sstatus=" + sstatus + ", slevel="
				+ slevel + ", ssum_salemin=" + ssum_salemin + ", ssum_salemax="
				+ ssum_salemax + ", semoneymin=" + semoneymin + ", semoneymax="
				+ semoneymax + ", sregdt=" + Arrays.toString(sregdt)
				+ ", slastdt=" + Arrays.toString(slastdt) + ", sex=" + sex
				+ ", mailling=" + mailling + ", scnt_loginmin=" + scnt_loginmin
				+ ", scnt_loginmax=" + scnt_loginmax + ", dormancy=" + dormancy
				+ ", birthtype=" + birthtype + ", birthdatemin=" + birthdatemin
				+ ", birthdatemax=" + birthdatemax + ", marriyn=" + marriyn
				+ ", marridatemin=" + marridatemin + ", marridatemax="
				+ marridatemax + "]";
	}
	
		
}
