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
package com.wepinit.wepinit_shop.xmall.admin.vo.basic;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMemberGrp;
import com.wepinit.wepinit_shop.xmall.common.vo.join.MemberMemberGrp;

import java.util.Arrays;
import java.util.List;

public class AdminGroupVO extends PageMaker {

	
	private String grpType="0";
	private String skey="all";
	private String sword="";
	private String sort="regdt2 desc";
	private int pageNum=0;
	private int totalCount=0;

	
	private String lastLogin="";
	private String now="";
	
	private String mode="";
	private String query="";
	
	private int[] mno=null;
	private String navig="";
	private String[] level=null;
	private int[] vnum=null;
	
	
	//동적query
	private String schSword="";
	private String schLevel="";
	private String schCondition="";
	private String schRegdt="";
	private String orderBy="";
	
	private List<GdMemberGrp> grpSelectList=null;
	private List<MemberMemberGrp> grpList=null;
	
	
	
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int searchCount) {
		this.totalCount = searchCount;
	}
	public String getNow() {
		return now;
	}
	public void setNow(String now) {
		this.now = now;
	}
	public int[] getMno() {
		return mno;
	}
	public void setMno(int[] mno) {
		this.mno = mno;
	}
	
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public List<MemberMemberGrp> getGrpList() {
		return grpList;
	}
	public void setGrpList(List<MemberMemberGrp> grpList) {
		this.grpList = grpList;
	}
	public int[] getVnum() {
		return vnum;
	}
	public void setVnum(int[] vnum) {
		this.vnum = vnum;
	}
	
	public String getSchCondition() {
		return schCondition;
	}
	public void setSchCondition(String schCondition) {
		this.schCondition = schCondition;
	}
	public String getSchRegdt() {
		return schRegdt;
	}
	public void setSchRegdt(String schRegdt) {
		this.schRegdt = schRegdt;
	}
	public List<GdMemberGrp> getGrpSelectList() {
		return grpSelectList;
	}
	public void setGrpSelectList(List<GdMemberGrp> grpSelectList) {
		this.grpSelectList = grpSelectList;
	}
	public String getSchSword() {
		return schSword;
	}
	public void setSchSword(String schSword) {
		this.schSword = schSword;
	}
	public String getSchLevel() {
		return schLevel;
	}
	public void setSchLevel(String schLevel) {
		this.schLevel = schLevel;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getGrpType() {
		return grpType;
	}
	public void setGrpType(String grpType) {
		this.grpType = grpType;
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
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	public String getNavig() {
		return navig;
	}
	public void setNavig(String navig) {
		this.navig = navig;
	}
	public String[] getLevel() {
		return level;
	}
	public void setLevel(String[] level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "AdminGroupVO [grpType=" + grpType + ", skey=" + skey
				+ ", sword=" + sword + ", sort=" + sort + ", pageNum="
				+ pageNum + ", lastLogin=" + lastLogin + ", mode=" + mode
				+ ", query=" + query + ", mno=" + mno + ", navig=" + navig
				+ ", level=" + Arrays.toString(level) + ", vnum="
				+ Arrays.toString(vnum) + ", schSword=" + schSword
				+ ", schLevel=" + schLevel + ", schCondition=" + schCondition
				+ ", schRegdt=" + schRegdt + ", orderBy=" + orderBy
				+ ", grpSelectList=" + grpSelectList + ", grpList=" + grpList
				+ "]";
	}
	


	
}
