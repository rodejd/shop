package com.wepinit.wepinit_shop.xmall.common.vo;

import java.util.Date;

public class GdTax {
	private int sno				;//일련번호
	private long ordno			;//주문번호
	private int mNo			;//회원번호
	private String name			;//대표자
	private String company		;//회사명
	private String service		;//업태
	private String item			;//종목
	private String busino		;//사용자번호
	private String address		;//주소
	private String goodsnm		;//상품명(영문)
	private String goodsnmKR	;//상품명(국문)
	private String goodsnmCN	;//상품명(중문)
	private int price			;//판매가
	private int supply			;//공급가
	private int surtax			;//부가가치세
	private Date issuedate		;//발생일시
	private String ip			;//IP
	private Date regdt			;//등록일시
	private Date agreedt		;//동의일시
	private Date printdt		;//출력일시
	private int step			;//단계
	private String docNumber	;//발생번호
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public long getOrdno() {
		return ordno;
	}
	public void setOrdno(long ordno) {
		this.ordno = ordno;
	}
	public int getmNo() {
		return mNo;
	}
	public void setmNo(int mNo) {
		this.mNo = mNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getBusino() {
		return busino;
	}
	public void setBusino(String busino) {
		this.busino = busino;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGoodsnm() {
		return goodsnm;
	}
	public void setGoodsnm(String goodsnm) {
		this.goodsnm = goodsnm;
	}
	public String getGoodsnmKR() {
		return goodsnmKR;
	}
	public void setGoodsnmKR(String goodsnmKR) {
		this.goodsnmKR = goodsnmKR;
	}
	public String getGoodsnmCN() {
		return goodsnmCN;
	}
	public void setGoodsnmCN(String goodsnmCN) {
		this.goodsnmCN = goodsnmCN;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSupply() {
		return supply;
	}
	public void setSupply(int supply) {
		this.supply = supply;
	}
	public int getSurtax() {
		return surtax;
	}
	public void setSurtax(int surtax) {
		this.surtax = surtax;
	}
	public Date getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(Date issuedate) {
		this.issuedate = issuedate;
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
	public Date getAgreedt() {
		return agreedt;
	}
	public void setAgreedt(Date agreedt) {
		this.agreedt = agreedt;
	}
	public Date getPrintdt() {
		return printdt;
	}
	public void setPrintdt(Date printdt) {
		this.printdt = printdt;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getDocNumber() {
		return docNumber;
	}
	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}
	
	
}
