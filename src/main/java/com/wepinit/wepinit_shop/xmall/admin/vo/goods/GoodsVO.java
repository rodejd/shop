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
package com.wepinit.wepinit_shop.xmall.admin.vo.goods;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.seller.goods.vo.SellerGoodsViewVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public class GoodsVO extends PageMaker {

	// 검색 조건
	private String schRegty = "";				//구분(scmapi:SCM 연동, direct:직등록)
	private String[] schRegdt;					//상품등록일 배열
	private String schRegdtS = "";				//검색 시작일
	private String schRegdtE = "";				//검색 종료일
	private String schSellerCd = "";			//부틱 코드
	private String schSellerNm = "";			//부틱 명
	private String[] cate;						//카테고리 배열
	private String schCate = "";				//카테고리 전체일련번호
	private String schBrand = "";				//브랜드
	private String schSeasonNm = "";			//시즌
	private String schKey = "";					//검색필드
	private String schWord = "";				//검색어
	private String[] schWords = null;			//검색어 배열
	private String schOpen = "";				//판매상태
	private String schSoldOut = "";				//재고유무
	private String schSpec = "";				//특별구좌
	private String schWish = "";				//Wish상품
	private String schManageYn = "";			//관리상품여부
	private String sort = "";           		//정렬 
	private int totalCnt;						//총 건수
	
	// 상품기본정보
	private int goodsno = 0;					//상품일련번호
	private String goodscd = "";				//상품코드(SCM번호)
	private String sellerCd = "";				//판매사코드
	private String goodsnm = "";				//상품명(영문)
	private String goodsnmKR = "";				//상품명(국문)
	private String goodsnmCN = "";				//상품명(중문)
	private int brandno = 0;					//브랜드번호
	private String origin = "";					//원산지명
	private String binCd = "";					//Bin코드
	private String simnCd = "";					//Simn코드
	private String seasonNm = "";				//시즌
	private String euYn = "";					//EU 구분
	private String shippingOrigin = "";			//배송출발국가
	private String scmRegdt;					//SCM 등록일
	private String lowestPriceYn = "";			//최저가여부
	private int lowestPrice = 0;				//최저가
	private String lowest2PriceYn = "";			//차저가여부
	private int lowest2Price = 0;				//차저가
	private String metaTitle = "";				//상품메타타이틀여부
	private String keyword = "";				//키워드
	private String open = "0";					//판매상태(0:전시대기, 1:판매중, 2:일시중지, 3:영구중지)
	private String[] exTitle = new String[]{};	//상품추가정보타이틀
	private String ex1 = "";					//상품추가정보ex1
	private String ex2 = "";					//상품추가정보ex2
	private String ex3 = "";					//상품추가정보ex3
	private String ex4 = "";					//상품추가정보ex4
	private String ex5 = "";					//상품추가정보ex5
	private String ex6 = "";					//상품추가정보ex6
	private String useEmoney = "";				//적립금사용유무
	private String usestock = "";				//재고량연동
	private int runout = 0;						//품절상품여부
	private int tax = 1;						//과세여부
	private String strprice = "";				//가격대체문구
	private int deliveryType = 0;				//배송타입
	private int goodsDelivery = 0;				//배송가격
	private String usegoodsadd = "0";			//옵션사용여부
	private String optnm = "";					//옵션명
	private String opttype = "single";			//옵션출력방식
	private String useadd = "0";				//추가옵션사용여부
	private String addoptnm = "";				//추가옵션명
	private int relationis = 0;					//관련상품타입(0:자동, 1:수동)
	private String relation = "";				//관련상품
	private String imgI	= "";					//메인이미지
	private String imgS	= "";					//리스트이미지
	private String imgL	= "";					//확대이미지
	private String imgM	= "";					//상세이미지
	private String shortdesc = "";				//짧은설명
	private String longdesc = "";				//긴설명
	private String memo = "";					//관리메모
	private String mId = "";					//등록자아이디
	private String regdt = "";					//등록일자
	private String moddt = "";					//수정일자
	private String approvalStatus = "";			//상품승인상태(1:승인요청, 2:승인, 3:반려, 4:승인취소)
	private String approvalMemo = "";			//상품승인메모
	private String manageYn = "";				//관리상품 유무
	private String delYn = "";					//삭제여부
	private String coupon = "";					//쿠폰명
	private int couponEa = 0;					//쿠폰개수
	private String couponUsecnt = "";			//쿠폰사용개수
	private String couponDate = "";				//쿠폰사용일자
	private String hotYn = "";					//Hot100 유무
	private String vipYn = "";					//VIP 유무

	//필수 옵션
	private String[] opt1[];
	private String[] opt2[];
	private int[][] option = new int[][]{};
	
	//추가 옵션
	private String[] addOptionNm;
	private String[] addOptionOpt;
	private String[] addOptionPrice;
	
	// 기타 등등
	private String mode;
	private String category = "001";
	private String name;
	private int goodsDelivery2 = 0;
	private String strResult;
	private String strHieen;			//화면에 모드별로 출력여부
	private String use;	//판매사 추가
	
	// open modify
	private String goodsArr;
	private String openVal;
	
	// 상품 이미지
	private String[][] imgsArray;
	final String[] strImg = new String[]{"원본(확대)이미지", "메인노출이미지", "리스트이미지", "상세이미지"};
	
	// 수정페이지
	private List<GdGoods> goodsList = null;
	private GdGoods goodsObj = null;
	
	private List<GdCategory> categoryList = null;
	private GdCategory categoryObj = null;
	
	private List<GdGoodsAdd> goodsAddList = null;
	private GdGoodsAdd goodsAddObj = null;
	
	private List<GdGoodsBrand> goodsBrandList = null;
	private GdGoodsBrand goodsBrandObj = null;
	
	private List<String> goodsSeasonList = null;
	
	private List<GdGoods> originList = null;
	
	private List<GdGoodsOption> goodsOptionList;
	
	private String[] optNmArray;

	private String consumer		= "";	//리테일가
	private String price1		= "";	//즉시할인가
	private String priceRate	= "";	//즉시할인율
	private String supply		= "";	//바잉원가(정책)
	private String supply2		= "";	//바잉원가(DATA)
	private String margin		= "";	//공헌이익율
	private String[] optTitle	= null;	//사용자정의 옵션 타이틀
	private int intTotalStock = 0;		//재고량

	private String priceMyRitz	= "";	//MyRitz공급가
	
	private int relationCnt = 0;		//관련 상품
	private List<Map<String, Object>> relationList = null;
	
	private String itemNo = "";
	private int goodsGroupNo = 0;
	private List<SellerGoodsViewVO> goodsNotiList = null;
	
	private String sellerYn = "";	//판매사여부
	private int reqsno = 0;
	
	private List<MultipartFile> images;
	
	
	public String getPriceMyRitz() {
		return priceMyRitz;
	}
	public void setPriceMyRitz(String priceMyRitz) {
		this.priceMyRitz = priceMyRitz;
	}
	public String getSchRegty() {
		return schRegty;
	}
	public void setSchRegty(String schRegty) {
		this.schRegty = schRegty;
	}
	public String[] getSchRegdt() {
		return schRegdt;
	}
	public void setSchRegdt(String[] schRegdt) {
		this.schRegdt = schRegdt;
	}
	public String getSchRegdtS() {
		if (schRegdt != null) {
			if (schRegdt.length > 0) {
				return schRegdt[0];
			}
			return schRegdtS;
		}
		return schRegdtS;
	}
	public void setSchRegdtS(String schRegdtS) {
		this.schRegdtS = schRegdtS;
	}
	public String getSchRegdtE() {
		if (schRegdt != null) {
			if (schRegdt.length > 1) {
				return schRegdt[1];
			}
			return schRegdtE;
		}
		return schRegdtE;
	}
	public void setSchRegdtE(String schRegdtE) {
		this.schRegdtE = schRegdtE;
	}
	public String getSchSellerCd() {
		return schSellerCd;
	}
	public void setSchSellerCd(String schSellerCd) {
		this.schSellerCd = schSellerCd;
	}
	public String getSchSellerNm() {
		return schSellerNm;
	}
	public void setSchSellerNm(String schSellerNm) {
		this.schSellerNm = schSellerNm;
	}
	public String[] getCate() {
		return cate;
	}
	public void setCate(String[] cate) {
		this.cate = cate;
	}
	public String getSchCate() {
		return schCate;
	}
	public void setSchCate(String schCate) {
		this.schCate = schCate;
	}
	public String getSchBrand() {
		return schBrand;
	}
	public void setSchBrand(String schBrand) {
		this.schBrand = schBrand;
	}
	public String getSchSeasonNm() {
		return schSeasonNm;
	}
	public void setSchSeasonNm(String schSeasonNm) {
		this.schSeasonNm = schSeasonNm;
	}
	public String getSchKey() {
		return schKey;
	}
	public void setSchKey(String schKey) {
		this.schKey = schKey;
	}
	public String getSchWord() {
		return schWord;
	}
	public void setSchWord(String schWord) {
		this.schWord = schWord;
		if (!"".equals(schWord) && schWord.split(" ").length > 0)
			this.schWords = schWord.split(" ");
	}
	public String[] getSchWords() {
		return schWords;
	}
	public void setSchWords(String[] schWords) {
		this.schWords = schWords;
	}
	public String getSchOpen() {
		return schOpen;
	}
	public void setSchOpen(String schOpen) {
		this.schOpen = schOpen;
	}
	public String getSchSoldOut() {
		return schSoldOut;
	}
	public void setSchSoldOut(String schSoldOut) {
		this.schSoldOut = schSoldOut;
	}
	public String getSchSpec() {
		return schSpec;
	}
	public void setSchSpec(String schSpec) {
		this.schSpec = schSpec;
	}
	public String getSchWish() {
		return schWish;
	}
	public void setSchWish(String schWish) {
		this.schWish = schWish;
	}
	public String getSchManageYn() {
		return schManageYn;
	}
	public void setSchManageYn(String schManageYn) {
		this.schManageYn = schManageYn;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
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
	public String getMetaTitle() {
		return metaTitle;
	}
	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
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
	public String[] getExTitle() {
		return exTitle;
	}
	public void setExTitle(String[] exTitle) {
		this.exTitle = exTitle;
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
	public int getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(int deliveryType) {
		this.deliveryType = deliveryType;
	}
	public int getGoodsDelivery() {
		return goodsDelivery;
	}
	public void setGoodsDelivery(int goodsDelivery) {
		this.goodsDelivery = goodsDelivery;
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
	public String getImgI() {
		return imgI;
	}
	public void setImgI(String imgI) {
		this.imgI = imgI;
	}
	public String getImgS() {
		return imgS;
	}
	public void setImgS(String imgS) {
		this.imgS = imgS;
	}
	public String getImgL() {
		return imgL;
	}
	public void setImgL(String imgL) {
		this.imgL = imgL;
	}
	public String getImgM() {
		return imgM;
	}
	public void setImgM(String imgM) {
		this.imgM = imgM;
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
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
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
	public String getApprovalStatus() {
		return approvalStatus;
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
	public int getCouponEa() {
		return couponEa;
	}
	public void setCouponEa(int couponEa) {
		this.couponEa = couponEa;
	}
	public String getCouponUsecnt() {
		return couponUsecnt;
	}
	public void setCouponUsecnt(String couponUsecnt) {
		this.couponUsecnt = couponUsecnt;
	}
	public String getCouponDate() {
		return couponDate;
	}
	public void setCouponDate(String couponDate) {
		this.couponDate = couponDate;
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
	
	public String[][] getOpt1() {
		return opt1;
	}
	public void setOpt1(String[][] opt1) {
		this.opt1 = opt1;
	}
	public String[][] getOpt2() {
		return opt2;
	}
	public void setOpt2(String[][] opt2) {
		this.opt2 = opt2;
	}
	public int[][] getOption() {
		return option;
	}
	public void setOption(int[][] option) {
		this.option = option;
	}
	
	public String[] getAddOptionNm() {
		return addOptionNm;
	}
	public void setAddOptionNm(String[] addOptionNm) {
		this.addOptionNm = addOptionNm;
	}
	public String[] getAddOptionOpt() {
		return addOptionOpt;
	}
	public void setAddOptionOpt(String[] addOptionOpt) {
		this.addOptionOpt = addOptionOpt;
	}
	public String[] getAddOptionPrice() {
		return addOptionPrice;
	}
	public void setAddOptionPrice(String[] addOptionPrice) {
		this.addOptionPrice = addOptionPrice;
	}
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGoodsDelivery2() {
		return goodsDelivery2;
	}
	public void setGoodsDelivery2(int goodsDelivery2) {
		this.goodsDelivery2 = goodsDelivery2;
	}
	public String getStrResult() {
		return strResult;
	}
	public void setStrResult(String strResult) {
		this.strResult = strResult;
	}
	public String getStrHieen() {
		return strHieen;
	}
	public void setStrHieen(String strHieen) {
		this.strHieen = strHieen;
	}
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	
	public String getGoodsArr() {
		return goodsArr;
	}
	public void setGoodsArr(String goodsArr) {
		this.goodsArr = goodsArr;
	}
	public String getOpenVal() {
		return openVal;
	}
	public void setOpenVal(String openVal) {
		this.openVal = openVal;
	}
	
	public String[][] getImgsArray() {
		return imgsArray;
	}
	public void setImgsArray(String[][] imgsArray) {
		this.imgsArray = imgsArray;
	}
	public String[] getStrImg() {
		return strImg;
	}
	
	public List<GdGoods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<GdGoods> goodsList) {
		this.goodsList = goodsList;
	}
	public GdGoods getGoodsObj() {
		return goodsObj;
	}
	public void setGoodsObj(GdGoods goodsObj) {
		this.goodsObj = goodsObj;
	}
	public List<GdCategory> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<GdCategory> list) {
		this.categoryList = list;
	}
	public GdCategory getCategoryObj() {
		return categoryObj;
	}
	public void setCategoryObj(GdCategory categoryObj) {
		this.categoryObj = categoryObj;
	}
	public List<GdGoodsAdd> getGoodsAddList() {
		return goodsAddList;
	}
	public void setGoodsAddList(List<GdGoodsAdd> goodsAddList) {
		this.goodsAddList = goodsAddList;
	}
	public GdGoodsAdd getGoodsAddObj() {
		return goodsAddObj;
	}
	public void setGoodsAddObj(GdGoodsAdd goodsAddObj) {
		this.goodsAddObj = goodsAddObj;
	}
	public List<GdGoodsBrand> getGoodsBrandList() {
		return goodsBrandList;
	}
	public void setGoodsBrandList(List<GdGoodsBrand> goodsBrandList) {
		this.goodsBrandList = goodsBrandList;
	}
	public GdGoodsBrand getGoodsBrandObj() {
		return goodsBrandObj;
	}
	public void setGoodsBrandObj(GdGoodsBrand goodsBrandObj) {
		this.goodsBrandObj = goodsBrandObj;
	}
	public List<String> getGoodsSeasonList() {
		return goodsSeasonList;
	}
	public void setGoodsSeasonList(List<String> goodsSeasonList) {
		this.goodsSeasonList = goodsSeasonList;
	}
	public List<GdGoods> getOriginList() {
		return originList;
	}
	public void setOriginList(List<GdGoods> originList) {
		this.originList = originList;
	}
	public List<GdGoodsOption> getGoodsOptionList() {
		return goodsOptionList;
	}
	public void setGoodsOptionList(List<GdGoodsOption> goodsOptionList) {
		this.goodsOptionList = goodsOptionList;
	}
	public String[] getOptNmArray() {
		return optNmArray;
	}
	public void setOptNmArray(String[] optNmArray) {
		this.optNmArray = optNmArray;
	}	
	public String getConsumer() {
		return consumer;
	}
	public void setConsumer(String consumer) {
		this.consumer = consumer;
	}
	public String getPrice1() {
		return price1;
	}
	public void setPrice1(String price1) {
		this.price1 = price1;
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
	public String[] getOptTitle() {
		return optTitle;
	}
	public void setOptTitle(String[] optTitle) {
		this.optTitle = optTitle;
	}
	public int getIntTotalStock() {
		return intTotalStock;
	}
	public void setIntTotalStock(int intTotalStock) {
		this.intTotalStock = intTotalStock;
	}
	public int getRelationCnt() {
		return relationCnt;
	}
	public void setRelationCnt(int relationCnt) {
		this.relationCnt = relationCnt;
	}
	public List<Map<String, Object>> getRelationList() {
		return relationList;
	}
	public void setRelationList(List<Map<String, Object>> relationList) {
		this.relationList = relationList;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public int getGoodsGroupNo() {
		return goodsGroupNo;
	}
	public void setGoodsGroupNo(int goodsGroupNo) {
		this.goodsGroupNo = goodsGroupNo;
	}
	public List<SellerGoodsViewVO> getGoodsNotiList() {
		return goodsNotiList;
	}
	public void setGoodsNotiList(List<SellerGoodsViewVO> goodsNotiList) {
		this.goodsNotiList = goodsNotiList;
	}
	public String getSellerYn() {
		return sellerYn;
	}
	public void setSellerYn(String sellerYn) {
		this.sellerYn = sellerYn;
	}
	public int getReqsno() {
		return reqsno;
	}
	public void setReqsno(int reqsno) {
		this.reqsno = reqsno;
	}
	public List<MultipartFile> getImages() {
        return images;
    }
    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }	
}
