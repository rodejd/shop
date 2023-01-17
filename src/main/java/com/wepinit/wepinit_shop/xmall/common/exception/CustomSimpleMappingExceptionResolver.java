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
package com.wepinit.wepinit_shop.xmall.common.exception;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.common.util.MessageAccessorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
 
public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver{
	
	private String ajaxErrorView;
	private String ajaxDefaultErrorMessage = "";
	private boolean ajaxShowTechMessage = true;
	private String defaultExceptionCode = "";
	private String ajaxDefaultErrorReturnUrl = "/";
	private boolean ajaxShowTechReturnUrl = true;
	
	private static Logger logger = LoggerFactory.getLogger(CustomSimpleMappingExceptionResolver.class);
	 
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		
        String exceptionCode = (ex instanceof BizException) ? ((BizException) ex).getErrorCode() : defaultExceptionCode;
        String exceptionMessage = "";
        String exceptionReturnUrl = "";
        String ip = "";
        
        /******************************************************
		 * 프록시나 Load Balancer 같은것을 겨쳐 오게 되는 경우
		 ******************************************************/
		ip = request.getHeader("X-FORWARDED-FOR");

		if("".equals(StringUtil.N2S(ip))) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if("".equals(StringUtil.N2S(ip))) {
			ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
		}

		if("".equals(StringUtil.N2S(ip))) {
			ip = request.getRemoteAddr();
		}
		
		if(logger.isDebugEnabled()){
			ex.printStackTrace();
		}
		
		logger.error("# EXCEPTION #############################");
		logger.error("# uri : " + request.getRequestURI());
		logger.error("# code : " + exceptionCode + "(" + exceptionMessage + ")");
		logger.error("# return_url : " + exceptionReturnUrl);
		logger.error("# exception : " + ex);
		logger.error("# ip : " + ip);
		logger.error("#######################################");
		
	    if ( isAjax(request) ) {
	    	
            exceptionMessage = ajaxDefaultErrorMessage;
            if ( ajaxShowTechMessage ) {
            	exceptionMessage += (ex instanceof BizException) ? ((BizException) ex).getErrorMessage() : MessageAccessorUtils.getMessage(defaultExceptionCode);
            }
            
            if ( ajaxShowTechReturnUrl ) {
            	exceptionReturnUrl = (ex instanceof BizException) ?((BizException) ex).getReturnUrl() : ajaxDefaultErrorReturnUrl;
            }
            
            MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
            Map<String, String> model = new HashMap<String, String>();
            
            model.put("exceptionCode", exceptionCode);
            model.put("exceptionMessage", exceptionMessage);
            model.put("exceptionReturnUrl", exceptionReturnUrl);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new ModelAndView(jsonView,model);
        } else {
            return super.resolveException(request, response, handler, ex);
        }
	    
	}
	
	/**
	 * <pre>
	 * Exception 발생시 ajax call 호출 에러인지 여부 판단
	 * </pre>
	 * @param request
	 * @return
	 */
    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

	public String getAjaxErrorView() {
		return ajaxErrorView;
	}

	public void setAjaxErrorView(String ajaxErrorView) {
		this.ajaxErrorView = ajaxErrorView;
	}

	public String getAjaxDefaultErrorMessage() {
		return ajaxDefaultErrorMessage;
	}

	public void setAjaxDefaultErrorMessage(String ajaxDefaultErrorMessage) {
		this.ajaxDefaultErrorMessage = ajaxDefaultErrorMessage;
	}

	public boolean isAjaxShowTechMessage() {
		return ajaxShowTechMessage;
	}

	public void setAjaxShowTechMessage(boolean ajaxShowTechMessage) {
		this.ajaxShowTechMessage = ajaxShowTechMessage;
	}

	public String getDefaultExceptionCode() {
		return defaultExceptionCode;
	}
	
	public void setDefaultExceptionCode(String defaultExceptionCode) {
		this.defaultExceptionCode = defaultExceptionCode;
	}

	public String getAjaxDefaultErrorReturnUrl() {
		return ajaxDefaultErrorReturnUrl;
	}

	public void setAjaxDefaultErrorReturnUrl(String ajaxDefaultErrorReturnUrl) {
		this.ajaxDefaultErrorReturnUrl = ajaxDefaultErrorReturnUrl;
	}

	public boolean isAjaxShowTechReturnUrl() {
		return ajaxShowTechReturnUrl;
	}

	public void setAjaxShowTechReturnUrl(boolean ajaxShowTechReturnUrl) {
		this.ajaxShowTechReturnUrl = ajaxShowTechReturnUrl;
	}
}

