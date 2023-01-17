<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<c:set var="uri" value="${requestScope['javax.servlet.forward.servlet_path']}" />
<div id="jsmotion"></div>
<div id="maxlicense" style="display:none;"></div>
			
<script type="text/JavaScript">
	$(function(){
		<%-- // TODO ] 스크립트 에러 무슨 스크립트인지 확인 요함 --%>
		linecss();
		table_design_load();
		
		<%-- 
			// #####
			// # header 메뉴 _on.gif 설정
			// #####
		--%>
		var obj = returnImg(location.href);
		$("#menuKey" + obj.menuKey + " img").attr("src", obj.src);
	});
	
	<%-- // Header 메뉴 On/Off 설정 --%>
	function returnImg(url){
		
		if(url.indexOf("/basic/") > 0){
			return { menuKey : 1, src : "/shop/admin/img/gnb_basic_on.gif" };
		}else if(url.indexOf("/goods/") > 0){
			return { menuKey : 2, src : "/shop/admin/img/gnb_goods_on.gif" };
		}else if(url.indexOf("/order/") > 0){
			return { menuKey : 3, src : "/shop/admin/img/gnb_order_on.gif" };
		}else if(url.indexOf("/member/") > 0){
			return { menuKey : 4, src : "/shop/admin/img/gnb_member_on.gif" };
		}else if(url.indexOf("/board/") > 0){
			return { menuKey : 5, src : "/shop/admin/img/gnb_board_on.gif" };
		}else if(url.indexOf("/event/") > 0){
			return { menuKey : 6, src : "/shop/admin/img/gnb_event_on.gif" };
		}
		//정산관리 주문코드 정리 후 추후 구현
		/* else if(url.indexOf("/stat/") > 0){
			return { menuKey : 7, src : "/shop/admin/img/gnb_stat_on.gif" };
		} */
		else if(url.indexOf("/oper/") > 0){
			return { menuKey : 8, src : "/shop/admin/img/gnb_oper_on.gif" };
		}
	}
	
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

<div class="header_wrap">
	<div class="logo_box"><a href="${ctx }/shop/seller/basic/default">MaLL IN MaLL<img src="/resources/shop/admin/img/logo.png"></a></div>
	<div class="nav_box">
		<%--
		<a href="${ctx }/shop/seller/basic/default" data-menu="1" id="menuKey1" ><img src="/resources/shop/admin/img/gnb_basic.gif"></a>
		<a href="${ctx }/shop/seller/goods/list" data-menu="2" id="menuKey2"><img src="/resources/shop/admin/img/gnb_goods.gif"></a>
		<a href="${ctx }/shop/seller/order/list?mode=group&period=0&first=1" data-menu="3" id="menuKey3"><img src="/resources/shop/admin/img/gnb_order.gif"></a>
		<a href="${ctx }/shop/seller/member/orderMemberList" data-menu="4" id="menuKey4"><img src="/resources/shop/admin/img/gnb_member.gif"></a>
		<a href="${ctx }/shop/seller/board/goodsQna" data-menu="5" id="menuKey5"><img src="/resources/shop/admin/img/gnb_board.gif"></a>
		<a href="${ctx }/shop/seller/event/eventList" data-menu="6" id="menuKey6"><img src="/resources/shop/admin/img/gnb_event.gif"></a>
		<!-- <a href="/shop/seller/stat/sales" data-menu="7" id="menuKey7"><img src="/resources/shop/admin/img/gnb_stat.gif"></a> -->
		<a href="${ctx }/shop/seller/oper/noticeList" data-menu="8" id="menuKey8"><img src="/resources/shop/admin/img/gnb_oper.gif"></a>
		--%>
		<a href="${ctx }/shop/seller/basic/default" data-menu="1" id="menuKey1" <c:if test="${fn:indexOf(uri, '/basic/') != -1}"> class="active"</c:if>>쇼핑몰기본관리</a>
		<a href="${ctx }/shop/seller/goods/list" data-menu="2" id="menuKey2" <c:if test="${fn:indexOf(uri, '/goods/') != -1}"> class="active"</c:if>>상품관리</a>
		<a href="${ctx }/shop/seller/order/list?mode=group&period=0&first=1" data-menu="3" id="menuKey3" <c:if test="${fn:indexOf(uri, '/order/') != -1}"> class="active"</c:if>>주문관리</a>
		<a href="${ctx }/shop/seller/member/orderMemberList" data-menu="4" id="menuKey4" <c:if test="${fn:indexOf(uri, '/member/') != -1}"> class="active"</c:if>>회원관리</a>
		<a href="${ctx }/shop/seller/board/goodsQna" data-menu="5" id="menuKey5" <c:if test="${fn:indexOf(uri, '/board/') != -1}"> class="active"</c:if>>게시판관리</a>
		<a href="${ctx }/shop/seller/event/eventList" data-menu="6" id="menuKey6" <c:if test="${fn:indexOf(uri, '/event/') != -1}"> class="active"</c:if>>이벤트/쿠폰관리</a>
		<a href="${ctx }/shop/seller/oper/noticeList" data-menu="8" id="menuKey8" <c:if test="${fn:indexOf(uri, '/oper/') != -1}"> class="active"</c:if>>운영관리</a>
	</div>
	
	<div class="util_box">
        <c:if test="${ shop_seller.isLogin() }">
        <p class="navbar-before-sign">${shop_seller.getUserInfo().userName}님 환영합니다.</p>
        </c:if>	
		<a href="${ctx }/shop/seller/logout"><img src="/resources/shop/admin/img/logout.gif"></a>
	</div>
</div>

