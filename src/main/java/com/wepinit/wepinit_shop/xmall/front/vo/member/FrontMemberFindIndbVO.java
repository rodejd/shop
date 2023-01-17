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
package com.wepinit.wepinit_shop.xmall.front.vo.member;

import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import org.springframework.web.multipart.MultipartFile;

public class FrontMemberFindIndbVO extends GdMember {
	// in
	private String mode;
	private String email1;
	private String email2;
	private String birthMonth;
	private String birthDate;
	private String marryYear;
	private String marryMonth;
	private String marryDate;
	private MultipartFile mpUpload;
	private String oldProfile;
	private String newpassword;
	private String newpassword2;
	private String recommid;
	
	// out
	private String alertMessage;
	private String redirectUrl;
	
	@Override
	public String getRecommid() {
		return recommid;
	}
	@Override
	public void setRecommid(String recommid) {
		this.recommid = recommid;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getAlertMessage() {
		return alertMessage;
	}
	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public String getBirthMonth() {
		return birthMonth;
	}
	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getMarryYear() {
		return marryYear;
	}
	public void setMarryYear(String marryYear) {
		this.marryYear = marryYear;
	}
	public String getMarryMonth() {
		return marryMonth;
	}
	public void setMarryMonth(String marryMonth) {
		this.marryMonth = marryMonth;
	}
	public String getMarryDate() {
		return marryDate;
	}
	public void setMarryDate(String marryDate) {
		this.marryDate = marryDate;
	}
	public MultipartFile getMpUpload() {
		return mpUpload;
	}
	public void setMpUpload(MultipartFile mpUpload) {
		this.mpUpload = mpUpload;
	}
	public String getOldProfile() {
		return oldProfile;
	}
	public void setOldProfile(String oldProfile) {
		this.oldProfile = oldProfile;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getNewpassword2() {
		return newpassword2;
	}
	public void setNewpassword2(String newpassword2) {
		this.newpassword2 = newpassword2;
	}
}
