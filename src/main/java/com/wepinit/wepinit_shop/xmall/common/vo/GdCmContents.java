package com.wepinit.wepinit_shop.xmall.common.vo;

import java.util.Date;

public class GdCmContents {
	private int sno;
	private String id;
	private String title;
	private String subject;
	private String contents;
	private String img = "";
	private String useYn;
	private Date regdt;
	private Date moddt;
	private String autoYn;
	private int m_no;
	
	public GdCmContents() {}
	
	public GdCmContents(String id, String title, String useYn) {
		super();
		this.id = id;
		this.title = title;
		this.useYn = useYn;
	}

	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	public String getAutoYn() {
		return autoYn;
	}
	public void setAutoYn(String autoYn) {
		this.autoYn = autoYn;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	@Override
	public String toString() {
		return "GdCmContents [sno=" + sno + ", id=" + id + ", title=" + title
				+ ", subject=" + subject + ", contents=" + contents + ", img="
				+ img + ", useYn=" + useYn + ", regdt=" + regdt + ", moddt="
				+ moddt + ", autoYn=" + autoYn + ", m_no=" + m_no + "]";
	}
	
	
}
