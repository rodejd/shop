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

import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsAdd;

import java.util.List;

public class GoodsAddVO {
	
	private int sno;
	private int goodsno;
	private int step;
	private String opt;
	private int addprice;
	
	private List<GdGoodsAdd> goodsAddList = null;
	private GdGoodsAdd goodsAddObj = null;
	
	
	public List<GdGoodsAdd> getGoodsAddList() {
		return goodsAddList;
	}
	public void setGoodsAddList(List<GdGoodsAdd> goodsAddList) {
		this.goodsAddList = goodsAddList;
	}
	public GdGoodsAdd getGoodsAddObj() {
		return goodsAddObj;
	}
	public void setGoodsAddObj(GdGoodsAdd goodsAddObj) {
		this.goodsAddObj = goodsAddObj;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public int getAddprice() {
		return addprice;
	}
	public void setAddprice(int addprice) {
		this.addprice = addprice;
	}
	
	@Override
	public String toString() {
		return "GoodsAddVO [sno=" + sno + ", goodsno=" + goodsno + ", step="
				+ step + ", opt=" + opt + ", addprice=" + addprice + "]";
	}
}
