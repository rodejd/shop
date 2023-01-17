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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.List;
import java.util.UUID;


@Controller
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Value("${shop-upload.upload-path}")
	private String uploadPath;
	
	
	@RequestMapping(value="/uploadForm", method=RequestMethod.GET)
	public void uploadForm() {
		
		logger.info("uploadPath: "+uploadPath+File.separator);
		
	}
	
	/*@RequestMapping(value="/uploadForm", method=RequestMethod.POST)
	public String uploadFormPost(MultipartFile file, Model model) throws Exception {
		
		logger.info("originalName: "+ file.getOriginalFilename());
		logger.info("size: "+ file.getSize());
		logger.info("contentType: "+ file.getContentType());
		
		String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());		
		model.addAttribute("savedName", savedName);		
		return "uploadResult";
		
	}*/
	
	@RequestMapping(value="/uploadForm", method=RequestMethod.POST)
	public String uploadFormPost(MultipartHttpServletRequest mhsq, Model model) throws Exception {
	
		List<MultipartFile> mf = mhsq.getFiles("file");
		System.out.println("=============="+mf.size());
		for (int i = 0; i < mf.size(); i++) {
			uploadFile(mf.get(i).getOriginalFilename(), mf.get(i).getBytes());
		}
	
		return "uploadResult";
		
	}
	
	private String uploadFile(String originalName, byte[] fileData) throws Exception {
		
		UUID uid = UUID.randomUUID();	
		String savedName = uid.toString() + "_" + originalName;		
		File target = new File(uploadPath+File.separator+"shop"+File.separator+"main", savedName);
		FileCopyUtils.copy(fileData, target);
		
		return savedName;
	}

}
