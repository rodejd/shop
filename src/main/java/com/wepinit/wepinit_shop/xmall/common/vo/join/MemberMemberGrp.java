package com.wepinit.wepinit_shop.xmall.common.vo.join;

import java.util.Arrays;
import java.util.Date;

public class MemberMemberGrp {
	
	//gd_member
	private int mno = 0;
	private String mid = "";
	private int klevel = 0;
	private String name = "";
	private String name1 = "";
	private String name2 = "";
	private String nickname = "";
	private String password = "";
	private int status = 0;
	private String resno1 = "";
	private String resno2 = "";
	private String sex = "";
	private String birthyear = "";
	private String birth = "";
	private String calendar = "";
	private String email = "";
	private String zipcode = "";
	private String address ="";
	private String addresssub = "";
	private String phone ="";
	private String mobile = "";
	private String fax = "";
	private String company = "";
	private String service = "";
	private String item = "";
	private String busino = "";
	private int emoney = 0;
	private int point = 0;
	private String mailling = "";
	private String sms = "";
	private String marriyn = "";
	private String marridate = "";
	private String job ="";
	private String interest = "";
	private String regdt2 = "";
	private Date regdt = null;
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
	private String customsnum = "";
	private String diszipcode = "";
	private String disaddress = "";
	private String disaddresssub = "";
	
	private String[] arrmobile;
	
	//gd_member_grp
	private int sno = 0; 
	private String grpnm = "";
	private int kLevel = 0;
	private int dc = 0;
	private int addEmoney = 0;
	private String freeDeliveryfee = "";
	/*private Date regdt = null;*/
	private Date moddt = null;
	private int cnt =0;
	private int smsCnt =0;
	
	
	
	
	public String getRegdt2() {
		return regdt2;
	}
	public void setRegdt2(String regdt2) {
		this.regdt2 = regdt2;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getGrpnm() {
		return grpnm;
	}
	public void setGrpnm(String grpnm) {
		this.grpnm = grpnm;
	}
	public int getkLevel() {
		return kLevel;
	}
	public void setkLevel(int kLevel) {
		this.kLevel = kLevel;
	}
	public int getDc() {
		return dc;
	}
	public void setDc(int dc) {
		this.dc = dc;
	}
	public int getAddEmoney() {
		return addEmoney;
	}
	public void setAddEmoney(int addEmoney) {
		this.addEmoney = addEmoney;
	}
	public String getFreeDeliveryfee() {
		return freeDeliveryfee;
	}
	public void setFreeDeliveryfee(String freeDeliveryfee) {
		this.freeDeliveryfee = freeDeliveryfee;
	}
	public Date getModdt() {
		return moddt;
	}
	public void setModdt(Date moddt) {
		this.moddt = moddt;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getSmsCnt() {
		return smsCnt;
	}
	public void setSmsCnt(int smsCnt) {
		this.smsCnt = smsCnt;
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
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	public String getBirthyear() {
		return birthyear;
	}
	public void setBirthyear(String birthyear) {
		this.birthyear = birthyear;
	}
	public String getAddresssub() {
		return addresssub;
	}
	public void setAddresssub(String addresssub) {
		this.addresssub = addresssub;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getCustomsnum() {
		return customsnum;
	}
	public void setCustomsnum(String customsnum) {
		this.customsnum = customsnum;
	}
	public String getDiszipcode() {
		return diszipcode;
	}
	public void setDiszipcode(String diszipcode) {
		this.diszipcode = diszipcode;
	}
	public String getDisaddress() {
		return disaddress;
	}
	public void setDisaddress(String disaddress) {
		this.disaddress = disaddress;
	}
	public String getDisaddresssub() {
		return disaddresssub;
	}
	public void setDisaddresssub(String disaddresssub) {
		this.disaddresssub = disaddresssub;
	}
	@Override
	public String toString() {
		return "MemberMemberGrp [mno=" + mno + ", mid=" + mid + ", klevel="
				+ klevel + ", name=" + name + ", nickname=" + nickname
				+ ", password=" + password + ", status=" + status + ", resno1="
				+ resno1 + ", resno2=" + resno2 + ", sex=" + sex
				+ ", birthyear=" + birthyear + ", birth=" + birth
				+ ", calendar=" + calendar + ", email=" + email + ", zipcode="
				+ zipcode + ", address=" + address + ", addresssub="
				+ addresssub + ", phone=" + phone + ", mobile=" + mobile
				+ ", fax=" + fax + ", company=" + company + ", service="
				+ service + ", item=" + item + ", busino=" + busino
				+ ", emoney=" + emoney + ", point=" + point + ", mailling="
				+ mailling + ", sms=" + sms + ", marriyn=" + marriyn
				+ ", marridate=" + marridate + ", job=" + job + ", interest="
				+ interest + ", regdt2=" + regdt2 + ", regdt=" + regdt
				+ ", lastlogin=" + lastlogin + ", lastlogin_ip=" + lastlogin_ip
				+ ", lastsale=" + lastsale + ", cntlogin=" + cntlogin
				+ ", cntsale=" + cntsale + ", sumsale=" + sumsale + ", memo="
				+ memo + ", recommid=" + recommid + ", ex1=" + ex1 + ", ex2="
				+ ex2 + ", ex3=" + ex3 + ", ex4=" + ex4 + ", ex5=" + ex5
				+ ", ex6=" + ex6 + ", lpinfo=" + lpinfo + ", profile="
				+ profile + ", arrmobile=" + Arrays.toString(arrmobile)
				+ ", sno=" + sno + ", grpnm=" + grpnm + ", kLevel=" + kLevel
				+ ", dc=" + dc + ", addEmoney=" + addEmoney
				+ ", freeDeliveryfee=" + freeDeliveryfee + ", moddt=" + moddt
				+ ", cnt=" + cnt + ", smsCnt=" + smsCnt + "]";
	}

	
}
