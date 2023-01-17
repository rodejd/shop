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

import com.wepinit.wepinit_shop.xmall.common.vo.join.GoodsdisplayGoodsGoodsoption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class DispMainVO {
		//GoodsdisplayGoodsGoodsoption
		private String mode;
		private String goodsno;
		private String goodsnm;
		private String goodsnmKR;
		private String goodsnmCN;
		private String imgs;
		private String price;
		//input
		private String[] title;
		private String[] img;
		private String[] page_num;
		private String[] cols;
		private String sort;
		
		//output
		private List<GoodsdisplayGoodsGoodsoption> dispMainList = null;
		private GoodsdisplayGoodsGoodsoption dispObj = null;
		private Map<String, ArrayList> arrMap=null; //진열 상품정보 재조합
		private List list=null; //메인페이지 상품진열 기본 데이터
		
		
		
		public String getSort() {
			return sort;
		}
		public void setSort(String sort) {
			this.sort = sort;
		}
		public String[] getTitle() {
			return title;
		}
		public void setTitle(String[] title) {
			this.title = title;
		}
		public String[] getImg() {
			return img;
		}
		public void setImg(String[] img) {
			this.img = img;
		}
		public String[] getPage_num() {
			return page_num;
		}
		public void setPage_num(String[] page_num) {
			this.page_num = page_num;
		}
		public String[] getCols() {
			return cols;
		}
		public void setCols(String[] cols) {
			this.cols = cols;
		}
		public List getList() {
			return list;
		}
		public void setList(List list) {
			this.list = list;
		}
		public Map<String, ArrayList> getArrMap() {
			return arrMap;
		}
		public void setArrMap(Map<String, ArrayList> arrMap) {
			this.arrMap = arrMap;
		}
		public String getMode() {
			return mode;
		}
		public void setMode(String mode) {
			this.mode = mode;
		}
		public String getGoodsno() {
			return goodsno;
		}
		public void setGoodsno(String goodsno) {
			this.goodsno = goodsno;
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
		public String getImgs() {
			return imgs;
		}
		public void setImgs(String imgs) {
			this.imgs = imgs;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		public List<GoodsdisplayGoodsGoodsoption> getDispMainList() {
			return dispMainList;
		}
		public void setDispMainList(List<GoodsdisplayGoodsGoodsoption> dispMainList) {
			this.dispMainList = dispMainList;
		}
		public GoodsdisplayGoodsGoodsoption getDispObj() {
			return dispObj;
		}
		public void setDispObj(GoodsdisplayGoodsGoodsoption dispObj) {
			this.dispObj = dispObj;
		}
		@Override
		public String toString() {
			return "DispMainVO [mode=" + mode + ", goodsno=" + goodsno
					+ ", goodsnm=" + goodsnm + ", imgs=" + imgs + ", price="
					+ price + ", title=" + Arrays.toString(title) + ", img="
					+ Arrays.toString(img) + ", page_num="
					+ Arrays.toString(page_num) + ", cols="
					+ Arrays.toString(cols) + ", sort=" + sort
					+ ", dispMainList=" + dispMainList + ", dispObj=" + dispObj
					+ ", arrMap=" + arrMap + ", list=" + list + "]";
		}

}
