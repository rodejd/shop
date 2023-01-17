package com.wepinit.wepinit_shop.xmall.admin.vo.goods;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdGoodsManage;

import java.util.Date;
import java.util.List;

public class GoodsManageVO extends PageMaker {
	private int sno = 0; //일련번호
	private String binCd = ""; //Bin코드
	private String price = ""; //희망가
	private String m_id = ""; //등록자아이디
	private Date regdt; //등록일자
	
	private String optionBinCd = ""; //Bin코드
	private String goodsno = "";
	private String imgs = "";
	private String goodscd = "";
	private String goodsnm = "";   //상품명(영문)
	private String goodsnmKR = ""; //상품명(국문)
	private String goodsnmCN = ""; //상품명(중문)
	private String sellerCd = "";
	private String optionPrice  = "";
	private String stock = "";
	private String open = "";
	private String manageYn = "";
	private String categoryNm = "";
	
	private String mode = "";
	private String search = "";
	
	private String spec = "";
	private String[] goodsArr;
	private String[] manageArr;
	private String[] specArr;
	
	private List<GdGoodsManage> goodsManageList = null;
	private List<GoodsManageVO> goodsDataList = null;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getBinCd() {
		return binCd;
	}
	public void setBinCd(String binCd) {
		this.binCd = binCd;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public List<GdGoodsManage> getGoodsManageList() {
		return goodsManageList;
	}
	public void setGoodsManageList(List<GdGoodsManage> goodsManageList) {
		this.goodsManageList = goodsManageList;
	}
	public List<GoodsManageVO> getGoodsDataList() {
		return goodsDataList;
	}
	public void setGoodsDataList(List<GoodsManageVO> goodsDataList) {
		this.goodsDataList = goodsDataList;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
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
	public String getOptionPrice() {
		return optionPrice;
	}
	public void setOptionPrice(String optionPrice) {
		this.optionPrice = optionPrice;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}
	public String getOptionBinCd() {
		return optionBinCd;
	}
	public void setOptionBinCd(String optionBinCd) {
		this.optionBinCd = optionBinCd;
	}
	public String getManageYn() {
		return manageYn;
	}
	public void setManageYn(String manageYn) {
		this.manageYn = manageYn;
	}
	public String[] getGoodsArr() {
		return goodsArr;
	}
	public void setGoodsArr(String[] goodsArr) {
		this.goodsArr = goodsArr;
	}
	public String[] getManageArr() {
		return manageArr;
	}
	public void setManageArr(String[] manageArr) {
		this.manageArr = manageArr;
	}
	public String getCategoryNm() {
		return categoryNm;
	}
	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}
	public String[] getSpecArr() {
		return specArr;
	}
	public void setSpecArr(String[] specArr) {
		this.specArr = specArr;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
}
