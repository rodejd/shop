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
		//??????
		msg = msg.replace("#{??????}", StringUtil.nvl(data.get("name")));
		msg = msg.replace("#{????????????}", StringUtil.nvl(data.get("ordno")));
		msg = msg.replace("#{????????????}", StringUtil.nvl(data.get("brandnm")));
		msg = msg.replace("#{???????????????}", StringUtil.nvl(data.get("goodsnm")));
		msg = msg.replace("#{?????????????????????}", StringUtil.nvl(data.get("optnm")));
		msg = msg.replace("#{????????????}", StringUtil.nvl(data.get("settlekind")));
		msg = msg.replace("#{??????????????????}", ShopConfig.getProperty("bs_call"));
		
		//????????????????????????
		msg = msg.replace("#{????????????}", StringUtil.nvl(data.get("deliveryno")));
		msg = msg.replace("#{????????????}", StringUtil.nvl(data.get("deliverycode")));
		//??????/?????? ??????
		msg = msg.replace("#{?????????}", StringUtil.nvl(data.get("rfee")));
		msg = msg.replace("#{??????}", StringUtil.nvl(data.get("currency")));
		msg = msg.replace("#{????????????}", StringUtil.nvl(data.get("account")));
		//??????/?????? ?????? ??????
		msg = msg.replace("#{????????????}", StringUtil.nvl(data.get("settleprice")));
		
		msg = StringUtil.replace(msg, "\\n", "\n");
			
		JSONObject params = new JSONObject();
		params.put("msgIdx", data.get("tmpltCode") + "_" + Long.toString(System.currentTimeMillis()));
		params.put("countryCode", countryCode);
		params.put("recipient", data.get("recipient"));
		params.put("senderKey", ShopConfig.getProperty("bs_skey"));
		params.put("message", msg);
		params.put("tmpltCode", data.get("tmpltCode"));
		params.put("resMethod", "PUSH");
		
		//MMS??? ?????? ????????? ???????????? ?????? ????????? ?????????
		//????????? ???????????? ?????? ??????????????? ??????????????? ??????????????? ????????? ?????? ??? ??? ??????
		/*
		params.put("useFailback", "Y");
		if ("82".equals(countryCode)) {
			JSONObject mmsAttach = new JSONObject();
			mmsAttach.put("mmsContent", msg);
			mmsAttach.put("callback", ShopConfig.getProperty("bs_call"));
			mmsAttach.put("subject", "Rizmall - ???????????? ????????????");
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
	 * ????????? ???????????? ??????
	 * @param ordno : ????????????
	 * @param tmpltCode : ????????? ??????
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> sendAlimTalk(String ordno, String tmpltCode) throws Exception {
		Map<String, Object> result = null;
		try {
			// ????????? ?????? ????????????
			ShopLibFncService shopLibFncService = null;
			shopLibFncService = (ShopLibFncService)ApplicationContextUtil.getApplicationContext().getBean("shopLibFncService");
			Map<String, Object> orderInfo = shopLibFncService.selectShopLibTalkInfo(ordno);
			
			Map<String, Object> data = new HashMap<String,Object>();
			data.put("tmpltCode", tmpltCode); // ????????? ??????
			data.put("countryCode", orderInfo.get("mobileReceiverType")); // ????????? ??????
			data.put("recipient", StringUtil.setPhone(String.valueOf(orderInfo.get("mobileReceiver")))); // ????????? ??????
			data.put("name", orderInfo.get("nameReceiver")); // ??????
			data.put("ordno", orderInfo.get("ordno")); // ????????????
			data.put("brandnm", ""); // ????????????
			data.put("goodsnm", orderInfo.get("goodsnmKR")); // ???????????????
			data.put("optnm", orderInfo.get("opt")); // ?????????????????????
			data.put("settlekind", orderInfo.get("settlekind")); // ????????????
			data.put("settleprice", orderInfo.get("prn_settleprice")); // ????????????
			data.put("rfee", orderInfo.get("prn_settleprice")); // ?????????
			data.put("deliveryno", orderInfo.get("deliverycomp")); // ????????????
			data.put("deliverycode", orderInfo.get("invoice")); // ????????????
			data.put("account", orderInfo.get("deliveryReturncomp")); // ????????????
			data.put("currency", ""); // ??????
			
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
