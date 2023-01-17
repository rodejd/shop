package com.wepinit.wepinit_shop.xmall.api.vo;

import java.util.List;

public class ProductVO {
	private String key = "";
	private String shop_id = "";
	private int goodsno = 0;
	private String goodsCd = "";
	private String goodsNmEN = "";
	private String goodsNmKR = "";
	private String goodsNmCN = "";
	private String categoryCd = "";
	private String sellerCd = "";
	private String brandCd = "";
	private String binCd = "";
	private String simnCd = "";
	private String seasonNm = "";
	private String origin = "";
	private String euYn = "";
	private String shippingOrigin = "";
	private int consumer = 0;
	private int price = 0;
	private int priceRate = 0;
	private String b2bYn = "";
	private int priceB2b = 0;
	private int priceRateB2b = 0;
	private int supplyPrice1 = 0;
	private int supplyPrice2 = 0;
	private int margin = 0;
	private int stock = 0;
	private String lowestPriceYn = "";
	private int lowestPrice = 0;
	private String lowest2PriceYn = "";
	private int lowest2Price = 0;
	private String longdesc = "";
	private String img_i = "";
	private String img_s = "";
	private String img_m = "";
	private String img_l = "";
	private String scmRegdt = "";
	private String usegoodsadd = "0";
	private String optnm = "";
	private String open = "";
	private int priceMyRitz = 0;
	
	private List<ProductImageVO> images;
	private List<ProductOptionVO> options;
	
	
	public String getB2bYn() {
		return b2bYn;
	}
	public void setB2bYn(String b2bYn) {
		this.b2bYn = b2bYn;
	}
	public int getPriceB2b() {
		return priceB2b;
	}
	public void setPriceB2b(int priceB2b) {
		this.priceB2b = priceB2b;
	}
	public int getPriceRateB2b() {
		return priceRateB2b;
	}
	public void setPriceRateB2b(int priceRateB2b) {
		this.priceRateB2b = priceRateB2b;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
	}
	public String getGoodsCd() {
		return goodsCd;
	}
	public void setGoodsCd(String goodsCd) {
		this.goodsCd = goodsCd;
	}
	public String getGoodsNmEN() {
		return goodsNmEN;
	}
	public void setGoodsNmEN(String goodsNmEN) {
		this.goodsNmEN = goodsNmEN;
	}
	public String getGoodsNmKR() {
		return goodsNmKR;
	}
	public void setGoodsNmKR(String goodsNmKR) {
		this.goodsNmKR = goodsNmKR;
	}
	public String getGoodsNmCN() {
		return goodsNmCN;
	}
	public void setGoodsNmCN(String goodsNmCN) {
		this.goodsNmCN = goodsNmCN;
	}
	public String getCategoryCd() {
		return categoryCd;
	}
	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	public String getBrandCd() {
		return brandCd;
	}
	public void setBrandCd(String brandCd) {
		this.brandCd = brandCd;
	}
	public String getBinCd() {
		return binCd;
	}
	public void setBinCd(String binCd) {
		this.binCd = binCd;
	}
	public String getSimnCd() {
		return simnCd;
	}
	public void setSimnCd(String simnCd) {
		this.simnCd = simnCd;
	}
	public String getSeasonNm() {
		return seasonNm;
	}
	public void setSeasonNm(String seasonNm) {
		this.seasonNm = seasonNm;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getEuYn() {
		return euYn;
	}
	public void setEuYn(String euYn) {
		this.euYn = euYn;
	}
	public String getShippingOrigin() {
		return shippingOrigin;
	}
	public void setShippingOrigin(String shippingOrigin) {
		this.shippingOrigin = shippingOrigin;
	}
	public int getConsumer() {
		return consumer;
	}
	public void setConsumer(int consumer) {
		this.consumer = consumer;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPriceRate() {
		return priceRate;
	}
	public void setPriceRate(int priceRate) {
		this.priceRate = priceRate;
	}
	public int getSupplyPrice1() {
		return supplyPrice1;
	}
	public void setSupplyPrice1(int supplyPrice1) {
		this.supplyPrice1 = supplyPrice1;
	}
	public int getSupplyPrice2() {
		return supplyPrice2;
	}
	public void setSupplyPrice2(int supplyPrice2) {
		this.supplyPrice2 = supplyPrice2;
	}
	public int getMargin() {
		return margin;
	}
	public void setMargin(int margin) {
		this.margin = margin;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getLowestPriceYn() {
		return lowestPriceYn;
	}
	public void setLowestPriceYn(String lowestPriceYn) {
		this.lowestPriceYn = lowestPriceYn;
	}
	public int getLowestPrice() {
		return lowestPrice;
	}
	public void setLowestPrice(int lowestPrice) {
		this.lowestPrice = lowestPrice;
	}
	public String getLowest2PriceYn() {
		return lowest2PriceYn;
	}
	public void setLowest2PriceYn(String lowest2PriceYn) {
		this.lowest2PriceYn = lowest2PriceYn;
	}
	public int getLowest2Price() {
		return lowest2Price;
	}
	public void setLowest2Price(int lowest2Price) {
		this.lowest2Price = lowest2Price;
	}
	public String getLongdesc() {
		return longdesc;
	}
	public void setLongdesc(String longdesc) {
		this.longdesc = longdesc;
	}
	public String getImg_i() {
		return img_i;
	}
	public void setImg_i(String img_i) {
		this.img_i = img_i;
	}
	public String getImg_s() {
		return img_s;
	}
	public void setImg_s(String img_s) {
		this.img_s = img_s;
	}
	public String getImg_m() {
		return img_m;
	}
	public void setImg_m(String img_m) {
		this.img_m = img_m;
	}
	public String getImg_l() {
		return img_l;
	}
	public void setImg_l(String img_l) {
		this.img_l = img_l;
	}
	public String getScmRegdt() {
		return scmRegdt;
	}
	public void setScmRegdt(String scmRegdt) {
		this.scmRegdt = scmRegdt;
	}	
	public String getUsegoodsadd() {
		return usegoodsadd;
	}
	public void setUsegoodsadd(String usegoodsadd) {
		this.usegoodsadd = usegoodsadd;
	}	
	public String getOptnm() {
		return optnm;
	}
	public void setOptnm(String optnm) {
		this.optnm = optnm;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public int getPriceMyRitz() {
		return priceMyRitz;
	}
	public void setPriceMyRitz(int priceMyRitz) {
		this.priceMyRitz = priceMyRitz;
	}
	
	public List<ProductImageVO> getImages() {
		return images;
	}
	public void setImages(List<ProductImageVO> images) {
		this.images = images;
	}
	public List<ProductOptionVO> getOptions() {
		return options;
	}
	public void setOptions(List<ProductOptionVO> options) {
		this.options = options;
	}
	
	@Override
	public String toString() {
		return "ProductVO [key=" + key
				 + ", shop_id=" + shop_id
				 + ", goodsno=" + goodsno
				 + ", goodsCd=" + goodsCd
				 + ", goodsNmEN=" + goodsNmEN
				 + ", goodsNmKR=" + goodsNmKR
				 + ", goodsNmCN=" + goodsNmCN
				 + ", categoryCd=" + categoryCd
				 + ", sellerCd=" + sellerCd
				 + ", brandCd=" + brandCd
				 + ", binCd=" + binCd
				 + ", simnCd=" + simnCd
				 + ", seasonNm=" + seasonNm
				 + ", origin=" + origin
				 + ", euYn=" + euYn
				 + ", shippingOrigin=" + shippingOrigin
				 + ", consumer=" + consumer
				 + ", price=" + price
				 + ", priceRate=" + priceRate
				 + ", supplyPrice1=" + supplyPrice1
				 + ", supplyPrice2=" + supplyPrice2
				 + ", margin=" + margin
				 + ", stock=" + stock
				 + ", lowestPriceYn=" + lowestPriceYn
				 + ", lowestPrice=" + lowestPrice
				 + ", lowest2PriceYn=" + lowest2PriceYn
				 + ", lowest2Price=" + lowest2Price
				 + ", longdesc=" + longdesc
				 + ", img_i=" + img_i
				 + ", img_s=" + img_s
				 + ", img_m=" + img_m
				 + ", img_l=" + img_l
				 + ", scmRegdt=" + scmRegdt
				 + ", usegoodsadd=" + usegoodsadd
				 + ", optnm=" + optnm
				 + ", images=" + images
				 + ", options=" + options
				 + ", open=" + open
				 + ", priceMyRitz=" + priceMyRitz + "]";
	}
}
