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
package com.wepinit.wepinit_shop.xmall.admin.vo.seller;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

import java.util.Date;
import java.util.List;

public class AdminSellerManagementFM extends PageMaker {

	// 검색조건
	private String skey;
	private String sword;
	private String sId;
	private String sSellerNm;
	private String sCompanyRegNo;
	private String sManagerNm;
	private String sManagerTel;
	private String sManagerHp;
	private String sManagerEmail;
		
	private String mode;
	private String nolist;
	private String view;
	
	private int    dupliCount;
	private String totalApp;
	private String todayApp;
	
	private String sellerCd;
	private String id;
	private String pwd;
	private String status;
	private String sellerNm;
	private String companyRegNo;
	private String agentNm;
	private String businessConditions;
	private String event;
	private String homepage;
	private String zipcode;
	private String sellerAddr;
	private String sellerAddrSub;
	private String etc;
	private String adminMemo;
	private String settlementCycle;
	private String fees;
	private String feeDiv;
	private String flag;
	private Date regdt;
	private String reguser;
	private Date moddt;
	private String moduser;
	private String sellerPw;
	
	private int sno;
	private String managerNm;
	private String managerTel;
	private String managerHp;
	private String managerEmail;
	private String managerPosition;
	private String managerFax;

	private String accNm;
	private String bankCd;
	private String accNo;
	
	private List sellerManagementList; // 판매자 공지사항 목록
	private List<AdminSellerManagementVO> sellerList = null;
	
//	private List<AdminSellerManagementListVO> sellerManagementList = null; // 판매사신청목록
	private AdminSellerManagementFM sellerObj = null;
	private AdminSellerManagementVO sellerView = null;
	
	// 판매자공지사항 정보
	private AdminSellerManagementVO managementVO;
	
	
	
	
	
	public String getSellerPw() {
		return sellerPw;
	}

	public void setSellerPw(String sellerPw) {
		this.sellerPw = sellerPw;
	}

	public List getSellerManagementList() {
		return sellerManagementList;
	}

	public void setSellerManagementList(List sellerManagementList) {
		this.sellerManagementList = sellerManagementList;
	}
	
	public List<AdminSellerManagementVO> getSellerList() {
		return sellerList;
	}

	public void setSellerList(List<AdminSellerManagementVO> sellerList) {
		this.sellerList = sellerList;
	}
	
	
//	public List<AdminSellerManagementListVO> getSellerManagementList() {
//		return sellerManagementList;
//	}
//	public void setSellerManagementList(List<AdminSellerManagementListVO> sellerManagementList) {
//		this.sellerManagementList = sellerManagementList;
//	}

	public AdminSellerManagementFM getSellerObj() {
		return sellerObj;
	}
	public void setSellerObj(AdminSellerManagementFM sellerObj) {
		this.sellerObj = sellerObj;
	}
	
	public AdminSellerManagementVO getSellerView() {
		return sellerView;
	}
	public void setSellerView(AdminSellerManagementVO sellerView) {
		this.sellerView = sellerView;
	}

	public AdminSellerManagementVO getManagementVO() {
		return managementVO;
	}

	public void setManagementVO(AdminSellerManagementVO managementVO) {
		this.managementVO = managementVO;
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
	public String getSId() {
		return sId;
	}
	public void setSId(String sId) {
		this.sId = sId;
	}
	public String getSSellerNm() {
		return sSellerNm;
	}
	public void setSSellerNm(String sSellerNm) {
		this.sSellerNm = sSellerNm;
	}
	public String getSCompanyRegNo() {
		return sCompanyRegNo;
	}
	public void setSCompanyRegNo(String sCompanyRegNo) {
		this.sCompanyRegNo = sCompanyRegNo;
	}
	public String getSManagerNm() {
		return sManagerNm;
	}
	public void setSManagerNm(String sManagerNm) {
		this.sManagerNm = sManagerNm;
	}
	public String getSManagerTel() {
		return sManagerTel;
	}
	public void setSManagerTel(String sManagerTel) {
		this.sManagerTel = sManagerTel;
	}
	public String getSManagerHp() {
		return sManagerHp;
	}
	public void setSManagerHp(String sManagerHp) {
		this.sManagerHp = sManagerHp;
	}
	public String getSManagerEmail() {
		return sManagerEmail;
	}
	public void setSManagerEmail(String sManagerEmail) {
		this.sManagerEmail = sManagerEmail;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getNolist() {
		return nolist;
	}
	public void setNolist(String nolist) {
		this.nolist = nolist;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	
	public int getDupliCount() {
		return dupliCount;
	}
	public void setDupliCount(int dupliCount) {
		this.dupliCount = dupliCount;
	}
	public String getTotalApp() {
		return totalApp;
	}
	public void setTotalApp(String totalApp) {
		this.totalApp = totalApp;
	}
	public String getTodayApp() {
		return todayApp;
	}
	public void setTodayApp(String todayApp) {
		this.todayApp = todayApp;
	}
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSellerNm() {
		return sellerNm;
	}
	public void setSellerNm(String sellerNm) {
		this.sellerNm = sellerNm;
	}
	public String getCompanyRegNo() {
		return companyRegNo;
	}
	public void setCompanyRegNo(String companyRegNo) {
		this.companyRegNo = companyRegNo;
	}
	public String getAgentNm() {
		return agentNm;
	}
	public void setAgentNm(String agentNm) {
		this.agentNm = agentNm;
	}
	public String getBusinessConditions() {
		return businessConditions;
	}
	public void setBusinessConditions(String businessConditions) {
		this.businessConditions = businessConditions;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getSellerAddr() {
		return sellerAddr;
	}
	public void setSellerAddr(String sellerAddr) {
		this.sellerAddr = sellerAddr;
	}
	public String getSellerAddrSub() {
		return sellerAddrSub;
	}
	public void setSellerAddrSub(String sellerAddrSub) {
		this.sellerAddrSub = sellerAddrSub;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getAdminMemo() {
		return adminMemo;
	}
	public void setAdminMemo(String adminMemo) {
		this.adminMemo = adminMemo;
	}
	public String getSettlementCycle() {
		return settlementCycle;
	}
	public void setSettlementCycle(String settlementCycle) {
		this.settlementCycle = settlementCycle;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	public String getFeeDiv() {
		return feeDiv;
	}
	public void setFeeDiv(String feeDiv) {
		this.feeDiv = feeDiv;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public String getReguser() {
		return reguser;
	}
	public void setReguser(String reguser) {
		this.reguser = reguser;
	}
	public Date getModdt() {
		return moddt;
	}
	public void setModdt(Date moddt) {
		this.moddt = moddt;
	}
	public String getModuser() {
		return moduser;
	}
	public void setModuser(String moduser) {
		this.moduser = moduser;
	}

	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getManagerNm() {
		return managerNm;
	}
	public void setManagerNm(String managerNm) {
		this.managerNm = managerNm;
	}
	public String getManagerTel() {
		return managerTel;
	}
	public void setManagerTel(String managerTel) {
		this.managerTel = managerTel;
	}
	public String getManagerHp() {
		return managerHp;
	}
	public void setManagerHp(String managerHp) {
		this.managerHp = managerHp;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	public String getManagerPosition() {
		return managerPosition;
	}
	public void setManagerPosition(String managerPosition) {
		this.managerPosition = managerPosition;
	}
	public String getManagerFax() {
		return managerFax;
	}
	public void setManagerFax(String managerFax) {
		this.managerFax = managerFax;
	}
	
	public String getAccNm() {
		return accNm;
	}
	public void setAccNm(String accNm) {
		this.accNm = accNm;
	}
	public String getBankCd() {
		return bankCd;
	}
	public void setBankCd(String bankCd) {
		this.bankCd = bankCd;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	@Override
	public String toString() {
//		return "AdminSellerManagementFM [sellerCd=" + sellerCd  + "dupliCount=" + dupliCount + "]";
		return "sellerCd:" + sellerCd  + ",dupliCount: " + dupliCount;
	}

}
