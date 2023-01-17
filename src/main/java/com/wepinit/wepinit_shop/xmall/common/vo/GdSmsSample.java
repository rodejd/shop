package com.wepinit.wepinit_shop.xmall.common.vo;


public class GdSmsSample {
	
	private long sno;
	private String subject;
	private String msg;
	private String category;
	private int sort;
	
	@Override
	public String toString() {
		return "GdSmsSample [sno=" + sno + ", subject=" + subject + ", msg="
				+ msg + ", category=" + category + ", sort=" + sort + "]";
	}
	public long getSno() {
		return sno;
	}
	public void setSno(long sno) {
		this.sno = sno;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
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

	
}
