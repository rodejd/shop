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
package com.wepinit.wepinit_shop.xmall.seller.event.controller;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.member.MemberService;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.MemberVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.session.SellerSessionObject;
import com.wepinit.wepinit_shop.xmall.seller.event.service.SellerEventService;
import com.wepinit.wepinit_shop.xmall.seller.event.vo.SellerCouponFM;
import com.wepinit.wepinit_shop.xmall.seller.event.vo.SellerEventFM;
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
import java.util.Map;

@Controller
@RequestMapping("/shop/seller/event")
public class SellerEventController {

	private static final Logger logger = LoggerFactory.getLogger(SellerEventController.class);

	@Resource
	SellerEventService service;
	
	@Resource
	MemberService memberService;
	
	/**
	 * 관리자 > 이벤트/쿠폰관리 > 이벤트관리 > 이벤트리스트
	 */
	@RequestMapping(value="/eventList")
	public String eventList(@ModelAttribute SellerEventFM sellerEventFM, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		//판매사가 등록되 있는 경우에만 노출 조건
		sellerEventFM.setSchSellerYn("Y");
		//판매사 검색 : session에서 조회 
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		if(sellerSO != null){
			sellerEventFM.setSchSellerCd(sellerSO.getUserInfo().getSellerCd());
			model.addAttribute("sellerEventVO", service.getEventList(sellerEventFM));
		}else{
			model.addAttribute("sellerEventVO", null);
		}
		
		model.addAttribute("sellerEventFM", sellerEventFM);
		
		return "seller/event/eventList";
	}
	
	/**
	 * 관리자 > 이벤트/쿠폰관리 > 이벤트관리 > 이벤트만들기
	 */
	@RequestMapping(value="eventRegister")
	public String eventRegister(@ModelAttribute SellerEventFM sellerEventFM, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 수정
		if( "modify".equals(sellerEventFM.getMode()) ){
			sellerEventFM.setEventVO(service.detailEvent(sellerEventFM));
		}else{
			SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
			if(sellerSO != null){
				model.addAttribute("setSellerCd", sellerSO.getUserInfo().getSellerCd());
			}
		}
		model.addAttribute("SellerEventFM", sellerEventFM);
		
		return "seller/event/eventRegister";
	}
	
	/**
	 * 관리자 > 이벤트/쿠폰관리 > 이벤트관리 > 등록/수정
	 */
	@RequestMapping(value="selEventIndb")
	public String selEventIndb(@ModelAttribute SellerEventFM sellerEventFM, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		String returnUrl = "";
		logger.debug("mode : " + sellerEventFM.getMode());
		
		//등록
		if( "".equals(sellerEventFM.getMode()) ) {
			String savedName = FileUtil.uploadFile2(ConfigClass.value("WEB_DIR_PATH") + "shop/data/upload/eventimg/", sellerEventFM.getAttachFile().getOriginalFilename(), sellerEventFM.getAttachFile().getBytes(), false);
			sellerEventFM.getEventVO().setAttach(savedName);
			logger.debug("savedName : "+savedName);
			service.insertEvent(sellerEventFM.getEventVO());
			returnUrl = "redirect:/shop/seller/event/eventList";
		}
		
		//수정
		if( "modify".equals(sellerEventFM.getMode()) ){
			if(sellerEventFM.getAttachFile() != null){
				if(!sellerEventFM.getAttachFile().isEmpty()){
					logger.debug(" file : "+sellerEventFM.getAttachFile());
					
					//이벤트정보 등록
					FileUtil.deleteFile(ConfigClass.value("WEB_DIR_PATH") + "shop/data/upload/eventimg/", sellerEventFM.getOldAttach());
					String savedName = FileUtil.uploadFile2( 
							ConfigClass.value("WEB_DIR_PATH") + "shop/data/upload/eventimg/", 
							sellerEventFM.getAttachFile().getOriginalFilename(), 
							sellerEventFM.getAttachFile().getBytes(), 
							false);
					sellerEventFM.getEventVO().setAttach(savedName);
					logger.debug("savedName : "+savedName);
				}
			}
			service.updateEvent(sellerEventFM.getEventVO());
			returnUrl = "redirect:/shop/seller/event/eventList";
		}
		
		return returnUrl;		
	}
	
	/**
	 * 관리자 > 이벤트/쿠폰관리 > 이벤트관리 > 리스트에서 게시여부 수정
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="selEventIndb2")
	public String selEventIndb2(HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		HashMap<String, Object> paramsDb = null; // DB처리를 위한 map
		String mode = "";
		
		paramsDb = new HashMap();
		mode = req.getParameter("mode");
		
		if( "open_modify".equals(mode) ){
			paramsDb.put("statVal", req.getParameter("statVal"));
			paramsDb.put("eventArr", req.getParameterValues("eventArr"));
			service.updateOpen(paramsDb);
		}
		
		return "redirect:/shop/seller/event/eventList";
	}
	
	/**
	 * 관리자 > 이벤트/쿠폰관리 > 쿠폰리스트
	 */
	@RequestMapping(value="couponList")
	public String couponList(@ModelAttribute SellerCouponFM sellerCouponFM, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		/** 검색설정 **/
		//Map<String, Object> param = new HashMap<String, Object>();
		
		// 검색타입(쿠폰검색[통합])
		//param.put("skey", sellerCouponFM.getSkey());
		// 검색어(쿠폰검색[통합])
		//param.put("sword", sellerCouponFM.getSword());
		// 쿠폰기능
		//param.put("ability", sellerCouponFM.getAbility());
		// 적용상품범위
		//param.put("goodstype", sellerCouponFM.getGoodstype());	
		// 적용상품범위 > 분류선택 - 카테고리
		//param.put("category", sellerCouponFM.getCategory());	
		// 적용상품범위 > 분류선택 - 분류선택(key)
		//param.put("gkey", sellerCouponFM.getGkey());	
		// 적용상품범위 > 분류선택 - 분류선택(word)
		//param.put("gword", sellerCouponFM.getGword());	
		// 쿠폰발급방식
		//param.put("coupontype", sellerCouponFM.getCoupontype());	
		// 쿠폰발행일/기간검색(key)
		//param.put("dtkind", sellerCouponFM.getDtkind());	
		// 쿠폰발행일/기간검색(word)
		if (sellerCouponFM.getRegdt() != null && sellerCouponFM.getRegdt().length == 2 ) {
			//param.put("regdts", sellerCouponFM.getRegdt()[0]);	
			//param.put("regdte", sellerCouponFM.getRegdt()[1]);	
			sellerCouponFM.setRegdts(sellerCouponFM.getRegdt()[0]);
			sellerCouponFM.setRegdte(sellerCouponFM.getRegdt()[1]);
		}
		//적용상품범위 > 분류선택 - 총 카테고리배열
		//param.put("categoryArr", sellerCouponFM.getCategoryArr());
		
		//판매사 검색
		//param.put("schSellerCd", sellerCouponFM.getSchSellerCd());
		//param.put("schSellerNm", sellerCouponFM.getSchSellerNm());
		
		//판매사가 등록되 있는 경우에만 노출 조건
		sellerCouponFM.setSchSellerYn("Y");
		//판매사 검색 : session에서 조회 
		SellerSessionObject sellerSO = SellerSessionObject.getSessionObject(req);
		if(sellerSO != null){
			sellerCouponFM.setSchSellerCd(sellerSO.getUserInfo().getSellerCd());
			model.addAttribute("sellerCouponFM", service.getCouponList(sellerCouponFM));
		}else{
			model.addAttribute("sellerCouponFM", null);
		}
		
		return "seller/event/couponList";
	}
	
	/**
	 * 관리자 > 이벤트/쿠폰관리 > 쿠폰관리 > 등록/수정
	 */
	@RequestMapping(value="selIndbCoupon")
	public String selIndbCoupon(
			@ModelAttribute SellerCouponFM sellerCouponFM, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		String returnStr = "";
		/*if ("applyAdd".equals(couponVO.getMode())) {	// 쿠폰발급
			String errMsg = "";
			boolean valid = true;
			// 파라미터 설정 
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("couponcd", couponVO.getCouponcd());	
			param.put("membertype", couponVO.getMembertype());	
			param.put("membergrpsno", StringUtil.nvl(couponVO.getMembergrpsno(), "0"));	
			// 선택한 쿠폰 발급받은 멤버리스트	
			List<GdCouponApply> chkList = service.getCouponMemberNew(param);
			if (chkList.size() > 0) {
				for (GdCouponApply obj : chkList) {
					if ( "0".equals(obj.getMembertype()) ) {
						errMsg = "이미 발급되어진 쿠폰입니다.!";
						valid = false;
						break;
					}
					if ( couponVO.getMembergrpsno().equals(obj.getMembergrpsno()) ) {
						errMsg = "이미 발급되어진 쿠폰입니다.!";
						valid = false;
						break;
					}
					if ( "2".equals(couponVO.getMembertype()) ) {
						
						for(String mid : couponVO.getMids()) {
							if(obj.getMno().equals(mid)) {
								errMsg = "중복되는 회원이 있습니다.";
								valid = false;
								break;
							}
						}
					}
				}
			}
			
			model.addAttribute("RESULT", valid);
			if (valid) {
				// 저장 처리
				if ( "2".equals(couponVO.getMembertype()) ) {
					service.deleteCouponApplyMember(param);
					for(String mid : couponVO.getMids()) {
						param.put("mno", mid);
						service.insertCouponApplyMember(param);
					}
				} else {
					service.insertCouponApply(param);
				}
				
			} else {
				// 에러 처리
				model.addAttribute("RESULT_MSG", errMsg);
			}
			
			returnStr = "dojson";
		} else if ("delApply".equals(couponVO.getMode())) {	// 쿠폰발급취소
			// 파라미터 설정
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("sno", req.getParameter("sno"));	
			service.deleteCouponApply(param);
			model.addAttribute("RESULT", true);
			model.addAttribute("couponcd", req.getParameter("couponcd"));
			returnStr = "dojson";
		} else */
		if ("register".equals(sellerCouponFM.getMode()) || "modify".equals(sellerCouponFM.getMode())) {	// 쿠폰만들기/수정
			// 쿠폰금액 값 설정
			sellerCouponFM.setPerc(sellerCouponFM.getPerc().replaceAll("원", ""));
			sellerCouponFM.getCouponVO().setPrice(sellerCouponFM.getCouponVO().getPrice()+sellerCouponFM.getPerc());
			//쿠폰사용제한 공란 값
			/*if(sellerCouponFM.getExcPrice() == null || "".equals(sellerCouponFM.getExcPrice())){
				sellerCouponFM.setExcPrice("0");
			}*/
			// 쿠폰적용기간 설정
			if("1".equals(sellerCouponFM.getCouponVO().getPriodtype())) {
				sellerCouponFM.getCouponVO().setSdate(sellerCouponFM.getPriod());
			}
			
			sellerCouponFM.setRefer(req.getParameterValues("e_refer[]"));
			int newCouponcd = service.insertCoupon(sellerCouponFM);	// 등록 또는 수정처리
			
			model.addAttribute("RESULT", true);
			model.addAttribute("couponcd", newCouponcd);
			returnStr = "dojson";
		}
		/*else if ("modifyApply".equals(couponVO.getMode())) {
			service.updateCouponApply(couponVO);
			
			model.addAttribute("result", true);
			returnStr ="dojson";
		}*/
			
		return returnStr;
	}
	
	/**
	 * 관리자 > 이벤트/쿠폰관리 > 쿠폰관리 > 리스트에서 게시여부 수정
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="selIndbCoupon2")
	public String selIndbCoupon2(HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		
		HashMap<String, Object> paramsDb = null; // DB처리를 위한 map
		String mode = "";
		
		paramsDb = new HashMap();
		mode = req.getParameter("mode");
		
		if( "open_modify".equals(mode) ){
			paramsDb.put("statVal", req.getParameter("statVal"));
			paramsDb.put("couponArr", req.getParameterValues("couponArr"));
			service.updateCouponOpen(paramsDb);
		}
		
		return "redirect:/shop/seller/event/couponList";
	}
	
	/**
	 * 관리자 > 이벤트/쿠폰관리 > 쿠폰발급/조회
	 */
	@RequestMapping(value="selCouponApply")
	public String selCouponApply(@ModelAttribute SellerCouponFM sellerCouponFM, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 전체회원수 조회
		service.getCouponApply1TotalCount(sellerCouponFM);
		// 쿠폰발급을 위한 그룹 조회
		service.getCouponApply1(sellerCouponFM);
		// 선택한 쿠폰 조회
		service.getCouponApply2(sellerCouponFM);
		// 선택한 쿠폰 발급받은 멤버수
		service.getCouponMemberCount(sellerCouponFM);
		// 선택한 쿠폰 발급받은 멤버리스트	
		service.getCouponMemberNew(sellerCouponFM);
		
		model.addAttribute("couponInfo", sellerCouponFM);
		
		return "seller/event/couponApply";
	}

	/**
	 * 관리자 > 이벤트/쿠폰관리 > 쿠폰만들기
	 */
	@RequestMapping(value="couponRegister")
	public String couponRegister(@ModelAttribute SellerCouponFM sellerCouponFM, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 수정의 경우 DB조회
		if ("modify".equals(sellerCouponFM.getMode())) {
			model.addAttribute("sellerCouponFM", service.getCouponApply2(sellerCouponFM));
			model.addAttribute("couponCategoryArr", service.getCouponCategory(sellerCouponFM));
			model.addAttribute("couponGoodsList", service.getCouponGoods(sellerCouponFM));
		} else {
			sellerCouponFM.setMode("register");
			model.addAttribute("sellerCouponFM", sellerCouponFM);
		}
		
		return "seller/event/couponRegister";
	}
	
	/*
	 * 회원리스트
	 * 관리자 > 이벤트/쿠폰관리 > 쿠폰관리 > 쿠폰리스트 > 회원 발급하기 > 회원검색하기 팝업
	 */
	@RequestMapping(value="selPopupMember")
	public String selPopupMember(@ModelAttribute("memberVO") MemberVO memberVO
			, HttpServletRequest req , HttpServletResponse res, Model model) throws Exception {
		
		// 회원관리 접근 권한 처리
		memberService.memberMenuAuth(req, res);

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
		
		//총 건수
		memberVO.setTotalCount(memberService.getMemberTotalCount());
		//검색 총 건수
		memberVO.setRowCount(memberService.getMemberCount(param));
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, memberVO.getPageSize());
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, memberVO.getRowStart());
		
		//팝업상세
		param.put("popupDetail", memberVO.getPopupDetail());
		
		// 회원 그룹별 조회
		memberVO.setGdMemberList(memberService.getMemberList(param)); 	// 회원관리 > 회원일괄관리 > SMS일괄발송(회원리스트)
		
		return "seller/event/couponPopupMember";
	}
	
	/**
	 * 쿠폰발급회원 내역 조회
	 * @param couponVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="selPopupApply")
	public String selPopupApply(@ModelAttribute SellerCouponFM sellerCouponFM, Model model) throws Exception{
		
		// 쿠폰발급회원 내역 조회
		sellerCouponFM.setCouponApplyMember(service.getCouponApplyMember(sellerCouponFM));
		model.addAttribute("sellerCouponFM", sellerCouponFM);
		
		return "seller/event/couponPopupApply";
	}
	
}
