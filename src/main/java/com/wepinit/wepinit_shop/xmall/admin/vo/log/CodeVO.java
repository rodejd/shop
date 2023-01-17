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
package com.wepinit.wepinit_shop.xmall.admin.vo.log;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.DataCodeOutVO;

import java.util.List;

public class CodeVO extends PageMaker {

	// input
	private String groupcd;
	private String mode;
	private String code;
	private int sno;
	private int sort;
	
	private String itemcd;
	private String itemnm;
	private String nolist;
	private String allmodify;
	
	// output
	private List<DataCodeOutVO> dataCodeList = null;
	
	@Override
	public String toString() {
		return "DataCodeVO [groupcd=" + groupcd + ", mode=" + mode + ", code="
				+ code + ", sno=" + sno + ", sort=" + sort + ", itemcd="
				+ itemcd + ", itemnm=" + itemnm + ", nolist=" + nolist
				+ ", allmodify=" + allmodify + ", dataCodeList=" + dataCodeList
				+ "]";
	}

	public String getGroupcd() {
		return groupcd;
	}

	public void setGroupcd(String groupcd) {
		this.groupcd = groupcd;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getItemcd() {
		return itemcd;
	}

	public void setItemcd(String itemcd) {
		this.itemcd = itemcd;
	}

	public String getItemnm() {
		return itemnm;
	}

	public void setItemnm(String itemnm) {
		this.itemnm = itemnm;
	}

	public String getNolist() {
		return nolist;
	}

	public void setNolist(String nolist) {
		this.nolist = nolist;
	}

	public String getAllmodify() {
		return allmodify;
	}

	public void setAllmodify(String allmodify) {
		this.allmodify = allmodify;
	}

	public List<DataCodeOutVO> getDataCodeList() {
		return dataCodeList;
	}

	public void setDataCodeList(List<DataCodeOutVO> dataCodeList) {
		this.dataCodeList = dataCodeList;
	}


}
