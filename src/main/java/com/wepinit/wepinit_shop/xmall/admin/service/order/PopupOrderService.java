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

import com.wepinit.wepinit_shop.xcube.util.CodeUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xcube.util.WebUtil;
import com.wepinit.wepinit_shop.xmall.admin.vo.order.OrderCancelVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.order.OrderDeliveryVO;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCode;
import com.wepinit.wepinit_shop.xmall.common.vo.GdListBank;
import com.wepinit.wepinit_shop.xmall.common.vo.GdListDelivery;
import com.wepinit.wepinit_shop.xmall.common.vo.join.PopupOrder1;
import com.wepinit.wepinit_shop.xmall.common.vo.join.PopupOrder2;
import com.wepinit.wepinit_shop.xmall.admin.dao.order.OrderMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.order.*;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.service.ShopLibFncService;
import com.wepinit.wepinit_shop.xmall.common.util.BiztalkUtil;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.*;
import com.wepinit.wepinit_shop.xmall.front.service.order.FrontOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/* 2017-08-24 : OrderService 에서 분리 (지은정)
 * 주문상세페이지 컨트롤러
 * */

@Transactional
@Service
public class PopupOrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(PopupOrderService.class);

	@Resource
	OrderMapper mapper;
	
	@Resource
	FrontOrderService frontOrdService;
	
	@Resource
	ShopLibFncService shopLibFncService;
	
// ------------------------------------------------------------mapper
	
	public int getCancelNumber(String ordno){
		return mapper.getCancelNumber(ordno);
	}
	
	public String[] getCancelGoodsOfSeller(Map<String,Object> param){
		return mapper.getCancelGoodsOfSeller(param);
	}
	
	public Map<String,Object> getDeliveryInfoForInsertCancel(String sno){
		return mapper.getDeliveryInfoForInsertCancel(sno);
	}
	
	public void updateDeliveryPrice1(Map<String,Object> param){
		mapper.updateDeliveryPrice1(param);
	}
	
	public int orderChkCancel10(String ordno){
		return mapper.orderChkCancel10(ordno);
	}
	
	public String getSellerCdForCancel (String sno){
		return this.mapper.getSellerCdForCancel(sno);
	}
	public int checkAllCancelOfSellerGoods(Map<String,Object> param){
		return this.mapper.checkAllCancelOfSellerGoods(param);
	}
	public Map<String,Object> getRecoveryDelivery(String sno){
		return this.mapper.getRecoveryDelivery(sno);
	}
	public Map<String,Object> getDeliveryPriceInfo(Map<String,Object> param){
		return this.mapper.getDeliveryPriceInfo(param);
	}
	public int getItemGoodsno(String sno){
		return this.mapper.getItemGoodsno(sno);
	}
	
	/** 택배회사 리스트 */
	public List<GdListDelivery> ordersPopupOrder3() {
		return this.mapper.ordersPopupOrder3();
	}
	
	/** 계좌리스트 */
	public List<GdListBank> ordersPopupOrder4() {
		return this.mapper.ordersPopupOrder4();
	}
	
	/** 주문번호로 상품조회 */
	public List<PopupOrder1> ordersPopupOrder1(long ordno) {
		return this.mapper.ordersPopupOrder1(ordno);
	}
	
	/** 주문번호로 주문자 정보조회 */
	public PopupOrder2 ordersPopupOrder2(long ordno) {
		return this.mapper.ordersPopupOrder2(ordno);
	}
	
	/** 주문번호로 주문한 상품갯수 조회 */
	public int ordersPopupOrder5(long ordno) {
		return this.mapper.ordersPopupOrder5(ordno);
	}
	/** 세금관리 테이블 */
	public List<GdTax> paperTaxState(long ordno) {
		return this.mapper.paperTaxState(ordno);
	}
	
	public int ordersPopupOrder6(long ordno) {
		return this.mapper.ordersPopupOrder6(ordno);
	}
	
	public List<Long> ordersPopupOrder7(long ordno) {
		return this.mapper.ordersPopupOrder7(ordno);
	}

	public List<PopupOrder3> ordersPopupOrder8(long ordno) {
		return this.mapper.ordersPopupOrder8(ordno);
	}
	
	public List<PopupOrder4> ordersPopupOrder9(long ordno) {
		return this.mapper.ordersPopupOrder9(ordno);
	}
	
	public List<GdOrderItem> ordersPopupOrder10(int cancel) {
		return this.mapper.ordersPopupOrder10(cancel);
	}
	/**
	 * ordno 에 해당하는 주문 취소 정보를 가져온다.
	 * */
	public List<GdOrderCancel> ordersPopupOrder11(long ordno) {
		return this.mapper.ordersPopupOrder11(ordno);
	}
	
	public List<GdLogCancel> ordersPopupOrder12(int cancel) {
		return this.mapper.ordersPopupOrder12(cancel);
	}
	
	// 주문리스트 > 주문상세내역 > 주문취소/반품처리
	public List<Map<String, Object>> ordersCancel1(Map<String, Object> param) throws Exception {
		return mapper.ordersCancel1(param);
	}
	
	/** 주문처리Log내역 */
	public List<Map<String, Object>> orderLogList(Map<String, Object> param) throws Exception {
		return mapper.orderLogList(param);
	}
	
	public PopupOrdersRecovery1 ordersRecovery1(int sno) {
		return this.mapper.ordersRecovery1(sno);
	}
	
	public Integer ordersRecovery2(Integer mno) {
		return this.mapper.ordersRecovery2(mno);
	}
	
	public Integer ordersRecovery3(Map<String, Object> map) {
		return this.mapper.ordersRecovery3(map);
	}

	public void ordersRecovery4(Map<String, Object> map) {
		this.mapper.ordersRecovery4(map);
	}
	
	public void ordersRecovery5(int sno2) {
		this.mapper.ordersRecovery5(sno2);
	}
	
	public void ordersRecovery6(Map<String, Object> param) {
		this.mapper.ordersRecovery6(param);
	}
	
	public void ordersRecovery7(Map<String, Object> param) {
		this.mapper.ordersRecovery7(param);
	}
	
	public void ordersRecovery8(Map<String, Object> param) {
		this.mapper.ordersRecovery8(param);
	}
	
	public void ordersRecovery9(long ordno) {
		this.mapper.ordersRecovery9(ordno);
	}
	
	public void ordersRecovery10(Map<String, Object> param) {
		this.mapper.ordersRecovery10(param);
	}
	
	public void ordersRecovery11(Map<String, Object> param) {
		this.mapper.ordersRecovery11(param);
	}
	
	/** 주문상세 출력 */
	public List<Long> orderPrintList(Map<String, Object> param) {
		return mapper.orderPrintList(param);
	}
	
	public int paperTaxCount(Long ordno) {
		return mapper.paperTaxCount(ordno);
	}
	
	/** 주문상세페이지 배송정보 테이블 가져오기 */
	public List<OrderDeliveryVO> selectDeliveryInfo(Long ordno) {
		return this.mapper.selectDeliveryInfo(ordno);
	}
	
	public OrderDeliveryVO selectPopupDeliveryInfo(Long ordno) {
		return this.mapper.selectPopupDeliveryInfo(ordno);
	}
	
	public List<GdListDelivery> getUseDeliveryCompList(String value) {
		return this.mapper.getUseDeliveryCompList(value);
	}
	
	public Map<String, Object> orderSetPrnSettleprice2(Map<String, Object> param) {
		return this.mapper.orderSetPrnSettleprice2(param);
	}
// ------------------------------------------------------------service
	public Properties getBankProperties() throws Exception {
		Properties bankPro = new Properties();
		List<GdCode> rtBank = CodeUtil.codeitem("bank");
		if(rtBank != null && rtBank.size() > 0) {
			for(GdCode gdCode : rtBank) {
				bankPro.setProperty(gdCode.getItemcd(), gdCode.getItemnm());
			}
		}
		
		return bankPro;
	}
	
	/**
	 * 주문상세 출력 > report
	 * @throws Exception 
	 * */
	public void orderPrintReport(OrderPrintVO printVO) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>orderService orderPrintReport");
		}
		
//		//### 은행 배열생성
//		Properties bankPro = this.getBankProperties();

//		//### 주문리스트 리퍼러
		String referer = !"".equals(printVO.getReferer()) ? printVO.getReferer() : "" ;
		printVO.setReferer(referer);
		
//		//### 배송업체 정보
		List<GdListDelivery> tmpDeli = this.ordersPopupOrder3();
		printVO.setTmpDeli(tmpDeli);

//		//### 입금계좌 정보
		List<GdListBank> tmpBank = this.ordersPopupOrder4();
		printVO.setTmpBank(tmpBank);
		Long ordno = printVO.getOrdno();
//		// 상품리스트
		List<PopupOrder1> rtList = this.ordersPopupOrder1(ordno);
		if(rtList.size() > 0) {
			for(PopupOrder1 order1 : rtList) {
				// 상품가격에 고객이 선택한 상품의 옵션가도 추가하도록 내용 변경
				int totalOptionPrice = ShopLibFunction.getTotalOptionPrice(order1.getAddopt());	
				if(logger.isDebugEnabled()) logger.debug("옵션가격:"+totalOptionPrice);
				order1.setAddoptPrice(Integer.toString(totalOptionPrice));
			}
		}
		printVO.setRtList(rtList);
		
//		//주문 및 주문한 회원 
		PopupOrder2 rtData = this.ordersPopupOrder2(ordno);
		printVO.setRtData(rtData);
		
		//2020.03.02 이현빈 관리자 배송정보 추가 
		printVO.setDelivery(mapper.selectDeliveryInfo(ordno));
		
//		// 정상 주문상품 갯수 구하기
		int icnt = this.ordersPopupOrder5(ordno);
		printVO.setiCnt(icnt);
//		// 주문단계정보
		LinkedHashMap<String, String> lhm = ShopLibFunction.getArrStep();
		
//		//세금계산서
		List<GdTax> rtTax = this.paperTaxState(ordno);
		printVO.setRtTax(rtTax);
		
		int goodsPrice 	= 0;
		int memberDC 	= 0;
		int coupon 		= 0;
		int cntDV 		= 0;
		if(rtList != null && rtList.size() > 0) {
			
			for(PopupOrder1 order1 : rtList) {
				//정상주문 상품만 계산
				if(ShopLibFunction.getNormalIstep(order1.getIstep())) {
					goodsPrice 	+= (StringUtil.N2D(order1.getPrice()) * order1.getEa()) + StringUtil.N2D(order1.getAddoptPrice());
					memberDC 	+= StringUtil.N2D(order1.getMemberdc());
					// coupon 가격에 왜 개수를 곱하는건지 확인해볼 필요 있음
					coupon 		+= StringUtil.N2D(order1.getCoupon()) * order1.getEa();
				}
				
				if(!"".equals(order1.getDvcode())) {
					cntDV++;
				}
				
				order1.setrIstep(ShopLibFunction.r_istep(String.valueOf(order1.getIstep())));
				
				//### 할인액 계산
				printVO.setDiscount(memberDC + rtData.getEmoney() + rtData.getCoupon() + rtData.getEnuri());

				//### 실데이타 계산으로 결제금액 산출
				printVO.setSettleprice(goodsPrice + rtData.getDelivery() - printVO.getDiscount() + rtData.getEggfee());
				
			}
			
			printVO.setGoodsPrice(goodsPrice);
			printVO.setMemberDC(memberDC);
			printVO.setCoupon(coupon);
			printVO.setCntDV(cntDV);
			printVO.setDeliveryBasis(ShopConfig.getProperty("delivery.basis"));
			
		}
		List<Map<String, String>> tmpDeliMapList = new ArrayList<Map<String,String>>();
		for(GdListDelivery delivery : tmpDeli) {
			Map<String, String> deliMap = new HashMap<String, String>();
			deliMap.put("deliveryno", String.valueOf(delivery.getDeliveryno()));
			deliMap.put("deliverycomp", delivery.getDeliverycomp());
			tmpDeliMapList.add(deliMap);
		}
		printVO.setStepSelectOption(WebUtil.makeSelectOption(lhm, String.valueOf(rtData.getStep())));		
		printVO.setDeliSelectOption(WebUtil.makeSelectOption(tmpDeliMapList, "deliveryno", "deliverycomp", String.valueOf(rtData.getDeliveryno())));
		
		List<PopupOrder4> tmpRt = this.ordersPopupOrder9(ordno);
		List<PopupOrder4> tmpRt2 = new ArrayList<PopupOrder4>();
		
		for(PopupOrder4 order4 : tmpRt) {
			order4.setOrderItemList(this.ordersPopupOrder10(order4.getCancel()));
			order4.setBankPro(StringUtil.getPadString(String.valueOf(order4.getBankcode()), 2, "0"));
			tmpRt2.add(order4);
		}
		printVO.setTmpRt(tmpRt2);
		
		Map<String, String> codeMap = new HashMap<String, String>();
		List<GdCode> codeList = ShopLibFunction.codeitem("cancel");
		for(GdCode code : codeList) {
			codeMap.put(code.getItemcd(), code.getItemnm());
		}
		List<GdOrderCancel> orderCancelList = this.ordersPopupOrder11(ordno);
		List<GdOrderCancel> orderCancelList2 = new ArrayList<GdOrderCancel>();
		
		for(GdOrderCancel orderCancel : orderCancelList) {
			orderCancel.setTmpRt12(this.ordersPopupOrder12(orderCancel.getSno()));
			orderCancel.setCancelCode(codeMap.get(String.valueOf(orderCancel.getCode())));
			orderCancelList2.add(orderCancel);
		}
		
		orderCancelList = new ArrayList<GdOrderCancel>();
		for(GdOrderCancel orderCancel : orderCancelList2) {
			List<GdLogCancel> logCancelList = new ArrayList<GdLogCancel>();
			for(GdLogCancel logCancel : orderCancel.getTmpRt12()) {
				logCancel.setrStepi(ShopLibFunction.r_stepi(String.valueOf(logCancel.getPrev()), String.valueOf(logCancel.getNext())));
				logCancelList.add(logCancel);
			}
			orderCancel.setTmpRt12(logCancelList);
			orderCancelList.add(orderCancel);
		}
		printVO.setOrderCancelList(orderCancelList);			// tmp_rt
	}
	

	/**
	 * 취소완료 처리
	 * @param cancelVO
	 * @return
	 * @throws Exception
	 */
	public int orderPopupCancel(OrderCancelVO cancelVO) throws Exception {
		int result = 0;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			
			//주문 상태 step2 변경 (gd_order)
			paramMap = new HashMap<String, Object>();
			paramMap.put("ordno", cancelVO.getOrdno()); //주문번호
			paramMap.put("step2", "56"); //취소완료
			shopLibFncService.updateShopLibOrderStep(paramMap);
			
			//주문 상태 istep 변경 (gd_order_item)
			paramMap = new HashMap<String, Object>();
			paramMap.put("ordno", cancelVO.getOrdno()); //주문번호
			paramMap.put("istep", "56"); //취소완료
			shopLibFncService.updateShopLibOrderIStep(paramMap);
			
			// 취소이력 등록
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("ordno", cancelVO.getOrdno());	
			param.put("name", cancelVO.getpName());	
			param.put("code", cancelVO.getCode());	
			param.put("memo", cancelVO.getMemo());	
			this.mapper.orderChkCancel1(param);	// INSERT gd_order_cancel
					
			// 취소완료 로직
			paramMap = new HashMap<String, Object>();
			paramMap.put("ordno", cancelVO.getOrdno());
			paramMap.put("mno", cancelVO.getMno());
			result = shopLibFncService.orderCancelProc(paramMap);
			
			/* 비즈톡 발송 */
			BiztalkUtil biztalk = new BiztalkUtil();
			biztalk.sendAlimTalk(cancelVO.getOrdno(), "ritzmall_04");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void orderCancel(OrderCancelVO cancelVO) throws Exception {
		long ordno = Long.parseLong(cancelVO.getOrdno());
		// 취소/반품 처리
		this.excuteOrderCancel(cancelVO);	// as-is : chkCancel
		// 재고 조정
		//ShopLibFunction.setStock(ordno);
		// 결제금액 계산
		ShopLibFunction.setPrnSettleprice(ordno);	// as-is : set_prn_settleprice
	}
	
	public void excuteOrderCancel(OrderCancelVO cancelVO) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("PopupOrderService >> excuteOrderCancel");
		}
		this.setParamAndInsertOrderCancel(cancelVO);
		this.insertOrUpdateLogCancelAndOrderCancel(cancelVO);
	}
	
	/**
	 * sql 실행시 필요한 파라미터를 정제하고 gd_order_cancel 테이블에 주문취소건을 추가합니다.
	 * */
	private void setParamAndInsertOrderCancel(OrderCancelVO cancelVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("PopupOrderService >> setParamAndInsertOrderCancel");
		}
		String[] arr_sno = cancelVO.getSno();
		String [] name = cancelVO.getpName().split("\\,");
		String [] code = cancelVO.getCode().split("\\,");
		String [] memo = cancelVO.getMemo().split("\\,");
		String [] bankcode = cancelVO.getBankcode().split("\\,");
		String [] bankaccount = cancelVO.getBankaccount().split("\\,");
		String [] bankuser = cancelVO.getBankuser().split("\\,");
		
		for(int i=0 ; i<arr_sno.length ; i++){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("ordno", cancelVO.getOrdno());	
			param.put("name", name[i]);	
			param.put("code", code[i]);	
			param.put("memo", memo[i]);	
			param.put("bankcode", bankcode[i]);	
			param.put("bankaccount", bankaccount[i]);	
			param.put("bankuser", bankuser[i]);
			this.mapper.orderChkCancel1(param);	// INSERT gd_order_cancel
		}
	}

	/**
	 * gd_log_cancel테이블에 주문취소정보 추가, gd_order_item 주문취소정보 update.
	 * */	
	private void insertOrUpdateLogCancelAndOrderCancel(OrderCancelVO cancelVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("PopupOrderService >> insertOrUpdateLogCancelAndOrderCancel");
		}
		
		String[] arr_sno = cancelVO.getSno();
		String[] arr_ea = cancelVO.getEa();
		
		Map<String,Object> cancelMap = new HashMap<String,Object>();
		cancelMap.put("ordno", cancelVO.getOrdno());

		//gd_order_cancel 취소번호 최대값조회
		int orino_cancel = this.mapper.getCancelNumber(cancelVO.getOrdno()); // SELECT gd_order_cancel
		int idx = arr_sno.length -1;
		
		for (int i = 0; i < arr_sno.length; i++) {
			GdOrderItem orderItem = this.mapper.orderChkCancel3(arr_sno[i]);	// SELECT gd_order_item
			int no_cancel = orino_cancel;
			
			//no_cancel 은 gd_log_cancel에 대입될 값이고 orino_cancel 최대값을 대입하기 때문에
			//취소건이 두건 이상일 경우 그냥 대입하면 cancel 값이 중복되기 때문에 건수만큼 뺀 계산값으로 대입 
			if(arr_sno.length > 1){
				no_cancel = no_cancel - idx;
				idx --;
			}
			
			int istep = 0;
			int ea_3 = 0;
			if (orderItem != null) {
				istep = orderItem.getIstep(); 
				// 반품신청 || 취소요청
				if(istep == 40 || istep == 57 || istep < 40){
					istep = 56; //취소완료
				}else if(istep == 70 || istep == 60){
					istep ++ ;
				}
				ea_3 = orderItem.getEa();
				
				Map<String,Object> map = this.getDeliveryInfoForInsertCancel(arr_sno[i]);
				
				GdLogCancel params = new GdLogCancel();
				params.setOrdno(Long.valueOf(cancelVO.getOrdno()));
				params.setItemno(Integer.parseInt(arr_sno[i]));
				params.setCancel(no_cancel);
				params.setPrev(orderItem.getIstep());
				params.setNext(istep);
				params.setGoodsnm(orderItem.getGoodsnm());
				params.setGoodsnmKR(orderItem.getGoodsnmKR());
				params.setGoodsnmCN(orderItem.getGoodsnmCN());
				params.setEa(orderItem.getEa());
				params.setDeliveryPrice(StringUtil.N2I(String.valueOf(map.get("deliveryPrice"))));
				params.setAddDeliveryPrice(StringUtil.N2I(String.valueOf(map.get("addDeliveryPrice"))));
				params.setGoodsno(Integer.parseInt(String.valueOf(map.get("goodsno"))));
				params.setPaymentTerms(Integer.parseInt(String.valueOf(map.get("paymentTerms"))));
				params.setOpt(String.valueOf(map.get("opt")));
				
				// 취소로그 저장
				mapper.orderChkCancel4(params);	// INSERT gd_log_cancel
				
				int gap = ea_3 - (Integer.parseInt(arr_ea[i]));
				if (gap > 0) {
					// 주문수량과 취소수량이 불일치할 경우 주문서 분리
					this.saparateOrder(params, gap);
				} else {
					GdOrderItem gdOrderItemParam = new GdOrderItem();
					gdOrderItemParam.setSno(Integer.parseInt(arr_sno[i]));
					gdOrderItemParam.setIstep(istep);
					gdOrderItemParam.setCancel(no_cancel);					
					this.mapper.orderChkCancel7(gdOrderItemParam);	// UPDATE gd_order_item
				}
			}
			//배송정보 업데이트  
			//상품에 해당하는 판매사코드 가져오기
			String sellerCd = this.getSellerCdForCancel(arr_sno[i]);
			if("admin".equals(sellerCd)){
				//sellercd 가 공백이면 관리자 코드이므로 admin 으로 받게 해놨는데 
				//gdgoods에서 관리자 sellerCd는 공백이나 null로 되있어서 따로 설정을 해줘야 할듯 . 
			}else{
				cancelMap.put("sellerCd", sellerCd);
				//주문상품중 판매사 코드에 해당하는 상품전체수 - 취소상품수   
				int sellerCdCancelCnt = this.checkAllCancelOfSellerGoods(cancelMap);
				//sellerCdCancelCnt == 0 한 판매사 상품이 전부 취소
				if(sellerCdCancelCnt == 0){
					//주문상품에 해당판매사 상품만 조회
					String[] goodsno = mapper.getCancelGoodsOfSeller(cancelMap);
					for(String s : goodsno){
						cancelMap.put("cgoodsno", s);
						cancelMap.put("deliveryPrice", 0);
						cancelMap.put("addDeliveryPrice", 0);
						this.mapper.updateDeliveryPrice1(cancelMap);
					}
				}
			}
		}
		//gd_order_delivery 테이블 배송비 조회
		
		Map<String,Object> deliveryMap = this.getDeliveryPriceInfo(cancelMap);
		deliveryMap.put("ordno", cancelVO.getOrdno());
		//gd_order 배송비 업데이트 
		this.mapper.updateDeliveryPrice2(deliveryMap);
		
	}
	
	/**주문수량과 취소수량이 불일치할 경우 주문서를 분리합니다.*/
	private void saparateOrder(GdLogCancel params, int gap) {
		if (logger.isDebugEnabled()) {
			logger.debug("PopupOrderService >> saparateOrder");
		}
		mapper.orderChkCancel5(params);	// INSERT gd_order_item
		params.setEa(gap);
		mapper.orderChkCancel6(params);	// UPDATE gd_order_item
	}
	
	private boolean isEmoneyRefundable(GdOrder order) {
		return order.getMno() != 0 && order.getEmoney() != 0 && order.getStep() < 1
				? true : false;
	}
	
	private void refundEmoney(GdOrder order, String message) {
		if (logger.isDebugEnabled()) {
			logger.debug("PopupOrderService >> refundEmoney");
		}
		GdLogEmoney logEmoney = new GdLogEmoney();
		logEmoney.setMno(order.getMno());
		logEmoney.setOrdno(order.getOrdno());
		logEmoney.setEmoney(order.getEmoney());
		logEmoney.setMemo(message);
		mapper.orderChkCancel16(logEmoney);	// UPDATE gd_member
		mapper.orderChkCancel17(logEmoney);	// INSERT gd_log_emoney
	}
	
	private void restoreCouponHistory(long ordno) {
		if (logger.isDebugEnabled()) {
			logger.debug("PopupOrderService >> restoreCouponHistory");
		}
		this.mapper.orderChkCancel13(ordno);	// DELETE gd_coupon_order
		this.mapper.orderChkCancel14(ordno);	// UPDATE gd_order
	}
// 2017-09-07 : 메소드 분리-----------------------------------------------------	
// 2019-12-27 : 결제금액 계산 재수정 - 공통부분에 재정의 현재 메소드는 사용하지않음.
	
	/** 결재금액 계산 */
	public void excuteSetPrnSettleprice(String ordno) throws Exception {
		
		/** 파라미터 설정 **/
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ordno", ordno);
		List<Map<String, Object>> items = mapper.orderSetPrnSettleprice1(param);	// SELECT gd_order_item
		
		String allCoupon = "";	//전체쿠폰정보
		int settleprice = 0;
		int totalAddopt = 0;
		
		int price = 0;
		int ea = 0;
		int memberdc = 0;
		int totalMemberdc = 0;
		int coupon = 0;
		int totalCoupon = 0;

		List<Map<String, String>> couponInfo = mapper.orderModifyDC1(ordno);
		for(Map<String, String> cou : couponInfo){
			//전체쿠폰이 존재하면 저장
			if("0".equals(cou.get("goodstype"))){
				allCoupon += cou.get("price") + ",";
			}
		}
		String couponPrice[] = allCoupon.split(",");
		
		if (items != null && items.size() > 0) {
			for(Map<String, Object> item : items) {
				price = Integer.valueOf(String.valueOf(item.get("price")));
				memberdc = Integer.valueOf(String.valueOf(item.get("memberdc")));
				ea = Integer.valueOf(String.valueOf(item.get("ea")));
				coupon = Integer.valueOf(String.valueOf(item.get("coupon")));
				
				if (Integer.valueOf(String.valueOf(item.get("istep"))) > 40) {
					logger.debug("case istep > 40 *continue*");
					continue;
				} else {
					settleprice += (price * ea);
					totalAddopt += ShopLibFunction.getTotalOptionPrice(String.valueOf(item.get("addopt")));
					totalMemberdc += memberdc;
					totalCoupon += coupon;
				}
			} 
		}
		// 전체쿠폰
		if (couponPrice != null && 0 < couponPrice.length) {
			int addCoupon = 0;
			for (String idx : couponPrice) {
				addCoupon = settleprice - ShopLibFunction.getDcprice(String.valueOf(settleprice), idx);
			}
			totalCoupon += addCoupon;
		}

		Map<String, Object> order = mapper.orderSetPrnSettleprice2(param);	// SELECT gd_order
		//int rt2_enuri = 0;
		int rt2_delivery = 0;
		int rt2_m_no = 0;
		int rt2_emoney = 0;
		//int rt2_eggFee = 0;
		int rt2_addDelivery = 0;
		if (null != order) {
			//rt2_enuri = Integer.valueOf(String.valueOf(order.get("enuri")));
			rt2_delivery = Integer.valueOf(String.valueOf(order.get("delivery")));
			rt2_m_no = Integer.valueOf(String.valueOf(order.get("m_no")));
			rt2_emoney = Integer.valueOf(String.valueOf(order.get("emoney")));
			//rt2_eggFee = Double.valueOf(String.valueOf(order.get("eggFee")));
			rt2_addDelivery = Integer.valueOf(String.valueOf(order.get("adddelivery")));
		}
		
		settleprice = (settleprice + totalAddopt + rt2_delivery + rt2_addDelivery) 
						- 
					  (totalCoupon + totalMemberdc);
		logger.debug("****settleprice****" + settleprice);
		
		param.put("settleprice", settleprice);
		//최종 구매금액 업데이트
		mapper.orderSetPrnSettleprice3(param);	// UPDATE gd_order
		
		
		
		int rt2_member_sum = 0;
		int rt2_member_cnt = 0;
		if (rt2_m_no > 0) { //회원일경우 회원 구매 금액 업데이트(배송완료건에 대해서만 조회함)
			param.put("mno", rt2_m_no);
			Map<String, Object> calcOrder = mapper.orderSetPrnSettleprice4(param);	// SELECT gd_order
			if (null != calcOrder) {
				rt2_member_sum = Integer.valueOf(String.valueOf(calcOrder.get("membersum")));
				rt2_member_cnt = Integer.valueOf(String.valueOf(calcOrder.get("membercnt")));
				param.put("membersum", rt2_member_sum);
				param.put("membercnt", rt2_member_cnt);
				mapper.orderSetPrnSettleprice5(param);	// UPDATE gd_member
			}
		}
	}
	
	/** 주문 삭제 처리 */
	public void excuteDeleteOrder(OrderCancelVO cancelVO) throws Exception {

		/** 파라미터 설정 **/
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ordno", cancelVO.getOrdno());
		
		mapper.orderDeleteInsert(param);	// INSERT gd_order_del
		mapper.orderDelete(param);			// DELETE gd_order
		
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
				price[i] = (orderItem.getPrice());
				itemPrice += orderItem.getPrice() * orderItem.getEa();
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
	
	// 2017-09-07 전화번호에 입력된 자릿수가 너무 적으면 표시해주지 않는 것으로 변경.
	public String formatCheckPhoneNumber(String phoneNumber) {
		return phoneNumber.length() < 11 ? "" : phoneNumber;
	}
	
	//(2017-09-07 회원의 전화번호 자릿수가 11보다 적으면 전화번호를 세팅하지 않는 것으로 변경)
	public PopupOrder2 getOrderMember(long ordno) {
		if (logger.isDebugEnabled()) {
			logger.debug("PopupOrderService >> getOrderMember");
		}
		
		PopupOrder2 rtData = this.ordersPopupOrder2(ordno);
		rtData.setMobileorder(this.formatCheckPhoneNumber(rtData.getMobileorder()));
		rtData.setPhoneorder(this.formatCheckPhoneNumber(rtData.getPhoneorder()));
		return rtData;
	}
	
	public void setOrderCancel(PopupOrderVO popupVO, Properties bankPro) throws Exception {
		popupVO.setTmpRt11(this.ordersPopupOrder11(popupVO.getOrdno()));
		for(GdOrderCancel cancel : popupVO.getTmpRt11()) {
			
			List<GdCode> gdCodeList = ShopLibFunction.codeitem("cancel");
			Properties props = new Properties();
			for(GdCode gdCode : gdCodeList) {
				bankPro.setProperty(gdCode.getItemcd(), gdCode.getItemnm());
				props.setProperty(gdCode.getItemcd(), gdCode.getItemnm());
			}
			popupVO.setCancel(cancel.getSno());
			cancel.setCancelCode(props.getProperty(String.valueOf(cancel.getCode())));
			cancel.setTmpRt12(this.ordersPopupOrder12(popupVO.getCancel()));
			
			for(GdLogCancel logCancel : cancel.getTmpRt12()) {
				logCancel.setrStepi(ShopLibFunction.r_stepi(String.valueOf(logCancel.getPrev()), String.valueOf(logCancel.getNext())));
			}
		}
		
	}
	
	public void setRtList(PopupOrderVO popupVO) {
		List<PopupOrder1> rtList = mapper.ordersPopupOrder1(popupVO.getOrdno());
		//List<OrderDeliveryVO> deliveryList = popupVO.getDeliveryList();
		
		// goodsno 배열 만들기
//		String[] goodsnos = new String[rtList.size()];
//		for (int i = 0; i < goodsnos.length; i++) {
//			goodsnos[i] = String.valueOf(rtList.get(i).getGoodsno());
//		}
		/* 추가배송비 정보를 왜 다시 가져오는지 모름. 이전 쿼리에서 가져온 정보로 사용함
		String addressSido = popupVO.getRtData().getAddress().substring(0, 2);
		
		List<OverDeliveryVO> overDeliveryList = frontOrdService.getOverDeliveryPolicy(goodsnos, addressSido);
		Map<String, String> overDeliveryMap = new HashMap<String, String>();
		for (OverDeliveryVO overDelivery : overDeliveryList) {
			overDeliveryMap.put(overDelivery.getGoodsno(), overDelivery.getPrice());
		}
		*/
//		String goodsno = "";
//		for (int i = 0; i < rtList.size(); i++) {
//			PopupOrder1 order1 = rtList.get(i);
//			OrderDeliveryVO deliveryVO = deliveryList.get(i);
//			goodsno = "".equals(order1.getSellerCd()) ? "admin" : String.valueOf(order1.getGoodsno());
//			order1.setAddDeliveryPrice(deliveryVO.getAddDeliveryPrice());
//			order1.setDeliveryPrice(deliveryVO.getDeliveryPrice());
//			order1.setPaymentTerms(deliveryVO.getPaymentTerms());
//		}
		
		popupVO.setRtList(rtList);
	}
	
	//2020-01-07 주문 복원시 배송비 정보도 복원 
	public void RecoveryDeliveryPrice(PopupOrderIndbVO indbVO){
		String sno = String.valueOf(indbVO.getSno());
		Map<String,Object> map = this.getRecoveryDelivery(sno);
		map.put("ordno", indbVO.getOrdno());
		//상품에 해당하는 판매사코드 가져오기
		String sellerCd = mapper.getSellerCdForCancel(sno);
		if("admin".equals(sellerCd)){
			//sellercd 가 공백이면 관리자 코드이므로 admin 으로 받게 해놨는데 
			//gdgoods에서 관리자 sellerCd는 공백이나 null로 되있어서 따로 설정을 해줘야 할듯 .
		}else{
			map.put("sellerCd", sellerCd);
			//주문상품중 판매사 코드에 해당하는 상품전체수 - 취소상품수   
			int sellerCdCancelCnt = mapper.checkAllCancelOfSellerGoods(map);
			//sellerCdCancelCnt == 0 한 판매사 상품이 전부 취소
			if(sellerCdCancelCnt == 0){
				//주문상품에 해당판매사 상품만 조회
				String[] goodsno = mapper.getCancelGoodsOfSeller(map);
				for(String s : goodsno){
					map.put("cgoodsno", s);
					this.mapper.updateDeliveryPrice1(map);
				}
				Map<String,Object> deliveryMap = this.getDeliveryPriceInfo(map);
				
				deliveryMap.put("ordno", indbVO.getOrdno());
				mapper.updateDeliveryPrice2(deliveryMap);
			}
		}

	}
}
