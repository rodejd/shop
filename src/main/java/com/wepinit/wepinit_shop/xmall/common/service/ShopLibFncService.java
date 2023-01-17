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
package com.wepinit.wepinit_shop.xmall.common.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.vo.event.CouponVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.GoodsVO;
import com.wepinit.wepinit_shop.xmall.common.dao.ShopLibFncMapper;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.CodeSmssample;
import com.wepinit.wepinit_shop.xmall.common.vo.join.ShopLibSetStock1;
import com.wepinit.wepinit_shop.xmall.front.service.order.FrontOrderService;
import com.wepinit.wepinit_shop.xmall.seller.basic.vo.SellerDeliveryOverPolicyVO;
import com.wepinit.wepinit_shop.xmall.seller.basic.vo.SellerDeliveryPolicyInfoVO;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ShopLibFncService {
	
	//AccessToken
	private final String authorization = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJxRlItM0w0SGo2RzNWbmNEYmo0alp6YmNhV2lMNmRtNmlYYUNtck9IQ2RRIn0.eyJleHAiOjE5NTAwNTM5MTMsImlhdCI6MTYzNDY5NDQ0MywiYXV0aF90aW1lIjoxNjM0Njk0NDQzLCJqdGkiOiI4M2IwNDJhMi04MDQzLTQ1YWQtYjkzNy0wYWZhNjVjZjU3NDUiLCJpc3MiOiJodHRwczovL3Nzby5heGVwdGEuaXQvYXV0aC9yZWFsbXMvTWVyY2hhbnQiLCJhdWQiOlsicGctcGF5bWVudC1hcGktaW5ldCIsInBnLXBheW1lbnQtYXBpLWluZXQtc2FuZGJveCIsImFjY291bnQiXSwic3ViIjoiY2E3NjNiZTktODJhMS00MWE5LWIxZmUtNDY5ODM5MjRjYjRjIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoicGctcGF5bWVudC1hcGktaW5ldCIsInNlc3Npb25fc3RhdGUiOiI1ZTAwM2RjYi1lMDVlLTQ5OGMtODA4MC05ZWNlYWM3MWE4YjgiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vcGF5LmF4ZXB0YS5pdCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7InBnLXBheW1lbnQtYXBpLWluZXQiOnsicm9sZXMiOlsidXNlciJdfSwicGctcGF5bWVudC1hcGktaW5ldC1zYW5kYm94Ijp7InJvbGVzIjpbInVzZXIiXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIGVtYWlsIG9mZmxpbmVfYWNjZXNzIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJzYW5nIGhvb24gc2hpbiIsInByZWZlcnJlZF91c2VybmFtZSI6ImJobG9mYW1sZWxlY0BnbWFpbC5jb20iLCJnaXZlbl9uYW1lIjoic2FuZyBob29uIiwiZmFtaWx5X25hbWUiOiJzaGluIiwiZW1haWwiOiJiaGxvZmFtbGVsZWNAZ21haWwuY29tIn0.dx0u5e-8gUXzCNuKRY3s9-GweftAfjg1QCLWn-Z3-tjxx4u1cifxXlKsxkS1DI56CVEbqGLAYGG2d7n5fvs17qBIslZB_YM0OgQuw3eUu3z6862HHLm6owqyUXTmUgm0Z080IJQUPOQebwJEVG3hIUtcrcm_pGua9car2ooRBUyPqOi76D0VH5ip8qwHO392GwUQUUTvvkMkMezNgtujIwQftWGjeMjm0_LOGuhLIYCkYJ_VkEMTDAkiS__rGGjL8oYN7qsdAZlWzrGUiymw1ayaVd_r0fDfjrfjEe_onBpdT6VVf01LPtpahlmfoY2om2K5DCClZqvFP4jO-DoKYA";
	//API License Key
	private final String licenseKey = "652G4DC-GQD485R-JRT67M0-H3DVXMT";
	//API URL
	private final String apiServerUrl = "https://pay.axepta.it";

	@Resource
	FrontOrderService service;
	
	@Resource
	ShopLibFncMapper mapper;
	
	public Map<String, String> getQnaSearch(String sch_loadfrom, String table_name) throws Exception {
		return mapper.getQnaSearch(sch_loadfrom, table_name);
		
	}
	
	public List<GdCode> getCodeItem(String groupcd) throws Exception {
		return mapper.getCodeItem(groupcd);
	}
	
	public List<GdCode> getCodeItem2() throws Exception {
		return mapper.getCodeItem2();
	}
	
	public List<CodeSmssample> getCodeItem3() throws Exception {
		return mapper.getCodeItem3();
	}
	
	public Map<String, Object> getGoodsCategoryHiddenState(String category) throws Exception {
		return mapper.getGoodsCategoryHiddenState(category);
	}
	
	public GoodsVO getGoodsInfo(String goodsno) {
		return mapper.getGoodsInfo(goodsno);
	}
	
	public void insertGoodsInfoCopy(GoodsVO vo){
		mapper.insertGoodsInfoCopy(vo);
	}

	public int getGoodsNoMax() {
		return mapper.getGoodsNoMax();
	}
	
	public int getBirthCongratsCnt(Map<String,Object> map) {
		return mapper.getBirthCongratsCnt(map);
	}
	
	public void insertGoodsAddCopy(Map<String, Object> map) {
		mapper.insertGoodsAddCopy(map);
	}
	
	public void insertGoodsOptionCopy(Map<String, Object> map) {
		mapper.insertGoodsOptionCopy(map);
	}
	
	public void insertGoodsLinkCopy(Map<String, Object> map) {
		mapper.insertGoodsLinkCopy(map);
	}
	
	/*public void insertGoodsTipCopy(Map<String, Object> map) {
		mapper.insertGoodsTipCopy(map);
	}*/
	
	/*public void insertGoodsRecipeCopy(Map<String, Object> map) {
		mapper.insertGoodsRecipeCopy(map);
	}*/
	
	public GoodsVO getGoodsInfoAll(String goodsno) {
		return mapper.getGoodsInfoAll(goodsno);
	}
	
//	public void deleteGoodsDisplayAll(String goodsno) {
//		mapper.deleteGoodsDisplayAll(goodsno);
//	}
//	
//	public void deleteGoodsAdd(String goodsno) {
//		mapper.deleteGoodsAdd(goodsno);
//	}
//	
//	public void deleteGoodsOptionAll(String goodsno) {
//		mapper.deleteGoodsOptionAll(goodsno);
//	}
//	
//	public void deleteGoodsdLinkAll(String goodsno) {
//		mapper.deleteGoodsdLinkAll(goodsno);
//	}
//	
//	public void deleteGoodsInfo(String goodsno) {
//		mapper.deleteGoodsInfo(goodsno);
//	}
//	
//	public void deleteMemberWishListGoods(String goodsno) {
//		mapper.deleteMemberWishListGoods(goodsno);
//	}
	
	public List<Map<String, Object>> getGoodsCategoryList(Map<String, Object> map) {
		return mapper.getGoodsCategoryList(map);
	}
	
	public String getCodeName(String grpNm, String itemCd) {
		return (String)mapper.getCodeName(grpNm, itemCd).get("itemnm");
	}
	
	/*public String getConfValue(String confCd, String dimSet, String dim1Var) {
		return (String)mapper.getConfValue(confCd, dimSet, dim1Var).get("dim_val");
	}*/
	
	/*public void setConfValue(String confCd, String dimSet, String dim1Var, String dimVal) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>dim1Var" + dim1Var);
		mapper.setConfValue(confCd, dimSet, dim1Var, dimVal);
	}*/
	
	public List<GdMemberGrp> getMemberGrp() throws Exception {
		return mapper.getMemberGrp();
	}
	
	public List<Map<String,Object>> getConfValueArry(String confCd, String dimSet, String dim1Var) {
		return mapper.getConfValueArry(confCd, dimSet, dim1Var);
	}
	
	public List<String> getStepMsg(GdOrderItem orderItem) {
		return mapper.getStepMsg(orderItem);
	}
	
	public void setEmoney1(Map<String,Object> paramMap) {
		mapper.setEmoney1(paramMap);
	}
	
	public void setEmoney2(Map<String,Object> paramMap) {
		mapper.setEmoney2(paramMap);
	}
	
	public List<ShopLibSetStock1> setStock1(long ordno) {
		return mapper.setStock1(ordno);
	}

	public GdGoodsOption setStock2(Map<String,Object> paramMap) {
		return mapper.setStock2(paramMap);
	}
	
	public void setStock3(Map<String,Object> paramMap) {
		mapper.setStock3(paramMap);
	}
	
	public void setStock4(Map<String,Object> paramMap) {
		mapper.setStock4(paramMap);
	}
	
	public List<GdOrderItem> setPrnSettleprice1(long ordno) {
		return mapper.setPrnSettleprice1(ordno);
	}
	
	public GdOrder setPrnSettleprice2(long ordno) {
		return mapper.setPrnSettleprice2(ordno);
	}
	
	public void setPrnSettleprice3(Map<String,Object> paramMap) {
		mapper.setPrnSettleprice3(paramMap);
	}
	
	public PrnSettlePrice4VO setPrnSettleprice4(int mno) {
		return mapper.setPrnSettleprice4(mno);
	}
	
	public void setPrnSettleprice5(Map<String,Object> paramMap) {
		mapper.setPrnSettleprice5(paramMap);
	}
	
	/*
	 * reorder
	 */
	public Map<String, Object> getOrderInfo(long ordno){
		return mapper.getOrderInfo(ordno);
	}
	public int getOrderItemCount(long ordno){
		return mapper.getOrderItemCount(ordno);
	}
	public List<Map<String, Object>> getOrderItemList(int cancel){
		return mapper.getOrderItemList(cancel);
	}
	public List<Map<String, Object>> getOrderItemDeliveryList(long ordno){
		return mapper.getOrderItemDeliveryList(ordno);
	}	
	public void insertReorderItem(Map<String, Object> param){
		mapper.insertReorderItem(param);
	}
	public void insertReorderInfo(Map<String, Object> param){
		mapper.insertReorderInfo(param);
	}
	public void insertReorderDelivery(Map<String, Object> param){
		mapper.insertReorderDelivery(param);
	}	
	/*
	 * 20191128 주문스텝 상세처리
	 */
	public String[] detailGoods(Map<String,Object> paramMap) throws Exception{
		return mapper.detailGoods(paramMap);
	}
	
	
	/**
	 * 주문 스텝 처리
	 */
	public GdOrder ctlStep1(Map<String,Object> paramMap) throws Exception {
		return mapper.ctlStep1(paramMap);
	}
	public int ctlStep2(Map<String,Object> paramMap) throws Exception {
		return mapper.ctlStep2(paramMap);
	}
	public void ctlStep3(Map<String,Object> paramMap) throws Exception {
		mapper.ctlStep3(paramMap);
	}
	public void ctlStep4(Map<String,Object> paramMap) throws Exception {
		mapper.ctlStep4(paramMap);
	}
	public void ctlStep5(Map<String,Object> paramMap) throws Exception {
		mapper.ctlStep5(paramMap);
	}
	public void ctlStep6(Map<String,Object> paramMap) throws Exception {
			mapper.ctlStep6(paramMap);
	}
	public void ctlStep7(Map<String,Object> paramMap) throws Exception {
		mapper.ctlStep7(paramMap);
	}
	public void ctlStep8(Map<String,Object> paramMap) throws Exception {
			mapper.ctlStep8(paramMap);
	}
	public void ctlStep9(Map<String,Object> paramMap) throws Exception {
		mapper.ctlStep9(paramMap);
	}
	public void ctlStep10(Map<String,Object> paramMap) throws Exception {
			mapper.ctlStep10(paramMap);
	}
	public void ctlStep11(Map<String,Object> paramMap) throws Exception {
		mapper.ctlStep11(paramMap);
	}
	public void ctlStep12(Map<String,Object> paramMap) throws Exception {
		mapper.ctlStep12(paramMap);
	}
	public int checkDyn(String ordno) throws Exception {
		return mapper.checkDyn(ordno);
	}
	public void changeStep2(String ordno) throws Exception {
		mapper.changeStep2(ordno);
	}
	public int getOrderStep2(String ordno) throws Exception {
		return mapper.getOrderStep2(ordno);
	}
	
	/**
	 * 상품구매 적립금 처리
	 */
	public GdOrder setGoodsEmoney1(Map<String,Object> paramMap) throws Exception {
		return mapper.setGoodsEmoney1(paramMap);
	}
	public int setGoodsEmoney2(Map<String,Object> paramMap) throws Exception {
		return mapper.setGoodsEmoney2(paramMap);
	}
	public void setGoodsEmoney3(Map<String,Object> paramMap) throws Exception {
		mapper.setGoodsEmoney3(paramMap);
	}
	public void setGoodsEmoney4(Map<String,Object> paramMap) throws Exception {
		mapper.setGoodsEmoney4(paramMap);
	}
	
	/**
	 * 상품에 배치된 쿠폰 세팅
	 */
	public int setGoodsCoupon1(Map<String,Object> paramMap) throws Exception {
		return mapper.setGoodsCoupon1(paramMap);
	}
	public int[] setGoodsCoupon2(Map<String,Object> paramMap) throws Exception {
		List<Integer> rst = mapper.setGoodsCoupon2(paramMap);
		return ArrayUtils.toPrimitive(rst.toArray(new Integer[rst.size()]));
	}
	public List<Map<String, Object>> setGoodsCoupon3(Map<String,Object> paramMap) throws Exception {
		return mapper.setGoodsCoupon3(paramMap);
	}
	public List<GdCoupon> setGoodsCoupon4(Map<String,Object> paramMap) throws Exception {
		return mapper.setGoodsCoupon4(paramMap);
	}
	public int setGoodsCoupon5(Map<String,Object> paramMap) throws Exception {
		return mapper.setGoodsCoupon5(paramMap);
	}
	public void setGoodsCoupon6(Map<String,Object> paramMap) throws Exception {
		mapper.setGoodsCoupon6(paramMap);
	}
	public void setGoodsCoupon7(Map<String,Object> paramMap) throws Exception {
		mapper.setGoodsCoupon7(paramMap);
	}
	@SuppressWarnings("rawtypes")
	public List<HashMap> getCodeItemMap(String groupcd) {
		return mapper.getCodeItemMap(groupcd);
	}
	
	public String getGoodsBrandName(int sno) throws Exception{
		return mapper.getGoodsBrandName(sno);
	}
	public Map<String, Object> getGoodsUseEmoney(int goodsno) throws Exception{
		return mapper.getGoodsUseEmoney(goodsno);
	}
	public List<GdDeliveryPolicy> getDeliveryDefaultPolicy(String no) {
		return this.mapper.getDeliveryDefaultPolicy(no);
	}
	
	public List<GdCoupon> getCouponOrder(){
		return mapper.getCouponOrder();
	}
	
	public void GoodsOrderCouponApplyInsert(Map<String, Object> couponMap)
	{
		couponMap.keySet().stream().forEach(key->{
			System.out.println("eunjung GoodsOrderCouponApplyInsert key : " + key);
			System.out.println("eunjung GoodsOrderCouponApplyInsert value : " + couponMap.get(key));
		});
		mapper.GoodsOrderCouponApplyInsert(couponMap);
	}
	public void GoodsOrderCouponApplymemberInsert(Map<String, Object> couponMap)
	{
		 mapper.GoodsOrderCouponApplymemberInsert(couponMap);
	}
	public int tableMaxFieldSelect(Map<String, Object>tableMaxFieldMap)
	{
		return mapper.tableMaxFieldSelect(tableMaxFieldMap);
	}
	public List<CouponVO> selectCouponcategory(Map<String, Object> couponcd)
	{
		return mapper.selectCouponcategory(couponcd);
	}
	public List<CouponVO> selectCoupongoodsno(Map<String, Object> couponcd)
	{
		return mapper.selectCoupongoodsno(couponcd);
	}
	
	public List<Map<String,String>> orderModifyDC1(long ordno){
		return mapper.orderModifyDC1(ordno);
	}
	
	
	/** 판매사의 상품 추가배송비 정보를 return */
	public Map<String, String> getSellerDeliveryOverPolicy(String sellerCd) {
		Map<String, String> map = new HashMap<String, String>();
		List<SellerDeliveryOverPolicyVO> voList = this.mapper.getSellerDeliveryOverPolicy(sellerCd);
		for(SellerDeliveryOverPolicyVO deliveryOverPolicy : voList) {
			map.put(deliveryOverPolicy.getCities(), String.valueOf(deliveryOverPolicy.getAddDeliveryPrice()));
		}
		
		return map;
	}
	
	/** 판매사의 상품 추가배송비 정보를 GdDeliveryPolicy 클래스에 맞추어 return */
	public List<GdDeliveryPolicy> getSellerDeliveryPolicy(String sellerCd) {
		List<GdDeliveryPolicy> deliveryPolicyList = new ArrayList<GdDeliveryPolicy>();
		GdDeliveryPolicy deliveryPolicy = new GdDeliveryPolicy();
		
		SellerDeliveryPolicyInfoVO sellerDeliveryPolicy = this.mapper.getSellerDeliveryPolicy(sellerCd);
		
		if(sellerDeliveryPolicy == null){
			deliveryPolicy.setrFree("0");
			deliveryPolicy.setrDeliType("0");
			deliveryPolicy.setrDefault("0");
			deliveryPolicy.setrDefaultMsg("0");
			deliveryPolicy.setrDelivery("0");
			deliveryPolicy.setFreeDelivery("0");
			deliveryPolicy.setGoodsDelivery("0");
		}else{
			deliveryPolicy.setrFree(StringUtil.nvl(sellerDeliveryPolicy.getDefaultPolicyFreePrice(),"0"));
			deliveryPolicy.setrDeliType(StringUtil.nvl(sellerDeliveryPolicy.getDefaultPolicyPayment(),"0"));
			deliveryPolicy.setrDefault(StringUtil.nvl(sellerDeliveryPolicy.getDefaultPolicyPrice(),"0"));
			deliveryPolicy.setrDefaultMsg(StringUtil.nvl(sellerDeliveryPolicy.getDefaultPolicyMsg(),"0"));
			deliveryPolicy.setrDelivery(StringUtil.nvl(sellerDeliveryPolicy.getDefaultPolicyNm(),"0"));
			deliveryPolicy.setFreeDelivery(StringUtil.nvl(sellerDeliveryPolicy.getFreeByGoods(),"0"));
			deliveryPolicy.setGoodsDelivery(StringUtil.nvl(sellerDeliveryPolicy.getDeliveryPriceByGoods(),"0"));
		}
		
		deliveryPolicyList.add(deliveryPolicy);
		
		return deliveryPolicyList;
	}
	
	/*
	 * 쿠폰사용조회
	 */
	public List<GdCouponOrder> getFrontCouponOrderList(Map<String, Object> param) throws Exception{
		return mapper.getFrontCouponOrderList(param);
	};
	
	/*
	 * 적립금 로그 
	 */
	public void insertLogEmoney(List<GdCouponOrder> couponOrder) throws Exception {
	
		GdCouponOrder couponOrderParam = null;
		
		for(int i=0 ; i<couponOrder.size();i++){
			couponOrderParam= couponOrder.get(i);
			
			if(couponOrderParam.getEmoney() > 0){
				couponOrderParam.setCoupon(couponOrderParam.getCoupon()+" 쿠폰 사용으로 인한 적립금 적립");
				mapper.insertLogEmoney(couponOrderParam);
			}
		}
	};
	
	/*
	 *  멥버 적립금 적용
	 */
	public void updateMemberEmoney(List<GdCouponOrder> couponOrder) throws Exception {
		
		GdCouponOrder couponOrderParam = null;
		
		for(int i=0 ; i<couponOrder.size();i++){
			couponOrderParam= couponOrder.get(i);

			if(couponOrderParam.getEmoney() > 0){
				mapper.updateMemberEmoney(couponOrderParam);
			}
		}
	};
	
	public List<Map<String,Object>> getCancelLogDeliveryList(Map<String,Object> param){
		return mapper.getCancelLogDeliveryList(param);
	}
	
	public int updateShopLibOrderStep(Map<String,Object> param){
		return mapper.updateShopLibOrderStep(param);
	}
	
	public int updateShopLibOrderIStep(Map<String,Object> param){
		return mapper.updateShopLibOrderIStep(param);
	}
	
	/**
	 * 취소완료 로직
	 * 쿠폰, 할인코드, 적립금 취소처리
	 * @param : ordno (주문번호)
	 * @param : mno (회원번호)
	 * @return
	 */
	public int orderCancelProc(Map<String, Object> pMap) {
		int result = 0;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			
			String ordno = StringUtil.nvl( pMap.get("ordno") ); //주문번호
			String mno = StringUtil.nvl( pMap.get("mno") ); // 회원번호
			
			//비회원
			if("0".equals(mno)) {
				return result;
			}
			
			Map<String, Object> memberInfo = mapper.selectShopLibMemberInfo(pMap);
			if(memberInfo == null) {
				return -1; //회원정보가 없습니다.
			}
			
			Map<String, Object> orderInfo = mapper.selectShopLibOrderInfo(pMap);
			if(orderInfo == null) {
				return -2; //주문내역이 없습니다.
			}
			
			//카드일경우 환불처리
			if("c".equals(orderInfo.get("settlekind"))) {
				
				//결제모듈실행-환불
				System.out.println("결제모듈로  환불실행!!!");
				Map<String, Object> orderPayTranLog = mapper.selectShopLibOrderPayTranLog(pMap);
				JsonObject jsonObj = new JsonObject();
				try {					
					JsonObject resultObject = new JsonObject();					
					resultObject.addProperty("paymentId", (String) orderPayTranLog.get("paymentId"));
					resultObject.addProperty("transactionID", (String) orderPayTranLog.get("transactionID"));
					resultObject.addProperty("amount", (String) orderPayTranLog.get("amount"));
					
					//initPayment API 호출
					HttpResponse<String> res = Unirest.post(apiServerUrl + "/api/v1/payments/credit") // API URL
							.header("Authorization", "Bearer " + authorization) // AccessToken
							.header("X-license-key", licenseKey) //API License Key
							.header("Content-Type", "application/json")
							.header("cache-control", "no-cache")
							.body(resultObject.toString())
							.asString();
					
					String data = res.getBody();
					Gson gson = new Gson();
					@SuppressWarnings("unchecked")
					Map<String, Object> bodyMap = gson.fromJson(data, Map.class);
					bodyMap.put(ordno, ordno);
					service.insertOrderPayLog(bodyMap);
					
				}catch (Exception e) {
					e.printStackTrace();
					jsonObj.addProperty("code", "-99");
					jsonObj.addProperty("message", "E99"); // E99
				}
			}
			
			DecimalFormat form = new DecimalFormat("0.00");
			BigDecimal zero = new BigDecimal("0");
			
			/**
			 * 적립금 취소
			 */
			BigDecimal memberEmoney = new BigDecimal(StringUtil.nvl(memberInfo.get("emoney"),"0")); //회원정보에 남은 적립금
			BigDecimal orderEmoney = new BigDecimal(StringUtil.nvl(orderInfo.get("emoney"),"0")); // 결제시 사용한 적립금
			if(orderEmoney.compareTo(zero) == 1) { //-1은 작다, 0은 같다, 1은 크다
				//적립금 로그등록 (gd_log_emoney)
				paramMap = new HashMap<String, Object>();
				paramMap.put("m_no", mno); //회원번호
				paramMap.put("gbn", "C"); //구분(S:적립, U:사용, E:소멸, C:취소/환불)
				paramMap.put("ordno", ordno); //주문번호
				paramMap.put("emoney", form.format(orderEmoney));
				paramMap.put("memo", "주문 취소에 의한 적립금 환불");
				mapper.insertShopLibEmoneyLog(paramMap);
				
				//회원테이블에 적립금 복구처리 (gd_member)
				paramMap = new HashMap<String, Object>();
				paramMap.put("m_no", mno);
				paramMap.put("emoney", form.format(orderEmoney.add(memberEmoney)));
				result = mapper.updateShopLibMemberEmoney(paramMap);
			}
			
			/**
			 * 쿠폰&할인코드 취소
			 */
			BigDecimal orderCoupon = new BigDecimal(StringUtil.nvl(orderInfo.get("coupon"),"0")); // 결제시 사용한 적립금
			if(orderCoupon.compareTo(zero) == 1) {
				List<Map<String, Object>> couponOrderList = mapper.selectShopLibCouponOrderInfo(pMap);
				if(couponOrderList != null) {
					for(Map<String, Object> couponOrderInfo : couponOrderList) {
						//쿠폰타입(0:운영자발급/1:회원직접다운로드/2:회원가입자동발급/3:TEXT 입력 다운로드형[할인코드])
						String coupontype = StringUtil.nvl( couponOrderInfo.get("coupontype") );
						
						//할인코드
						if(StringUtils.equals("3", coupontype)) {
							//쿠폰결제사용내역 삭제 (gd_coupon_order)
							paramMap = new HashMap<String, Object>();
							paramMap.put("sno", StringUtil.nvl(couponOrderInfo.get("applysno"))); // 쿠폰적용일련번호
							mapper.deleteShopLibCouponApply(paramMap);
							
							//쿠폰결제사용내역 삭제 (gd_coupon_order)
							paramMap = new HashMap<String, Object>();
							paramMap.put("applysno", StringUtil.nvl(couponOrderInfo.get("applysno"))); // 쿠폰적용일련번호
							mapper.deleteShopLibCouponApplymember(paramMap);
							
							//쿠폰결제사용내역 삭제 (gd_coupon_order)
							paramMap = new HashMap<String, Object>();
							paramMap.put("ordno", ordno); // 주문번호
							paramMap.put("applysno", StringUtil.nvl(couponOrderInfo.get("applysno"))); // 쿠폰적용일련번호
							paramMap.put("m_no", mno); // 회원일련번호
							result = mapper.deleteShopLibCouponOrder(paramMap);
						}else {
							// 쿠폰발급회원정보 수정 (gd_coupon_applymember)
							paramMap = new HashMap<String, Object>();
							paramMap.put("applysno", StringUtil.nvl(couponOrderInfo.get("applysno"))); // 쿠폰적용일련번호
							paramMap.put("m_no", mno); // 회원일련번호
							mapper.updateShopLibCouponApplymember(paramMap);
							
							//쿠폰결제사용내역 삭제 (gd_coupon_order)
							paramMap = new HashMap<String, Object>();
							paramMap.put("ordno", ordno); // 주문번호
							paramMap.put("applysno", StringUtil.nvl(couponOrderInfo.get("applysno"))); // 쿠폰적용일련번호
							paramMap.put("m_no", mno); // 회원일련번호
							result = mapper.deleteShopLibCouponOrder(paramMap);
						}
					}
				}
			}
			
			//적립금, 쿠폰 미사용일경우
			if(orderEmoney.compareTo(zero) == 0 && orderCoupon.compareTo(zero) == 0) {
				result = 1;
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 반품(환불)완료 로직
	 * 쿠폰, 할인코드, 적립금 취소처리
	 * 상품구매완료 적립금 차감
	 * @param pMap
	 * @return
	 */
	public int orderReturnProc(String ordno) {
		int result = 0;
		
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			
			//주문정보 및 회원정보 조회
			paramMap.put("ordno", ordno); // 주문번호
			Map<String, Object> orderMemberInfo = mapper.selectShopLibOrderMemberReturnInfo(paramMap);
			if(orderMemberInfo != null) {
				//비회원
				if("0".equals(orderMemberInfo.get("m_no"))) {
					return result;
				}
				
				DecimalFormat form = new DecimalFormat("0.00");
				
				String memEmoney = "";
				String addemoneyyn = StringUtil.N2S(String.valueOf(orderMemberInfo.get("addemoneyyn")), "n") ; //상품구매 후 적립금 지급여부
				BigDecimal bgAddEmoney = new BigDecimal(StringUtil.nvl(orderMemberInfo.get("addemoney"),"0")); //상품구매 후 적립된 적립금
				BigDecimal bgMemEmoney = new BigDecimal(StringUtil.nvl(orderMemberInfo.get("mem_emoney"),"0")); //회원 사용가능 적립금
				
				//상품구매후 적립금 지금 여부 체크
				if( StringUtils.equals("y", addemoneyyn) ) {
					//회원 사용가능 적립금 - 상품구매 후 적립된 적립금
					memEmoney = form.format(bgMemEmoney.subtract(bgAddEmoney).intValue());
					
					//회원테이블에 적립금 업데이트 (gd_member)
					paramMap.put("ordno", ordno);
					paramMap.put("m_no", orderMemberInfo.get("m_no"));
					paramMap.put("emoney", memEmoney);
					mapper.updateShopLibMemberEmoney(paramMap);
					
					//반품(환불)완료 여부 업데이트(gd_order)
					paramMap = new HashMap<String, Object>();
					paramMap.put("ordno", ordno); //주문번호
					paramMap.put("returnyn", 'y'); //반품(환불)완료 여부
					mapper.updateShopLibOrderByReturnyn(paramMap);
					
					//적립금 로그등록 (gd_log_emoney)
					paramMap = new HashMap<String, Object>();
					paramMap.put("m_no", orderMemberInfo.get("m_no")); //회원번호
					paramMap.put("gbn", "C"); //구분(S:적립, U:사용, E:소멸, C:취소/환불)
					paramMap.put("ordno", ordno); //주문번호
					paramMap.put("emoney", "-" + form.format(bgAddEmoney.intValue())); // 상품구매 후 적립된 적립금
					paramMap.put("memo", "반품(환불)에 의한 상품구매 완료 후 지급된 적립금 차감");
					mapper.insertShopLibEmoneyLog(paramMap);
				}
				
				// 취소완료 로직
				paramMap = new HashMap<String, Object>();
				paramMap.put("ordno", ordno);
				paramMap.put("mno", orderMemberInfo.get("m_no"));
				result = orderCancelProc(paramMap);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 회원 적립금 지급 처리
	 * @param cancelVO
	 * @return
	 */
	public int memberAddEmoneyModify(String ordno){
		int result = 0;
		
		try {
			//주문정보&회원정보 조회
			Map<String, Object> orderMemberInfo = mapper.selectShopLibOrderMemberInfo(ordno);
			
			if(orderMemberInfo != null) {
				//비회원
				if("0".equals(orderMemberInfo.get("m_no"))) {
					return result;
				}
				
				String emoney = "0";
				String sumSale = "0"; //구매금액합계
				String sumSale6 = "0"; //최근 6개월 구매금액합계
				BigDecimal bgGoodsprice = new BigDecimal(StringUtil.N2S(String.valueOf( orderMemberInfo.get("goodsprice") ),"0")); //상품금액
				BigDecimal bgAddEmoney = new BigDecimal(StringUtil.N2S(String.valueOf( orderMemberInfo.get("addemoney") ),"0")); //적립 적립금
				BigDecimal bgEemEmoney = new BigDecimal(StringUtil.N2S(String.valueOf( orderMemberInfo.get("mem_emoney") ),"0")); // 회원 적립금
				BigDecimal bgMemSumSale = new BigDecimal(StringUtil.N2S(String.valueOf( orderMemberInfo.get("sum_sale") ),"0")); // 회원 구매금액합계
				BigDecimal bgMemSumSale6 = new BigDecimal(StringUtil.N2S(String.valueOf( orderMemberInfo.get("sum_sale") ),"0")); // 회원 최근 6개월 구매금액합계
				int cntSale = StringUtil.N2I(String.valueOf( orderMemberInfo.get("cnt_sale") )); //회원 구매횟수
				
				BigDecimal Emoney = new BigDecimal(bgAddEmoney.add(bgEemEmoney).intValue()); // 회원 최종 적립금

				DecimalFormat form = new DecimalFormat("0.00");
				emoney = form.format(bgAddEmoney.add(bgEemEmoney).intValue());
				sumSale = form.format(bgGoodsprice.add(bgMemSumSale).intValue());
				sumSale6 = form.format(bgGoodsprice.add(bgMemSumSale6).intValue());
				
				Map<String,Object> paramMap = new HashMap<String, Object>();
				
				//회원테이블에 적립금 업데이트 (gd_member)
				paramMap.put("ordno", ordno);
				paramMap.put("m_no", orderMemberInfo.get("m_no"));
				paramMap.put("emoney", emoney);
				result = mapper.updateShopLibMemberEmoney(paramMap);
				
				if(result > 0) {
					//회원 구매횟수, 구매금액합계 수정(gd_member)
					paramMap = new HashMap<String, Object>();
					paramMap.put("m_no", orderMemberInfo.get("m_no")); //회원번호
					paramMap.put("cnt_sale", ++cntSale); //구매횟수
					paramMap.put("sum_sale", sumSale); //구매금액합계
					paramMap.put("grp_sale", sumSale6); //6개월 구매금액합계
					mapper.updateShopLibMemberSale(paramMap);
					
					//적립금 지급여부 업데이트(gd_order)
					paramMap = new HashMap<String, Object>();
					paramMap.put("ordno", ordno); //주문번호
					paramMap.put("addemoneyyn", 'y'); //적립금 지급여부
					mapper.updateShopLibOrderByAddemoneyyn(paramMap);
					
					//적립금 로그등록 (gd_log_emoney)
					paramMap = new HashMap<String, Object>();
					paramMap.put("m_no", orderMemberInfo.get("m_no")); //회원번호
					paramMap.put("gbn", "S"); //구분(S:적립, U:사용, E:소멸, C:취소/환불)
					paramMap.put("ordno", ordno); //주문번호
					paramMap.put("emoney", form.format(bgAddEmoney.intValue()));
					paramMap.put("memo", "상품 구매에 의한 적립");
					mapper.insertShopLibEmoneyLog(paramMap);
					
					//2. 첫구매 5% 적립 : 프로모션 형태로 첫구매 10% 적립
					//  ++> 배송완료시 적용
					if(cntSale == 1 ) {
						Emoney = Emoney.add(bgGoodsprice.multiply(new BigDecimal("0.1"))); //프로모션 형태로 첫구매 10% 적립
						emoney = form.format(Emoney.intValue());
						
						//회원테이블에 적립금 업데이트 (gd_member)
						paramMap.put("ordno", ordno);
						paramMap.put("m_no", orderMemberInfo.get("m_no"));
						paramMap.put("emoney", emoney);
						result = mapper.updateShopLibMemberEmoney(paramMap);

						//적립금 로그등록 (gd_log_emoney)
						paramMap = new HashMap<String, Object>();
						paramMap.put("m_no", orderMemberInfo.get("m_no")); //회원번호
						paramMap.put("gbn", "S"); //구분(S:적립, U:사용, E:소멸, C:취소/환불)
						paramMap.put("ordno", ordno); //주문번호
						paramMap.put("emoney", form.format(bgGoodsprice.multiply(new BigDecimal("0.1")).intValue())); //프로모션 형태로 첫구매 10% 적립
						paramMap.put("memo", "상품 구매에 의한 적립(첫구매 10% 추가적립)");
						mapper.insertShopLibEmoneyLog(paramMap);
					}

					//5. 무통장 입금 : 2% 추가 적립
					//  ++> 배송완료시 적용
					if("a".equals(orderMemberInfo.get("settlekind"))) {
						Emoney = Emoney.add(bgGoodsprice.multiply(new BigDecimal("0.02")));//무통장입금 2% 추가적립
						emoney = form.format(Emoney.intValue());
						
						//회원테이블에 적립금 업데이트 (gd_member)
						paramMap.put("ordno", ordno);
						paramMap.put("m_no", orderMemberInfo.get("m_no"));
						paramMap.put("emoney", emoney);
						result = mapper.updateShopLibMemberEmoney(paramMap);

						//적립금 로그등록 (gd_log_emoney)
						paramMap = new HashMap<String, Object>();
						paramMap.put("m_no", orderMemberInfo.get("m_no")); //회원번호
						paramMap.put("gbn", "S"); //구분(S:적립, U:사용, E:소멸, C:취소/환불)
						paramMap.put("ordno", ordno); //주문번호
						paramMap.put("emoney", form.format(bgGoodsprice.multiply(new BigDecimal("0.02")).intValue())); //무통장입금 2% 추가적립
						paramMap.put("memo", "상품 구매에 의한 적립(무통장입금 2% 추가적립)");
						mapper.insertShopLibEmoneyLog(paramMap);
					}
					
					//6. 생일축하 : 생일 있는 달 1회 5% 추가적립
					//  ++> 배송완료시 적용
					SimpleDateFormat format = new SimpleDateFormat("MM"); 
					Date now = new Date(); 
					String now_dt = format.format(now); 
					
					
					paramMap.put("mno", orderMemberInfo.get("m_no")); //회원번호
					Map<String, Object> memberInfo = mapper.selectShopLibMemberInfo(paramMap);
					if(memberInfo != null) {
						if(now_dt.equals(memberInfo.get("birth").toString().substring(0, 2))) {
							
							SimpleDateFormat format2 = new SimpleDateFormat("yyyy"); 
							Date now2 = new Date(); 
							String now_dt2 = format2.format(now2); 
							paramMap.put("yyyy", now_dt2); 
							paramMap.put("memo", "생일축하"); 
							int birthCongratsCnt = mapper.getBirthCongratsCnt(paramMap);
							
							if(birthCongratsCnt == 0) {								
								
								Emoney = Emoney.add(bgGoodsprice.multiply(new BigDecimal("0.05"))); //생일축하 5% 추가적립
								emoney = form.format(Emoney.intValue());
								
								//회원테이블에 적립금 업데이트 (gd_member)
								paramMap.put("ordno", ordno);
								paramMap.put("m_no", orderMemberInfo.get("m_no"));
								paramMap.put("emoney", emoney);
								result = mapper.updateShopLibMemberEmoney(paramMap);

								//적립금 로그등록 (gd_log_emoney)
								paramMap = new HashMap<String, Object>();
								paramMap.put("m_no", orderMemberInfo.get("m_no")); //회원번호
								paramMap.put("gbn", "S"); //구분(S:적립, U:사용, E:소멸, C:취소/환불)
								paramMap.put("ordno", ordno); //주문번호
								paramMap.put("emoney", form.format(bgGoodsprice.multiply(new BigDecimal("0.05")).intValue())); //생일축하 5% 추가적립
								paramMap.put("memo", "상품 구매에 의한 적립(생일축하 5% 추가적립)");
								mapper.insertShopLibEmoneyLog(paramMap);
								
							}	
						}
					}
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 비즈톡 발송 정보조회
	 * @param sch_loadfrom
	 * @param table_name
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectShopLibTalkInfo(String ordno) throws Exception {
		return mapper.selectShopLibTalkInfo(ordno);
	}
	
}
