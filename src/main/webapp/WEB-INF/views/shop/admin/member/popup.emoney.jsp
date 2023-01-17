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

<!-- Jquery Setting-->
<script type="text/javascript" src="/resources/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery.ajax.js"></script>
<!-- //Jquery Setting-->

<script language="javascript">
if(window.addEventListener) {
	window.addEventListener('load',linecss,false); 
} else {
	window.attachEvent('onload',linecss); 
}
</script>
<div id="dynamic"></div>
<div id="jsmotion"></div>

<body class="scroll">

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
	/*
	 * jQuery ready
	 */
	$(function(){
		
	});		
</script>
<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<form name="pform" id="pform">
	<input type="hidden" name="pageNo" id="pageNo" value="${pageNo != '' ? memberVO.pageNo : '1' }">
	<input type="hidden" name="mno" value="${memberVO.mno}">
	<input type="hidden" name="savedEmoney" id="savedEmoney" value="${memberVO.emoneyObj.emoney }">
</form>

<div style="margin-bottom:10px;">
	<div class="title title_top">현재 적립금현황<span>현재의 적립금현황을 확인합니다</span></div>
	<table cellpadding=0 cellspacing=1 border=0 bgcolor=EBEBEB>
		<tr>
			<td bgcolor=E8E8E8>
				<table cellpadding=3 cellspacing=0 border=0 bgcolor=E8E8E8>
					<tr>
						<td bgcolor=F6F6F6 width=160 align=center>현재 <b>${memberVO.emoneyObj.name}</b>님의 적립금은</td>
						<td bgcolor=white width=400>&nbsp;&nbsp;₩<b>${memberVO.emoneyObj.emoney}</b> 입니다</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
	<div style="padding-top:20"></div>
	<table style="width: 98%" cellpadding=0 cellspacing=1 border=0 bgcolor=EBEBEB>
		<tr>
			<td bgcolor=E8E8E8>
				<table style="width: 100%" cellpadding=3 cellspacing=0 border=0 bgcolor=E8E8E8>
					<colgroup>
						<col width="20%">
						<col width="20%">
						<col width="60%">
					</colgroup>
					<tr>
						<td bgcolor=F6F6F6 width=160 align=center rowspan="3">고객등급은</td>
						<td bgcolor=white width=160 align=center rowspan="3">${memberVO.emoneyObj.grpnm}</td>
						<td bgcolor=white width=160 align=left >00일내 0000원 추가 구매시 V3 UP 되며</td>
					</tr>
					<tr>
						<td bgcolor=white width=160 align=left >00일내 0000원 구매 없을시 V2 Down 예정</td>
					</tr>
					<tr>
						<td bgcolor=white width=160 align=left >00이후 000원이 적립금 소별 예정</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<div style="padding-top:20"></div>
	<div class="title title_top">적립금지급/차감<span>적립금을 지급/차감합니다</span></div>

	<form name=frmMember method=post action="indb" onsubmit="return chkForm(this)">
		<input type=hidden name=mode value="emoney_add">
		<input type=hidden name=mno value="${memberVO.mno}">
		<input type=hidden name=addEmoney value="${memberVO.emoneyObj.addEmoney}">
	
		<table cellpadding=0 cellspacing=0 border=0 bgcolor=EBEBEB>
			<tr>
				<td bgcolor=E8E8E8>
					<table cellpadding=2 cellspacing=1 border=0 bgcolor=E8E8E8>
					<tr>
						<td bgcolor=F6F6F6 width=160 align=center>지급액/차감액</td>
						<td bgcolor=white width=400>₩ <input type=text name=emoney id="emoney" size=8 class="rline" required label="적립금"> <font class=small color=444444>(차감시 마이너스금액으로 입력. 예) -200 )</font></td>
					</tr>
					<tr>
						<td bgcolor=F6F6F6 align=center>이유</td>
						<td bgcolor=white>
							<select name="memo" required label="지급이유" onchange="openLayer('direct', (this.value=='direct' ? 'block' : 'none') )" style="float:left;">
								<option value="">- 선택하세요 -</option>
								${webUtil:makeSelectCodeItem2((codeUtil:codeitem('point')), "") }
								<option value="direct">☞ 직접입력</option>
							</select>
							<div id="direct" style="display:none;"><input type=text name=directmemo size=30 class="line"></div>
						</td>
					</tr>
					</table>
				</td>
			</tr>
		</table>
	
		<div style="margin-bottom:10px;padding-top:10;" class=noline align=center>
			<input type="image" src="/resources/shop/admin/img/btn_confirm_s.gif" name="addEmoney"  id="addEmoney">
		</div>
	</form>

	<div class="title title_top">적립금내역<span>적립금 상세내역을 확인합니다</span></div>
	
	<div style="width: 100%; overflow: auto;">
		<table width=150% cellpadding=0 cellspacing=0 border=0>
			<tr>
				<td class=rnd colspan=10></td>
			</tr>
			<tr class=rndbg>
				<th>번호</th>
				<th>날짜</th>
				<th>구분</th>
				<th>적립/사용사유</th>
				<th>적립율</th>
				<th>적립금액</th>
				<th>취소/환불금액</th>
				<th>사용금액</th>
				<th>소멸금액</th>
				<th>삭제</th>
			</tr>
			<tr>
				<td class=rnd colspan=10></td>
			</tr>
			<col width=50 align=center>
			<col width=80 align=center>
			<col width=80 align=center>
			<col align=left>
			<col width=80 align=center>
			<col width=80 align=center>
			<col width=80 align=center>
			<col width=80 align=center>
			<col width=80 align=center>
			<col width=40 align=center>
			
			<c:forEach items="${memberVO.emoneyLogList }" var="list"  varStatus="vnum">
				<tr>
					<td height=4 colspan=10></td>
				</tr>
				<tr height=25 align="center">
					<td><font class=ver81 color=616161>${(memberVO.rowCount - vnum.index) - ( memberVO.rowStart ) }</td>
					<td><font class=ver81 color=616161><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd"/></td>
					<td><font class=ver81 color=616161>
						<c:choose>
							<c:when test="${list.gbn eq 'S'}">적립</c:when>
							<c:when test="${list.gbn eq 'U'}">사용</c:when>
							<c:when test="${list.gbn eq 'E'}">소멸</c:when>
							<c:when test="${list.gbn eq 'C'}">취소/환불</c:when>
						</c:choose>
					</td>
					<td><font class=ver81 color=333333>${list.memo }</td>
					<td><c:if test="${list.gbn eq 'S' and list.addemoney ne '0'}"><font class=ver81 color=333333>${list.addemoney}%</font></c:if></td>
					<td><c:if test="${list.gbn eq 'S'}">₩<font class=ver81 color=0074BA><b>${list.emoney}</b></font></c:if></td>
					<td><c:if test="${list.gbn eq 'C'}">₩<font class=ver81 color=0074BA><b>${list.emoney}</b></font></c:if></td>
					<td><c:if test="${list.gbn eq 'U'}">₩<font class=ver81 color=0074BA><b>${list.emoney}</b></font></c:if></td>
					<td><c:if test="${list.gbn eq 'E'}">₩<font class=ver81 color=0074BA><b>${list.emoney}</b></font></c:if></td>
					<td align=center><a href="../member/indb?mode=emoney_delete&sno=${list.sno }"><img src="/resources/shop/admin/img/i_del.gif"></a></td>
				</tr>
				<tr><td height=4 colspan=10></td></tr>
				<tr><td colspan=10 class=rndline></td></tr>
			</c:forEach>
		</table>
	</div>

	<!-- 페이징 -->
	<td width="60%" align="center">
		<font class="ver8">
			<tags:paginator currentPageNo="${memberVO.pageNo}" rowCount="${memberVO.rowCount}" pageSize="${memberVO.pageSize}"  pageGroupSize="${memberVO.pageGroupSize}" />
		</font>
	</td>
</div>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>
<script>table_design_load();</script>
<%-- ================================================================================
* 공통 HTML CONTENT 종료
================================================================================ --%>

<script type="text/javascript">
function goPage(page){
	document.getElementById("pageNo").value = page ;
	document.getElementById("pform").submit();
}

$(function(){
	/* 적립금 차감시 보유적립금 확인 추가 */
	$('[name=addEmoney]').on('click', function() {
		var addEmoney = Number($('[name=emoney]').val());
		var savedEmoney = Number($('[name=savedEmoney]').val());
		if (0 > (savedEmoney+addEmoney)) {
			alert(savedEmoney+addEmoney);
			alert("보유적립금이 부족합니다");
			return false;
		}
	});

});		
</script>