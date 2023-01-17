<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<html>
<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<html>
<head>
<title>XMaLL 관리자모드</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="styleSheet" href="/resources/shop/admin/style.css">
<script src="/resources/shop/admin/common.js"></script>
<script src="/resources/shop/admin/prototype.js"></script>
<?=$scriptLoad?>

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
<div id="jsmotion"></div>

<body class="scroll" >
<form name="form" method="post" action="indb" onsubmit="return chkForm(this);">
<input type="hidden" name="mode" value="${codeVO.mode}" />
<input type="hidden" name="sno" value="${codeVO.sno}" />
<input type="hidden" name="groupcd" value="${codeVO.groupcd}" />
<%-- <input type="hidden" name="returnUrl" value="${codeVO.returnUrl}" /> --%>

<div class="title title_top">코드 ${ "modify" == codeVO.mode ? "수정" : "등록" }<%-- <%=("modify".equals(mode)) ? "수정" : "등록" %> --%><span></span></div>

<table class="tb">
<col class="cellC"><col class="cellL">
<tr height="26">
	<td>분류</td>
	<td><c:if test="${ !empty codeVO.groupcd }">${codeUtil:getCodeName("", codeVO.groupcd)}</c:if></td>
</tr>
<tr>
	<td>코드번호</td>
	<td><input type="text" name="itemcd" size="5"  maxlength="2" value="${codeVO.itemcd}" onKeyDown="onlynumber();"> 2자리</td>
</tr>
<tr>
	<td>코드명</td>
	<td><input type="text" name="itemnm" size="60"  maxlength="30" value="${codeVO.itemnm}"> 30자리</td>
</tr>
</table>

<div class="button_popup">
<input type="image" src="/resources/shop/admin/img/btn_confirm_s.gif" />
<a href="javascript:parent.closeLayer()"><img src="/resources/shop/admin/img/btn_cancel_s.gif" /></a>
</div>

</form>

<script>table_design_load();</script>

</body>
<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

	</td>
</tr>
</table>
<%-- ================================================================================
* 공통 HTML CONTENT 종료
================================================================================ --%>
<script language="javascript">
if( ${result} == 1 ){
	parent.location.reload();
	parent.closeLayer();
}
</script>