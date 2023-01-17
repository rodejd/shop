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
package com.wepinit.wepinit_shop.xmall.common.session;

import com.wepinit.wepinit_shop.xcube.util.SessionUtil;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;



public class AdminSessionObject {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminSessionObject.class);
	
	private UserInfo userInfo = new UserInfo();
	private Map<String,Object> sessionMap	= new HashMap<String,Object>();
	private boolean isLogin	= false;
	private final String _SESSION_KEY_ = CommonConstants._ADMIN_SESSION_KEY_;
	private final String _USER_KEY_= "ADMINUSER";
	
	public static AdminSessionObject getSessionObject(HttpServletRequest request){
		return new AdminSessionObject();
	}
	public static AdminSessionObject getSessionObject(){
		return new AdminSessionObject();
	}
	
	
	/**
	 * 태그라이브러리 > key를 이용한 사용자 정보 가져오기
	 * @param key
	 * @return
	 */
	public static String getFuncLoginValue(String key) {
		return AdminSessionObject.getSessionObject().getLoginValue(key);
	}
	
	/**
	 * 태그라이브러리 > 모든 사용자 정보 가져오기
	 * @return
	 */
	public static UserInfo getFuncUserInfo() {
		return AdminSessionObject.getSessionObject().getUserInfo();
	}
	
	
	public AdminSessionObject(){
		if(SessionUtil.getSession(_SESSION_KEY_) != null){
			this.isLogin = true;
			this.sessionMap = (Map)SessionUtil.getSession(_SESSION_KEY_);
			this.userInfo = (UserInfo)getSessionObject(_USER_KEY_);
		}
	}
	
	public void setSessionObject(UserInfo userInfo){
		this.setUserInfo(userInfo);
		this.addSessionObject(_USER_KEY_, this.userInfo);
	}
	
	private void setSession(){
		SessionUtil.setSession(_SESSION_KEY_, this.sessionMap);
	}
	
	private Map<String,Object> getSession(){
		return this.sessionMap;
	}
	
	public void addSessionObject(String key, Object value){
		this.sessionMap.put(key, value);
		this.setSession();
	}
	
	public Object getSessionObject(String key){
		return this.sessionMap.get(key);
	}
	
	public UserInfo getUserInfo() { 
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void removeSession() {
		SessionUtil.removeSession(_SESSION_KEY_);
	}
	
	public String getLoginValue(String key) {
		String val = "";
		
		if ( null != userInfo ) {
			switch(key){
			case "USER_ID":		// 아이디
				val = userInfo.getUserId();
				break;
			case "USER_NAME":	// 이름
				val = userInfo.getUserName();
				break;
			case "M_NO":		// 회원고유번호
				val = String.valueOf(userInfo.getM_no());
				break;
			case "SELLER_CD":	// 판매사 코드
				val = userInfo.getSellerCd();
				break;
			}
		}
		
		return val;
	}
	
	
	
}
