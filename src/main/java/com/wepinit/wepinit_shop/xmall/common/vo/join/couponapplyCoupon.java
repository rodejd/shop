package com.wepinit.wepinit_shop.xmall.common.vo.join;

import java.util.Date;



public class couponapplyCoupon{
	
	// gd_coupon_apply
	private int cnt = 0;
	private int couCnt = 0;
	private int couponcd = 0;
	private int sno = 0;
	private int goodsno = 0;
	private int membergrpsno = 0;
	private String goodsnm = "";
	private String goodsnmKR = "";
	private String goodsnmCN = "";
	private String membertype = "";
	private Date regdt = null;
	private String status = "";
	// gd_coupon
//	private int cnt = 0;
//	private int couponcd = 0;
	private String coupon = "";
	private String coupontype = "";
//	private Date regdt = null;
	private String ability = "";
	private String price = "";
	private String priodtype = "";
	private String sdate= "";
	private String edate = "";
	private int couponimg = 0;
	private int dncnt = 1;
	private String duplctl = "";
	private String eactl = "";
	private int edncnt = 1;
	private int excPrice = 0;
	private String goodsall = "";
	private String goodstype = "";
	private String summa = "";
	private String maxprice = "";
	private String skin = "";
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getCouCnt() {
		return couCnt;
	}
	public void setCouCnt(int couCnt) {
		this.couCnt = couCnt;
	}
	public int getCouponcd() {
		return couponcd;
	}
	public void setCouponcd(int couponcd) {
		this.couponcd = couponcd;
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
	public int getMembergrpsno() {
		return membergrpsno;
	}
	public void setMembergrpsno(int membergrpsno) {
		this.membergrpsno = membergrpsno;
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
	public String getMembertype() {
		return membertype;
	}
	public void setMembertype(String membertype) {
		this.membertype = membertype;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public int getCouponimg() {
		return couponimg;
	}
	public void setCouponimg(int couponimg) {
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
	@Override
	public String toString() {
		return "couponapplyCoupon [cnt=" + cnt + ", couCnt=" + couCnt
				+ ", couponcd=" + couponcd + ", sno=" + sno + ", goodsno="
				+ goodsno + ", membergrpsno=" + membergrpsno + ", goodsnm="
				+ goodsnm + ", membertype=" + membertype + ", regdt=" + regdt
				+ ", status=" + status + ", coupon=" + coupon + ", coupontype="
				+ coupontype + ", ability=" + ability + ", price=" + price
				+ ", priodtype=" + priodtype + ", sdate=" + sdate + ", edate="
				+ edate + ", couponimg=" + couponimg + ", dncnt=" + dncnt
				+ ", duplctl=" + duplctl + ", eactl=" + eactl + ", edncnt="
				+ edncnt + ", excPrice=" + excPrice + ", goodsall=" + goodsall
				+ ", goodstype=" + goodstype + ", summa=" + summa + "]";
	}
}
