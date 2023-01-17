package com.wepinit.wepinit_shop.xmall.common.vo.join;

import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.vo.GdOrder;

import java.util.Date;
/**join
 * gd_member
 * gd_order
 * */
public class PopupOrder2 extends GdOrder {
	private String mId;
	
	// 화면
	private String rSettlekind;
	private String oldordnoN2S;
	private String confirmdtStr;
	
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getrSettlekind() {
		return rSettlekind;
	}
	@Override
	public void setSettlekind(String settlekind) {
		super.setSettlekind(settlekind);
		this.rSettlekind = ShopLibFunction.r_settlekind(settlekind);
	}
	
	public void setOldordnoN2S(String oldordnoN2S) {
		this.oldordnoN2S = oldordnoN2S;
	}
	public String getOldordnoN2S() {
		return oldordnoN2S;
	}
	@Override
	public void setConfirmdt(Date confirmdt) {
		super.setConfirmdt(confirmdt);
		this.confirmdtStr = confirmdt.toString();
	}
	public String getConfirmdtStr() {
		return confirmdtStr;
	}
	@Override
	public String toString() {
		return super.toString() + " / PopupOrder2 [mId=" + mId + ", rSettlekind=" + rSettlekind
				+ ", oldordnoN2S=" + oldordnoN2S + ", confirmdtStr="
				+ confirmdtStr + "]";
	}	
}
