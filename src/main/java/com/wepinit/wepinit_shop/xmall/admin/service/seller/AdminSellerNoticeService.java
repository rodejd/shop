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

import com.wepinit.wepinit_shop.xmall.admin.dao.seller.AdminSellerNoticeMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.seller.AdminSellerNoticeFM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class AdminSellerNoticeService {
	private static final Logger logger = LoggerFactory.getLogger(AdminSellerNoticeService.class);
	
	@Resource
	AdminSellerNoticeMapper mapper;

	/**
	 * 판매사 공지사항 목록
	 * @param noticeFM
	 * @return
	 */
	public AdminSellerNoticeFM getSellerNoticeList(AdminSellerNoticeFM noticeFM){
		
		noticeFM.setSchText(noticeFM.getTmpText());
		noticeFM.setSchType(noticeFM.getTmpType());
		//총 갯수
		noticeFM.setRowCount(mapper.getSellerNoticeListCount(noticeFM));
		
		// 목록
		noticeFM.setNoticeList(mapper.getSellerNoticeList(noticeFM));
		
		return noticeFM;
	}
	
	/**
	 * 판매사 공지사항 정보
	 * @param noticeFM
	 * @return
	 */
	public AdminSellerNoticeFM getSellerNoticeView(AdminSellerNoticeFM noticeFM){
		
		//공지사항 정보
		noticeFM.setNoticeVO(mapper.getSellerNoticeView(noticeFM));
		
		return noticeFM;
	}
	
	/**
	 * 판매사 공지사항 정보
	 * @param noticeFM
	 * @return
	 */
	public AdminSellerNoticeFM getSellerNoticeProc(AdminSellerNoticeFM noticeFM){
		
		if ( "I".equals(noticeFM.getProcType()) ) {
			// 등록
			noticeFM.getNoticeVO().setNoticeYn("N");	// 공지글여부
			mapper.getSellerNoticeInsert(noticeFM.getNoticeVO());
		}else if ( "M".equals(noticeFM.getProcType()) ) {
			// 수정
			mapper.getSellerNoticeModify(noticeFM.getNoticeVO());
		}else if ( "D".equals(noticeFM.getProcType()) ) {
			// 삭제
			mapper.getSellerNoticeDelete(noticeFM.getNoticeVO());
		}
		
		return noticeFM;
	}
}
