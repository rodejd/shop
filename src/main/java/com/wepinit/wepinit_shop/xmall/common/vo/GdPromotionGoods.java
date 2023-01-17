package com.wepinit.wepinit_shop.xmall.common.vo;

public class GdPromotionGoods extends GdGoods {
	private int pgno = 0;
	private int grno = 0;
	private String grnm = "";

	private int addoptFlag;
	private int likes;

	private int sort = 0;
	
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getPgno() {
		return pgno;
	}
	public void setPgno(int pgno) {
		this.pgno = pgno;
	}
	public int getGrno() {
		return grno;
	}
	public void setGrno(int grno) {
		this.grno = grno;
	}
	public String getGrnm() {
		return grnm;
	}
	public void setGrnm(String grnm) {
		this.grnm = grnm;
	}
	public int getAddoptFlag() {
		return addoptFlag;
	}
	public void setAddoptFlag(int addoptFlag) {
		this.addoptFlag = addoptFlag;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
}
