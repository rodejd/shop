package com.wepinit.wepinit_shop.xmall.common.vo;

public class GdEmailer {
	
	private int mno;
	private String toname;
	private String toemail;
	private String fromname;
	private String fromemail;
	private String subject;
	private String etc1;
	private String etc2;
	private String etc3;
	private String contents;
	private String tranyn;
	private String trandt;
	private String regdt;
	private String recvyn;
	private String recvdt;
	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getToname() {
		return toname;
	}
	public void setToname(String toname) {
		this.toname = toname;
	}
	public String getToemail() {
		return toemail;
	}
	public void setToemail(String toemail) {
		this.toemail = toemail;
	}
	public String getFromname() {
		return fromname;
	}
	public void setFromname(String fromname) {
		this.fromname = fromname;
	}
	public String getFromemail() {
		return fromemail;
	}
	public void setFromemail(String fromemail) {
		this.fromemail = fromemail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEtc1() {
		return etc1;
	}
	public void setEtc1(String etc1) {
		this.etc1 = etc1;
	}
	public String getEtc2() {
		return etc2;
	}
	public void setEtc2(String etc2) {
		this.etc2 = etc2;
	}
	public String getEtc3() {
		return etc3;
	}
	public void setEtc3(String etc3) {
		this.etc3 = etc3;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getTranyn() {
		return tranyn;
	}
	public void setTranyn(String tranyn) {
		this.tranyn = tranyn;
	}
	public String getTrandt() {
		return trandt;
	}
	public void setTrandt(String trandt) {
		this.trandt = trandt;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	public String getRecvyn() {
		return recvyn;
	}
	public void setRecvyn(String recvyn) {
		this.recvyn = recvyn;
	}
	public String getRecvdt() {
		return recvdt;
	}
	public void setRecvdt(String recvdt) {
		this.recvdt = recvdt;
	}
	
	@Override
	public String toString() {
		return "GdEmailer [mno=" + mno + ", toname=" + toname + ", toemail="
				+ toemail + ", fromname=" + fromname + ", fromemail="
				+ fromemail + ", subject=" + subject + ", etc1=" + etc1
				+ ", etc2=" + etc2 + ", etc3=" + etc3 + ", contents="
				+ contents + ", tranyn=" + tranyn + ", trandt=" + trandt
				+ ", regdt=" + regdt + ", recvyn=" + recvyn + ", recvdt="
				+ recvdt + "]";
	}

}
