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
		
		sellerSmsFM.setRowCount(mapper.getSellerSmsListCount(sellerSmsFM)); //??? ??????

		sellerSmsFM.setSellerSmsList(mapper.getSellerSmsList(sellerSmsFM)); // ??????
		
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
		
		
		
		//smscool api ??????
		String api_key = ""; //?????? api-key ?????? ????????? ??????
		String api_secret = "";//?????? api-secret ?????? ????????? ??????
	    Coolsms coolsms = new Coolsms(api_key, api_secret);

	    HashMap<String, String> set = new HashMap<String, String>();
		// 10??? 16??? ????????? ???????????? ?????????????????? ?????? ????????? ?????????????????? ????????? ????????? ??? ????????????.
		set.put("from", sellerSmsFM.getCallbackphone()); // ????????????
	    set.put("text", sellerSmsFM.getMsg()); // ????????????
	    set.put("type", "sms"); // ?????? ??????
	    set.put("mode", "test"); // ??????????????? ????????? ???????????????

    	JSONObject result = null;
	    logger.debug("sms????????????");

		// ????????????(1)
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
		    	result = coolsms.send(set); // ?????????&??????????????????
		    }
			
			
		// ?????????????????????(2)
		}else if( "2".equals(sellerSmsFM.getType()) ){
			
			//to_tran = mapper.getGdMemberGrpInfo(smsSendVO).getGrpnm();
			to_tran = String.valueOf(sellerSmsFM.getKlevel());		// ????????? ?????? level???
			
			param = new HashMap<String, Object>();
			
			param.put("type", sellerSmsFM.getType() );

			param.put("klevel", sellerSmsFM.getKlevel() );
			
			getMemberSMSList = mbmapper.getMemberSMSList(param);
			
			total = getMemberSMSList.size();												//SMS????????????
			
			for(GdMember list : getMemberSMSList){
				set.put("to", list.getMobile().replace("-", ""));
				result = coolsms.send(set); // ?????????&??????????????????
			}
		
		// ???????????? ??????(3)
		}else if( "3".equals(sellerSmsFM.getType()) ){
			
			sellerSmsFM.setKlevel(0);
			
			to_tran = "????????????";													//?????? ?????? ??????
			
			param = new HashMap<String, Object>();
			
			param.put("type", sellerSmsFM.getType() );
			
			param.put("klevel", 0 );
			
			getMemberSMSList = mbmapper.getMemberSMSList(param);
			
			total = getMemberSMSList.size();												//SMS????????????
			
			for(GdMember list : getMemberSMSList){
				set.put("to", list.getMobile().replace("-", ""));
				result = coolsms.send(set); // ?????????&??????????????????
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
			//String msg = "SMS ??????????????? " + numberFormat(String.valueOf(total))+"?????? ??????????????? " + numberFormat(String.valueOf(getSmsPoint()))+"????????? ????????????";
			throw new BizException("member.90002", new String[]{StringUtil.numberFormat(String.valueOf(total)), StringUtil.numberFormat(String.valueOf(smsPoint))});
			
		}
					

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>total" + total);
		}
		
		ShopConfig.setProperty( "smsPt", String.valueOf(smsPoint- total) );
		
		//SMS????????????
		smsparam.put("msg", sellerSmsFM.getMsg());
		smsparam.put("toTran", to_tran);
		smsparam.put("type", sellerSmsFM.getType());
		smsparam.put("cnt", String.valueOf(total));
		smsparam.put("sellerCd",sellerCd);
		smsmapper.smsLog(smsparam);
		//smsMapper.smsLog(sellerSmsFM.getMsg(),to_tran, sellerSmsFM.getType(), String.valueOf(total), sellerCd);
		
		if ((Boolean) result.get("status") == true) {
			// ????????? ????????? ?????? ??? ???????????? ??????
			logger.debug("??????");
			logger.debug((String) result.get("group_id")); // ???????????????
			logger.debug((String) result.get("result_code")); // ????????????
			logger.debug((String) result.get("result_message")); // ???????????????
			/*logger.debug((String) result.get("success_count")); // ????????????
			logger.debug((String) result.get("error_count")); // ???????????? ????????? ???
			*/
		} else {
			// ????????? ????????? ??????
			logger.debug("??????");
			logger.debug((String) result.get("code"));// REST API ????????????
			logger.debug((String) result.get("message"));// ???????????????
		}
		
		return total;
	}

	
}
