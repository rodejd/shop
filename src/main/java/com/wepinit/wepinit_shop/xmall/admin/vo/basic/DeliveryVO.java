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

import com.wepinit.wepinit_shop.xmall.common.vo.GdDeliveryPolicy;
import com.wepinit.wepinit_shop.xmall.common.vo.GdListDelivery;

import java.util.Arrays;
import java.util.List;

public class DeliveryVO {
	//input
	//gd_list_delivery
	private String deliveryno;
	private String deliveryno1;
	private String deliverycomp;
	private String deliveryurl;
	private String useyn;
	
	//gd_delivery_policy
	//배송정책 추가 데이터용
	private int[] no;
	private String[] rDelivery;
	private String[] rFree;
	private String[] rDeliType;
	private String[] rDefault;
	private String[] rDefaultMsg;
	
	//delivery.jsp
	private int result=0;
	private String deliverynm;
	private String free;
	private String deliveryType;
	private String default1;
	private String defaultMsg;
	private String freeDelivery;
	private String goodsDelivery;
	private String over;
	private String overZipcodeName;
	private String[] stOver;
	private String[] stZipcodeName;
	private String shippingFee;
	
	//택배사 전체리스트
	private int[] deliveryTmp;
	//이용택배사 리스트
	private String[] delivery;
	
	//모드
	private String mode;
	
	private List<GdListDelivery> deliveryList;
	private List<GdDeliveryPolicy> deliveryPolicyList;
	/*private Map<String, String> confSetMap;*/
	
	private String sweetTrackerAPIKey;
	
	
	public String getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(String shippingFee) {
		this.shippingFee = shippingFee;
	}

	public String getSweetTrackerAPIKey() {
		return sweetTrackerAPIKey;
	}
	
	public void setSweetTrackerAPIKey(String sweetTrackerAPIKey) {
		this.sweetTrackerAPIKey = sweetTrackerAPIKey;
	}
	
	public List<GdDeliveryPolicy> getDeliveryPolicyList() {
		return deliveryPolicyList;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public void setDeliveryPolicyList(List<GdDeliveryPolicy> deliveryPolicyList) {
		this.deliveryPolicyList = deliveryPolicyList;
	}

	

	public int[] getNo() {
		return no;
	}

	public void setNo(int[] no) {
		this.no = no;
	}

	public String[] getrDelivery() {
		return rDelivery;
	}

	public void setrDelivery(String[] rDelivery) {
		this.rDelivery = rDelivery;
	}

	public String[] getrFree() {
		return rFree;
	}

	public void setrFree(String[] rFree) {
		this.rFree = rFree;
	}

	public String[] getrDeliType() {
		return rDeliType;
	}

	public void setrDeliType(String[] rDeliType) {
		this.rDeliType = rDeliType;
	}

	public String[] getrDefault() {
		return rDefault;
	}

	public void setrDefault(String[] rDefault) {
		this.rDefault = rDefault;
	}

	public String[] getrDefaultMsg() {
		return rDefaultMsg;
	}

	public void setrDefaultMsg(String[] rDefaultMsg) {
		this.rDefaultMsg = rDefaultMsg;
	}

	public String getDeliveryno() {
		return deliveryno;
	}

	public void setDeliveryno(String  deliveryno) {
		this.deliveryno = deliveryno;
	}

	public String getDeliverycomp() {
		return deliverycomp;
	}

	public void setDeliverycomp(String deliverycomp) {
		this.deliverycomp = deliverycomp;
	}

	public String getDeliveryurl() {
		return deliveryurl;
	}

	public void setDeliveryurl(String deliveryurl) {
		this.deliveryurl = deliveryurl;
	}



	public String getUseyn() {
		return useyn;
	}

	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}

	public List<GdListDelivery> getDeliveryList() {
		return deliveryList;
	}

	public void setDeliveryList(List<GdListDelivery> deliveryList) {
		this.deliveryList = deliveryList;
	}

	

	public String getDeliverynm() {
		return deliverynm;
	}

	public void setDeliverynm(String deliverynm) {
		this.deliverynm = deliverynm;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getDefault1() {
		return default1;
	}

	public void setDefault1(String default1) {
		this.default1 = default1;
	}

	public String getDefaultMsg() {
		return defaultMsg;
	}

	public void setDefaultMsg(String defaultMsg) {
		this.defaultMsg = defaultMsg;
	}

	public String getFreeDelivery() {
		return freeDelivery;
	}

	public void setFreeDelivery(String freeDelivery) {
		this.freeDelivery = freeDelivery;
	}

	public String getGoodsDelivery() {
		return goodsDelivery;
	}

	public void setGoodsDelivery(String goodsDelivery) {
		this.goodsDelivery = goodsDelivery;
	}

	public String getOver() {
		return over;
	}

	public void setOver(String over) {
		this.over = over;
	}

	public String getOverZipcodeName() {
		return overZipcodeName;
	}

	public void setOverZipcodeName(String overZipcodeName) {
		this.overZipcodeName = overZipcodeName;
	}

	public String[] getStOver() {
		return stOver;
	}

	public void setStOver(String[] stOver) {
		this.stOver = stOver;
	}

	public String[] getStZipcodeName() {
		return stZipcodeName;
	}

	public void setStZipcodeName(String[] stZipcodeName) {
		this.stZipcodeName = stZipcodeName;
	}



	public int[] getDeliveryTmp() {
		return deliveryTmp;
	}

	public void setDeliveryTmp(int[] deliveryTmp) {
		this.deliveryTmp = deliveryTmp;
	}

	public String[] getDelivery() {
		return delivery;
	}

	public void setDelivery(String[] delivery) {
		this.delivery = delivery;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getDeliveryno1() {
		return deliveryno1;
	}

	public void setDeliveryno1(String deliveryno1) {
		this.deliveryno1 = deliveryno1;
	}	
	
	@Override
	public String toString() {
		return "DeliveryVO [deliveryno=" + deliveryno + ", deliverycomp="
				+ deliverycomp + ", deliveryurl=" + deliveryurl + ", useyn="
				+ useyn + ", no=" + Arrays.toString(no) + ", rDelivery="
				+ Arrays.toString(rDelivery) + ", rFree="
				+ Arrays.toString(rFree) + ", rDeliType="
				+ Arrays.toString(rDeliType) + ", rDefault="
				+ Arrays.toString(rDefault) + ", rDefaultMsg="
				+ Arrays.toString(rDefaultMsg) + ", deliverynm=" + deliverynm
				+ ", free=" + free + ", deliveryType=" + deliveryType
				+ ", default1=" + default1 + ", defaultMsg=" + defaultMsg
				+ ", freeDelivery=" + freeDelivery + ", goodsDelivery="
				+ goodsDelivery + ", over=" + over + ", overZipcodeName="
				+ overZipcodeName + ", stOver=" + Arrays.toString(stOver)
				+ ", stZipcodeName=" + Arrays.toString(stZipcodeName)
				+ ", deliveryTmp=" + Arrays.toString(deliveryTmp)
				+ ", delivery=" + Arrays.toString(delivery) + ", mode=" + mode
				+ ", deliveryList=" + deliveryList + ", shippingFee=" + shippingFee  
				+ ", deliveryPolicyList=" + deliveryPolicyList + "]";
	}
	
}
