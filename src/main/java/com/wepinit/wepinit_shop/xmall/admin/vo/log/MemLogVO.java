package com.wepinit.wepinit_shop.xmall.admin.vo.log;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

public class MemLogVO extends PageMaker {
	private int l_no;  // 로그번호
	private int m_no;  // 회원번호
	private String agent; // 접속 기기(W:Web, M:Mobile, A:App)
	private String gbn;   // 구분(L:로그인, B:상품구매)
	private String regdt; // 등록일자
	
	public int getL_no() {
		return l_no;
	}
	public void setL_no(int l_no) {
		this.l_no = l_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getGbn() {
		return gbn;
	}
	public void setGbn(String gbn) {
		this.gbn = gbn;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
}
