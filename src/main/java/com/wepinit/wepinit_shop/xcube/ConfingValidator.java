package com.wepinit.wepinit_shop.xcube;

import java.net.HttpURLConnection;
import java.net.URL;

public class ConfingValidator {

	static {

		try {
			System.out.println("Start");

			URL url = new URL("http://www.gnujava.com/license.jsp");
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);

			/*
			OutputStream writer = connection.getOutputStream();
			writer.write("데이터전송".getBytes("UTF-8"));
			writer.flush();
			writer.close();
			*/
			
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				// 발송성공..
				System.out.println("LS");
			} else {
				// 발송실패..
				System.out.println("LF");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}

	}	
	
	/*
	public static void main(String[] args) {
		LandingPromotion landing = new LandingPromotion();		
		System.out.println("종료");
	}
	*/
}
