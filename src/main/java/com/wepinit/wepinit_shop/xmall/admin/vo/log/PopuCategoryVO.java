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
import com.wepinit.wepinit_shop.xmall.common.vo.PopuCategoryOutVO;

import java.util.List;

public class PopuCategoryVO extends PageMaker {
	// input
	private String category;
	private String schWhere;
	
	private String year;
	private String month;

	// output
	private String ret;	
	private List<PopuCategoryOutVO> popuCategoryList = null;
	
	
	@Override
	public String toString() {
		return "PopuCategoryVO [category=" + category + ", schWhere="
				+ schWhere + ", year=" + year + ", month=" + month + ", ret="
				+ ret + ", popuCategoryList=" + popuCategoryList + "]";
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSchWhere() {
		return schWhere;
	}
	public void setSchWhere(String schWhere) {
		this.schWhere = schWhere;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getRet() {
		return ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}
	public List<PopuCategoryOutVO> getPopuCategoryList() {
		return popuCategoryList;
	}
	public void setPopuCategoryList(List<PopuCategoryOutVO> popuCategoryList) {
		this.popuCategoryList = popuCategoryList;
	}

	

}
