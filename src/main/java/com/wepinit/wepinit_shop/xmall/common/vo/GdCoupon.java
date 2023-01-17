package com.wepinit.wepinit_shop.xmall.common.vo;

import java.util.Date;

public class GdCoupon {
	private int cnt = 0;
	private int couponcd = 0;
	private String coupon = "";
	private String coupontype = "";
	private Date regdt = null;
	private String ability = "";
	private String price = "";
	private String priodtype = "";
	private String sdate= "";
	private String edate = "";
	private String sdt;
	private String stm;
	private String edt;
	private String etm;
	private String couponimg = "";
	private int dncnt = 1;
	private String duplctl = "";
	private String eactl = "";
	private int edncnt = 1;
	private int excPrice = 0;
	private String goodsall = "";
	private String goodstype = "";
	private String summa = "";
	private int open = 0;
	private String approvalstatus = "";
	private String approvalstatusnm = "";
	private String memo = "";
	private String maxprice = "";
	private String skin = "";
	private String dncode = "";
	private int m_no ;
	
	private String sellerNameList = "";
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getCouponcd() {
		return couponcd;
	}
	public void setCouponcd(int couponcd) {
		this.couponcd = couponcd;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public String getCoupontype() {
		return coupontype;
	}
	public void setCoupontype(String coupontype) {
		this.coupontype = coupontype;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public String getAbility() {
		return ability;
	}
	public void setAbility(String ability) {
		this.ability = ability;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPriodtype() {
		return priodtype;
	}
	public void setPriodtype(String priodtype) {
		this.priodtype = priodtype;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getStm() {
		return stm;
	}
	public void setStm(String stm) {
		this.stm = stm;
	}
	public String getEdt() {
		return edt;
	}
	public void setEdt(String edt) {
		this.edt = edt;
	}
	public String getEtm() {
		return etm;
	}
	public void setEtm(String etm) {
		this.etm = etm;
	}
	public String getCouponimg() {
		return couponimg;
	}
	public void setCouponimg(String couponimg) {
		this.couponimg = couponimg;
	}
	public int getDncnt() {
		return dncnt;
	}
	public void setDncnt(int dncnt) {
		this.dncnt = dncnt;
	}
	public String getDuplctl() {
		return duplctl;
	}
	public void setDuplctl(String duplctl) {
		this.duplctl = duplctl;
	}
	public String getEactl() {
		return eactl;
	}
	public void setEactl(String eactl) {
		this.eactl = eactl;
	}
	public int getEdncnt() {
		return edncnt;
	}
	public void setEdncnt(int edncnt) {
		this.edncnt = edncnt;
	}
	public int getExcPrice() {
		return excPrice;
	}
	public void setExcPrice(int excPrice) {
		this.excPrice = excPrice;
	}
	public String getGoodsall() {
		return goodsall;
	}
	public void setGoodsall(String goodsall) {
		this.goodsall = goodsall;
	}
	public String getGoodstype() {
		return goodstype;
	}
	public void setGoodstype(String goodstype) {
		this.goodstype = goodstype;
	}
	public String getSumma() {
		return summa;
	}
	public void setSumma(String summa) {
		this.summa = summa;
	}
	public String getSellerNameList() {
		return sellerNameList;
	}
	public void setSellerNameList(String sellerNameList) {
		this.sellerNameList = sellerNameList;
	}
	public String getApprovalstatus() {
		return approvalstatus;
	}
	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
	}
	public String getApprovalstatusnm() {
		return approvalstatusnm;
	}
	public void setApprovalstatusnm(String approvalstatusnm) {
		this.approvalstatusnm = approvalstatusnm;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getOpen() {
		return open;
	}
	public void setOpen(int open) {
		this.open = open;
	}
	public String getMaxprice() {
		return maxprice;
	}
	public void setMaxprice(String maxprice) {
		this.maxprice = maxprice;
	}
	public String getSkin() {
		return skin;
	}
	public void setSkin(String skin) {
		this.skin = skin;
	}
	public String getDncode() {
		return dncode;
	}
	public void setDncode(String dncode) {
		this.dncode = dncode;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	@Override
	public String toString() {
		return "GdCoupon [cnt=" + cnt + ", couponcd=" + couponcd + ", coupon=" + coupon + ", coupontype=" + coupontype + ", regdt=" + regdt
				+ ", ability=" + ability + ", price=" + price + ", priodtype=" + priodtype + ", sdate=" + sdate + ", edate=" + edate + ", couponimg="
				+ couponimg + ", dncnt=" + dncnt + ", duplctl=" + duplctl + ", eactl=" + eactl + ", edncnt=" + edncnt + ", excPrice=" + excPrice
				+ ", goodsall=" + goodsall + ", goodstype=" + goodstype + ", summa=" + summa + ", approvalstatus=" + approvalstatus
				+ ", approvalstatusnm=" + approvalstatusnm + ", memo=" + memo + ", open=" + open
				 + ", maxprice=" + maxprice + ", skin=" + skin + "]";
	}	
}
