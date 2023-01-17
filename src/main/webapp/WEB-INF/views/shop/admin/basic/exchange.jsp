<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
function chkval(){
	
}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="styleSheet" href="/resources/shop/admin/style.css">
<script src="/resources/shop/admin/common.js"></script>

<div class="title title_top">통화/환율 관리</div>

<form method="post" name="fm" action="exchange/indb" onsubmit="return chkForm(this);">
	<!--
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr><td class="rnd" colspan="4"></td></tr>
		<tr class="rndbg" style="padding-top:2">
			<th><font class="small1"><b>유로</b></font></th>
			<th><font class="small1"><b>원</b></font></th>
			<th><font class="small1"><b>달러</b></font></th>
			<th><font class="small1"><b>위안</b></font></th>
		</tr>
		<tr><td class="rnd" colspan="4"></td></tr>
		<tr><td height="4" colspan="4"></td></tr>
		<tr align="center">
			<td>₩1</td>
			<td>₩<input type="text" id="kr" name="kr" value="${exchangeVO.kr}" class="line" maxlength="200" required></td>
			<td>$<input type="text" id="en" name="en" value="${exchangeVO.en}" class="line" maxlength="200" required></td>
			<td>¥<input type="text" id="cn" name="cn" value="${exchangeVO.cn}" class="line" maxlength="200" required></td>
		</tr>
		<tr><td height="4" colspan="4"></td></tr>
		<tr><td colspan="10" class="rndline"></td></tr>
		<tr>
			<td colspan="4" align="center" style="padding:25 0 20 0"><input type="image" src="/resources/shop/admin/img/btn_save.gif" style="border:0;"></td>
		</tr>
	</table>
	-->


	<!-- change -->
	<table class="stripe-tbl inp-tbl">
		<thead>
			<tr>
				<th>유로</th>
				<th>원</th>
				<th>달러</th>
				<th>위안</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>₩1</td>
				<td>
<%--					<span>₩</span>--%>
					<label for="kr">₩</label>
					<input type="text" id="kr" name="kr" value="${exchangeVO.kr}" class="line" maxlength="200" required>
				</td>
				<td>
					<label for="en">$</label>
					<input type="text" id="en" name="en" value="${exchangeVO.en}" class="line" maxlength="200" required>
				</td>
				<td>
					<label for="cn">¥</label>
					<input type="text" id="cn" name="cn" value="${exchangeVO.cn}" class="line" maxlength="200" required>
				</td>
			</tr>
			<!--
			<tr>
				<td colspan="4">
					<input type="image" src="/resources/shop/admin/img/btn_save.gif" alt="저장" style="border:0;">
				</td>
			</tr>
			-->
		</tbody>
	</table>

	<!-- TODO - jqGrid -->

	<div class="button">
		<input type="submit" class="admin-btn" value="저장" />
	</div>

</form>


<div class="title title_top">통화/환율 관리 History<span> </span></div>

<form name="fmList" method="post">
<%--	<input type="hidden" id="pageNo" name="pageNo" value=""/>--%>
	<input type="hidden" id="pageNo" name="pageNo" value="${exchangeVO.pageNo}" />
<%--	<table class="tb">--%>
<%--		<col class="cellC" />--%>
<%--		<col class="cellL" />--%>
<%--		<tr height="30">--%>
<%--			<td>기간</td>--%>
<%--			<td>--%>
<%--				<input type="text" name="sregdt" id="sregdt1" value='${exchangeVO.sregdt[0]}' onclick="calendar(event)" class=line onkeydown="onlynumber(event)" onkeyup="removeChar(event)" maxlength="8"> ---%>
<%--				<input type="text" name="sregdt" id="sregdt2" value='${exchangeVO.sregdt[1]}' onclick="calendar(event)" class=line onkeydown="onlynumber(event)" onkeyup="removeChar(event)" maxlength="8">--%>
<%--				<input type="image" src="/resources/shop/admin/img/btn_search2.gif" value="검색" style="border:0;vertical-align: middle;" />--%>
<%--			</td>--%>
<%--		</tr>--%>
<%--	</table>--%>
<%--	<div class="button_top"></div>--%>
<%--	<input type="image" src="/resources/shop/admin/img/btn_search2.gif" value="검색" style="border:0;vertical-align: middle;" />--%>


	<div class="sub-cont-wrap">
		<div class="div-tbl">
			<div class="th" style="width: 100px;">기간</div>
			<div>
				<input type="text" name="sregdt" id="sregdt1" value='${exchangeVO.sregdt[0]}' onclick="calendar(event);" class=line onkeydown="onlynumber(event)" onkeyup="removeChar(event)" maxlength="8">
				<span>-</span>
				<input type="text" name="sregdt" id="sregdt2" value='${exchangeVO.sregdt[1]}' onclick="calendar(event);" class=line onkeydown="onlynumber(event)" onkeyup="removeChar(event)" maxlength="8">
<%--				<input type="image" src="/resources/shop/admin/img/btn_search2.gif" value="검색" style="border:0;vertical-align: middle;" />--%>
<%--				<button type="submit" class="adminMenu-btn-search">검색</button>--%>
				<button type="submit" class="admin-btn-search2">검색</button>
			</div>
		</div>
	</div>
	
<%--	<table width="100%" cellpadding="0" cellspacing="0" border="0">--%>
<%--		<tr><td class=rnd colspan=6></td></tr>--%>
<%--		<tr class=rndbg style="padding-top: 2px">--%>
<%--			<th width=60><font class="small1"><b>번호</b></font></th>--%>
<%--			<th><font class="small1"><b>변경일</b></font></th>--%>
<%--			<th><font class="small1"><b>유로</b></font></th>--%>
<%--			<th><font class="small1"><b>원</b></font></th>--%>
<%--			<th><font class="small1"><b>달러</b></font></th>--%>
<%--			<th><font class="small1"><b>위안</b></font></th>--%>
<%--		</tr>--%>

	<div class="sub-cont-wrap">
		<table class="stripe-tbl">
			<colgroup>
				<col style="width: 60px">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>변경일</th>
					<th>유로</th>
					<th>원</th>
					<th>달러</th>
					<th>위안</th>
				</tr>
			</thead>
			<tbody>
	<%--		<tr><td class=rnd colspan=6></td></tr>--%>
			<c:forEach items="${exchangeVO.exchangeList}" var="list" varStatus="status">
			<tr height="30" align="center">
	<%--			<td><font class="ver71" color="616161">${(exchangeVO.rowCount - status.index) - ( (exchangeVO.pageNo - 1)  *  10 ) }</font></td>--%>
	<%--			<td><font class="ver81"><fmt:formatDate value="${list.regdt}" pattern="yyyy.MM.dd HH::mm:ss"/></font></td>--%>

				<td>${(exchangeVO.rowCount - status.index) - ( (exchangeVO.pageNo - 1)  *  10 ) }</td>
				<td><fmt:formatDate value="${list.regdt}" pattern="yyyy.MM.dd HH::mm:ss"/></td>
				<td>₩1</td>
				<td>₩<fmt:formatNumber type="number" value="${list.kr}" /></td>
				<td>$<fmt:formatNumber type="number" value="${list.en}" /></td>
				<td>¥<fmt:formatNumber type="number" value="${list.cn}" /></td>
			</tr>
	<%--		<tr><td colspan="6" class=rndline></td></tr>--%>
			</c:forEach>
			</tbody>
		</table>
	
		<tags:paginator currentPageNo="${exchangeVO.pageNo}" rowCount="${exchangeVO.rowCount}" pageSize="${exchangeVO.pageSize}"  pageGroupSize="${exchangeVO.pageGroupSize}" />
	</div>
</form>

<script type="text/javascript">
function goPage(page){
	$("#pageNo").val(page);
	document.fmList.submit();
	//window.location.href="?pageNo="+page;
}
</script>

<%@ include file="../common/bottom.jsp" %>