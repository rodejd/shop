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
package com.wepinit.wepinit_shop.xmall.admin.service.seller;

import com.wepinit.wepinit_shop.xmall.admin.dao.seller.AdminSellerQuestMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.seller.AdminSellerQuestAnswerFM;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional
public class AdminSellerQuestService {
	@Resource
	private AdminSellerQuestMapper mapper;
	
	
	
	public String insertAdminAnswer(AdminSellerQuestAnswerFM adminSellerQuestAnswerFM,String adminId) {
		adminSellerQuestAnswerFM.getAnswerVO().setRegId(adminId);
		mapper.insertAdminAnswer(adminSellerQuestAnswerFM.getAnswerVO());
		return "redirect:adminanswer?schType=qno&schText="+adminSellerQuestAnswerFM.getAnswerVO().getQno();
	}

	public String insertAdminQuest(AdminSellerQuestAnswerFM adminSellerQuestAnswerFM) {
		mapper.insertAdminQuest(adminSellerQuestAnswerFM.getQuestionVO());
		return "redirect:/shop/admin/seller/sellerQuestList";
	}

	
	public String sellerQuestList(AdminSellerQuestAnswerFM adminSellerQuestAnswerFM) {
		// TODO Auto-generated method stub

		//총 질문갯수
		adminSellerQuestAnswerFM.setRowCount(mapper.getSellerQuestCount(adminSellerQuestAnswerFM));
		
		//질문리스트
		adminSellerQuestAnswerFM.setQuestionlist(mapper.getSellerQuestList(adminSellerQuestAnswerFM));
		
	
		return "/shop/admin/seller/sellerQuestList";
	}

	public String questDelete(AdminSellerQuestAnswerFM adminSellerQuestAnswerFM, String[] delcheck) {
		//체크된 항목만 삭제
		if(delcheck!=null){
			for(int i =0 ;i<delcheck.length;i++){
				mapper.deleteQuest(delcheck[i]);
				mapper.deleteAnswer(delcheck[i]);
			}
		}
		return "redirect:/shop/admin/seller/sellerQuestList";
	}

	public String adminanswer(AdminSellerQuestAnswerFM adminSellerQuestAnswerFM) {
		//질문내용
		adminSellerQuestAnswerFM.setQuestionVO(mapper.getSellerQuestion(adminSellerQuestAnswerFM));
		//답변내용
		adminSellerQuestAnswerFM.setAnswerlist(mapper.getSellerAnswerList(adminSellerQuestAnswerFM));
		return "/shop/admin/seller/adminAnswer";
	}

	
}
