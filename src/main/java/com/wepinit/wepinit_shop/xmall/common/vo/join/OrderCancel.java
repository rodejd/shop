package com.wepinit.wepinit_shop.xmall.common.vo.join;

import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.vo.GdOrder;

import java.util.Date;

/*join
 * gd_order_cancel
 * gd_order_item
 * gd_order
 * gd_member
 * */
public class OrderCancel extends GdOrder {
	private int sno;			// 일련번호
	private int goodsno;		// 상품번호
	private String goodsnm;		// 상품명(영문)
	private String goodsnmKR;	// 상품명(국문)
	private String goodsnmCN;	// 상품명(중문)
	private String brandnm;		// 브랜드명
	private String opt1;		// 옵션1
	private String opt2;		// 옵션2
	private String addopt;		// 추가옵션
	private int price;			// 판매가격
	private int supply;			// 공급가격
/*	private int reserve;		// 할인가격
	private int coupon;			// 쿠폰가격
	private int ordno;			// 주문번호
	private int memberdc;		// 회원DC*/
	private int ea;				// 수량
	private int dvno;			// 택배사번호
	private String dvcode;		// 송장코드
	private int istep;			// 단계
	private String stockyn;		// 재고여부
/*	private String cyn;			// 결제여부
	private String dyn;			// 주문단계*/
	private int cancel;			// 취소
	private int tax;			// 부가세
	private String optno;		// 옵션NO
/*	private int couponEmoney;	// 쿠폰_적립금*/
	private Date canceldt;
	private int code;
	private int cnt;
	private int sea;
	private int pay;
	private int itemsno;
	private String mId;
	private String codeItem;	// 취소사유
	
	private String bgColor;
	private String disabled;
	private String stepMsg;
	
	private String sellerNm;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
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
	public String getBrandnm() {
		return brandnm;
	}
	public void setBrandnm(String brandnm) {
		this.brandnm = brandnm;
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
	public String getAddopt() {
		return addopt;
	}
	public void setAddopt(String addopt) {
		this.addopt = addopt;
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
	public int getEa() {
		return ea;
	}
	public void setEa(int ea) {
		this.ea = ea;
	}
	public int getDvno() {
		return dvno;
	}
	public void setDvno(int dvno) {
		this.dvno = dvno;
	}
	public String getDvcode() {
		return dvcode;
	}
	public void setDvcode(String dvcode) {
		this.dvcode = dvcode;
	}
	public int getIstep() {
		return istep;
	}
	public void setIstep(int istep) {
		this.istep = istep;
		
		if(this.istep == 44) {
			this.bgColor = "#E6FBFE";
			this.disabled = "disabled";
		} else {
			this.bgColor = "#ffffff";
			this.disabled = "";
		}
	}
	public String getStockyn() {
		return stockyn;
	}
	public void setStockyn(String stockyn) {
		this.stockyn = stockyn;
	}
	public int getCancel() {
		return cancel;
	}
	public void setCancel(int cancel) {
		this.cancel = cancel;
	}
	public int getTax() {
		return tax;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}
	public String getOptno() {
		return optno;
	}
	public void setOptno(String optno) {
		this.optno = optno;
	}
	public Date getCanceldt() {
		return canceldt;
	}
	public void setCanceldt(Date canceldt) {
		this.canceldt = canceldt;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getSea() {
		return sea;
	}
	public void setSea(int sea) {
		this.sea = sea;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	public int getItemsno() {
		return itemsno;
	}
	public void setItemsno(int itemsno) {
		this.itemsno = itemsno;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmId() {
		return mId;
	}
	public String getBgColor() {
		return bgColor;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setStepMsg(String stepMsg) {
		this.stepMsg = stepMsg;
	}
	public String getStepMsg() {
		return stepMsg;
	}
	public void setCodeItem(String codeItem) {
		this.codeItem = codeItem;
	}
	public String getCodeItem() {
		return codeItem;
	}
	@Override
	public void setSettlekind(String settlekind) {
		super.setSettlekind(ShopLibFunction.r_settlekind(settlekind));
	}
	public String getSellerNm() {
		return sellerNm;
	}
	public void setSellerNm(String sellerNm) {
		this.sellerNm = sellerNm;
	}
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	@Override
	public String toString() {
		return "OrderCancel [sno=" + sno + ", goodsno=" + goodsno
				+ ", goodsnm=" + goodsnm + ", brandnm="
				+ brandnm + ", opt1=" + opt1 + ", opt2=" + opt2 + ", addopt="
				+ addopt + ", price=" + price + ", supply=" + supply + ", ea="
				+ ea + ", dvno=" + dvno + ", dvcode=" + dvcode + ", istep="
				+ istep + ", stockyn=" + stockyn + ", cancel=" + cancel
				+ ", tax=" + tax + ", optno=" + optno
				+ ", canceldt=" + canceldt + ", code=" + code
				+ ", cnt=" + cnt + ", sea=" + sea + ", pay=" + pay
				+ ", itemsno=" + itemsno + ", mId=" + mId + ", codeItem="
				+ codeItem + ", bgColor=" + bgColor + ", disabled=" + disabled
				+ ", stepMsg=" + stepMsg + ", sellerNm=" + sellerNm + "]";
	}
		
	
}
