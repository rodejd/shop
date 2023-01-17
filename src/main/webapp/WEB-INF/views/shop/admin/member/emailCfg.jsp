<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<c:if test="${save}">
	<script type="text/javascript">
		alert('정상적으로 변경되었습니다.');
		history.go(-1);
	</script>
</c:if>

<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
	<tr>
		<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<form method=post action="email/indb" onsubmit="return chkForm(this)">
	<input type=hidden name=mode value="${emailCfgVO.mode}">

	<div class="title title_top">${emailCfgVO.title}<span>고객에게 자동발송되는 메일을 수정하고 관리합니다 <%-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=member&no=7',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle hspace=2></a> --%> </div>

	<table width=100% class=tb>
		<col class=cellC><col class=cellL>

	<c:if test="${11 != emailCfgVO.mode}">
		<tr height=25>
			<td>자동발송여부</td>
			<td class=noline>
				<input type=radio name=cfg[mailyn_${emailCfgVO.mode}] value="y" ${emailCfgVO.useYn == 'y' ? 'checked' : ''}>자동으로 보냄
				<input type=radio name=cfg[mailyn_${emailCfgVO.mode}] value="n" ${emailCfgVO.useYn == 'n' ? 'checked' : ''}>보내지않음
			</td>
		</tr>
	</c:if>

		<tr height=25>
			<td>메일제목</td>
			<td><input type=text name="headers[Subject]" value="${emailCfgVO.subject}" style="width:100%" required class="line"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td style="padding:5px">
				<textarea name=body type=editor style="width:100%;height:500px">${emailCfgVO.contents}</textarea>
				<script src="/resources/shop/lib/meditor/mini_editor.js"></script>
				<script>mini_editor("/resources/shop/lib/meditor/")</script>
			</td>
		</tr>
	</table>

	<div class=button>
		<input type=image src="/resources/shop/admin/img/btn_modify.gif">
		<!-- <a href="javascript:history.back()"><img src="../img/btn_cancel.gif"></a> -->
	</div>

</form>

<div id=MSG01>
	<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">메일 하단에 있는 로고는 <a href="../design/design_banner.jsp" target=_blank><font color=white><b>[로고/배너관리]</b></font></a> 에서 메일로고를 등록하시면 됩니다.</td></tr>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">메일 내용에 쓰이는 이미지들은 <a href="design/design_webftp.jsp" target=_blank><font color=white><b>[webFTP이미지관리 > data > editor]</b></font></a> 에서 관리하세요.</td></tr>
	</table>
</div>
<script>cssRound('MSG01')</script>


<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>
