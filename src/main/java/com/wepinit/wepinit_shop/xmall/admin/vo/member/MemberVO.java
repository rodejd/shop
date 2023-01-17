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
import com.wepinit.wepinit_shop.xmall.common.vo.GdLogEmoney;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import com.wepinit.wepinit_shop.xmall.common.vo.GdOrder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MemberVO extends PageMaker {
	private String sort = "regdt desc";
	private String srchSave = "";
	private String mode = "";
	private String func = "";
	private String deleteMember = "";
	private String popupDetail = "";
	
	private String skey = "";
	private String sword = "";
	private String sstatus = "";
	private String slevel = "";
	private String ssum_salemin = "";
	private String ssum_salemax = "";
	private String semoneymin = "";
	private String semoneymax = "";
	private String scustomsnum = ""; // 통관고유부호
	private String spush = ""; // Push동의여부
	private String sex = "";
	private String sage = "";
	private String scnt_login = "";
	private String scnt_loginmin = "";
	private String scnt_loginmax = "";
	private String dormancy = "";
	private String smailling = "";
	private String smsyn = "";
	private String birthtype = "";
	private String birthdatemin = "";
	private String birthdatemax = "";
	private String marridatemin = "";
	private String marridatemax = "";
	private String smarriyn = "";
	private String mobileYN = "";

	private String modpass; // 패스워드 변경 여부
	private String modsex; // 성별 변경 여부

	private String[] sregdt; // 가입일_검색 날짜
	private String[] slastdt; // 최종로그인_검색날짜
	private String[] sorderdt; // 구매기간_검색 날짜

	private String sregdt_0;
	private String sregdt_1;
	private String slastdt_0;
	private String slastdt_1;
	private String sorderdt_0;
	private String sorderdt_1;

	// gd_member
	private int status;
	private String name;
	private String nickname;
	private int klevel;
	private String password;
	private String birthyear;
	private String[] birth;
	private String strbirth; // birth[] 문자열 합친 결과 값
	private String email;
	private String address;
	private String addresssub;
	private String mobilec;
	private String[] mobile;
	private String strmobile; // mobile[] 문자열 합친 결과 값
	private String sms;
	private String[] interest;
	private int valinterest = 0; // interest[] VALUE 결과 값
	private int emoney;
	private String lastlogin;
	private int cntlogin;
	private String lastsale;
	private int sumsale;
	private int cntsale;
	private String zipcode;
	private String[] marridate;
	private String marridates;
	private int mno;
	private int sno;
	private String chgsex;
	private String mailling = "";
	private String marriyn = "";

	private String diszipcode;
	private String disaddress;
	private String disaddresssub;
	
	private String customsnum = "";
	
	private int totalCount; // 총 건수
	private String mid;
	private String nolist;	// 삭제 선택된 mno
	private String memo;
	private String directmemo;		//적립금 지급이유 직접 작성

	private List<GdMember> gdMemberList = null;
	private GdMember memberObj;				//회원정보
	private List<GdLogEmoney> emoneyLogList;				//회원 적립금 내역
	private Map<String, Object> emoneyObj;
	private List<GdOrder> orderList;				//회원 주문 내역

	private String gbn;	// 구분(S:적립, U:사용, E:소멸, C:취소/환불)
	private String addEmoney;	// 적립율
	
	public String getMailling() {
		return mailling;
	}
	public void setMailling(String mailling) {
		this.mailling = mailling;
	}
	public String getMarriyn() {
		return marriyn;
	}
	public void setMarriyn(String marriyn) {
		this.marriyn = marriyn;
	}
	public String getPopupDetail() {
		return popupDetail;
	}
	public void setPopupDetail(String popupDetail) {
		this.popupDetail = popupDetail;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getNolist() {
		return nolist;
	}
	public void setNolist(String nolist) {
		this.nolist = nolist;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getDirectmemo() {
		return directmemo;
	}
	public void setDirectmemo(String directmemo) {
		this.directmemo = directmemo;
	}
	public String getModpass() {
		return modpass;
	}
	public void setModpass(String modpass) {
		this.modpass = modpass;
	}
	public String getModsex() {
		return modsex;
	}
	public void setModsex(String modsex) {
		this.modsex = modsex;
	}

	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getKlevel() {
		return klevel;
	}

	public void setKlevel(int klevel) {
		this.klevel = klevel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthyear() {
		return birthyear;
	}

	public void setBirthyear(String birthyear) {
		this.birthyear = birthyear;
	}

	public String[] getBirth() {
		return birth;
	}

	public void setBirth(String[] birth) {
		this.birth = birth;
	}

	public String getStrbirth() {
		if (birth != null) {
			String str = "";
			if (birth.length > 0) {
				for (int i = 0; i < birth.length; i++) {
					str += birth[i];
				}
				return str;
			}
			return strbirth;
		}
		return strbirth;
	}

	public void setStrbirth(String strbirth) {
		this.strbirth = strbirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddresssub() {
		return addresssub;
	}

	public void setAddresssub(String addresssub) {
		this.addresssub = addresssub;
	}

	public String getMobilec() {
		return mobilec;
	}
	public void setMobilec(String mobilec) {
		this.mobilec = mobilec;
	}
	public String[] getMobile() {
		return mobile;
	}

	public void setMobile(String[] mobile) {
		this.mobile = mobile;
	}

	public String getStrmobile() {
		if (mobile != null) {
			if (mobile.length > 0) {
				String rst = "";
				for (int i = 0; i < mobile.length; i++) {
					rst += mobile[i];
					if (i < mobile.length - 1)
						rst += "-";
				}
				return rst;
			}
			return strmobile;
		}
		return strmobile;
	}

	public void setStrmobile(String strmobile) {
		this.strmobile = strmobile;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public String[] getInterest() {
		return interest;
	}

	public void setInterest(String[] interest) {
		this.interest = interest;
	}

	public int getValinterest() {
		return valinterest;
	}

	public void setValinterest(int valinterest) {
		this.valinterest = valinterest;
	}

	public int getEmoney() {
		return emoney;
	}

	public void setEmoney(int emoney) {
		this.emoney = emoney;
	}

	public String getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}

	public int getCntlogin() {
		return cntlogin;
	}

	public void setCntlogin(int cntlogin) {
		this.cntlogin = cntlogin;
	}

	public String getLastsale() {
		return lastsale;
	}

	public void setLastsale(String lastsale) {
		this.lastsale = lastsale;
	}

	public int getSumsale() {
		return sumsale;
	}

	public void setSumsale(int sumsale) {
		this.sumsale = sumsale;
	}

	public int getCntsale() {
		return cntsale;
	}

	public void setCntsale(int cntsale) {
		this.cntsale = cntsale;
	}
	public String[] getMarridate() {
		return marridate;
	}
	public void setMarridate(String[] marridate) {
		this.marridate = marridate;
	}
	public String getMarridates() {
		if(marridate != null){
			if(marridate.length > 0){
				marridates="";
				for(int i=0; i<marridate.length; i++){
					marridates = marridates + marridate[i];
				}
			}
		}
		return marridates;
	}
	public void setMarridates(String marridates) {
		this.marridates = marridates;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getChgsex() {
		return chgsex;
	}
	public void setChgsex(String chgsex) {
		this.chgsex = chgsex;
	}
	public GdMember getMemberObj() {
		return memberObj;
	}
	public void setMemberObj(GdMember memberObj) {
		this.memberObj = memberObj;
	}
	public List<GdLogEmoney> getEmoneyLogList() {
		return emoneyLogList;
	}
	public void setEmoneyLogList(List<GdLogEmoney> emoneyLogList) {
		this.emoneyLogList = emoneyLogList;
	}
	public Map<String, Object> getEmoneyObj() {
		return emoneyObj;
	}
	public void setEmoneyObj(Map<String, Object> emoneyObj) {
		this.emoneyObj = emoneyObj;
	}
	public List<GdOrder> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<GdOrder> orderList) {
		this.orderList = orderList;
	}
	public String getSregdt_0() {
		if (sregdt != null) {
			if (sregdt.length > 0) {
				return sregdt[0];
			}
			return sregdt_0;
		}
		return sregdt_0;
	}
	public void setSregdt_0(String sregdt_0) {
		this.sregdt_0 = sregdt_0;
	}

	public String getSregdt_1() {
		if (sregdt != null) {
			if (sregdt.length > 1) {
				return sregdt[1];
			}
			return sregdt_1;
		}
		return sregdt_1;
	}

	public void setSregdt_1(String sregdt_1) {
		this.sregdt_1 = sregdt_1;
	}

	public String getSlastdt_0() {
		if (slastdt != null) {
			if (slastdt.length > 0) {
				return slastdt[0];
			}
			return slastdt_0;
		}
		return slastdt_0;
	}

	public void setSlastdt_0(String slastdt_0) {
		this.slastdt_0 = slastdt_0;
	}

	public String getSlastdt_1() {
		if (slastdt != null) {
			if (slastdt.length > 1) {
				return slastdt[1];
			}
			return slastdt_1;
		}
		return slastdt_1;
	}

	public void setSlastdt_1(String slastdt_1) {
		this.slastdt_1 = slastdt_1;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSrchSave() {
		return srchSave;
	}

	public void setSrchSave(String srchSave) {
		this.srchSave = srchSave;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getDeleteMember() {
		return deleteMember;
	}

	public void setDeleteMember(String deleteMember) {
		this.deleteMember = deleteMember;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSage() {
		return sage;
	}

	public void setSage(String sage) {
		this.sage = sage;
	}
	public String getScnt_login() {
		return scnt_login;
	}
	public void setScnt_login(String scnt_login) {
		this.scnt_login = scnt_login;
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

	public String getSmailling() {
		return smailling;
	}

	public void setSmailling(String smailling) {
		this.smailling = smailling;
	}

	public String getSmsyn() {
		return smsyn;
	}

	public void setSmsyn(String smsyn) {
		this.smsyn = smsyn;
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

	public String getSmarriyn() {
		return smarriyn;
	}

	public void setSmarriyn(String smarriyn) {
		this.smarriyn = smarriyn;
	}

	public String getMobileYN() {
		return mobileYN;
	}

	public void setMobileYN(String mobileYN) {
		this.mobileYN = mobileYN;
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

	public List<GdMember> getGdMemberList() {
		return gdMemberList;
	}

	public void setGdMemberList(List<GdMember> gdMemberList) {
		this.gdMemberList = gdMemberList;
	}
	public String getScustomsnum() {
		return scustomsnum;
	}
	public void setScustomsnum(String scustomsnum) {
		this.scustomsnum = scustomsnum;
	}
	public String getSpush() {
		return spush;
	}
	public void setSpush(String spush) {
		this.spush = spush;
	}
	
	public String[] getSorderdt() {
		return sorderdt;
	}
	public void setSorderdt(String[] sorderdt) {
		this.sorderdt = sorderdt;
	}
	public String getSorderdt_0() {
		if (sorderdt != null) {
			if (sorderdt.length > 0) {
				return sorderdt[0];
			}
			return sorderdt_0;
		}
		return sorderdt_0;
	}
	public void setSorderdt_0(String sorderdt_0) {
		this.sorderdt_0 = sorderdt_0;
	}

	public String getSorderdt_1() {
		if (sorderdt != null) {
			if (sorderdt.length > 1) {
				return sorderdt[1];
			}
			return sorderdt_1;
		}
		return sorderdt_1;
	}

	public void setSorderdt_1(String sorderdt_1) {
		this.sorderdt_1 = sorderdt_1;
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
	public String getCustomsnum() {
		return customsnum;
	}
	public void setCustomsnum(String customsnum) {
		this.customsnum = customsnum;
	}
	public String getGbn() {
		return gbn;
	}
	public void setGbn(String gbn) {
		this.gbn = gbn;
	}
	public String getAddEmoney() {
		return addEmoney;
	}
	public void setAddEmoney(String addEmoney) {
		this.addEmoney = addEmoney;
	}
	@Override
	public String toString() {
		return "MemberVO [sort=" + sort + ", srchSave=" + srchSave + ", mode="
				+ mode + ", func=" + func + ", deleteMember=" + deleteMember
				+ ", popupDetail=" + popupDetail + ", skey=" + skey
				+ ", sword=" + sword + ", sstatus=" + sstatus + ", slevel="
				+ slevel + ", ssum_salemin=" + ssum_salemin + ", ssum_salemax="
				+ ssum_salemax + ", semoneymin=" + semoneymin + ", semoneymax="
				+ semoneymax + ", sex=" + sex + ", sage=" + sage
				+ ", scnt_loginmin=" + scnt_loginmin + ", scnt_loginmax="
				+ scnt_loginmax + ", dormancy=" + dormancy + ", smailling="
				+ smailling + ", smsyn=" + smsyn + ", birthtype=" + birthtype
				+ ", birthdatemin=" + birthdatemin + ", birthdatemax="
				+ birthdatemax + ", marridatemin=" + marridatemin
				+ ", marridatemax=" + marridatemax + ", smarriyn=" + smarriyn
				+ ", mobileYN=" + mobileYN + ", modpass=" + modpass
				+ ", modsex=" + modsex + ", sregdt=" + Arrays.toString(sregdt)
				+ ", slastdt=" + Arrays.toString(slastdt) + ", sregdt_0="
				+ sregdt_0 + ", sregdt_1=" + sregdt_1 + ", slastdt_0="
				+ slastdt_0 + ", slastdt_1=" + slastdt_1 + ", status=" + status
				+ ", name=" + name + ", nickname=" + nickname + ", klevel="
				+ klevel + ", password=" + password + ", birthyear="
				+ birthyear + ", birth=" + Arrays.toString(birth)
				+ ", strbirth=" + strbirth + ", email=" + email + ", address="
				+ address + ", addresssub=" + addresssub + ", mobile="
				+ Arrays.toString(mobile) + ", strmobile=" + strmobile
				+ ", sms=" + sms + ", interest=" + Arrays.toString(interest)
				+ ", valinterest=" + valinterest + ", emoney=" + emoney
				+ ", lastlogin=" + lastlogin + ", cntlogin=" + cntlogin
				+ ", lastsale=" + lastsale + ", sumsale=" + sumsale
				+ ", cntsale=" + cntsale + ", zipcode=" + zipcode
				+ ", marridate=" + Arrays.toString(marridate) + ", marridates="
				+ marridates + ", mno=" + mno + ", sno=" + sno + ", chgsex="
				+ chgsex + ", mailling=" + mailling + ", marriyn=" + marriyn
				+ ", totalCount=" + totalCount + ", mid=" + mid + ", nolist="
				+ nolist + ", memo=" + memo + ", directmemo=" + directmemo
				+ ", gdMemberList=" + gdMemberList + ", memberObj=" + memberObj
				+ ", emoneyLogList=" + emoneyLogList + ", emoneyObj="
				+ emoneyObj + ", orderList=" + orderList + "]";
	}

}
