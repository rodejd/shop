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
package com.wepinit.wepinit_shop.xmall.admin.service.seller;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.CodeUtil;
import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xcube.util.WebUtil;
import com.wepinit.wepinit_shop.xmall.admin.controller.order.PopupOrderController;
import com.wepinit.wepinit_shop.xmall.admin.dao.seller.AdminSellerOrderMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.order.OrderDeliveryVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.seller.*;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.session.AdminSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.*;
import com.wepinit.wepinit_shop.xmall.front.service.order.FrontOrderService;
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
public class AdminSellerOrderService {
	private static final Logger logger = LoggerFactory.getLogger(AdminSellerOrderService.class);
	
	@Resource
	AdminSellerOrderMapper mapper;

	@Resource
	FrontOrderService frontOrdService;
	
	// ????????? ?????? ??????
	public void loginAndPermissionCheck(HttpServletRequest req, HttpServletResponse res) throws Exception {
		AdminSessionObject adminSO = AdminSessionObject.getSessionObject(req);
		PrintWriter out = null;
		if( adminSO.isLogin() && null != adminSO.getUserInfo() ){
			Map<String, Object> xkey = adminSO.getUserInfo().getXkey();
			String level = String.valueOf(xkey.get("level"));
			
			if (logger.isDebugEnabled()) {
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>level" + level);
			}
			// ???????????? ????????????
			if( 0 > ShopConfig.getProperty("level"+level).indexOf("order")){
				if("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))){
					throw new BizException("member.00001", CommonConstants.MAIN_ADMIN_URL);	// ???????????? ????????? ????????????.
				}
				
				try{
					res.setCharacterEncoding("UTF-8");
					res.setContentType("text/html;charset=UTF-8");
					out = res.getWriter();
					out.print(WebUtil.getAlertRedirect("???????????? ????????? ????????????.", CommonConstants.MAIN_ADMIN_URL));
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
	 * @param orderFM
	 * @param req
	 * ??????????????? view??? ????????? ?????? ??????
	 * @throws Exception 
	 */
	public void getOrderListInfo(AdminSellerOrderFM orderFM,
			HttpServletRequest req) throws Exception {

		// ????????? ??????
		String orderPeriod = ShopConfig.getProperty("orderPeriod");
		
		if("1".equals(orderFM.getFirst()) && !"".equals( orderPeriod )  ){
			orderFM.setPeriod(orderPeriod);
		}
		
		//???????????? STEP2
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
		
		orderFM.setStep2(tmp_step2);
		
		if(-1 < orderFM.getStep2().indexOf("60")){
			orderFM.setStep2_60("step2_60");
		}
		
		//?????? ???????????? ????????????(?????????, ???????????????, ?????????, ???????????????)
		orderFM.setDtkind(StringUtil.nvl(orderFM.getDtkind() , "orddt"));
		
		//?????? ???????????? ?????????
		if(!"".equals( StringUtil.nvl( orderFM.getPeriod(), "") )){
			//??????????????????
			orderFM.setSregdt_0(DateUtil.getDateFrom("yyyyMMdd","-"+StringUtil.nvl( orderFM.getPeriod(), "0")+"d"));
			orderFM.setSregdt_1(DateUtil.getDateString());
		}else{
			//???????????? ??????
			orderFM.setSregdt_0(orderFM.getSregdt() != null ? orderFM.getSregdt()[0] : "" );
			orderFM.setSregdt_1(orderFM.getSregdt() != null ? orderFM.getSregdt()[1] : "" );
		}
		
		orderFM.setSregdt(new String[]{(String)orderFM.getSregdt_0(), (String)orderFM.getSregdt_1()});
		
		//??????????????? ??????
		orderFM.setRowCount("group".equals(orderFM.getMode()) ? 1 : this.getOrderCount(orderFM));
		
		if("group".equals(orderFM.getMode())){	
			orderFM.setOrderList(mapper.getOrderList2(orderFM));							// ???????????? > ???????????? > ???????????????(?????????????????? ??????)
		}else{
			orderFM.setOrderList(mapper.getOrderList(orderFM)); 							// ???????????? > ???????????? > ???????????????(???????????? ??????)
		}
	}
	
	//??????????????? ??????
	public int getOrderCount(AdminSellerOrderFM orderFM) throws Exception {
		return  mapper.getOrderCount(orderFM);
		
	}

	/**
	 * @param orderExcelVO
	 * @param parameterValues
	 * @return
	 * ?????????or????????? ?????? ????????????
	 */
	public List<OrderMember> getOrderXls(AdminSellerOrderFM orderExcelVO,
			String[] parameterValues,HttpServletRequest req) {

		// ????????? ??????
		String orderPeriod = ShopConfig.getProperty("orderPeriod");
		
		if("1".equals(orderExcelVO.getFirst()) && !"".equals( orderPeriod )  ){
			orderExcelVO.setPeriod(orderPeriod);
		}
		
		//???????????? STEP2
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
		
		//?????? ???????????? ????????????(?????????, ???????????????, ?????????, ???????????????)
		orderExcelVO.setDtkind(StringUtil.nvl(orderExcelVO.getDtkind() , "orddt"));
		
		//?????? ???????????? ?????????
		if(!"".equals( StringUtil.nvl( orderExcelVO.getPeriod(), "") )){
			//??????????????????
			orderExcelVO.setSregdt_0(DateUtil.getDateFrom("yyyyMMdd","-"+StringUtil.nvl( orderExcelVO.getPeriod(), "0")+"d"));
			orderExcelVO.setSregdt_1(DateUtil.getDateString());
		}else{
			//???????????? ??????
			orderExcelVO.setSregdt_0(orderExcelVO.getSregdt() != null ? orderExcelVO.getSregdt()[0] : "" );
			orderExcelVO.setSregdt_1(orderExcelVO.getSregdt() != null ? orderExcelVO.getSregdt()[1] : "" );
		}
		
		orderExcelVO.setSregdt(new String[]{(String)orderExcelVO.getSregdt_0(), (String)orderExcelVO.getSregdt_1()});
		
		if ("order".equals(orderExcelVO.getMode())) {	
			return mapper.getOrderXls(orderExcelVO);		// ????????? ????????????
		} else {
			return mapper.getOrderGoodsXls(orderExcelVO);	// ????????? ????????????
		}
	}

	/**
	 * @param popupVO
	 * ???????????? ?????? ????????? ??????
	 * @throws Exception 
	 */
	public void getPopupOrderInfo(AdminSellerPopupOrderFM popupVO,HttpServletRequest req) throws Exception {

		String[] newordno = null;
		
//		### ?????? ????????????
		Properties bankPro = this.getBankProperties();
		int goodsPrice 	= 0;
		int memberDC 	= 0;
		int coupon 		= 0;
		int icancel 	= 0;
		long ordno 		= popupVO.getOrdno();
		
		//### ??????????????? ?????????
		popupVO.setReferer((!"".equals(popupVO.getReferer())) ? popupVO.getReferer() : req.getHeader("referer"));
		
		//### ???????????? ??????
		popupVO.setTmpBank(mapper.ordersPopupOrder4());
		
		//?????? ??? ????????? ??????
		PopupOrder2 rtData = this.getOrderMember(popupVO.getOrdno());
		popupVO.setRtData(rtData);
		
		// ???????????????
		this.setRtList(popupVO);
		
		// ?????? ???????????? ?????? ?????????
		int icnt = mapper.ordersPopupOrder5(ordno);
		popupVO.setiCnt(icnt);
		
		//???????????????
		popupVO.setRtTax(mapper.paperTaxState(ordno));
		LinkedHashMap<String, String> lhm = ShopLibFunction.getArrStep();
		
		// ??????????????????
		popupVO.setDeliveryBasis(ShopConfig.getProperty("delivery.basis"));
		
		String wskin = ConfigClass.WEB_SKIN;
		int ea = 0;
		if(popupVO.getRtList().size() > 0) {
			for(PopupOrder1 order1 : popupVO.getRtList()) {
				if((icnt == 0) || (order1.getIstep() < 40)) {
					ea = order1.getEa();
					// 2017-09-05 : ??????????????? ????????? ????????? ????????? ???????????? ??????????????? ?????? ??????
					int totalOptionPrice = ShopLibFunction.getTotalOptionPrice(order1.getAddopt());					
					goodsPrice += (StringUtil.N2D(order1.getPrice()) * ea) + totalOptionPrice;
					
					memberDC += StringUtil.N2D(order1.getMemberdc());
					coupon += Integer.parseInt(order1.getCoupon());
				}
				
				String bgColor = "#ffffff";
				if(order1.getIstep() > 40) {
					icancel++;
					bgColor = "#F0F4FF";
				}
				PopupOrderController poc = new PopupOrderController();
				int priceSum = poc.getPriceSum((Integer.parseInt(order1.getPrice()) * ea), order1.getAddopt()); // ???????????? * ?????? + ????????? ??????  = ?????????
				logger.debug("priceSum ################# "+priceSum);
				order1.setPriceSum(priceSum+"");				
				order1.setBgColor(bgColor);
				order1.setSubImgs(order1.getImgs().replace("|", ""));
				order1.setBrandnm(StringUtils.hasText(order1.getBrandnm()) ? order1.getBrandnm() : "??????");
				order1.setrIstep(ShopLibFunction.r_istep(String.valueOf(order1.getIstep())));
				order1.setDvno(!"".equals(order1.getDvcode()) || !"0".equals(order1.getDvcode()) && false ? "-" : String.valueOf(order1.getDvno()));
				
				//??????????????? ????????? ????????????
				if("????????????".equals(order1.getrIstep())) {
					ShopLibFunction.insertGoodsOrderCoupon(String.valueOf(rtData.getMno()));
				}
			}
			popupVO.setGoodsPrice(goodsPrice);
			popupVO.setMemberDc(memberDC);
			popupVO.setCoupon(coupon);
			popupVO.setiCancel(icancel);
			popupVO.setWskin(wskin);

			//????????? ??????(????????????+??????+?????????)
			popupVO.setDiscount(memberDC+rtData.getCoupon()+rtData.getEmoney());
			//???????????? ???????????? ???????????? ??????
			popupVO.setSettleprice(goodsPrice + rtData.getDelivery() - popupVO.getDiscount() + rtData.getEggfee());
		}
		
		popupVO.setSelectOption(WebUtil.makeSelectOption(lhm, String.valueOf(rtData.getStep())));
		popupVO.setStepMsg(ShopLibFunction.getStepMsg(
				String.valueOf(rtData.getStep()), 
				String.valueOf(rtData.getStep2()), 
				String.valueOf(rtData.getOrdno()), 
				""));
		
		
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
		
		// 2017-09-05 : cancel ????????? ????????? ???????????? ?????? ???????????? ???. ????????? ????????? ??????
		//popupVO.setTmpRt10(this.service.ordersPopupOrder10(popupVO.getCancel()));
		for(PopupOrder4 order4 : popupVO.getTmpRt9()) {
			order4.setBankPro(bankPro.getProperty(StringUtil.getPadString(String.valueOf(order4.getBankcode()), 2, "0")));
		}
		
		// ???????????? ??????
		this.setOrderCancel(popupVO, bankPro);
		
	}
	
	/** ????????? ?????? ?????? 
	 * @param
	 * */
	private void setDeliveryInfo(AdminSellerPopupOrderFM popupVO) {
		popupVO.setDelivery(this.mapper.selectDeliveryInfo(popupVO.getRtList().get(0)));
		
		List<OrderDeliveryVO> deliveryList = popupVO.getDelivery();
		int totalDelivery = 0;
		for (OrderDeliveryVO deliveryVO : deliveryList) {
			if ("??????".equals(deliveryVO.getPaymentTerms())) {
				totalDelivery += Integer.parseInt(deliveryVO.getDeliveryPrice());
			}
		}
		
		PopupOrder2 order2 = popupVO.getRtData();
		order2.setDelivery(totalDelivery);
		popupVO.setRtData(order2);
	}
	
	/**
	 * @return
	 * @throws Exception
	 * ?????? ????????????
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
	 * ????????? ???????????? ???????????? 11?????? ????????? ??????????????? ???????????? ?????? ????????? ??????
	 */
	public PopupOrder2 getOrderMember(long ordno) {
		
		PopupOrder2 rtData = mapper.ordersPopupOrder2(ordno);
		rtData.setMobileorder(this.formatCheckPhoneNumber(rtData.getMobileorder()));
		rtData.setPhoneorder(this.formatCheckPhoneNumber(rtData.getPhoneorder()));
		return rtData;
	}
	
	/**
	 * @param phoneNumber
	 * @return
	 * ???????????? ????????? ???????????? ?????? ????????? ??????????????? ?????? ????????? ??????
	 */
	public String formatCheckPhoneNumber(String phoneNumber) {
		return phoneNumber.length() < 11 ? "" : phoneNumber;
	}
	
	/**
	 * @param popupVO
	 * @param bankPro
	 * @throws Exception
	 * ??????????????????
	 */
	public void setOrderCancel(AdminSellerPopupOrderFM popupVO, Properties bankPro) throws Exception {
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
	 * ????????? > ???????????? > ?????????????????????
	 */
	public List<OrderCancel> getOrderCancelList(AdminSellerOrderCSFM csVO) {
		String skey = csVO.getSkey();
		String sword = csVO.getSword();
		String settlekind = StringUtil.nullConv(csVO.getSettlekind(),"");
		String[] regdtArray	= new String[]{StringUtil.nvl(csVO.getRegdt1(), ""), StringUtil.nvl(csVO.getRegdt2(), "")};
		String[] typeArr = csVO.getType();
		String sqlSword = "";
		
		// ***dynamic query***
		// ???????????? > ?????????(????????????[??????])
		if (StringUtils.hasText(sword)) {
			String tmpSword = "";
			tmpSword = ("all".equals(skey)) ? "concat( a.ordno, ifnull(d.m_id,''), nameOrder, bankSender )" : skey ; 
			sqlSword = tmpSword+ " like '%" + csVO.getSword() + "%'";
		}
		
		String tmp_type = "";
		
		// ?????????????????? > ???????????? ????????? ?????? [????????????-type]
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
		// ???????????? > ????????? (????????????)
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

	/**
	 * @param regoodsVO
	 * @return
	 * ?????? ??????/?????????????????????
	 */
	public List<Map<String, Object>> getOrderRegoodsList(
			AdminOrderOrderRegoodsFM vo) {
		vo.setRowCount(mapper.getOrderRegoodsListCount());
		
		List<Map<String, Object>> resultList = null;
		
		resultList = mapper.getOrderRegoodsList(vo);
		for(int i=0; i<resultList.size(); i++){
			resultList.get(i).put("infoList", mapper.getOrderRegoodsInfoList(Integer.parseInt(String.valueOf(resultList.get(i).get("sno")))));
		}
		
		return resultList;
	}

	public int getRepayCount() {
		return mapper.getRepayCount();
	}

	public List<OrdercancelOrderitemMember> getRepayList(
			AdminSellerRepayFM repayVO) {
		return mapper.getRepayList(repayVO);
	}

	public int getRepayList2(OrdercancelOrderitemMember oom) {
		return mapper.getRepayList2(oom);
	}

	public OrderitemOrdercancel getRepayList3(String oCordno) {
		return mapper.getRepayList3(oCordno);
	}

	public void changeOrderStep(AdminSellerOrderListStepChangeVO orderListStepChangeVO) throws Exception{
		logger.debug("#########################orderListStepChangeVO.getOrdno()==="+orderListStepChangeVO.getOrdno());
		// ????????? ???????????? (ordno) ??????
		String [] ordnos = orderListStepChangeVO.getOrdno().split(",");
		int ordnoCount = ordnos.length;
		
		// ???????????? ??????????????? ????????????.
		for(int i = 0; i < ordnoCount; i++) {
			ShopLibFunction.ctlStep(ordnos[i], orderListStepChangeVO.getChangeStep());
		}
		
	}
	
	/**
	 * ????????????
	 * @param repayVO
	 * @param req
	 * @throws Exception
	 */
	public void repaydb(AdminOrderRepayFM repayVO,HttpServletRequest req) throws Exception {
		String[] arrSNO = req.getParameterValues("chks");
		String[] rprice=req.getParameterValues("repays");
		String[] rfee = req.getParameterValues("repayfees");
		String[] remoney = req.getParameterValues("remoneys");
		String[] m_no = req.getParameterValues("m_nos");
		String[] ordno = req.getParameterValues("ordnos");
		String[] bankcode = req.getParameterValues("bankcodes");
		String[] bankaccount = req.getParameterValues("bankaccounts");
		String[] bankuser = req.getParameterValues("bankusers");
		String[] sno = req.getParameterValues("snos");
		List<HashMap> rt_couponYn = new ArrayList();
		int j;
		if ( null != arrSNO && 0 < arrSNO.length ) {
			for(int i=0;i<arrSNO.length;i++){
				j=Integer.parseInt(arrSNO[i]);		
				String ono=null;
				
				repayVO.setRprice(rprice[j]);
				repayVO.setRfee(rfee[j]);
				repayVO.setRemoney(remoney[j]);
				repayVO.setM_no(m_no[j]);
				repayVO.setOrdno(ordno[j]);
				repayVO.setBankcode(bankcode[j]);
				repayVO.setBankaccount(bankaccount[j]);
				repayVO.setBankuser(bankuser[j]);
				repayVO.setSno(sno[j]);
				
				updateOrderRepay1(repayVO);
				updateOrderRepay2(repayVO);
				ono = updateOrderRepay3(repayVO);
				repayVO.setOrdno(ono);
				updateOrderRepay4(repayVO);
			
				ShopLibFunction.setStock(Long.parseLong(ono));
				rt_couponYn = updateOrderRepay5(repayVO);
				if(rt_couponYn.size()>0){
					if(!"0".equals(rt_couponYn.get(0).get("coupon"))){
						couponYnStatus(repayVO);
					}	
				}
				
				
				
				if(m_no[i]!=null&&m_no[i]!="" && remoney[i]!=null&&remoney[i]!=""){
					ShopLibFunction.setEmoney(Integer.parseInt(remoney[i]),Integer.parseInt(m_no[i]),Long.parseLong(ordno[i]),"?????? ????????? ?????? ??????????????? ??????");
				}
				if ("0".equals(ShopConfig.getProperty("emoney_limit")) || "0".equals(remoney[i])){
					HashMap hmp = libfuncCtlStep1(repayVO);
					if(hmp.size()>0){
						String mNo = hmp.get("m_no")+"";
						String emoney = hmp.get("reserve")+"";
						String msg = "????????? ?????? ????????? ??????";
						ShopLibFunction.setEmoney(Integer.parseInt(emoney),Integer.parseInt(mNo),Long.parseLong(ono),msg);
					}
				}
			}
		}
	}
	
	public void updateOrderRepay1(AdminOrderRepayFM repayVO) {
		mapper.updateOrderRepay1(repayVO);
	}

	public void updateOrderRepay2(AdminOrderRepayFM repayVO) {
		mapper.updateOrderRepay2(repayVO);
	}

	public String updateOrderRepay3(AdminOrderRepayFM repayVO) {
		// TODO Auto-generated method stub
		return mapper.updateOrderRepay3(repayVO);
	}

	public void updateOrderRepay4(AdminOrderRepayFM repayVO) {
		mapper.updateOrderRepay4(repayVO);
	}

	public List<HashMap> updateOrderRepay5(AdminOrderRepayFM repayVO) {
		return mapper.updateOrderRepay5(repayVO);
	}

	public void couponYnStatus(AdminOrderRepayFM repayVO) {
		mapper.couponYnStatus(repayVO);
	}

	public HashMap libfuncCtlStep1(AdminOrderRepayFM repayVO) {
		return mapper.libfuncCtlStep1(repayVO);
	}

	public void orderCancel(AdminSellerOrderCancelFM cancelVO) throws Exception{
		// ??????/?????? ??????
		this.excuteOrderCancel(cancelVO);	// as-is : chkCancel
		// ?????? ??????
		ShopLibFunction.setStock(Long.parseLong(cancelVO.getOrdno()));
		// ???????????? ??????
		this.excuteSetPrnSettleprice(cancelVO);	// as-is : set_prn_settleprice
		
	}
	
	public void excuteOrderCancel(AdminSellerOrderCancelFM cancelVO) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("PopupOrderService >> excuteOrderCancel");
		}
		this.setParamAndInsertOrderCancel(cancelVO);
		this.insertOrUpdateLogCancelAndOrderCancel(cancelVO);
		GdOrder order = mapper.orderChkCancel8(cancelVO.getOrdno());	// SELECT gd_order
		
		Map<String, Object> cntMap = mapper.orderChkCancel9(cancelVO.getOrdno());	// SELECT gd_order_item || as-is 8,9,10 ?????? ?????? ??????
		int cnt =  Integer.valueOf(String.valueOf(cntMap.get("cnt")));	// ordno ??? ????????? ??????
		int ministep =  Integer.valueOf(String.valueOf(cntMap.get("ministep")));// ordno ??? ????????? MIN(istep)
		int istepCnt =  Integer.valueOf(String.valueOf(cntMap.get("icnt")));	// istep > 40 ??? ?????????
		
		// ordno ??? ???????????? ???????????? ??? ??? ?????? ??? ???????????? ????????? ????????????, ????????????, ???????????? ????????? ??????.
		if (cnt == istepCnt) {
			order.setStep2(ministep);
			this.mapper.orderChkCancel11(order);	// UPDATE gd_order
			
			if (this.isEmoneyRefundable(order)) {
				this.refundEmoney(order, "??????????????? ?????? ?????? ????????? ??????");
			}
			
			// ?????? ??????????????? ?????? ?????? ?????? ??????
			int couponCnt = mapper.orderChkCancel12(String.valueOf(order.getOrdno()));	// SELECT gd_coupon_order
			if (couponCnt > 0) {
				this.restoreCouponHistory(order.getOrdno());
			}
			
			// ????????? ????????? ???????????? ???????????? ???????????? ????????? ??????
			if ( order.getStep() > 3 && order.getMno() > 0 ) {
				// ???????????? ??????????????? ??????
				int reserve = mapper.orderChkCancel15(cancelVO.getOrdno(), cancelVO.getSno());	// SELECT gd_order_item
				
				order.setEmoney(reserve * (-1));
				this.refundEmoney(order, "??????????????? ?????? ??????????????? ??????");
			}
		}
	}
	
	/**
	 * ???????????? ??????
	 * @param cancelVO
	 */
	private void setParamAndInsertOrderCancel(AdminSellerOrderCancelFM cancelVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("PopupOrderService >> setParamAndInsertOrderCancel");
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ordno", cancelVO.getOrdno());	
		param.put("name", cancelVO.getpName());	
		param.put("code", cancelVO.getCode());	
		param.put("memo", cancelVO.getMemo());	
		param.put("bankcode", cancelVO.getBankcode());	
		param.put("bankaccount", cancelVO.getBankaccount());	
		param.put("bankuser", cancelVO.getBankuser());	

		this.mapper.orderChkCancel1(param);	// INSERT gd_order_cancel
	}
	
	private void insertOrUpdateLogCancelAndOrderCancel(AdminSellerOrderCancelFM cancelVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("PopupOrderService >> insertOrUpdateLogCancelAndOrderCancel");
		}
		
		String[] arr_sno = cancelVO.getSno();
		String[] arr_ea = cancelVO.getEa();
		String no_cancel = String.valueOf(this.mapper.orderChkCancel2());	// SELECT gd_order_cancel
		for (int i = 0; i < arr_sno.length; i++) {
			GdOrderItem orderItem = this.mapper.orderChkCancel3(arr_sno[i]);	// SELECT gd_order_item
			
			String istep = "0";
			int ea_3 = 0;
			if (orderItem != null) {
				istep = ("n".equals(orderItem.getCyn()) && "n".equals(orderItem.getDyn()) ? "44" : "41");
				ea_3 = orderItem.getEa();
				
				GdLogCancel params = new GdLogCancel();
				params.setOrdno(Long.valueOf(cancelVO.getOrdno()));
				params.setItemno(Integer.parseInt(arr_sno[i]));
				params.setCancel(Integer.parseInt(no_cancel));
				params.setPrev(orderItem.getIstep());
				params.setNext(Integer.parseInt(istep));
				params.setGoodsnm(orderItem.getGoodsnm());
				params.setEa(orderItem.getEa());
				
				// ???????????? ??????
				mapper.orderChkCancel4(params);	// INSERT gd_log_cancel
				
				int gap = ea_3 - (Integer.parseInt(arr_ea[i]));
				if (gap > 0) {
					// ??????????????? ??????????????? ???????????? ?????? ????????? ??????
					this.saparateOrder(params, gap);
				} else {
					GdOrderItem gdOrderItemParam = new GdOrderItem();
					gdOrderItemParam.setSno(Integer.parseInt(arr_sno[i]));
					gdOrderItemParam.setIstep(Integer.parseInt(istep));
					this.mapper.orderChkCancel7(gdOrderItemParam);	// UPDATE gd_order_item
				}
			}
		}
	}
	
	/** ???????????? ?????? */
	public void excuteSetPrnSettleprice(AdminSellerOrderCancelFM cancelVO) throws Exception {

		/** ???????????? ?????? **/
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

		if (tcnt == icnt) { //?????? ??????????????? ??????
			csettleprice = settleprice1 - rt2_enuri + rt2_delivery - rt2_emoney + rt2_eggFee - rt2_coupon;
		} else {
			csettleprice = settleprice2 - rt2_enuri + rt2_delivery - rt2_emoney + rt2_eggFee - rt2_coupon;
		}
		
		param.put("csettleprice", csettleprice);
		mapper.orderSetPrnSettleprice3(param);	// UPDATE gd_order
		
		int rt2_member_sum = 0;
		int rt2_member_cnt = 0;
		if (rt2_m_no > 0) { //??????????????? ?????? ?????? ?????? ????????????
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
	
	/**??????????????? ??????????????? ???????????? ?????? ???????????? ???????????????.*/
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

	public void excuteDeleteOrder(AdminSellerOrderCancelFM cancelVO) {
		/** ???????????? ?????? **/
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ordno", cancelVO.getOrdno());
		
		mapper.orderDeleteInsert(param);	// INSERT gd_order_del
		mapper.orderDelete(param);			// DELETE gd_order
		
	}
	
	/**
	 * ?????? ??????
	 */
	public void excuteOrderModify(AdminSellerOrderCancelFM cancelVO) throws Exception {
		/** ???????????? ?????? **/
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("step", cancelVO.getStep());	
		param.put("step2", cancelVO.getStep2());	
		param.put("ordno", cancelVO.getOrdno());	
		
		String[] arr_sno	= cancelVO.getSno();
		String[] arr_ea		= cancelVO.getEa();
		String[] arr_price	= cancelVO.getPrice();
		String[] arr_supply	= cancelVO.getSupply();
		String[] arr_dvno	= cancelVO.getDvno();
		String[] arr_dvcode	= cancelVO.getDvcode();
		int gcoupon = 0;
		int gcoupon_emoney = 0;
		
		// ??????????????? ?????? ??????
		if (arr_sno != null && arr_sno.length > 0) {
			for ( int i = 0 ; i < arr_sno.length ; i++ ) {
				// ???????????? ?????? ????????? ?????? ??????
				param.put("sno", Integer.parseInt(arr_sno[i]));	
				GdOrderItem orderItem = mapper.orderModify1(param);	// SELECT gd_order_item
				if (logger.isDebugEnabled()) logger.debug("#####[CAR] orderItem : " + orderItem.toString());
				
				if ("0".equals(cancelVO.getStep()) 
						&& ( !arr_ea[i].equals(String.valueOf(orderItem.getEa())) 
						  || !arr_price[i].equals(String.valueOf(orderItem.getPrice()))
						  || !arr_supply[i].equals(String.valueOf(orderItem.getSupply())) 
						  && !"0".equals(cancelVO.getStep2()) ) ) {
					if (logger.isDebugEnabled()) logger.debug("#####[CAR] ????????????????????? ???????????????????????? ?????? ");
					// ?????? ?????? ??????
					if (!arr_ea[i].equals(String.valueOf(orderItem.getEa()))) {
						int imode = ("n".equals(orderItem.getStockyn()) ? 1 : -1);
						param.put("goodsno", orderItem.getGoodsno());
						param.put("opt1", orderItem.getOpt1());
						param.put("opt2", orderItem.getOpt2());

						int cstock = mapper.orderModify2(param);	// SELECT gd_goods_option
						cstock = cstock +( imode * ( Integer.parseInt(arr_ea[i]) - orderItem.getEa() ) );
						
						if (cstock < 0 ) {
							cstock = 0;
						}
						param.put("cstock", cstock);
						mapper.orderModify3(param);	// UPDATE gd_goods_option
					}
					int gap = Integer.parseInt(arr_ea[i]) - orderItem.getEa();

					if ( gap != 0 ) {
						if ( orderItem.getCoupon() > 0 ) {
							gcoupon = orderItem.getCoupon() * gap;	// ????????????
						}
						if( orderItem.getCouponEmoney() > 0 ) {
							gcoupon_emoney = orderItem.getCouponEmoney() * gap;	// ???????????????
						}
					}
					param.put("ea", 	Integer.parseInt(arr_ea[i]));
					param.put("price",	Integer.parseInt(arr_price[i]));
					param.put("supply", Integer.parseInt(arr_supply[i]));
					param.put("dvno",	Integer.parseInt(arr_dvno[i]));
					param.put("dvcode", arr_dvcode[i]);
					mapper.orderModify4(param);	// UPDATE gd_order_item
				}
			}
		}
		String deliverycode = cancelVO.getDeliverycode().replaceAll("-","");
		param.put("enuri", 		Integer.parseInt(cancelVO.getEnuri().equals("") ? "0" : cancelVO.getEnuri()));
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
		param.put("coupon", 		gcoupon);
		param.put("couponemoney", 	gcoupon_emoney);
		mapper.orderModify5(param);	// UPDATE gd_order
		
		this.updateDeliveries(cancelVO);
		
	}
	
	/** ???????????? ??? ????????? ?????? ???????????? ???????????? ???????????? */
	private void updateDeliveries(AdminSellerOrderCancelFM vo) {
		String [] invoices = vo.getDeliverycode().split(",");	// ???????????? invoice
		String [] deliveryCompCds = vo.getDeliveryno().split(",");		// ????????? deliveryCompCd
		String [] goodsnos = vo.getGoodsno();		// ???????????? goodsno
		String ordno = vo.getOrdno();		// ???????????? ordno 
		logger.debug("eunjung >> " + invoices.length);
		
		for (int i = 0; i < invoices.length; i++) {
			OrderDeliveryVO delivery = new OrderDeliveryVO();
			delivery.setInvoice(isNoData(invoices[i]) ? "" : invoices[i]);
			delivery.setDeliveryCompCd(isNoData(deliveryCompCds[i]) ? "" : deliveryCompCds[i]);
			delivery.setGoodsno(goodsnos[i]);
			delivery.setOrdno(ordno);
			this.mapper.updateDeliveryInfo(delivery);
		}
		
	}
	
	private boolean isNoData(String data) {
		return "0".equals(data) || "".equals(data) || data == null;
	}

	public void updateOrderRegoods(AdminOrderRegoodsFM vo) throws Exception{
		mapper.updateOrderRegoodsItem(vo.getSno());
		vo.setOrdno(mapper.getOrderRegoodsCancel(vo.getSno()));
		mapper.updateOrderRegoods(vo.getOrdno());
		//?????? ??????
		ShopLibFunction.setStock(vo.getOrdno());

		Map<String, Object> orderObj = null;
		orderObj = mapper.getOrderInfo(vo.getOrdno());
		
		//????????? ?????? ????????? ??????
		if(orderObj != null){
			ShopLibFunction.setEmoney(-Integer.parseInt(String.valueOf(orderObj.get("reserve"))), 
					Integer.parseInt(String.valueOf(orderObj.get("m_no"))), vo.getOrdno(), "????????? ?????? ????????? ??????");
		}
		
	}

	/**
	 * ??????????????? ??????
	 * @param regoodsVO
	 */
	public void updateOrderExchange(AdminOrderRegoodsFM vo) throws Exception{
		mapper.updateOrderItemExchange(vo.getSno());
		vo.setOrdno(mapper.getOrderExchangeCancel(vo.getSno()));
		mapper.updateOrderExchange(vo.getOrdno());
		//?????? ??????
		ShopLibFunction.reorder(vo.getOrdno(), vo.getSno());
		
	}
	
	public void setRtList(AdminSellerPopupOrderFM popupVO) {
		List<PopupOrder1> rtList = mapper.ordersPopupOrder1(popupVO.getOrdno());
		
		
		popupVO.setRtList(rtList);
		
		// ?????????
		this.setDeliveryInfo(popupVO);
		
		List<OrderDeliveryVO> deliveryList = popupVO.getDelivery();

		
		// goodsno ?????? ?????????
		String[] goodsnos = new String[rtList.size()];
		for (int i = 0; i < goodsnos.length; i++) {
			goodsnos[i] = String.valueOf(rtList.get(i).getGoodsno());
		}
		/* 
		String addressSido = popupVO.getRtData().getAddress().substring(0, 2);
		
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
			order1.setAddDeliveryPrice(deliveryVO.getAddDeliveryPrice());
			order1.setDeliveryPrice(deliveryVO.getDeliveryPrice());
			order1.setPaymentTerms(deliveryVO.getPaymentTerms());
		}
		
		popupVO.setRtList(rtList);
	}
	
}
