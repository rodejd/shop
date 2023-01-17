package com.wepinit.wepinit_shop.xmall.common.vo.join;


public class GoodsGoodsbrand {
	
	private String brandnm;
	private String brandnmCN;
	private String brandnmKR;
	private int tax;
	
	public String getBrandnm() {
		return brandnm;
	}
	public void setBrandnm(String brandnm) {
		this.brandnm = brandnm;
	}
	public String getBrandnmCN() {
		return brandnmCN;
	}
	public void setBrandnmCN(String brandnmCN) {
		this.brandnmCN = brandnmCN;
	}
	public String getBrandnmKR() {
		return brandnmKR;
	}
	public void setBrandnmKR(String brandnmKR) {
		this.brandnmKR = brandnmKR;
	}
	public int getTax() {
		return tax;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}
	@Override
	public String toString() {
		return "GoodsGoodsbrand [brandnm=" + brandnm
				+ ", tax=" + tax + "]";
	}
}
