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
		
		
		
		//smscool api ??????
		String api_key = ""; //?????? api-key ?????? ????????? ??????
		String api_secret = ""; //?????? api-secret ?????? ????????? ??????
	    Coolsms coolsms = new Coolsms(api_key, api_secret);

	    HashMap<String, String> set = new HashMap<String, String>();
		// 10??? 16??? ????????? ???????????? ?????????????????? ?????? ????????? ?????????????????? ????????? ????????? ??? ????????????.
		set.put("from", smsSendVO.getCallbackphone()); // ????????????
	    set.put("text", smsSendVO.getMsg()); // ????????????
	    set.put("type", "sms"); // ?????? ??????
	    set.put("mode", "test"); // ??????????????? ????????? ???????????????

    	JSONObject result = null;
	    logger.debug("sms????????????");

		// ????????????(1)
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
		    	result = coolsms.send(set); // ?????????&??????????????????
		    }
			
			
		// ?????????????????????(2)
		}else if( "2".equals(smsSendVO.getType()) ){
			
			//to_tran = mapper.getGdMemberGrpInfo(smsSendVO).getGrpnm();
			to_tran = String.valueOf(smsSendVO.getKlevel());		// ????????? ?????? level???
			
			param = new HashMap<String, Object>();
			
			param.put("type", smsSendVO.getType() );

			param.put("klevel", smsSendVO.getKlevel() );
			
			getMemberSMSList = mbmapper.getMemberSMSList(param);
			
			total = getMemberSMSList.size();												//SMS????????????
			
			for(GdMember list : getMemberSMSList){
				set.put("to", list.getMobile().replace("-", ""));
				result = coolsms.send(set); // ?????????&??????????????????
			}
		
		// ???????????? ??????(3)
		}else if( "3".equals(smsSendVO.getType()) ){
			
			smsSendVO.setKlevel(0);
			
			to_tran = "????????????";													//?????? ?????? ??????
			
			param = new HashMap<String, Object>();
			
			param.put("type", smsSendVO.getType() );
			
			param.put("klevel", 0 );
			
			getMemberSMSList = mbmapper.getMemberSMSList(param);
			
			total = getMemberSMSList.size();												//SMS????????????
			
			for(GdMember list : getMemberSMSList){
				set.put("to", list.getMobile().replace("-", ""));
				result = coolsms.send(set); // ?????????&??????????????????
			}
			
		// SMS????????? ????????????(6)
		}else if( "6".equals(smsSendVO.getType()) ){
				
			to_tran = "SMS????????? ????????????";
			
			param = new HashMap<String, Object>();
			
			param.put("type", smsSendVO.getType() );
			
			param.put("sno", smsSendVO.getChk() );

			getSmsAddressList = mapper.getSmsAddressList(param);
			
			total = getSmsAddressList.size();												//SMS????????????
			
			for(GdSmsAddress list : getSmsAddressList){
				set.put("to", list.getSmsMobile().replace("-", ""));
				result = coolsms.send(set); // ?????????&??????????????????
			}
			
		// SMS????????? ????????????(7)
		}else if( "7".equals(smsSendVO.getType()) ){
			
			to_tran = "SMS????????? ????????????";
			
			param = new HashMap<String, Object>();
			
			param.put("type", smsSendVO.getType() );
			
			// ????????????
			param.put("skey", smsSendVO.getSkey());
			
			// ?????????
			param.put("sword", smsSendVO.getSword());
			
			// ??????
			param.put("slevel", smsSendVO.getSlevel());
			
			// ?????????
			param.put("sregdt_0", smsSendVO.getSregdt() != null ? smsSendVO.getSregdt()[0] : "" );
			param.put("sregdt_1", smsSendVO.getSregdt() != null ? smsSendVO.getSregdt()[1] : "" );
			
			// ??????
			param.put("sex", smsSendVO.getSex());
			

			getSmsAddressList = mapper.getSmsAddressList(param);
			
			total = getSmsAddressList.size();												//SMS????????????
			
			for(GdSmsAddress list : getSmsAddressList){
				set.put("to", list.getSmsMobile().replace("-", ""));
				result = coolsms.send(set); // ?????????&??????????????????
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
			//String msg = "SMS ??????????????? " + numberFormat(String.valueOf(total))+"?????? ??????????????? " + numberFormat(String.valueOf(getSmsPoint()))+"????????? ????????????";
			throw new BizException("member.90002", new String[]{StringUtil.numberFormat(String.valueOf(total)), StringUtil.numberFormat(String.valueOf(smsPoint))});
			
		}
					

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>total" + total);
		}
		
		ShopConfig.setProperty( "smsPt", String.valueOf(smsPoint- total) );
		
		//SMS????????????
		smsparam.put("msg", smsSendVO.getMsg());
		smsparam.put("toTran", to_tran);
		smsparam.put("type", smsSendVO.getType());
		smsparam.put("cnt", String.valueOf(total));
		smsparam.put("sellerCd",adminCd);
		mapper.smsLog(smsparam);
		//mapper.smsLog(smsSendVO.getMsg(),to_tran, smsSendVO.getType(), String.valueOf(total),admin);
		
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
		
		
		// SMS ????????????(4)????????????	
		if( "4".equals(batchVO.getType()) ){
			
			to_tran = "SMS????????????(????????????)";										//SMS????????????(????????????)
			
			param = new HashMap<String, Object>();
			
			param.put("type", batchVO.getType() );
			
			param.put("chk", batchVO.getChk() );
			
			getMemberSMSList = mbmapper.getMemberSMSList(param);
			
			total = getMemberSMSList.size();												//SMS????????????
			
		// SMS ????????????(5)??????
		}else if( "5".equals(batchVO.getType()) ){
			
			to_tran = "SMS????????????(??????)";												//SMS????????????(??????)
			
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
			
			getMemberSMSList = mbmapper.getMemberSMSList(param);
			
			total = getMemberSMSList.size();												//SMS????????????
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
			//String msg = "SMS ??????????????? " + numberFormat(String.valueOf(total))+"?????? ??????????????? " + numberFormat(String.valueOf(getSmsPoint()))+"????????? ????????????";
			throw new BizException("member.90002", new String[]{StringUtil.numberFormat(String.valueOf(total)), StringUtil.numberFormat(String.valueOf(smsPoint))});
			
		}
		
		//Sms sms = new Sms(true);
		int idx = 0;
		int pre_perc = 0;
		int ici_perc = 0;
		int num_fail = 0;
		int num_success = 0;
		
		String msg = batchVO.getMsg();
		
		
		// ?????????????????????(2), ???????????? ??????(3), SMS????????????(????????????)(4), SMS????????????(??????)(5)
		if( "4".equals(batchVO.getType()) || "5".equals(batchVO.getType()) ){
			
			if (logger.isDebugEnabled()) {
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms_send Type!!!" + batchVO.getType());
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms_send getMemberSMSList.size()!!!" + getMemberSMSList.size());
			}
			
			for(int i=0; i < getMemberSMSList.size();i++){
				
				try{
					// TODO sms ??????(??????????????????)
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

		//smsPoint??????
		ShopConfig.setProperty( "smsPt", String.valueOf(smsPoint- total) );
		//SMS????????????
		//mapper.smsLog(msg,to_tran, batchVO.getType(), String.valueOf(total));
		
		return total;
		
	}


	/*public int insertSendSms(SmsAddressVO smsAddressVO) throws Exception {
		String to_tran = "";
		int total = 0;
		//String query = null;
		Map<String, Object> param = null;
		
		List<GdSmsAddress> getSmsAddressList = null;
		
			
		// SMS????????? ????????????(6)
		if( "6".equals(smsAddressVO.getType()) ){
			
			to_tran = "SMS????????? ????????????";
			
			param = new HashMap<String, Object>();
			
			param.put("type", smsAddressVO.getType() );
			
			param.put("sno", smsAddressVO.getChk() );

			getSmsAddressList = mapper.getSmsAddressList(param);
			
			total = getSmsAddressList.size();												//SMS????????????
			
		// SMS????????? ????????????(7)
		}else if( "7".equals(smsAddressVO.getType()) ){
			
			to_tran = "SMS????????? ????????????";
			
			param = new HashMap<String, Object>();
			
			param.put("type", smsAddressVO.getType() );
			
			// ????????????
			param.put("skey", smsAddressVO.getSkey());
			
			// ?????????
			param.put("sword", smsAddressVO.getSword());
			
			// ??????
			param.put("slevel", smsAddressVO.getSlevel());
			
			// ?????????
			param.put("sregdt_0", smsAddressVO.getSregdt() != null ? smsAddressVO.getSregdt()[0] : "" );
			param.put("sregdt_1", smsAddressVO.getSregdt() != null ? smsAddressVO.getSregdt()[1] : "" );
			
			// ??????
			param.put("sex", smsAddressVO.getSex());
			

			getSmsAddressList = mapper.getSmsAddressList(param);
			
			total = getSmsAddressList.size();												//SMS????????????
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
		
			
		// SMS????????? ---> (????????????)(6), (??????)(7)
		if( "6".equals(smsAddressVO.getType()) || "7".equals(smsAddressVO.getType()) ){
			
			if (logger.isDebugEnabled()) {
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms_send Type!!!" + smsAddressVO.getType());
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sms_send getSmsAddressList.size()!!!" + getSmsAddressList.size());
			}
			
			for(int i=0; i < getSmsAddressList.size();i++){
				
				try{
					// TODO sms ??????(??????????????????)
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
		//SMS????????????
		mapper.smsLog(msg,to_tran, smsAddressVO.getType(), String.valueOf(total));
		
		return total;
		
	}*/
	
	
	
	
	
}
