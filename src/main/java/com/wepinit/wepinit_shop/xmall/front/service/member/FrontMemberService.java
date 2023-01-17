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
* <pre>
* 업무구분명			:	Community
* 세부업무구분명	: 	사용자 > Community
* 작성자 				: 	이병환
* 설명 				: 	사용자 Community Service
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170208		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.service.member;

import com.fasterxml.jackson.databind.JsonNode;
import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.*;
import com.wepinit.wepinit_shop.xmall.admin.service.login.LoginService;
import com.wepinit.wepinit_shop.xmall.admin.vo.login.LoginVO;
import com.wepinit.wepinit_shop.xmall.common.AmazonSES;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.session.ShopSessionObject;
import com.wepinit.wepinit_shop.xmall.common.util.SnsUtil;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdMemberJoinGdMemberGrp;
import com.wepinit.wepinit_shop.xmall.front.dao.member.FrontMemberMapper;
import com.wepinit.wepinit_shop.xmall.front.vo.member.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@Transactional
public class FrontMemberService {
	
	Logger logger = LoggerFactory.getLogger(FrontMemberService.class);
	
	@Resource
	FrontMemberMapper mapper;	

	@Resource
	LoginService loginService;
	
	/**
	 * 사용자 Login 처리 service
	 * @param frontMemberVO
	 * @param req
	 * @throws Exception
	 */
	public void setFrontLoginProcess(FrontMemberVO frontMemberVO, HttpServletRequest req) throws Exception{
		ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		LoginVO loginVO = new LoginVO();
		loginVO.setM_id(frontMemberVO.getM_id());
		loginVO.setPassword(frontMemberVO.getPassword());
		shopSO.setSessionObject(loginService.process(loginVO, req));
	}
	
	public FrontMemberVO frontMemberInfoBySns(SnsVO snsvo) throws Exception{
		return mapper.frontMemberInfoBySns(snsvo);
	}
	
	/**
	 * 사용자 LogOut 처리 service
	 * @param frontMemberVO
	 * @param req
	 * @throws Exception
	 */
	public void setFrontLogOutProcess(HttpServletRequest req) throws Exception{
		
		ShopSessionObject shopSO = ShopSessionObject.getSessionObject(req);
		shopSO.removeSession();
		HttpSession session = req.getSession();
		session.removeAttribute("nonMemberkey");
		
	}
	
	/**
	 * 고객센터 > 아이디찾기 (mapper)
	 * @param map
	 * @return List<String>
	 */
	public List<String> frontFindID(Map<String, Object> map) {
		return this.mapper.frontFindID(map);
	}
	
	/**
	 * 고객센터 > 비밀번호 찾기-정보조회 (mapper)
	 * @param
	 * @return int
	 */
	public int frontFindPassword(Map<String, Object> map) {
		return this.mapper.frontFindPassword(map);
	}
	
	/**
	 * 고객센터 > 비밀번호 찾기-새로운 비밀번호 저장 (mapper)
	 * @param
	 * @return 
	 */
	public void frontNewPassword(Map<String, Object> map) {
		this.mapper.frontNewPassword(map);
	}
	
	/**
	 * 마이페이지 > 정보수정-정보 가져오기 (mapper)
	 * @param
	 * @return GdMember
	 */
	public GdMember frontMemberInfo(Map<String, Object> map) {
		return this.mapper.frontMemberInfo(map);
	}
	
	/**
	 * 고객센터 > 비밀번호 찾기 (Service)
	 * @param
	 * @return 
	 * @throws Exception 
	 */
	public void setFrontNewPassword(FrontMemberFindVO memberVO, Map<String, Object> map, HttpServletRequest req) throws Exception {
		//메일발송  
		AmazonSES mailConfig = new AmazonSES();
		Map<String,Object> mailMap = new HashMap<String,Object>();
		mailMap.put("id", memberVO.getM_id());
		mailMap.put("pw", memberVO.getPassword());
		mailMap.put("name", memberVO.getSrchName());
		String tokenurl =  ConfigClass.value("SHOP_URL") + "/shop/member/popup_change_pwd?token=" + Aes128Util.encode(memberVO.getM_id() + "|" + memberVO.getPassword() + "|" +  DateUtil.getFormatToday("yyyy-MM-dd HH:mm:ss"));
		mailMap.put("tokenurl", tokenurl);
		
		String html = "11_" + ConfigClass.getSkin(req);
		//수신자이메일 , mode 메일종류 , mailMap  
		mailConfig.mailSender(memberVO.getM_id(), html, mailMap);
	}

	/**
	 * 회원가입(수정) > 닉네임중복체크 (Service)
	 * @param
	 * @return 
	 */
	public String frontMemberChkNickname(Map<String, Object> map) {
		return this.mapper.frontMemberChkNickname(map);
	}
	
	public String frontMemberJoinChkNickname(String nickname) {
		return this.mapper.frontMemberJoinChkNickname(nickname);
	}
	
	public String frontMemberChkEmail(Map<String, Object> map) {
		return this.mapper.frontMemberChkEmail(map);
	}
	
	public void frontMemberUpdate(Map<String, Object> map) {
		this.mapper.frontMemberUpdate(map);
	}
	
	public List<GdCode> itemGdCode(String groupcd) {
		return this.mapper.itemGdCode(groupcd);
	}
	
	public GdMember frontLoginCheckPassword(FrontMemberVO memberVO) {
		return this.mapper.frontLoginCheckPassword(memberVO);
	}
	
	public GdMember frontLoginCheckID(String m_id) {
		return this.mapper.frontLoginCheckID(m_id);
	}
	
	public void frontInsertHackLog(Map<String, Object> map) {
		this.mapper.frontInsertHackLog(map);
	}
	
	public void frontDeleteMember(int m_no) {
		this.mapper.frontDeleteMember(m_no);
	}
	public void frontDeleteSns(int m_no){
		this.mapper.frontDeleteSns(m_no);
	}

	
	public void frontDeleteEmoney(int m_no) {
		this.mapper.frontDeleteEmoney(m_no);
	}
	
	public void interestCodeList(GdMember member, FrontMemberMyinfoVO myInfoVO) throws Exception {
		List<FrontMemberMyinfoCodeVO> myInfoCodeList = new ArrayList<FrontMemberMyinfoCodeVO>();
		List<GdCode> codeList = CodeUtil.codeitem("like");
		int interest = StringUtils.hasText(member.getInterest()) ? Integer.parseInt(member.getInterest()) : 0;

		if(codeList.size() > 0) {
			for(int i = 0, size = codeList.size(); i < size; i++) {
				FrontMemberMyinfoCodeVO myinfoCodeVO = new FrontMemberMyinfoCodeVO();
				int itemcd = Integer.parseInt(codeList.get(i).getItemcd());
				int val = (int)Math.pow(2, itemcd);
				myinfoCodeVO.setVal((int)Math.pow(2, itemcd));
				myinfoCodeVO.setItemnm(codeList.get(i).getItemnm());
				if((val&interest) == val) {
					myinfoCodeVO.setChecked("checked");
				}
				myInfoCodeList.add(myinfoCodeVO);
			}
		}
		
		
		myInfoVO.setCodeList(myInfoCodeList);
	}
	
	public String frontMemberChkid(String m_id) {
		return this.mapper.frontMemberChkid(m_id);
	}
	
	public String chkId(String m_id) {
		if(ShopConfig.getProperty("fieldset", "joinset", "unableid").indexOf(m_id) > -1) {
			//  sb 에 태그추가
			return "9";
		// 등록된 아이디 존재하는지 확인
		} else {
			String pattern2 = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
			if(!Pattern.matches(pattern2, m_id)) {
				return "2";
			}
			
			String id = this.frontMemberChkid(m_id);
			if(StringUtils.hasText(id)) {
				return "1";
			}
			return "0";
		}
	}
	
	public String chkNickName(String nickname, String m_id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// 닉네임 체크
		paramMap.put("nickname", nickname);
		paramMap.put("m_id", m_id);
		String result = this.frontMemberChkNickname(paramMap);
		//  sb 에 태그추가
		
		return StringUtils.hasText(result) ? "2" : "0";
	}
	
	public String chkNickName(String nickname) {
		String result = this.frontMemberJoinChkNickname(nickname);
		//  sb 에 태그추가
		
		return StringUtils.hasText(result) ? "2" : "0";
	}

	public String profileUpload(FrontMemberFindIndbVO indbVO) throws Exception {
		//화일업로드 경로
		String filePath = ConfigClass.value("WEB_DIR_PATH") + "shop/data/skin/en/member/profile/";
		String oldPro = indbVO.getOldProfile();

		if(StringUtils.hasText(oldPro)) {
			FileUtil.deleteFile(ConfigClass.value("WEB_DIR_PATH") + "shop/data/skin/en/member/profile", oldPro);
		}

		// 아이콘 업로드
		String fileName = StringUtils.hasText(indbVO.getMpUpload().getOriginalFilename()) ? FileUtil.uploadFile2(filePath, indbVO.getMpUpload().getOriginalFilename(), indbVO.getMpUpload().getBytes(), false) : "";
		
		return fileName;
	}
	
	public Map<String, Object> setMemberInfoParam(Map<String, Object> paramMap, FrontMemberFindIndbVO indbVO, HttpServletRequest req) throws Exception {
		String email = indbVO.getEmail1() + "@" + indbVO.getEmail2();
		if(!StringUtils.hasText(indbVO.getEmail1()) || !StringUtils.hasText(indbVO.getEmail2())) {
			email = null;
		}
		paramMap.put("email", email);
		paramMap.put("profile", indbVO.getMpUpload() != null ? this.profileUpload(indbVO) : "user.gif");
		
		if(StringUtils.hasText(indbVO.getBirthMonth())) {
			paramMap.put("birth", StringUtil.lpad(indbVO.getBirthMonth(), 2, '0') + StringUtil.lpad(indbVO.getBirthDate(), 2, '0'));
		}
		paramMap.put("zipcode"		, indbVO.getZipcode());
		paramMap.put("phone"		, StringUtil.implode("-", req.getParameterValues("phone")));
		paramMap.put("fax"			, "");
		paramMap.put("mobile"		, StringUtil.implode("-", req.getParameterValues("mobile")));
		paramMap.put("mailling"		, "on".equals(indbVO.getMailling()) ? "y" : "n");
		paramMap.put("sms"			, "on".equals(indbVO.getSms()) ? "y" : "n");
		
		if(indbVO.getMarriyn().equals("y")) {
			paramMap.put("marriyn", "y");
			if(indbVO.getMarryYear() != null) {
				StringBuffer marryDay = new StringBuffer();
				marryDay.append(StringUtil.lpad(indbVO.getMarryYear(), 4, '0'));
				marryDay.append(StringUtil.lpad(indbVO.getMarryMonth(), 2, '0'));
				marryDay.append(StringUtil.lpad(indbVO.getMarryDate(), 2, '0'));
				paramMap.put("marridate", marryDay.toString());
			}
		} else {
			paramMap.put("marriyn", "n");
		}

		paramMap.put("interest", StringUtil.array_sum(req.getParameterValues("interest")));
		
		paramMap.put("name"			, indbVO.getName());
		paramMap.put("sex"			, indbVO.getSex());
		paramMap.put("birth_year"	, indbVO.getBirthyear());
		paramMap.put("address"		, indbVO.getAddress());
		paramMap.put("addresssub"	, indbVO.getAddresssub());
		paramMap.put("job"			, indbVO.getJob());
		paramMap.put("memo"			, indbVO.getMemo());
		return paramMap;
	}
	
	public GdMember frontMemberRecommidSelect(String recommid) {
		return this.mapper.frontMemberRecommidSelect(recommid);
	}
	
	public void frontMemberRecommEmoneyInsert(Map<String, Object> map) {
		this.mapper.frontMemberRecommEmoneyInsert(map);
	}
	
	public void frontMemberRecommEmoneyUpdate(Map<String, Object> map) {
		this.mapper.frontMemberRecommEmoneyUpdate(map);
	}
	
	public void emoneyInsert(FrontMemberFindIndbVO indbVO, String emoney, int addEmoney) throws Exception {
		// insert m_no 가져오기
		Map<String, Object> tableMaxParamMap = new HashMap<String, Object>();
		tableMaxParamMap.put("tbl_name", "gd_member");
		tableMaxParamMap.put("col_name", "m_no");
		
		int tableMaxField = this.mapper.tableMaxFieldSelect(tableMaxParamMap);
		
		// 적립금 내역 입력
		Map<String, Object> emoneyInsertMap = new HashMap<String, Object>();
		
		if(tableMaxField > 0) {
			indbVO.setMno(tableMaxField);
			emoneyInsertMap.put("m_no", tableMaxField);
		}			
		emoneyInsertMap.put("memo", CodeUtil.getCodeName("point", "01"));
		emoneyInsertMap.put("emoney", emoney);
		emoneyInsertMap.put("gbn", "S"); // 구분(S:적립, U:사용, E:소멸, C:취소/환불)
		emoneyInsertMap.put("addEmoney", addEmoney); //적립율
		this.mapper.frontMemberEmoneyInsert(emoneyInsertMap);

	}
	
	public void saveEmoney(FrontMemberFindIndbVO indbVO, int recommidMno) {
		// 추천인에게 적립금 적립
		String recommEmoneyTemp =  "";
		if(ShopConfig.getProperty("fieldset", "joinset", "recomm_emoney") == null || "".equals(ShopConfig.getProperty("fieldset", "joinset", "recomm_emoney"))){
			 recommEmoneyTemp = "0";
		}else {
			 recommEmoneyTemp = ShopConfig.getProperty("fieldset", "joinset", "recomm_emoney");
		}
		
		int recommEmoney = Integer.parseInt(recommEmoneyTemp);
		if(recommEmoney > 0) {
			Map<String, Object> recommMap = new HashMap<String, Object>();
			recommMap.put("recommid_m_no", recommidMno);
			recommMap.put("recomm_emoney", recommEmoney);
			recommMap.put("memo", "추천인 적립 ("+indbVO.getMid()+")");
			// 회원의 추천으로 포인트 적립
			this.frontMemberRecommEmoneyInsert(recommMap);
			// 피추천인 emoney update
			this.frontMemberRecommEmoneyUpdate(recommMap);
		}

		int recommAddEmoney = 0;
		if(ShopConfig.getProperty("fieldset", "joinset", "recomm_add_emoney") == null || "".equals(ShopConfig.getProperty("fieldset", "joinset", "recomm_add_emoney"))){
			recommAddEmoney = 0;
		}else {
			recommAddEmoney = Integer.parseInt(ShopConfig.getProperty("fieldset", "joinset", "recomm_add_emoney"));
		}		
		
		// 추천한사람(가입자) 적립금 적립
		//int recommAddEmoney = Integer.parseInt(ShopConfig.getProperty("fieldset", "joinset", "recomm_add_emoney"));
		if(recommAddEmoney > 0) {
			Map<String, Object> recommendedMap = new HashMap<String, Object>();
			recommendedMap.put("recomm_emoney", recommAddEmoney);
			recommendedMap.put("recommid_m_no", indbVO.getMno());
			recommendedMap.put("memo", "신규가입 추천인 기입 적립");
			this.frontMemberRecommEmoneyInsert(recommendedMap);
			this.frontMemberRecommEmoneyUpdate(recommendedMap);
		} 
	}
	
	// 회원가입메일
	public void joinMail(FrontMemberFindIndbVO indbVO) {
		if(StringUtils.hasText(indbVO.getEmail()) && "y".equals(ShopConfig.getProperty("mailyn_10"))) {
			// 이메일발송 xMall 의 xCube 에는 메일유틸이 없어서 처리하지 않음. 필요시 하기 코드와 asis 의 MailUtil 참조하여 구현 필요함.

/*			// MailUtil 정의
			MailUtil mail = new MailUtil(dbHandler);
			
			requestSet.setProperty("ordno", "1245129520147");
			
			// 주문내역(TEST)
			//rtList4 = dbHandler.executeXmlQuery("xmall_order/orders_popup_order_1", requestSet);
			
			// 주문내역(TEST)
			// 반복부 변경 map 세팅(key : 변경원본, value : 반복부 컬럼명)
			// HashMap map_list = new HashMap();
			//map_list.put("{ordno}", "ordno");		
			//map_list.put("{goodsno}", "goodsno"); 
			
			// E-Mail 변경 map 세팅
			HashMap map = new HashMap();
			// 아래 /home/dev/xmall/shop/conf/email  에 있는 이메일 템플릿 파일의 {keyname} 값을 map 에 대체하여 넘긴다.
			
			map.put("{name}", requestSet.getProperty("name"));		
			map.put("{shopName}", "XMaLL2.0 for MySQL");
			map.put("{shopUrl}", "xmallmy.gnujava.com");
			
			// 주문내역(TEST)
			//map.put("{list}", mail.getEmailTemplateList("order_good", map_list, rtList4));
			
			//보낼 이메일 회원 가입 템플릿 파일에 위 map 에 담은 값으로 파싱 치환
			//String content = sender.parser("tpl_10.html", map);
			
			try {
				// sendSmtpMail("받은이메일주소", "메일제목", "메일내용", false->html)
				//sender.sendSmtpMail(requestSet.getProperty("email"), "안녕하세요~ XMaLL2.0 회원가입 안내 이메일", content, false);
				mail.sendMail(requestSet.getProperty("email"), requestSet.getProperty("name"),  "안녕하세요~ XMaLL2.0 회원가입 안내 이메일", "join", map);	
			}catch(Exception e) {
				log.error("회원가입메일 발송에러");
				e.printStackTrace();
			}*/
		
		}
	}
	
	public int joinCouponOffer(FrontMemberFindIndbVO indbVO) {
		// 회원가입쿠폰 발급
		List<GdCoupon> couponList = this.frontMemberJoinCouponSelect(indbVO.getSkin()); //회원가입 쿠폰조회 
		int couponCnt = 0;
		if(couponList.size() > 0) {
			for(GdCoupon coupon : couponList) {
				Map<String, Object> couponMap = new HashMap<String, Object>();
				couponMap.put("couponcd", coupon.getCouponcd());
				couponMap.put("m_no", indbVO.getMno());
//				couponApplyList => mapper frontMemberCouponApplySelect 거쳐오면 null리턴 4/10 주석처리
//				List<GdCouponApply> couponApplyList = this.mapper.frontMemberCouponApplySelect(couponMap);
//				if(couponApplyList.get(0) != null && "0".equals(couponApplyList.get(0).getCnt())) { 
					String newApplySno = "";
					Map<String, Object> tableMaxFieldMap = new HashMap<String, Object>();
					tableMaxFieldMap.put("tbl_name", "gd_coupon_apply");
					tableMaxFieldMap.put("col_name", "sno");
					int maxInt = this.mapper.tableMaxFieldSelect(tableMaxFieldMap);

					
					if(maxInt > 0) {
						newApplySno = (maxInt + 1) + "";
						couponMap.put("newapplysno", newApplySno);
						this.frontMemberJoinCouponApplyInsert(couponMap);
						this.frontMemberJoinCouponApplymemberInsert(couponMap);
					}
					couponCnt++;
				}		
			}
//		}
		return couponCnt;
	}
	
	public List<GdCoupon> frontMemberJoinCouponSelect(String skin) {
		return this.mapper.frontMemberJoinCouponSelect(skin);
	}
	
	public void frontMemberJoinCouponApplyInsert(Map<String, Object> map) {
		this.mapper.frontMemberJoinCouponApplyInsert(map);
	}
	
	public void frontMemberJoinCouponApplymemberInsert(Map<String, Object> map) {
		this.mapper.frontMemberJoinCouponApplymemberInsert(map);
	}
	
	public GdMemberJoinGdMemberGrp frontMemberGrpSelect(Map<String, Object> map) {
		return this.mapper.frontMemberGrpSelect(map);
	}
	
	public void frontMemberLoginUpdate(int m_no) {
		this.mapper.frontMemberLoginUpdate(m_no);
	}
	
	public void frontMemberInsert(Map<String, Object> map) {
		this.mapper.frontMemberInsert(map);
	}
	
	public void frontMemberSnsInsert(Map<String,Object> map){
		this.mapper.frontMemberSnsInsert(map);
	}
	
	public FrontMemberSnsVO frontMemberSnsInfoSelect(Map<String,Object> map) {
		return this.mapper.frontMemberSnsInfoSelect(map);
	}
	
	public int frontMemberSnsInfoDelete(Map<String,Object> map) {
		return this.mapper.frontMemberSnsInfoDelete(map);
	}
	
	
	public Map<String, Object> kakaoUserInfo(String code, String client_id, String redirect_url){
		// accesstoken 받아오기
		JsonNode jsonToken = SnsUtil.kakao_getAccessToken(code, client_id, redirect_url);
		//access토큰 받아서 유저정보 받아옴
	    JsonNode userInfo = SnsUtil.kakao_getKakaoUserInfo(jsonToken.get("access_token").asText());
	    String sns_id = userInfo.path("id").asText();

	    String sns_email = null;
		String sns_nickname = null;
	
       // 유저정보 카톡에서 가져오기 Get kakao_account
		JsonNode kakaoAccount = userInfo.path("kakao_account");
		if (!kakaoAccount.isMissingNode()) {
			sns_email = kakaoAccount.path("email").asText();
			
			JsonNode profile = kakaoAccount.path("profile");
			if (!profile.isMissingNode()) {
				sns_nickname = profile.path("nickname").asText();
			}
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sns_id", sns_id);
		map.put("sns_email", sns_email);
		map.put("sns_nickname", sns_nickname);
		return map;
	}
	
	public Map<String, Object> facebookUserInfo(String code, String client_id, String client_secret, String redirect_url) {
		// accesstoken 받아오기
		JsonNode jsonToken = SnsUtil.facebook_getAccessToken(code, client_id, client_secret, redirect_url);
		// access토큰 받아서 유저정보 받아옴
		JsonNode userInfo = SnsUtil.facebook_getUserInfo(jsonToken.get("access_token").asText());

		String sns_id = userInfo.path("id").asText();
		String sns_name = userInfo.path("name").asText();
		String sns_email = userInfo.path("email").asText();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sns_id", sns_id);
		map.put("sns_email", sns_email);
		map.put("sns_nickname", sns_name);
		
		return map;
	}
	
	public Map<String, Object> googleUserInfo(String code, String client_id, String client_secret, String redirect_url) {
		// accesstoken 받아오기
		JsonNode jsonToken = SnsUtil.google_getAccessToken(code, client_id, client_secret, redirect_url);
		// access토큰 받아서 유저정보 받아옴
		JsonNode userInfo = SnsUtil.google_getUserInfo(jsonToken.get("access_token").asText());

		String sns_id = userInfo.path("id").asText();
		String sns_email = userInfo.path("email").asText();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sns_id", sns_id);
		map.put("sns_email", sns_email);
		
		return map;
	}
	
	public Map<String, Object> appleUserInfo(String code, String id_token) {
		
		JsonNode userInfo = SnsUtil.apple_getUserInfo(id_token);

		String sns_id = userInfo.path("sub").asText();
		String sns_email = userInfo.path("email").asText();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sns_id", sns_id);
		map.put("sns_email", sns_email);
		
		return map;
	}
	
	public Map<String, Object> naverUserInfo(String code, String state) {
		// accesstoken 받아오기
		String NAVER_CLIENT_ID = ShopConfig.getProperty("naver_client_id");
		String NAVER_CLIENT_Secret = ShopConfig.getProperty("naver_client_secret");
		String NAVER_REDIRECT_URI = ShopConfig.getProperty("naver_domain")+ShopConfig.getProperty("naver_redirect_uri");
		
		JsonNode jsonToken = SnsUtil.naver_getAccessToken(code, state, NAVER_CLIENT_ID, NAVER_CLIENT_Secret, NAVER_REDIRECT_URI);
		// access토큰 받아서 유저정보 받아옴
		JsonNode userInfo = SnsUtil.naver_getUserInfo(jsonToken.get("access_token").toString());
		String email = null;
		String nickname = null;
		String profileImage = null;
		String age = null;
		String gender = null;
		String id = null;
		String name = null;
		String birthday = null;

		// 유저정보 카톡에서 가져오기 Get properties
		JsonNode properties = userInfo.path("response");
		if (properties.isMissingNode()) {
			// if "name" node is missing
		} else {
			email = properties.path("email").asText();
			nickname = properties.path("nickname").asText();
			profileImage = properties.path("profile_image").asText();
			age = properties.path("age").asText();
			gender = properties.path("gender").asText();
			id = properties.path("id").asText();
			name = properties.path("name").asText();
			birthday = properties.path("birthday").asText();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("sns_email", email);
		map.put("sns_nickname", nickname);
		map.put("sns_profileImage", profileImage);
		map.put("sns_age", age);
		map.put("sns_gender", gender);
		map.put("sns_id", id);
		map.put("sns_name", name);
		map.put("sns_birthday", birthday);
		
		return map;
	}
	
	/**
	 * 회원 등급 정보 조회
	 * @param map
	 * @return
	 */
	public GdMemberGrp frontMemberGrpByLevel(int level) {
		return this.mapper.frontMemberGrpByLevel(level);
	}

}
