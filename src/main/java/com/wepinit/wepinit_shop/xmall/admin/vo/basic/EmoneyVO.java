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


public class EmoneyVO {
	
	private String useyn;
	private String limit;
	private String hold;
	private String min;
	private String[] max = new String[2];
	private String delivery;
	private String cut;
	private String[] goodsEmoney = new String[2];
	private String chkGoodsEmoney;
	
	private String mode;
	
	private String kMax;
	private String emoneyDelivery;
	
	public String getUseyn() {
		return useyn;
	}
	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getHold() {
		return hold;
	}
	public void setHold(String hold) {
		this.hold = hold;
	}
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	public String getMax2(int index){
		if(max !=null && max.length !=0){
			return this.max[index];
		}else{
			return "";
		}
	}
	public String[] getMax() {
		return max;
	}
	public void setMax2(String max , int index){
		if(max !=null)
			this.max[index] = max;
		else
			this.max[index] = "";
	}
	public void setMax(String[] max) {
		this.max = max;
	}

	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public String getCut() {
		return cut;
	}
	public void setCut(String cut) {
		this.cut = cut;
	}
	public String[] getGoodsEmoney() {
		return goodsEmoney;
	}
	public String getGoodsEmoney2(int index){
		if(goodsEmoney !=null && goodsEmoney.length !=0){
			return goodsEmoney[index];
		}else{
			return "";
		}
	}
	public void setGoodsEmoney2(String goodsEmoney,int index){
		if(goodsEmoney !=null ){
			this.goodsEmoney[index] = goodsEmoney;
		}else{
			this.goodsEmoney[index] = "";
		}
	}
	public void setGoodsEmoney(String[] goodsEmoney) {
		this.goodsEmoney = goodsEmoney;
	}
	
	public String getChkGoodsEmoney() {
		return chkGoodsEmoney;
	}
	public void setChkGoodsEmoney(String chkGoodsEmoney) {
		this.chkGoodsEmoney = chkGoodsEmoney;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getkMax() {
		return kMax;
	}
	public void setkMax(String kMax) {
		this.kMax = kMax;
	}
	public String getEmoneyDelivery() {
		return emoneyDelivery;
	}
	public void setEmoneyDelivery(String emoneyDelivery) {
		this.emoneyDelivery = emoneyDelivery;
	}
	
	@Override
	public String toString() {
		return "EmoneyVO [useyn=" + useyn + ", limit=" + limit + ", hold="
				+ hold + ", min=" + min + ", max=" + max + ", delivery="
				+ delivery + ", cut=" + cut + ", goodsEmoney=" + goodsEmoney
				+ ", chkGoodsEmoney=" + chkGoodsEmoney + ", mode=" + mode
				+ ", kMax=" + kMax + ", emoneyDelivery=" + emoneyDelivery + "]";
	}
}
