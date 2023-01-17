package com.wepinit.wepinit_shop.xmall.common.vo.join;

public class CodeSmssample {
	//gd_code
	private int sno;
	private String groupcd;
	private String itemcd;
	private String itemnm;
	private int sort;
	
	//gd_sms_sample
	private int cnt;
	
	@Override
	public String toString() {
		return "CodeSmssample [sno=" + sno + ", groupcd=" + groupcd
				+ ", itemcd=" + itemcd + ", itemnm=" + itemnm + ", sort="
				+ sort + ", cnt=" + cnt + "]";
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
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

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	

}
