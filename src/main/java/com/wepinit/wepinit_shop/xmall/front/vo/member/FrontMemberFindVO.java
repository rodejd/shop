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
/********************************************************************************
* <pre>
* 업무구분명			:	Member
* 세부업무구분명	: 	사용자 > Member
* 작성자 				: 	이병환
* 설명 				: 	사용자 Member VO
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170208		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.vo.member;

import java.util.List;


public class FrontMemberFindVO extends FrontMemberVO {

	// in
	private String act;
	private String srchName	= "";
	private String srchNum1	= "";
	private String srchNum2	= "";
	private String srchMail	= "";
	private String srchId	= "";
	
	// out
	private List<String> idList;
	private String resnoChk;
	private String emailChk;
	private String alertMessage;
	
	//비밀번호 변경
	private String token;
	private String join_pwd1;
	private String join_pwd2;
	
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getSrchName() {
		return srchName;
	}
	public void setSrchName(String srchName) {
		this.srchName = srchName;
	}
	public String getSrchNum1() {
		return srchNum1;
	}
	public void setSrchNum1(String srchNum1) {
		this.srchNum1 = srchNum1;
	}
	public String getSrchNum2() {
		return srchNum2;
	}
	public void setSrchNum2(String srchNum2) {
		this.srchNum2 = srchNum2;
	}
	public String getSrchMail() {
		return srchMail;
	}
	public void setSrchMail(String srchMail) {
		this.srchMail = srchMail;
	}
	public List<String> getIdList() {
		return idList;
	}
	public void setIdList(List<String> idList) {
		this.idList = idList;
	}
	public String getResnoChk() {
		return resnoChk;
	}
	public void setResnoChk(String resnoChk) {
		this.resnoChk = resnoChk;
	}
	public String getEmailChk() {
		return emailChk;
	}
	public void setEmailChk(String emailChk) {
		this.emailChk = emailChk;
	}
	public String getSrchId() {
		return srchId;
	}
	public void setSrchId(String srchId) {
		this.srchId = srchId;
	}
	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}
	public String getAlertMessage() {
		return alertMessage;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getJoin_pwd1() {
		return join_pwd1;
	}
	public void setJoin_pwd1(String join_pwd1) {
		this.join_pwd1 = join_pwd1;
	}
	public String getJoin_pwd2() {
		return join_pwd2;
	}
	public void setJoin_pwd2(String join_pwd2) {
		this.join_pwd2 = join_pwd2;
	}
	
}
