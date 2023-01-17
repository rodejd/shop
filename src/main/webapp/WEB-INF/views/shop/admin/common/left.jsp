<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<c:set var="uri" value="${requestScope['javax.servlet.forward.servlet_path']}" />
<%
	//현재 URL - Header Top Menu 1Depth, 2Depth, 3Depth 메뉴 선택될 디렉토리명 셋팅
	//aMenuNmLn[][] 배열 값 - aMenuDepth[0]: 1Depth - shop aMenuDepth[1] : admin 인지 front 인지  aMenuDepth[2] : Admin 메뉴Depth
	//쇼핑몰기본관리 - basic
	//디자인관리 - design
	//상품관리 - goods
	//주문관리 - order
	//회원관리 - memeber
	//게시판관리 - board
	//이벤트/쿠폰관리 - event
	//마케팅관리 - naver
	//통계/데이터관리 - log
	//아래내용 - common.jsp 로 이동
	//String[][] aMenuNmLn = Menu.getMenuNameLinks(aMenuDepth[1]+"_"+aMenuDepth[2]);
	//log.debug(">>>>> aMenuNmLn length : " + aMenuNmLn.length);
	//for (int j =0 ; j < aMenuNmLn.length ; j++){
	//	log.debug("||" + aMenuNmLn[j][0] );
	//}
%>
<style>
	.menu-ul {
		list-style-type: none;
		padding-left: 0;
	}
</style>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<table cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td valign="top" id="leftMenu" width="170" background="/resources/shop/admin/img/sub_leftmenu_back.gif">
			<table height="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td>
						<strong class="lnb_title">
							<c:choose>
								<c:when test="${fn:indexOf(uri, '/basic/') != -1}">쇼핑몰기본관리</c:when>
								<c:when test="${fn:indexOf(uri, '/goods/') != -1}">상품관리</c:when>
								<c:when test="${fn:indexOf(uri, '/order/') != -1}">주문관리</c:when>
								<c:when test="${fn:indexOf(uri, '/member/') != -1}">회원관리</c:when>
								<c:when test="${fn:indexOf(uri, '/board/') != -1}">게시판관리</c:when>
								<c:when test="${fn:indexOf(uri, '/promotion/') != -1}">프로모션관리</c:when>
								<c:when test="${fn:indexOf(uri, '/event/') != -1}">쿠폰관리</c:when>
								<c:when test="${fn:indexOf(uri, '/log/') != -1}">통계/데이터관리</c:when>
								<c:when test="${fn:indexOf(uri, '/seller/') != -1}">판매사 관리</c:when>
								<c:when test="${fn:indexOf(uri, '/myritz/') != -1}">MyRitz 관리</c:when>
							</c:choose>
						</strong>
					</td>
				</tr>
				<tr>
					<td height="100%" valign="top">
						<div>
<%--							<table width="100%" cellpadding="0" cellspacing="0" border="0" id="sLeftMenu"></table>--%>
							<ul id="sLeftMenu" class="menu-ul"></ul>

							<div style="padding-top:14px"></div>
						</div>
						<div id="s2panelside" style="padding-left:7px"><script> /* panel('s2panelside', 'member'); */</script></div>
					</td>
				</tr>
			</table>
		</td>
		<td valign="top" height="100%" width="100%" style="padding:20px 30px 30px 18px">
			<div class="search_wrap">
				<div class="path_box">
					<img src="/resources/shop/admin/img/top_area_arrow.gif" vspace="2" />HOME > <span id="navi1">기본관리</span> > <span id="navi2">기본관리설정</span>
				</div>
			</div>

			<!-- Left 메뉴 스크립트 -->
			<script>
				$(document).ready(function() {
					//navi 정보
					var url = location.href;
					url = url.split("shop")[1];
					if (url.indexOf('func') > -1) {
						if (url.indexOf('&') > -1) {
							url = url.substring(0, url.indexOf('&'));
						}
					} else if (url.indexOf('?type') > -1 || url.indexOf('?btype') > -1) {
						if (url.indexOf('&') > -1) {
							url = url.substring(0, url.indexOf('&'));
						}
					} else if (url.indexOf('?')>-1) {
						url = url.substring(0, url.indexOf('?'));
					}
					url = "/shop" + url;
					menuAjax(url);
				});

				//left메뉴리스트
				function menuAjax(url){
					var menuKey = returnKey(location.href);
					$.ajax({
						type:'get',
						url:ctx+'/shop/admin/common/left/' + menuKey,
						headers : { "Content-Type": "application/json" },
						dataType:'json',
						success:function(result){
							var source = $("#template2").html();

							var template = Handlebars.compile(source);
							var menuKeyData = {
								data : [
									{
										dataSet : result
									}
								]
							};
							/* var mergedResult = $.extend({}, result, menuKeyData ); */
							$("#sLeftMenu").html(template(menuKeyData));

							if ($(".navi[data-link*='" + url + "']").length == 0) {
								var i = url.lastIndexOf("/");
								url = url.substring(0, i) + "/list";
							}

							$('.navi').each(function() {
								if ($(this).attr("href") == url){
									$(this).css({ 'color': 'blue', 'font-weight': 'bold' });

									$("#navi1").html($(".lmenu[data-link*='" + $(this).data("id") + "']").eq(0).text());
									$("#navi2").html($(this).text());

									return;
								}
							});
						}
					});
				}

				function returnKey(url){
					if(url.indexOf("/basic/") > 0){
						return 1;
					}else if(url.indexOf("/goods/") > 0){
						return 2;
					}else if(url.indexOf("/order/") > 0){
						return 3;
					}else if(url.indexOf("/member/") > 0){
						return 4;
					}else if(url.indexOf("/board/") > 0){
						return 5;
					}else if(url.indexOf("/event/") > 0){
						return 6;
					}else if(url.indexOf("/seller/") > 0){
						return 120;
					}else if(url.indexOf("/myritz/") > 0){
						return 193;
					}else if(url.indexOf("/promotion/") > 0){
						return 38;
					}else{
						return 7;
					}
				}

				Handlebars.registerHelper('isLevel', function(menuLevel, idx, opts) {
					if(menuLevel == idx){
						return opts.fn(this);
					}else{
						return opts.inverse(this);
					}
				});
			</script>

			<script id="template" type="text/x-handlebars-template">
				{{#data}}
				{{#dataSet}}
				{{#isLevel @index "0"}}
				<tr>
					<td background="/resources/shop/admin/img/left_navi_bg.gif" height="25" class="lmenu" data-id="{{menuKey}}" data-link="{{link}}">{{menuName}}</td>
				</tr>
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%" data-tb="{{@index}}">

							{{else}}
							{{#isLevel menuLevel "2"}}
						</table>
					</td>
				</tr>
				<tr>
					<td background="/resources/shop/admin/img/left_navi_bg.gif" height="25" class="lmenu" data-id="{{menuKey}}" data-link="{{link}}">{{menuName}}</td>
				</tr>
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%" data-tb="{{@index}}">
							{{else}}
							<tr>
								<td class="smenu">
									<a href="${ctx}{{{link}}}" class="navi" data-id="{{menuKey}}" data-link={{link}}>{{menuName}}</a>
								</td>
							</tr>
							{{/isLevel}}
							{{/isLevel}}
							{{#if @last}}
						</table>
					</td>
				</tr>
				{{/if}}
				{{/dataSet}}
				{{/data}}
			</script>

			<script id="template2" type="text/x-handlebars-template">
				{{#data}}
				{{#dataSet}}
				{{#isLevel @index "0"}}
				<li background="/resources/shop/admin/img/left_navi_bg.gif" height="25" class="lmenu" data-id="{{menuKey}}" data-link="{{link}}">
					{{menuName}}
				</li>

				{{else}}
				{{#isLevel menuLevel "2"}}
				<li background="/resources/shop/admin/img/left_navi_bg.gif" height="25" class="lmenu" data-id="{{menuKey}}" data-link="{{link}}">{{menuName}}</li>
				{{else}}
				<li class="smenu">
					<a href="${ctx}{{{link}}}" class="navi" data-id="{{menuKey}}" data-link={{link}}>{{menuName}}</a>
				</li>
				{{/isLevel}}
				{{/isLevel}}
				{{/dataSet}}
				{{/data}}
			</script>