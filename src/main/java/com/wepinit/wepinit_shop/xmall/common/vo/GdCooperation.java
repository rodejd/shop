package com.wepinit.wepinit_shop.xmall.common.vo;


import java.util.Date;


public class GdCooperation {
	private int sno = 0;
	private String itemcd = "";
	private String name = "";
	private String email = "";
	private String title = "";
	private String content = "";
	private String reply = "";
	private Date regdt = null;
	private Date replydt = null;
	private Date maildt = null;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public Date getReplydt() {
		return replydt;
	}
	public void setReplydt(Date replydt) {
		this.replydt = replydt;
	}
	public Date getMaildt() {
		return maildt;
	}
	public void setMaildt(Date maildt) {
		this.maildt = maildt;
	}
}
