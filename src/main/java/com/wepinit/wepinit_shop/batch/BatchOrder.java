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

import com.wepinit.wepinit_shop.batch.service.BatchOrderService;
import com.wepinit.wepinit_shop.batch.vo.BatchOrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Component
public class BatchOrder {
	@Resource
	BatchOrderService service;

	private static final Logger logger = LoggerFactory.getLogger(BatchOrder.class);
	
	/**
	 * 입금 기한이 지난 주문건의 주문 취소가 지정된 스케줄에 따라 진행됩니다.
	 * */
	/*
	@Scheduled(cron="0 50 23 * * * ")
	public void batchOrderCancel() throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("BatchOrder >> batchOrderCancel() "); 
		}
		this.service.updateDepositExceedPeriodOrderCancel();
	}
	*/
	
	/**
	 * 상품이미지가 없는경우 비게시처리
	 * @throws Exception
	 */
	@Scheduled(cron="0 00 02 * * * ")
	public void goodsImgCheckBatch() throws Exception {
		System.out.println("Goods Img Check Batch Start");
		List<BatchOrderVO> orderList = this.service.selectBatchGoodsList();
		System.out.println("Goods Img Check Batch Start [List Size] : " + orderList.size());
		
		if(orderList != null) {
			for(BatchOrderVO batchVO : orderList) {
				int failCnt = 0;
				
				failCnt = callUrlImg(batchVO.getImgI());
				if(failCnt == 0) failCnt = callUrlImg(batchVO.getImgS());
				if(failCnt == 0) failCnt = callUrlImg(batchVO.getImgL());
				
				//이미지 3개 다 있을경우
				if(failCnt == 0) {
					batchVO.setOpen("1");
					batchVO.setBatchYn("Y");
					
				//이미지가 하나라도 없을경우 비게시처리
				} else {
					batchVO.setOpen("0");
					batchVO.setBatchYn("N");
					
					this.service.insertBatchGoodsImgLog(batchVO);
				}
				this.service.updateBatchGoods(batchVO);
			}
		}
		
		/**
		 * 이미지는 있으나, Connection Time Out으로 비게시처리된 게시물때문에
		 * 오늘날짜로 로그테이블에 등록된 데이터로 한번 게시&비게시 처리
		 */
		orderList = this.service.selectBatchGoodsListByLog();
		if(orderList != null) {
			for(BatchOrderVO batchVO : orderList) {
				int failCnt = 0;
				
				failCnt = callUrlImg(batchVO.getImgI());
				if(failCnt == 0) failCnt = callUrlImg(batchVO.getImgS());
				if(failCnt == 0) failCnt = callUrlImg(batchVO.getImgL());
				
				//이미지 3개 다 있을경우
				if(failCnt == 0) {
					batchVO.setOpen("1");
					batchVO.setBatchYn("Y");
					
				//이미지가 하나라도 없을경우 비게시처리
				} else {
					batchVO.setOpen("0");
					batchVO.setBatchYn("N");
				}
				this.service.updateBatchGoods(batchVO);
			}
		}
		
		System.out.println("Goods Img Check Batch End");
	}
	
	/**
	 * 이미지 유무체크
	 * @param urlImg
	 * @return
	 * @throws IOException 
	 */
	public int callUrlImg(String urlImg) throws IOException {
		int result = 0;
		HttpURLConnection con  = null;
		try {
			HttpURLConnection.setFollowRedirects(false);
			con = (HttpURLConnection) new URL(urlImg.replaceAll("\\|", "")).openConnection();
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.setRequestMethod("HEAD");
			
			if( con.getResponseCode() != 200 ) {
				result++;
			}
			
//			System.out.println("SUCCESS :::: urlImg : " + urlImg + " / ResponseCode " + con.getResponseCode());
		}catch (Exception e) {
//			System.out.println("ERROR :::: urlImg : " + urlImg + " / ResponseCode " + con.getResponseCode());
			e.printStackTrace();
			
			result++;
		}finally {
			if( con != null ) {
				con.disconnect();
			}
		}
		
		return result;
	}
}
