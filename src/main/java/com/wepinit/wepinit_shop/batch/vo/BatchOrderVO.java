package com.wepinit.wepinit_shop.batch.vo;

public class BatchOrderVO {
	private String goodsno;
	private String ordno;
	private String sno;
	private String ea;
	private String imgI	= "";					//메인이미지
	private String imgS	= "";					//리스트이미지
	private String imgL	= "";					//확대이미지
	private String imgM	= "";					//상세이미지
	private String batchYn	= "";				//배치유무
	private String open= "0";					//OPEN유무
	
	
	public String getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}
	public String getOrdno() {
		return ordno;
	}
	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getEa() {
		return ea;
	}
	public void setEa(String ea) {
		this.ea = ea;
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
	public String getBatchYn() {
		return batchYn;
	}
	public void setBatchYn(String batchYn) {
		this.batchYn = batchYn;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
}
