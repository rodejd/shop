package com.wepinit.wepinit_shop.xmall.common.vo.join;

import com.wepinit.wepinit_shop.xmall.common.vo.GdGoods;



public class IndexWeekSoldGoods extends GdGoods{
	
	private int goodsno = 0;
	private int cnt = 0;
	
	@Override
	public int getGoodsno() {
		return goodsno;
	}
	@Override
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
}
