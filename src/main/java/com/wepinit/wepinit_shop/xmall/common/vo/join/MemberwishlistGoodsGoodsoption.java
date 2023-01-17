package com.wepinit.wepinit_shop.xmall.common.vo.join;

import java.util.Date;

/*
 * gd_member_wishlist
 * gd_goods
 * gd_goods_option
 * gd_goods_link
 * JOIN
 */
public class MemberwishlistGoodsGoodsoption {
	
	//gd_member_wishlist
	private int sno;
	private int mno;
	private int goodsno;
	private String opt1;
	private String opt2;
	private String addopt;
	private Date regdt;
	
	//gd_goods
	private String goodsnm;
	private String goodsnmKR;
	private String goodsnmCN;
	private String imgl;
	private String imgs;
	private String vipYn;
	
	//gd_goods_option
	private int price;
	private int priceRate;
	private int consumer;
	private int stock;
	
	//gd_goods_link
	private String category;
	
	private String brandnm;
	private String rdt;

	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
	}
	public String getOpt1() {
		return opt1;
	}
	public void setOpt1(String opt1) {
		this.opt1 = opt1;
	}
	public String getOpt2() {
		return opt2;
	}
	public void setOpt2(String opt2) {
		this.opt2 = opt2;
	}
	public String getAddopt() {
		return addopt;
	}
	public void setAddopt(String addopt) {
		this.addopt = addopt;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public String getGoodsnm() {
		return goodsnm;
	}
	public void setGoodsnm(String goodsnm) {
		this.goodsnm = goodsnm;
	}
	public String getGoodsnmKR() {
		return goodsnmKR;
	}
	public void setGoodsnmKR(String goodsnmKR) {
		this.goodsnmKR = goodsnmKR;
	}
	public String getGoodsnmCN() {
		return goodsnmCN;
	}
	public void setGoodsnmCN(String goodsnmCN) {
		this.goodsnmCN = goodsnmCN;
	}
	public String getImgl() {
		return imgl;
	}
	public void setImgl(String imgl) {
		this.imgl = imgl;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public String getVipYn() {
		return vipYn;
	}
	public void setVipYn(String vipYn) {
		this.vipYn = vipYn;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPriceRate() {
		return priceRate;
	}
	public void setPriceRate(int priceRate) {
		this.priceRate = priceRate;
	}
	public int getConsumer() {
		return consumer;
	}
	public void setConsumer(int consumer) {
		this.consumer = consumer;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBrandnm() {
		return brandnm;
	}
	public void setBrandnm(String brandnm) {
		this.brandnm = brandnm;
	}
	public String getRdt() {
		return rdt;
	}
	public void setRdt(String rdt) {
		this.rdt = rdt;
	}

}
