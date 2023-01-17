package com.wepinit.wepinit_shop.xmall.common.vo;

import java.util.Date;


public class GdMemberQna {
	private int sno = 0;
	private String itemcd = "";
	private int parent = 0;
	private String subject = "";
	private String contents = "";
	private int mno = 0;
	private String email = "";
	private String mobile = "";
	private String mailling = "";
	private String sms = "";
	private long ordno = 0;
	private Date regdt = null;
	private String ip = "";
	private Date maildt = null;
	private String name = "";
	private String password = "";
	private Date smsdt = null;
	
	
	private int recnt;
	
	
	public int getRecnt() {
		return recnt;
	}
	public void setRecnt(int recnt) {
		this.recnt = recnt;
	}
	private int asno=0;
	
	public int getAsno() {
		return asno;
	}
	public void setAsno(int asno) {
		this.asno = asno;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getItemcd() {
		return itemcd;
	}
	public void setItemcd(String itemcd) {
		this.itemcd = itemcd;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
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
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMailling() {
		return mailling;
	}
	public void setMailling(String mailling) {
		this.mailling = mailling;
	}
	public String getSms() {
		return sms;
	}
	public void setSms(String sms) {
		this.sms = sms;
	}
	public long getOrdno() {
		return ordno;
	}
	public void setOrdno(long ordno) {
		this.ordno = ordno;
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
	public Date getMaildt() {
		return maildt;
	}
	public void setMaildt(Date maildt) {
		this.maildt = maildt;
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
	public Date getSmsdt() {
		return smsdt;
	}
	public void setSmsdt(Date smsdt) {
		this.smsdt = smsdt;
	}
	
	
}
