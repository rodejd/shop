package com.wepinit.wepinit_shop.xmall.common.vo;

import java.util.List;

public class GdCategory {
	
	private int sno = 0;
	private String catnm = "";
	private String catnmKR = "";
	private String catnmCN = "";
	private String category = "";
	private int sort = 0;
	private int hidden = 0;
	private int klevel = 0;
	private String useimg = "";
	private String boldflag = "";
	private String catMemo = "";
	
	private String folder = "folder";
	private String id = "";
	private String catenm = "";
	private String cateLength = "";
	private String bestYn = "";
	private int goodsCnt = 0;
	
	private List<GdCategory> categoryList = null;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getCatnm() {
		return catnm;
	}
	public void setCatnm(String catnm) {
		this.catnm = catnm;
	}
	public String getCatnmKR() {
		return catnmKR;
	}
	public void setCatnmKR(String catnmKR) {
		this.catnmKR = catnmKR;
	}
	public String getCatnmCN() {
		return catnmCN;
	}
	public void setCatnmCN(String catnmCN) {
		this.catnmCN = catnmCN;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getHidden() {
		return hidden;
	}
	public void setHidden(int hidden) {
		this.hidden = hidden;
	}
	public int getKlevel() {
		return klevel;
	}
	public void setKlevel(int klevel) {
		this.klevel = klevel;
	}
	public String getUseimg() {
		return useimg;
	}
	public void setUseimg(String useimg) {
		this.useimg = useimg;
	}
	public String getBoldflag() {
		return boldflag;
	}
	public void setBoldflag(String boldflag) {
		this.boldflag = boldflag;
	}
	public String getCatMemo() {
		return catMemo;
	}
	public void setCatMemo(String catMemo) {
		this.catMemo = catMemo;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	
	public String getId() {
		return category;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCatenm() {
		return catnm;
	}
	public void setCatenm(String catenm) {
		this.catenm = catenm;
	}
	public String getCateLength() {
		return cateLength;
	}
	public void setCateLength(String cateLength) {
		this.cateLength = cateLength;
	}
	public String getBestYn() {
		return bestYn;
	}
	public void setBestYn(String bestYn) {
		this.bestYn = bestYn;
	}
	public int getGoodsCnt() {
		return goodsCnt;
	}
	public void setGoodsCnt(int goodsCnt) {
		this.goodsCnt = goodsCnt;
	}
	
	public List<GdCategory> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<GdCategory> categoryList) {
		this.categoryList = categoryList;
	}
	
}
