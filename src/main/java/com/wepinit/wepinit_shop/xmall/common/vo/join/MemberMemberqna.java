package com.wepinit.wepinit_shop.xmall.common.vo.join;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

import java.util.Date;

/*
 * gd_member_qna
 * gd_member
 * JOIN
 */
public class MemberMemberqna extends PageMaker {
	
	//gd_member_qna
	private int sno;
	private String itemcd;
	private int parent=0;
	private String subject;
	private String contents;
	private int mno;
	private String email;
	private String mobile;
	private String mailling;
	private String sms;
	private long ordno;
	private Date regdt;
	private String ip;
	private Date maildt;
	private String name;
	private String password;
	private Date smsdt;
	private String sellerCd;
	
	//gd_member
	private String mid = "";
	private int klevel = 0;
//	private String name = "";
	private String nickname = "";
//	private String password = "";
	private int status = 0;
	private String resno1 = "";
	private String resno2 = "";
	private String sex = "";
	private String birth_year = "";
	private String birth = "";
	private String calendar = "";
//	private String email = "";
	private String zipcode = "";
	private String address ="";
	private String address_sub = "";
	private String phone ="";
//	private String mobile = "";
	private String fax = "";
	private String company = "";
	private String service = "";
	private String item = "";
	private String busino = "";
	private int emoney = 0;
	private int point = 0;
//	private String mailling = "";
//	private String sms = "";
	private String marriyn = "";
	private String marridate = "";
	private String job ="";
	private String interest = "";
//	private Date regdt = null;
	private Date lastlogin = null;
	private Date lastlogin_ip = null;
	private Date lastsale = null;
	private int cntlogin = 0;
	private int cntsale = 0;
	private int sumsale = 0;
	private String memo = "";
	private String recommid = "";
	private String ex1 = "";
	private String ex2 = "";
	private String ex3 = "";
	private String ex4 = "";
	private String ex5 = "";
	private String ex6 = "";
	private String lpinfo = "";
	private String profile = "";
	
	//
	private int recnt;
	private int ordcnt;
	
	private String[] arrmobile;
	
	
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	public String[] getArrmobile() {
		if(mobile != "") {
			arrmobile = mobile.split("-");
		}
		return arrmobile;
	}
	public void setArrmobile(String[] arrmobile) {
		this.arrmobile = arrmobile;
	}

	public int getRecnt() {
		return recnt;
	}
	public void setRecnt(int recnt) {
		this.recnt = recnt;
	}
	public int getOrdcnt() {
		return ordcnt;
	}
	public void setOrdcnt(int ordcnt) {
		this.ordcnt = ordcnt;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getItemcd() {
		return itemcd;
	}
	public void setItemcd(String itemcd) {
		this.itemcd = itemcd;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
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
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMailling() {
		return mailling;
	}
	public void setMailling(String mailling) {
		this.mailling = mailling;
	}
	public String getSms() {
		return sms;
	}
	public void setSms(String sms) {
		this.sms = sms;
	}
	public long getOrdno() {
		return ordno;
	}
	public void setOrdno(long ordno) {
		this.ordno = ordno;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getMaildt() {
		return maildt;
	}
	public void setMaildt(Date maildt) {
		this.maildt = maildt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getSmsdt() {
		return smsdt;
	}
	public void setSmsdt(Date smsdt) {
		this.smsdt = smsdt;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getKlevel() {
		return klevel;
	}
	public void setKlevel(int klevel) {
		this.klevel = klevel;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirth_year() {
		return birth_year;
	}
	public void setBirth_year(String birth_year) {
		this.birth_year = birth_year;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getCalendar() {
		return calendar;
	}
	public void setCalendar(String calendar) {
		this.calendar = calendar;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress_sub() {
		return address_sub;
	}
	public void setAddress_sub(String address_sub) {
		this.address_sub = address_sub;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
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
	public int getEmoney() {
		return emoney;
	}
	public void setEmoney(int emoney) {
		this.emoney = emoney;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getMarriyn() {
		return marriyn;
	}
	public void setMarriyn(String marriyn) {
		this.marriyn = marriyn;
	}
	public String getMarridate() {
		return marridate;
	}
	public void setMarridate(String marridate) {
		this.marridate = marridate;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public Date getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}
	public Date getLastlogin_ip() {
		return lastlogin_ip;
	}
	public void setLastlogin_ip(Date lastlogin_ip) {
		this.lastlogin_ip = lastlogin_ip;
	}
	public Date getLastsale() {
		return lastsale;
	}
	public void setLastsale(Date lastsale) {
		this.lastsale = lastsale;
	}
	public int getCntlogin() {
		return cntlogin;
	}
	public void setCntlogin(int cntlogin) {
		this.cntlogin = cntlogin;
	}
	public int getCntsale() {
		return cntsale;
	}
	public void setCntsale(int cntsale) {
		this.cntsale = cntsale;
	}
	public int getSumsale() {
		return sumsale;
	}
	public void setSumsale(int sumsale) {
		this.sumsale = sumsale;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getRecommid() {
		return recommid;
	}
	public void setRecommid(String recommid) {
		this.recommid = recommid;
	}
	public String getEx1() {
		return ex1;
	}
	public void setEx1(String ex1) {
		this.ex1 = ex1;
	}
	public String getEx2() {
		return ex2;
	}
	public void setEx2(String ex2) {
		this.ex2 = ex2;
	}
	public String getEx3() {
		return ex3;
	}
	public void setEx3(String ex3) {
		this.ex3 = ex3;
	}
	public String getEx4() {
		return ex4;
	}
	public void setEx4(String ex4) {
		this.ex4 = ex4;
	}
	public String getEx5() {
		return ex5;
	}
	public void setEx5(String ex5) {
		this.ex5 = ex5;
	}
	public String getEx6() {
		return ex6;
	}
	public void setEx6(String ex6) {
		this.ex6 = ex6;
	}
	public String getLpinfo() {
		return lpinfo;
	}
	public void setLpinfo(String lpinfo) {
		this.lpinfo = lpinfo;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}

}
