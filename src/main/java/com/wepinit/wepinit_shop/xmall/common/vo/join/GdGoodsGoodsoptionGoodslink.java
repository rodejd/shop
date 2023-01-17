package com.wepinit.wepinit_shop.xmall.common.vo.join;

import java.util.Date;

public class GdGoodsGoodsoptionGoodslink {
	//gd_goods
	private int goodsno = 0;					//상품일련번호
	private String goodscd = "";				//상품코드(SCM번호)
	private String sellerCd = "";				//판매사코드
	private String goodsnm = "";				//상품명(영문)
	private String goodsnmKR = "";				//상품명(국문)
	private String goodsnmCN = "";				//상품명(중문)
	private int brandno = 0;					//브랜드번호
	private String brandnm = "";				//브랜드명
	private String origin = "";					//원산지명
	private String binCd = "";					//Bin코드
	private String simnCd = "";					//Simn코드
	private String seasonNm = "";				//시즌
	private String euYn = "";					//EU 구분
	private String shippingOrigin = "";			//배송출발국가
	private String scmRegdt;					//SCM 등록일
	private String lowestPriceYn = "";			//최저가여부
	private int lowestPrice = 0;			//최저가
	private String lowest2PriceYn = "";			//차저가여부
	private int lowest2Price = 0;			//차저가
	private String metatitle = "";				//상품메타타이틀여부
	private String keyword = "";				//키워드
	private int open = 0;						//판매상태(0:전시대기, 1:판매중, 2:일시중지, 3:영구중지)
	private String extitle = "";				//상품추가정보타이틀
	private String ex1 = "";					//상품추가정보ex1
	private String ex2 = "";					//상품추가정보ex2
	private String ex3 = "";					//상품추가정보ex3
	private String ex4 = "";					//상품추가정보ex4
	private String ex5 = "";					//상품추가정보ex5
	private String ex6 = "";					//상품추가정보ex6
	private String useemoney = "";				//적립금사용유무
	private String usestock = "";				//재고량연동
	private int runout = 0;						//품절상품여부
	private int tax = 0;						//과세여부
	private String strprice = "";				//가격대체문구
	private int deliverytype = 0;				//배송타입
	private int goodsdelivery = 0;			//배송가격
	private String usegoodsadd = "";			//옵션사용여부
	private String optnm = "";					//옵션명
	private String opttype = "";				//옵션출력방식
	private String useadd = "";					//추가옵션사용여부
	private String addoptnm = "";				//추가옵션명
	private int relationis = 0;					//관련상품타입(0:자동, 1:수동)
	private String relation = "";				//관련상품
	private String imgi = "";					//메인이미지
	private String imgs = "";					//리스트이미지
	private String imgl = "";					//확대이미지
	private String imgm = "";					//상세이미지
	private String shortdesc = "";				//짧은설명
	private String longdesc = "";				//긴설명
	private String memo = "";					//관리메모
	private String mid = "";					//등록자아이디
	private Date regdt = null;					//등록일자
	private String regDate = "";				//등록일자(String)
	private Date moddt = null;					//수정일자
	private String modDate = "";				//수정일자(String)
	private String coupon = "";					//쿠폰명
	private int couponea = 0;					//쿠폰개수
	private String couponusecn = "";			//쿠폰사용개수
	private String coupondate = "";				//쿠폰사용일자
	private String hotYn = "";					//Hot100 유무
	private String vipYn = "";					//VIP 유무
	
	//gd_goods_option
	private int sno;
	private String opt1;
	private String opt2;
	private int consumer;
	private int price;
	private int dcPrice;
	private int priceRate;
	private int supply;
	private int supply2;
	private int stock;
	private int link;
	private String optno;
	
	//gd_goods_link
	private String category;
	private int sort;
	private int hidden;
	
	//alias
	private String img;
	private String sellerNm = "";
	private int addoptFlag;
	private int likes;
	private int buycnt;
	private int reviewcnt;
	private String ordno = "";
	private int reserve = 0;

	private String cpCouponcd; // 쿠폰번호			
	private String cpCouponprice;	// 쿠폰금액
	private String cpMaxprice;	// 최대 할인액
	private String cpCouponskin;	// 쿠폰적용 스킨
	
	public int getReviewcnt() {
		return reviewcnt;
	}
	public void setReviewcnt(int reviewcnt) {
		this.reviewcnt = reviewcnt;
	}
	public String getOrdno() {
		return ordno;
	}
	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}
	public void setCpMaxprice(String cpMaxprice) {
		this.cpMaxprice = cpMaxprice;
	}
	public String getCpCouponcd() {
		return cpCouponcd;
	}
	public void setCpCouponcd(String cpCouponcd) {
		this.cpCouponcd = cpCouponcd;
	}
	public String getCpCouponprice() {
		return cpCouponprice;
	}
	public void setCpCouponprice(String cpCouponprice) {
		this.cpCouponprice = cpCouponprice;
	}
	public String getCpMaxprice() {
		return cpMaxprice;
	}
	public void setMpCaxprice(String cpMaxprice) {
		this.cpMaxprice = cpMaxprice;
	}
	public String getCpCouponskin() {
		return cpCouponskin;
	}
	public void setCpCouponskin(String cpCouponskin) {
		this.cpCouponskin = cpCouponskin;
	}
	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
	}
	public String getGoodscd() {
		return goodscd;
	}
	public void setGoodscd(String goodscd) {
		this.goodscd = goodscd;
	}
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
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
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
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
	public String getMetatitle() {
		return metatitle;
	}
	public void setMetatitle(String metatitle) {
		this.metatitle = metatitle;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getOpen() {
		return open;
	}
	public void setOpen(int open) {
		this.open = open;
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
	public String getUseemoney() {
		return useemoney;
	}
	public void setUseemoney(String useemoney) {
		this.useemoney = useemoney;
	}
	public String getUsestock() {
		return usestock;
	}
	public void setUsestock(String usestock) {
		this.usestock = usestock;
	}
	public int getRunout() {
		return runout;
	}
	public void setRunout(int runout) {
		this.runout = runout;
	}
	public int getTax() {
		return tax;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}
	public String getStrprice() {
		return strprice;
	}
	public void setStrprice(String strprice) {
		this.strprice = strprice;
	}
	public int getDeliverytype() {
		return deliverytype;
	}
	public void setDeliverytype(int deliverytype) {
		this.deliverytype = deliverytype;
	}
	public int getGoodsdelivery() {
		return goodsdelivery;
	}
	public void setGoodsdelivery(int goodsdelivery) {
		this.goodsdelivery = goodsdelivery;
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
	public String getUseadd() {
		return useadd;
	}
	public void setUseadd(String useadd) {
		this.useadd = useadd;
	}
	public String getUsegoodsadd() {
		return usegoodsadd;
	}
	public void setUsegoodsadd(String usegoodsadd) {
		this.usegoodsadd = usegoodsadd;
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
	public String getImgl() {
		return imgl;
	}
	public void setImgl(String imgl) {
		this.imgl = imgl;
	}
	public String getImgm() {
		return imgm;
	}
	public void setImgm(String imgm) {
		this.imgm = imgm;
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Date getModdt() {
		return moddt;
	}
	public void setModdt(Date moddt) {
		this.moddt = moddt;
	}
	public String getModDate() {
		return modDate;
	}
	public void setModDate(String modDate) {
		this.modDate = modDate;
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
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
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
	public int getSupply() {
		return supply;
	}
	public void setSupply(int supply) {
		this.supply = supply;
	}
	public int getSupply2() {
		return supply2;
	}
	public void setSupply2(int supply2) {
		this.supply2 = supply2;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getLink() {
		return link;
	}
	public void setLink(int link) {
		this.link = link;
	}
	public String getOptno() {
		return optno;
	}
	public void setOptno(String optno) {
		this.optno = optno;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getHidden() {
		return hidden;
	}
	public void setHidden(int hidden) {
		this.hidden = hidden;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getSellerNm() {
		return sellerNm;
	}
	public void setSellerNm(String sellerNm) {
		this.sellerNm = sellerNm;
	}
	public int getAddoptFlag() {
		return addoptFlag;
	}
	public void setAddoptFlag(int addoptFlag) {
		this.addoptFlag = addoptFlag;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}	
	public int getBuycnt() {
		return buycnt;
	}
	public void setBuycnt(int buycnt) {
		this.buycnt = buycnt;
	}
	public int getDcPrice() {
		return dcPrice;
	}
	public void setDcPrice(int dcPrice) {
		this.dcPrice = dcPrice;
	}
	public int getReserve() {
		return reserve;
	}
	public void setReserve(int reserve) {
		this.reserve = reserve;
	}
	@Override
	public String toString() {
		return "GdGoodsGoodsoptionGoodslink [goodsno=" + goodsno
				+ ", goodscd=" + goodscd
				+ ", sellerCd=" + sellerCd
				+ ", goodsnm=" + goodsnm
				+ ", goodsnmKR=" + goodsnmKR
				+ ", goodsnmCN=" + goodsnmCN
				+ ", brandno=" + brandno
				+ ", origin=" + origin
				+ ", binCd=" + binCd
				+ ", simnCd=" + simnCd
				+ ", seasonNm=" + seasonNm
				+ ", euYn=" + euYn
				+ ", shippingOrigin=" + shippingOrigin
				+ ", scmRegdt=" + scmRegdt
				+ ", lowestPriceYn=" + lowestPriceYn
				+ ", lowestPrice=" + lowestPrice
				+ ", lowest2PriceYn=" + lowest2PriceYn
				+ ", lowest2Price=" + lowest2Price
				+ ", metatitle=" + metatitle
				+ ", keyword=" + keyword
				+ ", open=" + open
				+ ", extitle=" + extitle
				+ ", ex1=" + ex1
				+ ", ex2=" + ex2
				+ ", ex3=" + ex3
				+ ", ex4=" + ex4
				+ ", ex5=" + ex5
				+ ", ex6=" + ex6
				+ ", useemoney=" + useemoney
				+ ", usestock=" + usestock
				+ ", runout=" + runout
				+ ", tax=" + tax
				+ ", strprice=" + strprice
				+ ", deliverytype=" + deliverytype
				+ ", goodsdelivery=" + goodsdelivery
				+ ", optnm=" + optnm
				+ ", opttype=" + opttype
				+ ", addoptnm=" + addoptnm
				+ ", useadd=" + useadd
				+ ", usegoodsadd=" + usegoodsadd
				+ ", relationis=" + relationis
				+ ", relation=" + relation
				+ ", imgi=" + imgi
				+ ", imgs=" + imgs
				+ ", imgl=" + imgl
				+ ", imgm=" + imgm
				+ ", shortdesc=" + shortdesc
				+ ", longdesc=" + longdesc
				+ ", memo=" + memo
				+ ", mid=" + mid
				+ ", regdt=" + regdt
				+ ", regDate=" + regDate
				+ ", moddt=" + moddt
				+ ", modDate=" + modDate
				+ ", coupon=" + coupon
				+ ", couponea=" + couponea
				+ ", couponusecn=" + couponusecn
				+ ", coupondate=" + coupondate
				+ ", hotYn=" + hotYn
				+ ", vipYn=" + vipYn
				+ ", sno=" + sno
				+ ", opt1=" + opt1
				+ ", opt2=" + opt2
				+ ", consumer=" + consumer
				+ ", price=" + price
				+ ", priceRate=" + priceRate
				+ ", supply=" + supply
				+ ", supply2=" + supply2
				+ ", stock=" + stock
				+ ", link=" + link
				+ ", optno=" + optno
				+ ", category=" + category
				+ ", sort=" + sort
				+ ", hidden=" + hidden
				+ ", img=" + img
				+ ", sellerNm=" + sellerNm
				+ ", addoptFlag=" + addoptFlag
				+ ", likes=" + likes
				+ ", buycnt=" + buycnt
				+ ", reserve=" + reserve + "]";
	}
	
}
