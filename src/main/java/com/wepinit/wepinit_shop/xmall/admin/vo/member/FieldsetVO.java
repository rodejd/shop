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
package com.wepinit.wepinit_shop.xmall.admin.vo.member;

import com.wepinit.wepinit_shop.xmall.common.vo.GdConfSet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FieldsetVO {
	private String status;
	private String rejoin;
	private String unableid;
	
	private String emoney;
	private String grp;
	private String recommEmoney;
	private String recommAddEmoney;
	private String[] useField;
	private String[] useFieldName;
	private String[] reqField;
	private String[] reqFieldName;
	
	private String mode;
	private List<GdConfSet> useFieldList;
	private List<GdConfSet> reqFieldList;
	private HashMap<Integer, String> garr;

	
	
	

	public String[] getReqFieldName() {
		return reqFieldName;
	}

	public void setReqFieldName(String[] reqFieldName) {
		this.reqFieldName = reqFieldName;
	}

	public HashMap<Integer, String> getGarr() {
		return garr;
	}

	public void setGarr(HashMap<Integer, String> garr) {
		this.garr = garr;
	}

	public List<GdConfSet> getUseFieldList() {
		return useFieldList;
	}

	public void setUseFieldList(List<GdConfSet> useFieldList) {
		this.useFieldList = useFieldList;
	}

	public List<GdConfSet> getReqFieldList() {
		return reqFieldList;
	}

	public void setReqFieldList(List<GdConfSet> reqFieldList) {
		this.reqFieldList = reqFieldList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRejoin() {
		return rejoin;
	}

	public void setRejoin(String rejoin) {
		this.rejoin = rejoin;
	}

	public String getUnableid() {
		return unableid;
	}

	public void setUnableid(String unableid) {
		this.unableid = unableid;
	}

	public String getEmoney() {
		return emoney;
	}

	public void setEmoney(String emoney) {
		this.emoney = emoney;
	}

	public String getGrp() {
		return grp;
	}

	public void setGrp(String grp) {
		this.grp = grp;
	}

	public String getRecommEmoney() {
		return recommEmoney;
	}

	public void setRecommEmoney(String recommEmoney) {
		this.recommEmoney = recommEmoney;
	}

	public String getRecommAddEmoney() {
		return recommAddEmoney;
	}

	public void setRecommAddEmoney(String recommAddEmoney) {
		this.recommAddEmoney = recommAddEmoney;
	}

	

	public String[] getReqField() {
		return reqField;
	}

	public void setReqField(String[] reqField) {
		this.reqField = reqField;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public String[] getUseField() {
		return useField;
	}

	public void setUseField(String[] useField) {
		this.useField = useField;
	}

	public String[] getUseFieldName() {
		return useFieldName;
	}

	public void setUseFieldName(String[] useFieldName) {
		this.useFieldName = useFieldName;
	}

	@Override
	public String toString() {
		return "FieldsetVO [status=" + status + ", rejoin=" + rejoin
				+ ", unableid=" + unableid + ", emoney=" + emoney + ", grp="
				+ grp + ", recommEmoney=" + recommEmoney + ", recommAddEmoney="
				+ recommAddEmoney + ", useField=" + Arrays.toString(useField)
				+ ", useFieldName=" + Arrays.toString(useFieldName)
				+ ", reqField=" + Arrays.toString(reqField) + ", reqFieldName="
				+ Arrays.toString(reqFieldName) + ", mode=" + mode
				+ ", useFieldList=" + useFieldList + ", reqFieldList="
				+ reqFieldList + ", garr=" + garr + "]";
	}

	

	
	
}
