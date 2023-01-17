package com.wepinit.wepinit_shop.xmall.common.vo.join;

import java.util.Date;

/*
 * gd_order  단독 또는
 * gd_order_item
 * gd_goods
 * JOIN
 * 
 * -- 현기준 주문 엑셀다운로드에 필요한 항목만 정의함. 필요시 추가요.
 */
public class OrderExcel {
	
	/** gd_order **/
	private long ordno = 0;
	private String nameorder = "";
	private String email = "";
	private String phoneorder = "";
	private String mobileorder = "";
	private String namereceiver = "";
	private String phonereceiver = "";
	private String mobilereceiver = "";
	private String zipcode = "";
	private String address = "";
	private String memo = "";
	private String settlekind = "";
	private Date orddt = null;
	private int step = 0;
	private int step2 = 0;
	private int goodsprice = 0;
	private int settleprice = 0;
	private int prnsettleprice = 0;
	private int deliveryno = 0;
	private String deliverycode = "";
	private Date ddt = null;
	
	/** gd_order_item **/
	private int sno = 0;
	private String addopt = "";
	private String brandnm = "";
	private int ea = 0;
	private int supply = 0;
	private int istep ;
	
	/** gd_goods **/
	private String goodsnm;
	private String goodsnmKR;
	private String goodsnmCN;
	private String goodscd;
	private String origin;
	
	/*gd_seller*/
	private String sellerNm;
	
	private int seller_price = 0;
	
	
	
	public int getPrnsettleprice() {
		return prnsettleprice;
	}

	public void setPrnsettleprice(int prnsettleprice) {
		this.prnsettleprice = prnsettleprice;
	}

	public int getSeller_price() {
		return seller_price;
	}

	public void setSeller_price(int seller_price) {
		this.seller_price = seller_price;
	}

	public int getIstep() {
		return istep;
	}

	public void setIstep(int istep) {
		this.istep = istep;
	}

	public long getOrdno() {
		return ordno;
	}

	public void setOrdno(long ordno) {
		this.ordno = ordno;
	}

	public String getNameorder() {
		return nameorder;
	}

	public void setNameorder(String nameorder) {
		this.nameorder = nameorder;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneorder() {
		return phoneorder;
	}

	public void setPhoneorder(String phoneorder) {
		this.phoneorder = phoneorder;
	}

	public String getMobileorder() {
		return mobileorder;
	}

	public void setMobileorder(String mobileorder) {
		this.mobileorder = mobileorder;
	}

	public String getNamereceiver() {
		return namereceiver;
	}

	public void setNamereceiver(String namereceiver) {
		this.namereceiver = namereceiver;
	}

	public String getPhonereceiver() {
		return phonereceiver;
	}

	public void setPhonereceiver(String phonereceiver) {
		this.phonereceiver = phonereceiver;
	}

	public String getMobilereceiver() {
		return mobilereceiver;
	}

	public void setMobilereceiver(String mobilereceiver) {
		this.mobilereceiver = mobilereceiver;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getSettlekind() {
		return settlekind;
	}

	public void setSettlekind(String settlekind) {
		this.settlekind = settlekind;
	}

	public Date getOrddt() {
		return orddt;
	}

	public void setOrddt(Date orddt) {
		this.orddt = orddt;
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
	public int getGoodsprice() {
		return goodsprice;
	}

	public void setGoodsprice(int goodsprice) {
		this.goodsprice = goodsprice;
	}

	public int getSettleprice() {
		return settleprice;
	}

	public void setSettleprice(int settleprice) {
		this.settleprice = settleprice;
	}

	public int getDeliveryno() {
		return deliveryno;
	}

	public void setDeliveryno(int deliveryno) {
		this.deliveryno = deliveryno;
	}

	public String getDeliverycode() {
		return deliverycode;
	}

	public void setDeliverycode(String deliverycode) {
		this.deliverycode = deliverycode;
	}

	public Date getDdt() {
		return ddt;
	}

	public void setDdt(Date ddt) {
		this.ddt = ddt;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getAddopt() {
		return addopt;
	}

	public void setAddopt(String addopt) {
		this.addopt = addopt;
	}

	public String getBrandnm() {
		return brandnm;
	}

	public void setBrandnm(String brandnm) {
		this.brandnm = brandnm;
	}

	public int getEa() {
		return ea;
	}

	public void setEa(int ea) {
		this.ea = ea;
	}

	public int getSupply() {
		return supply;
	}

	public void setSupply(int supply) {
		this.supply = supply;
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

	public String getSellerNm() {
		return sellerNm;
	}

	public void setSellerNm(String sellerNm) {
		this.sellerNm = sellerNm;
	}

	@Override
	public String toString() {
		return "OrderExcel [ordno=" + ordno + ", nameorder=" + nameorder
				+ ", email=" + email + ", phoneorder=" + phoneorder
				+ ", mobileorder=" + mobileorder + ", namereceiver="
				+ namereceiver + ", phonereceiver=" + phonereceiver
				+ ", mobilereceiver=" + mobilereceiver + ", zipcode=" + zipcode
				+ ", address=" + address + ", memo=" + memo + ", settlekind="
				+ settlekind + ", orddt=" + orddt + ", step=" + step
				+ ", step2=" + step2 + ", goodsprice=" + goodsprice
				+ ", settleprice=" + settleprice + ", prnsettleprice="
				+ prnsettleprice + ", deliveryno=" + deliveryno
				+ ", deliverycode=" + deliverycode + ", ddt=" + ddt + ", sno="
				+ sno + ", addopt=" + addopt + ", brandnm=" + brandnm + ", ea="
				+ ea + ", supply=" + supply + ", istep=" + istep + ", goodsnm="
				+ goodsnm + ", goodscd=" + goodscd
				+ ", origin=" + origin + ", sellerNm=" + sellerNm
				+ ", seller_price=" + seller_price + "]";
	}

	
		

}
