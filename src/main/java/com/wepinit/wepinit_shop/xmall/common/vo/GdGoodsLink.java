package com.wepinit.wepinit_shop.xmall.common.vo;

public class GdGoodsLink {

	private int sno;
	private int goodsno;
	private String category;
	private int sort;
	private int hidden;
	
	private int clen;
	
	
	/**
	 * @return the clen
	 */
	public int getClen() {
		return clen;
	}
	/**
	 * @param clen the clen to set
	 */
	public void setClen(int clen) {
		this.clen = clen;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getHidden() {
		return hidden;
	}
	public void setHidden(int hidden) {
		this.hidden = hidden;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GdGoodsLink [sno=" + sno + ", goodsno=" + goodsno
				+ ", category=" + category + ", sort=" + sort + ", hidden="
				+ hidden + ", clen=" + clen + "]";
	}
}
