package com.wepinit.wepinit_shop.xmall.common.vo;


public class StatAreaOutVO {
	private String odt;
	private int zip;
	private long price;	
	private int cnt;
	
	//output
	private String day;						//일자
	
	private int rtList_su_cnt;				//서울(건수)
	private long rtList_su_price;			//서울(금액)
	
	private int rtList_gg_cnt;				//경기(건수)
	private long rtList_gg_price;			//경기(금액)
	
	private int rtList_gn_cnt;				//경남(건수)
	private long rtList_gn_price;			//경남(금액)
	
	private int rtList_gb_cnt;				//경북(건수)
	private long rtList_gb_price;			//경북(금액)
	
	private int rtList_jn_cnt;				//전남(건수)
	private long rtList_jn_price;			//전남(금액)
	
	private int rtList_jb_cnt;				//전북(건수)
	private long rtList_jb_price;			//전북(금액)
	
	private int rtList_cn_cnt;				//충남(건수)
	private long rtList_cn_price;			//충남(금액)
	
	private int rtList_cb_cnt;				//충북(건수)
	private long rtList_cb_price;			//충북(금액)
	
	private int rtList_jj_cnt;				//제주(건수)
	private long rtList_jj_price;			//제주(금액)
	
	
	public String getOdt() {
		return odt;
	}
	public void setOdt(String odt) {
		this.odt = odt;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getRtList_su_cnt() {
		return rtList_su_cnt;
	}
	public void setRtList_su_cnt(int rtList_su_cnt) {
		this.rtList_su_cnt = rtList_su_cnt;
	}
	public long getRtList_su_price() {
		return rtList_su_price;
	}
	public void setRtList_su_price(long rtList_su_price) {
		this.rtList_su_price = rtList_su_price;
	}
	public int getRtList_gg_cnt() {
		return rtList_gg_cnt;
	}
	public void setRtList_gg_cnt(int rtList_gg_cnt) {
		this.rtList_gg_cnt = rtList_gg_cnt;
	}
	public long getRtList_gg_price() {
		return rtList_gg_price;
	}
	public void setRtList_gg_price(long rtList_gg_price) {
		this.rtList_gg_price = rtList_gg_price;
	}
	public int getRtList_gn_cnt() {
		return rtList_gn_cnt;
	}
	public void setRtList_gn_cnt(int rtList_gn_cnt) {
		this.rtList_gn_cnt = rtList_gn_cnt;
	}
	public long getRtList_gn_price() {
		return rtList_gn_price;
	}
	public void setRtList_gn_price(long rtList_gn_price) {
		this.rtList_gn_price = rtList_gn_price;
	}
	public int getRtList_gb_cnt() {
		return rtList_gb_cnt;
	}
	public void setRtList_gb_cnt(int rtList_gb_cnt) {
		this.rtList_gb_cnt = rtList_gb_cnt;
	}
	public long getRtList_gb_price() {
		return rtList_gb_price;
	}
	public void setRtList_gb_price(long rtList_gb_price) {
		this.rtList_gb_price = rtList_gb_price;
	}
	public int getRtList_jn_cnt() {
		return rtList_jn_cnt;
	}
	public void setRtList_jn_cnt(int rtList_jn_cnt) {
		this.rtList_jn_cnt = rtList_jn_cnt;
	}
	public long getRtList_jn_price() {
		return rtList_jn_price;
	}
	public void setRtList_jn_price(long rtList_jn_price) {
		this.rtList_jn_price = rtList_jn_price;
	}
	public int getRtList_jb_cnt() {
		return rtList_jb_cnt;
	}
	public void setRtList_jb_cnt(int rtList_jb_cnt) {
		this.rtList_jb_cnt = rtList_jb_cnt;
	}
	public long getRtList_jb_price() {
		return rtList_jb_price;
	}
	public void setRtList_jb_price(long rtList_jb_price) {
		this.rtList_jb_price = rtList_jb_price;
	}
	public int getRtList_cn_cnt() {
		return rtList_cn_cnt;
	}
	public void setRtList_cn_cnt(int rtList_cn_cnt) {
		this.rtList_cn_cnt = rtList_cn_cnt;
	}
	public long getRtList_cn_price() {
		return rtList_cn_price;
	}
	public void setRtList_cn_price(long rtList_cn_price) {
		this.rtList_cn_price = rtList_cn_price;
	}
	public int getRtList_cb_cnt() {
		return rtList_cb_cnt;
	}
	public void setRtList_cb_cnt(int rtList_cb_cnt) {
		this.rtList_cb_cnt = rtList_cb_cnt;
	}
	public long getRtList_cb_price() {
		return rtList_cb_price;
	}
	public void setRtList_cb_price(long rtList_cb_price) {
		this.rtList_cb_price = rtList_cb_price;
	}
	public int getRtList_jj_cnt() {
		return rtList_jj_cnt;
	}
	public void setRtList_jj_cnt(int rtList_jj_cnt) {
		this.rtList_jj_cnt = rtList_jj_cnt;
	}
	public long getRtList_jj_price() {
		return rtList_jj_price;
	}
	public void setRtList_jj_price(long rtList_jj_price) {
		this.rtList_jj_price = rtList_jj_price;
	}
	@Override
	public String toString() {
		return "StatAreaOutVO [odt=" + odt + ", zip=" + zip + ", price="
				+ price + ", cnt=" + cnt + ", day=" + day + ", rtList_su_cnt="
				+ rtList_su_cnt + ", rtList_su_price=" + rtList_su_price
				+ ", rtList_gg_cnt=" + rtList_gg_cnt + ", rtList_gg_price="
				+ rtList_gg_price + ", rtList_gn_cnt=" + rtList_gn_cnt
				+ ", rtList_gn_price=" + rtList_gn_price + ", rtList_gb_cnt="
				+ rtList_gb_cnt + ", rtList_gb_price=" + rtList_gb_price
				+ ", rtList_jn_cnt=" + rtList_jn_cnt + ", rtList_jn_price="
				+ rtList_jn_price + ", rtList_jb_cnt=" + rtList_jb_cnt
				+ ", rtList_jb_price=" + rtList_jb_price + ", rtList_cn_cnt="
				+ rtList_cn_cnt + ", rtList_cn_price=" + rtList_cn_price
				+ ", rtList_cb_cnt=" + rtList_cb_cnt + ", rtList_cb_price="
				+ rtList_cb_price + ", rtList_jj_cnt=" + rtList_jj_cnt
				+ ", rtList_jj_price=" + rtList_jj_price + "]";
	}
	
	
}
