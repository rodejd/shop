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
package com.wepinit.wepinit_shop.xmall.admin.controller.common;

import com.google.common.io.ByteStreams;
import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.common.CommonService;
import com.wepinit.wepinit_shop.xmall.admin.vo.common.ImgFileVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.common.MenuVO;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/shop/admin/common/*")
public class CommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Resource
	private CommonService commonService;
	
	@RequestMapping(value="header")
	public void header(Model model) throws Exception {
		logger.debug("header!?");		
		List<MenuVO> list = commonService.headerList();
		
		model.addAttribute("list", list);
		logger.debug(list.toString());
	}
	
	@RequestMapping(value="left/{menuKey}")
	public @ResponseBody List<MenuVO> left(@PathVariable String menuKey) throws Exception {
		logger.debug("ajax@@@@@@@@@"+menuKey);
			
		return commonService.leftList(menuKey);
	}
	
	@RequestMapping(value="navi", method=RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> navi(@RequestBody Map<String,String> urlMap) throws Exception {
		logger.debug("ajax@@@@@@@@@"+urlMap.get("url"));

		return commonService.getNavi(urlMap.get("url"));
	}
	
	//이미지 등록 popup
	@RequestMapping(value="imgRegister")
	public String imgRegister(String idx, @ModelAttribute("imgFileVO") ImgFileVO imgFileVO) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug("go imgRegister page !");
		}
		return "/shop/lib/meditor/popup.image";
	}
	//이미지 등록
	@RequestMapping(value="imgRegister", method=RequestMethod.POST)
	public String imgRegister(@ModelAttribute("imgFileVO") ImgFileVO imgFileVO) throws Exception {
		if(!FileUtil.isCheckFileImgExt(imgFileVO.getImgPc().getOriginalFilename())){
			throw new BizException("common.00003", new String[]{"이미지 파일만 업로드가 가능합니다."});
		}else{
			String awsPath = "editor/";
			String imgUrl = FileUtil.awsUploadFile(imgFileVO.getImgPc().getOriginalFilename(), imgFileVO.getImgPc().getBytes(), awsPath);
			imgFileVO.setName(imgUrl);
		}
		return "/shop/lib/meditor/popup.image";
	}

	@Value("${shop-upload.upload-path}")
	private String uploadPath;
	
	private String uploadFile(String originalName, byte[] fileData) throws Exception {
		return uploadFile(originalName, fileData, null) ;
	}
	
	private String uploadFile(String originalName, byte[] fileData, String dir) throws Exception {
		String directory = "goods";
		if(dir != null && !"".equals(dir)){
			directory = dir;
		}
		return uploadFile(originalName, fileData, directory, false) ;
	}
	/*
	 * 파일 업로드
	 * parameter : originalName(파일명), fileData(파일실 데이터), dir(저장 경로), overWirte(파일 덮어 쓰기 여부)
	 * return : 파일명
	 */
	private String uploadFile(String originalName, byte[] fileData, String dir, boolean overWrite) throws Exception {
		String directory = "goods";
		if(dir != null && !"".equals(dir)){
			directory = dir;
		}
		String savedName = originalName;
		if(!overWrite){
			UUID uid = UUID.randomUUID();
			savedName = "[" + uid.toString() + "]" + originalName;
		}else{}
			
		File target = new File(uploadPath+File.separator+"shop"+File.separator+"data"+File.separator+"upload"+File.separator+directory, savedName);
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
	/*
	 * 파일 업로드
	 * parameter : originalName(파일명), fileData(파일실 데이터), dir(저장 경로), overWirte(파일 덮어 쓰기 여부)
	 * return : 파일명
	 */
	private String uploadFile2(String originalName, byte[] fileData, String dir, boolean overWrite) throws Exception {
		String directory = "goods";
		if(dir != null && !"".equals(dir)){
			directory = dir;
		}
		String savedName = originalName;
		if(!overWrite){
			String uniqkey = ShopLibFunction.getUniqueKey();
			savedName = "[" + uniqkey + "]" + originalName;
		}else{}
			
//		File target = new File(uploadPath+File.separator+"shop"+File.separator+"data"+File.separator+"upload"+File.separator+directory, savedName);
//		FileCopyUtils.copy(fileData, target);
		File target = new File(ConfigClass.value("FILE_PATH") +directory, savedName);
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
	
	@RequestMapping(value="fileDownload")
	public void fileDown(HttpServletRequest req, HttpServletResponse res) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@ fileDownload >> "+req.getParameter("filePath")+" >> "+req.getParameter("fileName"));
		}
		
		// 다운받을 파일의 이름을 가져옴
		String saveFileName = req.getParameter("saveFileName");
		String orgFileName = req.getParameter("orgFileName");
		String realFolder = req.getParameter("filePath");
		realFolder = StringUtil.xssReturn(realFolder);
		  saveFileName = StringUtil.xssReturn(saveFileName);
		  
		  String header = req.getHeader("User-Agent");
			
		  // 다운받을 파일의 전체 경로를 filePath에 저장
		  String filePath = ConfigClass.value("FILE_PATH") + realFolder + "/" + saveFileName;
		  try{
			    String docName = new String(orgFileName.getBytes("UTF-8"), "8859_1");
			    
			    // 익스플로러에서 이름이 깨짐으로 인한 인코딩	
			    if(header.contains("Trident")) {
			    	docName = URLEncoder.encode(saveFileName,"UTF-8").replaceAll("\\+", "%20");
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
}
