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
package com.wepinit.wepinit_shop.xmall.admin.controller.event;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.AwsFileUtil;
import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.event.EventService;
import com.wepinit.wepinit_shop.xmall.admin.service.member.MemberService;
import com.wepinit.wepinit_shop.xmall.admin.vo.event.CouponVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.event.EventVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.event.SurveyVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.MemberVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCouponApply;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shop/admin/event/*")
public class EventController {

	private static final Logger logger = LoggerFactory.getLogger(EventController.class);

	@Resource
	EventService service;
	
	@Resource
	MemberService memberService;

	@RequestMapping(value = "list")
	public String list(@ModelAttribute("eventVO") EventVO eventVO,
			HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>event list!!!");
		}
		
		// 이벤트/쿠폰 관리 권한
		ShopLibFunction.menuAuthPermissionCheck(req, res, "event");
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		//판매사 검색
		param.put("schSellerCd", eventVO.getSchSellerCd());
		param.put("schSellerNm", eventVO.getSchSellerNm());
		
		//정렬
		param.put("schSort", eventVO.getSchSort());
		
		//총건수
		eventVO.setRowCount(service.getEventCount(eventVO));
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, eventVO.getPageSize());
				
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, eventVO.getRowStart());
		
		eventVO.setEventList(service.getEventList(param));

		model.addAttribute("eventVO", eventVO);
		
		return "/shop/admin/event/list";
	}
	
	@RequestMapping(value="indb")
	public String indb(@ModelAttribute("eventVO") EventVO eventVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		String returnUrl = "";
		logger.debug(eventVO.getMode());
		if(logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>event indb!!!");
		}
		
		if("".equals(eventVO.getMode())) {
//			String savedName = FileUtil.uploadFile2( 
//					ConfigClass.value("WEB_DIR_PATH") + "shop/data/upload/eventimg/", 
//					eventVO.getAttach_file().getOriginalFilename(), 
//					eventVO.getOld_attach().getBytes(), 
//					false);
			String savedName = FileUtil.uploadFile2(ConfigClass.value("WEB_DIR_PATH") + "shop/data/upload/eventimg/", eventVO.getAttach_file().getOriginalFilename(), eventVO.getAttach_file().getBytes(), false);
			eventVO.setAttach(savedName);
			service.insertEvent(eventVO);
			
			logger.debug("@@ savedName11 "+ savedName);
			
			returnUrl = "redirect:/shop/admin/event/list";
		}
		
		//수정
		if( "modify".equals(eventVO.getMode()) ){
			
			if(eventVO.getAttach_file() != null){
				if(!eventVO.getAttach_file().isEmpty()){
					logger.debug(" file >> "+eventVO.getAttach_file());
					
					//이벤트정보 등록
					FileUtil.deleteFile(ConfigClass.value("WEB_DIR_PATH") + "shop/data/upload/eventimg/", eventVO.getOld_attach());
					//FileUtil.uploadFiles(mpUpload, request, requestSet, ConfigClass.value("WEB_DIR_PATH") + "shop/data/upload/eventimg/", "attach", "old_attach", "old_attach");
					String savedName = FileUtil.uploadFile2( 
							ConfigClass.value("WEB_DIR_PATH") + "shop/data/upload/eventimg/",
							eventVO.getAttach_file().getOriginalFilename(), 
							eventVO.getAttach_file().getBytes(), 
							false);
					eventVO.setAttach(savedName);
					
					logger.debug("@@ savedName22"+savedName);
										
				}
			}

			service.updateEvent(eventVO);
			returnUrl = "redirect:/shop/admin/event/list";
		}  
		
		// 삭제
		if( "delete".equals(eventVO.getMode()) ){
			service.deleteEvent(eventVO);
			
			FileUtil.deleteFile(ConfigClass.value("WEB_DIR_PATH") + "shop/data/upload/eventimg/", eventVO.getOld_attach());
			
			returnUrl = "redirect:/shop/admin/event/list";
		}
		return returnUrl;		
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="indb2")
	public String indb2(HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		
		HashMap<String, Object> paramsDb = null; // DB처리를 위한 map
		String mode = "";
		
		paramsDb = new HashMap();
		mode = req.getParameter("mode");
		
		if( "open_modify".equals(mode) ){
			paramsDb.put("statVal", req.getParameter("statVal"));
			paramsDb.put("eventArr", req.getParameterValues("eventArr"));
			service.updateOpen(paramsDb);
		} else if( "approvalstatus_modify".equals(mode) ){
			paramsDb.put("statVal", req.getParameter("statVal"));
			paramsDb.put("eventArr", req.getParameterValues("eventArr"));
			paramsDb.put("sellercodeArr", req.getParameterValues("sellercodeArr"));
			service.updateApproval(paramsDb);
		}
		
		return "redirect:/shop/admin/event/list";
	}
	
	/**
	 * 관리자 > 이벤트/쿠폰관리 > 이벤트관리 > 이벤트만들기
	 */
	@RequestMapping(value="register")
	public String register(@ModelAttribute("eventVO") EventVO eventVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>event register!!!");
		}
		
		// 수정
		if( "modify".equals(eventVO.getMode()) ){
			eventVO.setEventObj(service.detailEvent(eventVO));
		}
		
		return "/shop/admin/event/register";
	}
	
	/**
	 * 관리자 > 이벤트/쿠폰관리 > 쿠폰기능설정
	 */
	@RequestMapping(value="coupon_cfg")
	public String couponCfg(@ModelAttribute("couponVO") CouponVO couponVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug("##### EVENT/COUPON > coupon_cfg");
		}
		
		String[] chk = StringUtil.split(ShopConfig.getProperty("cfgcoupon"), "^");
		model.addAttribute("cfgcoupon", chk);
		
		return "/shop/admin/event/coupon_cfg";
		
	}

	/**
	 * 관리자 > 이벤트/쿠폰관리 > 쿠폰INDB
	 */
	@RequestMapping(value="indb.coupon")
	public String indbCoupon(@ModelAttribute("couponVO") CouponVO couponVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug("##### EVENT/COUPON > coupon indb");
			logger.debug("##### couponVO : "+couponVO.toString());
			logger.debug("##### model : "+model.toString());
		}
		if("".equals(couponVO.getExcPrice())) {
			couponVO.setExcPrice("0");
		}
		
		String returnStr = "";
//		try {
//		System.out.println("couponVO.getMode()="+couponVO.getMode());
			if ("config".equals(couponVO.getMode())) {	// 쿠폰기능설정
				
//				ShopConfig.setProperty("cfgcoupon", req.getParameter("cfgChkVal"));
//				System.out.println("req.getParameter(cfgChkVal)"+req.getParameter("cfgChkVal"));
				
				if(logger.isDebugEnabled()){
					logger.debug("@@ coupon >> use_yn >>"+req.getParameter("use_yn")+" double >> "+req.getParameter("coupon_double"));
				}
				//쿠폰기능설정 > 쿠폰기능 사용여부(0:사용, 1:사용하지않음)
				ShopConfig.setProperty("use_yn", req.getParameter("use_yn"));
				//쿠폰기능설정 > 중복할인여부(0:쿠폰할인,회원할인 동시사용가능, 1:쿠폰할인만 사용, 2: 회원할인만 사용)
				ShopConfig.setProperty("range", req.getParameter("range"));
				//쿠폰기능설정 > 쿠폰 사용제한(0:하나의 주문에 여러 쿠폰 사용가능, 1:하나의 주문에는 오직 한개의 쿠폰만 사용)
				ShopConfig.setProperty("double", req.getParameter("coupon_double"));
				
				returnStr = "redirect:/shop/admin/event/coupon_cfg";
			} else if ("applyAdd".equals(couponVO.getMode())) {	// 쿠폰발급
				
				String errMsg = "";
				boolean valid = true;
				/** 파라미터 설정 **/
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("couponcd", couponVO.getCouponcd());	
				param.put("membertype", couponVO.getMembertype());	
				param.put("membergrpsno", StringUtil.nvl(couponVO.getMembergrpsno(), "0"));	
				// 선택한 쿠폰 발급받은 멤버리스트	
				List<GdCouponApply> chkList = service.getCouponMemberNew(param);
				if (chkList.size() > 0) {
					for (GdCouponApply obj : chkList) {
						if ( "0".equals(obj.getMembertype()) ) {
							errMsg = "이미 발급되어진 쿠폰입니다.";
							valid = false;
							break;
						}
						if ( couponVO.getMembergrpsno().equals(Integer.toString(obj.getMembergrpsno())) ) {
							errMsg = "이미 발급되어진 쿠폰입니다.";
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
				/** 파라미터 설정 **/
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("sno", req.getParameter("sno"));	
				service.deleteCouponApply(param);
				model.addAttribute("RESULT", true);
				model.addAttribute("couponcd", req.getParameter("couponcd"));
				returnStr = "dojson";
			} else if ("register".equals(couponVO.getMode()) || "modify".equals(couponVO.getMode())) {	// 쿠폰만들기/수정
				// 쿠폰금액 값 설정
				couponVO.setPerc(couponVO.getPerc().replaceAll("유로", ""));
				couponVO.setPrice(couponVO.getPrice()+couponVO.getPerc());
				//쿠폰사용제한 공란 값
				if(couponVO.getExcPrice()==null || couponVO.getExcPrice()=="") {
					couponVO.setExcPrice("0");
				}
				//쿠폰 최대 할인액
				if ("".equals(couponVO.getPerc()) || "0".equals(StringUtil.nvl(couponVO.getMaxprice(), "0"))) {
					couponVO.setMaxprice("");
				}
				// 쿠폰적용기간 설정
				if("1".equals(couponVO.getPriodtype())) {
					couponVO.setSdate(couponVO.getPriod());
				}
				
				couponVO.setRefer(req.getParameterValues("e_refer[]"));
				int newCouponcd = service.insertCoupon(couponVO);	// 등록 또는 수정처리
				couponVO.setCouponcd(Integer.toString(newCouponcd));
				
				String couponimg = "";
				if (!couponVO.getImgFile().isEmpty()) {
					AwsFileUtil awsfileUtil = new AwsFileUtil();
					String awsPath = "coupon/" + String.valueOf(newCouponcd) + "/";

					// S3 파일삭제
					if (FileUtil.getChkAwsFile(couponVO.getCouponimg())) {
						String awsKey = awsPath + FileUtil.getUrlFileName(couponVO.getCouponimg());
						awsfileUtil.delete(awsKey);
					}
					
					// AWS 파일업로드
					couponimg = FileUtil.awsUploadFile(couponVO.getImgFile().getOriginalFilename(), couponVO.getImgFile().getBytes(), awsPath);
				}
				
				couponVO.setCouponimg(couponimg);
				service.updateCouponImage(couponVO);
				
				model.addAttribute("RESULT", true);
				model.addAttribute("couponcd", newCouponcd);
				//returnStr = "dojson";
				returnStr = "redirect:/shop/admin/event/coupon";
			} else if ("delete".equals(couponVO.getMode())) {	// 쿠폰삭제
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("couponcd", couponVO.getCouponcd());	
				service.deleteCoupon(param);

				// S3 파일삭제
				AwsFileUtil awsfileUtil = new AwsFileUtil();
				String awsKey = "couponcd/" + couponVO.getCouponcd();
				awsfileUtil.deleteList(awsKey);
				
				model.addAttribute("RESULT", true);
				returnStr = "dojson";
			} else if ("modifyApply".equals(couponVO.getMode())) {
				//2017-09-06 추가 - 쿠폰 발급 내역 사용여부 수정
				service.updateCouponApply(couponVO);
				
				model.addAttribute("result", true);
				returnStr ="dojson";
			}
			
//		} catch (Exception e) {
//			model.addAttribute("RESULT", false);
//			model.addAttribute("RESULT_MSG", "처리중 일시적인 오류가 발생하였습니다. 잠시후 다시 시도해 주세요.");
//			e.printStackTrace();
//			throw new BizException("common.00004");
//		} 
		
		return returnStr;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="indb.coupon2")
	public String indbCoupon2(HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		
		HashMap<String, Object> paramsDb = null; // DB처리를 위한 map
		String mode = "";
		
		paramsDb = new HashMap();
		mode = req.getParameter("mode");
		
		if( "open_modify".equals(mode) ){
			paramsDb.put("statVal", req.getParameter("statVal"));
			paramsDb.put("couponArr", req.getParameterValues("couponArr"));
			service.updateCouponOpen(paramsDb);
		} else if( "approvalstatus_modify".equals(mode) ){
			paramsDb.put("statVal", req.getParameter("statVal"));
			paramsDb.put("couponArr", req.getParameterValues("couponArr"));
			service.updateCouponApproval(paramsDb);
		}
		
		return "redirect:/shop/admin/event/coupon";
	}
	
	/**
	 * 쿠폰리스트 페이지
	 * @param couponVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="coupon")
	public String coupon(@ModelAttribute("couponVO") CouponVO couponVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug("##### couponVO : " + couponVO.toString());
		}
		
		/** 검색설정 **/
		Map<String, Object> param = new HashMap<String, Object>();
		
		// 검색타입(쿠폰검색[통합])
		param.put("skey", couponVO.getSkey());
		// 검색어(쿠폰검색[통합])
		param.put("sword", couponVO.getSword());
		// 쿠폰기능
		//param.put("ability", couponVO.getAbility());
		// 노출스킨
		param.put("schSkin", couponVO.getSchSkin());
		// 적용상품범위
		param.put("goodstype", couponVO.getGoodstype());	
		// 적용상품범위 > 분류선택 - 카테고리
		param.put("category", couponVO.getCategory());	
		// 적용상품범위 > 분류선택 - 분류선택(key)
		param.put("gkey", couponVO.getGkey());	
		// 적용상품범위 > 분류선택 - 분류선택(word)
		param.put("gword", couponVO.getGword());	
		// 쿠폰발급방식
		param.put("coupontype", couponVO.getCoupontype());	
		// 쿠폰발행일/기간검색(key)
		param.put("dtkind", couponVO.getDtkind());	
		// 쿠폰발행일/기간검색(word)
		if (couponVO.getRegdt() != null && couponVO.getRegdt().length == 2 ) {
			param.put("regdts", couponVO.getRegdt()[0]);	
			param.put("regdte", couponVO.getRegdt()[1]);	
		}
		//적용상품범위 > 분류선택 - 총 카테고리배열
		param.put("categoryArr", couponVO.getCategoryArr());
		
		//판매사 검색
		param.put("schSellerCd", couponVO.getSchSellerCd());
		param.put("schSellerNm", couponVO.getSchSellerNm());
		
		//정렬
		param.put("schSort", couponVO.getSchSort());
		
		//총건수
		couponVO.setRowCount(service.getCouponCount(param));
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, couponVO.getPageSize());
				
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, couponVO.getRowStart());
			
		couponVO.setCouponList(service.getCouponList(param));
		
		model.addAttribute("couponVO", couponVO);
		
		return "/shop/admin/event/coupon";
		
	}
	
	/**
	 * 쿠폰발급/조회 페이지
	 * @param couponVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="coupon_apply")
	public String couponApply(@ModelAttribute("couponVO") CouponVO couponVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug("##### EVENT/COUPON :: couponVO >> " + couponVO.toString());
		}
		
		/** 검색 파라미터 설정 **/
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("couponcd", couponVO.getCouponcd());	
		
		// 전체회원수 조회
		couponVO.setCouponGrpTotal(service.getCouponApply1TotalCount());
		// 쿠폰발급을 위한 그룹 조회
		couponVO.setCouponGrpList(service.getCouponApply1());
		// 선택한 쿠폰 조회
		couponVO.setCouponInfo(service.getCouponApply2(param));

		// 선택한 쿠폰 발급받은 멤버수
		couponVO.setCouponApplyGrpTotal(service.getCouponMemberCount(param));
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, couponVO.getPageSize());
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, couponVO.getRowStart());
		// 선택한 쿠폰 발급받은 멤버리스트	
		couponVO.setCouponApplyGrpList(service.getCouponMemberNew(param));
		
		model.addAttribute("couponInfo", couponVO.getCouponInfo());
		
		return "/shop/admin/event/coupon_apply";
		
	}

	/**
	 * 쿠폰만들기(수정) 페이지
	 * @param couponVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="coupon_register")
	public String couponRegister(@ModelAttribute("couponVO") CouponVO couponVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug("##### EVENT/COUPON REGISTER :: couponVO >> " + couponVO.toString());
		}
		
		// 수정의 경우 DB조회
		if ("modify".equals(couponVO.getMode())) {
			/** 파라미터 설정 **/
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("couponcd", couponVO.getCouponcd());	
			if(logger.isDebugEnabled()) {
				logger.debug("#####@@ couponVO :: couponVO >> " + couponVO.toString());
			}
			couponVO.setCouponInfo(service.getCouponApply2(param));

//			model.addAttribute("editFlg", true);
			
			model.addAttribute("couponInfo", couponVO.getCouponInfo());
			model.addAttribute("couponCategoryArr", service.getCouponCategory(param));
			model.addAttribute("couponGoodsList", service.getCouponGoods(param));
		} else {
			couponVO.setMode("register");
//			model.addAttribute("editFlg", false);- 
		}
		
		return "/shop/admin/event/coupon_register";
		
	}
	
	/**
	 * 쿠폰리스트 > 회원 발급하기 > 회원검색하기 팝업
	 * @param memberVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="popup_member")
	public String popup_member(@ModelAttribute("memberVO") MemberVO memberVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
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
		//memberVO.setGdMemberList(service.getMemberList(memberVO));		
		return "/shop/admin/event/popup_member";
	}
	
	/**
	 * 쿠폰발급회원 내역 조회
	 * @param couponVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="popup_apply")
	public String popupApplyMember(@ModelAttribute("couponVO")CouponVO couponVO) throws Exception{

		// 쿠폰발급회원 내역 조회
		couponVO.setCouponApplyMember(service.getCouponApplyMember(couponVO));
		
		return "/shop/admin/event/popup_apply";
	}
	
	/**
	 * 설문조사 리스트
	 * @param surveyVO
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value="surveyList")
	public String surveyList(@ModelAttribute("surveyVO") SurveyVO surveyVO) throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("####[EventController > surveyList] : {}", surveyVO.getPageNo() );
		}
		//총건수
		surveyVO.setRowCount(service.getSurveyCount(surveyVO));
		surveyVO.setSurveyList(service.getSurveyList(surveyVO));
		
		return "/shop/admin/event/surveyList";
	}
	
	/**
	 * requset 로 넘어온 mode 로 구분하여 등록 혹은 수정페이지로 이동
	 * @Param surveyVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="survey")
	public String survey(@ModelAttribute("surveyVO") SurveyVO surveyVO) throws Exception{
		if(logger.isDebugEnabled()) {
			logger.debug("####[EventController  survey]");
		}
		
		SurveyVO detail = null;
		
		//mode 가 modfiy로 넘어온 경우는 수정페이지로 이동
		if("modify".equals(surveyVO.getMode())){
			
			//surveySno 에 해당하는 설문조사 데이터를 조회
			detail = service.getDetailSurvey(surveyVO.getSurveySno());
			
			//parameter SurveySno로 잘못된 접근을 막기위한 bizException 처리 
			if(detail == null || "".equals(detail.getSurveySno())) {
				//존재하지 않는 게시글입니다.
				throw new BizException("survey.00002");
			}
			
			//설문조사 수정페이지 이동
			surveyVO.setDetailSurveyVO(detail);
			
		}
		
		return "/shop/admin/event/survey";
	}
		
	/**
	 * 설문조사 등록, 수정 , 삭제 ,게시여부 등록을 위한 페이지
	 * mode 의 등록: insert 수정 : modify 삭제 : delete 게시여부 : open_modify 로 구분을 한다.
	 * @Param surveyVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="surveyIndb")
	public String surveyIndb(@ModelAttribute SurveyVO surveyVO, HttpServletRequest request) throws Exception{
		logger.debug("EventController surveyIndb >> {} >>" , surveyVO);
		
		String mode = StringUtil.nvl(surveyVO.getMode()); 		//수정종류
		String sno = StringUtil.nvl(surveyVO.getSurveySno()); 	//설문조사 인덱스  
		
		//설문조사 수정
		if("modify".equals(mode)) { 
			service.modifySurvey(surveyVO);
		}else if("open_modify".equals(surveyVO.getMode())) {
			//게시, 게시취소
			service.updateSurveyOpen(surveyVO);
			 //게시여부 처리 후 페이지 정보를 담기위해 queryString 사용 
			return "redirect:/shop/admin/event/surveyList?"+request.getQueryString();
		}else if("delete".equals(mode)){
			//설문조사 삭제
			service.deleteSurvey(sno);
			 //게시여부 처리 후 페이지 정보를 담기위해 queryString 사용 
			return "redirect:/shop/admin/event/surveyList?"+request.getQueryString();
		}else if("insert".equals(mode)){
			//설문조사 등록시
			//gd_survey  데이터 삽입
			//gd_survey_question 데이터 삽입
			service.setSurvey(surveyVO);
		}else {
			//mode 에 다른 조건을 입력받았을 경우
			//survey.00001 : 잘못된 접근입니다.
			throw new BizException("survey.00001");
		}
		
		return "redirect:/shop/admin/event/surveyList";
	}
	
	
	/**
	 * 설문조사 상세페이지
	 * @param surveyVO
	 * @return
	 */
	  @RequestMapping(value="surveyDetail") 
	  public String surveyDetail(@ModelAttribute("surveyVO") SurveyVO surveyVO){
		  if(logger.isDebugEnabled()) {
			  logger.debug("####[EventController > surveyDetail"); 
		  }
		  surveyVO.setDetailSurveyVO(service.getDetailSurvey(surveyVO.getSurveySno()));
		  return "/shop/admin/event/surveyDetail"; 
	  }

	
}

