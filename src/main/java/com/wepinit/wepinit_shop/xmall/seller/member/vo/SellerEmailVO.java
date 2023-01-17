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
package com.wepinit.wepinit_shop.xmall.seller.member.vo;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

public class SellerEmailVO extends PageMaker {
	

	private String mno;
	private String toName;
	private String toEmail;
	private String fromName;
	private String fromEmail;
	private String subject;
	private String etc1;
	private String etc2;
	private String etc3;
	private String contents;
	private String tranYn;
	private String tranDt;
	private String regDt;
	private String recvYn;
	private String recvDt;
	

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	
	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getEtc1() {
		return etc1;
	}

	public void setEtc1(String etc1) {
		this.etc1 = etc1;
	}
	
	public String getEtc2() {
		return etc2;
	}

	public void setEtc2(String etc2) {
		this.etc2 = etc2;
	}
	
	public String getEtc3() {
		return etc3;
	}

	public void setEtc3(String etc3) {
		this.etc3 = etc3;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String getTranYn() {
		return tranYn;
	}

	public void setTranYn(String tranYn) {
		this.tranYn = tranYn;
	}
	
	public String getTranDt() {
		return tranDt;
	}

	public void setTranDt(String tranDt) {
		this.tranDt = tranDt;
	}
	
	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	
	public String getRecvYn() {
		return recvYn;
	}

	public void setRecvYn(String recvYn) {
		this.recvYn = recvYn;
	}
	
	public String getRecvDt() {
		return recvDt;
	}

	public void setRecvDt(String recvDt) {
		this.recvDt = recvDt;
	}

	@Override
	public String toString() {
		return "SellerMailVO [mno=" + mno + ", toName=" + toName + ", toEmail=" + toEmail 
				 + ", fromName=" + fromName + ", fromEmail=" + fromEmail + ", subject=" + subject
				 + ", etc1=" + etc1 + ", etc2=" + etc2 + ", etc3=" + etc3
				 + ", contents=" + contents + ", tranYn=" + tranYn + ", tranDt=" + tranDt
				 + ", regDt=" + regDt + ", recvYn=" + recvYn + ", recvDt=" + recvDt
				 + "]";
	}

											
}
