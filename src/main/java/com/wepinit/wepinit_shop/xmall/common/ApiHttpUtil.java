package com.wepinit.wepinit_shop.xmall.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ApiHttpUtil {

	public static boolean DEV = false; //true : Dev, false : Real
	
	public static final String GET = "GET";
	public static final String POST = "POST";
	public static final String PUT = "PUT";
	public static final String DELETE = "DELETE";
	
	public static String makeParams(Map<String, Object> params) {
		String param = null;
		StringBuffer sb = new StringBuffer();
		
		if (params != null) {
			for (String key : params.keySet()){
				sb.append(key).append("=").append((params.get(key) == null ? "" : params.get(key)).toString().trim()).append("&");
			}
		}
		param = sb.toString().substring(0, sb.toString().length() - 1);
		
		return param;
	}
	
	private static String makeJsonParams(Map<String, Object> params) {
		String json = "";
		
		if (params != null) {
			json = new Gson().toJson(params);
		}
		
		return json;
	}
	
	private static Map<String, String> dtryxHeaders() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
	
		return headers;
	}
	
	public static Map<String, Object> httpUrlConnection(String method, String targetUrl, Map<String, Object> params, Map<String, String> headers) throws Exception {
		URL url = null;
		HttpURLConnection conn = null;
		Map<String, Object> result = new HashMap<String, Object>();
		
		try{
			//운영
			String ADDR = "https://api.sauceFlex.com/V1";
			//개발
			if (DEV) {
				ADDR = "https://stage.api.sauceFlex.com/V1";
			}
			//외부 URL
			if (targetUrl.contains("kakao.com") || targetUrl.contains("naver.com")) {	
				ADDR = "";
			}
			
			if (method.equalsIgnoreCase(GET)) {
				url = new URL(ADDR + targetUrl + ((params != null) ? "?" + makeParams(params) : ""));
				System.out.println(method + ": " + url);
			} else {
				url = new URL(ADDR + targetUrl);
				String json = "";
				json = new Gson().toJson(params);
				System.out.println(method + ": " + url + " " + json);
			}
			
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(method);
			for (Map.Entry<String, String> header : headers.entrySet()) {
				conn.setRequestProperty(header.getKey(), header.getValue());
            }
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			
			if (!method.equalsIgnoreCase(GET) && params != null) {
				DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
				wr.write(makeJsonParams(params).getBytes("utf-8"));
				wr.flush();
				wr.close();
			}
			
			int responseCode = conn.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			} else {  // 에러 발생
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			}
				
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			
			Gson gson = new GsonBuilder().registerTypeAdapter(Map.class, new MapDeserializer()).setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			result = gson.fromJson(response.toString(), new TypeToken<Map<String, Object>>(){}.getType());
		} catch(Exception e) {
			result.put("RetCode", "fail");
			result.put("RetMsg", e.getMessage());
			result.put("Recordset", "[]");
			result.put("RecordTotalCount", 0);
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.disconnect();
		}
		
		return result;
	}
	
	public static Map<String, Object> get(String targetUrl, Map<String, Object> params, Map<String, String> headers) throws Exception {
		return httpUrlConnection(ApiHttpUtil.GET, targetUrl, params, headers);
	}
	
	public static Map<String, Object> post(String targetUrl, Map<String, Object> params, Map<String, String> headers) throws Exception {
		return httpUrlConnection(ApiHttpUtil.POST, targetUrl, params, headers);
	}
	
	public static Map<String, Object> put(String targetUrl, Map<String, Object> params, Map<String, String> headers) throws Exception {
		return httpUrlConnection(ApiHttpUtil.PUT, targetUrl, params, headers);
	}
	
	public static Map<String, Object> delete(String targetUrl, Map<String, Object> params, Map<String, String> headers) throws Exception {
		return httpUrlConnection(ApiHttpUtil.DELETE, targetUrl, params, headers);
	}
	
	
	public static Map<String, Object> get(String targetUrl, Map<String, Object> params) throws Exception {
		return httpUrlConnection(ApiHttpUtil.GET, targetUrl, params, dtryxHeaders());
	}
	
	public static Map<String, Object> post(String targetUrl, Map<String, Object> params) throws Exception {
		return httpUrlConnection(ApiHttpUtil.POST, targetUrl, params, dtryxHeaders());
	}
	
	public static Map<String, Object> put(String targetUrl, Map<String, Object> params) throws Exception {
		return httpUrlConnection(ApiHttpUtil.PUT, targetUrl, params, dtryxHeaders());
	}
	
	public static Map<String, Object> delete(String targetUrl, Map<String, Object> params) throws Exception {
		return httpUrlConnection(ApiHttpUtil.DELETE, targetUrl, params, dtryxHeaders());
	}
}