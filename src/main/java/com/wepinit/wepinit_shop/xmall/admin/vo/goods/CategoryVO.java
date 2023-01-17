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

import com.wepinit.wepinit_shop.xmall.common.vo.GdCategory;
import com.wepinit.wepinit_shop.xmall.common.vo.XmCategoryBrandInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public class CategoryVO {
	
	private int sno;
	private String catnm;
	private String catnmKR;
	private String catnmCN;
	private String category;
	private int sort;
	private int hidden=0;
	private int kLevel;
	private String useimg;
	private String boldFlag;
	private String categoryNm;
	private String catMemo;
	
	private int goodsCnt; 
	private String mode;
	private String focus;			//???
	private int catelength;			//카테고리 length
	private String sub;				//하위분류(영문)
	private String subKR;			//하위분류(국문)
	private String subCN;			//하위분류(중문)
	private String subMemo;			//하위분류 연관 키워드
	private String infoyn;			//상품 리스트 레이아웃 INSERT OR UPDAET FLAG
	private String[] erefer;			//hitList goodsno 배열
	private String chkdesign; 	//하위분류 동일적용 체크유무
	private MultipartFile attachFile0;		//분류 이미지
	private MultipartFile attachFile1;		//마우스오버 이미지
	private String attach0;
	private String attach1;
	private String chkimg0="";					//삭제 분류 이미지 체크유무
	private String chkimg1="";					//삭제 마우스 이미지 체크유무
	private String oldattach0="";
	private String oldattach1="";

	//xm_category_brand_info
	private int brandno=0;
	private String rtpl;
	private String rpagenum;
	private String rcols;
	private String body;
	private int rsize;
	private String pagenum;
	private String cols;
	private String tpl;
	private int size;
	
	//gd_goods_display
	private int goodsno;
	
	//sort
	private String[] arrSno;
	private String[] arrSort;
	
	private String[] categoryInfo;
//	private String[] categoryImg;
	
	private List<GdCategory> categoryList = null;
	private GdCategory categoryObj = null;				//카테고리 리스트
	private List<Map<String, Object>> hitList = null;		//추천상품 정보
	private XmCategoryBrandInfo categoryInfoObj = null;			//카테고리 정보
	private Map<String, Object> categoryMax = null;
	
	public List<GdCategory> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<GdCategory> categoryList) {
		this.categoryList = categoryList;
	}
	public List<Map<String, Object>> getHitList() {
		return hitList;
	}
	public void setHitList(List<Map<String, Object>> hitList) {
		this.hitList = hitList;
	}
	public GdCategory getCategoryObj() {
		return categoryObj;
	}
	public void setCategoryObj(GdCategory categoryObj) {
		this.categoryObj = categoryObj;
	}
	public XmCategoryBrandInfo getCategoryInfoObj() {
		return categoryInfoObj;
	}
	public void setCategoryInfoObj(XmCategoryBrandInfo categoryInfoObj) {
		this.categoryInfoObj = categoryInfoObj;
	}
	public Map<String, Object> getCategoryMax() {
		return categoryMax;
	}
	public void setCategoryMax(Map<String, Object> categoryMax) {
		this.categoryMax = categoryMax;
	}
	
	public String[] getCategoryInfo() {
		return categoryInfo;
	}
	public void setCategoryInfo(String[] categoryInfo) {
		this.categoryInfo = categoryInfo;
	}
	
	public int getGoodsCnt() {
		return goodsCnt;
	}
	public void setGoodsCnt(int goodsCnt) {
		this.goodsCnt = goodsCnt;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getFocus() {
		return focus;
	}
	public void setFocus(String focus) {
		this.focus = focus;
	}
	public int getCatelength() {
		if(category != null){
			catelength = category.length();
		} else {
			catelength = 0;
		}
		return catelength;
	}
	public void setCatelength(int catelength) {
		this.catelength = catelength;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getSubKR() {
		return subKR;
	}
	public void setSubKR(String subKR) {
		this.subKR = subKR;
	}
	public String getSubCN() {
		return subCN;
	}
	public void setSubCN(String subCN) {
		this.subCN = subCN;
	}
	public String getSubMemo() {
		return subMemo;
	}
	public void setSubMemo(String subMemo) {
		this.subMemo = subMemo;
	}
	public String getInfoyn() {
		return infoyn;
	}
	public void setInfoyn(String infoyn) {
		this.infoyn = infoyn;
	}
	public String[] getErefer() {
		return erefer;
	}
	public void setErefer(String[] erefer) {
		this.erefer = erefer;
	}
	public String getChkdesign() {
		return chkdesign;
	}
	public void setChkdesign(String chkdesign) {
		this.chkdesign = chkdesign;
	}
	
	public MultipartFile getAttachFile0() {
		return attachFile0;
	}
	public void setAttachFile0(MultipartFile attachFile0) {
		this.attachFile0 = attachFile0;
	}
	public MultipartFile getAttachFile1() {
		return attachFile1;
	}
	public void setAttachFile1(MultipartFile attachFile1) {
		this.attachFile1 = attachFile1;
	}
	public String getAttach0() {
		return attach0;
	}
	public void setAttach0(String attach0) {
		this.attach0 = attach0;
	}
	public String getAttach1() {
		return attach1;
	}
	public void setAttach1(String attach1) {
		this.attach1 = attach1;
	}
	public String getChkimg0() {
		return chkimg0;
	}
	public void setChkimg0(String chkimg0) {
		this.chkimg0 = chkimg0;
	}
	public String getChkimg1() {
		return chkimg1;
	}
	public void setChkimg1(String chkimg1) {
		this.chkimg1 = chkimg1;
	}
	public String getOldattach0() {
		return oldattach0;
	}
	public void setOldattach0(String oldattach0) {
		this.oldattach0 = oldattach0;
	}
	public String getOldattach1() {
		return oldattach1;
	}
	public void setOldattach1(String oldattach1) {
		this.oldattach1 = oldattach1;
	}
	
	public int getBrandno() {
		return brandno;
	}
	public void setBrandno(int brandno) {
		this.brandno = brandno;
	}
	public String getRtpl() {
		return rtpl;
	}
	public void setRtpl(String rtpl) {
		this.rtpl = rtpl;
	}
	public String getRpagenum() {
		return rpagenum;
	}
	public void setRpagenum(String rpagenum) {
		this.rpagenum = rpagenum;
	}
	public String getRcols() {
		return rcols;
	}
	public void setRcols(String rcols) {
		this.rcols = rcols;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getRsize() {
		return rsize;
	}
	public void setRsize(int rsize) {
		this.rsize = rsize;
	}
	public String getPagenum() {
		return pagenum;
	}
	public void setPagenum(String pagenum) {
		this.pagenum = pagenum;
	}
	public String getCols() {
		return cols;
	}
	public void setCols(String cols) {
		this.cols = cols;
	}
	public String getTpl() {
		return tpl;
	}
	public void setTpl(String tpl) {
		this.tpl = tpl;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
	}
	public String[] getArrSno() {
		return arrSno;
	}
	public void setArrSno(String[] arrSno) {
		this.arrSno = arrSno;
	}
	public String[] getArrSort() {
		return arrSort;
	}
	public void setArrSort(String[] arrSort) {
		this.arrSort = arrSort;
	}
	public String getCategoryNm() {
		return categoryNm;
	}
	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getCatnm() {
		return catnm;
	}
	public void setCatnm(String catnm) {
		this.catnm = catnm;
	}
	public String getCategory() {
		if(category == null){
			category = "";
		}
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getHidden() {
		return hidden;
	}
	public void setHidden(int hidden) {
		this.hidden = hidden;
	}
	public String getUseimg() {
		return useimg;
	}
	public void setUseimg(String useimg) {
		this.useimg = useimg;
	}
	public int getkLevel() {
		return kLevel;
	}
	public void setkLevel(int kLevel) {
		this.kLevel = kLevel;
	}
	public String getBoldFlag() {
		return boldFlag;
	}
	public void setBoldFlag(String boldFlag) {
		this.boldFlag = boldFlag;
	}
	public String getCatnmKR() {
		return catnmKR;
	}
	public void setCatnmKR(String catnmKR) {
		this.catnmKR = catnmKR;
	}
	public String getCatnmCN() {
		return catnmCN;
	}
	public void setCatnmCN(String catnmCN) {
		this.catnmCN = catnmCN;
	}
	public String getCatMemo() {
		return catMemo;
	}
	public void setCatMemo(String catMemo) {
		this.catMemo = catMemo;
	}
	@Override
	public String toString() {
		return "CategoryVO [sno=" + sno + ", catnm=" + catnm + ", category="
				+ category + ", sort=" + sort + ", hidden=" + hidden
				+ ", kLevel=" + kLevel
				+ ", useimg=" + useimg + ", boldFlag=" + boldFlag
				+ ", categoryNm=" + categoryNm + "]";
	}

}
