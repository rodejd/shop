<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%----------------------------------------------------------------------------------------------
* 파일명 : default_popup_template.jsp
* 생성일 : 2017. 11. 24
* 작성자 : 이의창
* 설   명 : 판매사 팝업 템플릿
* ==============================================
* 변경이력:
* DATE				AUTHOR			DESCRIPTION
* ---------------------------------------------------------------------------
* 20171124			이의창				최초작성
 ----------------------------------------------------------------------------------------------%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%   
	response.setHeader("Cache-Control","no-store");   
	response.setHeader("Pragma","no-cache");   
	response.setDateHeader("Expires", 0);
	
	if(request.getProtocol().equals("HTTP/1.1")){
		response.setHeader("Cache-Control", "no-cache"); 
	} 
%>   

<html>
	<head>
		<title>판매사 팝업 모드</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Expires" content="-1"> 
		<meta http-equiv="Pragma" content="no-cache"> 
		<meta http-equiv="Cache-Control" content="No-Cache"> 
		
		<link rel="styleSheet" href="/resources/shop/admin/style.css">
		
		<script type="text/javascript" src="/resources/js/jquery/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="/resources/js/jquery/jquery.ajax.js"></script>
		
		<script src="/resources/shop/admin/common.js"></script>
		<script language="javascript">
			if(window.addEventListener) {
				window.addEventListener('load',linecss,false); 
			} else {
				window.attachEvent('onload',linecss); 
			}
		</script>
	</head>
	<body class="scroll">
		<div id="dynamic"></div>
		<div id="jsmotion"></div>
		
		<tiles:insertAttribute name="body" />
	</body>

</html>