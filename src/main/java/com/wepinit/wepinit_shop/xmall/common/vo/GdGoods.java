package com.wepinit.wepinit_shop.xmall.common.vo;

public class GdGoods {
	private int goodsno = 0;
	private String goodsnm = "";
	private String metatitle = "";
	private String goodscd = "";
	private String origin = "";
	private int brandno = 0;
	private String brandnm = "";
	private int tax = 0;
	private int deliverytype = 0;
	private String keyword = "";
	private String strprice;
	private String shortdesc = "";
	private String longdesc = "";
	private String imgi = "";
	private String imgs = "";
	private String imgl = "";
	private String imgm = "";
	private String memo = "";
	private String regdt;
	private String regDate;
	private String moddt;
	private String modDate;
	private int open = 0;
	private int runout = 0;
	private String optnm = "";
	private String opttype = "";
	private String addoptnm = "";
	private String extitle = "";
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
	private String useadd = "";
	
	// 판매사 추가
	private String sellerNm ="";
	private String sellerCd ="";
	private String approvalStatus ="";
	private String approvalMemo ="";
	
	///temp(상품리스트 조회 조인 컬럼)
	private String price;
	private String reserve;
	private String stock;
	//temp(상품 복사)
	private String newGoodsNo;
	private String oldGoodsNo;
	//iframeGoodsList
	private String category;
	
	//판매자가 등록한 상품 상태
	private String reqAprlStat;
	private String reqAprlCd;
	//판매자가 임시 등록한 제품 번호
	private String reqsno;
	
	//이현빈 상품옵션추가
	private String useGoodsAdd = "";

	
	// 추가 필드
	private String goodsnmKR = "";
	private String goodsnmCN = "";
	private String binCd = "";
	private String simnCd = "";
	private String seasonNm = "";
	private String euYn = "";
	private String shippingOrigin = "";
	private String scmRegdt = "";
	private String manageYn = "";
	private String hotYn = "";
	private String vipYn = "";
	
	private String categoryNm = "";
	private String consumer = "";
	private String priceRate = "";
	private String supply = "";
	private String supply2 = "";
	private String margin = "";
	private String lowestPriceYn = "";
	private String lowestPrice = "";
	private String lowest2PriceYn = "";
	private String lowest2Price = "";
	
	public String getUseGoodsAdd() {
		return useGoodsAdd;
	}
	public void setUseGoodsAdd(String useGoodsAdd) {
		this.useGoodsAdd = useGoodsAdd;
	}
	public String getReqAprlStat() {
		return reqAprlStat;
	}
	public void setReqAprlStat(String reqAprlStat) {
		this.reqAprlStat = reqAprlStat;
	}
	public String getReqAprlCd() {
		return reqAprlCd;
	}
	public void setReqAprlCd(String reqAprlCd) {
		this.reqAprlCd = reqAprlCd;
	}
	public String getReqsno() {
		return reqsno;
	}
	public void setReqsno(String reqsno) {
		this.reqsno = reqsno;
	}
	public String getUseadd() {
		return useadd;
	}
	public void setUseadd(String useadd) {
		this.useadd = useadd;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getNewGoodsNo() {
		return newGoodsNo;
	}
	public void setNewGoodsNo(String newGoodsNo) {
		this.newGoodsNo = newGoodsNo;
	}
	public String getOldGoodsNo() {
		return oldGoodsNo;
	}
	public void setOldGoodsNo(String oldGoodsNo) {
		this.oldGoodsNo = oldGoodsNo;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getModdt() {
		return moddt;
	}
	public void setModdt(String moddt) {
		this.moddt = moddt;
	}
	public String getModDate() {
		return modDate;
	}
	public void setModDate(String modDate) {
		this.modDate = modDate;
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
	public String getBrandnm() {
		return brandnm;
	}
	public void setBrandnm(String brandnm) {
		this.brandnm = brandnm;
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
	public String getExtitle() {
		return extitle;
	}
	public void setExtitle(String extitle) {
		this.extitle = extitle;
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
	public String getSellerNm() {
		return sellerNm;
	}
	public String getSellerCd() {
		return sellerCd;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setSellerNm(String sellerNm) {
		this.sellerNm = sellerNm;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getApprovalMemo() {
		return approvalMemo;
	}
	public void setApprovalMemo(String approvalMemo) {
		this.approvalMemo = approvalMemo;
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
	public String getScmRegdt() {
		return scmRegdt;
	}
	public void setScmRegdt(String scmRegdt) {
		this.scmRegdt = scmRegdt;
	}
	public String getManageYn() {
		return manageYn;
	}
	public void setManageYn(String manageYn) {
		this.manageYn = manageYn;
	}
	public String getCategoryNm() {
		return categoryNm;
	}
	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}
	public String getConsumer() {
		return consumer;
	}
	public void setConsumer(String consumer) {
		this.consumer = consumer;
	}
	public String getPriceRate() {
		return priceRate;
	}
	public void setPriceRate(String priceRate) {
		this.priceRate = priceRate;
	}
	public String getSupply() {
		return supply;
	}
	public void setSupply(String supply) {
		this.supply = supply;
	}
	public String getSupply2() {
		return supply2;
	}
	public void setSupply2(String supply2) {
		this.supply2 = supply2;
	}
	public String getMargin() {
		return margin;
	}
	public void setMargin(String margin) {
		this.margin = margin;
	}
	public String getLowestPriceYn() {
		return lowestPriceYn;
	}
	public void setLowestPriceYn(String lowestPriceYn) {
		this.lowestPriceYn = lowestPriceYn;
	}
	public String getLowestPrice() {
		return lowestPrice;
	}
	public void setLowestPrice(String lowestPrice) {
		this.lowestPrice = lowestPrice;
	}
	public String getLowest2PriceYn() {
		return lowest2PriceYn;
	}
	public void setLowest2PriceYn(String lowest2PriceYn) {
		this.lowest2PriceYn = lowest2PriceYn;
	}
	public String getLowest2Price() {
		return lowest2Price;
	}
	public void setLowest2Price(String lowest2Price) {
		this.lowest2Price = lowest2Price;
	}
	public String getHotYn() {
		return hotYn;
	}
	public void setHotYn(String hotYn) {
		this.hotYn = hotYn;
	}
	public String getVipYn() {
		return vipYn;
	}
	public void setVipYn(String vipYn) {
		this.vipYn = vipYn;
	}
}
