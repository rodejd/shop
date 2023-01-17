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
	window.location.href="popup_srch_seller?schType=${sellerAddressVO.schType}&schWord=${sellerAddressVO.schWord}&pageNo="+page;
}

function sendNumber(str)
{
	var obj = parent.document.getElementsByName('phone')[0];
	obj.value = (obj.value.trim()) ? obj.value + "\n" + str : str;
}
</script>

<body scroll="no">
<div class="title title_top">판매사검색 <span>핸드폰번호를 클릭하면 자동입력됩니다</span></div>  

<form>
<table>
<tr>
	<td>
	<select name="schType">
	<option value="sellerNm" ${sellerAddressVO.schType == 'sellerNm' ? "selected" : "" }>판매사명</option>
	<option value="managerNm" ${sellerAddressVO.schType == 'managerNm' ? "selected" : "" }>담당자명</option>
	</select>
	</td>
	<td>
	<input type=text name=schWord value="${sellerAddressVO.schWord}" class="line">
	</td>
	<td><input type=image src="/resources/shop/admin/img/btn_search_s1.gif" class=null></td>
</tr>
</form>

<table width=100% border=1 bordercolor=#B9B9B9 style="border-collapse:collapse">
<tr bgcolor=#F1F1F1 height=25>
	<th width=60><font class=small color=262626><b>번호</th>
	<th width=90><font class=small color=262626><b>판매사명</th>
	<th width=90><font class=small color=262626><b>담당자명</th>
	<th><font class=small color=262626><b>핸드폰번호</th>
</tr>
<col align=center span=4>

<c:choose>
<c:when test="${sellerAddressVO.sellerAddressGroupList != null &&  fn:length(sellerAddressVO.sellerAddressGroupList) >0 }">
<c:forEach items="${sellerAddressVO.sellerAddressGroupList}" var="sellerAddressGroupList" varStatus="st">	
<tr height=24 align="center">
	<td><font class=ver8 color=262626>${(sellerAddressVO.rowCount - st.index) - ( (sellerAddressVO.pageNo - 1)  *  10 ) }</td>
	<td><font class=small color=262626>${sellerAddressGroupList.sellerNm}</td>
	<td><font class=ver8 color=262626>${sellerAddressGroupList.managerNm}</td>
	<td><a href="javascript:sendNumber('${sellerAddressGroupList.managerHp}')"><font class=ver8 color=0074BA>${sellerAddressGroupList.managerHp}</a></td>
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
<tags:paginator currentPageNo="${sellerAddressVO.pageNo}" rowCount="${sellerAddressVO.rowCount}" pageSize="${sellerAddressVO.pageSize}"  pageGroupSize="${sellerAddressVO.pageGroupSize}" />


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