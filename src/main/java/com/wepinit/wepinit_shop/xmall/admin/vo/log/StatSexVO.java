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
import com.wepinit.wepinit_shop.xmall.common.vo.StatSexOutVO;

import java.util.List;

public class StatSexVO extends PageMaker {

	// input
	private String year;
	private String month;

	// output
	private int last;
	private List<StatSexOutVO> statSexList = null;
	
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
	public int getLast() {
		return last;
	}
	public void setLast(int last) {
		this.last = last;
	}
	public List<StatSexOutVO> getStatSexList() {
		return statSexList;
	}
	public void setStatSexList(List<StatSexOutVO> statSexList) {
		this.statSexList = statSexList;
	}
	@Override
	public String toString() {
		return "StatSexVO [year=" + year + ", month=" + month + ", last="
				+ last + ", statSexList=" + statSexList + "]";
	}


}
