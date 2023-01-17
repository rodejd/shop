<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>


<%@ include file="../common/header.jsp"%>
<%@ include file="../common/left.jsp"%>

<%@ page import="static com.wepinit.wepinit_shop.xmall.common.ShopLibFunction.*"%>
<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script type="text/javascript">
function chkForm() {
	var form = document.form;
	if (!chkText(form.answer, form.answer.value, "답변을 입력해주세요")){
		return false;
	}else{
		form.submit();
	}
}
	</script>
<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
	<tr>
		<td valign="top" style="padding-left: 12px">
			<div class="title title_top">
				판매자 답변
				
			</div><br/><br/>
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
						<td colspan="3"> ${adminSellerQuestAnswerFM.questionVO.title }</td>
					</tr>
					<tr>
					
						<td>등록일</td>
						<td>
							${dateUtil:formatDate(adminSellerQuestAnswerFM.questionVO.regDt, "yyyy-MM-dd")}
						
						</td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="3">${adminSellerQuestAnswerFM.questionVO.contents}</td>
					</tr>
				</tbody>
			</table><br/><br/><br/>
<!-- 			<form action="answerindb" id="form" name="form" method="post"> -->
<%-- 			<input type="hidden" name="answerVO.qno" value="${adminSellerQuestAnswerFM.schText}"> --%>
<%-- 			<input type="hidden" name="schText" value="${adminSellerQuestAnswerFM.schText}"> --%>
			
<!-- 				<table class="tb" border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;"> -->
<%-- 					<colgroup> --%>
<%-- 						<col class="cellC"> --%>
<%-- 						<col class="cellL"> --%>
<%-- 					</colgroup> --%>
<!-- 					<tbody> -->
<!-- 						<tr> -->
<!-- 							<td>판매사</td> -->
<%-- 							<td>${adminSellerQuestAnswerFM.questionVO.sellerNm}</td> --%>
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td>답변 </td> -->
<!-- 							<td colspan="3"> -->
<%-- 								${adminSellerQuestAnswerFM.questionVO.sellerNm} --%>
<%-- 							<textarea id="body" name="answerVO.answer" style="width:100%;height:350px" type="editor">${adminSellerQuestAnswerFM.answerVO.answer}</textarea> --%>
<!-- 							<script src="../../lib/meditor/mini_editor.js"></script> -->
<!-- 							<script>mini_editor("../../lib/meditor/");</script> -->
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 					</tbody>					 -->
<!-- 				</table> -->
<!-- 			</form> -->
			
<form action="answerIndb" name="form" id="form" method="post">
	<table width="100%"  class="tb" border="1" bordercolor="#e6e6e6"  cellpadding="0" cellspacing="0" border="1">
		<col width="30">
		<col width="100">
		<tr class="rndbg">
			<th></th>
			<th>코멘트</th>
		</tr>
		<tr height=30 align="center"> 
			<td><strong>답변달기</strong>
				<input type="hidden" id="qno" name="answerVO.qno" value="${adminSellerQuestAnswerFM.schText}">
			</td> 
			
			<td>
				<textarea name="answerVO.answer"  style="width: 80%; height: 50px; border: 1px solid rgb(204, 204, 204);" id="answer"
				maxlength="200"></textarea>
<!-- 				<input type="image" id="btn_register"style=" padding: 5px 0px 0px 15px;  border: 0;" src="/resources/shop/admin/img/btn_register.gif">			 -->
				<img src="/resources/shop/admin/img/btn_register.gif" style=" padding: 5px 0px 0px 15px;  border: 0; cursor: pointer;" id="btn_register" onclick="chkForm();">
			</td> 
		</tr>
		
	<c:forEach items="${adminSellerQuestAnswerFM.answerlist}" var="list" varStatus="i" >
		<tr height=30 align="center">
			<td>
				<strong>
					 ${list.regId } 
				</strong> 
					(작성일 ${list.regDt})
			</td>
			<td align="left">
				${list.answer }
			</td>
				
		</tr>
	</c:forEach>
	</table>
</form>
		</td>
	</tr>
	
</table>
	
<script>
linecss();
table_design_load();
</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>


<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>