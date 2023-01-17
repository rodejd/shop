package com.wepinit.wepinit_shop.xcube.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Aes128Util {
	final static String secretKey = "RTSZY31U9ym91hv3";
	static String IV = "EGTHRIZHIJKLMOQU";

	/**
	 * Encrypt
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
		String encrypt = "";
		try {
			byte[] keyData = secretKey.getBytes();
			SecretKey secureKey = new SecretKeySpec(keyData, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes()));
			encrypt = byteArrayToHex(cipher.doFinal(str.getBytes("UTF-8")));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return encrypt;
	}

	/**
	 * Decrypt
	 * @param str
	 * @return
	 */
	public static String decode(String str) {
		String decrypt = "";
		try {
			byte[] keyData = secretKey.getBytes();
			SecretKey secureKey = new SecretKeySpec(keyData, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes("UTF-8")));
			decrypt = new String(cipher.doFinal(hexToByteArray(str)),"UTF-8");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return decrypt;
	}
	
	/**
	 * HEX string to byte[]
	 * @param hex
	 * @return
	 */
	private static byte[] hexToByteArray(String hex) {
		hex = hex.replaceAll("\"", "\\\""); /*" */
		if (hex == null || hex.length() == 0) {
			return null;
		}
		
		byte[] ba = new byte[hex.length() / 2];
		for (int i = 0; i < ba.length; i++) {
			ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		
		return ba;
	}
	 
	/**
	 * byte[] to HEX sting
	 * @param byteArray
	 * @return
	 */
	public static String byteArrayToHex(byte[] byteArray) {
		if (byteArray == null || byteArray.length == 0) {
			return null;
		}
		
		StringBuilder stringBuffer = new StringBuilder(byteArray.length * 2);
		String hexNumber;
		for (byte aBa : byteArray) {
			hexNumber = "0" + Integer.toHexString(0xff & aBa);
			stringBuffer.append(hexNumber.substring(hexNumber.length() - 2));
		}
		
		return stringBuffer.toString();
	}
}