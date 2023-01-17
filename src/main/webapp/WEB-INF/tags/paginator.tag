<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="currentPageNo" required="true" type="java.lang.Integer" %>
<%@ attribute name="rowCount" required="true" type="java.lang.Integer" %>
<%@ attribute name="pageSize" required="true" type="java.lang.Integer" %>
<%@ attribute name="pageGroupSize" required="true" type="java.lang.Integer" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
	int pageCount = (int)Math.ceil((double)rowCount/pageSize);

	int pageGroupIdx = (int)Math.floor((double)currentPageNo/pageGroupSize) ;
	int pageGroupNo = (currentPageNo % pageGroupSize == 0)? pageGroupIdx - 1 : pageGroupIdx;
	int startPageNo = pageGroupNo * pageGroupSize + 1;
	
	int endPageNo = ((pageCount - startPageNo) < pageGroupSize)? pageCount : startPageNo + pageGroupSize -1;
	
%>

<c:set var="pageCount" value="<%=pageCount %>" />
<c:set var="pageGroupSize" value="<%=pageGroupSize %>" />
<c:set var="startPageNo" value="<%=startPageNo %>" />
<c:set var="endPageNo" value="<%=endPageNo %>" />

<!-- 페이징 -->
<div class="pageNavi">

<c:if test="${currentPageNo > pageGroupSize }">
	<a href="#" onclick="javascript:goPage('1');"> ◁ </a>
	<a href="#" onclick="javascript:goPage(${startPageNo - 1});"> ◀ </a>
</c:if>

<c:forEach begin="${startPageNo }" end="${endPageNo }" step="1" varStatus="status">
	<c:if test="${status.index == currentPageNo}">
		<span class="current">${status.index }</span>
	</c:if>
	<c:if test="${status.index != currentPageNo}">
		<a href="javascript:goPage(${status.index });">${status.index }</a>
	</c:if>
</c:forEach>

<c:if test="${(pageCount > pageGroupSize) && ((pageCount - startPageNo) >= pageGroupSize) }">
	<a href="#" onclick="javascript:goPage(${endPageNo + 1});"> ▶ </a>
	<a href="#" onclick="javascript:goPage(${pageCount});"> ▷ </a>
</c:if>
<!--// 페이징 -->

<!-- <input type="hidden" id="pageNo" name="pageNo" value="1"> -->

</div>