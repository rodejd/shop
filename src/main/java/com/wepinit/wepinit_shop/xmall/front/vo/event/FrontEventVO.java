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
package com.wepinit.wepinit_shop.xmall.front.vo.event;

import com.wepinit.wepinit_shop.xmall.common.CommonVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdEvent;
import com.wepinit.wepinit_shop.xmall.common.vo.GdEventMemo;

import java.util.List;


public class FrontEventVO extends CommonVO{
	private int sno;
	private int eno=0;
	private String mode;
	private String body;
	private String body1;
	
	private GdEvent frontEventObj = null;
	private List<GdEvent> frontEventList = null;
	private List<GdEvent> frontEventEndList = null;
	private List<GdEventMemo> frontEventMemoList = null;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getEno() {
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getBody1() {
		return body1;
	}
	public void setBody1(String body1) {
		this.body1 = body1;
	}
	public GdEvent getFrontEventObj() {
		return frontEventObj;
	}
	public void setFrontEventObj(GdEvent frontEventObj) {
		this.frontEventObj = frontEventObj;
	}
	public List<GdEvent> getFrontEventList() {
		return frontEventList;
	}
	public void setFrontEventList(List<GdEvent> frontEventList) {
		this.frontEventList = frontEventList;
	}
	public List<GdEvent> getFrontEventEndList() {
		return frontEventEndList;
	}
	public void setFrontEventEndList(List<GdEvent> frontEventEndList) {
		this.frontEventEndList = frontEventEndList;
	}
	public List<GdEventMemo> getFrontEventMemoList() {
		return frontEventMemoList;
	}
	public void setFrontEventMemoList(List<GdEventMemo> frontEventMemoList) {
		this.frontEventMemoList = frontEventMemoList;
	}
	@Override
	public String toString() {
		return "FrontEventVO [sno=" + sno + ", eno=" + eno + ", mode=" + mode
				+ ", body=" + body + ", body1=" + body1 + ", frontEventObj="
				+ frontEventObj + ", frontEventList=" + frontEventList
				+ ", frontEventEndList=" + frontEventEndList
				+ ", frontEventMemoList=" + frontEventMemoList + "]";
	}
}
