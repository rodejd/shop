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
		
		// ?????????/?????? ?????? ??????
		ShopLibFunction.menuAuthPermissionCheck(req, res, "event");
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		//????????? ??????
		param.put("schSellerCd", eventVO.getSchSellerCd());
		param.put("schSellerNm", eventVO.getSchSellerNm());
		
		//??????
		param.put("schSort", eventVO.getSchSort());
		
		//?????????
		eventVO.setRowCount(service.getEventCount(eventVO));
		
		// ????????? ?????? ?????????
		param.put(CommonConstants.PAGE_SIZE, eventVO.getPageSize());
				
		// ?????? ROW ??????
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
		
		//??????
		if( "modify".equals(eventVO.getMode()) ){
			
			if(eventVO.getAttach_file() != null){
				if(!eventVO.getAttach_file().isEmpty()){
					logger.debug(" file >> "+eventVO.getAttach_file());
					
					//??????????????? ??????
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
		
		// ??????
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
		
		HashMap<String, Object> paramsDb = null; // DB????????? ?????? map
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
	 * ????????? > ?????????/???????????? > ??????????????? > ??????????????????
	 */
	@RequestMapping(value="register")
	public String register(@ModelAttribute("eventVO") EventVO eventVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>event register!!!");
		}
		
		// ??????
		if( "modify".equals(eventVO.getMode()) ){
			eventVO.setEventObj(service.detailEvent(eventVO));
		}
		
		return "/shop/admin/event/register";
	}
	
	/**
	 * ????????? > ?????????/???????????? > ??????????????????
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
	 * ????????? > ?????????/???????????? > ??????INDB
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
			if ("config".equals(couponVO.getMode())) {	// ??????????????????
				
//				ShopConfig.setProperty("cfgcoupon", req.getParameter("cfgChkVal"));
//				System.out.println("req.getParameter(cfgChkVal)"+req.getParameter("cfgChkVal"));
				
				if(logger.isDebugEnabled()){
					logger.debug("@@ coupon >> use_yn >>"+req.getParameter("use_yn")+" double >> "+req.getParameter("coupon_double"));
				}
				//?????????????????? > ???????????? ????????????(0:??????, 1:??????????????????)
				ShopConfig.setProperty("use_yn", req.getParameter("use_yn"));
				//?????????????????? > ??????????????????(0:????????????,???????????? ??????????????????, 1:??????????????? ??????, 2: ??????????????? ??????)
				ShopConfig.setProperty("range", req.getParameter("range"));
				//?????????????????? > ?????? ????????????(0:????????? ????????? ?????? ?????? ????????????, 1:????????? ???????????? ?????? ????????? ????????? ??????)
				ShopConfig.setProperty("double", req.getParameter("coupon_double"));
				
				returnStr = "redirect:/shop/admin/event/coupon_cfg";
			} else if ("applyAdd".equals(couponVO.getMode())) {	// ????????????
				
				String errMsg = "";
				boolean valid = true;
				/** ???????????? ?????? **/
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("couponcd", couponVO.getCouponcd());	
				param.put("membertype", couponVO.getMembertype());	
				param.put("membergrpsno", StringUtil.nvl(couponVO.getMembergrpsno(), "0"));	
				// ????????? ?????? ???????????? ???????????????	
				List<GdCouponApply> chkList = service.getCouponMemberNew(param);
				if (chkList.size() > 0) {
					for (GdCouponApply obj : chkList) {
						if ( "0".equals(obj.getMembertype()) ) {
							errMsg = "?????? ??????????????? ???????????????.";
							valid = false;
							break;
						}
						if ( couponVO.getMembergrpsno().equals(Integer.toString(obj.getMembergrpsno())) ) {
							errMsg = "?????? ??????????????? ???????????????.";
							valid = false;
							break;
						}
						if ( "2".equals(couponVO.getMembertype()) ) {
							
							for(String mid : couponVO.getMids()) {
								if(obj.getMno().equals(mid)) {
									errMsg = "???????????? ????????? ????????????.";
									valid = false;
									break;
								}
							}
						}
					}
				}
				
				model.addAttribute("RESULT", valid);
				if (valid) {
					// ?????? ??????
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
					// ?????? ??????
					model.addAttribute("RESULT_MSG", errMsg);
				}
				
				returnStr = "dojson";
				
			} else if ("delApply".equals(couponVO.getMode())) {	// ??????????????????
				/** ???????????? ?????? **/
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("sno", req.getParameter("sno"));	
				service.deleteCouponApply(param);
				model.addAttribute("RESULT", true);
				model.addAttribute("couponcd", req.getParameter("couponcd"));
				returnStr = "dojson";
			} else if ("register".equals(couponVO.getMode()) || "modify".equals(couponVO.getMode())) {	// ???????????????/??????
				// ???????????? ??? ??????
				couponVO.setPerc(couponVO.getPerc().replaceAll("??????", ""));
				couponVO.setPrice(couponVO.getPrice()+couponVO.getPerc());
				//?????????????????? ?????? ???
				if(couponVO.getExcPrice()==null || couponVO.getExcPrice()=="") {
					couponVO.setExcPrice("0");
				}
				//?????? ?????? ?????????
				if ("".equals(couponVO.getPerc()) || "0".equals(StringUtil.nvl(couponVO.getMaxprice(), "0"))) {
					couponVO.setMaxprice("");
				}
				// ?????????????????? ??????
				if("1".equals(couponVO.getPriodtype())) {
					couponVO.setSdate(couponVO.getPriod());
				}
				
				couponVO.setRefer(req.getParameterValues("e_refer[]"));
				int newCouponcd = service.insertCoupon(couponVO);	// ?????? ?????? ????????????
				couponVO.setCouponcd(Integer.toString(newCouponcd));
				
				String couponimg = "";
				if (!couponVO.getImgFile().isEmpty()) {
					AwsFileUtil awsfileUtil = new AwsFileUtil();
					String awsPath = "coupon/" + String.valueOf(newCouponcd) + "/";

					// S3 ????????????
					if (FileUtil.getChkAwsFile(couponVO.getCouponimg())) {
						String awsKey = awsPath + FileUtil.getUrlFileName(couponVO.getCouponimg());
						awsfileUtil.delete(awsKey);
					}
					
					// AWS ???????????????
					couponimg = FileUtil.awsUploadFile(couponVO.getImgFile().getOriginalFilename(), couponVO.getImgFile().getBytes(), awsPath);
				}
				
				couponVO.setCouponimg(couponimg);
				service.updateCouponImage(couponVO);
				
				model.addAttribute("RESULT", true);
				model.addAttribute("couponcd", newCouponcd);
				//returnStr = "dojson";
				returnStr = "redirect:/shop/admin/event/coupon";
			} else if ("delete".equals(couponVO.getMode())) {	// ????????????
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("couponcd", couponVO.getCouponcd());	
				service.deleteCoupon(param);

				// S3 ????????????
				AwsFileUtil awsfileUtil = new AwsFileUtil();
				String awsKey = "couponcd/" + couponVO.getCouponcd();
				awsfileUtil.deleteList(awsKey);
				
				model.addAttribute("RESULT", true);
				returnStr = "dojson";
			} else if ("modifyApply".equals(couponVO.getMode())) {
				//2017-09-06 ?????? - ?????? ?????? ?????? ???????????? ??????
				service.updateCouponApply(couponVO);
				
				model.addAttribute("result", true);
				returnStr ="dojson";
			}
			
//		} catch (Exception e) {
//			model.addAttribute("RESULT", false);
//			model.addAttribute("RESULT_MSG", "????????? ???????????? ????????? ?????????????????????. ????????? ?????? ????????? ?????????.");
//			e.printStackTrace();
//			throw new BizException("common.00004");
//		} 
		
		return returnStr;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="indb.coupon2")
	public String indbCoupon2(HttpServletRequest req, HttpServletResponse res,  Model model) throws Exception {
		
		HashMap<String, Object> paramsDb = null; // DB????????? ?????? map
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
	 * ??????????????? ?????????
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
		
		/** ???????????? **/
		Map<String, Object> param = new HashMap<String, Object>();
		
		// ????????????(????????????[??????])
		param.put("skey", couponVO.getSkey());
		// ?????????(????????????[??????])
		param.put("sword", couponVO.getSword());
		// ????????????
		//param.put("ability", couponVO.getAbility());
		// ????????????
		param.put("schSkin", couponVO.getSchSkin());
		// ??????????????????
		param.put("goodstype", couponVO.getGoodstype());	
		// ?????????????????? > ???????????? - ????????????
		param.put("category", couponVO.getCategory());	
		// ?????????????????? > ???????????? - ????????????(key)
		param.put("gkey", couponVO.getGkey());	
		// ?????????????????? > ???????????? - ????????????(word)
		param.put("gword", couponVO.getGword());	
		// ??????????????????
		param.put("coupontype", couponVO.getCoupontype());	
		// ???????????????/????????????(key)
		param.put("dtkind", couponVO.getDtkind());	
		// ???????????????/????????????(word)
		if (couponVO.getRegdt() != null && couponVO.getRegdt().length == 2 ) {
			param.put("regdts", couponVO.getRegdt()[0]);	
			param.put("regdte", couponVO.getRegdt()[1]);	
		}
		//?????????????????? > ???????????? - ??? ??????????????????
		param.put("categoryArr", couponVO.getCategoryArr());
		
		//????????? ??????
		param.put("schSellerCd", couponVO.getSchSellerCd());
		param.put("schSellerNm", couponVO.getSchSellerNm());
		
		//??????
		param.put("schSort", couponVO.getSchSort());
		
		//?????????
		couponVO.setRowCount(service.getCouponCount(param));
		
		// ????????? ?????? ?????????
		param.put(CommonConstants.PAGE_SIZE, couponVO.getPageSize());
				
		// ?????? ROW ??????
		param.put(CommonConstants.ROW_START, couponVO.getRowStart());
			
		couponVO.setCouponList(service.getCouponList(param));
		
		model.addAttribute("couponVO", couponVO);
		
		return "/shop/admin/event/coupon";
		
	}
	
	/**
	 * ????????????/?????? ?????????
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
		
		/** ?????? ???????????? ?????? **/
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("couponcd", couponVO.getCouponcd());	
		
		// ??????????????? ??????
		couponVO.setCouponGrpTotal(service.getCouponApply1TotalCount());
		// ??????????????? ?????? ?????? ??????
		couponVO.setCouponGrpList(service.getCouponApply1());
		// ????????? ?????? ??????
		couponVO.setCouponInfo(service.getCouponApply2(param));

		// ????????? ?????? ???????????? ?????????
		couponVO.setCouponApplyGrpTotal(service.getCouponMemberCount(param));
		// ????????? ?????? ?????????
		param.put(CommonConstants.PAGE_SIZE, couponVO.getPageSize());
		// ?????? ROW ??????
		param.put(CommonConstants.ROW_START, couponVO.getRowStart());
		// ????????? ?????? ???????????? ???????????????	
		couponVO.setCouponApplyGrpList(service.getCouponMemberNew(param));
		
		model.addAttribute("couponInfo", couponVO.getCouponInfo());
		
		return "/shop/admin/event/coupon_apply";
		
	}

	/**
	 * ???????????????(??????) ?????????
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
		
		// ????????? ?????? DB??????
		if ("modify".equals(couponVO.getMode())) {
			/** ???????????? ?????? **/
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
	 * ??????????????? > ?????? ???????????? > ?????????????????? ??????
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
		
		// ???????????? ?????? ?????? ??????
		memberService.memberMenuAuth(req, res);

		Map<String, Object> param = new HashMap<String, Object>();

		/*
		 * ????????????(1)
		 * 
		 * */
		// ????????????
		param.put("skey", memberVO.getSkey());
		// ?????????
		param.put("sword", memberVO.getSword());
		// ??????(????????????????????????)
		if("resno".equals(memberVO.getSkey())){
			String tmp = StringUtil.fillSpace(memberVO.getSword().replaceAll("-", ""),13);
			param.put("resno1", tmp.substring(0,6));
			param.put("resno2", tmp.substring(6,13));
		}
		// ????????????
		param.put("sstatus", memberVO.getSstatus());
		// ??????
		param.put("slevel", memberVO.getSlevel());
		
		/*
		 * ????????????(2)
		 * 
		 * */
		// ?????????
		param.put("ssum_salemin", memberVO.getSsum_salemin());
		param.put("ssum_salemax", memberVO.getSsum_salemax());
		//?????????
		param.put("semoneymin", memberVO.getSemoneymin());
		param.put("semoneymax", memberVO.getSemoneymax());
		
		/*
		 * ????????????(3)
		 * 
		 * */
		//?????????
		param.put("sregdt_0", memberVO.getSregdt() != null ? memberVO.getSregdt()[0] : "" );
		param.put("sregdt_1", memberVO.getSregdt() != null ? memberVO.getSregdt()[1] : "" );
		//???????????????
		param.put("slastdt_0", memberVO.getSlastdt() != null ? memberVO.getSlastdt()[0] : "" );
		param.put("slastdt_1", memberVO.getSlastdt() != null ? memberVO.getSlastdt()[1] : "" );
		
		/*
		 * ????????????(4)
		 * 
		 * */
		// ??????, ??????, SMS ????????????
		param.put("sex", memberVO.getSex());
		/*param.put("sage", memberVO.getSage() );
		if(!"".equals(memberVO.getSage()) && null != memberVO.getSage() ){
			param.put("age0", Integer.parseInt(DateUtil.getDateFrom("yyyy", "1y")) - Integer.parseInt( memberVO.getSage() ));
			param.put("age1", (Integer)param.get("age1") - 9 );
		}*/
		param.put("mailling", memberVO.getSmailling());
		
		// ????????????, ??????????????????
		param.put("scnt_loginmin", memberVO.getScnt_loginmin());				// ???????????? min
		param.put("scnt_loginmax", memberVO.getScnt_loginmax());			// ???????????? max
		
		if(!"".equals(memberVO.getDormancy()) && null != memberVO.getDormancy() ){
			param.put("dormancy", DateUtil.getDateFrom("yyyyMMdd", "-" + memberVO.getDormancy()+ "d"));		// ????????????
		}
		
		// ????????????, ????????????/???????????????
		param.put("birthtype", memberVO.getBirthtype());
		param.put("birthdatemin", memberVO.getBirthdatemin());
		param.put("birthdatemax", memberVO.getBirthdatemax());
		
		param.put("marriyn", memberVO.getSmarriyn());
		param.put("marridatemin", memberVO.getMarridatemin());
		param.put("marridatemax", memberVO.getMarridatemax());
		
		// sort
		param.put("sort", memberVO.getSort());
		
		//??? ??????
		memberVO.setTotalCount(memberService.getMemberTotalCount());
		//?????? ??? ??????
		memberVO.setRowCount(memberService.getMemberCount(param));
		// ????????? ?????? ?????????
		param.put(CommonConstants.PAGE_SIZE, memberVO.getPageSize());
		// ?????? ROW ??????
		param.put(CommonConstants.ROW_START, memberVO.getRowStart());
		
		//????????????
		param.put("popupDetail", memberVO.getPopupDetail());
		
		// ?????? ????????? ??????
		memberVO.setGdMemberList(memberService.getMemberList(param)); 	// ???????????? > ?????????????????? > SMS????????????(???????????????)
		//memberVO.setGdMemberList(service.getMemberList(memberVO));		
		return "/shop/admin/event/popup_member";
	}
	
	/**
	 * ?????????????????? ?????? ??????
	 * @param couponVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="popup_apply")
	public String popupApplyMember(@ModelAttribute("couponVO")CouponVO couponVO) throws Exception{

		// ?????????????????? ?????? ??????
		couponVO.setCouponApplyMember(service.getCouponApplyMember(couponVO));
		
		return "/shop/admin/event/popup_apply";
	}
	
	/**
	 * ???????????? ?????????
	 * @param surveyVO
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value="surveyList")
	public String surveyList(@ModelAttribute("surveyVO") SurveyVO surveyVO) throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("####[EventController > surveyList] : {}", surveyVO.getPageNo() );
		}
		//?????????
		surveyVO.setRowCount(service.getSurveyCount(surveyVO));
		surveyVO.setSurveyList(service.getSurveyList(surveyVO));
		
		return "/shop/admin/event/surveyList";
	}
	
	/**
	 * requset ??? ????????? mode ??? ???????????? ?????? ?????? ?????????????????? ??????
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
		
		//mode ??? modfiy??? ????????? ????????? ?????????????????? ??????
		if("modify".equals(surveyVO.getMode())){
			
			//surveySno ??? ???????????? ???????????? ???????????? ??????
			detail = service.getDetailSurvey(surveyVO.getSurveySno());
			
			//parameter SurveySno??? ????????? ????????? ???????????? bizException ?????? 
			if(detail == null || "".equals(detail.getSurveySno())) {
				//???????????? ?????? ??????????????????.
				throw new BizException("survey.00002");
			}
			
			//???????????? ??????????????? ??????
			surveyVO.setDetailSurveyVO(detail);
			
		}
		
		return "/shop/admin/event/survey";
	}
		
	/**
	 * ???????????? ??????, ?????? , ?????? ,???????????? ????????? ?????? ?????????
	 * mode ??? ??????: insert ?????? : modify ?????? : delete ???????????? : open_modify ??? ????????? ??????.
	 * @Param surveyVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="surveyIndb")
	public String surveyIndb(@ModelAttribute SurveyVO surveyVO, HttpServletRequest request) throws Exception{
		logger.debug("EventController surveyIndb >> {} >>" , surveyVO);
		
		String mode = StringUtil.nvl(surveyVO.getMode()); 		//????????????
		String sno = StringUtil.nvl(surveyVO.getSurveySno()); 	//???????????? ?????????  
		
		//???????????? ??????
		if("modify".equals(mode)) { 
			service.modifySurvey(surveyVO);
		}else if("open_modify".equals(surveyVO.getMode())) {
			//??????, ????????????
			service.updateSurveyOpen(surveyVO);
			 //???????????? ?????? ??? ????????? ????????? ???????????? queryString ?????? 
			return "redirect:/shop/admin/event/surveyList?"+request.getQueryString();
		}else if("delete".equals(mode)){
			//???????????? ??????
			service.deleteSurvey(sno);
			 //???????????? ?????? ??? ????????? ????????? ???????????? queryString ?????? 
			return "redirect:/shop/admin/event/surveyList?"+request.getQueryString();
		}else if("insert".equals(mode)){
			//???????????? ?????????
			//gd_survey  ????????? ??????
			//gd_survey_question ????????? ??????
			service.setSurvey(surveyVO);
		}else {
			//mode ??? ?????? ????????? ??????????????? ??????
			//survey.00001 : ????????? ???????????????.
			throw new BizException("survey.00001");
		}
		
		return "redirect:/shop/admin/event/surveyList";
	}
	
	
	/**
	 * ???????????? ???????????????
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

