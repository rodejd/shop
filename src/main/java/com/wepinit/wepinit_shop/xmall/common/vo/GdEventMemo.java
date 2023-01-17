package com.wepinit.wepinit_shop.xmall.common.vo;





public class GdEventMemo {
	private int eno = 0;
	private int sno = 0;
	private String id = "";
	private String pic = "";
	private String body = "";
	private String regdt = "";
	public int getEno() {
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	@Override
	public String toString() {
		return "GdEventMemo [eno=" + eno + ", sno=" + sno + ", id=" + id
				+ ", pic=" + pic + ", body=" + body + ", regdt=" + regdt + "]";
	}
	
}
