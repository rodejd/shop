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
package com.wepinit.wepinit_shop.xmall.admin.dao.event;

import com.wepinit.wepinit_shop.xmall.admin.vo.event.CouponVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.event.EventVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.event.SurveyVO;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface EventMapper {
	
	/*
	 * 1.이벤트
	 * 
	 * */
	
	//리스트건수
	public int getEventCount(EventVO eventVO);
	
	//리스트조회
	public List<GdEvent> getEventList(Map<String, Object> param);
	
	//리스트삭제
	public void deleteEvent(EventVO eventVO);
	
	//리스트 업데이트
	public void updateEvent(EventVO eventVO);
	
	//리스트 추가
	public void insertEvent(EventVO eventVO);
	
	//리스트상세
	public GdEvent detailEvent(EventVO eventVO);

	
	/** 관리자 > 이벤트/쿠폰관리 > 쿠폰 **/
	// 쿠폰리스트건수
	public int getCouponCount(Map<String, Object> param);
	
	// 쿠폰리스트조회
	public List<GdCoupon> getCouponList(Map<String, Object> param);
	
	// 쿠폰발급/조회 - 전체회원수
	public int getCouponApply1TotalCount();

	// 쿠폰발급/조회 - 그룹조회
	public List<GdMemberGrp> getCouponApply1();
	
	// 쿠폰발급/조회 - 쿠폰 단건 조회
	public GdCoupon getCouponApply2(Map<String, Object> param);
	
	// 쿠폰발급/조회 - 해당 쿠폰을 발급받은 회원수
	public int getCouponMemberCount(Map<String, Object> param);
	
	// 쿠폰발급/조회 - 해당 쿠폰을 발급받은 회원리스트
	public List<GdCouponApply> getCouponMemberNew(Map<String, Object> param);
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
	public String[] getCouponCategory(Map<String, Object> param);
	
	// 쿠폰만들기 - 쿠폰 상품조회
	public List<GdCouponApply> getCouponGoods(Map<String, Object> param);
	
	// 쿠폰만들기 - 쿠폰등록
	public void insertCoupon(CouponVO couponVO);
	
	// 쿠폰만들기 - 쿠폰수정
	public void updateCoupon(CouponVO couponVO);
	
	// 쿠폰만들기 - 쿠폰등록처리 > 등록된 쿠폰번호취득
	public int getNewCouponcd();
	
	// 쿠폰 이미지 업데이트
	public int updateCouponImage(CouponVO couponVO);
	
	// 쿠폰만들기 - 쿠폰등록처리 > delete gd_coupon_goodsno
	public void deleteCouponGoodsno(Map<String, Object> param);
	
	// 쿠폰만들기 - 쿠폰등록처리 > delete gd_coupon_category
	public void deleteCouponCategory(Map<String, Object> param);
	
	// 쿠폰만들기 - 쿠폰등록처리 > insert gd_coupon_goodsno
	public void insertCouponGoodsno(Map<String, Object> param);
	
	// 쿠폰만들기 - 쿠폰등록처리 > insert gd_coupon_category
	public void insertCouponGoodCategory(Map<String, Object> param);
	
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
	public List<GdCouponApplyMember> getCouponApplyMember(Map<String, Object> param);
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
	
	// 설문조사 등록
	public void setSurvey(SurveyVO surveyVO);
	
	//설문조사 질문 등록
	public void setSurveyQuestion(SurveyVO surveyVO);
	
	public int getSurveyCount(SurveyVO surveyVO);
	
	public List<SurveyVO> getSurveyList(SurveyVO surveyVO);
	
	public void updateSurveyOpen(SurveyVO surveyVO);
	
	public void deleteSurvey(String sno);
	public void deleteSurveyQuestion(String sno);

	public SurveyVO getDetailSurvey(String sno);
	
	public void modifySurvey(SurveyVO surveyVO);
}
