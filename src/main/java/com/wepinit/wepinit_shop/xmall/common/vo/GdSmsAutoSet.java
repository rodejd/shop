package com.wepinit.wepinit_shop.xmall.common.vo;

public class GdSmsAutoSet {
	
	private String smsType;
	private String msgC;
	private String sendC;
	private String msgA;
	private String sendA;
	private String sendM;
	private String sendOk;
	private String regdt;
	private String uptdt;
	private String denySet;
	private String msgTitle;
	
	
	@Override
	public String toString() {
		return "GdSmsAutoSet [smsType=" + smsType + ", msgC=" + msgC
				+ ", sendC=" + sendC + ", msgA=" + msgA + ", sendA=" + sendA
				+ ", sendM=" + sendM + ", sendOk=" + sendOk + ", regdt="
				+ regdt + ", uptdt=" + uptdt + ", denySet=" + denySet
				+ ", msgTitle=" + msgTitle + "]";
	}
	
	
	public String getSmsType() {
		return smsType;
	}
	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}
	public String getMsgC() {
		return msgC;
	}
	public void setMsgC(String msgC) {
		this.msgC = msgC;
	}
	public String getSendC() {
		return sendC;
	}
	public void setSendC(String sendC) {
		this.sendC = sendC;
	}
	public String getMsgA() {
		return msgA;
	}
	public void setMsgA(String msgA) {
		this.msgA = msgA;
	}
	public String getSendA() {
		return sendA;
	}
	public void setSendA(String sendA) {
		this.sendA = sendA;
	}
	public String getSendM() {
		return sendM;
	}
	public void setSendM(String sendM) {
		this.sendM = sendM;
	}
	public String getSendOk() {
		return sendOk;
	}
	public void setSendOk(String sendOk) {
		this.sendOk = sendOk;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	public String getUptdt() {
		return uptdt;
	}
	public void setUptdt(String uptdt) {
		this.uptdt = uptdt;
	}
	public String getDenySet() {
		return denySet;
	}
	public void setDenySet(String denySet) {
		this.denySet = denySet;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	

}
