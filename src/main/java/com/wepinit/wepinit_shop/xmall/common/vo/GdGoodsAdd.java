package com.wepinit.wepinit_shop.xmall.common.vo;

public class GdGoodsAdd {
	
	private int sno;
	private int goodsno;
	private int step;
	private String opt;
	private int addprice;

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
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public int getAddprice() {
		return addprice;
	}
	public void setAddprice(int addprice) {
		this.addprice = addprice;
	}

	@Override
	public String toString() {
		return "GdGoodsAdd [sno=" + sno + ", goodsno=" + goodsno + ", step="
				+ step + ", opt=" + opt + ", addprice=" + addprice + "]";
	}

}
