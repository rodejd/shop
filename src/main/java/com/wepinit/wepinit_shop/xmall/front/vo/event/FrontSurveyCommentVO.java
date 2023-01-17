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
* 세부업무구분명		:   설문조사 댓글 VO 
* 작성자 				: 	이현빈
* 설명 				: 	사용자 Community VO
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20200514		이현빈			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.vo.event;

public class FrontSurveyCommentVO {

	private int sno ; 			//일련번호
	private int surveySno;		//설문번호
	private String id;			//회원아이디
	private String memo;		//comment
	private String regdt;		//등록날짜
	private String mode; 		//모드
	private String modfiyMemo;	//수정 comment
	
	
	
	
	public String getModfiyMemo() {
		return modfiyMemo;
	}
	public void setModfiyMemo(String modfiyMemo) {
		this.modfiyMemo = modfiyMemo;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getSurveySno() {
		return surveySno;
	}
	public void setSurveySno(int surveySno) {
		this.surveySno = surveySno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	
	@Override
	public String toString() {
		return "FrontSurveyCommentVO [sno=" + sno + ", surveySno=" + surveySno
				+ ", id=" + id + ", memo=" + memo + ", regdt=" + regdt + "]";
	}
	
	
	
}
