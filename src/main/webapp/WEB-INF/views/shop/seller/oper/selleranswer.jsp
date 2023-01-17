<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

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

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
	<tr>
		<td valign="top" style="padding-left: 12px">
			<div class="title title_top">
				관리자 질문내용
				<ul>
					<li><span>관리자의 질문에 답변을 합니다.</span></li>
				</ul>
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
						<td colspan="3"> ${sellerNoticeFM.questionVO.title }</td>
					</tr>
					<tr>
						<td>글쓴이</td>
						<td>${sellerNoticeFM.questionVO.regId}</td>
						<td>등록일</td>
						<td>
							${dateUtil:formatDate(sellerNoticeFM.questionVO.regDt, "yyyy-MM-dd")}
						
						</td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="3">${sellerNoticeFM.questionVO.contents}</td>
					</tr>
				</tbody>
			</table>
		<br/>
	<form action="answerindb"  name="form" id="form" method="post">
		<input type="hidden" name="schText" value="${sellerNoticeFM.schText }">
			<table width="100%"  class="tb" border="1" bordercolor="#e6e6e6" cellpadding="0" cellspacing="0" border="0">
				<col width="30">
				<col width="100">
				<tr class="rndbg">
					<th></th>
					<th>코멘트</th>
				</tr>
				<tr height=30 align="center"> 
					<td><strong>답변달기</strong>
						<input type="hidden" id="qno" name="answerVO.qno" value="${sellerNoticeFM.schText}">
					</td> 
					
					<td>
						<textarea name="answerVO.answer"  style="width:83%; height: 50px; border: 1px solid rgb(204, 204, 204);" class="tline" id="answer"
						maxlength="200"></textarea>
						<img src="/resources/shop/admin/img/btn_register.gif" style=" padding: 5px 0px 0px 15px;  border: 0; cursor: pointer;" id="btn_register" onclick="chkForm();">
					</td> 
				</tr>
			<c:forEach items="${sellerNoticeFM.answerlist}" var="list" varStatus="i" >
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
</table>
		</td>
	</tr>
</table>
