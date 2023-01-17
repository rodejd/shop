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
/********************************************************************************
* <pre>
* 업무구분명			:	Community
* 세부업무구분명	: 	사용자 > Community
* 작성자 				: 	이병환
* 설명 				: 	사용자 Community Controller
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170208		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.controller.common;

import com.google.common.io.ByteStreams;
import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.front.service.common.FrontCommonService;
import com.wepinit.wepinit_shop.xmall.front.vo.common.FrontCommonVO;
import com.wepinit.wepinit_shop.xmall.sample.service.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;


@Controller
@RequestMapping("/shop/common/")
public class FrontCommonController {

	private static final Logger logger = LoggerFactory.getLogger(FrontCommonController.class); 
	
	@Resource
	SampleService service;

//	@Resource
//	FrontCommonService frontCommonService;

	@RequestMapping(value="index")
	public String main(@ModelAttribute("frontCommonVO") FrontCommonVO frontCommonVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("sample common");
		}
		
		return "/shop/common/index";
	}
	
	@RequestMapping(value="fileDownload")
	public void fileDown(HttpServletRequest req, HttpServletResponse res) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@ fileDownload >> "+req.getParameter("filePath")+" >> "+req.getParameter("fileName"));
		}
		
		
		
		 // 다운받을 파일의 이름을 가져옴
		  String fileName = req.getParameter("fileName");
		  String realFolder = req.getParameter("filePath");
		  realFolder = StringUtil.xssReturn(realFolder);
		  fileName = StringUtil.xssReturn(fileName);
		  
		  String header = req.getHeader("User-Agent");
			
		  // 다운받을 파일의 전체 경로를 filePath에 저장
		  String filePath = ConfigClass.value("WEB_DIR_PATH") + realFolder + fileName;
		  try{
			    String docName = new String(fileName.getBytes("UTF-8"), "8859_1");
			    
			    // 익스플로러에서 이름이 깨짐으로 인한 인코딩	
			    if(header.contains("Trident")) {
			    	docName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
    			}
			    
			    res.setHeader("Content-Disposition", "attachment;filename=" + docName + ";");
			    res.setContentType("text/plain");
			 
			    File downFile = new File(filePath); //파일 생성
			    FileInputStream fileIn = new FileInputStream(downFile); //파일 읽어오기
			    ByteStreams.copy(fileIn, res.getOutputStream());
			    res.flushBuffer();
			    
		  } catch(Exception e){
		  }
	}

	// 환율 API test
	/*
	@RequestMapping("/dollar")
	@ResponseBody
	public boolean test() throws Exception {
		boolean flag = false;
		if (ShopConfig.getProperty("exchange_en") == null) {
			String dollar = frontCommonService.convertToDollar(1);
			ShopConfig.setProperty("exchange_en", dollar);
			flag = true;
			logger.info("...... new :: {}", dollar);
		} else {
			logger.info("...... origin :: {}", ShopConfig.getProperty("exchange_en"));
		}
		return flag;
	}
	 */
	
	
}
