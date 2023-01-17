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

import java.util.List;


public class AdminSellerQuestAnswerFM extends PageMaker{
 
	// 검색조건
	private String schType = "";
	private String schText = "";
	
	//판매자코드
	private String sellerCd;
	
	//gd_seller_quest 의 리스트
	private List<AdminSellerQuestVO> questionlist;
	private List<AdminSellerAnswerVO> answerlist;
	
	private AdminSellerQuestVO questionVO = new AdminSellerQuestVO();

	//gd_seller_quest_answer 
	private AdminSellerAnswerVO answerVO = new AdminSellerAnswerVO();
	
	public String getSchType() {
		return schType;
	}
	public void setSchType(String schType) {
		this.schType = schType;
	}
	public String getSchText() {
		return schText;
	}
	public void setSchText(String schText) {
		this.schText = schText;
	}
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	public List<AdminSellerQuestVO> getQuestionlist() {
		return questionlist;
	}
	public void setQuestionlist(List<AdminSellerQuestVO> questionlist) {
		this.questionlist = questionlist;
	}
	public AdminSellerQuestVO getQuestionVO() {
		return questionVO;
	}
	public void setQuestionVO(AdminSellerQuestVO questionVO) {
		this.questionVO = questionVO;
	}
	public AdminSellerAnswerVO getAnswerVO() {
		return answerVO;
	}
	public void setAnswerVO(AdminSellerAnswerVO answerVO) {
		this.answerVO = answerVO;
	}
	public List<AdminSellerAnswerVO> getAnswerlist() {
		return answerlist;
	}
	public void setAnswerlist(List<AdminSellerAnswerVO> answerlist) {
		this.answerlist = answerlist;
	}
	
	
}
