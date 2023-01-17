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

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsQna;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMemberQna;
import com.wepinit.wepinit_shop.xmall.common.vo.join.MemberGoodsrevwGoods;
import com.wepinit.wepinit_shop.xmall.common.vo.join.MemberMemberqna;
import com.wepinit.wepinit_shop.xmall.common.vo.join.MemberwishlistGoodsGoodsoption;

import java.util.List;
import java.util.Map;

public class FrontMypageBoardVO extends PageMaker{
	
	private int mno;
	private int sno;
	private String mid;
	private String name;
	private String email;
	private String[] emailArr;
	private String cate;
	private String subSpeech;
	
	private String snoList;
	private String optionsList;
	
	private String mode;
	private String formtype;
	private String imgPath;
	
	private String ordno;
	private String[] mobileArr;
	private String mobile;
	private String subject;
	private String contents;
	private String mailling;
	private String sms;
	private String ip;
	private String itemcd;
	private int point;
	
	private String goodsno;
	private String goodsnm;		//상품명(영문)
	private String goodsnmKR;	//상품명(국문)
	private String goodsnmCN;	//상품명(중문)
	private String opt1;
	private String opt2;
	private String addopt;
	private String ea;
	
	private String sellerCd;
	private List<GdMemberQna> qnaList;					//1:1 문의 리스트
	private MemberMemberqna qnaObj;						//1:1 문의 정보
	private List<MemberGoodsrevwGoods> reviewList;		//나의 상품후기 리스트
	private List<GdGoodsQna> goodsQnaList;				//나의 상품문의 리스트
	private Map<String, Object> goodsInfoObj;			//나의 상품후기,문의 상품정보
	
	private List<FrontMypageBoardVO> orderGoodsList;  				//주문한 상품 리스트 
	private List<MemberwishlistGoodsGoodsoption> wishList;			//상품보관함
	
	
	
	public String getEa() {
		return ea;
	}
	public void setEa(String ea) {
		this.ea = ea;
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
	public List<FrontMypageBoardVO> getOrderGoodsList() {
		return orderGoodsList;
	}
	public void setOrderGoodsList(List<FrontMypageBoardVO> orderGoodsList) {
		this.orderGoodsList = orderGoodsList;
	}
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getSno() {
	 	return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String[] getEmailArr() {
		if(email != null){
			emailArr = StringUtil.explode(email, "@");
		}
		return emailArr;
	}
	public void setEmailArr(String[] emailArr) {
		this.emailArr = emailArr;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getSubSpeech() {
		return subSpeech;
	}
	public void setSubSpeech(String subSpeech) {
		this.subSpeech = subSpeech;
	}
	
	public String getSnoList() {
		return snoList;
	}
	public void setSnoList(String snoList) {
		this.snoList = snoList;
	}
	public String getOptionsList() {
		return optionsList;
	}
	public void setOptionsList(String optionsList) {
		this.optionsList = optionsList;
	}

	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getFormtype() {
		return formtype;
	}
	public void setFormtype(String formtype) {
		this.formtype = formtype;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	public String getOrdno() {
		return ordno;
	}
	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}
	public String[] getMobileArr() {
		return mobileArr;
	}
	public void setMobileArr(String[] mobileArr) {
		this.mobileArr = mobileArr;
	}
	public String getMobile() {
		if (mobileArr != null) {
			if (mobileArr.length > 0) {
				String rst = "";
				for (int i = 0; i < mobileArr.length; i++) {
					rst += mobileArr[i];
					if (i < mobileArr.length - 1)
						rst += "-";
				}
				return rst;
			}
			return mobile;
		}
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getMailling() {
		if(mailling != null){
			if("on".equals(mailling)){
				return "y";
			}
			return "n";
		}
		return "n";
	}
	public void setMailling(String mailling) {
		this.mailling = mailling;
	}
	public String getSms() {
		if(sms != null){
			if("on".equals(sms)){
				return "y";
			}
			return "n";
		}
		return "n";
	}
	public void setSms(String sms) {
		this.sms = sms;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getItemcd() {
		return itemcd;
	}
	public void setItemcd(String itemcd) {
		this.itemcd = itemcd;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}

	public String getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}
	public String getOpt1() {
		return opt1;
	}
	public void setOpt1(String opt1) {
		this.opt1 = opt1;
	}
	public String getOpt2() {
		return opt2;
	}
	public void setOpt2(String opt2) {
		this.opt2 = opt2;
	}
	public String getAddopt() {
		return addopt;
	}
	public void setAddopt(String addopt) {
		this.addopt = addopt;
	}
	
	public List<GdMemberQna> getQnaList() {
		return qnaList;
	}
	public void setQnaList(List<GdMemberQna> qnaList) {
		this.qnaList = qnaList;
	}
	public MemberMemberqna getQnaObj() {
		return qnaObj;
	}
	public void setQnaObj(MemberMemberqna qnaObj) {
		this.qnaObj = qnaObj;
	}
	public List<MemberGoodsrevwGoods> getReviewList() {
		return reviewList;
	}
	public void setReviewList(List<MemberGoodsrevwGoods> reviewList) {
		this.reviewList = reviewList;
	}
	public List<GdGoodsQna> getGoodsQnaList() {
		return goodsQnaList;
	}
	public void setGoodsQnaList(List<GdGoodsQna> goodsQnaList) {
		this.goodsQnaList = goodsQnaList;
	}
	public Map<String, Object> getGoodsInfoObj() {
		return goodsInfoObj;
	}
	public void setGoodsInfoObj(Map<String, Object> goodsInfoObj) {
		this.goodsInfoObj = goodsInfoObj;
	}
	public List<MemberwishlistGoodsGoodsoption> getWishList() {
		return wishList;
	}
	public void setWishList(List<MemberwishlistGoodsGoodsoption> wishList) {
		this.wishList = wishList;
	}

}
