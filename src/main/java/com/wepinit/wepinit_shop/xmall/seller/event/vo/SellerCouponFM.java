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
package com.wepinit.wepinit_shop.xmall.seller.event.vo;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCouponApply;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCouponApplyMember;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMemberGrp;

import java.util.List;

public class SellerCouponFM extends PageMaker {
	
	private int totalCnt = 0;
	
	// 쿠폰 목록 search input
	private String mode;
	
	private String skey;		// 쿠폰검색(key)
	private String sword;		// 쿠폰검색(word)
	private String[] ability;	// 쿠폰기능 (할인,적립)
	private String goodstype;	// 적용상품범위
	private String[] cate;		// 적용상품범위 > 분류선택(카테고리) 
	private String category;	// 적용상품범위 > 분류선택(카테고리) 최종 선k택된 값
	private String[] categoryArr;
	private String gkey;		// 적용상품범위 > 분류선택(key)
	private String gword;		// 적용상품범위 > 분류선택(word)
	private String[] coupontype;// 쿠폰발급방식
	private String dtkind;		// 쿠폰발행일/기간검색(key)
	private String[] regdt;		// 쿠폰발행일/기간검색일자 
	private String regdt_s;		// 쿠폰발행일/기간검색시작
	private String regdt_e;		// 쿠폰발행일/기간검색종료
	
	private String regdts;
	private String regdte;

	// 쿠폰 발급/조회 inpit
	private int couponcd = 0;		// 쿠폰코드
	private String membertype;		// 발급멤버타입
	private String[] mids;			// 발급대상 멤버 ID
	private String membergrpsno;	// 발급그룹번호
	
	private String[] statusGroup;		//쿠폰사용가능 여부(0:사용가능, 1:사용불가능)
	private String sno;			//쿠폰 일련번호
	private String[] snoGroup;			//status 값 수정을 위한 쿠폰 일련번호 배열
	
	// 쿠폰만들기
	private String perc = "";		// 쿠폰금액 단위
	private String maxprice = "";	// 쿠폰 최대 할인액
	private String[] refer;			// 쿠폰발급상품 > 상품선정
	private String priod = "";		// 쿠폰적용기간 일수
	
	private String schSellerCd;	//판매사 코드 검색
	private String schSellerNm;	//판매사 명 검색

	private String schSellerYn;	
	private String schCouponcd;	
	private String schSort;	
	
	private String popView;	
	
	private String goodsno;
	
	//쿠폰 정보
	private SellerCouponVO couponVO;
	
	//쿠폰 목록
	private List<SellerCouponVO> couponList = null;
	
	// 쿠폰발급 - 전체회원수
	private int couponGrpTotal;
	private List<GdMemberGrp> couponGrpList = null;	// 쿠폰발급/조회 - 그룹조회
	private int couponApplyGrpTotal; 				// 해당 쿠폰을 발급받은 회원수
	private List<GdCouponApply> couponApplyGrpList = null;	// 쿠폰발급/조회 - 해당 쿠폰을 발급받은 회원리스트
	private List<GdCouponApplyMember> couponApplyMember = null;		// 쿠폰발급회원 내역리스트

	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
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
	public String[] getAbility() {
		return ability;
	}
	public void setAbility(String[] ability) {
		this.ability = ability;
	}
	public String getGoodstype() {
		return goodstype;
	}
	public void setGoodstype(String goodstype) {
		this.goodstype = goodstype;
	}
	public String[] getCate() {
		return cate;
	}
	public void setCate(String[] cate) {
		this.cate = cate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String[] getCategoryArr() {
		return categoryArr;
	}
	public void setCategoryArr(String[] categoryArr) {
		this.categoryArr = categoryArr;
	}
	public String getGkey() {
		return gkey;
	}
	public void setGkey(String gkey) {
		this.gkey = gkey;
	}
	public String getGword() {
		return gword;
	}
	public void setGword(String gword) {
		this.gword = gword;
	}
	public String[] getCoupontype() {
		return coupontype;
	}
	public void setCoupontype(String[] coupontype) {
		this.coupontype = coupontype;
	}
	public String getDtkind() {
		return dtkind;
	}
	public void setDtkind(String dtkind) {
		this.dtkind = dtkind;
	}
	public String[] getRegdt() {
		return regdt;
	}
	public void setRegdt(String[] regdt) {
		this.regdt = regdt;
	}
	public String getRegdt_s() {
		return regdt_s;
	}
	public void setRegdt_s(String regdt_s) {
		this.regdt_s = regdt_s;
	}
	public String getRegdt_e() {
		return regdt_e;
	}
	public void setRegdt_e(String regdt_e) {
		this.regdt_e = regdt_e;
	}
	public String getRegdts() {
		return regdts;
	}
	public void setRegdts(String regdts) {
		this.regdts = regdts;
	}
	public String getRegdte() {
		return regdte;
	}
	public void setRegdte(String regdte) {
		this.regdte = regdte;
	}
	public int getCouponcd() {
		return couponcd;
	}
	public void setCouponcd(int couponcd) {
		this.couponcd = couponcd;
	}
	public String getMembertype() {
		return membertype;
	}
	public void setMembertype(String membertype) {
		this.membertype = membertype;
	}
	public String[] getMids() {
		return mids;
	}
	public void setMids(String[] mids) {
		this.mids = mids;
	}
	public String getMembergrpsno() {
		return membergrpsno;
	}
	public void setMembergrpsno(String membergrpsno) {
		this.membergrpsno = membergrpsno;
	}
	public String[] getStatusGroup() {
		return statusGroup;
	}
	public void setStatusGroup(String[] statusGroup) {
		this.statusGroup = statusGroup;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String[] getSnoGroup() {
		return snoGroup;
	}
	public void setSnoGroup(String[] snoGroup) {
		this.snoGroup = snoGroup;
	}
	public String getPerc() {
		return perc;
	}
	public void setPerc(String perc) {
		this.perc = perc;
	}
	public String getMaxprice() {
		return maxprice;
	}
	public void setMaxprice(String maxprice) {
		this.maxprice = maxprice;
	}
	public String[] getRefer() {
		return refer;
	}
	public void setRefer(String[] refer) {
		this.refer = refer;
	}
	public String getPriod() {
		return priod;
	}
	public void setPriod(String priod) {
		this.priod = priod;
	}
	public String getSchSellerCd() {
		return schSellerCd;
	}
	public void setSchSellerCd(String schSellerCd) {
		this.schSellerCd = schSellerCd;
	}
	public String getSchSellerNm() {
		return schSellerNm;
	}
	public void setSchSellerNm(String schSellerNm) {
		this.schSellerNm = schSellerNm;
	}
	public String getSchSellerYn() {
		return schSellerYn;
	}
	public void setSchSellerYn(String schSellerYn) {
		this.schSellerYn = schSellerYn;
	}
	public String getSchCouponcd() {
		return schCouponcd;
	}
	public void setSchCouponcd(String schCouponcd) {
		this.schCouponcd = schCouponcd;
	}
	public String getSchSort() {
		return schSort;
	}
	public void setSchSort(String schSort) {
		this.schSort = schSort;
	}
	public String getPopView() {
		return popView;
	}
	public void setPopView(String popView) {
		this.popView = popView;
	}
	public String getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}
	public SellerCouponVO getCouponVO() {
		return couponVO;
	}
	public void setCouponVO(SellerCouponVO couponVO) {
		this.couponVO = couponVO;
	}
	public List<SellerCouponVO> getCouponList() {
		return couponList;
	}
	public void setCouponList(List<SellerCouponVO> couponList) {
		this.couponList = couponList;
	}
	public int getCouponGrpTotal() {
		return couponGrpTotal;
	}
	public void setCouponGrpTotal(int couponGrpTotal) {
		this.couponGrpTotal = couponGrpTotal;
	}
	public List<GdMemberGrp> getCouponGrpList() {
		return couponGrpList;
	}
	public void setCouponGrpList(List<GdMemberGrp> couponGrpList) {
		this.couponGrpList = couponGrpList;
	}
	public int getCouponApplyGrpTotal() {
		return couponApplyGrpTotal;
	}
	public void setCouponApplyGrpTotal(int couponApplyGrpTotal) {
		this.couponApplyGrpTotal = couponApplyGrpTotal;
	}
	public List<GdCouponApply> getCouponApplyGrpList() {
		return couponApplyGrpList;
	}
	public void setCouponApplyGrpList(List<GdCouponApply> couponApplyGrpList) {
		this.couponApplyGrpList = couponApplyGrpList;
	}
	public List<GdCouponApplyMember> getCouponApplyMember() {
		return couponApplyMember;
	}
	public void setCouponApplyMember(List<GdCouponApplyMember> couponApplyMember) {
		this.couponApplyMember = couponApplyMember;
	}
}
