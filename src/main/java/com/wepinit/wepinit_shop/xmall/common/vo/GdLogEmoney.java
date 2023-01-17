package com.wepinit.wepinit_shop.xmall.common.vo;

import java.util.Date;

public class GdLogEmoney {
	
	private int sno;
	private int mno;
	private String gbn;
	private String addemoney;
	private long ordno;
	private int emoney;
	private String memo;
	private Date regdt;
	private String excemoney;
	private String regdate;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public long getOrdno() {
		return ordno;
	}
	public void setOrdno(long ordno) {
		this.ordno = ordno;
	}
	public int getEmoney() {
		return emoney;
	}
	public void setEmoney(int emoney) {
		this.emoney = emoney;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public String getGbn() {
		return gbn;
	}
	public void setGbn(String gbn) {
		this.gbn = gbn;
	}
	public String getAddemoney() {
		return addemoney;
	}
	public void setAddemoney(String addemoney) {
		this.addemoney = addemoney;
	}
	public String getExcemoney() {
		return excemoney;
	}
	public void setExcemoney(String excemoney) {
		this.excemoney = excemoney;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

}
