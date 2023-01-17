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
package com.wepinit.wepinit_shop.xmall.admin.controller.member;

import com.wepinit.wepinit_shop.xmall.admin.service.member.FieldsetService;
import com.wepinit.wepinit_shop.xmall.admin.vo.member.FieldsetVO;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.vo.GdConfSet;
import com.wepinit.wepinit_shop.xmall.common.vo.join.MemberMemberGrp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/shop/admin/member/*")
public class FieldsetController {

	private static final Logger logger = LoggerFactory.getLogger(FieldsetController.class); 
	
	@Resource
	FieldsetService fService;
	
	@RequestMapping(value = "fieldset")
	public String Fieldset(@ModelAttribute("fieldsetVO") FieldsetVO fieldsetVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
			GdConfSet conf = new GdConfSet();
			conf.setConfCd("fieldset");
			conf.setDimSet("useField");
			conf.setDim1Var("");
			conf.setDim2Var("");
			fieldsetVO.setUseFieldList(fService.getFieldSetProperty(conf));
			conf.setDimSet("reqField");
			fieldsetVO.setReqFieldList(fService.getFieldSetProperty(conf));
		
			fieldsetVO.setStatus(ShopConfig.getProperty("fieldset","joinset","status"));
			fieldsetVO.setRejoin(ShopConfig.getProperty("fieldset","joinset","rejoin"));
			fieldsetVO.setUnableid(ShopConfig.getProperty("fieldset","joinset","unableid"));
			fieldsetVO.setEmoney(ShopConfig.getProperty("fieldset","joinset","emoney"));
			
			List<MemberMemberGrp> rtList = fService.getMemberGroupLIST("");  // where level
			HashMap<Integer, String> garr = new HashMap<Integer,String>();
			for(int i=0;i<rtList.size();i++){
				garr.put(rtList.get(i).getkLevel(),rtList.get(i).getGrpnm());
			}
			fieldsetVO.setGarr(garr);
			fieldsetVO.setGrp(ShopConfig.getProperty("fieldset","joinset","grp"));
			fieldsetVO.setRecommEmoney(ShopConfig.getProperty("fieldset","joinset","recomm_emoney"));
			fieldsetVO.setRecommAddEmoney(ShopConfig.getProperty("fieldset","joinset","recomm_add_emoney"));
			
			logger.debug("@@ fieldset "+fieldsetVO.toString());
		return "/shop/admin/member/fieldset";
	}
	
	@RequestMapping(value = "fieldset/indb")
	public String FieldsetIndb(@ModelAttribute("fieldsetVO") FieldsetVO fieldsetVO,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
				// 회원가입관리 수정
				ShopConfig.setProperty2("fieldset", "joinset", "status", fieldsetVO.getStatus());
				ShopConfig.setProperty2("fieldset", "joinset", "rejoin", fieldsetVO.getRejoin());
				ShopConfig.setProperty2("fieldset", "joinset", "unableid", fieldsetVO.getUnableid());
				ShopConfig.setProperty2("fieldset", "joinset", "emoney", fieldsetVO.getEmoney());
				ShopConfig.setProperty2("fieldset", "joinset", "recomm_emoney", fieldsetVO.getRecommEmoney());
				ShopConfig.setProperty2("fieldset", "joinset", "recomm_add_emoney", fieldsetVO.getRecommAddEmoney());
				//2017-08-25 추가 - 가입시 회원그룹 설정 수정
				ShopConfig.setProperty2("fieldset", "joinset","grp", fieldsetVO.getGrp());
				
//				String[] arrUName=fieldsetVO.getUseFieldName();
//				String[] arrRName=fieldsetVO.getReqFieldName();
//				String[] arrUValue=fieldsetVO.getUseField();
//				String[] arrRValue=fieldsetVO.getReqField();
//				
//				String uName = "";
//				String rName = "";
//				String uValue = "";
//				String rValue = "";
//					for (int i =0;i< arrUName.length && i<arrRName.length; i++) {
//						// 제큐어에서 파라미터이름에 널이나 널스트링이 있을 수 있다.
//						logger.debug("@@@@@@@ fieldset Indb uname "+arrUName[i].toString() +" uvalue "+arrUValue[i].toString() +" rname "+arrRName[i]+" rvalue "+arrRValue[i] );
//							if(!arrUName[i].equals("")){
//								uName = arrUName[i];
//								uValue = arrUValue[i];
//								if(uValue.equals("checked")){
//									ShopConfig.setProperty2("fieldset", "useField", uName, "checked");
//								}else{
//									ShopConfig.setProperty2("fieldset", "useField", uName, "");
//								}
//							}else if(!arrRName[i].equals("")){
//								rName = arrRName[i];
//								rValue = arrRValue[i];
//								if(rValue.equals("checked")){
////		 							rCheckQuery += "'"+rName+"',";
//									ShopConfig.setProperty2("fieldset", "reqField", rName, "checked");
//								}else{
//									ShopConfig.setProperty2("fieldset", "reqField", rName, "");
//								}
//							}
//					}
				
				if(fieldsetVO.getUseFieldName() != null && fieldsetVO.getReqFieldName() != null) {
					if(fieldsetVO.getUseFieldName().length > 0 && fieldsetVO.getReqFieldName().length > 0){
						for(int i=0; i < fieldsetVO.getUseFieldName().length && i< fieldsetVO.getReqFieldName().length; i++){
							if(logger.isDebugEnabled()){
								logger.debug("@@ for ::"+fieldsetVO.getUseFieldName()[i]+" :: "+fieldsetVO.getUseField()[i]);
								logger.debug("@@ req :: "+fieldsetVO.getReqFieldName()[i]+" :: "+fieldsetVO.getReqField()[i]);
							}
							ShopConfig.setProperty2("fieldset", "useField", fieldsetVO.getUseFieldName()[i], fieldsetVO.getUseField()[i] != null ? fieldsetVO.getUseField()[i] : "");
							ShopConfig.setProperty2("fieldset", "reqField", fieldsetVO.getReqFieldName()[i], fieldsetVO.getReqField()[i] != null ? fieldsetVO.getReqField()[i] : "");
						}
					}
				}
				
		logger.debug("@@ fieldset indb"+fieldsetVO.toString());
		return"redirect:/shop/admin/member/fieldset";
	}
	
	
	
}
