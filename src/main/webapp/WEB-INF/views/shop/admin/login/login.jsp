<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<html>
	<head>
		<title>로그인 - 관리자모드</title>
	
		<script type="text/javascript" src="/resources/js/jquery/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="/resources/js/jquery/jquery.ajax.js"></script>
		<script>
			function goID() {
				ajaxCallJsonPost("/shop/admin/login/login_check.dojson", "frm1", function(result){
					location.href= ctx+ "/shop/admin/basic/index";
				});
			}
		</script>
		<link rel="styleSheet" href="/resources/shop/admin/style.css">
	</head>

	<body style="margin:0;" onload="document.getElementsByName('m_id')[0].focus()" bgcolor="#edefed">
		<form name="frm1" id="frm1">
			<input type="hidden" name="returnUrl" value="../admin/index.jsp">
			<div class="login_wrap">
				<div class="logo_box"><img src="/resources/shop/data/skin/kr/images/logo.png/" width="400"/></div>
				<div class="login_box">
					<input type="text" name="m_id" required label="아이디" onkeyup="fnKeyup(event)" value="" placeholder="아이디">
					<input type="password" name="password" required label="비밀번호" value="" onkeyup="fnKeyup(event)" value="admin" placeholder="비밀번호">
					<input type="button" id="loginBtn" onclick="javascript:goID(); return false;" border="0" value="로그인">
				</div>
				<div class="copy_box">COPYRIGHTⓒ 2022 WEPINIT. ALL RIGHTS RESERVED.</div>
			</div>
			<script>
				function fnKeyup(e) {
					var keyCode = e.keyCode;
					if (keyCode == 13) {
						$("#loginBtn").trigger("click");
					}
				}
			</script>
		</form>
	</body>
</html>