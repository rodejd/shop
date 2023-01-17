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
package com.wepinit.wepinit_shop.xmall.seller.order.controller;

import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.order.PopupOrderService;
import com.wepinit.wepinit_shop.xmall.admin.vo.order.OrderPrintVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.order.PopupOrderIndbVO;
import com.wepinit.wepinit_shop.xmall.common.AmazonSES;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.service.ShopLibFncService;
import com.wepinit.wepinit_shop.xmall.common.session.SellerSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCode;
import com.wepinit.wepinit_shop.xmall.common.vo.join.OrderCancel;
import com.wepinit.wepinit_shop.xmall.common.vo.join.OrderExcel;
import com.wepinit.wepinit_shop.xmall.common.vo.join.OrdercancelOrderitemMember;
import com.wepinit.wepinit_shop.xmall.common.vo.join.PopupOrdersRecovery1;
import com.wepinit.wepinit_shop.xmall.seller.order.service.SellerOrderService;
import com.wepinit.wepinit_shop.xmall.seller.order.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/shop/seller/order")
public class SellerOrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(SellerOrderController.class);
	
	@Resource
	SellerOrderService service;
	
	@Resource
	PopupOrderService sellerService;
	
	@Resource
	ShopLibFncService shopLibFncService;
	
	/**
	 * 판매사 주문리스트
	 * @param orderFM
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list")
	public String sellerOrderList(@ModelAttribute("orderFM") SellerOrderFM orderFM,
			HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {
		
		//model.addAttribute("noticeFM", service.getSellerNoticList(noticeFM));
		/*
		 * 1.주문권한관리
		 * 
		 * */
		this.service.loginAndPermissionCheck(req, res);

		/**
		 * 세션 판매사코드 set
		 */
		orderFM.setSsSellerCd(service.getSessionSellerCd());

		/**
		 * 주문리스트 정보
		 */
		service.getOrderListInfo(orderFM,req);
		
		return "seller/order/list";
	}
	
	/**
	 * 주문상세화면
	 * @param popupVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "popupOrder")
	public String popupOrder(@ModelAttribute("popupVO") SellerPopupOrderFM popupVO,
			HttpServletRequest req, HttpServletResponse res, Model model)
					throws Exception {
		
		//주문상세 관련 데이터 조회
		service.getPopupOrderInfo(popupVO,req);
		
		model.addAttribute("popupVO", popupVO);
		if ("view".equals(req.getParameter("viewFlg"))) {
			// 주문리스트 > 주문상세내역 화면으로 이동시
			return "seller/order/orderView";
		} else {
			// 주문상세내역 팝업으로 이동시
			return "/shop/seller/order/orderPopup";
		}
	}
	
	
	/**
	 * 엑셀 download
	 * @param orderExcelVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "excel/orderExcelDown")
	public String orderExcelDown(@ModelAttribute("orderExcelVO") SellerOrderFM orderExcelVO,
			HttpServletRequest req, HttpServletResponse res, Model model)
					throws Exception {
		
		res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		req.setCharacterEncoding("UTF-8");
		res.setHeader("Content-Disposition","attachment;filename=GDorder_"+orderExcelVO.getMode()+"_"+DateUtil.getDate("yyyyMMddHHmm")+".xls");
		res.setHeader("Content-Description", "JSP Generated Data");
		res.setHeader("Content-Description", "style=mso-number-format:'\\@'");
		
		/*
		 * 1.주문권한관리
		 * 
		 * */
		this.service.loginAndPermissionCheck(req, res);
		int chk_flag = 0;
		
		String[][] ordXls = null;
		if ("order".equals(orderExcelVO.getMode())) {	
			String[] chk = StringUtil.split(this.service.selectSellerOrderXlsSettings(this.service.getSessionSellerCd()), "^");
			//결제금액 check 값
			if("y".equals(chk[13])){
				chk_flag = 1;
			}
			if (chk == null || chk.length == 0) chk = new String[20];
			int idx = 0;
			ordXls = new String[][] {
				{ "번호"			, "no"				, chk[idx++] },
				{ "주문번호"		, "ordno"			, chk[idx++] },
				{ "주문자명"		, "nameorder"		, chk[idx++] },
				{ "이메일"		, "email"			, chk[idx++] },
				{ "주문자전화번호"	, "phoneorder"		, chk[idx++] },
				{ "주문자핸드폰"	, "mobileorder"		, chk[idx++] },
				{ "받는분이름"		, "namereceiver"	, chk[idx++] },
				{ "받는분전화번호"	, "phonereceiver"	, chk[idx++] },
				{ "받는분핸드폰"	, "mobilereceiver"	, chk[idx++] },
				{ "우편번호"		, "zipcode"			, chk[idx++] },
				{ "주소"			, "address"			, chk[idx++] },
				{ "배송메세지"		, "memo"			, chk[idx++] },
				{ "결제수단"		, "settlekind"		, chk[idx++] },
				{ "결제금액"		, "settleprice"		, chk[idx++] },
				{ "주문일자"		, "orddt"			, chk[idx++] },
				{ "주문상태"		, "step"			, chk[idx++] },
				{ "배송코드"		, "deliveryno"		, chk[idx++] },
				{ "송장번호"		, "deliverycode"	, chk[idx++] },
				{ "배송일"		, "ddt"				, chk[idx++] },
				{ "판매사명"		, "sellerNm"		, chk[idx++] }
			};
		} else {
			String[] chk = StringUtil.split(this.service.selectSellerOrderGoodsXlsSettings(this.service.getSessionSellerCd()), "^");
			if (chk == null || chk.length == 0) chk = new String[29];
			//결제금액,구매금액 check 값 
			if("y".equals(chk[24]) && "y".equals(chk[25])){
				chk_flag = 3;
			}else if("y".equals(chk[24])){
				chk_flag = 2;  
			}else if("y".equals(chk[25])){
				chk_flag = 1;
			}
			int idx = 0;
			ordXls = new String[][] {
				{ "번호"			, "no"				, chk[idx++] },
				{ "일련번호"		, "sno"				, chk[idx++] },
				{ "주문번호"		, "ordno"			, chk[idx++] },
				{ "주문자명"		, "nameorder"		, chk[idx++] },
				{ "이메일"		, "email"			, chk[idx++] },
				{ "주문자전화번호"	, "phoneorder"		, chk[idx++] },
				{ "주문자핸드폰"	, "mobileorder"		, chk[idx++] },
				{ "받는분이름"		, "namereceiver"	, chk[idx++] },
				{ "받는분전화번호"	, "phonereceiver"	, chk[idx++] },
				{ "받는분핸드폰"	, "mobilereceiver"	, chk[idx++] },
				{ "우편번호"		, "zipcode"			, chk[idx++] },
				{ "주소"			, "address"			, chk[idx++] },
				{ "배송메세지"		, "memo"			, chk[idx++] },
				{ "결제수단"		, "settlekind"		, chk[idx++] },
				{ "주문일자"		, "orddt"			, chk[idx++] },
				{ "주문상태"		, "step"			, chk[idx++] },
				{ "상품명(영문)"	, "goodsnm"			, chk[idx++] },
				{ "상품명(국문)"	, "goodsnmKR"		, chk[idx++] },
				{ "상품명(중문)"	, "goodsnmCN"		, chk[idx++] },
				{ "상품코드"		, "goodscd"			, chk[idx++] },
				{ "옵션"			, "addopt"			, chk[idx++] },
				{ "원산지"		, "origin"			, chk[idx++] },
				{ "브랜드"		, "brandnm"			, chk[idx++] },
				{ "수량"			, "ea"				, chk[idx++] },
				{ "매입가"		, "supply"			, chk[idx++] },
				{ "상품가격"		, "price"			, chk[idx++] },
				{ "주문결제가격"	, "sprice"			, chk[idx++] },
				{ "배송코드"		, "deliveryno"		, chk[idx++] },
				{ "송장번호"		, "deliverycode"	, chk[idx++] },
				{ "배송일"		, "ddt"				, chk[idx++] },
				{ "판매사명"		, "sellerNm"		, chk[idx++] }
			};
		}
		
		/**
		 * 세션 판매사코드 set
		 */
		orderExcelVO.setSsSellerCd(service.getSessionSellerCd());
		// 주문관리 > 주문관리 > 주문리스트 다운로드
		//orderExcelVO.setOrderList(service.getOrderXls(orderExcelVO, req.getParameterValues("step2"),req));
		
		//2020.02.27 이현빈 엑셀다운로드 결제금액, istep ,구매금액 데이터 수정 
		List<OrderExcel> orderList = service.getOrderXls(orderExcelVO,chk_flag);

		model.addAttribute("selConfList" , ordXls);
		model.addAttribute("orderList",orderList);
		
		
		return "seller/order/excel/orderDnXls";
	}
	
	/**
	 * 주문상세내용 수정
	 * @author CAR
	 */
	@RequestMapping(value="updateOrder")
	public String indb(@ModelAttribute("cancelVO") SellerOrderCancelFM cancelVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug("##### cancelVO#### cancelVO#### cancelVO#### cancelVO#### cancelVO#### cancelVO#### cancelVO#### cancelVO#### cancelVO#### cancelVO#### cancelVO : " );
			logger.debug("name >> "  + cancelVO.getUserNm());
		}
		
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		String sellerCd = sellerSO.getUserInfo().getSellerCd();
		logger.debug("sellerCd >>> " + sellerCd);
		
		try {
			/** 파라미터 설정 **/
			Map<String, Object> param = new HashMap<String, Object>();
			if ("chkCancel".equals(cancelVO.getMode())) {	// 주문 취소/반품 처리
				param.put("snoArr", cancelVO.getSno());	
				this.service.orderCancel(cancelVO);
				model.addAttribute("RESULT", true);
				shopLibFncService.changeStep2(cancelVO.getOrdno());
				
			} if ("delOrder".equals(cancelVO.getMode())) {	// 주문 삭제 처리
				// 삭제처리
				service.excuteDeleteOrder(cancelVO);
				model.addAttribute("RESULT", true);
			} if ("modOrder".equals(cancelVO.getMode())) {	// 주문 수정 처리
				// 수량 변경에 따른 재고량, 쿠폰, 적립금 관련 로직 수행
				this.service.excuteOrderModify(cancelVO);
				
				//### 진행상황별 처리
				if (cancelVO.getStep() != null) {
					ShopLibFunction.ctlStep(cancelVO.getOrdno(), cancelVO.getStep(), sellerCd);	// as-is : ctlStep
				}
				
				// 재고 조정 # 위 excuteOrderModify에 재고량 부분이 있으니 재고량 연동이 필요한 경우 확인필요
				ShopLibFunction.setStock(Long.parseLong(cancelVO.getOrdno()));
				
				ShopLibFunction.setPrnSettleprice(Long.parseLong(cancelVO.getOrdno()));
				// 결제금액 계산 구매금액에 대한 정의에 따라 수정이 필요
				//service.excuteSetPrnSettleprice(cancelVO);	// as-is : set_prn_settleprice
				
				String view = "";
				if ("view".equals(req.getParameter("viewFlg"))) {
					view = "&viewFlg=view";
				}
				if("3".equals(cancelVO.getStep())) {
					//회원가입메일발송  
					AmazonSES mailConfig = new AmazonSES();
					Map<String,Object> mailMap = new HashMap<String,Object>();
					
					mailMap.put("name", cancelVO.getUserNm());
					//수신자이메일 , mode 메일종류 , mailMap  
					mailConfig.mailSender(cancelVO.getEmail(), "3", mailMap);
				}

				
				
				return "redirect:/shop/seller/order/popupOrder?ordno=" + cancelVO.getOrdno() + view;
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
	 * 관리자 > 주문관리 > 주문취소리스트
	 */
	@RequestMapping(value="orderCs")
	public String sellerCs(@ModelAttribute("csVO") SellerOrderCSFM csVO, HttpServletRequest req, Model model) throws Exception {
		String[] type = req.getParameterValues("type");
		if(type != null) {
			csVO.setType(type);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>order cs!!!");
		}
		/**
		 * 세션 판매사코드 set
		 */
		csVO.setSsSellerCd(service.getSessionSellerCd());
		List<OrderCancel> orderCancelList = this.service.getOrderCancelList(csVO);	
		List<GdCode> gdCodeList = ShopLibFunction.codeitem("cancel");
		Map<String, String> codeMap = new HashMap<String, String>();
		for (GdCode gdCode : gdCodeList) {
			codeMap.put(gdCode.getItemcd(), gdCode.getItemnm());
		}
		int addopt = 0;
		for(OrderCancel list : orderCancelList) {
			/*list.setStepMsg(ShopLibFunction.getStepMsg(
					StringUtil.nvl(String.valueOf(list.getStep()), "0"), 
					String.valueOf(list.getIstep()), 
					String.valueOf(list.getOrdno()), 
					String.valueOf(list.getItemsno())));*/
			list.setStepMsg(ShopLibFunction.r_istep(String.valueOf(list.getIstep())));
			list.setCodeItem(codeMap.get(String.valueOf(list.getCode())));
			addopt = ShopLibFunction.getTotalOptionPrice(list.getAddopt());
			list.setPay(list.getPay() + addopt);
		}

		model.addAttribute("cancelList", orderCancelList);
		model.addAttribute("csVO", csVO);
		model.addAttribute("listSize", orderCancelList.size());
		
		return "seller/order/orderCs";
	}
	
	/**
	 * 반품/교환 접수 리스트
	 * regoods/list
	 */
	@RequestMapping(value="regoods/list")
	public String regoodsList(@ModelAttribute("regoodsVO")SellerOrderRegoodsFM regoodsVO) throws Exception{
		
		/**
		 * 세션 판매사코드 set
		 */
		regoodsVO.setSsSellerCd(service.getSessionSellerCd());
		
		regoodsVO.setRegoodsList(service.getOrderRegoodsList(regoodsVO));
		
		return "seller/order/orderRegoods";
	}
	
	/**
	 * @param repayVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 * 환불접수리스트
	 */
	@RequestMapping(value="orderRepay")
	public String sellerOrderRepay(@ModelAttribute("repayVO") SellerRepayFM repayVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		/**
		 * 세션 판매사코드 set
		 */
		repayVO.setSsSellerCd(service.getSessionSellerCd());
		
		//총건수
		repayVO.setRowCount(service.getRepayCount(repayVO));		
		List<OrdercancelOrderitemMember> repayList = service.getRepayList(repayVO);
		
		//판매사 배송비 중복적용을 체크를 위한 flagMap
		Map<String,Object> flagMap = new HashMap<String,Object>();
		for(int i =0;i<repayList.size();i++){
			
			OrdercancelOrderitemMember oom = repayList.get(i);
			oom.setSsSellerCd(repayVO.getSsSellerCd());
			oom.setCcnt(service.getRepayList2(oom));
			oom.setOiocObject(service.getRepayList3(oom));
			
			flagMap = service.calculateRefundPrice(repayList.get(i), flagMap);
			repayList.get(i).setRefundPrice(Integer.parseInt(String.valueOf(flagMap.get("refundPrice"))));
			service.sellerSettlePrice(repayList.get(i));
			
			/*
			 * flagMap : sellerCd = 판매사 배송비 중복 적용 방지를 위한 판매사코드
			   			 refundPrice = 환불금액  */
		}
		repayVO.setRepayList(repayList);		
		return "seller/order/orderRepay";
	}
	
	/**
	 * 판매사        반품/교환접수리스트 : 반품완료or교환완료 후 재주문 넣기
	 * @param orderListStepChangeVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("regoods/indb")
	public String indb(@ModelAttribute("regoodsVO") SellerOrderRegoodsFM regoodsVO) throws Exception {
		logger.debug("regoods/indb >>>>>>>>>>>>>>>>>>>>>>>> "+regoodsVO.getMode());
		if("regoods".equals(regoodsVO.getMode())){
			//반품처리
			if(regoodsVO.getChk() != null){
				if(regoodsVO.getChk().length > 0){
					for(int i=0; i<regoodsVO.getChk().length; i++){
						regoodsVO.setSno(Integer.parseInt(regoodsVO.getChk()[i]));
						//주문아이템 처리
						service.updateOrderRegoods(regoodsVO);
					}
				}
			}
			
		} else if("exc_ok".equals(regoodsVO.getMode())){
			//교환완료 후 재주문 처리
			if(regoodsVO.getChk() != null){
				if(regoodsVO.getChk().length > 0){
					for(int i=0; i<regoodsVO.getChk().length; i++){
						regoodsVO.setSno(Integer.parseInt(regoodsVO.getChk()[i]));
						//주문 아이템 처리
						service.updateOrderExchange(regoodsVO);
					}
				}
			}
		}
		
		return "redirect:/shop/seller/order/regoods/list";
	}
	
	/**
	 *  판매사        판매자 환불접수리스트 : 환불완료
	 * @param repayVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="repay/indb")
	public String repaydb(@ModelAttribute("repayVO") SellerOrderRepayFM repayVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		service.repaydb(repayVO,req);

		String returnUrl = "redirect:/shop/seller/order/orderRepay";
		
		return returnUrl;
	}

	
	/**
	 * 팝업 - 다운로드 파일설정  
	 * @author CAR
	 */
	@RequestMapping(value="popup.orderxls")
	public String popupOrderXls(@ModelAttribute("orderExcelVO") SellerOrderExcelFM excelFM, Model model) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug("##### SellerOrderController >> popupOrderXls : "+ excelFM.getMode());
		}
		
		model.addAttribute("ordXls", this.service.selectSellerOrderXls(excelFM.getMode()));
		
		return "/shop/seller/order/popup.orderxls";
	}
	
	/**
	 * 팝업 - 다운로드 파일설정 저장처리  
	 * @author CAR
	 */
	@RequestMapping(value="popup_indb.dojson" , method=RequestMethod.POST)
	public String popupOrderXlsIndb(SellerOrderExcelFM excelFM, Model model) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug("##### mode : "+ excelFM.getMode());
			logger.debug("##### cfgChkVal : "+ excelFM.getCfgChkVal());
		}
		
		model.addAttribute("RESULT", this.service.updateSellerOrderXls(excelFM));
		
		return "dojson";
	}
	
	@RequestMapping("indb")
	public String indb(@ModelAttribute("orderListStepChangeVO") SellerOrderListStepChangeVO sellerOrderListStepChangeVO) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("** SellerOrderController >> indb");
			logger.debug("** SellerOrderListStepChangeVO: "+sellerOrderListStepChangeVO.toString());
		}
		
			this.service.changeOrderStep(sellerOrderListStepChangeVO);
		
		// this.service.excuteOrderModify(cancelVO);
		return "redirect:/shop/seller/order/list?mode=group&period=0&first=1";
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
		
		
		String sellerCd = this.service.getSessionSellerCd();
		printVO.setSellerCd(sellerCd); //판매사 코드
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
			
			List<Long> ordno = this.sellerService.orderPrintList(orderPrintListMap);
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
				int cnt = this.sellerService.paperTaxCount(printVO.getOrdno());
				printVO.setCnt(cnt);
			} else if("report".equals(printVO.getType())) {
				this.service.sellerOrderPrintReport(printVO);
			} else if("reception".equals(printVO.getType())) {
				this.service.sellerOrderPrintReception(printVO);
			}
		}
		
		printVO.setPaperOrders(paperOrders);
		model.addAttribute("printVO", printVO);
		return "/shop/seller/order/_paper";
	}

	/*
	 * 판매사 주문취소 복원
	 * 관리자의 복원과 같음
	 * */	
	@RequestMapping(value="indb/recovery")
	public String popupIndbRecovery(@ModelAttribute("indbVO") PopupOrderIndbVO indbVO, Model model, HttpServletRequest req) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> indb/recovery");
		}
		PopupOrdersRecovery1 recovery = this.sellerService.ordersRecovery1(indbVO.getSno());
		
		//### 전체 주문단계가 취소단계시 일반 주문단계로 단계복원
		if(recovery != null) {
			Integer emoney = this.sellerService.ordersRecovery2(recovery.getMno());

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
			
			Integer snoInteger = this.sellerService.ordersRecovery3(paramMap);
			int sno = snoInteger != null ? snoInteger : 0;
			paramMap.put("sno2", sno); // 5,6번에서 사용

			if(sno > 0) {
				paramMap.put("sno1", sno); // 4번에서 사용	
				if(sno > 0) {
					this.sellerService.ordersRecovery4(paramMap);
					this.sellerService.ordersRecovery5((Integer)paramMap.get("sno2"));
				}
			} else {
				this.sellerService.ordersRecovery6(paramMap);
			}
			
			paramMap.put("name", "");

			//### 주문복원 정보 저장			
			this.sellerService.ordersRecovery7(paramMap);

			paramMap.put("itemno", recovery.getSno());
			paramMap.put("prev", recovery.getIstep());
			paramMap.put("next", recovery.getStep());
			
			//### 취소(복원) 로그 저장
			this.sellerService.ordersRecovery8(paramMap);
				
			//### 전체 주문단계가 취소단계시 일반 주문단계로 단계복원
			if(recovery.getStep2() >0) {
				this.sellerService.ordersRecovery9((Long)paramMap.get("ordno"));
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
					this.sellerService.ordersRecovery10(paramMap);

					paramMap.put("emoney", recovery.getReserve());
					paramMap.put("memo", msg);
					this.sellerService.ordersRecovery11(paramMap);
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
		return "redirect:/shop/seller/order/popupOrder?ordno=" + indbVO.getOrdno() + param;
	}
		
	
}
