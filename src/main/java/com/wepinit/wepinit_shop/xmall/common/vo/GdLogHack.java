package com.wepinit.wepinit_shop.xmall.common.vo;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GdLogHack extends PageMaker {
	private int sno;
	private String mId;
	private String name;
	private String resno1;
	private String resno2;
	private int actor;
	private int itemcd;
	private String reason;
	private String ip;
	private Date regdt;
	private String adminMemo;
	private String rejoin;
	private String customsnum;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResno1() {
		return resno1;
	}
	public void setResno1(String resno1) {
		this.resno1 = resno1;
	}
	public String getResno2() {
		return resno2;
	}
	public void setResno2(String resno2) {
		this.resno2 = resno2;
	}
	public int getActor() {
		return actor;
	}
	public void setActor(int actor) {
		this.actor = actor;
	}
	public void setItemcd(int itemcd) {
		this.itemcd = itemcd;
	}
	public int getItemcd() {
		return itemcd;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public String getAdminMemo() {
		return adminMemo;
	}
	public void setAdminMemo(String adminMemo) {
		this.adminMemo = adminMemo;
	}
	
	public String getRejoin() {
		return rejoin;
	}
	public void setRejoin(String rejoin) {
		this.rejoin = rejoin;
	}
	public String getFormatDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(this.regdt);
	}
	public String getCustomsnum() {
		return customsnum;
	}
	public void setCustomsnum(String customsnum) {
		this.customsnum = customsnum;
	}
	@Override
	public String toString() {
		return "GdLogHack [sno=" + sno + ", mId=" + mId + ", name=" + name
				+ ", resno1=" + resno1 + ", resno2=" + resno2 + ", actor="
				+ actor + ", itemcd=" + itemcd + ", reason=" + reason + ", ip="
				+ ip + ", regdt=" + regdt + ", adminMemo=" + adminMemo
				+ ", rejoin=" + rejoin + "]";
	}
	
	
	
	
}
