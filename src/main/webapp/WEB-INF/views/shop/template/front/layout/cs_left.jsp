<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
//	String cs_left = "/shop/skin/"+wskin+"/common/cs_left.jsp";
// 	request.setAttribute("shopAlign", ShopConfig.getProperty("shopAlign", "left"));
%>
<jsp:include page="/WEB-INF/views/shop/front/skin/${wskin}/common/cs_left.jsp" flush="true">
	<jsp:param name="skin" value="${wskin}" />
</jsp:include>