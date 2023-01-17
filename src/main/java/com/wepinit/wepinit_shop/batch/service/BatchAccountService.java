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

package com.wepinit.wepinit_shop.batch.service;

import com.wepinit.wepinit_shop.batch.BatchWAcount;
import com.wepinit.wepinit_shop.batch.dao.BatchAccountMapper;
import com.wepinit.wepinit_shop.batch.vo.BatchAccountVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BatchAccountService {

	@Resource
	BatchAccountMapper mapper;
	

	
	private static final Logger logger = LoggerFactory.getLogger(BatchWAcount.class);
	
	
	/**
	 *  판매사정보 정산주기 조회
	 *  @param sInfo : : W(주간) H(15일) M(월간)
	 */
	public List<BatchAccountVO> sellerInfo(String sInfo){
		return this.mapper.sellerInfo(sInfo);
		
	}
	
	/**
	 * 판매사별 정산
	 * @param sdate : 조회시작일자
	 * @param edate : 조회마지막일자
	 * @param flag : W(주간) H(15일) M(월간)
	 * */
	public void goodsAccountCal(String sdate,String edate, String flag) {
		
		Integer insResult = 0;
		Integer upResult = 0;
		try {
			List<BatchAccountVO> list = new ArrayList<BatchAccountVO>();
			
			//정산대상 즉 승인된 판매사조회
			List<BatchAccountVO> sInfo = sellerInfo(flag);

			logger.debug(" sInfo.size() >>>>>>>>>>> "+sInfo.size());
			for(BatchAccountVO bVO : sInfo) {
				insResult = 0;
				upResult = 0;
				logger.debug(" sInfo >>>>>>>>>>> "+bVO.getSellerCd());
				
				bVO.setSdate(sdate);
				bVO.setEdate(edate);
				
				//주문일자가 정산기간내의 배송완료 주문상품조회
				list = mapper.sellerAccountInfo(bVO);
				
				logger.debug("sellerAccountInfo list ####################"+list.size());
			//	logger.debug("sellerAccountInfo list.get(0) ####################"+list.get(0));
				//정산정보 insert gd_account_info
				if(list.size() > 0){
					insResult = mapper.batchInsert(list);
				}
				//정산완료 상태 update gd_order_item
				if(insResult>0){
					upResult = mapper.AccountGoodsUpdate(list);
				}
				logger.debug("batch result #################### "+insResult+"/"+upResult);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
