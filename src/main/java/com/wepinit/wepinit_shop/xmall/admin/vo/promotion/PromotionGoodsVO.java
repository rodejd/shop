package com.wepinit.wepinit_shop.xmall.admin.vo.promotion;

import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsBrand;

import java.util.List;

public class PromotionGoodsVO extends PromotionVO {
	private int pgno;
	private int grno;
	private String goodscd;
	private int sort;
	
	private String[] cate;
	private String schCate;
	private String schSellerNm;
	private String schSellerCd;
	private String schBrand;
	private String schSoldOut;
	private String schOpen;
	private String schSeasonNm;
	private String schEuYn;
	private String schKey;
	
	private List<GdGoodsBrand> goodsBrandList = null;
	private List<String> goodsSeasonList = null;
	
	public int getPgno() {
		return pgno;
	}
	public void setPgno(int pgno) {
		this.pgno = pgno;
	}
	public int getGrno() {
		return grno;
	}
	public void setGrno(int grno) {
		this.grno = grno;
	}
	public String getGoodscd() {
		return goodscd;
	}
	public void setGoodscd(String goodscd) {
		this.goodscd = goodscd;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String[] getCate() {
		return cate;
	}
	public void setCate(String[] cate) {
		this.cate = cate;
	}
	public String getSchCate() {
		return schCate;
	}
	public void setSchCate(String schCate) {
		this.schCate = schCate;
	}
	public String getSchSellerNm() {
		return schSellerNm;
	}
	public void setSchSellerNm(String schSellerNm) {
		this.schSellerNm = schSellerNm;
	}
	public String getSchSellerCd() {
		return schSellerCd;
	}
	public void setSchSellerCd(String schSellerCd) {
		this.schSellerCd = schSellerCd;
	}
	public String getSchBrand() {
		return schBrand;
	}
	public void setSchBrand(String schBrand) {
		this.schBrand = schBrand;
	}
	public String getSchSoldOut() {
		return schSoldOut;
	}
	public void setSchSoldOut(String schSoldOut) {
		this.schSoldOut = schSoldOut;
	}
	public String getSchOpen() {
		return schOpen;
	}
	public void setSchOpen(String schOpen) {
		this.schOpen = schOpen;
	}
	public String getSchSeasonNm() {
		return schSeasonNm;
	}
	public void setSchSeasonNm(String schSeasonNm) {
		this.schSeasonNm = schSeasonNm;
	}
	public String getSchEuYn() {
		return schEuYn;
	}
	public void setSchEuYn(String schEuYn) {
		this.schEuYn = schEuYn;
	}
	public String getSchKey() {
		return schKey;
	}
	public void setSchKey(String schKey) {
		this.schKey = schKey;
	}
	public List<GdGoodsBrand> getGoodsBrandList() {
		return goodsBrandList;
	}
	public void setGoodsBrandList(List<GdGoodsBrand> goodsBrandList) {
		this.goodsBrandList = goodsBrandList;
	}
	public List<String> getGoodsSeasonList() {
		return goodsSeasonList;
	}
	public void setGoodsSeasonList(List<String> goodsSeasonList) {
		this.goodsSeasonList = goodsSeasonList;
	}
}
