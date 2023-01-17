<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script> 
	if ('${easy}' != '') {
		window.opener.alert("${fn:toUpperCase(easy)} 간편 로그인이 설정 되었습니다.");
	} else if ('${login}' == 'true') {
		if ('${err}' != '') {
			window.opener.alert("${err}");
			window.opener.location.href = "login";
		} else {
			window.opener.location.href = ctx+"/shop/main/index";
		}
	} else {
		//window.opener.location.href = "joinSns";
		window.opener.alert("일치하는 회원정보가 없습니다.\n회원가입 후 이용해 주세요.");
	}
	window.close();
</script>
</body>
</html>