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
package com.wepinit.wepinit_shop.xmall.front.vo.goods;

public class FrontGoodsCouponVO {
	
	private int mno;
	private String grpsno;
	private String goodsno;
	private String goodsnm;
	private String goodsnmKR;
	private String goodsnmCN;
	private String couponcd;				//쿠폰번호
	private int membertype;			//회원타입(0:전체회원발급/1:그룹별발급/2:회원개별발급)
	private int membergrpsno;		//회원그룹번호(그룹별발급일 경우)
	private int sno;						//쿠폰발급내역 일련번호 최대값
	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getGrpsno() {
		return grpsno;
	}
	public void setGrpsno(String grpsno) {
		this.grpsno = grpsno;
	}
	public String getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}
	public String getGoodsnm() {
		return goodsnm;
	}
	public void setGoodsnm(String goodsnm) {
		this.goodsnm = goodsnm;
	}
	public String getGoodsnmKR() {
		return goodsnmKR;
	}
	public void setGoodsnmKR(String goodsnmKR) {
		this.goodsnmKR = goodsnmKR;
	}
	public String getGoodsnmCN() {
		return goodsnmCN;
	}
	public void setGoodsnmCN(String goodsnmCN) {
		this.goodsnmCN = goodsnmCN;
	}
	public String getCouponcd() {
		return couponcd;
	}
	public void setCouponcd(String couponcd) {
		this.couponcd = couponcd;
	}
	public int getMembergrpsno() {
		return membergrpsno;
	}
	public void setMembergrpsno(int membergrpsno) {
		this.membergrpsno = membergrpsno;
	}
	public int getMembertype() {
		return membertype;
	}
	public void setMembertype(int membertype) {
		this.membertype = membertype;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}

}
