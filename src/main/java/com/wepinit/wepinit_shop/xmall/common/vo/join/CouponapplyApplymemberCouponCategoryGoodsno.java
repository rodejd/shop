package com.wepinit.wepinit_shop.xmall.common.vo.join;

import java.util.Date;

/*
 * gd_coupon_apply 
 * gd_coupon_applymember	 
 * gd_coupon	
 * gd_coupon_goodsno
 * JOIN
 */
public class CouponapplyApplymemberCouponCategoryGoodsno {
	//gd_coupon_goodsno			
	private int goodsno;
	private int couponcd;
	//gd_coupon_category			
	private String category;
//	private int couponcd;
	//gd_coupon			
//	private int couponcd;
	private String goodstype;
	private String coupontype;
	private String coupon;
	private String summa;
	private String priodtype;
	private String sdate;
	private String edate;
	private String excPrice;
	private String ability;
	private String price;
	private Date regdt;
	private String goodsall;
	private int couponimg;
	private String eactl;
	private int dncnt;
	private String duplctl;
	private int edncnt;
	private String maxprice;
	private String skin;
	//gd_coupon_applymember			
	private int applysno;
	private int m_no;
	private String used;
	//gd_coupon_apply			
	private int sno;
//	private int couponcd;
	private String membertype;
	private int member_grp_sno;
	private Date gcaRegdt;
//	private int goodsno;
	private String goodsnm;
	private String goodsnmKR;
	private String goodsnmCN;
	private String status;
	private int goodsPrice;
	private String dcPrice;		// 쿠폰적용 된 할인 금액
	private String couponAbi;	// 적립금쿠폰 / 할인쿠폰 구분
	
	private String expdate;
	
	public String getCouponAbi() {
		return couponAbi;
	}
	public void setCouponAbi(String couponAbi) {
		this.couponAbi = couponAbi;
	}
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	public Date getGcaRegdt() {
		return gcaRegdt;
	}
	public void setGcaRegdt(Date gcaRegdt) {
		this.gcaRegdt = gcaRegdt;
	}
	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
	}
	public int getCouponcd() {
		return couponcd;
	}
	public void setCouponcd(int couponcd) {
		this.couponcd = couponcd;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getGoodstype() {
		return goodstype;
	}
	public void setGoodstype(String goodstype) {
		this.goodstype = goodstype;
	}
	public String getCoupontype() {
		return coupontype;
	}
	public void setCoupontype(String coupontype) {
		this.coupontype = coupontype;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public String getSumma() {
		return summa;
	}
	public void setSumma(String summa) {
		this.summa = summa;
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
	public String getExcPrice() {
		return excPrice;
	}
	public void setExcPrice(String excPrice) {
		this.excPrice = excPrice;
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
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public String getGoodsall() {
		return goodsall;
	}
	public void setGoodsall(String goodsall) {
		this.goodsall = goodsall;
	}
	public int getCouponimg() {
		return couponimg;
	}
	public void setCouponimg(int couponimg) {
		this.couponimg = couponimg;
	}
	public String getEactl() {
		return eactl;
	}
	public void setEactl(String eactl) {
		this.eactl = eactl;
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
	public int getEdncnt() {
		return edncnt;
	}
	public void setEdncnt(int edncnt) {
		this.edncnt = edncnt;
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
	public int getApplysno() {
		return applysno;
	}
	public void setApplysno(int applysno) {
		this.applysno = applysno;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getMembertype() {
		return membertype;
	}
	public void setMembertype(String membertype) {
		this.membertype = membertype;
	}
	public int getMember_grp_sno() {
		return member_grp_sno;
	}
	public void setMember_grp_sno(int member_grp_sno) {
		this.member_grp_sno = member_grp_sno;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getDcPrice() {
		return dcPrice;
	}
	public void setDcPrice(String dcPrice) {
		this.dcPrice = dcPrice;
	}
	public String getExpdate() {
		return expdate;
	}
	public void setExpdate(String expdate) {
		this.expdate = expdate;
	}
	@Override
	public String toString() {
		return "CouponapplyApplymemberCouponCategoryGoodsno [goodsno="
				+ goodsno + ", couponcd=" + couponcd + ", category=" + category
				+ ", goodstype=" + goodstype + ", coupontype=" + coupontype
				+ ", coupon=" + coupon + ", summa=" + summa + ", priodtype="
				+ priodtype + ", sdate=" + sdate + ", edate=" + edate
				+ ", excPrice=" + excPrice + ", ability=" + ability
				+ ", price=" + price + ", regdt=" + regdt + ", goodsall="
				+ goodsall + ", couponimg=" + couponimg + ", eactl=" + eactl
				+ ", dncnt=" + dncnt + ", duplctl=" + duplctl + ", edncnt="
				+ edncnt + ", applysno=" + applysno + ", m_no=" + m_no
				+ ", sno=" + sno + ", membertype=" + membertype
				+ ", member_grp_sno=" + member_grp_sno + ", goodsnm=" + goodsnm
				+ ", status=" + status + "]";
	}

	
}