package com.wepinit.wepinit_shop.xmall.common.vo;

import java.util.Date;


public class GdGoodsCart {
	
	private int sno;
	private int m_no;
	private int goodsno;
	private String opt1;
	private String opt2;
	private String addopt;
	private Date regdt;
	private int ea;
	private String deliveryPolicyNo;
	private String sellerCd;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
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
	public int getEa() {
		return ea;
	}
	public void setEa(int ea) {
		this.ea = ea;
	}
	public String getDeliveryPolicyNo() {
		return deliveryPolicyNo;
	}
	public void setDeliveryPolicyNo(String deliveryPolicyNo) {
		this.deliveryPolicyNo = deliveryPolicyNo;
	}
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	@Override
	public String toString() {
		return "GdGoodsCart [sno=" + sno + ", m_no=" + m_no + ", goodsno="
				+ goodsno + ", opt1=" + opt1 + ", opt2=" + opt2 + ", addopt="
				+ addopt + ", regdt=" + regdt + ", ea=" + ea
				+ ", deliveryPolicyNo=" + deliveryPolicyNo + ", sellerCd="
				+ sellerCd + "]";
	}
}
