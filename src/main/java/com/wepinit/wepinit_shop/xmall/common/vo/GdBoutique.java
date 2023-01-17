package com.wepinit.wepinit_shop.xmall.common.vo;

import java.util.Date;

public class GdBoutique {
	
	private int sno;
	private String skin;
	private String title;
	private String content;
	private String img;
	private String imgm;
	private String linkaddr;
	private String target;
	private int sort;
	private String used;
	private Date regdt;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getSkin() {
		return skin;
	}
	public void setSkin(String skin) {
		this.skin = skin;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getImgm() {
		return imgm;
	}
	public void setImgm(String imgm) {
		this.imgm = imgm;
	}
	public String getLinkaddr() {
		return linkaddr;
	}
	public void setLinkaddr(String linkaddr) {
		this.linkaddr = linkaddr;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	
	@Override
	public String toString() {
		return "GdBanner [sno=" + sno 
				+ ", skin=" + skin 
				+ ", title=" + title 
				+ ", content=" + content
				+ ", img=" + img 
				+ ", imgm=" + imgm
				+ ", linkaddr=" + linkaddr 
				+ ", target=" + target 
				+ ", sort=" + sort  
				+ ", used=" + used
				+ ", regdt=" + regdt + "]";
	}

}
