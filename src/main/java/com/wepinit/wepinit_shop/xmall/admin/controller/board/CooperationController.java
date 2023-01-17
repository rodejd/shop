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
import com.wepinit.wepinit_shop.xmall.admin.service.board.CooperationService;
import com.wepinit.wepinit_shop.xmall.admin.vo.board.CooperationVO;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/shop/admin/board/cooperation/*")
public class CooperationController {
	
	private static final Logger logger = LoggerFactory.getLogger(CooperationController.class);
	
	@Resource
	CooperationService service;
	
	/*
	 * 게시판관리
	 * 광고제휴문의 리스트
	 */
	@RequestMapping(value="list")
	public String cooperation(@ModelAttribute("cooperVO") CooperationVO cooperVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("@@cooperation ==> get");
			logger.debug(">>> list :: "+cooperVO.toString());
		}
		
		//총 건수(화면 출력용)
		cooperVO.setTotalCount(service.getCooperationTotalCount());
		
		//총건수
		cooperVO.setRowCount(service.getCooperationCount(cooperVO));
		cooperVO.setCooperList(service.getCooperList(cooperVO));
		
		return "/shop/admin/board/cooperation";
	}
	
	/*
	 * 게시판관리
	 * 광고제휴문의 등록/수정 페이지 
	 */
	@RequestMapping(value="register")
	public String cooperationRegister(@ModelAttribute("cooperVO") CooperationVO cooperVO
			,HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		if(logger.isDebugEnabled()) {
			logger.debug("@@cooperation Register ==> get ");
		}
		
		if("".equals(cooperVO.getReturnUrl()) || null == cooperVO.getReturnUrl()){
			cooperVO.setReturnUrl(req.getHeader("referer"));
		} else {
			cooperVO.setReturnUrl(StringUtil.replace(cooperVO.getReturnUrl(), "||", "&"));
		}
		
		if("modify".equals(cooperVO.getMode())) {
			cooperVO.setCooperObj(service.getCooperView(cooperVO.getSno()));
		}
		
		return "/shop/admin/board/cooperation_register";
	}
	
	/*
	 * 게시판관리
	 * 광고제휴문의 수정/삭제 처리
	 */
	@RequestMapping(value="indb")
	public String cooperationModify(@ModelAttribute("cooperVO") CooperationVO cooperVO
			,HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		if(logger.isDebugEnabled()) {
			logger.debug("@@cooperation Modify ==> post");
		}
		
		if("delete".equals(cooperVO.getMode())) {
			String[] deleteNume = cooperVO.getNolist().split(";");
			for(int i=0; i<deleteNume.length; i++){
				service.deleteCooperation(Integer.parseInt(deleteNume[i]));
			}
		} else if ("modify".equals(cooperVO.getMode())) {
			service.updateCooperModify(cooperVO);
			service.updateCooperDateModify(cooperVO);
		} else if ("allmodify".equals(cooperVO.getMode())) {
			String[] allmodiArr = StringUtil.split( cooperVO.getAllmodify().trim() ,"||");
			String[] catchArrName = StringUtil.split(allmodiArr[0],"==");
			String[] catchArrData = StringUtil.split(catchArrName[1],";");
			String[][] 	alldatalist = new String[allmodiArr.length][catchArrData.length];
			
			for(int i=0; i<allmodiArr.length; i++){
				catchArrName = StringUtil.split(allmodiArr[i],"==");
				catchArrData = StringUtil.split(catchArrName[1],";");
				for(int j=0; j<catchArrData.length ; j++){
					alldatalist[i][j] = catchArrData[j];
				}
			}
			for(int i=0; i<alldatalist[0].length; i++){
				service.updateCooperAllModify(alldatalist[1][i], Integer.parseInt(alldatalist[0][i]));
			}
		} else {
			throw new BizException("goods.00001");
		}
		return "redirect:/shop/admin/board/cooperation/list";
	}

}
