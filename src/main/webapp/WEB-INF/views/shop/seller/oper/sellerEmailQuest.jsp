<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<style type="text/css">
	.test {
		background-color : aliceblue;
		color : maroon;
		font-family: arial;  
		font-weight: bold;
		border: 1px solid silver;
	}
	.aster {
		color : red;
		padding-left : 2px;
	}
</style>


<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
			<div class="title title_top">
				관리자에게 메일 문의<span>고객들이 보낸 메일을 살펴보실 수 있습니다</span>
			</div>
			<form action="emailSend" method=post>
				<table class=tb>	
					<col class=cellC><col class=cellL>
					<tr>
						<td>제목</td>
						<td><input name="headers[Subject]" type="" style="width:100%;"/></td>
					</tr>
					<tr>
						<td>내용</td>
						<td>
							<textarea name=bodyb style="width: 100%; height: 550px; border: 1px solid #ccc"></textarea>
							
						</td>
					</tr>
				</table>
				<div class="button">
					<input type=image src="/resources/shop/admin/img/btn_confirm.gif">
				</div>
			</form>
	<%-- ================================================================================
	* 업무 HTML CONTENT 종료
	================================================================================ --%>