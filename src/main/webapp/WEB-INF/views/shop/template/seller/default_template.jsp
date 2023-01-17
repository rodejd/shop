<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%----------------------------------------------------------------------------------------------
* 파일명 : default_template.jsp
* 생성일 : 2017. 02. 06
* 작성자 : 이병환
* 설   명 : 관리자 default template
* ==============================================
* 변경이력:
* DATE				AUTHOR			DESCRIPTION
* ---------------------------------------------------------------------------
* 20170206			이병환				최초작성
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
		<title>판매사 모드</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Expires" content="-1"> 
		<meta http-equiv="Pragma" content="no-cache"> 
		<meta http-equiv="Cache-Control" content="No-Cache"> 

		<link rel="styleSheet" href="/resources/shop/admin/style.css">
		
		<script src="/resources/shop/admin/common.js"></script>
		<!-- <script src="../prototype.js"></script> -->
		
		<!-- Jquery Setting-->
		<script type="text/javascript" src="/resources/js/jquery/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="/resources/js/jquery/jquery.ajax.js"></script>
		<!-- //Jquery Setting-->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

		<!-- font awesome -->
		<link href="/resources/shop/css/fontawesome6/css/fontawesome.css" rel="stylesheet">
		<link href="/resources/shop/css/fontawesome6/css/brands.css" rel="stylesheet">
		<link href="/resources/shop/css/fontawesome6/css/solid.css" rel="stylesheet">

		<!-- 추가한 CSS -->
		<link rel="styleSheet" href="/resources/shop/admin/custom.css">
		
	</head>
	
	
	<body class="scroll" style=""><div id="dynamic"></div>
		
		<!-------------------- header 시작 --------------------->
		<tiles:insertAttribute name="header" />
		<!-------------------- header 끝 --------------------->
		
		<!-------------------- 측면, 관리타이틀이미지, 메뉴닫힘 시작 ------------------------------->
		<table cellpadding="0" cellspacing="0" border="0">
			<tbody>
				<tr>
					<td valign="top" id="leftMenu" width="170" background="/resources/shop/admin/img/sub_leftmenu_back.gif">
						<!-------------------- left 시작 --------------------->
						<tiles:insertAttribute name="left" />
						<!-------------------- left 끝 --------------------->
					</td>
					<td valign="top" height="100%" width="100%" style="padding:20px 30px 30px 18px">
						<div class="search_wrap">
							<div class="path_box">
								<img src="/resources/shop/admin/img/top_area_arrow.gif" vspace="2">HOME &gt; <span id="navi1"></span> &gt; <span id="navi2"></span> 
							</div>
							<!--  <div class="search_box"> -->
							<!--  	<label for="crm">회원CRM검색</label> -->
							<!--  	<span><input type="text" name="crm" id="crm" value="" onkeypress="if(event.keyCode==13) topSearchCrm();" /></span><img src="/resources/shop/admin/img/top_search_icon.gif" style="cursor:pointer;" onclick="topSearchCrm();" /> -->
							<!--  </div> -->
						</div>
							<!-------------------- 업무 시작 --------------------->
							<tiles:insertAttribute name="body" />
							<!-------------------- 업무 끝 --------------------->
					</td>
				</tr>
			</tbody>
		</table>
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tbody>
				<tr>
					<td style="padding:20px 10px; border-top:1px solid #ddd;"><font color="#666">Copyright © XMaLL Shop. All Rights Reserved.</font></td>
				</tr>
			</tbody>
		</table>
	</body>
</html>