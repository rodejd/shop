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

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExchangeVO extends PageMaker {

	private ArrayList<String> sregdt;
	private String sregdt0;
	private String sregdt1;
	
	private String kr;
	private String en;
	private String cn;
	private Date regdt;

	private List<ExchangeVO> exchangeList = null;
	
	public ArrayList<String> getSregdt() {
		return sregdt;
	}
	public void setSregdt(ArrayList<String> sregdt) {
		this.sregdt = sregdt;
	}
	public String getSregdt0() {
		return sregdt0;
	}
	public void setSregdt0(String sregdt0) {
		this.sregdt0 = sregdt0;
	}
	public String getSregdt1() {
		return sregdt1;
	}
	public void setSregdt1(String sregdt1) {
		this.sregdt1 = sregdt1;
	}
	public String getKr() {
		return kr;
	}
	public void setKr(String string) {
		this.kr = string;
	}
	public String getEn() {
		return en;
	}
	public void setEn(String en) {
		this.en = en;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	
	public List<ExchangeVO> getExchangeList() {
		return exchangeList;
	}
	public void setExchangeList(List<ExchangeVO> exchangeList) {
		this.exchangeList = exchangeList;
	}
	
	@Override
	public String toString() {
		return "ExchangeVO [sregdt=" + sregdt + ", sregdt0=" + sregdt0
				+ ", sregdt1=" + sregdt1 + ", kr=" + kr + ", en=" + en + ", cn=" + cn + ", regdt=" + regdt
				+ ", exchangeList=" + exchangeList
				+ "]";
	}
}
