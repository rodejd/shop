package com.wepinit.wepinit_shop.xcube.util;

import javax.servlet.http.HttpServletRequest;

public class AgentUtil {
	/**
	 * 접속 기기 체크
	 * @param req
	 * @return
	 */
	public static String getAgent(HttpServletRequest req) {
		String returnVal = "W"; //WEB
		String header = req.getHeader("User-Agent");
		if (header != null) {
			header = header.toUpperCase();
			
			if (header.indexOf("MOBILE") > -1) {
				returnVal = "M"; //MOBILE
			}else if(header.indexOf("TRIDENT") > -1 || header.indexOf("EDGE") > -1 || header.indexOf("WHALE") > -1
					|| header.indexOf("OPERA") > -1 || header.indexOf("OPR") > -1 || header.indexOf("FIREFOX") > -1
					|| header.indexOf("SAFARI") > -1 || header.indexOf("CHROME") == -1 ) {
				returnVal = "W"; //WEB 
			}else if (header.indexOf("APP") > -1) {
				returnVal = "A"; //APP
			}
		}
		return returnVal;
	}
}
