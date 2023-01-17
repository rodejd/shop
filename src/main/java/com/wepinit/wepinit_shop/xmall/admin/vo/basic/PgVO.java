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

import java.util.Arrays;

public class PgVO extends PageMaker {
	//관리
	String settlePg="";
	String[] set_use = null;
	String[] escrow = null;
	String spot = "";
	String target="";
	
	//공통
	String pg_id = "";
	String pg_quota = "";
	String pg_zerofee = "";
	String escrow_use = "";
	String escrow_min = "";
	String pg_receipt = "";
	String cfg_display_egg = "";
	

	//inicis,dacom,kcp
	String pg_zerofee_period = "";

	//inicis
	String escrow_id = "";
	//dacom 
	String pg_mertkey = "";
	//kcp
	String pg_key = "";
	//allat
	String pg_formkey = "";
	String pg_crosskey = "";
	String pg_cert = "";
	String pg_bonus = "";
	
	
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getSettlePg() {
		return settlePg;
	}
	public void setSettlePg(String settlePg) {
		this.settlePg = settlePg;
	}
	public String[] getSet_use() {
		return set_use;
	}
	public void setSet_use(String[] set_use) {
		this.set_use = set_use;
	}
	public String[] getEscrow() {
		return escrow;
	}
	public void setEscrow(String[] escrow) {
		this.escrow = escrow;
	}
	public String getPg_id() {
		return pg_id;
	}
	public void setPg_id(String pg_id) {
		this.pg_id = pg_id;
	}
	public String getPg_quota() {
		return pg_quota;
	}
	public void setPg_quota(String pg_quota) {
		this.pg_quota = pg_quota;
	}
	public String getPg_zerofee() {
		return pg_zerofee;
	}
	public void setPg_zerofee(String pg_zerofee) {
		this.pg_zerofee = pg_zerofee;
	}
	public String getEscrow_use() {
		return escrow_use;
	}
	public void setEscrow_use(String escrow_use) {
		this.escrow_use = escrow_use;
	}
	public String getEscrow_min() {
		return escrow_min;
	}
	public void setEscrow_min(String escrow_min) {
		this.escrow_min = escrow_min;
	}
	public String getPg_receipt() {
		return pg_receipt;
	}
	public void setPg_receipt(String pg_receipt) {
		this.pg_receipt = pg_receipt;
	}
	public String getCfg_display_egg() {
		return cfg_display_egg;
	}
	public void setCfg_display_egg(String cfg_display_egg) {
		this.cfg_display_egg = cfg_display_egg;
	}
	public String getSpot() {
		return spot;
	}
	public void setSpot(String spot) {
		this.spot = spot;
	}
	public String getPg_zerofee_period() {
		return pg_zerofee_period;
	}
	public void setPg_zerofee_period(String pg_zerofee_period) {
		this.pg_zerofee_period = pg_zerofee_period;
	}
	public String getEscrow_id() {
		return escrow_id;
	}
	public void setEscrow_id(String escrow_id) {
		this.escrow_id = escrow_id;
	}
	public String getPg_mertkey() {
		return pg_mertkey;
	}
	public void setPg_mertkey(String pg_mertkey) {
		this.pg_mertkey = pg_mertkey;
	}
	public String getPg_key() {
		return pg_key;
	}
	public void setPg_key(String pg_key) {
		this.pg_key = pg_key;
	}
	public String getPg_formkey() {
		return pg_formkey;
	}
	public void setPg_formkey(String pg_formkey) {
		this.pg_formkey = pg_formkey;
	}
	public String getPg_crosskey() {
		return pg_crosskey;
	}
	public void setPg_crosskey(String pg_crosskey) {
		this.pg_crosskey = pg_crosskey;
	}
	public String getPg_cert() {
		return pg_cert;
	}
	public void setPg_cert(String pg_cert) {
		this.pg_cert = pg_cert;
	}
	public String getPg_bonus() {
		return pg_bonus;
	}
	public void setPg_bonus(String pg_bonus) {
		this.pg_bonus = pg_bonus;
	}
	@Override
	public String toString() {
		return "PgVO [settlePg=" + settlePg + ", set_use="
				+ Arrays.toString(set_use) + ", escrow="
				+ Arrays.toString(escrow) + ", spot=" + spot + ", target="
				+ target + ", pg_id=" + pg_id + ", pg_quota=" + pg_quota
				+ ", pg_zerofee=" + pg_zerofee + ", escrow_use=" + escrow_use
				+ ", escrow_min=" + escrow_min + ", pg_receipt=" + pg_receipt
				+ ", cfg_display_egg=" + cfg_display_egg
				+ ", pg_zerofee_period=" + pg_zerofee_period + ", escrow_id="
				+ escrow_id + ", pg_mertkey=" + pg_mertkey + ", pg_key="
				+ pg_key + ", pg_formkey=" + pg_formkey + ", pg_crosskey="
				+ pg_crosskey + ", pg_cert=" + pg_cert + ", pg_bonus="
				+ pg_bonus + "]";
	}
}
