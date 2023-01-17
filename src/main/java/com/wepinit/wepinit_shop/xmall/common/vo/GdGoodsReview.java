package com.wepinit.wepinit_shop.xmall.common.vo;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

import java.util.Date;



public class GdGoodsReview extends PageMaker {
	
	private int sno = 0;
	private int goodsno = 0;
	private String ordno = "";
	private String subject = "";
	private String contents = "";
	private int point = 0;
	private int emoney = 0;
	private int mno = 0;
	private Date regdt = null;
	private String ip = "";
	private String name = "";
	private String password = "";
	private int parent = 0;
	
	private String apply = "";
	private String apply2 = "";
	
	public String getOrdno() {
		return ordno;
	}
	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}
	public String getApply() {
		return apply;
	}
	public void setApply(String apply) {
		this.apply = apply;
	}
	public String getApply2() {
		return apply2;
	}
	public void setApply2(String apply2) {
		this.apply2 = apply2;
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getEmoney() {
		return emoney;
	}
	public void setEmoney(int emoney) {
		this.emoney = emoney;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	@Override
	public String toString() {
		return "GdGoodsReview [sno=" + sno + ", goodsno=" + goodsno
				+ ", subject=" + subject + ", contents=" + contents
				+ ", point=" + point + ", emoney=" + emoney + ", mno=" + mno
				+ ", regdt=" + regdt + ", ip=" + ip + ", name=" + name
				+ ", password=" + password + ", parent=" + parent + ", apply="
				+ apply + ", apply2=" + apply2 + "]";
	}
}
