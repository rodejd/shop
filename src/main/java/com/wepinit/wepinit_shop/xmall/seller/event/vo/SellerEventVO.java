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
package com.wepinit.wepinit_shop.xmall.seller.event.vo;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

public class SellerEventVO extends PageMaker {

	private int sno = 0;	
	private String sellerCd = "";
	private String sellerNm = "";
	private String subject = "";
	private String body = "";
	private String sdate = "";
	private String edate = "";
	private String tpl = "";
	private int size = 0;
	private String pageNum = "";
	private int cols = 0;
	private String category = "";
	private String rCategory = "";
	private String rBrand = "";
	private String wview = "";
	private String win = "";
	private String attach = "";
	private int open = 0;
	private int approvalStatus = 0;
	private String approvalStatusNm = "";
	private String memo = "";

	public int getSno() {
		return sno;
	}

	public void setSno(Object tmp) {
		this.sno = tmp == null || "".equals(tmp) ? 0 : Integer.valueOf(String.valueOf(tmp));
	}

	public String getSellerCd() {
		return sellerCd;
	}

	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}

	public String getSellerNm() {
		return sellerNm;
	}

	public void setSellerNm(String sellerNm) {
		this.sellerNm = sellerNm;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
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

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getrCategory() {
		return rCategory;
	}

	public void setrCategory(String rCategory) {
		this.rCategory = rCategory;
	}

	public String getrBrand() {
		return rBrand;
	}

	public void setrBrand(String rBrand) {
		this.rBrand = rBrand;
	}

	public String getWview() {
		return wview;
	}

	public void setWview(String wview) {
		this.wview = wview;
	}

	public String getWin() {
		return win;
	}

	public void setWin(String win) {
		this.win = win;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public int getOpen() {
		return open;
	}

	public void setOpen(int open) {
		this.open = open;
	}

	public int getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(int approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApprovalStatusNm() {
		return approvalStatusNm;
	}

	public void setApprovalStatusNm(String approvalStatusNm) {
		this.approvalStatusNm = approvalStatusNm;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

}
