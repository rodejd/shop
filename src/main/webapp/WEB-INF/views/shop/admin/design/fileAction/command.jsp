<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${ftpVO.uplMonitorIncludePage != '' and ftpVO.uplMonitorIncludePage != null }">
	<jsp:include page="${ftpVO.uplMonitorIncludePage}"></jsp:include>
</c:if>