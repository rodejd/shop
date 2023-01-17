package com.wepinit.wepinit_shop.xcube.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
	
public class SessionUtil {
	
	public static boolean isLogin(){
		boolean isLogin = false;
		
		if (null != RequestContextHolder.getRequestAttributes().getAttribute("", RequestAttributes.SCOPE_SESSION) ) {
			isLogin = true;
		}
		
		return isLogin;
	}
	
	public static void setSession(String key, Object obj){
		RequestContextHolder.getRequestAttributes().setAttribute(key, obj, RequestAttributes.SCOPE_SESSION);
	}
	
	public static Object getSession(String key){
		return RequestContextHolder.getRequestAttributes().getAttribute(key, RequestAttributes.SCOPE_SESSION);
	}
	
	public static void removeSession(String key){
		RequestContextHolder.getRequestAttributes().removeAttribute(key, RequestAttributes.SCOPE_SESSION);
	}
	
//	public static String getSessionValue(String key){
//		
//	}
}
