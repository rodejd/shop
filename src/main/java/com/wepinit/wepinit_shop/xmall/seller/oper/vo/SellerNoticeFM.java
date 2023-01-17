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
package com.wepinit.wepinit_shop.xmall.seller.oper.vo;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;

import java.util.List;

public class SellerNoticeFM extends PageMaker {
	
		// 검색조건
		private String schType = "";
		private String schText = "";
		private String tmpType = "";
		private String tmpText = "";
		//========================문의부분=========================
		//판매자코드
		private String sellerCd;
		
		//gd_seller_quest 의 리스트
		private List<SellerQuestionVO> questionlist;
		private SellerQuestionVO questionVO = new SellerQuestionVO();

		//gd_seller_quest_answer 
		private SellerAnswerVO answerVO = new SellerAnswerVO();
		private List<SellerAnswerVO> answerlist;
		//========================================================

		//========================공지사항부분=========================
		private String procType = "";	// 처리구분(I/M/D)
		private String targetURL = "";	// 처리후 리턴 url
		
		// 판매자공지사항 정보
		private SellerNoticeVO noticeVO = new SellerNoticeVO();
		
		// 판매자 공지사항 목록
		private List<SellerNoticeVO> noticeList;

		//========================================================

		
		public String getProcType() {
			return procType;
		}
		public String getTmpType() {
			return tmpType;
		}
		public void setTmpType(String tmpType) {
			this.tmpType = tmpType;
		}
		public String getTmpText() {
			return tmpText;
		}
		public void setTmpText(String tmpText) {
			this.tmpText = tmpText;
		}
		public void setProcType(String procType) {
			this.procType = procType;
		}
		public String getTargetURL() {
			return targetURL;
		}
		public void setTargetURL(String targetURL) {
			this.targetURL = targetURL;
		}
		
		public SellerNoticeVO getNoticeVO() {
			return noticeVO;
		}
		public void setNoticeVO(SellerNoticeVO noticeVO) {
			this.noticeVO = noticeVO;
		}
		public List<SellerNoticeVO> getNoticeList() {
			return noticeList;
		}
		public void setNoticeList(List<SellerNoticeVO> noticeList) {
			this.noticeList = noticeList;
		}
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
		public List<SellerQuestionVO> getQuestionlist() {
			return questionlist;
		}
		public void setQuestionlist(List<SellerQuestionVO> questionlist) {
			this.questionlist = questionlist;
		}
		public SellerAnswerVO getAnswerVO() {
			return answerVO;
		}
		public void setAnswerVO(SellerAnswerVO answerVO) {
			this.answerVO = answerVO;
		}
		
		public SellerQuestionVO getQuestionVO() {
			return questionVO;
		}
		public void setQuestionVO(SellerQuestionVO questionVO) {
			this.questionVO = questionVO;
		}
		
		public List<SellerAnswerVO> getAnswerlist() {
			return answerlist;
		}
		public void setAnswerlist(List<SellerAnswerVO> answerlist) {
			this.answerlist = answerlist;
		}
		
		/**
		 * View 페이지 validation check
		 */
		public void validNoticeView() {
			// 고유번호가 없을 경우 오류처리
			if ( "".equals(noticeVO.getSno()) ) {
				throw new BizException("필수입력 누락");
			}
		}
		
		/**
		 * Proc validation check
		 */
		public void validNoticeProc(){
			if ( !"I".equals(procType) ) {
				// 고유번호가 없을 경우 오류처리
				if ( "".equals(noticeVO.getSno()) ) {
					throw new BizException("필수입력 누락 - sno");
				}
			}
			
			// 등록, 수정일 경우 제목, 내용 체크
			if ( !"D".equals(procType) ) {
				if ( "".equals(noticeVO.getTitle()) ) {
					throw new BizException("필수입력 누락 - title");
				}
				if ( "".equals(noticeVO.getContents()) ) {
					throw new BizException("필수입력 누락 - contents");
				}
			}
		}
		@Override
		public String toString() {
			return "SellerNoticeFM [schType=" + schType + ", schText="
					+ schText + ", sellerCd=" + sellerCd + ", questionlist="
					+ questionlist + ", questionVO=" + questionVO
					+ ", answerVO=" + answerVO + "]";
		}
		
		
		
}
