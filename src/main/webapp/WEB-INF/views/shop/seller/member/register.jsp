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

<script src="/resources/shop/admin/prototype.js"></script>


<div class="title title_top"><font  face=굴림 color=black><b>SMS</b></font> 예제등록</div>

<form method=post action="sample_indb" onsubmit="return chkForm(this)">
<input type=hidden name=mode value="${smsSendVO.mode}">
<c:if test="${ !empty smsSendVO.smsSampleObj.sno }">
<input type=hidden name=sno value="${smsSendVO.smsSampleObj.sno}">
</c:if>

<table class=tb>
<col class=cellC><col class=cellL>
<tr>
	<td>분류</td>
	<td>
	<select name=category required>
	<option value="">== 선택해주세요 ==
	<c:forEach items="${codeUtil:codeitem3()}" var="codeList" varStatus="st">
		<option value="${codeList.itemnm}" ${codeList.itemnm ==  smsSendVO.smsSampleObj.category ? "selected" : "" }>${codeList.itemnm}</option>
	</c:forEach>
	</select>
	</td>
</tr>
<tr>
	<td>제목</td>
	<td><input type=text name=subject maxlength="20" value="${smsSendVO.smsSampleObj.subject}<%-- <%=bEditFlag?rtItem.get("subject"):""%> --%>" required class="line"></td>
</tr>
<tr>
	<td>메세지</td>
	<td>
	
	<table cellpadding=0 cellspacing=0>
	<tr><td><img src="/resources/shop/admin/img/sms_top.gif"></td></tr>
	<tr>
		<td background="/resources/shop/admin/img/sms_bg.gif" align=center height="81">
			<textarea name=msg maxlength="60" cols=16 rows=5 style="font:9pt 굴림체;overflow:hidden;border:0;background-color:transparent;" required onkeydown="return chkLength(this)" msgR="메세지를 입력해주세요.">${smsSendVO.smsSampleObj.msg}</textarea>
		</td>
	</tr>
	<tr><td><img src="/resources/shop/admin/img/sms_bottom.gif"></td></tr>
	</table>
	
	</td>
</tr>
</table>

<div class="button_popup">
<input type=image src="/resources/shop/admin/img/btn_regist.gif">
<a href="javascript:parent.closeLayer()"><img src="/resources/shop/admin/img/btn_cancel_s.gif"></a>
</div>

</form>

<script>table_design_load();</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<%-- ================================================================================
* 공통 HTML CONTENT 종료
================================================================================ --%>
<script language="javascript">
if( '${result}' == 1 ){
	parent.location.reload();
	parent.closeLayer();
}
</script>