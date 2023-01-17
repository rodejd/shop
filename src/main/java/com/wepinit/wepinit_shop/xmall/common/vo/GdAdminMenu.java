package com.wepinit.wepinit_shop.xmall.common.vo;



public class GdAdminMenu {
	
	private int menukey;
	private int parentkey;
	private String menuname;
	private String link;
	private int menulevel;
	private int menunum;
	private String parentmenuname;
	private String usemenu;		//2017-08-21 추가 메뉴 사용여부
	
	
	public String getUsemenu() {
		return usemenu;
	}
	public void setUsemenu(String usemenu) {
		this.usemenu = usemenu;
	}
	public int getMenukey() {
		return menukey;
	}
	public void setMenukey(int menukey) {
		this.menukey = menukey;
	}
	public int getParentkey() {
		return parentkey;
	}
	public void setParentkey(int parentkey) {
		this.parentkey = parentkey;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getMenulevel() {
		return menulevel;
	}
	public void setMenulevel(int menulevel) {
		this.menulevel = menulevel;
	}
	public int getMenunum() {
		return menunum;
	}
	public void setMenunum(int menuNum) {
		this.menunum = menuNum;
	}
	public String getParentmenuname() {
		return parentmenuname;
	}
	public void setParentmenuname(String parentmenuname) {
		this.parentmenuname = parentmenuname;
	}
	@Override
	public String toString() {
		return "GdAdminMenu [menukey=" + menukey + ", parentkey=" + parentkey
				+ ", menuname=" + menuname + ", link=" + link + ", menulevel="
				+ menulevel + ", menunum=" + menunum + ", parentmenuname="
				+ parentmenuname + "]";
	}
}
