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
package com.wepinit.wepinit_shop.xmall.front.vo.goods;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCategory;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsBrand;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdGoodsGoodsoptionGoodslink;

import java.util.List;

public class FrontGoodsSearchVO extends PageMaker {
	
	public FrontGoodsSearchVO(){
		super.pageSize = 20;
	}
	
	private int mno;
	private int level;
	
	private String[] cate;
	private String category;
	private String brands;
	private String options;
	
	private String searched;
	private String log;
	private String sort = "priceRate desc";
	private String skey;
	private String sword;
	private String[] swords;
	private String price1;
	private String price2;
	private String order;
	
	private String searchlog;
	private int totalCount;
	private String imgPath = ConfigClass.value("FRONT_GOODS_IMG_PATH");
	
	private List<GdGoodsGoodsoptionGoodslink> goodsList;
	private List<GdCategory> frontGoodsGoodsCategoryList = null;
	private List<GdGoodsBrand> frontGoodsGoodsBrandList = null;
	
	public List<GdGoodsGoodsoptionGoodslink> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<GdGoodsGoodsoptionGoodslink> goodsList) {
		this.goodsList = goodsList;
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
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	public String[] getCate() {
		return cate;
	}
	public void setCate(String[] cate) {
		this.cate = cate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public String getSearched() {
		return searched;
	}
	public void setSearched(String searched) {
		this.searched = searched;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
	public String[] getSwords() {
		return swords;
	}
	public void setSwords(String[] swords) {
		this.swords = swords;
	}
	//	public String[] getPrice() {
//		return price;
//	}
//	public void setPrice(String[] price) {
//		this.price = price;
//	}
	public String getPrice1() {
//		if(price != null){
//			if(price.length > 0){
//				return price[0];
//			}
//			return price1;
//		}
		return price1;
	}
	public void setPrice1(String price1) {
		this.price1 = price1;
	}
	public String getPrice2() {
//		if(price != null){
//			if(price.length > 1){
//				return price[1];
//			}
//			return price2;
//		}
		return price2;
	}
	public void setPrice2(String price2) {
		this.price2 = price2;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	public String getSearchlog() {
		return searchlog;
	}
	public void setSearchlog(String searchlog) {
		this.searchlog = searchlog;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	

}
