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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class SnsUtil {

	public static JsonNode kakao_getAccessToken(String autorize_code, String KAKAO_CLIENT_ID, String KAKAO_REDIRECT_URI) { 
		//post정보 담기
		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", KAKAO_CLIENT_ID));    // REST API KEY
		postParams.add(new BasicNameValuePair("redirect_uri", KAKAO_REDIRECT_URI));    // 리다이렉트 URI
		postParams.add(new BasicNameValuePair("code", autorize_code));    // 로그인 과정중 얻은 code 값
		
		//httpclient 빌드
		final HttpClient client = HttpClientBuilder.create().build();
		
		//해당 url로 post보낼준비
		final HttpPost post = new HttpPost(CommonConstants.KAKAO_ACCESSTOKEN_RequestUrl);
		JsonNode returnNode = null;
	
		try {
			//post정보 url과함께 보낼준비
			post.setEntity(new UrlEncodedFormEntity(postParams));
			final HttpResponse response = client.execute(post);
			//final int responseCode = response.getStatusLine().getStatusCode();
	
			//JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
		} catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
		} catch (ClientProtocolException e) {
		  e.printStackTrace();
		} catch (IOException e) {
		  e.printStackTrace();
		} finally {
			// clear resources
		}
		
		return returnNode;
	}
	
	public static JsonNode kakao_getKakaoUserInfo(String accessToken) {
		// 로그인 과정중 얻은 토큰 값 =accessToken
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(CommonConstants.KAKAO_USERINFO_RequestUrl);
		
		// add header
		post.addHeader("Authorization", "Bearer " + accessToken);
		
		JsonNode returnNode = null;
		
		try {
			final HttpResponse response = client.execute(post);
			//final int responseCode = response.getStatusLine().getStatusCode();
	
			//JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
		} catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
		} catch (ClientProtocolException e) {
		  e.printStackTrace();
		} catch (IOException e) {
		  e.printStackTrace();
		} finally {
			// clear resources
		}
		return returnNode;
	}
	
	public static JsonNode facebook_getAccessToken(String autorize_code, String FACEBOOK_CLIENT_ID, String FACEBOOK_CLIENT_Secret, String FACEBOOK_REDIRECT_URI) { 

		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("client_id", FACEBOOK_CLIENT_ID));    // REST API KEY
		postParams.add(new BasicNameValuePair("client_secret", FACEBOOK_CLIENT_Secret));    // REST API KEY
		postParams.add(new BasicNameValuePair("redirect_uri", FACEBOOK_REDIRECT_URI));    // 리다이렉트 URI
		postParams.add(new BasicNameValuePair("code", autorize_code));    // 로그인 과정중 얻은 code 값

		final HttpClient client = HttpClientBuilder.create().build();
		final HttpGet get = new HttpGet(CommonConstants.FACEBOOK_ACCESSTOKEN_RequestUrl + URLEncodedUtils.format(postParams, "UTF-8"));
		JsonNode returnNode = null;

		try {
			final HttpResponse responseget = client.execute(get);
			//final int responseCodeget = responseget.getStatusLine().getStatusCode();
			
			//JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(responseget.getEntity().getContent());
		} catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
		} catch (ClientProtocolException e) {
		  e.printStackTrace();
		} catch (IOException e) {
		  e.printStackTrace();
		} finally {
			// clear resources
		}
		
		return returnNode;
	}
	
	public static JsonNode facebook_getUserInfo(String accessToken) {
		// 로그인 과정중 얻은 토큰 값 = accessToken
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpGet get = new HttpGet(CommonConstants.FACEBOOK_USERINFO_RequestUrl + accessToken);
		JsonNode returnNode = null;

		try {
			final HttpResponse responseget = client.execute(get);
			//final int responseCodeget = responseget.getStatusLine().getStatusCode();
			
			//JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(responseget.getEntity().getContent());
		} catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
		} catch (ClientProtocolException e) {
		  e.printStackTrace();
		} catch (IOException e) {
		  e.printStackTrace();
		} finally {
			// clear resources
		}
		
		return returnNode;
	}
	
	public static JsonNode google_getAccessToken(String autorize_code, String GOOGLE_CLIENT_ID, String GOOGLE_CLIENT_Secret, String GOOGLE_REDIRECT_URI) { 

		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", GOOGLE_CLIENT_ID));    // REST API KEY
		postParams.add(new BasicNameValuePair("client_secret", GOOGLE_CLIENT_Secret));    // REST API KEY
		postParams.add(new BasicNameValuePair("redirect_uri", GOOGLE_REDIRECT_URI));    // 리다이렉트 URI
		postParams.add(new BasicNameValuePair("code", autorize_code));    // 로그인 과정중 얻은 code 값
		
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(CommonConstants.GOOGLE_ACCESSTOKEN_RequestUrl);
		JsonNode returnNode = null;

		try {
			post.setEntity(new UrlEncodedFormEntity(postParams));
			final HttpResponse response = client.execute(post);
			//final int responseCodeget = responseget.getStatusLine().getStatusCode();
			
			//JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
		} catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
		} catch (ClientProtocolException e) {
		  e.printStackTrace();
		} catch (IOException e) {
		  e.printStackTrace();
		} finally {
			// clear resources
		}
		
		return returnNode;
	}
	
	public static JsonNode google_getUserInfo(String accessToken) {
		// 로그인 과정중 얻은 토큰 값 = accessToken
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpGet get = new HttpGet(CommonConstants.GOOGLE_USERINFO_RequestUrl + accessToken);
		JsonNode returnNode = null;

		try {
			final HttpResponse responseget = client.execute(get);
			//final int responseCodeget = responseget.getStatusLine().getStatusCode();
			
			//JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(responseget.getEntity().getContent());
		} catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
		} catch (ClientProtocolException e) {
		  e.printStackTrace();
		} catch (IOException e) {
		  e.printStackTrace();
		} finally {
			// clear resources
		}
		
		return returnNode;
	}
	
	public static JsonNode naver_getAccessToken(String autorize_code,String state,String NAVER_CLIENT_ID, String NAVER_CLIENT_Secret, String NAVER_REDIRECT_URI) {
		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", NAVER_CLIENT_ID));    // REST API KEY
		postParams.add(new BasicNameValuePair("client_secret", NAVER_CLIENT_Secret));    // REST API KEY
		postParams.add(new BasicNameValuePair("redirect_uri", NAVER_REDIRECT_URI));    // 리다이렉트 URI
		postParams.add(new BasicNameValuePair("code", autorize_code));    // 로그인 과정중 얻은 code 값
		postParams.add(new BasicNameValuePair("state", state));    // 로그인 과정중 얻은 state 값
		
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(CommonConstants.NAVER_ACCESSTOKEN_RequestUrl);
		JsonNode returnNode = null;
	
		try {
			post.setEntity(new UrlEncodedFormEntity(postParams));
			final HttpResponse response = client.execute(post);
			//final int responseCode = response.getStatusLine().getStatusCode();
			
			//JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
		} catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
		} catch (ClientProtocolException e) {
		  e.printStackTrace();
		} catch (IOException e) {
		  e.printStackTrace();
		} finally {
			// clear resources
		}
		
		return returnNode;
	}
		
	public static JsonNode naver_getUserInfo(String accessToken) {
		// 로그인 과정중 얻은 토큰 값 = accessToken
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(CommonConstants.NAVER_USERINFO_RequestUrl);
		
		// add header
		post.addHeader("Authorization", "Bearer " + accessToken);
		
		JsonNode returnNode = null;
		try {
			final HttpResponse response = client.execute(post);
			//final int responseCode = response.getStatusLine().getStatusCode();
			
			//JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
		} catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
		} catch (ClientProtocolException e) {
		  e.printStackTrace();
		} catch (IOException e) {
		  e.printStackTrace();
		} finally {
			// clear resources
		}
		return returnNode;
	}
	
	public static JsonNode instargram_getAccessToken(String autorize_code) { 
		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", ShopConfig.getProperty("instagram_client_id")));    // REST API KEY
		postParams.add(new BasicNameValuePair("client_secret", ShopConfig.getProperty("instagram_client_secret")));    // REST API KEY
		postParams.add(new BasicNameValuePair("redirect_uri", ShopConfig.getProperty("instagram_domain")+ ShopConfig.getProperty("instagram_redirect_uri")));    // 리다이렉트 URI
		postParams.add(new BasicNameValuePair("code", autorize_code));    // 로그인 과정중 얻은 code 값

		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(CommonConstants.INSTAGRAM_ACCESSTOKEN_RequestUrl);
		JsonNode returnNode = null;
				
		try {
			post.setEntity(new UrlEncodedFormEntity(postParams));
			final HttpResponse response = client.execute(post);
			//final int responseCode = response.getStatusLine().getStatusCode();
 
			//JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
		} catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
		} catch (ClientProtocolException e) {
		  e.printStackTrace();
		} catch (IOException e) {
		  e.printStackTrace();
		} finally {
			// clear resources
		}
		
		return returnNode;
	}
	
	public static JsonNode apple_getUserInfo(String id_token) {
		JsonNode returnNode = null;
		
		try {
			SignedJWT signedJWT = SignedJWT.parse(id_token);
            JWTClaimsSet getPayload = signedJWT.getJWTClaimsSet();
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(getPayload.toJSONObject().toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return returnNode;
	}
}
