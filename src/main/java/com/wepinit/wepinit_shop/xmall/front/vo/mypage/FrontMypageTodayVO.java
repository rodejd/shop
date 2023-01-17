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
package com.wepinit.wepinit_shop.xmall.front.vo.mypage;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdGoodsGoodsoptionGoodslink;

import java.util.List;
import java.util.Map;

public class FrontMypageTodayVO extends PageMaker {
	
	private int mno;
	private String imgPath = ConfigClass.value("FRONT_GOODS_IMG_PATH");
	private String goodsno;					//쿠키에서 가져온 goodsno 
	private String sort;
	
	private Map<String, String> goodsMap;
	private List<GdGoodsGoodsoptionGoodslink> todayGoodsList;

	
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}
	
	public Map<String, String> getGoodsMap() {
		return goodsMap;
	}
	public void setGoodsMap(Map<String, String> goodsMap) {
		this.goodsMap = goodsMap;
	}
	public List<GdGoodsGoodsoptionGoodslink> getTodayGoodsList() {
		return todayGoodsList;
	}
	public void setTodayGoodsList(List<GdGoodsGoodsoptionGoodslink> todayGoodsList) {
		this.todayGoodsList = todayGoodsList;
	}
	
}
