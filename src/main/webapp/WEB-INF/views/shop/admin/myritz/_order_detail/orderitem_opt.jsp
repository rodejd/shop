<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<c:set var="addoptArr" value="${fn:split(addopt,'|') }"/>
<c:forEach var="addoptArr" items="${addoptArr }" varStatus="addStatus" >
	<c:if test="${not empty addoptArr }">
		<c:set var="addoptTemp" value="${fn:split(addoptArr,'^') }"/>
		<c:set var="addopt1" value="${addoptTemp[1] }"/>
		<c:set var="addopt2" value="${addoptTemp[2] }"/>
		<c:set var="addopt3" value="${addoptTemp[3] }"/>
		<div><span class="small">${ addopt1} : ${addopt2 } [+₩<fmt:formatNumber value="${addopt3 }" pattern="#,###"/>]</span></div>
	</c:if>
</c:forEach>