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
import com.wepinit.wepinit_shop.xmall.common.vo.GdConfSet;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdGoodsGoodsoptionGoodslink;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SellerPriceVO  extends PageMaker{
	
	private String indicate="";
	private String skey="";
	private String sword="";
	private String open="";
	private String smain="";
	private String sevent="";
	private String seventpage="";
	private String dtprice="";
	private int pmnum=0;
	private String pmmark="";
	private String pmtype="";
	private String tgprice="";
	private String roundunit="";
	private String roundtype="";
	private int totalCnt;
	
	private List<GdGoodsGoodsoptionGoodslink> priceList=null;
	private int[] chk;
	private int[] consumerAmt;
	private int[] priceAmt;
	private int[] supplyAmt;
	private List<GdConfSet> arrTmp;  // getGoodsType() => list
	private List<HashMap<String, String>> goodsTypeList;
	
	private String mode;
	
	private int[] vnum=null;   // 글목록 일련번호
	private int sno;
	private String priceColumn;
	
	
	// 재귀변수
	private String[] cate;   //selectBox 배열
	private String sort="";           // 정렬 
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
	
	private String schSellerNm = "";	// 판매자명
	private String sellerCd = "";		// 판매사코드
	
	//2017-08-30 추가 - out
	private String search="";		//페이징유지 검색조건
	
	
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
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
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getPriceColumn() {
		return priceColumn;
	}
	public void setPriceColumn(String priceColumn) {
		this.priceColumn = priceColumn;
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
	
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}

	public String getDtprice() {
		return dtprice;
	}
	public void setDtprice(String dtprice) {
		this.dtprice = dtprice;
	}
	public List<GdGoodsGoodsoptionGoodslink> getPriceList() {
		return priceList;
	}

	public int[] getVnum() {
		return vnum;
	}
	public void setVnum(int[] vnum) {
		this.vnum = vnum;
	}
	public String[] getCate() {
		return cate;
	}
	public void setCate(String[] cate) {
		this.cate = cate;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
	
	
	public String getSword() {
		return sword;
	}
	public void setSword(String sword) {
		this.sword = sword;
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
	public String getPmmark() {
		return pmmark;
	}
	public void setPmmark(String pmmark) {
		this.pmmark = pmmark;
	}
	public String getPmtype() {
		return pmtype;
	}
	public void setPmtype(String pmtype) {
		this.pmtype = pmtype;
	}
	public String getTgprice() {
		return tgprice;
	}
	public void setTgprice(String tgprice) {
		this.tgprice = tgprice;
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
	public int[] getChk() {
		return chk;
	}
	public void setChk(int[] chk) {
		this.chk = chk;
	}
	
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public int getPmnum() {
		return pmnum;
	}
	public void setPmnum(int pmnum) {
		this.pmnum = pmnum;
	}
	public int[] getConsumerAmt() {
		return consumerAmt;
	}
	public void setConsumerAmt(int[] consumerAmt) {
		this.consumerAmt = consumerAmt;
	}
	public int[] getPriceAmt() {
		return priceAmt;
	}
	public void setPriceAmt(int[] priceAmt) {
		this.priceAmt = priceAmt;
	}
	public int[] getSupplyAmt() {
		return supplyAmt;
	}
	public void setSupplyAmt(int[] supplyAmt) {
		this.supplyAmt = supplyAmt;
	}
	public List<GdConfSet> getArrTmp() {
		return arrTmp;
	}
	public void setArrTmp(List<GdConfSet> arrTmp) {
		this.arrTmp = arrTmp;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public void setPriceList(List<GdGoodsGoodsoptionGoodslink> priceList) {
		this.priceList = priceList;
	}
	
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	@Override
	public String toString() {
		return "PriceVO [indicate=" + indicate + ", skey=" + skey + ", sword="
				+ sword + ", open=" + open + ", smain=" + smain + ", sevent="
				+ sevent + ", seventpage=" + seventpage + ", dtprice="
				+ dtprice + ", pmnum=" + pmnum + ", pmmark=" + pmmark
				+ ", pmtype=" + pmtype + ", tgprice=" + tgprice
				+ ", roundunit=" + roundunit + ", roundtype=" + roundtype
				+ ", priceList=" + priceList + ", chk=" + Arrays.toString(chk)
				+ ", consumerAmt=" + Arrays.toString(consumerAmt)
				+ ", priceAmt=" + Arrays.toString(priceAmt) + ", supplyAmt="
				+ Arrays.toString(supplyAmt) + ", arrTmp=" + arrTmp
				+ ", goodsTypeList=" + goodsTypeList + ", mode=" + mode
				+ ", vnum=" + Arrays.toString(vnum) + ", sno=" + sno
				+ ", priceColumn=" + priceColumn + ", cate="
				+ Arrays.toString(cate) + ", sort=" + sort + ", strCategory="
				+ strCategory 
				+ ", schCategory=" + schCategory + ", schWord=" + schWord
				+ ", schOpen=" + schOpen + ", schStock=" + schStock
				+ ", schEtc=" + schEtc + ", groupBy=" + groupBy + ", orderBy="
				+ orderBy + ", joinTable=" + joinTable + ", addColumn="
				+ addColumn + "]";
	}
	
	
	
	
	
	
}
