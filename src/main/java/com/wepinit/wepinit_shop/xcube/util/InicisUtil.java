package com.wepinit.wepinit_shop.xcube.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class InicisUtil {
	
	public static void main(String[] args) {
		inicisAuthCancel("StdpayCARDINIpayTest20210405110915449049", "Refund", "0", "0");
	}

	/**
	 * 이니시스 결제 취소 요청 (카드)
	 * 
	 * 테스트용 KG이니시스 결제데모 URL : https://manual.inicis.com/demo/stdpay/
	 * 해당 주소로 들어가 결제 후  tid를 파라미터로 세팅하고 보내면 정상적으로 취소처리가 됩니다.
	 * 
	 * sample 	전체취소 : inicisAuthCancel("StdpayCARDINIpayTest123456", "Refund", "",""); 
	 * 			부분취소 : inicisAuthCancel("StdpayCARDINIpayTest123456", "PartialRefund","500", "500");
	 * 
	 * flag : Refund > 전체취소 
	 * flag : PartialRefund > 부분취소 : 여러 차례 부분취소하는 경우에도 부분취소 요청
	 * 시 원거래TID 로 요청합니다. 원 주문 금액을 넘거나 부분취소 후 잔액이상으로 요청시 에러 발생, 부분취소는 당일 취소 불가 이니시스
	 * 메뉴얼 공식 -> (PRICE (취소금액) + CONFIRM_PRICE (남은 금액) = 원 거래금액)
	 * 
	 * 1. tid(거래번호)는 이니시스 결제 후 오는 응답 값(tranNum <- tid) 
	 * 2. sample처럼 호출하면 정상응답인 경우 [sucssYn=Y]가 리턴됨 그 이외 실패시 N 
	 * 3. 성공여부는 sucssYn이 Y면 가맹점에서 별도로 필요한 DB처리 
	 * 4. sucssYn 이외에 주는 데이터는 이니시스에서 주는 응답 값 
	 * 5. 에러코드나 메세지 같은 경우를 처리하려면 리턴 값 확인 후 별도로 구현
	 * 6. 정상적으로 동작하지 않는 경우 step1 가맹점에 관한 세팅은 환경에 맞춰서 별도 세팅 필요
	 * 
	 * @param tranNum, flag
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> inicisAuthCancel(String tranNum, String flag, String reqAmount, String remaAmount) {
		Map<String, Object> CashReceiptsMap = new HashMap<String, Object>(); // 요청 값 세팅을 위한 맵
		Map<String, Object> resultMp = new HashMap<String, Object>(); // 결과 값 반환을 위한 맵
		Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다//년월일시분초 14자리 포멧
		SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyyMMddHHmmss");
		// 현재 세팅된 값은 테스트 세팅 값이며 가맹점별로 맞게 세팅을 해주셔야합니다.
		// step1. 요청을 위한 파라미터 설정
		String Key = "ItEQKi3rY7uvDS8l"; // INIpayTest의 테스트 key
		String type = flag; // 승인취소, 부분승인취소 구분
		String paymethod = "Card";
		String timestamp = fourteen_format.format(date_now);
		String clientIp = "127.0.0.1"; // 가맹점 ip
		String mid = "INIpayTest";
		String tid = tranNum; // 40byte 승인 TID 입력
		String msg = "거래취소요청";
		String data_hash = Key + type + paymethod + timestamp + clientIp + mid + tid;

		// step2. 승인부분취소를 위한 추가적인 값 세팅
		if ("PartialRefund".equals(type)) {
			String price = reqAmount; // 취소요청금액
			String confirmPrice = remaAmount; // 부분취소 후 남은금액
			data_hash = Key + type + paymethod + timestamp + clientIp + mid + tid + price + confirmPrice;
			CashReceiptsMap.put("price", price);
			CashReceiptsMap.put("confirmPrice", confirmPrice);
		}

		// 승인취소를 위한 기본 세팅 값
		CashReceiptsMap.put("type", type);
		CashReceiptsMap.put("paymethod", paymethod);
		CashReceiptsMap.put("timestamp", timestamp);
		CashReceiptsMap.put("clientIp", clientIp);
		CashReceiptsMap.put("mid", mid);
		CashReceiptsMap.put("tid", tid);
		CashReceiptsMap.put("msg", msg);
		CashReceiptsMap.put("hashData", CryptoUtil.getSHA256(data_hash)); // SHA_512_Util 을 이용하여 hash암호화

		// step3. 취소 거래를 보내는 부분
		try {
			String authResultString = "";
			authResultString = HttpUtil.sendRequest("https://iniapi.inicis.com/api/v1/refund", CashReceiptsMap); // str,
																													// map
			if (null != authResultString) {
				// JsonString을 Map으로
				Map<String, Object> mp = ConvertUtil.convertJSONstringToMap(authResultString);
				resultMp.put("sucssYn", ("00".equals(mp.get("resultCode"))) ? "Y" : "N");
				resultMp.putAll(mp);
				System.out.println(resultMp);
			}
		} catch (Exception e) {
			System.out.println("####### 승인취소거래 처리시 오류가 발생했습니다 #######\n" + "에러메세지 : " + e
					+ "\n#######################################");
		}

		return resultMp;
	}

}
