<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>

<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<c:set var="title" value="${orderExcelVO.mode eq 'orderXls'? '주문별 다운로드 파일 설정' : '주문 상품별 다운로드 파일 설정'}"></c:set>  

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

		<div class="title title_top">${title} <span>다운로드 받으실 주문서의 항목을 설정하실수 있습니다.</span> </div>
		<div id="MSGPOP01">
			<table cellpadding=1 cellspacing=0 border=0 class=small_tip>
				<tr>
					<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">순서변경시 <font color=0074BA>항목의 빈공간을 클릭하시고 키보드의 상하 키</font>를 이용하여 순서를 변경해주세요</td>
				</tr>
				<tr>
					<td><img src="http://guide.godo.co.kr/guide/img/sa_orderarticle_change.gif"></td>
				</tr>
				<tr>
					<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle"><font color=0074BA>선택란에 체크가 되어 있는 항목</font>만 엑셀파일로 다운로드 받아집니다.</td>
				</tr>
				<tr>
					<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">항목 순서와 선택여부를 변경 후에는 하단의 <font color=0074BA>저장버튼을 눌러야 변경한 정보가 적용</font>되어 집니다.</td>
				</tr>
			</table>
		</div>
		<div style="padding-top:15px"></div>
		<form method="post" id="cfgForm" name="cfgForm">
			<input type=hidden name=mode value="${orderExcelVO.mode}">
			<input type="hidden" name="cfgChkVal" id="cfgChkVal" value="">
			<table width="100%" border="0" bordercolor="#dfdfdf" style="border-collapse:collapse">
				<tr bgcolor=#313131 style="color:#ffffff" height=30>
					<th width=40>번호</th>
					<th width=40><a href="javascript:void(0)" onClick="chkBoxAll(document.getElementsByName('cfgChk'),'rev')" class=white>선택</a></th>
					<th width=150>항목</th>
					<th>설명</th>
				</tr>
			</table>
			<table width="100%" border="1" bordercolor="#dfdfdf" style="border-collapse:collapse" frame="hsides" rules="rows">
			<c:forEach items="${ordXls}" var="arry" varStatus="i">
				<tr height=30>
					<td align=center bgcolor=#f7f7f7 width=40 nowrap><font class=small1 color=444444>${i.index + 1 }</font></td>
					<td align=center width=40><input type=checkbox name="cfgChk" value="y" class=null ${arry[3] } >
					<td style="padding-left:5px;" width=150><b>${arry[0] }</b></td>
					<td style="padding-left:10px" nowrap><font class=ver8 color=444444> - ${arry[2] }</font></td>
				</tr>
			</c:forEach>
			</table>		
		<div class=button>
		<input type=image src="/resources/shop/admin/img/btn_save.gif" onclick="javascript:submitPopForm();">
		</div>
		
		</form>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>
	</td>
</tr>
</table>
<%-- ================================================================================
* 공통 HTML CONTENT 종료
================================================================================ --%>
<script>
	table_design_load();
	linecss(document.form);
</script>
<%-- ================================================================================
=====================================================================================
* 화면 종료
=====================================================================================
================================================================================ --%>