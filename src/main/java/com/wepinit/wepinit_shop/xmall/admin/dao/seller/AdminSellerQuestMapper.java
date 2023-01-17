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
package com.wepinit.wepinit_shop.xmall.admin.dao.seller;

import com.wepinit.wepinit_shop.xmall.admin.vo.seller.AdminSellerAnswerVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.seller.AdminSellerQuestAnswerFM;
import com.wepinit.wepinit_shop.xmall.admin.vo.seller.AdminSellerQuestVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AdminSellerQuestMapper {
	
	public int getSellerQuestCount(AdminSellerQuestAnswerFM adminSellerQuestAnswerFM);						//판매자 질문 총갯수
	public List<AdminSellerQuestVO> getSellerQuestList(AdminSellerQuestAnswerFM adminSellerQuestAnswerFM);	//판매자 질문 리스트
	public List<AdminSellerAnswerVO> getSellerAnswerList(AdminSellerQuestAnswerFM adminSellerQuestAnswerFM);			//판매자 대답
	public int getSellerAnswerCount(AdminSellerQuestAnswerFM adminSellerQuestAnswerFM);
	public AdminSellerQuestVO getSellerQuestion(AdminSellerQuestAnswerFM adminSellerQuestAnswerFM);			//판매자 질문
	public void insertAdminAnswer(AdminSellerAnswerVO sellerAnswerVO);										//관리자 대답 추가 사용하지 않음
//	public void updateAdminAnswer(AdminSellerAnswerVO sellerAnswerVO);										//관리자 대답 수정 사용하지 않음
//	public int getSellerCorrect(AdminSellerQuestAnswerFM adminSellerQuestAnswerFM);							//경로 구분자 	사용하지 않음
	public void insertAdminQuest(AdminSellerQuestVO sellerQuestVO);											//관리자 질문 추가
	public void deleteQuest(String sno);
	public void deleteAnswer(String sno);
}