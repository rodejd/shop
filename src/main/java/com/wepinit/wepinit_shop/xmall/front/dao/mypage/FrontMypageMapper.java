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
package com.wepinit.wepinit_shop.xmall.front.dao.mypage;

import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.*;
import com.wepinit.wepinit_shop.xmall.front.vo.mypage.FrontMypageBoardVO;
import com.wepinit.wepinit_shop.xmall.front.vo.mypage.FrontMypageOrderVO;
import com.wepinit.wepinit_shop.xmall.front.vo.mypage.FrontMypageTodayVO;
import com.wepinit.wepinit_shop.xmall.front.vo.mypage.FrontMypageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface FrontMypageMapper {

	
	//마이페이지 > 혜택관리 > 쿠폰함
	public int getFrontCouponCount(Map<String, Object> param);
	public List<CouponapplyApplymemberCouponCategoryGoodsno> getFrontCouponList(Map<String, Object> param);
	
	//마이페이지 > 혜택관리 > 적립금관리
	public int getFrontEmoneyCount(Map<String, Object> param);
	public List<GdLogEmoney> getFrontEmoneyList(Map<String, Object> param);

	//마이페이 > 나의쇼핑 > 나의 문의내역
	public int getFrontMyPageGoodsQnaCount(FrontMypageBoardVO vo);
	public List<GdGoodsQna> getFrontMyPageGoodsQnaList(FrontMypageBoardVO vo);
	public List<GdGoodsQna> getFrontMyPageGoodsQnaReply(FrontMypageBoardVO vo);
	
//	public int getFrontMyPageGoodsQnaTotalCount(int mno);
//	public int getFrontMyPageGoodsQnaReplyCount(int parent);
//	public Map<String, Object> getFrontMyPageGoodsInfo(int goodsno);
	
	
	
	//내쇼핑정보 > 주문내역/배송조회
	public OrderOrderitem getFrontOrderCountStep(Map<String, Object> param);
	public List<GdListDelivery> getUseDeliveryCompList(Map<String, Object> param);
	public int getFrontOrderCount(Map<String, Object> param);
	public List<OrderMember> getFrontOrderList(Map<String, Object> param);
	public void updateOrderStep(Map<String, Object> param);
	public List<GdOrderItem> getFrontOrderItemObj(FrontMypageVO frontMypageVO);
	public int insertOrderCancel(Map<String, Object> param);
	public int updateOrderItem(Map<String, Object> param);
	public int updateDeliveryReturn(Map<String, Object> param);
	public void updateOrderItem2(Map<String, Object> param);
	//whpark 20191111 start
	public void updateOrderItemIstep(Map<String, Object> param);
	public int getOtherItemCnt(Map<String, Object> param);
	public void updateOrderStep2(Map<String, Object> param);
	//whpark 20191111 end
		
		
	
	
	public List<OrderitemGoods> getFrontOrderViewList(FrontMypageVO frontMypageVO);
	public Map<String, Object> getFrontOrderView(Map<String, Object> map);
	public List<Map<String, Object>> getFrontOrderItemList(Map<String, Object> map);
	public List<GdOrder> getFrontOrderViewObj(FrontMypageVO frontMypageVO);
	
	/**
	 * 상품별 이용후기 작성
	 * @param gdGoodsReview
	 * @return
	 */
	public int setGoodsReviewInsert(GdGoodsReview gdGoodsReview);
	
	/**
	 * 이용후기작성 sno 업데이트
	 * @return
	 */
	public int setGoodsReviewSnoUpdate();
	
	/**
	 * 나의 상품후기 수정
	 * @param gdGoodsReview
	 * @return
	 */
	public int setGoodsReviewUpdate(GdGoodsReview gdGoodsReview);
	
	/**
	 * 나의 상품후기 삭제
	 * @param gdGoodsReview
	 * @return
	 */
	public int setGoodsReviewDelete(GdGoodsReview gdGoodsReview);
	
	/**
	 * 상품별 QnA 작성
	 * @param gdGoodsQna
	 * @return
	 */
	public int setGoodsQnAInsert(GdGoodsQna gdGoodsQna);
	

	
	/**
	 * 상품별 QnA sno 업데이트
	 * @return
	 */
	public int setGoodsQnASnoUpdate();
	
	/**
	 * 나의 상품별 QnA 수정
	 * @param gdGoodsQna
	 * @return
	 */
	public int setGoodsQnAUpdate(GdGoodsQna gdGoodsQna);
	

	/**
	 * 나의 상품별 QnA 삭제
	 * @param gdGoodsQna
	 * @return
	 */
	public int setGoodsQnADelete(GdGoodsQna gdGoodsQna);
	
	
	
	/*
	 * Mypage > 1:1문의
	 */
	public int getFrontMemberQnaListCount(int mno);
	public List<GdMemberQna> getFrontMemberQnaList(FrontMypageBoardVO vo);
	public MemberMemberqna getFrontMemberQnaInfo(int sno);
	public void updateFrontMemberQna(FrontMypageBoardVO vo);
	public void insertFrontMemberQnaReply(FrontMypageBoardVO vo);
	public void insertFrontMemberQna(FrontMypageBoardVO vo);
	public int getFrontMemberQnaSnoMax();
	public void updateFrontMemberQnaParent(int sno);
	public void deleteFrontMemberQna(int sno);
	public int getFrontMyPageMemberQnaOrderListCount(int mno);
	public int getFrontMyPageOrderGoddsListCount(String ordno);
	
	public List<Map<String, Object>> getFrontMyPageMemberQnaOrderList(FrontMypageOrderVO vo);
	public List<FrontMypageBoardVO> getFrontMyPageOrderGoddsList(FrontMypageBoardVO vo);
	
	/*
	 * Mypage > 상품후기
	 */
	public int getFrontMyPageReviewCount(int mno);
	public List<MemberGoodsrevwGoods> getFrontMyPageReviewList(FrontMypageBoardVO vo);
	public void updateFrontMyPageReview(FrontMypageBoardVO vo);
	public void deleteFrontMyPageReview(int sno);
	/*
	 * Mypage > 상품보관함
	 */
	public int getFrontMyPageWishCount(int mno);
	public List<MemberwishlistGoodsGoodsoption> getFrontMyPageWishList(FrontMypageBoardVO vo);
	public void deleteFrontMyPageWishList(Map<String, Object> param);
	public int getFrontMyPageWishListCheckCount(FrontMypageBoardVO vo);
	public void insertFrontMyPageWishList(FrontMypageBoardVO vo);
	/*
	 * Mypage > 최근본상품
	 */
	public int getFrontMyPageTodayGoodsListCount(@Param("goodsno")String goodsno);
	public List<GdGoodsGoodsoptionGoodslink> getFrontMyPageTodayGoodsList(FrontMypageTodayVO vo);
	public String getsellerCd(String ordno);
	
	public void insertOrderLog(Map<String, Object> map);
	public List<CouponapplyApplymemberCouponCategoryGoodsno> getUsedCouponList(@Param("ordno")String ordno);
 	
}
