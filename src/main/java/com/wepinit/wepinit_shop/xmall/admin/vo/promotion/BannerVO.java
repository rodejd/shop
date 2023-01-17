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
package com.wepinit.wepinit_shop.xmall.admin.vo.promotion;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdBanner;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class BannerVO extends PageMaker {
	//input
	private int sno = 0;
	private String loccd;
	private String title;
	private String img;
	private String imgMobile;
	private MultipartFile imgFile;
	private MultipartFile imgMobileFile;
	private String linkaddr;
	private String target;
	private String copy1;
	private String copy2;
	private int sort;
	private String used;
	private String regid;
	private String regdt;
	private String modid;
	private String moddt;
	
	//검색
	private String schLoccd = "";
	private String schKey = "";
	private String schWord = "";
	private String schUsed = "";
	private String schSort = "";
	private int totalCnt;
	private String mode = "";
	
	//output
	private List<GdBanner> bannerList = null;
	private GdBanner bannerObj = null;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
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
	public MultipartFile getImgFile() {
		return imgFile;
	}
	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}
	public MultipartFile getImgMobileFile() {
		return imgMobileFile;
	}
	public void setImgMobileFile(MultipartFile imgMobileFile) {
		this.imgMobileFile = imgMobileFile;
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
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	public String getModid() {
		return modid;
	}
	public void setModid(String modid) {
		this.modid = modid;
	}
	public String getModdt() {
		return moddt;
	}
	public void setModdt(String moddt) {
		this.moddt = moddt;
	}
	public String getSchLoccd() {
		return schLoccd;
	}
	public void setSchLoccd(String schLoccd) {
		this.schLoccd = schLoccd;
	}
	public String getSchKey() {
		return schKey;
	}
	public void setSchKey(String schKey) {
		this.schKey = schKey;
	}
	public String getSchWord() {
		return schWord;
	}
	public void setSchWord(String schWord) {
		this.schWord = schWord;
	}
	public String getSchUsed() {
		return schUsed;
	}
	public void setSchUsed(String schUsed) {
		this.schUsed = schUsed;
	}
	public String getSchSort() {
		return schSort;
	}
	public void setSchSort(String schSort) {
		this.schSort = schSort;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public List<GdBanner> getBannerList() {
		return bannerList;
	}
	public void setBannerList(List<GdBanner> bannerList) {
		this.bannerList = bannerList;
	}
	public GdBanner getBannerObj() {
		return bannerObj;
	}
	public void setBannerObj(GdBanner bannerObj) {
		this.bannerObj = bannerObj;
	}
}
