package com.wepinit.wepinit_shop.xmall.common.vo;


public class StatSettlekindOutVO {
	private String odt;
	private String settlekind;
	private int cnt;
	private long price;	
	
	//output
	private String day;						//일자
	
	private int rtList_a_cnt;				//무통장(건수)
	private long rtList_a_price;			//무통장(금액)
	
	private int rtList_c_cnt;				//신용카드(건수)
	private long rtList_c_price;			//신용카드(금액)
	
	private int rtList_o_cnt;				//계좌이체(건수)
	private long rtList_o_price;			//계좌이체(금액)
	
	private int rtList_v_cnt;				//가상계좌(건수)
	private long rtList_v_price;			//가상계좌(금액)
	
	private int rtList_h_cnt;				//핸드폰(건수)
	private long rtList_h_price;			//핸드폰(금액)
	

	@Override
	public String toString() {
		return "StatSettlekindOutVO [odt=" + odt + ", settlekind=" + settlekind
				+ ", cnt=" + cnt + ", price=" + price + ", day=" + day
				+ ", rtList_a_cnt=" + rtList_a_cnt + ", rtList_a_price="
				+ rtList_a_price + ", rtList_c_cnt=" + rtList_c_cnt
				+ ", rtList_c_price=" + rtList_c_price + ", rtList_o_cnt="
				+ rtList_o_cnt + ", rtList_o_price=" + rtList_o_price
				+ ", rtList_v_cnt=" + rtList_v_cnt + ", rtList_v_price="
				+ rtList_v_price + ", rtList_h_cnt=" + rtList_h_cnt
				+ ", rtList_h_price=" + rtList_h_price + "]";
	}
	public String getOdt() {
		return odt;
	}
	public void setOdt(String odt) {
		this.odt = odt;
	}
	public String getSettlekind() {
		return settlekind;
	}
	public void setSettlekind(String settlekind) {
		this.settlekind = settlekind;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getRtList_a_cnt() {
		return rtList_a_cnt;
	}
	public void setRtList_a_cnt(int rtList_a_cnt) {
		this.rtList_a_cnt = rtList_a_cnt;
	}
	public long getRtList_a_price() {
		return rtList_a_price;
	}
	public void setRtList_a_price(long rtList_a_price) {
		this.rtList_a_price = rtList_a_price;
	}
	public int getRtList_c_cnt() {
		return rtList_c_cnt;
	}
	public void setRtList_c_cnt(int rtList_c_cnt) {
		this.rtList_c_cnt = rtList_c_cnt;
	}
	public long getRtList_c_price() {
		return rtList_c_price;
	}
	public void setRtList_c_price(long rtList_c_price) {
		this.rtList_c_price = rtList_c_price;
	}
	public int getRtList_o_cnt() {
		return rtList_o_cnt;
	}
	public void setRtList_o_cnt(int rtList_o_cnt) {
		this.rtList_o_cnt = rtList_o_cnt;
	}
	public long getRtList_o_price() {
		return rtList_o_price;
	}
	public void setRtList_o_price(long rtList_o_price) {
		this.rtList_o_price = rtList_o_price;
	}
	public int getRtList_v_cnt() {
		return rtList_v_cnt;
	}
	public void setRtList_v_cnt(int rtList_v_cnt) {
		this.rtList_v_cnt = rtList_v_cnt;
	}
	public long getRtList_v_price() {
		return rtList_v_price;
	}
	public void setRtList_v_price(long rtList_v_price) {
		this.rtList_v_price = rtList_v_price;
	}
	public int getRtList_h_cnt() {
		return rtList_h_cnt;
	}
	public void setRtList_h_cnt(int rtList_h_cnt) {
		this.rtList_h_cnt = rtList_h_cnt;
	}
	public long getRtList_h_price() {
		return rtList_h_price;
	}
	public void setRtList_h_price(long rtList_h_price) {
		this.rtList_h_price = rtList_h_price;
	}
	
	
}
