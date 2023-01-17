package com.wepinit.wepinit_shop.xmall.common.vo.join;

import java.util.Date;
import java.util.List;

/*
 * gd_order
 * gd_member
 * JOIN
 */
public class OrderMember {
	
	//gd_order
	private long ordno = 0;
	private String nameorder = "";
	private String email = "";
	private String phoneorder = "";
	private String mobileorder = "";
	private String namereceiver = "";
	private String phonereceiver = "";
	private String mobilereceiver = "";
	private String zipcode = "";
	private String address = "";
	private String memo = "";
	private String adminmemo = "";
	private String settlelog = "";
	private String settlekind = "";
	private int settleprice = 0;
	private int prnsettleprice = 0;
	private int goodsprice = 0;
	private String delititle = "";
	private String delitype = "";
	private String delimsg = "";
	private String deliveryurl = "";
	private int delivery = 0;
	private int coupon = 0;
	private int emoney = 0;
	private int memberdc = 0;
	private int enuri = 0;
	private int reserve = 0;
	private int bankaccount = 0;
	private String banksender = "";
	private int deliveryno = 0;
	private String deliverycode = "";
	private int step = 0;
	private int step2 = 0;
	private String inistid = "";
	private int mno = 0;
	private String ip = "";
	private String referer = "";
	private Date orddt = null;
	private Date cdt = null;
	private Date ddt = null;
	private String cyn = "";
	private String dyn = "";
	private String confirm = "";
	private Date confirmdt = null;
	private String escrowyn = "";
	private int escrowconfirm = 0;
	private String escrowno = "";
	private String cashreceipt = "";
	private String vaccount = "";
	private long oldordno = 0;
	private int eggfee = 0;
	private String eggyn = "";
	private String eggno = "";
	private String eggpginfo = "";
	private int couponemoney = 0;
	private String cashbagcard = "";
	private int cashbagpoint = 0;
	private String cbyn = "";
	private String emailrecceiver = "";
	
	//gd_member
//	private int mno = 0;
	private String mid = "";
	private int klevel = 0;
	private String name = "";
	private String nickname = "";
	private String password = "";
	private int status = 0;
	private String resno1 = "";
	private String resno2 = "";
	private String sex = "";
	private String birthyear = "";
	private String birth = "";
	private String calendar = "";
//	private String email = "";
//	private String zipcode = "";
//	private String address ="";
	private String addresssub = "";
	private String phone ="";
	private String mobile = "";
	private String fax = "";
	private String company = "";
	private String service = "";
	private String item = "";
	private String busino = "";
//	private int emoney = 0;
	private int point = 0;
	private String mailling = "";
	private String sms = "";
	private String marriyn = "";
	private String marridate = "";
	private String job ="";
	private String interest = "";
	private Date regdt = null;
	private Date lastlogin = null;
	private Date lastloginip = null;
	private Date lastsale = null;
	private int cntlogin = 0;
	private int cntsale = 0;
	private int sumsale = 0;
//	private String memo = "";
	private String recommid = "";
	private String ex1 = "";
	private String ex2 = "";
	private String ex3 = "";
	private String ex4 = "";
	private String ex5 = "";
	private String ex6 = "";
	private String lpinfo = "";
	private String profile = "";
	
	// 추가
	private String goodsnm;
	private String goodsnmKR;
	private String goodsnmCN;
	private int goodsnmcnt;
	private int sno;
	private int seller_price = 0;
	
	//프론트 추가
	private String imgs;
	private String brandno;
	
	
	private String skin;
	private String agent;
	private String customsNum;
	private String goodsno;
	private String sellerCd;
	private String brandnm;
	private String ea;
	private String country;
	private String nameOrder   ;
	private String customs_num ;
	private String brandnmKR   ;
	private String brandnmCN   ;
	private String opt1        ;
	private String opt2        ;
	private int price       ;
	private int addemoney   ;
	private String couponcd    ;
	private String couponNm    ;
	private int couponPrice ; 
	private String dcCouponcd    ;
	private String dcCodeNm    ;
	private int dcCodePrice ;
	private String estimate    ;
	
	private String sellerNm;	//판매사명
	//20191218 이현빈 판매사코드추가
	private String sellercd;	//판매사코드
	private int istep = 0;
	
	
	private List<PopupOrder1> itemList = null;

	private int myritz_price = 0;
	
	private String myritzCd;
	
	private String myritzNm;	//판매사명
	
	private String myritzcd;	//판매사코드

	
	public int getMyritz_price() {
		return myritz_price;
	}
	public void setMyritz_price(int myritz_price) {
		this.myritz_price = myritz_price;
	}
	public String getMyritzCd() {
		return myritzCd;
	}
	public void setMyritzCd(String myritzCd) {
		this.myritzCd = myritzCd;
	}
	public String getMyritzNm() {
		return myritzNm;
	}
	public void setMyritzNm(String myritzNm) {
		this.myritzNm = myritzNm;
	}
	public String getMyritzcd() {
		return myritzcd;
	}
	public void setMyritzcd(String myritzcd) {
		this.myritzcd = myritzcd;
	}
	public String getBrandno() {
		return brandno;
	}
	public void setBrandno(String brandno) {
		this.brandno = brandno;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public int getAddemoney() {
		return addemoney;
	}
	public void setAddemoney(int addemoney) {
		this.addemoney = addemoney;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getCustomsNum() {
		return customsNum;
	}
	public void setCustomsNum(String customsNum) {
		this.customsNum = customsNum;
	}
	public String getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	public String getBrandnm() {
		return brandnm;
	}
	public void setBrandnm(String brandnm) {
		this.brandnm = brandnm;
	}
	public String getEa() {
		return ea;
	}
	public void setEa(String ea) {
		this.ea = ea;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getNameOrder() {
		return nameOrder;
	}
	public void setNameOrder(String nameOrder) {
		this.nameOrder = nameOrder;
	}
	public String getCustoms_num() {
		return customs_num;
	}
	public void setCustoms_num(String customs_num) {
		this.customs_num = customs_num;
	}
	public String getBrandnmKR() {
		return brandnmKR;
	}
	public void setBrandnmKR(String brandnmKR) {
		this.brandnmKR = brandnmKR;
	}
	public String getBrandnmCN() {
		return brandnmCN;
	}
	public void setBrandnmCN(String brandnmCN) {
		this.brandnmCN = brandnmCN;
	}
	public String getOpt1() {
		return opt1;
	}
	public void setOpt1(String opt1) {
		this.opt1 = opt1;
	}
	public String getOpt2() {
		return opt2;
	}
	public void setOpt2(String opt2) {
		this.opt2 = opt2;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCouponNm() {
		return couponNm;
	}
	public void setCouponNm(String couponNm) {
		this.couponNm = couponNm;
	}
	public int getCouponPrice() {
		return couponPrice;
	}
	public void setCouponPrice(int couponPrice) {
		this.couponPrice = couponPrice;
	}
	public String getCouponcd() {
		return couponcd;
	}
	public void setCouponcd(String couponcd) {
		this.couponcd = couponcd;
	}
	public String getDcCouponcd() {
		return dcCouponcd;
	}
	public void setDcCouponcd(String dcCouponcd) {
		this.dcCouponcd = dcCouponcd;
	}
	public String getDcCodeNm() {
		return dcCodeNm;
	}
	public void setDcCodeNm(String dcCodeNm) {
		this.dcCodeNm = dcCodeNm;
	}
	public int getDcCodePrice() {
		return dcCodePrice;
	}
	public void setDcCodePrice(int dcCodePrice) {
		this.dcCodePrice = dcCodePrice;
	}
	public String getEstimate() {
		return estimate;
	}
	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}
	public String getSkin() {
		return skin;
	}
	public void setSkin(String skin) {
		this.skin = skin;
	}
	public int getIstep(){
		return this.istep;
	}
	public void setIstep(int istep){
		this.istep = istep;
	}
	public List<PopupOrder1> getItemList() {
		return itemList;
	}
	public void setItemList(List<PopupOrder1> itemList) {
		this.itemList = itemList;
	}
	public String getSellercd() {
		return sellercd;
	}
	public void setSellercd(String sellercd) {
		this.sellercd = sellercd;
	}
	public int getSeller_price() {
		return seller_price;
	}
	public void setSeller_price(int seller_price) {
		this.seller_price = seller_price;
	}
	/**
	 * @return the sno
	 */
	public int getSno() {
		return sno;
	}
	/**
	 * @param sno the sno to set
	 */
	public void setSno(int sno) {
		this.sno = sno;
	}
	public long getOrdno() {
		return ordno;
	}
	public void setOrdno(long ordno) {
		this.ordno = ordno;
	}
	public String getNameorder() {
		return nameorder;
	}
	public void setNameorder(String nameorder) {
		this.nameorder = nameorder;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneorder() {
		return phoneorder;
	}
	public void setPhoneorder(String phoneorder) {
		this.phoneorder = phoneorder;
	}
	public String getMobileorder() {
		return mobileorder;
	}
	public void setMobileorder(String mobileorder) {
		this.mobileorder = mobileorder;
	}
	public String getNamereceiver() {
		return namereceiver;
	}
	public void setNamereceiver(String namereceiver) {
		this.namereceiver = namereceiver;
	}
	public String getPhonereceiver() {
		return phonereceiver;
	}
	public void setPhonereceiver(String phonereceiver) {
		this.phonereceiver = phonereceiver;
	}
	public String getMobilereceiver() {
		return mobilereceiver;
	}
	public void setMobilereceiver(String mobilereceiver) {
		this.mobilereceiver = mobilereceiver;
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getAdminmemo() {
		return adminmemo;
	}
	public void setAdminmemo(String adminmemo) {
		this.adminmemo = adminmemo;
	}
	public String getSettlelog() {
		return settlelog;
	}
	public void setSettlelog(String settlelog) {
		this.settlelog = settlelog;
	}
	public String getSettlekind() {
		return settlekind;
	}
	public void setSettlekind(String settlekind) {
		this.settlekind = settlekind;
	}
	public int getSettleprice() {
		return settleprice;
	}
	public void setSettleprice(int settleprice) {
		this.settleprice = settleprice;
	}
	public int getPrnsettleprice() {
		return prnsettleprice;
	}
	public void setPrnsettleprice(int prnsettleprice) {
		this.prnsettleprice = prnsettleprice;
	}
	public int getGoodsprice() {
		return goodsprice;
	}
	public void setGoodsprice(int goodsprice) {
		this.goodsprice = goodsprice;
	}
	public String getDelititle() {
		return delititle;
	}
	public void setDelititle(String delititle) {
		this.delititle = delititle;
	}
	public String getDelitype() {
		return delitype;
	}
	public void setDelitype(String delitype) {
		this.delitype = delitype;
	}
	public String getDelimsg() {
		return delimsg;
	}
	public void setDelimsg(String delimsg) {
		this.delimsg = delimsg;
	}
	public int getDelivery() {
		return delivery;
	}
	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}
	public int getCoupon() {
		return coupon;
	}
	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}
	public int getEmoney() {
		return emoney;
	}
	public void setEmoney(int emoney) {
		this.emoney = emoney;
	}
	public int getMemberdc() {
		return memberdc;
	}
	public void setMemberdc(int memberdc) {
		this.memberdc = memberdc;
	}
	public int getEnuri() {
		return enuri;
	}
	public void setEnuri(int enuri) {
		this.enuri = enuri;
	}
	public int getReserve() {
		return reserve;
	}
	public void setReserve(int reserve) {
		this.reserve = reserve;
	}
	public int getBankaccount() {
		return bankaccount;
	}
	public void setBankaccount(int bankaccount) {
		this.bankaccount = bankaccount;
	}
	public String getBanksender() {
		return banksender;
	}
	public void setBanksender(String banksender) {
		this.banksender = banksender;
	}
	public int getDeliveryno() {
		return deliveryno;
	}
	public void setDeliveryno(int deliveryno) {
		this.deliveryno = deliveryno;
	}
	public String getDeliverycode() {
		return deliverycode;
	}
	public void setDeliverycode(String deliverycode) {
		this.deliverycode = deliverycode;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getStep2() {
		return step2;
	}
	public void setStep2(int step2) {
		this.step2 = step2;
	}
	public String getInistid() {
		return inistid;
	}
	public void setInistid(String inistid) {
		this.inistid = inistid;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public Date getOrddt() {
		return orddt;
	}
	public void setOrddt(Date orddt) {
		this.orddt = orddt;
	}
	public Date getCdt() {
		return cdt;
	}
	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}
	public Date getDdt() {
		return ddt;
	}
	public void setDdt(Date ddt) {
		this.ddt = ddt;
	}
	public String getCyn() {
		return cyn;
	}
	public void setCyn(String cyn) {
		this.cyn = cyn;
	}
	public String getDyn() {
		return dyn;
	}
	public void setDyn(String dyn) {
		this.dyn = dyn;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public Date getConfirmdt() {
		return confirmdt;
	}
	public void setConfirmdt(Date confirmdt) {
		this.confirmdt = confirmdt;
	}
	public String getEscrowyn() {
		return escrowyn;
	}
	public void setEscrowyn(String escrowyn) {
		this.escrowyn = escrowyn;
	}
	public int getEscrowconfirm() {
		return escrowconfirm;
	}
	public void setEscrowconfirm(int escrowconfirm) {
		this.escrowconfirm = escrowconfirm;
	}
	public String getEscrowno() {
		return escrowno;
	}
	public void setEscrowno(String escrowno) {
		this.escrowno = escrowno;
	}
	public String getCashreceipt() {
		return cashreceipt;
	}
	public void setCashreceipt(String cashreceipt) {
		this.cashreceipt = cashreceipt;
	}
	public String getVaccount() {
		return vaccount;
	}
	public void setVaccount(String vaccount) {
		this.vaccount = vaccount;
	}
	public long getOldordno() {
		return oldordno;
	}
	public void setOldordno(long oldordno) {
		this.oldordno = oldordno;
	}
	public int getEggfee() {
		return eggfee;
	}
	public void setEggfee(int eggfee) {
		this.eggfee = eggfee;
	}
	public String getEggyn() {
		return eggyn;
	}
	public void setEggyn(String eggyn) {
		this.eggyn = eggyn;
	}
	public String getEggno() {
		return eggno;
	}
	public void setEggno(String eggno) {
		this.eggno = eggno;
	}
	public String getEggpginfo() {
		return eggpginfo;
	}
	public void setEggpginfo(String eggpginfo) {
		this.eggpginfo = eggpginfo;
	}
	public int getCouponemoney() {
		return couponemoney;
	}
	public void setCouponemoney(int couponemoney) {
		this.couponemoney = couponemoney;
	}
	public String getCashbagcard() {
		return cashbagcard;
	}
	public void setCashbagcard(String cashbagcard) {
		this.cashbagcard = cashbagcard;
	}
	public int getCashbagpoint() {
		return cashbagpoint;
	}
	public void setCashbagpoint(int cashbagpoint) {
		this.cashbagpoint = cashbagpoint;
	}
	public String getCbyn() {
		return cbyn;
	}
	public void setCbyn(String cbyn) {
		this.cbyn = cbyn;
	}
	public String getEmailrecceiver() {
		return emailrecceiver;
	}
	public void setEmailrecceiver(String emailrecceiver) {
		this.emailrecceiver = emailrecceiver;
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
	public String getAddresssub() {
		return addresssub;
	}
	public void setAddresssub(String addresssub) {
		this.addresssub = addresssub;
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
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public Date getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}
	public Date getLastloginip() {
		return lastloginip;
	}
	public void setLastloginip(Date lastloginip) {
		this.lastloginip = lastloginip;
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
	public int getGoodsnmcnt() {
		return goodsnmcnt;
	}
	public void setGoodsnmcnt(int goodsnmcnt) {
		this.goodsnmcnt = goodsnmcnt;
	}
	public void setDeliveryurl(String deliveryurl) {
		this.deliveryurl = deliveryurl;
	}
	public String getDeliveryurl() {
		return deliveryurl;
	}
	
	public String getSellerNm() {
		return sellerNm;
	}
	public void setSellerNm(String sellerNm) {
		this.sellerNm = sellerNm;
	}
	@Override
	public String toString() {
		return "OrderMember [ordno=" + ordno + ", nameorder=" + nameorder
				+ ", email=" + email + ", phoneorder=" + phoneorder
				+ ", mobileorder=" + mobileorder + ", namereceiver="
				+ namereceiver + ", phonereceiver=" + phonereceiver
				+ ", mobilereceiver=" + mobilereceiver + ", zipcode=" + zipcode
				+ ", address=" + address + ", memo=" + memo + ", adminmemo="
				+ adminmemo + ", settlelog=" + settlelog + ", settlekind="
				+ settlekind + ", settleprice=" + settleprice
				+ ", prnsettleprice=" + prnsettleprice + ", goodsprice="
				+ goodsprice + ", delititle=" + delititle + ", delitype="
				+ delitype + ", delimsg=" + delimsg + ", deliveryurl="
				+ deliveryurl + ", delivery=" + delivery + ", coupon=" + coupon
				+ ", emoney=" + emoney + ", memberdc=" + memberdc + ", enuri="
				+ enuri + ", reserve=" + reserve + ", bankaccount="
				+ bankaccount + ", banksender=" + banksender + ", deliveryno="
				+ deliveryno + ", deliverycode=" + deliverycode + ", step="
				+ step + ", step2=" + step2 + ", inistid=" + inistid + ", mno="
				+ mno + ", ip=" + ip + ", referer=" + referer + ", orddt="
				+ orddt + ", cdt=" + cdt + ", ddt=" + ddt + ", cyn=" + cyn
				+ ", dyn=" + dyn + ", confirm=" + confirm + ", confirmdt="
				+ confirmdt + ", escrowyn=" + escrowyn + ", escrowconfirm="
				+ escrowconfirm + ", escrowno=" + escrowno + ", cashreceipt="
				+ cashreceipt + ", vaccount=" + vaccount
				+ ", oldordno=" + oldordno + ", eggfee=" + eggfee + ", eggyn="
				+ eggyn + ", eggno=" + eggno + ", eggpginfo=" + eggpginfo
				+ ", couponemoney=" + couponemoney + ", cashbagcard="
				+ cashbagcard + ", cashbagpoint=" + cashbagpoint + ", cbyn="
				+ cbyn + ", emailrecceiver=" + emailrecceiver + ", mid=" + mid
				+ ", klevel=" + klevel + ", name=" + name + ", nickname="
				+ nickname + ", password=" + password + ", status=" + status
				+ ", resno1=" + resno1 + ", resno2=" + resno2 + ", sex=" + sex
				+ ", birthyear=" + birthyear + ", birth=" + birth
				+ ", calendar=" + calendar + ", addresssub=" + addresssub
				+ ", phone=" + phone + ", mobile=" + mobile + ", fax=" + fax
				+ ", company=" + company + ", service=" + service + ", item="
				+ item + ", busino=" + busino + ", point=" + point
				+ ", mailling=" + mailling + ", sms=" + sms + ", marriyn="
				+ marriyn + ", marridate=" + marridate + ", job=" + job
				+ ", interest=" + interest + ", regdt=" + regdt
				+ ", lastlogin=" + lastlogin + ", lastloginip=" + lastloginip
				+ ", lastsale=" + lastsale + ", cntlogin=" + cntlogin
				+ ", cntsale=" + cntsale + ", sumsale=" + sumsale
				+ ", recommid=" + recommid + ", ex1=" + ex1 + ", ex2=" + ex2
				+ ", ex3=" + ex3 + ", ex4=" + ex4 + ", ex5=" + ex5 + ", ex6="
				+ ex6 + ", lpinfo=" + lpinfo + ", profile=" + profile
				+ ", goodsnm=" + goodsnm + ", goodsnmcnt=" + goodsnmcnt
				+ ", sno=" + sno + ", sellerNm=" + sellerNm + "]";
	}
}
