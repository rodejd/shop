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
package com.wepinit.wepinit_shop.xmall.seller.order.service;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.CodeUtil;
import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xcube.util.WebUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.order.OrderService;
import com.wepinit.wepinit_shop.xmall.admin.service.order.PopupOrderService;
import com.wepinit.wepinit_shop.xmall.admin.vo.order.OrderDeliveryVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.order.OrderPrintVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.dao.ShopLibFncMapper;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.service.ShopLibFncService;
import com.wepinit.wepinit_shop.xmall.common.session.SellerSessionObject;
import com.wepinit.wepinit_shop.xmall.common.util.ApplicationContextUtil;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.*;
import com.wepinit.wepinit_shop.xmall.front.service.order.FrontOrderService;
import com.wepinit.wepinit_shop.xmall.seller.order.dao.SellerOrderMapper;
import com.wepinit.wepinit_shop.xmall.seller.order.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author kang
 *
 */
@Service
@Transactional
public class SellerOrderService {
	private static final Logger logger = LoggerFactory.getLogger(SellerOrderService.class);
	
	@Resource
	SellerOrderMapper mapper;
	
	@Resource
	ShopLibFncMapper fncmapper;
	
	@Resource
	FrontOrderService frontOrdService;
	
	@Resource
	PopupOrderService popupOrderService;
	
	@Resource
	OrderService oService;

	public void repayReservePrice(Map<String,Object> param){
		mapper.repayReservePrice(param);
	}
	
	//2020-01-29 이현빈 환불 후 적립금 변경
	public void repayEmoneyPrice(Map<String,Object> param){
		mapper.repayEmoneyPrice(param);
	}
	
	public void couponYnStatus1(Map<String,Object> param){
		this.mapper.couponYnStatus1(param);
	}

	//2020-01-17 이현빈 사용쿠폰정보 가져오기
	public String [] getCouponApplySno(Map<String,Object> param){
		return mapper.getCouponApplySno(param);
	}
	
	//2020-01-21 이현빈 전체쿠폰 가져오기
	public String[] getAllCouponApplySno(String ordno){
		return mapper.getAllCouponApplySno(ordno);
	}
	
	public List<PopupOrder1> getItemList (Map<String,Object> param){
		return mapper.getItemList(param);
	}
	
	// 로그인 여부 체크
	public void loginAndPermissionCheck(HttpServletRequest req, HttpServletResponse res) throws Exception {
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		PrintWriter out = null;
		if( sellerSO.isLogin() && null != sellerSO.getUserInfo() ){
			logger.debug("#####################확인필요###=="+sellerSO.getUserInfo().toString());
			Map<String, Object> xkey = sellerSO.getUserInfo().getXkey();
			/**
			 * 기존 어드민 주문관리권한체크시 level 을 확인하여 권한을 체크했으나
			 * 판매사일경우 level에 관련된 내용이 정해지지 않아
			 * sellerCd 로 체크한다.
			 */
			String sellerCd = String.valueOf(xkey.get("sellerCd"));
			if (logger.isDebugEnabled()) {
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sellerCd" + sellerCd);
			}
			// 로그인시 권한체크
			if("".equals(StringUtil.nvl(sellerCd,""))){
				if("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))){
					throw new BizException("member.00001", CommonConstants.MAIN_SELLER_URL);	// 주문관리 권한이 없습니다.
				}
				
				try{
					res.setCharacterEncoding("UTF-8");
					res.setContentType("text/html;charset=UTF-8");
					out = res.getWriter();
					out.print(WebUtil.getAlertRedirect("주문관리 권한이 없습니다.", CommonConstants.MAIN_SELLER_URL));
					out.flush();
				}catch(Exception e){
					throw e;
				} finally{
					if( null != out ){
						out.close();
					}
				}	
			}
			
			
			/*String level = String.valueOf(xkey.get("level"));
			
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
			}*/
		}
	}

	/**
	 * @param orderFM
	 * @param req
	 * 주문리스트 view에 관련된 정보 조회
	 * @throws Exception 
	 */
	public void getOrderListInfo(SellerOrderFM orderFM,
			HttpServletRequest req) throws Exception {
		
		/*
		 * 1.검색설정
		 * 
		 * */
		Map<String, Object> param = new HashMap<String, Object>();
		
		// 초기값 설정
		String orderPeriod = ShopConfig.getProperty("orderPeriod");
		
		if("1".equals(orderFM.getFirst()) && !"".equals( orderPeriod )  ){
			orderFM.setPeriod(orderPeriod);
		}
		
		// 검색타입(주문검색[통합])
		param.put("skey", orderFM.getSkey());
		
		// 검색어(주문검색[통합])
		param.put("sword", orderFM.getSword());
		
		// 검색타입(상품검색[선택])
		param.put("sgkey", orderFM.getSgkey());
		
		// 검색어(상품검색[선택])
		param.put("sgword", orderFM.getSgword());
		
		// 주문상태(step, step2)
		param.put("step", orderFM.getStep());
		
		//주문상태 STEP2
		String tmp_step2 = "";
		String[] step2	= req.getParameterValues("step2");
		
		if( null != step2 ){
			
			// *** dynamic query *** 
			for(int i=0; i < step2.length ; i++){
				if(i != 0){
					tmp_step2 += " or ";
				}
				
				if("7".equals(step2[i])){
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
		
		if(-1 < orderFM.getStep2().indexOf("60")){
			param.put("step2_60", "step2_60");
		}
		
		//초기 처리일자 조회구분(주문일, 결제확인일, 배송일, 배송완료일)
		param.put("dtkind", StringUtil.nvl(orderFM.getDtkind() , "orddt"));
		
		//초기 처리일자 값세팅
		if(!"".equals( StringUtil.nvl( orderFM.getPeriod(), "") )){
			//초기날짜등록
			param.put("sregdt_0", DateUtil.getDateFrom("yyyyMMdd","-"+StringUtil.nvl( orderFM.getPeriod(), "0")+"d") );
			param.put("sregdt_1", DateUtil.getDateString() );
		}else{
			//처리일자 세팅
			param.put("sregdt_0", orderFM.getSregdt() != null ? orderFM.getSregdt()[0] : "" );
			param.put("sregdt_1", orderFM.getSregdt() != null ? orderFM.getSregdt()[1] : "" );
		}
		
		orderFM.setDtkind( (String)param.get("dtkind") );
		orderFM.setSregdt(new String[]{(String)orderFM.getSregdt_0(), (String)orderFM.getSregdt_1()});
		
		//결제방법
		param.put("settlekind", orderFM.getSettlekind());
				
		//쿠폰사용여부
		param.put("couponyn", orderFM.getCouponyn());
		
		//현금영수증신청여부
		param.put("cashreceipt", orderFM.getCashreceipt());
		
		//판매사 코드
		param.put("ssSellerCd", orderFM.getSsSellerCd());
						
		//주문리스트 건수
		orderFM.setRowCount("group".equals(orderFM.getMode()) ? 1 : this.getOrderCount(orderFM));
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, Integer.parseInt( StringUtil.N2S(ShopConfig.getProperty("orderPageNum"), "15")) );
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, orderFM.getRowStart());
				
		if("group".equals(orderFM.getMode())){	
			orderFM.setOrderList(mapper.getOrderList2(param));// 주문관리 > 주문관리 > 주문리스트(주문흐름으로 보기)
		}else{
			orderFM.setOrderList(mapper.getOrderList(param));// 주문관리 > 주문관리 > 주문리스트(주문일로 보기)
		}
		//판매사 주문리스트 판매금액 판매사별로 계산
		this.getSeller_Price(orderFM);
	}
	
	//주문리스트 건수
	public int getOrderCount(SellerOrderFM orderFM) throws Exception {
		return  mapper.getOrderCount(orderFM);
	}

	/**
	 * @param orderExcelVO
	 * @param parameterValues
	 * @return
	 * 주문별or상품별 엑셀 다운로드
	 */
	/*public List<OrderMember> getOrderXls(SellerOrderFM orderExcelVO,
			String[] parameterValues,HttpServletRequest req) {

		// 초기값 설정
		String orderPeriod = ShopConfig.getProperty("orderPeriod");
		
		if("1".equals(orderExcelVO.getFirst()) && !"".equals( orderPeriod )  ){
			orderExcelVO.setPeriod(orderPeriod);
		}
		
		//주문상태 STEP2
		String tmp_step2 = "";
		String[] step2	= req.getParameterValues("step2");
		
		if( null != step2 ){
			
			// *** dynamic query *** 
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
		orderExcelVO.setStep2(tmp_step2);
		
		if(-1 < orderExcelVO.getStep2().indexOf("60")){
			orderExcelVO.setStep2_60("step2_60");
		}
		
		//초기 처리일자 조회구분(주문일, 결제확인일, 배송일, 배송완료일)
		orderExcelVO.setDtkind(StringUtil.nvl(orderExcelVO.getDtkind() , "orddt"));
		
		//초기 처리일자 값세팅
		if(!"".equals( StringUtil.nvl( orderExcelVO.getPeriod(), "") )){
			//초기날짜등록
			orderExcelVO.setSregdt_0(DateUtil.getDateFrom("yyyyMMdd","-"+StringUtil.nvl( orderExcelVO.getPeriod(), "0")+"d"));
			orderExcelVO.setSregdt_1(DateUtil.getDateString());
		}else{
			//처리일자 세팅
			orderExcelVO.setSregdt_0(orderExcelVO.getSregdt() != null ? orderExcelVO.getSregdt()[0] : "" );
			orderExcelVO.setSregdt_1(orderExcelVO.getSregdt() != null ? orderExcelVO.getSregdt()[1] : "" );
		}
		
		orderExcelVO.setSregdt(new String[]{(String)orderExcelVO.getSregdt_0(), (String)orderExcelVO.getSregdt_1()});
		logger.debug("orderExcelVO >>" + orderExcelVO);
		if ("order".equals(orderExcelVO.getMode())) {	
			return mapper.getOrderXls(orderExcelVO);		// 주문별 다운로드
		} else {
			return mapper.getOrderGoodsXls(orderExcelVO);	// 상품별 다운로드
		}
	}
*/
	/**
	 * @param orderExcelVO
	 * @param parameterValues
	 * @return List<OrderExcel>
	 * 주문별 엑셀 다운로드
	 */
	public List<OrderExcel> getOrderXls(SellerOrderFM orderExcelVO,int chk_flag){
		// 초기값 설정
		String orderPeriod = ShopConfig.getProperty("orderPeriod");
		
		if("1".equals(orderExcelVO.getFirst()) && !"".equals( orderPeriod ) ){
			orderExcelVO.setPeriod(orderPeriod);
		}	
		//초기 처리일자 조회구분(주문일, 결제확인일, 배송일, 배송완료일)
		orderExcelVO.setDtkind(StringUtil.nvl(orderExcelVO.getDtkind() , "orddt"));
		
		//초기 처리일자 값세팅
		if(!"".equals( StringUtil.nvl( orderExcelVO.getPeriod(), "") )){
			//초기날짜등록
			orderExcelVO.setSregdt_0(DateUtil.getDateFrom("yyyyMMdd","-"+StringUtil.nvl( orderExcelVO.getPeriod(), "0")+"d"));
			orderExcelVO.setSregdt_1(DateUtil.getDateString());
		}else{
			//처리일자 세팅
			orderExcelVO.setSregdt_0(orderExcelVO.getSregdt() != null ? orderExcelVO.getSregdt()[0] : "" );
			orderExcelVO.setSregdt_1(orderExcelVO.getSregdt() != null ? orderExcelVO.getSregdt()[1] : "" );
		}
		
		orderExcelVO.setSregdt(new String[]{(String)orderExcelVO.getSregdt_0(), (String)orderExcelVO.getSregdt_1()});
		
		List<OrderExcel> orderList = null;
		String sellerCd = orderExcelVO.getSsSellerCd();
		if("order".equals(orderExcelVO.getMode())){
			orderList = mapper.getOrderXls(orderExcelVO);
		}else{
			orderList = mapper.getOrderGoodsXls(orderExcelVO);
		}
		//chk_flag 엑셀 파일 다운로드 될때 구매금액,결제금액에 값이 체크된경우
		switch(chk_flag){
		case 1:
			//case 1 판매사별 결제금액
			this.sellerPrice_Excel(orderList, sellerCd);
			break;
		case 2:
			//case 2 판매사별 구매금액
			this.goodsPrice_Excel(orderList, sellerCd);
			break;
		case 3:
			//case 3 판매사별 결재금액,구매금액
			this.sellerPrice_Excel(orderList, sellerCd);
			this.goodsPrice_Excel(orderList, sellerCd);
			break;
		}
		
		return orderList;
	}
	
	
	
	/**
	 * @param popupVO
	 * 주문상세 관련 데이터 조회
	 * @throws Exception 
	 */
	public void getPopupOrderInfo(SellerPopupOrderFM popupVO,HttpServletRequest req) throws Exception {

		String[] newordno = null;
		
//		### 은행 배열생성
		Properties bankPro = this.getBankProperties();
		int goodsPrice 	= 0;
		int memberDC 	= 0;
		int coupon 		= 0;
		int icancel 	= 0;
		long ordno 		= popupVO.getOrdno();
		
		//### 주문리스트 리퍼러
		popupVO.setReferer((!"".equals(popupVO.getReferer())) ? popupVO.getReferer() : req.getHeader("referer"));
		
		//### 입금계좌 정보
		popupVO.setTmpBank(mapper.ordersPopupOrder4());
		
		//주문 및 주문한 회원
		PopupOrder2 rtData = this.getOrderMember(popupVO.getOrdno());
		/*//20191203 이현빈 판매사별 주문단계 정보
		String sellerCd = getSessionSellerCd();
		int sistep = mapper.getOrdersPopupIstep(ordno, sellerCd);
		int sstep =rtData.getStep();
		if(sistep > 4 ){
			rtData.setIstep(rtData.getStep());
		}else{
			rtData.setIstep(sistep);
		}*/
		
		
		popupVO.setRtData(rtData);
		// 상품리스트
		this.setRtList(popupVO);
		
		// 정상 주문상품 갯수 구하기
		int icnt = mapper.ordersPopupOrder5(ordno);
		popupVO.setiCnt(icnt);
		
		//세금계산서 
		popupVO.setRtTax(mapper.paperTaxState(ordno));
		
		LinkedHashMap<String, String> lhm = ShopLibFunction.getArrStep();
		
		// 주문단계정보
		popupVO.setDeliveryBasis(ShopConfig.getProperty("delivery.basis"));
		
		String wskin = ConfigClass.WEB_SKIN;
		
		if(popupVO.getRtList().size() > 0) {
			for(PopupOrder1 order1 : popupVO.getRtList()) {
				int istep = order1.getIstep();
				if(ShopLibFunction.getNormalIstep(istep)) {
					
					int ea = order1.getEa();
					// 2017-09-05 : 상품가격에 고객이 선택한 상품의 옵션가도 추가하도록 내용 변경
					int totalOptionPrice = ShopLibFunction.getTotalOptionPrice(order1.getAddopt());					
					goodsPrice += (StringUtil.N2D(order1.getPrice()) * ea) + totalOptionPrice;
					
					memberDC += StringUtil.N2D(order1.getMemberdc());
					coupon += StringUtil.N2D(order1.getCoupon());
				}
				
				String bgColor = "#ffffff";
				if(order1.getIstep() > 40) {
					icancel++;
					bgColor = "#F0F4FF";
				} 
				
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
			
			popupVO.setGoodsPrice(goodsPrice);
			popupVO.setMemberDc(memberDC);
			popupVO.setCoupon(coupon);
			popupVO.setiCancel(icancel);
			popupVO.setWskin(wskin);
	
			//20191203 이현빈 판매금액 적립금 제외
			//할인액 계산(회원할인율 + 적용쿠폰)
			popupVO.setDiscount(memberDC + coupon);
			//실데이타 계산으로 결제금액 산출
			popupVO.setSettleprice(goodsPrice + rtData.getDelivery() - popupVO.getDiscount() + rtData.getEggfee());
		}
		
		popupVO.setSelectOption(WebUtil.makeSelectOption(lhm, String.valueOf(rtData.getIstep())));
		/*popupVO.setStepMsg(ShopLibFunction.getStepMsg(
				String.valueOf(rtData.getStep()),  
				//2019.12.05 이현빈 getStep2를 Istep으로 수정 판매사별 주문상태 적용 
				String.valueOf(rtData.getIstep()), 
				String.valueOf(rtData.getOrdno()), 
				""));
		*/
		//판매사 주문리스트는 istep 으로 재적용
		popupVO.setStepMsg(ShopLibFunction.r_istep(String.valueOf(rtData.getIstep())));
		
		int cnt = mapper.ordersPopupOrder6(ordno);
		popupVO.setCnt(cnt);
		if(cnt > 0) {
			List<Long> tmpRt7 = mapper.ordersPopupOrder7(ordno);
			if(tmpRt7.size() > 0) {
				for(int i = 0, size = tmpRt7.size(); i < size; i++) {
					newordno = new String[tmpRt7.size()];
					newordno[i] = String.valueOf(tmpRt7.get(i));
				}
			}	
		}
		popupVO.setNewordno(newordno);
		popupVO.getRtData().setOldordnoN2S(StringUtil.N2S(String.valueOf(rtData.getOldordno()), ""));
		
		popupVO.setTmpRt8(mapper.ordersPopupOrder8(ordno));
		popupVO.setTmpRt9(mapper.ordersPopupOrder9(ordno));
		
		// 2017-09-05 : cancel 이상한 데이터 들어가고 있어 주석처리 함. 구현시 재구현 필요
		//popupVO.setTmpRt10(this.service.ordersPopupOrder10(popupVO.getCancel()));
		for(PopupOrder4 order4 : popupVO.getTmpRt9()) {
			order4.setBankPro(bankPro.getProperty(StringUtil.getPadString(String.valueOf(order4.getBankcode()), 2, "0")));
		}
		
		// 주문취소 내역
		this.setOrderCancel(popupVO, bankPro);
		
	}
	
	/** 배송비 정보 세팅 
	 * @param
	 * */
	private void setDeliveryInfo(SellerPopupOrderFM popupVO) {
		popupVO.setDelivery(this.mapper.selectDeliveryInfo(popupVO.getRtList().get(0)));
		for(OrderDeliveryVO i : popupVO.getDelivery()){
			logger.debug("seller 배송정보  >>> " + i);
		}
		List<OrderDeliveryVO> deliveryList = popupVO.getDelivery();
		int intDelivery = 0;
		int intAddDelivery = 0;
		for (OrderDeliveryVO deliveryVO : deliveryList) {
			//2019-12-10  배송비정보 가져오기
			intDelivery = StringUtil.N2I(deliveryVO.getDeliveryPrice());
			intAddDelivery = StringUtil.N2I(deliveryVO.getAddDeliveryPrice());
		}
		//2019.12.10 
		popupVO.getRtData().setDelivery(intDelivery);
		popupVO.getRtData().setAddDelivery(intAddDelivery+"");
	}
	
	/**
	 * @return
	 * @throws Exception
	 * 은행 배열생성
	 */
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
	 * @param ordno
	 * @return
	 * 회원의 전화번호 자릿수가 11보다 적으면 전화번호를 세팅하지 않는 것으로 변경
	 */
	public PopupOrder2 getOrderMember(long ordno) {
		PopupOrder2 rtData = mapper.ordersPopupOrder2(ordno);
		logger.debug("rtData addDelivery >> " + rtData.getAddDelivery());
		//20191203 이현빈 판매사별 주문단계 정보
		String sellerCd = getSessionSellerCd();
		rtData.setIstep(mapper.getOrdersPopupIstep(ordno, sellerCd));
		
		logger.debug("eunjung getOrderMember>> " + rtData.toString());
		rtData.setMobileorder(this.formatCheckPhoneNumber(rtData.getMobileorder()));
		rtData.setPhoneorder(this.formatCheckPhoneNumber(rtData.getPhoneorder()));
		return rtData;
	}
	
	/**
	 * @param phoneNumber
	 * @return
	 * 화번호에 입력된 자릿수가 너무 적으면 표시해주지 않는 것으로 변경
	 */
	public String formatCheckPhoneNumber(String phoneNumber) {
		return phoneNumber.length() < 11 ? "" : phoneNumber;
	}
	
	/**
	 * @param popupVO
	 * @param bankPro
	 * @throws Exception
	 * 주문취소내역
	 */
	public void setOrderCancel(SellerPopupOrderFM popupVO, Properties bankPro) throws Exception {
		popupVO.setTmpRt11(mapper.ordersPopupOrder11(popupVO.getOrdno()));
		for(GdOrderCancel cancel : popupVO.getTmpRt11()) {
			List<GdCode> gdCodeList = ShopLibFunction.codeitem("cancel");
			Properties props = new Properties();
			for(GdCode gdCode : gdCodeList) {
				bankPro.setProperty(gdCode.getItemcd(), gdCode.getItemnm());
				props.setProperty(gdCode.getItemcd(), gdCode.getItemnm());
			}
			popupVO.setCancel(cancel.getSno());
			cancel.setCancelCode(props.getProperty(String.valueOf(cancel.getCode())));
			cancel.setTmpRt12(mapper.ordersPopupOrder12(popupVO.getCancel()));
			
			for(GdLogCancel logCancel : cancel.getTmpRt12()) {
				logCancel.setrStepi(ShopLibFunction.r_stepi(String.valueOf(logCancel.getPrev()), String.valueOf(logCancel.getNext())));
			}
		}
		
	}

	/**
	 * @param csVO
	 * @return
	 * 관리자 > 주문관리 > 주문취소리스트
	 */
	public List<OrderCancel> getOrderCancelList(SellerOrderCSFM csVO) {
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
				if("1".equals(typeArr[i])){
					tmp_type += " (b.cyn='n' and b.dyn='n') ";
				}else if("2".equals(typeArr[i])){
					tmp_type += " (b.cyn='y') ";
				}else if("3".equals(typeArr[i])){
					tmp_type += " (b.cyn='r') ";
				}else if("4".equals(typeArr[i])){
					tmp_type += " (b.dyn='y') ";
				}else if("5".equals(typeArr[i])){
					tmp_type += " (b.dyn='r' and b.cyn='y') ";
				}else if("6".equals(typeArr[i])){
					tmp_type += " (b.dyn='r' and b.cyn='r') ";
				}else if("7".equals(typeArr[i])){
					tmp_type += " (b.dyn='e' and b.cyn='e') ";
				}			
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
		sqlVO.setSsSellerCd(csVO.getSsSellerCd());
		
		csVO.setPageSize(15);
		csVO.setRowCount(this.mapper.cancelListCnt1(sqlVO));
		return this.mapper.cancelList1(sqlVO);
	}

	/**
	 * @param regoodsVO
	 * @return
	 * 주문 반품/교환접수리스트
	 */
	public List<Map<String, Object>> getOrderRegoodsList(
			SellerOrderRegoodsFM vo) {
		vo.setRowCount(mapper.getOrderRegoodsListCount(vo));
		
		List<Map<String, Object>> resultList = null;
		Map<String,Object> map = new HashMap<String,Object>();
		
		resultList = mapper.getOrderRegoodsList(vo);
		for(int i=0; i<resultList.size(); i++){
			map.put("sno", Integer.parseInt(String.valueOf(resultList.get(i).get("sno"))));
			map.put("ssSellerCd", vo.getSsSellerCd());
			resultList.get(i).put("infoList", mapper.getOrderRegoodsInfoList(map));
		}
		
		return resultList;
	}

	public int getRepayCount(SellerRepayFM repayVO) {
		return mapper.getRepayCount(repayVO);
	}

	public List<OrdercancelOrderitemMember> getRepayList(
			SellerRepayFM repayVO) {
		return mapper.getRepayList(repayVO);
	}

	public int getRepayList2(OrdercancelOrderitemMember oom) {
		return mapper.getRepayList2(oom);
	}

	public OrderitemOrdercancel getRepayList3(OrdercancelOrderitemMember oom) {
		return mapper.getRepayList3(oom);
	}

	public void changeOrderStep(
			SellerOrderListStepChangeVO orderListStepChangeVO) throws Exception{
		// 변경할 주문번호 (ordno) 배열
		String [] ordnos = orderListStepChangeVO.getOrdno().split(",");
		int ordnoCount = ordnos.length;
		
		// 차례대로 주문상태를 변경한다.
		for(int i = 0; i < ordnoCount; i++) {
			ShopLibFunction.ctlStep(ordnos[i], orderListStepChangeVO.getChangeStep());
		}
		
	}

	public void repaydb(SellerOrderRepayFM repayVO, HttpServletRequest req) throws Exception{
		String[] arrSNO = req.getParameterValues("chks");
		String[] rprice=req.getParameterValues("repays");
		String[] rfee = req.getParameterValues("repayfees");
		String[] m_no = req.getParameterValues("m_nos");
		String[] ordno = req.getParameterValues("ordnos");
		String[] bankcode = req.getParameterValues("bankcodes");
		String[] bankaccount = req.getParameterValues("bankaccounts");
		String[] bankuser = req.getParameterValues("bankusers");
		String[] sno = req.getParameterValues("snos");
		String[] oIsno = req.getParameterValues("oisno");
		
		ShopLibFncService shopLibFncService = null;
		shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
		
		int j;
		if ( null != arrSNO && 0 < arrSNO.length ) {
			for(int i=0;i<arrSNO.length;i++){
				j=Integer.parseInt(arrSNO[i]);		
				
				List<GdOrderItem> item = shopLibFncService.setPrnSettleprice1(Long.parseLong(ordno[j]));
				//chkFlag 주문상태가 전부 취소나 반품임을 확인하는 Flag
				boolean chkFlag = true;
				for(GdOrderItem goi : item){
					if(ShopLibFunction.getNormalIstep(goi.getIstep())){
						chkFlag = false;
						break;
					}
				}
				
				
				String ono=null;
				
				//gd_order_item 정보
				GdOrderItem orderItemInfo = oService.orderChkCancel3(oIsno[j]);
				//gd_order 정보
				Map<String,Object> orderInfo = oService.getOrderInfo(Long.parseLong(ordno[j]));
				
				repayVO.setRprice(rprice[j]);
				repayVO.setRfee(rfee[j]);
				repayVO.setM_no(m_no[j]);
				repayVO.setOrdno(ordno[j]);
				repayVO.setBankcode(bankcode[j]);
				repayVO.setBankaccount(bankaccount[j]);
				repayVO.setBankuser(bankuser[j]);
				repayVO.setSno(sno[j]);
				if(chkFlag){
					repayVO.setRemoney(StringUtil.nvl(orderInfo.get("emoney"),"0"));
				}else{
					repayVO.setRemoney("0");
				}
				
				updateOrderRepay1(repayVO);
				updateOrderRepay2(repayVO);
				ono = updateOrderRepay3(repayVO);
				repayVO.setOrdno(ono);
				updateOrderRepay4(repayVO);
			
				ShopLibFunction.setStock(Long.parseLong(ono));
				
				//쿠폰 복원 
				Map<String,Object> recoveryCoupon = new HashMap<String,Object>();
				recoveryCoupon.put("ordno", ordno[j]);
				//판매사 쿠폰 복원
				recoveryCoupon.put("goodsno", orderItemInfo.getGoodsno());
				String [] sellerCoupon = getCouponApplySno(recoveryCoupon);
				if(sellerCoupon.length > 0 && sellerCoupon != null){
					for(String s : sellerCoupon){
						recoveryCoupon.put("applysno", s);
						this.couponYnStatus1(recoveryCoupon);
					}
				}
			
				//chkFlag = true 이면 상품이 전부취소혹은반품일때
				if(chkFlag){
					int reserve = StringUtil.N2I(StringUtil.nvl(orderInfo.get("reserve"),"0"));
					if (reserve > 0){
						String msg = "환불에 의한 적립금 회수";
						ShopLibFunction.setEmoney(-reserve,Integer.parseInt(m_no[i]),Long.parseLong(ono),msg);
						orderInfo.put("reserve", "0");
						this.repayReservePrice(orderInfo);
					}
					//전체 쿠폰 복원 
					String[] allCoupon = getAllCouponApplySno(ordno[j]);
					if(allCoupon.length > 0 && allCoupon != null){
						recoveryCoupon.clear();
						recoveryCoupon.put("ordno", ordno[j]);
						for (String s : allCoupon) {
							recoveryCoupon.put("applysno", s);
							this.couponYnStatus1(recoveryCoupon);
						}
					}
				
					int emoney = StringUtil.N2I(StringUtil.nvl(orderInfo.get("emoney"),"0"));
					if(emoney > 0){
						ShopLibFunction.setEmoney(emoney,Integer.parseInt(m_no[i]),Long.parseLong(ordno[i]),"주문 환불로 인한 사용적립금 환원");
						orderInfo.put("emoney", "0") ;
						this.repayEmoneyPrice(orderInfo);
					}
					ShopLibFunction.setPrnSettleprice(Long.parseLong(ordno[i]));
				}
				
				
			}
		}
		
	}
	
	public void updateOrderRepay1(SellerOrderRepayFM repayVO) {
		mapper.updateOrderRepay1(repayVO);
	}

	public void updateOrderRepay2(SellerOrderRepayFM repayVO) {
		mapper.updateOrderRepay2(repayVO);
	}

	public String updateOrderRepay3(SellerOrderRepayFM repayVO) {
		// TODO Auto-generated method stub
		return mapper.updateOrderRepay3(repayVO);
	}

	public void updateOrderRepay4(SellerOrderRepayFM repayVO) {
		mapper.updateOrderRepay4(repayVO);
	}

	public List<HashMap> updateOrderRepay5(SellerOrderRepayFM repayVO) {
		return mapper.updateOrderRepay5(repayVO);
	}

	public void couponYnStatus(SellerOrderRepayFM repayVO) {
		mapper.couponYnStatus(repayVO);
	}

	public HashMap libfuncCtlStep1(SellerOrderRepayFM repayVO) {
		return mapper.libfuncCtlStep1(repayVO);
	}

	public void orderCancel(SellerOrderCancelFM cancelVO) throws Exception{
		long ordno = Long.parseLong(cancelVO.getOrdno());
		// 취소/반품 처리
		this.excuteOrderCancel(cancelVO);	// as-is : chkCancel
		// 재고 조정
		ShopLibFunction.setStock(ordno);
		// 결제금액 계산
		ShopLibFunction.setPrnSettleprice(ordno); //as-is : set_prn_settleprice
		
	}
	
	public void excuteOrderCancel(SellerOrderCancelFM cancelVO) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("PopupOrderService >> excuteOrderCancel");
		}
		this.setParamAndInsertOrderCancel(cancelVO);
		this.insertOrUpdateLogCancelAndOrderCancel(cancelVO);
	}
	
	/**
	 * 주문건수 취소
	 * @param cancelVO
	 */
	private void setParamAndInsertOrderCancel(SellerOrderCancelFM cancelVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("PopupOrderService >> setParamAndInsertOrderCancel");
		}		
		String [] arr_sno = cancelVO.getSno();
		String[] name = cancelVO.getpName().split("\\,");
		String[] code = cancelVO.getCode().split("\\,");
		String[] memo = cancelVO.getMemo().split("\\,");
		String[] bankcode = cancelVO.getBankcode().split("\\,");
		String[] bankaccount = cancelVO.getBankaccount().split("\\,");
		String[] bankuser = cancelVO.getBankuser().split("\\,");
		
		for (int i = 0; i < arr_sno.length; i++) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("ordno", cancelVO.getOrdno());
			param.put("name", name[i]);
			param.put("code", code[i]);
			param.put("memo", memo[i]);
			param.put("bankcode", bankcode[i]);
			param.put("bankaccount", bankaccount[i]);
			param.put("bankuser", bankuser[i]);
			this.mapper.orderChkCancel1(param); // INSERT gd_order_cancel
		}
	}
	
	private void insertOrUpdateLogCancelAndOrderCancel(SellerOrderCancelFM cancelVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("PopupOrderService >> insertOrUpdateLogCancelAndOrderCancel");
		}
		
		String[] arr_sno = cancelVO.getSno();
		String[] arr_ea = cancelVO.getEa();
		
		Map<String,Object> cancelMap = new HashMap<String,Object>();
		cancelMap.put("ordno", cancelVO.getOrdno());
		
		int orino_cancel = popupOrderService.getCancelNumber(cancelVO.getOrdno()); // SELECT gd_order_cancel
		int idx = arr_sno.length - 1;
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
				if(istep == 40 || istep < 40){
					istep = "n".equals(orderItem.getCyn()) && "n".equals(orderItem.getDyn()) ? 44 : 41;
				}else if(istep == 70 || istep == 60){
					istep ++ ;
				}
				ea_3 = orderItem.getEa();
				
				Map<String,Object> map = popupOrderService.getDeliveryInfoForInsertCancel(arr_sno[i]);
				
				GdLogCancel params = new GdLogCancel();
				params.setOrdno(Long.valueOf(cancelVO.getOrdno()));
				params.setItemno(Integer.parseInt(arr_sno[i]));
				params.setCancel(no_cancel);
				params.setPrev(orderItem.getIstep());
				params.setNext(istep);
				params.setGoodsnm(orderItem.getGoodsnm());
				params.setEa(orderItem.getEa());
				params.setDeliveryPrice(StringUtil.N2I(String.valueOf(map.get("deliveryPrice"))));
				params.setAddDeliveryPrice(StringUtil.N2I(String.valueOf(map.get("addDeliveryPrice"))));
				params.setGoodsno(Integer.parseInt(String.valueOf(map.get("goodsno"))));
				params.setPaymentTerms(Integer.parseInt(String.valueOf(map.get("paymentTerms"))));
				
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
			String sellerCd = popupOrderService.getSellerCdForCancel(arr_sno[i]);
			if("admin".equals(sellerCd)){
				//sellercd 가 공백이면 관리자 코드이므로 admin 으로 받게 해놨는데 
				//gdgoods에서 관리자 sellerCd는 공백이나 null로 되있어서 따로 설정을 해줘야 할듯 . 
			}else{
				cancelMap.put("sellerCd", sellerCd);
				//주문상품중 판매사 코드에 해당하는 상품전체수 - 취소상품수   
				int sellerCdCancelCnt = popupOrderService.checkAllCancelOfSellerGoods(cancelMap);
				//sellerCdCancelCnt == 0 한 판매사 상품이 전부 취소
				if(sellerCdCancelCnt == 0){
					//주문상품에 해당판매사 상품만 조회
					String[] goodsno = popupOrderService.getCancelGoodsOfSeller(cancelMap);
					for(String s : goodsno){
						cancelMap.put("cgoodsno", s);
						cancelMap.put("deliveryPrice", 0);
						cancelMap.put("addDeliveryPrice", 0);
						popupOrderService.updateDeliveryPrice1(cancelMap);
					}
				}
			}
		}
	}
	
	/** 결재금액 계산 */
	public void excuteSetPrnSettleprice(SellerOrderCancelFM cancelVO) throws Exception {

		/** 파라미터 설정 **/
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ordno", cancelVO.getOrdno());
		
		List<Map<String, Object>> items = mapper.orderSetPrnSettleprice1(param);	// SELECT gd_order_item
		
		int tcnt = 0;
		int icnt = 0;
		int settleprice1 = 0;
		int settleprice2 = 0;
		
		if (items != null && items.size() > 0) {
			for(Map<String, Object> item : items) {
				tcnt++;
				if (Integer.valueOf(String.valueOf(item.get("istep"))) > 40) {
					icnt++;
					settleprice1 += ( Integer.valueOf(String.valueOf(item.get("price"))) 
									- Integer.valueOf(String.valueOf(item.get("memberdc")))
									* Integer.valueOf(String.valueOf(item.get("ea"))) );
				} else {
					settleprice2 += ( Integer.valueOf(String.valueOf(item.get("price"))) 
							- Integer.valueOf(String.valueOf(item.get("memberdc")))
							* Integer.valueOf(String.valueOf(item.get("ea"))) );
				}
			}
		}
		
		Map<String, Object> order = mapper.orderSetPrnSettleprice2(param);	// SELECT gd_order
		int rt2_enuri = 0;
		int rt2_delivery = 0;
		int rt2_m_no = 0;
		int rt2_emoney = 0;
		int rt2_eggFee = 0;
		int rt2_coupon = 0;
		int csettleprice = 0;
		if (null != order) {
			rt2_enuri = Integer.valueOf(String.valueOf(order.get("enuri")));
			rt2_delivery = Integer.valueOf(String.valueOf(order.get("delivery")));
			rt2_m_no = Integer.valueOf(String.valueOf(order.get("m_no")));
			rt2_emoney = Integer.valueOf(String.valueOf(order.get("emoney")));
			rt2_eggFee = Integer.valueOf(String.valueOf(order.get("eggFee")));
			rt2_coupon = Integer.valueOf(String.valueOf(order.get("coupon")));
		}

		if (tcnt == icnt) { //모두 주문취소일 경우
			csettleprice = settleprice1 - rt2_enuri + rt2_delivery - rt2_emoney + rt2_eggFee - rt2_coupon;
		} else {
			csettleprice = settleprice2 - rt2_enuri + rt2_delivery - rt2_emoney + rt2_eggFee - rt2_coupon;
		}
		
		param.put("csettleprice", csettleprice);
		// 구매금액에 대하여 정의한후 정의에 맞게 수정필요
		//mapper.orderSetPrnSettleprice3(param);	// UPDATE gd_order
		
		int rt2_member_sum = 0;
		int rt2_member_cnt = 0;
		if (rt2_m_no > 0) { //회원일경우 회원 구매 금액 업데이트 (배송완료건에 대해서만 조회함)
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
	
	private boolean isEmoneyRefundable(GdOrder order) {
		return order.getMno() != 0 && order.getEmoney() != 0 && order.getStep() < 1
				? true : false;
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

	public void excuteDeleteOrder(SellerOrderCancelFM cancelVO) {
		/** 파라미터 설정 **/
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ordno", cancelVO.getOrdno());
		
		mapper.orderDeleteInsert(param);	// INSERT gd_order_del
		mapper.orderDelete(param);			// DELETE gd_order
		
	}
	
	/**
	 * 주문 수정
	 */
	public void excuteOrderModify(SellerOrderCancelFM cancelVO) throws Exception {
		/** 파라미터 설정 **/
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("step", cancelVO.getStep());	
		param.put("step2", cancelVO.getStep2());	
		param.put("ordno", cancelVO.getOrdno());	
		
		String[] arr_sno	= cancelVO.getSno();
		String[] arr_ea		= cancelVO.getEa();
		String[] arr_price	= cancelVO.getPrice();
		String[] arr_supply	= cancelVO.getSupply();
		
		String goodsno= "";		
		int memberDC = 0; 	//회원할인가
		int priceSum = 0;		//상품금액
		String allCoupon = "";	//전체쿠폰
		
		List<Map<String, String>> couponInfo = mapper.orderModifyDC1(cancelVO.getOrdno());
		for(Map<String, String> cou : couponInfo){
			//전체쿠폰이 존재하면 저장
			if("0".equals(cou.get("goodstype"))){
				allCoupon += cou.get("price") + ",";
			}
		}
		
		//회원할인율 정보
		int mDC = mapper.orderModifyDC2(cancelVO.getUserNm());
		int coupon = 0; // 쿠폰할인액 
		double memdc = mDC * 0.01; // 회원할인율
		//주문상세내역 수정 
		if (arr_sno != null && arr_sno.length > 0) {
			for ( int i = 0 ; i < arr_sno.length ; i++ ) {
				// 주문상품 수량 변경시 재고 수정
				param.put("sno", Integer.parseInt(arr_sno[i]));	
				//orderItem 상품정보
				GdOrderItem orderItem = mapper.orderModify1(param);	// SELECT gd_order_item
				if (logger.isDebugEnabled()) logger.debug("#####[CAR] orderItem : " + orderItem.toString());
				//주문상태가 주문접수이고 수량의변화가 있을 때 또는 가격의변화가있을 때 또는 매입가의 변화가 있을때 
				if ("0".equals(cancelVO.getStep()) 
					&& ( !arr_ea[i].equals(String.valueOf(orderItem.getEa())) 
					|| !arr_price[i].equals(String.valueOf(orderItem.getPrice()))
					|| !arr_supply[i].equals(String.valueOf(orderItem.getSupply())) ) ) {
						if (logger.isDebugEnabled()) logger.debug("#####[CAR] 주문내역변경은 주문접수상태에서 가능 ");
						goodsno = String.valueOf(orderItem.getGoodsno());
						// 상품 갯수 변경
						if (!arr_ea[i].equals(String.valueOf(orderItem.getEa()))) {
							int imode = ("n".equals(orderItem.getStockyn()) ? 1 : -1);
							
							if(imode == -1){
								param.put("goodsno", orderItem.getGoodsno());
								param.put("opt1", StringUtil.nullConv(orderItem.getOpt1(), ""));
								param.put("opt2", StringUtil.nullConv(orderItem.getOpt2(), ""));
								
								int cstock = mapper.orderModify2(param);	// SELECT gd_goods_option
									cstock = cstock +( imode * ( Integer.parseInt(arr_ea[i]) - orderItem.getEa() ) );
								
								if (cstock < 0 ) {
									cstock = 0;
								}
								param.put("cstock", cstock);
								mapper.orderModify3(param);	// UPDATE gd_goods_option
							}
						}
						//합계 가격 구하기
						priceSum = Integer.parseInt(arr_ea[i]) * StringUtil.N2I(arr_price[i]);
						//할인가
						memberDC = (int) (priceSum * memdc);
						//쿠폰정보
						for(Map<String, String> cou : couponInfo){
							if(goodsno.equals(cou.get("goodsno"))){
								coupon = priceSum - ShopLibFunction.getDcprice(Integer.toString((priceSum)) ,cou.get("price"));
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
		//gd_order Update
		int totalPrice = 0 ;	//상품총금액
		int totalAddOption = 0;	//추가옵션 금액 합계
		int totalCoupon = 0 ; 	//Coupon 합계
		int totalDC = 0 ;		//memberDC 합계
		String couponPrice[] = allCoupon.split(",");
		
		//gd_order_item List
		List<GdOrderItem> itemInfo = fncmapper.setPrnSettleprice1(Long.parseLong(cancelVO.getOrdno()));
		for(GdOrderItem orderItem : itemInfo){
			totalAddOption += ShopLibFunction.getTotalOptionPrice(orderItem.getAddopt());
			totalCoupon += orderItem.getCoupon();
			totalPrice += orderItem.getEa() * orderItem.getPrice();
			totalDC += orderItem.getMemberdc();
		}
		//전체쿠폰
		if(couponPrice != null && 0 < couponPrice.length){
			int addCoupon = 0;
			for(String idx : couponPrice){
				addCoupon = totalPrice - ShopLibFunction.getDcprice(String.valueOf(totalPrice) , idx);
			}
			totalCoupon += addCoupon;
		}
		//상품총금액
		totalPrice += totalAddOption;
		//상품결제금액
		int prn_settlePrice = 0;
		
		long ordno = Long.parseLong((String) param.get("ordno"));
		Map<String,Object> orderInfo = mapper.getOrderInfo(ordno);
		int gd_delivery = (int) orderInfo.get("delivery");		//배송비
		int gd_addDelivery = (int) orderInfo.get("addDelivery");//추가배송비
		int gd_emoney = (int) orderInfo.get("emoney");			//적립금
		//상품결제금액 = 상품총금액 - 쿠폰가 - 회원할인 - 적립금 + 배송비 + 추가배송비
		prn_settlePrice = totalPrice - totalCoupon - totalDC - gd_emoney + gd_delivery + gd_addDelivery;
		
		logger.debug("******************************************");
		logger.debug("최종수정값 >>> " + prn_settlePrice);
		logger.debug("******************************************");
		
		String deliverycode = cancelVO.getDeliverycode().replaceAll("-","");
		param.put("enuri", 		StringUtil.N2D(cancelVO.getEnuri().equals("") ? "0" : cancelVO.getEnuri()));
		param.put("zipcode", 	cancelVO.getZipcode());
		param.put("address", 	cancelVO.getAddress());
		param.put("memo", 		cancelVO.getMemo());
		param.put("adminmemo", 	cancelVO.getAdminmemo());
		param.put("settlelog", 	cancelVO.getSettlelog());
		param.put("bankAccount", 	Integer.parseInt(StringUtil.nvl(cancelVO.getBankaccount(), "0")));
		param.put("bankSender", 	cancelVO.getBankSender());
		param.put("nameReceiver", 	cancelVO.getNameReceiver());
		param.put("phoneReceiver", 	cancelVO.getPhoneReceiver());
		param.put("mobileReceiver", cancelVO.getMobileReceiver());
		param.put("deliverycode", 	deliverycode);
		param.put("coupon", 		totalCoupon);
		param.put("goodsprice", totalPrice);
		param.put("memberdc", totalDC);
		param.put("prn_settleprice", prn_settlePrice);
		mapper.orderModify5(param);	// UPDATE gd_order
		
		this.updateDeliveries(cancelVO);
		
	}

	/** 송장번호 및 택배사 정보 배송정보 테이블에 업데이트 */
	private void updateDeliveries(SellerOrderCancelFM vo) {
		String [] invoices = vo.getDeliverycode().split(",");	// 송장번호 invoice
		String [] deliveryCompCds = vo.getDeliveryno().split(",");		// 택배사 deliveryCompCd
		String [] goodsnos = vo.getGoodsno();		// 상품코드 goodsno
		String ordno = vo.getOrdno();		// 주문번호 ordno 
		String [] opt = vo.getOpt().split(",");
		logger.debug("eunjung >> " + invoices.length);
		
		for (int i = 0; i < invoices.length; i++) {
			OrderDeliveryVO delivery = new OrderDeliveryVO();
			delivery.setInvoice(isNoData(invoices[i]) ? "" : invoices[i]);
			delivery.setDeliveryCompCd(isNoData(deliveryCompCds[i]) ? "" : deliveryCompCds[i]);
			delivery.setGoodsno(goodsnos[i]);
			delivery.setOrdno(ordno);
			delivery.setOpt(opt[i]);
			this.mapper.updateDeliveryInfo(delivery);
		}
		
	}
	
	public void updateOrderRegoods(SellerOrderRegoodsFM vo) throws Exception{
		mapper.updateOrderRegoodsItem(vo.getSno());
		vo.setOrdno(mapper.getOrderRegoodsCancel(vo.getSno()));
		mapper.updateOrderRegoods(vo.getOrdno());
		//재고 조정
		/*ShopLibFunction.setStock(vo.getOrdno());

		Map<String, Object> orderObj = null;
		orderObj = mapper.getOrderInfo(vo.getOrdno());
		
		//반품에 의한 적립금 회수
		if(orderObj != null){
			ShopLibFunction.setEmoney(-StringUtil.N2D(String.valueOf(orderObj.get("reserve"))), 
					Integer.parseInt(String.valueOf(orderObj.get("m_no"))), vo.getOrdno(), "반품에 의한 적립금 회수");
		}*/
		
	}

	/**
	 * 주문아이템 처리
	 * @param regoodsVO
	 */
	public void updateOrderExchange(SellerOrderRegoodsFM vo) throws Exception{
		mapper.updateOrderItemExchange(vo.getSno());
		vo.setOrdno(mapper.getOrderExchangeCancel(vo.getSno()));
		mapper.updateOrderExchange(vo.getOrdno());
		//재고 조정
		ShopLibFunction.reorder(vo.getOrdno(), vo.getSno());
		
	}
	

	/**
	 * 세션 판매사코드 조회
	 * @return
	 */
	public String getSessionSellerCd() {
		return SellerSessionObject.getSessionObject().getLoginValue("SELLER_CD");
	}
	
	private boolean isNoData(String data) {
		return "0".equals(data) || "".equals(data) || data == null;
	}

	public void setRtList(SellerPopupOrderFM popupVO) {
		String sellerCD = getSessionSellerCd();
		List<PopupOrder1> rtList = mapper.ordersPopupOrder1(popupVO.getOrdno(),sellerCD);
		popupVO.setRtList(rtList);
		// 배송비
		this.setDeliveryInfo(popupVO);	
		List<OrderDeliveryVO> deliveryList = popupVO.getDelivery();

		// goodsno 배열 만들기
		String[] goodsnos = new String[rtList.size()];
		for (int i = 0; i < goodsnos.length; i++) {
			goodsnos[i] = String.valueOf(rtList.get(i).getGoodsno());
		}
		int index = 0;
		if(rtList.size() > 0) {
			for(PopupOrder1 order1 : rtList) {
			
				// 상품가격에 고객이 선택한 상품의 옵션가도 추가하도록 내용 변경
				int totalOptionPrice = ShopLibFunction.getTotalOptionPrice(order1.getAddopt());	
				logger.debug("옵션가격"+totalOptionPrice);
				rtList.get(index).setAddoptPrice(Integer.toString(totalOptionPrice));
				index++;
			}
		}		
		//String addressSido = popupVO.getRtData().getAddress().substring(0, 2);
		//이미 조회된 추가배송비 내역을 왜 또 조회해오는지 알수 없어 주석처리
		/*
		List<OverDeliveryVO> overDeliveryList = frontOrdService.getOverDeliveryPolicy(goodsnos, addressSido);
		Map<String, String> overDeliveryMap = new HashMap<String, String>();
		for (OverDeliveryVO overDelivery : overDeliveryList) {
			overDeliveryMap.put(overDelivery.getGoodsno(), overDelivery.getPrice());
		}
		*/
		String goodsno = "";
		for (int i = 0; i < rtList.size(); i++) {
			PopupOrder1 order1 = rtList.get(i);
			OrderDeliveryVO deliveryVO = deliveryList.get(i);
			goodsno = "".equals(order1.getSellerCd()) ? "admin" : String.valueOf(order1.getGoodsno());
			order1.setAddDeliveryPrice(deliveryVO.getAddDeliveryPrice());// overDeliveryMap.get(goodsno));
			order1.setDeliveryPrice(deliveryVO.getDeliveryPrice());
			order1.setPaymentTerms(deliveryVO.getPaymentTerms());
		}
		popupVO.setRtList(rtList);
	}
	
	public String[][] selectSellerOrderXls(String mode) {
		
		String[][] ordXls = null;
		if ("orderXls".equals(mode)) {	// 주문별 항목설정
			
			String settings = this.mapper.selectSellerOrderXlsSettings(this.getSessionSellerCd());
			String[] chk = null;
			if(settings != null || "".equals(settings)){
				//항목 체크값 가져오기 
				chk = StringUtil.split(settings, "^");
			}else{
				//데이터가 없으면 새로만듬
				chk = new String[20];
			}
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
				{ "배송일"		, "ddt"				, "배송일자 표시 예)2007-10-10 18:00:00"	, "y".equals(chk[idx++]) ? "checked" : "" },
				{ "판매사"		, "sellerNm"		, "판매사명 표시"						, "y".equals(chk[idx++]) ? "checked" : "" }
			};
			
		} else {	// 상품별 항목설정
			String settings = this.mapper.selectSellerOrderGoodsXlsSettings(this.getSessionSellerCd());
			String[] chk = null;
			if(settings != null || "".equals(settings)){
				chk = StringUtil.split(settings, "^");
			}else{
				 chk = new String[30];
			}
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
				{ "주문결제가격"		, "sprice"		, "주문 상품 결제가격 표시"				,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "배송코드"		, "deliveryno"		, "배송사표시"							,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "송장번호"		, "deliverycode"	, "송장번호표시"						,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "배송일"		, "ddt"				, "배송일자 표시 예)2007-10-10 18:00:00"	,"y".equals(chk[idx++]) ? "checked" : "" },
				{ "판매사"		, "sellerNm"		, "판매사명 표시"						,"y".equals(chk[idx++]) ? "checked" : "" }
			};
		}
		
		return ordXls;
	}
	
	public boolean updateSellerOrderXls(SellerOrderExcelFM excelFM) {
		if (logger.isDebugEnabled()) {
			logger.debug("SellerOrderService > updateSellerOrderXls >> " + excelFM.toString());
		}
		
		excelFM.setSellerCd(this.getSessionSellerCd());
		
		try {
			if ("orderXls".equals(excelFM.getMode())) {	// 주문별 항목설정
				this.mapper.updateSellerOrderXlsSettings(excelFM);
			} else {	// 상품별 항목설정
				this.mapper.updateSellerOrderGoodsXlsSettings(excelFM);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 엑셀 항목설정 데이터 가져오기 - 주문별
	 * */
	public String selectSellerOrderXlsSettings(String sellerCd) {
		return this.mapper.selectSellerOrderXlsSettings(sellerCd);
	}

	/**
	 * 엑셀 항목설정 데이터 가져오기 - 상품별
	 * */
	public String selectSellerOrderGoodsXlsSettings(String sellerCd) {
		return this.mapper.selectSellerOrderGoodsXlsSettings(sellerCd);
	}
	
	/**
	 * 주문상세 출력 > report
	 * @throws Exception 
	 * */
	public void sellerOrderPrintReport(OrderPrintVO printVO) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>orderService orderPrintReport");
		}
		
//		//### 은행 배열생성
//		Properties bankPro = this.getBankProperties();
		String sellerCd = printVO.getSellerCd();
//		//### 주문리스트 리퍼러
		String referer = !"".equals(printVO.getReferer()) ? printVO.getReferer() : "" ;
		printVO.setReferer(referer);
		
//		//### 배송업체 정보
		List<GdListDelivery> tmpDeli = popupOrderService.ordersPopupOrder3();
		printVO.setTmpDeli(tmpDeli);

//		//### 입금계좌 정보
		List<GdListBank> tmpBank = popupOrderService.ordersPopupOrder4();
		printVO.setTmpBank(tmpBank);
		Long ordno = printVO.getOrdno();
//		// 상품리스트
		List<PopupOrder1> rtList = this.sellerOrdersPopupOrder1(ordno,sellerCd); //판매자꺼만 나오게 수정

		if(rtList.size() > 0){
			for(PopupOrder1 order1 : rtList){
				// 상품가격에 고객이 선택한 상품의 옵션가도 추가하도록 내용 변경
				int totalOptionPrice = ShopLibFunction.getTotalOptionPrice(order1.getAddopt());
				if(logger.isDebugEnabled()) logger.debug("옵션가격:"+totalOptionPrice);
				order1.setAddoptPrice(Integer.toString(totalOptionPrice));
			}
		}
		
		printVO.setRtList(rtList);			
		
//		//주문 및 주문한 회원 
		//PopupOrder2 rtData = popupOrderService.ordersPopupOrder2(ordno);
		PopupOrder2 rtData = this.getOrderMember(ordno);
		printVO.setRtData(rtData);
		
		printVO.setDelivery(this.mapper.selectDeliveryInfo(printVO.getRtList().get(0)));
		
		List<OrderDeliveryVO> deliveryList = printVO.getDelivery();
		int intDelivery = 0;
		int intAddDelivery = 0;
		for (OrderDeliveryVO deliveryVO : deliveryList) {
			//배송비를 보여주는데 선불 조건을 확인할 필요가 없는것 같아서 주석처리 
			//if ("선불".equals(deliveryVO.getPaymentTerms())) {}
				intDelivery = StringUtil.N2I(deliveryVO.getDeliveryPrice());
				intAddDelivery = StringUtil.N2I(deliveryVO.getAddDeliveryPrice());
		}
		
		PopupOrder2 order2 = printVO.getRtData();
		order2.setDelivery(intDelivery);
		order2.setAddDelivery(intAddDelivery+"");
		printVO.setRtData(order2);		
		
		
		
//		// 정상 주문상품 갯수 구하기
		int icnt = this.sellerOrdersPopupOrder5(ordno, sellerCd);  //판매자꺼만 나오게 수정
		printVO.setiCnt(icnt);
//		// 주문단계정보
		LinkedHashMap<String, String> lhm = ShopLibFunction.getArrStep();
		
//		//세금계산서
		List<GdTax> rtTax = popupOrderService.paperTaxState(ordno);
		printVO.setRtTax(rtTax);
		
		int goodsPrice 	= 0;
		int memberDC 	= 0;
		int coupon 		= 0;
		int cntDV 		= 0;
		if(rtList != null && rtList.size() > 0) {
			
			for(PopupOrder1 order1 : rtList) {
				//정상주문 상품 만 계산
				if(ShopLibFunction.getNormalIstep(order1.getIstep())) {			
					goodsPrice += (StringUtil.N2D(order1.getPrice()) * order1.getEa()) + StringUtil.N2D(order1.getAddoptPrice());
					memberDC += StringUtil.N2D(order1.getMemberdc());
					coupon += StringUtil.N2D(order1.getCoupon());										
				}
				
				if(!"".equals(order1.getDvcode())) {
					cntDV++;
				}
				
				order1.setrIstep(ShopLibFunction.r_istep(String.valueOf(order1.getIstep())));
							
				//할인액 계산(회원할인율 + 적용쿠폰)
				printVO.setDiscount(memberDC + coupon);
				//실데이타 계산으로 결제금액 산출
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
		//주문 상태
		//프린트 할 때 select box 를 보여줄필요는 없으니 추후에 그냥 data로 수정 
		//printVO.setStepSelectOption(WebUtil.makeSelectOption(lhm, String.valueOf(rtData.getIstep())));		
		//printVO.setDeliSelectOption(WebUtil.makeSelectOption(tmpDeliMapList, "deliveryno", "deliverycomp", String.valueOf(rtData.getDeliveryno())));
		
		
		List<PopupOrder4> tmpRt = popupOrderService.ordersPopupOrder9(ordno);
		List<PopupOrder4> tmpRt2 = new ArrayList<PopupOrder4>();
		
		for(PopupOrder4 order4 : tmpRt) {
			order4.setOrderItemList(popupOrderService.ordersPopupOrder10(order4.getCancel()));
			order4.setBankPro(StringUtil.getPadString(String.valueOf(order4.getBankcode()), 2, "0"));
			tmpRt2.add(order4);
		}
		printVO.setTmpRt(tmpRt2);
		
		Map<String, String> codeMap = new HashMap<String, String>();
		List<GdCode> codeList = ShopLibFunction.codeitem("cancel");
		for(GdCode code : codeList) {
			codeMap.put(code.getItemcd(), code.getItemnm());
		}
		List<GdOrderCancel> orderCancelList = popupOrderService.ordersPopupOrder11(ordno);
		List<GdOrderCancel> orderCancelList2 = new ArrayList<GdOrderCancel>();
		
		for(GdOrderCancel orderCancel : orderCancelList) {
			orderCancel.setTmpRt12(popupOrderService.ordersPopupOrder12(orderCancel.getSno()));
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
	
	/** 주문번호로 상품조회 */
	public List<PopupOrder1> sellerOrdersPopupOrder1(long ordno, String sellerCd) {
		return this.mapper.sellerOrdersPopupOrder1(ordno,sellerCd);
	}
	
	/** 주문번호로 주문한 상품갯수 조회 */
	public int sellerOrdersPopupOrder5(long ordno, String sellerCd) {
		return this.mapper.sellerOrdersPopupOrder5(ordno, sellerCd);
	}
	
	/**
	 * 주문상세 출력 > reception
	 * */
	public void sellerOrderPrintReception(OrderPrintVO printVO) {
		printVO.setClassids(new String[] {"cssblue" , "cssred"});
		printVO.setHeaduser(new String[] {"공급받는자용", "공급자용"});
		Long ordno = printVO.getOrdno();
		String sellerCd = printVO.getSellerCd();
		List<PopupOrdersRecovery1> orderItemList = this.mapper.sellerPaperReception(ordno,sellerCd);
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
	
	//2020.01.13 이현빈 환불예정금액 계산
	public Map<String,Object> calculateRefundPrice(OrdercancelOrderitemMember repay, Map<String,Object> flagMap){
				
		Map<String,Object> param = new HashMap<String,Object>();
		//상품에 해당하는 판매사코드 
		
		int addopt = ShopLibFunction.getTotalOptionPrice(String.valueOf(repay.getoIaddopt()));
		param.put("sellerCd", repay.getSsSellerCd());
		param.put("ordno", repay.getoIordno());
		
		String ordno = repay.getoIordno();
		
		int refundPrice = 0;
		
		//배송비 중복 적용 방지를 위한 flag
		//배송비 적용이 되면 flag 에 주문번호를 담고 그 번호와 비교해 중복을 비교 
		String sellerCancel = StringUtil.nvl(flagMap.get(ordno),"");
		boolean flag = ordno.equals(sellerCancel) ? false : true;
		int allCancel = popupOrderService.checkAllCancelOfSellerGoods(param); //allCancel 0이면 해당 판매사 상품이 모두 취소됨
		//환불 금액
		
		if(allCancel == 0 && flag){
				//취소된 상품에 배송비를 조회
				param = popupOrderService.getRecoveryDelivery(String.valueOf(repay.getoIsno()));
				repay.setlCdelivery(StringUtil.N2I(param.get("deliveryPrice").toString()));
				repay.setlCaddDelivery(StringUtil.N2I(param.get("addDeliveryPrice").toString()));
				
				flagMap.put(ordno, ordno);
		}else{
			repay.setlCaddDelivery(0);
			repay.setlCdelivery(0);
		}
		//환불 금액
		refundPrice = (repay.getoIprice() * repay.getoIea() + addopt + repay.getlCdelivery() + repay.getlCaddDelivery())
				- (repay.getoImemberdc() + repay.getoIcoupon());
		flagMap.put("refundPrice", refundPrice);
		
		return flagMap;
	}
	
	public void sellerSettlePrice(OrdercancelOrderitemMember repay){
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("ordno", repay.getoIordno());
		param.put("sellercd", repay.getSsSellerCd());
		
		Map<String,Object> deliveryMap = popupOrderService.getDeliveryInfoForInsertCancel(String.valueOf(repay.getoIsno()));
		List<PopupOrder1> itemList = this.getItemList(param);
		int goodsPrice = 0;
		int memberDC = 0;
		int coupon = 0;
		int totalPrice = 0;
		int totalOptionPrice = 0;
		int delivery = 0;
		int addDelivery = 0;
		if(deliveryMap != null){
			delivery = StringUtil.N2I(StringUtil.nvl(deliveryMap.get("deliveryPrice"), "0"));
			addDelivery = StringUtil.N2I(StringUtil.nvl(deliveryMap.get("addDeliveryPrice"), "0"));
		}
		
		for(PopupOrder1 po : itemList){
			totalOptionPrice += ShopLibFunction.getTotalOptionPrice(po.getAddopt());
			goodsPrice = StringUtil.N2I(po.getPrice());
			goodsPrice *= po.getEa();
			memberDC += StringUtil.N2D(po.getMemberdc());
			coupon += StringUtil.N2D(po.getCoupon());
			totalPrice += goodsPrice;
		}
		totalPrice += totalOptionPrice;
		repay.setsSumAddopt(totalOptionPrice);
		repay.setsSumCoupon(coupon);
		repay.setsSumMemberDC(memberDC);
		repay.setsDelivery(delivery);
		repay.setsAddDelivery(addDelivery);
		//seller_price 판매금액
		repay.setsSumGoodsPrice(totalPrice + delivery + addDelivery);
		repay.setsSumSettlePrice(totalPrice + delivery + addDelivery - (memberDC + coupon));
	}
	
	//20200226 이현빈 판매사 주문리스트 판매금액 판매사별로 상품 구매 금액으로 수정 메소드 분할
	public void getSeller_Price(SellerOrderFM fm){
		List<PopupOrder1> rtList = null;
		if (fm.getOrderList().size() > 0) {
			for (OrderMember omlist : fm.getOrderList()) {
				logger.debug("####[OrderList] : " + omlist);
				rtList = omlist.getItemList();
				int goodsPrice = 0;
				int memberDC = 0;
				int coupon = 0;
				int seller_price = 0;
				int totalPrice = 0;
				int intDelivery = 0;
				int intAddDelivery = 0;
				long ordno = omlist.getOrdno();
				String sellerCd = omlist.getSellercd();

				List<OrderDeliveryVO> deliveryInfo = null;
				for (PopupOrder1 sellerList : rtList) {
					logger.debug("####[PopupOrder1] : " + sellerList);
					sellerList.setSellerCd(sellerCd);
					sellerList.setOrdno(ordno);
					deliveryInfo = this.mapper.selectDeliveryInfo(sellerList);
					int totalOptionPrice = ShopLibFunction
							.getTotalOptionPrice(sellerList.getAddopt());
					goodsPrice = StringUtil.N2I(sellerList.getPrice());
					goodsPrice *= sellerList.getEa();
					goodsPrice += totalOptionPrice;
					totalPrice += goodsPrice;
					memberDC += StringUtil.N2D(sellerList.getMemberdc());
					coupon += StringUtil.N2D(sellerList.getCoupon());
				}
				// 2019.12.18 배송비 정보
				if (deliveryInfo != null && !deliveryInfo.isEmpty()) {
					intDelivery = StringUtil.N2I(deliveryInfo.get(0)
							.getDeliveryPrice());
					intAddDelivery = StringUtil.N2I(deliveryInfo.get(0)
							.getAddDeliveryPrice());
					totalPrice += (intDelivery + intAddDelivery);
				}
				// seller_price 판매금액
				seller_price = totalPrice - (memberDC + coupon);
				omlist.setSeller_price(seller_price);
			}
		}

	}
	
	public void sellerPrice_Excel(List<OrderExcel> orderExcel, String sellerCd){
		Map<String,Object> param = new HashMap<String,Object>();
		List<PopupOrder1> rtList = null;
		for(OrderExcel oe : orderExcel){
			long ordno = oe.getOrdno();
			int goodsPrice = 0;
			int memberDC = 0;
			int coupon = 0;
			int seller_price = 0;
			int totalPrice = 0;
			int intDelivery = 0;
			int intAddDelivery = 0;
			param.put("ordno", ordno);
			param.put("sellercd", sellerCd);
			rtList = mapper.getItemList(param);
			List<OrderDeliveryVO> deliveryInfo = null;
			for (PopupOrder1 sellerList : rtList) {
				logger.debug("####[PopupOrder1] : " + sellerList);
				sellerList.setSellerCd(sellerCd);
				sellerList.setOrdno(ordno);
				deliveryInfo = this.mapper.selectDeliveryInfo(sellerList);
				int totalOptionPrice = ShopLibFunction.getTotalOptionPrice(sellerList.getAddopt());
				goodsPrice = StringUtil.N2I(sellerList.getPrice());
				goodsPrice *= sellerList.getEa();
				goodsPrice += totalOptionPrice;
				totalPrice += goodsPrice;
				memberDC += StringUtil.N2D(sellerList.getMemberdc());
				coupon += StringUtil.N2D(sellerList.getCoupon());
			}
			// 2019.12.18 배송비 정보
			if (deliveryInfo != null && !deliveryInfo.isEmpty()) {
				intDelivery = StringUtil.N2I(deliveryInfo.get(0)
						.getDeliveryPrice());
				intAddDelivery = StringUtil.N2I(deliveryInfo.get(0)
						.getAddDeliveryPrice());
				totalPrice += (intDelivery + intAddDelivery);
			}
			// seller_price 판매금액
			seller_price = totalPrice - (memberDC + coupon);
			oe.setSeller_price(seller_price);
			logger.debug("OrderExcel seller_price >> " + oe.getSeller_price());
		}
	}
	public void goodsPrice_Excel(List<OrderExcel> orderExcel, String sellerCd){
		Map<String,Object> param = new HashMap<String,Object>();
		for(OrderExcel oe : orderExcel){
			param.put("sno", oe.getSno());
			GdOrderItem goi = mapper.orderModify1(param);
			int price= goi.getPrice();
			int ea = goi.getEa();
			String addopt = goi.getAddopt();
			int priceSum = price * ea + ShopLibFunction.getTotalOptionPrice(addopt);
			oe.setGoodsprice(priceSum);
		}
	}
		
	
}
