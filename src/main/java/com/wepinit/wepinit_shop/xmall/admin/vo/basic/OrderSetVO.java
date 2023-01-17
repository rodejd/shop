/*******************************************************************
                     PMGroupKorea Co., Ltd.
Copyright PMGroupKorea Co., Ltd. 2005. All rights reserved.
No part of this work covered by the copyright hereon may be reproduced,
stored in a retrieval system, in any form or by any means, electronic,
mechanical, photocopying, recording or otherwise, without the prior
written permission of PMGroupKorea Co., Ltd.
SOLUTION    :   XMaLL4 for Spring
FILE_NAME   :
DATE        :   2018.1.29
AUTHOR      :   PMGK S/W Engineer   <contact@gmail.com>
DESCRIPTION :
FUNCTIONS   :
HISTORY     :
REMARKS     :
******************************************************************/
package com.wepinit.wepinit_shop.xmall.admin.vo.basic;


public class OrderSetVO {

	//input
	private String stepStock;
	private String deliveryBasis;
	private String autoCancel;
	private String autoCancelFail;
	private String orderPeriod;
	private String orderPageNum;
	
	
	

	public String getStepStock() {
		return stepStock;
	}

	public void setStepStock(String stepStock) {
		this.stepStock = stepStock;
	}

	public String getDeliveryBasis() {
		return deliveryBasis;
	}

	public void setDeliveryBasis(String deliveryBasis) {
		this.deliveryBasis = deliveryBasis;
	}

	public String getAutoCancel() {
		return autoCancel;
	}

	public void setAutoCancel(String autoCancel) {
		this.autoCancel = autoCancel;
	}

	public String getAutoCancelFail() {
		return autoCancelFail;
	}

	public void setAutoCancelFail(String autoCancelFail) {
		this.autoCancelFail = autoCancelFail;
	}

	public String getOrderPeriod() {
		return orderPeriod;
	}

	public void setOrderPeriod(String orderPeriod) {
		this.orderPeriod = orderPeriod;
	}

	public String getOrderPageNum() {
		return orderPageNum;
	}

	public void setOrderPageNum(String orderPageNum) {
		this.orderPageNum = orderPageNum;
	}

	@Override
	public String toString() {
		return "OrderSetVO [stepStock=" + stepStock + ", deliveryBasis="
				+ deliveryBasis + ", autoCancel=" + autoCancel
				+ ", autoCancelFail=" + autoCancelFail + ", orderPeriod="
				+ orderPeriod + ", orderPageNum=" + orderPageNum + "]";
	}

	
	
	
	
	
}
