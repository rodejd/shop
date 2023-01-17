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
package com.wepinit.wepinit_shop.xmall.front.vo.order;

import java.util.HashMap;
import java.util.Map;

/**총 배송비의 합계를 저장하는 VO</br>
 * 
 * prepaidDelivery	: 선불 배송비</br>
 * postpaidDelivery : 후불 배송비
 * */
public class TotalDeliveryInfoVO {
	// 한 주문당 착불배송비 총합
	private String postpaidDelivery = null;
	// 한 주문당 선불배송비 총합
	private String prepaidDelivery = null;

	// 상품별 선불배송비 총 합산용 Map
	// input data : ("sellerCd", 상품별배송비 적용정책에 따른 선불배송비 합산)
	Map<String, Integer> totalPrepaymentMap = new HashMap<String, Integer>();
	
	// 상품별 착불배송비 총 합산용 Map
	// input data : ("sellerCd", 착불배송비 합산)
	Map<String, Integer> postPaymentMap = new HashMap<String, Integer>();
	
	public String getPostpaidDelivery() {
		return postpaidDelivery;
	}
	public void setPostpaidDelivery(String postpaidDelivery) {
		this.postpaidDelivery = postpaidDelivery;
	}
	public String getPrepaidDelivery() {
		return prepaidDelivery;
	}
	public void setPrepaidDelivery(String prepaidDelivery) {
		this.prepaidDelivery = prepaidDelivery;
	}
	public Map<String, Integer> getTotalPrepaymentMap() {
		return totalPrepaymentMap;
	}
	public void setTotalPrepaymentMap(Map<String, Integer> totalPrepaymentMap) {
		this.totalPrepaymentMap = totalPrepaymentMap;
	}
	public Map<String, Integer> getPostPaymentMap() {
		return postPaymentMap;
	}
	public void setPostPaymentMap(Map<String, Integer> postPaymentMap) {
		this.postPaymentMap = postPaymentMap;
	}
	@Override
	public String toString() {
		return "TotalDeliveryInfoVO [postpaidDelivery=" + postpaidDelivery
				+ ", prepaidDelivery=" + prepaidDelivery
				+ ", totalPrepaymentMap=" + totalPrepaymentMap
				+ ", postPaymentMap=" + postPaymentMap + "]";
	}
	
}
