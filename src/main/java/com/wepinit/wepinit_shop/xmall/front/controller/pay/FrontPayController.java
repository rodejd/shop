package com.wepinit.wepinit_shop.xmall.front.controller.pay;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.wepinit.wepinit_shop.common.ConfigClass;
import com.wepinit.wepinit_shop.xmall.front.service.order.FrontOrderService;
import com.wepinit.wepinit_shop.xmall.front.vo.pay.FrontPayVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Map;

@Controller
@RequestMapping("/shop/pay/")
public class FrontPayController {
	//AccessToken
	private final String authorization = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJxRlItM0w0SGo2RzNWbmNEYmo0alp6YmNhV2lMNmRtNmlYYUNtck9IQ2RRIn0.eyJleHAiOjE5NTAwNTM5MTMsImlhdCI6MTYzNDY5NDQ0MywiYXV0aF90aW1lIjoxNjM0Njk0NDQzLCJqdGkiOiI4M2IwNDJhMi04MDQzLTQ1YWQtYjkzNy0wYWZhNjVjZjU3NDUiLCJpc3MiOiJodHRwczovL3Nzby5heGVwdGEuaXQvYXV0aC9yZWFsbXMvTWVyY2hhbnQiLCJhdWQiOlsicGctcGF5bWVudC1hcGktaW5ldCIsInBnLXBheW1lbnQtYXBpLWluZXQtc2FuZGJveCIsImFjY291bnQiXSwic3ViIjoiY2E3NjNiZTktODJhMS00MWE5LWIxZmUtNDY5ODM5MjRjYjRjIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoicGctcGF5bWVudC1hcGktaW5ldCIsInNlc3Npb25fc3RhdGUiOiI1ZTAwM2RjYi1lMDVlLTQ5OGMtODA4MC05ZWNlYWM3MWE4YjgiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vcGF5LmF4ZXB0YS5pdCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7InBnLXBheW1lbnQtYXBpLWluZXQiOnsicm9sZXMiOlsidXNlciJdfSwicGctcGF5bWVudC1hcGktaW5ldC1zYW5kYm94Ijp7InJvbGVzIjpbInVzZXIiXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIGVtYWlsIG9mZmxpbmVfYWNjZXNzIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJzYW5nIGhvb24gc2hpbiIsInByZWZlcnJlZF91c2VybmFtZSI6ImJobG9mYW1sZWxlY0BnbWFpbC5jb20iLCJnaXZlbl9uYW1lIjoic2FuZyBob29uIiwiZmFtaWx5X25hbWUiOiJzaGluIiwiZW1haWwiOiJiaGxvZmFtbGVsZWNAZ21haWwuY29tIn0.dx0u5e-8gUXzCNuKRY3s9-GweftAfjg1QCLWn-Z3-tjxx4u1cifxXlKsxkS1DI56CVEbqGLAYGG2d7n5fvs17qBIslZB_YM0OgQuw3eUu3z6862HHLm6owqyUXTmUgm0Z080IJQUPOQebwJEVG3hIUtcrcm_pGua9car2ooRBUyPqOi76D0VH5ip8qwHO392GwUQUUTvvkMkMezNgtujIwQftWGjeMjm0_LOGuhLIYCkYJ_VkEMTDAkiS__rGGjL8oYN7qsdAZlWzrGUiymw1ayaVd_r0fDfjrfjEe_onBpdT6VVf01LPtpahlmfoY2om2K5DCClZqvFP4jO-DoKYA";
	//API License Key
	private final String licenseKey = "652G4DC-GQD485R-JRT67M0-H3DVXMT";
	//API URL
	private final String apiServerUrl = "https://pay.axepta.it";
	
	//TEST
//	private final String authorization = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJxRlItM0w0SGo2RzNWbmNEYmo0alp6YmNhV2lMNmRtNmlYYUNtck9IQ2RRIn0.eyJleHAiOjE5NTAwNTM5MTMsImlhdCI6MTYzNDY5NDQ0MywiYXV0aF90aW1lIjoxNjM0Njk0NDQzLCJqdGkiOiI4M2IwNDJhMi04MDQzLTQ1YWQtYjkzNy0wYWZhNjVjZjU3NDUiLCJpc3MiOiJodHRwczovL3Nzby5heGVwdGEuaXQvYXV0aC9yZWFsbXMvTWVyY2hhbnQiLCJhdWQiOlsicGctcGF5bWVudC1hcGktaW5ldCIsInBnLXBheW1lbnQtYXBpLWluZXQtc2FuZGJveCIsImFjY291bnQiXSwic3ViIjoiY2E3NjNiZTktODJhMS00MWE5LWIxZmUtNDY5ODM5MjRjYjRjIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoicGctcGF5bWVudC1hcGktaW5ldCIsInNlc3Npb25fc3RhdGUiOiI1ZTAwM2RjYi1lMDVlLTQ5OGMtODA4MC05ZWNlYWM3MWE4YjgiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vcGF5LmF4ZXB0YS5pdCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7InBnLXBheW1lbnQtYXBpLWluZXQiOnsicm9sZXMiOlsidXNlciJdfSwicGctcGF5bWVudC1hcGktaW5ldC1zYW5kYm94Ijp7InJvbGVzIjpbInVzZXIiXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIGVtYWlsIG9mZmxpbmVfYWNjZXNzIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJzYW5nIGhvb24gc2hpbiIsInByZWZlcnJlZF91c2VybmFtZSI6ImJobG9mYW1sZWxlY0BnbWFpbC5jb20iLCJnaXZlbl9uYW1lIjoic2FuZyBob29uIiwiZmFtaWx5X25hbWUiOiJzaGluIiwiZW1haWwiOiJiaGxvZmFtbGVsZWNAZ21haWwuY29tIn0.dx0u5e-8gUXzCNuKRY3s9-GweftAfjg1QCLWn-Z3-tjxx4u1cifxXlKsxkS1DI56CVEbqGLAYGG2d7n5fvs17qBIslZB_YM0OgQuw3eUu3z6862HHLm6owqyUXTmUgm0Z080IJQUPOQebwJEVG3hIUtcrcm_pGua9car2ooRBUyPqOi76D0VH5ip8qwHO392GwUQUUTvvkMkMezNgtujIwQftWGjeMjm0_LOGuhLIYCkYJ_VkEMTDAkiS__rGGjL8oYN7qsdAZlWzrGUiymw1ayaVd_r0fDfjrfjEe_onBpdT6VVf01LPtpahlmfoY2om2K5DCClZqvFP4jO-DoKYA";
//	private final String licenseKey = "SNCEFK5-0D44GCC-MFTRVGC-9WJ9XHN";
//	private final String apiServerUrl = "https://pay-sandbox.axepta.it";
	
	@Resource
	FrontOrderService service;
	
	@RequestMapping(value="initPayment")
	@ResponseBody
	public String initPayment(@ModelAttribute("payVO") FrontPayVO payVO, HttpServletRequest req, HttpServletResponse resp, Model model ,HttpSession session) throws Exception {
		JsonObject jsonObj = new JsonObject();
		try {
			DecimalFormat form = new DecimalFormat("0.00");
			
			JsonObject resultObject = new JsonObject();
			JsonObject jsonObject = new JsonObject();
			JsonArray jsonArray = new JsonArray();
			
			resultObject.addProperty("transaction_type", "PURCHASE"); // PURCHASE 즉시청구, VERIFY: 금액확인을 위해 승인/취소 진행.
			resultObject.addProperty("transaction_timeout", "30000");
//			resultObject.addProperty("shopID", "Ritzmall");
			resultObject.addProperty("currency", "EUR");
			resultObject.addProperty("language", "EN");
			resultObject.addProperty("amount", form.format(payVO.getAmount()));
			
			// notifications
			jsonObject = new JsonObject();
			payVO.setParam(payVO);
			jsonObject.addProperty("name", payVO.getNameOrder());
			jsonObject.addProperty("email", payVO.getEmail());
			jsonObject.addProperty("smartphone", payVO.getMobileReceiver());
			resultObject.add("notifications", jsonObject);
			
			//addresses
			jsonObject = new JsonObject();
			jsonArray = new JsonArray();
			jsonObject.addProperty("type", "SHIPPING");
			jsonObject.addProperty("addresseeName", payVO.getCountry());
			jsonObject.addProperty("streetAddress_1", payVO.getAddress());
			jsonObject.addProperty("streetAddress_2", payVO.getAddress_sub());
			jsonObject.addProperty("zip", payVO.getZipcode());
			jsonObject.addProperty("city", payVO.getCountry());
			jsonObject.addProperty("provinceState", "RM");
			jsonObject.addProperty("country", payVO.getCountry());
			jsonArray.add(jsonObject);
			resultObject.add("addresses", jsonArray);
			
			resultObject.addProperty("addressesURI", "https://www.merchantSite.com/addressEdit");
			
			//products
			jsonArray = new JsonArray();
			for(int i=0; i <  payVO.getPriceArr().length; i++) {
				jsonObject = new JsonObject();
				jsonObject.addProperty("logo", payVO.getGoodsImgArr()[i]);
				jsonObject.addProperty("quantity", payVO.getEaArr()[i]);
				jsonObject.addProperty("description", payVO.getGoodsNmArr()[i]);
				jsonObject.addProperty("price", form.format(payVO.getPriceArr()[i]));
				jsonArray.add(jsonObject);
			}
			resultObject.add("products", jsonArray);
			
			resultObject.addProperty("redirect_successUrl", ConfigClass.value("SHOP_URL") + "shop/pay/popup_payment_success_redirect");
			resultObject.addProperty("redirect_failureUrl", ConfigClass.value("SHOP_URL") + "shop/pay/popup_payment_failure_redirect");
			resultObject.addProperty("callback_url", ConfigClass.value("SHOP_URL") + "shop/pay/popup_payment_callback");
			
			//additionals
			jsonObject = new JsonObject();
			jsonArray = new JsonArray();
			jsonObject.addProperty("key", "ordno");
			jsonObject.addProperty("value", payVO.getOrdno());
			jsonArray.add(jsonObject);
			resultObject.add("additionals", jsonArray);
			
			//initPayment API 호출
			HttpResponse<String> res = Unirest.post(apiServerUrl + "/api/v1/payment/initPayment") // API URL
					.header("Authorization", "Bearer " + authorization) // AccessToken
					.header("X-license-key", licenseKey) //API License Key
					.header("Content-Type", "application/json")
					.header("cache-control", "no-cache")
					.body(resultObject.toString())
					.asString();
			
			String data = res.getBody();
			Gson gson = new Gson();
			JsonElement element = gson.fromJson (data, JsonElement.class);
			jsonObj = element.getAsJsonObject();

		}catch (Exception e) {
			e.printStackTrace();
			jsonObj.addProperty("code", "-99");
			jsonObj.addProperty("message", "E99"); // E99
			return jsonObj.toString();
		}
		return jsonObj.toString();
	}
	
	/**
	 * 결제 콜백정보
	 * @param req
	 * @param resp
	 * @param model
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="popup_payment_callback")
	public String popup_payment_callback(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		String retrunVal = "Failure";
		try {
			String body = null;
			StringBuilder stringBuilder = new StringBuilder();
			BufferedReader bufferedReader = null;
	
			try {
				InputStream inputStream = req.getInputStream();
				if (inputStream != null) {
					bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
					char[] charBuffer = new char[2048];
					int bytesRead = -1;
					while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
						stringBuilder.append(charBuffer, 0, bytesRead);
					}
				}
			} catch (IOException ex) {
				throw ex;
			} finally {
				if (bufferedReader != null) {
					try {
						bufferedReader.close();
					} catch (IOException ex) {
						throw ex;
					}
				}
			}
			body = stringBuilder.toString();
			
			// 결제 트렌젝션ID발급 이력 등록
			Gson gson = new Gson(); 
			Map<String, Object> bodyMap = gson.fromJson(body, Map.class);
			String strAddresses = String.valueOf(bodyMap.get("addresses"));
			String strProducts = String.valueOf(bodyMap.get("products"));
			String strNotification = String.valueOf(bodyMap.get("notification"));
			String strAdditionals = String.valueOf(bodyMap.get("additionals"));
			
			// ordno 추출
			JsonElement element = gson.fromJson (strAdditionals, JsonElement.class);
			if(element != null) {
				JsonArray jsonArray = element.getAsJsonArray();
				JsonObject jsonObject = new JsonObject();
				
				if(jsonArray != null && jsonArray.size() > 0) {
					jsonObject = (JsonObject) jsonArray.get(0);
					
					if( jsonObject.get("key") != null && jsonObject.get("value") != null ){
						if( StringUtils.equals("ordno", String.valueOf(jsonObject.get("key").getAsString())) ) {
							bodyMap.put("ordno", jsonObject.get("value").getAsString());
						}
					}
				}
			}
			bodyMap.put("strAddresses", strAddresses);
			bodyMap.put("strProducts", strProducts);
			bodyMap.put("strNotification", strNotification);
			bodyMap.put("strAdditionals", strAdditionals);
			int result = service.insertOrderPayLog(bodyMap);
			
			if(result > 0) {
				retrunVal = "Success";
			}
		}catch (Exception e) {
			System.out.println("payment Callback Error!!");
			e.printStackTrace();
		}
		System.out.println("Callback result : " + retrunVal);
		model.addAttribute("result", retrunVal);
		return "/shop/common/popup_pay_result";
	}
	
	@RequestMapping(value="popup_payment_success_redirect")
	public void payment_success_redirect(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		System.out.println("success_redirect");
	}
	@RequestMapping(value="popup_payment_failure_redirect")
	public void popup_payment_failure_redirect(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		System.out.println("failure_redirect");
	}
}
