package com.wepinit.wepinit_shop.xmall.common.vo;

import java.util.Date;

public class GdCouponOrder {
	private int sno			;//일련번호
	private long ordno		;//주문번호
	private int goodsno		;//상품일련번호
	private int applysno	;//적용일련번호
	private String coupon	;//쿠폰
	private String dc		;//DC
	private int emoney = 0;//적립금
	private Date regdt		;//등록일시
	private int m_no		;//회원번호
	
	private String cnt;// count query
	
	
	/**
	 * @return the cnt
	 */
	public String getCnt() {
		return cnt;
	}
	/**
	 * @param cnt the cnt to set
	 */
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public long getOrdno() {
		return ordno;
	}
	public void setOrdno(long ordno) {
		this.ordno = ordno;
	}
	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
	}
	public int getApplysno() {
		return applysno;
	}
	public void setApplysno(int applysno) {
		this.applysno = applysno;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public String getDc() {
		return dc;
	}
	public void setDc(String dc) {
		this.dc = dc;
	}
	public int getEmoney() {
		return emoney;
	}
	public void setEmoney(int emoney) {
		this.emoney = emoney;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GdCouponOrder [sno=" + sno + ", ordno=" + ordno + ", goodsno="
				+ goodsno + ", applysno=" + applysno + ", coupon=" + coupon
				+ ", dc=" + dc + ", emoney=" + emoney + ", regdt=" + regdt
				+ ", m_no=" + m_no + ", cnt=" + cnt + "]";
	}
}
