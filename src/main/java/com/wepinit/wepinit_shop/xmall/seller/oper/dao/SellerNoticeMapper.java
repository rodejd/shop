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
package com.wepinit.wepinit_shop.xmall.seller.oper.dao;

import com.wepinit.wepinit_shop.xmall.seller.oper.vo.SellerAnswerVO;
import com.wepinit.wepinit_shop.xmall.seller.oper.vo.SellerNoticeFM;
import com.wepinit.wepinit_shop.xmall.seller.oper.vo.SellerNoticeVO;
import com.wepinit.wepinit_shop.xmall.seller.oper.vo.SellerQuestionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellerNoticeMapper {
	public int getSellerQuestCount(SellerNoticeFM sellerNoticeFM);
	public List<SellerQuestionVO> getSellerQuestList(SellerNoticeFM sellerNoticeFM);
	public List<SellerAnswerVO> getSellerAnswer(SellerNoticeFM sellerNoticeFM);
	public SellerQuestionVO getSellerQuestion(SellerNoticeFM sellerNoticeFM);
	public void insertSellerAnswer(SellerAnswerVO sellerAnswerVO);
	public void updateSellerAnswer(SellerAnswerVO sellerAnswerVO);
	public int getSellerCorrect(SellerNoticeFM sellerNoticeFM);
	public int getSellerNoticeListCount(SellerNoticeFM sellerNoticeFM);
	public List<SellerNoticeVO> getSellerNoticeList(SellerNoticeFM sellerNoticeFM);
	public SellerNoticeVO getSellerNoticeView(SellerNoticeFM sellerNoticeFM);
}
