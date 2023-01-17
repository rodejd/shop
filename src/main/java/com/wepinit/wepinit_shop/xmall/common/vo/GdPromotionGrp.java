package com.wepinit.wepinit_shop.xmall.common.vo;

import java.util.ArrayList;
import java.util.List;

public class GdPromotionGrp {
	private int grno;
	private int pmno;
	private String grnm;
	private int sort;
	private String useYn;
	
	private List<GdPromotionGoods> goodsList = new ArrayList<GdPromotionGoods>();
	
	public int getGrno() {
		return grno;
	}
	public void setGrno(int grno) {
		this.grno = grno;
	}
	public int getPmno() {
		return pmno;
	}
	public void setPmno(int pmno) {
		this.pmno = pmno;
	}
	public String getGrnm() {
		return grnm;
	}
	public void setGrnm(String grnm) {
		this.grnm = grnm;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public List<GdPromotionGoods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<GdPromotionGoods> goodsList) {
		this.goodsList = goodsList;
	}
}
