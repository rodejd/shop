package com.wepinit.wepinit_shop.xmall.api.vo;

public class ProductImageVO {
	private int seq = 0;
	private String imgUrl = "";
	private String filePath = "";
	private String fileType = "";
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Override
	public String toString() {
		return "ProductImageVO [seq=" + seq + ", imgUrl=" + imgUrl + ", filePath=" + filePath + "]";
	}
}
