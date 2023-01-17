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
package com.wepinit.wepinit_shop.xmall.seller.member.vo;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;


public class SellerOrderMemeberVO extends PageMaker {
	
	
	//gd_member
	private String name;
	private String mid;
	private String sex;
	private String birthyear;
	private String birth;
	private String email;
	private String address;
	private String address_sub;
	private String job;
	private String mobile;
	private String nickname;
	
	//구매횟수
	private String ordercount;
	//판매자 코드
	private String sellerCd;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getOrdercount() {
		return ordercount;
	}

	public void setOrdercount(String ordercount) {
		this.ordercount = ordercount;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthyear() {
		return birthyear;
	}

	public void setBirthyear(String birthyear) {
		this.birthyear = birthyear;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
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

	public String getAddress_sub() {
		return address_sub;
	}

	public void setAddress_sub(String address_sub) {
		this.address_sub = address_sub;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSellerCd() {
		return sellerCd;
	}

	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "SellerOrderMemeberVO [name=" + name + ", mid=" + mid + ", sex="
				+ sex + ", birthyear=" + birthyear + ", birth=" + birth
				+ ", email=" + email + ", address=" + address
				+ ", address_sub=" + address_sub + ", job=" + job + ", mobile="
				+ mobile + ", nickname=" + nickname + ", ordercount="
				+ ordercount + ", sellerCd=" + sellerCd + "]";
	}
	

	
}
