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

public class SweetTrackerVO {
	private String t_key;
	private String t_invoice;
	private String t_code;
	
	public String getT_key() {
		return t_key;
	}
	public void setT_key(String t_key) {
		this.t_key = t_key;
	}
	public String getT_invoice() {
		return t_invoice;
	}
	public void setT_invoice(String t_invoice) {
		this.t_invoice = t_invoice;
	}
	public String getT_code() {
		return t_code;
	}
	public void setT_code(String t_code) {
		this.t_code = t_code;
	}
	@Override
	public String toString() {
		return "SweetTrackerVO [t_key=" + t_key + ", t_invoice=" + t_invoice
				+ ", t_code=" + t_code + "]";
	}
}
