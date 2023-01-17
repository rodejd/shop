<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ taglib uri="/WEB-INF/tlds/function_codeUtil.tld" prefix="codeUtil" %>

<html>
<head>
<title>'Xmall 관리자 모드'</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="styleSheet" href="/resources/shop/admin/style.css">
<script src="/resources/shop/admin/common.js"></script>
<script src="/resources/shop/admin/prototype.js"></script>
<script language="javascript">
if(window.addEventListener) 
{
	window.addEventListener('load',linecss,false); 
}
else 
{
	window.attachEvent('onload',linecss); 
}
</script>
<div id="dynamic"></div>
<div id="jsmotion"></div>

<body class="scroll" >

<form name=form method=post  action="indb" onsubmit="return chkForm(this)">
<input type=hidden name=itemcd value="${memQnaVO.memQnaObj.itemcd }">
<input type=hidden name=mode value="${memQnaVO.mode }">
<input type=hidden name=sno value="${memQnaVO.sno }">
<c:choose>
<c:when test="${'modify' eq memQnaVO.mode}">
	<input type=hidden name=email value="${memQnaVO.memQnaPrtObj.email}">
</c:when>
<c:otherwise>
	<input type=hidden name=email value="${memQnaVO.memQnaObj.email}">
</c:otherwise>
</c:choose>

<div class="title title_top">이메일 문의 ${'modify' eq memQnaVO.mode ? '수정' : '답변' }<span></span></div>

<table class=tb>
<col class=cellC><col class=cellL>
<tr>
	<td>${'modify' eq memQnaVO.mode ? '작성자' : '답변관리자' }</td>
	<td>
		<font class=ver8>
		
		<c:if test="${'reply' eq memQnaVO.mode }">
			<select name="mno">
				<c:forEach items="${memQnaVO.memberList }" var="list">
					<option value="${list.mno}">${list.mid}[${list.name}]</option>
				</c:forEach>
			</select>
		</c:if>
		<c:if test="${'reply' != memQnaVO.mode }">
				${memQnaVO.memQnaObj.mno == 0 ? memQnaVO.memQnaObj.name : memQnaVO.mid }
		</c:if>
		</font>
	</td>
</tr>
<tr>
	<td>작성일</td>
	<td><font class=ver8><fmt:formatDate value="${'reply' != memQnaVO.mode ? memQnaVO.memQnaObj.regdt : memQnaVO.regdt }" pattern="yyyy-MM-dd HH:mm:ss"/></font></td>
</tr>

	<c:if test="${'reply' != memQnaVO.formType }">
		<tr>
			<td>문의분야</td>
			<td>
				<c:if test="${not empty memQnaVO.memQnaObj.itemcd}">
					${ codeUtil:getCodeName("boardtype", memQnaVO.memQnaObj.itemcd) }
				</c:if>
			</td>
		</tr>
	</c:if>
	
	<c:if test="${'reply' == memQnaVO.formType }">
		<tr>
			<td>질문유형</td>
			<td>
				<c:if test="${not empty memQnaVO.memQnaPrtObj.itemcd}">
					${ codeUtil:getCodeName("boardtype", memQnaVO.memQnaPrtObj.itemcd) }
				</c:if>
			</td>
			
		</tr>	
		<tr>
			<td>이메일</td>
			<td style="padding-top:3px; padding-bottom:3px;">
			${memQnaVO.memQnaPrtObj.email }<span style="padding-left:10px">${'y' eq memQnaVO.memQnaPrtObj.mailling ? '답변을 전송해주세요' : '' }</span>

		</tr>
	</c:if>
	
<tr>
	<td>제목</td>
	<td><input type="text" name="subject" value="${'modify' eq memQnaVO.mode ? memQnaVO.memQnaObj.subject : ''}" style="width:90%;" class=line></td>
</tr>
<tr>
	<td>문의</td>
	<td>

	<!-- 답변이 아닌 경우에만 출력 -->	
	<c:choose>
		<c:when test="${!'reply' eq memQnaVO.formType  }">
			<textarea name="contents" cols=60 rows=20 style="width:90%;" class=tline>${'modify' eq memQnaVO.mode ? memQnaVO.memQnaObj.contents : '' }</textarea>
		</c:when>
		<c:otherwise>
			<textarea name="contents" cols=60 rows=14 style="width:90%;" class=tline>${'modify' eq memQnaVO.mode ? memQnaVO.memQnaObj.contents : '' }</textarea>
		</c:otherwise>
	</c:choose>
	</td>
</tr>
</table>

<div class="button_popup">
<input type=image src="/resources/shop/admin/img/btn_confirm_s.gif">
<a href="javascript:parent.closeLayer()"><img src="/resources/shop/admin/img/btn_cancel_s.gif"></a>
</div>

</form>


<iframe id=ifm_order frameborder=0 scrolling=no style="display:none; background-color:#ffffff; border-style:solid; border-width:1; border-color:#000000;"></iframe>
<script language="javascript">
if( '${result}' == 1 ){
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
	if( divEl.src == '' ) divEl.src = "member_qna_order?mno='${memQnaVO.memQnaObj.mno}'";
}

function order_close(){
	var divEl = document.getElementById('ifm_order');
	divEl.style.display = "none";
}

function order_put( ordno ){
	form.ordno.value = ordno;
	order_close();
}
</script>


<script>table_design_load();</script>



