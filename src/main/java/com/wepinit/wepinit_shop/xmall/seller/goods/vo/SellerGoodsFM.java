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
package com.wepinit.wepinit_shop.xmall.seller.goods.vo;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoods;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsBrand;

import java.util.List;

public class SellerGoodsFM extends PageMaker {
	
	private int totalCnt = 0;
	private String ssSellerCd = "";	// 판매사 세션의 판매사 코드
	private String reqsno = "";
	private String goodsno = "";
	
	private String schKey = "";
	private String schWord = "";
	private String schMinPrice = "";
	private String schMaxPrice = "";
	private String schStRegdt = "";
	private String schEdRegdt = "";
	private String schAprlStat = "";
	private String schSort = "";
	private String schCategory ="";	// ifrmae 상품 목록 카테고리
	
	private String listViewCd = "";	// 리스트 보기 구분(등록신청 : I, 수정신청 : U, 삭제신청 : D);
	private String procType ="";	// 처리구분(I/U/D)
	private String mode ="";		// 화면구분(register/modify)
	private String name ="";		// iframe 상품 목록 명
	
	private SellerGoodsViewVO sellerGoodsViewVO = new SellerGoodsViewVO();
	private SellerBrandVO sellerBrandVO = new SellerBrandVO();
	
	private String[] myiconArr = null;				// 아이콘 array
	private String[] myiconChkArr = null;			// 아이콘 checked array
	private String[][] imgsArray;
	
	private List<GdGoods> originList = null;		// 원산지 리스트
	private List<GdGoods> makerList = null;			// 제조자 리스트
	private List<GdGoodsBrand> brandList = null;	// 브랜드 리스트
	
	private List<SellerGoodsViewVO> goodsNotiList = null; 	// 상품고시 관련
	private List<SellerGoodsViewVO> categoryList = null;	// 상품분류 리스트
	private List<SellerGoodsViewVO> goodsOptionList = null;	// 필수옵션 리스트
	private List<SellerGoodsViewVO> goodsAddInfoList = null; // 추가정보 리스트
	private List<SellerGoodsViewVO> sellerGoodsList = null; 	// 상품 요청 리스트
	
	private List<SellerGoodsViewVO> goodsRelationList = null; // 관련 상품  리스트
	
	final String[] strImg = new String[]{"원본(확대)이미지", "메인노출이미지", "리스트이미지", "상세이미지"};
	
	
	public String[][] getImgsArray() {
		return imgsArray;
	}
	public void setImgsArray(String[][] imgsArray) {
		this.imgsArray = imgsArray;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public String getSsSellerCd() {
		return ssSellerCd;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public void setSsSellerCd(String ssSellerCd) {
		this.ssSellerCd = ssSellerCd;
	}
	public String getProcType() {
		return procType;
	}
	public SellerGoodsViewVO getSellerGoodsViewVO() {
		return sellerGoodsViewVO;
	}
	public void setProcType(String procType) {
		this.procType = procType;
	}
	public void setSellerGoodsViewVO(SellerGoodsViewVO sellerGoodsViewVO) {
		this.sellerGoodsViewVO = sellerGoodsViewVO;
	}
	public List<GdGoods> getOriginList() {
		return originList;
	}
	public List<GdGoodsBrand> getBrandList() {
		return brandList;
	}
	public void setOriginList(List<GdGoods> originList) {
		this.originList = originList;
	}
	public void setMakerList(List<GdGoods> makerList) {
		this.makerList = makerList;
	}
	public void setBrandList(List<GdGoodsBrand> brandList) {
		this.brandList = brandList;
	}
	public String[] getMyiconArr() {
		return myiconArr;
	}
	public void setMyiconArr(String[] myiconArr) {
		this.myiconArr = myiconArr;
	}
	public String getReqsno() {
		return reqsno;
	}
	public void setReqsno(String reqsno) {
		this.reqsno = reqsno;
	}
	public List<SellerGoodsViewVO> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<SellerGoodsViewVO> categoryList) {
		this.categoryList = categoryList;
	}
	public List<SellerGoodsViewVO> getGoodsOptionList() {
		return goodsOptionList;
	}
	public void setGoodsOptionList(List<SellerGoodsViewVO> goodsOptionList) {
		this.goodsOptionList = goodsOptionList;
	}
	public List<SellerGoodsViewVO> getGoodsAddInfoList() {
		return goodsAddInfoList;
	}
	public void setGoodsAddInfoList(List<SellerGoodsViewVO> goodsAddInfoList) {
		this.goodsAddInfoList = goodsAddInfoList;
	}
	public List<SellerGoodsViewVO> getGoodsNotiList() {
		return goodsNotiList;
	}
	public void setGoodsNotiList(List<SellerGoodsViewVO> goodsNotiList) {
		this.goodsNotiList = goodsNotiList;
	}
	
	public SellerBrandVO getSellerBrandVO() {
		return sellerBrandVO;
	}
	public void setSellerBrandVO(SellerBrandVO sellerBrandVO) {
		this.sellerBrandVO = sellerBrandVO;
	}
	public String getSchKey() {
		return schKey;
	}
	public String getSchWord() {
		return schWord;
	}
	public String getSchMinPrice() {
		return schMinPrice;
	}
	public String getSchMaxPrice() {
		return schMaxPrice;
	}
	public void setSchKey(String schKey) {
		this.schKey = schKey;
	}
	public void setSchWord(String schWord) {
		this.schWord = schWord;
	}
	public void setSchMinPrice(String schMinPrice) {
		this.schMinPrice = schMinPrice;
	}
	public void setSchMaxPrice(String schMaxPrice) {
		this.schMaxPrice = schMaxPrice;
	}
	public String getSchAprlStat() {
		return schAprlStat;
	}
	public void setSchAprlStat(String schAprlStat) {
		this.schAprlStat = schAprlStat;
	}
	public List<SellerGoodsViewVO> getSellerGoodsList() {
		return sellerGoodsList;
	}
	public void setSellerGoodsList(List<SellerGoodsViewVO> sellerGoodsList) {
		this.sellerGoodsList = sellerGoodsList;
	}
	public String getSchStRegdt() {
		return schStRegdt;
	}
	public String getSchEdRegdt() {
		return schEdRegdt;
	}
	public void setSchStRegdt(String schStRegdt) {
		this.schStRegdt = schStRegdt;
	}
	public void setSchEdRegdt(String schEdRegdt) {
		this.schEdRegdt = schEdRegdt;
	}
	public String getSchSort() {
		return schSort;
	}
	public void setSchSort(String schSort) {
		this.schSort = schSort;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getListViewCd() {
		return listViewCd;
	}
	public void setListViewCd(String listViewCd) {
		this.listViewCd = listViewCd;
	}
	public String[] getMyiconChkArr() {
		return myiconChkArr;
	}
	public void setMyiconChkArr(String[] myiconChkArr) {
		this.myiconChkArr = myiconChkArr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchCategory() {
		return schCategory;
	}
	public void setSchCategory(String schCategory) {
		this.schCategory = schCategory;
	}
	public String getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}
	public List<SellerGoodsViewVO> getGoodsRelationList() {
		return goodsRelationList;
	}
	public void setGoodsRelationList(List<SellerGoodsViewVO> goodsRelationList) {
		this.goodsRelationList = goodsRelationList;
	}
	
	/**
	 * Proc validation check
	 */
	public void validSellerGoodsProc(){
//		if( !"I".equals(procType) ) {
//			// 고유번호가 없을 경우 오류처리
//			if( "".equals(sellerGoodsViewVO.getReqsno()) ) {
//				throw new BizException("필수입력 누락 - sno");
//			}
//		}
//		
//		// 등록, 수정일 경우 제목, 내용 체크
//		if( !"D".equals(procType) ) {
//			if ( "".equals(noticeVO.getTitle()) ) {
//				throw new BizException("필수입력 누락 - title");
//			}
//			if ( "".equals(noticeVO.getContents()) ) {
//				throw new BizException("필수입력 누락 - contents");
//			}
//		}
	}
}
