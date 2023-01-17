<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="addoptArr" value="${fn:split(addopt,'|') }"/>
<c:set var="optsum" value="0" />

<c:forEach var="addoptArr" items="${addoptArr }" varStatus="addStatus" >
	<c:if test="${not empty addoptArr }">
		<c:set var="addoptTemp" value="${fn:split(addoptArr,'^') }"/>

		<c:set var="addopt1" value="${addoptTemp[1] }"/>
		<c:set var="addopt2" value="${addoptTemp[2] }"/>
		<c:set var="addopt3" value="${addoptTemp[3] }"/>
		<div><span class="dot-yellow-s">${ addopt1} : ${addopt2 } [+<fmt:formatNumber value="${addopt3 }" pattern="#,###"/>원]</span></div>

		<c:set var="optsum" value="${optsum + addopt3 }"/>
	</c:if>
	
</c:forEach>
