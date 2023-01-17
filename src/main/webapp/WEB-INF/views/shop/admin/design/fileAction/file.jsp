<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${error != null and error != '' }">
	<jsp:include page="${ftpVO.includePage }"></jsp:include>
</c:if>