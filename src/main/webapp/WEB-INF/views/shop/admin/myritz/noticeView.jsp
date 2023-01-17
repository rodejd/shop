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
		$('#btn_modify').on('click', function(){
			$('#procType').val($(this).attr('procType'));	// 처리구분(I/M/D)
			$('#form')
				.attr('action', ctx+'/shop/admin/myritz/noticeInput')
				.submit();
		});
		
		// 삭제버튼 클릭
		$('#btn_delete').on('click', function(){
			$('#procType').val($(this).attr('procType'));	// 처리구분(I/M/D)
			$('#form')
				.attr('action', ctx+'/shop/admin/myritz/noticeProc')
				.submit();
		});
		
		// 목록 클릭시
		$('#btn_list').on('click', function(){
			$('#form')
				.attr('action', ctx+'/shop/admin/myritz/noticeList')
				.submit();
		});
		
		cssRound('MSG01');
	});
	
</script>

<%-- ================================================================================
* HTML
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<form id="form" method="get" action="${ctx}/shop/admin/myritz/noticeView">
<input type=hidden id=pageNo name="pageNo" value="${noticeFM.pageNo}"/>

<input type=hidden id=schType name="schType" value="${noticeFM.schType}"/>
<input type=hidden id=schText name="schText" value="${noticeFM.schText}"/>

<input type=hidden id=sno name="noticeVO.sno" value="${noticeFM.noticeVO.sno}"/>	<!-- 고유번호 -->
<input type=hidden id=procType name="procType" value=""/>	<!-- 처리구분(I/M/D) -->

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
				<td colspan="3">${noticeFM.noticeVO.title}</td>
			</tr>
			<tr>
				<td>글쓴이</td>
				<td>${noticeFM.noticeVO.regId}</td>
				<td>등록일</td>
				<td>${noticeFM.noticeVO.regDt}</td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="3">
					${fn:replace(fn:replace(fn:replace(noticeFM.noticeVO.contents, '&lt;', '<'), '&gt;', '>'), '&#47;', '/') }
				</td>
			</tr>
			</tbody>
		</table>
		
		<div style="padding:20px" align="center" class="noline">
			<div class="button">
			<c:if test="${ noticeFM.noticeVO.regId == sessionScope.xmall_admin.ADMINUSER.xkey.m_id}">
				<input type="image" id="btn_modify" procType="M" src="/resources/shop/admin/img/btn_modify.gif">
				<input type="image" id="btn_delete" procType="D" src="/resources/shop/admin/img/btn_del.gif">
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
