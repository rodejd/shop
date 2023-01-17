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

import com.wepinit.wepinit_shop.xmall.common.CommonVO;

public class FrontGoodsBrandVO extends CommonVO {
	
	private int sno;
	private String brandnm;
	private String brandnmKR;
	private String brandnmCN;
	private int sort;
	private String sellerCd;
	private String approvalStatus;
	private String sellerNm;
	private String brandMemo;
	private String imgPC;
	private String imgMO;
	private String bestYn;
	
	private String sword;
	private String sbest;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getBrandnm() {
		return brandnm;
	}
	public void setBrandnm(String brandnm) {
		this.brandnm = brandnm;
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
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
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
	public String getSellerNm() {
		return sellerNm;
	}
	public void setSellerNm(String sellerNm) {
		this.sellerNm = sellerNm;
	}
	public String getBrandMemo() {
		return brandMemo;
	}
	public void setBrandMemo(String brandMemo) {
		this.brandMemo = brandMemo;
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
	public String getBestYn() {
		return bestYn;
	}
	public void setBestYn(String bestYn) {
		this.bestYn = bestYn;
	}
	public String getSword() {
		return sword;
	}
	public void setSword(String sword) {
		this.sword = sword;
	}	
	public String getSbest() {
		return sbest;
	}
	public void setSbest(String sbest) {
		this.sbest = sbest;
	}
	
	@Override
	public String toString() {
		return "FrontGoodsBrandVO [sno=" + sno 
				+ ", brandnm=" + brandnm 
				+ ", brandnmKR=" + brandnmKR
				+ ", brandnmCN=" + brandnmCN 
				+ ", sort=" + sort 
				+ ", sellerCd=" + sellerCd  
				+ ", sellerNm=" + sellerNm 
				+ ", approvalStatus=" + approvalStatus
				+ ", brandMemo=" + brandMemo
				+ ", imgPC=" + imgPC
				+ ", imgMO=" + imgMO
				+ ", bestYn=" + bestYn
				+ ", sword=" + sword
				+ ", sbest=" + sbest + "]";
	}
}
