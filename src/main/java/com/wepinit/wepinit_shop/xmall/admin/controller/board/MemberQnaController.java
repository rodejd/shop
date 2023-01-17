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

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xmall.admin.service.board.MemberQnaService;
import com.wepinit.wepinit_shop.xmall.admin.vo.board.MemberQnaVO;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.session.AdminSessionObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("shop/admin/board/member_qna/*")
public class MemberQnaController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberQnaController.class);
	
	@Resource
	MemberQnaService service;
	
	/*
	 * 게시판관리
	 * 1:1 문의관리 리스트
	 */
	@RequestMapping(value="list")
	public String memberQna(@ModelAttribute("memQnaVO") MemberQnaVO memQnaVO, HttpServletRequest req) throws Exception{
		//게시판 총 건수
		memQnaVO.setTotalCnt(service.getMemberQnaTotalCount());
		
		//게시판 검색 총건수
		memQnaVO.setRowCount(service.getMemberQnaCount(memQnaVO));
		memQnaVO.setMemQnaList(service.getMemberQnaList(memQnaVO));
		return "/shop/admin/board/member_qna";
	}
	
	/*
	 * 게시판관리
	 * 1:1 문의관리 등록/수정 페이지(답변)
	 */
	@RequestMapping(value="register")
	public String memberQnaRegister(@ModelAttribute("memQnaVO") MemberQnaVO memQnaVO,HttpServletRequest req) throws Exception {
		
		if("modify".equals(memQnaVO.getMode()) || "reply".equals(memQnaVO.getMode()) ){
			memQnaVO.setMemQnaObj(service.getMemberQnaInfo(memQnaVO.getSno()));
			memQnaVO.setMid(service.getMemberQnaID(memQnaVO.getMemQnaObj().getMno()));
			
			if("reply".equals(memQnaVO.getMode())){
				memQnaVO.setIp(req.getRemoteAddr());
				Date date = new Date();
				logger.debug(">> date :: "+date);
				memQnaVO.setRegdt(date);
				
				memQnaVO.setMemberList(service.getMemberInfo());
			}
			
			if(memQnaVO.getMemQnaObj().getParent() != memQnaVO.getMemQnaObj().getSno() ){
				memQnaVO.setFormType("reply");	//입력항목제어
				memQnaVO.setMemQnaPrtObj(service.getMemberQnaParentInfo(memQnaVO.getMemQnaObj().getParent()));
			}
		}
		
		return "/shop/admin/board/member_qna_register";
	}
	
	/*
	 * 게시판관리
	 * 1:1문의관리 등록/수정 처리
	 */
	@RequestMapping(value="indb")
	public String memberQnaRegisterPost(@ModelAttribute("memQnaVO")MemberQnaVO memQnaVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		String returnUrl = "";
		AdminSessionObject adminSession = new AdminSessionObject();
		
		String adminName=adminSession.getUserInfo().getUserName();
		memQnaVO.setName(adminName);
		memQnaVO.setSkin(ConfigClass.getSkin(req));
		
		if("delete".equals(memQnaVO.getMode())){
			//데이터 삭제
			String[] deleteNume = memQnaVO.getNolist().split(";");
			for(int i=0; i < deleteNume.length; i++){
				service.deleteMemberQna(Integer.parseInt(deleteNume[i]));
			}
			returnUrl = "redirect:/shop/admin/board/member_qna/list";
		}else if("modify".equals(memQnaVO.getMode())){
			//데이터 수정
			memQnaVO.setIp(req.getRemoteAddr());
			service.updateMemberQna(memQnaVO);
			
			//POPUP CLOSE를 위한 FLAG
			model.addAttribute("result", 1);
			returnUrl = "/shop/admin/board/member_qna_register";
		}else if("reply".equals(memQnaVO.getMode())){
			//데이터 답글
			memQnaVO.setIp(req.getRemoteAddr());
			service.insertMemberQnaReply(memQnaVO);
			//POPUP CLOSE를 위한 FLAG
			model.addAttribute("result", 1);
			
			returnUrl = "/shop/admin/board/member_qna_register";
		}else{
			throw new BizException("goods.00001");
		}
		return returnUrl;
	}
}