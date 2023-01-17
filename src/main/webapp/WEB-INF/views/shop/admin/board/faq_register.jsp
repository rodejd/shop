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
<form id="faqForm" method=post action="indb" onsubmit="return chkForm(this)">
<input type=hidden name=mode value="${faqVO.mode }">
<input type=hidden name=sno value="${faqVO.sno}">
<input type=hidden name=returnUrl value="${faqVO.returnUrl}">

<div class="title title_top">FAQ 질문<span></span></div>
<table class=tb>
<col class=cellC><col class=cellL>

<tr>
	<td nowrap>분류 설정</td>
	<td nowrap>
		<SELECT NAME="itemcd" required label="분류">
			<option value="">↓ FAQ분류을 선택하세요.</option>
			${webUtil:makeSelectCodeItem((codeUtil:codeitem('faq')), faqVO.faqObj.itemcd) }
		</SELECT>
		<SELECT NAME="skin" required label="분류">
			<option value="">↓ 노출스킨을 선택하세요.</option>
			${webUtil:makeSelectCodeItem((codeUtil:codeitem('skin')), faqVO.faqObj.skin) }
		</SELECT>
	</td>
</tr>
<TR>
	<td nowrap>질문 (단문)</td>
	<td nowrap><input type="text" id="question"name="question" size="55" value="${faqVO.faqObj.question}" required label="질문" class=line></td>
</tr>
<TR>
	<td nowrap>질문 (장문)</td>
	<td nowrap>

	<textarea type="editor" NAME="descant" id="descant" ROWS="10" COLS="100" style="width:100%;" class=tline >${fn:replace(faqVO.faqObj.descant, '<br>', enter) }</textarea>
	
	</td>
</tr>
<tr>
	<td nowrap>베스트 등록</td>
	<td nowrap><span class=noline>
	<input name="best" type="radio" value="Y" ${stringUtil:checked(faqVO.faqObj.best, 'Y')} > 추가
	<input name="best" type="radio" value="N" ${stringUtil:checked(faqVO.faqObj.best, 'N')} > 추가안함&nbsp;&nbsp;&nbsp;&nbsp;</span>
	순서 : <input name="bestsort" type="text" size="5" value="${faqVO.faqObj.bestsort}" style="width:30;text-align:center" onkeydown="onlynumber();" class=line>
	<font class=extext>베스트 순서를 수정합니다</font>
	</td>
</tr>
</table>

<div class="title">FAQ 답변<span></span></div>
<table class=tb>
<col class=cellC><col class=cellL>
<TR>
	<td nowrap>답변</td>
	<td nowrap>
	<%-- <textarea type="editor" NAME="answer" id="answer" ROWS="18" COLS="100" style="width:100%;" required label="답변" class=tline>${stringUtil:replaceAll(faqVO.faqObj.answer, '<br>', '\\n')}</textarea> --%>
	<textarea type="editor" NAME="answer" id="answer" ROWS="10" COLS="100" style="width:100%;" class=tline >${fn:replace(faqVO.faqObj.answer, '<br>', enter) }</textarea>
	<script src="/resources/shop/lib/meditor/mini_editor.js"></script>
	<script>mini_editor("/resources/shop/lib/meditor/");</script>
	</td>
	
</tr>
</table>

<div class=button>
<input type=image src="/resources/shop/admin/img/btn_${faqVO.mode eq 'modify' ? faqVO.mode : 'register' }.gif">
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
			var que = $('#question').val();
			var des = $('#descant').val();
			var ans = $('#answer').val();
			console.log(que.length);
			if(que.length >= 150){
				alert("질문(단문)의 총 글자 수를 초과했습니다. 150자 내외로 입력해주세요");
				event.preventDefault;
			}else{
				alert("작성 완료되었습니다.");
			} 
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