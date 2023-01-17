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
package com.wepinit.wepinit_shop.xmall.seller.order.dao;

import com.wepinit.wepinit_shop.xmall.admin.vo.order.OrderDeliveryVO;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.*;
import com.wepinit.wepinit_shop.xmall.seller.order.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Mapper
public interface SellerOrderMapper {
	

	/**
	 * @param orderFM
	 * @return
	 * 주문리스트 총건수
	 */
	public int getOrderCount(SellerOrderFM orderFM);

	/**
	 * @param orderFM
	 * @return
	 * 주문리스트(주문처리흐름)
	 */
	public List<OrderMember> getOrderList2(Map<String, Object> param);

	/**
	 * @param orderFM
	 * @return
	 * 주문리스트(주문일)
	 */
	public List<OrderMember> getOrderList(Map<String, Object> param);

	/**
	 * @param orderExcelVO
	 * @return
	 * 주문별 엑셀 다운로드
	 */
	public List<OrderExcel> getOrderXls(SellerOrderFM orderExcelVO);

	/**
	 * @param orderExcelVO
	 * @return
	 * 상품별 엑셀 다운로드
	 */
	public List<OrderExcel> getOrderGoodsXls(SellerOrderFM orderExcelVO);

	/**
	 * @return
	 * 택배회사 리스트
	 */
	public List<GdListDelivery> ordersPopupOrder3();

	/**
	 * @return
	 * 입금계좌 정보
	 */
	public List<GdListBank> ordersPopupOrder4();

	/**
	 * @param ordno
	 * @return
	 * 주문번호로 상품조회 
	 */
	public List<PopupOrder1> ordersPopupOrder1(@Param("ordno") long ordno, @Param("sellerCd") String sellerCd);

	/**
	 * @param ordno
	 * @return
	 * 주문번호로 주문자 정보조회 
	 */
	public PopupOrder2 ordersPopupOrder2(long ordno);

	/**
	 * @param ordno
	 * @return
	 * 주문번호로 주문한 상품갯수 조회
	 */
	public int ordersPopupOrder5(long ordno);

	/**
	 * @param ordno
	 * @return
	 * 세금관리
	 */
	public List<GdTax> paperTaxState(long ordno);

	public int ordersPopupOrder6(long ordno);

	public List<Long> ordersPopupOrder7(long ordno);

	public List<PopupOrder3> ordersPopupOrder8(long ordno);

	public List<PopupOrder4> ordersPopupOrder9(long ordno);

	public List<GdOrderCancel> ordersPopupOrder11(long ordno);

	public List<GdLogCancel> ordersPopupOrder12(int cancel);

	public int cancelListCnt1(GdOrderCancelSqlVO sqlVO);

	public List<OrderCancel> cancelList1(GdOrderCancelSqlVO sqlVO);

	public int getOrderRegoodsListCount(SellerOrderRegoodsFM vo);

	public List<Map<String, Object>> getOrderRegoodsList(
			SellerOrderRegoodsFM vo);

	public List<Map<String, Object>> getOrderRegoodsInfoList(Map<String,Object> map);

	public int getRepayCount(SellerRepayFM repayVO);

	public List<OrdercancelOrderitemMember> getRepayList(
			SellerRepayFM repayVO);

	public int getRepayList2(OrdercancelOrderitemMember oom);

	public OrderitemOrdercancel getRepayList3(OrdercancelOrderitemMember oom);
	
	public void updateOrderRepay1(SellerOrderRepayFM repayVO);

	public void updateOrderRepay2(SellerOrderRepayFM repayVO);

	public String updateOrderRepay3(SellerOrderRepayFM repayVO);

	public void updateOrderRepay4(SellerOrderRepayFM repayVO);

	public List<HashMap> updateOrderRepay5(SellerOrderRepayFM repayVO);

	public void couponYnStatus(SellerOrderRepayFM repayVO);

	public HashMap libfuncCtlStep1(SellerOrderRepayFM repayVO);
	
	public GdOrder orderChkCancel8(String ordno);

	public Map<String, Object> orderChkCancel9(String ordno);

	public void orderChkCancel11(GdOrder order);

	public int orderChkCancel12(String valueOf);

	public int orderChkCancel15(String ordno, String[] sno);

	public void orderChkCancel1(Map<String, Object> param);

	public char[] orderChkCancel2();

	public GdOrderItem orderChkCancel3(String string);

	public void orderChkCancel16(GdLogEmoney logEmoney);

	public void orderChkCancel17(GdLogEmoney logEmoney);

	public void orderChkCancel6(GdLogCancel params);

	public void orderChkCancel5(GdLogCancel params);

	public void orderChkCancel13(long ordno);

	public void orderChkCancel14(long ordno);

	public void orderSetPrnSettleprice5(Map<String, Object> param);

	public Map<String, Object> orderSetPrnSettleprice4(Map<String, Object> param);

	public void orderSetPrnSettleprice3(Map<String, Object> param);

	public void orderChkCancel4(GdLogCancel params);

	public void orderChkCancel7(GdOrderItem gdOrderItemParam);

	public List<Map<String, Object>> orderSetPrnSettleprice1(
			Map<String, Object> param);

	public Map<String, Object> orderSetPrnSettleprice2(Map<String, Object> param);

	public void orderDeleteInsert(Map<String, Object> param);

	public void orderDelete(Map<String, Object> param);

	//20191212 주문상세 수량및 가격변경시 수정하는 로직 재수정
	public List<Map<String,String>> orderModifyDC1(String ordno);
	
	public int orderModifyDC2(String id);
	
	public GdOrderItem orderModify1(Map<String, Object> param);

	public int orderModify2(Map<String, Object> param);

	public void orderModify3(Map<String, Object> param);

	public void orderModify4(Map<String, Object> param);

	public void orderModify5(Map<String, Object> param);
	
	/** 배송정보 테이블 조회 */
	public List<OrderDeliveryVO> selectDeliveryInfo(PopupOrder1 param);
	

	/** 배송테이블 업데이트 */
	public void updateDeliveryInfo(OrderDeliveryVO delivery);
	
	public void updateOrderRegoodsItem(@Param("sno")int sno);		//반품/교환접수 > 반품완료 UPDATE
	
	public long getOrderRegoodsCancel(@Param("sno")int sno);		//반품/교환접수 > 반품완료 주문취소 정보 반환
	
	public void updateOrderRegoods(@Param("ordno")long ordno);		//반품/교환접수 > 반품완료 UPDATE

	public Map<String, Object> getOrderInfo(@Param("ordno")long ordno);
	
	public void updateOrderItemExchange(@Param("sno")int sno);		//반품/교환접수 > 교환완료 UPDATE
	
	public long getOrderExchangeCancel(@Param("sno")int sno);		//반품/교환접수 > 교환완료 주문취소 정보 반환
	
	public void updateOrderExchange(@Param("ordno")long ordno);		//반품/교환접수 > 교환완료 UPDATE

	/**
	 * 엑셀 항목설정 업데이트 - 주문별
	 * */
	public void updateSellerOrderXlsSettings(SellerOrderExcelFM excelFM);

	/**
	 * 엑셀 항목설정 업데이트 - 상품별
	 * */
	public void updateSellerOrderGoodsXlsSettings(SellerOrderExcelFM excelFM);
	
	/**
	 * 엑셀 항목설정 데이터 가져오기 - 주문별
	 * */
	public String selectSellerOrderXlsSettings(@Param("sellerCd") String sellerCd);

	/**
	 * 엑셀 항목설정 데이터 가져오기 - 상품별
	 * */
	public String selectSellerOrderGoodsXlsSettings(@Param("sellerCd") String sellerCd);
	
	/**
	 * 판매자 주문상품 리스트 
	 * */
	public List<PopupOrder1> sellerOrdersPopupOrder1(@Param("ordno") long ordno, @Param("sellerCd") String sellerCd);
	
	/**
	 * 판매자 주문상품 리스트 
	 * */
	public int sellerOrdersPopupOrder5(@Param("ordno") long ordno, @Param("sellerCd") String sellerCd);
	
	/**
	 * 판매자 간이영수증 리스트  
	 * */
	public List<PopupOrdersRecovery1> sellerPaperReception(@Param("ordno") long ordno, @Param("sellerCd") String sellerCd);

	/**
	 *	판매자별 주문상태 데이터 가져오기
	 * */
	public int getOrdersPopupIstep(@Param("ordno") long ordno, @Param("sellerCd") String sellerCd);
	
	public List<PopupOrder1> getItemList(Map<String,Object> param);
	
	//2020-01-17 이현빈 환불후 쿠폰상태 변경
	public void couponYnStatus1(Map<String,Object> param);
	
	//2020-01-29 이현빈 환불 후 적립금 변경
	public void repayReservePrice(Map<String,Object> param);
	
	//2020-01-29 이현빈 환불 후 적립금 변경
	public void repayEmoneyPrice(Map<String,Object> param);
	
	//2020-01-17 이현빈 사용쿠폰정보 조회
	public String [] getCouponApplySno(Map<String,Object> param);
	
	//2020-01-21 이현빈 전체쿠폰 조회
	public String [] getAllCouponApplySno(String ordno);
	
}
