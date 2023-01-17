package com.wepinit.wepinit_shop.xcube.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUtil {
	
	private static int connectionTimeOut = 10;	// 10초
	private static int readTimeOut = 10; // 10초
	private static int DEFAULT_TIME_OUT = 3000; // 10초

	/**
	 * http 통신
	 * 	- sample은 맨아래 main 참고
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Object executeHttp(Object obj) throws Exception {
		return executeHttp(obj, null);
	}
	public static Object executeHttp(Object obj, Object returnObj) throws Exception {
		
		HashMap returnMap = new HashMap();
		JSONObject requestJsonObj = null;
		
		// 전송할 데이터가 HashMap, Map로 되어 있을 경우 JSONObject로 변환한다.
		if ( obj instanceof HashMap || obj instanceof Map) {
			requestJsonObj = ConvertUtil.hashMapToJsonObj((HashMap) obj);
		}else{
			// 전송할 데이터가 VO로 되어 있을 경우 JSONObject로 변환한다.
			requestJsonObj = ConvertUtil.voToJsonObj(obj);
		}

		// 반환 받을 데이터의 type을 셋팅하다.
		String returnType = "HASHMAP";
		if ( null != requestJsonObj.get("RETURN_TYPE")
				&& !"HASHMAP".equals(requestJsonObj.get("RETURN_TYPE")) ) {
			returnType = String.valueOf(requestJsonObj.get("RETURN_TYPE"));
		}
		requestJsonObj.remove("RETURN_TYPE");
		
		
		JSONObject returnJsonObj = HttpUtil.getHttpResponseData(requestJsonObj);
		
		// return type 따라 데이터를 변환하여 return 한다.
		if ( "JSON".equals(returnType) ) {
			return returnJsonObj;
		}else if ( "Map".equals(returnType) ) {
			return (Map)ConvertUtil.jsonObjToHashMap(returnJsonObj);
		}else{
			return ConvertUtil.jsonObjToHashMap(returnJsonObj);
		}
	}
	
	public static JSONObject getHttpResponseData(JSONObject requestJsonObj) throws Exception {
		
		String responseJsonStr = "";
		OutputStream os = null;
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		JSONObject returnJsonObj = null;
				
		// connection Url 체크
		String connectionUrl = "";
		if ( null != requestJsonObj.get("CONNECTION_URL") ) {
			connectionUrl = String.valueOf(requestJsonObj.get("CONNECTION_URL"));
			if ( "".equals(connectionUrl) ) {
				return null;
			}
		}else{
			return null;
		}
		requestJsonObj.remove("CONNECTION_URL");
		
		// http or https
		String sslType = "HTTP";
		if ( null != requestJsonObj.get("SSL_TYPE")
				&& "HTTPS".equals(requestJsonObj.get("SSL_TYPE")) ) {
			sslType = "HTTPS";
		}
		requestJsonObj.remove("SSL_TYPE");
		
		// set Method type
		String methodType = "POST";
		if ( null != requestJsonObj.get("METHOD_TYPE")
				&& "GET".equals(requestJsonObj.get("METHOD_TYPE")) ) {
			methodType = "GET";
		}
		requestJsonObj.remove("METHOD_TYPE");
		
		// get방식일 경우 url에 parameter를 붙여 url을 셋팅한다.
		if ( "GET".equals(methodType) ) {
			connectionUrl = connectionUrl + HttpUtil.getGETTypeParams(ConvertUtil.jsonObjToHashMap(requestJsonObj));
		}
		
		////////////////////////////////////////////////////////////////////////
		URL url = new URL(connectionUrl);
		// set connection
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		//if ( "HTTPS".equals(sslType) ) {
		//HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
		//}
		
		// connection setting
		conn.setRequestMethod(methodType);	// 요청방식
		conn.setDoOutput(true);				// OutputStream으로 POST데이터를 넘겨주겠다는 옵션
		conn.setDoInput(true);				// InputStream으로 서버로부터 응답을 받겠다는 옵션
		conn.setUseCaches(false);
		conn.setReadTimeout(readTimeOut * 1000);
		conn.setConnectTimeout(connectionTimeOut * 1000);
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlcode, charset=utf-8");
		// POST일 때만 사용
		if ( "POST".equals(methodType) ) {
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(dos, "utf-8"));
			bw.write(requestJsonObj.toString());
			bw.close();
			dos.close();
		}
		
		int responseCode = conn.getResponseCode();
		if ( responseCode == HttpURLConnection.HTTP_OK) {
			is = conn.getInputStream();
			baos = new ByteArrayOutputStream();
			byte[] byteBuffer = new byte[1024];
			byte[] byteData = null;
			int length = 0;
			while ( (length = is.read(byteBuffer, 0, byteBuffer.length)) != -1 ) {
				baos.write(byteBuffer, 0, length);
			}
			byteData = baos.toByteArray();
			responseJsonStr = new String(byteData, "UTF-8");
			System.out.println("======================================================");
			System.out.println(responseJsonStr);
			System.out.println("======================================================");
			JSONParser parser = new JSONParser();
			returnJsonObj = (JSONObject)parser.parse(responseJsonStr);
			
		}
		
		return returnJsonObj;
	}
	
	/**
	 * 맵의 property 를 '?key=value' 형태의 string 으로 변환하여 반환합니다.
	 * @param 
	 * @return String
	 * */
	private static String getGETTypeParams(HashMap<String, String> paramsMap) {
		String params = "?";
		for(String key : paramsMap.keySet()) {
			params += key + "=" + paramsMap.get(key) + "&";
		}
		
		return params;
	}
	
	/**
	 * http 통신  - 이니시스 거래 용도로 추가
	 * 
	 * @throws Exception
	 */
	public static String sendRequest(String url) throws Exception {
		return sendRequest("get", url, null, null, "UTF-8", DEFAULT_TIME_OUT);
	}
	public static String sendRequest(String url, Map<String, Object> params) throws Exception {
		return sendRequest("post", url, null, params, "UTF-8", DEFAULT_TIME_OUT);
	}
	public static String sendRequest(String method, String url, Map<String, Object> params) throws Exception {
		return sendRequest(method, url, null, params, "UTF-8", DEFAULT_TIME_OUT);
	}
	public static String sendRequest(String method, String url, Map<String, Object> headers, Map<String, Object> params,
			String encoding, int timeout) throws Exception {
		String result = "";
		CloseableHttpClient client = HttpClients.createDefault();
		Builder builder = RequestConfig.custom();
		builder.setConnectTimeout(timeout);
		builder.setSocketTimeout(timeout);
		builder.setStaleConnectionCheckEnabled(false);
		RequestConfig config = builder.build();

		try {
			List<NameValuePair> paramList = convertParam(params);
			method = method.toLowerCase();

			if (method.equals("get")) {
				String param = params != null ? "?" + URLEncodedUtils.format(paramList, encoding) : "";
				HttpGet get = new HttpGet(url + param);
				get.setConfig(config);
				get.setHeader("Connection", "close");

				if (headers != null) {
					for (Object okey : headers.keySet()) {
						String key = String.valueOf(okey);
						get.setHeader(key, headers.get(key).toString());
					}
				}

				ResponseHandler<String> rh = new BasicResponseHandler();

				try {
					result = client.execute(get, rh);
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					get.releaseConnection();
				}
			} else if (method.equals("post")) {
				HttpPost post = new HttpPost(url);
				post.setConfig(config);
				post.setHeader("Connection", "close");

				if (headers != null) {
					for (Object okey : headers.keySet()) {
						String key = String.valueOf(okey);
						post.setHeader(key, headers.get(key).toString());
					}
				}

				post.setEntity(new UrlEncodedFormEntity(paramList, encoding));
				ResponseHandler<String> rh = new BasicResponseHandler();

				try {
					result = client.execute(post, rh);
				} catch (Exception e2) {
					e2.printStackTrace();
				} finally {
					post.releaseConnection();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		return result;
	}

	private static List<NameValuePair> convertParam(Map<String, Object> params) {
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();

		if (params != null) {
			for (Object okey : params.keySet()) {
				String key = String.valueOf(okey);
				paramList.add(new BasicNameValuePair(key, params.get(key).toString()));
			}
		}

		return paramList;
	}

//	public static void main(String[] args) throws Exception{
//		HttpUtil hu = new HttpUtil();
//		Map map = new HashMap();
//		map.put("CONNECTION_URL", "http://info.sweettracker.co.kr/api/v1/trackingInfo");	// URL
//		map.put("SSL_TYPE", "");		// HTTP or HTTPS, default HTTP 
//		map.put("METHOD_TYPE", "GET");	// GET or POST, default PSOT
//		map.put("RETURN_TYPE", "Map");	// return data type
//		
//		map.put("t_invoice", "611877003214");
//		map.put("t_code", "4");
//		map.put("t_key", "qJYagLfeQsGLoKsCh56hag");
//		
//		hu.executeHttp(map);
//	}
}
