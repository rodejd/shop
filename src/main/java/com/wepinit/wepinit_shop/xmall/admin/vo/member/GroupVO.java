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
import com.wepinit.wepinit_shop.xmall.common.vo.GdMemberGrp;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GroupVO extends PageMaker {
	//input
	private int sno;
	private String grpnm;
	private int kLevel;
	private int dc;
	private int addEmoney;
	private String freeDeliveryfee;
	private int kAmount = 0;
	private Date regdt;
	private Date moddt;
	private int result=0;
	private String errorMsg;
	private String[] menunm;
	private List<String[]> menunmList;
	private int adminCount=0;
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	private String grpLevel;
	private String feeName;
	private int adminAuth;
	private int cnt;
	private String mode;
	
	//메뉴로 부터 들어오는값
	private String firstmenu;
	private String secondmenu;
	
	//output
	private List<GdMemberGrp> memberList = null;
	private GdMemberGrp memberObj = null;
	//popupGroupList
	private List<GdMemberGrp> groupList = null;
	//SelectBoxList용
	private HashMap<Integer,String> selectBoxMap=null;
	private HashMap<Integer, String> checkMap=null;
	
	
	
	
	public int getAdminCount() {
		return adminCount;
	}
	public void setAdminCount(int adminCount) {
		this.adminCount = adminCount;
	}
	public List<String[]> getMenunmList() {
		return menunmList;
	}
	public void setMenunmList(List<String[]> menunmList) {
		this.menunmList = menunmList;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	
	public String getFirstmenu() {
		return firstmenu;
	}
	public void setFirstmenu(String firstmenu) {
		this.firstmenu = firstmenu;
	}
	public String getSecondmenu() {
		return secondmenu;
	}
	public void setSecondmenu(String secondmenu) {
		this.secondmenu = secondmenu;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getAdminAuth() {
		return adminAuth;
	}
	public void setAdminAuth(int adminAuth) {
		System.out.println("test adminAuth >>>>>>" + adminAuth);

		this.adminAuth = adminAuth;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getGrpLevel() {
		return grpLevel;
	}
	public void setGrpLevel(String grpLevel) {
		this.grpLevel = grpLevel;
	}
	public String getFeeName() {
		return feeName;
	}
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		System.out.println("test sno >>>>>>" + sno);
		this.sno = sno;
	}
	public String getGrpnm() {
		return grpnm;
	}
	public void setGrpnm(String grpnm) {
		this.grpnm = grpnm;
	}

	
	public int getDc() {
		return dc;
	}
	public void setDc(int dc) {
		this.dc = dc;
	}
	
	
	public int getkLevel() {
		return kLevel;
	}
	public void setkLevel(int kLevel) {
		this.kLevel = kLevel;
	}
	public int getAddEmoney() {
		return addEmoney;
	}
	public void setAddEmoney(int addEmoney) {
		this.addEmoney = addEmoney;
	}
	public String getFreeDeliveryfee() {
		return freeDeliveryfee;
	}
	public void setFreeDeliveryfee(String freeDeliveryfee) {
		this.freeDeliveryfee = freeDeliveryfee;
	}
	public int getkAmount() {
		return kAmount;
	}
	public void setkAmount(int kAmount) {
		this.kAmount = kAmount;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public Date getModdt() {
		return moddt;
	}
	public void setModdt(Date moddt) {
		this.moddt = moddt;
	}


	
	
	

	public HashMap<Integer, String> getSelectBoxMap() {
		return selectBoxMap;
	}
	public void setSelectBoxMap(HashMap<Integer, String> selectBoxMap) {
		this.selectBoxMap = selectBoxMap;
	}
	public HashMap<Integer, String> getCheckMap() {
		return checkMap;
	}
	public void setCheckMap(HashMap<Integer, String> checkMap) {
		this.checkMap = checkMap;
	}
	public List<GdMemberGrp> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<GdMemberGrp> groupList) {
		this.groupList = groupList;
	}
	
	public List<GdMemberGrp> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<GdMemberGrp> memberList) {
		this.memberList = memberList;
	}
	public GdMemberGrp getMemberObj() {
		return memberObj;
	}
	public void setMemberObj(GdMemberGrp memberObj) {
		this.memberObj = memberObj;
	}
	public String[] getMenunm() {
		return menunm;
	}
	public void setMenunm(String[] menunm) {
		this.menunm = menunm;
	}
	@Override
	public String toString() {
		return "GroupVO [sno=" + sno + ", grpnm=" + grpnm + ", kLevel="
				+ kLevel + ", dc=" + dc + ", addEmoney=" + addEmoney
				+ ", freeDeliveryfee=" + freeDeliveryfee + ", regdt=" + regdt
				+ ", moddt=" + moddt + ", result=" + result + ", errorMsg="
				+ errorMsg + ", menunm=" + Arrays.toString(menunm)
				+ ", menunmList=" + menunmList + ", adminCount=" + adminCount
				+ ", grpLevel=" + grpLevel + ", feeName=" + feeName
				+ ", adminAuth=" + adminAuth + ", cnt=" + cnt + ", mode="
				+ mode + ", firstmenu=" + firstmenu + ", secondmenu="
				+ secondmenu + ", memberList=" + memberList + ", memberObj="
				+ memberObj + ", groupList=" + groupList + ", selectBoxMap="
				+ selectBoxMap + ", checkMap=" + checkMap + "]";
	}
	

		
		

	
}
