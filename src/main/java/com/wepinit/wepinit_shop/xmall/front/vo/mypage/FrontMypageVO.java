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
* 업무구분명			:	Community
* 세부업무구분명	: 	사용자 > Community
* 작성자 				: 	이병환
* 설명 				: 	사용자 Community VO
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170208		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.vo.mypage;

import com.wepinit.wepinit_shop.xmall.common.CommonVO;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.*;
import com.wepinit.wepinit_shop.xmall.front.vo.goods.FrontGoodsCartsInnerVO;

import java.util.List;


public class FrontMypageVO extends CommonVO{
	//input(내쇼핑정보>주문내역/배송조회)
	private String ordno;		//주문번호
	private String sno;			//일련번호whpark 20191111
	
	private String code;		//취소사유코드(cancel)
	private String mode;		//주문상태변경
	private String sear;		//주문상태
	private String sear2;		//주문기간
	
	private String bankcode;	//환불은행코드
	private String bankaccount;	//환불은행계좌번호
	private String bankuser;	//환불계좌예금주
	private String memo;		//상세사유
	private String usrNm;		// 회원명
	private String settlekind;	// 결제종류
	private String opt;	// 옵션번호
	private String returnDeliveryCompCd; // 반품 택배사
	private String returnInvoice;	// 반품 송장번호
	
	//output
	private GdMember frontMember = null;		//회원정보
	private GdMemberGrp frontMemberGrp = null;	//회원등급정보
	private GdMemberGrp frontMemberMaxGrp = null;	//최고등급정보
	private GdMemberGrp frontMemberNxtGrp = null;	//다음등급정보
	private int frontCouponCount = 0;													//쿠폰갯수
	private List<CouponapplyApplymemberCouponCategoryGoodsno> frontCouponList = null;	//쿠폰목록
	private int frontReviewCount = 0;							//리뷰갯수
	private List<MemberGoodsrevwGoods> frontReviewList = null;	//리뷰목록
	private int frontWishCount = 0;										//위시갯수
	private List<MemberwishlistGoodsGoodsoption> frontWishList = null;	//위시리스트 
	private int frontGoodsQnaCount = 0;					//문의갯수
	private List<GdGoodsQna> frontGoodsQnaList = null;	//문의내역
	private List<FrontGoodsCartsInnerVO> frontGoodsCartList = null;	//장바구니 
	
	//output(취소화면 물품정보
	private List<GdOrderItem> frontOrderItemObj = null;
	//frontOrderItemObj 길이
	private int frontOrderItemObjLength = 0;
	//output(내쇼핑정보 > 주문내역/배송조회)
	private List<OrderMember> frontOrderList = null;
	//output(내쇼핑정보 > 주문내역/배송조회 , 글상세[상품정보])
	private List<OrderitemGoods> frontOrderViewList = null;
	//output(내쇼핑정보 > 주문내역/배송조회 , 글상세[주문내역])
	private List<GdOrder> frontOrderViewObj = null;
	//output(내쇼핑정보 > 주문내역/배송조회 , 글상세[주문자정보])
	private GdMember frontOrderViewAddressObj = null;
	//output(내쇼핑정보 > 적립금내역)
	private List<GdLogEmoney> frontEmoneyList = null;
	
	public String getOrdno() {
		return ordno;
	}
	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getSear() {
		return sear;
	}
	public void setSear(String sear) {
		this.sear = sear;
	}
	public String getSear2() {
		return sear2;
	}
	public void setSear2(String sear2) {
		this.sear2 = sear2;
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getUsrNm() {
		return usrNm;
	}
	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}
	public GdMember getFrontMember() {
		return frontMember;
	}
	public void setFrontMember(GdMember frontMember) {
		this.frontMember = frontMember;
	}
	public GdMemberGrp getFrontMemberGrp() {
		return frontMemberGrp;
	}
	public void setFrontMemberGrp(GdMemberGrp frontMemberGrp) {
		this.frontMemberGrp = frontMemberGrp;
	}
	public GdMemberGrp getFrontMemberMaxGrp() {
		return frontMemberMaxGrp;
	}
	public void setFrontMemberMaxGrp(GdMemberGrp frontMemberMaxGrp) {
		this.frontMemberMaxGrp = frontMemberMaxGrp;
	}
	public GdMemberGrp getFrontMemberNxtGrp() {
		return frontMemberNxtGrp;
	}
	public void setFrontMemberNxtGrp(GdMemberGrp frontMemberNxtGrp) {
		this.frontMemberNxtGrp = frontMemberNxtGrp;
	}
	public List<GdOrderItem> getFrontOrderItemObj() {
		return frontOrderItemObj;
	}
	public void setFrontOrderItemObj(List<GdOrderItem> frontOrderItemObj) {
		this.frontOrderItemObj = frontOrderItemObj;
		this.frontOrderItemObjLength = frontOrderItemObj.size();
	}
	public int getFrontOrderItemObjLength() {
		return frontOrderItemObjLength;
	}
	public List<OrderMember> getFrontOrderList() {
		return frontOrderList;
	}
	public void setFrontOrderList(List<OrderMember> frontOrderList) {
		this.frontOrderList = frontOrderList;
	}
	public List<OrderitemGoods> getFrontOrderViewList() {
		return frontOrderViewList;
	}
	public void setFrontOrderViewList(List<OrderitemGoods> frontOrderViewList) {
		this.frontOrderViewList = frontOrderViewList;
	}
	public List<GdOrder> getFrontOrderViewObj() {
		return frontOrderViewObj;
	}
	public void setFrontOrderViewObj(List<GdOrder> frontOrderViewObj) {
		this.frontOrderViewObj = frontOrderViewObj;
	}
	public GdMember getFrontOrderViewAddressObj() {
		return frontOrderViewAddressObj;
	}
	public void setFrontOrderViewAddressObj(GdMember frontOrderViewAddressObj) {
		this.frontOrderViewAddressObj = frontOrderViewAddressObj;
	}
	public List<GdLogEmoney> getFrontEmoneyList() {
		return frontEmoneyList;
	}
	public void setFrontEmoneyList(List<GdLogEmoney> frontEmoneyList) {
		this.frontEmoneyList = frontEmoneyList;
	}
	public int getFrontCouponCount() {
		return frontCouponCount;
	}
	public void setFrontCouponCount(int frontCouponCount) {
		this.frontCouponCount = frontCouponCount;
	}
	public void setFrontOrderItemObjLength(int frontOrderItemObjLength) {
		this.frontOrderItemObjLength = frontOrderItemObjLength;
	}
	public List<CouponapplyApplymemberCouponCategoryGoodsno> getFrontCouponList() {
		return frontCouponList;
	}
	public void setFrontCouponList(List<CouponapplyApplymemberCouponCategoryGoodsno> frontCouponList) {
		this.frontCouponList = frontCouponList;
	}
	public int getFrontReviewCount() {
		return frontReviewCount;
	}
	public void setFrontReviewCount(int frontReviewCount) {
		this.frontReviewCount = frontReviewCount;
	}
	public List<MemberGoodsrevwGoods> getFrontReviewList() {
		return frontReviewList;
	}
	public void setFrontReviewList(List<MemberGoodsrevwGoods> frontReviewList) {
		this.frontReviewList = frontReviewList;
	}
	public int getFrontWishCount() {
		return frontWishCount;
	}
	public void setFrontWishCount(int frontWishCount) {
		this.frontWishCount = frontWishCount;
	}
	public List<MemberwishlistGoodsGoodsoption> getFrontWishList() {
		return frontWishList;
	}
	public void setFrontWishList(List<MemberwishlistGoodsGoodsoption> frontWishList) {
		this.frontWishList = frontWishList;
	}
	public int getFrontGoodsQnaCount() {
		return frontGoodsQnaCount;
	}
	public void setFrontGoodsQnaCount(int frontGoodsQnaCount) {
		this.frontGoodsQnaCount = frontGoodsQnaCount;
	}
	public List<GdGoodsQna> getFrontGoodsQnaList() {
		return frontGoodsQnaList;
	}
	public void setFrontGoodsQnaList(List<GdGoodsQna> frontGoodsQnaList) {
		this.frontGoodsQnaList = frontGoodsQnaList;
	}
	public List<FrontGoodsCartsInnerVO> getFrontGoodsCartList() {
		return frontGoodsCartList;
	}
	public void setFrontGoodsCartList(List<FrontGoodsCartsInnerVO> frontGoodsCartList) {
		this.frontGoodsCartList = frontGoodsCartList;
	}
	public String getSettlekind() {
		return settlekind;
	}
	public void setSettlekind(String settlekind) {
		this.settlekind = settlekind;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public String getReturnDeliveryCompCd() {
		return returnDeliveryCompCd;
	}
	public void setReturnDeliveryCompCd(String returnDeliveryCompCd) {
		this.returnDeliveryCompCd = returnDeliveryCompCd;
	}
	public String getReturnInvoice() {
		return returnInvoice;
	}
	public void setReturnInvoice(String returnInvoice) {
		this.returnInvoice = returnInvoice;
	}
	@Override
	public String toString() {
		return "FrontMypageVO [ordno=" + ordno + ",sno="+sno+" ,code=" + code + ", mode="
				+ mode + ", sear=" + sear + ", sear2=" + sear2 + ", bankcode="
				+ bankcode + ", bankaccount=" + bankaccount + ", bankuser="
				+ bankuser + ", memo=" + memo + ", frontOrderItemObj="
				+ frontOrderItemObj + ", frontOrderList=" + frontOrderList
				+ ", frontOrderViewList=" + frontOrderViewList
				+ ", frontOrderViewObj=" + frontOrderViewObj
				+ ", frontOrderViewAddressObj=" + frontOrderViewAddressObj
				+ ", frontEmoneyList=" + frontEmoneyList + ", frontCouponList="
				+ frontCouponList + "]";
	}
}
