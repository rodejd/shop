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

public class OrderListStepChangeVO {
	private String ordno;
	private String changeStep;
	private String[] ordnoList; //주문번호 리스트
	
	public String[] getOrdnoList() {
		return ordnoList;
	}

	public void setOrdnoList(String[] ordnoList) {
		this.ordnoList = ordnoList;
	}

	public String getOrdno() {
		return ordno;
	}

	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}

	public String getChangeStep() {
		return changeStep;
	}

	public void setChangeStep(String changeStep) {
		this.changeStep = changeStep;
	}

	@Override
	public String toString() {
		return "OrderListStepChangeVO [ordno=" + ordno + ", changeStep="
				+ changeStep + "]";
	}
}
