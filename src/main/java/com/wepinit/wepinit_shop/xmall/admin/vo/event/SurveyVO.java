/*******************************************************************
                     PMGroupKorea Co., Ltd.
Copyright PMGroupKorea Co., Ltd. 2005. All rights reserved.
No part of this work covered by the copyright hereon may be reproduced,
stored in a retrieval system, in any form or by any means, electronic,
mechanical, photocopying, recording or otherwise, without the prior
written permission of PMGroupKorea Co., Ltd.
SOLUTION    :   XMaLL4 for Spring
FILE_NAME   :
DATE        :   2020.05.07
AUTHOR      :   PMGK S/W Engineer   <hyunbin7195@daum.net>
DESCRIPTION :
FUNCTIONS   :
HISTORY     :
REMARKS     :
******************************************************************/

package com.wepinit.wepinit_shop.xmall.admin.vo.event;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.front.vo.event.FrontSurveyCommentVO;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class SurveyVO extends PageMaker{
	
	private String mode ;			// 수정 or 등록
	
	private String surveySno ;		//일련번호
	private String surveyTitle ;	//설문제목
	private String open ;			//공개여부 
	private String sdate ;			//시작일
	private String edate ;			//종료일
	private String surveydt ;		//등록일자
	private String [] question ;	//질문제목
	private String [] surveyNum ; 	//설문번호
	
	private String statVal ;//게시 게시취소
	private String [] surveyArr ;	//설문조사 인덱스
	
	private int sumSurveyCnt ;		//질문수 합계
	
	private String schSort = ""; //정렬
	private String schTitle = ""; //제목검색
	
	private SurveyVO detailSurveyVO;
	
	private List<SurveyVO> surveyList ;
	
	private List<Map<String,Object>> questions;
	
	private List<FrontSurveyCommentVO> frontSurveyCommentList;
	
	
	
	public String getStatVal() {
		return statVal;
	}
	public void setStatVal(String statVal) {
		this.statVal = statVal;
	}
	public String[] getSurveyArr() {
		return surveyArr;
	}
	public void setSurveyArr(String[] surveyArr) {
		this.surveyArr = surveyArr;
	}
	public SurveyVO getDetailSurveyVO() {
		return detailSurveyVO;
	}
	public void setDetailSurveyVO(SurveyVO detailSurveyVO) {
		this.detailSurveyVO = detailSurveyVO;
	}
	public String getSchTitle() {
		return schTitle;
	}
	public void setSchTitle(String schTitle) {
		this.schTitle = schTitle;
	}
	
	public List<FrontSurveyCommentVO> getFrontSurveyCommentList() {
		return frontSurveyCommentList;
	}

	public void setFrontSurveyCommentList(
			List<FrontSurveyCommentVO> frontSurveyCommentList) {
		this.frontSurveyCommentList = frontSurveyCommentList;
	}

	public String[] getSurveyNum() {
		return surveyNum;
	}

	public void setSurveyNum(String[] surveyNum) {
		this.surveyNum = surveyNum;
	}

	public int getSumSurveyCnt() {
		return sumSurveyCnt;
	}

	public void setSumSurveyCnt(int sumSurveyCnt) {
		this.sumSurveyCnt = sumSurveyCnt;
	}

	public List<Map<String, Object>> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Map<String, Object>> questions) {
		this.questions = questions;
	}

	public List<SurveyVO> getSurveyList() {
		return surveyList;
	}

	public void setSurveyList(List<SurveyVO> surveyList) {
		this.surveyList = surveyList;
	}

	public String getSchSort() {
		return schSort;
	}

	public void setSchSort(String schSort) {
		this.schSort = schSort;
	}

	public void setSurveySno(String surveySno) {
		this.surveySno = surveySno;
	}

	public String getMode() {
		return mode;
	}
	
	public void setMode(String mode){
		this.mode = mode;
	}
	
	public String getSurveySno() {
		return surveySno;
	}
	public void setSno(String surveySno) {
		this.surveySno = surveySno;
	}
	public String getSurveyTitle() {
		return surveyTitle;
	}
	public void setSurveyTitle(String surveyTitle) {
		this.surveyTitle = surveyTitle;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public String getSurveydt() {
		return surveydt;
	}
	public void setSurveydt(String surveydt) {
		this.surveydt = surveydt;
	}
	public String[] getQuestion() {
		return question;
	}
	public void setQuestion(String[] question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "SurveyVO [mode=" + mode + ", surveySno=" + surveySno
				+ ", surveyTitle=" + surveyTitle + ", open=" + open
				+ ", sdate=" + sdate + ", edate=" + edate + ", surveydt="
				+ surveydt + ", question=" + Arrays.toString(question)
				+ ", surveyNum=" + Arrays.toString(surveyNum)
				+ ", sumSurveyCnt=" + sumSurveyCnt + ", schSort=" + schSort
				+ ", surveyList=" + surveyList + ", questions=" + questions
				+ ", frontSurveyCommentList=" + frontSurveyCommentList + "]";
	}
	
}
