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
package com.wepinit.wepinit_shop.xmall.admin.vo.basic;

import com.wepinit.wepinit_shop.xmall.common.PageMaker;

import java.util.Map;


public class DefaultVO  extends PageMaker {
	//input
	private String shopName;
	private String shopEng;
	private String adminEmail;
	private String shopUrl;
	private String compName;
	private String service;
	private String item;
	private String zipcode;
	private String address;
	private String compSerial;
	private String orderSerial;
	private String ceoName;
	private String adminName;
	private String compPhone;
	private String compFax;
	private String title;
	private String keywords;
	private String sessTime;

	private String kakao_client_id;
	private String kakao_client_secret;
	private String kakao_domain;
	private String kakao_redirect_uri;
	
	private String naver_client_id;
	private String naver_client_secret;
	private String naver_domain;
	private String naver_redirect_uri;
   
    private String facebook_client_id; 
    private String facebook_client_secret; 
    private String facebook_domain;
    private String facebook_redirect_uri; 
  
    private String instagram_client_id;
    private String instagram_client_secret;
    private String instagram_domain;
    private String instagram_redirect_uri; 

    private String google_client_id; 
    private String google_client_secret; 
    private String google_domain;
    private String google_redirect_uri; 
   
    private String apple_client_id; 
    private String apple_team_id; 
    private String apple_key_id; 
    private String apple_domain;
    private String apple_redirect_uri; 
	
    
	private Map<String, String> dbData;
	
	
	public Map<String, String> getDbData() {
		return dbData;
	}
	public void setDbData(Map<String, String> dbData) {
		this.dbData = dbData;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopEng() {
		return shopEng;
	}
	public void setShopEng(String shopEng) {
		this.shopEng = shopEng;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getShopUrl() {
		return shopUrl;
	}
	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCompSerial() {
		return compSerial;
	}
	public void setCompSerial(String compSerial) {
		this.compSerial = compSerial;
	}
	public String getOrderSerial() {
		return orderSerial;
	}
	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}
	public String getCeoName() {
		return ceoName;
	}
	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getCompPhone() {
		return compPhone;
	}
	public void setCompPhone(String compPhone) {
		this.compPhone = compPhone;
	}
	public String getCompFax() {
		return compFax;
	}
	public void setCompFax(String compFax) {
		this.compFax = compFax;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getSessTime() {
		return sessTime;
	}
	public void setSessTime(String sessTime) {
		this.sessTime = sessTime;
	}
	
	public String getKakao_client_id() {
		return kakao_client_id;
	}
	public void setKakao_client_id(String kakao_client_id) {
		this.kakao_client_id = kakao_client_id;
	}
	public String getKakao_client_secret() {
		return kakao_client_secret;
	}
	public void setKakao_client_secret(String kakao_client_secret) {
		this.kakao_client_secret = kakao_client_secret;
	}
	public String getKakao_redirect_uri() {
		return kakao_redirect_uri;
	}
	public void setKakao_redirect_uri(String kakao_redirect_uri) {
		this.kakao_redirect_uri = kakao_redirect_uri;
	}
	
	public String getNaver_client_id() {
		return naver_client_id;
	}
	public void setNaver_client_id(String naver_client_id) {
		this.naver_client_id = naver_client_id;
	}
	public String getNaver_client_secret() {
		return naver_client_secret;
	}
	public void setNaver_client_secret(String naver_client_secret) {
		this.naver_client_secret = naver_client_secret;
	}
	public String getNaver_redirect_uri() {
		return naver_redirect_uri;
	}
	public void setNaver_redirect_uri(String naver_redirect_uri) {
		this.naver_redirect_uri = naver_redirect_uri;
	}
	
	public String getFacebook_client_id() {
		return facebook_client_id;
	}
	public void setFacebook_client_id(String facebook_client_id) {
		this.facebook_client_id = facebook_client_id;
	}
	public String getFacebook_client_secret() {
		return facebook_client_secret;
	}
	public void setFacebook_client_secret(String facebook_client_secret) {
		this.facebook_client_secret = facebook_client_secret;
	}
	public String getFacebook_redirect_uri() {
		return facebook_redirect_uri;
	}
	public void setFacebook_redirect_uri(String facebook_redirect_uri) {
		this.facebook_redirect_uri = facebook_redirect_uri;
	}

	public String getInstagram_client_id() {
		return instagram_client_id;
	}
	public void setInstagram_client_id(String instagram_client_id) {
		this.instagram_client_id = instagram_client_id;
	}
	public String getInstagram_client_secret() {
		return instagram_client_secret;
	}
	public void setInstagram_client_secret(String instagram_client_secret) {
		this.instagram_client_secret = instagram_client_secret;
	}
	public String getInstagram_redirect_uri() {
		return instagram_redirect_uri;
	}
	public void setInstagram_redirect_uri(String instagram_redirect_uri) {
		this.instagram_redirect_uri = instagram_redirect_uri;
	}
	
	public String getKakao_domain() {
		return kakao_domain;
	}
	public void setKakao_domain(String kakao_domain) {
		this.kakao_domain = kakao_domain;
	}
	public String getNaver_domain() {
		return naver_domain;
	}
	public void setNaver_domain(String naver_domain) {
		this.naver_domain = naver_domain;
	}
	public String getFacebook_domain() {
		return facebook_domain;
	}
	public void setFacebook_domain(String facebook_domain) {
		this.facebook_domain = facebook_domain;
	}
	public String getInstagram_domain() {
		return instagram_domain;
	}
	public void setInstagram_domain(String instagram_domain) {
		this.instagram_domain = instagram_domain;
	}
	
	public String getGoogle_client_id() {
		return google_client_id;
	}
	public void setGoogle_client_id(String google_client_id) {
		this.google_client_id = google_client_id;
	}
	public String getGoogle_client_secret() {
		return google_client_secret;
	}
	public void setGoogle_client_secret(String google_client_secret) {
		this.google_client_secret = google_client_secret;
	}
	public String getGoogle_domain() {
		return google_domain;
	}
	public void setGoogle_domain(String google_domain) {
		this.google_domain = google_domain;
	}
	public String getGoogle_redirect_uri() {
		return google_redirect_uri;
	}
	public void setGoogle_redirect_uri(String google_redirect_uri) {
		this.google_redirect_uri = google_redirect_uri;
	}
	public String getApple_client_id() {
		return apple_client_id;
	}
	public void setApple_client_id(String apple_client_id) {
		this.apple_client_id = apple_client_id;
	}
	public String getApple_team_id() {
		return apple_team_id;
	}
	public void setApple_team_id(String apple_team_id) {
		this.apple_team_id = apple_team_id;
	}
	public String getApple_key_id() {
		return apple_key_id;
	}
	public void setApple_key_id(String apple_key_id) {
		this.apple_key_id = apple_key_id;
	}
	public String getApple_domain() {
		return apple_domain;
	}
	public void setApple_domain(String apple_domain) {
		this.apple_domain = apple_domain;
	}
	public String getApple_redirect_uri() {
		return apple_redirect_uri;
	}
	public void setApple_redirect_uri(String apple_redirect_uri) {
		this.apple_redirect_uri = apple_redirect_uri;
	}
	@Override
	public String toString() {
		return "DefaultVO [shopName=" + shopName + ", shopEng=" + shopEng
				+ ", adminEmail=" + adminEmail + ", shopUrl=" + shopUrl
				+ ", compName=" + compName + ", service=" + service + ", item="
				+ item + ", zipcode=" + zipcode + ", address=" + address
				+ ", compSerial=" + compSerial + ", orderSerial=" + orderSerial
				+ ", ceoName=" + ceoName + ", adminName=" + adminName
				+ ", compPhone=" + compPhone + ", compFax=" + compFax
				+ ", title=" + title + ", keywords=" + keywords + ", sessTime="
				+ sessTime + ", kakao_client_id=" + kakao_client_id
				+ ", kakao_client_secret=" + kakao_client_secret
				+ ", kakao_domain=" + kakao_domain + ", kakao_redirect_uri="
				+ kakao_redirect_uri + ", naver_client_id=" + naver_client_id
				+ ", naver_client_secret=" + naver_client_secret
				+ ", naver_domain=" + naver_domain + ", naver_redirect_uri="
				+ naver_redirect_uri + ", facebook_client_id="
				+ facebook_client_id + ", facebook_client_secret="
				+ facebook_client_secret + ", facebook_domain="
				+ facebook_domain + ", facebook_redirect_uri="
				+ facebook_redirect_uri + ", instagram_client_id="
				+ instagram_client_id + ", instagram_client_secret="
				+ instagram_client_secret + ", instagram_domain="
				+ instagram_domain + ", instagram_redirect_uri="
				+ instagram_redirect_uri + ", dbData=" + dbData + "]";
	}
}
