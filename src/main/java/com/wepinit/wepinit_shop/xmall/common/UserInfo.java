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
/*
 * Created on 2006-08-30
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.wepinit.wepinit_shop.xmall.common;

import java.io.Serializable;
import java.util.Map;

public class UserInfo implements Serializable {

	private String userId;
	private String userName;
	private String juminNo;
	private String deptName;
	private String err_code;
	private String err_msg;
	private boolean isAdmin;
	private int m_no;


	// #####
	// # 판매사 추가
	// #####
	private String sellerCd = ""; 
	private Map<String, Object> xkey = null;
	
	private String myritzCd = ""; 
	
	
	public String getMyritzCd() {
		return myritzCd;
	}
	public void setMyritzCd(String myritzCd) {
		this.myritzCd = myritzCd;
	}
	//2020.02.10 이현빈 비회원키
	private String ukey = "";
	
	public String getUkey() {
		return ukey;
	}
	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	
	public int getM_no() {
		return m_no;
	}

	public void setM_no(int m_no) {
		this.m_no = m_no;
	}

	public UserInfo(){
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getJuminNo() {
		return juminNo;
	}

	public void setJuminNo(String juminNo) {
		this.juminNo = juminNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String errCode) {
		err_code = errCode;
	}

	public String getErr_msg() {
		return err_msg;
	}

	public void setErr_msg(String errMsg) {
		err_msg = errMsg;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Map<String, Object> getXkey() {
		return xkey;
	}

	public void setXkey(Map<String, Object> xkey) {
		this.xkey = xkey;
	}
	public String getSellerCd() {
		return sellerCd;
	}

	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName
				+ ", juminNo=" + juminNo + ", deptName=" + deptName
				+ ", err_code=" + err_code + ", err_msg=" + err_msg
				+ ", isAdmin=" + isAdmin + ", m_no=" + m_no + ", ukey=" + ukey
				+ ", sellerCd=" + sellerCd + ", xkey="
				+ xkey + "]";
	}

	
}
