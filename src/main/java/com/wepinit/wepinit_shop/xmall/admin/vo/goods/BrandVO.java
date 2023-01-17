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

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsBrand;
import com.wepinit.wepinit_shop.xmall.common.vo.XmCategoryBrandInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public class BrandVO extends PageMaker{
	
	private String skey;
	private String sword;
	private String sBrandnm;
	private String sApprovalstatus;
	private String sSellerNm;
	
	private int sno;
	private String brandnm ="";
	private String brandnmKR ="";
	private String brandnmCN ="";
	private int sort;
	private String sellerCd;
	private String approvalStatus;
	private String sellerNm;
	private String brandMemo;
	
	private int brandno=0;
	private String infoyn;
	private int brandCnt;
	private String focus;		//?
	private String sub;			//하위브랜드(영문)
	private String subKR;		//하위브랜드(국문)
	private String subCN;		//하위브랜드(중문)
	private String mode;
	
	//xm_category_brand_info
	private String category;
	private String rtpl;
	private String rpagenum;
	private int rcols;
	private String body;
	private int rsize;
	private String pagenum;
	private String cols;
	private String tpl;
	private int size;
	
	private String oldImgPC;
	private String oldImgMO;
	private String imgPC;
	private String imgMO;
	private MultipartFile brandImgPC;
	private MultipartFile brandImgMO;
	
	private String bestYn;
	
	private List<GdGoodsBrand> brandList;
	private GdGoodsBrand brandObj;
	private XmCategoryBrandInfo categoryInfoObj;
	private Map<String, Object> categoryMax;
	public List<BrandVO> sellerBrandList;
	public List<BrandVO> myritzBrandList;
	
	private String[] schRegdt;					//상품등록일 배열
	private String schRegdtS = "";				//검색 시작일
	private String schRegdtE = "";				//검색 종료일
	private String schApprovalStatus;
	private String schBrandMemo;
	private String schBrandnm;

	private String myritzCd;
	private String myritzNm;
	
	public List<BrandVO> getMyritzBrandList() {
		return myritzBrandList;
	}
	public void setMyritzBrandList(List<BrandVO> myritzBrandList) {
		this.myritzBrandList = myritzBrandList;
	}
	public String getMyritzCd() {
		return myritzCd;
	}
	public void setMyritzCd(String myritzCd) {
		this.myritzCd = myritzCd;
	}
	public String getMyritzNm() {
		return myritzNm;
	}
	public void setMyritzNm(String myritzNm) {
		this.myritzNm = myritzNm;
	}
	
	public List<BrandVO> getSellerBrandList() {
		return sellerBrandList;
	}
	public void setSellerBrandList(List<BrandVO> sellerBrandList) {
		this.sellerBrandList = sellerBrandList;
	}
	public List<GdGoodsBrand> getBrandList() {
		return brandList;
	}
	public void setBrandList(List<GdGoodsBrand> brandList) {
		this.brandList = brandList;
	}
	public GdGoodsBrand getBrandObj() {
		return brandObj;
	}
	public void setBrandObj(GdGoodsBrand brandObj) {
		this.brandObj = brandObj;
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
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getBrandnm() {
		return brandnm;
	}
	public String getBrandnmKR() {
		return brandnmKR;
	}
	public void setBrandnmKR(String brandnmKR) {
		this.brandnmKR = brandnmKR;
	}
	public String getBrandnmCN() {
		return brandnmCN;
	}
	public void setBrandnmCN(String brandnmCN) {
		this.brandnmCN = brandnmCN;
	}
	public void setBrandnm(String brandnm) {
		this.brandnm = brandnm;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getBrandno() {
		return brandno;
	}
	public void setBrandno(int brandno) {
		this.brandno = brandno;
	}
	public String getInfoyn() {
		if(infoyn == null){
			infoyn ="N";
		}
		return infoyn;
	}
	public void setInfoyn(String infoyn) {
		this.infoyn = infoyn;
	}
	public int getBrandCnt() {
		return brandCnt;
	}
	public void setBrandCnt(int brandCnt) {
		this.brandCnt = brandCnt;
	}
	public String getFocus() {
		return focus;
	}
	public void setFocus(String focus) {
		this.focus = focus;
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
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public String getCategory() {
		if(category == null){
			category="";
		}
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public int getRcols() {
		return rcols;
	}
	public void setRcols(int rcols) {
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
	
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
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
	public String getsBrandnm() {
		return sBrandnm;
	}
	public void setsBrandnm(String sBrandnm) {
		this.sBrandnm = sBrandnm;
	}
	public String getsApprovalstatus() {
		return sApprovalstatus;
	}
	public void setsApprovalstatus(String sApprovalstatus) {
		this.sApprovalstatus = sApprovalstatus;
	}
	public String getSellerNm() {
		return sellerNm;
	}
	public void setSellerNm(String sellernm) {
		this.sellerNm = sellernm;
	}
	public String getsSellerNm() {
		return sSellerNm;
	}
	public void setsSellerNm(String sSellerNm) {
		this.sSellerNm = sSellerNm;
	}
	public String getBrandMemo() {
		return brandMemo;
	}
	public void setBrandMemo(String brandMemo) {
		this.brandMemo = brandMemo;
	}
	public String getOldImgPC() {
		return oldImgPC;
	}
	public void setOldImgPC(String oldImgPC) {
		this.oldImgPC = oldImgPC;
	}
	public String getOldImgMO() {
		return oldImgMO;
	}
	public void setOldImgMO(String oldImgMO) {
		this.oldImgMO = oldImgMO;
	}
	public String getImgPC() {
		return imgPC;
	}
	public void setImgPC(String imgPC) {
		this.imgPC = imgPC;
	}
	public String getImgMO() {
		return imgMO;
	}
	public void setImgMO(String imgMO) {
		this.imgMO = imgMO;
	}
	public MultipartFile getBrandImgPC() {
		return brandImgPC;
	}
	public void setBrandImgPC(MultipartFile brandImgPC) {
		this.brandImgPC = brandImgPC;
	}
	public MultipartFile getBrandImgMO() {
		return brandImgMO;
	}
	public void setBrandImgMO(MultipartFile brandImgMO) {
		this.brandImgMO = brandImgMO;
	}
	public String getBestYn() {
		return bestYn;
	}
	public void setBestYn(String bestYn) {
		this.bestYn = bestYn;
	}
	public String getSchApprovalStatus() {
		return schApprovalStatus;
	}
	public void setSchApprovalStatus(String schApprovalStatus) {
		this.schApprovalStatus = schApprovalStatus;
	}
	public String getSchBrandMemo() {
		return schBrandMemo;
	}
	public void setSchBrandMemo(String schBrandMemo) {
		this.schBrandMemo = schBrandMemo;
	}
	public String getSchBrandnm() {
		return schBrandnm;
	}
	public void setSchBrandnm(String schBrandnm) {
		this.schBrandnm = schBrandnm;
	}
	public String[] getSchRegdt() {
		return schRegdt;
	}
	public void setSchRegdt(String[] schRegdt) {
		this.schRegdt = schRegdt;
	}
	public String getSchRegdtS() {
		if (schRegdt != null) {
			if (schRegdt.length > 0) {
				return schRegdt[0];
			}
			return schRegdtS;
		}
		return schRegdtS;
	}
	public void setSchRegdtS(String schRegdtS) {
		this.schRegdtS = schRegdtS;
	}
	public String getSchRegdtE() {
		if (schRegdt != null) {
			if (schRegdt.length > 1) {
				return schRegdt[1];
			}
			return schRegdtE;
		}
		return schRegdtE;
	}
	public void setSchRegdtE(String schRegdtE) {
		this.schRegdtE = schRegdtE;
	}
}
