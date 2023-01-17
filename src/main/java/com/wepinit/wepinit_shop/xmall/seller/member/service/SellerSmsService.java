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
package com.wepinit.wepinit_shop.xmall.seller.member.service;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.dao.member.MemberMapper;
import com.wepinit.wepinit_shop.xmall.admin.dao.member.SmsMapper;
import com.wepinit.wepinit_shop.xmall.admin.service.common.Coolsms;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.exception.BizException;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMemberGrp;
import com.wepinit.wepinit_shop.xmall.common.vo.GdSmsAddress;
import com.wepinit.wepinit_shop.xmall.common.vo.GdSmsSample;
import com.wepinit.wepinit_shop.xmall.seller.member.dao.SellerSmsMapper;
import com.wepinit.wepinit_shop.xmall.seller.member.vo.SellerSmsFM;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SellerSmsService {
	
	private static final Logger logger = LoggerFactory.getLogger(SellerSmsService.class);
	
	@Resource
	SellerSmsMapper mapper;

	@Resource
	MemberMapper mbmapper;
	
	@Resource
	SmsMapper smsmapper;
	
	public SellerSmsFM getSellerSmsList(SellerSmsFM sellerSmsFM) throws Exception {
		
		sellerSmsFM.setRowCount(mapper.getSellerSmsListCount(sellerSmsFM)); //총 갯수

		sellerSmsFM.setSellerSmsList(mapper.getSellerSmsList(sellerSmsFM)); // 목록
		
		return sellerSmsFM;
		
	}
	
	public List<GdMemberGrp> getGdMemberGrpList(SellerSmsFM sellerSmsFM) throws Exception {
		
		return mapper.getGdMemberGrpList(sellerSmsFM);
		
	}
	
	public int getGdSmsAddressCount(SellerSmsFM sellerSmsFM) throws Exception {
		return  mapper.getGdSmsAddressCount(sellerSmsFM);
	}
	
	public List<GdSmsAddress> getGdSmsAddressList(SellerSmsFM sellerSmsFM) throws Exception {
		
		return mapper.getGdSmsAddressList(sellerSmsFM);
	}
	
	public int getGdSmsSampleCount(Map<String, Object> param) throws Exception {
		return  mapper.getGdSmsSampleCount(param);	
	}
	
	public List<GdSmsSample> getGdSmsSampleList(Map<String, Object> param) throws Exception {
		return mapper.getGdSmsSampleList(param);	
	}

	public GdSmsSample getSmsSampleInfo(SellerSmsFM sellerSmsFM) throws Exception {
		return mapper.getSmsSampleInfo(sellerSmsFM);
	}
	
	public void insertSmsSample(SellerSmsFM sellerSmsFM) throws Exception {
		mapper.insertSmsSample(sellerSmsFM);
	}
	
	public void updateSmsSample(SellerSmsFM sellerSmsFM) throws Exception {
		mapper.updateSmsSample(sellerSmsFM);
	}

	public int insertSendSms(SellerSmsFM sellerSmsFM, String sellerCd) throws Exception {
		String[] div = null;
		String to_tran = "";
		int total = 0;
		//String query = null;
		Map<String, Object> param = null;
		Map<String, Object> smsparam = new HashMap<String, Object>();
		List<GdMember> getMemberSMSList = null;
		
		
		
		//smscool api 이용
		String api_key = ""; //업체 api-key 입력 가입후 발급
		String api_secret = "";//업체 api-secret 입력 가입후 발급
	    Coolsms coolsms = new Coolsms(api_key, api_secret);

	    HashMap<String, String> set = new HashMap<String, String>();
		// 10월 16일 이후로 발신번호 사전등록제로 인해 등록된 발신번호로만 문자를 보내실 수 있습니다.
		set.put("from", sellerSmsFM.getCallbackphone()); // 발신번호
	    set.put("text", sellerSmsFM.getMsg()); // 문자내용
	    set.put("type", "sms"); // 문자 타입
	    set.put("mode", "test"); // 테스트모드 일때만 사용하세요

    	JSONObject result = null;
	    logger.debug("sms전송부분");

		// 개별발송(1)
		if("1".equals(sellerSmsFM.getType())){
			String phone  = sellerSmsFM.getPhone().replaceAll("-", "");
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
		}else if( "2".equals(sellerSmsFM.getType()) ){
			
			//to_tran = mapper.getGdMemberGrpInfo(smsSendVO).getGrpnm();
			to_tran = String.valueOf(sellerSmsFM.getKlevel());		// 선택된 회원 level값
			
			param = new HashMap<String, Object>();
			
			param.put("type", sellerSmsFM.getType() );

			param.put("klevel", sellerSmsFM.getKlevel() );
			
			getMemberSMSList = mbmapper.getMemberSMSList(param);
			
			total = getMemberSMSList.size();												//SMS발송명수
			
			for(GdMember list : getMemberSMSList){
				set.put("to", list.getMobile().replace("-", ""));
				result = coolsms.send(set); // 보내기&전송결과받기
			}
		
		// 전체회원 발송(3)
		}else if( "3".equals(sellerSmsFM.getType()) ){
			
			sellerSmsFM.setKlevel(0);
			
			to_tran = "전체회원";													//전체 회원 명칭
			
			param = new HashMap<String, Object>();
			
			param.put("type", sellerSmsFM.getType() );
			
			param.put("klevel", 0 );
			
			getMemberSMSList = mbmapper.getMemberSMSList(param);
			
			total = getMemberSMSList.size();												//SMS발송명수
			
			for(GdMember list : getMemberSMSList){
				set.put("to", list.getMobile().replace("-", ""));
				result = coolsms.send(set); // 보내기&전송결과받기
			}
					
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms insertSendSms!!!");
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms smsSendVO.getType()" + sellerSmsFM.getType());
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
		smsparam.put("msg", sellerSmsFM.getMsg());
		smsparam.put("toTran", to_tran);
		smsparam.put("type", sellerSmsFM.getType());
		smsparam.put("cnt", String.valueOf(total));
		smsparam.put("sellerCd",sellerCd);
		smsmapper.smsLog(smsparam);
		//smsMapper.smsLog(sellerSmsFM.getMsg(),to_tran, sellerSmsFM.getType(), String.valueOf(total), sellerCd);
		
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

	
}
