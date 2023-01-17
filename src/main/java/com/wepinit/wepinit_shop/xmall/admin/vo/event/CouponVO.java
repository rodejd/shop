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
package com.wepinit.wepinit_shop.xmall.admin.vo.event;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCoupon;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCouponApply;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCouponApplyMember;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMemberGrp;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class CouponVO extends PageMaker {

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
	private String schSort;		// 정렬

	// 쿠폰 발급/조회 inpit
	private String couponcd;		// 쿠폰코드
	private String membertype;		// 발급멤버타입
	private String[] mids;			// 발급대상 멤버 ID
	private String membergrpsno;	// 발급그룹번호
	
	//2017-09-05 추가 - 쿠폰 사용가능 여부
	private String[] statusGroup;		//쿠폰사용가능 여부(0:사용가능, 1:사용불가능)
	private String sno;			//쿠폰 일련번호
	private String[] snoGroup;			//status 값 수정을 위한 쿠폰 일련번호 배열
	
	// 쿠폰만들기
	private String couponname;	// 쿠폰이름
	private String summa;		// 쿠폰설명
	private String coupontypesel;	// 쿠폰발급방식 선택
	private String abilitysel;		// 쿠폰기능 선택
	private String price = "";		// 쿠폰금액
	private String perc = "";		// 쿠폰금액 단위
	private String maxprice = "";	// 쿠폰 최대 할인액
	private String[] refer;			// 쿠폰발급상품 > 상품선정
	private String couponimg = "";	// 쿠폰이미지
	private MultipartFile imgFile;	// 쿠폰이미지파일
	private String priodtype = "";	// 쿠폰적용기간 구분
	private String sdate = "";		// 쿠폰적용기간 시작일
	private String edate = "";		// 쿠폰적용기간 종료일
	private String priod = "";		// 쿠폰적용기간 일수
	private String excPrice = "0";	// 쿠폰사용제한
	private String eactl = "";		// 쿠폰상품적용여부 - 사용안함
	private String duplctl = "";	// 같은상품쿠폰다운로드허용여부 - 사용안함
	private String dncnt;			// 쿠폰다운로드횟수
	private String edncnt;			// 같은상품쿠폰다운로드횟수
	
	private String sellercode;	//판매사 코드
	private String sellername;	//판매사 명
	
	private int open;	//쿠폰출력여부
	private String approvalstatus = "";	//쿠폰승인상태
	private String memo = "";	//메모
	private String dncode = "";	//할인코드
	
	private String schSellerCd;	//판매사 코드 검색
	private String schSellerNm;	//판매사 명 검색
	

	private String myritzcode;	//판매사 코드
	private String myritzname;	//판매사 명
	private String schMyritzCd;	//판매사 코드 검색
	private String schMyritzNm;	//판매사 명 검색
	
	
	
	public String getMyritzcode() {
		return myritzcode;
	}
	public void setMyritzcode(String myritzcode) {
		this.myritzcode = myritzcode;
	}
	public String getMyritzname() {
		return myritzname;
	}
	public void setMyritzname(String myritzname) {
		this.myritzname = myritzname;
	}
	public String getSchMyritzCd() {
		return schMyritzCd;
	}
	public void setSchMyritzCd(String schMyritzCd) {
		this.schMyritzCd = schMyritzCd;
	}
	public String getSchMyritzNm() {
		return schMyritzNm;
	}
	public void setSchMyritzNm(String schMyritzNm) {
		this.schMyritzNm = schMyritzNm;
	}
	// output
	private List<GdCoupon> couponList = null;		// 쿠폰리스트에서 사용

	// 쿠폰발급/조회
	private int couponGrpTotal; 					// 쿠폰발급 - 전체회원수
	private List<GdMemberGrp> couponGrpList = null;	// 쿠폰발급/조회 - 그룹조회
	private GdCoupon couponInfo = null; 			// 쿠폰발급/조회 - 쿠폰 단건 조회
	private int couponApplyGrpTotal; 				// 해당 쿠폰을 발급받은 회원수
	private List<GdCouponApply> couponApplyGrpList = null;	// 쿠폰발급/조회 - 해당 쿠폰을 발급받은 회원리스트
	private GdCouponApply couponApplyInfo = null; 			// 쿠폰발급/조회 - 개별로 발급 받은 회원정보
	private List<GdCouponApplyMember> couponApplyMember = null;		// 쿠폰발급회원 내역리스트
	
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
	public String getSchSort() {
		return schSort;
	}
	public void setSchSort(String schSort) {
		this.schSort = schSort;
	}
	public String getCouponcd() {
		return couponcd;
	}
	public void setCouponcd(String couponcd) {
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
	public String getCouponname() {
		return couponname;
	}
	public void setCouponname(String couponname) {
		this.couponname = couponname;
	}
	public String getSumma() {
		return summa;
	}
	public void setSumma(String summa) {
		this.summa = summa;
	}
	public String getCoupontypesel() {
		return coupontypesel;
	}
	public void setCoupontypesel(String coupontypesel) {
		this.coupontypesel = coupontypesel;
	}
	public String getAbilitysel() {
		return abilitysel;
	}
	public void setAbilitysel(String abilitysel) {
		this.abilitysel = abilitysel;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
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
	public String getCouponimg() {
		return couponimg;
	}
	public void setCouponimg(String couponimg) {
		this.couponimg = couponimg;
	}
	public MultipartFile getImgFile() {
		return imgFile;
	}
	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}
	public String getPriodtype() {
		return priodtype;
	}
	public void setPriodtype(String priodtype) {
		this.priodtype = priodtype;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public String getPriod() {
		return priod;
	}
	public void setPriod(String priod) {
		this.priod = priod;
	}
	public String getExcPrice() {
		return excPrice;
	}
	public void setExcPrice(String excPrice) {
		this.excPrice = excPrice;
	}
	public String getEactl() {
		return eactl;
	}
	public void setEactl(String eactl) {
		this.eactl = eactl;
	}
	public String getDuplctl() {
		return duplctl;
	}
	public void setDuplctl(String duplctl) {
		this.duplctl = duplctl;
	}
	public String getDncnt() {
		return dncnt;
	}
	public void setDncnt(String dncnt) {
		this.dncnt = dncnt;
	}
	public String getEdncnt() {
		return edncnt;
	}
	public void setEdncnt(String edncnt) {
		this.edncnt = edncnt;
	}
	public String getSellercode() {
		return sellercode;
	}
	public void setSellercode(String sellercode) {
		this.sellercode = sellercode;
	}
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	public int getOpen() {
		return open;
	}
	public void setOpen(int open) {
		this.open = open;
	}
	public String getApprovalstatus() {
		return approvalstatus;
	}
	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
	public List<GdCoupon> getCouponList() {
		return couponList;
	}
	public void setCouponList(List<GdCoupon> couponList) {
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
	public GdCoupon getCouponInfo() {
		return couponInfo;
	}
	public void setCouponInfo(GdCoupon couponInfo) {
		this.couponInfo = couponInfo;
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
	public GdCouponApply getCouponApplyInfo() {
		return couponApplyInfo;
	}
	public void setCouponApplyInfo(GdCouponApply couponApplyInfo) {
		this.couponApplyInfo = couponApplyInfo;
	}
	public List<GdCouponApplyMember> getCouponApplyMember() {
		return couponApplyMember;
	}
	public void setCouponApplyMember(List<GdCouponApplyMember> couponApplyMember) {
		this.couponApplyMember = couponApplyMember;
	}
	public String getDncode() {
		return dncode;
	}
	public void setDncode(String dncode) {
		this.dncode = dncode;
	}
	@Override
	public String toString() {
		return "CouponVO [mode=" + mode + ", skey=" + skey + ", sword=" + sword
				+ ", ability=" + Arrays.toString(ability) + ", goodstype="
				+ goodstype + ", cate=" + Arrays.toString(cate) + ", category="
				+ category + ", categoryArr=" + Arrays.toString(categoryArr)
				+ ", gkey=" + gkey + ", gword=" + gword + ", coupontype="
				+ Arrays.toString(coupontype) + ", dtkind=" + dtkind
				+ ", regdt=" + Arrays.toString(regdt) + ", regdt_s=" + regdt_s
				+ ", regdt_e=" + regdt_e + ", couponcd=" + couponcd + ", schSort=" + schSort
				+ ", membertype=" + membertype + ", mids="
				+ Arrays.toString(mids) + ", membergrpsno=" + membergrpsno
				+ ", couponname=" + couponname + ", summa=" + summa
				+ ", coupontypesel=" + coupontypesel + ", abilitysel="
				+ abilitysel + ", price=" + price + ", perc=" + perc + ", maxprice=" + maxprice
				+ ", refer=" + Arrays.toString(refer) + ", couponimg="
				+ couponimg + ", imgFile=" + imgFile + ", priodtype=" + priodtype + ", sdate=" + sdate
				+ ", edate=" + edate + ", priod=" + priod + ", excPrice="
				+ excPrice + ", eactl=" + eactl + ", duplctl=" + duplctl
				+ ", dncnt=" + dncnt + ", edncnt=" + edncnt + ", couponList="
				+ couponList + ", couponGrpTotal=" + couponGrpTotal
				+ ", couponGrpList=" + couponGrpList + ", couponInfo="
				+ couponInfo + ", couponApplyGrpTotal=" + couponApplyGrpTotal
				+ ", couponApplyGrpList=" + couponApplyGrpList
				+ ", couponApplyInfo=" + couponApplyInfo
				+ ", open=" + open
				+ ", approvalstatus=" + approvalstatus
				+ ", memo=" + memo
				+ ", sellercode=" + sellercode
				+ ", sellername=" + sellername + "]";
	}
}
