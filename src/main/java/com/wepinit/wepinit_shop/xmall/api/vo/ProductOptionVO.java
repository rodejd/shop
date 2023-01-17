package com.wepinit.wepinit_shop.xmall.api.vo;

public class ProductOptionVO {
	private int sno = 0;
	private int goodsno = 0;
	private String opt1 = "";
	private String opt2 = "";
	private int consumer = 0;
	private int price = 0;
	private int priceRate = 0;
	private int priceB2b = 0;
	private int priceRateB2b = 0;
	private int supplyPrice1 = 0;
	private int supplyPrice2 = 0;
	private int margin = 0;
	private int stock = 0;
	private int link = 0;
	private String parent = "0";
	private String optexplain = "";
	private int priceMyRitz = 0;
	
	
	public int getPriceB2b() {
		return priceB2b;
	}
	public void setPriceB2b(int priceB2b) {
		this.priceB2b = priceB2b;
	}
	public int getPriceRateB2b() {
		return priceRateB2b;
	}
	public void setPriceRateB2b(int priceRateB2b) {
		this.priceRateB2b = priceRateB2b;
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
	public void setGoodsno(int goodsno2) {
		this.goodsno = goodsno2;
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
	public int getSupplyPrice1() {
		return supplyPrice1;
	}
	public void setSupplyPrice1(int supplyPrice1) {
		this.supplyPrice1 = supplyPrice1;
	}
	public int getSupplyPrice2() {
		return supplyPrice2;
	}
	public void setSupplyPrice2(int supplyPrice2) {
		this.supplyPrice2 = supplyPrice2;
	}
	public int getMargin() {
		return margin;
	}
	public void setMargin(int margin) {
		this.margin = margin;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getLink() {
		return link;
	}
	public void setLink(int link) {
		this.link = link;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getOptexplain() {
		return optexplain;
	}
	public void setOptexplain(String optexplain) {
		this.optexplain = optexplain;
	}
	public int getPriceMyRitz() {
		return priceMyRitz;
	}
	public void setPriceMyRitz(int priceMyRitz) {
		this.priceMyRitz = priceMyRitz;
	}
	
	@Override
	public String toString() {
		return "ProductOptionVO [sno=" + sno
				 + ", goodsno=" + goodsno
				 + ", opt1=" + opt1
				 + ", opt2=" + opt2
				 + ", consumer=" + consumer
				 + ", price=" + price
				 + ", priceRate=" + priceRate
				 + ", supplyPrice1=" + supplyPrice1
				 + ", supplyPrice2=" + supplyPrice2
				 + ", margin=" + margin
				 + ", stock=" + stock
				 + ", link=" + link
				 + ", parent=" + parent
				 + ", optexplain=" + optexplain
				 + ", priceMyRitz=" + priceMyRitz + "]";
	}
}
