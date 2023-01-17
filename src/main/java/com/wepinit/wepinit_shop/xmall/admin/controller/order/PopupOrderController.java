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
package com.wepinit.wepinit_shop.xmall.admin.controller.order;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xcube.util.WebUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.order.OrderService;
import com.wepinit.wepinit_shop.xmall.admin.service.order.PopupOrderService;

import com.wepinit.wepinit_shop.xmall.admin.vo.order.*;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.service.ShopLibFncService;
import com.wepinit.wepinit_shop.xmall.common.util.BiztalkUtil;
import com.wepinit.wepinit_shop.xmall.common.vo.GdListDelivery;
import com.wepinit.wepinit_shop.xmall.common.vo.join.PopupOrder1;
import com.wepinit.wepinit_shop.xmall.common.vo.join.PopupOrder2;
import com.wepinit.wepinit_shop.xmall.common.vo.join.PopupOrder4;
import com.wepinit.wepinit_shop.xmall.common.vo.join.PopupOrdersRecovery1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/* 2017-08-24 : OrderController 에서 분리 (지은정)
 * 주문상세페이지 컨트롤러
 * */

@Controller
@RequestMapping("/shop/admin/order/*")
public class PopupOrderController {
	@Resource
	PopupOrderService service;
	
	@Resource
	OrderService orderService;
	private static final Logger logger = LoggerFactory.getLogger(PopupOrderController.class);
	
	@Resource
	ShopLibFncService shopLibFncService ;
	

	/**
	 * 관리자 > 주문관리 > 주문상세
	 * 팝업 - 주문상태 팝업
	 */
	@RequestMapping(value="popup.order")
	public String popupOrder(@ModelAttribute("popupVO") PopupOrderVO popupVO, HttpServletRequest req, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>popup.order");
		}

		String[] newordno = null;
		//		### 은행 배열생성
		Properties bankPro = this.service.getBankProperties();
		int goodsPrice 	= 0;
		int memberDC 	= 0;
		int icancel 	= 0;
		//int totalCoupon = 0;
//		int cntDv 		= 0;
		long ordno 		= popupVO.getOrdno();
	
		//### 주문리스트 리퍼러
		popupVO.setReferer((!"".equals(popupVO.getReferer())) ? popupVO.getReferer() : req.getHeader("referer"));
		
		//### 배송업체 정보
		popupVO.setTmpDeli(this.service.ordersPopupOrder3());
//		String tmpRt = null;

		//### 입금계좌 정보
		popupVO.setTmpBank(this.service.ordersPopupOrder4());
		
		//주문 및 주문한 회원
		PopupOrder2 rtData = this.service.getOrderMember(popupVO.getOrdno());
		popupVO.setRtData(rtData);
		
		// 주문상세페이지 배송테이블 정보 
		popupVO.setDeliveryInfo(this.service.selectPopupDeliveryInfo(popupVO.getOrdno()));
		
		//택배사 selectbox list
		popupVO.setDeliveryCompList(this.service.getUseDeliveryCompList("admin"));
		
		// 상품리스트
		this.service.setRtList(popupVO);
		
		// 정상 주문상품 갯수 구하기
		int icnt = this.service.ordersPopupOrder5(ordno);
		popupVO.setiCnt(icnt);

		//세금계산서
		popupVO.setRtTax(this.service.paperTaxState(ordno));
		//LinkedHashMap<String, String> lhm = ShopLibFunction.getArrStep();
		
		// 주문단계정보
		popupVO.setDeliveryBasis(ShopConfig.getProperty("delivery.basis"));
		
		String wskin = ConfigClass.WEB_SKIN;
		int ea = 0;
		if(popupVO.getRtList().size() > 0) {
			
			//전체쿠폰 조회 
			String[] allCoupons = orderService.getAllCouponPrice(String.valueOf(ordno));
			boolean couponFlag = false;
			for(PopupOrder1 order1 : popupVO.getRtList()) {
				ea = order1.getEa();
				int istep = order1.getIstep();
				int coupon = StringUtil.N2I(order1.getCoupon());
				//전체쿠폰이 있을경우
				if(allCoupons.length > 0){
					coupon += orderService.getAllCouponPercentPrice(allCoupons ,StringUtil.N2I(order1.getPrice()) * order1.getEa());
				}
				if(ShopLibFunction.getNormalIstep(istep)) {
					couponFlag = true;
					// 2017-09-05 : 상품가격에 고객이 선택한 상품의 옵션가도 추가하도록 내용 변경
					int totalOptionPrice = ShopLibFunction.getTotalOptionPrice(order1.getAddopt());					
					goodsPrice += (StringUtil.N2D(order1.getPrice()) * ea) + totalOptionPrice;
					memberDC += StringUtil.N2D(order1.getMemberdc());
					//totalCoupon += coupon;
				}
				String bgColor = "#ffffff";
				if(order1.getIstep() > 40) {
					icancel++;
					bgColor = "#F0F4FF";
				} 
					
				int priceSum = this.getPriceSum((StringUtil.N2I(order1.getPrice()) * ea), order1.getAddopt()); // 상품가격 * 개수 + 옵션들 가격  = 총가격
				logger.debug("priceSum ################# "+priceSum);
				order1.setPriceSum(priceSum+"");
				order1.setBgColor(bgColor);
				order1.setSubImgs(order1.getImgs().replace("|", ""));
				order1.setBrandnm(StringUtils.hasText(order1.getBrandnm()) ? order1.getBrandnm() : "없음");
				order1.setrIstep(ShopLibFunction.r_istep(String.valueOf(order1.getIstep())));
				order1.setDvno(!"".equals(order1.getDvcode()) || !"0".equals(order1.getDvcode()) && false ? "-" : String.valueOf(order1.getDvno()));
				
				//배송완료시 구매후 쿠폰발급
				if("배송완료".equals(order1.getrIstep())) {
					ShopLibFunction.insertGoodsOrderCoupon(String.valueOf(rtData.getMno()));
				}
			}
			//if(couponFlag && allCoupons.length > 0){
				//totalCoupon += orderService.addAllCouponPrice(String.valueOf(ordno), allCoupons);
			//}
			popupVO.setGoodsPrice(goodsPrice);
			popupVO.setMemberDc(memberDC);
//			popupVO.setCoupon(coupon);
			//popupVO.getRtData().setCoupon(totalCoupon);
			popupVO.setiCancel(icancel);
			popupVO.setWskin(wskin);
			
	
			//할인액 계산
			// memberDc 미구현으로 제외, 에누리 기존 소스 주석처리로 인한 제외. 할인 추가시 포함해주어야 함.
			popupVO.setDiscount(memberDC + rtData.getCoupon() + rtData.getEmoney());
			//실데이타 계산으로 결제금액 산출
			popupVO.setSettleprice(goodsPrice + rtData.getDelivery() - popupVO.getDiscount() + rtData.getEggfee());
			int cnt = orderService.getCancelOrRefundCnt(String.valueOf(ordno));
			if(cnt == 0 ){
				popupVO.getRtData().setDelivery(0);
			}
		}
		
		//popupVO.setSelectOption(WebUtil.makeSelectOption(lhm, String.valueOf(rtData.getStep())));
		popupVO.setStepMsg(ShopLibFunction.getStepMsg(
				String.valueOf(rtData.getStep()), 
				String.valueOf(rtData.getStep2()), 
				String.valueOf(rtData.getOrdno()), 
				""));
		
		
		int cnt = this.service.ordersPopupOrder6(ordno);
		popupVO.setCnt(cnt);
		if(cnt > 0) {
			List<Long> tmpRt7 = this.service.ordersPopupOrder7(ordno);
			if(tmpRt7.size() > 0) {
				for(int i = 0, size = tmpRt7.size(); i < size; i++) {
					newordno = new String[tmpRt7.size()];
					newordno[i] = String.valueOf(tmpRt7.get(i));
				}
			}	
		}
		popupVO.setNewordno(newordno);
		popupVO.getRtData().setOldordnoN2S(StringUtil.N2S(String.valueOf(rtData.getOldordno()), ""));
		
		popupVO.setTmpRt8(this.service.ordersPopupOrder8(ordno));
		popupVO.setTmpRt9(this.service.ordersPopupOrder9(ordno));
		
		// 2017-09-05 : cancel 이상한 데이터 들어가고 있어 주석처리 함. 구현시 재구현 필요
		//popupVO.setTmpRt10(this.service.ordersPopupOrder10(popupVO.getCancel()));
		for(PopupOrder4 order4 : popupVO.getTmpRt9()) {
			order4.setBankPro(bankPro.getProperty(StringUtil.getPadString(String.valueOf(order4.getBankcode()), 2, "0")));
		}
		
		List<Map<String, String>> deliveryList = new ArrayList<Map<String,String>>();
		for(GdListDelivery delivery : popupVO.getTmpDeli()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("deliveryno", String.valueOf(delivery.getDeliveryno()));
			map.put("deliverycomp", delivery.getDeliverycomp());
			map.put("deliveryurl", delivery.getDeliveryurl());
			map.put("useyn", delivery.getUseyn());
			deliveryList.add(map);
		}
		
		popupVO.setDeliverySelectOption(
			WebUtil.makeSelectOption(
				deliveryList, 
				"deliveryno", 
				"deliverycomp", 
				String.valueOf(popupVO.getRtData().getDeliveryno())
			)
		);
		
		
		// 주문취소 내역
		this.service.setOrderCancel(popupVO, bankPro);

		model.addAttribute("popupVO", popupVO);
		
		if ("view".equals(req.getParameter("viewFlg"))) {
			// 주문리스트 > 주문상세내역 화면으로 이동시
			return "/shop/admin/order/view";
		} else {
			// 주문상세내역 팝업으로 이동시
			return "/shop/admin/order/popup.order";
		}
	}
	
	/**
	 * 관리자 > 주문관리 > 주문리스트 > 주문상세내역 
	 * 주문상품 및 교환하기 
	 * @author CAR
	 */
	@RequestMapping(value="orderCancel")
	public String orderCancel(@ModelAttribute("orderVO") OrderVO orderVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		/*
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("snoArr", orderVO.getSno());	
		model.addAttribute("cancleItems", service.ordersCancel1(param));
		*/
		
		List<String> snoList = new ArrayList<String>();
		String[] snoArr = orderVO.getSno();
		if(snoArr != null && snoArr.length > 0) {
			for(String sno : snoArr) {
				snoList.add(sno);
			}
		}
		
		model.addAttribute("snoList", snoList);
		model.addAttribute("param_m", req.getParameter("m"));

		return "/shop/admin/order/order.cancel";	//  AS-IS : ifrm.cancel.jsp
	}
	
	/**
	 * 관리자 > 주문관리 INDB
	 * @author CAR
	 */
	@RequestMapping(value="indb.cancel")
	public String indb(@ModelAttribute("cancelVO") OrderCancelVO cancelVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		try {
			// 주문 취소/반품 처리
			if ("chkCancel".equals(cancelVO.getMode())) {
				int result = service.orderPopupCancel(cancelVO);
				
				if(result > 0) {
					model.addAttribute("RESULT", true);
				}else {
					model.addAttribute("RESULT", false);
				}
			// 주문 삭제 처리
			}else if ("delOrder".equals(cancelVO.getMode())) {
				// 삭제처리
				service.excuteDeleteOrder(cancelVO);
				model.addAttribute("RESULT", true);
				
			// 주문 수정 처리
			}else if ("modOrder".equals(cancelVO.getMode())) {
				
				// 주문정보 변경 & 송장번호 변경
				this.orderService.excuteOrderModify(cancelVO);
				
				// 진행상황별 처리
				
				if (cancelVO.getStep() != null) {
					if( !"40".equals(cancelVO.getStep2()) && !"56".equals(cancelVO.getStep2()) && !"58".equals(cancelVO.getStep2()) ) {
						ShopLibFunction.ctlStep(cancelVO.getOrdno(), cancelVO.getStep());	// as-is : ctlStep
					}
				}
				
				//배송중
				if ("3".equals(cancelVO.getStep())) {
					/* 비즈톡 발송 */
					BiztalkUtil biztalk = new BiztalkUtil();
					biztalk.sendAlimTalk(cancelVO.getOrdno(), "ritzmall_02");
					
				//배송완료
				}else if ("4".equals(cancelVO.getStep())) {
					//회원 적립 적립금 처리
					shopLibFncService.memberAddEmoneyModify(cancelVO.getOrdno());
				}
				
				String view = "";
				if ("view".equals(req.getParameter("viewFlg"))) {
					view = "&viewFlg=view";
				}
				return "redirect:/shop/admin/order/popup.order?ordno=" + cancelVO.getOrdno() + view;
			
			//반품요청 처리
			}else if ("cancelOrder".equals(cancelVO.getMode())) {
				String view = "";
				if ("view".equals(req.getParameter("viewFlg"))) {
					view = "&viewFlg=view";
				}
				
				// 주문정보 변경 & 송장번호 변경
				this.orderService.excuteOrderModify(cancelVO);
				
				int intStep = StringUtil.N2I(cancelVO.getStep());
				
				//잘못된 접근
				if(intStep <= 79) {
					return "redirect:/shop/admin/order/popup.order?ordno=" + cancelVO.getOrdno() + view;
				}
				
				//주문 상태 step2 변경 (gd_order)
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("ordno", cancelVO.getOrdno()); //주문번호
				paramMap.put("step2", intStep);
				shopLibFncService.updateShopLibOrderStep(paramMap);
				
				//주문 상태 istep 변경 (gd_order_item)
				paramMap = new HashMap<String, Object>();
				paramMap.put("ordno", cancelVO.getOrdno()); //주문번호
				paramMap.put("istep", intStep);
				shopLibFncService.updateShopLibOrderIStep(paramMap);
				
				// 반품(환불)완료일경우, 지급된적립금, 구매시사용된 쿠폰,할인코드,적립금 환불
				if(intStep == 83) {
					// 반품(환불)완료 로직
					shopLibFncService.orderReturnProc(cancelVO.getOrdno());
					
					/* 비즈톡 발송 */
					BiztalkUtil biztalk = new BiztalkUtil();
					biztalk.sendAlimTalk(cancelVO.getOrdno(), "ritzmall_04");
				}
				
				return "redirect:/shop/admin/order/popup.order?ordno=" + cancelVO.getOrdno() + view;
			}
		} catch (Exception e) {
			model.addAttribute("RESULT", false);
			model.addAttribute("RESULT_MSG", "처리중 일시적인 오류가 발생하였습니다. 잠시후 다시 시도해 주세요.");
			e.printStackTrace();
			throw new BizException("common.00004");
		} 
		return "dojson";
	}
	
	/**
	 * 관리자 > 주문관리 > 주문리스트 > 주문내역상세
	 * 팝업 - 주문처리Log내역
	 * @author CAR
	 */
	@RequestMapping(value="popup.log")
	public String popupOrderLog(@ModelAttribute("orderVO") OrderVO orderVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug("##### orderVO : "+orderVO.toString());
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ordno"	, orderVO.getOrdno());
		model.addAttribute("logList", service.orderLogList(paramMap));
	
		return "/shop/admin/order/popup.log";
	}
	
	@RequestMapping(value="indb/recovery")
	public String popupIndbRecovery(@ModelAttribute("indbVO") PopupOrderIndbVO indbVO, Model model, HttpServletRequest req) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> indb/recovery");
		}
		PopupOrdersRecovery1 recovery = this.service.ordersRecovery1(indbVO.getSno());
		
		//배송비 복원 .
		this.service.RecoveryDeliveryPrice(indbVO);
		
		//### 전체 주문단계가 취소단계시 일반 주문단계로 단계복원
		if(recovery != null) {
			Integer emoney = this.service.ordersRecovery2(recovery.getMno());

			if(emoney != null && emoney > 0) {
				if(recovery.getEmoney() > emoney) {
					indbVO.setAlertMsg("회원적립금이 주문시 사용한 적립금을 초과하여 복원할 수 없습니다.");
				}
			}
		
			//### 복원시 동일단계의 아이템이 존재하는지 체크
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ordno"	, recovery.getOrdno()	);
			paramMap.put("istep"	, recovery.getStep()	);
			paramMap.put("goodsno"	, recovery.getGoodsno()	);
			paramMap.put("goodsnm"	, recovery.getGoodsnm()	);
			paramMap.put("goodsnmKR", recovery.getGoodsnmKR()	);
			paramMap.put("goodsnmCN", recovery.getGoodsnmCN()	);
			paramMap.put("opt1"		, recovery.getOpt1()	);
			paramMap.put("opt2"		, recovery.getOpt2()	);
			paramMap.put("addopt"	, recovery.getAddopt()	);
			paramMap.put("price"	, recovery.getPrice()	);
			paramMap.put("ea"		, recovery.getEa()		);
			paramMap.put("sno"		, recovery.getSno()		);
			
			Integer snoInteger = this.service.ordersRecovery3(paramMap);
			int sno = snoInteger != null ? snoInteger : 0;
			paramMap.put("sno2", sno); // 5,6번에서 사용

			if(sno > 0) {
				paramMap.put("sno1", sno); // 4번에서 사용	
				this.service.ordersRecovery4(paramMap);
				this.service.ordersRecovery5((Integer)paramMap.get("sno2"));
			} else {
				this.service.ordersRecovery6(paramMap);
			}
			
			paramMap.put("name", "");

			//### 주문복원 정보 저장			
			this.service.ordersRecovery7(paramMap);

			paramMap.put("itemno", recovery.getSno());
			paramMap.put("prev", recovery.getIstep());
			paramMap.put("next", recovery.getStep());
			
			//### 취소(복원) 로그 저장
			this.service.ordersRecovery8(paramMap);
				
			//### 전체 주문단계가 취소단계시 일반 주문단계로 단계복원
			if(recovery.getStep2() >0) {
				this.service.ordersRecovery9((Long)paramMap.get("ordno"));
				//### 상품구입시 사용적립금 재적용
				if(recovery.getMno() > 0 && recovery.getEmoney() > 0) {
					ShopLibFunction.setEmoney(recovery.getEmoney(), recovery.getMno(), recovery.getOrdno(), "주문복원으로 인한 사용적립금 재사용");
				}
			}
			//### 재고조정
			ShopLibFunction.setStock(recovery.getOrdno());
			//취소 복원시 따른 구매금액 정의하여 사용할것. 수량,상품가격 변경후 수정시에 구매금액합계구하는 로직과 동일
			ShopLibFunction.setPrnSettleprice(recovery.getOrdno());
			

			
			if(recovery.getStep() > 3) {
				//### 취소상품 구매적립금 환원
				if(recovery.getReserve() > 0 && recovery.getMno() > 0) {
					String msg = "주문 복원으로 인해 구매적립금 적립";
					
					paramMap.put("reserve", recovery.getReserve());
					paramMap.put("m_no", recovery.getMno());
					this.service.ordersRecovery10(paramMap);

					paramMap.put("emoney", recovery.getReserve());
					paramMap.put("memo", msg);
					this.service.ordersRecovery11(paramMap);
				}
			}
		} else {
			indbVO.setAlertMsg("비정상적인 접근입니다.");
		}
		
		indbVO.setFormTarget("_self");
		model.addAttribute("popupVO", indbVO);
		String param = "";
		if ("view".equals(req.getParameter("viewFlg"))) {
			param = "&viewFlg=view";
		}
		return "redirect:/shop/admin/order/popup.order?ordno=" + indbVO.getOrdno() + param;
	}
	
	/*
	 * 주문상세/출력
	 * _print
	 * */
	@RequestMapping(value="_paper")
	public String orderPrint(@ModelAttribute("printVO") OrderPrintVO printVO, Model model) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug(">>>>>>>>>>>>>>>>>>>> order _popup");
		}
		
		String[] paperOrders 	= null;
		String[] regdt 			= printVO.getRegdt();
		String listType 		= printVO.getListType();
		String settlekind 		= "";
		String step 			= printVO.getStep();
		Map<String, Object> orderPrintListMap = new HashMap<String, Object>();

		if("list".equals(listType)) {
			paperOrders = StringUtil.explode(printVO.getOrdnos(), ";");
		} else if("term".equals(listType)) {
			orderPrintListMap.put("settlekind", settlekind);

			orderPrintListMap.put("regdt0", regdt[0]);
			orderPrintListMap.put("regdt1", regdt[1]);

			if(!"".equals(step)) {
				String[] sStep = step.split("_");
				orderPrintListMap.put("sStep0", sStep[0]);
				orderPrintListMap.put("sStep1", sStep[1]);
			}
			
			List<Long> ordno = this.service.orderPrintList(orderPrintListMap);
			if(ordno.size() > 0) {
				paperOrders = new String[ordno.size()];
				for(int i = 0, size = ordno.size(); i < size; i++) {
					paperOrders[i] = String.valueOf(ordno.get(i));
				}
			}
		} else if("tax_term".equals(listType)) {
			// 애즈이즈에서도 비어있음
		} else {
			// 애즈이즈에서도 비어있음
		}
		
		for (String order : paperOrders) {
			printVO.setOrdno(Long.parseLong(order));
			if("tax".equals(printVO.getType())) {
				int cnt = this.service.paperTaxCount(printVO.getOrdno());
				printVO.setCnt(cnt);
			} else if("report".equals(printVO.getType())) {
				this.service.orderPrintReport(printVO);
			} else if("reception".equals(printVO.getType())) {
				this.service.orderPrintReception(printVO);
			}
		}
		
		printVO.setPaperOrders(paperOrders);
		model.addAttribute("printVO", printVO);
		return "/shop/admin/order/_paper";
	}

	public int getPriceSum(int price, String addopt) {
		//int price = StringUtil.N2D(cartListVO.getPrice());
		int addPriceSum = 0;
		if(StringUtils.hasText(addopt) && !addopt.equals("NULL")) {
			String[] addoptArr = addopt.split("\\|");
			for(String add : addoptArr) {
				String addoptResult = add.split("\\^")[3];
				if(addoptResult != null) {
					addPriceSum += StringUtil.N2D(addoptResult);
				}
			}
		}
		return price + addPriceSum;
	}	
	
}
