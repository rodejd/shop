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
package com.wepinit.wepinit_shop.xmall.seller.oper.service;

import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.seller.oper.dao.SellerNoticeMapper;
import com.wepinit.wepinit_shop.xmall.seller.oper.vo.SellerNoticeFM;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class SellerNoticeService {
	@Resource
	SellerNoticeMapper mapper;
	
	public String sellerQuestion(SellerNoticeFM sellerNoticeFM,String sellerCd){
		//세션에서 받아온 sellerCd
		sellerNoticeFM.setSellerCd(sellerCd);
		//질문갯수
		sellerNoticeFM.setRowCount(mapper.getSellerQuestCount(sellerNoticeFM));
		//질문리스트
		sellerNoticeFM.setQuestionlist(mapper.getSellerQuestList(sellerNoticeFM));
		
		return "seller/oper/sellerquest";
	}
	public String sellerAnswer(SellerNoticeFM sellerNoticeFM, String sellerCd) {
		//세션에서 받아온 sellerCd
		sellerNoticeFM.setSellerCd(sellerCd);
		
		//경로가 올바르지 않을경우 익셉션처리
		int sellerCorrect = 0;
		sellerCorrect =mapper.getSellerCorrect(sellerNoticeFM);
		if(sellerCorrect==0){
			throw new BizException("common.00004");
		}
		
		//질문내용
		sellerNoticeFM.setQuestionVO(mapper.getSellerQuestion(sellerNoticeFM));
		//답변내용
		sellerNoticeFM.setAnswerlist(mapper.getSellerAnswer(sellerNoticeFM));
				
		return "seller/oper/selleranswer";
	}
	
//	public void updateSellerAnswer(SellerNoticeFM sellerNoticeFM) {
//
//		mapper.updateSellerAnswer(sellerNoticeFM.getAnswerVO());
//	}
	public String answerIndb(SellerNoticeFM sellerNoticeFM, String userId,String sellerCd) {

 		sellerNoticeFM.getAnswerVO().setRegId(userId);
		sellerNoticeFM.setSellerCd(sellerCd);
		
		//경로가 올바르지 않을경우 익셉션처리
		int sellerCorrect = 0;
		sellerCorrect =mapper.getSellerCorrect(sellerNoticeFM);
		
		if(sellerCorrect==0){
			throw new BizException("common.00004");
		}
			//contents 내용, sellerNoticeFM.getSchType()  qno , sellerNoticeFM.getSchType() sellerNoticeFM.getSchText() 번호
		mapper.insertSellerAnswer(sellerNoticeFM.getAnswerVO());
		
		return "redirect:/shop/seller/oper/selleranswer?schType=qno&schText="+sellerNoticeFM.getSchText();
	}
	
	/**
	 * 판매사 공지사항목록
	 * @param sellerNoticeFM
	 * @return url
	 */
	public String getSellerNoticeList(SellerNoticeFM sellerNoticeFM) {
		sellerNoticeFM.setSchText(sellerNoticeFM.getTmpText());
		sellerNoticeFM.setSchType(sellerNoticeFM.getTmpType());

		//총 갯수
		sellerNoticeFM.setRowCount(mapper.getSellerNoticeListCount(sellerNoticeFM));
		// 목록
		sellerNoticeFM.setNoticeList(mapper.getSellerNoticeList(sellerNoticeFM));
		
		
		return "seller/oper/noticeList";
	}
	public SellerNoticeFM getSellerNoticeView(SellerNoticeFM sellerNoticeFM) {

		//공지사항 정보
		sellerNoticeFM.setNoticeVO(mapper.getSellerNoticeView(sellerNoticeFM));
				
		return sellerNoticeFM;
	}
	
	public String sellerEmailQuestion(SellerNoticeFM sellerNoticeFM,String sellerCd){
		//세션에서 받아온 sellerCd
		sellerNoticeFM.setSellerCd(sellerCd);

		return "seller/oper/sellerEmailQuest";
	}
	
	public String sellerSendEmail(SellerNoticeFM sellerNoticeFM,String sellerCd){
		//세션에서 받아온 sellerCd
		sellerNoticeFM.setSellerCd(sellerCd);

		return "seller/oper/sellerEmailQuest";
	}

	
}
