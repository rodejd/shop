<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%----------------------------------------------------------------------------------------------
* 파일명 : index.jsp
* 생성일 : 2017. 02. 07
* 작성자 : 이병환
* 설   명 : skin default1 사용자 index
* ==============================================
* 변경이력:
* DATE				AUTHOR			DESCRIPTION
* ---------------------------------------------------------------------------
* 20170208			이병환				최초작성
----------------------------------------------------------------------------------------------%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<script type="text/javascript">

$(function(){

});

</script>

<!-- <header class="page-header page-header-banner x-service-h">
	<div class="container">
		<div class="page-header-banner-inner">
			<h1 class="page-title">A/S정책</h1>
		</div>
	</div>
</header> -->

<div class="top_tit_ty01">
	<div class="tit_dp01">POLICY</div>
</div>

<div class="x-agreement container_service">
	<div class="tabbable product-tabs">
		<!-- 고객센터 공통탭메뉴 처리 -->
		<jsp:include page="../service/service_tab_menu02.jsp" flush="true">
			<jsp:param name="tab_order" value="6" />
		</jsp:include>

		<div>
			<A name=00></A> 
			<c:import url="file:////${filePath}${policy}" charEncoding="utf-8" var="html"/>
			${fn:replace(fn:replace(fn:replace(html, '&lt;', '<'), '&gt;', '>'), '&#47;', '/') }
		</div>
	</div>
</div>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>



