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
package com.wepinit.wepinit_shop.xmall.admin.vo.event;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdEvent;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class EventVO extends PageMaker {

	// input
	private String mode;
	
	private int sno = 0;	
	private String sellercode = "";
	private String sellername = "";
	private String subject;
	private String body;
	private String sdate;
	private String edate;
	private String tpl = "";
	private String size;
	private String page_num;
	private int cols;
	private String category;
	private String r_category;
	private String r_brand;
	private String wview;
	private String win;
	private String attach;
	private String old_attach;
	private MultipartFile attach_file;
	private GdEvent eventObj = null;
	private int open;
	private String approvalstatus = "";
	private String memo = "";
	
	private String sellerYn = ""; //seller만 조회되는 조건
	private String schSellerCd = ""; //sellerCd 조회
	private String schSellerNm = ""; //sellerNm 조회
	private String schSort = ""; //정렬


	private String myritzYn = ""; //seller만 조회되는 조건
	private String myritzCd;
	private String myritzNm;

	private String schMyritzCd = ""; //sellerCd 조회
	private String schMyritzNm = ""; //sellerNm 조회
	
	
	public String getMyritzYn() {
		return myritzYn;
	}
	public void setMyritzYn(String myritzYn) {
		this.myritzYn = myritzYn;
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
	
	// output
	
	public String getSchMyritzCd() {
		return schMyritzCd;
	}
	public void setSchMyritzCd(String schMyritzCd) {
		this.schMyritzCd = schMyritzCd;
	}
	public String getSchMyritzNm() {
		return schMyritzNm;
	}
	public void setSchMyritzNm(String schMyritzNm) {
		this.schMyritzNm = schMyritzNm;
	}
	public MultipartFile getAttach_file() {
		return attach_file;
	}

	public void setAttach_file(MultipartFile attach_file) {
		this.attach_file = attach_file;
	}

	
	private List<GdEvent> eventList = null;
	
	
	@Override
	public String toString() {
		return "EventVO [mode=" + mode + ", sno=" + sno + ", sellercode=" + sellercode + ", sellername=" + sellername
				+ ", subject=" + subject + ", body=" + body + ", sdate=" + sdate
				+ ", edate=" + edate + ", tpl=" + tpl + ", size=" + size
				+ ", page_num=" + page_num + ", cols=" + cols + ", category=" + category
				+ ", r_category=" + r_category + ", r_brand=" + r_brand
				+ ", wview=" + wview + ", win=" + win + ", attach=" + attach
				+ ", old_attach=" + old_attach + ", eventObj=" + eventObj
				+ ", open=" + open + ", approvalstatus=" + approvalstatus  + ", memo=" + memo
				+ ", eventList=" + eventList + "]";
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(Object tmp) {
		this.sno = tmp == null || "".equals(tmp) ? 0 : Integer.valueOf(String.valueOf(tmp));
	}

	public String getSellercode() {
		return sellercode;
	}

	public void setSellercode(String sellercode) {
		this.sellercode = sellercode;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getPage_num() {
		return page_num;
	}

	public void setPage_num(String page_num) {
		this.page_num = page_num;
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

	public String getR_category() {
		return r_category;
	}

	public void setR_category(String r_category) {
		this.r_category = r_category;
	}

	public String getR_brand() {
		return r_brand;
	}

	public void setR_brand(String r_brand) {
		this.r_brand = r_brand;
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

	public String getOld_attach() {
		return old_attach;
	}

	public void setOld_attach(String old_attach) {
		this.old_attach = old_attach;
	}

	public GdEvent getEventObj() {
		return eventObj;
	}

	public void setEventObj(GdEvent eventObj) {
		this.eventObj = eventObj;
	}
	
	public int getOpen() {
		return open;
	}
	
	public void setOpen(int open) {
		this.open = open;
	}

	public String getApprovalstatus() {
		return approvalstatus;
	}

	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<GdEvent> getEventList() {
		return eventList;
	}

	public void setEventList(List<GdEvent> eventList) {
		this.eventList = eventList;
	}

	public String getSellerYn() {
		return sellerYn;
	}

	public void setSellerYn(String sellerYn) {
		this.sellerYn = sellerYn;
	}

	public String getSchSellerCd() {
		return schSellerCd;
	}

	public void setSchSellerCd(String schSellerCd) {
		this.schSellerCd = schSellerCd;
	}

	public String getSchSellerNm() {
		return schSellerNm;
	}

	public void setSchSellerNm(String schSellerNm) {
		this.schSellerNm = schSellerNm;
	}

	public String getSchSort() {
		return schSort;
	}

	public void setSchSort(String schSort) {
		this.schSort = schSort;
	}

}
