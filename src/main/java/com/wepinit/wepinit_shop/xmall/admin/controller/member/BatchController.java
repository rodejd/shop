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
package com.wepinit.wepinit_shop.xmall.admin.controller.member;

import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.member.MemberService;
import com.wepinit.wepinit_shop.xmall.admin.service.member.SmsService;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.BatchVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.MemberVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/shop/admin/member/batch/*")
public class BatchController {

	private static final Logger logger = LoggerFactory.getLogger(BatchController.class);

	@Resource
	MemberService memberService;
	
	@Resource
	SmsService smsService;
	
	/*
	 * 회원일괄관리 > 적립금일괄지급, 회원그룹일괄변경, 회원승인상태, SMS일괄발송
	 * 
	 * */
	

	/**
	 * 회원일괄관리 > 회원리스트조회
	 * 
	 * @param smsAutoVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list")
	public String list(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>batch list!!!" + memberVO);
		}

		Map<String, Object> param = new HashMap<String, Object>();

		/*
		 * 검색설정(1)
		 * 
		 * */
		
		// 검색타입
		param.put("skey", memberVO.getSkey());
		
		// 검색어
		param.put("sword", memberVO.getSword());
		
		// 검색(주민등록번호검색)
		if("resno".equals(memberVO.getSkey())){
			String tmp = StringUtil.fillSpace(memberVO.getSword().replaceAll("-", ""),13);
			
			param.put("resno1", tmp.substring(0,6));
			param.put("resno2", tmp.substring(6,13));
		}
		
		// 승인여부
		param.put("sstatus", memberVO.getSstatus());
		
		// 그룹
		param.put("slevel", memberVO.getSlevel());
		
		
		/*
		 * 검색설정(2)
		 * 
		 * */
		// 구매액
		param.put("ssum_salemin", memberVO.getSsum_salemin());
		param.put("ssum_salemax", memberVO.getSsum_salemax());
		
		//적립금
		param.put("semoneymin", memberVO.getSemoneymin());
		param.put("semoneymax", memberVO.getSemoneymax());
		
		/*
		 * 검색설정(3)
		 * 
		 * */
		
		//가입일
		param.put("sregdt_0", memberVO.getSregdt() != null ? memberVO.getSregdt()[0] : "" );
		param.put("sregdt_1", memberVO.getSregdt() != null ? memberVO.getSregdt()[1] : "" );
		
		//최종로그인
		param.put("slastdt_0", memberVO.getSlastdt() != null ? memberVO.getSlastdt()[0] : "" );
		param.put("slastdt_1", memberVO.getSlastdt() != null ? memberVO.getSlastdt()[1] : "" );
		
		/*
		 * 검색설정(4)
		 * 
		 * */
		// 성별, 메일, SMS 수신여부
		param.put("sex", memberVO.getSex());
		/*param.put("sage", memberVO.getSage() );
		if(!"".equals(memberVO.getSage()) && null != memberVO.getSage() ){
			param.put("age0", Integer.parseInt(DateUtil.getDateFrom("yyyy", "1y")) - Integer.parseInt( memberVO.getSage() ));
			param.put("age1", (Integer)param.get("age1") - 9 );
		}*/
		param.put("mailling", memberVO.getSmailling());
		
		// 방문횟수, 휴면회원검색
		param.put("scnt_loginmin", memberVO.getScnt_loginmin());				// 방문횟수 min
		param.put("scnt_loginmax", memberVO.getScnt_loginmax());			// 방문횟수 max
		
		if(!"".equals(memberVO.getDormancy()) && null != memberVO.getDormancy() ){
			param.put("dormancy", DateUtil.getDateFrom("yyyyMMdd", "-" + memberVO.getDormancy()+ "d"));		// 휴면회원
		}
		
		// 생년월일, 결혼여부/결혼기념일
		
		param.put("birthtype", memberVO.getBirthtype());
		param.put("birthdatemin", memberVO.getBirthdatemin());
		param.put("birthdatemax", memberVO.getBirthdatemax());
		
		param.put("marriyn", memberVO.getSmarriyn());
		param.put("marridatemin", memberVO.getMarridatemin());
		param.put("marridatemax", memberVO.getMarridatemax());
		
		
		// sort
		param.put("sort", memberVO.getSort());
		
		
		//회원 총건수
		memberVO.setTotalCount(memberService.getMemberTotalCount());
		
		//검색 총건수
		memberVO.setRowCount(memberService.getMemberCount(param));
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, memberVO.getPageSize());
		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, memberVO.getRowStart());
		
		// 회원 그룹별 조회
		memberVO.setGdMemberList(memberService.getMemberList(param)); 								// 회원관리 > 회원일괄관리 > SMS일괄발송(회원리스트)

		return "/shop/admin/member/batch/list";
	}
	
	
	/**
	 * 회원일괄관리 > 적립금일괄지급, 회원그룹일괄변경, 회원승인상태, SMS일괄발송(처리프로세스)
	 * 
	 * @param smsSendVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "indb.dojson" , method=RequestMethod.POST)
	public String indb(@ModelAttribute("batchVO") BatchVO batchVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>회원일괄처리" + batchVO);
		}
		
		int total = 0;
		
		if("sms".equals( batchVO.getFunc() )){
			total = smsService.insertSendSms(batchVO);
		}else{
			total = memberService.updateMemberBatch(batchVO);
		}
		
		model.addAttribute("total", total);

		return "dojson";
	}
	
	
}
