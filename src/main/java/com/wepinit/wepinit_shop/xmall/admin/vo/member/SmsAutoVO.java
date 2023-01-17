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

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdSmsAutoSet;

import java.util.List;

public class SmsAutoVO extends PageMaker {
	
	//input
	private String mode;
	private String smsType;
	private String msgC;
	private String sendC;
	private String msgA;
	private String sendA;
	private String sendM;
	
	//output
	private List<GdSmsAutoSet> gdSmsAutoSetList = null;
	
	@Override
	public String toString() {
		return "SmsAutoVO [mode=" + mode + ", smsType=" + smsType + ", msgC="
				+ msgC + ", sendC=" + sendC + ", msgA=" + msgA + ", sendA="
				+ sendA + ", sendM=" + sendM + ", gdSmsAutoSetList="
				+ gdSmsAutoSetList + "]";
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public String getMsgC() {
		return msgC;
	}

	public void setMsgC(String msgC) {
		this.msgC = msgC;
	}

	public String getSendC() {
		return sendC;
	}

	public void setSendC(String sendC) {
		this.sendC = sendC;
	}

	public String getMsgA() {
		return msgA;
	}

	public void setMsgA(String msgA) {
		this.msgA = msgA;
	}

	public String getSendA() {
		return sendA;
	}

	public void setSendA(String sendA) {
		this.sendA = sendA;
	}

	public String getSendM() {
		return sendM;
	}

	public void setSendM(String sendM) {
		this.sendM = sendM;
	}

	public List<GdSmsAutoSet> getGdSmsAutoSetList() {
		return gdSmsAutoSetList;
	}

	public void setGdSmsAutoSetList(List<GdSmsAutoSet> gdSmsAutoSetList) {
		this.gdSmsAutoSetList = gdSmsAutoSetList;
	}
	
	
				


}
