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
package com.wepinit.wepinit_shop.xmall.front.vo.main;

import com.wepinit.wepinit_shop.xmall.common.CommonVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdBanner;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPopUp;
import com.wepinit.wepinit_shop.xmall.common.vo.GdPromotion;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdGoodsGoodsoptionGoodslink;

import java.util.ArrayList;
import java.util.List;


public class FrontMainVO extends CommonVO{

	//input
	String poptype = "";	//팝업 type

	//output
	List<GdGoodsGoodsoptionGoodslink> hotGoods1List		= new ArrayList<GdGoodsGoodsoptionGoodslink>();		//Today, HOT100 목록
	List<GdGoodsGoodsoptionGoodslink> hotGoods2List		= new ArrayList<GdGoodsGoodsoptionGoodslink>();		//Today, HOT100 목록
	List<GdGoodsGoodsoptionGoodslink> hotGoods3List		= new ArrayList<GdGoodsGoodsoptionGoodslink>();		//Today, HOT100 목록
	List<GdGoodsGoodsoptionGoodslink> newGoodsList		= new ArrayList<GdGoodsGoodsoptionGoodslink>();		//What's New 목록
	List<GdGoodsGoodsoptionGoodslink> saleGoodsList		= new ArrayList<GdGoodsGoodsoptionGoodslink>();		//Sale this week 목록
	
	GdPopUp winPopList	= new GdPopUp();		//유효한 팝업목록 조회
	GdPopUp fixPopList	= new GdPopUp();		//유효한 팝업목록 조회
	GdPopUp movePopList	= new GdPopUp();		//유효한 팝업목록 조회
	List<GdBanner> slideBannerList	= new ArrayList<GdBanner>();		//메인 슬라이드 배너
	List<GdBanner> bottomBannerList	= new ArrayList<GdBanner>();		//메인 하단 배너
	
	GdPromotion promotion1 = new GdPromotion();	//메인 프로모션1
	GdPromotion promotion2 = new GdPromotion();	//메인 프로모션2
	GdPromotion promotion3 = new GdPromotion();	//메인 프로모션3
	GdPromotion promotion4 = new GdPromotion();	//메인 프로모션4
	
	String imgPath = "";	//이미지 경로
	
	public String getPoptype() {
		return poptype;
	}
	public void setPoptype(String poptype) {
		this.poptype = poptype;
	}
	public List<GdGoodsGoodsoptionGoodslink> getHotGoods1List() {
		return hotGoods1List;
	}
	public void setHotGoods1List(List<GdGoodsGoodsoptionGoodslink> hotGoods1List) {
		this.hotGoods1List = hotGoods1List;
	}
	public List<GdGoodsGoodsoptionGoodslink> getHotGoods2List() {
		return hotGoods2List;
	}
	public void setHotGoods2List(List<GdGoodsGoodsoptionGoodslink> hotGoods2List) {
		this.hotGoods2List = hotGoods2List;
	}
	public List<GdGoodsGoodsoptionGoodslink> getHotGoods3List() {
		return hotGoods3List;
	}
	public void setHotGoods3List(List<GdGoodsGoodsoptionGoodslink> hotGoods3List) {
		this.hotGoods3List = hotGoods3List;
	}
	public List<GdGoodsGoodsoptionGoodslink> getNewGoodsList() {
		return newGoodsList;
	}
	public void setNewGoodsList(List<GdGoodsGoodsoptionGoodslink> newGoodsList) {
		this.newGoodsList = newGoodsList;
	}
	public List<GdGoodsGoodsoptionGoodslink> getSaleGoodsList() {
		return saleGoodsList;
	}
	public void setSaleGoodsList(List<GdGoodsGoodsoptionGoodslink> saleGoodsList) {
		this.saleGoodsList = saleGoodsList;
	}
	public GdPopUp getWinPopList() {
		return winPopList;
	}
	public void setWinPopList(GdPopUp winPopList) {
		this.winPopList = winPopList;
	}
	public GdPopUp getFixPopList() {
		return fixPopList;
	}
	public void setFixPopList(GdPopUp fixPopList) {
		this.fixPopList = fixPopList;
	}
	public GdPopUp getMovePopList() {
		return movePopList;
	}
	public void setMovePopList(GdPopUp movePopList) {
		this.movePopList = movePopList;
	}
	public List<GdBanner> getSlideBannerList() {
		return slideBannerList;
	}
	public void setSlideBannerList(List<GdBanner> slideBannerList) {
		this.slideBannerList = slideBannerList;
	}
	public List<GdBanner> getBottomBannerList() {
		return bottomBannerList;
	}
	public void setBottomBannerList(List<GdBanner> bottomBannerList) {
		this.bottomBannerList = bottomBannerList;
	}
	public GdPromotion getPromotion1() {
		return promotion1;
	}
	public void setPromotion1(GdPromotion promotion1) {
		this.promotion1 = promotion1;
	}
	public GdPromotion getPromotion2() {
		return promotion2;
	}
	public void setPromotion2(GdPromotion promotion2) {
		this.promotion2 = promotion2;
	}
	public GdPromotion getPromotion3() {
		return promotion3;
	}
	public void setPromotion3(GdPromotion promotion3) {
		this.promotion3 = promotion3;
	}
	public GdPromotion getPromotion4() {
		return promotion4;
	}
	public void setPromotion4(GdPromotion promotion4) {
		this.promotion4 = promotion4;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	@Override
	public String toString() {
		return "FrontMainVO [poptype=" + poptype 
				+ ", hotGoods1List=" + hotGoods1List
				+ ", hotGoods2List=" + hotGoods2List
				+ ", hotGoods3List=" + hotGoods3List
				+ ", newGoodsList=" + newGoodsList 
				+ ", saleGoodsList=" + saleGoodsList 
				+ ", winPopList=" + winPopList 
				+ ", fixPopList=" + fixPopList 
				+ ", movePopList=" + movePopList 
				+ ", slideBannerList=" + slideBannerList
				+ ", bottomBannerList=" + bottomBannerList
				+ ", promotion1=" + promotion1
				+ ", promotion2=" + promotion2
				+ ", promotion3=" + promotion3
				+ ", promotion4=" + promotion4
				+ ", imgPath=" + imgPath + "]";
	}
}
