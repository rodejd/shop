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
import com.wepinit.wepinit_shop.xmall.admin.dao.member.MemberMapper;
import com.wepinit.wepinit_shop.xmall.admin.dao.member.SmsMapper;
import com.wepinit.wepinit_shop.xmall.admin.service.common.Coolsms;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.BatchVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.SmsAddressVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.SmsAutoVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.SmsSendVO;
import com.wepinit.wepinit_shop.xmall.admin.vo.seller.AdminSellerManagementVO;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.vo.*;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SmsService {
	
	private static final Logger logger = LoggerFactory.getLogger(SmsService.class);
	
	@Resource
	SmsMapper mapper;
	
	@Resource
	MemberMapper mbmapper;
	
	
	public List<GdSmsAutoSet> getGdSmsAutoSetList(SmsAutoVO smsAutoVO) throws Exception {
		
		return mapper.getGdSmsAutoSetList(smsAutoVO);
		
	}
	
	public void updateSmsAutoSet(SmsAutoVO smsAutoVO) throws Exception {
		mapper.updateSmsAutoSet(smsAutoVO);
	}
	
	public List<GdMemberGrp> getGdMemberGrpList(SmsSendVO smsSendVO) throws Exception {
		
		return mapper.getGdMemberGrpList(smsSendVO);
		
	}
	
	public int getGdSmsAddressCount(Map<String, Object> param) throws Exception {
		return  mapper.getGdSmsAddressCount(param);
		
	}
	
	public List<GdSmsAddress> getGdSmsAddressList(Map<String, Object> param) throws Exception {
		
		return mapper.getGdSmsAddressList(param);
		
	}
	
	public int getGdSmsSampleCount(Map<String, Object> param) throws Exception {
		return  mapper.getGdSmsSampleCount(param);
		
	}
	
	public List<GdSmsSample> getGdSmsSampleList(Map<String, Object> param) throws Exception {
		
		return mapper.getGdSmsSampleList(param);
		
	}
	
	public GdSmsSample getSmsSampleInfo(SmsSendVO smsSendVO) throws Exception {
		return mapper.getSmsSampleInfo(smsSendVO);
	}
	
	public void updateSmsSample(SmsSendVO smsSendVO) throws Exception {
		mapper.updateSmsSample(smsSendVO);
	}
	
	public void insertSmsSample(SmsSendVO smsSendVO) throws Exception {
		mapper.insertSmsSample(smsSendVO);
	}
	
	
	public int insertSendSms(SmsSendVO smsSendVO,String adminCd) throws Exception {
		String[] div = null;
		String to_tran = "";
		int total = 0;
		//String query = null;
		Map<String, Object> param = null;
		Map<String, Object> smsparam = new HashMap<String, Object>();
		List<GdMember> getMemberSMSList = null;
		List<GdSmsAddress> getSmsAddressList = null;
		
		
		
		//smscool api 이용
		String api_key = ""; //업체 api-key 입력 가입후 발급
		String api_secret = ""; //업체 api-secret 입력 가입후 발급
	    Coolsms coolsms = new Coolsms(api_key, api_secret);

	    HashMap<String, String> set = new HashMap<String, String>();
		// 10월 16일 이후로 발신번호 사전등록제로 인해 등록된 발신번호로만 문자를 보내실 수 있습니다.
		set.put("from", smsSendVO.getCallbackphone()); // 발신번호
	    set.put("text", smsSendVO.getMsg()); // 문자내용
	    set.put("type", "sms"); // 문자 타입
	    set.put("mode", "test"); // 테스트모드 일때만 사용하세요

    	JSONObject result = null;
	    logger.debug("sms전송부분");

		// 개별발송(1)
		if("1".equals(smsSendVO.getType())){
			String phone  = smsSendVO.getPhone().replaceAll("-", "");
			if(phone.length()==0){
				throw new BizException("member.90001");
			}
			div = StringUtil.explode(phone, "\n");
			to_tran = StringUtil.implode(",",div);
			total = div.length;
			
			for(int i=0; i<total; i++){
		    	set.put("to", div[i]);
		    	result = coolsms.send(set); // 보내기&전송결과받기
		    }
			
			
		// 회원그룹별발송(2)
		}else if( "2".equals(smsSendVO.getType()) ){
			
			//to_tran = mapper.getGdMemberGrpInfo(smsSendVO).getGrpnm();
			to_tran = String.valueOf(smsSendVO.getKlevel());		// 선택된 회원 level값
			
			param = new HashMap<String, Object>();
			
			param.put("type", smsSendVO.getType() );

			param.put("klevel", smsSendVO.getKlevel() );
			
			getMemberSMSList = mbmapper.getMemberSMSList(param);
			
			total = getMemberSMSList.size();												//SMS발송명수
			
			for(GdMember list : getMemberSMSList){
				set.put("to", list.getMobile().replace("-", ""));
				result = coolsms.send(set); // 보내기&전송결과받기
			}
		
		// 전체회원 발송(3)
		}else if( "3".equals(smsSendVO.getType()) ){
			
			smsSendVO.setKlevel(0);
			
			to_tran = "전체회원";													//전체 회원 명칭
			
			param = new HashMap<String, Object>();
			
			param.put("type", smsSendVO.getType() );
			
			param.put("klevel", 0 );
			
			getMemberSMSList = mbmapper.getMemberSMSList(param);
			
			total = getMemberSMSList.size();												//SMS발송명수
			
			for(GdMember list : getMemberSMSList){
				set.put("to", list.getMobile().replace("-", ""));
				result = coolsms.send(set); // 보내기&전송결과받기
			}
			
		// SMS주소록 회원선택(6)
		}else if( "6".equals(smsSendVO.getType()) ){
				
			to_tran = "SMS주소록 회원선택";
			
			param = new HashMap<String, Object>();
			
			param.put("type", smsSendVO.getType() );
			
			param.put("sno", smsSendVO.getChk() );

			getSmsAddressList = mapper.getSmsAddressList(param);
			
			total = getSmsAddressList.size();												//SMS발송명수
			
			for(GdSmsAddress list : getSmsAddressList){
				set.put("to", list.getSmsMobile().replace("-", ""));
				result = coolsms.send(set); // 보내기&전송결과받기
			}
			
		// SMS주소록 회원검색(7)
		}else if( "7".equals(smsSendVO.getType()) ){
			
			to_tran = "SMS주소록 회원검색";
			
			param = new HashMap<String, Object>();
			
			param.put("type", smsSendVO.getType() );
			
			// 검색타입
			param.put("skey", smsSendVO.getSkey());
			
			// 검색어
			param.put("sword", smsSendVO.getSword());
			
			// 그룹
			param.put("slevel", smsSendVO.getSlevel());
			
			// 등록일
			param.put("sregdt_0", smsSendVO.getSregdt() != null ? smsSendVO.getSregdt()[0] : "" );
			param.put("sregdt_1", smsSendVO.getSregdt() != null ? smsSendVO.getSregdt()[1] : "" );
			
			// 성별
			param.put("sex", smsSendVO.getSex());
			

			getSmsAddressList = mapper.getSmsAddressList(param);
			
			total = getSmsAddressList.size();												//SMS발송명수
			
			for(GdSmsAddress list : getSmsAddressList){
				set.put("to", list.getSmsMobile().replace("-", ""));
				result = coolsms.send(set); // 보내기&전송결과받기
			}
			
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms insertSendSms!!!");
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms smsSendVO.getType()" + smsSendVO.getType());
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms to_tran!!!" + to_tran);
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms total!!!" + total);
		}
		
		int smsPoint = Integer.parseInt(ShopConfig.getProperty("smsPt")) ;
		
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms smsPoint!!!" + smsPoint);
		}
		
		if (total>smsPoint){
			//String msg = "SMS 발송예정인 " + numberFormat(String.valueOf(total))+"건이 잔여콜수인 " + numberFormat(String.valueOf(getSmsPoint()))+"건보다 많습니다";
			throw new BizException("member.90002", new String[]{StringUtil.numberFormat(String.valueOf(total)), StringUtil.numberFormat(String.valueOf(smsPoint))});
			
		}
					

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>total" + total);
		}
		
		ShopConfig.setProperty( "smsPt", String.valueOf(smsPoint- total) );
		
		//SMS발송로그
		smsparam.put("msg", smsSendVO.getMsg());
		smsparam.put("toTran", to_tran);
		smsparam.put("type", smsSendVO.getType());
		smsparam.put("cnt", String.valueOf(total));
		smsparam.put("sellerCd",adminCd);
		mapper.smsLog(smsparam);
		//mapper.smsLog(smsSendVO.getMsg(),to_tran, smsSendVO.getType(), String.valueOf(total),admin);
		
		if ((Boolean) result.get("status") == true) {
			// 메시지 보내기 성공 및 전송결과 출력
			logger.debug("성공");
			logger.debug((String) result.get("group_id")); // 그룹아이디
			logger.debug((String) result.get("result_code")); // 결과코드
			logger.debug((String) result.get("result_message")); // 결과메시지
			/*logger.debug((String) result.get("success_count")); // 성공갯수
			logger.debug((String) result.get("error_count")); // 발송실패 메시지 수
			*/
		} else {
			// 메시지 보내기 실패
			logger.debug("실패");
			logger.debug((String) result.get("code"));// REST API 에러코드
			logger.debug((String) result.get("message"));// 에러메시지
		}
		
		return total;
	}
	

	
	public List<GdSmsAddress> getSmsAddressGroupList() throws Exception {
		return mapper.getSmsAddressGroupList();
	}
	
	/*public int getSmsAddressCount(Map<String, Object> param) throws Exception {
		return  mapper.getSmsAddressCount(param);
		
	}
	
	public List<GdSmsAddress> getSmsAddressList(Map<String, Object> param) throws Exception {
		return mapper.getSmsAddressList(param);
	}*/
	
	public GdSmsAddress getGdSmsAddressInfo(SmsAddressVO smsAddressVO) throws Exception {
		return mapper.getGdSmsAddressInfo(smsAddressVO);
	}
	
	public void deleteSmsAddress(SmsAddressVO smsAddressVO) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("sno", smsAddressVO.getChk() );
		
		mapper.deleteSmsAddress(param);
	}
	
	public void updateSmsAddress(SmsAddressVO smsAddressVO) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("smsGrp", "Def".equals(smsAddressVO.getGrpChk()) ? smsAddressVO.getSmsGrp() : smsAddressVO.getSmsGrpNew() );
		param.put("smsName", smsAddressVO.getSmsName());
		param.put("smsMobile", StringUtil.implode("-",smsAddressVO.getSmsMobile()) );
		param.put("smsEtc", smsAddressVO.getSmsEtc() );
		param.put("sex", smsAddressVO.getSex() );
		param.put("sno", smsAddressVO.getSno() );
		
		mapper.updateSmsAddress(param);
	}
	
	public void insertSmsAddress(SmsAddressVO smsAddressVO) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("smsGrp", "Def".equals(smsAddressVO.getGrpChk()) ? smsAddressVO.getSmsGrp() : smsAddressVO.getSmsGrpNew() );
		param.put("smsName", smsAddressVO.getSmsName());
		param.put("smsMobile", StringUtil.implode("-",smsAddressVO.getSmsMobile()) );
		param.put("smsEtc", smsAddressVO.getSmsEtc() );
		param.put("sex", smsAddressVO.getSex() );
		
		mapper.insertSmsAddress(param);
	}

	public int getSellerAddressGroupListCount(Map<String, Object> param) throws Exception {
		return  mapper.getSellerAddressGroupListCount(param);
		
	}
	
	public List<AdminSellerManagementVO> getSellerAddressGroupList(Map<String, Object> param) throws Exception {
		
		return mapper.getSellerAddressGroupList(param);
		
	}
	
	
	public int insertSendSms(BatchVO batchVO) throws Exception {
		String to_tran = "";
		int total = 0;
		Map<String, Object> param = null;
		
		List<GdMember> getMemberSMSList = null;
		
		
		// SMS 일괄발송(4)회원선택	
		if( "4".equals(batchVO.getType()) ){
			
			to_tran = "SMS일괄발송(회원선택)";										//SMS일괄발송(회원선택)
			
			param = new HashMap<String, Object>();
			
			param.put("type", batchVO.getType() );
			
			param.put("chk", batchVO.getChk() );
			
			getMemberSMSList = mbmapper.getMemberSMSList(param);
			
			total = getMemberSMSList.size();												//SMS발송명수
			
		// SMS 일괄발송(5)검색
		}else if( "5".equals(batchVO.getType()) ){
			
			to_tran = "SMS일괄발송(검색)";												//SMS일괄발송(검색)
			
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
			
			getMemberSMSList = mbmapper.getMemberSMSList(param);
			
			total = getMemberSMSList.size();												//SMS발송명수
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms insertSendSms!!!");
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms smsSendVO.getType()" + batchVO.getType());
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms to_tran!!!" + to_tran);
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms total!!!" + total);
		}
		
		int smsPoint = Integer.parseInt(ShopConfig.getProperty("smsPt")) ;
		
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms smsPoint!!!" + smsPoint);
		}
		
		if (total>smsPoint){
			//String msg = "SMS 발송예정인 " + numberFormat(String.valueOf(total))+"건이 잔여콜수인 " + numberFormat(String.valueOf(getSmsPoint()))+"건보다 많습니다";
			throw new BizException("member.90002", new String[]{StringUtil.numberFormat(String.valueOf(total)), StringUtil.numberFormat(String.valueOf(smsPoint))});
			
		}
		
		//Sms sms = new Sms(true);
		int idx = 0;
		int pre_perc = 0;
		int ici_perc = 0;
		int num_fail = 0;
		int num_success = 0;
		
		String msg = batchVO.getMsg();
		
		
		// 회원그룹별발송(2), 전체회원 발송(3), SMS일괄발송(회원선택)(4), SMS일괄발송(검색)(5)
		if( "4".equals(batchVO.getType()) || "5".equals(batchVO.getType()) ){
			
			if (logger.isDebugEnabled()) {
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms_send Type!!!" + batchVO.getType());
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms_send getMemberSMSList.size()!!!" + getMemberSMSList.size());
			}
			
			for(int i=0; i < getMemberSMSList.size();i++){
				
				try{
					// TODO sms 발송(추후변경가능)
					//sms.send(msg, getMemberSMSList.get(i).getMobile(), smsSendVO.getCallback(), "","","");
					
					if (logger.isDebugEnabled()) {
						logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>getMemberSMSList.get(i).getMobile()" + getMemberSMSList.get(i).getMobile());
						logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>smsSendVO.getCallbackphone()" + batchVO.getCallbackphone());
						logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>msg" + msg);
					}
					
					mapper.smsSend(StringUtil.replaceAll(getMemberSMSList.get(i).getMobile(), "-", "") , batchVO.getCallbackphone(), msg, "4");
					
					num_success ++;
				}catch(Exception e){
					num_fail ++;
				}
				
				ici_perc = (int) Math.floor(++ idx / total * 100);
				if (pre_perc!=ici_perc){
					pre_perc = ici_perc;
				}
			}
			
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>total" + total);
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>num_success" + num_success);
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>num_fail" + num_fail);
		}

		//smsPoint변경
		ShopConfig.setProperty( "smsPt", String.valueOf(smsPoint- total) );
		//SMS발송로그
		//mapper.smsLog(msg,to_tran, batchVO.getType(), String.valueOf(total));
		
		return total;
		
	}


	/*public int insertSendSms(SmsAddressVO smsAddressVO) throws Exception {
		String to_tran = "";
		int total = 0;
		//String query = null;
		Map<String, Object> param = null;
		
		List<GdSmsAddress> getSmsAddressList = null;
		
			
		// SMS주소록 회원선택(6)
		if( "6".equals(smsAddressVO.getType()) ){
			
			to_tran = "SMS주소록 회원선택";
			
			param = new HashMap<String, Object>();
			
			param.put("type", smsAddressVO.getType() );
			
			param.put("sno", smsAddressVO.getChk() );

			getSmsAddressList = mapper.getSmsAddressList(param);
			
			total = getSmsAddressList.size();												//SMS발송명수
			
		// SMS주소록 회원검색(7)
		}else if( "7".equals(smsAddressVO.getType()) ){
			
			to_tran = "SMS주소록 회원검색";
			
			param = new HashMap<String, Object>();
			
			param.put("type", smsAddressVO.getType() );
			
			// 검색타입
			param.put("skey", smsAddressVO.getSkey());
			
			// 검색어
			param.put("sword", smsAddressVO.getSword());
			
			// 그룹
			param.put("slevel", smsAddressVO.getSlevel());
			
			// 등록일
			param.put("sregdt_0", smsAddressVO.getSregdt() != null ? smsAddressVO.getSregdt()[0] : "" );
			param.put("sregdt_1", smsAddressVO.getSregdt() != null ? smsAddressVO.getSregdt()[1] : "" );
			
			// 성별
			param.put("sex", smsAddressVO.getSex());
			

			getSmsAddressList = mapper.getSmsAddressList(param);
			
			total = getSmsAddressList.size();												//SMS발송명수
		}
		
		
		
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms insertSendSms!!!");
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms smsAddressVO.getType()" + smsAddressVO.getType());
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms to_tran!!!" + to_tran);
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms total!!!" + total);
		}
		
		int smsPoint = Integer.parseInt(ShopConfig.getProperty("smsPt")) ;
		
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms smsPoint!!!" + smsPoint);
		}
		
		if (total>smsPoint){
			throw new BizException("member.90002", new String[]{StringUtil.numberFormat(String.valueOf(total)), StringUtil.numberFormat(String.valueOf(smsPoint))});
			
		}
		
		int idx = 0;
		int pre_perc = 0;
		int ici_perc = 0;
		int num_fail = 0;
		int num_success = 0;
		
		String msg = smsAddressVO.getMsg();
		
			
		// SMS주소록 ---> (회원선택)(6), (검색)(7)
		if( "6".equals(smsAddressVO.getType()) || "7".equals(smsAddressVO.getType()) ){
			
			if (logger.isDebugEnabled()) {
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms_send Type!!!" + smsAddressVO.getType());
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms_send getSmsAddressList.size()!!!" + getSmsAddressList.size());
			}
			
			for(int i=0; i < getSmsAddressList.size();i++){
				
				try{
					// TODO sms 발송(추후변경가능)
					//sms.send(msg, getMemberSMSList.get(i).getMobile(), smsSendVO.getCallback(), "","","");
					
					if (logger.isDebugEnabled()) {
						logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>getMemberSMSList.get(i).getMobile()" + getSmsAddressList.get(i).getSmsMobile());
						logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>smsSendVO.getCallbackphone()" + smsAddressVO.getCallbackphone());
						logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>msg" + msg);
					}
					
					mapper.smsSend(StringUtil.replaceAll(getSmsAddressList.get(i).getSmsMobile(), "-", "") , smsAddressVO.getCallbackphone(), msg, "4");
					
					num_success ++;
				}catch(Exception e){
					num_fail ++;
				}
				
				ici_perc = (int) Math.floor(++ idx / total * 100);
				if (pre_perc!=ici_perc){
					//out.println("<script>parent.document.getElementById('sms_bar').style.width = '"+ici_perc+"%';</script>");
					pre_perc = ici_perc;
				}
			}
			
		}

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>total" + total);
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>num_success" + num_success);
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>num_fail" + num_fail);
		}
		
		ShopConfig.setProperty( "smsPt", String.valueOf(smsPoint- total) );
		//SMS발송로그
		mapper.smsLog(msg,to_tran, smsAddressVO.getType(), String.valueOf(total));
		
		return total;
		
	}*/
	
	
	
	
	
}
