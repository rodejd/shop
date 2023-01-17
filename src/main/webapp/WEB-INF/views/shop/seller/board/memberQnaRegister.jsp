<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>


<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<script language="javascript">

if( '${sellerBoardFM.result}' == 1 ){
	parent.location.reload();
	parent.closeLayer();
}

function order_open(){
	var divEl = document.getElementById('ifm_order');
	divEl.style.display = "block";
	divEl.style.left = 20;
	divEl.style.top = 165;
	divEl.style.width = 560;
	divEl.style.height = 280;
	divEl.style.position = "absolute";
	if( divEl.src == '' ) divEl.src = "member_qna_order?mno='${sellerBoardFM.sellerMemberQnaVO.memQnaObj.mno}'";
}

function order_close(){
	var divEl = document.getElementById('ifm_order');
	divEl.style.display = "none";
}

function order_put( ordno ){
	form.ordno.value = ordno;
	order_close();
}
$(function(){
	$('#btn_save').on('click', function(){
		
		if(""== $('#subject').val()||undefined == $('#subject').val()){
			alert("제목을 입력해주세요")
			return false;
		}else if(""== $('#contents').val()||undefined == $('#contents').val()){
			alert("내용을 입력해주세요")
			return false;
		}else{
			$('#form')
			.attr('action',ctx+'/shop/seller/board/memberQnaIndb')
			.submit();
		}
		
	});
})
</script>
<div id="dynamic"></div>
<div id="jsmotion"></div>

<body class="scroll" >

<form id=form name=form method=post  action="memberQnaIndb" onsubmit="return chkForm(this)">
<input type=hidden name=sellerMemberQnaVO.mode value="${sellerBoardFM.sellerMemberQnaVO.mode }">
<input type=hidden name=sellerMemberQnaVO.sno value="${sellerBoardFM.sellerMemberQnaVO.sno }">

<c:choose>
<c:when test="${'modify' eq memQnaVO.mode}">
	<input type=hidden name=email value="${sellerBoardFM.sellerMemberQnaVO.memQnaObj.email}">
</c:when>
<c:otherwise>
	<input type=hidden name=email value="${sellerBoardFM.sellerMemberQnaVO.memQnaPrtObj.email}">
</c:otherwise>
</c:choose>

<div class="title title_top">1:1 문의 ${'modify' eq sellerBoardFM.sellerMemberQnaVO.mode ? '수정' : '답변' }<span></span></div>

<table class=tb>
<col class=cellC><col class=cellL>
<tr>
	<td>${'modify' eq sellerBoardFM.sellerMemberQnaVO.mode ? '작성자' : '답변관리자' }</td>
	<td>
		<font class=ver8>
		
<%-- 		<c:if test="${'reply' eq sellerBoardFM.sellerMemberQnaVO.mode }"> --%>
<!-- 			<select name="mno"> -->
<%-- 				<c:forEach items="${sellerBoardFM.sellerMemberQnaVO.memberList }" var="list"> --%>
<%-- 					<option value="${list.mno}">${list.mid}[${list.name}]</option> --%>
<%-- 				</c:forEach> --%>
<!-- 			</select> -->
<%-- 		</c:if> --%>
<%-- 		<c:if test="${'reply' != sellerBoardFM.sellerMemberQnaVO.mode }"> --%>
<%-- 				${sellerBoardFM.sellerMemberQnaVO.memQnaObj.mno == 0 ? sellerBoardFM.sellerMemberQnaVO.memQnaObj.name : sellerBoardFM.sellerMemberQnaVO.mid } --%>
<%-- 		</c:if> --%>
		${sellerBoardFM.sellerMemberQnaVO.sellerNm}
		<input type="hidden" name="sellerMemberQnaVO.sellerNm" value="${sellerBoardFM.sellerMemberQnaVO.sellerNm}">
		
		</font>
	</td>
</tr>
<tr>
	<td>작성일</td>
	<td><font class=ver8><fmt:formatDate value="${'reply' != sellerBoardFM.sellerMemberQnaVO.mode ? sellerBoardFM.sellerMemberQnaVO.memQnaObj.regdt : sellerBoardFM.sellerMemberQnaVO.regdt }" pattern="yyyy-MM-dd HH:mm:ss"/></font></td>
</tr>

	<c:if test="${'reply' != sellerBoardFM.sellerMemberQnaVO.formType }">
		<tr>
			<td>질문유형</td>
			<td>
				<c:if test="${sellerBoardFM.sellerMemberQnaVO.memQnaObj.itemcd != null && sellerBoardFM.sellerMemberQnaVO.memQnaObj.itemcd != 00 }">
					${ codeUtil:getCodeName("question", sellerBoardFM.sellerMemberQnaVO.memQnaObj.itemcd) }
				</c:if>
			</td>
		</tr>
	</c:if>
	
	<c:if test="${'reply' == sellerBoardFM.sellerMemberQnaVO.formType }">
		<tr>
			<td>질문유형</td>
			<td>
				<c:if test="${sellerBoardFM.sellerMemberQnaVO.memQnaPrtObj.itemcd != null && sellerBoardFM.sellerMemberQnaVO.memQnaPrtObj.itemcd != 00 }">
					${ codeUtil:getCodeName("question", sellerBoardFM.sellerMemberQnaVO.memQnaPrtObj.itemcd) }
				</c:if>
			</td>
			
		</tr>	
		<tr>
			<td>이메일</td>
			<td style="padding-top:3px; padding-bottom:3px;">
			${sellerBoardFM.sellerMemberQnaVO.memQnaPrtObj.email }<span style="padding-left:10px">${'y' eq sellerBoardFM.sellerMemberQnaVO.memQnaPrtObj.mailling ? '답변을 전송해주세요' : '' }</span>

		</tr>
	</c:if>
	
<tr>
	<td>제목</td>
	<td><input type="text" id="subject" name="sellerMemberQnaVO.subject" value="${'modify' eq sellerBoardFM.sellerMemberQnaVO.mode ? sellerBoardFM.sellerMemberQnaVO.memQnaObj.subject : ''}" style="width:90%;" class=line></td>
</tr>
<tr>
	<td>문의</td>
	<td>

	<!-- 답변이 아닌 경우에만 출력 -->	
	<c:choose>
		<c:when test="${!'reply' eq sellerBoardFM.sellerMemberQnaVO.formType  }">
			<textarea  id="contents" name="sellerMemberQnaVO.contents" cols=60 rows=20 style="width:90%;" class=tline>${'modify' eq sellerBoardFM.sellerMemberQnaVO.mode ? sellerBoardFM.sellerMemberQnaVO.memQnaObj.contents : '' }</textarea>
		</c:when>
		<c:otherwise>
			<textarea id="contents" name="sellerMemberQnaVO.contents" cols=60 rows=14 style="width:90%;" class=tline>${'modify' eq sellerBoardFM.sellerMemberQnaVO.mode ? sellerBoardFM.sellerMemberQnaVO.memQnaObj.contents : '' }</textarea>
		</c:otherwise>
	</c:choose>
	</td>
</tr>
</table>

<div class="button_popup">
<input type="image" id="btn_save" src="/resources/shop/admin/img/btn_confirm_s.gif" id>
<a href="javascript:parent.closeLayer()"><img src="/resources/shop/admin/img/btn_cancel_s.gif"></a>
</div>

</form>


<iframe id=ifm_order frameborder=0 scrolling=no style="display:none; background-color:#ffffff; border-style:solid; border-width:1; border-color:#000000;"></iframe>



<script>table_design_load();</script>
<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

