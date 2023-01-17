package com.wepinit.wepinit_shop.xmall.common.vo.join;

import com.wepinit.wepinit_shop.xmall.common.vo.GdCouponOrder;
/*join
 * gd_coupon_apply
 * gd_coupon_order
 * */
public class PopupOrder3 extends GdCouponOrder {
	private int couponcd;
	private String goodsnm;
	private String goodsnmKR;
	private String goodsnmCN;
	
	public int getCouponcd() {
		return couponcd;
	}
	public void setCouponcd(int couponcd) {
		this.couponcd = couponcd;
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
}
