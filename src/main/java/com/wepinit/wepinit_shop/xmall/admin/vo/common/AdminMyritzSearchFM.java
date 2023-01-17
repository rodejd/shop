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
package com.wepinit.wepinit_shop.xmall.admin.vo.common;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

import java.util.List;





public class AdminMyritzSearchFM extends PageMaker {
	
	
	// #####
	// # INPUT
	// #####
	private String schType = "";
	private String schWord = "";
	
	
	// #####
	// # OUTPUT
	// #####
	private List<AdminMyritzSearchListVO> myritzSearchList = null;
	
	// #####
	// # GET SET
	// #####
	public String getSchType() {
		return schType;
	}
	public void setSchType(String schType) {
		this.schType = schType;
	}
	public String getSchWord() {
		return schWord;
	}
	public void setSchWord(String schWord) {
		this.schWord = schWord;
	}
	public List<AdminMyritzSearchListVO> getMyritzSearchList() {
		return myritzSearchList;
	}
	public void setMyritzSearchList(List<AdminMyritzSearchListVO> myritzSearchList) {
		this.myritzSearchList = myritzSearchList;
	}
}
