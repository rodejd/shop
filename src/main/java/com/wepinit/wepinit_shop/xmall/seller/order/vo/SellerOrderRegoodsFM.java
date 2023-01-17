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
package com.wepinit.wepinit_shop.xmall.seller.order.vo;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SellerOrderRegoodsFM extends PageMaker {
	
	private int totalCount;
	private int sno;
	private long ordno;
	private String mode;
	private String[] chk;
	
	private String ssSellerCd;				//판매사 세션의 판매사 코드
	
	private List<Map<String, Object>> regoodsList;
//	private List<Map<String, Object>> regoodsInfoList;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public long getOrdno() {
		return ordno;
	}
	public void setOrdno(long ordno) {
		this.ordno = ordno;
	}
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	
	public List<Map<String, Object>> getRegoodsList() {
		return regoodsList;
	}
	public void setRegoodsList(List<Map<String, Object>> regoodsList) {
		this.regoodsList = regoodsList;
	}
	public String getSsSellerCd() {
		return ssSellerCd;
	}
	public void setSsSellerCd(String ssSellerCd) {
		this.ssSellerCd = ssSellerCd;
	}
	@Override
	public String toString() {
		return "SellerOrderRegoodsVO [totalCount=" + totalCount + ", sno="
				+ sno + ", ordno=" + ordno + ", mode=" + mode + ", chk="
				+ Arrays.toString(chk) + ", ssSellerCd=" + ssSellerCd
				+ ", regoodsList=" + regoodsList + "]";
	}

}
