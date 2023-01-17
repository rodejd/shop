<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="/WEB-INF/views/shop/admin/common/header_popup.jsp" %>

<html>
<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<script type="text/javascript">
/*
 * jQuery ready
 */
function goPage(page){
	window.location.href="popup_srch_member?schType=${smsSendVO.schType}&schWord=${smsSendVO.schWord}&pageNo="+page;
}

function sendNumber(str)
{
	var obj = parent.document.getElementsByName('phone')[0];
	obj.value = (obj.value.trim()) ? obj.value + "\n" + str : str;
}
</script>

<body scroll="no">
<div class="title title_top">회원검색 <span>핸드폰번호를 클릭하면 자동입력됩니다</span></div>  

<form>
<table>
<tr>
	<td>
	<select name="schType">
	<option value="name" ${smsSendVO.schType == 'name' ? "selected" : "" }>이름
	<option value="m_id" ${smsSendVO.schType == 'm_id' ? "selected" : "" }>아이디
	</select>
	</td>
	<td>
	<input type=text name=schWord value="${smsSendVO.schWord}" class="line">
	</td>
	<td><input type=image src="/resources/shop/admin/img/btn_search_s1.gif" class=null></td>
</tr>
</form>

<table width=100% border=1 bordercolor=#B9B9B9 style="border-collapse:collapse">
<tr bgcolor=#F1F1F1 height=25>
	<th width=60><font class=small color=262626><b>번호</th>
	<th width=90><font class=small color=262626><b>이름</th>
	<th width=90><font class=small color=262626><b>아이디</th>
	<th><font class=small color=262626><b>핸드폰번호</th>
</tr>
<col align=center span=4>

<c:choose>
<c:when test="${smsSendVO.gdMemberList != null &&  fn:length(smsSendVO.gdMemberList) >0 }">
<c:forEach items="${smsSendVO.gdMemberList}" var="gdMemberList" varStatus="st">	
<tr height=24 align="center">
	<td><font class=ver8 color=262626>${(smsSendVO.rowCount - st.index) - ( (smsSendVO.pageNo - 1)  *  10 ) }</td>
	<td><font class=small color=262626>${gdMemberList.name}</td>
	<td><font class=ver8 color=262626>${gdMemberList.mid}</td>
	<td><a href="javascript:sendNumber('${gdMemberList.mobile}')"><font class=ver8 color=0074BA>${gdMemberList.mobile}</a></td>
</tr>
</c:forEach>
</c:when>
<c:otherwise>
	<tr height=25><td align=center colspan=4> 조회된 데이터가 존재하지 않습니다. </td></tr>
</c:otherwise>
</c:choose>
</table>

<%-- <div class="pageNavi" align=center><font class=ver8 color=444444><%=page_list%></div> --%>
<!-- 페이징  -->
<tags:paginator currentPageNo="${smsSendVO.pageNo}" rowCount="${smsSendVO.rowCount}" pageSize="${smsSendVO.pageSize}"  pageGroupSize="${smsSendVO.pageGroupSize}" />


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

	</td>
</tr>
</table>
<%-- ================================================================================
* 공통 HTML CONTENT 종료
================================================================================ --%>
<script language="javascript">
if( '${result}' == 1 ){
	parent.location.reload();
	parent.closeLayer();
}
</script>