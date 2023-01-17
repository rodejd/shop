package com.wepinit.wepinit_shop.xmall.common.vo;

import java.util.Date;

public class GdCouponApply {
	
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
	private String membertype = "";	// 0 : 전체회원 발급 / 1 : 그룹별 발급 / 2 : 회원개별발급
	private Date regdt = null;
	private String status = "";
	
	// gd_meber_grp
	private String grpnm = "";
	
	// gd_goods
	private String imgs = "";
	
	// gd_member
	private String mid = "";
	private String mno = "";
	
	// gd_coupon_order
	private Date mregdt = null;
	
	// go_goods_option
	private int price = 0;
	
	//gd_goods_link
	private String category = "";
	
	//count 추가
	private String totalCount ="";
	private String usedCount ="";
	
	
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getUsedCount() {
		return usedCount;
	}
	public void setUsedCount(String usedCount) {
		this.usedCount = usedCount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
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

	public String getGrpnm() {
		return grpnm;
	}

	public void setGrpnm(String grpnm) {
		this.grpnm = grpnm;
	}

	public String getImg_s() {
		return imgs;
	}

	public void setImg_s(String imgs) {
		this.imgs = imgs;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	public Date getMregdt() {
		return mregdt;
	}

	public void setMregdt(Date mregdt) {
		this.mregdt = mregdt;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "GdCouponApply [cnt=" + cnt + ", couCnt=" + couCnt + ", couponcd=" + couponcd + ", sno=" + sno + ", goodsno=" + goodsno
				+ ", membergrpsno=" + membergrpsno + ", goodsnm=" + goodsnm + ", membertype=" + membertype + ", regdt=" + regdt + ", status="
				+ status + ", grpnm=" + grpnm + ", imgs=" + imgs + ", mid=" + mid + ", mno=" + mno + ", mregdt=" + mregdt + "]";
	}
	
}
