package com.wepinit.wepinit_shop.xmall.common.vo.join;

import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;

import java.util.Date;

public class GdMemberJoinGdMemberGrp extends GdMember{
	private int sno = 0; 
	private String grpnm = "";
	private int kLevel = 0;
	private int dc = 0;
	private int addEmoney = 0;
	private String freeDeliveryfee = "";
	private Date regdt = null;
	private Date moddt = null;
	private int cnt = 0;
	private int smsCnt = 0;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getGrpnm() {
		return grpnm;
	}
	public void setGrpnm(String grpnm) {
		this.grpnm = grpnm;
	}
	public int getkLevel() {
		return kLevel;
	}
	public void setkLevel(int kLevel) {
		this.kLevel = kLevel;
	}
	public int getDc() {
		return dc;
	}
	public void setDc(int dc) {
		this.dc = dc;
	}
	public int getAddEmoney() {
		return addEmoney;
	}
	public void setAddEmoney(int addEmoney) {
		this.addEmoney = addEmoney;
	}
	public String getFreeDeliveryfee() {
		return freeDeliveryfee;
	}
	public void setFreeDeliveryfee(String freeDeliveryfee) {
		this.freeDeliveryfee = freeDeliveryfee;
	}
	@Override
	public Date getRegdt() {
		return regdt;
	}
	@Override
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public Date getModdt() {
		return moddt;
	}
	public void setModdt(Date moddt) {
		this.moddt = moddt;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getSmsCnt() {
		return smsCnt;
	}
	public void setSmsCnt(int smsCnt) {
		this.smsCnt = smsCnt;
	}
}
