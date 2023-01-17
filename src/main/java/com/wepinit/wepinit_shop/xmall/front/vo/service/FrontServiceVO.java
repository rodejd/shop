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
package com.wepinit.wepinit_shop.xmall.front.vo.service;

import com.wepinit.wepinit_shop.xmall.common.CommonVO;


public class FrontServiceVO extends CommonVO{
	private String cusCompPhone;
	private String cusCompFax;
	private String cusAdminEmail;
	private int tabOrder;
	
	public String getCusCompPhone() {
		return cusCompPhone;
	}
	public void setCusCompPhone(String cusCompPhone) {
		this.cusCompPhone = cusCompPhone;
	}
	public String getCusCompFax() {
		return cusCompFax;
	}
	public void setCusCompFax(String cusCompFax) {
		this.cusCompFax = cusCompFax;
	}
	public String getCusAdminEmail() {
		return cusAdminEmail;
	}
	public void setCusAdminEmail(String cusAdminEmail) {
		this.cusAdminEmail = cusAdminEmail;
	}
	public void setTabOrder(int tabOrder) {
		this.tabOrder = tabOrder;
	}
	public int getTabOrder() {
		return tabOrder;
	}
}
