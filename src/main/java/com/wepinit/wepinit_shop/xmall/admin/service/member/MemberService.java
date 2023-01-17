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
package com.wepinit.wepinit_shop.xmall.admin.service.member;

import com.wepinit.wepinit_shop.xcube.util.DateUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xcube.util.WebUtil;
import com.wepinit.wepinit_shop.xmall.admin.dao.member.MemberMapper;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.BatchVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.MemberHackVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.MemberVO;
import com.wepinit.wepinit_shop.xmall.common.CommonConstants;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.session.AdminSessionObject;
import com.wepinit.wepinit_shop.xmall.common.vo.GdLogEmoney;
import com.wepinit.wepinit_shop.xmall.common.vo.GdLogHack;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import com.wepinit.wepinit_shop.xmall.common.vo.GdOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
public class MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
	
	@Resource
	MemberMapper mapper;
	
	public void memberMenuAuth(HttpServletRequest req, HttpServletResponse res) throws Exception{
		String level = "";		Map<String, Object> xkey = null;
		AdminSessionObject adminSO = null;
		PrintWriter out = null;
		
		adminSO = AdminSessionObject.getSessionObject(req);
		
		if( adminSO.isLogin() && null != adminSO.getUserInfo() ){
			xkey = adminSO.getUserInfo().getXkey();
			level = String.valueOf(xkey.get("level"));
			
			if( 0 > ShopConfig.getProperty("level"+level).indexOf("member")){
				// ajax 일경우 Exception 발생 후 jquery.ajax.js errorHandler에서 처리. 
				if("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))){
					throw new BizException("member.00001", CommonConstants.MAIN_ADMIN_URL);	// 회원관리 권한이 없습니다.
				}
				
				try{
					res.setCharacterEncoding("UTF-8");
					res.setContentType("text/html;charset=UTF-8");
					out = res.getWriter();
					out.print(WebUtil.getAlertRedirect("회원관리 권한이 없습니다.", CommonConstants.MAIN_ADMIN_URL));
					out.flush();
				}catch(Exception e){
					throw e;
				} finally{
					if( null != out ){
						out.close();
					}
				}
			}
		}
	}
	
	public void deleteMember(HttpServletRequest req, String deleteMember) throws Exception{
		int i =0;
		String[] deleteMemArr = null;
		deleteMemArr = deleteMember.split("/");
		
		for( i=0 ; i < deleteMemArr.length ; i++ ){
			if(logger.isDebugEnabled()){
				logger.debug("선택한회원확인::::::{}:::::::::::::::::::::", deleteMemArr[i]);
			}
			try{
				mapper.deleteMember(deleteMemArr[i]);
			} catch(Exception e){
				logger.error("회원 삭제처리 중 오류가 발생하였습니다.", e);
			}
		}
	}
	
	
	public List<GdMember> getMemberList(MemberVO memberVO) throws Exception{
		String orderby = "";
		String skey = "";
		String sword = "";
		String ssum_salemin = "";	//최소구매액 검색기간
		String ssum_salemax = "";	//초대구매액 검색기간
		String semoneymin = "";	//적립금최소 검색기간
		String semoneymax = "";	//적립금최대 검색기간
		String scnt_loginmin = "";		//최소방문횟수 검색기간
		String scnt_loginmax = "";	//최대방문횟수 검색기간
		String birthdatemin  = "";	//최소생년월일 검색기간
		String birthdatemax = "";	//최대생년월일 검색기간
		String marridatemin = "";	//최소결혼기념일 검색기간
		String marridatemax = "";	//최대결혼기념일 검색기간
		String sex = "";
		String sage = "";
		String smsyn = "";
		String birthtype = "";
		String marriyn = "";
	
		
		Map<String, Object> param = null;
		StringBuffer sch_condition = new StringBuffer(" 1=1 ");
		String[] sregdtArr = null;
		String[] slastdtArr = null;
		int[] age = null;
		
		param = new HashMap<String, Object>();
		sch_condition = new StringBuffer();
		age = new int[2];
		
		skey = StringUtil.N2S(memberVO.getSkey());
		sword = StringUtil.N2S(memberVO.getSword());
		ssum_salemin = StringUtil.N2S(memberVO.getSsum_salemin());
		ssum_salemax = StringUtil.N2S(memberVO.getSsum_salemax());
		semoneymin = StringUtil.N2S(memberVO.getSemoneymin());
		semoneymax = StringUtil.N2S(memberVO.getSemoneymax());
		scnt_loginmin = StringUtil.N2S(memberVO.getScnt_loginmin());
		scnt_loginmax = StringUtil.N2S(memberVO.getScnt_loginmax());
		sex = StringUtil.N2S(memberVO.getSex());	
		sage = StringUtil.N2S(memberVO.getSage());
		smsyn = StringUtil.N2S(memberVO.getSmsyn());
		birthtype = StringUtil.N2S(memberVO.getBirthtype());
		birthdatemin  = StringUtil.N2S(memberVO.getBirthdatemin());
		birthdatemax = StringUtil.N2S(memberVO.getBirthdatemax());
		marriyn = StringUtil.N2S(memberVO.getSmarriyn());
		marridatemin =StringUtil.N2S(memberVO.getMarridatemin());
		marridatemax = StringUtil.N2S(memberVO.getMarridatemax());
		
		sregdtArr = memberVO.getSregdt();
		slastdtArr = memberVO.getSlastdt();
		
		if( !"".equals(skey)  && !"".equals(sword) ){
			if( "resno".equals(skey) ){
				sword = StringUtil.replaceAll(sword, "-", "");
				sch_condition.append(" and ( resno1=md5('" + sword.substring(0,6) + "') and resno2=md5('" + sword.substring(6,7) + "') )"); 
			}else if( "all".equals(skey) ){
				sch_condition.append(" and ( concat( m_id, name ) like '%" + sword +"%' or nickname like '%" + sword +"%' )");
			}else {
				sch_condition.append(" and " +skey + " like '%" + sword + "%'");
			}
		}
		
		if( !"".equals( StringUtil.N2S(memberVO.getSstatus()) ) ){
			sch_condition.append(" and status='" + memberVO.getSstatus() + "'");
		}
		
		if( !"".equals( StringUtil.N2S(memberVO.getSlevel()) ) ){
			sch_condition.append(" and k_level='" + memberVO.getSlevel() + "'");
		}
		
		//구매액의 입력이 양쪽다 있을 경우.
		if( !"".equals(ssum_salemin) && !"".equals(ssum_salemax) ){	
				sch_condition.append(" and sum_sale between "+ ssum_salemin + " and " + ssum_salemax);
		}else if( !"".equals(ssum_salemin) && "".equals(ssum_salemax) ){
				sch_condition.append(" and sum_sale >= " + ssum_salemin);
		}else if( "".equals(ssum_salemin) && !"".equals(ssum_salemax) ){
				sch_condition.append(" and sum_sale <= " + ssum_salemax);
		}
		
		//적립금이 입력이 양쪽다 있을 경우.
		if( !"".equals(semoneymin) && !"".equals(semoneymax) ){	
				sch_condition.append(" and emoney between "+ semoneymin + " and " +semoneymax);
		}else if( !"".equals(semoneymin) && "".equals(semoneymax) ){
				sch_condition.append(" and emoney >= " + semoneymin);
		}else if( "".equals(semoneymin) && !"".equals(semoneymax) ){
				sch_condition.append(" and emoney <= " + semoneymax);
		}
		
		if( null != sregdtArr ){
			if( 1 < sregdtArr.length ){
				if( !"".equals( StringUtil.N2S(sregdtArr[0]) ) && !"".equals( StringUtil.N2S(sregdtArr[1]) ) ){
					param.put("sch_regdt", "regdt between date_format("+sregdtArr[0]+",'%Y-%m-%d 00:00:00') and date_format("+sregdtArr[1]+",'%Y-%m-%d 23:59:59')");
				}
			}
		}
			
		//최종로그인 조건문
		if( null != slastdtArr ){
			if( 1 < slastdtArr.length ){
				if( !"".equals( StringUtil.N2S(slastdtArr[0]) ) && !"".equals( StringUtil.N2S(slastdtArr[1]) ) ){
					param.put("last_login", "last_login between date_format("+slastdtArr[0]+",'%Y-%m-%d 00:00:00') and date_format("+slastdtArr[1]+",'%Y-%m-%d 23:59:59')");
				}
			}
		}
		
		if( "".equals(sex) || "all".equals(sex) ){
			sch_condition.append(" and sex in ('m','w')");
		} else {
			sch_condition.append(" and sex = '"+ sex +"'");
		}
		
		if( !"".equals(sage) ){
			age[0] = Integer.parseInt(DateUtil.getDateFrom("yyyy", "1y")) - Integer.parseInt( sage );
			age[1] = age[0] - 9;
			
			if( "60".equals(sage) ){
				//sch_condition.append(" and right(birth_year,2) <= "+ age[1]);
				sch_condition.append(" and birth_year <= "+ age[1]);
			} else {
				//sch_condition.append(" and right(birth_year,2) between "+ age[1]+" and "+ age[0]);
				sch_condition.append(" and birth_year between "+ age[1]+" and "+ age[0]);
			}
		}
		
		//방문횟수
		if( !"".equals(scnt_loginmin) && !"".equals(scnt_loginmax) ){	
				sch_condition.append(" and cnt_login between "+ scnt_loginmin + " and " + scnt_loginmax);
		}else if( !"".equals(scnt_loginmin) && "".equals(scnt_loginmax) ){
				sch_condition.append(" and cnt_login >= "+ scnt_loginmin);
		}else if( "".equals(scnt_loginmin) && !"".equals(scnt_loginmax) ){
				sch_condition.append(" and cnt_login <= "+ scnt_loginmax);
		}
		
		if ( !"".equals(StringUtil.N2S(memberVO.getDormancy())) ){
			sch_condition.append(" and  date_format(last_login,'%Y%m%d') <= '"+ DateUtil.getDateFrom("yyyyMMdd", "-" + memberVO.getDormancy() + "d") + "'");
		}
		
		if ( !"".equals(StringUtil.N2S(memberVO.getSmailling())) ){
			if( "all".equals(memberVO.getSmailling()) ){
				sch_condition.append(" and mailling in ('n','y')");
			}else{
				sch_condition.append(" and mailling = '" + memberVO.getSmailling() + "'");
			}	
		}
		
		if ( !"".equals(smsyn) ){
			if( "all".equals(smsyn) ){
				sch_condition.append(" and sms in ('n','y')");
			}else{
				sch_condition.append(" and sms = '"+smsyn+"'");
			}	
		}
		
		if ( !"".equals(birthtype) ){
			if( "all".equals(birthtype) ){
				sch_condition.append(" and calendar in ('s','l')");
			}else{
				sch_condition.append(" and calendar = '"+birthtype+"'");
			}	
		}
		
		//생년월일
		if( !"".equals(birthdatemin) ){
			if( !"".equals(birthdatemax) ){
				if(birthdatemin.length() > 4 && birthdatemax.length() > 4 ){
					sch_condition.append(" and concat(birth_year, birth) between '"+ birthdatemin+"' and '"+birthdatemax+"'");
				}else{
					sch_condition.append(" and birth between '"+birthdatemin+"' and '"+birthdatemax+"'");
				}
			}else{
				if (birthdatemin.length() >4 ){
					sch_condition.append(" and concat(birth_year, birth) = '"+ birthdatemin +"'");
				}else{
					sch_condition.append(" and birth = '"+birthdatemin+"'");
				}		
			}
		}
		
		
		if( !"".equals(marriyn) ){
			if( "all".equals(marriyn) ){
				sch_condition.append(" and marriyn in ('n','y')");
			}else{
				sch_condition.append(" and marriyn = '"+marriyn+"'");	
			}
		}

		if( !"".equals(marridatemin) ){
			if( !"".equals(marridatemax) ){
				if( marridatemin.length() >4 && marridatemax.length() >4 ){
					sch_condition.append(" and marridate between '"+ marridatemin + "' and '" + marridatemax + "'");
				}else{
					sch_condition.append(" and substring(marridate,5,4) between '"+ marridatemin + "' and '"+ marridatemax+ "'");
				}
			}else{
				if( marridatemin.length() > 4 ){
					sch_condition.append(" and marridate = '"+ marridatemin +"'");
				}else{
					sch_condition.append(" and substring(marridate,5,4) = '"+marridatemin+"'");
				}		
			}
		}

		//# 메인에서 생일자 SMS 확인용
		if( "y".equals(StringUtil.N2S(memberVO.getMobileYN())) ){
			sch_condition.append(" and mobile != ''");
		}
	
		sch_condition.append(" and m_id != 'xmall'");	
		
		param.put("sch_condition", sch_condition.toString());
		
		orderby = memberVO.getSort();
		param.put("orderby", orderby);
		
		param.put("vo", memberVO);
		
		if(logger.isDebugEnabled()){
			logger.debug("memberList >> service");
			logger.debug("sch_condition :: "+sch_condition+ " orderby ::" +orderby);
			logger.debug("map >> " + param.toString());
		}
		
		return mapper.getmemberList(param);
	}
	
	
	public int getMemberSrchCount(Map<String, Object> param) throws Exception {
		return  mapper.getMemberSrchCount(param);
		
	}
	
	public List<GdMember> getMemberSrchList(Map<String, Object> param) throws Exception {
		return mapper.getMemberSrchList(param);
	}
	
	public int getMemberCount(Map<String, Object> param) throws Exception {
		return  mapper.getMemberCount(param);
	}
	
	public List<GdMember> getMemberList(Map<String, Object> param) throws Exception{
		return mapper.getMemberList(param);
	}
	
	public int memberHackTotalCnt() throws Exception {
		return  mapper.memberHackTotalCnt();
	}
	
	public int memberHackCnt(MemberHackVO hackVO) throws Exception {
		return  mapper.memberHackCnt(hackVO);
	}
	
	public List<GdLogHack> memberHackList(MemberHackVO hackVO) {
		return mapper.memberHackList(hackVO);
	}
	
	public void memberHackDelete(int sno) {
		mapper.memberHackDelete(sno);
	}
	
	public void memberHackUpdate(MemberHackVO hackVO) {
		mapper.memberHackUpdate(hackVO);
	}
	
	public GdLogHack memberHackQuery(int sno) {
		return mapper.memberHackQuery(sno);
	}
	
	public String getEmailCfgUploadPath (String uploadPath) {
		StringBuffer uploadPathStr = new StringBuffer();
		uploadPathStr.append(uploadPath)
					 .append("shop").append(File.separator)
					 .append("data").append(File.separator)
					 .append("email").append(File.separator);
		return uploadPathStr.toString();
	}
	
	public Properties getEmailSubjectProp(String uploadPath, String mode) throws IOException {
		// 메일제목
		Properties subjectProp = new Properties();
		InputStream in = new FileInputStream(uploadPath.concat("subject.html"));
		subjectProp.load(in);
		in.close();	
		return subjectProp;
	}
	
	public Properties getNmProp() {
		Properties nmProp = new Properties();
		nmProp.setProperty("0", "주문확인메일");
		nmProp.setProperty("1", "입금확인메일");
		nmProp.setProperty("3", "배송중메일");
		nmProp.setProperty("4", "배송완료메일");
		nmProp.setProperty("10", "회원가입메일");
		nmProp.setProperty("11", "아이디/비밀번호찾기메일");
		nmProp.setProperty("12", "회원탈퇴메일");
		nmProp.setProperty("20", "1:1문의답변메일");
		return nmProp;
	}
	
	
	public int getMemberTotalCount(){
		return mapper.getMemberTotalCount();
	}
	public GdMember getMemberInfo(String mid){
		return mapper.getMemberInfo(mid);
	}
	public GdMember getMemberInfoMno(int mno){
		return mapper.getMemberInfoMno(mno);
	}
	public void updateMemberInfo(MemberVO vo){
		mapper.updateMemberInfo(vo);
	}
	public void deleteMemberInfo(int mno, HttpServletRequest req){
		//2017-08-29 수정 - 탈퇴회원 로그 등록 추가
		Map<String, Object> param = new HashMap<String, Object>();
		GdMember gdMember = new GdMember();
		
		gdMember = mapper.getMemberInfoMno(mno);
		
		mapper.deleteMemberInfo(mno);
		
		param.put("mid", gdMember.getMid());
		param.put("name", gdMember.getName());
		param.put("ip", req.getRemoteAddr());
		param.put("skin", gdMember.getSkin());
		param.put("klevel", gdMember.getKlevel());
		param.put("customsnum", gdMember.getCustomsnum());
		
		mapper.insertLogHackMember(param);
	}
	public Map<String, Object> getMemberEmoneyInfo(int mno){
		return mapper.getMemberEmoneyInfo(mno);
	}
//	public int getMemberLogEmoneyCount(int mno){
//		return mapper.getMemberLogEmoneyCount(mno);
//	}
	public List<GdLogEmoney> getMemberLogEmoney(int mno, MemberVO vo){
		Map<String, Object> param = new HashMap<String, Object>();
		
		//총건수
		vo.setRowCount(mapper.getMemberLogEmoneyCount(mno));
		
		param.put("mno", mno);
		param.put("rowStart", vo.getRowStart());
		param.put("pageSize", vo.getPageSize());
		
		return mapper.getMemberLogEmoney(param);
	}
	public void insertMemberLogEmoney(MemberVO vo){
		mapper.insertMemberLogEmoney(vo);
	}
	public void updateMemberEmoney(int emoney, int mno){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("emoney", emoney);
		param.put("mno", mno);
		
		mapper.updateMemberEmoney(param);
	}
	public Map<String, Object> getMemberLogEmoneyInfo(int sno){
		return mapper.getMemberLogEmoneyInfo(sno);
	}
	public void deleteMemberLogEmoney(int sno){
		mapper.deleteMemberLogEmoney(sno);
	}
	public List<GdOrder> getMemberOrderList(int mno, MemberVO vo){
		Map<String, Object> param = new HashMap<String, Object>();
		
		//총건수
		vo.setRowCount(mapper.getMemberOrderListCount(mno));
		
		param.put("mno", mno);
		param.put("rowStart", vo.getRowStart());
		param.put("pageSize", vo.getPageSize());
		
		return mapper.getMemberOrderList(param);
	}
	
	public int updateMemberBatch(BatchVO batchVO) throws Exception {
		int total = 0;
		Map<String, Object> param = null;
		
		List<GdMember> getMemberBatchList = null;
		
		
		// 회원선택(4)
		if( "4".equals(batchVO.getType()) ){
			
			param = new HashMap<String, Object>();
			
			param.put("type", batchVO.getType() );
			
			param.put("chk", batchVO.getChk() );
			
			getMemberBatchList = mapper.getMemberBatchList(param);
			
		// 검색(5)
		}else if( "5".equals(batchVO.getType()) ){
			
			
			param = new HashMap<String, Object>();
			
			/*
			 * 검색설정
			 * 
			 * */
			
			param.put("type", batchVO.getType() );
			
			// 검색어, 검색타입
			param.put("skey", batchVO.getSkey());
			param.put("sword", batchVO.getSword());
			
			// 검색(주민등록번호검색)
			if("resno".equals(batchVO.getSkey())){
				String tmp = StringUtil.fillSpace(batchVO.getSword().replaceAll("-", ""),13);
				
				param.put("resno1", tmp.substring(0,6));
				param.put("resno2", tmp.substring(6,13));
			}
			
			// 승인여부
			param.put("sstatus", batchVO.getSstatus());
			// 그룹
			param.put("slevel", batchVO.getSlevel());
			
			// 구매액
			param.put("ssum_salemin", batchVO.getSsum_salemin());
			param.put("ssum_salemax", batchVO.getSsum_salemax());
			
			//적립금
			param.put("semoneymin", batchVO.getSemoneymin());
			param.put("semoneymax", batchVO.getSemoneymax());
			
			
			//가입일
			param.put("sregdt_0", batchVO.getSregdt() != null ? batchVO.getSregdt()[0] : "" );
			param.put("sregdt_1", batchVO.getSregdt() != null ? batchVO.getSregdt()[1] : "" );
			
			//최종로그인
			param.put("slastdt_0", batchVO.getSlastdt() != null ? batchVO.getSlastdt()[0] : "" );
			param.put("slastdt_1", batchVO.getSlastdt() != null ? batchVO.getSlastdt()[1] : "" );
			
			// 성별, 메일, SMS 수신여부
			param.put("sex", batchVO.getSex());
			param.put("mailling", batchVO.getMailling());
			
			// 방문횟수, 휴면회원검색
			param.put("scnt_loginmin", batchVO.getScnt_loginmin());				// 방문횟수 min
			param.put("scnt_loginmax", batchVO.getScnt_loginmax());			// 방문횟수 max
			
			if(!"".equals(batchVO.getDormancy()) && null != batchVO.getDormancy() ){
				param.put("dormancy", DateUtil.getDateFrom("yyyyMMdd", "-" + batchVO.getDormancy()+ "d"));		// 휴면회원
			}
			
			// 생년월일, 결혼여부/결혼기념일
			param.put("birthtype", batchVO.getBirthtype());
			param.put("birthdatemin", batchVO.getBirthdatemin());
			param.put("birthdatemax", batchVO.getBirthdatemax());
			
			param.put("marriyn", batchVO.getMarriyn());
			param.put("marridatemin", batchVO.getMarridatemin());
			param.put("marridatemax", batchVO.getMarridatemax());

			
			getMemberBatchList = mapper.getMemberBatchList(param);
		}
		
		total = getMemberBatchList.size();
		
		
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> updateMemberBatch !!!");
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>updateMemberBatch" + batchVO.getType());
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>updateMemberBatch total!!!" + total);
		}
		
		// 회원선택(4), 검색(5)
		if( "4".equals(batchVO.getType()) || "5".equals(batchVO.getType()) ){
			
			if (logger.isDebugEnabled()) {
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms_send Type!!!" + batchVO.getType());
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms_send getMemberSMSList.size()!!!" + getMemberBatchList.size());
			}
			
			for(int i=0; i < getMemberBatchList.size();i++){
					
				if (logger.isDebugEnabled()) {
					logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>func:"+batchVO.getFunc());
				}
				
				param.put("mno", getMemberBatchList.get(i).getMno());
				param.put("emoney", batchVO.getEmoney());
				param.put("memo", batchVO.getMemo());
				param.put("level", batchVO.getLevel());
				param.put("status", batchVO.getStatus());
				
				
				if( "emoney".equals( batchVO.getFunc() ) ){
					if (StringUtil.N2D(batchVO.getEmoney()) < 0) {
						param.put("gbn", "E");
					} else {
						param.put("gbn", "S");
					}
					
					mapper.insertEmoneyLog(param);				// 적립금이력등록
					mapper.updateMemberEmoney(param);		// 회원적립금수정
					
				}else if( "level".equals( batchVO.getFunc() ) ){
					
					mapper.updateMemberGroup(param);			// 회원적립금수정
					
				}else if( "status".equals( batchVO.getFunc() ) ){
					
					mapper.updateMemberStatus(param);		// 회원상태수정
					
				}
				
			}
			
		}
		
		return total;
		
	}

	public List<GdMember> getMemberListSendMail(Map<String, Object> param) {
		return mapper.getMemberListSendMail(param);
	}

	public List<GdMember> getEmailChkList(String email) {
		// TODO Auto-generated method stub
		return mapper.getEmailChkList(email);
	}

	public List<GdMember> getEmailChkListMno(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mapper.getEmailChkListMno(param);
	}
	
	public void insertGdEmailer(Map<String, Object> param){
		mapper.insertGdEmailer(param);
	}
}
