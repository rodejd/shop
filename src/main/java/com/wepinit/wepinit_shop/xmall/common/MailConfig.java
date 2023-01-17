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

package com.wepinit.wepinit_shop.xmall.common;

import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xcube.util.FileUtil;
import com.wepinit.wepinit_shop.xcube.util.StringUtil;
import com.wepinit.wepinit_shop.xmall.admin.service.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

public class MailConfig {
	
	@Resource
	MemberService memberService;

	private static final Logger logger = LoggerFactory.getLogger(MailConfig.class);
	
	//받는사람, 제목, 내용, 메일종류
	public void mailSender(String sTo, String mode, Map<String,Object> map) {
		//String emailSubject = "";
		String emailCfgUploadPath = ConfigClass.value("EMAIL_PATH");
		
		// 메일제목
		//Properties nmProp = memberService.getNmProp();
		
		// 메일내용
		StringBuffer mainContents = new StringBuffer();
		ArrayList list = FileUtil.getOneLineListToFile(emailCfgUploadPath.concat("contents_" + mode + ".html"));
		
		if(null != list && 0 < list.size()) {
			for(int i = 0, size = list.size(); i < size; i++) {
				mainContents.append(list.get(i) + "\n");
			}
		}
		
		String host = ConfigClass.value("smtp_host");//"smtp.naver.com";
		final String username = ConfigClass.value("smtp_id"); //"보내는사람인증메일아이디"; //네이버 아이디를 입력해주세요.
		final String password = ConfigClass.value("smtp_pw"); //"메일주소 비밀번호";
		int port = Integer.parseInt(ConfigClass.value("smtp_port"));	// 포트번호
		final String ssl_factory = ConfigClass.value("ssl_factory"); //"메일주소 비밀번호";

		//받는사람
		String recipient = sTo;
		//내용
		String body = StringUtil.xssReturn(mainContents.toString());
		body = changeStr(body,map);
		
		// SMTP 서버 정보 설정 
		Properties props = System.getProperties();

		props.put("mail.smtp.user", "noreply.ritzmall@gmail.com"); // Google계정@gmail.com으로 설정
		props.put("mail.smtp.host", "smtp.gmail.com"); // POP3 메일 서버 속성 설정
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.debug", "false");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		
		//Session 생성 
		Session session = Session.getDefaultInstance(props, new Authenticator() { 
			protected PasswordAuthentication getPasswordAuthentication() { 
				return new PasswordAuthentication(username, password);
			} 
		});
		
		try {
			//제목
			Properties subjectProp = new Properties();
			InputStream in = new FileInputStream(emailCfgUploadPath.concat("subject.html"));
			subjectProp.load(in);
			in.close();
			String sTitle = StringUtil.toConvert(subjectProp.getProperty("subject_" + mode, "").trim(), "8859_1", "UTF-8");
			
			
			session.setDebug(true); //for debug 
			Message mimeMessage = new MimeMessage(session); //MimeMessage 생성
			
			mimeMessage.setHeader("content-type", "text/html;charset=UTF-8");
			mimeMessage.setFrom(new InternetAddress(username)); //발신자 셋팅 , 보내는 사람의 이메일주소를 한번 더 입력합니다. 이때는 이메일 풀 주소를 다 작성해주세요. 
			//mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); //수신자셋팅 //.TO 외에 .CC(참조) .BCC(숨은참조) 도 있음
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			mimeMessage.setSubject(sTitle); //제목셋팅 
			//mimeMessage.setText(body); //내용셋팅 
			mimeMessage.setContent(body, "text/html;charset=UTF-8");
			Transport.send(mimeMessage); //javax.mail.Transport.send() 이용
		} catch (Exception e) {
			logger.debug("error > {}" ,e.getMessage());
		}
		

	}
	
	public String changeStr(String body, Map<String,Object> map) {
		String temp = "";
		if(body != null && !body.isEmpty()) {
			temp = body.replaceAll("\\{name\\}", String.valueOf(map.get("name")));
			temp = temp.replaceAll("\\{shopName\\}", ConfigClass.value("SHOP_NAME"));
			//temp = temp.replaceAll("/shop", ConfigClass.value("SHOP_URL")+"shop");
			temp = temp.replaceAll("/resources/shop", ConfigClass.value("SHOP_URL")+"resources/shop");
			temp = temp.replaceAll("\\{shopUrl\\}", ConfigClass.value("SHOP_MAIN_URL"));
			temp = temp.replaceAll("\\{adminEmail\\}", String.valueOf(map.get("adminEmail")));
			temp = temp.replaceAll("\\{ordno\\}", String.valueOf(map.get("ordno")));
			temp = temp.replaceAll("\\{goodsName\\}", String.valueOf(map.get("goodsName")));
			temp = temp.replaceAll("\\{questiontitle\\}", String.valueOf(map.get("questiontitle")));
			temp = temp.replaceAll("\\{question\\}", String.valueOf(map.get("question")));
			temp = temp.replaceAll("\\{answertitle\\}", String.valueOf(map.get("answertitle")));
			temp = temp.replaceAll("\\{answer\\}", String.valueOf(map.get("answer")));
			temp = temp.replaceAll("\\{id\\}", String.valueOf(map.get("id")));
			temp = temp.replaceAll("\\{password\\}", String.valueOf(map.get("pw")));
			temp = temp.replaceAll("\\{tokenurl\\}", String.valueOf(map.get("tokenurl")));
		}
		return temp;
	}
	
	
}
