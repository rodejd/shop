package com.wepinit.wepinit_shop.xmall.common.vo;

public class GdGoodsBrand {
	
	private int sno;
	private String brandnm;
	private String brandnmKR;
	private String brandnmCN;
	private int sort;
	private String sellerCd;
	private String approvalStatus;
	private String sellerNm;
	private String brandMemo;
	private String imgPC;
	private String imgMO;
	private String bestYn;
	private String spell;
	private int goodsCnt = 0;
	
	private int tCount = 0; // 총 브랜드수
	private int uCount = 0; // 사용 브랜드수
	private int nCount = 0; // 미사용 브랜드수
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getBrandnm() {
		return brandnm;
	}
	public void setBrandnm(String brandnm) {
		this.brandnm = brandnm;
	}
	public String getBrandnmKR() {
		return brandnmKR;
	}
	public void setBrandnmKR(String brandnmKR) {
		this.brandnmKR = brandnmKR;
	}
	public String getBrandnmCN() {
		return brandnmCN;
	}
	public void setBrandnmCN(String brandnmCN) {
		this.brandnmCN = brandnmCN;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	public String getSellerNm() {
		return sellerNm;
	}
	public void setSellerNm(String sellerNm) {
		this.sellerNm = sellerNm;
	}
	
	public String getBrandMemo() {
		return brandMemo;
	}
	public void setBrandMemo(String brandMemo) {
		this.brandMemo = brandMemo;
	}
	public String getImgPC() {
		return imgPC;
	}
	public void setImgPC(String imgPC) {
		this.imgPC = imgPC;
	}
	public String getImgMO() {
		return imgMO;
	}
	public void setImgMO(String imgMO) {
		this.imgMO = imgMO;
	}
	public String getBestYn() {
		return bestYn;
	}
	public void setBestYn(String bestYn) {
		this.bestYn = bestYn;
	}
	public int gettCount() {
		return tCount;
	}
	public void settCount(int tCount) {
		this.tCount = tCount;
	}
	public int getuCount() {
		return uCount;
	}
	public void setuCount(int uCount) {
		this.uCount = uCount;
	}
	public int getnCount() {
		return nCount;
	}
	public void setnCount(int nCount) {
		this.nCount = nCount;
	}	
	public String getSpell() {
		return spell;
	}
	public void setSpell(String spell) {
		this.spell = spell;
	}
	public int getGoodsCnt() {
		return goodsCnt;
	}
	public void setGoodsCnt(int goodsCnt) {
		this.goodsCnt = goodsCnt;
	}
	
	@Override
	public String toString() {
		return "GdGoodsBrand [sno=" + sno 
				+ ", brandnm=" + brandnm 
				+ ", sort=" + sort 
				+ ", sellerCd=" + sellerCd 
				+ ", approvalStatus=" + approvalStatus 
				+ ", sellerNm=" + sellerNm 
				+ ", brandMemo=" + brandMemo
				+ ", bestYn=" + bestYn + "]";
	}
}
