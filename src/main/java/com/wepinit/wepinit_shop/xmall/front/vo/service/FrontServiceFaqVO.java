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
package com.wepinit.wepinit_shop.xmall.front.vo.service;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

public class FrontServiceFaqVO extends PageMaker {
	private String sword = "";	// 검색어
	private String cname;	// 소카테고리 항목
	private String sitemcd = "itemcd";
	private String ssno = "";
	
	public String getSword() {
		return sword;
	}
	public void setSword(String sword) {
		this.sword = sword;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public void setSitemcd(String sitemcd) {
		this.sitemcd = sitemcd;
	}
	public String getSitemcd() {
		return sitemcd;
	}
	public void setSsno(String ssno) {
		this.ssno = ssno;
	}
	public String getSsno() {
		return ssno;
	}
}
