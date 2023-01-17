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
* 설명 				: 	사용자 Community dao
* ----------------------------------------------
* 변경이력
* ----------------------------------------------
* NO	 	날짜				작성자			내용
* 1			20170208		이병환			최초작성
* 
* </pre>
********************************************************************************/
package com.wepinit.wepinit_shop.xmall.front.dao.member;

import com.wepinit.wepinit_shop.xmall.common.vo.*;
import com.wepinit.wepinit_shop.xmall.common.vo.join.GdMemberJoinGdMemberGrp;
import com.wepinit.wepinit_shop.xmall.front.vo.member.FrontMemberSnsVO;
import com.wepinit.wepinit_shop.xmall.front.vo.member.FrontMemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface FrontMemberMapper {
	
	// 매개변수는 하나 이상인 경우 Map 형태로 작성
//	public List<TSample> getFrontMemberList(Map<String, Object> param);
//	public TSample getMemberSample(String sample_no);
//	public int setFrontMemberReg(Map<String, Object> param);
//	public int setFrontMemberMod(Map<String, Object> param);
//	public int getFrontMemberRowCount(Map<String, Object> param);
	public List<String> frontFindID(Map<String, Object> map);		// 고객센터 > 아이디 찾기
	public int frontFindPassword(Map<String, Object> map);			// 고객센터 > 비밀번호 찾기
	public void frontNewPassword(Map<String, Object> map);			// 고객센터 > 새 비밀번호 저장
	public GdMember frontMemberInfo(Map<String, Object> map);		// 마이페이지 > 정보수정
	public String frontMemberChkNickname(Map<String, Object> map);	// 회원수정 > 닉네임중복체크
	public String frontMemberChkEmail(Map<String, Object> map);		// 회원가입(수정) > 이메일중복체크
	public void frontMemberUpdate(Map<String, Object> map);			// 회원정보수정  update
	public List<GdCode> itemGdCode(String groupcd);					// 회원탈퇴
	public GdMember frontLoginCheckPassword(FrontMemberVO memberVO);// 회원탈퇴
	public GdMember frontLoginCheckID(String m_id);
	public void frontInsertHackLog(Map<String, Object> map);		// 회원탈퇴 > 탈퇴로그저장
	public void frontDeleteSns(int m_no);							// sns 멤버삭제
	public void frontDeleteMember(int m_no);						// 멤버삭제
	public void frontDeleteEmoney(int m_no);						// 적립금 삭제
	public String frontMemberChkid(String m_id);					// 회원가입 > 아이디중복체크
	public String frontMemberJumindup(Map<String, Object> map);		// 회원가입 > 주민번호 중복체크
	public String frontMemberJoinChkNickname(String nickname); 		// 회원가입 > 닉네임 중복체크
	public String frontMemberRejoin(Map<String, Object> map);		// 회원가입 > 회원재가입기간 체크
	public void frontMemberInsert(Map<String, Object> map);			// 회원가입
	public void frontMemberSnsInsert(Map<String, Object> map);		// 회원가입 > sns 등록
	public FrontMemberVO frontMemberInfoBySns(SnsVO snsVO);			// sns로그인 > idselector
	public FrontMemberSnsVO frontMemberSnsInfoSelect(Map<String, Object> map);	// 회원수정 sns > 회원수정 관리
	public int frontMemberSnsInfoDelete(Map<String, Object> map);	// 회원수정 sns > sns 삭제
	public int tableMaxFieldSelect(Map<String, Object> map);		// 테이블의 필드의 max
	public void frontMemberEmoneyInsert(Map<String, Object> map);	// 회원가입 > 적립금 내역 입력
	public GdMember frontMemberRecommidSelect(String recommid);		// 회원가입 > 추천인 검색
	public List<GdCoupon> frontMemberJoinCouponSelect(String skin);	// 회원가입 > 회원가입쿠폰 조회
	public GdMemberJoinGdMemberGrp frontMemberGrpSelect(Map<String, Object> map);		// 회원가입 > 회원로그인 정보 조회
	public void frontMemberRecommEmoneyInsert(Map<String, Object> map); 				// 회원가입 > 추천인에게 적립금 적립
	public void frontMemberRecommEmoneyUpdate(Map<String, Object> map);					//회원가입 > 추천인에게 적립금 업데이트
	public List<GdCouponApply> frontMemberCouponApplySelect(Map<String, Object> map); 	// 회원가입 > 회원가입쿠폰 조회
	public void frontMemberJoinCouponApplyInsert(Map<String, Object> map);				// 회원가입 > 쿠폰 발급(GD_COUPON_APPLY)
	public void frontMemberJoinCouponApplymemberInsert(Map<String, Object> map);		// 회원가입 > 쿠폰 발급(GD_COUPON_APPLYmember)
	public void frontMemberLoginUpdate(int m_no);
	public GdMemberGrp frontMemberGrpByLevel(int level);			// h회원 등급 정보 조회
}
