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
package com.wepinit.wepinit_shop.xmall.admin.vo.member;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.vo.GdLogHack;

import java.util.List;

public class MemberHackVO extends PageMaker {
	private String sort = "regdt desc";		// 정렬기준 select
	private String skey;		// 검색키워드 select
	private String sword;		// 검색키워드 input
	private String srejoin;		// 재가입가능여부 select
	private String sactor;		// 탈퇴 처리형태 select
	private String sregdt1;		// 날짜선택 시작 input
	private String sregdt2;		// 날짜선택 종료 input
	private String mode;		// 삭제, 수정시
	private String sno;			// DB에 저장된 탈퇴한 회원 번호
	private String nolist;
	private String rejoindt;
	private String sql;
	private String reason;		// 상세보기 - 충고말씀
	private String adminMemo;	// 상세보기 - 관리메모
	private int totalCount; // 총 건수
	
	private String slevel = "";
	
	private int totCnt;			// 전체 갯수
	
	private List<GdLogHack> hackList;
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
	public String getSrejoin() {
		return srejoin;
	}
	public void setSrejoin(String srejoin) {
		this.srejoin = srejoin;
	}
	public String getSactor() {
		return sactor;
	}
	public void setSactor(String sactor) {
		this.sactor = sactor;
	}
	public String getSregdt1() {
		return sregdt1;
	}
	public void setSregdt1(String sregdt1) {
		this.sregdt1 = sregdt1;
	}
	public String getSregdt2() {
		return sregdt2;
	}
	public void setSregdt2(String sregdt2) {
		this.sregdt2 = sregdt2;
	}
	public String getRejoindt() {
		return rejoindt;
	}
	public void setRejoindt(String rejoindt) {
		this.rejoindt = rejoindt;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public int getTotCnt() {
		return totCnt;
	}
	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getMode() {
		return mode;
	}
	public String getNolist() {
		return nolist;
	}
	public void setNolist(String nolist) {
		this.nolist = nolist;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public void setAdminMemo(String adminMemo) {
		this.adminMemo = adminMemo;
	}
	public String getAdminMemo() {
		return adminMemo;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getReason() {
		return reason;
	}
	public String getSlevel() {
		return slevel;
	}
	public void setSlevel(String slevel) {
		this.slevel = slevel;
	}
	public List<GdLogHack> getHackList() {
		return hackList;
	}
	public void setHackList(List<GdLogHack> hackList) {
		this.hackList = hackList;
	}
}
