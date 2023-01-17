package com.wepinit.wepinit_shop.xmall.common.vo;

import java.util.Date;
import java.util.List;

public class GdOrderCancel {
	private int sno				; //일련번호
	private long ordno			; //주문번호
	private int code			; //코드번호
	private String memo			; //메모
	private String name			; //이름
	private Date regdt			; //등록일시
	private int rprice			; //할인금액
	private int rfee			; //환불수수료
	private int remoney			; //환불e머니
	private Date ccdt			; //환불처리일시
	private String bankcode		; //은행코드
	private String bankaccount	; //은행계좌
	private String bankuser		; //계좌주
	
	// 화면
	private String cancelCode;
	private List<GdLogCancel> tmpRt12;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public long getOrdno() {
		return ordno;
	}
	public void setOrdno(long ordno) {
		this.ordno = ordno;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public int getRprice() {
		return rprice;
	}
	public void setRprice(int rprice) {
		this.rprice = rprice;
	}
	public int getRfee() {
		return rfee;
	}
	public void setRfee(int rfee) {
		this.rfee = rfee;
	}
	public int getRemoney() {
		return remoney;
	}
	public void setRemoney(int remoney) {
		this.remoney = remoney;
	}
	public Date getCcdt() {
		return ccdt;
	}
	public void setCcdt(Date ccdt) {
		this.ccdt = ccdt;
	}
	public String getBankcode() {
		return bankcode;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	public String getBankaccount() {
		return bankaccount;
	}
	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}
	public String getBankuser() {
		return bankuser;
	}
	public void setBankuser(String bankuser) {
		this.bankuser = bankuser;
	}
	public void setCancelCode(String cancelCode) {
		this.cancelCode = cancelCode;
	}
	public String getCancelCode() {
		return cancelCode;
	}
	public List<GdLogCancel> getTmpRt12() {
		return tmpRt12;
	}
	public void setTmpRt12(List<GdLogCancel> tmpRt12) {
		this.tmpRt12 = tmpRt12;
	}
	@Override
	public String toString() {
		return "GdOrderCancel [sno=" + sno + ", ordno=" + ordno + ", code="
				+ code + ", memo=" + memo + ", name=" + name + ", regdt="
				+ regdt + ", rprice=" + rprice + ", rfee=" + rfee
				+ ", remoney=" + remoney + ", ccdt=" + ccdt + ", bankcode="
				+ bankcode + ", bankaccount=" + bankaccount + ", bankuser="
				+ bankuser + ", cancelCode=" + cancelCode + ", tmpRt12="
				+ tmpRt12 + "]";
	}
}
