<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%----------------------------------------------------------------------------------------------
* 파일명 : default_template.jsp
* 생성일 : 2017. 02. 06
* 작성자 : 이병환
* 설   명 : 관리자 default template
* ==============================================
* 변경이력:
* DATE				AUTHOR			DESCRIPTION
* ---------------------------------------------------------------------------
* 20170206			이병환				최초작성
 ----------------------------------------------------------------------------------------------%>
<%@ taglib prefix="tiles"     uri="http://tiles.apache.org/tags-tiles" %>

<!-- font awesome -->
<link href="/resources/shop/css/fontawesome6/css/fontawesome.css" rel="stylesheet">
<link href="/resources/shop/css/fontawesome6/css/brands.css" rel="stylesheet">
<link href="/resources/shop/css/fontawesome6/css/solid.css" rel="stylesheet">
<!-- header -->
<%--<%@ include file="/WEB-INF/views/shop/admin/common/header.jsp" %>--%>

<!-- left -->
<%--<%@ include file="/WEB-INF/views/shop/admin/common/left.jsp" %>--%>

<!-- tiles body -->
<tiles:insertAttribute name="body"/>

<!-- bottom -->
<%--<%@ include file="/WEB-INF/views/shop/admin/common/bottom.jsp" %>--%>
