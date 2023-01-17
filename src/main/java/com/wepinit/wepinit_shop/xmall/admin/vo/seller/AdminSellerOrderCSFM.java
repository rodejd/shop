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
package com.wepinit.wepinit_shop.xmall.admin.vo.seller;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public class AdminSellerOrderCSFM extends PageMaker {
	private String mode;
	private String skey;
	private String sword;
	private String[] type;
	private String typeStr = "";
	private String regdt1;
	private String regdt2;
	private String settlekind;
	
	/*판매사명*/
	private String schSellerNm;
	private String schSellerCd;
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public String getSword() {
		return sword;
	}
	public void setSword(String sword) {
		this.sword = sword;
	}
	public String[] getType() {
		return type;
	}
	public void setType(String[] type) {
		this.type = type;
		StringUtils.removeDuplicateStrings(type);
		this.typeStr = StringUtils.arrayToCommaDelimitedString(StringUtils.removeDuplicateStrings(type));
	}
	public String getRegdt1() {
		return regdt1;
	}
	public void setRegdt1(String regdt1) {
		this.regdt1 = regdt1;
	}
	public String getRegdt2() {
		return regdt2;
	}
	public void setRegdt2(String regdt2) {
		this.regdt2 = regdt2;
	}
	public String getSettlekind() {
		return settlekind;
	}
	public void setSettlekind(String settlekind) {
		this.settlekind = settlekind;
	}
	public String getTypeStr() {
		return typeStr;
	}
	public String getSchSellerNm() {
		return schSellerNm;
	}
	public void setSchSellerNm(String schSellerNm) {
		this.schSellerNm = schSellerNm;
	}
	public String getSchSellerCd() {
		return schSellerCd;
	}
	public void setSchSellerCd(String schSellerCd) {
		this.schSellerCd = schSellerCd;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	@Override
	public String toString() {
		return "OrderCSVO [mode=" + mode + ", skey=" + skey + ", sword="
				+ sword + ", type=" + Arrays.toString(type) + ", typeStr="
				+ typeStr + ", regdt1=" + regdt1 + ", regdt2=" + regdt2
				+ ", settlekind=" + settlekind + ", schSellerNm=" + schSellerNm
				+ ", schSellerCd=" + schSellerCd + "]";
	}
}
