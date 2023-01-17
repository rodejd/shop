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
package com.wepinit.wepinit_shop.batch;

import com.wepinit.wepinit_shop.batch.service.BatchGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BatchGoods {
	@Resource
	BatchGoodsService service;

	private static final Logger logger = LoggerFactory.getLogger(BatchGoods.class);
		
//	/**
//	 * 판매중상품을 테이블에 입력
//	 * @throws Exception
//	 */
//	@Scheduled(cron="0 24 00 * * * ")
//	public void onSaleGoodsnoBatch() throws Exception {
//		System.out.println("on Sale Goodsno insert Batch Start");
//
//		this.service.deleteAllGoodsno();
//
//		BatchGoodsVO batchGoodsVO = new BatchGoodsVO();
//		this.service.insertBatchGoodsno(batchGoodsVO);
//
//		System.out.println("on Sale Goodsno insert Batch End");
//	}
	
	/**
	 * 카테고리 테이블에 상품수 입력
	 * @throws Exception
	 */
	@Scheduled(cron="0 56 00 * * * ")
	public void updateBatchCategoryGoodsCntBatch() throws Exception {
		System.out.println("updateBatchCategoryGoodsCnt update Batch Start");

		this.service.updateBatchCategoryGoodsCnt();

		System.out.println("updateBatchCategoryGoodsCnt update Batch End");
	}
}
