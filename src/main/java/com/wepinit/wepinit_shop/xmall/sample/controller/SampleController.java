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
package com.wepinit.wepinit_shop.xmall.sample.controller;

import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.sample.service.SampleService;
import com.wepinit.wepinit_shop.xmall.sample.vo.SampleFM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/sample")
public class SampleController {

	private static final Logger logger = LoggerFactory.getLogger(SampleController.class); 
	
	@Resource
	SampleService service;
	
	@RequestMapping(value="index")
	public String main(@ModelAttribute("sampleVO") SampleFM sampleVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("sample main");
		}
		
		sampleVO.setSampleList(service.getSampleList(sampleVO));
		
		return "/sample/index";
	}
	
	@RequestMapping(value="view")
	public String view(@ModelAttribute("sampleVO") SampleFM sampleVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
		Map<String, Object> param = null;
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("sample view");
		}
		
		param = new HashMap<String, Object>();
		
		param.put("sample_no", sampleVO.getSample_no());
		param.put("title", sampleVO.getTitle());
		param.put("description", sampleVO.getDescription());
		
		sampleVO.setSampleObj( service.getSample(sampleVO.getSample_no()) );
		
		return "/sample/view";
	}
	
	@RequestMapping(value="reg", method=RequestMethod.POST)
	public String reg(@ModelAttribute("sampleVO") SampleFM sampleVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		int result = 0;
		Map<String, Object> param = null;
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("sample reg");
		}
		
		param = new HashMap<String, Object>();
		
		param.put("sample_no", sampleVO.getSample_no());
		param.put("title", sampleVO.getTitle());
		param.put("description", sampleVO.getDescription());
		
		result = service.setSampleReg(param);
		
		// 개발자가 Exception 처리 할 경우 BizException 사용.
		if(  0 >= result ){
			throw new BizException();
		}
		
		return "redirect:/shop/sample/main";
	}
	
	@RequestMapping(value="mod", method=RequestMethod.POST)
	public String mod(@ModelAttribute("sampleVO") SampleFM sampleVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		Map<String, Object> param = null;
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("sample mod");
		}
		
		param = new HashMap<String, Object>();
		
		param.put("sample_no", sampleVO.getSample_no());
		param.put("title", sampleVO.getTitle());
		param.put("description", sampleVO.getDescription());
		
		service.setSampleMod(param);
		
		return "redirect:/shop/sample/view?sample_no=" + sampleVO.getSample_no();
	}
	
	
	@RequestMapping(value="sampleAjax")
	public String sampleAajax(@ModelAttribute("sampleVO") SampleFM sampleVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {

		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("sample main");
		}
		
		sampleVO.setSampleList(service.getSampleList(sampleVO));
		
		return "/sample/ajax";
	}
	
	@RequestMapping(value="bizExceptionTest1")
	public String bizExceptionTest1(@ModelAttribute("sampleVO") SampleFM sampleVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("sample main");
		}
		
		if(true){
			throw new BizException("sample.00000");
		}
		
		return "/sample/main";
	}
	
	@RequestMapping(value="bizExceptionTest2")
	public String bizExceptionTest2(@ModelAttribute("sampleVO") SampleFM sampleVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("sample main");
		}
		
		if(true){
			throw new BizException("sample.00001", new String[]{"1"});
		}
		
		return "/sample/main";
	}
	
	@RequestMapping(value="multipartRequest")
	public String multipartRequest(@ModelAttribute("sampleVO") SampleFM sampleVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, MultipartHttpServletRequest mhsq
			, Model model) throws Exception {
		
		
		MultipartFile file = mhsq.getFile("param");
		
		FileUtil.uploadFile3(file.getName(), file.getBytes());
		
		return "/sample/main";
	}
}
