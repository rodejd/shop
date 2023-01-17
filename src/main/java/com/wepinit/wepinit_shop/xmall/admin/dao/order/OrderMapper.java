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
package com.wepinit.wepinit_shop.xmall.admin.dao.order;

import com.wepinit.wepinit_shop.xmall.admin.vo.order.OrderDeliveryVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.order.OrderListStepChangeVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.order.OrderRegoodsVO;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.*;
import com.wepinit.wepinit_shop.xmall.front.vo.order.FrontOrderSettleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

	//주문관리 > 주문관리 > 주문리스트
	public int getOrderCount(Map<String, Object> param);
	public List<OrderMember> getOrderList(Map<String, Object> param);
	public List<Map<String, Object>> selectOrderExcelList(Map<String, Object> param);
	public List<OrderMember> getOrderList2(Map<String, Object> param);
	public List<OrderExcel> getOrderXls(Map<String, Object> param);
	public List<OrderExcel> getOrderGoodsXls(Map<String, Object> param);
	public int cancelListCnt1(GdOrderCancelSqlVO sqlVO);
	public List<OrderCancel> cancelList1(GdOrderCancelSqlVO sqlVO);
	public List<GdListDelivery> ordersPopupOrder3();
	public List<GdListBank> ordersPopupOrder4();
	public List<PopupOrder1> ordersPopupOrder1(long ordno);
	public PopupOrder2 ordersPopupOrder2(long ordno);
	public int ordersPopupOrder5(long ordno);
	public List<GdTax> paperTaxState(long ordno);
	public int ordersPopupOrder6(long ordno);
	public List<Long> ordersPopupOrder7(long ordno);
	public List<PopupOrder3> ordersPopupOrder8(long ordno);
	public List<PopupOrder4> ordersPopupOrder9(long ordno);
	public List<GdOrderItem> ordersPopupOrder10(int cancel);
	public List<GdOrderCancel> ordersPopupOrder11(long ordno);
	public List<GdLogCancel> ordersPopupOrder12(int cancel);
	public PopupOrdersRecovery1 ordersRecovery1(int sno);
	public Integer ordersRecovery2(Integer mno);
	public Integer ordersRecovery3(Map<String, Object> map);
	public void ordersRecovery4(Map<String, Object> map);
	public void ordersRecovery5(int sno2);
	public void ordersRecovery6(Map<String, Object> param);
	public void ordersRecovery7(Map<String, Object> param);
	public void ordersRecovery8(Map<String, Object> param);
	public void ordersRecovery9(long ordno);
	public void ordersRecovery10(Map<String, Object> param);
	public void ordersRecovery11(Map<String, Object> param);

	// 주문리스트 > 주문상세내역 > 주문취소/반품처리
	public List<Map<String, Object>> ordersCancel1(Map<String, Object> param);
	/**gd_order_cancel 테이블에 새로운 주문취소건을 등록한다.*/
	public void orderChkCancel1(Map<String, Object> param);	// INSERT gd_order_cancel
	/**gd_order_cancel 테이블 sno 컬럼의 제일 최종 값을 가져온다. (sno 는 auto_increament)*/
	public int orderChkCancel2();							// SELECT gd_order_cancel
	/**gd_order_item 테이블에서 파라미터(sno)에 해당하는 정보를 가져옵니다.*/
	public GdOrderItem orderChkCancel3(@Param("sno") String sno);	// SELECT gd_order_item
	/**gd_log_cancel 테이블에 새로운 취소건을 추가합니다.*/
	public void orderChkCancel4(GdLogCancel param);	// INSERT gd_log_cancel
	/**주문 취소건 수량이 다른 경우 남은 수량만큼의 주문서를 gd_order_item 테이블에 새로 insert 합니다.
	 * 수량은 orderChkCancel6 를 통해 따로 update 합니다.*/
	public void orderChkCancel5(GdLogCancel param);	// INSERT gd_order_item
	/**gd_order_item 테이블의 sno 에 해당하는 ea(갯수)를 변경(update) 합니다.*/
	public void orderChkCancel6(GdLogCancel param);	// UPDATE gd_order_item
	/**gd_order_item 테이블의 sno 에 해당하는 주문단계(istep)와 취소여부(cancel) 을 update 합니다.*/
	public void orderChkCancel7(GdOrderItem param);	// UPDATE gd_order_item
	/**ordno 와 일치하는 주문건을 가져옵니다.*/
	public GdOrder orderChkCancel8(@Param("ordno") String ordno);	// SELECT gd_order
	/**gd_order_item 테이블의 ordno 와 일치하는 데이터의 갯수(cnt), 상태(istep), 반품완료, 환불완료, 교환완료 상태인지의 여부(icnt = 1 : true, icnt = 0 : false) 를 가져옵니다.*/
	public Map<String, Object> orderChkCancel9(@Param("ordno") String ordno);	// SELECT gd_order_item
	public int orderChkCancel10(@Param("ordno") String ordno);		// SELECT gd_order_item
	/**gd_order 테이블의 ordno 와 일치하는 데이터의 단계(step2) 를 변경합니다. */
	public void orderChkCancel11(GdOrder param);	// UPDATE gd_order
	/**gd_coupon_order(주문시 쿠폰사용내역) 테이블의 ordno 주문건에 해당하는 쿠폰사용내역의 갯수를 구합니다.*/
	public int orderChkCancel12(@Param("ordno") String ordno);		// SELECT gd_coupon_order
	/**gd_coupon_order(주문시 쿠폰사용내역) 테이블의 ordno 주문건에 해당하는 쿠폰 사용내역을 삭제합니다.*/
	public void orderChkCancel13(@Param("ordno") long ordno);	// DELETE gd_coupon_order
	/**gd_order 주문건의 ordno 주문에 해당하는 데이터 중 쿠폰에 관련된 내용을 삭제합니다(update)*/
	public void orderChkCancel14(@Param("ordno") long ordno);	// UPDATE gd_order
	public int orderChkCancel15(@Param("ordno") String ordno, @Param("snoArr") String[] sno);		// SELECT gd_order_item
	/**회원(m_no) 에게 적립금(emoney) 를 환불합니다.*/
	public void orderChkCancel16(GdLogEmoney param);	// UPDATE gd_member
	/**적립금의 변경내용 로그를 남깁니다.*/
	public void orderChkCancel17(GdLogEmoney param);	// INSERT gd_log_emoney
	// 주문리스트 > 주문상세내역 > 주문취소/반품처리 재고수정
	public List<Map<String, Object>> orderSetStock1(Map<String, Object> param);	// SELECT gd_order_item
	public int orderSetStock2(Map<String, Object> param);	// SELECT gd_goods_option
	public void orderSetStock3(Map<String, Object> param);	// UPDATE gd_goods_option
	public void orderSetStock4(Map<String, Object> param);	// UPDATE gd_order_item
	// 주문리스트 > 주문상세내역 > 주문취소/반품처리 결제금액(최종/출력) 계산
	public List<Map<String, Object>> orderSetPrnSettleprice1(Map<String, Object> param);	// SELECT gd_order_item
	public Map<String, Object> orderSetPrnSettleprice2(Map<String, Object> param);	// SELECT gd_order
	public void orderSetPrnSettleprice3(Map<String, Object> param);	// UPDATE gd_order
	public Map<String, Object> orderSetPrnSettleprice4(Map<String, Object> param);	// SELECT gd_order
	public void orderSetPrnSettleprice5(Map<String, Object> param);	// UPDATE gd_member
	// 주문리스트> 주문상세내역 > 주문삭제
	public void orderDeleteInsert(Map<String, Object> param);	// INSERT gd_order_del
	public void orderDelete(Map<String, Object> param);			// DELETE gd_order
	// 주문처리Log내역
	public List<Map<String, Object>> orderLogList(Map<String, Object> param);	// SELECT gd_order_log
	//  주문리스트> 주문상세내역 > 주문 수정
	public GdOrderItem orderModify1(Map<String, Object> param);	// SELECT gd_order_item
	public int orderModify2(Map<String, Object> param);		// SELECT gd_goods_option
	public void orderModify3(Map<String, Object> param);	// UPDATE gd_goods_option
	public void orderModify4(Map<String, Object> param);	// UPDATE gd_order_item
	public void orderModify5(Map<String, Object> param);	// UPDATE gd_order
	
	//20191212 주문상세 수량및 가격변경시 수정하는 로직 재수정
	public List<Map<String,String>> orderModifyDC1(String ordno);
	public int orderModifyDC2(String id);
	
	//주문관리 > 반품/교환접수리스트
	public int getOrderRegoodsListCount();	//반품/교환접수리스트 총 건수
	public List<Map<String, Object>> getOrderRegoodsList(OrderRegoodsVO vo);	//반품/교환접수리스트 반환
	public Map<String, Object> getOrderRegoodsInfoList(@Param("sno")int sno);		//반품/교환접수 상세정보리스트 반환
	public void updateOrderRegoodsItem(@Param("sno")int sno);		//반품/교환접수 > 반품완료 UPDATE
	public long getOrderRegoodsCancel(@Param("sno")int sno);		//반품/교환접수 > 반품완료 주문취소 정보 반환
	public void updateOrderRegoods(@Param("ordno")long ordno);		//반품/교환접수 > 반품완료 UPDATE
	public void updateOrderItemExchange(@Param("sno")int sno);		//반품/교환접수 > 교환완료 UPDATE
	public long getOrderExchangeCancel(@Param("sno")int sno);		//반품/교환접수 > 교환완료 주문취소 정보 반환
	public void updateOrderExchange(@Param("ordno")long ordno);		//반품/교환접수 > 교환완료 UPDATE
	public Map<String, Object> getOrderInfo(@Param("ordno")long ordno);		//주문 정보 조회
	
	//주문상세 > 출력
	public List<Long> orderPrintList(Map<String, Object> param);
	public int paperTaxCount(Long ordno);
	public List<PopupOrdersRecovery1> paperReception(Long ordno);
	
	/** 주문상세페이지 배송정보 테이블 가져오기 */
	public List<OrderDeliveryVO> selectDeliveryInfo(Long ordno);
	
	public OrderDeliveryVO selectPopupDeliveryInfo(Long ordno);
	
	public List<GdListDelivery> getUseDeliveryCompList(String value);
	
	/** 송장번호 및 택배사 정보 배송정보 테이블에 업데이트 */
	public void updateDeliveryInfo(OrderDeliveryVO delivery);
	public List<FrontOrderSettleVO> getOrderInfo3(OrderListStepChangeVO ordno);	//선택한 주문정부 
	
	public List<Map<String,Object>> getNormalGoodsno(Map<String,Object> param);
	
	/** 주문 취소시 배송정보 업데이트*/
	
	public Map<String,Object> getDeliveryInfoForInsertCancel(String sno);
	// 취소 상품에 해당하는 판매사 코드 조회
	public String getSellerCdForCancel(String sno);
	//주문 상품중 한 판매사에 해당하는 상품과 취소된 상품과의 관계를 개수로 조회
	public int checkAllCancelOfSellerGoods(Map<String,Object> param);
	//주문 상품중 한 판매사에 해당하는 상품을 전부 조회
	public String [] getCancelGoodsOfSeller(Map<String,Object> param);
	//배송비 업데이트 gd_order_delivery
	public void updateDeliveryPrice1(Map<String,Object> param);
	//배송비 업데이트 gd_order
	public void updateDeliveryPrice2(Map<String,Object> param);
	//gd_order_delivery 배송비 조회 
	public Map<String,Object> getDeliveryPriceInfo(Map<String,Object> param);
	
	/** 주문 복원시 배송정보 업데이트*/
	public Map<String,Object> getRecoveryDelivery(String sno);
	
	public int getItemGoodsno(String sno);

	//20.02.20 이현빈 취소번호 가져오기
	public int getCancelNumber(String ordno);
	
	//주문번호로 취소상품 가져오기  
	public int getCancelOrRefundCnt(String ordno);
	
	//주문시 사용한 전체쿠폰 가져오기 
	public String[] getAllCouponPrice(String ordno);
	
	public int updateCancelStep(PopupOrder1 popupOrder1);
}
