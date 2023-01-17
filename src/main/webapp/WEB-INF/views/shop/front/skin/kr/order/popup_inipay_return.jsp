<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>


<textarea id="ini_authToken">${iniMap.authToken}</textarea>
<script>
	
	var input1 = document.createElement('input');
	input1.setAttribute("type", "hidden");
	input1.setAttribute("name", "ini_resultCode");
	input1.setAttribute("value", '${iniMap.resultCode}');
	opener.document.getElementById("_payres-form").appendChild(input1);
	
	input1 = document.createElement('input');
	input1.setAttribute("type", "hidden");
	input1.setAttribute("name", "ini_resultMsg");
	input1.setAttribute("value", '${iniMap.resultMsg}');
	opener.document.getElementById("_payres-form").appendChild(input1);
	
	input1 = document.createElement('input');
	input1.setAttribute("type", "hidden");
	input1.setAttribute("name", "ini_returnUrl");
	input1.setAttribute("value", '${iniMap.returnUrl}');
	opener.document.getElementById("_payres-form").appendChild(input1);
	
	input1 = document.createElement('input');
	input1.setAttribute("type", "hidden");
	input1.setAttribute("name", "ini_authUrl");
	input1.setAttribute("value", '${iniMap.authUrl}');
	opener.document.getElementById("_payres-form").appendChild(input1);
	
	input1 = document.createElement('input');
	input1.setAttribute("type", "hidden");
	input1.setAttribute("name", "ini_charset");
	input1.setAttribute("value", '${iniMap.charset}');
	opener.document.getElementById("_payres-form").appendChild(input1);
	
	input1 = document.createElement('input');
	input1.setAttribute("type", "hidden");
	input1.setAttribute("name", "ini_cardnum");
	input1.setAttribute("value", '${iniMap.cardnum}');
	opener.document.getElementById("_payres-form").appendChild(input1);
	
	input1 = document.createElement('input');
	input1.setAttribute("type", "hidden");
	input1.setAttribute("name", "ini_orderNumber");
	input1.setAttribute("value", '${iniMap.orderNumber}');
	opener.document.getElementById("_payres-form").appendChild(input1);
	
	input1 = document.createElement('input');
	input1.setAttribute("type", "textarea");
	input1.setAttribute("name", "ini_authToken");
	input1.setAttribute("value", document.getElementById("ini_authToken").value);
	opener.document.getElementById("_payres-form").appendChild(input1);
	
	input1 = document.createElement('input');
	input1.setAttribute("type", "hidden");
	input1.setAttribute("name", "ini_checkAckUrl");
	input1.setAttribute("value", '${iniMap.checkAckUrl}');
	opener.document.getElementById("_payres-form").appendChild(input1);
	
	input1 = document.createElement('input');
	input1.setAttribute("type", "hidden");
	input1.setAttribute("name", "ini_netCancelUrl");
	input1.setAttribute("value", '${iniMap.netCancelUrl}');
	opener.document.getElementById("_payres-form").appendChild(input1);
	
	input1 = document.createElement('input');
	input1.setAttribute("type", "hidden");
	input1.setAttribute("name", "ini_mid");
	input1.setAttribute("value", '${iniMap.mid}');
	opener.document.getElementById("_payres-form").appendChild(input1);
	
	input1 = document.createElement('input');
	input1.setAttribute("type", "hidden");
	input1.setAttribute("name", "ini_merchantData");
	input1.setAttribute("value", '${iniMap.merchantData}');
	opener.document.getElementById("_payres-form").appendChild(input1);
	
	opener.document.getElementById("_payres-form").action = "${ctx}/shop/order/inipayres";
	opener.document.getElementById("_payres-form").submit();
	
// 	self.close();

	function iniMapSetting(value, key, map){
		input1 = document.createElement('input');
		input1.setAttribute("type", "hidden");
		input1.setAttribute("name", '${key}');
		input1.setAttribute("value", '${value}');
		opener.document.getElementById("_payres-form").appendChild(input1);
	}
</script>
