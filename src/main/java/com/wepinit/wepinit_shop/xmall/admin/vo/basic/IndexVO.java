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
package com.wepinit.wepinit_shop.xmall.admin.vo.basic;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.IndexRecentGoodsReg;
import com.wepinit.wepinit_shop.xmall.common.vo.join.IndexRegularMember;
import com.wepinit.wepinit_shop.xmall.common.vo.join.IndexWeekSoldGoods;

import java.util.List;


public class IndexVO {
	
	private String birthDayYn = "N";
	private String[] arrState = StringUtil.split(ShopConfig.getProperty("adminMainState"), "^");
	private String[] arrTitle = new String[]{"매출액 (원)", "주문건수 (건)", "입금확인 (건)", "배송완료 (건)", "취소 / 환불 / 반품 (건)", "상품후기 (건)", "상품문의 (건)", "1:1문의 (건)", "회원가입 (명)"};

	private IndexSalepr indexSalepr = null;
	private IndexReview indexReview = null;
	private IndexQna indexQna = null;
	private IndexMqna indexMqna = null;
	private IndexMember indexMember = null;
	private List<GdMemberQna> indexMqnaList = null;
	private List<GdCooperation> indexCooperationList = null;
	private List<GdGoodsReview> indexGoodsReviewList = null;
	private List<IndexRecentGoodsReg> indexRecentGoodsRegList = null;
	private List<GdEvent> indexEventList = null;
	private List<IndexWeekSoldGoods> indexWeekSoldGoodsList = null;
	private List<IndexRegularMember> indexRegularMemberList = null;
	private int autoCancel;
	
	public String getBirthDayYn() {
		return birthDayYn;
	}
	public void setBirthDayYn(String birthDayYn) {
		this.birthDayYn = birthDayYn;
	}
	public String[] getArrState() {
		return arrState;
	}
	public void setArrState(String[] arrState) {
		this.arrState = arrState;
	}
	public String[] getArrTitle() {
		return arrTitle;
	}
	public void setArrTitle(String[] arrTitle) {
		this.arrTitle = arrTitle;
	}
	public IndexSalepr getIndexSalepr() {
		return indexSalepr;
	}
	public void setIndexSalepr(IndexSalepr indexSalepr) {
		this.indexSalepr = indexSalepr;
	}
	public IndexReview getIndexReview() {
		return indexReview;
	}
	public void setIndexReview(IndexReview indexReview) {
		this.indexReview = indexReview;
	}
	public IndexQna getIndexQna() {
		return indexQna;
	}
	public void setIndexQna(IndexQna indexQna) {
		this.indexQna = indexQna;
	}
	public IndexMqna getIndexMqna() {
		return indexMqna;
	}
	public void setIndexMqna(IndexMqna indexMqna) {
		this.indexMqna = indexMqna;
	}
	public IndexMember getIndexMember() {
		return indexMember;
	}
	public void setIndexMember(IndexMember indexMember) {
		this.indexMember = indexMember;
	}
	public List<GdMemberQna> getIndexMqnaList() {
		return indexMqnaList;
	}
	public void setIndexMqnaList(List<GdMemberQna> indexMqnaList) {
		this.indexMqnaList = indexMqnaList;
	}
	public List<GdCooperation> getIndexCooperationList() {
		return indexCooperationList;
	}
	public void setIndexCooperationList(List<GdCooperation> indexCooperationList) {
		this.indexCooperationList = indexCooperationList;
	}
	public List<GdGoodsReview> getIndexGoodsReviewList() {
		return indexGoodsReviewList;
	}
	public void setIndexGoodsReviewList(List<GdGoodsReview> indexGoodsReviewList) {
		this.indexGoodsReviewList = indexGoodsReviewList;
	}
	public List<IndexRecentGoodsReg> getIndexRecentGoodsRegList() {
		return indexRecentGoodsRegList;
	}
	public void setIndexRecentGoodsRegList(
			List<IndexRecentGoodsReg> indexRecentGoodsRegList) {
		this.indexRecentGoodsRegList = indexRecentGoodsRegList;
	}
	public List<GdEvent> getIndexEventList() {
		return indexEventList;
	}
	public void setIndexEventList(List<GdEvent> indexEventList) {
		this.indexEventList = indexEventList;
	}
	public List<IndexWeekSoldGoods> getIndexWeekSoldGoodsList() {
		return indexWeekSoldGoodsList;
	}
	public void setIndexWeekSoldGoodsList(
			List<IndexWeekSoldGoods> indexWeekSoldGoodsList) {
		this.indexWeekSoldGoodsList = indexWeekSoldGoodsList;
	}
	public List<IndexRegularMember> getIndexRegularMemberList() {
		return indexRegularMemberList;
	}
	public void setIndexRegularMemberList(
			List<IndexRegularMember> indexRegularMemberList) {
		this.indexRegularMemberList = indexRegularMemberList;
	}
	public int getAutoCancel() {
		return autoCancel;
	}
	public void setAutoCancel(int autoCancel) {
		this.autoCancel = autoCancel;
	}
}
