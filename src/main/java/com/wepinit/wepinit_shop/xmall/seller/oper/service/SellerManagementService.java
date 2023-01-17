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

import com.wepinit.wepinit_shop.xcube.util.CryptoUtil;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.seller.oper.dao.SellerManagementMapper;
import com.wepinit.wepinit_shop.xmall.seller.oper.vo.SellerManagementFM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class SellerManagementService {
	private static final Logger logger = LoggerFactory.getLogger(SellerManagementService.class);
	
	@Resource
	SellerManagementMapper mapper;

	/**
	 * 판매자 상세보기
	 * @param managementFM
	 * @return
	 */
	public SellerManagementFM getSellerManagementView(SellerManagementFM managementFM) {
		
		if (logger.isDebugEnabled()) logger.debug("1111111111111111111===" + managementFM.getMode() + " " + managementFM.getSellerCd());

		if( managementFM.getMode() != null || !"".equals(managementFM.getSellerCd()) ) {
			
			managementFM.setManagementVO(mapper.getSellerManagementView(managementFM));
		}

		return managementFM;
	}
	
	/**
	 * 비밀번호 업데이트
	 * @param managementFM
	 */
	public void updatePwd(SellerManagementFM managementFM){

		// 비밀번호 초기화시 기존아이디가 비밀번호
		if(managementFM.getDiv() == "R") {
			// 최초가입시 비밀번호는 아이디 SHA256 방식
			if(managementFM.getId() != null) {
				String getPw = CryptoUtil.getSHA256(managementFM.getId());
				
				managementFM.setPwd(getPw);
				
				mapper.updatePwd(managementFM);

			}
		} else {
			
			String getPw = CryptoUtil.getSHA256(managementFM.getPwd());

			managementFM.setPwd(getPw);

			mapper.updatePwd(managementFM);
		}

	}
	
	/**
	 * 판매자 수정
	 * @param managementFM
	 * @return
	 */
	public void getSellerManagementModify(SellerManagementFM managementFM) {

		if( managementFM.getMode() != null || !"".equals(managementFM.getSellerCd()) ) {

			// 수정시 기존에 등록된 부분이 있으면 Update 아니면 Inset ( 판매사정보만 저장 후 추후 기본저장도 가능해서 )
			int mCount = mapper.getGdSellerMngCount(managementFM.getSellerCd());
			int aCount = mapper.getGdSellerAccCount(managementFM.getSellerCd());

			mapper.updateGdSeller(managementFM);

			if(mCount > 0) {
				mapper.updateGdSellerMng(managementFM);
			} else {
				mapper.insertGdSellerMng(managementFM);
			}
			
			if(aCount > 0) {
				mapper.updateGdSellerAcc(managementFM);
			} else {
				mapper.insertGdSellerAcc(managementFM);
			}
		}  else {
			throw new BizException("common.00001");
		}		
	}

}
