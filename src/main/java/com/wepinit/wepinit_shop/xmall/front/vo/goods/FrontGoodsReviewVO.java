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
* 업무구분명			:	상품 상세
* 세부업무구분명	: 	사용자 > 상품 상세 > 상품별 상품 Review
* 작성자 				: 	이병환
* 설명 				: 	사용자 상품별 상품 Review VO
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170217		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.vo.goods;

import com.wepinit.wepinit_shop.xmall.common.CommonVO;
import com.wepinit.wepinit_shop.xmall.common.vo.join.MemberGoodsrevwGoods;

import java.util.List;


public class FrontGoodsReviewVO extends CommonVO{
	
	/******************************************
	 * input 
	 ******************************************/
	private String goodsno;									// 상품번호
	private String mode;									// mode(등록:add_review, 수정:mod_review , 삭제:del_review)
	private String contents;
	private String point;
	private String sno;
	private String ordno;									// 주문번호
	
	private Integer fno = 0;
	private String img;
	private String regid;
	private String[] fnoArr;
	private String sort = "regdt";
	private int mno = 0;
	
	
	/******************************************
	 * output 
	 ******************************************/
	private List<MemberGoodsrevwGoods> goodsReviewList;		//상품평 목록

	
	public String getGoodsno() {
		return goodsno;
	}

	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}

	
	public String getOrdno() {
		return ordno;
	}

	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}

	public List<MemberGoodsrevwGoods> getGoodsReviewList() {
		return goodsReviewList;
	}

	public void setGoodsReviewList(List<MemberGoodsrevwGoods> goodsReviewList) {
		this.goodsReviewList = goodsReviewList;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}
	public int getFno() {
		return fno;
	}

	public void setFno(int fno) {
		this.fno = fno;
	}

	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String[] getFnoArr() {
		return fnoArr;
	}
	public void setFnoArr(String[] fnoArr) {
		this.fnoArr = fnoArr;
	}
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

	@Override
	public String toString() {
		return "FrontGoodsReviewVO [goodsno=" + goodsno 
				+ ", mode=" + mode
				+ ", contents=" + contents 
				+ ", point=" + point 
				+ ", sno=" + sno 
				+ ", sort=" + sort 
				+ ", goodsReviewList=" + goodsReviewList + "]";
	}
	
	
		
}