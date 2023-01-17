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
package com.wepinit.wepinit_shop.xmall.seller.board.vo;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsQna;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SellerGoodsQnaVO extends PageMaker{
	private int sno;
	private int goodsno;
	private int parent;
	private String subject;
	private String contents;
	private int mno;
	private Date regdt;
	private String ip;
	private String name;
	private String password;
	
	private String sort;			//정렬
	private String skey;		//키워드 KEY
	private String sword;		//키워드 VALUE
	
	private String[] cate;		//카테고리
	private String cateVal;
	
	private String[] sregdt;	//등록일
	private String sregdt_0;
	private String sregdt_1;
	
	private int totalCount;
	private String mode;
	private String nolist;
	private String schword;	//쿼리문
	
	private String schcate;
	private String subtable;
	private String schparent;
	
	private String sellerCd;
	
	private List<GdGoodsQna> goodsQnaList;	//목록 화면 데이터
	private GdGoodsQna goodsQnaObj;			//조회/수정화면 데이터
	private List<GdMember> adminMemList;		//답글 권한 관리자 리스트
	
	
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	public String getSchcate() {
		return schcate;
	}
	public void setSchcate(String schcate) {
		this.schcate = schcate;
	}
	public String getSubtable() {
		return subtable;
	}
	public void setSubtable(String subtable) {
		this.subtable = subtable;
	}
	public String getSchparent() {
		return schparent;
	}
	public void setSchparent(String schparent) {
		this.schparent = schparent;
	}
	public List<GdMember> getAdminMemList() {
		return adminMemList;
	}
	public void setAdminMemList(List<GdMember> adminMemList) {
		this.adminMemList = adminMemList;
	}
	public List<GdGoodsQna> getGoodsQnaList() {
		return goodsQnaList;
	}
	public void setGoodsQnaList(List<GdGoodsQna> goodsQnaList) {
		this.goodsQnaList = goodsQnaList;
	}
	public GdGoodsQna getGoodsQnaObj() {
		return goodsQnaObj;
	}
	public void setGoodsQnaObj(GdGoodsQna goodsQnaObj) {
		this.goodsQnaObj = goodsQnaObj;
	}
	
	public String getSchword() {
		return schword;
	}
	public void setSchword(String schword) {
		this.schword = schword;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
	public String[] getCate() {
		return cate;
	}
	public void setCate(String[] cate) {
		this.cate = cate;
	}
	public String getCateVal() {
		return cateVal;
	}
	public void setCateVal(String cateVal) {
		this.cateVal = cateVal;
	}
	public String[] getSregdt() {
		return sregdt;
	}
	public void setSregdt(String[] sregdt) {
		this.sregdt = sregdt;
	}
	public String getSregdt_0() {
		if(sregdt != null){
			if(sregdt.length > 0){
				return sregdt[0];
			}
			return sregdt_0;
		} else {
			return sregdt_0;	
		}
	}
	public void setSregdt_0(String sregdt_0) {
		this.sregdt_0 = sregdt_0;
	}
	public String getSregdt_1() {
		if(sregdt != null){
			if(sregdt.length > 0){
				return sregdt[1];
			}
			return sregdt_1;
		} else {
			return sregdt_1;	
		}
	}
	public void setSregdt_1(String sregdt_1) {
		this.sregdt_1 = sregdt_1;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
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
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "GoodsQnaVO [sno=" + sno + ", goodsno=" + goodsno + ", parent="
				+ parent + ", subject=" + subject + ", contents=" + contents
				+ ", mno=" + mno + ", regdt=" + regdt + ", ip=" + ip
				+ ", name=" + name + ", password=" + password + ", sort="
				+ sort + ", skey=" + skey + ", sword=" + sword + ", cate="
				+ Arrays.toString(cate) + ", cateVal=" + cateVal + ", sregdt="
				+ Arrays.toString(sregdt) + ", sregdt_0=" + sregdt_0
				+ ", sregdt_1=" + sregdt_1 + ", totalCount=" + totalCount
				+ ", mode=" + mode + "]";
	}

}
