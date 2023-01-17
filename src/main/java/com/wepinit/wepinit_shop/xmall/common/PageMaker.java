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
package com.wepinit.wepinit_shop.xmall.common;

import com.wepinit.wepinit_shop.common.ConfigClass;

import java.util.HashMap;


/**
 * @author pmgk32
 *
 */
public class PageMaker {
	
	public int pageGroupSize = ConfigClass.PAGE_GROUP_SIZE;		// 페이지 그룹 사이즈
	public int pageNo = 1;										// 페이지 번호
	public int pageSize = ConfigClass.PAGE_SIZE;				// 페이지 목록 사이즈
	public int rowCount;										// 총건수
	public int pageStart = 0;									// 시작페이지 번호
	public int pageEnd = 0;										// 끝 페이지 번호
	public String popView = "";									// 팝업View 여부
	
	public String skin = ""; // 스킨
	public String schSkin = ""; //스킨 검색
	HashMap<String, String> languageList = null;

	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		int pageCount = 0;
		
		this.rowCount = rowCount;
		
		pageCount = (int)Math.ceil((double)rowCount/this.pageSize);
		
		if(0 < pageCount && this.pageNo > pageCount){
			this.pageNo = pageCount;
		}
		
		this.pageStart 	= (this.pageNo -1) * this.pageSize;
		this.pageEnd 	= this.pageStart + this.pageSize;
	}
	public int getPageGroupSize() {
		return pageGroupSize;
	}
	public void setPageGroupSize(int pageGroupSize) {
		this.pageGroupSize = pageGroupSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		if ( 0 >= pageNo) {
			pageNo = 1;
		}
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getRowStart() {
		return (this.pageNo -1) * this.pageSize;
	}
	public int getPageStart() {
		return pageStart;
	}
	public int getPageEnd() {
		return pageEnd;
	}
	public String getPopView() {
		return popView;
	}
	public void setPopView(String popView) {
		this.popView = popView;
	}
	public String getSkin() {
		return skin;
	}
	public void setSkin(String skin) {
		this.skin = skin;
	}
	public String getSchSkin() {
		return schSkin;
	}
	public void setSchSkin(String schSkin) {
		this.schSkin = schSkin;
	}
	public HashMap<String, String> getLanguageList() {
		return languageList;
	}
	public void setLanguageList(HashMap<String, String> languageList) {
		this.languageList = languageList;
	}
}
