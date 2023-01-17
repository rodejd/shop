package com.wepinit.wepinit_shop.xmall.common.vo;

import java.text.DecimalFormat;

public class GdDeliveryPolicy {

	private int no;					// 번호
	private String rDelivery;		// 정책명
	private String rFree;			// 택배 무료 주문금액
	private String rDeliType;		// 선/착불 여부
	private String rDefault;		// 택배요금
	private String rDefaultMsg;		// 기본메세지
	private String freeDelivery;	// 상품별 배송정책-무료배송상품(0,1)
	private String goodsDelivery;	// 상품별 배송정책-상품별 배송비(0,1)
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getrFree() {
		return rFree;
	}
	public void setrFree(String rFree) {
		this.rFree = rFree;
	}
	
	public String getrDefault() {
		return this.rDefault;
	}
	public void setrDefault(String rDefault) {
		this.rDefault = rDefault;
	}
	public String getrDefaultMsg() {
		return rDefaultMsg;
	}
	public void setrDefaultMsg(String rDefaultMsg) {
		this.rDefaultMsg = rDefaultMsg;
	}
	public String getrDelivery() {
		return rDelivery;
	}
	public void setrDelivery(String rDelivery) {
		this.rDelivery = rDelivery;
	}
	public String getrDeliType() {
		return rDeliType;
	}
	public void setrDeliType(String rDeliType) {
		this.rDeliType = rDeliType;
	}
	public String getFreeDelivery() {
		return freeDelivery;
	}
	public void setFreeDelivery(String freeDelivery) {
		this.freeDelivery = freeDelivery;
	}
	public String getGoodsDelivery() {
		return goodsDelivery;
	}
	public void setGoodsDelivery(String goodsDelivery) {
		this.goodsDelivery = goodsDelivery;
	}
	public String getDeliveryInfo() {
		DecimalFormat format = new DecimalFormat("#,###");
		StringBuffer deliveryBuffer = new StringBuffer();
		deliveryBuffer	.append("[").append(this.rDelivery).append("-")
						.append(this.rDeliType).append("]")
						.append(" ").append(format.format(Integer.parseInt(this.rDefault))).append("원");
		return deliveryBuffer.toString();			
	}
	@Override
	public String toString() {
		return "GdDeliveryPolicy [no=" + no + ", rDelivery=" + rDelivery
				+ ", rFree=" + rFree + ", rDeliType=" + rDeliType
				+ ", rDefault=" + rDefault + ", rDefaultMsg=" + rDefaultMsg
				+ ", freeDelivery=" + freeDelivery + ", goodsDelivery="
				+ goodsDelivery + "]";
	}
	
	
}
