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

import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;



public class ShopSessionObject {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopSessionObject.class);
	
	private HttpSession session;
	private UserInfo userInfo = new UserInfo();
	private Map<String,Object> sessionMap	= new HashMap<String,Object>();
	private boolean isShopLogin	= false;
	private final String _SESSION_KEY_ = CommonConstants._FRONT_SESSION_KEY_;
	private final String _USER_KEY_= "SHOPUSER";
	
	public static ShopSessionObject getSessionObject(HttpServletRequest request){
		return new ShopSessionObject(request);
	}
	
	public ShopSessionObject(HttpServletRequest request){
		session = request.getSession();
		if(session.getAttribute(_SESSION_KEY_) != null){
			this.isShopLogin = true;
			this.sessionMap = this.getSession();
			this.userInfo = (UserInfo)getSessionObject(_USER_KEY_);
		}
	}
	
	public void setSessionObject(UserInfo userInfo){
		this.setUserInfo(userInfo);
		this.setShopLogin(true);
		this.addSessionObject(_USER_KEY_, this.userInfo);
	}
	
	private void setSession(){
		session.setAttribute(_SESSION_KEY_, this.sessionMap);
	}
	
	private Map<String,Object> getSession(){
		return this.sessionMap = (Map<String,Object>)session.getAttribute(_SESSION_KEY_);
	}
	
	public void addSessionObject(String key, Object value){
		this.sessionMap.put(key, value);
		this.setSession();
	}
	
	public Object getSessionObject(String key){
		return this.sessionMap.get(key);
	}
	
	public UserInfo getUserInfo() { 
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public boolean isShopLogin() {
		return isShopLogin;
	}

	public void setShopLogin(boolean isShopLogin) {
		this.isShopLogin = isShopLogin;
	}

	public void removeSession() {
		this.session.removeAttribute(_SESSION_KEY_);
	}
	
}
