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
package com.wepinit.wepinit_shop.xmall.admin.controller.board;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xcube.util.WebUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.board.FaqService;
import com.wepinit.wepinit_shop.xmall.admin.vo.board.FaqVO;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/shop/admin/board/faq/*")
public class FaqController {
	@Resource
	FaqService service;
	
	/*
	 * FAQ관리
	 */
	@RequestMapping(value="list")
	public String faq(@ModelAttribute("faqVO") FaqVO faqVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		//TOTAL COUTN(총 건수 출력용)
		faqVO.setTotalCount(service.getFaqTotalCount());
		
		//SEARCH COUNT
		faqVO.setRowCount(service.getFaqSearchCount(faqVO));
		faqVO.setFaqList(service.getFaqList(faqVO));
		
		return "/shop/admin/board/faq";
	}
	
	/*
	 * FAQ관리 등록 페이지
	 */
	@RequestMapping(value="register")
	public String faqRegister(@ModelAttribute("faqVO")FaqVO faqVO, Model model) throws Exception {
		//수정 화면
		if("modify".equals(faqVO.getMode())){
			faqVO.setFaqObj(service.getFaqDetail(faqVO.getSno()));
			model.addAttribute("faqVO.faqObj", faqVO.getFaqObj());
		}
		return "/shop/admin/board/faq_register";
	}
	
	/*
	 * FAQ관리 등록 POST
	 */
	@RequestMapping(value="indb")
	public String faqRegister(@ModelAttribute("faqVO")FaqVO faqVO, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int lastSort = 0;
		int mySno = 0;
		
		faqVO.setDescant(WebUtil.setLineChange2(faqVO.getDescant()));
		faqVO.setAnswer(WebUtil.setLineChange2(faqVO.getAnswer()));
		
		if("".equals(faqVO.getMode()) || "register".equals(faqVO.getMode())){
			//등록 페이지
			
			faqVO.setReturnUrl(req.getHeader("referer"));
			lastSort = service.getSortMax(faqVO.getItemcd());
			
			service.insertFapReply(lastSort, faqVO);
			mySno = service.getSnoMax(faqVO.getItemcd());
			
			faqVO.setReturnUrl("./register?mode=modify&sno="+mySno+"&returnUrl="+ StringUtil.replace(faqVO.getReturnUrl(),"&","||"));
		} else if("modify".equals(faqVO.getMode())) {
			//수정 페이지
			faqVO.setDescant(WebUtil.setLineChange2(faqVO.getDescant()));
			faqVO.setAnswer(WebUtil.setLineChange2(faqVO.getAnswer()));
			
			service.updateFaqModify(faqVO);
			
			faqVO.setReturnUrl("./register?mode=modify&sno="+ faqVO.getSno()+"&returnUrl="+StringUtil.replace(faqVO.getReturnUrl(),"&","||"));
		} else if("allmodify".equals(faqVO.getMode())) {
			String[] allmodiArr = StringUtil.split(faqVO.getAllmodify().trim() ,"||");
			String[] catchArrName = StringUtil.split(allmodiArr[0],"==");
			String[] catchArrData = StringUtil.split(catchArrName[1],";");
			String[][] 	alldatalist = new String[allmodiArr.length][catchArrData.length];
			
			for(int i=0; i < allmodiArr.length; i++)
			{
				catchArrName = StringUtil.split(allmodiArr[i],"==");
				catchArrData = StringUtil.split(catchArrName[1],";");
				
				for(int j=0; j<catchArrData.length ; j++)
				{
					alldatalist[i][j] = catchArrData[j];
				}
			}
			for(int i=0; i < alldatalist[0].length; i++)
			{
				faqVO.setSno(Integer.parseInt(alldatalist[0][i]));
				faqVO.setSort(Integer.parseInt(alldatalist[1][i]));
				faqVO.setItemcd(alldatalist[2][i]);
				faqVO.setBest(alldatalist[3][i]);
				faqVO.setBestsort(Integer.parseInt(alldatalist[4][i]));
				service.updateAllFaqModify(faqVO);
			}
		} else if("delete".equals(faqVO.getMode())) {
			String[] deleteNume = faqVO.getNolist().split(";");
			for(int i=0; i < deleteNume.length; i++)
			{
				service.deleteFaqRegister(Integer.parseInt(deleteNume[i]));
			}
		} else {
			throw new BizException("goods.00001");
		}
		return "redirect:/shop/admin/board/faq/list";
	}
	
	@RequestMapping(value="sort.dojson")
	public String sortAjax(@RequestParam(value="sno")int[] sno, @RequestParam(value="sort")int[] sort, Model model) throws Exception{
		List<Integer> existingSort = new ArrayList<Integer>();
		existingSort = service.checkFaqSort(sno, sort);
		
		if (null != existingSort && 0 < existingSort.size()) {
			model.addAttribute("existingSort", existingSort);
		}
		
		return "dojson";
	}
	
}
