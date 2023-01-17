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
package com.wepinit.wepinit_shop.xmall.admin.controller.order;

import com.wepinit.wepinit_shop.xmall.admin.service.order.PopupOrderService;
import com.wepinit.wepinit_shop.xmall.admin.service.order.SweetTrackerService;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@RequestMapping(value="/shop/admin/sweetTracker/*")
@Controller
public class SweetTrackerController {
	
	private static final Logger logger = LoggerFactory.getLogger(PopupOrderService.class);
	
	@Resource
	SweetTrackerService service;
	/*
		deliverycode = t_invoice = 택배 송장번호
		
		deliveryno = t_code = 스윗트래커 측에 등록되어 있는 택배사 코드.
					DB 의 gd_list_delivery 테이블에 sweetTracker 측의 코드와 일치하도록 코드와 택배서 정보 업데이트 하였습니다.
		
		sweetTrackerAPIKey = t_key = 스윗트래커 측에 회원가입 후 발급받은 API Key.
							DB의 gd_conf_set 에 'sweetTrackerAPIKey' 를 key 값으로 등록되어 있습니다.
							무료 계정은 월 1000 건 까지만 조회가 가능합니다.
							같은 송장번호로 하루 20회 까지만 조회가 가능합니다.
		
		sweetTrackerBaseURL = DB 의 gd_conf_set 에 'sweetTrackerBaseURL' 을 key 값으로 등록되어 있습니다.
							 스윗트래커 측의 기본 URL 주소가 저장되어 있습니다.
		
		* 스윗트래커 제공 서비스 이용 tip *
		- 서비스의 requestSweetTrackerAndReturnJson 메소드에 
		    첫번째 파라미터로 아래의 업무 중 원하는 업무를 String 으로, 
		    두번째 파라미터로 필요 parameter 를 Map 에 세팅하여 넘기면 요청 결과가 JSON 형태로 return 됩니다.
		- recommend 는 미구현 상태이니 필요에 따라 구현하여 주십시오.
		
		[trackingInfo] 운송장 번호 조회
			parameters : t_key, t_code, t_invoice
		[recommend] 운송장 번호로 추천 택배사 목록 조회
			parameters : t_key, t_invoice
		[companylist] 스윗트래커에 등록되어 있는 모든 택배사 목록 조회
			parameters : t_key
		
		참고 (스윗트래커 API) : http://info.sweettracker.co.kr/apidoc
	*/
	@RequestMapping(value="trackingInfo")
	public String trackingInfo(
			@RequestParam(value="deliverycode") String t_invoice, 
			@RequestParam(value="deliveryno") String t_code,
			Model model) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("SweetTrackerController >> trackingInfo()");
		}
		
		JSONObject trackingInfo = this.service.invoiceTracking(t_invoice, t_code);
		
		model.addAttribute("trackingInfo", trackingInfo);
		
		return "/shop/admin/order/trackingInfo";
	}
	
	@RequestMapping(value="companylist")
	public String companyList() throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("SweetTrackerController >> companylist()");
		}
		
		this.service.updateDeliveryCompanies();

		return "redirect:/shop/admin/basic/delivery";
	}
}
