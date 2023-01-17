/*******************************************************************
                     PMGroupKorea Co., Ltd.
Copyright PMGroupKorea Co., Ltd. 2005. All rights reserved.
No part of this work covered by the copyright hereon may be reproduced,
stored in a retrieval system, in any form or by any means, electronic,
mechanical, photocopying, recording or otherwise, without the prior
written permission of PMGroupKorea Co., Ltd.
SOLUTION    :   XMaLL4 for Spring
FILE_NAME   :
DATE        :   2018.1.29
AUTHOR      :   PMGK S/W Engineer   <contact@gmail.com>
DESCRIPTION :
FUNCTIONS   :
HISTORY     :
REMARKS     :
******************************************************************/
package com.wepinit.wepinit_shop.xmall.admin.vo.basic;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdAdminMenu;

import java.util.List;

public class AdminMenuVO extends PageMaker {
	//input
	private int menukey;
	private int parentkey;
	private String menuname;
	private String link;
	private int menulevel;
	private int menunum;
	private String usemenu;
	
	private String newmenuname;
	private String newlink;
	//output
	private List<GdAdminMenu> adminMenuList = null;
	private GdAdminMenu adminMenuObj = null;
	
	
	public String getUsemenu() {
		return usemenu;
	}
	public void setUsemenu(String usemenu) {
		this.usemenu = usemenu;
	}
	public String getNewmenuname() {
		return newmenuname;
	}
	public void setNewmenuname(String newmenuname) {
		this.newmenuname = newmenuname;
	}
	public String getNewlink() {
		return newlink;
	}
	public void setNewlink(String newlink) {
		this.newlink = newlink;
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
	public void setMenunum(int menunum) {
		this.menunum = menunum;
	}
	public List<GdAdminMenu> getAdminMenuList() {
		return adminMenuList;
	}
	public void setAdminMenuList(List<GdAdminMenu> adminMenuList) {
		this.adminMenuList = adminMenuList;
	}
	public GdAdminMenu getAdminMenuObj() {
		return adminMenuObj;
	}
	public void setAdminMenuObj(GdAdminMenu adminMenuObj) {
		this.adminMenuObj = adminMenuObj;
	}
	@Override
	public String toString() {
		return "AdminMenuVO [menukey=" + menukey + ", parentkey=" + parentkey
				+ ", menuname=" + menuname + ", link=" + link + ", menulevel="
				+ menulevel + ", menunum=" + menunum + ", newmenuname="
				+ newmenuname + ", newlink=" + newlink + ", adminMenuList="
				+ adminMenuList + ", adminMenuObj=" + adminMenuObj + "]";
	}
}
