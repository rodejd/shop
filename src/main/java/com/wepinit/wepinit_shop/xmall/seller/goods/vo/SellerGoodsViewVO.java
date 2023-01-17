/*******************************************************************
                     PMGroupKorea Co., Ltd.
Copyright PMGroupKorea Co., Ltd. 2005. All rights reserved.
No part of this work covered by the copyright hereon may be reproduced,
stored in a retrieval system, in any form or by any means, electronic,
mechanical, photocopying, recording or otherwise, without the prior
written permission of PMGroupKorea Co., Ltd.
SOLUTION    :   XMaLL4 for Spring
FILE_NAME   :
DATE        :   2018.1.29
AUTHOR      :   PMGK S/W Engineer   <contact@gmail.com>
DESCRIPTION :
FUNCTIONS   :
HISTORY     :
REMARKS     :
******************************************************************/
package com.wepinit.wepinit_shop.xmall.seller.goods.vo;


public class SellerGoodsViewVO {
	// #####
	// # 판매사별고시맵핑정보(gd_seller_goods_noti)
	// #####
	private int goodsGroupNo = 0;	// 상품군일련번호
	private int itemNo = 0;			// 항목일련번호
//	private String sellerCd = "";	// 판매사코드
	private String goodsGroup = "";	// 상품군 명
	private String item = "";		// 항목
//	private String memo = "";		// 내용
	
	// #####
	// # 상품고시정보(gd_goods_noti)
	// #####
//	private int reqsno = 0; 		// 상품요청일련번호
//	private String item = ""; 		// 항목
//	private String memo = ""; 		// 내용
//	private String regdt = ""; 		// 등록일자
	
	// #####
	// # 추가옵션항목명(gd_seller_goods_add)
	// #####
	private int sno = 0; 			// 일련번호
//	private int reqsno = 0; 		// 상품요청일련번호
	private int step = 0;			// 단계
	private String opt = "";		// 옵션명
	private String addprice = "";	// 추가가격
	
	// #####
	// # 판매사요청상품가격정보(gd_seller_goods_option)
	// #####
	// private int reqsno = 0;		// 상품일련번호
	private String opt1 = "";		// 가격옵션명1
	private String opt2 = "";		// 가격옵션명2
	private String consumer = "";	// 리테일가
	private String price = "";		// 즉시할인가
	private String priceRate = "";	// 즉시할인율
	private String supply = "";		// 바잉원가(정책)
	private String supply2 = "";	// 바잉원가(DATA)
	private String margin = "";		// 공헌이익율
	private String stock = "0";		// 재고량
	
	// #####
	// # 판매사요청상품분류정보(gd_seller_goods_link)
	// #####
	private String category = "";	// 카테고리
	private String sort = "";		// 정렬
	private String categorynm = "";	// 카테고리명
	
	// #####
	// # 판매사요청상품정보(gd_seller_goods) / 상품정보(gd_goods) 
	// #####
	private int reqsno = 0;						// 상품요청일련번호
	private int goodsno = 0;					//상품일련번호
	private String goodscd = "";				//상품코드(SCM번호)
	private String sellerCd = "";				//판매사코드
	private String goodsnm = "";				//상품명(영문)
	private String goodsnmKR = "";				//상품명(국문)
	private String goodsnmCN  = "";				//상품명(중문)
	private String brandno = "";				//브랜드번호
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
	private String open = "";					//판매상태(0:전시대기, 1:판매중, 2:일시중지, 3:영구중지)
	private String extitle = "";				//상품추가정보타이틀
	private String ex1 = "";					//상품추가정보ex1
	private String ex2 = "";					//상품추가정보ex2
	private String ex3 = "";					//상품추가정보ex3
	private String ex4 = "";					//상품추가정보ex4
	private String ex5 = "";					//상품추가정보ex5
	private String ex6 = "";					//상품추가정보ex6
	private String useemoney = "";				//적립금사용유무
	private String usestock = "";				//재고량연동
	private String runout = "";					//품절상품여부
	private String tax = "";					//과세여부
	private String strprice = "";				//가격대체문구
	private String deliverytype = "";			//배송타입
	private String goodsdelivery = "";			//배송가격
	private String usegoodsadd = "";			//옵션사용여부
	private String optnm = "";					//옵션명
	private String opttype = "";				//옵션출력방식
	private String useadd = "";					//추가옵션사용여부
	private String addoptnm = "";				//추가옵션명
	private String relationis = "";				//관련상품타입(0:자동, 1:수동)
	private String relation = "";				//관련상품
	private String imgi = "";					//메인이미지
	private String imgs = "";					//리스트이미지
	private String imgl = "";					//확대이미지
	private String imgm = "";					//상세이미지
	private String shortdesc = "";				//짧은설명
	private String longdesc = "";				//긴설명
	private String memo = "";					//관리메모
	private String mid = "";					//등록자아이디
	private String regdt = "";					//등록일자
	private String moddt = "";					//수정일자
	private int approvalStatus = 0;				//상품승인상태(1:승인요청, 2:승인, 3:반려, 4:승인취소)
	private String approvalMemo = "";			//승인메모
	private String manageYn = "";				//관리상품 유무
	private String delYn = "";					//삭제여부
	private String coupon = "";					//쿠폰명
	private String couponea = "";				//쿠폰개수
	private String couponusecnt = "";			//쿠폰사용개수
	private String coupondate = "";				//쿠폰사용일자
	private String hotYn = "";					//Hot100 유무
	private String vipYn = "";					//VIP 유무
	
	private int approvalReqCd = 0;				//승인요청코드
	private int reqAprlStat = 0;				//상품요청승인상태
	private int reqAprlCd = 0;					//상품요청코드
	private String [] optNmArray;
	private String optexplain;
	private String [] stocks;
	
	
	public int getGoodsGroupNo() {
		return goodsGroupNo;
	}
	public void setGoodsGroupNo(int goodsGroupNo) {
		this.goodsGroupNo = goodsGroupNo;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getGoodsGroup() {
		return goodsGroup;
	}
	public void setGoodsGroup(String goodsGroup) {
		this.goodsGroup = goodsGroup;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public String getAddprice() {
		return addprice;
	}
	public void setAddprice(String addprice) {
		this.addprice = addprice;
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
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getCategorynm() {
		return categorynm;
	}
	public void setCategorynm(String categorynm) {
		this.categorynm = categorynm;
	}
	public int getReqsno() {
		return reqsno;
	}
	public void setReqsno(int reqsno) {
		this.reqsno = reqsno;
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
	public String getBrandno() {
		return brandno;
	}
	public void setBrandno(String brandno) {
		this.brandno = brandno;
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
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
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
	public String getRunout() {
		return runout;
	}
	public void setRunout(String runout) {
		this.runout = runout;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getStrprice() {
		return strprice;
	}
	public void setStrprice(String strprice) {
		this.strprice = strprice;
	}
	public String getDeliverytype() {
		return deliverytype;
	}
	public void setDeliverytype(String deliverytype) {
		this.deliverytype = deliverytype;
	}
	public String getGoodsdelivery() {
		return goodsdelivery;
	}
	public void setGoodsdelivery(String goodsdelivery) {
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
	public String getRelationis() {
		return relationis;
	}
	public void setRelationis(String relationis) {
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
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	public String getModdt() {
		return moddt;
	}
	public void setModdt(String moddt) {
		this.moddt = moddt;
	}
	public int getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(int approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getApprovalMemo() {
		return approvalMemo;
	}
	public void setApprovalMemo(String approvalMemo) {
		this.approvalMemo = approvalMemo;
	}
	public String getManageYn() {
		return manageYn;
	}
	public void setManageYn(String manageYn) {
		this.manageYn = manageYn;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public String getCouponea() {
		return couponea;
	}
	public void setCouponea(String couponea) {
		this.couponea = couponea;
	}
	public String getCouponusecnt() {
		return couponusecnt;
	}
	public void setCouponusecnt(String couponusecnt) {
		this.couponusecnt = couponusecnt;
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
	public int getApprovalReqCd() {
		return approvalReqCd;
	}
	public void setApprovalReqCd(int approvalReqCd) {
		this.approvalReqCd = approvalReqCd;
	}
	public int getReqAprlStat() {
		return reqAprlStat;
	}
	public void setReqAprlStat(int reqAprlStat) {
		this.reqAprlStat = reqAprlStat;
	}
	public int getReqAprlCd() {
		return reqAprlCd;
	}
	public void setReqAprlCd(int reqAprlCd) {
		this.reqAprlCd = reqAprlCd;
	}
	public String[] getOptNmArray() {
		return optNmArray;
	}
	public void setOptNmArray(String[] optNmArray) {
		this.optNmArray = optNmArray;
	}
	public String getOptexplain() {
		return optexplain;
	}
	public void setOptexplain(String optexplain) {
		this.optexplain = optexplain;
	}
	public String[] getStocks() {
		return stocks;
	}
	public void setStocks(String[] stocks) {
		this.stocks = stocks;
	}
}
