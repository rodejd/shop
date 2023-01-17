<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<html>
<head>
<title>'xMall ||| Shoppingmall 관리자모드'</title>
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

<body class="scroll">

<div class="title title_top">회원 주문내역보기</div>

<table>
<col style="font-weight:bold">
<tr>
	<td>- 회원이름</td>
	<td>: ${memberVO.memberObj.name} ( ${memberVO.memberObj.mid} )</td>
</tr>
<tr>
	<td>- 결제금액</td>
	<td>: ₩<font class=ver8 color=0074BA><b>${stringUtil:getMoneyFormatInteger(memberVO.memberObj.sumsale)}</b></font> <font class=small1 color=444444>(배송완료기준)</td>
</tr>
</table><p>


<div style="padding-left:8"><font class=small1 color=444444>아래 주문번호를 클릭하면 주문상세내역을 볼 수 있습니다.</div>
<div style="padding-top:3"></div>

<table width=100% border=1 bordercolor=#cccccc style="border-collapse:collapse">
<tr bgcolor=#302D2A height=25>
	<th><font class=small1 color=white><b>주문번호</th>
	<th><font class=small1 color=white><b>주문금액</th>
	<th><font class=small1 color=white><b>주문일</th>
	<th><font class=small1 color=white><b>처리상태</th>
</tr>
<col align=center span=4>

	<c:forEach items="${memberVO.orderList }" var="list" varStatus="vnum">
		<tr height=23>
			<td><a href="javascript:popup('../order/popup.order?ordno=${list.ordno}',800,600)"><font class=ver7 color=0074BA><b>${list.ordno }</b></a></td>
			<td><font class=ver81 color=555555><b>₩${list.prnsettleprice }</td>
			<td><font class=ver7 color=777777><fmt:formatDate value="${list.orddt}" pattern="yyyy-MM-dd HH:mm"/></td>
			<td style="padding-top:2px"><font class=small1 color=666666>
			${shopLibFunction:getStepMsg(list.step, list.step2, list.ordno,"")}
			<%-- <%=getStepMsg(dbHandler,data.get(i, "step"), data.get(i, "step2") , data.get(i, "ordno") , "")%> --%></td>
		</tr>
	</c:forEach>
</table>

<!-- 페이징 -->
	<td width="60%" align="center"><font class="ver8">
	<tags:paginator currentPageNo="${memberVO.pageNo}" rowCount="${memberVO.rowCount}" pageSize="${memberVO.pageSize}"  pageGroupSize="${memberVO.pageGroupSize}" />
	</font></td>

<form name="pform" id="pform">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo != '' ? memberVO.pageNo : '1' }">
<input type="hidden" name="mno" value="${memberVO.mno}">
</form>

<script type="text/javascript">

function goPage(page){
	document.getElementById("pageNo").value = page ;
	document.getElementById("pform").submit();
}

</script>