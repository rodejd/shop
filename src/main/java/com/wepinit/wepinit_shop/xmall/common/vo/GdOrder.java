package com.wepinit.wepinit_shop.xmall.common.vo;

import java.util.Date;


/**
 * @author LG
 *
 */
public class GdOrder {
	
	private long ordno = 0;
	private String nameorder = "";
	private String email = "";
	private String phoneorder = "";
	private String mobileorder = "";
	private String namereceiver = "";
	private String phonereceiver = "";
	private String mobileReceiverType = "";
	private String mobilereceiver = "";
	private String country = "";
	private String zipcode = "";
	private String address = "";
	private String address2 = "";
	private String city = "";
	private String state = "";
	private String customsNum = "";
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
	private int istep = 0;
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
	private String addDelivery = "";

	
	public int getIstep() {
		return istep;
	}
	public void setIstep(int istep) {
		this.istep = istep;
	}
	
	public String getAddDelivery() {
		return addDelivery;
	}
	public void setAddDelivery(String addDelivery) {
		this.addDelivery = addDelivery;
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
	public String getMobileReceiverType() {
		return mobileReceiverType;
	}
	public void setMobileReceiverType(String mobileReceiverType) {
		this.mobileReceiverType = mobileReceiverType;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCustomsNum() {
		return customsNum;
	}
	public void setCustomsNum(String customsNum) {
		this.customsNum = customsNum;
	}
	@Override
	public String toString() {
		return "GdOrder [ordno=" + ordno + ", nameorder=" + nameorder
				+ ", email=" + email + ", phoneorder=" + phoneorder
				+ ", mobileorder=" + mobileorder + ", namereceiver="
				+ namereceiver + ", phonereceiver=" + phonereceiver
				+ ", mobilereceiver=" + mobilereceiver + ", zipcode=" + zipcode
				+ ", address=" + address + ", memo=" + memo + ", adminmemo="
				+ adminmemo + ", settlelog=" + settlelog + ", settlekind="
				+ settlekind + ", settleprice=" + settleprice
				+ ", prnsettleprice=" + prnsettleprice + ", goodsprice="
				+ goodsprice + ", delititle=" + delititle + ", delitype="
				+ delitype + ", delimsg=" + delimsg + ", delivery=" + delivery
				+ ", coupon=" + coupon + ", emoney=" + emoney + ", memberdc="
				+ memberdc + ", enuri=" + enuri + ", reserve=" + reserve
				+ ", bankaccount=" + bankaccount + ", banksender=" + banksender
				+ ", deliveryno=" + deliveryno + ", deliverycode="
				+ deliverycode + ", step=" + step + ", step2=" + step2
				+ ", inistid=" + inistid + ", mno=" + mno + ", ip=" + ip
				+ ", referer=" + referer + ", orddt=" + orddt + ", cdt=" + cdt
				+ ", ddt=" + ddt + ", cyn=" + cyn + ", dyn=" + dyn
				+ ", confirm=" + confirm + ", confirmdt=" + confirmdt
				+ ", escrowyn=" + escrowyn + ", escrowconfirm=" + escrowconfirm
				+ ", escrowno=" + escrowno + ", cashreceipt=" + cashreceipt
				+ ", vaccount=" + vaccount
				+ ", oldordno=" + oldordno + ", eggfee=" + eggfee + ", eggyn="
				+ eggyn + ", eggno=" + eggno + ", eggpginfo=" + eggpginfo
				+ ", couponemoney=" + couponemoney + ", cashbagcard="
				+ cashbagcard + ", cashbagpoint=" + cashbagpoint + ", cbyn="
				+ cbyn + ", emailrecceiver=" + emailrecceiver + "]";
	}
	
	
	
}
