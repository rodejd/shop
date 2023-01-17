package com.wepinit.wepinit_shop.xmall.common.vo.join;

import com.wepinit.wepinit_shop.xmall.common.vo.GdGoods;

/*join
 * gd_order_item
 * gd_goods
 * */
public class PopupOrder1 extends GdGoods {
	private int sno				; // 일련번호
	private long ordno			; // 주문번호
	private int goodsno			; // 상품번호
	private String goodsnm		; // 상품명(영문)
	private String goodsnmKR	; // 상품명(국문)
	private String goodsnmCN	; // 상품명(중문)
	private String brandnm		; // 브랜드명
	private String opt1			; // 옵션1
	private String opt2			; // 옵션2
	private String addopt		; // 추가옵션
	private String addoptPrice  ; // 추가옵션 가격
	private String supply		; // 공급가격
	private String memberdc		; // 회원DC
	private int ea				; // 수량
	private String dvno			; // 택배사번호
	private String dvcode		; // 송장코드
	private int istep			; // 단계
	private int step			; // 단계
	private int step2			; // 단계
	private String stockyn		; // 재고여부
	private String cyn			; // 결제여부
	private String dyn			; // 주문단계
	private int cancel			; // 취소
	private int tax				; // 부가세
	private String optno		; // 옵션NO
	private int couponEmoney	; // 쿠폰_적립금
	private int addemoney	; // 쿠폰_적립금
	private String optname 		; // 상품옵션
	
	// 화면
	private String subImgs;
	private String rIstep;
	private String bgColor;
	private String sellerNm;
	private String sellerCd;
	private String deliveryPrice;	// 배송비 출력
	private String addDeliveryPrice;// 추가배송비 출력시
	private String paymentTerms;
	
	private String priceSum; //주문상세의 소계 정보 (상품가격 * 개수 + 옵션들 가격  = 총가격)
	
	private String myritzNm;
	private String myritzCd;
	
	
	public String getMyritzNm() {
		return myritzNm;
	}
	public void setMyritzNm(String myritzNm) {
		this.myritzNm = myritzNm;
	}
	public String getMyritzCd() {
		return myritzCd;
	}
	public void setMyritzCd(String myritzCd) {
		this.myritzCd = myritzCd;
	}
	public int getAddemoney() {
		return addemoney;
	}
	public void setAddemoney(int addemoney) {
		this.addemoney = addemoney;
	}
	public String getOptname() {
		return optname;
	}
	public void setOptname(String optname) {
		this.optname = optname;
	}
	public String getAddoptPrice() {
		return addoptPrice;
	}
	public void setAddoptPrice(String addoptPrice) {
		this.addoptPrice = addoptPrice;
	}
	public String getAddDeliveryPrice() {
		return addDeliveryPrice;
	}
	public void setAddDeliveryPrice(String addDeliveryPrice) {
		this.addDeliveryPrice = addDeliveryPrice;
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
	@Override
	public int getGoodsno() {
		return goodsno;
	}
	@Override
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
	}
	@Override
	public String getGoodsnm() {
		return goodsnm;
	}
	@Override
	public void setGoodsnm(String goodsnm) {
		this.goodsnm = goodsnm;
	}
	@Override
	public String getGoodsnmKR() {
		return goodsnmKR;
	}
	@Override
	public void setGoodsnmKR(String goodsnmKR) {
		this.goodsnmKR = goodsnmKR;
	}
	@Override
	public String getGoodsnmCN() {
		return goodsnmCN;
	}
	@Override
	public void setGoodsnmCN(String goodsnmCN) {
		this.goodsnmCN = goodsnmCN;
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
	public String getSupply() {
		return supply;
	}
	public void setSupply(String supply) {
		this.supply = supply;
	}
	public String getMemberdc() {
		return memberdc;
	}
	public void setMemberdc(String memberdc) {
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
	public String getDvcode() {
		return dvcode;
	}
	public void setDvcode(String dvcode) {
		this.dvcode = dvcode;
	}
	public int getIstep() {
		return istep;
	}
	public void setIstep(int istep) {
		this.istep = istep;
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
	@Override
	public int getTax() {
		return tax;
	}
	@Override
	public void setTax(int tax) {
		this.tax = tax;
	}
	public String getOptno() {
		return optno;
	}
	public void setOptno(String optno) {
		this.optno = optno;
	}
	public int getCouponEmoney() {
		return couponEmoney;
	}
	public void setCouponEmoney(int couponEmoney) {
		this.couponEmoney = couponEmoney;
	}
	public String getSubImgs() {
		return subImgs;
	}
	public void setSubImgs(String subImgs) {
		this.subImgs = subImgs;
	}
	public String getrIstep() {
		return rIstep;
	}
	public void setrIstep(String rIstep) {
		this.rIstep = rIstep;
	}
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
	public String getBgColor() {
		return bgColor;
	}
	public String getSellerNm() {
		return sellerNm;
	}
	public void setSellerNm(String sellerNm) {
		this.sellerNm = sellerNm;
	}
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	public String getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(String deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	
	public String getPaymentTerms() {
		return paymentTerms;
	}
	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	@Override
	public String toString() {
		return "PopupOrder1 [sno=" + sno + ", ordno=" + ordno + ", goodsno="
				+ goodsno + ", goodsnm=" + goodsnm
				+ ", brandnm=" + brandnm + ", opt1=" + opt1 + ", opt2=" + opt2
				+ ", addopt=" + addopt + ", supply=" + supply + ", memberdc="
				+ memberdc + ", ea=" + ea + ", dvno=" + dvno + ", dvcode="
				+ dvcode + ", istep=" + istep + ", stockyn=" + stockyn
				+ ", cyn=" + cyn + ", dyn=" + dyn + ", cancel=" + cancel
				+ ", tax=" + tax + ", optno=" + optno 
				+ ", couponEmoney=" + couponEmoney + ", subImgs="
				+ subImgs + ", rIstep=" + rIstep + ", bgColor=" + bgColor
				+ ", sellerNm=" + sellerNm + ", sellerCd=" + sellerCd
				+ ", deliveryPrice=" + deliveryPrice + ", addDeliveryPrice="
				+ addDeliveryPrice + ", paymentTerms=" + paymentTerms + "]";
	}
	public String getPriceSum() {
		return priceSum;
	}
	public void setPriceSum(String priceSum) {
		this.priceSum = priceSum;
	}
	
	
}
