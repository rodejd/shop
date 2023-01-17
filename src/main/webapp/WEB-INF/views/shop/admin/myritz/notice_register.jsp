<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<html>
<head>
<title>'Xmall 관리자 모드'</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="styleSheet" href="/shop/css/style.css">
<style>
/*** 어드민 레이아웃 설정 ***/
body {margin:0 0 0 0px}
</style>

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
<form id="faqForm" method=post action="indb" onsubmit="return chkForm(this)" enctype="multipart/form-data" >
<input type=hidden name=mode value="${noticeVO.mode }">
<input type=hidden name=no value="${noticeVO.no}">
<input type=hidden name=returnUrl value="${noticeVO.returnUrl}">

<div class="title title_top">공지사항<span></span></div>
<table class=tb>
<col class=cellC><col class=cellL>

<tr>
	<td nowrap>제목</td>
	<td nowrap>
	<input type="text" id="subject"name="subject" size="55" value="${noticeVO.noticeObj.subject}" required label="질문" class=line>
	</td>
</tr>
<tr>
	<td nowrap>작성자</td>
	<td nowrap><input type="text" id="name"name="name" size="55" value="${noticeVO.noticeObj.name}" required label="질문" class=line></td>
</tr>
<tr>
	<td nowrap>공개 여부</td>
	<td nowrap><span class=noline>
	<input name="secret" type="radio" value="Y" ${stringUtil:checked(noticeVO.noticeObj.secret, 'Y')} > 공개
	<input name="secret" type="radio" value="N" ${stringUtil:checked(noticeVO.noticeObj.secret, 'N')} > 비공개</span>
	</td>
</tr>
<tr>
	<td nowrap>파일</td>
	<td nowrap>
		<input type="file" id="attach" class="form-control" name="attach" title="파일" value=""/>
		<c:if test="${not empty noticeVO.noticeObj.newfile}">
			<div style="margin: 5px 0;">
				<a href="${noticeVO.noticeObj.newfile}" target="_blank">${noticeVO.noticeObj.newfile}</a>
			</div>
		</c:if>
	</td>
</tr>
<TR>
	<td nowrap>내용</td>
	<td nowrap>
	<textarea type="editor" NAME="contents" id="contents" ROWS="10" COLS="100" style="width:100%;" class=tline >${fn:replace(noticeVO.noticeObj.contents, '<br>', enter) }</textarea>
	<script src="/resources/shop/lib/meditor/mini_editor.js"></script>
	<script>mini_editor("/resources/shop/lib/meditor/");</script>
	</td>
	
</tr>
</table>

<div class=button>
<input type=image src="/resources/shop/admin/img/btn_${noticeVO.mode eq 'modify' ? noticeVO.mode : 'register' }.gif">
<a href="javascript:history.back()"><img src='/resources/shop/admin/img/btn_list.gif'></a>
</div>

</form>
</div>

			</td>
		</tr>
		</table>
		
		</tr>
		</table>
		
		</td>
	</tr>

	</table>
	
	</td>
</tr>
<tr>

</tr>
</table>

<script>
//스크립트 에러 무슨 스크립트인지 확인 요함
linecss();
table_design_load();
</script>
<script>
	$(function(){
		$('#faqForm').on('submit', function(){
			alert("작성 완료되었습니다.");
		});
	});
	
</script>



<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>