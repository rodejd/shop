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
/***********************************************
* <pre>
* 업무구분명		:	업무 BizException(사용자 Exception)
* 세부업무구분명	: 	업무 BizException
* 작성자 			: 	이의창
* 설명 				: 	업무 BizException
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO 	날짜					작성자		내용
* 1		20150109				이의창		최초작성
* 
* </pre>
***********************************************/
package com.wepinit.wepinit_shop.xmall.common.exception;

import com.wepinit.wepinit_shop.xmall.common.util.MessageAccessorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

public class BizException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(BizException.class);
	
	private String errorCode = "common.00001";
	private String errorMessage = "불편을 드려 죄송합니다.";
	private String returnUrl = "/";
	
	public BizException() {
		super();
		exceptionLogWrite();
	}

	public BizException(String code) {
		//super(code);
		this.errorCode = code; 
		this.errorMessage = MessageAccessorUtils.getMessage(code);
		exceptionLogWrite();
	}
	
	public BizException(String code, Object[] objs) {
		//super(code);
		this.errorCode = code; 
		this.errorMessage = MessageAccessorUtils.getMessage(code,objs); 
		exceptionLogWrite();
	}
	
	public BizException(String code, Object[] objs,String returnUrl) {
		//super(code);
		this.errorCode = code; 
		this.errorMessage = MessageAccessorUtils.getMessage(code,objs); 
		this.returnUrl = returnUrl;
		exceptionLogWrite();
	}
	
	public BizException(Throwable cause) {
		super(cause);
	}
	public BizException(String message, Throwable cause) {
		super(message, cause);
		exceptionLogWrite();
	}
	public BizException(BindingResult bindingResult) {
		this(bindingResult.getGlobalError().getCode(), bindingResult.getGlobalError().getDefaultMessage());
		exceptionLogWrite();
	}
	public BizException(BindingResult bindingResult, String errorMessage) {
		this(bindingResult.getGlobalError().getCode(), errorMessage);	
		exceptionLogWrite();
	}
	public BizException(String errorCode, String returnUrl) {
		this.errorCode = errorCode;
		this.errorMessage = MessageAccessorUtils.getMessage(errorCode);
		this.returnUrl = returnUrl;
		exceptionLogWrite();
	}
	
	public BizException(String code, String message, String returnUrl) {
		//super(code);
		this.errorCode = code; 
		this.errorMessage = message;
		this.returnUrl = returnUrl;
		exceptionLogWrite();
	}

	// getter, setter
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	
	public void exceptionLogWrite(){
		//logger.error("#######################################");
		//logger.error("#error_code : " +  this.errorCode);
		//logger.error("#error_message : " + this.errorMessage);
		//logger.error("#######################################");
	}
}
