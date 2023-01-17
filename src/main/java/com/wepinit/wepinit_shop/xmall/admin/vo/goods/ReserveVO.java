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
package com.wepinit.wepinit_shop.xmall.admin.vo.goods;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdGoodsGoodsoptionGoodslink;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ReserveVO extends PageMaker {
	private String indicate="";
	private String skey="";
	private String sword="";
	private String open="";
	private String smain="";
	private String sevent="";
	private String seventpage="";
	private String roundunit="";
	private String roundtype="";
	private String method="";
	private String reserve="";
	private String percent="";
	
	private int[] chk;
	private int[] priceAmt;
	
	// 재귀변수
	private String[] cate;   //selectBox 배열
	private String strCategory="";  // form이름  {searchForm, form1}
	
	//동적query
	private String schCategory="";
	private String schWord="";
	private String schOpen="";
	private String schStock="";
	private String schEtc="";
	private String groupBy="";
	private String orderBy="";
	private String joinTable = "";
	private String addColumn="";
	
	private int[] vnum=null;   // 글목록 일련번호
	
	private String mode;
	
	private List<GdGoodsGoodsoptionGoodslink> reserveList;
	private List<HashMap<String, String>> goodsTypeList;
	
	private int totalCnt;
	
	//2017-08-30 추가 - out
	private String search="";		//페이징유지 검색조건
	
	private String schSellerNm = "";	// 판매사명
	
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

	public List<HashMap<String, String>> getGoodsTypeList() {
		return goodsTypeList;
	}

	public void setGoodsTypeList(List<HashMap<String, String>> goodsTypeList) {
		this.goodsTypeList = goodsTypeList;
	}

	public String getIndicate() {
		return indicate;
	}

	public void setIndicate(String indicate) {
		this.indicate = indicate;
	}

	public String getSkey() {
		return skey;
	}

	public void setSkey(String skey) {
		this.skey = skey;
	}

	public String getSword() {
		return sword;
	}

	public void setSword(String sword) {
		this.sword = sword;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getSmain() {
		return smain;
	}

	public void setSmain(String smain) {
		this.smain = smain;
	}

	public String getSevent() {
		return sevent;
	}

	public void setSevent(String sevent) {
		this.sevent = sevent;
	}

	public String getSeventpage() {
		return seventpage;
	}

	public void setSeventpage(String seventpage) {
		this.seventpage = seventpage;
	}

	public String getRoundunit() {
		return roundunit;
	}

	public void setRoundunit(String roundunit) {
		this.roundunit = roundunit;
	}

	public String getRoundtype() {
		return roundtype;
	}

	public void setRoundtype(String roundtype) {
		this.roundtype = roundtype;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public int[] getChk() {
		return chk;
	}

	public void setChk(int[] chk) {
		this.chk = chk;
	}

	public int[] getPriceAmt() {
		return priceAmt;
	}
	public int getPriceAmt2(int index){
		return priceAmt[index];
	}
	public void setPriceAmt(int[] priceAmt) {
		this.priceAmt = priceAmt;
	}
	public void setPriceAmt2(int priceAmt, int index){
		this.priceAmt[index] = priceAmt;
	}


	public String[] getCate() {
		return cate;
	}

	public void setCate(String[] cate) {
		this.cate = cate;
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

	public int[] getVnum() {
		return vnum;
	}

	public void setVnum(int[] vnum) {
		this.vnum = vnum;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public List<GdGoodsGoodsoptionGoodslink> getReserveList() {
		return reserveList;
	}

	public void setReserveList(List<GdGoodsGoodsoptionGoodslink> reserveList) {
		this.reserveList = reserveList;
	}

	@Override
	public String toString() {
		return "ReserveVO [indicate=" + indicate + ", skey=" + skey
				+ ", sword=" + sword + ", open=" + open + ", smain=" + smain
				+ ", sevent=" + sevent + ", seventpage=" + seventpage
				+ ", roundunit=" + roundunit + ", roundtype=" + roundtype
				+ ", method=" + method + ", reserve=" + reserve + ", percent="
				+ percent + ", chk=" + Arrays.toString(chk) + ", priceAmt="
				+ Arrays.toString(priceAmt) + ", cate=" + Arrays.toString(cate)
				+ ", strCategory=" + strCategory + ", schCategory="
				+ schCategory + ", schWord=" + schWord + ", schOpen=" + schOpen
				+ ", schStock=" + schStock + ", schEtc=" + schEtc
				+ ", groupBy=" + groupBy + ", orderBy=" + orderBy
				+ ", joinTable=" + joinTable + ", addColumn=" + addColumn
				+ ", vnum=" + Arrays.toString(vnum) + ", mode=" + mode
				+ ", reserveList=" + reserveList+ ", goodsTypeList="
				+ goodsTypeList + ", totalCnt=" + totalCnt + "]";
	}




	
	
}
