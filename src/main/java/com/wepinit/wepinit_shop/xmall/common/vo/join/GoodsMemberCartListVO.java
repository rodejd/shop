package com.wepinit.wepinit_shop.xmall.common.vo.join;


// join
// gd_goods_link
// gd_goods
// gd_goods_option
public class GoodsMemberCartListVO {
	private String category;
	private String goodsno;
	private String goodsnm;
	private String goodsnmKR;
	private String goodsnmCN;
	private String img;
	private String consumer;
	private String price;
	private String priceRate;
	private String deliveryType;
	private String goodsDelivery;
	private String useEmoney;
	private String usestock;
	private String usegoodsadd;
	private String sellerCd;
	private String opttype;
	private String optNm;
	private String brandnm;
	private String vipYn;
	private int ea;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(String goodsno) {
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getConsumer() {
		return consumer;
	}
	public void setConsumer(String consumer) {
		this.consumer = consumer;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPriceRate() {
		return priceRate;
	}
	public void setPriceRate(String priceRate) {
		this.priceRate = priceRate;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public String getGoodsDelivery() {
		return goodsDelivery;
	}
	public void setGoodsDelivery(String goodsDelivery) {
		this.goodsDelivery = goodsDelivery;
	}
	public String getUseEmoney() {
		return useEmoney;
	}
	public void setUseEmoney(String useEmoney) {
		this.useEmoney = useEmoney;
	}
	public String getUsestock() {
		return usestock;
	}
	public void setUsestock(String usestock) {
		this.usestock = usestock;
	}
	public String getUsegoodsadd() {
		return usegoodsadd;
	}
	public void setUsegoodsadd(String usegoodsadd) {
		this.usegoodsadd = usegoodsadd;
	}
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	public String getOpttype() {
		return opttype;
	}
	public void setOpttype(String opttype) {
		this.opttype = opttype;
	}
	public String getOptNm() {
		return optNm;
	}
	public void setOptNm(String optNm) {
		this.optNm = optNm;
	}
	public String getBrandnm() {
		return brandnm;
	}
	public void setBrandnm(String brandnm) {
		this.brandnm = brandnm;
	}
	public String getVipYn() {
		return vipYn;
	}
	public void setVipYn(String vipYn) {
		this.vipYn = vipYn;
	}
	public int getEa() {
		return ea;
	}
	public void setEa(int ea) {
		this.ea = ea;
	}
	
	@Override
	public String toString() {
		return "GoodsMemberCartListVO [category=" + category + ", goodsno="
				+ goodsno + ", goodsnm=" + goodsnm + ", img=" + img
				+ ", price=" + price
				+ ", deliveryType=" + deliveryType + ", goodsDelivery="
				+ goodsDelivery + ", useEmoney=" + useEmoney + ", usestock="
				+ usestock + "]";
	}
}
