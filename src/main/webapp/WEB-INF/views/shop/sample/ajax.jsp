<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<c:forEach items="${sampleVO.sampleList }" var="sample" varStatus="status">
	<li>${sample.sampleno}</li>
	<li><a href="${ctx }/shop/sample/view?sample_no=${sample.sampleno}">${sample.title}</a></li>
</c:forEach>

<%-- 
	// ajax 일 경우에도 아래 코드 사용 가능함.
--%>
<%-- <tags:paginator currentPageNo="${sampleVO.pageNo}" rowCount="${sampleVO.rowCount}" pageSize="${sampleVO.pageSize}"  pageGroupSize="${sampleVO.pageGroupSize}" /> --%>