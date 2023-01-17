<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<!-- <header class="page-header page-header-banner x-goods-h">
    <div class="container">
        <div class="page-header-banner-inner">
            <h1 class="page-title">DESIGNER</h1>
        </div>
    </div>
</header> -->

<div class="global-wrapper clearfix" id="global-wrapper">
	<div class="top_tit_ty01">
		<div class="tit_dp01">DESIGNER</div>

		<div class="designer_tp_search">
			<div class="in_bx">
				<input type="text" id="txt_search_d"/>
				<a href="#" class="btn_search_d">검색<!-- <i class="zmdi zmdi-search"></i> --></a>
				<p>${frontGoodsBrandVO.rowCount} DESIGNERS</p>
			</div>
		</div>
	
		<c:if test="${fn:length(bestList) > 0}">
			<div class="designer_stit">BEST DESIGNERS</div>
			<div class="designer_list01">
				<ul>
					<c:forEach var="list" items="${bestList}" varStatus="status">
						<li><a href="${ctx}/shop/goods/goods_brand?brandno=${list.sno}"><div class="tb_out"><div class="tb_in">${list.brandnm}</div></div></a></li>
					</c:forEach>
				</ul>
				<div style="clear:both;"></div>
			</div>
		</c:if>

		<div class="designer_list02">
			<ul>
				<c:set var="alphabet" value="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z" />
				<c:forTokens var="a" items="${alphabet}" delims=",">
					<li><a href="#" class="alphabet">${a}</a></li>
				</c:forTokens>
			</ul>
		</div>
		<div class="designer_list03">
			<div class="tx_big">A</div>
			<div>
				<ul>
					<c:forEach var="list" items="${brandList}" varStatus="status">
						<li class="designer${fn:toUpperCase(fn:substring(list.brandnm, 0, 1))}"><a href="${ctx}/shop/goods/goods_brand?brandno=${list.sno}" data-wds="${list.brandnm},${list.brandnmKR},${list.brandMemo}">${list.brandnm}</a></li>
					</c:forEach>
				</ul>
				<div style="clear:both;"></div>
			</div>
		</div>
	</div>
</div>
<br>

<script type="text/javascript">
$(function() {
	$(".alphabet").on("click", function(e) {
		e.preventDefault();
		
		var a = $(this).text();
		$(".designer_list03 .tx_big").text(a).show();
		$(".designer_list03 li").hide();
		$(".designer" + a).show();
	});
	$(".alphabet").eq(0).click();
	
	$("#txt_search_d").keydown(function(key) {
		if (key.keyCode == 13) {
			$(".btn_search_d").click();
		}
	});
	
	$(".btn_search_d").on("click", function(e) {
		e.preventDefault();
		var txt = $("#txt_search_d").val().trim();
		if (txt == "") {
			alert("검색어를 입력해주세요.");
			$("#txt_search_d").focus();
			return;
		}
		
		var a = txt.substring(0, 1).toUpperCase();
		$(".designer_list03 .tx_big").text(a).hide();
		$(".designer_list03 li").each(function(){
			if ($(this).children("a").data("wds").toUpperCase().indexOf(txt.toUpperCase()) > -1) {
				$(this).show();
			} else {
				$(this).hide();
			}
		});
		
		var target = $(".designer_list03").offset().top - $(".header_wrap").outerHeight() - 30;
		window.scrollTo(0, target);
	});
});
</script>
