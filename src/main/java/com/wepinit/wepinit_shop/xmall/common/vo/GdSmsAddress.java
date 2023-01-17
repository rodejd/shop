package com.wepinit.wepinit_shop.xmall.common.vo;

import java.util.Date;

public class GdSmsAddress {
	
	private long sno;
	private String smsGrp;
	private String smsName;
	private String smsMobile;
	private String smsEtc;
	private String sex;
	private Date regdt;
	private Date moddt;
	
	@Override
	public String toString() {
		return "GdSmsAddress [sno=" + sno + ", smsGrp=" + smsGrp + ", smsName="
				+ smsName + ", smsMobile=" + smsMobile + ", smsEtc=" + smsEtc
				+ ", sex=" + sex + ", regdt=" + regdt + ", moddt=" + moddt
				+ "]";
	}
	public long getSno() {
		return sno;
	}
	public void setSno(long sno) {
		this.sno = sno;
	}
	public String getSmsGrp() {
		return smsGrp;
	}
	public void setSmsGrp(String smsGrp) {
		this.smsGrp = smsGrp;
	}
	public String getSmsName() {
		return smsName;
	}
	public void setSmsName(String smsName) {
		this.smsName = smsName;
	}
	public String getSmsMobile() {
		return smsMobile;
	}
	public void setSmsMobile(String smsMobile) {
		this.smsMobile = smsMobile;
	}
	public String getSmsEtc() {
		return smsEtc;
	}
	public void setSmsEtc(String smsEtc) {
		this.smsEtc = smsEtc;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public Date getModdt() {
		return moddt;
	}
	public void setModdt(Date moddt) {
		this.moddt = moddt;
	}

	

}
