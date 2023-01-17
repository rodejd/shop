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
package com.wepinit.wepinit_shop.xmall.admin.service.order;

import com.wepinit.wepinit_shop.xcube.util.HttpUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.dao.basic.DeliveryMapper;
import com.wepinit.wepinit_shop.xmall.admin.dao.basic.SweetTrackerMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.order.OrderDeliveryVO;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.vo.GdListDelivery;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class SweetTrackerService {
	
	private static final Logger logger = LoggerFactory.getLogger(PopupOrderService.class);
	
	@Resource
	SweetTrackerMapper mapper;
	
	@Resource
	DeliveryMapper deliveryMapper;
	
	
	/**송장번호외 택배사코드로 배송정보를 조회합니다.
	 * @param
	 * @return JSONObject
	 * @exception
	 * */
	public JSONObject invoiceTracking(String t_invoice, String t_code) throws Exception {
		logger.debug("params t_code >> " + t_code);
		logger.debug("params t_invoice >> " + t_invoice);
		
		JSONObject trackingInfo = new JSONObject();
		
		if (StringUtil.N2B(t_code) || StringUtil.N2B(t_invoice)) {
			trackingInfo.put("status", false);
			trackingInfo.put("msg", "배송정보 저장 후 이용해주시기 바랍니다.");
		} else {
			
			Map<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("t_key", ShopConfig.getProperty("sweetTrackerAPIKey"));
			paramsMap.put("t_invoice", t_invoice);
			paramsMap.put("t_code", t_code);
			
			trackingInfo = this.requestSweetTrackerAndReturnJson("trackingInfo", paramsMap);
			trackingInfo.put("deliveryComp", this.getDeliveryCompName(paramsMap.get("t_code")));
			
			

			// 배송조회 결과 update 용 vo 데이터 세팅
			OrderDeliveryVO deliveryVO = new OrderDeliveryVO();
			deliveryVO.setInvoice(t_invoice);
			deliveryVO.setDeliveryCompCd(t_code);
			
			this.updateDeliveryStatus(trackingInfo, deliveryVO);
		}
		return trackingInfo;
	}
	
	/**송장번호외 택배사코드로 배송정보를 조회합니다.
	 * @param
	 * @exception
	 * */
	public void updateDeliveryCompanies() throws Exception {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("t_key", ShopConfig.getProperty("sweetTrackerAPIKey"));
		
		JSONObject companies = this.requestSweetTrackerAndReturnJson("companylist", paramsMap);
		
		this.insertOrUpdateDeliveryCompanyList((List<Map<String, Object>>)companies.get("Company"));
		
	}
	
	/**스윗트래커에 요청할 업무 명(String)과 필요한 파라미터(Map)를 넘겨주면 업무에 대한 결과가 json 으로 반환됩니다.
	 * @param
	 * @return JSONObject
	 * @throws Exception 
	 * */
	public JSONObject requestSweetTrackerAndReturnJson(String work, Map<String, String> params) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("SweetTrackerService >> requestSweetTrackerAndReturnJson()");
		}
		
		String url = ShopConfig.getProperty("sweetTrackerBaseURL") + work;
		params.put("CONNECTION_URL", url);
		params.put("METHOD_TYPE", "GET");
		params.put("RETURN_TYPE", "JSON");
		
		Object obj = HttpUtil.executeHttp(params); 

		JSONObject jsonObj = (JSONObject) obj; 
		
		if (jsonObj == null) {
			jsonObj = new JSONObject();
			jsonObj.put("status", false);
			jsonObj.put("msg", "스윗트래커 계정의 조회기간이 만료되었습니다.\n관리자에게 문의하여주시기 바랍니다.");
		}
		
		return jsonObj;
	}
	
	/**DB의 배송사 정보 테이블(gd_list_delivery)에서 deliveryNo 로 배송사 이름을 가져옵니다.
	 * @param
	 * @return String
	 * */
	private String getDeliveryCompName(String deliveryNo) {
		if (logger.isDebugEnabled()) {
			logger.debug("SweetTrackerService >> getDeliveryCompName()");
		}
		
		List<GdListDelivery> deliveryCompanies = this.deliveryMapper.getPopupDeliveryList(deliveryNo);
		
		if (deliveryCompanies.size() == 0) {
			return "";
		}
		
		return deliveryCompanies.get(0).getDeliverycomp();
	}
	
	/**DB의 배송사 정보를 스윗트래커 측에서 받아와 update 합니다.	
	 * @param
	 * */
	private void insertOrUpdateDeliveryCompanyList(List<Map<String, Object>> companies) {
		this.mapper.insertOrUpdateDeliveryCompanyList(companies);
	}
	
	/** 배송조회 후 결과를 업데이트 
	 * @param
	 * */
	private void updateDeliveryStatus(JSONObject trackingInfo, OrderDeliveryVO deliveryVO) {
		
		// 스윗트래커 조회 결과가 실패인 경우
		if (!isSearchSuccess(trackingInfo)) {
			deliveryVO.setDeliveryStatus("조회 불가");
		} else {
			JSONObject lastDetail = (JSONObject)trackingInfo.get("lastDetail");
			deliveryVO.setDeliveryStatus((String)lastDetail.get("kind"));
			deliveryVO.setDeliveryEdt((String)lastDetail.get("timeString")); 
			deliveryVO.setLevel(String.valueOf(lastDetail.get("level")));
		}
		
		this.mapper.updateDeliveryStatus(deliveryVO);
	}
	
	private boolean isSearchSuccess(JSONObject trackingInfo) {
		if (null == trackingInfo.get("status")) {
			return trackingInfo.get("lastDetail") != null;
		} else {
			return (Boolean)trackingInfo.get("status");
		}
	}

}
