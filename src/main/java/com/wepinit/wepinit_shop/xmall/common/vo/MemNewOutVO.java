package com.wepinit.wepinit_shop.xmall.common.vo;


public class MemNewOutVO {
	private String rdt;
	private int cnt;
	private int login;
	private String sex;
	private long saleCnt;	
	
	//output
	private String day;						//일자
	
	private int m_cnt;
	private int m_login;
	private long m_sale_cnt;
	
	private int w_cnt;
	private int w_login;
	private long w_sale_cnt;
	
	public String getRdt() {
		return rdt;
	}
	public void setRdt(String rdt) {
		this.rdt = rdt;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getLogin() {
		return login;
	}
	public void setLogin(int login) {
		this.login = login;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public long getSaleCnt() {
		return saleCnt;
	}
	public void setSaleCnt(long saleCnt) {
		this.saleCnt = saleCnt;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getM_cnt() {
		return m_cnt;
	}
	public void setM_cnt(int m_cnt) {
		this.m_cnt = m_cnt;
	}
	public int getM_login() {
		return m_login;
	}
	public void setM_login(int m_login) {
		this.m_login = m_login;
	}
	public long getM_sale_cnt() {
		return m_sale_cnt;
	}
	public void setM_sale_cnt(long m_sale_cnt) {
		this.m_sale_cnt = m_sale_cnt;
	}
	public int getW_cnt() {
		return w_cnt;
	}
	public void setW_cnt(int w_cnt) {
		this.w_cnt = w_cnt;
	}
	public int getW_login() {
		return w_login;
	}
	public void setW_login(int w_login) {
		this.w_login = w_login;
	}
	public long getW_sale_cnt() {
		return w_sale_cnt;
	}
	public void setW_sale_cnt(long w_sale_cnt) {
		this.w_sale_cnt = w_sale_cnt;
	}
	@Override
	public String toString() {
		return "MemNewOutVO [rdt=" + rdt + ", cnt=" + cnt + ", login=" + login
				+ ", sex=" + sex + ", saleCnt=" + saleCnt + ", day=" + day
				+ ", m_cnt=" + m_cnt + ", m_login=" + m_login + ", m_sale_cnt="
				+ m_sale_cnt + ", w_cnt=" + w_cnt + ", w_login=" + w_login
				+ ", w_sale_cnt=" + w_sale_cnt + "]";
	}

	
	
}
