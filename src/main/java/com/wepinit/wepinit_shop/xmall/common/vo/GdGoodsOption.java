package com.wepinit.wepinit_shop.xmall.common.vo;

import java.util.Arrays;

public class GdGoodsOption {
	
	private int sno;
	private int goodsno;
	private String opt1;
	private String opt2;
	private int consumer;
	private int price;
	private int priceRate;
	private int supply;
	private int supply2;
	private int margin;
	private String stock;
	private int link;
	private String optno;
	private String parent ="";
	private String [] stocks ;
	private String optexplain;
	private int priceMyRitz;
	
	public int getPriceMyRitz() {
		return priceMyRitz;
	}
	public void setPriceMyRitz(int priceMyRitz) {
		this.priceMyRitz = priceMyRitz;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
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
	public int getConsumer() {
		return consumer;
	}
	public void setConsumer(int consumer) {
		this.consumer = consumer;
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
	public int getSupply() {
		return supply;
	}
	public void setSupply(int supply) {
		this.supply = supply;
	}
	public int getSupply2() {
		return supply2;
	}
	public void setSupply2(int supply2) {
		this.supply2 = supply2;
	}
	public int getMargin() {
		return margin;
	}
	public void setMargin(int margin) {
		this.margin = margin;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public int getLink() {
		return link;
	}
	public void setLink(int link) {
		this.link = link;
	}
	public String getOptno() {
		return optno;
	}
	public void setOptno(String optno) {
		this.optno = optno;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String[] getStocks() {
		return stocks;
	}
	public void setStocks(String[] stocks) {
		this.stocks = stocks;
	}
	public String getOptexplain() {
		return optexplain;
	}
	public void setOptexplain(String optexplain) {
		this.optexplain = optexplain;
	}
	
	@Override
	public String toString() {
		return "GdGoodsOption [sno=" + sno + ", goodsno=" + goodsno + ", opt1=" + opt1 + ", opt2=" + opt2
				+ ", consumer=" + consumer + ", price=" + price + ", priceRate=" + priceRate + ", supply=" + supply + ", supply2=" + supply2
				+ ", margin=" + margin + ", stock=" + stock
				+ ", link=" + link + ", optno=" + optno + ", parent=" + parent + ", stocks=" + Arrays.toString(stocks) + ", optexplain=" + optexplain
				+ "]";
	}
}
