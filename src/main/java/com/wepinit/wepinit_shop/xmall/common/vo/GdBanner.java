package com.wepinit.wepinit_shop.xmall.common.vo;

import java.util.Date;

public class GdBanner {
	
	private int sno;
	private String skin;
	private String loccd;
	private String title;
	private String img;
	private String imgMobile;
	private String linkaddr;
	private String target;
	private String copy1;
	private String copy2;
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
	public String getLoccd() {
		return loccd;
	}
	public void setLoccd(String loccd) {
		this.loccd = loccd;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getImgMobile() {
		return imgMobile;
	}
	public void setImgMobile(String imgMobile) {
		this.imgMobile = imgMobile;
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
	public String getCopy1() {
		return copy1;
	}
	public void setCopy1(String copy1) {
		this.copy1 = copy1;
	}
	public String getCopy2() {
		return copy2;
	}
	public void setCopy2(String copy2) {
		this.copy2 = copy2;
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
				+ ", loccd=" + loccd
				+ ", title=" + title 
				+ ", img=" + img 
				+ ", imgMobile=" + imgMobile
				+ ", linkaddr=" + linkaddr 
				+ ", target=" + target 
				+ ", copy1=" + copy1 
				+ ", copy2=" + copy2 
				+ ", sort=" + sort  
				+ ", used=" + used
				+ ", regdt=" + regdt + "]";
	}

}
