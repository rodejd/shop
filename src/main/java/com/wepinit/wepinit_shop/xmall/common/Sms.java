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

import com.wepinit.wepinit_shop.xcube.util.StringUtil;

import java.util.*;

/*import xcube.DBHandler;
import xcube.RequestSet;
import xcube.util.StringUtil;*/

public class Sms {
	private String sno;
	private boolean msgOn =false;
	private String smsPass;
	private static long smsPt = -1L;
	private static List r_data = new ArrayList();
	
	public boolean update_ok_eNamoo = false;
	
	private final String HOST = "www.gnumall.co.kr";
	private final int PORT = 1688;
	
	public long getSmsPt(){
		return Sms.smsPt;
	}
	
	public Sms() throws Exception{		
		getSno();
	}
	
	public Sms(boolean msgOn) throws Exception{
		this();
		this.msgOn = msgOn;
	}
	
	private void getSno() throws Exception{
		// get godo's serial
		ShopConfig sc = ShopConfig.getInstance();
		
		this.sno = sc.getProperty("godomall", "cfg", "sno", "N/A");
		
		if (sno == null || sno.length() == 0) {
			throw new Exception("업체고유번호가 지정되어 있지 않습니다");
		}
		
		
		// get smsPassword
		this.smsPass = sc.getProperty("config", "cfg", "smsPass", "N/A");
		
		if (this.smsPass == null || this.smsPass.length() == 0) {
			throw new Exception("SMS 비밀번호가 지정되어 있지 않습니다");
		}
		
		Map data = new HashMap();
		data.put("sno", sno);
		data.put("pass", this.smsPass);
		
		String result = sms_socket(data);
		
		if(result==null || result.length()==0){
			result = "0";
		}
		
		result =  result.replaceAll("[\\[\\]=]|result", "");
		
		Sms.smsPt = Long.parseLong(result);
	}	
	
	private String sms_socket(Map data){
		
		Iterator iterator = data.keySet().iterator();
		
		String strSms = "";
		String key;
		while(iterator.hasNext()){
			key = (String) iterator.next();
			strSms += key + "=" + data.get(key) +"\n"; 
		}		
		
		//TODO Socket 연결 구현 필요함
		Random r = new Random();
		if(smsPt < 0){
			return String.valueOf(r.nextInt(100));
		}else{
			return String.valueOf(r.nextInt((int)smsPt));
		}
		
	}
	
	public void send(String tranMsg, String tranPhone, String tranCallback, String sendDate, String alramEtc, String tranType)throws Exception{
		
		boolean result = false;
		
		if (Sms.smsPt <= 0 ){
			throw new Exception("SMS 잔여콜수가 부족합니다. 추가 충전하셔서 사용하세요");
		}

		tranPhone = tranPhone.replaceAll("-","");
		tranCallback = tranCallback.replaceAll("-","");
		tranMsg = tranMsg;
		
		boolean add = false;
		int tp = r_data.size() -1;
		String hp = "";
		
		if(tp > -1 && "send".equals(tranType) ){
			
			if(tranMsg.equals(((Map)r_data.get(tp)).get("msg"))){
				
				hp = ((Map)r_data.get(tp)).get("hp").toString();
				
				String[] tmp = StringUtil.explode(hp, ","); 
				
				if(tmp.length < 30){
					
					add = true;
					
				}else{
					update();
				}
			}
			
		}
	

		// set msg
		if(add){
			hp += "," + tranPhone;
		}else{
			Map data = new LinkedHashMap();
			data.put("type", tranType);
			data.put("sno", this.sno);
			data.put("pass", this.smsPass);
			data.put("callback", tranCallback);
			data.put("hp", tranPhone);
			data.put("res_date", sendDate);
			data.put("res_etc", alramEtc);
			data.put("__head__", "__body__");
			data.put("msg", tranMsg);
			
			r_data.add(data);		
		}

		Sms.smsPt -= 1;	

	}
	
	public void log(String msg, String toTran, String type, String cnt) throws Exception{
		/*toTran = toTran.replaceAll("-","");
		
		RequestSet param = new RequestSet();
		param.setProperty("msg", msg);
		param.setProperty("to_tran", toTran);
		param.setProperty("type", type);
		param.setProperty("cnt", cnt);
		
		
		String query = "INSERT INTO gd_sms_log set msg = :msg, type	= :type, to_tran = :to_tran, cnt = :cnt, regdt	= now()";
		
		DBHandler.executeUpdateForNonTrans(query, param);*/
	}
	
	public void update() throws Exception{
		/*if(this.r_data.size()>0){
			for(int i=0;i<r_data.size();i++){
				sms_socket((Map) r_data.get(i));
			}
		}
		
		this.r_data.clear();
		ShopConfig sc = ShopConfig.getInstance();
		sc.setProperty("sms", "cfg", "smsPt", "N/A", String.valueOf(this.smsPt));*/
		
	}	
	
	public boolean sms_Control(String phone,String msg, String alramDate, String etcData, String type) throws Exception{
		if(type.equals("new")){
			
			send(msg, phone, phone, alramDate, etcData, "res_send");
			update_ok_eNamoo = true;
			update();
			
		}else if(type.equals("delete")){
			
			send(msg, phone, phone, alramDate, etcData,"res_delete");
			update_ok_eNamoo = true;			
			Sms.smsPt = Sms.smsPt + 2;
			update();
		}else{
			send(msg, phone, phone, alramDate, etcData,"res_delete");
			send(msg, phone, phone, alramDate, etcData, "res_send");
			update_ok_eNamoo = true;			
			Sms.smsPt = Sms.smsPt + 2;
			update();
		}
		return true;
	}
	/*
	public static void main(String[] args) throws Exception{
		Sms sms = new Sms();
		System.out.println("smsPt = "+ sms.smsPt);
	}
	*/
}
