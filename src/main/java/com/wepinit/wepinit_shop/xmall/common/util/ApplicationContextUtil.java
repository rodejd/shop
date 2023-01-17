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
/**
 * ApplicationContext 로드
 * 
 */
package com.wepinit.wepinit_shop.xmall.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

public class ApplicationContextUtil {

	private static Logger logger = LoggerFactory.getLogger(ApplicationContextUtil.class);

	private static ApplicationContext ctx = null;

	/**
	 * Spring 에서 사용하는 WebApplicationContext를 가져온다.
	 */
	public static ApplicationContext getApplicationContext(){
		ServletContext context = null ;

		if(ctx == null){
			//현재 요청중인 thread local의 HttpServletRequest 객체 가져오기
			context = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getServletContext();
			 ctx  = WebApplicationContextUtils.getWebApplicationContext(context);
		}

		return ctx;
	}

}