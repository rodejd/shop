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
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdGoodsGoodsoptionGoodslink;

import java.util.Arrays;
import java.util.List;

public class SellerStockVO extends PageMaker {
	
		private int[] vnum=null;   // 글목록 일련번호
		
		
		private String dataLink="";
		private String disabled="";
		
		// 재귀변수
		private String[] cate;   //selectBox 배열
		private String sort="";           // 정렬 
		private String strCategory="";  // form이름  {searchForm, form1}
		private String goodsnm="";     // 상품명(영문)
		private String goodsnmKR="";   // 상품명(국문)
		private String goodsnmCN="";   // 상품명(중문)
		
		
		private String mode="";  //기본 : stock   전송 :  recipe_register, register.jsp~ modify    
		private int[] modeKey=null;
		private int[] goodsno=null;   //상품번호
		private String[] opt1=null;    // 옵션1
		private String[] opt2=null;   // 옵션2
		
		private int[] sno=null;          //
		private int[] consumer=null;  // 정가
		private int[] price=null;        // 판매가
		private int[] supply=null;      // 매입가
		private int[] reserve=null;     // 적립금
		private int[] stock=null;       // 재고
		private int totalCnt;
		
		//동적query
		private String schCategory="";
		private String schWord="";
		private String schOpen="";
		private String schStock="";
		private String schEtc="";
		private String schSellerNm = "";
		private String schGoodsNm = "";
		private String groupBy="";
		private String orderBy="a.goodsno desc";
		private String joinTable = "";
		private String addColumn="";
		
		private String sellerCd = "";	// 판매사코드
		
		
		//2017-08-30 추가 - out
		private String search="";		//페이징유지 검색조건
		
		private List<GdGoodsGoodsoptionGoodslink> goodsList=null;
		
		
		
		public String getSellerCd() {
			return sellerCd;
		}
		public void setSellerCd(String sellerCd) {
			this.sellerCd = sellerCd;
		}
		public String getSchGoodsNm() {
			return schGoodsNm;
		}
		public void setSchGoodsNm(String schGoodsNm) {
			this.schGoodsNm = schGoodsNm;
		}
		public String getSchSellerNm() {
			return schSellerNm;
		}
		public void setSchSellerNm(String schSellerNm) {
			this.schSellerNm = schSellerNm;
		}
		public String getSearch() {
			return search;
		}
		public void setSearch(String search) {
			this.search = search;
		}
		public int getTotalCnt() {
			return totalCnt;
		}
		public void setTotalCnt(int totalCnt) {
			this.totalCnt = totalCnt;
		}
		public String[] getOpt2() {
			return opt2;
		}
		public void setOpt2(String[] opt2) {
			this.opt2 = opt2;
		}
		
		public String[] getCate() {
			return cate;
		}
		public void setCate(String[] cate) {
			this.cate = cate;
		}
		
		public String getDataLink() {
			return dataLink;
		}
		public void setDataLink(String dataLink) {
			this.dataLink = dataLink;
		}
		public String getDisabled() {
			return disabled;
		}
		public void setDisabled(String disabled) {
			this.disabled = disabled;
		}
		
		public List<GdGoodsGoodsoptionGoodslink> getGoodsList() {
			return goodsList;
		}
		public void setGoodsList(List<GdGoodsGoodsoptionGoodslink> goodsList) {
			this.goodsList = goodsList;
		}
		public int[] getVnum() {
			return vnum;
		}
		public void setVnum(int[] vnum) {
			this.vnum = vnum;
		}
		public String getStrCategory() {
			return strCategory;
		}
		public void setStrCategory(String strCategory) {
			this.strCategory = strCategory;
		}
		public String getSchCategory() {
			return schCategory;
		}
		public void setSchCategory(String schCategory) {
			this.schCategory = schCategory;
		}
		public String getSchWord() {
			return schWord;
		}
		public void setSchWord(String schWord) {
			this.schWord = schWord;
		}
		public String getSchOpen() {
			return schOpen;
		}
		public void setSchOpen(String schOpen) {
			this.schOpen = schOpen;
		}
		public String getSchStock() {
			return schStock;
		}
		public void setSchStock(String schStock) {
			this.schStock = schStock;
		}
		public String getSchEtc() {
			return schEtc;
		}
		public void setSchEtc(String schEtc) {
			this.schEtc = schEtc;
		}
		public String getGroupBy() {
			return groupBy;
		}
		public void setGroupBy(String groupBy) {
			this.groupBy = groupBy;
		}
		public String getOrderBy() {
			return orderBy;
		}
		public void setOrderBy(String orderBy) {
			this.orderBy = orderBy;
		}
		public String getJoinTable() {
			return joinTable;
		}
		public void setJoinTable(String joinTable) {
			this.joinTable = joinTable;
		}
		public String getAddColumn() {
			return addColumn;
		}
		public void setAddColumn(String addColumn) {
			this.addColumn = addColumn;
		}
		
		public String getSort() {
			return sort;
		}
		public void setSort(String sort) {
			this.sort = sort;
		}
		public String getGoodsnm() {
			return goodsnm;
		}
		public void setGoodsnm(String goodsnm) {
			this.goodsnm = goodsnm;
		}
		public String getGoodsnmKR() {
			return goodsnmKR;
		}
		public void setGoodsnmKR(String goodsnmKR) {
			this.goodsnmKR = goodsnmKR;
		}
		public String getGoodsnmCN() {
			return goodsnmCN;
		}
		public void setGoodsnmCN(String goodsnmCN) {
			this.goodsnmCN = goodsnmCN;
		}
		public String getMode() {
			return mode;
		}
		public void setMode(String mode) {
			this.mode = mode;
		}
		public int[] getGoodsno() {
			return goodsno;
		}
		public void setGoodsno(int[] goodsno) {
			this.goodsno = goodsno;
		}
		public String[] getOpt1() {
			return opt1;
		}
		public void setOpt1(String[] opt1) {
			this.opt1 = opt1;
		}
		public int[] getModeKey() {
			return modeKey;
		}
		public void setModeKey(int[] modeKey) {
			this.modeKey = modeKey;
		}
		public int[] getSno() {
			return sno;
		}
		public void setSno(int[] sno) {
			this.sno = sno;
		}
		
		public int getConsumer(int index){
			return consumer[index];
		}
		public int[] getConsumer() {
			return consumer;
		}
		public void setConsumer(int[] consumer) {
			this.consumer = consumer;
		}
		public int getPrice(int index){
			return price[index];
		}
		public int[] getPrice() {
			return price;
		}
		public void setPrice(int[] price) {
			this.price = price;
		}
		public int getSupply(int index){
			return supply[index];
		}
		public int[] getSupply() {
			return supply;
		}
		public void setSupply(int[] supply) {
			this.supply = supply;
		}
		public int getReserve(int index){
			return reserve[index];
		}
		public int[] getReserve() {
			return reserve;
		}
		public void setReserve(int[] reserve) {
			this.reserve = reserve;
		}
		public int getStock(int index){
			return stock[index];
		}
		public int[] getStock() {
			return stock;
		}
		public void setStock(int[] stock) {
			this.stock = stock;
		}
		@Override
		public String toString() {
			return "StockVO [vnum=" + Arrays.toString(vnum) + ", dataLink="
					+ dataLink + ", disabled=" + disabled + ", cate="
					+ Arrays.toString(cate) + ", sort=" + sort
					+ ", strCategory=" + strCategory + ", goodsnm=" + goodsnm
					+ ", mode=" + mode + ", modeKey="
					+ Arrays.toString(modeKey) + ", goodsno="
					+ Arrays.toString(goodsno) + ", opt1="
					+ Arrays.toString(opt1) + ", opt2=" + Arrays.toString(opt2)
					+ ", sno=" + Arrays.toString(sno) + ", consumer="
					+ Arrays.toString(consumer) + ", price="
					+ Arrays.toString(price) + ", supply="
					+ Arrays.toString(supply) + ", reserve="
					+ Arrays.toString(reserve) + ", stock="
					+ Arrays.toString(stock) + ", totalCnt=" + totalCnt
					+ ", schCategory=" + schCategory + ", schWord=" + schWord
					+ ", schOpen=" + schOpen + ", schStock=" + schStock
					+ ", schEtc=" + schEtc + ", groupBy=" + groupBy
					+ ", orderBy=" + orderBy + ", joinTable=" + joinTable
					+ ", addColumn=" + addColumn + ", goodsList=" + goodsList
					+ "]";
		}

		

		
		
		
}
