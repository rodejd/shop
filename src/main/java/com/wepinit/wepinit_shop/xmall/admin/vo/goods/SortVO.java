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

import java.util.List;
import java.util.Map;

public class SortVO {
	
	private String category;
	private String[] cate;
	private int limitCnt;
	private int totalCnt;
	
	private List<Map<String, Object>> goodsList;

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String[] getCate() {
		return cate;
	}
	public void setCate(String[] cate) {
		this.cate = cate;
	}
	public int getLimitCnt() {
		if(limitCnt > totalCnt){
			return totalCnt; 
		} else {
			return limitCnt;	
		}
	}
	public void setLimitCnt(int limitCnt) {
		this.limitCnt = limitCnt;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	
	public List<Map<String, Object>> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<Map<String, Object>> goodsList) {
		this.goodsList = goodsList;
	}

}
