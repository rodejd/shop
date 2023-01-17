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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HtmlTagRequestWrapper  extends HttpServletRequestWrapper {

    private static Logger log = LoggerFactory.getLogger(HtmlTagRequestWrapper.class);

    /**
     * constructor
     * @param request HttpServletRequest
     */
    public HtmlTagRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * getParameterValues
     * @param parameter
     */
    public String[] getParameterValues(String parameter) {
        String values[] = super.getParameterValues(parameter);
        
        if (values == null)
            return null;

        if(HtmlTagFilter.excludeParameters.containsKey(parameter)) {
            if(log.isDebugEnabled()) log.debug("exclude parameter : {}", parameter);
            return values;
        }


        for (int i = 0; i < values.length; i++) {
            if (values[i] != null) {
                StringBuffer strBuff = new StringBuffer();
                for (int j = 0; j < values[i].length(); j++) {
                    char c = values[i].charAt(j);
                    switch (c) {
                        case 39: // '\''
                            // strBuff.append("&apos;");
                            // strBuff.append("&#39;");
                            strBuff.append("&quot;");
                            break;
                        case 34: // '"'
                            strBuff.append("&quot;");
                            break;
                        case 40: // '('
                            strBuff.append("&#40;");
                            break;

                        case 41: // ')'
                            strBuff.append("&#41;");
                            break;
                        case 33: // '!'
                            strBuff.append("&#33;");
                            break;
                        case 92: // '\\'
                            // strBuff.append("&apos;");
                            strBuff.append("&#92;");
                            break;
                        case 47: // '/'
                            strBuff.append("&#47;");
                            break;
                        case 60: // '<'
                            strBuff.append("&lt;");
                            break;
                        case 62: // '>'
                            strBuff.append("&gt;");
                            break;
                        default:
                            strBuff.append(c);
                            break;
                    }
                }
                values[i] = strBuff.toString();
                values[i] = convertSecurityCharacter(values[i]);
                if(log.isDebugEnabled()) log.debug("=====HtmlTagRequestWrapper====getParameterValues--" + values[i]);
            } else {
                values[i] = null;
            }
        }
        return values;
    }

    /**
     * getParameter
     * @param parameter
     */
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        
        if (value == null)
            return null;

        if(HtmlTagFilter.excludeParameters.containsKey(parameter)) {
            if(log.isDebugEnabled()) log.debug("exclude parameter : {}", parameter);
            return value;
        }

        StringBuffer strBuff = new StringBuffer();
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            switch (c) {
                case 39: // '\''
                    // strBuff.append("&apos;");
                    // strBuff.append("&#39;");
                    strBuff.append("&quot;");
                    break;
                case 34: // '"'
                    strBuff.append("&quot;");
                    break;
                case 40: // '('
                    strBuff.append("&#40;");
                    break;

                case 41: // ')'
                    strBuff.append("&#41;");
                    break;
                case 33: // '!'
                    strBuff.append("&#33;");
                    break;
                case 92: // '\\'
                    // strBuff.append("&apos;");
                    strBuff.append("&#92;");
                    break;
                case 47: // '/'
                    strBuff.append("&#47;");
                    break;
                case 60: // '<'
                    strBuff.append("&lt;");
                    break;
                case 62: // '>'
                    strBuff.append("&gt;");
                    break;
                default:
                    strBuff.append(c);
                    break;
            }
        }
        value = strBuff.toString();
        value = convertSecurityCharacter(value);
        if(log.isDebugEnabled()) log.debug("=============HtmlTagRequestWrapper==========getParameter---" + value);
        return value;
    }

    public String convertSecurityCharacter(String value) {

/*
 *  예외 필터 검토를 위하여 남겨둠...
        // 겹치는 문자 script -> x_script로 치환
        // value = value.replaceAll("javascript", "x_javascript");
        value= value.replaceAll("--", "&#45;&#45;");
        value= value.replaceAll("\\.\\.", "&#46;&#46;");
        value = value.replaceAll(" vbscript ", "x_vbscript");
        value = value.replaceAll(" applet ", "x_applet");
        value = value.replaceAll(" meta ", "x_meta");
        value = value.replaceAll(" xml ", "x_xml");
        value = value.replaceAll(" blink ", "x_blink");
        value = value.replaceAll(" style ", "x_style");
        value = value.replaceAll(" script ", "x_script");
        value = value.replaceAll(" embed ", "x_embed");
        value = value.replaceAll(" object ", "x_object");
        value = value.replaceAll(" iframe ", "x_iframe");
        value = value.replaceAll(" frame ", "x_frame");
        value = value.replaceAll(" frameset ", "x_frameset");
        value = value.replaceAll(" ilayer ", "x_ilayer");
        value = value.replaceAll(" title ", "x_title");
        value = value.replaceAll(" base ", "x_base");
        value = value.replaceAll(" eval ", "x_eval");
        value = value.replaceAll(" innnerHTML ", "x_innnerHTML");
        value = value.replaceAll(" charset ", "x_charset");
        value = value.replaceAll(" document ", "x_document");
        value = value.replaceAll(" string ", "x_string");
        value = value.replaceAll(" create ", "x_create");
        value = value.replaceAll(" append ", "x_append");
        value = value.replaceAll(" binding ", "x_binding");
        value = value.replaceAll(" alert ", "x_alert");
        value = value.replaceAll(" refresh ", "x_refresh");
        value = value.replaceAll(" embed ", "x_embed");
        value = value.replaceAll(" cookie ", "x_cookie");
        value = value.replaceAll(" void ", "x_void");
        value = value.replaceAll(" href ", "x_href");
        value = value.replaceAll(" onload ", "x_onload");
        value = value.replaceAll(" onsubmit ", "x_onsubmit");
        value = value.replaceAll(" onunload ", "x_onunload");
        value = value.replaceAll(" union ", "q_union");
        value = value.replaceAll(" select ", "q_select");
        value = value.replaceAll(" insert ", "q_insert");
        value = value.replaceAll(" drop ", "q_drop");
        value = value.replaceAll(" update ", "q_update");
        value = value.replaceAll(" join ", "q_join");
        value = value.replaceAll(" substring ", "q_substring");
        value = value.replaceAll(" from ", "q_from");
        value = value.replaceAll(" where ", "q_where");
        value = value.replaceAll(" declare ", "q_declare");
        value = value.replaceAll(" substr ", "q_substr");
        value = value.replaceAll(" delete ", "q_delete");
        value = value.replaceAll(" having ", "q_having");
        value = value.replaceAll(" rownum ", "q_rownum");
        //value = value.replaceAll("link", "x_link");
        //value = value.replaceAll("all", "q_all");
        //value = value.replaceAll("order", "q_order");

        //value = value.replaceAll("and", "q_and");
        //value = value.replaceAll("or", "q_or");
        //value = value.replaceAll("if", "q_if");
        value = value.replaceAll("' or", "' q_or");
        value = value.replaceAll(" or", " q_or");
        value = value.replaceAll("';", "");
*/
        value = value.replaceAll("script ", "x_script");
        value = value.replaceAll("iframe ", "x_iframe");
        value = value.replaceAll("frame ", "x_frame");
        value = value.replaceAll("frameset ", "x_frameset");
        value = value.replaceAll("innnerHTML", "x_innnerHTML");


        value = value.replaceAll("union ", "q_union");
        value = value.replaceAll("select ", "q_select");
        value = value.replaceAll("insert ", "q_insert");
        value = value.replaceAll("drop ", "q_drop");
        value = value.replaceAll("update ", "q_update");
        //value = value.replaceAll("and", "q_and");
        //value = value.replaceAll("or", "q_or");
        //value = value.replaceAll("if", "q_if");
        value = value.replaceAll("join ", "q_join");
        value = value.replaceAll(" substring", "q_substring");
        value = value.replaceAll("from ", "q_from");
        value = value.replaceAll("where ", "q_where");
        value = value.replaceAll("declare ", "q_declare");
        value = value.replaceAll(" substr", "q_substr");
        //value = value.replaceAll("all", "q_all");
        value = value.replaceAll("delete ", "q_delete");
        //value = value.replaceAll("order", "q_order");
        value = value.replaceAll("having ", "q_having");
        value = value.replaceAll(" rownum", "q_rownum");

        value = value.replaceAll("' or", "' q_or");
        value = value.replaceAll(" or", " q_or");

        if(log.isDebugEnabled()) log.debug("===HtmlTagRequestWrapper====convertSecurityCharacter---" + value);
        return value;
    }
}
