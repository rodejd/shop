<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<c:set var="uri" value="${requestScope['javax.servlet.forward.servlet_path']}" />
<html>
<head>
<title>관리자모드</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="styleSheet" href="/resources/shop/admin/style.css">

<!-- 추가한 CSS -->
<link rel="styleSheet" href="/resources/shop/admin/custom.css">

<script src="/resources/shop/admin/common.js"></script>
<!-- <script src="../prototype.js"></script> -->

<!-- Jquery Setting-->
<script type="text/javascript" src="/resources/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery.ajax.js"></script>

<div id="dynamic"></div>
<!-- <iframe name="ifrmHidden" src="/resources/shop/admin/blank.jsp" style="display:block;width:100%;height:0px;border:0;" scrolling="yes"></iframe> -->
<div id="jsmotion"></div>
<div id="maxlicense" style="display:none;"></div>

<style>
	.admin-logo-wrap {
		display: flex;
		flex-direction: row;
		flex-wrap: nowrap;
		align-content: center;
		justify-content: center;
		align-items: center;
		padding: 0 !important;

		height: calc(100% - 8px);
		border-top: 4px solid transparent;
		border-bottom: 4px solid transparent;
	}

	/* 메인 하이라이터 */
	.admin-logo-wrap.active {
		border-bottom-color: #f7b81a;
	}

	.admin-logo-wrap a {
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: row;
		flex-wrap: nowrap;
		justify-content: center;
		align-items: center;
	}
</style>

<body class="scroll">
	<div class="header_wrap">
		<!-- main일 경우 로고 아래 active 표시, 제거시 el구문 삭제 -->
		<div class="logo_box admin-logo-wrap ${fn:indexOf(uri, '/basic/index') != -1 ? 'active' : ''}">
			<a href="${ctx}/shop/admin/basic/index">
				<img src="/resources/shop/data/skin/kr/images/logo.png/" alt="WEPINIT" style="width:120px;"/>
			</a>
		</div>
		<div class="nav_box">
			<%-- main이면 active하지 않는다--%>
			<a href="${ctx}/shop/admin/basic/adminMenu?menuKey=107"<c:if test="${fn:indexOf(uri, '/basic/index') == -1 && fn:indexOf(uri, '/basic/') != -1}"> class="active"</c:if>>쇼핑몰기본관리</a>
			<a href="${ctx}/shop/admin/goods/list?menuKey=47"<c:if test="${fn:indexOf(uri, '/goods/') != -1}"> class="active"</c:if>>상품관리</a>
			<a href="${ctx}/shop/admin/order/list?mode=group&period=0&first=1&menuKey=57"<c:if test="${fn:indexOf(uri, '/order/') != -1}"> class="active"</c:if>>주문관리</a>
			<a href="${ctx}/shop/admin/member/list?menuKey=61"<c:if test="${fn:indexOf(uri, '/member/') != -1}"> class="active"</c:if>>회원관리</a>
			<a href="${ctx}/shop/admin/board/goods_review/list?btype=P&menuKey=155"<c:if test="${fn:indexOf(uri, '/board/') != -1}"> class="active"</c:if>>게시판관리</a>
			<a href="${ctx}/shop/admin/promotion/banner/list?menuKey=183"<c:if test="${fn:indexOf(uri, '/promotion/') != -1}"> class="active"</c:if>>프로모션관리</a>
			<a href="${ctx}/shop/admin/event/coupon?menuKey=89"<c:if test="${fn:indexOf(uri, '/event/') != -1}"> class="active"</c:if>>쿠폰관리</a>
			<a href="${ctx}/shop/admin/log/stat/sale?menuKey=91"<c:if test="${fn:indexOf(uri, '/log/') != -1}"> class="active"</c:if>>통계/데이터관리</a>
			<a href="${ctx}/shop/admin/seller/list?menuKey=122"<c:if test="${fn:indexOf(uri, '/seller/') != -1}"> class="active"</c:if>>판매사 관리</a>
		</div>
		<div class="util_box"><a href="${ctx}/shop/admin/login/logout"><img src="/resources/shop/admin/img/logout.gif" /></a></div>
	</div>
	
	<script language="JavaScript" type="text/JavaScript">
	/*** 관리자 CRM 검색 ***/
	function topSearchCrm(){
		var searchVal = document.getElementById('crm').value;
		if( !searchVal ){
			alert('회원 아이디나 이름을 넣어주세요!');
			return;
		}
		window.open('../member/popup.list.jsp?skey=all&sword='+searchVal,'crmAdminPopUp','width=800,height=600,scrollbars=1');
	}
	</script>