package com.wepinit.wepinit_shop.xmall.common.vo;


public class StatSaleOutVO {
	private String dt;
	private int cnt;
	private long sumPrnSettleprice;	
	private long sumSupply;
	
	//output
	private String day;										//일자

	private int rtList1_cnt;									//주문건수
	private long rtList1_sum_prn_settleprice;			//주문금액
	
	private int rtList2_cnt;									//주문금액
	private long rtList2_sum_prn_settleprice;			//결제건수
	private long rtList2_sum_supply;					//매입금액
	private long rtList2_net_sales;						//순매출액
	
	private int rtList3_cnt;									//배송건수
	private long rtList3_sum_prn_settleprice;			//배송중/배송완료
	
	
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public long getSumPrnSettleprice() {
		return sumPrnSettleprice;
	}
	public void setSumPrnSettleprice(long sumPrnSettleprice) {
		this.sumPrnSettleprice = sumPrnSettleprice;
	}
	public long getSumSupply() {
		return sumSupply;
	}
	public void setSumSupply(long sumSupply) {
		this.sumSupply = sumSupply;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getRtList1_cnt() {
		return rtList1_cnt;
	}
	public void setRtList1_cnt(int rtList1_cnt) {
		this.rtList1_cnt = rtList1_cnt;
	}
	public long getRtList1_sum_prn_settleprice() {
		return rtList1_sum_prn_settleprice;
	}
	public void setRtList1_sum_prn_settleprice(long rtList1_sum_prn_settleprice) {
		this.rtList1_sum_prn_settleprice = rtList1_sum_prn_settleprice;
	}
	public int getRtList2_cnt() {
		return rtList2_cnt;
	}
	public void setRtList2_cnt(int rtList2_cnt) {
		this.rtList2_cnt = rtList2_cnt;
	}
	public long getRtList2_sum_prn_settleprice() {
		return rtList2_sum_prn_settleprice;
	}
	public void setRtList2_sum_prn_settleprice(long rtList2_sum_prn_settleprice) {
		this.rtList2_sum_prn_settleprice = rtList2_sum_prn_settleprice;
	}
	public long getRtList2_sum_supply() {
		return rtList2_sum_supply;
	}
	public void setRtList2_sum_supply(long rtList2_sum_supply) {
		this.rtList2_sum_supply = rtList2_sum_supply;
	}
	public long getRtList2_net_sales() {
		return rtList2_net_sales;
	}
	public void setRtList2_net_sales(long rtList2_net_sales) {
		this.rtList2_net_sales = rtList2_net_sales;
	}
	public int getRtList3_cnt() {
		return rtList3_cnt;
	}
	public void setRtList3_cnt(int rtList3_cnt) {
		this.rtList3_cnt = rtList3_cnt;
	}
	public long getRtList3_sum_prn_settleprice() {
		return rtList3_sum_prn_settleprice;
	}
	public void setRtList3_sum_prn_settleprice(long rtList3_sum_prn_settleprice) {
		this.rtList3_sum_prn_settleprice = rtList3_sum_prn_settleprice;
	}
	@Override
	public String toString() {
		return "StatSaleVO [dt=" + dt + ", cnt=" + cnt + ", sumPrnSettleprice="
				+ sumPrnSettleprice + ", sumSupply=" + sumSupply + ", day="
				+ day + ", rtList1_cnt=" + rtList1_cnt
				+ ", rtList1_sum_prn_settleprice="
				+ rtList1_sum_prn_settleprice + ", rtList2_cnt=" + rtList2_cnt
				+ ", rtList2_sum_prn_settleprice="
				+ rtList2_sum_prn_settleprice + ", rtList2_sum_supply="
				+ rtList2_sum_supply + ", rtList2_net_sales="
				+ rtList2_net_sales + ", rtList3_cnt=" + rtList3_cnt
				+ ", rtList3_sum_prn_settleprice="
				+ rtList3_sum_prn_settleprice + "]";
	}
	
}
