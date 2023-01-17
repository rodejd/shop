<!-- taglib -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="dateUtil" uri="/WEB-INF/tlds/function_dateUtil.tld" %>
<%@ taglib prefix="stringUtil" uri="/WEB-INF/tlds/function_stringUtil.tld" %>
<%@ taglib prefix="codeUtil" uri="/WEB-INF/tlds/function_codeUtil.tld" %>
<%@ taglib prefix="webUtil" uri="/WEB-INF/tlds/function_webUtil.tld" %>
<%@ taglib prefix ="shopLibFunction" uri="/WEB-INF/tlds/function_shopLibFunction.tld" %>
<%@ taglib prefix="adminSessionObject" uri="/WEB-INF/tlds/function_adminSessionObject.tld"%>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="baseUrl" value="${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, '')}" />
<%--<spring:eval var="ctx" expression="@config['CONTEXT_PATH']"/> --%>

<script>
	var ctx = "${ctx }"; 
</script>