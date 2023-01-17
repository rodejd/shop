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
package com.wepinit.wepinit_shop.xmall.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.service.ShopLibFncService;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class BiztalkUtil {
	
	private static Logger logger = LoggerFactory.getLogger(BiztalkUtil.class);
	
	private String token = "";
	
	public BiztalkUtil() {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(CommonConstants.BIZTALK_ACCESSTOKEN_RequestUrl);
		
		JSONObject params = new JSONObject();
		params.put("bsid", ShopConfig.getProperty("bs_id"));
		params.put("passwd", ShopConfig.getProperty("bs_pw"));
		String jsonData = params.toString();
		
		Map<String, Object> result = null;
		try {
			post.setEntity(new StringEntity(jsonData, ContentType.APPLICATION_JSON));
			HttpResponse response = client.execute(post);
	
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(response.getEntity().getContent());
			result = mapper.convertValue(jsonNode, new TypeReference<Map<String, Object>>(){});
		} catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
		} catch (ClientProtocolException e) {
		  e.printStackTrace();
		} catch (IOException e) {
		  e.printStackTrace();
		} finally {
			if (!"".equals(result.get("token")))
			this.token = String.valueOf(result.get("token"));
		}
	}
	
	public String setParams(Map<String, Object> data) {
		String countryCode = "";
		if (!"".equals(data.get("countryCode"))) {
			countryCode = StringUtil.nvl(data.get("countryCode")).replace("+", "");
		}

		String msg = ShopConfig.getProperty("bs_" + data.get("tmpltCode") + "_msg");
		//공통
		msg = msg.replace("#{이름}", StringUtil.nvl(data.get("name")));
		msg = msg.replace("#{주문번호}", StringUtil.nvl(data.get("ordno")));
		msg = msg.replace("#{브랜드명}", StringUtil.nvl(data.get("brandnm")));
		msg = msg.replace("#{주문상품명}", StringUtil.nvl(data.get("goodsnm")));
		msg = msg.replace("#{주문상품옵션명}", StringUtil.nvl(data.get("optnm")));
		msg = msg.replace("#{결제수단}", StringUtil.nvl(data.get("settlekind")));
		msg = msg.replace("#{고객센터번호}", ShopConfig.getProperty("bs_call"));
		
		//주문상품발송안내
		msg = msg.replace("#{배송사명}", StringUtil.nvl(data.get("deliveryno")));
		msg = msg.replace("#{송장번호}", StringUtil.nvl(data.get("deliverycode")));
		//취소/반품 안내
		msg = msg.replace("#{반품비}", StringUtil.nvl(data.get("rfee")));
		msg = msg.replace("#{통화}", StringUtil.nvl(data.get("currency")));
		msg = msg.replace("#{계좌번호}", StringUtil.nvl(data.get("account")));
		//취소/환불 완료 안내
		msg = msg.replace("#{주문금액}", StringUtil.nvl(data.get("settleprice")));
		
		msg = StringUtil.replace(msg, "\\n", "\n");
			
		JSONObject params = new JSONObject();
		params.put("msgIdx", data.get("tmpltCode") + "_" + Long.toString(System.currentTimeMillis()));
		params.put("countryCode", countryCode);
		params.put("recipient", data.get("recipient"));
		params.put("senderKey", ShopConfig.getProperty("bs_skey"));
		params.put("message", msg);
		params.put("tmpltCode", data.get("tmpltCode"));
		params.put("resMethod", "PUSH");
		
		//MMS는 국내 발송만 가능하며 해외 발송이 불가능
		//사전에 비즈톡을 통해 회신번호를 등록하여야 정상적으로 문자를 발송 할 수 있다
		/*
		params.put("useFailback", "Y");
		if ("82".equals(countryCode)) {
			JSONObject mmsAttach = new JSONObject();
			mmsAttach.put("mmsContent", msg);
			mmsAttach.put("callback", ShopConfig.getProperty("bs_call"));
			mmsAttach.put("subject", "Rizmall - 이탈리아 명품직구");
			params.put("mmsAttach", mmsAttach);
		}
		*/
		
		JSONArray button = new JSONArray();
		String[] btns = StringUtil.split(ShopConfig.getProperty("bs_" + data.get("tmpltCode") + "_btn"), "^");
		for (String btn : btns) {
			String[] btnInfo = StringUtil.split(btn, "|");
			
			JSONObject bwl = new JSONObject();
			bwl.put("name", btnInfo[0]);
			bwl.put("type", btnInfo[1]);
			bwl.put("url_mobile", btnInfo[2]);
			bwl.put("url_pc", btnInfo[3]);
			button.put(bwl);
		}
		
		if (button.length() > 0) {
			JSONObject attach = new JSONObject();
			attach.put("button", button);
			params.put("attach", attach);
		}
		
		return params.toString();
	}
	
	public Map<String, Object> sendAlimTalk(Map<String, Object> data) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(CommonConstants.BIZTALK_SENDALIMTALK_RequestUrl);
		post.addHeader("bt-token", this.token);
		
		Map<String, Object> result = null;
		try {
			if (!"".equals(data.get("tmpltCode")) && !"".equals(data.get("countryCode")) && !"".equals(data.get("recipient"))) {
				String jsonData = setParams(data);				
				if (logger.isDebugEnabled()) {
					logger.debug("biztalk params >> " + jsonData);
				}
				
				post.setEntity(new StringEntity(jsonData, ContentType.APPLICATION_JSON));
				HttpResponse response = client.execute(post);
				
				ObjectMapper mapper = new ObjectMapper();
				JsonNode jsonNode = mapper.readTree(response.getEntity().getContent());
				result = mapper.convertValue(jsonNode, new TypeReference<Map<String, Object>>(){});
			}
		} catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
		} catch (ClientProtocolException e) {
		  e.printStackTrace();
		} catch (IOException e) {
		  e.printStackTrace();
		} finally {
		}
		
		return result;
	}
	
	/**
	 * 알림톡 발송정보 설정
	 * @param ordno : 주문번호
	 * @param tmpltCode : 템플릿 코드
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> sendAlimTalk(String ordno, String tmpltCode) throws Exception {
		Map<String, Object> result = null;
		try {
			// 비즈톡 발송 정보조회
			ShopLibFncService shopLibFncService = null;
			shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
			Map<String, Object> orderInfo = shopLibFncService.selectShopLibTalkInfo(ordno);
			
			Map<String, Object> data = new HashMap<String,Object>();
			data.put("tmpltCode", tmpltCode); // 템플릿 코드
			data.put("countryCode", orderInfo.get("mobileReceiverType")); // 휴대폰 구분
			data.put("recipient", StringUtil.setPhone(String.valueOf(orderInfo.get("mobileReceiver")))); // 휴대폰 번호
			data.put("name", orderInfo.get("nameReceiver")); // 이름
			data.put("ordno", orderInfo.get("ordno")); // 주문번호
			data.put("brandnm", ""); // 브랜드명
			data.put("goodsnm", orderInfo.get("goodsnmKR")); // 주문상품명
			data.put("optnm", orderInfo.get("opt")); // 주문상품옵션명
			data.put("settlekind", orderInfo.get("settlekind")); // 결제수단
			data.put("settleprice", orderInfo.get("prn_settleprice")); // 주문금액
			data.put("rfee", orderInfo.get("prn_settleprice")); // 반품비
			data.put("deliveryno", orderInfo.get("deliverycomp")); // 배송사명
			data.put("deliverycode", orderInfo.get("invoice")); // 송장번호
			data.put("account", orderInfo.get("deliveryReturncomp")); // 계좌번호
			data.put("currency", ""); // 통화
			
			result = sendAlimTalk(data);
			if (logger.isDebugEnabled()) {
				logger.debug("biztalk result >> " + result);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
