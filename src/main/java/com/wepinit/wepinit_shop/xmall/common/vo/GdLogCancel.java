package com.wepinit.wepinit_shop.xmall.common.vo;

public class GdLogCancel {
	private long ordno	;//주문번호
	private int itemno	;//항목번호
	private int cancel	;//취소번호
	private int prev	;//이전
	private int next	;//다음
	private String goodsnm;//상품명(영문)
	private String goodsnmKR;//상품명(국문)
	private String goodsnmCN;//상품명(중문)
	private int ea		;//갯수
	private int deliveryPrice ;//배송비
	private int addDeliveryPrice ;//추가배송비
	private int goodsno ;	//상품번호
	private int paymentTerms ;	//지불방식
	private String opt	;	//상품옵션번호
	
	//화면
	private String rStepi;
	
	
	
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
	}
	public int getPaymentTerms() {
		return paymentTerms;
	}
	public void setPaymentTerms(int paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	public int getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(int deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	public int getAddDeliveryPrice() {
		return addDeliveryPrice;
	}
	public void setAddDeliveryPrice(int addDeliveryPrice) {
		this.addDeliveryPrice = addDeliveryPrice;
	}
	public long getOrdno() {
		return ordno;
	}
	public void setOrdno(long ordno) {
		this.ordno = ordno;
	}
	public int getItemno() {
		return itemno;
	}
	public void setItemno(int itemno) {
		this.itemno = itemno;
	}
	public int getCancel() {
		return cancel;
	}
	public void setCancel(int cancel) {
		this.cancel = cancel;
	}
	public int getPrev() {
		return prev;
	}
	public void setPrev(int prev) {
		this.prev = prev;
	}
	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
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
	public int getEa() {
		return ea;
	}
	public void setEa(int ea) {
		this.ea = ea;
	}
	public String getrStepi() {
		return rStepi;
	}
	public void setrStepi(String rStepi) {
		this.rStepi = rStepi;
	}
	
}
