package com.wepinit.wepinit_shop.batch.vo;

public class BatchAccountVO {

	private String ordno; //주문번호
	private Integer goodsno; //상품일련번호
	private String goodsnm; //상품명(영문)
	private String goodsnmKR; //상품명
	private String goodsnmCN; //상품명
	private String sellerCd; //판매사코드
	private String orddt; //주문일시
	private Integer ea; //수량
	private Integer price; //판매가격
	private String addopt; //추가옵션
	private Integer deliveryPrice; //배송비
	private Integer addDeliveryPrice; //추가배송비
	private Integer fees; //수수료
	private Integer coupon; //쿠폰할인
	private Integer accountPrice; //정산금액
	private String accountFlag; //정산상태
	private String accountDate; //정산일시
	
	private String sdate;
	private String edate;
	
	
	public String getOrdno() {
		return ordno;
	}
	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}
	public Integer getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(Integer goodsno) {
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
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	public String getOrddt() {
		return orddt;
	}
	public void setOrddt(String orddt) {
		this.orddt = orddt;
	}
	public Integer getEa() {
		return ea;
	}
	public void setEa(Integer ea) {
		this.ea = ea;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getAddopt() {
		return addopt;
	}
	public void setAddopt(String addopt) {
		this.addopt = addopt;
	}
	public Integer getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(Integer deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	public Integer getAddDeliveryPrice() {
		return addDeliveryPrice;
	}
	public void setAddDeliveryPrice(Integer addDeliveryPrice) {
		this.addDeliveryPrice = addDeliveryPrice;
	}
	public Integer getFees() {
		return fees;
	}
	public void setFees(Integer fees) {
		this.fees = fees;
	}
	public Integer getCoupon() {
		return coupon;
	}
	public void setCoupon(Integer coupon) {
		this.coupon = coupon;
	}
	public Integer getAccountPrice() {
		return accountPrice;
	}
	public void setAccountPrice(Integer accountPrice) {
		this.accountPrice = accountPrice;
	}
	public String getAccountFlag() {
		return accountFlag;
	}
	public void setAccountFlag(String accountFlag) {
		this.accountFlag = accountFlag;
	}
	public String getAccountDate() {
		return accountDate;
	}
	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
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
}
