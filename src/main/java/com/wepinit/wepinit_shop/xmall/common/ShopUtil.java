/*******************************************************************
					 PMGroupKorea Co., Ltd.
Copyright PMGroupKorea Co., Ltd. 2005. All rights reserved.
No part of this work covered by the copyright hereon may be reproduced,
stored in a retrieval system, in any form or by any means, electronic,
mechanical, photocopying, recording or otherwise, without the prior
written permission of PMGroupKorea Co., Ltd.
SOLUTION	:   XMaLL4 for Spring
FILE_NAME   :
DATE		:   2018.1.29
AUTHOR	  :   PMGK S/W Engineer   <contact@gmail.com>
DESCRIPTION :
FUNCTIONS   :
HISTORY	 :
REMARKS	 :
******************************************************************/
package com.wepinit.wepinit_shop.xmall.common;

import com.wepinit.wepinit_shop.xcube.util.StringUtil;

import java.util.Base64;
import java.util.Base64.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

/*import xcube.ConfigClass;
import xcube.LogUtil;
import xcube.RequestSet;
import xcube.ResultTable;
import xcube.util.StringUtil;*/

public class ShopUtil {
	
	/**
	 * Converts a string from one base to another base. The bases must be between
	 * 2 and 36 inclusive. This function exhibits the same issues as the original
	 * PHP version related to overflow in 32 bit integers.
	 * 
	 * @param inputValue A string representation of a number.
	 * @param fromBase The starting radix of a number between 2 and 36 inclusive.
	 * @param toBase The ending radix of the number between 2 and 36 inclusive.
	 * @return The <code>inputValue</code> converted into <code>toBase</code> base.
	 * @see http://www.php.net/manual/en/function.base-convert.php
	 */
	public static String base_convert(final String inputValue, final int fromBase, final int toBase) {
		if (fromBase < 2 || fromBase > 36 || toBase < 2 || toBase > 36) {
			return null;
		}
		String ret = null;
		try {
			ret = Integer.toString(Integer.parseInt(inputValue, fromBase), toBase);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * Converts a decimal string into a binary string.
	 * @param inputValue A string representation of a decimal number.
	 * @return A bit string representation of the <code>inputValue</code>.
	 */
	public static String decbin(final String inputValue) {
		return base_convert(inputValue, 10, 2);
	}
	/**
	 * Converts a binary string into a decimal string.
	 * @param inputValue A string representation of a binary number.
	 * @return A decimal number string representation of the <code>inputValue</code>.
	 */
	public static String bindec(final String inputValue) {
		return base_convert(inputValue, 2, 10);
	}

	/**
	 * RFC 1321에 규정된 암호화 방식.
	 * 128비트 암호화 해시 함수
	 * @param inpara
	 * @return
	 */
	public static String MD5(String inpara) {
		byte[] bpara = new byte[inpara.length()];
		byte[] rethash;
		int i;
		
		for (i=0; i < inpara.length(); i++){
			bpara[i] = (byte)(inpara.charAt(i) & 0xff );
		}
		
		try {
			MessageDigest md5er = MessageDigest.getInstance("MD5");
			rethash = md5er.digest(bpara);
			
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	
		StringBuffer r = new StringBuffer(32);
		String x;
		for (i=0; i < rethash.length; i++) {
			x = Integer.toHexString(rethash[i] & 0xff);
			if (x.length()<2) r.append("0");
			r.append(x);
		}
		
		return r.toString();
	}
	
	/**
	 * BASE64 Encoder
	 * @param str
	 * @return
	 */
	public static String base64Encode(String str) {
		String result = "";
		Encoder encoder = Base64.getEncoder();
		byte[] b1 = str.getBytes();
		result = new String(encoder.encode(b1));
		return result;
	}
	 
	 /**
	  * BASE64 Decoder
	  * @param str
	  * @return
	  */
	public static String base64Decode(String str) {
		String result = "";
		Decoder decoder = Base64.getDecoder();
		byte[] b1 = decoder.decode(str);
		result = new String(b1);
		return result;
	}
	
	public static String addoptStr(String param) {
		String[] addopt = StringUtil.explode(param, "\\|");
		
		String addoptStr = "";
		int addoptLength = addopt.length;
		
		if(addoptLength > 0){
			for(int k=0;k<addoptLength;k++){
				if(addoptStr.equals("")){
					addoptStr += addopt[k];
				}else{
					addoptStr += "|" + addopt[k];
				}
			}
		}
		
		return addoptStr;
	}
	
	public static String addopt(String param1) {
		String[] addopt = StringUtil.explode(param1, "\\|");
		
		StringBuffer returnStr = new StringBuffer();
		int addoptLength = addopt.length;

		if(addoptLength > 0){
			for(int k=0;k<addoptLength;k++){
				if(!addopt[k].equals("")){
					if(StringUtil.explode(addopt[k],"\\^").length > 0 && !StringUtil.explode(addopt[k],"\\^").equals("")){
						returnStr.append(StringUtil.explode(addopt[k],"\\^")[1]);
						returnStr.append(":");
						returnStr.append(StringUtil.explode(addopt[k],"\\^")[2]);
					}
				}
			}
		}
		
		return returnStr.toString();
	}
	
	public static int getDcprice(String price, String discount, int percent)
			throws Exception {
	
		int ret = 0;
		int amt = StringUtil.N2I(price);	
		int emoney_cut = Integer.parseInt(ShopConfig.getProperty("emoney_cut", "0"));
		int result = 0;
		
		if (0 < emoney_cut) {
			percent = 1; // 3
			for(int i = 0; i < emoney_cut; i++) {
				percent *= 10;
			}
		}
		
		// 할인액 및 할인율이 없으면 할인금액 0원으로 설정
		if(discount.equals("") || discount.equals("%")){
			discount = "0";
		}
		ret = (-1 < discount.indexOf("%")) ? (int)(amt * (Integer.parseInt(discount.replace("%", "")) / (float)100)) : Integer.parseInt(discount);
		result = Math.round((amt - ret) * percent) /percent;
		return result;
	}
}