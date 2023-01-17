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
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdEventGoodslinkGoodsdisplay;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdGoodsGoodsoptionGoodslink;

import java.util.Arrays;
import java.util.List;

public class SellerLinkVO extends PageMaker {

	private String indicate="";
	private String skey="";
	private String sword="";
	private String open="";
	private String isToday="";
	private int[] chk;
	
	private int totalCount=0;
	private String mode="";
	private int searchCount = 0;
	private int[] vnum=null;   // 글목록 일련번호
	
	// 재귀변수
	private String[] cate;   //selectBox 배열
	private String strCategory="";  // form이름  {searchForm, form1}
	private String unlink="";
	private String strCategorySub="";  // 아래 category이름
	
	private String[] cateSub;
	
	//동적query
	private String schCategory="";
	private String schWord="";
	private String schOpen="";
	private String schPrice="";
	private String schEtc="";
	private String schRegdt="";
	private String groupBy="";
	private String orderBy="";
	private String joinTable = "";
	private String addColumn="";
	
	//2017-08-31 추가 - out
	private String search="";		//페이징유지 검색조건
	
	private String schSellerNm = "";	// 판매사명
	private String sellerCd = "";	// 판매사코드
	
	private List<GdGoodsGoodsoptionGoodslink> linkList = null;
	private List<GdEventGoodslinkGoodsdisplay> linkEventList;
	
	
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
	public List<GdEventGoodslinkGoodsdisplay> getLinkEventList() {
		return linkEventList;
	}
	public void setLinkEventList(List<GdEventGoodslinkGoodsdisplay> linkEventList) {
		this.linkEventList = linkEventList;
	}
	
	public String[] getCateSub() {
		return cateSub;
	}
	public void setCateSub(String[] cateSub) {
		this.cateSub = cateSub;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getSearchCount() {
		return searchCount;
	}
	public void setSearchCount(int searchCount) {
		this.searchCount = searchCount;
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
	public String getIsToday() {
		return isToday;
	}
	public void setIsToday(String isToday) {
		this.isToday = isToday;
	}
	public int[] getChk() {
		return chk;
	}
	public void setChk(int[] chk) {
		this.chk = chk;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
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
	public String getSchPrice() {
		return schPrice;
	}
	public void setSchPrice(String schPrice) {
		this.schPrice = schPrice;
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
	public String getStrCategorySub() {
		return strCategorySub;
	}
	public void setStrCategorySub(String strCategorySub) {
		this.strCategorySub = strCategorySub;
	}
	
	public String getUnlink() {
		return unlink;
	}
	public void setUnlink(String unlink) {
		this.unlink = unlink;
	}
	public int[] getVnum() {
		return vnum;
	}
	public void setVnum(int[] vnum) {
		this.vnum = vnum;
	}
	
	public String getSchRegdt() {
		return schRegdt;
	}
	public void setSchRegdt(String schRegdt) {
		this.schRegdt = schRegdt;
	}
	
	
	public List<GdGoodsGoodsoptionGoodslink> getLinkList() {
		return linkList;
	}
	public void setLinkList(List<GdGoodsGoodsoptionGoodslink> linkList) {
		this.linkList = linkList;
	}
	@Override
	public String toString() {
		return "LinkVO [indicate=" + indicate + ", skey=" + skey + ", sword="
				+ sword + ", open=" + open + ", isToday=" + isToday + ", chk="
				+ Arrays.toString(chk) + ", mode=" + mode + ", searchCount="
				+ searchCount + ", vnum=" + Arrays.toString(vnum) + ", cate="
				+ Arrays.toString(cate) + ", strCategory=" + strCategory
				+ ", unlink=" + unlink + ", strCategorySub=" + strCategorySub
				+ ", schCategory=" + schCategory + ", schWord=" + schWord
				+ ", schOpen=" + schOpen + ", schPrice=" + schPrice
				+ ", schEtc=" + schEtc + ", schRegdt=" + schRegdt
				+ ", groupBy=" + groupBy + ", orderBy=" + orderBy
				+ ", joinTable=" + joinTable + ", addColumn=" + addColumn
				+ ", linkList=" + linkList + "]";
	}
	
	
	
	
}
