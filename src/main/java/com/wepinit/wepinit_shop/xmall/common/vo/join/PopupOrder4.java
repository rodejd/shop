package com.wepinit.wepinit_shop.xmall.common.vo.join;

import com.wepinit.wepinit_shop.xmall.common.vo.GdOrderCancel;
import com.wepinit.wepinit_shop.xmall.common.vo.GdOrderItem;

import java.util.List;

/*join
 * gd_order_item
 * gd_order_cancel
 * 
 * */
public class PopupOrder4 extends GdOrderCancel {
	private int cancel;
	private String bankPro;
	
	// 화면
	private List<GdOrderItem> orderItemList;
	private int orderItemListSize;
	
	public int getCancel() {
		return cancel;
	}
	public void setCancel(int cancel) {
		this.cancel = cancel;
	}
	public String getBankPro() {
		return bankPro;
	}
	public void setBankPro(String bankPro) {
		this.bankPro = bankPro;
	}
	public void setOrderItemList(List<GdOrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public List<GdOrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemListSize(int orderItemListSize) {
		this.orderItemListSize = orderItemListSize;
	}
	public int getOrderItemListSize() {
		return orderItemListSize;
	}
	
}
