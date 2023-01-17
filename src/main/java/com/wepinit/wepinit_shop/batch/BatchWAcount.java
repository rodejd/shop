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

import com.wepinit.wepinit_shop.batch.service.BatchAccountService;
import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class BatchWAcount {

	@Resource
	BatchAccountService service;
	
	private static final Logger logger = LoggerFactory.getLogger(BatchWAcount.class);
	
	/**
	 * 판매사 주정산 주 위한 배치
	 * */
	@Scheduled(cron="0 0/1 05 * * ?") //@Scheduled(cron="0 0 01 * * *") 새벽1시
	public void BatchAcountCal() throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("BatchWAcount >>>>>>>>>>>>>>>>>>>>>>>>>>>> "); 
		}
		
		/*
		# gd_order_item의 istep값이 '4'(배송완료)값을 체크
		1. 주 : 일~토요일  다음주 일요일 새벽 1시에 정산
		2. 15일 : 1~15일:22일새벽1시에 정산 , 16~월말: 익월 7일 새벽1시에 정산
		3. 월정산 : 1~월말:익월 새벽1시 정산(7일)		 
				 
		 쿼리는 1개 조건은 
		 - 배송완료
		 - 시작일 ~ 종료일		 
		 */		
		SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");		
		String sTodate = fm.format(new Date());
		//요일정보 출력
		String s = DateUtil.getDayOfWeek(sTodate, "ko");
		//해당월의 마직막일자
		String sLDay = DateUtil.getLastDay(sTodate.substring(0, 4),sTodate.substring(4, 6));
		//전달 마지막 일자 조회
		String sPMDay = DateUtil.addDays(sTodate.substring(0, 6)+"01", -1);
		//전전일요일
		String sdate = DateUtil.addDays(sTodate, -14);
		//전전토요일
		String edate = DateUtil.addDays(sTodate, -8);
		
		logger.debug("##YYYY## "+sTodate.substring(0, 4)+"##MM##"+sTodate.substring(4, 6)+"##DD##"+sTodate.substring(6, 8));
		logger.debug("DateUtil.getLastDay ##### "+sLDay);
		logger.debug("DateUtil.getDayOfWeek ##### "+s);
		logger.debug("DateUtil.addDays ##### "+sPMDay);
		logger.debug("DateUtil.sdate ##### "+sdate);
		logger.debug("DateUtil.edate ##### "+edate);
		if("일".equals(s)) {		
		//	this.service.goodsAccountCal(sdate,edate,"W");
		}
		
	}


	
}
