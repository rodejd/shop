package com.wepinit.wepinit_shop.xmall.common.vo;


public class PopuGoodsOutVO {
	private String goodsnm;
	private String goodsnmKR;
	private String goodsnmCN;
	private int cnt;
	private long ea;
	private long price;
	
	
	@Override
	public String toString() {
		return "PopuGoodsOutVO [goodsnm=" + goodsnm + ", cnt=" + cnt + ", ea="
				+ ea + ", price=" + price + "]";
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

	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public long getEa() {
		return ea;
	}
	public void setEa(long ea) {
		this.ea = ea;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
	
	
}
