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
package com.wepinit.wepinit_shop.xmall.admin.controller.seller;

import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.seller.AdminSellerOrderService;

import com.wepinit.wepinit_shop.xmall.admin.vo.seller.*;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.session.AdminSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCode;
import com.wepinit.wepinit_shop.xmall.common.vo.join.OrderCancel;
import com.wepinit.wepinit_shop.xmall.common.vo.join.OrdercancelOrderitemMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="shop/admin/seller/*")
public class AdminSellerOrderController {
	private static final Logger logger = LoggerFactory.getLogger(AdminSellerOrderController.class);
	
	@Resource
	AdminSellerOrderService service;

	/**
	 * 판매사 주문리스트
	 * @param orderFM
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "sellerOrderList")
	public String sellerOrderList(@ModelAttribute("orderFM") AdminSellerOrderFM orderFM,
			HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {
		
		//model.addAttribute("noticeFM", service.getSellerNoticList(noticeFM));
		/*
		 * 1.주문권한관리
		 * 
		 * */
		this.service.loginAndPermissionCheck(req, res);
		
		/**
		 * 주문리스트 정보
		 */
		service.getOrderListInfo(orderFM,req);
		
		return "/shop/admin/seller/orderList";
	}
	
	/**
	 * 주문상세화면
	 * @param orderFM
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "sellerPopupOrder")
	public String popupOrder(@ModelAttribute("popupVO") AdminSellerPopupOrderFM popupVO,
			HttpServletRequest req, HttpServletResponse res, Model model)
					throws Exception {
		
		//주문상세 관련 데이터 조회
		service.getPopupOrderInfo(popupVO,req);
		
		model.addAttribute("popupVO", popupVO);
		
		if ("view".equals(req.getParameter("viewFlg"))) {
			// 주문리스트 > 주문상세내역 화면으로 이동시
			return "/shop/admin/seller/orderView";
		} else {
			// 주문상세내역 팝업으로 이동시
			return "/shop/admin/seller/orderPopup";
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
	@RequestMapping(value = "orderExcelDown")
	public String orderExcelDown(@ModelAttribute("orderExcelVO") AdminSellerOrderFM orderExcelVO,
			HttpServletRequest req, HttpServletResponse res, Model model)
					throws Exception {
		
		res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		req.setCharacterEncoding("UTF-8");
		res.setHeader("Content-Disposition","attachment;filename=GDorder_"+orderExcelVO.getMode()+"_"+ DateUtil.getDate("yyyyMMddHHmm")+".xls");
		res.setHeader("Content-Description", "JSP Generated Data");
		res.setHeader("Content-Description", "style=mso-number-format:'\\@'");
		
		/*
		 * 1.주문권한관리
		 */
		AdminSessionObject adminSO = null;
		String level = "";
		Map<String, Object> xkey = null;
		
		adminSO = AdminSessionObject.getSessionObject(req);
		
		if( adminSO.isLogin() && null != adminSO.getUserInfo() ){
			
			xkey = adminSO.getUserInfo().getXkey();
			level = String.valueOf(xkey.get("level"));
			
			if (logger.isDebugEnabled()) {
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>level" + level);
			}
			
			if( 0 > ShopConfig.getProperty("level"+level).indexOf("order")){
				throw new BizException("member.00002", CommonConstants.MAIN_ADMIN_URL);	// 주문관리 권한이 없습니다.
			}
		}
		
		String[][] ordXls = null;
		if ("order".equals(orderExcelVO.getMode())) {	
			String[] chk = StringUtil.split(ShopConfig.getProperty("orderXls"), "^");
			if (chk == null || chk.length == 0) chk = new String[19];
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
				{ "배송일"		, "ddt"				, chk[idx++] }
			};
		} else {
			String[] chk = StringUtil.split(ShopConfig.getProperty("orderGoodsXls"), "^");
			if (chk == null || chk.length == 0) chk = new String[29];
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
				{ "결제가격"		, "sprice"			, chk[idx++] },
				{ "배송코드"		, "deliveryno"		, chk[idx++] },
				{ "송장번호"		, "deliverycode"	, chk[idx++] },
				{ "배송일"		, "ddt"				, chk[idx++] }
			};
		}
		
		// 주문관리 > 주문관리 > 주문리스트 다운로드
		orderExcelVO.setOrderList(service.getOrderXls(orderExcelVO, req.getParameterValues("step2"),req));
		model.addAttribute("selConfList" , ordXls);
		
		
		return "/shop/admin/seller/orderDnXls";
	}
	
	/**
	 * 주문상세내용 수정
	 * @author CAR
	 */
	@RequestMapping(value="updateOrder")
	public String indb(@ModelAttribute("cancelVO") AdminSellerOrderCancelFM cancelVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug("##### cancelVO#### cancelVO#### cancelVO#### cancelVO#### cancelVO#### cancelVO#### cancelVO#### cancelVO#### cancelVO#### cancelVO#### cancelVO : " );
		}
		try {
			/** 파라미터 설정 **/
			Map<String, Object> param = new HashMap<String, Object>();
			if ("chkCancel".equals(cancelVO.getMode())) {	// 주문 취소/반품 처리
				param.put("snoArr", cancelVO.getSno());	
				this.service.orderCancel(cancelVO);
				model.addAttribute("RESULT", true);
				
			} if ("delOrder".equals(cancelVO.getMode())) {	// 주문 삭제 처리
				// 삭제처리
				service.excuteDeleteOrder(cancelVO);
				model.addAttribute("RESULT", true);
			} if ("modOrder".equals(cancelVO.getMode())) {	// 주문 수정 처리
				// 수정처리
				this.service.excuteOrderModify(cancelVO);
				
				//### 진행상황별 처리
				if (cancelVO.getStep() != null) {
					ShopLibFunction.ctlStep(cancelVO.getOrdno(), cancelVO.getStep());	// as-is : ctlStep
				}
				
				// 재고 조정
				ShopLibFunction.setStock(Long.parseLong(cancelVO.getOrdno()));
//				service.excuteSetStock(cancelVO);	// as-is : setStock
				// 결제금액 계산
				service.excuteSetPrnSettleprice(cancelVO);	// as-is : set_prn_settleprice
				String view = "";
				if ("view".equals(req.getParameter("viewFlg"))) {
					view = "&viewFlg=view";
				}
				return "redirect:/shop/admin/seller/sellerPopupOrder?ordno=" + cancelVO.getOrdno() + view;
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
	@RequestMapping(value="sellerCs")
	public String sellerCs(@ModelAttribute("csVO") AdminSellerOrderCSFM csVO, HttpServletRequest req, Model model) throws Exception {
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

		for(OrderCancel list : orderCancelList) {
			list.setStepMsg(ShopLibFunction.getStepMsg(
					StringUtil.nvl(String.valueOf(list.getStep()), "0"), 
					String.valueOf(list.getIstep()), 
					String.valueOf(list.getOrdno()), 
					String.valueOf(list.getItemsno())));
			list.setCodeItem(codeMap.get(String.valueOf(list.getCode())));
		}

		model.addAttribute("cancelList", orderCancelList);
		model.addAttribute("csVO", csVO);
		model.addAttribute("listSize", orderCancelList.size());
		
		return "shop/admin/seller/orderCs";
	}
	
	/**
	 * 반품/교환 접수 리스트
	 * regoods/list
	 */
	@RequestMapping(value="sellerReGoodsList")
	public String regoodsList(@ModelAttribute("regoodsVO") AdminOrderOrderRegoodsFM regoodsVO) throws Exception{
		
		regoodsVO.setRegoodsList(service.getOrderRegoodsList(regoodsVO));
		
		return "/shop/admin/seller/orderRegoods";
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
	@RequestMapping(value="sellerOrderRepay")
	public String sellerOrderRepay(@ModelAttribute("repayVO") AdminSellerRepayFM repayVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		//총건수
		repayVO.setRowCount(service.getRepayCount());		
		List<OrdercancelOrderitemMember> repayList = service.getRepayList(repayVO);
		for(int i =0;i<repayList.size();i++){
			OrdercancelOrderitemMember oom = repayList.get(i);
			oom.setCcnt(service.getRepayList2(oom));
			oom.setOiocObject(service.getRepayList3(oom.getoCordno()));
		}
		repayVO.setRepayList(repayList);		
		return "shop/admin/seller/orderRepay";
	}
	
	
	/**
	 * [판매자주문관리] > 판매자 반품/교환접수리스트 : 반품완료or교환완료 후 재주문 넣기
	 * @param orderListStepChangeVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sellerReGoodsList/orderIndb")
	public String indb(@ModelAttribute("regoodsVO") AdminOrderRegoodsFM regoodsVO) throws Exception {
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
		
		return "redirect:/shop/admin/seller/sellerReGoodsList";
	}
	
	/**
	 * [판매자주문관리] > 판매자 환불접수리스트 : 환불완료
	 * @param repayVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="repay/indb")
	public String repaydb(@ModelAttribute("repayVO") AdminOrderRepayFM repayVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		logger.debug("#@########################와놔");
		
		service.repaydb(repayVO,req);

		String returnUrl = "redirect:/shop/admin/seller/sellerOrderRepay";
		
		return returnUrl;
	}
	
}
