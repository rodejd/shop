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
package com.wepinit.wepinit_shop.xmall.admin.controller.seller;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.AwsFileUtil;
import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.event.EventService;
import com.wepinit.wepinit_shop.xmall.admin.service.goods.GoodsService;
import com.wepinit.wepinit_shop.xmall.admin.service.member.MemberService;
import com.wepinit.wepinit_shop.xmall.admin.vo.event.CouponVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.event.EventVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.goods.GoodsVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.MemberVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
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
@RequestMapping("/shop/admin/seller/*")
public class AdminSellerEventController {

	private static final Logger logger = LoggerFactory.getLogger(AdminSellerEventController.class);

	@Resource
	EventService service;
	
	@Resource
	MemberService memberService;
	
	@Resource
	GoodsService serviceG;
	
	@RequestMapping(value = "sellerEventList")
	public String sellerEventList(@ModelAttribute("eventVO") EventVO eventVO,
			HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>seller event sellerEventList!!!");
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		//판매사가 등록되 있는 경우에만 노출 조건
		param.put("sellerYn", "Y");
		eventVO.setSellerYn("Y");
		
		//dataCodeVO.setDataCodeList(service.getDataCodeList(dataCodeVO));					// 연령별매출분석LIST
		
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
		
		return "/shop/admin/seller/eventList";
	}
	
	@RequestMapping(value="sellerEventIndb")
	public String sellerEventIndb(@ModelAttribute("eventVO") EventVO eventVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
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
			
			returnUrl = "redirect:/shop/admin/seller/sellerEventList";
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
			returnUrl = "redirect:/shop/admin/seller/sellerEventList";
		}  
		
		// 삭제
		if( "delete".equals(eventVO.getMode()) ){
			service.deleteEvent(eventVO);
			
			FileUtil.deleteFile(ConfigClass.value("WEB_DIR_PATH") + "shop/data/upload/eventimg/", eventVO.getOld_attach());
			
			returnUrl = "redirect:/shop/admin/seller/sellerEventList";
		}
		return returnUrl;		
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="sellerEventIndb2")
	public String sellerEventIndb2(HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		
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
		
		return "redirect:/shop/admin/seller/sellerEventList";
	}
	
	/**
	 * 관리자 > 이벤트/쿠폰관리 > 이벤트관리 > 이벤트만들기
	 */
	@RequestMapping(value="sellerEventRegister")
	public String sellerEventRegister(@ModelAttribute("eventVO") EventVO eventVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>event register!!!");
		}
		
		// 수정
		if( "modify".equals(eventVO.getMode()) ){
			eventVO.setEventObj(service.detailEvent(eventVO));
		}
		
		return "/shop/admin/seller/eventRegister";
	}
	
	/**
	 * 관리자 > 이벤트/쿠폰관리 > 쿠폰기능설정
	 * 판매사관리에선 사용안함
	 */
	/*@RequestMapping(value="sellerCouponCfg")
	public String couponCfg(@ModelAttribute("couponVO") CouponVO couponVO, 
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug("##### EVENT/COUPON > coupon_cfg");
		}
		
		String[] chk = StringUtil.split(ShopConfig.getProperty("cfgcoupon"), "^");

	
		model.addAttribute("cfgcoupon", chk);
		
		return "/shop/admin/seller/coupon_cfg";
		
	}*/

	/**
	 * 관리자 > 이벤트/쿠폰관리 > 쿠폰INDB
	 */
	@RequestMapping(value="sellerIndbCoupon")
	public String sellerIndbCoupon(@ModelAttribute("couponVO") CouponVO couponVO,
			HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug("##### EVENT/COUPON > coupon indb");
			logger.debug("##### couponVO : "+couponVO.toString());
			logger.debug("##### model : "+model.toString());
		}
		
		String returnStr = "";
//		try {
//		System.out.println("couponVO.getMode()="+couponVO.getMode());
			if ("config".equals(couponVO.getMode())) {	// 쿠폰기능설정
				
//				ShopConfig.setProperty("cfgcoupon", req.getParameter("cfgChkVal"));
//				System.out.println("req.getParameter(cfgChkVal)"+req.getParameter("cfgChkVal"));
				
				/*if(logger.isDebugEnabled()){
					logger.debug("@@ coupon >> use_yn >>"+req.getParameter("use_yn")+" double >> "+req.getParameter("coupon_double"));
				}
				//쿠폰기능설정 > 쿠폰기능 사용여부(0:사용, 1:사용하지않음)
				ShopConfig.setProperty("use_yn", req.getParameter("use_yn"));
				//쿠폰기능설정 > 중복할인여부(0:쿠폰할인,회원할인 동시사용가능, 1:쿠폰할인만 사용, 2: 회원할인만 사용)
				ShopConfig.setProperty("range", req.getParameter("range"));
				//쿠폰기능설정 > 쿠폰 사용제한(0:하나의 주문에 여러 쿠폰 사용가능, 1:하나의 주문에는 오직 한개의 쿠폰만 사용)
				ShopConfig.setProperty("double", req.getParameter("coupon_double"));
				
				returnStr = "redirect:/shop/admin/seller/coupon_cfg";*/
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
				/** 파라미터 설정 **/
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("sno", req.getParameter("sno"));	
				service.deleteCouponApply(param);
				model.addAttribute("RESULT", true);
				model.addAttribute("couponcd", req.getParameter("couponcd"));
				returnStr = "dojson";
			} else if ("register".equals(couponVO.getMode()) || "modify".equals(couponVO.getMode())) {	// 쿠폰만들기/수정
				// 쿠폰금액 값 설정
				couponVO.setPerc(couponVO.getPerc().replaceAll("원", ""));
				couponVO.setPrice(couponVO.getPrice()+couponVO.getPerc());
				//쿠폰사용제한 공란 값
				if(couponVO.getExcPrice()==null || couponVO.getExcPrice()=="")
				{
					couponVO.setExcPrice("0");
				}
				//쿠폰 최대 할인액
				if ("".equals(couponVO.getPerc())) {
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
				returnStr = "redirect:/shop/admin/seller/sellerCouponList";
			} else if ("delete".equals(couponVO.getMode())) {	// 쿠폰삭제
				/** 파라미터 설정 **/
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("couponcd", couponVO.getCouponcd());	
				service.deleteCoupon(param);
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
	@RequestMapping(value="sellerIndbCoupon2")
	public String sellerIndbCoupon2(HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		
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
		
		return "redirect:/shop/admin/seller/sellerCouponList";
	}
	
	/**
	 * 관리자 > 이벤트/쿠폰관리 > 쿠폰리스트
	 */
	@RequestMapping(value="sellerCouponList")
	public String sellerCouponList(@ModelAttribute("couponVO") CouponVO couponVO, 
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
		
		//정렬
		param.put("schSort", couponVO.getSchSort());
		
		//판매사 검색
		param.put("schSellerCd", couponVO.getSchSellerCd());
		param.put("schSellerNm", couponVO.getSchSellerNm());
		
		//판매사가 등록되 있는 경우에만 노출 조건
		param.put("sellerYn", "Y");
		
		//총건수
		couponVO.setRowCount(service.getCouponCount(param));
		
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, couponVO.getPageSize());
				
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, couponVO.getRowStart());
			
		couponVO.setCouponList(service.getCouponList(param));
		
		model.addAttribute("couponVO", couponVO);
		
		return "/shop/admin/seller/couponList";
		
	}
	
	/**
	 * 관리자 > 이벤트/쿠폰관리 > 쿠폰발급/조회
	 */
	@RequestMapping(value="sellerCouponApply")
	public String sellerCouponApply(@ModelAttribute("couponVO") CouponVO couponVO, 
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
		
		return "/shop/admin/seller/couponApply";
		
	}

	/**
	 * 관리자 > 이벤트/쿠폰관리 > 쿠폰만들기(수정) 입력화면
	 */
	@RequestMapping(value="sellerCouponRegister")
	public String sellerCouponRegister(@ModelAttribute("couponVO") CouponVO couponVO, 
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
		
		return "/shop/admin/seller/couponRegister";
		
	}
	
	/*
	 * 회원리스트
	 * 관리자 > 이벤트/쿠폰관리 > 쿠폰관리 > 쿠폰리스트 > 회원 발급하기 > 회원검색하기 팝업
	 */
	@RequestMapping(value="sellerPopupMember")
	public String sellerPopupMember(@ModelAttribute("memberVO") MemberVO memberVO
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
		
//		memberVO.setGdMemberList(service.getMemberList(memberVO));
		
				
		return "/shop/admin/seller/couponPopupMember";
	}
	
	/**
	 * 쿠폰발급회원 내역 조회
	 * @param couponVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="sellerPopupApply")
	public String sellerPopupApply(@ModelAttribute("couponVO")CouponVO couponVO) throws Exception{

		// 쿠폰발급회원 내역 조회
		couponVO.setCouponApplyMember(service.getCouponApplyMember(couponVO));
		
		return "/shop/admin/seller/couponPopupApply";
	}
	
	/**
	 * 상품 목록 iframe
	 * @param goodsVO
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="couponIframeList")
	public String couponIframeList(@ModelAttribute("goodsVO") GoodsVO goodsVO, HttpServletRequest req, HttpServletResponse res) throws Exception {
		goodsVO.setSellerYn("Y");
		if(logger.isDebugEnabled()) {
			logger.debug("@@@list goodsVO::"+goodsVO);
		}
		Map<String, Object> param = new HashMap<String, Object>();
		
		//게시판 총 건수
		goodsVO.setTotalCnt(serviceG.getGoodsListTotalCount());
		//게시판 검색 총건수
		goodsVO.setRowCount(serviceG.getGoodsListCount(goodsVO));		
		logger.debug("@@@list goodsVO.getRowCount()::"+goodsVO.getRowCount());
		// 페이지 목록 사이즈
		param.put(CommonConstants.PAGE_SIZE, goodsVO.getPageSize());		
		// 시작 ROW 번호
		param.put(CommonConstants.ROW_START, goodsVO.getRowStart());		
		goodsVO.setGoodsList(serviceG.getGoodsList(goodsVO));
		
		return "/shop/admin/goods/_goodslist";
	}
	
}
