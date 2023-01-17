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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class SellerEventFM extends PageMaker {
	
	private int totalCnt = 0;
	
	// 검색조건
	private String schSellerCd = "";
	private String schSellerNm = "";
	private String schSellerCno = "";
	private String schSellerYn = ""; //seller만 조회되는 조건
	private String schAprlStat = ""; //승인상태 조건
	private String schSno = "";
	private String schSort = "";
	
	private String procType = "";	// 처리구분(I/M/D)
	private String targetURL = "";	// 처리후 리턴 url
	
	private String mode = "";
	private String popView = "";
	
	//이벤트 정보
	private SellerEventVO eventVO;
	
	//이벤트 목록
	private List<SellerEventVO> eventList = null;
	
	//첨부파일
	private MultipartFile attachFile;
	
	private String oldAttach;

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

	public String getSchSellerCno() {
		return schSellerCno;
	}

	public void setSchSellerCno(String schSellerCno) {
		this.schSellerCno = schSellerCno;
	}

	public String getSchSellerYn() {
		return schSellerYn;
	}

	public void setSchSellerYn(String schSellerYn) {
		this.schSellerYn = schSellerYn;
	}

	public String getProcType() {
		return procType;
	}

	public void setProcType(String procType) {
		this.procType = procType;
	}

	public String getTargetURL() {
		return targetURL;
	}

	public void setTargetURL(String targetURL) {
		this.targetURL = targetURL;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getPopView() {
		return popView;
	}

	public void setPopView(String popView) {
		this.popView = popView;
	}

	public SellerEventVO getEventVO() {
		return eventVO;
	}

	public void setEventVO(SellerEventVO eventVO) {
		this.eventVO = eventVO;
	}

	public List<SellerEventVO> getEventList() {
		return eventList;
	}

	public void setEventList(List<SellerEventVO> eventList) {
		this.eventList = eventList;
	}

	public MultipartFile getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(MultipartFile attachFile) {
		this.attachFile = attachFile;
	}

	public String getOldAttach() {
		return oldAttach;
	}

	public void setOldAttach(String oldAttach) {
		this.oldAttach = oldAttach;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public String getSchAprlStat() {
		return schAprlStat;
	}

	public void setSchAprlStat(String schAprlStat) {
		this.schAprlStat = schAprlStat;
	}

	public String getSchSno() {
		return schSno;
	}

	public void setSchSno(String schSno) {
		this.schSno = schSno;
	}

	public String getSchSort() {
		return schSort;
	}

	public void setSchSort(String schSort) {
		this.schSort = schSort;
	}

}
