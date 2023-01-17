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

<c:if test="${currentPageNo > pageGroupSize }">
	<li class="page-item">
		<!-- <a href="#none" class="page-link page-lt" onclick="javascript:goPage('1');">◀◀</a> -->
		<a href="#none" class="page-link page-lt prev" onclick="javascript:goPage(${startPageNo - 1});">◀</a>
	</li>
	
</c:if>

<!-- <li class="page-item"><a class="page-link page-lt" href="#">&lt;</a></li>
	<li class="page-item"><a class="page-link active" href="#">1</a></li>
	<li class="page-item"><a class="page-link" href="#">2</a></li>
	<li class="page-item"><a class="page-link" href="#">3</a></li>
	<li class="page-item"><a class="page-link page-gt" href="#">&gt;</a></li> 
-->
<c:forEach begin="${startPageNo }" end="${endPageNo }" step="1" varStatus="status">
	<c:if test="${status.index == currentPageNo}">
		<li class="page-item active">
			<a class="page-link" href="#none">
				${status.index }
			</a>
		</li>
	</c:if>
	<c:if test="${status.index != currentPageNo}">
		<li class="page-item">
			<a href="#none" class="scriptCon" onclick="goPage(${status.index });"> 
				${status.index }
			</a>
		</li>
	</c:if>
</c:forEach>

<c:if test="${(pageCount > pageGroupSize) && ((pageCount - startPageNo) >= pageGroupSize) }">
	<li class="page-item">
		<a href="#none" class="scriptConpage-link page-gt" onclick="javascript:goPage(${endPageNo + 1});">▶</a>
		<%-- <a href="#none" class="page-link page-gt" onclick="javascript:goPage(${pageCount});">▶▶</a> --%>
	</li>
</c:if>


									
									
<!--// 페이징 -->

<!-- <input type="hidden" id="pageNo" name="pageNo" value="1"> -->
					