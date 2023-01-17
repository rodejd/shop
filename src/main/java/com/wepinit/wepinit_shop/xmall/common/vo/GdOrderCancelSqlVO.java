package com.wepinit.wepinit_shop.xmall.common.vo;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;


public class GdOrderCancelSqlVO extends PageMaker {
	private String schSword;
	private String schType;
	private String schRegdt;
	private String schSettlekind;
	/*판매사명*/
	private String schSellerNm;
	private String schSellerCd;
	private String ssSellerCd;				//판매사 세션의 판매사 코드

	private String schMyritzNm;
	private String schMyritzCd;
	private String ssMyritzCd;				//판매사 세션의 판매사 코드
	
	public String getSchMyritzNm() {
		return schMyritzNm;
	}
	public void setSchMyritzNm(String schMyritzNm) {
		this.schMyritzNm = schMyritzNm;
	}
	public String getSchMyritzCd() {
		return schMyritzCd;
	}
	public void setSchMyritzCd(String schMyritzCd) {
		this.schMyritzCd = schMyritzCd;
	}
	public String getSsMyritzCd() {
		return ssMyritzCd;
	}
	public void setSsMyritzCd(String ssMyritzCd) {
		this.ssMyritzCd = ssMyritzCd;
	}
	public String getSchSword() {
		return schSword;
	}
	public void setSchSword(String schSword) {
		this.schSword = schSword;
	}
	public String getSchType() {
		return schType;
	}
	public void setSchType(String schType) {
		this.schType = schType;
	}
	public String getSchRegdt() {
		return schRegdt;
	}
	public void setSchRegdt(String schRegdt) {
		this.schRegdt = schRegdt;
	}
	public String getSchSettlekind() {
		return schSettlekind;
	}
	public void setSchSettlekind(String schSettlekind) {
		this.schSettlekind = schSettlekind;
	}
	public String getSchSellerNm() {
		return schSellerNm;
	}
	public void setSchSellerNm(String schSellerNm) {
		this.schSellerNm = schSellerNm;
	}
	public String getSchSellerCd() {
		return schSellerCd;
	}
	public void setSchSellerCd(String schSellerCd) {
		this.schSellerCd = schSellerCd;
	}
	public String getSsSellerCd() {
		return ssSellerCd;
	}
	public void setSsSellerCd(String ssSellerCd) {
		this.ssSellerCd = ssSellerCd;
	}
	@Override
	public String toString() {
		return "GdOrderCancelSqlVO [schSword=" + schSword + ", schType="
				+ schType + ", schRegdt=" + schRegdt + ", schSettlekind="
				+ schSettlekind + ", schSellerNm=" + schSellerNm
				+ ", schSellerCd=" + schSellerCd + ", ssSellerCd=" + ssSellerCd
				+ "]";
	}
}
