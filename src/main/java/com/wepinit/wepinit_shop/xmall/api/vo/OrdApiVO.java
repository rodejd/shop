package com.wepinit.wepinit_shop.xmall.api.vo;

public class OrdApiVO {
	private String key = "";
	private String shop_id = "";
	private String ordTypCd = "";
	private String strtDt = "";
	private String endDt = "";
	private String ordno = "";
	private String sno = "";
	private String step = "";
	private String istep = "";
	private String dvno = "";
	private String dvcode = "";
	private String confirmdt = "";
	
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
	public String getOrdTypCd() {
		return ordTypCd;
	}
	public void setOrdTypCd(String ordTypCd) {
		this.ordTypCd = ordTypCd;
	}
	public String getStrtDt() {
		return strtDt;
	}
	public void setStrtDt(String strtDt) {
		this.strtDt = strtDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
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
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getIstep() {
		return istep;
	}
	public void setIstep(String istep) {
		this.istep = istep;
	}
	public String getDvno() {
		return dvno;
	}
	public void setDvno(String dvno) {
		this.dvno = dvno;
	}
	public String getDvcode() {
		return dvcode;
	}
	public void setDvcode(String dvcode) {
		this.dvcode = dvcode;
	}
	public String getConfirmdt() {
		return confirmdt;
	}
	public void setConfirmdt(String confirmdt) {
		this.confirmdt = confirmdt;
	}
	
	@Override
	public String toString() {
		return "ProductVO [key=" + key
				 + ", shop_id=" + shop_id
				 + ", ordTypCd=" + ordTypCd
				 + ", strtDt=" + strtDt
				 + ", endDt=" + endDt
				 + ", ordno=" + ordno
				 + ", step=" + step
				 + ", istep=" + istep
				 + ", sno=" + sno
				 + ", dvno=" + dvno
				 + ", dvcode=" + dvcode
				 + ", confirmdt=" + confirmdt
				 + "]";
	}
}
