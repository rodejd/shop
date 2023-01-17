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
package com.wepinit.wepinit_shop.xmall.admin.vo.order;


public class PopupOrderIndbVO {
	private int sno;
	private long ordno;
	private String alertMsg;
	private String formTarget;
	
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getSno() {
		return sno;
	}
	public String getAlertMsg() {
		return alertMsg;
	}
	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}
	public String getFormTarget() {
		return formTarget;
	}
	public void setFormTarget(String formTarget) {
		this.formTarget = formTarget;
	}
	public void setOrdno(long ordno) {
		this.ordno = ordno;
	}
	public long getOrdno() {
		return ordno;
	}
	@Override
	public String toString() {
		return "PopupOrderIndbVO [sno=" + sno + ", ordno=" + ordno
				+ ", alertMsg=" + alertMsg + ", formTarget=" + formTarget + "]";
	}
}
