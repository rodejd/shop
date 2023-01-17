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
package com.wepinit.wepinit_shop.xmall.seller.event.dao;

import com.wepinit.wepinit_shop.xmall.common.vo.GdCouponApply;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCouponApplyMember;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMemberGrp;
import com.wepinit.wepinit_shop.xmall.seller.event.vo.SellerCouponFM;
import com.wepinit.wepinit_shop.xmall.seller.event.vo.SellerCouponVO;
import com.wepinit.wepinit_shop.xmall.seller.event.vo.SellerEventFM;
import com.wepinit.wepinit_shop.xmall.seller.event.vo.SellerEventVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface SellerEventMapper {
	
	/*
	 * 1.이벤트
	 * 
	 * */
	
	//리스트건수
	public int getEventCount(SellerEventFM eventFM);
	
	//리스트조회
	public List<SellerEventVO> getEventList(SellerEventFM eventFM);
	
	//리스트 업데이트
	public void updateEvent(SellerEventVO eventVO);
	
	//리스트 추가
	public void insertEvent(SellerEventVO eventVO);
	
	//리스트상세
	public SellerEventVO detailEvent(SellerEventFM sellerEventFM);

	
	/** 관리자 > 이벤트/쿠폰관리 > 쿠폰 **/
	// 쿠폰리스트건수
	public int getCouponCount(SellerCouponFM couponFM);
	
	// 쿠폰리스트조회
	public List<SellerCouponVO> getCouponList(SellerCouponFM couponFM);
	
	// 쿠폰발급/조회 - 전체회원수
	public int getCouponApply1TotalCount();

	// 쿠폰발급/조회 - 그룹조회
	public List<GdMemberGrp> getCouponApply1();
	
	// 쿠폰발급/조회 - 쿠폰 단건 조회
	public SellerCouponVO getCouponApply2(SellerCouponFM sellerCouponFM);
	
	// 쿠폰발급/조회 - 해당 쿠폰을 발급받은 회원수
	public int getCouponMemberCount(SellerCouponFM couponFM);
	
	// 쿠폰발급/조회 - 해당 쿠폰을 발급받은 회원리스트
	public List<GdCouponApply> getCouponMemberNew(SellerCouponFM couponFM);
//	public List<GdCouponApply> getCouponMember1(Map<String, Object> param);
	// 쿠폰발급/조회 - 개별로 발급 받은 회원정보
//	public GdCouponApply getCouponMember2(Map<String, Object> param);
	
	// 쿠폰발급/조회 - 등록처리 > coupon_apply max count
	public int getCouponApplyMax(Map<String, Object> param);
	
	// 쿠폰발급/조회 - 등록처리 > insert coupon_apply
	public int insertCouponApply(Map<String, Object> param);
	
	// 쿠폰발급/조회 - 등록처리 > delete coupon_applymember
	public int deleteCouponApplyMember(Map<String, Object> param);
	
	// 쿠폰발급/조회 - 등록처리 > insert coupon_applymember
	public int insertCouponApplyMember(Map<String, Object> param);
	
	// 쿠폰발급/조회 - 삭제처리 > delete coupon_apply, coupon_applymember
	public int deleteCouponApply(Map<String, Object> param);
	
	// 쿠폰만들기 - 쿠폰카테고리 조회
	public String[] getCouponCategory(SellerCouponFM sellerCouponFM);
	
	// 쿠폰만들기 - 쿠폰 상품조회
	public List<GdCouponApply> getCouponGoods(SellerCouponFM sellerCouponFM);
	
	// 쿠폰만들기 - 쿠폰등록
	public void insertCoupon(SellerCouponVO vo);
	
	// 쿠폰만들기 - 쿠폰수정
	public void updateCoupon(SellerCouponVO vo);
	
	// 쿠폰만들기 - 쿠폰등록처리 > 등록된 쿠폰번호취득
	public int getNewCouponcd();
	
	// 쿠폰만들기 - 쿠폰등록처리 > delete gd_coupon_goodsno
	public void deleteCouponGoodsno(SellerCouponFM paramVO);
	
	// 쿠폰만들기 - 쿠폰등록처리 > delete gd_coupon_category
	public void deleteCouponCategory(SellerCouponFM paramVO);
	
	// 쿠폰만들기 - 쿠폰등록처리 > insert gd_coupon_goodsno
	public void insertCouponGoodsno(SellerCouponFM paramVO);
	
	// 쿠폰만들기 - 쿠폰등록처리 > insert gd_coupon_category
	public void insertCouponGoodCategory(SellerCouponFM paramVO);
	
	// 쿠폰삭제 > delete gd_coupon_applymember
	public void deleteCouponApplymemberByCoupon(Map<String, Object> param);
	
	// 쿠폰삭제 > delete gd_coupon
	public void deleteCoupon(Map<String, Object> param);
	
	/* 2017-09-05 추가 */
	// 그룹 회원정보 조회
	@SuppressWarnings("rawtypes")
	public List getGroupMember(Map<String, Object> param);
	// 전체 회원정보 조회
	@SuppressWarnings("rawtypes")
	public List getTotalMember(Map<String, Object> param);
	// 쿠폰발급회원 내역 조회
	public List<GdCouponApplyMember> getCouponApplyMember(SellerCouponFM sellerCouponFM);
	// 쿠폰발급회원 건수 조회
	public int getCouponApplyMemberCount(String sno);
	// 발급쿠폰 사용가능여부 발급내역 수정
	public void updateCouponApply(Map<String, Object> param);
	// 발급쿠폰 사용가능여부 회원별발급내역 수정
	public void updateCouponApplyMember(Map<String, Object> param);

	/* 2017-11-16 추가 */
	//판매사 게시여부 수정
	@SuppressWarnings("rawtypes")
	public void updateOpen(HashMap paramsDb);
	//판매사 승인여부 수정
	@SuppressWarnings("rawtypes")
	public void updateApproval(HashMap paramsDb);
	//판매사 게시여부 수정(쿠폰)
	@SuppressWarnings("rawtypes")
	public void updateCouponOpen(HashMap paramsDb);
	//판매사 승인여부 수정(쿠폰)
	@SuppressWarnings("rawtypes")
	public void updateCouponApproval(HashMap paramsDb);
	
}
