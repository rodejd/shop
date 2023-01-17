/*******************************************************************
                     PMGroupKorea Co., Ltd.
Copyright PMGroupKorea Co., Ltd. 2005. All rights reserved.
No part of this work covered by the copyright hereon may be reproduced,
stored in a retrieval system, in any form or by any means, electronic,
mechanical, photocopying, recording or otherwise, without the prior
written permission of PMGroupKorea Co., Ltd.
SOLUTION    :   XMaLL4 for Spring
FILE_NAME   :
DATE        :   2018.1.29
AUTHOR      :   PMGK S/W Engineer   <contact@gmail.com>
DESCRIPTION :
FUNCTIONS   :
HISTORY     :
REMARKS     :
******************************************************************/
package com.wepinit.wepinit_shop.xmall.common.vo.join;

import java.util.Date;

public class OrderitemGoods {
	//add object
	private String category;
	private int step;
	private int step2;
	private int escrowconfirm;
	
	private int sno;
	private String ordno;
	private String brandnm;
	private String opt1;
	private String opt2;
	private String addopt;
	private int price;
	private int supply;
	private int reserve;
	private int memberdc;
	private int ea;
	private String dvno;
	private int dvcode;
	private int istep;
	private String stockyn;
	private String cyn;
	private String dyn;
	private int cancel;
	private String optno;
	private int couponemoney;
	
	//gd_goods
	private int goodsno = 0;
	private String goodsnm = "";
	private String goodsnmKR = "";
	private String goodsnmCN = "";
	private String metatitle = "";
	private String goodscd = "";
	private String origin = "";
	private int brandno = 0;;
	private int tax = 0;
	private int deliverytype = 0;
	private String keyword = "";
	private String strprice = "";
	private String shortdesc = "";
	private String longdesc = "";
	private String imgi = "";
	private String imgs = "";
	private String imgl = "";
	private String imgm = "";
	private String memo = "";
	private Date regdt = null;
	private int open = 0;
	private int runout = 0;
	private String optnm = "";
	private String opttype = "";
	private String addoptnm = "";
	private String ex_title = "";
	private String ex1 = "";
	private String ex2 = "";
	private String ex3 = "";
	private String ex4 = "";
	private String ex5 = "";
	private String ex6 = "";
	private int relationis = 0;
	private String relation = "";
	private String usestock = "";
	private String coupon = "";
	private int couponea = 0;
	private String couponusecn = "";
	private String coupondate = "";
	private int goodsdelivery = 0;
	private String useemoney = "";
	private String mid = "";
	private String tag = "";
	private String invoice;
	private String deliveryCompCd;
	private String deliveryStatus;
	private String deliveryCompNm;
	private String deliveryCompUrl;
	
	private String optname;
	
	
	
	public String getOptname() {
		return optname;
	}
	public void setOptname(String optname) {
		this.optname = optname;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getStep2() {
		return step2;
	}
	public void setStep2(int step2) {
		this.step2 = step2;
	}
	public int getEscrowconfirm() {
		return escrowconfirm;
	}
	public void setEscrowconfirm(int escrowconfirm) {
		this.escrowconfirm = escrowconfirm;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getOrdno() {
		return ordno;
	}
	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}
	public String getBrandnm() {
		return brandnm;
	}
	public void setBrandnm(String brandnm) {
		this.brandnm = brandnm;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSupply() {
		return supply;
	}
	public void setSupply(int supply) {
		this.supply = supply;
	}
	public int getReserve() {
		return reserve;
	}
	public void setReserve(int reserve) {
		this.reserve = reserve;
	}
	public int getMemberdc() {
		return memberdc;
	}
	public void setMemberdc(int memberdc) {
		this.memberdc = memberdc;
	}
	public int getEa() {
		return ea;
	}
	public void setEa(int ea) {
		this.ea = ea;
	}
	public String getDvno() {
		return dvno;
	}
	public void setDvno(String dvno) {
		this.dvno = dvno;
	}
	public int getDvcode() {
		return dvcode;
	}
	public void setDvcode(int dvcode) {
		this.dvcode = dvcode;
	}
	public int getIstep() {
		return istep;
	}
	public void setIstep(int istep) {
		this.istep = istep;
	}
	public String getStockyn() {
		return stockyn;
	}
	public void setStockyn(String stockyn) {
		this.stockyn = stockyn;
	}
	public String getCyn() {
		return cyn;
	}
	public void setCyn(String cyn) {
		this.cyn = cyn;
	}
	public String getDyn() {
		return dyn;
	}
	public void setDyn(String dyn) {
		this.dyn = dyn;
	}
	public int getCancel() {
		return cancel;
	}
	public void setCancel(int cancel) {
		this.cancel = cancel;
	}
	public String getOptno() {
		return optno;
	}
	public void setOptno(String optno) {
		this.optno = optno;
	}
	public int getCouponemoney() {
		return couponemoney;
	}
	public void setCouponemoney(int couponemoney) {
		this.couponemoney = couponemoney;
	}
	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
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
	public String getMetatitle() {
		return metatitle;
	}
	public void setMetatitle(String metatitle) {
		this.metatitle = metatitle;
	}
	public String getGoodscd() {
		return goodscd;
	}
	public void setGoodscd(String goodscd) {
		this.goodscd = goodscd;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public int getBrandno() {
		return brandno;
	}
	public void setBrandno(int brandno) {
		this.brandno = brandno;
	}
	public int getTax() {
		return tax;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}
	public int getDeliverytype() {
		return deliverytype;
	}
	public void setDeliverytype(int deliverytype) {
		this.deliverytype = deliverytype;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getStrprice() {
		return strprice;
	}
	public void setStrprice(String strprice) {
		this.strprice = strprice;
	}
	public String getShortdesc() {
		return shortdesc;
	}
	public void setShortdesc(String shortdesc) {
		this.shortdesc = shortdesc;
	}
	public String getLongdesc() {
		return longdesc;
	}
	public void setLongdesc(String longdesc) {
		this.longdesc = longdesc;
	}
	public String getImgi() {
		return imgi;
	}
	public void setImgi(String imgi) {
		this.imgi = imgi;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public String getImgm() {
		return imgm;
	}
	public void setImgm(String imgm) {
		this.imgm = imgm;
	}
	public String getImgl() {
		return imgl;
	}
	public void setImgl(String imgl) {
		this.imgl = imgl;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public int getOpen() {
		return open;
	}
	public void setOpen(int open) {
		this.open = open;
	}
	public int getRunout() {
		return runout;
	}
	public void setRunout(int runout) {
		this.runout = runout;
	}
	public String getOptnm() {
		return optnm;
	}
	public void setOptnm(String optnm) {
		this.optnm = optnm;
	}
	public String getOpttype() {
		return opttype;
	}
	public void setOpttype(String opttype) {
		this.opttype = opttype;
	}
	public String getAddoptnm() {
		return addoptnm;
	}
	public void setAddoptnm(String addoptnm) {
		this.addoptnm = addoptnm;
	}
	public String getEx_title() {
		return ex_title;
	}
	public void setEx_title(String ex_title) {
		this.ex_title = ex_title;
	}
	public String getEx1() {
		return ex1;
	}
	public void setEx1(String ex1) {
		this.ex1 = ex1;
	}
	public String getEx2() {
		return ex2;
	}
	public void setEx2(String ex2) {
		this.ex2 = ex2;
	}
	public String getEx3() {
		return ex3;
	}
	public void setEx3(String ex3) {
		this.ex3 = ex3;
	}
	public String getEx4() {
		return ex4;
	}
	public void setEx4(String ex4) {
		this.ex4 = ex4;
	}
	public String getEx5() {
		return ex5;
	}
	public void setEx5(String ex5) {
		this.ex5 = ex5;
	}
	public String getEx6() {
		return ex6;
	}
	public void setEx6(String ex6) {
		this.ex6 = ex6;
	}
	public int getRelationis() {
		return relationis;
	}
	public void setRelationis(int relationis) {
		this.relationis = relationis;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getUsestock() {
		return usestock;
	}
	public void setUsestock(String usestock) {
		this.usestock = usestock;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public int getCouponea() {
		return couponea;
	}
	public void setCouponea(int couponea) {
		this.couponea = couponea;
	}
	public String getCouponusecn() {
		return couponusecn;
	}
	public void setCouponusecn(String couponusecn) {
		this.couponusecn = couponusecn;
	}
	public String getCoupondate() {
		return coupondate;
	}
	public void setCoupondate(String coupondate) {
		this.coupondate = coupondate;
	}
	public int getGoodsdelivery() {
		return goodsdelivery;
	}
	public void setGoodsdelivery(int goodsdelivery) {
		this.goodsdelivery = goodsdelivery;
	}
	public String getUseemoney() {
		return useemoney;
	}
	public void setUseemoney(String useemoney) {
		this.useemoney = useemoney;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getDeliveryCompCd() {
		return deliveryCompCd;
	}
	public void setDeliveryCompCd(String deliveryCompCd) {
		this.deliveryCompCd = deliveryCompCd;
	}
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	
	// whpark 20191108 start
	public String getDeliveryCompNm() {
		return deliveryCompNm;
	}
	public void setDeliveryCompNm(String deliveryCompNm) {
		this.deliveryCompNm = deliveryCompNm;
	}
		
	
	public String getDeliveryCompUrl() {
		return deliveryCompUrl;
	}
	public void setDeliveryCompUrl(String deliveryCompUrl) {
		this.deliveryCompUrl = deliveryCompUrl;
	}
	//whpark 20191108 end 

	
	@Override
	public String toString() {
		return "OrderitemGoods [category=" + category + ", step=" + step
				+ ", step2=" + step2 + ", escrowconfirm=" + escrowconfirm
				+ ", sno=" + sno + ", ordno=" + ordno + ", brandnm=" + brandnm
				+ ", opt1=" + opt1 + ", opt2=" + opt2 + ", addopt=" + addopt
				+ ", price=" + price + ", supply=" + supply + ", reserve="
				+ reserve + ", memberdc=" + memberdc + ", ea=" + ea + ", dvno="
				+ dvno + ", dvcode=" + dvcode + ", istep=" + istep
				+ ", stockyn=" + stockyn + ", cyn=" + cyn + ", dyn=" + dyn
				+ ", cancel=" + cancel + ", optno=" + optno 
				+ ", couponemoney=" + couponemoney + ", goodsno=" + goodsno + ", goodsnm="
				+ goodsnm + ", metatitle=" + metatitle + ", goodscd=" + goodscd
				+ ", origin=" + origin + ", brandno="
				+ brandno + ", tax=" + tax + ", deliverytype=" + deliverytype
				+ ", keyword=" + keyword + ", strprice=" + strprice
				+ ", shortdesc=" + shortdesc + ", longdesc=" + longdesc
				+ ", imgi=" + imgi + ", imgs=" + imgs + ", imgm=" + imgm
				+ ", imgl=" + imgl + ", memo=" + memo + ", regdt=" + regdt
				+ ", open=" + open + ", runout=" + runout
				+ ", optnm=" + optnm + ", opttype=" + opttype + ", addoptnm="
				+ addoptnm + ", ex_title=" + ex_title + ", ex1=" + ex1
				+ ", ex2=" + ex2 + ", ex3=" + ex3 + ", ex4=" + ex4 + ", ex5="
				+ ex5 + ", ex6=" + ex6 + ", relationis=" + relationis
				+ ", relation=" + relation + ", usestock=" + usestock
				+ ", coupon=" + coupon + ", couponea=" + couponea
				+ ", couponusecn=" + couponusecn + ", coupondate=" + coupondate
				+ ", goodsdelivery=" + goodsdelivery + ", useemoney=" + useemoney + ", mid=" + mid
				+ ", tag=" + tag + ", invoice=" + invoice
				+ ", deliveryCompCd=" + deliveryCompCd + ", deliveryStatus="
				+ deliveryStatus 
				+ ", deliveryCompNm=" + deliveryCompNm
				+ ", deliveryCompUrl=" + deliveryCompUrl
				+ "]";
	}
}
