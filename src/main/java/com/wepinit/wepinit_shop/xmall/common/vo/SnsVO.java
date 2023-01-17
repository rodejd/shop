package com.wepinit.wepinit_shop.xmall.common.vo;

public class SnsVO {

	private String m_no;
	private String sns_id;
	private String sns_email;
	private String sns_nickname;
	private String sns_type;
	public String getM_no() {
		return m_no;
	}
	public void setM_no(String m_no) {
		this.m_no = m_no;
	}
	public String getSns_id() {
		return sns_id;
	}
	public void setSns_id(String sns_id) {
		this.sns_id = sns_id;
	}
	public String getSns_email() {
		return sns_email;
	}
	public void setSns_email(String sns_email) {
		this.sns_email = sns_email;
	}
	public String getSns_nickname() {
		return sns_nickname;
	}
	public void setSns_nickname(String sns_nickname) {
		this.sns_nickname = sns_nickname;
	}
	public String getSns_type() {
		return sns_type;
	}
	public void setSns_type(String sns_type) {
		this.sns_type = sns_type;
	}
	@Override
	public String toString() {
		return "SnsVO [m_no=" + m_no + ", sns_id=" + sns_id + ", sns_email="
				+ sns_email + ", sns_nickname=" + sns_nickname + ", sns_type="
				+ sns_type + "]";
	}
	
}
