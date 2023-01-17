<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%

// 	String bottom = "/shop/skin/"+wskin+"/common/bottom.jsp";

	/************************************************
	 *	쇼핑몰 정보
	 *************************************************/
// 	 request.setAttribute("shopAlign", ShopConfig.getProperty("shopAlign", "left"));
// 	 request.setAttribute("address", ShopConfig.getProperty("address"));
// 	 request.setAttribute("compSerial", ShopConfig.getProperty("compSerial"));
// 	 request.setAttribute("orderSerial", ShopConfig.getProperty("orderSerial"));
// 	 request.setAttribute("adminName", ShopConfig.getProperty("adminName"));
// 	 request.setAttribute("ceoName", ShopConfig.getProperty("ceoName"));
// 	 request.setAttribute("compName", ShopConfig.getProperty("compName"));
// 	 request.setAttribute("compPhone", ShopConfig.getProperty("compPhone"));
// 	 request.setAttribute("compFax", ShopConfig.getProperty("compFax"));
// 	 request.setAttribute("adminEmail", ShopConfig.getProperty("adminEmail"));
// 	 request.setAttribute("shopName", ShopConfig.getProperty("shopName"));
// 	 request.setAttribute("title", ShopConfig.getProperty("title"));

%>


<jsp:include page="/WEB-INF/views/shop/front/skin/${wskin}/common/bottom.jsp" flush="true">
	<jsp:param name="skin" value="${wskin}" />
</jsp:include>






