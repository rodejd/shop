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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

public class AmazonSES {

	private static final Logger logger = LoggerFactory.getLogger(AmazonSES.class);
	
	//받는사람, 제목, 내용, 메일종류
	public void mailSender(String recipient, String mode, Map<String,Object> map) throws Exception {
		final String from = ConfigClass.value("from_mail");
		final String fromname = ConfigClass.value("from_name");
		final String to = recipient; 
		final String username = ConfigClass.value("ases_name");
		final String password = ConfigClass.value("ases_pswd");
		
		final String host = ConfigClass.value("ases_host");
		final int port = Integer.parseInt(ConfigClass.value("ases_port"));
		
		
		String emailCfgUploadPath = ConfigClass.value("EMAIL_PATH");
		//제목
		Properties subjectProp = new Properties();
		InputStream in = new FileInputStream(emailCfgUploadPath.concat("subject.html"));
		subjectProp.load(in);
		in.close();
		final String subject = StringUtil.toConvert(subjectProp.getProperty("subject_" + mode, "").trim(), "8859_1", "UTF-8");
				
		//내용
		StringBuffer mainContents = new StringBuffer();
		ArrayList<?> list = FileUtil.getOneLineListToFile(emailCfgUploadPath.concat("contents_" + mode + ".html"));
		if (null != list && 0 < list.size()) {
			for (int i = 0, size = list.size(); i < size; i++) {
				mainContents.append(list.get(i) + "\n");
			}
		}
		String body = StringUtil.xssReturn(mainContents.toString());
		body = changeStr(body, map);
		
		// Create a Properties object to contain connection configuration information.
		Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtp");
    	props.put("mail.smtp.port", port); 
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.auth", "true");
		
    	// Create a Session object to represent a mail session with the specified properties. 
    	Session session = Session.getDefaultInstance(props);
		//session.setDebug(true); //for debug 
		
        // Create a message with the specified information. 
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from, fromname));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setContent(body, "text/html;charset=UTF-8");
    	
        // Create a transport.
        Transport transport = session.getTransport();
        
		try {
			logger.debug("Sending...");
	        // Connect to Amazon SES using the SMTP username and password you specified above.
	        transport.connect(host, username, password);
	        
	        // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
		} catch (Exception e) {
			logger.debug("Amason SES Error: " + e.getMessage());
		} finally {
            transport.close();
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
