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
* 설명 				: 	사용자 Community dao
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170208		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.dao.goods;

import com.wepinit.wepinit_shop.xmall.common.UserInfo;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.*;
import com.wepinit.wepinit_shop.xmall.front.vo.goods.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface FrontGoodsMapper {
	
	/**
	 * 브랜드목록
	 * @return
	 */
	public int getGdGoodsBrandListCount(FrontGoodsBrandVO frontGoodsBrandVO);
	public List<GdGoodsBrand> getGdGoodsBrandList(FrontGoodsBrandVO frontGoodsBrandVO);
	public GdGoodsBrand getGdGoodsBrandObj(FrontGoodsVO frontGoodsVO);
	public XmCategoryBrandInfo getXmCategoryBrandInfo(FrontGoodsVO frontGoodsVO);
	public int getFrontGoodsBrandCount(Map<String, Object> param);
	public List<GdGoodsGoodsoptionGoodslink> getFrontGoodsBrandList(Map<String, Object> param);
	
	/**
	 * 카테고리목록
	 * @return
	 */
	public int getFrontGoodsGoodsCount(Map<String, Object> param);
	public List<GdGoodsGoodsoptionGoodslinkGoodsbrand> getFrontGoodsGoodsList(Map<String, Object> param);
	public List<GdCategory> getFrontGoodsGoodsCategoryList(Map<String, Object> param);
	public GdCategory getFrontGoodsGoodsCategoryInfo(Map<String, Object> param);
	public List<GdGoodsBrand> getFrontGoodsGoodsBrandList(Map<String, Object> param);
	
	/**
	 * 상품번호로 대표카테고리 조회
	 * @param goodsno
	 * @return
	 */
	public GdCategory getFrontGoodsGoodsCategoryByGoodsno(String goodsno);
	
	/**
	 * 메인 상품 목록 조회
	 * @param frontGoodsVO
	 * @return
	 */
	public int getFrontMainGoodsTotCnt(Map<String, Object> param);
	public List<GdGoodsGoodsoptionGoodslink> getFrontMainGoodsList(Map<String, Object> param);
	
	/**
	 * 상품 목록 조회
	 * @param frontGoodsVO
	 * @return
	 */
	public int getFrontGoodsTotCnt(Map<String, Object> param);
	public List<GdGoodsGoodsoptionGoodslink> getFrontGoodsList(Map<String, Object> param);
	
	/**
	 * 상품 상세정보 조회
	 * - input : goodsno
	 * @param frontGoodsVO
	 * @return
	 */
	public GdGoodsGoodsoptionGoodslink getFrontGoodsView(FrontGoodsVO frontGoodsVO);
		
	/**
	 * 상품 브랜드 목록 조회
	 * - input : brandno
	 * @param frontGoodsVO
	 * @return
	 */
	public List<GdGoodsBrand> getFrontGoodsViewBrandList(FrontGoodsVO frontGoodsVO);
		
	/**
	 * 관련 상품 목록 조회
	 * - input : collection="relationList"
	 * @param frontGoodsVO
	 * @return
	 */
	public List<GdGoods> getFrontGoodsRelationList(FrontGoodsVO frontGoodsVO);
		
	/**
	 * 관련 상품 목록 random 조회[관련상품 설정이 안되어 있을 경우 조회] 
	 * - input : goodsno
	 * @param frontGoodsVO
	 * @return
	 */
	public List<GdGoods> getFrontGoodsRelationRandomList(FrontGoodsVO frontGoodsVO);
		
	/**
	 * 상품평 POINT 평균 조회
	 * - input : goodsno
	 * @param frontGoodsVO
	 * @return
	 */
	public int getFrontGoodsReviewPointAvg(FrontGoodsVO frontGoodsVO);
	
	/**
	 * 상품평 임시 이미지 등록
	 * @param frontGoodsReviewVO
	 * @return
	 */
	public int insertReviewTempFile(FrontGoodsReviewVO frontGoodsReviewVO);
	
	/**
	 * 상품평 임시 이미지 조회
	 * @param frontGoodsReviewVO
	 * @return
	 */
	public List<FrontGoodsReviewVO> selectReviewTempFileList(FrontGoodsReviewVO frontGoodsReviewVO);
	
	/**
	 * 상품평 임시 이미지 삭제
	 * @param frontGoodsReviewVO
	 * @return
	 */
	public int deleteReviewTempFile(FrontGoodsReviewVO frontGoodsReviewVO);
	
	
	/**
	 * 상품평 이미지 등록
	 * @param frontGoodsReviewVO
	 * @return
	 */
	public int insertReviewFile(FrontGoodsReviewVO insertReviewVO);
	
	/**
	 * 상품평 총카운트 조회
	 * - input : goodsno
	 * @param frontGoodsVO
	 * @return
	 */
	public int getFrontGoodsReviewTotCnt(FrontGoodsReviewVO frontGoodsReviewVO);
	
	/**
	 * 상품평 목록 조회
	 * - input : goodsno, rowStart, pageSize
	 * @param frontGoodsVO
	 * @return
	 */
	public List<MemberGoodsrevwGoods> getFrontGoodsReviewList(FrontGoodsReviewVO frontGoodsReviewVO);
	
	/**
	 * 상품평 포토 목록 조회
	 * - input : sno
	 * @param frontGoodsVO
	 * @return
	 */
	public List<FrontGoodsReviewVO> getFrontGoodsReviewFileList(FrontGoodsReviewVO frontGoodsReviewVO);
	
	/**
	 * 상품 필수 옵션 리스트 조회
	 * - input : goodsno
	 * @param frontGoodsVO
	 * @return
	 */
	public List<GdGoodsOption> getFrontGoodsOptionList(FrontGoodsVO frontGoodsVO);

	/**
	 * 상품 필수 추가 옵션 리스트 조회
	 * - input : goodsno
	 * @param frontGoodsVO
	 * @return
	 */
	public List<GdGoodsAdd> getFrontGoodsAddOptionList(FrontGoodsVO frontGoodsVO);
	
	/**
	 * 상품 QNA 총카운트 조회
	 * - input : goodsno
	 * @param frontGoodsVO
	 * @return
	 */
	public int getFrontGoodsQnATotCnt(FrontGoodsQnAVO frontGoodsQnAVO);
	
	/**
	 * 상품 QNA 목록 조회
	 * - input : goodsno, rowStart, pageSize
	 * @param frontGoodsVO
	 * @return
	 */
	public List<GdGoodsQna> getFrontGoodsQnAList(FrontGoodsQnAVO frontGoodsQnAVO);

	/** 장바구니 목록*/
	public FrontCheckStockVO frontCheckStock(Map<String, Object> map);
	
	/** 장바구니 > 중복확인*/
	public List<GdGoodsCart> goodsMemberCartChkcnt(Map<String, Object> map);
	
	/** 회원장바구니 인서트*/
	public void goodsMemberCartInsert(Map<String, Object> map);
	
	public GoodsMemberCartListVO goodsMemberCartList(Map<String, Object> map);
	
	/** 장바구니 비우기*/
	public void goodsMemberCartEmpty(int m_no);
	
	/** 장바구니 삭제*/
	public void goodsMemberCartDelete(Map<String, Object> map);
	
	/** 장바구니 옵션 업데이트 */
	public void goodsMemberCartOptUpdate(Map<String, Object> map);
	
	/** 장바구니 수량 업데이트 */
	public void goodsMemberCartEaUpdate(Map<String, Object> map);
	
	/** 회원장바구니 리스트 */
	//public List<GdGoodsCart> goodsCartList(int m_no);
	public List<GdGoodsCart> goodsCartList(UserInfo userInfo);
	
	public void updateCartList(UserInfo userInfo);
	
	/**
	 * 상단 상품검색 조회
	 * @param vo
	 * @return
	 */
	public List<GdGoodsGoodsoptionGoodslink> getFrontTopGoodsSearchList(Map<String, Object> param);
	public int getFrontTopGoodsSearchListCount(Map<String, Object> param);

	/**기본배송정책 추가리스트*/
	public List<GdDeliveryPolicy> getDeliveryPolicyList();
	
	/**해당 상품 연결 카테고리 조회 */
	public List<String> getFrontGoodsLinkList(int goodsno);
	
	/**회원 직접 다운로드 쿠폰 조회*/
	public List<CouponCouponcategoryCoupongoodsno> getFrontDownCouponList(Map<String, Object> param);
	
	/**쿠폰다운로드 횟수 조회 */
	public int getFrontDownCouponCount(String couponcd);
	
	/**회원별 쿠폰다운로드 횟수 조회 */
	public int getFrontDownCouponCountForMember(FrontGoodsCouponVO vo);
	
	/**쿠폰발급내역 일련번호 MAX 값 조회 */
	public int getFrontCouponApplyMax();
	
	/**회원직접 다운로드 쿠폰 발급 내역 등록  */
	public void insertFrontCouponApply(FrontGoodsCouponVO vo);
	public void insertFrontCouponApplyMember(FrontGoodsCouponVO vo);
	
	/** 장바구니 아이템 중복시 수량 업데이트 추가 */
	public void updateGoodsCartEA(Map<String, Object> parma);
	
	public void insertLikeGoods(@Param("goodsno")String goodsno , @Param("mno") int mno);
	
	public void deleteLikeGoods(@Param("goodsno")String goodsno , @Param("mno") int mno);
	
	public List<Map<String,Object>>getGoodsInfo();
	
	public String[] getGoodsNameInfo();
	public String[] getBrandNameInfo();
	
	public List<GdGoodsOption> getOpt2List(GdGoodsOption gdGoodsOption);
	
	public OrderMember selectFrontReviewInfo(Map<String, Object> param);
}