package com.wepinit.wepinit_shop.xmall.common.vo.join;

import java.util.Date;

public class OrdercancelOrderitemMember {
	//add object
	private int ccnt;
	private OrderitemOrdercancel oiocObject;
	//gd_order_cencel = oC
	private int oCsno;
	private String oCordno;
	private int oCcode;
	private String oCmemo;
	private String oCname;
	private Date oCregdt;
	private int oCrprice;
	private int oCrfee;
	private int oCremoney;
	private Date oCccdt;
	private int oCbankcode;
	private String oCbankaccount;
	private String oCbankuser;
	//gd_order_item = oI
	private int oIsno;
	private String oIordno;
	private String oIgoodsno;
	private String oIgoodsnm;
	private String oIgoodsnmKR;
	private String oIgoodsnmCN;
	private String oImaker;
	private String oIbrandnm;
	private String oIopt1;
	private String oIopt2;
	private String oIaddopt;
	private int oIprice;
	private int oIsupply;
	private int oIreserve;
	private int oIcoupon;
	private int oImemberdc;
	private int oIea;
	private String oIdvno;
	private int oIdvcode;
	private int oIistep;
	private String oIstockyn;
	private String oIcyn;
	private String oIdyn;
	private int oIcancel;
	private int oItax;
	private String oIoptno;
	private int oIcouponemoney;
	//gd_order = oR
	private String oRordno;
	private String oRnameorder;
	private String oRemail;
	private String oRphoneorder;
	private String oRmobileorder;
	private String oRnamereceiver;
	private String oRphonereceiver;
	private String oRmobilereceiver;
	private String oRzipcode;
	private String oRaddress;
	private String oRmemo;
	private String oRadminmemo;
	private String oRsettlelog;
	private String oRsettlekind;
	private int oRsettleprice;
	private int oRpmsettleprice;
	private int oRgoodsprice;
	private String oRdelititle;
	private String oRdelitype;
	private String oRdelimsg;
	private int oRdelivery;
	private int oRaddDelivery;
	private int oRcoupon;
	private int oRemoney;
	private int oRmemberdc;
	private int oRenuri;
	private int oRreserve;
	private int oRbankaccount;
	private String oRbankSender;
	private int oRdeliveryno;
	private String oRdeliverycode;
	private int oRstep;
	private int oRstep2;
	private String oRinistid;
	private int oRmNo;
	private String oRip;
	private String oRreferer;
	private Date oRorddt;
	private Date oRcdt;
	private Date oRddt;
	private String oRcyn;
	private String oRdyn;
	private String oRconfirm;
	private Date oRconfirmdt;
	private String oRescrowyn;
	private int oRescrowconfirm;
	private String oRescrowno;
	private String oRcashreceipt;
	private String oRvaccount;
	private String oRoldordno;
	private int oReggFee;
	private String oReggyn;
	private String oReggno	;
	private String oReggpginfo;
	private int oRcouponemoney;
	private String oRcashbagcard;
	private int oRcashbagpoint;
	private String oRcbyn;
	private String oRemailrecceiver;

	//gd_member =mB
	private int mBmNo ;
	private String mBmid ;
	private int mBklevel ;
	private String mBname ;
	private String mBnickname ;
	private String mBpassword ;
	private int mBstatus ;
	private String mBresno1 ;
	private String mBresno2 ;
	private String mBsex ;
	private String mBbirthyear ;
	private String mBbirth ;
	private String mBcalendar ;
	private String mBemail ;
	private String mBzipcode ;
	private String mBaddress ;
	private String mBaddresssub ;
	private String mBphone ;
	private String mBmBmobile ;
	private String mBfax ;
	private String mBcompany ;
	private String mBservice ;
	private String mBitem ;
	private String mBbusino ;
	private int mBemoney ;
	private int mBpoint ;
	private String mBmailling ;
	private String mBsms ;
	private String mBmarriyn ;
	private String mBmarridate ;
	private String mBjob ;
	private String mBinterest ;
	private Date mBregdt ;
	private Date mBlastlogin ;
	private Date mBlastloginip ;
	private Date mBlastsale ;
	private int mBcntlogin ;
	private int mBcntsale ;
	private int mBsumsale ;
	private String mBmemo ;
	private String mBrecommid ;
	private String mBex1 ;
	private String mBex2 ;
	private String mBex3 ;
	private String mBex4 ;
	private String mBex5 ;
	private String mBex6 ;
	private String mBLPINFO ;
	private String mBprofile ;
	//gd_goods_link =gL
	private int gLsno	;
	private int gLgoodsno;
	private String gLcategory;
	private int gLsort;
	private int gLhidden;
	
	private int lCdelivery;
	private int lCaddDelivery;
	
	//gd_seller
	private String sellerNm;
	private String ssSellerCd;				//판매사 세션의 판매사 코드

	//gd_seller
	private String myritzNm;
	private String ssMyritzCd;				//판매사 세션의 판매사 코드

	//환불금액
	private int refundPrice;
	
	//판매사 합계금액
	private int sSumCoupon;
	private int sSumMemberDC;
	private int sSumAddopt;
	private int sSumSettlePrice;
	private int sDelivery;
	private int sAddDelivery;
	private int sSumGoodsPrice;

	
	private String allCouponPrice;
	
	private String optname;
	
	
	
	public String getMyritzNm() {
		return myritzNm;
	}
	public void setMyritzNm(String myritzNm) {
		this.myritzNm = myritzNm;
	}
	public String getSsMyritzCd() {
		return ssMyritzCd;
	}
	public void setSsMyritzCd(String ssMyritzCd) {
		this.ssMyritzCd = ssMyritzCd;
	}
	public String getOptname() {
		return optname;
	}
	public void setOptname(String optname) {
		this.optname = optname;
	}
	public String getAllCouponPrice() {
		return allCouponPrice;
	}
	public void setAllCouponPrice(String allCouponPrice) {
		this.allCouponPrice = allCouponPrice;
	}
	public int getsSumGoodsPrice() {
		return sSumGoodsPrice;
	}
	public void setsSumGoodsPrice(int sSumGoodsPrice) {
		this.sSumGoodsPrice = sSumGoodsPrice;
	}
	public int getsDelivery() {
		return sDelivery;
	}
	public void setsDelivery(int sDelivery) {
		this.sDelivery = sDelivery;
	}
	public int getsAddDelivery() {
		return sAddDelivery;
	}
	public void setsAddDelivery(int sAddDelivery) {
		this.sAddDelivery = sAddDelivery;
	}
	public int getsSumCoupon() {
		return sSumCoupon;
	}
	public void setsSumCoupon(int sSumCoupon) {
		this.sSumCoupon = sSumCoupon;
	}
	public int getsSumMemberDC() {
		return sSumMemberDC;
	}
	public void setsSumMemberDC(int sSumMemberDC) {
		this.sSumMemberDC = sSumMemberDC;
	}
	public int getsSumAddopt() {
		return sSumAddopt;
	}
	public void setsSumAddopt(int sSumAddopt) {
		this.sSumAddopt = sSumAddopt;
	}
	public int getsSumSettlePrice() {
		return sSumSettlePrice;
	}
	public void setsSumSettlePrice(int sSumSettlePrice) {
		this.sSumSettlePrice = sSumSettlePrice;
	}
	public int getlCdelivery() {
		return lCdelivery;
	}
	public void setlCdelivery(int lCdelivery) {
		this.lCdelivery = lCdelivery;
	}
	public int getlCaddDelivery() {
		return lCaddDelivery;
	}
	public void setlCaddDelivery(int lCaddDelivery) {
		this.lCaddDelivery = lCaddDelivery;
	}
	public int getRefundPrice(){
		return refundPrice;
	}
	public void setRefundPrice(int refundPrice){
		this.refundPrice = refundPrice;
	}
	public int getoRaddDelivery() {
		return oRaddDelivery;
	}
	public void setoRaddDelivery(int oRaddDelivery) {
		this.oRaddDelivery = oRaddDelivery;
	}


	public int getCcnt() {
		return ccnt;
	}
	public void setCcnt(int ccnt) {
		this.ccnt = ccnt;
	}
	public OrderitemOrdercancel getOiocObject() {
		return oiocObject;
	}
	public void setOiocObject(OrderitemOrdercancel oiocObject) {
		this.oiocObject = oiocObject;
	}
	public int getoCsno() {
		return oCsno;
	}
	public void setoCsno(int oCsno) {
		this.oCsno = oCsno;
	}
	public String getoCordno() {
		return oCordno;
	}
	public void setoCordno(String oCordno) {
		this.oCordno = oCordno;
	}
	public int getoCcode() {
		return oCcode;
	}
	public void setoCcode(int oCcode) {
		this.oCcode = oCcode;
	}
	public String getoCmemo() {
		return oCmemo;
	}
	public void setoCmemo(String oCmemo) {
		this.oCmemo = oCmemo;
	}
	public String getoCname() {
		return oCname;
	}
	public void setoCname(String oCname) {
		this.oCname = oCname;
	}
	public Date getoCregdt() {
		return oCregdt;
	}
	public void setoCregdt(Date oCregdt) {
		this.oCregdt = oCregdt;
	}
	public int getoCrprice() {
		return oCrprice;
	}
	public void setoCrprice(int oCrprice) {
		this.oCrprice = oCrprice;
	}
	public int getoCrfee() {
		return oCrfee;
	}
	public void setoCrfee(int oCrfee) {
		this.oCrfee = oCrfee;
	}
	public int getoCremoney() {
		return oCremoney;
	}
	public void setoCremoney(int oCremoney) {
		this.oCremoney = oCremoney;
	}
	public Date getoCccdt() {
		return oCccdt;
	}
	public void setoCccdt(Date oCccdt) {
		this.oCccdt = oCccdt;
	}
	public int getoCbankcode() {
		return oCbankcode;
	}
	public void setoCbankcode(int oCbankcode) {
		this.oCbankcode = oCbankcode;
	}
	public String getoCbankaccount() {
		return oCbankaccount;
	}
	public void setoCbankaccount(String oCbankaccount) {
		this.oCbankaccount = oCbankaccount;
	}
	public String getoCbankuser() {
		return oCbankuser;
	}
	public void setoCbankuser(String oCbankuser) {
		this.oCbankuser = oCbankuser;
	}
	public int getoIsno() {
		return oIsno;
	}
	public void setoIsno(int oIsno) {
		this.oIsno = oIsno;
	}
	public String getoIordno() {
		return oIordno;
	}
	public void setoIordno(String oIordno) {
		this.oIordno = oIordno;
	}
	public String getoIgoodsno() {
		return oIgoodsno;
	}
	public void setoIgoodsno(String oIgoodsno) {
		this.oIgoodsno = oIgoodsno;
	}
	public String getoIgoodsnm() {
		return oIgoodsnm;
	}
	public void setoIgoodsnm(String oIgoodsnm) {
		this.oIgoodsnm = oIgoodsnm;
	}
	public String getoIgoodsnmKR() {
		return oIgoodsnmKR;
	}
	public void setoIgoodsnmKR(String oIgoodsnmKR) {
		this.oIgoodsnmKR = oIgoodsnmKR;
	}
	public String getoIgoodsnmCN() {
		return oIgoodsnmCN;
	}
	public void setoIgoodsnmCN(String oIgoodsnmCN) {
		this.oIgoodsnmCN = oIgoodsnmCN;
	}
	public String getoImaker() {
		return oImaker;
	}
	public void setoImaker(String oImaker) {
		this.oImaker = oImaker;
	}
	public String getoIbrandnm() {
		return oIbrandnm;
	}
	public void setoIbrandnm(String oIbrandnm) {
		this.oIbrandnm = oIbrandnm;
	}
	public String getoIopt1() {
		return oIopt1;
	}
	public void setoIopt1(String oIopt1) {
		this.oIopt1 = oIopt1;
	}
	public String getoIopt2() {
		return oIopt2;
	}
	public void setoIopt2(String oIopt2) {
		this.oIopt2 = oIopt2;
	}
	public String getoIaddopt() {
		return oIaddopt;
	}
	public void setoIaddopt(String oIaddopt) {
		this.oIaddopt = oIaddopt;
	}
	public int getoIprice() {
		return oIprice;
	}
	public void setoIprice(int oIprice) {
		this.oIprice = oIprice;
	}
	public int getoIsupply() {
		return oIsupply;
	}
	public void setoIsupply(int oIsupply) {
		this.oIsupply = oIsupply;
	}
	public int getoIreserve() {
		return oIreserve;
	}
	public void setoIreserve(int oIreserve) {
		this.oIreserve = oIreserve;
	}
	public int getoIcoupon() {
		return oIcoupon;
	}
	public void setoIcoupon(int oIcoupon) {
		this.oIcoupon = oIcoupon;
	}
	public int getoImemberdc() {
		return oImemberdc;
	}
	public void setoImemberdc(int oImemberdc) {
		this.oImemberdc = oImemberdc;
	}
	public int getoIea() {
		return oIea;
	}
	public void setoIea(int oIea) {
		this.oIea = oIea;
	}
	public String getoIdvno() {
		return oIdvno;
	}
	public void setoIdvno(String oIdvno) {
		this.oIdvno = oIdvno;
	}
	public int getoIdvcode() {
		return oIdvcode;
	}
	public void setoIdvcode(int oIdvcode) {
		this.oIdvcode = oIdvcode;
	}
	public int getoIistep() {
		return oIistep;
	}
	public void setoIistep(int oIistep) {
		this.oIistep = oIistep;
	}
	public String getoIstockyn() {
		return oIstockyn;
	}
	public void setoIstockyn(String oIstockyn) {
		this.oIstockyn = oIstockyn;
	}
	public String getoIcyn() {
		return oIcyn;
	}
	public void setoIcyn(String oIcyn) {
		this.oIcyn = oIcyn;
	}
	public String getoIdyn() {
		return oIdyn;
	}
	public void setoIdyn(String oIdyn) {
		this.oIdyn = oIdyn;
	}
	public int getoIcancel() {
		return oIcancel;
	}
	public void setoIcancel(int oIcancel) {
		this.oIcancel = oIcancel;
	}
	public int getoItax() {
		return oItax;
	}
	public void setoItax(int oItax) {
		this.oItax = oItax;
	}
	public String getoIoptno() {
		return oIoptno;
	}
	public void setoIoptno(String oIoptno) {
		this.oIoptno = oIoptno;
	}
	public int getoIcouponemoney() {
		return oIcouponemoney;
	}
	public void setoIcouponemoney(int oIcouponemoney) {
		this.oIcouponemoney = oIcouponemoney;
	}
	public String getoRordno() {
		return oRordno;
	}
	public void setoRordno(String oRordno) {
		this.oRordno = oRordno;
	}
	public String getoRnameorder() {
		return oRnameorder;
	}
	public void setoRnameorder(String oRnameorder) {
		this.oRnameorder = oRnameorder;
	}
	public String getoRemail() {
		return oRemail;
	}
	public void setoRemail(String oRemail) {
		this.oRemail = oRemail;
	}
	public String getoRphoneorder() {
		return oRphoneorder;
	}
	public void setoRphoneorder(String oRphoneorder) {
		this.oRphoneorder = oRphoneorder;
	}
	public String getoRmobileorder() {
		return oRmobileorder;
	}
	public void setoRmobileorder(String oRmobileorder) {
		this.oRmobileorder = oRmobileorder;
	}
	public String getoRnamereceiver() {
		return oRnamereceiver;
	}
	public void setoRnamereceiver(String oRnamereceiver) {
		this.oRnamereceiver = oRnamereceiver;
	}
	public String getoRphonereceiver() {
		return oRphonereceiver;
	}
	public void setoRphonereceiver(String oRphonereceiver) {
		this.oRphonereceiver = oRphonereceiver;
	}
	public String getoRmobilereceiver() {
		return oRmobilereceiver;
	}
	public void setoRmobilereceiver(String oRmobilereceiver) {
		this.oRmobilereceiver = oRmobilereceiver;
	}
	public String getoRzipcode() {
		return oRzipcode;
	}
	public void setoRzipcode(String oRzipcode) {
		this.oRzipcode = oRzipcode;
	}
	public String getoRaddress() {
		return oRaddress;
	}
	public void setoRaddress(String oRaddress) {
		this.oRaddress = oRaddress;
	}
	public String getoRmemo() {
		return oRmemo;
	}
	public void setoRmemo(String oRmemo) {
		this.oRmemo = oRmemo;
	}
	public String getoRadminmemo() {
		return oRadminmemo;
	}
	public void setoRadminmemo(String oRadminmemo) {
		this.oRadminmemo = oRadminmemo;
	}
	public String getoRsettlelog() {
		return oRsettlelog;
	}
	public void setoRsettlelog(String oRsettlelog) {
		this.oRsettlelog = oRsettlelog;
	}
	public String getoRsettlekind() {
		return oRsettlekind;
	}
	public void setoRsettlekind(String oRsettlekind) {
		this.oRsettlekind = oRsettlekind;
	}
	public int getoRsettleprice() {
		return oRsettleprice;
	}
	public void setoRsettleprice(int oRsettleprice) {
		this.oRsettleprice = oRsettleprice;
	}
	public int getoRpmsettleprice() {
		return oRpmsettleprice;
	}
	public void setoRpmsettleprice(int oRpmsettleprice) {
		this.oRpmsettleprice = oRpmsettleprice;
	}
	public int getoRgoodsprice() {
		return oRgoodsprice;
	}
	public void setoRgoodsprice(int oRgoodsprice) {
		this.oRgoodsprice = oRgoodsprice;
	}
	public String getoRdelititle() {
		return oRdelititle;
	}
	public void setoRdelititle(String oRdelititle) {
		this.oRdelititle = oRdelititle;
	}
	public String getoRdelitype() {
		return oRdelitype;
	}
	public void setoRdelitype(String oRdelitype) {
		this.oRdelitype = oRdelitype;
	}
	public String getoRdelimsg() {
		return oRdelimsg;
	}
	public void setoRdelimsg(String oRdelimsg) {
		this.oRdelimsg = oRdelimsg;
	}
	public int getoRdelivery() {
		return oRdelivery;
	}
	public void setoRdelivery(int oRdelivery) {
		this.oRdelivery = oRdelivery;
	}
	public int getoRcoupon() {
		return oRcoupon;
	}
	public void setoRcoupon(int oRcoupon) {
		this.oRcoupon = oRcoupon;
	}
	public int getoRemoney() {
		return oRemoney;
	}
	public void setoRemoney(int oRemoney) {
		this.oRemoney = oRemoney;
	}
	public int getoRmemberdc() {
		return oRmemberdc;
	}
	public void setoRmemberdc(int oRmemberdc) {
		this.oRmemberdc = oRmemberdc;
	}
	public int getoRenuri() {
		return oRenuri;
	}
	public void setoRenuri(int oRenuri) {
		this.oRenuri = oRenuri;
	}
	public int getoRreserve() {
		return oRreserve;
	}
	public void setoRreserve(int oRreserve) {
		this.oRreserve = oRreserve;
	}
	public int getoRbankaccount() {
		return oRbankaccount;
	}
	public void setoRbankaccount(int oRbankaccount) {
		this.oRbankaccount = oRbankaccount;
	}
	public String getoRbankSender() {
		return oRbankSender;
	}
	public void setoRbankSender(String oRbankSender) {
		this.oRbankSender = oRbankSender;
	}
	public int getoRdeliveryno() {
		return oRdeliveryno;
	}
	public void setoRdeliveryno(int oRdeliveryno) {
		this.oRdeliveryno = oRdeliveryno;
	}
	public String getoRdeliverycode() {
		return oRdeliverycode;
	}
	public void setoRdeliverycode(String oRdeliverycode) {
		this.oRdeliverycode = oRdeliverycode;
	}
	public int getoRstep() {
		return oRstep;
	}
	public void setoRstep(int oRstep) {
		this.oRstep = oRstep;
	}
	public int getoRstep2() {
		return oRstep2;
	}
	public void setoRstep2(int oRstep2) {
		this.oRstep2 = oRstep2;
	}
	public String getoRinistid() {
		return oRinistid;
	}
	public void setoRinistid(String oRinistid) {
		this.oRinistid = oRinistid;
	}
	public int getoRmNo() {
		return oRmNo;
	}
	public void setoRmNo(int oRmNo) {
		this.oRmNo = oRmNo;
	}
	public int getmBmNo() {
		return mBmNo;
	}
	public void setmBmNo(int mBmNo) {
		this.mBmNo = mBmNo;
	}
	public String getoRip() {
		return oRip;
	}
	public void setoRip(String oRip) {
		this.oRip = oRip;
	}
	public String getoRreferer() {
		return oRreferer;
	}
	public void setoRreferer(String oRreferer) {
		this.oRreferer = oRreferer;
	}
	public Date getoRorddt() {
		return oRorddt;
	}
	public void setoRorddt(Date oRorddt) {
		this.oRorddt = oRorddt;
	}
	public Date getoRcdt() {
		return oRcdt;
	}
	public void setoRcdt(Date oRcdt) {
		this.oRcdt = oRcdt;
	}
	public Date getoRddt() {
		return oRddt;
	}
	public void setoRddt(Date oRddt) {
		this.oRddt = oRddt;
	}
	public String getoRcyn() {
		return oRcyn;
	}
	public void setoRcyn(String oRcyn) {
		this.oRcyn = oRcyn;
	}
	public String getoRdyn() {
		return oRdyn;
	}
	public void setoRdyn(String oRdyn) {
		this.oRdyn = oRdyn;
	}
	public String getoRconfirm() {
		return oRconfirm;
	}
	public void setoRconfirm(String oRconfirm) {
		this.oRconfirm = oRconfirm;
	}
	public Date getoRconfirmdt() {
		return oRconfirmdt;
	}
	public void setoRconfirmdt(Date oRconfirmdt) {
		this.oRconfirmdt = oRconfirmdt;
	}
	public String getoRescrowyn() {
		return oRescrowyn;
	}
	public void setoRescrowyn(String oRescrowyn) {
		this.oRescrowyn = oRescrowyn;
	}
	public int getoRescrowconfirm() {
		return oRescrowconfirm;
	}
	public void setoRescrowconfirm(int oRescrowconfirm) {
		this.oRescrowconfirm = oRescrowconfirm;
	}
	public String getoRescrowno() {
		return oRescrowno;
	}
	public void setoRescrowno(String oRescrowno) {
		this.oRescrowno = oRescrowno;
	}
	public String getoRcashreceipt() {
		return oRcashreceipt;
	}
	public void setoRcashreceipt(String oRcashreceipt) {
		this.oRcashreceipt = oRcashreceipt;
	}
	public String getoRvaccount() {
		return oRvaccount;
	}
	public void setoRvaccount(String oRvaccount) {
		this.oRvaccount = oRvaccount;
	}
	public String getoRoldordno() {
		return oRoldordno;
	}
	public void setoRoldordno(String oRoldordno) {
		this.oRoldordno = oRoldordno;
	}
	public int getoReggFee() {
		return oReggFee;
	}
	public void setoReggFee(int oReggFee) {
		this.oReggFee = oReggFee;
	}
	public String getoReggyn() {
		return oReggyn;
	}
	public void setoReggyn(String oReggyn) {
		this.oReggyn = oReggyn;
	}
	public String getoReggno() {
		return oReggno;
	}
	public void setoReggno(String oReggno) {
		this.oReggno = oReggno;
	}
	public String getoReggpginfo() {
		return oReggpginfo;
	}
	public void setoReggpginfo(String oReggpginfo) {
		this.oReggpginfo = oReggpginfo;
	}
	public int getoRcouponemoney() {
		return oRcouponemoney;
	}
	public void setoRcouponemoney(int oRcouponemoney) {
		this.oRcouponemoney = oRcouponemoney;
	}
	public String getoRcashbagcard() {
		return oRcashbagcard;
	}
	public void setoRcashbagcard(String oRcashbagcard) {
		this.oRcashbagcard = oRcashbagcard;
	}
	public int getoRcashbagpoint() {
		return oRcashbagpoint;
	}
	public void setoRcashbagpoint(int oRcashbagpoint) {
		this.oRcashbagpoint = oRcashbagpoint;
	}
	public String getoRcbyn() {
		return oRcbyn;
	}
	public void setoRcbyn(String oRcbyn) {
		this.oRcbyn = oRcbyn;
	}
	public String getoRemailrecceiver() {
		return oRemailrecceiver;
	}
	public void setoRemailrecceiver(String oRemailrecceiver) {
		this.oRemailrecceiver = oRemailrecceiver;
	}
	public String getmBmid() {
		return mBmid;
	}
	public void setmBmid(String mBmid) {
		this.mBmid = mBmid;
	}
	public int getmBklevel() {
		return mBklevel;
	}
	public void setmBklevel(int mBklevel) {
		this.mBklevel = mBklevel;
	}
	public String getmBname() {
		return mBname;
	}
	public void setmBname(String mBname) {
		this.mBname = mBname;
	}
	public String getmBnickname() {
		return mBnickname;
	}
	public void setmBnickname(String mBnickname) {
		this.mBnickname = mBnickname;
	}
	public String getmBpassword() {
		return mBpassword;
	}
	public void setmBpassword(String mBpassword) {
		this.mBpassword = mBpassword;
	}
	public int getmBstatus() {
		return mBstatus;
	}
	public void setmBstatus(int mBstatus) {
		this.mBstatus = mBstatus;
	}
	public String getmBresno1() {
		return mBresno1;
	}
	public void setmBresno1(String mBresno1) {
		this.mBresno1 = mBresno1;
	}
	public String getmBresno2() {
		return mBresno2;
	}
	public void setmBresno2(String mBresno2) {
		this.mBresno2 = mBresno2;
	}
	public String getmBsex() {
		return mBsex;
	}
	public void setmBsex(String mBsex) {
		this.mBsex = mBsex;
	}
	public String getmBbirthyear() {
		return mBbirthyear;
	}
	public void setmBbirthyear(String mBbirthyear) {
		this.mBbirthyear = mBbirthyear;
	}
	public String getmBbirth() {
		return mBbirth;
	}
	public void setmBbirth(String mBbirth) {
		this.mBbirth = mBbirth;
	}
	public String getmBcalendar() {
		return mBcalendar;
	}
	public void setmBcalendar(String mBcalendar) {
		this.mBcalendar = mBcalendar;
	}
	public String getmBemail() {
		return mBemail;
	}
	public void setmBemail(String mBemail) {
		this.mBemail = mBemail;
	}
	public String getmBzipcode() {
		return mBzipcode;
	}
	public void setmBzipcode(String mBzipcode) {
		this.mBzipcode = mBzipcode;
	}
	public String getmBaddress() {
		return mBaddress;
	}
	public void setmBaddress(String mBaddress) {
		this.mBaddress = mBaddress;
	}
	public String getmBaddresssub() {
		return mBaddresssub;
	}
	public void setmBaddresssub(String mBaddresssub) {
		this.mBaddresssub = mBaddresssub;
	}
	public String getmBphone() {
		return mBphone;
	}
	public void setmBphone(String mBphone) {
		this.mBphone = mBphone;
	}
	public String getmBmBmobile() {
		return mBmBmobile;
	}
	public void setmBmBmobile(String mBmBmobile) {
		this.mBmBmobile = mBmBmobile;
	}
	public String getmBfax() {
		return mBfax;
	}
	public void setmBfax(String mBfax) {
		this.mBfax = mBfax;
	}
	public String getmBcompany() {
		return mBcompany;
	}
	public void setmBcompany(String mBcompany) {
		this.mBcompany = mBcompany;
	}
	public String getmBservice() {
		return mBservice;
	}
	public void setmBservice(String mBservice) {
		this.mBservice = mBservice;
	}
	public String getmBitem() {
		return mBitem;
	}
	public void setmBitem(String mBitem) {
		this.mBitem = mBitem;
	}
	public String getmBbusino() {
		return mBbusino;
	}
	public void setmBbusino(String mBbusino) {
		this.mBbusino = mBbusino;
	}
	public int getmBemoney() {
		return mBemoney;
	}
	public void setmBemoney(int mBemoney) {
		this.mBemoney = mBemoney;
	}
	public int getmBpoint() {
		return mBpoint;
	}
	public void setmBpoint(int mBpoint) {
		this.mBpoint = mBpoint;
	}
	public String getmBmailling() {
		return mBmailling;
	}
	public void setmBmailling(String mBmailling) {
		this.mBmailling = mBmailling;
	}
	public String getmBsms() {
		return mBsms;
	}
	public void setmBsms(String mBsms) {
		this.mBsms = mBsms;
	}
	public String getmBmarriyn() {
		return mBmarriyn;
	}
	public void setmBmarriyn(String mBmarriyn) {
		this.mBmarriyn = mBmarriyn;
	}
	public String getmBmarridate() {
		return mBmarridate;
	}
	public void setmBmarridate(String mBmarridate) {
		this.mBmarridate = mBmarridate;
	}
	public String getmBjob() {
		return mBjob;
	}
	public void setmBjob(String mBjob) {
		this.mBjob = mBjob;
	}
	public String getmBinterest() {
		return mBinterest;
	}
	public void setmBinterest(String mBinterest) {
		this.mBinterest = mBinterest;
	}
	public Date getmBregdt() {
		return mBregdt;
	}
	public void setmBregdt(Date mBregdt) {
		this.mBregdt = mBregdt;
	}
	public Date getmBlastlogin() {
		return mBlastlogin;
	}
	public void setmBlastlogin(Date mBlastlogin) {
		this.mBlastlogin = mBlastlogin;
	}
	public Date getmBlastloginip() {
		return mBlastloginip;
	}
	public void setmBlastloginip(Date mBlastloginip) {
		this.mBlastloginip = mBlastloginip;
	}
	public Date getmBlastsale() {
		return mBlastsale;
	}
	public void setmBlastsale(Date mBlastsale) {
		this.mBlastsale = mBlastsale;
	}
	public int getmBcntlogin() {
		return mBcntlogin;
	}
	public void setmBcntlogin(int mBcntlogin) {
		this.mBcntlogin = mBcntlogin;
	}
	public int getmBcntsale() {
		return mBcntsale;
	}
	public void setmBcntsale(int mBcntsale) {
		this.mBcntsale = mBcntsale;
	}
	public int getmBsumsale() {
		return mBsumsale;
	}
	public void setmBsumsale(int mBsumsale) {
		this.mBsumsale = mBsumsale;
	}
	public String getmBmemo() {
		return mBmemo;
	}
	public void setmBmemo(String mBmemo) {
		this.mBmemo = mBmemo;
	}
	public String getmBrecommid() {
		return mBrecommid;
	}
	public void setmBrecommid(String mBrecommid) {
		this.mBrecommid = mBrecommid;
	}
	public String getmBex1() {
		return mBex1;
	}
	public void setmBex1(String mBex1) {
		this.mBex1 = mBex1;
	}
	public String getmBex2() {
		return mBex2;
	}
	public void setmBex2(String mBex2) {
		this.mBex2 = mBex2;
	}
	public String getmBex3() {
		return mBex3;
	}
	public void setmBex3(String mBex3) {
		this.mBex3 = mBex3;
	}
	public String getmBex4() {
		return mBex4;
	}
	public void setmBex4(String mBex4) {
		this.mBex4 = mBex4;
	}
	public String getmBex5() {
		return mBex5;
	}
	public void setmBex5(String mBex5) {
		this.mBex5 = mBex5;
	}
	public String getmBex6() {
		return mBex6;
	}
	public void setmBex6(String mBex6) {
		this.mBex6 = mBex6;
	}
	public String getmBLPINFO() {
		return mBLPINFO;
	}
	public void setmBLPINFO(String mBLPINFO) {
		this.mBLPINFO = mBLPINFO;
	}
	public String getmBprofile() {
		return mBprofile;
	}
	public void setmBprofile(String mBprofile) {
		this.mBprofile = mBprofile;
	}
	public int getgLsno() {
		return gLsno;
	}
	public void setgLsno(int gLsno) {
		this.gLsno = gLsno;
	}
	public int getgLgoodsno() {
		return gLgoodsno;
	}
	public void setgLgoodsno(int gLgoodsno) {
		this.gLgoodsno = gLgoodsno;
	}
	public String getgLcategory() {
		return gLcategory;
	}
	public void setgLcategory(String gLcategory) {
		this.gLcategory = gLcategory;
	}
	public int getgLsort() {
		return gLsort;
	}
	public void setgLsort(int gLsort) {
		this.gLsort = gLsort;
	}
	public int getgLhidden() {
		return gLhidden;
	}
	public void setgLhidden(int gLhidden) {
		this.gLhidden = gLhidden;
	}
	
	public String getSellerNm() {
		return sellerNm;
	}
	public void setSellerNm(String sellerNm) {
		this.sellerNm = sellerNm;
	}
	public String getSsSellerCd() {
		return ssSellerCd;
	}
	public void setSsSellerCd(String ssSellerCd) {
		this.ssSellerCd = ssSellerCd;
	}
	@Override
	public String toString() {
		return "OrdercancelOrderitemMember [ccnt=" + ccnt + ", oiocObject="
				+ oiocObject + ", oCsno=" + oCsno + ", oCordno=" + oCordno
				+ ", oCcode=" + oCcode + ", oCmemo=" + oCmemo + ", oCname="
				+ oCname + ", oCregdt=" + oCregdt + ", oCrprice=" + oCrprice
				+ ", oCrfee=" + oCrfee + ", oCremoney=" + oCremoney
				+ ", oCccdt=" + oCccdt + ", oCbankcode=" + oCbankcode
				+ ", oCbankaccount=" + oCbankaccount + ", oCbankuser="
				+ oCbankuser + ", oIsno=" + oIsno + ", oIordno=" + oIordno
				+ ", oIgoodsno=" + oIgoodsno + ", oIgoodsnm=" + oIgoodsnm
				+ ", oImaker=" + oImaker + ", oIbrandnm=" + oIbrandnm
				+ ", oIopt1=" + oIopt1 + ", oIopt2=" + oIopt2 + ", oIaddopt="
				+ oIaddopt + ", oIprice=" + oIprice + ", oIsupply=" + oIsupply
				+ ", oIreserve=" + oIreserve + ", oIcoupon=" + oIcoupon
				+ ", oImemberdc=" + oImemberdc + ", oIea=" + oIea + ", oIdvno="
				+ oIdvno + ", oIdvcode=" + oIdvcode + ", oIistep=" + oIistep
				+ ", oIstockyn=" + oIstockyn + ", oIcyn=" + oIcyn + ", oIdyn="
				+ oIdyn + ", oIcancel=" + oIcancel + ", oItax=" + oItax
				+ ", oIoptno=" + oIoptno 
				+ ", oIcouponemoney=" + oIcouponemoney
				+ ", oRordno=" + oRordno + ", oRnameorder=" + oRnameorder
				+ ", oRemail=" + oRemail + ", oRphoneorder=" + oRphoneorder
				+ ", oRmobileorder=" + oRmobileorder + ", oRnamereceiver="
				+ oRnamereceiver + ", oRphonereceiver=" + oRphonereceiver
				+ ", oRmobilereceiver=" + oRmobilereceiver + ", oRzipcode="
				+ oRzipcode + ", oRaddress=" + oRaddress + ", oRmemo=" + oRmemo
				+ ", oRadminmemo=" + oRadminmemo + ", oRsettlelog="
				+ oRsettlelog + ", oRsettlekind=" + oRsettlekind
				+ ", oRsettleprice=" + oRsettleprice + ", oRpmsettleprice="
				+ oRpmsettleprice + ", oRgoodsprice=" + oRgoodsprice
				+ ", oRdelititle=" + oRdelititle + ", oRdelitype=" + oRdelitype
				+ ", oRdelimsg=" + oRdelimsg + ", oRdelivery=" + oRdelivery
				+ ", oRcoupon=" + oRcoupon + ", oRemoney=" + oRemoney
				+ ", oRmemberdc=" + oRmemberdc + ", oRenuri=" + oRenuri
				+ ", oRreserve=" + oRreserve + ", oRbankaccount="
				+ oRbankaccount + ", oRbankSender=" + oRbankSender
				+ ", oRdeliveryno=" + oRdeliveryno + ", oRdeliverycode="
				+ oRdeliverycode + ", oRstep=" + oRstep + ", oRstep2="
				+ oRstep2 + ", oRinistid=" + oRinistid + ", oRmNo=" + oRmNo
				+ ", oRip=" + oRip + ", oRreferer=" + oRreferer + ", oRorddt="
				+ oRorddt + ", oRcdt=" + oRcdt + ", oRddt=" + oRddt
				+ ", oRcyn=" + oRcyn + ", oRdyn=" + oRdyn + ", oRconfirm="
				+ oRconfirm + ", oRconfirmdt=" + oRconfirmdt + ", oRescrowyn="
				+ oRescrowyn + ", oRescrowconfirm=" + oRescrowconfirm
				+ ", oRescrowno=" + oRescrowno + ", oRcashreceipt="
				+ oRcashreceipt + ", oRvaccount=" + oRvaccount
				+ ", oRoldordno=" + oRoldordno + ", oReggFee="
				+ oReggFee + ", oReggyn=" + oReggyn + ", oReggno=" + oReggno
				+ ", oReggpginfo=" + oReggpginfo + ", oRcouponemoney="
				+ oRcouponemoney + ", oRcashbagcard=" + oRcashbagcard
				+ ", oRcashbagpoint=" + oRcashbagpoint + ", oRcbyn=" + oRcbyn
				+ ", oRemailrecceiver=" + oRemailrecceiver + ", mBmNo=" + mBmNo
				+ ", mBmid=" + mBmid + ", mBklevel=" + mBklevel + ", mBname="
				+ mBname + ", mBnickname=" + mBnickname + ", mBpassword="
				+ mBpassword + ", mBstatus=" + mBstatus + ", mBresno1="
				+ mBresno1 + ", mBresno2=" + mBresno2 + ", mBsex=" + mBsex
				+ ", mBbirthyear=" + mBbirthyear + ", mBbirth=" + mBbirth
				+ ", mBcalendar=" + mBcalendar + ", mBemail=" + mBemail
				+ ", mBzipcode=" + mBzipcode + ", mBaddress=" + mBaddress
				+ ", mBaddresssub=" + mBaddresssub + ", mBphone=" + mBphone
				+ ", mBmBmobile=" + mBmBmobile + ", mBfax=" + mBfax
				+ ", mBcompany=" + mBcompany + ", mBservice=" + mBservice
				+ ", mBitem=" + mBitem + ", mBbusino=" + mBbusino
				+ ", mBemoney=" + mBemoney + ", mBpoint=" + mBpoint
				+ ", mBmailling=" + mBmailling + ", mBsms=" + mBsms
				+ ", mBmarriyn=" + mBmarriyn + ", mBmarridate=" + mBmarridate
				+ ", mBjob=" + mBjob + ", mBinterest=" + mBinterest
				+ ", mBregdt=" + mBregdt + ", mBlastlogin=" + mBlastlogin
				+ ", mBlastloginip=" + mBlastloginip + ", mBlastsale="
				+ mBlastsale + ", mBcntlogin=" + mBcntlogin + ", mBcntsale="
				+ mBcntsale + ", mBsumsale=" + mBsumsale + ", mBmemo=" + mBmemo
				+ ", mBrecommid=" + mBrecommid + ", mBex1=" + mBex1
				+ ", mBex2=" + mBex2 + ", mBex3=" + mBex3 + ", mBex4=" + mBex4
				+ ", mBex5=" + mBex5 + ", mBex6=" + mBex6 + ", mBLPINFO="
				+ mBLPINFO + ", mBprofile=" + mBprofile + ", gLsno=" + gLsno
				+ ", gLgoodsno=" + gLgoodsno + ", gLcategory=" + gLcategory
				+ ", gLsort=" + gLsort + ", gLhidden=" + gLhidden
				+ ", sellerNm=" + sellerNm + ", ssSellerCd=" + ssSellerCd + "]";
	}	

}
