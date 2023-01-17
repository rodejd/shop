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
package com.wepinit.wepinit_shop.xmall.sample.vo;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

import java.util.List;


public class SampleFM extends PageMaker{
	
	//##############################################
	//# 화면에서 사용할 파라미터 IN / OUT  정의 VO
	//# INPUT(jsp -> controller) 파라미터
	//# OUTPUT(controller -> jsp) 파라미터
	//##############################################
	
	private String sample_no = "";	// request param
	private String title = "";			// request param
	private String description = "";	// request param

	private List<SampleViewVO> sampleList = null;	// 화면 출력용
	private SampleViewVO sampleObj = null;			// 화면 출력용
	
	public String getSample_no() {
		return sample_no;
	}
	public void setSample_no(String sample_no) {
		this.sample_no = sample_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<SampleViewVO> getSampleList() {
		return sampleList;
	}
	public void setSampleList(List<SampleViewVO> sampleList) {
		this.sampleList = sampleList;
	}
	public SampleViewVO getSampleObj() {
		return sampleObj;
	}
	public void setSampleObj(SampleViewVO sampleObj) {
		this.sampleObj = sampleObj;
	}
	
	@Override
	public String toString() {
		return "SampleVO [sample_no=" + sample_no + ", title=" + title
				+ ", description=" + description + ", sampleList=" + sampleList
				+ ", sampleObj=" + sampleObj + "]";
	}
}
