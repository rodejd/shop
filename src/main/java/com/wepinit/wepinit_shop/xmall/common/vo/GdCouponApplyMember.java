package com.wepinit.wepinit_shop.xmall.common.vo;

import java.util.Date;

public class GdCouponApplyMember {
	
	// gd_coupon_applymember
	private int mno = 0;
	private int applysno = 0;
	private String status = "";
	private String used = "";
	
	// gd_member
	private String mid = "";
	private String name = "";
	
	// gd_coupon_order
	private Date regdt = null;

	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getApplysno() {
		return applysno;
	}
	public void setApplysno(int applysno) {
		this.applysno = applysno;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	
}
