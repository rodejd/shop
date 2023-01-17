<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<html>
	<head>
		<title>판매사모드</title>
		<script type="text/javascript" src="/resources/js/jquery/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="/resources/js/jquery/jquery.ajax.js"></script>
		<script>
			function goID() {
				ajaxCallJsonPost("/shop/seller/login_check.dojson", "frm1", function(result) {
					if(result.loginVO.div != "S") {
						location.href= ctx +"/shop/seller/oper/sellerManagement";
					} else {
	 					location.href= ctx + "/shop/seller/basic/default";						
					}
				});
			}
			
			function goLogin() {
				if(window.event.keyCode == "13") {
					goID();
				} 
			}
			
		</script>
		<link rel="styleSheet" href="/resources/shop/admin/style.css">
	</head>

	<body style="margin:0;" onload="document.getElementsByName('m_id')[0].focus()" bgcolor="#edefed">
		<form name="frm1" id="frm1">
			<input type="hidden" name="returnUrl" value="">
			<div class="login_wrap">
				<div class="logo_box">
					MALL IN MALL
					<img src="/resources/shop/admin/img/logo_gate.png" />
					</div>
				<div class="login_box">
					<input type="text" name="m_id" required label="아이디" onFocus="" onBlur="" value="gnushop" placeholder="아이디">
					<input type="password" name="password" required label="비밀번호" value="pmgk1234" placeholder="비밀번호" onkeyup="javascript:goLogin(); return false;">
					<input type="button" onclick="javascript:goID(); return false;" border="0" value="로그인">
				</div>
				<div class="copy_box">Copyright &copy XMaLL Shop. All Rights Reserved.</div>
			</div>
		</form>
	</body>
</html>