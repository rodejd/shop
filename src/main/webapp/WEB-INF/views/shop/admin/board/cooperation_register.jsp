<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<div id=goods_form>

<form method=post action="indb" enctype="multipart/form-data" onsubmit="return chkForm(this)">
<input type=hidden name=mode value="${cooperVO.mode}">
<input type=hidden name=sno value="${cooperVO.sno}">
<input type=hidden name=returnUrl value="${cooperVO.returnUrl}">

<div class="title title_top">문의<span></span></div>
<table class=tb>
<col class=cellC><col class=cellL>

<tr>
	<td nowrap>문의분야 설정</td>
	<td nowrap>
	<SELECT NAME="itemcd" required label="문의분야">
	<option value="">↓ 문의분야를 선택하세요.</option>
	
	${webUtil:makeSelectCodeItem((codeUtil:codeitem('cooperation')), cooperVO.cooperObj.itemcd) }
	</SELECT>
	</td>
</tr>
<TR>
	<td nowrap>접수일</td>
	<td nowrap><font class=ver8><fmt:formatDate value="${cooperVO.cooperObj.regdt}" pattern="yyyy-MM-dd hh:mm:ss"/></font></td>
</tr>
<TR>
	<td nowrap>고객명</td>
	<td nowrap><input name="name" type="text" size="20"  value="${cooperVO.cooperObj.name}" required label="고객명" class=line></td>
</tr>
<TR>
	<td nowrap>E-mail</td>
	<td nowrap>
	<input name="email" type="text" size="50"  value="${cooperVO.cooperObj.email}" class=line>
	<a href="mailto:honhoya@nate.com"><font class=ver8>${cooperVO.cooperObj.email}</a>
	</td>
</tr>
<TR>
	<td nowrap>문의제목</td>
	<td nowrap>${cooperVO.cooperObj.title}</td>
</tr>
<TR>
	<td nowrap>문의내용</td>
	<td>${webUtil:setLineChange(cooperVO.cooperObj.content)}<%-- <%=WebUtil.setLineChange(content)  %> --%></td>
</tr>
</table>

<div class="title">답변<span></span></div>
<table class=tb>
<col class=cellC><col class=cellL>

<tr>
	<td nowrap>답변내용</td>
	<td nowrap><textarea name="reply" cols="100" rows="12" style="width:90%;" class=tline>${cooperVO.cooperObj.reply}</textarea></td>
</tr>
<TR>
	<td nowrap>답변일</td>
	<td nowrap><input name="replydt" type="text" size="20" value="<fmt:formatDate value="${cooperVO.cooperObj.replydt}" pattern="yyyyMMdd"/>" onclick="calendar(event)" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" class=line></td>
</tr>

</table>

<div class=button>
<input type=image src="/resources/shop/admin/img/btn_${'modify' eq cooperVO.mode ? cooperVO.mode : 'register'}.gif">
<a href="javascript:history.back()"><img src='/resources/shop/admin/img/btn_list.gif'></a>
</div>

</form>
</div>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>
