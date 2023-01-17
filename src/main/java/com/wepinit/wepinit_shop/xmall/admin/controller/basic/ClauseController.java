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
package com.wepinit.wepinit_shop.xmall.admin.controller.basic;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xmall.admin.repository.TermsAndConditionsRepository;
import com.wepinit.wepinit_shop.xmall.admin.service.basic.ClauseService;
import com.wepinit.wepinit_shop.xmall.admin.vo.basic.ClauseVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.basic.TermsAndConditions;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/shop/admin/basic/*")
public class ClauseController {

	private static final Logger logger = LoggerFactory.getLogger(ClauseController.class);

	@Resource
	ClauseService service;

	@Autowired
	private TermsAndConditionsRepository termsAndConditionsRepository;

	@RequestMapping(value = "clause")
	public String getClause(@ModelAttribute("clauseVO") ClauseVO clauseVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug("clause!!!");
		}
		
		if(StringUtils.isBlank(clauseVO.getSkin())) {
			clauseVO.setSkin("kr");
		}
		
		// 약관내용
		/*
		String filePath = ConfigClass.value("WEB_DIR_PATH").concat("/shop/data/clause/");
		StringBuffer sb = new StringBuffer();
		ArrayList list = FileUtil.getOneLineListToFile(filePath.concat("contents_"+clauseVO.getType()+"_"+clauseVO.getSkin()+".html"));
		int i = 0;
		if ( null != list && 0 < list.size() ) {
			i = 0;
			while ( i < list.size() ) {
				sb.append((String)list.get(i) + "\n");
				i++;
			}
		}
		clauseVO.setClause(sb.toString());
		 */

		TermsAndConditions item = termsAndConditionsRepository.findTopByCategoryAndTypeAndSkin("clause", clauseVO.getType(), clauseVO.getSkin());
		model.addAttribute("item", item);

		return "/shop/admin/basic/clause";
	}
	@RequestMapping(value = "clauseRegist")
	public String setClause(@ModelAttribute("clauseVO") ClauseVO clauseVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug("clauseRegist!!!");
			logger.debug("clauseRegist!!!::>>>"+clauseVO.getType());
		}
		// 내용저장
		/*
		String filePath = ConfigClass.value("WEB_DIR_PATH").concat("/shop/data/clause/");
		FileUtil.savefile(filePath.concat("contents_"+clauseVO.getType()+"_"+clauseVO.getSkin()+".html"), clauseVO.getClause(), false);
		 */

		String content = clauseVO.getClause();
//		logger.info(".............. content :: {}", content);

		TermsAndConditions item = null;
		if (req.getParameter("no") != null && !req.getParameter("no").equals("")) {
			item = termsAndConditionsRepository.findByNo(Integer.parseInt(req.getParameter("no")));
		} else {
			item = TermsAndConditions.builder()
				.category("clause")
				.type(clauseVO.getType())
				.skin(clauseVO.getSkin())
				.build();
		}

		item.setContent(content);

		termsAndConditionsRepository.save(item);

		return "redirect:/shop/admin/basic/clause?type=" + clauseVO.getType();
	}	
}