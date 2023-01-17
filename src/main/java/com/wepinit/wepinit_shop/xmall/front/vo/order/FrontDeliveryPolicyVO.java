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
/********************************************************************************
* <pre>
* 업무구분명			:	Community
* 세부업무구분명	: 	사용자 > Community
* 작성자 				: 	이병환
* 설명 				: 	사용자 Community VO
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170208		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.vo.order;

import com.wepinit.wepinit_shop.xmall.common.CommonVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdDeliveryPolicy;

import java.util.List;


public class FrontDeliveryPolicyVO extends CommonVO{
	
	private String zipcode;
	private int emoney;
	private int delipoli;
	private int coupon;
	private String dcPrice;
	
	private int no;
	private String r_delivery;
	private String r_free;
	private String r_deliType;
	private String r_default;
	private String r_defalt_msg;
	private String mode;
	
	private GdDeliveryPolicy gdpObj;
	private List<GdDeliveryPolicy> gdpList;
	
	
	
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public int getEmoney() {
		return emoney;
	}
	public void setEmoney(int emoney) {
		this.emoney = emoney;
	}
	public int getDelipoli() {
		return delipoli;
	}
	public void setDelipoli(int delipoli) {
		this.delipoli = delipoli;
	}
	public int getCoupon() {
		return coupon;
	}
	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}
	public String getDcPrice() {
		return dcPrice;
	}
	public void setDcPrice(String dcPrice) {
		this.dcPrice = dcPrice;
	}
	public GdDeliveryPolicy getGdpObj() {
		return gdpObj;
	}
	public void setGdpObj(GdDeliveryPolicy gdpObj) {
		this.gdpObj = gdpObj;
	}
	public List<GdDeliveryPolicy> getGdpList() {
		return gdpList;
	}
	public void setGdpList(List<GdDeliveryPolicy> gdpList) {
		this.gdpList = gdpList;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getR_delivery() {
		return r_delivery;
	}
	public void setR_delivery(String r_delivery) {
		this.r_delivery = r_delivery;
	}
	public String getR_free() {
		return r_free;
	}
	public void setR_free(String r_free) {
		this.r_free = r_free;
	}
	public String getR_deliType() {
		return r_deliType;
	}
	public void setR_deliType(String r_deliType) {
		this.r_deliType = r_deliType;
	}
	public String getR_default() {
		return r_default;
	}
	public void setR_default(String r_default) {
		this.r_default = r_default;
	}
	public String getR_defalt_msg() {
		return r_defalt_msg;
	}
	public void setR_defalt_msg(String r_defalt_msg) {
		this.r_defalt_msg = r_defalt_msg;
	}
	@Override
	public String toString() {
		return "FrontDeliveryPolicyVO [zipcode=" + zipcode + ", emoney="
				+ emoney + ", delipoli=" + delipoli + ", coupon=" + coupon
				+ ", dcPrice=" + dcPrice + ", no=" + no + ", r_delivery="
				+ r_delivery + ", r_free=" + r_free + ", r_deliType="
				+ r_deliType + ", r_default=" + r_default + ", r_defalt_msg="
				+ r_defalt_msg + ", mode=" + mode + ", gdpObj=" + gdpObj
				+ ", gdpList=" + gdpList + "]";
	}
}
