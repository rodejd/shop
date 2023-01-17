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
import com.wepinit.wepinit_shop.xmall.admin.vo.member.MemberVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/shop/admin/member/mail/*")
public class MailController {

	private static final Logger logger = LoggerFactory.getLogger(MailController.class); 

	
	@Resource
	MemberService memberService;

	/**
	 * 회원일괄관리 > 회원리스트조회
	 * 
	 * @param memberVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list")
	public String list(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>mail list!!!" + memberVO);
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

		return "/shop/admin/member/mail";
	}
	@RequestMapping("email")
	public String email(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest req, HttpServletResponse res, Model model)  throws Exception {
		String type = "";
		int size=0;
		String[] chk=null;
		List rtList = new LinkedList();
		StringBuffer ListToString = new StringBuffer();
		type=req.getParameter("type");
		if(type.equals("select")){
			chk = req.getParameterValues("chk[]");
			logger.debug(">>>>chk.length>>>"+chk.length);
			for(int i=0;i<chk.length;i++){
				logger.debug(">>>>>>>"+chk[i]);
				rtList.add(i, chk[i]);
			}
			size=chk.length;
		}else if(type.equals("direct")){
			
		}else if(type.equals("query")){
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
			
			
			memberVO.setGdMemberList(memberService.getMemberListSendMail(param));
			List<GdMember> getMemberList = memberVO.getGdMemberList();
			if(getMemberList!=null && getMemberList.size()>0){
				
				size=getMemberList.size();
				for(int ix=0;ix<size;ix++){
					GdMember member = getMemberList.get(ix);
					rtList.add(ix, member.getMno());
					logger.debug("member.getMno()::::["+ix+"]"+member.getMno());
				}
			}
		}
		for(int i=0; i<rtList.size(); i++){
			if(i != rtList.size()-1)
				ListToString.append(rtList.get(i)+",");
			else
				ListToString.append(rtList.get(i));
		}
		model.addAttribute("type", type);
		model.addAttribute("size", size);
		model.addAttribute("ListToString", ListToString.toString());
		return "shop/admin/member/email";
	}	
	@RequestMapping("testMail")
	public String testMail(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest req, HttpServletResponse res, Model model)  throws Exception {
		String type = req.getParameter("type");
		String size = req.getParameter("size");
		String toEmail = req.getParameter("toEmail");
		String toEmailArray = req.getParameter("toEmail");
		String[] toEmails = toEmail.split(",");
		for(int ix=0;ix<toEmails.length;ix++){
			if(ix==0){
				toEmailArray="'"+toEmails[ix]+"'";
			}else{
				toEmailArray+=",'"+toEmails[ix]+"'";
			}
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("toEmail", toEmailArray);
		String subject = req.getParameter("subject");
		String contents = req.getParameter("contents");
		String errorMsg = "";
		List<GdMember> list = null;
		
		param.put("subject", subject);
		param.put("contents", contents);
		param.put("fromname", ShopConfig.getProperty("shopName"));
		param.put("fromemail", ShopConfig.getProperty("adminEmail"));
		
		if("direct".equals(type)){
			String[] emailArr = toEmail.split(",");
			for(int i = 0; i<emailArr.length; i++){
				list = memberService.getEmailChkList(emailArr[i]);
				if (list.size() <= 0){
					errorMsg=emailArr[i]+"은 유효하지 않은 메일입니다";
				} else {
					param.put("toemail", emailArr[i]);
					param.put("toname", list.get(0).getName());
					memberService.insertGdEmailer(param);
				}
				// toEmail(CSV) - hyunsoo@naver.com,naiive@hotmail.com,hyunsoo@gmail.com (검증된 회원이메일)
			}
		}else{
			//회원번호 받음
			//회원번호 이메일 조회
			//이메일로 이메일 발생
			list = memberService.getEmailChkListMno(param);
			int i = list.size(); // 1부터 시작
			if(i > 0){
				toEmail = "";
				for(int ixl=0;ixl<i;ixl++){
					if(ixl == 0){
						toEmail += list.get(ixl).getEmail(); // 0부터 시작
					}else{
						toEmail += ","+list.get(ixl).getEmail();	
					}
					param.put("toemail", list.get(ixl).getEmail());
					param.put("toname", list.get(ixl).getName());
					memberService.insertGdEmailer(param);
				}	
			}
			
		}
		
			// toEmail(CSV) - hyunsoo@naver.com,naiive@hotmail.com,hyunsoo@gmail.com (검증된 회원이메일)
		// subject, contents 를 받음
	logger.debug("subject==>"+subject);
	logger.debug("contents==>"+contents);
	model.addAttribute("errorMsg", errorMsg);
	model.addAttribute("toEmail", toEmail);
	model.addAttribute("type", type);
	model.addAttribute("size", size);
	model.addAttribute("ListToString", req.getParameter("toEmail"));
	model.addAttribute("subject", subject);
	model.addAttribute("contents", contents);
		return "shop/admin/member/email";
	}	
}
