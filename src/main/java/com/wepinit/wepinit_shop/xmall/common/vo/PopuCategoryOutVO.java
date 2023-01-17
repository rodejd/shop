package com.wepinit.wepinit_shop.xmall.common.vo;


public class PopuCategoryOutVO {
	private int len;
	private String category;
	private String catnm;
	private int cnt;
	private long ea;
	
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCatnm() {
		return catnm;
	}
	public void setCatnm(String catnm) {
		this.catnm = catnm;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public long getEa() {
		return ea;
	}
	public void setEa(long ea) {
		this.ea = ea;
	}
	@Override
	public String toString() {
		return "PopuCategoryOutVO [len=" + len + ", category=" + category
				+ ", catnm=" + catnm + ", cnt=" + cnt + ", ea=" + ea + "]";
	}	
	
	
	
	
}
