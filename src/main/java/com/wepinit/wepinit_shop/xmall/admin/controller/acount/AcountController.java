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
package com.wepinit.wepinit_shop.xmall.admin.controller.acount;

import com.wepinit.wepinit_shop.xmall.admin.service.acount.AcountService;
import com.wepinit.wepinit_shop.xmall.admin.vo.acount.AcountVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/shop/admin/log/acount/*")
public class AcountController {
	private static final Logger logger = LoggerFactory.getLogger(AcountController.class);
	@Resource
	AcountService service;

	//정산세부내역리스트
	@RequestMapping(value="acountList")
	public String acountList(@ModelAttribute("acountVO") AcountVO acountVO, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug("AcountController acountList start ####################");
		}
		
		acountVO.setTotalCnt(service.getAcountListTotalCount());
		
		acountVO.setRowCount(service.getAcountListCount(acountVO));
		
		acountVO.setTotal(service.getAcountListData(acountVO));
		
		if(0 < acountVO.getRowCount()){
			acountVO.setAccountList(service.getAcountService(acountVO));
		}
		
		return "shop/admin/log/acount/acountList";
	}


	//정산세부내역리스트
	@RequestMapping(value="acountDetailInfo")
	public String acountDetailInfo(HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("AcountController acountDetailInfo start ####################");
		}
		
		return "shop/admin/log/acount/acountDetailInfo";
	}	
	
	
	//정산완료내역리스트
	@RequestMapping(value="acountEndList")
	public String acountEndList(HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("AcountController acountEndList start ####################");
		}
		
		return "shop/admin/log/acount/acountEndList";
	}
	
	@RequestMapping(value="indb")
	public String indbFlag(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redirectAttr) throws Exception{
		
		if (logger.isDebugEnabled()){
			logger.debug("AccountController indbFlag start ############");
		}
		String [] goodsnoArr = req.getParameterValues("acountArr_goodsno");
		String [] ordnoArr = req.getParameterValues("acountArr_ordno");
		
		if(goodsnoArr.length > 0 && ordnoArr.length > 0){
			Map<String, Object> paramsDB = null;
			paramsDB = new HashMap<String, Object>();
			paramsDB.put("acountArr_goodsno", goodsnoArr);
			paramsDB.put("acountArr_ordno", ordnoArr);
			paramsDB.put("acountFlag", req.getParameter("acountFlag"));
			service.updateFlag(paramsDB);
			redirectAttr.addFlashAttribute("message", "적용되었습니다.");
		}else {
			redirectAttr.addFlashAttribute("message", "데이터 적용에 실패하였습니다.");
		}
		return "redirect:/shop/admin/log/acount/acountList";
		
	}
	
}
