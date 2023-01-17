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
package com.wepinit.wepinit_shop.xmall.seller.oper.vo;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

import java.util.Date;
import java.util.List;

public class SellerManagementFM extends PageMaker {

	private String mode;
	private String div;

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

	// 판매자공지사항 정보
	private SellerManagementVO managementVO;
	
	public List getSellerManagementList() {
		return sellerManagementList;
	}

	public void setSellerManagementList(List sellerManagementList) {
		this.sellerManagementList = sellerManagementList;
	}

	public SellerManagementVO getManagementVO() {
		return managementVO;
	}

	public void setManagementVO(SellerManagementVO managementVO) {
		this.managementVO = managementVO;
	}
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getDiv() {
		return div;
	}
	public void setDiv(String div) {
		this.div = div;
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
		return "SellerManagementFM [sellerCd=" + sellerCd  + "id=" + id + "]";
	}
	
}
