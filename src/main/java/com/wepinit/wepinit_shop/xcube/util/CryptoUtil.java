package com.wepinit.wepinit_shop.xcube.util;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA, SHA256 암호화
 * @author Administrator
 *
 */
public class CryptoUtil {
	// RSA 공개키
	public static final String rsaPubKeyStr = "30819f300d06092a864886f70d010101050003818d0030818902818100b0f5f57332810c06287714d09fc00a0e0c2109e0584ef23c6c1ada2dc1866931329f47ed5f5bd19f2b0fffbef1d7efbe50b7e2d5be88005b257e54bb8ca89b7c9db324e9af8bd064fc157082214008b4a333a52389872638f7f5287a67ee126eb6cab190093dc3774388b114634bc5904d8007a43e1a245d3aaeda918bb748410203010001";	// 키 생성후 입력

	// RSA 개인키
	public static final String rsaPrivKeyStr = "30820277020100300d06092a864886f70d0101010500048202613082025d02010002818100b0f5f57332810c06287714d09fc00a0e0c2109e0584ef23c6c1ada2dc1866931329f47ed5f5bd19f2b0fffbef1d7efbe50b7e2d5be88005b257e54bb8ca89b7c9db324e9af8bd064fc157082214008b4a333a52389872638f7f5287a67ee126eb6cab190093dc3774388b114634bc5904d8007a43e1a245d3aaeda918bb748410203010001028181008b6402c68e06b698f730edd573b08a7b0c34ce19c895cf1ba72ff3a24d297a1cc5ba4fa6fc11506a673a4002816fcdaa04dd0431c7d64b2980bc25346294c17c345150feb3a31a2acd80d979288d6299fe07ff5cdc5686e89008bbe1f5e83a367cc768de3f7bbfc818057e5a8fe7e0b84a32150984f02e126dc6e4c2de9b9d51024100e5e10c8baefc55ea535482e4f7392237ee09b7c5ad145a6f6eb4240a5454d57ff7a293fb063f028d4245b4c1a2dc2625d08737dcaa23f79a9795fb79c4e04f45024100c51191b832e7cf44e3db68d5faca1c9e37b85f96b3f88cbe1ee93ba5f0080fd3a17a3810a3796c7cc4b580adf26d02dbcae1a30e5bbb90639f721a009b3676cd024019df4a5a2e479823d75d1b9e4974d4b516c561398e514c1c5e8947c521d8aa6ecca02fa780fc767d0f8868dfa244c4c65cc1db37aaae819f948ffccda7aad74102410092cab881bda18079ef44733dd8d68e24a89ab4d13bf3ab5abf09ba7dab6345eebd3c9de07ef4a683f5bec14050f1348ce5fdd7088dfa13deb1593bd731c68b0d0240660a74b3d50e2767e7e29b17e53457742d930a17ad276a93f8f14648292ccfb117d992955722627971b6797cad68a7dbbb5d7d21adfb0919aff8b0714ce86e4a";


	/**
	 *	RSA 공개키, 개인키 Generator
	 *		- 암호화할 문자열의 length가 길어질 경우 KeyPairGenerator initializedml bit수를 늘려 키를 다시 생성하여 사용해야 한다.
	 */
	public void getRSAKeyGenerator() throws Exception {
		// 기본으로 1024 bit 셋팅
		this.getRSAKeyGenerator(1024);
	}
	public void getRSAKeyGenerator(int bit) throws Exception {
		SecureRandom random = new SecureRandom();
		KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
		//KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA/ECB/PKCS1PADDING");
		//KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA/ECB/PKCS1PADDING", "SunJSSE");

		// 512 bit 는 53byte이상 암호화시 오류가 발생한다
		// 512 bit 키를 생성 (암호화할 문자열이 더 길경우 1024, 2048로 변경해 사용한다.
		gen.initialize(bit, random);

		KeyPair pair = gen.generateKeyPair();
		Key pubKey = pair.getPublic();		// 공개키
		Key privKey = pair.getPrivate();	// 개인키

		System.out.println("RSA pubKey : " + byteArrayToHex(pubKey.getEncoded()));
		System.out.println("RSA privKey : " + byteArrayToHex(privKey.getEncoded()));
	}

	/**
	 *	RSA 암호화 : 약속된 공개키로 RSA 암호화에 사용하여 암호화된 데이터를 반환한다.
	 *		- java.crypto.IllegalBlockSizeException : Data must not be longer than 53 bytes 오류발생시
	 *			-> gen.initialize(512, random); 중 512bit 를 1024 or 2048로 늘려준다.
	 */
	public static String getRSAEncrypt(String inStr) throws Exception {
		return getRSAEncrypt(inStr, "UTF-8");
	}
	public static String getRSAEncrypt(String inStr, String charset) throws Exception {
		if ( null == inStr || "".equals(inStr) )  return null;

		// 약속된 공개키를 사용가능한 공개키로 만들어준다.
		X509EncodedKeySpec spec = new X509EncodedKeySpec(CryptoUtil.hexToByteArray(rsaPubKeyStr));
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PublicKey publicKey = null;
		try {
			publicKey = kf.generatePublic(spec);
		}catch(Exception e){
			e.printStackTrace();
		}

		// RSA 공개키로 암호화한다.
		Cipher cipher = Cipher.getInstance("RSA");
		//Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
		//Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING", "SunJCE");
		byte[] inputByte = inStr.getBytes(charset);	// encoding

		cipher.init(cipher.ENCRYPT_MODE, publicKey);
		byte[] cipherText = cipher.doFinal(inputByte);

		return CryptoUtil.byteArrayToHex(cipherText);
	}

	/**
	 * RSA 복호화
	 */
	public static String getRSADecrypt(String inStr) throws Exception {
		return getRSADecrypt(inStr, "UTF-8");
	}
	public static String getRSADecrypt(String inStr, String charset) throws Exception {
		if ( null == inStr || "".equals(inStr) )  return null;
		// 약속된 개인키를 사용가능한 개인키로 만들어준다.
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(CryptoUtil.hexToByteArray(rsaPrivKeyStr));
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = null;
		try {
			privateKey = (PrivateKey) kf.generatePrivate(spec);
		}catch(Exception e){
			e.printStackTrace();
		}

		// RSA 공개키로 암호화한다.
		Cipher cipher = Cipher.getInstance("RSA");
		//Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
		//Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING", "SunJCE");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] outByte = cipher.doFinal(CryptoUtil.hexToByteArray(inStr));

		return new String(outByte, charset);
	}

	/**
	 *	hex string to byte[]
	 */
	public static byte[] hexToByteArray(String hex) {
		if ( null == hex || 0 == hex.length() )  return null;

		byte[] b = new byte[hex.length()/2];
		for ( int i=0 ; i < b.length ; i++ ){
			b[i] = (byte) Integer.parseInt(hex.substring(2*i, 2*i+2), 16);
		}
		return b;
	}

	/**
	 *	byte[] string to hex
	 */
	public static String byteArrayToHex(byte[] b) {
		if ( null == b || 0 == b.length )  return null;

		StringBuffer sb = new StringBuffer(b.length*2);

		String hexNumber;
		for ( int i=0 ; i < b.length ; i++ ) {
			hexNumber = "0" + Integer.toHexString(0xff & b[i]);
			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}
		return sb.toString();
	}

	/**
	 * SHA256 암호화
	 * @param str
	 */
	public static String getSHA256(String str) {
		if (null == str) return null;
		
		MessageDigest md;
		byte[] b;
		
		try {
	        md = MessageDigest.getInstance("SHA-256");
	        b = md.digest(str.getBytes("UTF-8"));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
		return CryptoUtil.byteArrayToHex(b);
	}

//	public static void main(String[] args) throws Exception {
//		// RSA 공개키, 인키생성
//		new CryptoUtil().getRSAKeyGenerator();
//
//		String s = "김호훈";
//		
//		// 암호화
//		String ens = CryptoUtil.getRSAEncrypt(s);
//		System.out.println("암호화 : " + ens);
//		
//		// 복호화
//		String dns = CryptoUtil.getRSADecrypt(ens);
//		System.out.println("암호화 : " + dns);
//		
//		// 암호화
//		String ens1 = CryptoUtil.getRSAEncrypt(s, "EUC-KR");
//		System.out.println("암호화 : " + ens1);
//		
//		// 복호화 한글
//		String dns2 = CryptoUtil.getRSADecrypt(ens1, "EUC-KR");
//		System.out.println("암호화 : " + dns2);
//		
//		// SHA256 암호화
//		System.out.println("SHA256 암호화 : " + CryptoUtil.getSHA256("qwer1234!@#$"));
//	}
}
