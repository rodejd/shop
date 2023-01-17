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
/********************************************************************************
* <pre>
* 업무구분명			:	Community
* 세부업무구분명	: 	사용자 > Community
* 작성자 				: 	이병환
* 설명 				: 	사용자 Community VO
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170208		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.vo.goods;

import com.wepinit.wepinit_shop.xmall.common.CommonVO;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.CouponCouponcategoryCoupongoodsno;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdGoodsGoodsoptionGoodslink;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdGoodsGoodsoptionGoodslinkGoodsbrand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FrontGoodsVO extends CommonVO{
	
	public FrontGoodsVO(){
		super.pageSize = 20;
	}
	
	//input
	private long brandno;							// 상품브랜드일련번호
	private String category;						// 카테고리
	private String sale;							// 세일
	private String sort = "";				// 정렬 기준
	private String listKind;						// 보여지는 리스트유형(갤러리형:tpl_01 , 리스트형:tpl_02 , 리스트그룹형:tpl_03 )
	private String[] relationArray;					// 관련상품번호 array
	private String goodsno;							// 상품번호
	private int m_no; 
	private String schSpec = "";					//특별구좌
	private String schType = "";
	private String brands;
	private String options;

	private String ordno;							// 주문번호(리뷰용)
	
	//output(브랜드목록)
	private GdGoodsBrand gdGoodsBrandObj = null;
	private XmCategoryBrandInfo xmCategoryBrandInfoObj = null;
	private List<GdGoodsGoodsoptionGoodslink> frontGoodsBrandList = null; //goods_brand
	
	//output(카테고리목록)
	private List<GdGoodsGoodsoptionGoodslinkGoodsbrand> frontGoodsGoodsList = null;	// 카테고리별 상품리스트
	private List<GdCategory> frontGoodsGoodsCategoryList = null;					// 카테고리 리스트
	private List<GdGoodsBrand> frontGoodsGoodsBrandList = null;

	//output
	private List<GdGoodsGoodsoptionGoodslink> frontGoodsList 	= new ArrayList<GdGoodsGoodsoptionGoodslink>();		//상품 리스트
	private List<GdGoodsGoodsoptionGoodslink> slideGoodsList 	= new ArrayList<GdGoodsGoodsoptionGoodslink>();		//슬라이드 상품 리스트
	
	//상품상세 
	private GdGoodsGoodsoptionGoodslink goodsView;			//상품상세정보
	private List<GdGoodsBrand> brandList;					//상품브랜드목록
	private List<GdGoods> relationGoodsList;				//관련 상품 목록
	private int	goodsReviewAvg;								//상품평 POINT 평균
	private List<GdGoodsOption> goodsOptionList;			//상품 필수 옵션 목록
	private FrontGoodsReviewVO frontGoodsReviewVO;			//상품별 상품 Review
	private FrontGoodsQnAVO	frontGoodsQnAVO;				//상품별 QNA
	private List<Map<String,Object>> 	goodsAddOptionList;	//추가옵션 정보 목록
                                                                
	private List<GdDeliveryPolicy> deliveryInfoList;		// 기본배송정책 리스트
	private List<CouponCouponcategoryCoupongoodsno> couponList;		//회원직접 다운로드 쿠폰 리스트
	private Map<String, String> overDeliveryInfo;			// 추가배송비 정보
		
	public String getOrdno() {
		return ordno;
	}
	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}
	public long getBrandno() {
		return brandno;
	}
	public void setBrandno(long brandno) {
		this.brandno = brandno;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSale() {
		return sale;
	}
	public void setSale(String sale) {
		this.sale = sale;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getListKind() {
		return listKind;
	}
	public void setListKind(String listKind) {
		this.listKind = listKind;
	}
	public String[] getRelationArray() {
		return relationArray;
	}
	public void setRelationArray(String[] relationArray) {
		this.relationArray = relationArray;
	}
	public String getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getSchSpec() {
		return schSpec;
	}
	public void setSchSpec(String schSpec) {
		this.schSpec = schSpec;
	}
	public String getSchType() {
		return schType;
	}
	public void setSchType(String schType) {
		this.schType = schType;
	}
	public String getBrands() {
		return brands;
	}
	public void setBrands(String brands) {
		this.brands = brands;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public GdGoodsBrand getGdGoodsBrandObj() {
		return gdGoodsBrandObj;
	}
	public void setGdGoodsBrandObj(GdGoodsBrand gdGoodsBrandObj) {
		this.gdGoodsBrandObj = gdGoodsBrandObj;
	}
	public XmCategoryBrandInfo getXmCategoryBrandInfoObj() {
		return xmCategoryBrandInfoObj;
	}
	public void setXmCategoryBrandInfoObj(XmCategoryBrandInfo xmCategoryBrandInfoObj) {
		this.xmCategoryBrandInfoObj = xmCategoryBrandInfoObj;
	}
	public List<GdGoodsGoodsoptionGoodslink> getFrontGoodsBrandList() {
		return frontGoodsBrandList;
	}
	public void setFrontGoodsBrandList(List<GdGoodsGoodsoptionGoodslink> frontGoodsBrandList) {
		this.frontGoodsBrandList = frontGoodsBrandList;
	}
	public List<GdGoodsGoodsoptionGoodslinkGoodsbrand> getFrontGoodsGoodsList() {
		return frontGoodsGoodsList;
	}
	public void setFrontGoodsGoodsList(List<GdGoodsGoodsoptionGoodslinkGoodsbrand> frontGoodsGoodsList) {
		this.frontGoodsGoodsList = frontGoodsGoodsList;
	}
	public List<GdCategory> getFrontGoodsGoodsCategoryList() {
		return frontGoodsGoodsCategoryList;
	}
	public void setFrontGoodsGoodsCategoryList(List<GdCategory> frontGoodsGoodsCategoryList) {
		this.frontGoodsGoodsCategoryList = frontGoodsGoodsCategoryList;
	}
	public List<GdGoodsBrand> getFrontGoodsGoodsBrandList() {
		return frontGoodsGoodsBrandList;
	}
	public void setFrontGoodsGoodsBrandList(List<GdGoodsBrand> frontGoodsGoodsBrandList) {
		this.frontGoodsGoodsBrandList = frontGoodsGoodsBrandList;
	}
	public List<GdGoodsGoodsoptionGoodslink> getFrontGoodsList() {
		return frontGoodsList;
	}
	public void setFrontGoodsList(List<GdGoodsGoodsoptionGoodslink> frontGoodsList) {
		this.frontGoodsList = frontGoodsList;
	}
	public List<GdGoodsGoodsoptionGoodslink> getSlideGoodsList() {
		return slideGoodsList;
	}
	public void setSlideGoodsList(List<GdGoodsGoodsoptionGoodslink> slideGoodsList) {
		this.slideGoodsList = slideGoodsList;
	}
	public GdGoodsGoodsoptionGoodslink getGoodsView() {
		return goodsView;
	}
	public void setGoodsView(GdGoodsGoodsoptionGoodslink goodsView) {
		this.goodsView = goodsView;
	}
	public List<GdGoodsBrand> getBrandList() {
		return brandList;
	}
	public void setBrandList(List<GdGoodsBrand> brandList) {
		this.brandList = brandList;
	}
	public List<GdGoods> getRelationGoodsList() {
		return relationGoodsList;
	}
	public void setRelationGoodsList(List<GdGoods> relationGoodsList) {
		this.relationGoodsList = relationGoodsList;
	}
	public int getGoodsReviewAvg() {
		return goodsReviewAvg;
	}
	public void setGoodsReviewAvg(int goodsReviewAvg) {
		this.goodsReviewAvg = goodsReviewAvg;
	}
	public List<GdGoodsOption> getGoodsOptionList() {
		return goodsOptionList;
	}
	public void setGoodsOptionList(List<GdGoodsOption> goodsOptionList) {
		this.goodsOptionList = goodsOptionList;
	}
	public FrontGoodsReviewVO getFrontGoodsReviewVO() {
		return frontGoodsReviewVO;
	}
	public void setFrontGoodsReviewVO(FrontGoodsReviewVO frontGoodsReviewVO) {
		this.frontGoodsReviewVO = frontGoodsReviewVO;
	}
	public FrontGoodsQnAVO getFrontGoodsQnAVO() {
		return frontGoodsQnAVO;
	}
	public void setFrontGoodsQnAVO(FrontGoodsQnAVO frontGoodsQnAVO) {
		this.frontGoodsQnAVO = frontGoodsQnAVO;
	}
	public List<Map<String, Object>> getGoodsAddOptionList() {
		return goodsAddOptionList;
	}
	public void setGoodsAddOptionList(List<Map<String, Object>> goodsAddOptionList) {
		this.goodsAddOptionList = goodsAddOptionList;
	}
	public List<GdDeliveryPolicy> getDeliveryInfoList() {
		return deliveryInfoList;
	}
	public void setDeliveryInfoList(List<GdDeliveryPolicy> deliveryInfoList) {
		this.deliveryInfoList = deliveryInfoList;
	}
	public List<CouponCouponcategoryCoupongoodsno> getCouponList() {
		return couponList;
	}
	public void setCouponList(List<CouponCouponcategoryCoupongoodsno> couponList) {
		this.couponList = couponList;
	}
	public Map<String, String> getOverDeliveryInfo() {
		return overDeliveryInfo;
	}
	public void setOverDeliveryInfo(Map<String, String> overDeliveryInfo) {
		this.overDeliveryInfo = overDeliveryInfo;
	}
}
