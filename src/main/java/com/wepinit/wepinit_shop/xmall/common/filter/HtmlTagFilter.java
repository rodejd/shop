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
package com.wepinit.wepinit_shop.xmall.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class HtmlTagFilter  implements Filter {

    private static Logger log = LoggerFactory.getLogger(HtmlTagFilter.class);

    private static Map<String, String> excludeUrls = new HashMap<String, String>();
    protected static Map<String, String> excludeParameters = new HashMap<String, String>();

    /**
     * default constructor
     */
    public HtmlTagFilter() {

    }


    /**
     * doFilter
     * @param request   ServletRequest
     * @param response  ServletResponse
     * @param chain     FilterChain
     * @exception IOException
     * @exception ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

    	String uri = "";
    	MultipartHttpServletRequest mRequest = null;
    	CommonsMultipartResolver commonMultipartResolver = null;
    	
    	uri = ((HttpServletRequest)request).getRequestURI();
    	
		if(request.getContentType() != null && request.getContentType().toLowerCase().contains("multipart/form-data")){
			commonMultipartResolver = new CommonsMultipartResolver();
			mRequest = commonMultipartResolver.resolveMultipart((HttpServletRequest)request);
			
			if( uri.startsWith("/shop/seller") || uri.startsWith("/shop/admin") || excludeUrls.containsKey(uri)) {
	            chain.doFilter(mRequest, response);
	        } else {
	            chain.doFilter(new HtmlTagRequestWrapper((MultipartHttpServletRequest) mRequest), response);
	        }
		}else{
			if( uri.startsWith("/shop/seller") || uri.startsWith("/shop/admin") || excludeUrls.containsKey(uri)) {
	            chain.doFilter(request, response);
	        } else {
	            chain.doFilter(new HtmlTagRequestWrapper((HttpServletRequest) request), response);
	        }
		}
    }

    /**
     * init
     * @param config FilterConfig
     */
    public void init(FilterConfig config) throws ServletException {
    	
        String exUrls = config.getInitParameter("exclude_urls");
        if(exUrls != null) {
            StringTokenizer st = new StringTokenizer(exUrls, ",");
            String exUrl = null;
            while (st.hasMoreElements()) {
                exUrl = ((String)st.nextElement()).trim();
                excludeUrls.put(exUrl, exUrl);
            }
        }

        if(log.isDebugEnabled()) log.debug("=======================excludeUrls : {}", excludeUrls);


        String exParams = config.getInitParameter("exclude_parameters");
        if(exParams != null) {
            StringTokenizer st = new StringTokenizer(exParams, ",");
            String exParam = null;
            while (st.hasMoreElements()) {
                exParam = ((String)st.nextElement()).trim();
                excludeParameters.put(exParam, exParam);
            }
        }

        if(log.isDebugEnabled()) log.debug("excludeParameters : {}", excludeParameters);
    }

    /**
     * destroy
     */
    public void destroy() {
    }
}