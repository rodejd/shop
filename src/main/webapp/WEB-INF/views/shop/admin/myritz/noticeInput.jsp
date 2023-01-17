<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language=javascript src="/resources/shop/admin/common.js"></script>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
	/*
	 * jQuery ready
	 */
	$(function(){
		// 등록/수정 클릭
		$('#btn_save').on('click', function(){
			
			// validation check
// 			if (!chkForm($('#form'))) return false;
			
			$('#contents').val($('#body').val());
			
			if ( "" == $('#procType').val() ) {
				$('#procType').val('I');
			}
			if(""== $('#title').val()||undefined == $('#title').val()){
				alert("제목을 입력해주세요")
				return false;
			}else if(""== $('#body').val()){
				alert("내용을 입력해주세요")
				return false;
			}else{
			$('#form')
				.attr('action', ctx+'/shop/admin/myritz/noticeProc')
				.submit();
			}
		});
		
		// 목록 클릭시
		$('#btn_list').on('click', function(){
			$('#form')
				.attr('action', ctx+'/shop/admin/myritz/noticeList')
				.submit();
		});
		
		// 삭제버튼 클릭
		$('#btn_delete').on('click', function(){
			$('#procType').val('D');	// 처리구분(I/M/D)
			$('#form')
				.attr('action', ctx+'/shop/admin/myritz/noticeProc')
				.submit();
		});
		
		// 게시물 고유번호가 없을 경우 삭제버튼을 숨긴다.
		if ( "" != $('#sno').val() ) {
			$('#btn_delete').show();
		}
		
		cssRound('MSG01');
	});
	
</script>

<%-- ================================================================================
* HTML
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<form id="form" name="form" method="get" action="${ctx}/shop/admin/myritz/noticeInput">
<input type=hidden id=pageNo name="pageNo" value="${noticeFM.pageNo}"/>

<input type=hidden id=schType name="schType" value="${noticeFM.schType}"/>
<input type=hidden id=schText name="schText" value="${noticeFM.schText}"/>

<input type=hidden id=procType name="procType" value="${noticeFM.procType}"/>	<!-- 처리구분(I/M/D) -->
<input type=hidden id=sno name="noticeVO.sno" value="${noticeFM.noticeVO.sno}"/>	<!-- 고유번호 -->
<input type=hidden id=noticeYn name="noticeVO.noticeYn" value="${noticeFM.noticeVO.noticeYn}"/>	<!-- 공지글여부) -->
<input type=hidden id=contents name="noticeVO.contents" value=""/>	<!-- 고유번호 -->

		<div class="title title_top">
			전체 공지사항
			<ul>
				<li><span>전체 쇼핑몰의 판매자들에게 공지를 하는 게시판입니다.</span></li>
			</ul>
			<br/>
		</div>
		<br/>
		<table class="tb" border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">	
			<colgroup>
				<col class="cellC">
				<col class="cellL">
				<col class="cellC">
				<col class="cellL">
			</colgroup>
			<tbody>
			<tr>
				<td>제목</td>
				<td colspan="3">
					<input type="text" id="title" name="noticeVO.title" value="${noticeFM.noticeVO.title}" label="제목" style="width:600px" required="required" class="line" maxlength="200">
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="3">
					<textarea id="body" name="body" style="width:100%;height:350px" type="editor">${noticeFM.noticeVO.contents}</textarea>
					<script src="/resources/shop/lib/meditor/mini_editor.js"></script>
					<script>mini_editor("/resources/shop/lib/meditor/");</script>
				</td>
			</tr>
			</tbody>
		</table>
		
		<div style="padding:20px" align="center" class="noline">
			<div class="button">
				<input type="image" id="btn_save" src="/resources/shop/admin/img/btn_save.gif">
			<c:if test="${ noticeFM.noticeVO.regId == sessionScope.xmall_admin.ADMINUSER.xkey.m_id}">
				<input type="image" id="btn_delete" src="/resources/shop/admin/img/btn_del.gif" style="display:none;">
			</c:if>
				<input type="image" id="btn_list" src="/resources/shop/admin/img/btn_list.gif">
			</div>
		</div>
</form>
<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>
<%@ include file="../common/bottom.jsp" %>
	</td>
</tr>
</table>
