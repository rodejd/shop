package com.wepinit.wepinit_shop.xmall.front.vo.pay;

public class FrontPayVO{
	private String ordno = "";
	private int amount = 0;
	private String nameOrder = "";
	private String nameOrder1 = "";
	private String nameOrder2 = "";
	private String email = "";
	private String email1 = "";
	private String email2 = "";
	private String mobileReceiver = "";
	private String mobileReceiver1 = "";
	private String mobileReceiver2= "";
	private String mobileReceiver3 = "";
	private String country = "";
	private String zipcode = "";
	private String address = "";
	private String address_sub = "";
	private String customs_num = "";
	
	private String goodsNmArr[];
	private String[] goodsImgArr;
	private String[] eaArr;
	private int[] priceArr;
	
	public String getOrdno() {
		return ordno;
	}
	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getNameOrder() {
		return nameOrder;
	}
	public void setNameOrder(String nameOrder) {
		this.nameOrder = nameOrder;
	}
	public String getNameOrder1() {
		return nameOrder1;
	}
	public void setNameOrder1(String nameOrder1) {
		this.nameOrder1 = nameOrder1;
	}
	public String getNameOrder2() {
		return nameOrder2;
	}
	public void setNameOrder2(String nameOrder2) {
		this.nameOrder2 = nameOrder2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getMobileReceiver() {
		return mobileReceiver;
	}
	public void setMobileReceiver(String mobileReceiver) {
		this.mobileReceiver = mobileReceiver;
	}
	public String getMobileReceiver1() {
		return mobileReceiver1;
	}
	public void setMobileReceiver1(String mobileReceiver1) {
		this.mobileReceiver1 = mobileReceiver1;
	}
	public String getMobileReceiver2() {
		return mobileReceiver2;
	}
	public void setMobileReceiver2(String mobileReceiver2) {
		this.mobileReceiver2 = mobileReceiver2;
	}
	public String getMobileReceiver3() {
		return mobileReceiver3;
	}
	public void setMobileReceiver3(String mobileReceiver3) {
		this.mobileReceiver3 = mobileReceiver3;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getAddress_sub() {
		return address_sub;
	}
	public void setAddress_sub(String address_sub) {
		this.address_sub = address_sub;
	}
	public String getCustoms_num() {
		return customs_num;
	}
	public void setCustoms_num(String customs_num) {
		this.customs_num = customs_num;
	}
	public String[] getGoodsImgArr() {
		return goodsImgArr;
	}
	public void setGoodsImgArr(String[] goodsImgArr) {
		this.goodsImgArr = goodsImgArr;
	}
	public String[] getEaArr() {
		return eaArr;
	}
	public void setEaArr(String[] eaArr) {
		this.eaArr = eaArr;
	}
	public int[] getPriceArr() {
		return priceArr;
	}
	public void setPriceArr(int[] priceArr) {
		this.priceArr = priceArr;
	}
	public String[] getGoodsNmArr() {
		return goodsNmArr;
	}
	public void setGoodsNmArr(String[] goodsNmArr) {
		this.goodsNmArr = goodsNmArr;
	}
	public void setParam(FrontPayVO payVO) {
		this.setNameOrder(payVO.getNameOrder2() +  payVO.getNameOrder1());
		this.setEmail(payVO.getEmail1() + "@" + payVO.getEmail2());
		this.setMobileReceiver(payVO.getMobileReceiver1() + "-" + payVO.getMobileReceiver2() + "-" + payVO.getMobileReceiver3());
	}
}
