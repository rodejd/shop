<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.wepinit.wepinit_shop.xmall.common.session.ShopSessionObject"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE HTML>
<html>
<head>
	<title>${meta_title} WEPINIT</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="description" content="${meta_title}">
	<meta name="keywords" content="${meta_keywords}">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta property="og:type" content="article"/>
	<meta property="og:title" content="${frontGoodsVO.goodsView.goodsnmKR}" />
	<meta property="og:url" content="${baseUrl}/shop/goods/goods_view?goodsno=${frontGoodsVO.goodsno}" />
	<meta property="og:description" content="${frontGoodsVO.goodsView.shortdesc}" />
	<meta property="og:image" content="${fn:split(frontGoodsVO.goodsView.imgm,'|')[0]}" />
	
	<link rel="stylesheet" type="text/css" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/css/jquery-ui.css" media="all" />
	<link href='https://fonts.googleapis.com/css?family=Roboto:500,300,700,400italic,400' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,600' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/css/bootstrap.css">
	<link rel="stylesheet" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/css/font-awesome.css">
	<link rel="stylesheet" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/css/styles.css?v=2">
	<link rel="stylesheet" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/css/mystyles.css?v=<fmt:formatDate value="${now}" pattern="yyyyMMddHHmmss"/>">
	<link rel="stylesheet" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/css/material-design-iconic-font.css">
	<!-- 
	<link rel="stylesheet" href="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/css/jquery.themepunch.css">
	-->
	
	<!-- 자동검색완성기능 구현을 위한 css, js 추가 -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/jquery.js"></script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/common.js"></script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/jquery.aw-showcase.js"></script>
	<script type="text/javascript" src="/resources/js/jquery/jquery.ajax.js"></script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/datepicker-ko.js"></script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js"></script>
	
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/owl.carousel.min.js"></script>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/OwlCarousel2Thumbs.min.js"></script>
	<!-- 
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/jquery.themepunch.tools.min.js"></script>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/jquery.themepunch.revolution.min.js"></script>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/revolution.extension.slideanims.min.js"></script>
	<script src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/jquery.themepunch.revolution.min.js"></script> -->
	
	<script type="text/javascript">
		//<![CDATA[
		var NJ = $.noConflict(true);
		//]]>
	</script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/ui.js"></script>
	<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/masonry.pkgd.js"></script>
	<script type="text/javascript">
		$(function() {
			//$.datepicker.setDefaults($.datepicker.regional['ko']); 
			//$('#start_date').datepicker({minDate: -1, maxDate: '+1m'});
			//$('#end_date').datepicker({minDate: -1, maxDate: '+1m'});
		});
	</script>
	<!--[if lt IE 9]>
	<![endif]-->

	<script type="text/javascript">
		//<![CDATA[
		$('.grid').masonry({
			// options
			itemSelector: '.grid-item',
			columnWidth: 320
		});
		//]]>
	</script>
	<!-- 메인 컨텐츠 스크립트 start -->
	<script type="text/javascript">
		$(function(){
			$('#publicSearch').on('click', function(){
				if ($.trim($('#search_form input[name=sword]').val()) == ""){
					alert("검색어를 입력하세요");
					return;
				}
				$('#search_form').submit();
			});
			
			$('.search_word').on('click', function(e){
				e.preventDefault();
				if (e.target.className != "btn_close_x") {
					var word = $(this).text();
					if ($(this).find(".btn_close_x").length > 0) {
						word = word.slice(0, -1);
					}
					console.log(word);
					$('#search_form input[name=sword]').val(word);
					$('#search_form').submit();
				}
			});
			
			$('.btn_close_x').on('click', function(e){
				e.preventDefault();
				var btn = $(this).parent();
				var word = btn.text().slice(0, -1);
				ajaxCallJson("/shop/goods/ajaxSearchWordDel.doJson", {"sword": word}, function(data){
					btn.parent().remove();
				});
			});

			// 검색창을 클릭 했을 때 자동검색 완성을 위한 상품 데이터 불러오기 
			$('#search_form input[name=sword]').on('click', function(){
				//select box에 선택한 값을 가져온다. 선택한값에 따라 값을 보여주기 위해 
				var check = $('#search_form input[name=skey]').val();
				var vali = true;
				switch(check){
					case "all" :
						//전역변수로 생성한 배열은 비동기방식으로 selectBox 값에 맞게 배열에 삽입된다.
						//이미 값을 가져왔는데 다시 ajax를 호출하는건 불필요하다고 생각하기에
						//ajax로 가져온 데이터는 값이 이미 있다면 false 리턴 값이 없으면 true 리턴  
						vali = allList.length > 0 ? false : true;
						break;
					case "goodsnm" :
						vali = goodsList.length > 0 ? false : true;
						break;
					case "brand" :
						vali = brandList.length > 0 ? false : true;
						break;
					case "maker" :
						vali = makerList.length > 0 ? false : true;
						break;
				}
				//vali값을 통해 중복 적으로 ajax를 호출하지 않는다.
				if(vali){
					ajaxCallJson("/shop/goods/ajaxAutoComplete.dojson", check , function(result){
						switch(check){
							case "all" :
								//ajax를 통해 자바에서 가져온 all에 해당하는 데이터를  자바스크립트 배열에 담는다. 
								$.each(result, function (index, item) {
									allList[index] = item;
								})
								break;
							case "goodsnm" :
								//상품명 
								$.each(result, function (index, item) {
									goodsList[index] = item;
								}) 
								break;
							case "brand" :
								//브랜드
								$.each(result, function (index, item) {
									brandList[index] = item;
								}) 
								break;
						}
					});
				}
				
				//check 값에 해당하는 데이터만 자동완성으로 보여주기위한 코드 
				switch(check){
					case "all" :
						$('#search_form input[name=sword]').autocomplete({
							source : allList
							,minLength : 2
						})
						break;
					case "goodsnm" :
						$('#search_form input[name=sword]').autocomplete({
							source : goodsList
							,minLength : 2
						})
						break;
					case "brand" :
						$('#search_form input[name=sword]').autocomplete({
							source : brandList
							,minLength : 2
						})
						break;
					case "maker" :
						$('#search_form input[name=sword]').autocomplete({
							source : makerList
							,minLength : 2
						})
						break;
				}
			});
		});
			
		/* all ,goodsnm ,maker ,brandnm 은  검색종류에 따라 원하는 데이터만 보여주기 위한 배열 생성 
		전역변수로 상품정보 데이터를 생성 .  Ajax로 호출할때마다 데이터를 새로 담지 않기 위해 전역변수로 데이터를 생성하였음 */
		allList = new Array();
		goodsList = new Array();
		makerList = new Array();
		brandList = new Array();
		
		// This is called with the results from from FB.getLoginStatus().
		function statusChangeCallback(response) {
			// The response object is returned with a status field that lets the
			// app know the current login status of the person.
			// Full docs on the response object can be found in the documentation
			// for FB.getLoginStatus().
			if (response.status === 'connected') {
				// Logged into your app and Facebook.
				testAPI();
			} else if (response.status === 'not_authorized') {
				// The person is logged into Facebook, but not your app.
				document.getElementById('status').innerHTML = 'Please log ' + 'into this app.';
			} else {
				// The person is not logged into Facebook, so we're not sure if
				// they are logged into this app or not.
				document.getElementById('status').innerHTML = 'Please log ' + 'into Facebook.';
			}
		}

		// This function is called when someone finishes with the Login
		// Button.  See the onlogin handler attached to it in the sample
		// code below.
		function checkLoginState() {
			FB.getLoginStatus(function(response) {
				statusChangeCallback(response);
			});
		}

		window.fbAsyncInit = function() {
			FB.init({
				appId      : '1525824351071726',
				cookie     : true,  // enable cookies to allow the server to access 
									// the session
				xfbml      : true,  // parse social plugins on this page
				version    : 'v2.2' // use version 2.2
			});

			// Now that we've initialized the JavaScript SDK, we call 
			// FB.getLoginStatus().  This function gets the state of the
			// person visiting this page and can return one of three states to
			// the callback you provide.  They can be:
			//
			// 1. Logged into your app ('connected')
			// 2. Logged into Facebook, but not your app ('not_authorized')
			// 3. Not logged into Facebook and can't tell if they are logged into
			//    your app or not.
			//
			// These three cases are handled in the callback function.

			/*
			FB.getLoginStatus(function(response) {
				statusChangeCallback(response);
			});
			*/
		};

		// Load the SDK asynchronously
		(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id)) return;
			js = d.createElement(s); js.id = id;
			js.src = "//connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));

		// Here we run a very simple test of the Graph API after login is
		// successful.  See statusChangeCallback() for when this call is made.
		function testAPI() {
			console.log('Welcome!  Fetching your information.... ');
			FB.api('/me', function(response) {
				console.log('Successful login for: ' + response.name);
				$("input[name=facebook_id]").val(response.id);
				$('#login_form').submit(); 
			});
		}
	  
		function logoutFacebook() {
			FB.getLoginStatus(function(response) {
				if (response.status === 'connected') {
					// the user is logged in and has authenticated your
					// app, and response.authResponse supplies
					// the user's ID, a valid access token, a signed
					// request, and the time the access token 
					// and signed request each expire
					var uid = response.authResponse.userID;
					var accessToken = response.authResponse.accessToken;

					FB.api('/'+uid+'/permissions', 'delete', function(response){});
				} else if (response.status === 'not_authorized') {
					// the user is logged in to Facebook, 
					// but has not authenticated your app
				} else {
					// the user isn't logged in to Facebook.
				}
			});
		}
	</script>

	<script>
		//크흠 네이버 로그인
		function generateState() {
			var mt = new Date();
			return mt.getTime();
		}
		function loginNaver(){
			var state = generateState();
			var redirect_uri ="http://www.nadocook.com/shop/member/login_naver.jsp";
			var client_id = "bsOwdJ01ncXET7n42BAI";
			//location.href="https://nid.naver.com/oauth2.0/authorize?client_id="+client_id+"&response_type=code&redirect_uri="+redirect_uri+"&state="+state;
			window.open("https://nid.naver.com/oauth2.0/authorize?client_id="+client_id+"&response_type=code&redirect_uri="+redirect_uri+"&state="+state,"네이버새창", "width=0, height=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes" ); 
			<%-- var stored_state = "<%=request.getParameter("state")%>";
			alert("stored_state"+stored_state);
			alert("state"+state);
			if( state != stored_state ) {
				alert("안됨");
				return RESPONSE_UNAUTHORIZED; //401 unauthorized
			} else {
				alert("됨");
				return RESPONSE_SUCCESS; //200 success
			} --%>
		}
	  
		/* function getNaverUserInfo(){
			naver.api(URL, tokenInfo.access_token, function(data) {
				var response = data._response.responseJSON;
				console.log("success to get user info", response);
				alert(response.response.email);
			});
		} */
		/*  function generateState() {
		alert("generateState");
			// CSRF 방지를 위한 state token 생성 코드
			// state token은 추후 검증을 위해 세션에 저장 되어야 합니다.
			var oDate = new Date();
			return oDate.getTime();
		}
		function saveState(state) {
			alert("saveState");
			$.removeCookie("state_token");
			$.cookie("state_token", state);
			alert("saveState1");
		}
		var naver = NaverAuthorize({
			client_id : "ZWItKn9kMm7Z9EbfM6XP",
			redirect_uri : "http://127.0.0.1/shop/main/index.jsp",
			client_secret : "l7CfbQkKxv"
		});

		function loginNaver() {
			alert("loginNaver");
			var state = generateState();
			saveState(state);
			naver.login(state);
		} */

		/* $("#NaverIdLoginBTN").click( function () {
			var state = generateState();
			saveState(state);
			naver.login(state);
		});
   
		var tokenInfo = { access_token:"" , refresh_token:"" };
	 
		function checkLoginState() {
			alert("checkLoginState");
			var state = $.cookie("state_token");
			if(naver.checkAuthorizeState(state) === "connected") {
				//정상적으로 Callback정보가 전달되었을 경우 Access Token발급 요청 수행
				naver.getAccessToken(function(data) {

					var response = data._response.responseJSON;
					if(response.error === "fail") {
						//access token 생성 요청이 실패하였을 경우에 대한 처리
						alert("checkLoginState실패");
						return ;
					}
					tokenInfo.access_token = response.access_token;
					tokenInfo.refresh_token = response.refresh_token;

					//sonsole.log에 나온다.
					console.log("success to get access token", response);
				});
			} else {
				//Callback으로 전달된 데이터가 정상적이지 않을 경우에 대한 처리
				alert("정보없음");
				return ;
			}
		} */
		/* function getNaverUserInfo(){
			naver.api(URL, tokenInfo.access_token, function(data) {
				var response = data._response.responseJSON;
				console.log("success to get user info", response);
				alert(response.response.email);
			});
		} */
	</script>
	<!-- Google Tag Manager -->
	<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
	new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
	j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
	'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
	})(window,document,'script','dataLayer','GTM-P8Z5TK8');</script>
	<!-- End Google Tag Manager -->
	
	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async src="https://www.googletagmanager.com/gtag/js?id=G-SYJYN6ZQ1G"></script>
	<script>
	  window.dataLayer = window.dataLayer || [];
	  function gtag(){dataLayer.push(arguments);}
	  gtag('js', new Date());
	
	  gtag('config', 'G-SYJYN6ZQ1G');
	</script>
</head>

<!-- <div id="dynamic"></div>
<iframe name="ifrmHidden" src="../../blank.jsp" style="height:0px;border:0;" scrolling="yes"></iframe>
<div id="jsmotion"></div>
<div id="maxlicense" style="display:none;"></div> -->

<%
	//request.setAttribute("shop_so", ShopSessionObject.getSessionObject(request));
%>
				
<body>
	<div id="dynamic"></div>
	<div class="modal-mask" id="fixedLayer" style="DISPLAY: none;"> 
		<div class="modal-wrapper">
			<div class="modal-container" id="fixed-container" style="DISPLAY: none;">
				<!-- <div class="modal-header">
					<div name="header">헤더</div>
				</div> -->

				<div class="modal-body">
					<div name="body" id="fixedLayer_cont">고정식팝업내용</div>
				</div>
				<div class="modal-footer">
					<div name="footer">
						<a href="javascript:void(0)" id="fixedLayer_exit">X</a> 
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal-mask" id="movingLayer" style="DISPLAY: none;">
		<div class="modal-wrapper" id="movingWrapper">
			<div class="modal-container" id="moving-container" style="DISPLAY: none;">
				<!-- <div class="modal-header">
					<div name="header">헤더</div>
				</div> -->

				<div class="modal-body">
					<div name="body" id="movingLayer_cont">이동식팝업내용</div>
				</div>
				<div class="modal-footer">
					<div name="footer">
						<a href="javascript:void(0)" id="movingLayer_exit">X</a>

					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 전체검색 레이어 -->
	<div class="search_layer_wrap">
		<a href="#" class="btn_close_search_ly">X</a>
		<div class="in_bx">
			<form class="w100_n" role="search" id="search_form" action="${ctx}/shop/goods/goods_search">
				<div class="tp_inp_wrap" style="position:relative;">
					<input type="hidden" name="skey" value="all">
					<input class="inp_ty01" id="sword" name="sword" type="text" value="${sword}" placeholder="검색어를 입력하세요" required />
					<a class="navbar-main-search-submit btn_search_all" id="publicSearch">검색<!-- <i class="zmdi zmdi-search"></i> --></a>
				</div>
				<div class="bx_bt">
					<div class="bx01">
						<p class="s_tit">최근 검색어</p>
						<ul>
							<c:set var="nsword" value="" />
							<c:if test="${not empty goodsSearchVO and not empty goodsSearchVO.sword}">
								<c:set var="nsword" value="${goodsSearchVO.sword}" />
								<li>
									<p class="search_word"><a href="#">${nsword}</a><a href="#" class="btn_close_x">x</a></p>
								</li>
							</c:if>
							<c:if test="${not empty headerRecentWordList}">
								<c:forEach var="recentWord" items="${headerRecentWordList}" varStatus="status">
									<c:if test="${nsword ne recentWord}">
										<li>
											<p class="search_word"><a href="#">${recentWord}</a><a href="#" class="btn_close_x">x</a></p>
										</li>
									</c:if>
								</c:forEach>
							</c:if>
						</ul>
					</div>
					<div class="bx02">
						<p class="s_tit">추천 검색어</p>
						<ul>
							<c:forEach var="i" begin="0" end="9" step="1">
								<c:set var="sword_recommend" value="sword_recommend${i}" />
								<c:if test="${fn:trim(shopLibFunction:getProperty(sword_recommend)) ne ''}">
									<li><p class="search_word"><a href="#">${shopLibFunction:getProperty(sword_recommend)}</a></p></li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
					<div class="bx03">
						<p class="s_tit">
							인기 검색어
							<span class="tx_up">
								<fmt:parseDate var="sword_update" value="${shopLibFunction:getProperty('sword_update')}" pattern="yyyy-MM-dd HH:mm:ss"/>
								<fmt:formatDate value="${sword_update}" pattern="MM.dd"/> 업데이트
							</span>
						</p>
						<ul>
							<c:forEach var="i" begin="0" end="9" step="1">
								<c:set var="sword_popular" value="sword_popular${i}" />
								<c:set var="sword_popular_mark" value="sword_popular_mark${i}" />
								<li>
									<span class="num"><fmt:formatNumber value="${i + 1}" type="number" minIntegerDigits="2"/></span>
									<a href="#" class="search_word">${shopLibFunction:getProperty(sword_popular)}</a>
									<span class="tx_result">
										${shopLibFunction:getProperty(sword_popular_mark) eq '2' ? '▽' : shopLibFunction:getProperty(sword_popular_mark) eq '1' ? 'ㅡ' : '▲'}
									</span>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- //전체검색 레이어 -->

	<script>
		$(document).ready(function(){
			$(".btn_hd_search_all").click(function(){
				$(".search_layer_wrap").css("display", "block");
			});
			$(".btn_close_search_ly").click(function(){
				$(".search_layer_wrap").css("display", "none");
			});
			$(".bt_lang01").click(function(){
				if(!$(this).data("click")){
					$(".area_lang").addClass("on");
					$(this).data("click", true);
				}else{
					$(".area_lang").removeClass("on");
					$(this).data("click", false);
				}
			});
		});
	</script>

	<div id="jsmotion"></div>
	<div id="maxlicense" style="display:none;"></div>
		<div class="" id="global-wrapper">
			<div class="header_wrap">	
				<div class="in_bx">
					<div class="tp_area">
						<a href="#" class="menu_mob_n"><img src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/images/mob_menu_n.png" alt="모바일메뉴" title="모바일메뉴" style="width:20px;" /></a>

						<c:if test="${ shop_so.isShopLogin() }">
							<p class="navbar-before-sign">${shop_so.getUserInfo().userName}님 환영합니다.</p>
						</c:if>

						
						<a class="logo_n" href="${ctx}/shop/main/index"><img src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/images/logo.png" width="200" alt="WEPINIT"/></a>

						<ul>
							<li>
								<a href="#" class="btn_hd_search_all">검색</a>
							</li>
							<!-- <li style="float:left;">
								<c:choose>
									<%-- 로그인 후 --%>
									<c:when test="${shop_so.isShopLogin()}">
										<a href="${ctx}/shop/member/logout" data-effect="mfp-move-from-top" class="">로그아웃</a>
									</c:when>
									<%-- 로그인 전 --%>
									<c:otherwise>
										<a href="${ctx}/shop/member/login" data-effect="mfp-move-from-top" class="">로그인</a>
									</c:otherwise>
								</c:choose>
							</li>
							<c:choose>
								<%-- 로그인 후 --%>
								<c:when test="${shop_so.isShopLogin()}">
									
								</c:when>
								<%-- 로그인 전 --%>
								<c:otherwise>
									<li style="float:left;"><a href="${ctx}/shop/member/join">회원가입</a></li>
								</c:otherwise>
							</c:choose> -->
						  
							<!-- <li style="float:left;"><a href="${ctx}/shop/service/customer" data-effect="mfp-move-from-top" class=""> Contact</a></li>
							<li style="float:left;"><a href="#" id="favorite" data-effect="mfp-move-from-top" class=""> 즐겨찾기</a></li> -->
							<li>
								<a href="${ctx}/shop/goods/goods_cart" id="wishGoods" data-effect="mfp-move-from-top" class="btn_hd_cart"> 장바구니<%--  <c:if test="${!(wishEA eq '-1')}"><b>${wishEA }</b></c:if> --%></a>                    	
							</li>

							<!--  zmdi zmdi-card-giftcard -->
							<!-- <li style="float:left;"><a href="${ctx}/shop/event/eventList" data-effect="mfp-move-from-top" class=""> Event</a></li> -->
							<c:choose>
								<%-- 비회원 로그인 후 --%>
								
								<c:when test="${shop_so.isShopLogin() == false}">
									<li><a href="${ctx}/shop/member/login" data-effect="mfp-move-from-top" class="btn_hd_mypage"> Mypage</a></li>
								</c:when>
								<c:otherwise>
									<!-- <li style="float:left;"><a href="${ctx}/shop/mypage/mypage_orderlist" data-effect="mfp-move-from-top" class=""> Order/Delivery</a></li> -->
									<li><a href="${ctx}/shop/mypage/main_index" data-effect="mfp-move-from-top" class="btn_hd_mypage"> Mypage</a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>

					<nav class="gnb_wrap">
						<div class="gnb_n">
							<div class="" id="main-nav-collapse">
								<ul>
									<li class="mn_wm"><a href="/shop/goods/goods_list?category=002">WOMEN</a></li>
									<li class="mn_m"><a href="/shop/goods/goods_list?category=001">MEN</a></li>
									<li class="mn_k"><a href="/shop/goods/goods_list?category=003">KIDS</a></li>
									<li><a href="/shop/goods/designer">DESIGNER</a></li>
									<li><a href="/shop/goods/new">NEW</a></li><!-- class="gnb_mob_no" -->
									<li><a href="/shop/goods/hot">HOT100</a></li>
									<li><a href="/shop/goods/vip">VIP SHOP</a></li>
									<li><a href="/shop/goods/sale">SALE</a></li>
								</ul>
							
								<!-- 메인메뉴 -->
								<!-- <ul class="nav navbar-nav" style="float:none;">
									<li class="dropdown"><a href="#"><div data-toggle="dropdown"><span>Shop by</span>Department<i class="zmdi zmdi-chevron-down"></i></div></a>
										<ul class="dropdown-menu dropdown-menu-category">

											<c:if test="${!empty(appMenuCategoryList)}">
												<c:forEach items="${appMenuCategoryList}"  var="appMenuCategoryList"  varStatus="status">
													<c:set var="menu_hidden" value="false" />
													
													<c:if test="${ '3' eq  appMenuCategoryList.cateLength}" >
														<c:if test="${!status.first}">
																					</ul>
																			</div>
																		</div>
																	</div>
																	<%-- <img class="dropdown-menu-category-section-theme-img" src="/resources/shop/data/skin/${wskin}/img/test_cat/${appMenuCategoryList.category - 1}-i.png" alt="Image Alternative text" title="Image Title"/> --%>
																</div>
															</div>
														</li>
														</c:if>
														
														<%-- <c:if test="${ appMenuCategoryList.hidden eq 0}" > --%>
														
														<li><a href="${ctx}/shop/goods/goods_list?category=${appMenuCategoryList.category}">${ appMenuCategoryList.catnm }</a>
															<div class="dropdown-menu-category-section">
																<div class="dropdown-menu-category-section-inner">
																	<div class="dropdown-menu-category-section-content">
																		<div class="row">
																			<div class="col-md-6">
																				<h5 class="dropdown-menu-category-title">${ appMenuCategoryList.catnm }</h5>
																					<ul class="dropdown-menu-category-list">
														<%-- </c:if> --%>
														
														<c:if test="${status.last}">
																					</ul>
																			</div>
																		</div>
																	</div>
																	<%-- <img class="dropdown-menu-category-section-theme-img" src="/resources/shop/data/skin/${wskin}/img/test_cat/${appMenuCategoryList.category +0}-i.png" alt="Image Alternative text" title="Image Title"/> --%>
																</div>
															</div>
														</li>
														</c:if>
																							
													</c:if>
													
													<c:if test="${ '3' ne  appMenuCategoryList.cateLength}" >
													<%-- <c:if test="${ '3' ne  appMenuCategoryList.cateLength && appMenuCategoryList.hidden eq 0}" > --%>
																						<li><a href="${ctx}/shop/goods/goods_list?category=${appMenuCategoryList.category}">${ appMenuCategoryList.catnm }</a></li>
														<c:if test="${status.last}">
																					</ul>
																			</div>
																		</div>
																	</div>
																	<%-- <img class="dropdown-menu-category-section-theme-img" src="/resources/shop/data/skin/${wskin}/img/test_cat/${fn:substring(appMenuCategoryList.category ,0 , 3)+0}-i.png" alt="Image Alternative text" title="Image Title"/> --%>
																</div>
															</div>
														</li>
														</c:if>
													</c:if>
												</c:forEach>
											</c:if>
											
											
										</ul>
									 </li>
									 
									 <!-- 브랜드메뉴
									 <c:if test="${ !empty(appMenuBrandList)}" >
									 <li class="dropdown"><a href="#"><div data-toggle="dropdown">Brand<i class="zmdi zmdi-chevron-down"></i></div></a>
										<ul class="dropdown-menu dropdown-menu-category">
										<c:forEach items="${appMenuBrandList}"  var="item"  varStatus="status">
											<li><a href="${ctx}/shop/goods/goods_brand?brandno=${item.sno}">${item.brandnm}</a></li>                                
										</c:forEach>
										</ul>
									</li>
									</c:if>

								<!-- </ul> -->
							</div>
						</div>
					</nav>
				</div>
			</div>

<style>
/* for MS계열 브라우저 */
@-webkit-keyframes blinker {
  from { opacity: 1.0; }
  to { opacity: 0.0; }
}

.waitingForConnection {
  -webkit-animation-name: blinker;
  -webkit-animation-iteration-count: infinite;
  -webkit-animation-timing-function: cubic-bezier(1.2, 0, 1, 1);
  -webkit-animation-duration: 1.2s;
}
</style>
			<div class="nav_bt_tx">
				<c:if test="${not empty mainCampaignList}">
					<c:forEach var="campaignList" items="${mainCampaignList}" varStatus="status">
						<c:choose>
							<c:when test="${campaignList.gbn eq 'I'}">
								<img src="${campaignList.pcImg}" style="width: 50px; height: 50px;">
							</c:when>
							<c:otherwise>
								<div class="waitingForConnection">${campaignList.title}</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>
			</div>
			<!-- women -->
			<div class="gnb_ly_n women_n">
				<div class="in_bx">
					<div class="bx_li_n">
						<p><a href="/shop/goods/goods_list?category=002001">여성가방</a></p>
						<ul>
							<li><a href="/shop/goods/goods_list?category=002001001">토트백</a></li>
							<li><a href="/shop/goods/goods_list?category=002001002">숄더/크로스백</a></li>
							<li><a href="/shop/goods/goods_list?category=002001003">클러치/파우치</a></li>
							<li><a href="/shop/goods/goods_list?category=002001004">백팩</a></li>
							<li><a href="/shop/goods/goods_list?category=002001007">벨트 백</a></li>
						</ul>
					</div>
					<div class="bx_li_n">
						<p><a href="/shop/goods/goods_list?category=002002">여성지갑</a></p>
						<ul>
							<li><a href="/shop/goods/goods_list?category=002002001">전체보기</a></li>
							<li><a href="/shop/goods/goods_list?category=002002002">반지갑</a></li>
							<li><a href="/shop/goods/goods_list?category=002002003">장지갑</a></li>
						</ul>
					</div>
					<div class="bx_li_n">
						<p><a href="/shop/goods/goods_list?category=002003">여성의류</a></p>
						<ul>
							<li><a href="/shop/goods/goods_list?category=002003001">상의</a></li>
							<li><a href="/shop/goods/goods_list?category=002003002">하의</a></li>
							<li><a href="/shop/goods/goods_list?category=002003003">원피스/점프수트</a></li>
							<li><a href="/shop/goods/goods_list?category=002003005">아우터</a></li>
							<li><a href="/shop/goods/goods_list?category=002003001003">니트웨어</a></li>
						</ul>
					</div>
					<div class="bx_li_n">
						<p><a href="/shop/goods/goods_list?category=002004">여성슈즈</a></p>
						<ul>
							<li><a href="/shop/goods/goods_list?category=002004001">스니커즈</a></li>
							<li><a href="/shop/goods/goods_list?category=002004002">로퍼/플랫</a></li>
							<li><a href="/shop/goods/goods_list?category=002004003">샌달/슬리퍼</a></li>
							<li><a href="/shop/goods/goods_list?category=002004006">부츠</a></li>
							<li><a href="/shop/goods/goods_list?category=002004009">하이힐</a></li>
							<li><a href="/shop/goods/goods_list?category=002004010">뮬</a></li>
							<li><a href="/shop/goods/goods_list?category=002004012">드레스 슈즈</a></li>
						</ul>
					</div>
					<div class="bx_li_n">
						<p><a href="/shop/goods/goods_list?category=002005">여성악세서리</a></p>
						<ul>
							<li><a href="/shop/goods/goods_list?category=002005001">키링/키홀더</a></li>
							<li><a href="/shop/goods/goods_list?category=002005003">벨트</a></li>
							<li><a href="/shop/goods/goods_list?category=002005005">머플러/스카프</a></li>
							<li><a href="/shop/goods/goods_list?category=002005006">선글라스/프레임</a></li>
							<li><a href="/shop/goods/goods_list?category=002005007">모자</a></li>
							<li><a href="/shop/goods/goods_list?category=002005009">장갑</a></li>
							<li><a href="/shop/goods/goods_list?category=002005013">헤어액세서리</a></li>
						</ul>
					</div>
					<div class="bx_li_n">
						<p><a href="/shop/goods/goods_list?category=002006">여성쥬얼리</a></p>
						<ul>
							<li><a href="/shop/goods/goods_list?category=002006001">목걸이</a></li>
							<li><a href="/shop/goods/goods_list?category=002006002">귀걸이</a></li>
							<li><a href="/shop/goods/goods_list?category=002006003">팔찌</a></li>
							<li><a href="/shop/goods/goods_list?category=002006004">반지</a></li>
							<li><a href="/shop/goods/goods_list?category=002006005">브로치/핀</a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- //women -->

			<!-- men -->
			<div class="gnb_ly_n men_n">
				<div class="in_bx">
					<div class="bx_li_n">
						<p><a href="/shop/goods/goods_list?category=001001">남성가방</a></p>
						<ul>
							<li><a href="/shop/goods/goods_list?category=001001001">토트백</a></li>
							<li><a href="/shop/goods/goods_list?category=001001002">숄더/크로스백</a></li>
							<li><a href="/shop/goods/goods_list?category=001001003">클러치/파우치</a></li>
							<li><a href="/shop/goods/goods_list?category=001001004">백팩</a></li>
							<li><a href="/shop/goods/goods_list?category=001001005">비즈니스백</a></li>
							<li><a href="/shop/goods/goods_list?category=001001006">트래블 백</a></li>
							<li><a href="/shop/goods/goods_list?category=001001007">벨트 백</a></li>
						</ul>
					</div>
					<div class="bx_li_n">
						<p><a href="/shop/goods/goods_list?category=001002">남성지갑</a></p>
						<ul>
							<li><a href="/shop/goods/goods_list?category=001002003">반지갑</a></li>
							<li><a href="/shop/goods/goods_list?category=001002004">장지갑</a></li>
							<li><a href="/shop/goods/goods_list?category=001002005">카드홀더</a></li>
						</ul>
					</div>
					<div class="bx_li_n">
						<p><a href="/shop/goods/goods_list?category=001003">남성의류</a></p>
						<ul>
							<li><a href="/shop/goods/goods_list?category=001003001">상의</a></li>
							<li><a href="/shop/goods/goods_list?category=001003001002">후디</a></li>
							<li><a href="/shop/goods/goods_list?category=001003002">하의</a></li>
							<li><a href="/shop/goods/goods_list?category=001003002001">반바지</a></li>
							<li><a href="/shop/goods/goods_list?category=001003005">아우터</a></li>
							<li><a href="/shop/goods/goods_list?category=001003001003">니트웨어</a></li>
						</ul>
					</div>
					<div class="bx_li_n">
						<p><a href="/shop/goods/goods_list?category=001004">남성슈즈</a></p>
						<ul>
							<li><a href="/shop/goods/goods_list?category=001004001">스니커즈</a></li>
							<li><a href="/shop/goods/goods_list?category=001004013">드레스 슈즈</a></li>
							<li><a href="/shop/goods/goods_list?category=001004004">샌달/슬리퍼</a></li>
							<li><a href="/shop/goods/goods_list?category=001004007">부츠</a></li>
							<li><a href="/shop/goods/goods_list?category=001004009">로퍼</a></li>
						</ul>
					</div>
					<div class="bx_li_n">
						<p><a href="/shop/goods/goods_list?category=001005">남성악세서리</a></p>
						<ul>
							<li><a href="/shop/goods/goods_list?category=001005001">벨트</a></li>
							<li><a href="/shop/goods/goods_list?category=001005002">선글라스/프레임</a></li>
							<li><a href="/shop/goods/goods_list?category=001005003">머플러/스카프</a></li>
							<li><a href="/shop/goods/goods_list?category=001005004">모자</a></li>
							<li><a href="/shop/goods/goods_list?category=001005005">넥타이</a></li>
							<li><a href="/shop/goods/goods_list?category=001005006">모바일 케이스</a></li>
							<li><a href="/shop/goods/goods_list?category=001005007">키링/키홀더</a></li>
						</ul>
					</div>
					<div class="bx_li_n">
						<p><a href="/shop/goods/goods_list?category=001006">남성쥬얼리/시계</a></p>
						<ul>
							<li><a href="/shop/goods/goods_list?category=001006001">목걸이</a></li>
							<li><a href="/shop/goods/goods_list?category=001006002">귀걸이</a></li>
							<li><a href="/shop/goods/goods_list?category=001006003">팔찌</a></li>
							<li><a href="/shop/goods/goods_list?category=001006004">반지</a></li>
							<li><a href="/shop/goods/goods_list?category=001006005">브로치/핀</a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- //men -->

			<script>
			$(document).ready(function(){
				$(".mn_wm").mouseover(function(){
					$(".women_n").css("display", "block");
				});
				$(".women_n").mouseover(function(){
					$(".women_n").css("display", "block");
				});
				$(".mn_wm").mouseout(function(){
					$(".women_n").css("display", "none");
				});
				$(".women_n").mouseout(function(){
					$(".women_n").css("display", "none");
				});
				
				$(".mn_m").mouseover(function(){
					$(".men_n").css("display", "block");
				});
				$(".men_n").mouseover(function(){
					$(".men_n").css("display", "block");
				});
				$(".mn_m").mouseout(function(){
					$(".men_n").css("display", "none");
				});
				$(".men_n").mouseout(function(){
					$(".men_n").css("display", "none");
				});

				$(".menu_mob_n").click(function(){
					$(".mob_gnb_l").animate({ left: '0' }, 300);
					$(".mob_opa").addClass("act");
					$("body").addClass("fixed");
					$(".wrap").addClass("open");
				});
				$(".btn_close_mob_n").click(function(){
					$(".mob_gnb_l").animate({ left: '-100%' }, 300);
					$(".mob_opa").removeClass("act");
					$("body").removeClass("fixed");
					$(".wrap").removeClass("open");
				});
				$(".mob_opa").click(function(){
					$(".mob_gnb_l").animate({ left: '-100%' }, 300);
					$(".mob_opa").removeClass("act");
					$("body").removeClass("fixed");
					$(".wrap").removeClass("open");
				});


				$(".mob_mn_n01").click(function(){
					$(".mob_mn_n01 a").addClass("on");
					$(".mob_mn_n02 a").removeClass("on");
					$(".mob_mn_n03 a").removeClass("on");
					$(".mob_mn_dp2_n01").css("display", "block");
					$(".mob_mn_dp2_n02").css("display", "none");
					$(".mob_mn_dp2_n03").css("display", "none");
				});
				$(".mob_mn_n02").click(function(){
					$(".mob_mn_n01 a").removeClass("on");
					$(".mob_mn_n02 a").addClass("on");
					$(".mob_mn_n03 a").removeClass("on");
					$(".mob_mn_dp2_n01").css("display", "none");
					$(".mob_mn_dp2_n02").css("display", "block");
					$(".mob_mn_dp2_n03").css("display", "none");
				});
				$(".mob_mn_n03").click(function(){
					$(".mob_mn_n01 a").removeClass("on");
					$(".mob_mn_n02 a").removeClass("on");
					$(".mob_mn_n03 a").addClass("on");
					$(".mob_mn_dp2_n01").css("display", "none");
					$(".mob_mn_dp2_n02").css("display", "none");
					$(".mob_mn_dp2_n03").css("display", "block");
				});
			});
			</script>
			<div class="mob_gnb_l">
				<div class="mob_opa"></div>
				<div class="in_bx" style="position:relative; overflow-y:scroll;">
					<a href="#" class="btn_close_mob_n" style="position:Absolute; top:40px; right:20px; color:#000;">X</a>
					<div style="padding:42px 0 20px 30px;"><a href="/shop/main/index"><img src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/images/logo.png" alt="Ritzmall" title="Ritzmall" style="width:73px;" /></a></div>

					<div style="padding:0 20px 30px 30px;">
						<div class="area_lang">
							<div class="bt_lang01">대한민국, KRW ￦</div>
						</div>
					</div>
										
					<div class="bx_mob_menu">
						<ul>
							<li style="float:left; width:29%;" class="mob_mn_n01"><a href="javascript:void(0);" class="on">WOMEN</a></li><!-- /shop/goods/goods_list?category=002 -->
							<li style="float:left; width:21%;" class="mob_mn_n02"><a href="javascript:void(0);">MEN</a></li><!-- /shop/goods/goods_list?category=001 -->
							<li style="float:left; width:21%;" class="mob_mn_n03"><a href="javascript:void(0);">KIDS</a></li><!-- /shop/goods/goods_list?category=003 -->
							<!-- <li style="float:left; width:29%;" class="mob_mn_n04"><a href="javascript:void(0);">BEAUTY</a></li> -->
						</ul>
						<div style="clear:both;"></div>
					</div>
					<div style="height:650px;">
						<div style="/*min-height:503px;*/ padding:30px 0 0 34px;">
							<ul>
								<li style="padding-bottom:22px;"><a href="/shop/ritz_live" style=" font-family:'Raleway', sans-serif; font-weight:600; font-size:18px; color:#2b2b2b; line-height:18px;margin: 0 0 0 -4px;"><img src="/resources/images/ritz_live.png" style="height:26px"></a></li>
					
								<li style="padding-bottom:22px;"><a href="/shop/goods/new" style=" font-family:'Raleway', sans-serif; font-weight:600; font-size:18px; color:#2b2b2b; line-height:18px;">NEW ARRIVAL</a></li>
								<li style="padding-bottom:22px;"><a href="/shop/goods/designer" style=" font-family:'Raleway', sans-serif; font-weight:600; font-size:18px; color:#2b2b2b; line-height:18px;">DESIGNER</a></li>
								<li style="padding-bottom:22px;"><a href="/shop/goods/hot" style=" font-family:'Raleway', sans-serif; font-weight:600; font-size:18px; color:#2b2b2b; line-height:18px;">HOT100</a></li>
								<li style="padding-bottom:22px;"><a href="/shop/goods/sale" style=" font-family:'Raleway', sans-serif; font-weight:600; font-size:18px; color:#d80000; line-height:18px;">SALE</a></li>
								
								<!-- women -->
								<li style="padding-bottom:22px;" class="mob_mn_dp2_n01"><a href="/shop/goods/goods_list?category=002001" style="font-weight:500; font-size:18px; color:#2b2b2b; line-height:18px;">가방</a></li>
								<li style="padding-bottom:22px;" class="mob_mn_dp2_n01"><a href="/shop/goods/goods_list?category=002002" style="font-weight:500; font-size:18px; color:#2b2b2b; line-height:18px;">지갑</a></li>
								<li style="padding-bottom:22px;" class="mob_mn_dp2_n01"><a href="/shop/goods/goods_list?category=002003" style="font-weight:500; font-size:18px; color:#2b2b2b; line-height:18px;">의류</a></li>
								<li style="padding-bottom:22px;" class="mob_mn_dp2_n01"><a href="/shop/goods/goods_list?category=002004" style="font-weight:500; font-size:18px; color:#2b2b2b; line-height:18px;">슈즈</a></li>
								<li style="padding-bottom:22px;" class="mob_mn_dp2_n01"><a href="/shop/goods/goods_list?category=002005" style="font-weight:500; font-size:18px; color:#2b2b2b; line-height:18px;">악세사리</a></li>
								<li style="padding-bottom:22px;" class="mob_mn_dp2_n01"><a href="/shop/goods/goods_list?category=002006" style="font-weight:500; font-size:18px; color:#2b2b2b; line-height:18px;">쥬얼리/시계</a></li>
								<!-- //women -->

								<!-- men -->
								<li style="padding-bottom:22px;" class="mob_mn_dp2_n02"><a href="/shop/goods/goods_list?category=001001" style="font-weight:500; font-size:18px; color:#2b2b2b; line-height:18px;">가방</a></li>
								<li style="padding-bottom:22px;" class="mob_mn_dp2_n02"><a href="/shop/goods/goods_list?category=001002" style="font-weight:500; font-size:18px; color:#2b2b2b; line-height:18px;">지갑</a></li>
								<li style="padding-bottom:22px;" class="mob_mn_dp2_n02"><a href="/shop/goods/goods_list?category=001003" style="font-weight:500; font-size:18px; color:#2b2b2b; line-height:18px;">의류</a></li>
								<li style="padding-bottom:22px;" class="mob_mn_dp2_n02"><a href="/shop/goods/goods_list?category=001004" style="font-weight:500; font-size:18px; color:#2b2b2b; line-height:18px;">슈즈</a></li>
								<li style="padding-bottom:22px;" class="mob_mn_dp2_n02"><a href="/shop/goods/goods_list?category=001005" style="font-weight:500; font-size:18px; color:#2b2b2b; line-height:18px;">악세사리</a></li>
								<li style="padding-bottom:22px;" class="mob_mn_dp2_n02"><a href="/shop/goods/goods_list?category=001006" style="font-weight:500; font-size:18px; color:#2b2b2b; line-height:18px;">쥬얼리/시계</a></li>
								<!-- //men -->

								<!-- kids -->
								<li style="padding-bottom:22px;" class="mob_mn_dp2_n03"><a href="/shop/goods/goods_list?category=003001" style="font-weight:500; font-size:18px; color:#2b2b2b; line-height:18px;">남아</a></li>
								<li style="padding-bottom:22px;" class="mob_mn_dp2_n03"><a href="/shop/goods/goods_list?category=003002" style="font-weight:500; font-size:18px; color:#2b2b2b; line-height:18px;">여아</a></li>
								<!-- //kids -->

								<li style="padding-bottom:22px;"><a href="/shop/goods/vip" style=" font-family:'Raleway', sans-serif; font-weight:600; font-size:18px; color:#2b2b2b; line-height:18px;">VIP SHOP</a></li>
							</ul>
						</div>
						<div style="padding:0 30px 56px 30px;;">
							<ul>
								<c:choose>
									<c:when test="${ shop_so.isShopLogin() }">
										<li style="float:left; width:50%; padding:0 5px 0 0;">
											<a href="${ctx}/shop/member/logout" style="display:block; height:35px; font-size:16px; color:#fff; line-height:35px; background:#000000; text-align:center;">Logout</a>
										</li>
										<li style="float:left; width:50%; padding:0 0 0 5px;">
											<a href="${ctx}/shop/mypage/main_index" style="display:block; height:35px; font-size:16px; color:#fff; line-height:35px; background:#000000; text-align:center;">My Page</a>
										</li>
									</c:when>
									<c:otherwise>
										<li style="float:left; width:50%; padding:0 5px 0 0;">
											<a href="${ctx}/shop/member/login" style="display:block; height:35px; font-size:16px; color:#fff; line-height:35px; background:#000000; text-align:center;">Sign In</a>
										</li>
										<li style="float:left; width:50%; padding:0 0 0 5px;">
											<a href="${ctx}/shop/member/login" style="display:block; height:35px; font-size:16px; color:#fff; line-height:35px; background:#000000; text-align:center;">Register</a>
										</li>
									</c:otherwise>
								</c:choose>
							</ul>
							<div style="clear:both;"></div>
						</div>
					</div>
				</div>
			</div>

			<script>
				$(".mypage").on("click", function(){
					if('${wishEA }' == '-1'){
						alert("로그인이 필요합니다.");
						$("#nonmember_buy_1").attr('style','display:none');
						$("#nonmember_buy_2").attr('style','display:block');
						$(".btn-pop-login").trigger("click");
					}else{
						location.href= ctx+"/shop/mypage/mypage_orderlist";
					}
					
				});

				$('#favorite').on('click', function(e) { 
					var bookmarkURL = window.location.href; 
					var bookmarkTitle = document.title;
					var triggerDefault = false; 
					if (window.sidebar && window.sidebar.addPanel) { 
						// Firefox version < 23 
						window.sidebar.addPanel(bookmarkTitle, bookmarkURL, ''); 
					} else if ((window.sidebar && (navigator.userAgent.toLowerCase().indexOf('firefox') > -1)) || (window.opera && window.print)) { 
						// Firefox version >= 23 and Opera Hotlist 
						var $this = $(this); 
						$this.attr('href', bookmarkURL); 
						$this.attr('title', bookmarkTitle); 
						$this.attr('rel', 'sidebar'); 
						$this.off(e); 
						triggerDefault = true; 
					} else if (window.external && ('AddFavorite' in window.external)) { 
						// IE Favorite 
						window.external.AddFavorite(bookmarkURL, bookmarkTitle); 
					} else { 
						// WebKit - Safari/Chrome 
						alert((navigator.userAgent.toLowerCase().indexOf('mac') != -1 ? 'Cmd' : 'Ctrl') + '+D 키를 눌러 즐겨찾기에 등록하실 수 있습니다.'); 
					} 
					return triggerDefault; 
				});
			</script>
			<!-- End header.jsp -->