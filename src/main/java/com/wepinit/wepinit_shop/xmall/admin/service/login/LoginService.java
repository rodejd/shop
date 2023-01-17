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
package com.wepinit.wepinit_shop.xmall.admin.service.login;

import com.wepinit.wepinit_shop.xcube.util.AgentUtil;
import com.wepinit.wepinit_shop.xcube.util.ConvertUtil;
import com.wepinit.wepinit_shop.xcube.util.CryptoUtil;
import com.wepinit.wepinit_shop.xmall.admin.dao.login.LoginMapper;
import com.wepinit.wepinit_shop.xmall.admin.dao.member.MemberMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.log.MemLogVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.login.LoginVO;
import com.wepinit.wepinit_shop.xmall.common.UserInfo;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import com.wepinit.wepinit_shop.xmall.common.vo.join.MemberMemberGrp;
import com.wepinit.wepinit_shop.xmall.front.dao.member.FrontMemberMapper;
import com.wepinit.wepinit_shop.xmall.seller.login.vo.SellerLoginProcVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	@Resource
	LoginMapper mapper;
	
	@Resource
	MemberMapper memberMapper;
	
	@Resource
	FrontMemberMapper frontmapper;
	
	public UserInfo process(LoginVO loginVO, HttpServletRequest req) throws Exception {
		
		Map<String, String> map = null;
		Map<String, Object> Xkey = null;
		UserInfo userInfo = null;
		GdMember gdMember = null;
		MemberMemberGrp memberMemberGrp = null;
		
		// #####
		// # 판매사 로그인 프로세스 관련 추가
		// #####
		SellerLoginProcVO sellerLoginProcVO = null;	
		
		
		map = new HashMap<String, String>();
		
		userInfo = new UserInfo();
		
		// #####
		// # URI == "/shop/seller/*" 인 경우 판매사 프로세스 
		// # URI != "/shop/seller/*" 인 경우 기존 xmall 프로세스
		// #####
		if( req.getRequestURI().startsWith(req.getContextPath() + "/shop/seller") ){
			
			// 비밀번호 SHA256 세팅
			loginVO.setPassword(CryptoUtil.getSHA256(loginVO.getPassword()));
			
			sellerLoginProcVO = mapper.getSellerLoginCheck(loginVO);
			
			if( null == sellerLoginProcVO || "".equals(sellerLoginProcVO.getId()) ) {
				throw new BizException("seller.login.00001");	// seller.login.00001=ID 또는 비밀번호가 잘못되었습니다.
			}
			
			// 승인상태 확인
			if(!"S".equals(sellerLoginProcVO.getStatus()) ) {

				if("X".equals(sellerLoginProcVO.getStatus())) {
					throw new BizException("seller.login.00003"); // seller.login.00003=탈퇴된 판매사입니다.
				}
				
				int mngCnt = mapper.getGdSellerMngCount(sellerLoginProcVO);
				int accCnt = mapper.getGdSellerAccCount(sellerLoginProcVO);

				if(mngCnt > 0 || accCnt > 0) {
					// 승인요청 일 경우 요청오류메시지 호출
					if("R".equals(sellerLoginProcVO.getStatus())) {
						throw new BizException("seller.login.00002"); // seller.login.00002=승인 대기중 입니다.
					} else {
						// 세션에 필요값 셋팅
						userInfo.setErr_code("0000");
						userInfo.setErr_msg("로그인중");
						
						// 판매사 로그인 세션 정보 세팅
						userInfo.setXkey(ConvertUtil.voToHashMap(sellerLoginProcVO));
						
						userInfo.setUserId(sellerLoginProcVO.getId());
						userInfo.setUserName(sellerLoginProcVO.getSellerNm());
						userInfo.setSellerCd(sellerLoginProcVO.getSellerCd());
	
						loginVO.setDiv("W"); // 대기 상태
					}
				} else {
					// 세션에 필요값 셋팅
					userInfo.setErr_code("0000");
					userInfo.setErr_msg("로그인중");
					
					// 판매사 로그인 세션 정보 세팅
					userInfo.setXkey(ConvertUtil.voToHashMap(sellerLoginProcVO));
					
					userInfo.setUserId(sellerLoginProcVO.getId());
					userInfo.setUserName(sellerLoginProcVO.getSellerNm());
					userInfo.setSellerCd(sellerLoginProcVO.getSellerCd());

					loginVO.setDiv("W"); // 대기 상태
				}
			} else { // 승인인 경우 관리자 메인 페이지로 이동

				// 세션에 필요값 셋팅
				userInfo.setErr_code("0000");
				userInfo.setErr_msg("로그인중");
				
				// 판매사 로그인 세션 정보 세팅
				userInfo.setXkey(ConvertUtil.voToHashMap(sellerLoginProcVO));
				
				userInfo.setUserId(sellerLoginProcVO.getId());
				userInfo.setUserName(sellerLoginProcVO.getSellerNm());
				userInfo.setSellerCd(sellerLoginProcVO.getSellerCd());

				loginVO.setDiv("S"); // 승인

			}
			return userInfo;
		}
		
		
		// AS-IS : 관리자ID체크
		//		if(requestSet.getProperty("returnUrl", "").indexOf("admin") > 0){
		//			requestSet.setProperty("admin_level", "k_level >= 80");
		//		}
		// TO-BE : 관리자ID체크
		//		if(  0 < loginVO.getReturnUrl().indexOf("admin") ){
		//			map.put("admin_level", "k_level >= 80");
		//		}
		
		if( 0 < req.getRequestURI().indexOf("admin") ){
			map.put("admin_level", "k_level >= 80");
		}
		
		map.put("m_id", loginVO.getM_id());
		map.put("password", loginVO.getPassword());
		
		gdMember = mapper.front_login_check_ID(map);
		
		// ID체크
		if( null == gdMember || "".equals(gdMember.getMid()) ) {
			//			userInfo.setErr_code("id err!!");
			//			userInfo.setErr_msg("ID가 존재하지 않습니다.");
			throw new BizException("login.00001");
		}
		
		// sns로그인일경우엔 비밀번호확인 필요없다는 구분
		if(req.getAttribute("sns_login") == null) {
			
			gdMember = mapper.front_login_check_password(map);
			
			// 비밀번호확인
			if (null == gdMember || "".equals(gdMember.getMid())) {
				// userInfo.setErr_code("pass err!!");
				// userInfo.setErr_msg("비밀번호를 확인해주세요.");
				throw new BizException("login.00002");
			}
		}
		
		//		2017-09-13 미인증 회원 로그인 불가 추가
		if(1 != gdMember.getStatus()){
			throw new BizException("login.00003");
		}
		
		mapper.member_last_login(map);
		
		//front_my_Box_List_1
		map.put("mno", Integer.toString(gdMember.getMno()));
		memberMemberGrp = mapper.front_my_Box_List_1(map);
		
		//front_mypage_coupon_list_count
		map.put("grp_sno", Integer.toString(memberMemberGrp.getSno()));
		int boxcnt = mapper.front_mypage_coupon_list_count(map);
		
		// 세션에 필요값 셋팅
		userInfo.setUserId(gdMember.getMid());
		userInfo.setErr_code("0000");
		userInfo.setErr_msg("로그인중");
		
		// 로그인추가정보를 위한 셋팅(PHP 동일하게 설정)
		Xkey = new HashMap<String, Object>();			
		Xkey.put("m_no", gdMember.getMno());
		Xkey.put("m_id", gdMember.getMid());
		Xkey.put("level", gdMember.getKlevel());
		Xkey.put("name", gdMember.getName());
		Xkey.put("email", gdMember.getEmail());
		Xkey.put("nickname", gdMember.getNickname());
		Xkey.put("profile", gdMember.getProfile());
		
		Xkey.put("emoney", memberMemberGrp.getEmoney());
		Xkey.put("sum_sale", memberMemberGrp.getSumsale());
		Xkey.put("boxcnt", Integer.toString(boxcnt));
		Xkey.put("grp_sno", memberMemberGrp.getSno());
		Xkey.put("add_emoney", memberMemberGrp.getAddEmoney());
		
		userInfo.setXkey(Xkey);
		
		userInfo.setUserName(gdMember.getName());
		userInfo.setM_no(gdMember.getMno());
		
		//로그등록
		MemLogVO memLogVO = new MemLogVO();
		memLogVO.setM_no(gdMember.getMno());
		memLogVO.setAgent(AgentUtil.getAgent(req));
		memLogVO.setGbn("L");
		memberMapper.insertMemberLog(memLogVO);
		
		return userInfo;
	}
}
