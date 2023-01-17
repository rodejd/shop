package com.wepinit.wepinit_shop.xmall.common.vo.join;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

import java.util.Date;

/*
 * gd_goods_review
 * gd_member
 * gd_goods
 * JOIN
 */
public class MemberGoodsrevwGoods extends PageMaker {
	
	//gd_goods_review
	private int sno;
	private int parent;
	private String subject;
	private String contents;
	private int point;
	private Date regdt;
	private String name;
	
	//gd_member
	private int mno;
	private int emoney;
	private String mid;
	private String mname;
	private String phone;
	private String mobile;
	private String profile;
	
	
	//gd_goods
	private String goodsnm;
	private String goodsnmKR;
	private String goodsnmCN;
	private String imgs;
	private int goodsno;
	private String brandno;
	private String binCd;
	private String reviewimg;
	
	//gd_goods_brand
	private String brandnm;
	
	//gd_goods_link
	private String category;
	
	private int replcnt;
	
	private String sellerNm = "";	// 판매사명
	
	
	public String getSellerNm() {
		return sellerNm;
	}
	public void setSellerNm(String sellerNm) {
		this.sellerNm = sellerNm;
	}
	public String getBrandnm() {
		return brandnm;
	}
	public void setBrandnm(String brandnm) {
		this.brandnm = brandnm;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
	}
	public int getReplcnt() {
		return replcnt;
	}
	public void setReplcnt(int replcnt) {
		this.replcnt = replcnt;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getEmoney() {
		return emoney;
	}
	public void setEmoney(int emoney) {
		this.emoney = emoney;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getGoodsnm() {
		return goodsnm;
	}
	public void setGoodsnm(String goodsnm) {
		this.goodsnm = goodsnm;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
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
	public String getBrandno() {
		return brandno;
	}
	public void setBrandno(String brandno) {
		this.brandno = brandno;
	}
	public String getBinCd() {
		return binCd;
	}
	public void setBinCd(String binCd) {
		this.binCd = binCd;
	}
	public String getReviewimg() {
		return reviewimg;
	}
	public void setReviewimg(String reviewimg) {
		this.reviewimg = reviewimg;
	}
	@Override
	public String toString() {
		return "MemberGoodsrevwGoods [sno=" + sno + ", parent=" + parent
				+ ", subject=" + subject + ", contents=" + contents
				+ ", point=" + point + ", regdt=" + regdt + ", name=" + name
				+ ", mno=" + mno + ", emoney=" + emoney + ", mid=" + mid
				+ ", mname=" + mname + ", phone=" + phone + ", mobile="
				+ mobile + ", profile=" + profile + ", goodsnm=" + goodsnm
				+ ", imgs=" + imgs + ", goodsno=" + goodsno + ", replcnt="
				+ replcnt + "]";
	}
}
