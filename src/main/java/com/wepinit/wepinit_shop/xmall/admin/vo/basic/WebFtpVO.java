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
package com.wepinit.wepinit_shop.xmall.admin.vo.basic;



public class WebFtpVO {
	private String currnetFile;

	public String getCurrnetFile() {
		return currnetFile;
	}

	public void setCurrnetFile(String currnetFile) {
		this.currnetFile = currnetFile;
	}

	@Override
	public String toString() {
		return "WebFtpVO [currnetFile=" + currnetFile + "]";
	}
	
}
