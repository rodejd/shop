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
package com.wepinit.wepinit_shop.xmall.admin.service.basic;

import com.wepinit.wepinit_shop.xmall.admin.controller.basic.DeliveryController;
import com.wepinit.wepinit_shop.xmall.admin.dao.basic.DeliveryMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.basic.DeliveryVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdDeliveryPolicy;
import com.wepinit.wepinit_shop.xmall.common.vo.GdListDelivery;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class DeliveryService {
	private static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);
	
	@Resource
	DeliveryMapper mapper;
	public List<GdListDelivery> getDeliveryList(){
		return mapper.getDeliveryList();
	}
	public List<GdDeliveryPolicy> getDeliveryPolicyList(){
		return mapper.getDeliveryPolicyList();
	}
	public void setDeliveryListUseyn(String deliveryno, String useyn){
		mapper.setDeliveryListUseyn(deliveryno,useyn);
	}
	public void initDeliveryPolicy(){
		mapper.initDeliveryPolicy();
	}
	public void setDeliveryPolicy(int no ,String r_delivery, String r_free, String r_deliType,String r_default, String r_default_msg){
		mapper.insertDeliveryPolicy(no,r_delivery, r_free, r_deliType, r_default, r_default_msg);
	}
	
	public List<GdListDelivery> getPopupDeliveryList(String no){
		return mapper.getPopupDeliveryList(no);
	}
	public void setDeliveryListCompAndUrl(DeliveryVO deliveryVO){
		mapper.setDeliveryListCompAndUrl(deliveryVO);
	}
	
	// 2017-08-23 : 택배사 등록을 위하여 추가
	public void insertDeliveryListCompAndUrl(DeliveryVO deliveryVO) {
		this.mapper.insertDeliveryListCompAndUrl(deliveryVO);
	}
	
	// 2017-08-23 : 택배사 삭제를 위하여 추가
	public void deleteCourier(@Param("deliveryno")String deliveryno) {
		this.mapper.deleteCourier(deliveryno);
	}
	
	
	/*	2017-08-23 : 택배사 등록을 위하여 추가
		관리자가 하려는 작업이 택배사 등록인지 수정인지 판단하여 insert 혹은 update 를 진행한다.*/
	public void insertOrModifyGdListDelivery(DeliveryVO deliveryVO) {
		String mode = deliveryVO.getMode();
		if(mode.equals("registerDelivery")) {
			mapper.insertDeliveryListCompAndUrl(deliveryVO);
		} else if(mode.equals("modifyDelivery")) {
			mapper.setDeliveryListCompAndUrl(deliveryVO);
		}
	}

	public int deliverynoCheck(@Param("deliveryno") String deliveryno) {
		 GdListDelivery noCheckResult = this.mapper.deliverynoCheck(deliveryno);
		 int result = 0;
		 if (noCheckResult != null) {
			result = 1;
		 }
		 logger.debug(" deliverynoCheck result >> "+result);
		 return result;
	 }	
}
