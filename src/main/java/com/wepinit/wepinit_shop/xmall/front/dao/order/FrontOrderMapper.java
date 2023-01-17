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
package com.wepinit.wepinit_shop.xmall.front.dao.order;

import com.wepinit.wepinit_shop.xmall.admin.vo.order.OrderDeliveryVO;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.*;
import com.wepinit.wepinit_shop.xmall.front.vo.order.FrontOrderSettleVO;
import com.wepinit.wepinit_shop.xmall.front.vo.order.FrontOrderVO;
import com.wepinit.wepinit_shop.xmall.front.vo.order.OverDeliveryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface FrontOrderMapper {
	
	// 매개변수는 하나 이상인 경우 Map 형태로 작성
//	public List<TSample> getFrontOrderList(Map<String, Object> param);
//	public TSample getOrderSample(String sample_no);
//	public int setFrontOrderReg(Map<String, Object> param);
//	public int setFrontOrderMod(Map<String, Object> param);
//	public int getFrontOrderRowCount(Map<String, Object> param);
	public List<FrontOrderSettleVO> getOrderDeliveryList(Map<String, Object> paramMap);
	public MemberMemberGrp getOrderMember(FrontOrderVO frontOrderVO);
	public GoodsGoodsption getFrontMypageCheckStock(FrontOrderVO frontOrderVO);
	public GdGoodsGoodsoptionGoodslink getFrontMypageCartlist(FrontOrderVO frontOrderVO);
	public List<GdDeliveryPolicy> getDeliveryPolicy();
	public List<GdGoodsLink> getFrontOrderCategory(FrontOrderVO frontOrderVO);
	public List<GdGoods> getFrontOrderSellerCd(FrontOrderVO frontOrderVO);
	public List<OrderMember> getFrontOrderMember(FrontOrderVO frontOrderVO);
	public List<GdCouponOrder> getFrontCouponOrder(FrontOrderVO frontOrderVO);
	public List<CouponapplyApplymemberCouponCategoryGoodsno> getFrontOrderCoupon(FrontOrderVO frontOrderVO);
	public List<GdDeliveryPolicy> getDelivery(FrontOrderVO frontOrderVO);
	public List<GdListBank> getOrdersPopupOrder4(FrontOrderVO frontOrderVO);
	public List<GdGoodsCart> getGoodsMemberCartList(FrontOrderVO frontOrderVO);
	public int deleteGoodsMemberCartList(FrontOrderVO frontOrderVO);
	public List<GoodsGoodsption> getFrontOrderGoods(FrontOrderVO frontOrderVO);
	public List<GdOrder> getFrontOrderCheck(FrontOrderVO frontOrderVO);
	public List<couponapplyCoupon> getFrontOrderCouponCheck(	FrontOrderVO frontOrderVO);
	public void updateFrontCoupon(FrontOrderVO frontOrderVO);
	public List<GdGoodsOption> getFrontGoodsSupply(FrontOrderVO frontOrderVO);
	public List<GoodsGoodsbrand> getFrontGoodsBrand(FrontOrderVO frontOrderVO);
	public List<MemberMemberGrp> getOrderMember(FrontOrderSettleVO frontOrderSettleVO);
	public List<GdOrder> getFrontOrderCheck(FrontOrderSettleVO frontOrderSettleVO);
	public GdMemberGrp getFrontDeliveryGrp(@Param("level") String level);
	public List<GdGoodsGoodsoptionGoodslink> getFrontMypageCartlist(FrontOrderSettleVO frontOrderSettleVO);
	public List<couponapplyCoupon> getFrontOrderCouponCheck(FrontOrderSettleVO frontOrderSettleVO);
	public void updateFrontCoupon(FrontOrderSettleVO frontOrderSettleVO);
	public List<GdGoodsOption> getFrontGoodsSupply(FrontOrderSettleVO frontOrderSettleVO);
	public List<GoodsGoodsbrand> getFrontGoodsBrand(FrontOrderSettleVO frontOrderSettleVO);
	public int insertFrontOrder(FrontOrderVO frontOrderVO);
	public int insertFrontOrder(FrontOrderSettleVO frontOrderSettleVO);
	public void insertFrontOrderGoods(FrontOrderVO frontOrderVO);
	public void insertFrontOrderCoupon(FrontOrderVO frontOrderVO);
	public void insertFrontOrderCoupon(FrontOrderSettleVO frontOrderSettleVO);
	public int insertFrontOrderGoods(FrontOrderSettleVO frontOrderSettleVO);
	public GdOrderListBank frontOrderResult(Long ordno);
	public void frontOrderEndUpdate(Map<String, Object> map);
	public void frontOrderItemUpdate(Map<String, Object> map);
	public void frontCouponStatusUpdate(@Param("usedCouponSnos") String [] usedCouponSnos, @Param("mno") int mno, @Param("ordno") Long ordno);
	public List<Long> selectApplysnoFrontCouponStatus(String ordno);
	public List<Integer> getFrontGoodsNo(Long ordno);			//상품번호 조회
	public void updateFrontReserve(Map<String, Object> param);		//주문정보 적립금 UPDATE
	
	public List<CouponapplyApplymemberCouponCategoryGoodsno> getFrontCouponList (Map<String, Object> paramMap);
	public List<GdCoupon> selectReserveCouponInUsedCoupon(@Param("usedCouponSnos") String [] usedCouponSnos);

	public List<String> getGoodsCategory(@Param("goodsnos") String [] goodsnos);
	
	/** gd_order_delivery 배송정보 테이블 insert */
	public void insertFrontOrderDelivery(FrontOrderSettleVO frontOrderSettleVO);
	
	/** 주문페이지 상품번호로 주문사별 추가배송비 정책 가져오기 */
	public List<OverDeliveryVO> getSellerOverDelivery(Map<String, Object> map);
	
	public OrderDeliveryVO getDivisionOverDeliveryPolicy(String goodsno);
	
	public FrontOrderSettleVO getOrderEndInfo(Map<String, Object> map);
	
	public int insertEmoneyLog(Map<String, Object> map);
	
	public int updateMemberEmoney(Map<String, Object> map);
	
	public GdCoupon getFrontOrderCouponCodeByCouponcd(String couponcd);
	
	public GdCoupon getFrontOrderDcCodeInfo(GdCoupon gdCoupon);
	
	public int getFrontOrderCouponCodeCnt(GdCoupon gdCoupon);

	public int selectCouponApplySno();
	
	public int insertCouponApply(Map<String, Object> map);
	
	public int updateCouponApplymember(Map<String, Object> map);
	
	public int insertCouponApplymember(Map<String, Object> map);
	
	public int insertCouponOrder(Map<String, Object> map);
	
	public int insertOrderPayLog(Map<String, Object> map);
}