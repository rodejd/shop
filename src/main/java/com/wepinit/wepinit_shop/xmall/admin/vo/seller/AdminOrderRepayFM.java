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
import com.wepinit.wepinit_shop.xmall.common.vo.join.OrdercancelOrderitemMember;

import java.util.List;

public class AdminOrderRepayFM extends PageMaker{
//	private String[] chk;
//	private String[] repay;
//	private String[] repayfee;
//	private String[] remoney;
//	private String[] m_no;
//	private String[] ordno;
//	private String[] bankcode;
//	private String[] bankaccount;
//	private String[] bankuser;
//	private String[] sno;
	
	private String rprice;
	private String rfee;
	private String remoney;
	private String m_no;
	private String ordno;
	private String bankcode;
	private String bankaccount;
	private String bankuser;
	private String sno;
	
	
	private List<OrdercancelOrderitemMember> repayList;
	private OrdercancelOrderitemMember repayObj;
	
	
	public String getRprice() {
		return rprice;
	}
	public void setRprice(String rprice) {
		this.rprice = rprice;
	}
	public String getRfee() {
		return rfee;
	}
	public void setRfee(String rfee) {
		this.rfee = rfee;
	}
	public String getRemoney() {
		return remoney;
	}
	public void setRemoney(String remoney) {
		this.remoney = remoney;
	}
	public String getM_no() {
		return m_no;
	}
	public void setM_no(String m_no) {
		this.m_no = m_no;
	}
	public String getOrdno() {
		return ordno;
	}
	public void setOrdno(String ordno) {
		this.ordno = ordno;
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
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public List<OrdercancelOrderitemMember> getRepayList() {
		return repayList;
	}
	public void setRepayList(List<OrdercancelOrderitemMember> repayList) {
		this.repayList = repayList;
	}
	public OrdercancelOrderitemMember getRepayObj() {
		return repayObj;
	}
	public void setRepayObj(OrdercancelOrderitemMember repayObj) {
		this.repayObj = repayObj;
	}
	@Override
	public String toString() {
		return "RepayVO [rprice=" + rprice + ", rfee=" + rfee + ", remoney="
				+ remoney + ", m_no=" + m_no + ", ordno=" + ordno
				+ ", bankcode=" + bankcode + ", bankaccount=" + bankaccount
				+ ", bankuser=" + bankuser + ", sno=" + sno + ", repayList="
				+ repayList + ", repayObj=" + repayObj + "]";
	}
}
