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
package com.wepinit.wepinit_shop.xmall.front.vo.member;

public class FrontMemberHackVO extends FrontMemberVO {
	// in
	private String outComplain;
	private String outComplainText;

	// out
	private String alertMessage;
	private String redirectUrl;
	
	public String getOutComplain() {
		return outComplain;
	}
	public void setOutComplain(String outComplain) {
		this.outComplain = outComplain;
	}
	public String getOutComplainText() {
		return outComplainText;
	}
	public void setOutComplainText(String outComplainText) {
		this.outComplainText = outComplainText;
	}
	public String getAlertMessage() {
		return alertMessage;
	}
	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
}
