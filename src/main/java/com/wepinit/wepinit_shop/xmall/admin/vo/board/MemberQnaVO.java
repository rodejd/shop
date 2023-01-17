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
package com.wepinit.wepinit_shop.xmall.admin.vo.board;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMemberQna;
import com.wepinit.wepinit_shop.xmall.common.vo.join.MemberMemberqna;

import java.util.Date;
import java.util.List;

public class MemberQnaVO extends PageMaker {
	
	private int sno;
	private String itemcd;
	private int parent;
	private String subject;
	private String contents;
	private int mno;
	private String email;
	private String mobile;
	private String mailling;
	private String sms;
	private int ordno;
	private Date regdt;
	private String ip;
	private Date maildt;
	private String name;
	private String password;
	private Date smsdt;
	
	private int totalCnt;			//총 건수
	private String sregd_0;
	private String sregd_1;
	private String[] sregdt;		//등록일 검색
	
	private String skey;			//키워드 검색 KEY
	private String sort;				//정렬
	private String sitemcd;		//질문유형
	private String sword;			//키워드 검색 VALUE
	
	private String sbtype;		//문의분야 검색
	private String schReply;	//답변여부 검색
	
	private String mode;
	private String mid;				//답변관리자 ID
	private String formType;
	
	private String nolist;
	
	private List<MemberMemberqna> memQnaList;
	private GdMemberQna memQnaObj;
	private List<GdMember> memberList;
	private GdMemberQna memQnaPrtObj;		//답변 원글 DATA

	public List<MemberMemberqna> getMemQnaList() {
		return memQnaList;
	}
	public void setMemQnaList(List<MemberMemberqna> memQnaList) {
		this.memQnaList = memQnaList;
	}
	public GdMemberQna getMemQnaObj() {
		return memQnaObj;
	}
	public void setMemQnaObj(GdMemberQna memQnaObj) {
		this.memQnaObj = memQnaObj;
	}
	public GdMemberQna getMemQnaPrtObj() {
		return memQnaPrtObj;
	}
	public void setMemQnaPrtObj(GdMemberQna memQnaPrtObj) {
		this.memQnaPrtObj = memQnaPrtObj;
	}
	public List<GdMember> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<GdMember> memberList) {
		this.memberList = memberList;
	}
	
	public String getNolist() {
		return nolist;
	}
	public void setNolist(String nolist) {
		this.nolist = nolist;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getFormType() {
		return formType;
	}
	public void setFormType(String formType) {
		this.formType = formType;
	}
	public String getSword() {
		return sword;
	}
	public void setSword(String sword) {
		this.sword = sword;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public String getSregdt_0() {
		if(sregdt != null) {
			if(sregdt.length > 0){
				return sregdt[0];
			}
			return sregd_0;
		} else {
			return sregd_0;
		}
	}
	public void setSregdt_0(String sregd_0) {
		this.sregd_0 = sregd_0;
	}
	public String getSregdt_1() {
		if(sregdt != null ){
			if(sregdt.length > 0){
			return sregdt[1];
			}
			return sregd_1;
		} else {
			return sregd_1;
		}
	}
	public void setSregdt_1(String sregd_1) {
		this.sregd_1 = sregd_1;
	}
	public String[] getSregdt() {
		return sregdt;
	}
	public void setSregdt(String[] sregdt) {
		this.sregdt = sregdt;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getSitemcd() {
		return sitemcd;
	}
	public void setSitemcd(String sitemcd) {
		this.sitemcd = sitemcd;
	}
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getItemcd() {
		return itemcd;
	}
	public void setItemcd(String itemcd) {
		this.itemcd = itemcd;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMailling() {
		return mailling;
	}
	public void setMailling(String mailling) {
		this.mailling = mailling;
	}
	public String getSms() {
		return sms;
	}
	public void setSms(String sms) {
		this.sms = sms;
	}
	public int getOrdno() {
		return ordno;
	}
	public void setOrdno(int ordno) {
		this.ordno = ordno;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getMaildt() {
		return maildt;
	}
	public void setMaildt(Date maildt) {
		this.maildt = maildt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getSmsdt() {
		return smsdt;
	}
	public void setSmsdt(Date smsdt) {
		this.smsdt = smsdt;
	}
	public String getSbtype() {
		return sbtype;
	}
	public void setSbtype(String sbtype) {
		this.sbtype = sbtype;
	}
	public String getSchReply() {
		return schReply;
	}
	public void setSchReply(String schReply) {
		this.schReply = schReply;
	}
	
	@Override
	public String toString() {
		return "MemberQnaVO [sno=" + sno + ", itemcd=" + itemcd + ", parent="
				+ parent + ", subject=" + subject + ", contents=" + contents
				+ ", mNo=" + mno + ", email=" + email + ", mobile=" + mobile
				+ ", mailling=" + mailling + ", sms=" + sms + ", ordno="
				+ ordno + ", regdt=" + regdt + ", ip=" + ip + ", maildt="
				+ maildt + ", name=" + name + ", password=" + password
				+ ", smsdt=" + smsdt + ", totalCnt=" + totalCnt + ", sregd0="
				+ sregd_0 + ", sregd1=" + sregd_1 + ", skey=" + skey + ", sort="
				+ sort + ", sitemcd=" + sitemcd + ", sword=" + sword
				+ ", memQnaList=" + memQnaList + "]";
	}

}
