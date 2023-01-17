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
package com.wepinit.wepinit_shop.xmall.front.vo.member;

import com.wepinit.wepinit_shop.xcube.util.CodeUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xcube.util.WebUtil;
import com.wepinit.wepinit_shop.xmall.common.ShopConfig;
import com.wepinit.wepinit_shop.xmall.common.vo.GdCode;
import com.wepinit.wepinit_shop.xmall.common.vo.GdMember;

import java.util.List;

public class FrontMemberMyinfoVO extends FrontMemberVO {
	// out ShopConfig.getProperty 실행
	private String nicknameUse  ;	
	private String nicknameReq  ;	
	private String birthUse 	;	
	private String birthReq 	;	
	private String sexUse 		;	
	private String calendarUse  ;	
	private String marriynUse 	;	
	private String marriynReq 	;	
	private String marridateUse ; 	
	private String marridateReq ; 	
	private String emailUse 	;	
	private String emailReq 	;	
	private String maillingUse  ;
	private String maillingReq	;
	private String addressUse 	;	
	private String addressReq 	;	
	private String mobileUse 	;	
	private String mobileReq 	;	
	private String smsUse 		;	
	private String smsReq 		;	
	private String phoneUse 	;	
	private String phoneReq 	;	
	private String faxUse 		;	
	private String faxReq 		;	
	private String companyUse 	;	
	private String companyReq 	;	
	private String serviceUse 	;	
	private String serviceReq 	;	
	private String itemUse 		;	
	private String itemReq 		;	
	private String businoUse 	;	
	private String businoReq 	;	
	private String jobUse 		;	
	private String jobReq 		;	
	private String interestUse 	;	
	private String interestReq 	;	
	private String ex1Use 		;	
	private String ex1Req 		;	
	private String ex1Join 		;	
	private String ex2Use 		;	
	private String ex2Req 		;	
	private String ex2Join 		;	
	private String ex3Use 		;	
	private String ex3Req 		;	
	private String ex3Join 		;	
	private String ex4Use 		;	
	private String ex4Req 		;	
	private String ex4Join 		;	
	private String ex5Use 		;	
	private String ex5Req 		;	
	private String ex5Join 		;		
	private String ex6Use 		;	
	private String ex6Req 		;	
	private String ex6Join 		;	
	private String recommidUse 	;	
	private String recommidReq 	;	
	private String memoUse 		;	
	private String memoReq 		;	
	private String jobSelect 	;	
	private List<GdCode> interestSelect ;
	
	// out
	private GdMember gdMember;
	private String errMessage;
	private String email1;	// email 아이디
	private String email2;	// email 도메인
	private String mobile0;
	private String mobile1;
	private String mobile2;
	private String phone0;
	private String phone1;
	private String phone2;
	private String[] faxArr;
	private List<FrontMemberMyinfoCodeVO> codeList;
	
	private FrontMemberSnsVO facebook;
	private FrontMemberSnsVO google;
	private FrontMemberSnsVO kakao;
	private FrontMemberSnsVO apple;
	
	// in
	private String password2;
	private String name;
	
	public FrontMemberMyinfoVO() throws Exception {
		this.initOutData();
	}
	
	public void initOutData() throws Exception {
		this.nicknameUse  	= ShopConfig.getProperty("fieldset", "useField", "nickname");     
		this.nicknameReq  	= ShopConfig.getProperty("fieldset", "reqField", "nickname");	                                 
		this.birthUse 	 	= ShopConfig.getProperty("fieldset", "useField", "birth");                                       
		this.birthReq 	 	= ShopConfig.getProperty("fieldset", "reqField", "birth");                                       
		this.sexUse 		= ShopConfig.getProperty("fieldset", "useField", "sex");                                         
		this.calendarUse  	= ShopConfig.getProperty("fieldset", "useField", "calendar");                                    
		this.marriynUse 	= ShopConfig.getProperty("fieldset", "useField", "marriyn");                                     
		this.marriynReq 	= ShopConfig.getProperty("fieldset", "reqField", "marriyn");                                     
		this.marridateUse 	= ShopConfig.getProperty("fieldset", "useField", "marridate");                                   
		this.marridateReq 	= ShopConfig.getProperty("fieldset", "reqField", "marridate");                                   
		this.emailUse 	 	= ShopConfig.getProperty("fieldset", "useField", "email");                                       
		this.emailReq 	 	= ShopConfig.getProperty("fieldset", "reqField", "email");                                       
		this.maillingUse  	= ShopConfig.getProperty("fieldset", "useField", "mailling");                                    
		this.maillingReq	= ShopConfig.getProperty("fieldset", "reqField", "mailling");
		this.addressUse 	= ShopConfig.getProperty("fieldset", "useField", "address");                                     
		this.addressReq 	= ShopConfig.getProperty("fieldset", "reqField", "address");                                     
		this.mobileUse 	 	= ShopConfig.getProperty("fieldset", "useField", "mobile");                                      
		this.mobileReq 	 	= ShopConfig.getProperty("fieldset", "reqField", "mobile");                                      
		this.smsUse 		= ShopConfig.getProperty("fieldset", "useField", "sms");                                         
		this.smsReq 		= ShopConfig.getProperty("fieldset", "reqField", "sms");                                         
		this.phoneUse 	 	= ShopConfig.getProperty("fieldset", "useField", "phone");                                       
		this.phoneReq 	 	= ShopConfig.getProperty("fieldset", "reqField", "phone");                                       
		this.faxUse 		= ShopConfig.getProperty("fieldset", "useField", "fax");                                         
		this.faxReq 		= ShopConfig.getProperty("fieldset", "reqField", "fax");                                         
		this.companyUse 	= ShopConfig.getProperty("fieldset", "useField", "fax");                                         
		this.companyReq 	= ShopConfig.getProperty("fieldset", "reqField", "fax");                                         
		this.serviceUse 	= ShopConfig.getProperty("fieldset", "useField", "service");                                     
		this.serviceReq 	= ShopConfig.getProperty("fieldset", "reqField", "service");                                     
		this.itemUse 		= ShopConfig.getProperty("fieldset", "useField", "item");                                        
		this.itemReq 		= ShopConfig.getProperty("fieldset", "reqField", "item");                                        
		this.businoUse 	 	= ShopConfig.getProperty("fieldset", "useField", "busino");                                      
		this.businoReq 	 	= ShopConfig.getProperty("fieldset", "reqField", "busino");                                      
		this.jobUse 		= ShopConfig.getProperty("fieldset", "useField", "job");                                         
		this.jobReq 		= ShopConfig.getProperty("fieldset", "reqField", "job");                                         
		this.interestUse 	= ShopConfig.getProperty("fieldset", "useField", "interest");                                    
		this.interestReq 	= ShopConfig.getProperty("fieldset", "reqField", "interest");	                                 
		this.ex1Use 		= ShopConfig.getProperty("fieldset", "useField", "ex1");                                         
		this.ex1Req 		= ShopConfig.getProperty("fieldset", "reqField", "ex1");                                         
		this.ex1Join 		= ShopConfig.getProperty("fieldset", "joinset", "ex1");                                          
		this.ex2Use 		= ShopConfig.getProperty("fieldset", "useField", "ex2");                                         
		this.ex2Req 		= ShopConfig.getProperty("fieldset", "reqField", "ex2");                                         
		this.ex2Join 		= ShopConfig.getProperty("fieldset", "joinset", "ex2");                                          
		this.ex3Use 		= ShopConfig.getProperty("fieldset", "useField", "ex3");                                         
		this.ex3Req 		= ShopConfig.getProperty("fieldset", "reqField", "ex3");                                         
		this.ex3Join 		= ShopConfig.getProperty("fieldset", "joinset", "ex3");                                          
		this.ex4Use 		= ShopConfig.getProperty("fieldset", "useField", "ex4");                                         
		this.ex4Req 		= ShopConfig.getProperty("fieldset", "reqField", "ex4");                                         
		this.ex4Join 		= ShopConfig.getProperty("fieldset", "joinset", "ex4");                                          
		this.ex5Use 		= ShopConfig.getProperty("fieldset", "useField", "ex5");                                         
		this.ex5Req 		= ShopConfig.getProperty("fieldset", "reqField", "ex5");                                         
		this.ex5Join 		= ShopConfig.getProperty("fieldset", "joinset", "ex5");                                          
		this.ex6Use 		= ShopConfig.getProperty("fieldset", "useField", "ex6");                                         
		this.ex6Req 		= ShopConfig.getProperty("fieldset", "reqField", "ex6");                                         
		this.ex6Join 		= ShopConfig.getProperty("fieldset", "joinset", "ex6");                                          
		this.recommidUse 	= ShopConfig.getProperty("fieldset", "useField", "recommend");                                    
		this.recommidReq 	= ShopConfig.getProperty("fieldset", "reqField", "recommend");                                    
		this.memoUse 		= ShopConfig.getProperty("fieldset", "useField", "memo");                                        
		this.memoReq 		= ShopConfig.getProperty("fieldset", "reqField", "memo");              
		
		if(this.gdMember != null) { // 회원정보수정
//			this.jobSelect 	 	= WebUtil.makeSelectOption(CodeUtil.codeitem("job"), this.gdMember.getJob());
			this.jobSelect 	 	= WebUtil.makeSelectCodeItem2(CodeUtil.codeitem("job"), this.gdMember.getJob());
		} else {					// 회원가입
//			this.jobSelect		= WebUtil.makeSelectOption(CodeUtil.codeitem("job"), "");
			this.jobSelect 	 	= WebUtil.makeSelectCodeItem2(CodeUtil.codeitem("job"), "");
		}
		
		this.interestSelect = CodeUtil.codeitem("like");
	}
	public GdMember getGdMember() {
		return gdMember;
	}
	public void setGdMember(GdMember gdMember) {
		this.gdMember = gdMember;
		
		String [] emailArr = gdMember.getEmail().split("@");
		if(emailArr.length > 1) {
			this.email1		= emailArr[0];
			this.email2		= emailArr[1];
		}
		
		if(this.gdMember != null) {
//			String[] mobileArr = StringUtil.explode(gdMember.getMobile(), "-");
//			this.mobile0 = mobileArr[0] != null && !mobileArr[0].equals("") ? mobileArr[0] : "";
//			this.mobile1 = mobileArr[1] != null && !mobileArr[1].equals("") ? mobileArr[1] : "";
//			this.mobile2 = mobileArr[2] != null && !mobileArr[2].equals("") ? mobileArr[2] : "";
				
			String[] phoneArr = StringUtil.explode(gdMember.getPhone(), "-");
			this.phone0 = phoneArr[0] != null && !phoneArr[0].equals("") ? phoneArr[0] : "";
			this.phone1 = phoneArr[1] != null && !phoneArr[1].equals("") ? phoneArr[1] : "";
			this.phone2 = phoneArr[2] != null && !phoneArr[2].equals("") ? phoneArr[2] : "";
		}

		this.faxArr		= StringUtil.explode(gdMember.getFax(), "-");
	}
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	public String getNicknameUse() {
		return nicknameUse;
	}
	public String getNicknameReq() {
		return nicknameReq;
	}
	public String getBirthUse() {
		return birthUse;
	}
	public String getBirthReq() {
		return birthReq;
	}
	public String getSexUse() {
		return sexUse;
	}
	public String getCalendarUse() {
		return calendarUse;
	}
	public String getMarriynUse() {
		return marriynUse;
	}
	public String getMarriynReq() {
		return marriynReq;
	}
	public String getMarridateUse() {
		return marridateUse;
	}
	public String getMarridateReq() {
		return marridateReq;
	}
	public String getEmailUse() {
		return emailUse;
	}
	public String getEmailReq() {
		return emailReq;
	}
	public String getMaillingUse() {
		return maillingUse;
	}
	public String getAddressUse() {
		return addressUse;
	}
	public String getAddressReq() {
		return addressReq;
	}
	public String getMobileUse() {
		return mobileUse;
	}
	public String getMobileReq() {
		return mobileReq;
	}
	public String getSmsUse() {
		return smsUse;
	}
	public String getSmsReq() {
		return smsReq;
	}
	public String getPhoneUse() {
		return phoneUse;
	}
	public String getPhoneReq() {
		return phoneReq;
	}
	public String getFaxUse() {
		return faxUse;
	}
	public String getFaxReq() {
		return faxReq;
	}
	public String getCompanyUse() {
		return companyUse;
	}
	public String getCompanyReq() {
		return companyReq;
	}
	public String getServiceUse() {
		return serviceUse;
	}
	public String getServiceReq() {
		return serviceReq;
	}
	public String getItemUse() {
		return itemUse;
	}
	public String getItemReq() {
		return itemReq;
	}
	public String getBusinoUse() {
		return businoUse;
	}
	public String getBusinoReq() {
		return businoReq;
	}
	public String getJobUse() {
		return jobUse;
	}
	public String getJobReq() {
		return jobReq;
	}
	public String getInterestUse() {
		return interestUse;
	}
	public String getInterestReq() {
		return interestReq;
	}
	public String getEx1Use() {
		return ex1Use;
	}
	public String getEx1Req() {
		return ex1Req;
	}
	public String getEx1Join() {
		return ex1Join;
	}
	public String getEx2Use() {
		return ex2Use;
	}
	public String getEx2Req() {
		return ex2Req;
	}
	public String getEx2Join() {
		return ex2Join;
	}
	public String getEx3Use() {
		return ex3Use;
	}
	public String getEx3Req() {
		return ex3Req;
	}
	public String getEx3Join() {
		return ex3Join;
	}
	public String getEx4Use() {
		return ex4Use;
	}
	public String getEx4Req() {
		return ex4Req;
	}
	public String getEx4Join() {
		return ex4Join;
	}
	public String getEx5Use() {
		return ex5Use;
	}
	public String getEx5Req() {
		return ex5Req;
	}
	public String getEx5Join() {
		return ex5Join;
	}
	public String getEx6Use() {
		return ex6Use;
	}
	public String getEx6Req() {
		return ex6Req;
	}
	public String getEx6Join() {
		return ex6Join;
	}
	public String getRecommidUse() {
		return recommidUse;
	}
	public String getRecommidReq() {
		return recommidReq;
	}
	public String getMemoUse() {
		return memoUse;
	}
	public String getMemoReq() {
		return memoReq;
	}
	public String getJobSelect() {
		return jobSelect;
	}
	public String getEmail1() {
		return email1;
	}
	public String getEmail2() {
		return email2;
	}
	public String[] getFaxArr() {
		return faxArr;
	}
	public String getMobile0() {
		return mobile0;
	}
	public String getMobile1() {
		return mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public String getPhone0() {
		return phone0;
	}
	public String getPhone1() {
		return phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setCodeList(List<FrontMemberMyinfoCodeVO> codeList) {
		this.codeList = codeList;
	}
	public List<FrontMemberMyinfoCodeVO> getCodeList() {
		return codeList;
	}
	
	public FrontMemberSnsVO getFacebook() {
		return facebook;
	}
	public void setFacebook(FrontMemberSnsVO facebook) {
		this.facebook = facebook;
	}
	public FrontMemberSnsVO getGoogle() {
		return google;
	}
	public void setGoogle(FrontMemberSnsVO google) {
		this.google = google;
	}
	public FrontMemberSnsVO getKakao() {
		return kakao;
	}
	public void setKakao(FrontMemberSnsVO kakao) {
		this.kakao = kakao;
	}
	public FrontMemberSnsVO getApple() {
		return apple;
	}
	public void setApple(FrontMemberSnsVO apple) {
		this.apple = apple;
	}

	public String getName() {
		return name;
	}
	public String getPassword2() {
		return password2;
	}
	public String getMaillingReq() {
		return maillingReq;
	}
	public void setMaillingReq(String maillingReq) {
		this.maillingReq = maillingReq;
	}
	public List<GdCode> getInterestSelect() {
		return interestSelect;
	}
	
}
