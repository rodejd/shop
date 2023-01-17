<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<c:if test="${!empty indbVO.html and 0 < fn:length( indbVO.html ) }">
	<c:forEach items="${indbVO.html}" var="html" >
		${html}
	</c:forEach>
</c:if>