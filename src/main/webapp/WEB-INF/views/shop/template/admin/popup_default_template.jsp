<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="tiles"     uri="http://tiles.apache.org/tags-tiles" %>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<link rel="styleSheet" href="/resources/shop/admin/style.css">

<!-- 추가한 CSS -->
<link rel="styleSheet" href="/resources/shop/admin/custom.css">

<script src="/resources/shop/admin/common.js"></script>
<!-- <script src="../prototype.js"></script> -->

<!-- Jquery Setting-->
<script type="text/javascript" src="/resources/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery.ajax.js"></script>

<!-- tiles body -->
<div style="margin: 0 15px;">
    <tiles:insertAttribute name="body"/>
</div>
