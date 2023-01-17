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
package com.wepinit.wepinit_shop.xmall.seller.basic.service;

import com.wepinit.wepinit_shop.xmall.admin.controller.basic.DeliveryController;
import com.wepinit.wepinit_shop.xmall.admin.dao.basic.DeliveryMapper;
import com.wepinit.wepinit_shop.xmall.common.vo.GdListDelivery;
import com.wepinit.wepinit_shop.xmall.seller.basic.dao.SellerDeliveryPolicyMapper;
import com.wepinit.wepinit_shop.xmall.seller.basic.vo.SellerDeliveryOverPolicyVO;
import com.wepinit.wepinit_shop.xmall.seller.basic.vo.SellerDeliveryPolicyInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class SellerDeliveryPolicyService {
	private static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);

	@Resource
	SellerDeliveryPolicyMapper mapper;
	
	@Resource
	DeliveryMapper deliveryMapper;
	
	public SellerDeliveryPolicyInfoVO getSellerDeliveryPolicyInfo(String sellerCd) {
		List<GdListDelivery> deliveryCompanyList = this.getDeliveryCompanyList();
		SellerDeliveryPolicyInfoVO infoVO = this.getSellerDeliveryPolicy(sellerCd);
		infoVO.setDeliveryOverPoicyList(this.getSellerDeliveryOverPolicy(sellerCd));
		infoVO.setDeliveryCompanyList(deliveryCompanyList);
		infoVO.setSellerUseDeliveryCompanyList(this.getSellerUseDeliveryCompList(infoVO.getUseDeliveryComp().split(",")));	
		return infoVO;
	}
	
	/**판매사 배송정책을 저장합니다.*/
	public void setSellerDeliveryPolicy(SellerDeliveryPolicyInfoVO infoVO) {
		this.setSellerUseDeliveryCompListParam(infoVO);
		this.updateSellerDeliveryPolicy(infoVO);
		this.deleteSellerDeliveryOverPolicy(infoVO.getSellerCd());
		this.insertSellerDeliveryOverPolicy(infoVO);
	}
	
	private void setSellerUseDeliveryCompListParam(SellerDeliveryPolicyInfoVO infoVO) {
		List<SellerDeliveryOverPolicyVO> list = new ArrayList<SellerDeliveryOverPolicyVO>();
		
		for(int i = 0; i < infoVO.getAddDeliveryPrices().length; i++) {
			SellerDeliveryOverPolicyVO vo = new SellerDeliveryOverPolicyVO();
			vo.setAddDeliveryPrice(infoVO.getAddDeliveryPrices()[i]);
			vo.setCities(infoVO.getAddDeliveryCities()[i]);
			list.add(vo);
		}
		
		infoVO.setDeliveryOverPoicyList(list);
	}
	
	public List<GdListDelivery> getDeliveryCompanyList() {
		List<GdListDelivery> deliveryList = this.deliveryMapper.getDeliveryList();
		return deliveryList == null ? Collections.emptyList() : deliveryList;
	}
	
	/**sellerCd 를 이용하여 판매사의 기본배송정책을 가져옵니다.*/
	public SellerDeliveryPolicyInfoVO getSellerDeliveryPolicy(String sellerCd) {
		SellerDeliveryPolicyInfoVO infoVO = this.mapper.getSellerDeliveryPolicy(sellerCd);
		return infoVO == null ? new SellerDeliveryPolicyInfoVO() : infoVO;
	}

	/**sellerCd 를 이용하여 판매사가 설정한 지역별 배송비정책 리스트를 가져옵니다.*/
	public List<SellerDeliveryOverPolicyVO> getSellerDeliveryOverPolicy(String sellerCd) {
		List<SellerDeliveryOverPolicyVO> list = this.mapper.getSellerDeliveryOverPolicy(sellerCd);
		return list == null ? Collections.emptyList() : list;
	}
	
	/**useDeliveryComp(판매사별 이용 택배사 코드) 를 이용하여 판매사가 이용중인 택배사 정보 리스트를 가져옵니다.*/
	public List<GdListDelivery> getSellerUseDeliveryCompList(String[] useDeliveryCompanies) {
		List<GdListDelivery> list = this.mapper.getSellerUseDeliveryCompList(useDeliveryCompanies);
		return list == null ? Collections.emptyList() : list;
	}

	/**판매사 배송정책을 저장합니다.*/
	public void updateSellerDeliveryPolicy(SellerDeliveryPolicyInfoVO infoVO) {
		this.mapper.updateSellerDeliveryPolicy(infoVO);
	}
	public void deleteSellerDeliveryOverPolicy(String sellerCd) {
		this.mapper.deleteSellerDeliveryOverPolicy(sellerCd);
	}
	public void insertSellerDeliveryOverPolicy(SellerDeliveryPolicyInfoVO infoVO) {
		this.mapper.insertSellerDeliveryOverPolicy(infoVO);
	}
}
