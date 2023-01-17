<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<div class="title title_top">주문처리로그</div>

<table class="tb">
	<col class="cellC"><col class="cellL">
	<c:choose>
		<c:when test="${logList != null and fn:length(logList) >0 }">
			<c:forEach items="${logList}" var="item" varStatus="status">
				<tr>
					<td>${dateUtil:formatDate(item.regdt, "yyyy-MM-dd HH:mm")}</td>
					<td>${item.memo}</td>
				<tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td align="center">조회내역이 없습니다.</td>
			<tr>
		</c:otherwise>
	</c:choose>
	<c:if test="${logList != null and fn:length(logList) >0 }">
	</c:if>
</table>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>
