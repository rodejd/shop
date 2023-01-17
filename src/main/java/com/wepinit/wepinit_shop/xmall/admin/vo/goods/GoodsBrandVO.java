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

import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsBrand;

import java.util.List;

public class GoodsBrandVO {
	
	private int sno;
	private String brandnm;
	private int sort;
	
	private List<GdGoodsBrand> goodsBrandList = null;
	private GdGoodsBrand goodsBrandObj = null;
	
	public List<GdGoodsBrand> getGoodsBrandList() {
		return goodsBrandList;
	}
	public void setGoodsBrandList(List<GdGoodsBrand> goodsBrandList) {
		this.goodsBrandList = goodsBrandList;
	}
	public GdGoodsBrand getGoodsBrandObj() {
		return goodsBrandObj;
	}
	public void setGoodsBrandObj(GdGoodsBrand goodsBrandObj) {
		this.goodsBrandObj = goodsBrandObj;
	}
	
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
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "GoodsBrandVO [sno=" + sno + ", brandnm=" + brandnm + ", sort="
				+ sort + ", goodsBrandList=" + goodsBrandList
				+ ", goodsBrandObj=" + goodsBrandObj + "]";
	}

}
