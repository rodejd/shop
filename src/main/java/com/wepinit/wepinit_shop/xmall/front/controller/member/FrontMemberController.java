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
/******r**************************************************************************
* <pre>ing	
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
package com.wepinit.wepinit_shop.xmall.front.controller.member;

import com.fasterxml.jackson.databind.JsonNode;
import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.Aes128Util;
import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xcube.util.WebUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.event.EventService;
import com.wepinit.wepinit_shop.xmall.admin.service.member.MemberService;
import com.wepinit.wepinit_shop.xmall.common.AmazonSES;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.UserInfo;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.service.ShopLibFncService;
import com.wepinit.wepinit_shop.xmall.common.session.ShopSessionObject;
import com.wepinit.wepinit_shop.xmall.common.util.SnsUtil;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import com.wepinit.wepinit_shop.xmall.common.vo.SnsVO;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdMemberJoinGdMemberGrp;
import com.wepinit.wepinit_shop.xmall.front.service.member.FrontMemberService;
import com.wepinit.wepinit_shop.xmall.front.vo.member.*;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/shop/member/")
public class FrontMemberController {

	private static final Logger logger = LoggerFactory.getLogger(FrontMemberController.class); 
	
	@Resource
	FrontMemberService service;
	
	@Resource
	EventService evenctservice;
	
	@Resource
	ShopLibFncService shopService;
	
	@Resource
	MemberService memberService;
	
	@RequestMapping(value="login")
	public String login(@ModelAttribute("frontMemberVO") FrontMemberVO frontMemberVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("sample member");
		}
		
		ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		if (shopSO.isShopLogin()) {
			return "redirect:/shop/mypage/main_index";
		}
		
		HttpSession session = req.getSession();
		model.addAttribute("returnUrl", session.getAttribute("referer"));
		
		return "/shop/member/login";
	}
	
	@RequestMapping(value="snslogin")
	public String snslogin(@RequestParam String type, 
			HttpServletRequest req, HttpServletResponse res, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) throws Exception {

		session.setAttribute("sns_process", "sns_process");
		
		String domain = req.getRequestURL().toString().replace(req.getRequestURI(), "");
		String kakao_client_id = ShopConfig.getProperty("kakao_client_id");
		String kakao_REDIRECT_URI = domain + ShopConfig.getProperty("kakao_redirect_uri");
		String facebook_client_id = ShopConfig.getProperty("facebook_client_id");
		String facebook_REDIRECT_URI = domain + ShopConfig.getProperty("facebook_redirect_uri");
		String google_client_id = ShopConfig.getProperty("google_client_id");
		String google_REDIRECT_URI = domain + ShopConfig.getProperty("google_redirect_uri");
		String apple_client_id = ShopConfig.getProperty("apple_client_id");
		String apple_REDIRECT_URI = domain + ShopConfig.getProperty("apple_redirect_uri");
	    String state = new BigInteger(130, new SecureRandom()).toString();
		
		if ("kakao".equals(type)) {
			String url = "https://kauth.kakao.com/oauth/authorize?client_id="+ kakao_client_id + "&redirect_uri=" + kakao_REDIRECT_URI + "&response_type=code";
			model.addAttribute("popUrl", url);
		} else if ("facebook".equals(type)) {
			String url = "https://www.facebook.com/v2.11/dialog/oauth?client_id=" + facebook_client_id + "&response_type=code&redirect_uri=" + facebook_REDIRECT_URI + "&state=" + state;
			model.addAttribute("popUrl", url);
		} else if ("google".equals(type)) {
			String url = "https://accounts.google.com/o/oauth2/auth?client_id=" + google_client_id + "&response_type=code&redirect_uri=" + google_REDIRECT_URI + "&scope=https://www.googleapis.com/auth/userinfo.email";
			model.addAttribute("popUrl", url);
		} else if ("apple".equals(type)) {
			String url = "https://appleid.apple.com/auth/authorize?response_type=code%20id_token&response_mode=form_post&client_id=" + apple_client_id + "&redirect_uri=" + apple_REDIRECT_URI + "&state=" + state + "&scope=name%20email";
			model.addAttribute("popUrl", url);
		} else {
			return "redirect:/shop/member/popup_snsclose";
		}
		
		return "/shop/member/popup_snslogin";
	}

	@RequestMapping(value = "popup_snsclose")
	public String popup_snsclose(){
		return "/shop/member/popup_snsclose";
	}
	
	/**
	 * 카카오 로그인
	 * @param code
	 * @param req
	 * @param res
	 * @param redirectAttributes
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="kakaologin", method={RequestMethod.GET,RequestMethod.POST})
	public String kakaologin(@RequestParam(value="code",defaultValue="denied")String code, HttpServletRequest req 
			, HttpServletResponse res,RedirectAttributes redirectAttributes ,HttpSession session)throws Exception{
	
		//sns 흐름 무시했을경우
		String sns_process = (String) session.getAttribute("sns_process");
		session.removeAttribute("sns_process");
		if(!"sns_process".equals(sns_process)){
			throw new BizException("login.00005");
		}

		// code넘어온 값이 "denied"일때 3자 제공 동의하지 경우 alert 진행불가
		if ("denied".equals(code)) {
			redirectAttributes.addFlashAttribute("login", true);
			redirectAttributes.addFlashAttribute("err","제3자 제공 동의 하지 않으면 가입불가합니다.");
		} else {
			try {
				String client_id = ShopConfig.getProperty("kakao_client_id");
				String domain = req.getRequestURL().toString().replace(req.getRequestURI(), "");
				String redirect_url = domain + ShopConfig.getProperty("kakao_redirect_uri"); 
				
				// sns에서 넘어온 유저정보
				Map<String, Object> sns_userInfo = service.kakaoUserInfo(code, client_id, redirect_url);
				String type = "kakao";
				
				ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
				if (shopSO.isShopLogin()) {
					sns_userInfo.put("m_no", shopSO.getUserInfo().getM_no());
					sns_userInfo.put("sns_type", type);
					
					this.service.frontMemberSnsInsert(sns_userInfo);
					
					redirectAttributes.addFlashAttribute("easy", type);
				} else {
					String sns_id = sns_userInfo.get("sns_id").toString();
	
					SnsVO snsvo = new SnsVO();
					snsvo.setSns_id(sns_id);
					snsvo.setSns_type(type);
	
					FrontMemberVO fv = new FrontMemberMyinfoVO();
					// 회원가입 로그인 구분
					fv = service.frontMemberInfoBySns(snsvo);
					// 로그인과정
					if (null != fv) {
						// 로그인 서비스에 보낼 비밀번호 인증 필요없다는 구분자
						req.setAttribute("sns_login", true);
						// 팝업창관리 true 로그인 아니면 join
						redirectAttributes.addFlashAttribute("login", true);
	
						// 로그인처리
						service.setFrontLoginProcess(fv, req);
	
					} else {
						throw new BizException("login.00001");
					}
				}
			} catch (NullPointerException e) {
				//return "redirect:/shop/member/login";
				redirectAttributes.addFlashAttribute("err", e.getMessage());
			} catch (BizException e) {
				// 로그인 실패하면 err메세지와함께 alert출력
				redirectAttributes.addFlashAttribute("err", e.getErrorMessage());
			}

		}
		
		return "redirect:/shop/member/popup_snsclose";
	}
	
	/**
	 * 페이스북 로그인
	 * @param code
	 * @param state
	 * @param req
	 * @param res
	 * @param redirectAttributes
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="facebooklogin", method={RequestMethod.GET,RequestMethod.POST})
	public String facebooklogin(@RequestParam(value="code",defaultValue="denied")String code,@RequestParam("state")String state, HttpServletRequest req 
			, HttpServletResponse res,RedirectAttributes redirectAttributes ,HttpSession session)throws Exception{

		//sns 흐름 무시했을경우
		String sns_process = (String) session.getAttribute("sns_process");
		session.removeAttribute("sns_process");
		if(!"sns_process".equals(sns_process)){
			throw new BizException("login.00005");
		}
			
		// code넘어온 값이 "denied"일때 3자 제공 동의하지 경우 alert 진행불가
		if ("denied".equals(code)) {
			redirectAttributes.addFlashAttribute("login", true);
			redirectAttributes.addFlashAttribute("err",	"제3자 제공 동의 하지 않으면 가입불가합니다.");
		} else {
			try {
				String client_id = ShopConfig.getProperty("facebook_client_id");
				String client_Secret = ShopConfig.getProperty("facebook_client_secret");
				String domain = req.getRequestURL().toString().replace(req.getRequestURI(), "");
				String redirect_url = domain + ShopConfig.getProperty("facebook_redirect_uri"); 
				
				// sns에서 넘어온 유저정보
				Map<String, Object> sns_userInfo = service.facebookUserInfo(code, client_id, client_Secret, redirect_url);
				String type = "facebook";
				
				ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
				if (shopSO.isShopLogin()) {
					sns_userInfo.put("m_no", shopSO.getUserInfo().getM_no());
					sns_userInfo.put("sns_type", type);
					
					this.service.frontMemberSnsInsert(sns_userInfo);
					
					redirectAttributes.addFlashAttribute("easy", type);
				} else {
					String sns_id = sns_userInfo.get("sns_id").toString();
					
					SnsVO snsvo = new SnsVO();
					snsvo.setSns_id(sns_id);
					snsvo.setSns_type(type);
	
					FrontMemberVO fv = new FrontMemberMyinfoVO();
					// 회원가입 로그인 구분
					fv = service.frontMemberInfoBySns(snsvo);
	
					if (null != fv) {
						// 로그인 서비스에 보낼 비밀번호 인증 필요없다는 구분자
						req.setAttribute("sns_login", true);
						// 팝업창 관리 true ->로그인 아니라면 join페이지
						redirectAttributes.addFlashAttribute("login", true);
	
						service.setFrontLoginProcess(fv, req);
	
					} else {
						throw new BizException("login.00001");
					}
				}
			} catch (NullPointerException e) {
				//return "redirect:/shop/member/login";
				redirectAttributes.addFlashAttribute("err", e.getMessage());
			} catch (BizException e) {
				// 로그인 실패하면 err메세지와함께 alert출력
				redirectAttributes.addFlashAttribute("err", e.getErrorMessage());
			}
		}
		return "redirect:/shop/member/popup_snsclose";
	}
	
	/**
	 * 구글 로그인
	 * @param code
	 * @param req
	 * @param res
	 * @param redirectAttributes
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="googlelogin", method={RequestMethod.GET,RequestMethod.POST})
	public String googlelogin(@RequestParam(value="code",defaultValue="denied")String code, HttpServletRequest req 
			, HttpServletResponse res,RedirectAttributes redirectAttributes ,HttpSession session)throws Exception{
	
		//sns 흐름 무시했을경우
		String sns_process = (String) session.getAttribute("sns_process");
		session.removeAttribute("sns_process");
		if(!"sns_process".equals(sns_process)){
			throw new BizException("login.00005");
		}

		// code넘어온 값이 "denied"일때 3자 제공 동의하지 경우 alert 진행불가
		if ("denied".equals(code)) {
			redirectAttributes.addFlashAttribute("login", true);
			redirectAttributes.addFlashAttribute("err","제3자 제공 동의 하지 않으면 가입불가합니다.");
		} else {
			try {
				String client_id = ShopConfig.getProperty("google_client_id");
				String client_Secret = ShopConfig.getProperty("google_client_secret");
				String domain = req.getRequestURL().toString().replace(req.getRequestURI(), "");
				String redirect_url = domain + ShopConfig.getProperty("google_redirect_uri"); 
				
				// sns에서 넘어온 유저정보
				Map<String, Object> sns_userInfo = service.googleUserInfo(StringEscapeUtils.unescapeHtml(code), client_id, client_Secret, redirect_url);
				String type = "google";
				
				ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
				if (shopSO.isShopLogin()) {
					sns_userInfo.put("m_no", shopSO.getUserInfo().getM_no());
					sns_userInfo.put("sns_type", type);
					
					this.service.frontMemberSnsInsert(sns_userInfo);
					
					redirectAttributes.addFlashAttribute("easy", type);
				} else {
					String sns_id = sns_userInfo.get("sns_id").toString();
	
					SnsVO snsvo = new SnsVO();
					snsvo.setSns_id(sns_id);
					snsvo.setSns_type(type);
	
					FrontMemberVO fv = new FrontMemberMyinfoVO();
					// 회원가입 로그인 구분
					fv = service.frontMemberInfoBySns(snsvo);
					// 로그인과정
					if (null != fv) {
						// 로그인 서비스에 보낼 비밀번호 인증 필요없다는 구분자
						req.setAttribute("sns_login", true);
						// 팝업창관리 true 로그인 아니면 join
						redirectAttributes.addFlashAttribute("login", true);
	
						// 로그인처리
						service.setFrontLoginProcess(fv, req);
	
					} else {
						throw new BizException("login.00001");
					}
				}
			} catch (NullPointerException e) {
				//return "redirect:/shop/member/login";
				redirectAttributes.addFlashAttribute("err", e.getMessage());
			} catch (BizException e) {
				// 로그인 실패하면 err메세지와함께 alert출력
				redirectAttributes.addFlashAttribute("err", e.getErrorMessage());
			}

		}
		
		return "redirect:/shop/member/popup_snsclose";
	}
	
	/**
	 * 애플 로그인
	 * @param code
	 * @param req
	 * @param res
	 * @param redirectAttributes
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="applelogin", method={RequestMethod.GET,RequestMethod.POST})
	public String applelogin(@RequestParam(value="code",defaultValue="denied")String code, @RequestParam("id_token") String id_token, HttpServletRequest req 
			, HttpServletResponse res,RedirectAttributes redirectAttributes ,HttpSession session)throws Exception{
	
		//sns 흐름 무시했을경우
//		String sns_process = (String) session.getAttribute("sns_process");
//		session.removeAttribute("sns_process");
//		if(!"sns_process".equals(sns_process)){
//			throw new BizException("login.00005");
//		}

		// code넘어온 값이 "denied"일때 3자 제공 동의하지 경우 alert 진행불가
		if ("denied".equals(code)) {
			redirectAttributes.addFlashAttribute("login", true);
			redirectAttributes.addFlashAttribute("err","제3자 제공 동의 하지 않으면 가입불가합니다.");
		} else {
			// sns에서 넘어온 유저정보
			try {
				Map<String, Object> sns_userInfo = service.appleUserInfo(code, id_token);
				String type = "apple";
				
				ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
				if (shopSO.isShopLogin()) {
					sns_userInfo.put("m_no", shopSO.getUserInfo().getM_no());
					sns_userInfo.put("sns_type", type);
					
					this.service.frontMemberSnsInsert(sns_userInfo);
					
					redirectAttributes.addFlashAttribute("easy", type);
				} else {
					String sns_id = sns_userInfo.get("sns_id").toString();
	
					SnsVO snsvo = new SnsVO();
					snsvo.setSns_id(sns_id);
					snsvo.setSns_type(type);
	
					FrontMemberVO fv = new FrontMemberMyinfoVO();
					// 회원가입 로그인 구분
					fv = service.frontMemberInfoBySns(snsvo);
					// 로그인과정
					if (null != fv) {
						// 로그인 서비스에 보낼 비밀번호 인증 필요없다는 구분자
						req.setAttribute("sns_login", true);
						// 팝업창관리 true 로그인 아니면 join
						redirectAttributes.addFlashAttribute("login", true);
	
						// 로그인처리
						service.setFrontLoginProcess(fv, req);
	
					} else {
						throw new BizException("login.00001");
					}
				}
			} catch (NullPointerException e) {
				//return "redirect:/shop/member/login";
				redirectAttributes.addFlashAttribute("err", e.getMessage());
			} catch (BizException e) {
				// 로그인 실패하면 err메세지와함께 alert출력
				redirectAttributes.addFlashAttribute("err", e.getErrorMessage());
			}

		}
		
		return "redirect:/shop/member/popup_snsclose";
	}

	@RequestMapping(value = "naverlogin",  method = {RequestMethod.GET, RequestMethod.POST })
	public String naverlogin(@RequestParam(value = "code", defaultValue = "denied") String code,@RequestParam("state") String state, HttpServletRequest req,
			HttpServletResponse res, RedirectAttributes redirectAttributes,	HttpSession session) throws Exception {
		
		//sns 흐름 무시했을경우
		String sns_process = (String) session.getAttribute("sns_process");
		session.removeAttribute("sns_process");
		if(!"sns_process".equals(sns_process)){
			throw new BizException("login.00005");
		}
		
		// code넘어온 값이 "denied"일때 3자 제공 동의하지 경우 alert 진행불가
		if ("denied".equals(code)) {
			redirectAttributes.addFlashAttribute("login", true);
			redirectAttributes.addFlashAttribute("err",
					"제3자 제공 동의 하지 않으면 가입불가합니다.");
		} else {
			try {
				// sns에서 넘어온 유저정보
				Map<String, Object> sns_userInfo = service.naverUserInfo(code,
						state);

				String sns_id = sns_userInfo.get("sns_id").toString();
				String sns_email = sns_userInfo.get("sns_email").toString();
				String sns_nickname = sns_userInfo.get("sns_nickname").toString();

				String type = "naver";

				SnsVO snsvo = new SnsVO();
				snsvo.setSns_id(sns_id);
				snsvo.setSns_type(type);

				FrontMemberVO fv = new FrontMemberMyinfoVO();
				// 회원가입 로그인 구분
				fv = service.frontMemberInfoBySns(snsvo);

				if (null != fv) {
					sns_id = "N" + sns_id;
					fv.setM_id(sns_id);

					// 로그인 서비스에 보낼 비밀번호 인증 필요없다는 구분자
					req.setAttribute("sns_login", true);
					// 팝업창 관리 true ->로그인 아니라면 join페이지
					redirectAttributes.addFlashAttribute("login", true);
					// 로그인처리
					service.setFrontLoginProcess(fv, req);

				} else {
					// 세션에 유저정보 담아 join페이지로
					session.setAttribute("sns_id", sns_id);
					session.setAttribute("sns_email", sns_email);
					session.setAttribute("sns_type", "naver");
					session.setAttribute("sns_nickname", sns_nickname);
				}
			} catch (NullPointerException e) {
				return "redirect:/shop/member/login";
			} catch (BizException e) {
				// 로그인 실패하면 err메세지와함께 alert출력
				redirectAttributes.addFlashAttribute("err", e.getErrorMessage());
			}
		}
		return "redirect:/shop/member/popup_snsclose";

	}
	
	@RequestMapping(value="instagramlogin", method={RequestMethod.GET,RequestMethod.POST})
	public String instagramlogin(@RequestParam(value="code",defaultValue="denied")String code, HttpServletRequest req 
			, HttpServletResponse res,RedirectAttributes redirectAttributes ,HttpSession session)throws Exception{
	
		//sns 흐름 무시했을경우
		String sns_process = (String) session.getAttribute("sns_process");
		session.removeAttribute("sns_process");
		if(!"sns_process".equals(sns_process)){
			throw new BizException("login.00005");
		}
			
		// code넘어온 값이 "denied"일때 3자 제공 동의하지 경우 alert 진행불가
		if ("denied".equals(code)) {
			redirectAttributes.addFlashAttribute("login", true);
			redirectAttributes.addFlashAttribute("err","제3자 제공 동의 하지 않으면 가입불가합니다.");
		} else {
			try {
				// 인스타그램은 AccessToken 받음과 동시에 user정보 뿌려줌
				JsonNode ex = SnsUtil.instargram_getAccessToken(code);

				JsonNode user = ex.path("user");
				if (user.isMissingNode()) {
					// if "name" node is missing
					return "redirect:/shop/member/popup_snsclose";
				} else {
					String sns_id = user.path("id").asText();
					String sns_nickname = user.path("username").asText();

					String type = "instagram";

					SnsVO snsvo = new SnsVO();
					snsvo.setSns_id(sns_id);
					snsvo.setSns_type(type);
					snsvo.setSns_nickname(sns_nickname);

					FrontMemberVO fv = new FrontMemberMyinfoVO();
					// 회원가입 로그인 구분
					fv = service.frontMemberInfoBySns(snsvo);

					if (null != fv) {
						sns_id = "I" + sns_id;
						fv.setM_id(sns_id);

						// 로그인 서비스에 보낼 비밀번호 인증 필요없다는 구분자
						req.setAttribute("sns_login", true);

						// 팝업창 관리 true ->로그인 아니라면 join페이지
						redirectAttributes.addFlashAttribute("login", true);
						// 로그인처리
						service.setFrontLoginProcess(fv, req);
						// 로그인 실패하면 err메세지와함께 alert출력

					} else {
						// 세션에 유저정보 담아 join페이지로
						session.setAttribute("sns_id", sns_id);
						session.setAttribute("sns_type", type);
						session.setAttribute("sns_nickname", sns_nickname);
					}
				}
			} catch (NullPointerException e) {
				return "redirect:/shop/member/login";
			} catch (BizException e) {
				// 로그인 실패하면 err메세지와함께 alert출력
				redirectAttributes.addFlashAttribute("err", e.getErrorMessage());
			}
		}
		return "redirect:/shop/member/popup_snsclose";

	}
	
	@RequestMapping(value="snslogout.dojson")
	public String snslogout(@RequestParam String type, 
			HttpServletRequest req, HttpServletResponse res, Model model, HttpSession session) throws Exception {
		    
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("m_no", userInfo.getM_no());
		paramMap.put("sns_type", type);
		int rst = this.service.frontMemberSnsInfoDelete(paramMap);
		
		model.addAttribute("result", rst);
		
		return "dojson";
	}

	@RequestMapping(value="login_ok")
	public String login_ok(@ModelAttribute("frontMemberVO") FrontMemberVO frontMemberVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("sample member");
		}
		//사용자 login 처리
		service.setFrontLoginProcess(frontMemberVO, req);
		//비회원 키 삭제
		//HttpSession session = req.getSession();
		//session.removeAttribute("nonMemberkey");
		
		//referer 삭제
		HttpSession session = req.getSession();
		session.removeAttribute("referer");
		
//		Map<String, Object> param= new HashMap<String, Object>();
//		String[] category = null;
//		String[] goodsno=null;
//		ShopLibFunction.insertGoodsOrderCoupon(param, req, category, goodsno);
		return "dojson";
	}
	
	@RequestMapping(value="logout")
	public String logout(@ModelAttribute("frontMemberVO") FrontMemberVO frontMemberVO
			, HttpServletRequest req 
			, HttpServletResponse res
			, Model model) throws Exception {
		
		// 로그 남길때 항상 if(logger.isDebugEnabled()) 사용
		if(logger.isDebugEnabled()){
			logger.debug("sample member");
		}
		
		//사용자 logOut 처리
		service.setFrontLogOutProcess(req);
		
		return "redirect:/shop/main/index";
	}
	
	// front 고객센터 > 아이디찾기
	@RequestMapping(value="find_id")
	public String findId(@ModelAttribute("memberVO") FrontMemberFindVO memberVO, Model model) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("front member >>>>>>>>>>>>>>>>>>>> findId");
		}
		
		List<String> idList = null;
		
//		//ResultTable rtList = null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		if("Y".equals(memberVO.getAct())) {
			paramMap.put("name", memberVO.getSrchName());
			
			if(StringUtils.hasText(memberVO.getSrchNum1()) && StringUtils.hasText(memberVO.getSrchNum2())) {
				paramMap.put("resno1", memberVO.getSrchNum1());
				paramMap.put("resno2", memberVO.getSrchNum2());
			}
			
			if(StringUtils.hasText(memberVO.getSrchMail())) {
				paramMap.put("email", memberVO.getSrchMail());
			}
			
			idList = this.service.frontFindID(paramMap);
		}

		if(idList != null && idList.size() > 0) {
			memberVO.setIdList(idList);
			memberVO.setM_id("use");
		} else {
			memberVO.setM_id("");
		}
		
		memberVO.setResnoChk(ShopConfig.getInstance().getProperty("fieldset", "reqField", "resno", "N/A"));
		logger.debug("1번"+memberVO);
		memberVO.setEmailChk(ShopConfig.getInstance().getProperty("fieldset", "reqField", "email", "N/A"));
		logger.debug("2번"+memberVO);
		model.addAttribute("memberVO", memberVO);

		return "/shop/member/find_id";
	}
	
	// front 고객센터 > 비밀번호 찾기
	@RequestMapping(value="find_pwd")
	public String findPwd(@ModelAttribute("memberVO") FrontMemberFindVO memberVO, HttpServletRequest req, Model model) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if("Y".equals(memberVO.getAct())) {
			paramMap.put("email", memberVO.getSrchMail());
			
			if(this.service.frontFindPassword(paramMap) == 1) {
				//회원정보 조회
				Map<String, Object> memberMap = new HashMap<String, Object>();
				memberMap.put("m_id", memberVO.getSrchMail());
				GdMember memberInfo = this.service.frontMemberInfo(memberMap);
				
				if(memberInfo != null) {
					memberVO.setM_id(memberInfo.getMid());
					memberVO.setPassword(memberInfo.getPassword());
					memberVO.setSrchName(memberInfo.getName());
					this.service.setFrontNewPassword(memberVO, memberMap, req);
					
					model.addAttribute("result", true);
					model.addAttribute("msg", "M01");
				}else {
					model.addAttribute("result", false);
					model.addAttribute("msg", "M02");
				}
			} else {
				model.addAttribute("result", false);
				model.addAttribute("msg", "M02");
			}
		}
		return "/shop/member/find_pwd";
	}
	
	/**
	 * 비밀번호 변경
	 * @param paramMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="popup_change_pwd")
	public String changePwd(@ModelAttribute("memberVO") FrontMemberFindVO memberVO, Model model) throws Exception {
		String token = memberVO.getToken();
		if( StringUtils.isEmpty(token) ) {
			model.addAttribute("result", false);
			model.addAttribute("msg", "M01");
			return "/shop/member/popup_change_pwd";
		}
		
		try {
			String desToken = Aes128Util.decode(token);
			String[] tokenArr = desToken.split("\\|");
			
			String mid = tokenArr[0];
			String pwd = tokenArr[1];
			String time = tokenArr[2];
			
			//회원정보 조회
			Map<String, Object> searchMap = new HashMap<String, Object>();
			searchMap.put("m_id", mid);
			GdMember memberInfo = this.service.frontMemberInfo(searchMap);
			if(memberInfo == null) {
				model.addAttribute("result", false);
				model.addAttribute("msg", "M02");
				return "/shop/member/popup_change_pwd";
			}
			
			if( !pwd.equals(memberInfo.getPassword()) ) {
				model.addAttribute("result", false);
				model.addAttribute("msg", "M03");
				return "/shop/member/popup_change_pwd";
			}
			
			SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			Date expTime = sdformat.parse(time);
			Calendar cal = Calendar.getInstance();
			cal.setTime(expTime);
			cal.add(Calendar.MINUTE, 30);
			String expiredTime = sdformat.format(cal.getTime());
			String now = DateUtil.getFormatToday("yyyy-MM-dd HH:mm:ss");
			//만료시간 <= 현재시간
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(dateFormat.parse(expiredTime).getTime() <= dateFormat.parse(now).getTime()) {
				model.addAttribute("result", false);
				model.addAttribute("msg", "M04");
				return "/shop/member/popup_change_pwd";
			}
			
			model.addAttribute("result", true);
			model.addAttribute("token", token);
		}catch (Exception e) {
			model.addAttribute("result", false);
			model.addAttribute("msg", "M05");
			return "/shop/member/popup_change_pwd";
		}
		return "/shop/member/popup_change_pwd";
	}
	
	/**
	 * 비밀번호 변경 수정처리
	 * @param paramMap
	 * @param req
	 * @param resp
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="changePwdIndb.dojson")
	public String changePwdIndb(@ModelAttribute("memberVO") FrontMemberFindVO memberVO, HttpServletRequest req, HttpServletResponse resp, Model model ,HttpSession session) throws Exception {
		String token = memberVO.getToken();
		if( StringUtils.isEmpty(token) ) {
			model.addAttribute("result", false);
			model.addAttribute("msg", "M01");
			return "dojson";
		}
		
		try {
			String desToken = Aes128Util.decode(token);
			String[] tokenArr = desToken.split("\\|");
			
			String mid = tokenArr[0];
			String pwd = tokenArr[1];
			String time = tokenArr[2];
			
			//회원정보 조회
			Map<String, Object> searchMap = new HashMap<String, Object>();
			searchMap.put("m_id", mid);
			GdMember memberInfo = this.service.frontMemberInfo(searchMap);
			if(memberInfo == null) {
				model.addAttribute("result", false);
				model.addAttribute("msg", "M02");
				return "dojson";
			}
			
			if( !pwd.equals(memberInfo.getPassword()) ) {
				model.addAttribute("result", false);
				model.addAttribute("msg", "M03");
				return "dojson";
			}
			
			SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			Date expTime = sdformat.parse(time);
			Calendar cal = Calendar.getInstance();
			cal.setTime(expTime);
			cal.add(Calendar.MINUTE, 30);
			String expiredTime = sdformat.format(cal.getTime());
			String now = DateUtil.getFormatToday("yyyy-MM-dd HH:mm:ss");
			//만료시간 <= 현재시간
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(dateFormat.parse(expiredTime).getTime() <= dateFormat.parse(now).getTime()) {
				model.addAttribute("result", false);
				model.addAttribute("msg", "M04");
				return "dojson";
			}
			
			String joinPwd1 = String.valueOf( memberVO.getJoin_pwd1() );
			String joinPwd2 = String.valueOf( memberVO.getJoin_pwd2() );
			
			if(!joinPwd1.equals(joinPwd2)) {
				model.addAttribute("result", false);
				model.addAttribute("msg", "M05");
				return "dojson";
			}
			
			//비밀번호 업데이트
			Map<String, Object> updateMap = new HashMap<String, Object>();
			updateMap.put("m_id", mid);
			updateMap.put("newPass", joinPwd1);
			this.service.frontNewPassword(updateMap);
			
			model.addAttribute("result", true);
		}catch (Exception e) {
			model.addAttribute("result", false);
			model.addAttribute("msg", "M06");
			return "/shop/member/popup_change_pwd";
		}
			
		return "dojson";
	}
	
	// front 마이페이지 > 내정보수정
	@RequestMapping(value="myinfo")
	public String myInfo(@ModelAttribute("myInfoVO") FrontMemberMyinfoVO myInfoVO, HttpServletRequest req, Model model) throws Exception {
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("m_id", userInfo.getUserId());
		paramMap.put("m_name", userInfo.getUserName());
		
		GdMember member = this.service.frontMemberInfo(paramMap);
		
		if(member == null) {
			myInfoVO.setErrMessage("고객님의 정보를 조회하는 중 오류가 발생하였습니다.");
			return "/shop/member/myinfo"; 
		}
		
		myInfoVO.setGdMember(member);
		myInfoVO.setM_id(userInfo.getUserId());
		
		// 관심분야
		this.service.interestCodeList(member, myInfoVO);
		myInfoVO.initOutData();
		
		//sns
		paramMap.put("m_no", userInfo.getM_no());
		paramMap.put("sns_type", "facebook");
		myInfoVO.setFacebook(this.service.frontMemberSnsInfoSelect(paramMap));
		paramMap.put("sns_type", "google");
		myInfoVO.setGoogle(this.service.frontMemberSnsInfoSelect(paramMap));
		paramMap.put("sns_type", "kakao");
		myInfoVO.setKakao(this.service.frontMemberSnsInfoSelect(paramMap));
		paramMap.put("sns_type", "apple");
		myInfoVO.setApple(this.service.frontMemberSnsInfoSelect(paramMap));

		model.addAttribute("myInfoVO", myInfoVO);
		
		return "/shop/member/myinfo";
	}
	
	@RequestMapping(value="modindb")
	public String indb(@ModelAttribute("indbVO") FrontMemberFindIndbVO indbVO, HttpServletRequest req, Model model) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		//회원정보수정
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		if(userInfo == null || userInfo.getUserId() == null) {
			indbVO.setAlertMessage("로그인 정보가 만료되었습니다.");
			indbVO.setRedirectUrl(req.getContextPath() + "/shop/member/login");
			return "/shop/member/myinfo";
			
		} else {
			//패스워드 확인 
			FrontMemberVO memberVO = new FrontMemberVO();
			memberVO.setM_id(userInfo.getUserId());
			memberVO.setPassword(indbVO.getPassword());
			GdMember gdMember = this.service.frontLoginCheckPassword(memberVO);
			if (gdMember == null) {
				indbVO.setAlertMessage("패스워드를 정확히 입력해주세요.");
				indbVO.setRedirectUrl(req.getContextPath() + "/shop/member/myinfo");
				return "/shop/member/myinfo";
			}
			
			if (!"".equals(indbVO.getNewpassword2())) {
				if (indbVO.getNewpassword().equals(indbVO.getNewpassword2())) {
					paramMap.put("password", indbVO.getNewpassword2());
					memberVO.setPassword(indbVO.getNewpassword2());
				} else {
					indbVO.setAlertMessage("패스워드를 정확히 입력해주세요.");
					indbVO.setRedirectUrl(req.getContextPath() + "/shop/member/myinfo");
					return "/shop/member/myinfo";
				}
			}

			paramMap.put("m_id", userInfo.getUserId());
			paramMap.put("birth_year"	, indbVO.getBirthyear());
			if(StringUtils.hasText(indbVO.getBirthMonth())) {
				paramMap.put("birth", StringUtil.lpad(indbVO.getBirthMonth(), 2, '0') + StringUtil.lpad(indbVO.getBirthDate(), 2, '0'));
			}
			paramMap.put("mobilec" 		, indbVO.getMobilec());
			paramMap.put("mobile" 		, indbVO.getMobile());
			paramMap.put("mailling"		, "on".equals(indbVO.getMailling()) ? "y" : "n");
			paramMap.put("name1"		, indbVO.getName1()); //이름
			paramMap.put("name2"		, indbVO.getName2()); //성
			paramMap.put("name"			, indbVO.getName2() + indbVO.getName1());
			paramMap.put("customsnum"	, indbVO.getCustomsnum());
			paramMap.put("sex"			, indbVO.getSex());
			
			//paramMap = this.service.setMemberInfoParam(paramMap, indbVO, req);
			
			// 회원정보 수정
			this.service.frontMemberUpdate(paramMap);
			this.service.setFrontLoginProcess(memberVO, req);

			//로그인추가정보를 위한 셋팅(PHP 동일하게 설정)
			indbVO.setAlertMessage("회원정보가 수정되었습니다.");
			indbVO.setRedirectUrl(req.getContextPath() + "/shop/member/myinfo");
		}
		model.addAttribute("indbVO", indbVO);
		
		return "/shop/member/myinfo";
	}

	@RequestMapping(value="chkNickName")
	@ResponseBody
	public String chkNickName(@RequestParam String nickname, HttpServletRequest req) {
		String m_id = ShopSessionObject.getSessionObject(req).getUserInfo().getUserId();
		return this.service.chkNickName(nickname, m_id);
	}
	
	@RequestMapping(value="chkJoinNickName")
	@ResponseBody
	public String chkJoinNickName(@RequestParam String nickname) {
		return this.service.chkNickName(nickname);
	}
	
	@RequestMapping(value="hack")
	public String hack(HttpServletRequest req, Model model) {
		
		return "/shop/member/hack";
	}
	
	@RequestMapping(value="hack/indb")
	public String hackIndb(@ModelAttribute("memberHackVO") FrontMemberHackVO memberHackVO, HttpServletRequest req, Model model) throws Exception {
		UserInfo userInfo = ShopSessionObject.getSessionObject(req).getUserInfo();
		
		memberHackVO.setM_id(userInfo.getUserId());
		GdMember gdMember = this.service.frontLoginCheckPassword(memberHackVO);
		
		//sns인지 체크
		if (gdMember == null) {
			memberHackVO.setAlertMessage("비밀번호가 일치하지 않습니다.");
			memberHackVO.setRedirectUrl("/shop/member/hack");
		} else {
			/*String[] outComplains = memberHackVO.getOutComplain().split(",");
			for(int i = 0, size = outComplains.length; i < size; i++) {
				outComplains[i] = Integer.toString((int)Math.pow(2, Double.valueOf(outComplains[i]).doubleValue()));
			}
			
			String sumComplain = Integer.toString(StringUtil.array_sum(outComplains));*/
			String sumComplain = "";
			String REMOTE_ADDR = req.getRemoteAddr();
			
			//주민등록번호 가져오기 
			GdMember member = this.service.frontLoginCheckID(memberHackVO.getM_id());
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("m_id", memberHackVO.getM_id());
			paramMap.put("name", userInfo.getUserName());
			paramMap.put("m_no", userInfo.getM_no());
			paramMap.put("ip", REMOTE_ADDR);
			paramMap.put("outComplain", sumComplain);
			paramMap.put("skin", member.getSkin());
			paramMap.put("klevel", member.getKlevel());
			paramMap.put("customsnum", member.getCustomsnum());
			this.service.frontInsertHackLog(paramMap);
			this.service.frontDeleteMember(userInfo.getM_no());
			this.service.frontDeleteSns(userInfo.getM_no());
			this.service.frontDeleteEmoney(userInfo.getM_no());
			
			this.service.setFrontLogOutProcess(req);
			
			//메일발송  
			AmazonSES mailConfig = new AmazonSES();
			Map<String,Object> mailMap = new HashMap<String,Object>();
			
			mailMap.put("name", userInfo.getUserName());
			//수신자이메일 , mode 메일종류 , mailMap  
			//mailConfig.mailSender(String.valueOf(userInfo.getXkey().get("email")), "12", mailMap);
			
			memberHackVO.setAlertMessage("정상적으로 회원탈퇴처리가 승인되었습니다. \n 그동안 이용해 주셔서 진심으로 감사합니다.");
			memberHackVO.setRedirectUrl("/shop/main/index");
		}

		model.addAttribute("memberHackVO", memberHackVO);
		return "/shop/member/hack";
	}
	
	@RequestMapping(value = "join")
	public String join(HttpServletRequest req, Model model,HttpSession session) {
		String filePath = ConfigClass.value("WEB_DIR_PATH") + "shop/data/clause/";
		String clause1 = "contents_1_"+ConfigClass.getSkin(req)+".html";
		String clause2 = "contents_2_"+ConfigClass.getSkin(req)+".html";
		model.addAttribute("agCompName", ShopConfig.getProperty("compName"));
		model.addAttribute("agShopName", ShopConfig.getProperty("shopName"));
		model.addAttribute("filePath", filePath);
		model.addAttribute("clause1", clause1);
		model.addAttribute("clause2", clause2);
		
		session.removeAttribute("sns_id");
		session.removeAttribute("sns_email");
		session.removeAttribute("sns_type");
		
		return "/shop/member/join";
	}
	@RequestMapping(value = "joinSns")
	public String joinSns(HttpServletRequest req, Model model,HttpSession session) {
		
		String filePath = ConfigClass.value("WEB_DIR_PATH") + "shop/data/clause/";
		String clause1 = "contents_1_"+ConfigClass.getSkin(req)+".html";
		String clause2 = "contents_2_"+ConfigClass.getSkin(req)+".html";
		model.addAttribute("agCompName", ShopConfig.getProperty("compName"));
		model.addAttribute("agShopName", ShopConfig.getProperty("shopName"));
		model.addAttribute("filePath", filePath);
		model.addAttribute("clause1", clause1);
		model.addAttribute("clause2", clause2);
	
		return "/shop/member/join";
	}
	
	@RequestMapping(value = "join_input")
	public String joinInput(String chk1, String chk2, HttpServletRequest req, Model model,HttpSession session) throws Exception {
		//  동의 체크 없이 해당 페이지에 접속하였다면 동의 페이지로 리다이렉트.
		if(!("on".equals(chk1) && "on".equals(chk2))) {
			return "/shop/member/join";
		}
		//sns로그인햇을때 들어오는 id,email
		if(null!=session.getAttribute("sns_id")&&null!=session.getAttribute("sns_email")){
			
		String sns_id = session.getAttribute("sns_id").toString();
		String sns_email = session.getAttribute("sns_email").toString();
		
		model.addAttribute("sns_id",sns_id);
		model.addAttribute("sns_email",sns_email);
		}
		UserInfo userInfo= ShopSessionObject.getSessionObject(req).getUserInfo();
		if(userInfo != null) {
			if(userInfo.getUserId() != null) {
				model.addAttribute("message", "고객님은 로그인 중입니다.");
			}
		}

		FrontMemberMyinfoVO myInfoVO = new FrontMemberMyinfoVO();
		myInfoVO.initOutData();
		
		this.service.interestCodeList(new GdMember(), myInfoVO);
		
		model.addAttribute("infoVO", myInfoVO);
		return "/shop/member/join_input";
	}
	
	@RequestMapping(value = "chkId")
	@ResponseBody
	public String chkId(@RequestParam("mid") String mid) {
		return this.service.chkId(mid);
	}
	
	
	@RequestMapping(value="joinIndb")
	@ResponseBody
	public String joinIndb(@ModelAttribute("infoVO") FrontMemberFindIndbVO indbVO, HttpServletRequest req, HttpServletResponse resp, Model model ,HttpSession session) throws Exception {
		String result = this.service.chkId(indbVO.getJoin_id());
		if(!"0".equals(result)) {
			if("9".equals(result)) {
				return "사용할 수 없는 아이디입니다.";
			} else if("1".equals(result)) {
				return "이미 사용중인 아이디입니다.";
			} else if("2".equals(result)) {
				return "잘못된 이메일 형식입니다.";
			}
		}
		
		indbVO.setSkin(ConfigClass.getSkin(req));
		
		Map<String, Object> paramMap = this.service.setMemberInfoParam(new HashMap<String, Object>(), indbVO, req);
		paramMap.put("m_id", indbVO.getJoin_id());
		paramMap.put("password", indbVO.getJoin_pwd1());
		paramMap.put("name", indbVO.getJoin_name2() + indbVO.getJoin_name1());
		paramMap.put("name1", indbVO.getJoin_name1());
		paramMap.put("name2", indbVO.getJoin_name2());
		paramMap.put("email", indbVO.getJoin_id());
		paramMap.put("skin", ConfigClass.getSkin(req));
		paramMap.put("status", ShopConfig.getProperty("fieldset", "joinset", "status"));
		paramMap.put("emoney", ShopConfig.getProperty("fieldset", "joinset", "emoney"));
		paramMap.put("level", ShopConfig.getProperty("fieldset", "joinset", "grp"));
		paramMap.put("mailling", "y".equals(indbVO.getMailling()) ? "y" : "n");
		paramMap.put("skin", indbVO.getSkin());
			
		// 회원등록
		this.service.frontMemberInsert(paramMap);
		indbVO.setMno(Integer.parseInt(StringUtil.nvl(paramMap.get("mno"), "0")));
			
		//회원가입메일발송  
		AmazonSES mailConfig = new AmazonSES();
		Map<String,Object> mailMap = new HashMap<String,Object>();		
		mailMap.put("name", indbVO.getJoin_name1() + indbVO.getJoin_name2());
		
		//수신자이메일 , mode 메일종류 , mailMap  
		//mailConfig.mailSender(indbVO.getEmail(), "10", mailMap);
			
		// 회원가입쿠폰 발급
		int couponCnt = this.service.joinCouponOffer(indbVO);
			
		// 회원 로그인
		Map<String, Object> loginMap = new HashMap<String, Object>();
		loginMap.put("m_id", indbVO.getJoin_id());
		loginMap.put("password", indbVO.getJoin_pwd1());
		GdMemberJoinGdMemberGrp gdMember = this.service.frontMemberGrpSelect(loginMap);
			
		if(gdMember != null) {
			// 적립금 내역 입력
			this.service.emoneyInsert(indbVO, (String)paramMap.get("emoney"), gdMember.getAddEmoney());
			
			if(gdMember.getStatus() == 1) {
				FrontMemberVO frontMemberVO = new FrontMemberMyinfoVO();
				frontMemberVO.setM_id(indbVO.getJoin_id());
				frontMemberVO.setPassword(indbVO.getJoin_pwd1());
				this.service.setFrontLoginProcess(frontMemberVO, req);
				//  xkey 세팅부분 login 처리시 작업되고 있음
				
				//쿠키설정
				WebUtil.setCookies(resp, "Xtime", "/", 0);
				
				// last_login, cnt_login 업데이트
				this.service.frontMemberLoginUpdate(gdMember.getMno());
			}
		}
		result = String.valueOf(couponCnt);
			
		return result;
	}
	
	//2020_02_11 이현빈 비회원 로그인 
	@RequestMapping(value="guest_login")
	public String guest_login(HttpServletRequest req
			,HttpServletResponse resp
			,Model model
			,HttpSession session) throws Exception {
		
		if(logger.isDebugEnabled()) logger.debug("####[FrontMemberController] -> guest_login");
		Map<String,Object> ordnoInfo = shopService.getOrderInfo(Long.parseLong(req.getParameter("ordno")));
		String nameOrder = req.getParameter("nameOrder");
		if(ordnoInfo != null && nameOrder.equals(ordnoInfo.get("nameOrder"))){
			session.setAttribute("nonMemberkey", req.getParameter("ordno"));
		}else{
			//주문정보를 확인해주세요.
			throw new BizException("login.00006");
		}
		return "dojson";
	}
	
	
	/**
	 * 프론트 로그인 체크
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="ajaxfrontLogin.doJson")
	public String ajaxfrontLogin(HttpServletRequest req , HttpServletResponse res, Model model) throws Exception {
		ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		
		boolean isLogin = false;
		if( shopSO.isShopLogin()) {
			isLogin = true;
		}
		model.addAttribute("isLogin", isLogin);
		
		return "doJson";
	}
}
