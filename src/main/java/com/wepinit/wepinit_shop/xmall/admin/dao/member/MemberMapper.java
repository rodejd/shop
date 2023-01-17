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
package com.wepinit.wepinit_shop.xmall.admin.dao.member;

import com.wepinit.wepinit_shop.xmall.admin.vo.log.MemLogVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.MemberHackVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.MemberVO;
import com.wepinit.wepinit_shop.xmall.common.vo.GdLogEmoney;
import com.wepinit.wepinit_shop.xmall.common.vo.GdLogHack;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import com.wepinit.wepinit_shop.xmall.common.vo.GdOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {

	public int deleteMember(String m_id);
	public List<GdMember> getmemberList(Map<String, Object> param);
	
	public int getMemberTotalCount();						//회원리스트 회원 총 건수
	public GdMember getMemberInfo(String mid);		//회원리스트 회원 단건 조회
	public GdMember getMemberInfoMno(int mno);	//회원리스트 회원 단건 조회(mno)
	public void updateMemberInfo(MemberVO vo);		//회원관리 회원 정보 수정
	public void deleteMemberInfo(int mno);				//회원리스트 회원 단건 삭제
	public void insertLogHackMember(Map<String, Object> param);
	public Map<String, Object> getMemberEmoneyInfo(int mno);		//회원 적립금 현황 조회
	public int getMemberLogEmoneyCount(int mno);	//회원 적립금 내역 건수
	public List<GdLogEmoney> getMemberLogEmoney(Map<String, Object> param);	//회원 적립금 내역 조회
	public void insertMemberLogEmoney(MemberVO vo);		//회원 적립금 내역 INSERT
	public Map<String, Object> getMemberLogEmoneyInfo(int sno);	//회원 적립금 단건 조회
	public void deleteMemberLogEmoney(int sno);		//회원 적립금 단건 삭제
	public int getMemberOrderListCount(int mno);		//회원 주문내역 건수
	public List<GdOrder> getMemberOrderList(Map<String, Object> param);		//회원 주문내역 조회
	
	//회원리스트
	public int getMemberSrchCount(Map<String, Object> param);
	public List<GdMember> getMemberSrchList(Map<String, Object> param);
	// SMS보내기(회원 그룹별 SMS 발송가능한 명수조회)
	//public int getMemberSMSCount(Map<String, Object> param);
	public List<GdMember> getMemberSMSList(Map<String, Object> param);
	
	
	//회원관리 > 회원일괄관리 > 리스트
	public int getMemberCount(Map<String, Object> param);
	public List<GdMember> getMemberList(Map<String, Object> param);
//	public int getMemberBatchCount(Map<String, Object> param);
	public List<GdMember> getMemberBatchList(Map<String, Object> param);
	
	//회원관리 > 회원일괄관리 > 적립금 적립,차감/그룹수정/상태수정
	public void insertEmoneyLog(Map<String, Object> param);				//적립금이력등록
	public void updateMemberEmoney(Map<String, Object> param);		//적립금수정
	public void updateMemberGroup(Map<String, Object> param);			//회원그룹수정
	public void updateMemberStatus(Map<String, Object> param);			//회원상태수정
	
	public int memberHackTotalCnt();
	public int memberHackCnt(@Param("vo") MemberHackVO vo);
	public List<GdLogHack> memberHackList(@Param("vo") MemberHackVO vo);
	public void memberHackDelete(int sno);
	public void memberHackUpdate(MemberHackVO hackVO);
	public GdLogHack memberHackQuery(int sno);
	public List<GdMember> getMemberListSendMail(Map<String, Object> param);
	public List<GdMember> getEmailChkList(String email);
	public List<GdMember> getEmailChkListMno(Map<String, Object> param);
	
	//파워메일
	public void insertGdEmailer(Map<String, Object> param);
	
	//회원 로그
	public void insertMemberLog(MemLogVO memLogVO);
	
	
}
