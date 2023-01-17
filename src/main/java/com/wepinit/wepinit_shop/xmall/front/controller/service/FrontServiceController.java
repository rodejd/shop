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
/********************************************************************************
\* <pre>
* 업무구분명			:	Community
* 세부업무구분명	: 	사용자 > Community
* 작성자 				: 	이병환
* 설명 				: 	사용자 Community Controller
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170208		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.controller.service;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xmall.admin.vo.board.CooperationVO;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.ShopLibFunction;
import com.wepinit.wepinit_shop.xmall.common.UserInfo;
import com.wepinit.wepinit_shop.xmall.common.session.ShopSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCode;
import com.wepinit.wepinit_shop.xmall.common.vo.GdFaq;
import com.wepinit.wepinit_shop.xmall.common.vo.GdListBank;
import com.wepinit.wepinit_shop.xmall.front.service.service.FrontServiceService;
import com.wepinit.wepinit_shop.xmall.front.vo.service.FrontServiceFaqVO;
import com.wepinit.wepinit_shop.xmall.front.vo.service.FrontServiceGuideVO;
import com.wepinit.wepinit_shop.xmall.front.vo.service.FrontServiceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/shop/service/")
public class FrontServiceController {

	private static final Logger logger = LoggerFactory.getLogger(FrontServiceController.class); 
	
	@Resource
	FrontServiceService service;
	
	/**
	 * 1. 회사소개
	 * @param map
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="company")
	public String company(@RequestParam Map<String, Object> map, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		String filePath = ConfigClass.value("WEB_DIR_PATH").concat("/shop/data/clause/");
		String clause = "contents_4_" + ConfigClass.getSkin(req) + ".html";
		model.addAttribute("filePath", filePath);
		model.addAttribute("clause", clause);
		
		return "/shop/service/company";
	}
	
	/**
	 * 2. 개인정보취급방침
	 * @param map
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="private")
	public String _private(@RequestParam Map<String, Object> map, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("private>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		
		return "/shop/service/private";
	}
	
	/**
	 * 3. 이용약관
	 * @param map
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="agreement")
	public String agreement(@RequestParam Map<String, Object> map, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("agreement>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		
		// 이용약관 
		String filePath = ConfigClass.value("WEB_DIR_PATH").concat("/shop/data/clause/");
		
		int i = 0;
		StringBuffer sb = new StringBuffer();
		ArrayList list = FileUtil.getOneLineListToFile(filePath.concat("contents_3_" + ConfigClass.getSkin(req) + ".html"));	//contents_3 은 이용약관 내용 저장 파일
		if ( null != list && 0 < list.size() ) {
			i = 0;
			while ( i < list.size() ) {
				sb.append((String)list.get(i) + "\n");
				i++;
			}
		}
		
		model.addAttribute("clause", sb.toString().replace("${ag_compName}", ShopConfig.getProperty("compName")).replace("${ag_shopName}",ShopConfig.getProperty("shopName")));
		
		return "/shop/service/agreement";
	}
	
	/**
	 * 4-1. 광고/제휴안내
	 * 
	 * @param cooperationVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="cooperation")
	public String cooperation(@ModelAttribute("cooperationVO") CooperationVO cooperationVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("cooperation>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		
		ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		UserInfo oUserInfoLogin = null;
		
		if(logger.isDebugEnabled()){
			logger.debug("oUserInfoLogin" + oUserInfoLogin);
		}
		
		if (shopSO.isShopLogin()){
			oUserInfoLogin = shopSO.getUserInfo();
			
			cooperationVO.setName(oUserInfoLogin.getUserId());
			cooperationVO.setEmail((String)oUserInfoLogin.getXkey().get("email"));
		}
		
		return "/shop/service/cooperation";
	}
	
	/**
	 * 4-2.광고/제휴 등록
	 * 
	 * @param cooperationVO
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="cooperation.dojson", method=RequestMethod.POST)
	public String cooperation_insert(@ModelAttribute("cooperationVO") CooperationVO cooperationVO, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("cooperation insert>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		
		service.setCooperation(cooperationVO);
		
		return "/shop/service/cooperation";
	}
	
	@RequestMapping(value="sitemap")
	public String sitemap(@RequestParam Map<String, Object> map, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("sitemap ");
		}
		
		return "/shop/service/sitemap";
	}
	
	/**
	 * 고객센터
	 * */
	@RequestMapping(value="customer")
	public String customer(@ModelAttribute("serviceVO")FrontServiceVO serviceVO, Model model) {
		if(logger.isDebugEnabled()){
			logger.debug(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> customer ");
		}
		
		int pageSize = 5;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		List<GdListBank> bankList = this.service.bankUseList();
		List<GdFaq> faqList = this.service.faqRecentList(map);
		
		model.addAttribute("bankList", bankList);
		model.addAttribute("faqList", faqList);

		serviceVO.setCusAdminEmail(ShopConfig.getProperty("adminEmail"));
		serviceVO.setCusCompFax(ShopConfig.getProperty("compFax"));
		serviceVO.setCusCompPhone(ShopConfig.getProperty("compPhone"));
		serviceVO.setTabOrder(0);
		
		model.addAttribute("serviceVO", serviceVO);
		
		return "/shop/service/customer";
	}
	
	@RequestMapping(value="faq")
	public String faq(@ModelAttribute("faqVO") FrontServiceFaqVO faqVO, Model model) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> faq ");
		}
		
		Map<String, Object> map = this.service.getParamMap(faqVO);

		List<GdFaq> faqList = this.service.frontFaqlistSelect(map);
		List<GdCode> codeList = ShopLibFunction.codeitem("faq");
		Map<String, String> codeMap = this.service.getCodeMap();
		List<GdCode> faqCategorySelect = this.service.faq3thCategorySelect();
		
		model.addAttribute("faqList", faqList); 				// faq_rtList1
		model.addAttribute("faqCategory", faqCategorySelect); 	// rtList3
		model.addAttribute("codeMap", codeMap);					// faq_rtList2
		model.addAttribute("faqVO", faqVO);
		model.addAttribute("codeList", codeList);
		
		return "/shop/service/faq";
	}
	
	@RequestMapping(value = "guide")
	public String guide(@ModelAttribute("guideVO") FrontServiceGuideVO guideVO, Model model) {
		guideVO.setGuShopName(ShopConfig.getProperty("shopName"));
		guideVO.setGuAddress(ShopConfig.getProperty("address"));
		guideVO.setGuCompPhone(ShopConfig.getProperty("compPhone"));

		return "/shop/service/guide";
	}
	
	
	/**
	 * 품질보증정책
	 * @param map
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="qualityPolicy")
	public String qualityPolicy(@RequestParam Map<String, Object> map, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		String filePath = ConfigClass.value("WEB_DIR_PATH").concat("/shop/data/policy/");
		String policy = "policy_1_" + ConfigClass.getSkin(req) + ".html";
		model.addAttribute("filePath", filePath);
		model.addAttribute("policy", policy);
		return "/shop/service/qualityPolicy";
	}
	
	/**
	 * 주문배송정책
	 * @param map
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="orderPolicy")
	public String orderPolicy(@RequestParam Map<String, Object> map, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		String filePath = ConfigClass.value("WEB_DIR_PATH").concat("/shop/data/policy/");
		String policy = "policy_2_" + ConfigClass.getSkin(req) + ".html";
		model.addAttribute("filePath", filePath);
		model.addAttribute("policy", policy);
		return "/shop/service/orderPolicy";
	}
	
	/**
	 * 교환반품정책
	 * @param map
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="exchangePolicy")
	public String exchangePolicy(@RequestParam Map<String, Object> map, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		String filePath = ConfigClass.value("WEB_DIR_PATH").concat("/shop/data/policy/");
		String policy = "policy_3_" + ConfigClass.getSkin(req) + ".html";
		model.addAttribute("filePath", filePath);
		model.addAttribute("policy", policy);
		return "/shop/service/exchangePolicy";
	}
	
	/**
	 * 고객등급정책
	 * @param map
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="ratingPolicy")
	public String ratingPolicy(@RequestParam Map<String, Object> map, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		String filePath = ConfigClass.value("WEB_DIR_PATH").concat("/shop/data/policy/");
		String policy = "policy_4_" + ConfigClass.getSkin(req) + ".html";
		model.addAttribute("filePath", filePath);
		model.addAttribute("policy", policy);
		return "/shop/service/ratingPolicy";
	}
	
	/**
	 * 결제/가격/적립금정책
	 * @param map
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="payPolicy")
	public String payPolicy(@RequestParam Map<String, Object> map, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		String filePath = ConfigClass.value("WEB_DIR_PATH").concat("/shop/data/policy/");
		String policy = "policy_5_" + ConfigClass.getSkin(req) + ".html";
		model.addAttribute("filePath", filePath);
		model.addAttribute("policy", policy);
		return "/shop/service/payPolicy";
	}
	
	/**
	 * A/S정책
	 * @param map
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="asPolicy")
	public String asPolicy(@RequestParam Map<String, Object> map, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		String filePath = ConfigClass.value("WEB_DIR_PATH").concat("/shop/data/policy/");
		String policy = "policy_6_" + ConfigClass.getSkin(req) + ".html";
		model.addAttribute("filePath", filePath);
		model.addAttribute("policy", policy);
		return "/shop/service/asPolicy";
	}
	
	/**
	 * 개인정보취급방침
	 * @param map
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="privacyPolicy")
	public String privacyPolicy(@RequestParam Map<String, Object> map, HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		String filePath = ConfigClass.value("WEB_DIR_PATH").concat("/shop/data/policy/");
		String policy = "policy_7_" + ConfigClass.getSkin(req) + ".html";
		model.addAttribute("filePath", filePath);
		model.addAttribute("policy", policy);
		return "/shop/service/privacyPolicy";
	}
}
