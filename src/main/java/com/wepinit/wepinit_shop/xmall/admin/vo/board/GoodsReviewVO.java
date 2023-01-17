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
package com.wepinit.wepinit_shop.xmall.admin.vo.board;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsReview;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import com.wepinit.wepinit_shop.xmall.common.vo.join.MemberGoodsrevwGoods;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GoodsReviewVO extends PageMaker {
	
	private int sno;
	private int goodsno;
	private String subject;
	private String contents;
	private int poitn;
	private int emoney;
	private int mno;
	private Date regdt;
	private String ip;
	private String name;
	private String password;
	private int parent;
	
	private int totalCount;		//총 건수
	private String mode;		//mode
	
	private String sort;			//정렬
	private String skey;		//검색 Key
	private String sword;		//검색 Value
	
	private String[] cate;		//분류선택
	private String cateVal;
	private String[] sregdt;	//등록일
	private String sregdt_0;
	private String sregdt_1;
	
	private String schword = "";	//검색어 쿼리문
	
	private String nolist;			//선택된 NUM LIST
	private String btype;	//타입(P:포토 / B:게시판)
	
	private Map<String, Object> goodsObj;	//상품정보(getGoodsReviewGoodsInfo 결과 값)
	
	private List<MemberGoodsrevwGoods> goodsRevwList;
	private GdGoodsReview goodsRevwObj;
	private List<GdMember> memberList;		//답변관리자 권한 회원 리스트
	private GdMember memberObj;			//회원정보
	
	private String schSellerNm = "";	// 조회 판매사명
	
	
	public String getSchSellerNm() {
		return schSellerNm;
	}
	public void setSchSellerNm(String schSellerNm) {
		this.schSellerNm = schSellerNm;
	}
	public GdMember getMemberObj() {
		return memberObj;
	}
	public void setMemberObj(GdMember memberObj) {
		this.memberObj = memberObj;
	}
	public List<GdMember> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<GdMember> memberList) {
		this.memberList = memberList;
	}
	public Map<String, Object> getGoodsObj() {
		return goodsObj;
	}
	public void setGoodsObj(Map<String, Object> goodsObj) {
		this.goodsObj = goodsObj;
	}
	public GdGoodsReview getGoodsRevwObj() {
		return goodsRevwObj;
	}
	public void setGoodsRevwObj(GdGoodsReview goodsRevwObj) {
		this.goodsRevwObj = goodsRevwObj;
	}
	public List<MemberGoodsrevwGoods> getGoodsRevwList() {
		return goodsRevwList;
	}
	public void setGoodsRevwList(List<MemberGoodsrevwGoods> goodsRevwList) {
		this.goodsRevwList = goodsRevwList;
	}
	
	
	public String getCateVal() {
		return cateVal;
	}
	public void setCateVal(String cateVal) {
		this.cateVal = cateVal;
	}
	public String getNolist() {
		return nolist;
	}
	public void setNolist(String nolist) {
		this.nolist = nolist;
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
		}
		return sregdt_0;
	}
	public void setSregdt_0(String sregdt_0) {
		this.sregdt_0 = sregdt_0;
	}
	public String getSregdt_1() {
		if(sregdt != null) {
			if(sregdt.length > 0){
				return sregdt[1];
			}
			return sregdt_1;
		}
		return sregdt_1;
	}
	public void setSregdt_1(String sregdt_1) {
		this.sregdt_1 = sregdt_1;
	}
	
	public String getSchword() {
		return schword;
	}
	public void setSchword(String schword) {
		this.schword = schword;
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
	public String getSubject() {
		if(subject == null){
			subject = "";
		}
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
	public int getPoitn() {
		return poitn;
	}
	public void setPoitn(int poitn) {
		this.poitn = poitn;
	}
	public int getEmoney() {
		return emoney;
	}
	public void setEmoney(int emoney) {
		this.emoney = emoney;
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
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public String getBtype() {
		return btype;
	}
	public void setBtype(String btype) {
		this.btype = btype;
	}
	@Override
	public String toString() {
		return "GoodsReviewVO [sno=" + sno + ", goodsno=" + goodsno
				+ ", subejct=" + subject + ", contents=" + contents
				+ ", poitn=" + poitn + ", emoney=" + emoney + ", mno=" + mno
				+ ", regdt=" + regdt + ", ip=" + ip + ", name=" + name
				+ ", password=" + password + ", parent=" + parent
				+ ", totalCount=" + totalCount + ", mode=" + mode + ", sort="
				+ sort + ", skey=" + skey + ", sword=" + sword + ", cate="
				+ Arrays.toString(cate) + ", sregdt=" + Arrays.toString(sregdt)
				+ ", sregdt_0=" + sregdt_0 + ", sregdt_1=" + sregdt_1
				+ ", schword=" + schword + ", goodsRevwList=" + goodsRevwList
				+ "]";
	}

}
