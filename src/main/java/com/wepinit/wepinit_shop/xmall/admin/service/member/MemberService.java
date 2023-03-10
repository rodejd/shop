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
				// ajax ????????? Exception ?????? ??? jquery.ajax.js errorHandler?????? ??????. 
				if("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))){
					throw new BizException("member.00001", CommonConstants.MAIN_ADMIN_URL);	// ???????????? ????????? ????????????.
				}
				
				try{
					res.setCharacterEncoding("UTF-8");
					res.setContentType("text/html;charset=UTF-8");
					out = res.getWriter();
					out.print(WebUtil.getAlertRedirect("???????????? ????????? ????????????.", CommonConstants.MAIN_ADMIN_URL));
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
				logger.debug("?????????????????????::::::{}:::::::::::::::::::::", deleteMemArr[i]);
			}
			try{
				mapper.deleteMember(deleteMemArr[i]);
			} catch(Exception e){
				logger.error("?????? ???????????? ??? ????????? ?????????????????????.", e);
			}
		}
	}
	
	
	public List<GdMember> getMemberList(MemberVO memberVO) throws Exception{
		String orderby = "";
		String skey = "";
		String sword = "";
		String ssum_salemin = "";	//??????????????? ????????????
		String ssum_salemax = "";	//??????????????? ????????????
		String semoneymin = "";	//??????????????? ????????????
		String semoneymax = "";	//??????????????? ????????????
		String scnt_loginmin = "";		//?????????????????? ????????????
		String scnt_loginmax = "";	//?????????????????? ????????????
		String birthdatemin  = "";	//?????????????????? ????????????
		String birthdatemax = "";	//?????????????????? ????????????
		String marridatemin = "";	//????????????????????? ????????????
		String marridatemax = "";	//????????????????????? ????????????
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
		
		//???????????? ????????? ????????? ?????? ??????.
		if( !"".equals(ssum_salemin) && !"".equals(ssum_salemax) ){	
				sch_condition.append(" and sum_sale between "+ ssum_salemin + " and " + ssum_salemax);
		}else if( !"".equals(ssum_salemin) && "".equals(ssum_salemax) ){
				sch_condition.append(" and sum_sale >= " + ssum_salemin);
		}else if( "".equals(ssum_salemin) && !"".equals(ssum_salemax) ){
				sch_condition.append(" and sum_sale <= " + ssum_salemax);
		}
		
		//???????????? ????????? ????????? ?????? ??????.
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
			
		//??????????????? ?????????
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
		
		//????????????
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
		
		//????????????
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

		//# ???????????? ????????? SMS ?????????
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
		// ????????????
		Properties subjectProp = new Properties();
		InputStream in = new FileInputStream(uploadPath.concat("subject.html"));
		subjectProp.load(in);
		in.close();	
		return subjectProp;
	}
	
	public Properties getNmProp() {
		Properties nmProp = new Properties();
		nmProp.setProperty("0", "??????????????????");
		nmProp.setProperty("1", "??????????????????");
		nmProp.setProperty("3", "???????????????");
		nmProp.setProperty("4", "??????????????????");
		nmProp.setProperty("10", "??????????????????");
		nmProp.setProperty("11", "?????????/????????????????????????");
		nmProp.setProperty("12", "??????????????????");
		nmProp.setProperty("20", "1:1??????????????????");
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
		//2017-08-29 ?????? - ???????????? ?????? ?????? ??????
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
		
		//?????????
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
		
		//?????????
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
		
		
		// ????????????(4)
		if( "4".equals(batchVO.getType()) ){
			
			param = new HashMap<String, Object>();
			
			param.put("type", batchVO.getType() );
			
			param.put("chk", batchVO.getChk() );
			
			getMemberBatchList = mapper.getMemberBatchList(param);
			
		// ??????(5)
		}else if( "5".equals(batchVO.getType()) ){
			
			
			param = new HashMap<String, Object>();
			
			/*
			 * ????????????
			 * 
			 * */
			
			param.put("type", batchVO.getType() );
			
			// ?????????, ????????????
			param.put("skey", batchVO.getSkey());
			param.put("sword", batchVO.getSword());
			
			// ??????(????????????????????????)
			if("resno".equals(batchVO.getSkey())){
				String tmp = StringUtil.fillSpace(batchVO.getSword().replaceAll("-", ""),13);
				
				param.put("resno1", tmp.substring(0,6));
				param.put("resno2", tmp.substring(6,13));
			}
			
			// ????????????
			param.put("sstatus", batchVO.getSstatus());
			// ??????
			param.put("slevel", batchVO.getSlevel());
			
			// ?????????
			param.put("ssum_salemin", batchVO.getSsum_salemin());
			param.put("ssum_salemax", batchVO.getSsum_salemax());
			
			//?????????
			param.put("semoneymin", batchVO.getSemoneymin());
			param.put("semoneymax", batchVO.getSemoneymax());
			
			
			//?????????
			param.put("sregdt_0", batchVO.getSregdt() != null ? batchVO.getSregdt()[0] : "" );
			param.put("sregdt_1", batchVO.getSregdt() != null ? batchVO.getSregdt()[1] : "" );
			
			//???????????????
			param.put("slastdt_0", batchVO.getSlastdt() != null ? batchVO.getSlastdt()[0] : "" );
			param.put("slastdt_1", batchVO.getSlastdt() != null ? batchVO.getSlastdt()[1] : "" );
			
			// ??????, ??????, SMS ????????????
			param.put("sex", batchVO.getSex());
			param.put("mailling", batchVO.getMailling());
			
			// ????????????, ??????????????????
			param.put("scnt_loginmin", batchVO.getScnt_loginmin());				// ???????????? min
			param.put("scnt_loginmax", batchVO.getScnt_loginmax());			// ???????????? max
			
			if(!"".equals(batchVO.getDormancy()) && null != batchVO.getDormancy() ){
				param.put("dormancy", DateUtil.getDateFrom("yyyyMMdd", "-" + batchVO.getDormancy()+ "d"));		// ????????????
			}
			
			// ????????????, ????????????/???????????????
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
		
		// ????????????(4), ??????(5)
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
					
					mapper.insertEmoneyLog(param);				// ?????????????????????
					mapper.updateMemberEmoney(param);		// ?????????????????????
					
				}else if( "level".equals( batchVO.getFunc() ) ){
					
					mapper.updateMemberGroup(param);			// ?????????????????????
					
				}else if( "status".equals( batchVO.getFunc() ) ){
					
					mapper.updateMemberStatus(param);		// ??????????????????
					
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
