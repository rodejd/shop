<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<html>
<head>
<title>'xMall ||| Shoppingmall 관리자모드'</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="styleSheet" href="/resources/shop/admin/style.css">
<script src="/resources/shop/admin/common.js"></script>
<script src="/resources/shop/admin/prototype.js"></script>
<script language="javascript">
if(window.addEventListener) 
{
	window.addEventListener('load',linecss,false); 
}
else 
{
	window.attachEvent('onload',linecss); 
}
</script>
<div id="dynamic"></div>
<div id="jsmotion"></div>

<body class="scroll">

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
	/*
	 * jQuery ready
	 */
	$(function(){
		
	});		


</script>
<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<form name="form" id="form">
<input type="hidden" name="sno" value="${couponVO.sno}">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo != '' ? couponVO.pageNo : '1' }">
</form>


<div class="title title_top">쿠폰발급 회원내역<!-- <span>상세내역을 확인합니다</span> --></div>

<table width="100%" cellpadding="0" cellspacing="0" border="0" >
<tr><td class=rnd colspan=4></td></tr>
<tr class=rndbg>
	<th>번호</th>
	<th>아이디</th>
	<th>이름</th>
	<th>사용 여부</th>
</tr>         
<tr><td class=rnd colspan=4></td></tr>
<col width=50 align=center>
<col width=150 align=center>
<col width=150 align=center>
<col align=left>

 	<c:forEach items="${couponVO.couponApplyMember }" var="list"  varStatus="status">
	
		<tr><td height=4 colspan=5></td></tr>
		<tr height=25 align="center">
			<td><font class=ver81 color=616161>${(couponVO.rowCount - status.index) - ( couponVO.rowStart ) }</font></td>
			<td><font class=ver81 color=616161>${list.mid }</font></td>
			<td><font class=ver81 color=0074BA>${list.name }</font></td>
			<td>${list.used == 0 ? '<font class=ver81 color=333333>미사용</font>' : '<font color="EA0095">사용</font>' }</td>
		</tr>
		<tr><td height=4 colspan=5></td></tr>
		<tr><td colspan=5 class=rndline></td></tr>
	
	</c:forEach>
</table>

<!-- 페이징 -->
 	<td width="60%" align="center"><font class="ver8">
	<tags:paginator currentPageNo="${couponVO.pageNo}" rowCount="${couponVO.rowCount}" pageSize="${couponVO.pageSize}"  pageGroupSize="${couponVO.pageGroupSize}" />
	</font></td>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>
<script>table_design_load();</script>
<%-- ================================================================================
* 공통 HTML CONTENT 종료
================================================================================ --%>

<script type="text/javascript">


function goPage(page){
	document.getElementById("pageNo").value = page ;
	document.getElementById("form").submit();
}

</script>