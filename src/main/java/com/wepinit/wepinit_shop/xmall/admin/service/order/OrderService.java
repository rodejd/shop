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
package com.wepinit.wepinit_shop.xmall.admin.service.order;

import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xcube.util.WebUtil;
import com.wepinit.wepinit_shop.xmall.admin.dao.common.CommonMapper;
import com.wepinit.wepinit_shop.xmall.admin.dao.order.OrderMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.order.*;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.dao.ShopLibFncMapper;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.session.AdminSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.GdOrderCancelSqlVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdOrderItem;
import com.wepinit.wepinit_shop.xmall.common.vo.join.*;
import com.wepinit.wepinit_shop.xmall.front.vo.order.FrontOrderSettleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	@Resource
	OrderMapper mapper;
	
	@Resource
	ShopLibFncMapper fncmapper;
	
	@Resource
	CommonMapper commonMapper;
	
	/*
	 * 주문관리 > 주문관리 > 주문리스트
	 * 
	 * */
	
	//주문시 사용한 전체쿠폰 조회 
	public String[] getAllCouponPrice(String ordno){
		return mapper.getAllCouponPrice(ordno);
	}
	public int getCancelOrRefundCnt(String ordno){
		return mapper.getCancelOrRefundCnt(ordno);
	}
	
	public GdOrderItem orderChkCancel3(String sno){
		return mapper.orderChkCancel3(sno);
	}
	
	public Map<String,Object> getOrderInfo(long ordno){
		return mapper.getOrderInfo(ordno);
	}
	
	public int getOrderCount(Map<String, Object> param) throws Exception {
		return  mapper.getOrderCount(param);
		
	}
	
	public List<OrderMember> getOrderList(Map<String, Object> param) throws Exception{
		return mapper.getOrderList(param);
	}
	
	public List<Map<String, Object>> selectOrderExcelList(Map<String, Object> param) throws Exception{
		commonMapper.selectRownum();
		return mapper.selectOrderExcelList(param);
	}
	
	public List<OrderMember> getOrderList2(Map<String, Object> param) throws Exception{
		return mapper.getOrderList2(param);
	}
	
	/** 송장번호 및 택배사 정보 배송정보 테이블에 업데이트 */
	public void updateDeliveryInfo(OrderDeliveryVO delivery) {
		this.mapper.updateDeliveryInfo(delivery);
	}

	
	public List<OrderExcel> getOrderXls(OrderExcelVO orderExcelVO, String[] paramStep2) throws Exception{
		/*
		 * 2.검색설정
		 */
		Map<String, Object> param = new HashMap<String, Object>();
		
		// 초기값 설정
		String orderPeriod = ShopConfig.getProperty("orderPeriod");
		
		if("1".equals(orderExcelVO.getFirst()) && !"".equals( orderPeriod )  ){
			orderExcelVO.setPeriod(orderPeriod);
		}
		
		// 검색타입(주문검색[통합])
		param.put("skey", orderExcelVO.getSkey());
		
		// 검색어(주문검색[통합])
		param.put("sword", orderExcelVO.getSword());
		
		// 검색타입(상품검색[선택])
		param.put("sgkey", orderExcelVO.getSgkey());
		
		// 검색어(상품검색[선택])
		param.put("sgword", orderExcelVO.getSgword());
		
		// 주문상태(step, step2)
		param.put("step", orderExcelVO.getStep());
		
		String tmp_step2 = "";
//		String[] step2	= req.getParameterValues("step2");
		String[] step2	= paramStep2;
		
		if( null != step2 ){
			for(int i=0; i < step2.length ; i++){
				if(i != 0){
					tmp_step2 += " or ";
				}
				
				if("1".equals(step2[i])){
					tmp_step2 += " (step=0 and step2 between 1 and 49) ";
				}else if("2".equals(step2[i])){
					tmp_step2 += " (step in (1,2) and step2!=0) OR (a.cyn='r' and step2='44' and a.dyn!='e') ";
				}else if("3".equals(step2[i])){
					tmp_step2 += "(step in (3,4) and step2!=0)";
				}else if("60".equals(step2[i])){
					tmp_step2 += "(a.dyn='e' and a.cyn='e')";
					
					//requestSet.setProperty("tbl_sgword", " left join gd_order_item c on a.ordno=c.ordno");
					//tbl_sgword = " left join gd_order_item c on a.ordno=c.ordno";
					
				}else if("61".equals(step2[i])){
					tmp_step2 += "oldordno != ''";
				}else{
					tmp_step2 += "step2= " + step2[i];
				}
				
			}
		}
		
		param.put("step2", tmp_step2);
		
		if(-1 < orderExcelVO.getStep2().indexOf("60")){
			param.put("step2_60", "step2_60");
		}
		
		//초기 처리일자 조회구분(주문일, 결제확인일, 배송일, 배송완료일)
		param.put("dtkind", StringUtil.nvl(orderExcelVO.getDtkind() , "orddt"));
		
		//초기 처리일자 값세팅
		if(!"".equals( StringUtil.nvl( orderExcelVO.getPeriod(), "") )){
			//초기날짜등록
			param.put("sregdt_0", DateUtil.getDateFrom("yyyyMMdd","-"+StringUtil.nvl( orderExcelVO.getPeriod(), "0")+"d") );
			param.put("sregdt_1", DateUtil.getDateString() );
		}else{
		
			//처리일자 세팅
			param.put("sregdt_0", orderExcelVO.getSregdt() != null ? orderExcelVO.getSregdt()[0] : "" );
			param.put("sregdt_1", orderExcelVO.getSregdt() != null ? orderExcelVO.getSregdt()[1] : "" );
		}
		
		orderExcelVO.setDtkind( (String)param.get("dtkind") );
		orderExcelVO.setSregdt(new String[]{(String)param.get("sregdt_0"), (String)param.get("sregdt_1")});
		
		//결제방법
		param.put("settlekind", orderExcelVO.getSettlekind());
		
		//쿠폰사용여부
		param.put("couponyn", orderExcelVO.getCouponyn());
		
		//현금영수증신청여부
		param.put("cashreceipt", orderExcelVO.getCashreceipt());
		
		if ("order".equals(orderExcelVO.getMode())) {	
			return mapper.getOrderXls(param);		// 주문별 다운로드
		} else {
			return mapper.getOrderGoodsXls(param);	// 상품별 다운로드
		}
	}
	
	public List<OrderCancel> getOrderCancelList(OrderCSVO csVO) {
		String skey = csVO.getSkey();
		String sword = csVO.getSword();
		String settlekind = StringUtil.nullConv(csVO.getSettlekind(),"");
		String[] regdtArray	= new String[]{StringUtil.nvl(csVO.getRegdt1(), ""), StringUtil.nvl(csVO.getRegdt2(), "")};
		String[] typeArr = csVO.getType();
		String sqlSword = "";
		
		// ***dynamic query***
		// 검색조건 > 검색어(주문검색[통합])
		if (StringUtils.hasText(sword)) {
			String tmpSword = "";
			tmpSword = ("all".equals(skey)) ? "concat( a.ordno, ifnull(d.m_id,''), nameOrder, bankSender )" : skey ; 
			sqlSword = tmpSword+ " like '%" + csVO.getSword() + "%'";
		}
		
		String tmp_type = "";
		
		// 검색조건세팅 > 분류선택 데이터 셋팅 [주문상태-type]
		if ( null != typeArr ) {
			for(int i = 0, size = typeArr.length; i < size ; i++){
				if(i != 0) {
					tmp_type += " or ";
				}
				tmp_type += " istep= " + typeArr[i];
			}
		}
		
		String sqlType = "";
		if(!"".equals(tmp_type)){
			sqlType = tmp_type;
		}
		
		String sqlRegdt = "";
		if ( !"".equals(regdtArray[0]) && !"".equals(regdtArray[1]) ){
			sqlRegdt = "orddt between date_format(" + regdtArray[0] + ",'%Y-%m-%d 00:00:00')" + "and date_format( " + regdtArray[1] + ",'%Y-%m-%d 23:59:59')";
		}

		String sqlSettlekind = "";
		// 검색조건 > 검색어 (결제방법)
		if ( !"".equals(settlekind) ) {
			sqlSettlekind = " settlekind = '" + csVO.getSettlekind() + "'";
		}
		
		
		GdOrderCancelSqlVO sqlVO = new GdOrderCancelSqlVO();
		sqlVO.setSchRegdt(sqlRegdt);
		sqlVO.setSchSettlekind(sqlSettlekind);
		sqlVO.setSchSword(sqlSword);
		sqlVO.setSchType(sqlType);
		sqlVO.setSchSellerCd(csVO.getSchSellerCd());
		
		csVO.setPageSize(15);
		csVO.setRowCount(this.mapper.cancelListCnt1(sqlVO));
		return this.mapper.cancelList1(sqlVO);
	}

	/*
	 * 주문 반품/교환접수리스트
	 */
	public List<Map<String, Object>> getOrderRegoodsList(OrderRegoodsVO vo) throws Exception{
		vo.setRowCount(mapper.getOrderRegoodsListCount());
		
		List<Map<String, Object>> resultList = null;
		
		resultList = mapper.getOrderRegoodsList(vo);
		
		for(Map<String,Object> regoodsMap : resultList){
			Map<String,Object> map = mapper.getOrderRegoodsInfoList(Integer.parseInt(String.valueOf(regoodsMap.get("sno"))));
			String [] allCoupon = this.getAllCouponPrice(String.valueOf(map.get("ordno")));
			
			if(allCoupon.length > 0 ){
				int price = Integer.parseInt(StringUtil.nvl(map.get("price"), "0"));
				int ea = Integer.parseInt(StringUtil.nvl(map.get("ea"),"0"));
				int coupon = Integer.parseInt(String.valueOf(map.get("coupon")));
				coupon += this.getAllCouponPercentPrice(allCoupon, price * ea);
				map.put("coupon", coupon);
			}
			logger.debug("map >> " + map);
			int addoptValue = ShopLibFunction.getTotalOptionPrice(String.valueOf(map.get("addopt")));
			map.put("addoptValue", addoptValue);
			regoodsMap.put("infoList", map);
		}
		return resultList;
	}

	/*
	 * 주문 반품/교환접수 상품리스트 > 반품완료 UPDATE
	 */
	public void updateOrderRegoods(OrderRegoodsVO vo) throws Exception{
		mapper.updateOrderRegoodsItem(vo.getSno());
		vo.setOrdno(mapper.getOrderRegoodsCancel(vo.getSno()));
		mapper.updateOrderRegoods(vo.getOrdno());
		//재고 조정
		ShopLibFunction.setStock(vo.getOrdno());

		// 환불 시 적립금 환불 중복 적용을 차단하기 위해 주석처리
		/*
		Map<String, Object> orderObj = null;
		orderObj = mapper.getOrderInfo(vo.getOrdno());
		
		if(orderObj != null){
			ShopLibFunction.setEmoney(-Integer.parseInt(String.valueOf(orderObj.get("reserve"))), 
					Integer.parseInt(String.valueOf(orderObj.get("m_no"))), vo.getOrdno(), "반품에 의한 적립금 회수");
		}
		int reserve = ShopLibFunction.getReserve(vo.getGoodsno()) * vo.getEa();
		if(reserve > 0){
			ShopLibFunction.setEmoney(-reserve,Integer.parseInt(String.valueOf(orderObj.get("m_no"))), vo.getOrdno(), "반품에 의한 적립금 회수");
		}*/
		
	}
	/*
	 * 주문 반품/교환접수 상품리스트 > 교환완료 UPDATE
	 */
	public void updateOrderExchange(OrderRegoodsVO vo) throws Exception{
		mapper.updateOrderItemExchange(vo.getSno());
		vo.setOrdno(mapper.getOrderExchangeCancel(vo.getSno()));
		mapper.updateOrderExchange(vo.getOrdno());
		//재고 조정
		ShopLibFunction.reorder(vo.getOrdno(), vo.getSno());

		//교환일때 환불접수리스트로 넘어가지 않는데 적립금을 회수해야 하는지 확인 필요(주석처리)
//		Map<String, Object> orderObj = null;
//		orderObj = mapper.getOrderInfoList(vo.getOrdno());
//		if(orderObj != null){
//			if ("0".equals(ShopConfig.getProperty("emoney_limit")) 
//					|| 0 == Integer.parseInt(String.valueOf(orderObj.get("emoney")))){
//				
//				ShopLibFunction.setEmoney(-Integer.parseInt(String.valueOf(orderObj.get("reserve"))), 
//						Integer.parseInt(String.valueOf(orderObj.get("m_no"))), vo.getOrdno(), "교환에 의한 적립금 회수");
//			}
//		}
		
	}

	
	/**
	 * 주문 수정
	 */
	/*
	public void excuteOrderModify(OrderCancelVO cancelVO) throws Exception {
		//파라미터 설정
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("step", cancelVO.getStep());	
		param.put("step2", cancelVO.getStep2());	
		param.put("ordno", cancelVO.getOrdno());	
		
		String[] arr_sno	= cancelVO.getSno();
		String[] arr_ea		= cancelVO.getEa();
		String[] arr_price	= cancelVO.getPrice();
		String[] arr_supply	= cancelVO.getSupply();
		
		String goodsno = "";
		int memberDC = 0; 	//회원할인가
		int priceSum = 0;		//상품금액
		int length = 0 ;		//전체쿠폰 개수
		String allCoupon = "";	//전체쿠폰
		
		List<Map<String, String>> couponInfo = mapper.orderModifyDC1(cancelVO.getOrdno());
		for(Map<String, String> cou : couponInfo){
			//전체쿠폰이 존재하면 저장
			if("전체".equals(cou.get("goodsno"))){
				allCoupon += cou.get("price");
				if(0 < length){
					allCoupon += ",";
				}
				length ++;
			}
		}
		//회원할인율 정보
		int mDC = mapper.orderModifyDC2(cancelVO.getUserNm());
		int coupon = 0; // 쿠폰할인액 
		int memdc = mDC * 0.01; // 회원할인율
		//주문상세내역 수정 
		if (arr_sno != null && arr_sno.length > 0) {
			for ( int i = 0 ; i < arr_sno.length ; i++ ) {
				// 주문상품 수량 변경시 재고 수정
				param.put("sno", Integer.parseInt(arr_sno[i]));	
				GdOrderItem orderItem = mapper.orderModify1(param);	// SELECT gd_order_item
				if (logger.isDebugEnabled()) logger.debug("#####[CAR] orderItem : " + orderItem.toString());
				//주문상태가 주문접수이고 수량의변화가 있을 때 또는 가격의변화가있을 때 또는 매입가의 변화가 있을때
				if ("0".equals(cancelVO.getStep()) 
						&& ( !arr_ea[i].equals(String.valueOf(orderItem.getEa())) 
						  || !arr_price[i].equals(String.valueOf(orderItem.getPrice()))
						  || !arr_supply[i].equals(String.valueOf(orderItem.getSupply())) 
						  && !"0".equals(cancelVO.getStep2()) ) ) {
					if (logger.isDebugEnabled()) logger.debug("#####[CAR] 주문내역변경은 주문접수상태에서 가능 "+cancelVO.getStep2());
					goodsno = String.valueOf(orderItem.getGoodsno());
					// 상품 갯수 변경
					if (!arr_ea[i].equals(String.valueOf(orderItem.getEa()))) {
						int imode = ("n".equals(orderItem.getStockyn()) ? 1 : -1);

						if(imode == -1){
							param.put("goodsno", orderItem.getGoodsno());
							param.put("opt1", StringUtil.nullConv(orderItem.getOpt1(), ""));
							param.put("opt2", StringUtil.nullConv(orderItem.getOpt2(), ""));
	
							int cstock = mapper.orderModify2(param);	// SELECT gd_goods_option
							if(logger.isDebugEnabled()) logger.debug("####[excuteModify] update stock before:"+ cstock);
							cstock = cstock +( imode * ( Integer.parseInt(arr_ea[i]) - orderItem.getEa() ) );
							if(logger.isDebugEnabled()) logger.debug("####[excuteModify] update stock after:"+ cstock);

							if (cstock < 0 ) {
								cstock = 0;
							}
							param.put("cstock", cstock);
							mapper.orderModify3(param);	// UPDATE gd_goods_option
						}
					}
					//합계 가격 구하기
					priceSum = Integer.parseInt(arr_ea[i]) * StringUtil.N2D(arr_price[i]);
					//할인가
					memberDC = priceSum * memdc;
					//쿠폰정보
					for(Map<String, String> cou : couponInfo){
						if(goodsno.equals(cou.get("goodsno"))){
							coupon = priceSum - ShopLibFunction.getDcprice(Double.toString((priceSum)) ,cou.get("price"));
						} 						
					}
					param.put("ea", 	Integer.parseInt(arr_ea[i]));
					param.put("price",	StringUtil.N2D(arr_price[i]));
					param.put("supply", StringUtil.N2D(arr_supply[i]));
					param.put("coupon", coupon);
					param.put("memberdc", Math.round(memberDC));
					mapper.orderModify4(param);	// UPDATE gd_order_item
				}
			}
		}
		// gd_order Update
		int totalPrice = 0; // 상품총금액
		int totalAddOption = 0; // 추가옵션 금액 합계
		int totalCoupon = 0; // Coupon 합계
		int totalDC = 0; // memberDC 합계
		String couponPrice[] = allCoupon.split(",");

		// gd_order_item List
		List<GdOrderItem> itemInfo = fncmapper.setPrnSettleprice1(Long
				.parseLong(cancelVO.getOrdno()));
		for (GdOrderItem orderItem : itemInfo) {
			totalAddOption += ShopLibFunction.getTotalOptionPrice(orderItem
					.getAddopt());
			totalCoupon += orderItem.getCoupon();
			totalPrice += orderItem.getEa() * orderItem.getPrice();
			totalDC += orderItem.getMemberdc();
		}
		// 전체쿠폰
		if (couponPrice != null && 0 < couponPrice.length) {
			int addCoupon = 0;
			for (String idx : couponPrice) {
				addCoupon = totalPrice - ShopLibFunction.getDcprice(String.valueOf(totalPrice), idx);
			}
			totalCoupon += addCoupon;
		}
		// 상품총금액
		totalPrice += totalAddOption;
		// 상품결제금액
		int prn_settlePrice = 0;

		long ordno = Long.parseLong((String) param.get("ordno"));
		Map<String, Object> orderInfo = mapper.getOrderInfo(ordno);
		int gd_delivery = StringUtil.N2D(String.valueOf(orderInfo.get("delivery"))); // 배송비
		int gd_addDelivery = StringUtil.N2D(String.valueOf(orderInfo.get("addDelivery")));// 추가배송비
		int gd_emoney = StringUtil.N2D(String.valueOf(orderInfo.get("emoney"))); // 적립금
		// 상품결제금액 = 상품총금액 - 쿠폰가 - 회원할인 - 적립금 + 배송비 + 추가배송비
		prn_settlePrice = totalPrice - totalCoupon - totalDC - gd_emoney + gd_delivery + gd_addDelivery;

		logger.debug("******************************************");
		logger.debug("최종수정값 >>> " + prn_settlePrice);
		logger.debug("******************************************");

		String deliverycode = cancelVO.getDeliverycode().replaceAll("-","");
		param.put("enuri", 		Integer.parseInt(cancelVO.getEnuri().equals("") ? "0" : cancelVO.getEnuri()));
		param.put("zipcode", 		cancelVO.getZipcode());
		param.put("address", 		cancelVO.getAddress());
		param.put("address2", 		cancelVO.getAddress2());
		param.put("city", 			cancelVO.getCity());
		param.put("state", 			cancelVO.getState());
		param.put("memo", 			cancelVO.getMemo());
		param.put("customs_num",	cancelVO.getCustoms_num());
		param.put("adminmemo", 		cancelVO.getAdminmemo());
		param.put("settlelog", 		cancelVO.getSettlelog());
		param.put("bankAccount", 	Integer.parseInt(StringUtil.nvl(cancelVO.getBankaccount(), "0")));
		param.put("bankSender", 	cancelVO.getBankSender());
		param.put("nameReceiver", 	cancelVO.getNameReceiver());
		param.put("phoneReceiver", 	cancelVO.getPhoneReceiver());
		param.put("mobileReceiver", cancelVO.getMobileReceiver());
		param.put("mobileReceiverType", cancelVO.getMobileReceiverType());
		param.put("country",		cancelVO.getCountry());
		param.put("deliverycode", 	deliverycode);
		param.put("coupon", 		totalCoupon);
		param.put("goodsprice", totalPrice);
		param.put("memberdc", totalDC);
		param.put("prn_settleprice", prn_settlePrice);
		mapper.orderModify5(param);	// UPDATE gd_order
		
		this.updateDeliveries(cancelVO);
		
	}
	*/
	public void excuteOrderModify(OrderCancelVO cancelVO) throws Exception {
		//파라미터 설정
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("step", cancelVO.getStep());	
		param.put("step2", cancelVO.getStep2());	
		param.put("ordno", cancelVO.getOrdno());	
		param.put("zipcode", 		cancelVO.getZipcode());
		param.put("address", 		cancelVO.getAddress());
		param.put("address2", 		cancelVO.getAddress2());
		param.put("city", 			cancelVO.getCity());
		param.put("state", 			cancelVO.getState());
		param.put("memo", 			cancelVO.getMemo());
		param.put("customs_num",	cancelVO.getCustoms_num());
		param.put("adminmemo", 		cancelVO.getAdminmemo());
		param.put("settlelog", 		cancelVO.getSettlelog());
		param.put("bankAccount", 	Integer.parseInt(StringUtil.nvl(cancelVO.getBankaccount(), "0")));
		param.put("bankSender", 	cancelVO.getBankSender());
		param.put("nameReceiver", 	cancelVO.getNameReceiver());
		param.put("mobileReceiver", cancelVO.getMobileReceiver());
		param.put("mobileReceiverType", cancelVO.getMobileReceiverType());
		param.put("country",		cancelVO.getCountry());
		param.put("deliverycode", 	cancelVO.getDeliverycode().replaceAll("-",""));
		mapper.orderModify5(param);	// UPDATE gd_order
		
		this.updateDeliveries(cancelVO);
	}
	
	/** 송장번호 및 택배사 정보 배송정보 테이블에 업데이트 */
	private void updateDeliveries(OrderCancelVO vo) {
		OrderDeliveryVO delivery = new OrderDeliveryVO();
		delivery.setInvoice(isNoData(vo.getDeliverycode()) ? null : vo.getDeliverycode()); // 송장번호
		delivery.setDeliveryCompCd(isNoData(vo.getDeliveryno()) ? null : vo.getDeliveryno()); // 배송비
		delivery.setOrdno(vo.getOrdno()); // 주문 별 주문번호
		delivery.setLevel(null);
		
		//배송완료일경우 배송완료일자 업데이트
		if( "4".equals(vo.getStep()) ) {
			delivery.setLevel("6");
		}
		this.updateDeliveryInfo(delivery);
		
	}
	
	private boolean isNoData(String data) {
		return "0".equals(data) || "".equals(data) || data == null;
	}
	
	/**
	 * 주문리스트에서 주문상태 변경처리시
	 * @throws Exception 
	 * */
	public void changeOrderStep(OrderListStepChangeVO orderListStepChangeVO) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("** OrderService >> changeOrderStep");
		}
		
		// 변경할 주문번호 (ordno) 배열
		String [] ordnos = orderListStepChangeVO.getOrdno().split(",");
		int ordnoCount = ordnos.length;
		
		// 차례대로 주문상태를 변경한다.
		for(int i = 0; i < ordnoCount; i++) {
			ShopLibFunction.ctlStep(ordnos[i], orderListStepChangeVO.getChangeStep());
		}
	}
	
	/**
	 * 주문상세 출력 > reception
	 * */
	public void orderPrintReception(OrderPrintVO printVO) {
		printVO.setClassids(new String[] {"cssblue" , "cssred"});
		printVO.setHeaduser(new String[] {"공급받는자용", "공급자용"});
		Long ordno = printVO.getOrdno();
		List<PopupOrdersRecovery1> orderItemList = this.mapper.paperReception(ordno);
		printVO.setOrderItemList(orderItemList);
		int orderItemSize = orderItemList.size();
		if(orderItemList != null && orderItemSize > 0) {
			int[] price = new int[orderItemSize];
			int itemPrice = 0;
			for(int i = 0; i < orderItemSize; i++) {
				PopupOrdersRecovery1 orderItem = orderItemList.get(i);
				price[i] = orderItem.getPrice() - orderItem.getMemberdc() - orderItem.getCoupon();
				itemPrice += price[i] * orderItem.getEa();
			}
			
			printVO.setItemPrice(itemPrice);
			printVO.setPrice(price);
		}
		
		printVO.setCompSerial(ShopConfig.getProperty("compSerial"));
		printVO.setCompName(ShopConfig.getProperty("compName"));
		printVO.setCeoName(ShopConfig.getProperty("ceoName"));
		printVO.setAddress(ShopConfig.getProperty("address"));
		printVO.setService(ShopConfig.getProperty("service"));
		printVO.setItem(ShopConfig.getProperty("item"));
	}
	
	/**
	 * 선택한주문정보 가져오기 
	 * @param ordno
	 * @return
	 */
	public List<FrontOrderSettleVO> getOrderInfo3(OrderListStepChangeVO ordno){
		
		List<FrontOrderSettleVO> vo = mapper.getOrderInfo3(ordno);
		
		return vo;
	}

	/**
	 * 구매정보 세팅
	 * @throws Exception 
	 * */
	/*public void setOrderGoodsInfo(List<PopupOrder1> popupVOList, int icnt) {
		PriceInfoVO totalPriceInfo = new PriceInfoVO();
		List<EachPriceInfoVO> eachPriceInfoList = new ArrayList<EachPriceInfoVO>();
		int icancel = 0;
		
		for(PopupOrder1 order1 : popupVOList) {
			EachPriceInfoVO eachPriceInfo = new EachPriceInfoVO();
			if((icnt == 0) || (order1.getIstep() < 40)) {
				eachPriceInfo.setEa(order1.getEa());		// 갯수 세팅
				eachPriceInfo.setPrice(Integer.parseInt(order1.getPrice());	// 상품 원가 세팅
				
				goodsPrice += Integer.parseInt(order1.getPrice()) * ea;
				memberDC += order1.getMemberdc() * ea;
				coupon += Integer.parseInt(order1.getCoupon()) * ea;
			}
			
			String bgColor = "#ffffff";
			if(order1.getIstep() > 40) {
				icancel++;
				bgColor = "#F0F4FF";
			} 
			
			if(!"".equals(order1.getDvcode())) {
				cntDv++;
			}
			
			order1.setBgColor(bgColor);
			order1.setSubImgs(order1.getImgs().replace("|", ""));
		
			order1.setMaker(StringUtils.hasText(order1.getMaker()) ? order1.getMaker() : "없음");
			order1.setBrandnm(StringUtils.hasText(order1.getBrandnm()) ? order1.getBrandnm() : "없음");
			order1.setrIstep(ShopLibFunction.r_istep(String.valueOf(order1.getIstep())));
			order1.setDvno(!"".equals(order1.getDvcode()) || !"0".equals(order1.getDvcode()) && false ? "-" : String.valueOf(order1.getDvno()));
			
		}
		popupVO.setGoodsPrice(goodsPrice);
		popupVO.setMemberDc(memberDC);
		popupVO.setCoupon(coupon);
		popupVO.setiCancel(icancel);
		popupVO.setWskin(wskin);

		//할인액 계산
		popupVO.setDiscount(memberDC + rtData.getEmoney() + rtData.getCoupon() + rtData.getEnuri());
		//실데이타 계산으로 결제금액 산출
		popupVO.setSettleprice(goodsPrice + rtData.getDelivery() - popupVO.getDiscount() + rtData.getEggfee());

	}*/
	
	// 로그인 여부 체크
	public void loginAndPermissionCheck(HttpServletRequest req, HttpServletResponse res) throws Exception {
		AdminSessionObject adminSO = AdminSessionObject.getSessionObject(req);
		PrintWriter out = null;
		if( adminSO.isLogin() && null != adminSO.getUserInfo() ){
			Map<String, Object> xkey = adminSO.getUserInfo().getXkey();
			String level = String.valueOf(xkey.get("level"));
			
			if (logger.isDebugEnabled()) {
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>level" + level);
			}
			// 로그인시 권한체크
			if( 0 > ShopConfig.getProperty("level"+level).indexOf("order")){
				if("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))){
					throw new BizException("member.00001", CommonConstants.MAIN_ADMIN_URL);	// 회원관리 권한이 없습니다.
				}
				
				try{
					res.setCharacterEncoding("UTF-8");
					res.setContentType("text/html;charset=UTF-8");
					out = res.getWriter();
					out.print(WebUtil.getAlertRedirect("주문관리 권한이 없습니다.", CommonConstants.MAIN_ADMIN_URL));
					out.flush();
				}catch(Exception e){
					throw e;
				} finally{
					if( null != out ){
						out.close();
					}
				}			
			}
		}
	}
	
	/**
	 * 2020-03-26 이현빈
	 * 정율 전체쿠폰 계산 
	 * @param String ordno
	 * @param gdOrderItem item
	 * @param String[] allCoupon
	 * @return
	 * @throws Exception 
	 */
	public int getAllCouponPercentPrice(String[] allCoupon, int price) throws Exception{
		if(logger.isDebugEnabled()) logger.debug("OrderService >> getAllCouponPercentPrice");
		int result = 0;
		//없는 데이터에 대한 null 처리또한 필요한지 확인
		//전체쿠폰 할인율이 % 이면 실행 가격이면 실행하지않는다.
		
		//환불 상품이 3개가 있는데 istep 으로만 기준을정하고 flag 로 만 하나만 띄운다면 ?
		
		//전체상품 쿠폰 할인율 조회 한번만 실행하면 되기때문에 getAllCouponPrice는 밖에서 실행하고 매개변수로 가져온다. 
		
		//해당상품 price, ea 조회
		
		for(String s : allCoupon){
			if(s.contains("%")){ 
				int couponPrice = price - ShopLibFunction.getDcprice(String.valueOf(price), s);
				logger.debug("couponPrice > " + couponPrice);
				result += couponPrice;
			}
		}
		
		//1. 한 아이템에 대하여 상품 전체쿠폰 계산을 해주는 로직을 짠다.
		//2. 환불 gd_order에 합산한 쿠폰 가격을 더한다 .
		//3. gd_order_item 에 있는 할인가는 변하지 않아야 한다. 
		//4. 취소를 하면 gd_order에 있는 쿠폰가격은 변하는게 맞다 ? 
		//5. 
		return result;
	}
	/**
	 * 2020-03-26 이현빈
	 * 사용한 전체쿠폰 가격 계산
	 * @param String ordno
	 * @param String[] allCoupon
	 * @return 
	 * @throws Exception 
	 */
	public int addAllCouponPrice(String ordno ,String[] allCoupon) throws Exception{
		if(logger.isDebugEnabled()) logger.debug("OrderService >> addAllCouponPrice");
		int result = 0;
		if(allCoupon.length > 0 && allCoupon != null){
			for(String s : allCoupon){
				if(!s.contains("%")){
					int couponPrice = -ShopLibFunction.getDcprice(String.valueOf(0), s);
					result += couponPrice;
				}
			}
		}
		return result;
	}
	
	public int updateCancelStep(PopupOrder1 popupOrder1) throws Exception{
		return mapper.updateCancelStep(popupOrder1);
	}
	
}

