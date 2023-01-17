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

import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xcube.util.ExcelUtil;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.order.OrderService;

import com.wepinit.wepinit_shop.xmall.admin.vo.order.*;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCode;
import com.wepinit.wepinit_shop.xmall.common.vo.join.OrderCancel;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/shop/admin/order/*")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	
	@Resource
	OrderService service;
	
	@RequestMapping(value="list")
	public String list(@ModelAttribute("orderVO") OrderVO orderVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>order list!!!" + orderVO);
		}
		
		/*
		 * 1.주문권한관리
		 * 
		 * */
		ShopLibFunction.menuAuthPermissionCheck(req, res, "order");
		//this.service.loginAndPermissionCheck(req, res);
		
		/*
		 * 2.검색설정
		 * 
		 * */
		Map<String, Object> param = new HashMap<String, Object>();

		
		// 초기값 설정
		String orderPeriod = ShopConfig.getProperty("orderPeriod");
		
		if("1".equals(orderVO.getFirst()) && !"".equals( orderPeriod )  ){
			orderVO.setPeriod(orderPeriod);
		}
		
		// 검색타입(주문검색[통합])
		param.put("skey", orderVO.getSkey());
		
		// 검색어(주문검색[통합])
		param.put("sword", orderVO.getSword());
		
		// 검색어(주문처)
		param.put("sagent", orderVO.getSagent());
		
		// 검색어(스킨)
		param.put("sskin", orderVO.getSchSkin());
		
		// 검색어(배송국가)
		param.put("scountry", orderVO.getScountry());
		
		// 검색타입(상품검색[선택])
		param.put("sgkey", orderVO.getSgkey());
		
		// 검색어(상품검색[선택])
		param.put("sgword", orderVO.getSgword());
		
		// 주문상태(step, step2)
		param.put("step", orderVO.getStep());
		String tmp_step2 = "";
		String[] step2	= req.getParameterValues("step2");
		
		if( step2 != null ) {
			for(String str : step2) {
				if(StringUtils.isNotBlank(tmp_step2)) tmp_step2 += ",";
				tmp_step2 += str;
			}
		}
		
		param.put("step2", tmp_step2);
		
		if(-1 < orderVO.getStep2().indexOf("60")){
			param.put("step2_60", "step2_60");
		}
		
		
		//초기 처리일자 조회구분(주문일, 결제확인일, 배송일, 배송완료일)
		param.put("dtkind", StringUtil.nvl(orderVO.getDtkind() , "orddt"));
		
		//초기 처리일자 값세팅
		if(!"".equals( StringUtil.nvl( orderVO.getPeriod(), "") )){
			//초기날짜등록
			param.put("sregdt_0", DateUtil.getDateFrom("yyyyMMdd","-"+StringUtil.nvl( orderVO.getPeriod(), "0")+"d") );
			param.put("sregdt_1", DateUtil.getDateString() );
		}else{
		
			//처리일자 세팅
			param.put("sregdt_0", orderVO.getSregdt() != null ? orderVO.getSregdt()[0] : "" );
			param.put("sregdt_1", orderVO.getSregdt() != null ? orderVO.getSregdt()[1] : "" );
		}
		
		orderVO.setDtkind( (String)param.get("dtkind") );
		orderVO.setSregdt(new String[]{(String)param.get("sregdt_0"), (String)param.get("sregdt_1")});
		
		//결제방법
		param.put("settlekind", orderVO.getSettlekind());
		
		//쿠폰사용여부
		param.put("couponyn", orderVO.getCouponyn());
		
		//현금영수증신청여부
		param.put("cashreceipt", orderVO.getCashreceipt());
		
		//판매사 코드 및 판매사명
		param.put("schSellerCd", orderVO.getSchSellerCd());
		
		//총건수
		orderVO.setRowCount( "group".equals(orderVO.getMode()) ? 1 : service.getOrderCount(param));
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, orderVO.getPageSize());
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, orderVO.getRowStart());
		
		if("group".equals(orderVO.getMode())){	
			orderVO.setOrderList(service.getOrderList2(param));							// 주문관리 > 주문관리 > 주문리스트(주문흐름으로 보기)
		}else{
			orderVO.setOrderList(service.getOrderList(param)); 							// 주문관리 > 주문관리 > 주문리스트(주문일로 보기)
		}

		return "shop/admin/order/list";
	}
	
	
	@RequestMapping(value = "orderExcelDownload")
	public void orderExcelDownload(@ModelAttribute("orderVO") OrderVO orderVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		/*
		 * 1.주문권한관리
		 * 
		 * */
		ShopLibFunction.menuAuthPermissionCheck(req, res, "order");
		
		/*
		 * 2.검색설정
		 * 
		 * */
		Map<String, Object> param = new HashMap<String, Object>();

		
		// 초기값 설정
		String orderPeriod = ShopConfig.getProperty("orderPeriod");
		
		if("1".equals(orderVO.getFirst()) && !"".equals( orderPeriod )  ){
			orderVO.setPeriod(orderPeriod);
		}
		
		// 검색타입(주문검색[통합])
		param.put("skey", orderVO.getSkey());
		
		// 검색어(주문검색[통합])
		param.put("sword", orderVO.getSword());
		
		// 검색어(주문처)
		param.put("sagent", orderVO.getSagent());
		
		// 검색어(스킨)
		param.put("sskin", orderVO.getSchSkin());
		
		// 검색어(배송국가)
		param.put("scountry", orderVO.getScountry());
		
		// 검색타입(상품검색[선택])
		param.put("sgkey", orderVO.getSgkey());
		
		// 검색어(상품검색[선택])
		param.put("sgword", orderVO.getSgword());
		
		// 주문상태(step, step2)
		param.put("step", orderVO.getStep());
		String tmp_step2 = "";
		String[] step2	= req.getParameterValues("step2");
		
		if( step2 != null ) {
			for(String str : step2) {
				if(StringUtils.isNotBlank(tmp_step2)) tmp_step2 += ",";
				tmp_step2 += str;
			}
		}
		
		param.put("step2", tmp_step2);
		
		if(-1 < orderVO.getStep2().indexOf("60")){
			param.put("step2_60", "step2_60");
		}
		
		
		//초기 처리일자 조회구분(주문일, 결제확인일, 배송일, 배송완료일)
		param.put("dtkind", StringUtil.nvl(orderVO.getDtkind() , "orddt"));
		
		//초기 처리일자 값세팅
		if(!"".equals( StringUtil.nvl( orderVO.getPeriod(), "") )){
			//초기날짜등록
			param.put("sregdt_0", DateUtil.getDateFrom("yyyyMMdd","-"+StringUtil.nvl( orderVO.getPeriod(), "0")+"d") );
			param.put("sregdt_1", DateUtil.getDateString() );
		}else{
		
			//처리일자 세팅
			param.put("sregdt_0", orderVO.getSregdt() != null ? orderVO.getSregdt()[0] : "" );
			param.put("sregdt_1", orderVO.getSregdt() != null ? orderVO.getSregdt()[1] : "" );
		}
		
		orderVO.setDtkind( (String)param.get("dtkind") );
		orderVO.setSregdt(new String[]{(String)param.get("sregdt_0"), (String)param.get("sregdt_1")});
		
		//결제방법
		param.put("settlekind", orderVO.getSettlekind());
		
		//쿠폰사용여부
		param.put("couponyn", orderVO.getCouponyn());
		
		//현금영수증신청여부
		param.put("cashreceipt", orderVO.getCashreceipt());
		
		//판매사 코드 및 판매사명
		param.put("schSellerCd", orderVO.getSchSellerCd());
		
		//총건수
		orderVO.setRowCount( "group".equals(orderVO.getMode()) ? 1 : service.getOrderCount(param));
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, orderVO.getPageSize());
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, orderVO.getRowStart());
		
		String[] title = {"번호","주문번호","주문일자","주문상태","주문자명"
						 , "주문자 이메일","주문자 연락처","수취인명","수취인 이메일","수취인  연락처"
						 , "수취인 국가","수취인 우편번호","수취인 주소","수취인 상세주소","수취인 City"
						 , "수취인 State","통관고유부호","결제수단","부티크명","브랜드명(영문)"
						 , "브랜드명(국문)","브랜드명(중문)","상품일련번호", "상품코드(SCM번호)","상품명(영문)","상품명(국문)","상품명(중문)"
						 , "옵션명1","옵션명2","원산지","수량","상품원판매가"
						 , "할인판매가","고객 실결제가","할인액합계","사용한 적립금","사용한 쿠폰명"
						 , "사용한 쿠폰할인가격","사용한 할인코드명","사용한 할인코드가격","지급예상 적립금","택배사"
						 , "송장번호","출고일","배송완료일","반품 택배사", "반품 송장번호"
						 };
		String[] field = {"rn", "ordno","orddt", "stepNm", "nameorder"
						 , "email", "mobileOrder", "nameReceiver", "emailReceiver", "mobileReceiver"
						 , "country", "zipcode", "address", "address2", "city"
						 , "state", "customs_num", "settlekindNm", "부티크명", "brandnm"
						 , "brandnmKR", "brandnmCN","goodsno", "goodscd", "goodsnm", "goodsnmKR", "goodsnmCN"
						 , "opt1", "opt2", "shippingOrigin", "ea", "consumer"
						 , "price", "prn_settleprice", "dcTotal", "emoney", "couponNm"
						 , "couponPrice", "dcCodeNm", "dcCodePrice", "addemoney", "deliverycomp"
						 , "invoice", "deliverySdt", "deliveryEdt", "deliveryReturncomp", "returnInvoice"
						 };
		try {
			//리스트 조회
			List<Map<String, Object>> excelList = service.selectOrderExcelList(param);
			if(excelList != null) {
				for(Map<String, Object> excelData : excelList) {
					excelData.put("stepNm", ShopLibFunction.r_stepi(String.valueOf(excelData.get("step")), String.valueOf(excelData.get("istep"))));
				}
			}
			
			ExcelUtil.writeExcel("주문 리스트_"+ FileUtil.getTimeStamp()+".xls", "정보", excelList, title, field, res);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 관리자 > 주문관리 > 주문리스트
	 * 팝업 - 다운로드 파일설정  
	 * @author CAR
	 */
	@RequestMapping(value="popup.orderxls")
	public String popupOrderXls(@ModelAttribute("orderExcelVO") OrderExcelVO orderExcelVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug("##### mode : "+orderExcelVO.getMode());
		}
		
		String[][] ordXls = null;
		if ("orderXls".equals(orderExcelVO.getMode())) {	// 주문별 항목설정
			String[] chk = StringUtil.split(ShopConfig.getProperty("orderXls"), "^");
			if (chk == null || chk.length == 0) chk = new String[19];
			int idx = 0;
			ordXls = new String[][] {
				{ "번호"			, "no"				, "순번 표시"							, "y".equals(chk[idx++]) ? "checked" : "" },
				{ "주문번호"		, "ordno"			, "주문번호 표시"						, "y".equals(chk[idx++]) ? "checked" : "" },
				{ "주문자명"		, "nameOrder"		, "주문자명 표시"						, "y".equals(chk[idx++]) ? "checked" : "" },
				{ "이메일"		, "email"			, "이메일 표시"							, "y".equals(chk[idx++]) ? "checked" : "" },
				{ "주문자전화번호"	, "phoneOrder"		, "주문자 전화번호 표시"					, "y".equals(chk[idx++]) ? "checked" : "" },
				{ "주문자핸드폰"	, "mobileOrder"		, "주문자 핸드폰 표시"					, "y".equals(chk[idx++]) ? "checked" : "" },
				{ "받는분이름"		, "nameReceiver"	, "받는분 이름 표시"						, "y".equals(chk[idx++]) ? "checked" : "" },
				{ "받는분전화번호"	, "phoneReceiver"	, "받는분 전화번호 표시"					, "y".equals(chk[idx++]) ? "checked" : "" },
				{ "받는분핸드폰"	, "mobileReceiver"	, "받는분 핸드폰번호 표시"					, "y".equals(chk[idx++]) ? "checked" : "" },
				{ "우편번호"		, "zipcode"			, "우편번호 표시"						, "y".equals(chk[idx++]) ? "checked" : "" },
				{ "주소"			, "address"			, "주소 표시"							, "y".equals(chk[idx++]) ? "checked" : "" },
				{ "배송메세지"		, "order_memo"		, "배송메세지 표시"						, "y".equals(chk[idx++]) ? "checked" : "" },
				{ "결제수단"		, "settlekind"		, "결제방법 표시"						, "y".equals(chk[idx++]) ? "checked" : "" },
				{ "결제금액"		, "settleprice"		, "결제금액 표시"						, "y".equals(chk[idx++]) ? "checked" : "" },
				{ "주문일자"		, "orddt"			, "주문한 일자를 표시 예)2007-10-10"		, "y".equals(chk[idx++]) ? "checked" : "" },
				{ "주문상태"		, "step"			, "주문상태 표시"						, "y".equals(chk[idx++]) ? "checked" : "" },
				{ "배송코드"		, "deliveryno"		, "배송사코드표시"						, "y".equals(chk[idx++]) ? "checked" : "" },
				{ "송장번호"		, "deliverycode"	, "송장번호표시"						, "y".equals(chk[idx++]) ? "checked" : "" },
				{ "배송일"		, "ddt"				, "배송일자 표시 예)2007-10-10 18:00:00"	, "y".equals(chk[idx++]) ? "checked" : "" }
			};
			
		} else {	// 상품별 항목설정
			String[] chk = StringUtil.split(ShopConfig.getProperty("orderGoodsXls"), "^");
			if (chk == null || chk.length == 0) chk = new String[29];
			int idx = 0;
			ordXls = new String[][] {
				{ "번호"			, "no"				, "순번 표시"							,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "일련번호"		, "sno"				, "주문상품의 일련번호"					,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "주문번호"		, "ordno"			, "주문번호 표시"						,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "주문자명"		, "nameOrder"		, "주문자명 표시"						,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "이메일"		, "email"			, "이메일 표시"							,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "주문자전화번호"	, "phoneOrder"		, "주문자 전화번호 표시"					,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "주문자핸드폰"	, "mobileOrder"		, "주문자 핸드폰 표시"					,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "받는분이름"		, "nameReceiver"	, "받는분 이름 표시"						,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "받는분전화번호"	, "phoneReceiver"	, "받는분 전화번호 표시"					,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "받는분핸드폰"	, "mobileReceiver"	, "받는분 핸드폰번호 표시"					,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "우편번호"		, "zipcode"			, "우편번호 표시"						,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "주소"			, "address"			, "주소 표시"							,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "배송메세지"		, "order_memo"		, "배송메세지 표시"						,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "결제수단"		, "settlekind"		, "결제방법 표시"						,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "주문일자"		, "orddt"			, "주문한 일자를 표시 예)2007-10-10"		,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "주문상태"		, "step"			, "주문상태 표시"						,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "상품명(영문)"	, "goodsnm"			, "주문 상품명(영문) 표시"				,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "상품명(국문)"	, "goodsnmKR"		, "주문 상품명(국문) 표시"				,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "상품명(중문)"	, "goodsnmCN"		, "주문 상품명(중문) 표시"				,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "상품코드"		, "goodscd"			, "주문 상품코드 표시"					,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "옵션"			, "opt"				, "주문 상품옵션 표시"					,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "원산지"		, "origin"			, "주문 상품 원산지 표시"					,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "브랜드"		, "brandnm"			, "주문 상품 브랜드 표시"					,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "수량"			, "ea"				, "주문 상품 수량 표시"					,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "매입가"		, "supply"			, "주문 상품 매입가 표시"					,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "상품가격"		, "price"			, "주문 상품가격 표시"					,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "주문결제가격"	, "sprice"			, "주문 상품 결제가격 표시"				,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "배송코드"		, "deliveryno"		, "배송사표시"							,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "송장번호"		, "deliverycode"	, "송장번호표시"						,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "배송일"		, "ddt"				, "배송일자 표시 예)2007-10-10 18:00:00"	,"y".equals(chk[idx++]) ? "checked" : "" }
			};
		}
		
		model.addAttribute("ordXls", ordXls);
	
		return "/shop/admin/order/popup.orderxls";
	}
	
	/**
	 * 관리자 > 주문관리 > 주문리스트
	 * 팝업 - 다운로드 파일설정 저장처리  
	 * @author CAR
	 */
	@RequestMapping(value="popup_indb.dojson" , method=RequestMethod.POST)
	public String popupOrderXlsIndb(@ModelAttribute("orderExcelVO") OrderExcelVO orderExcelVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug("##### mode : "+orderExcelVO.getMode());
			logger.debug("##### cfgChkVal : "+req.getParameter("cfgChkVal"));
		}
		
		try {
			if ("orderXls".equals(orderExcelVO.getMode())) {	// 주문별 항목설정
				ShopConfig.setProperty("orderXls", req.getParameter("cfgChkVal"));
			} else {	// 상품별 항목설정
				ShopConfig.setProperty("orderGoodsXls", req.getParameter("cfgChkVal"));
			}
			model.addAttribute("RESULT", true);
		} catch (Exception e) {
			model.addAttribute("RESULT", false);
		}
		return "dojson";
	}
	
	/**
	 * 관리자 > 주문관리 > 주문취소리스트
	 */
	@RequestMapping(value="cs")
	public String csView(@ModelAttribute("csVO") OrderCSVO csVO, HttpServletRequest req, Model model) throws Exception {
		String[] type = req.getParameterValues("type");
		if(type != null) {
			csVO.setType(type);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>order cs!!!");
		}

		List<OrderCancel> orderCancelList = this.service.getOrderCancelList(csVO);
		List<GdCode> gdCodeList = ShopLibFunction.codeitem("cancel");
		Map<String, String> codeMap = new HashMap<String, String>();
		for (GdCode gdCode : gdCodeList) {
			codeMap.put(gdCode.getItemcd(), gdCode.getItemnm());
		}
		int addopt = 0;
		for(OrderCancel list : orderCancelList) {
			list.setStepMsg(ShopLibFunction.getStepMsg(
					StringUtil.nvl(String.valueOf(list.getStep()), "0"), 
					String.valueOf(list.getIstep()), 
					String.valueOf(list.getOrdno()), 
					String.valueOf(list.getItemsno())));
			list.setCodeItem(codeMap.get(String.valueOf(list.getCode())));
			addopt = ShopLibFunction.getTotalOptionPrice(list.getAddopt());
			list.setPay(list.getPay() + addopt);
		}

		model.addAttribute("cancelList", orderCancelList);
		model.addAttribute("csVO", csVO);
		model.addAttribute("listSize", orderCancelList.size());
		
		return "shop/admin/order/cs";
	}
	
	/*
	 * 반품/교환 접수 리스트
	 * regoods/list
	 */
	@RequestMapping(value="regoods/list")
	public String regoodsList(@ModelAttribute("regoodsVO") OrderRegoodsVO regoodsVO) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@ order regoods ");
		}
		regoodsVO.setRegoodsList(service.getOrderRegoodsList(regoodsVO));
		
		return "/shop/admin/order/regoods";
	}
	
	/*
	 * 반품/교환 접수 처리
	 * regoods/indb
	 */
	@RequestMapping(value="regoods/indb")
	public String regoodsIndb(@ModelAttribute("regoodsVO")OrderRegoodsVO regoodsVO) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@ order regoods indb "+regoodsVO.getSno()+" :: "+regoodsVO.getChk().toString());
		}
		
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
		
		return "redirect:/shop/admin/order/regoods/list";
	}

	@RequestMapping("indb")
	public String indb(@ModelAttribute("orderListStepChangeVO") OrderListStepChangeVO orderListStepChangeVO) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("** OrderController >> indb");
			logger.debug(orderListStepChangeVO.toString());
		}
		
		this.service.changeOrderStep(orderListStepChangeVO);
		
		// this.service.excuteOrderModify(cancelVO);
		return "redirect:/shop/admin/order/list?mode=group&period=0&first=1";
	}
}