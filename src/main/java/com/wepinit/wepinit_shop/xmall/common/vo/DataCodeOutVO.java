package com.wepinit.wepinit_shop.xmall.common.vo;


public class DataCodeOutVO {
	private String sno;
	private String groupcd;
	private String itemcd;
	private String itemnm;
	private int sort;
	private int uprow;
	private int downrow;
	private int lastsort;
	
	
	
	@Override
	public String toString() {
		return "DataCodeOutVO [sno=" + sno + ", groupcd=" + groupcd
				+ ", itemcd=" + itemcd + ", itemnm=" + itemnm + ", sort="
				+ sort + ", uprow=" + uprow + ", downrow=" + downrow
				+ ", lastsort=" + lastsort + "]";
	}
	
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getGroupcd() {
		return groupcd;
	}
	public void setGroupcd(String groupcd) {
		this.groupcd = groupcd;
	}
	public String getItemcd() {
		return itemcd;
	}
	public void setItemcd(String itemcd) {
		this.itemcd = itemcd;
	}
	public String getItemnm() {
		return itemnm;
	}
	public void setItemnm(String itemnm) {
		this.itemnm = itemnm;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getUprow() {
		return uprow;
	}
	public void setUprow(int uprow) {
		this.uprow = uprow;
	}
	public int getDownrow() {
		return downrow;
	}
	public void setDownrow(int downrow) {
		this.downrow = downrow;
	}
	public int getLastsort() {
		return lastsort;
	}
	public void setLastsort(int lastsort) {
		this.lastsort = lastsort;
	}
	
	
	
	
}
