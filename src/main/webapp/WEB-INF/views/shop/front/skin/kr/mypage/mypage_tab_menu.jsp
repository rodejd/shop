<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%
	String tab_order = request.getParameter("tab_order");
%>

<style>
@media only screen and (min-width : 721px) {
.left_menu_ty01.mypage li.active a {text-decoration:underline;}

input.ck_my_ty01 {width:22px; height:22px; margin:0 8px 0 0; border:none; background:url('/resources/shop/data/skin/kr/images/bg_ck01_off.png') no-repeat left top; background-size:22px 22px; -webkit-appearance:none; -webkit-border-radius:0; -moz-appearance:none; vertical-align:top;}
input.ck_my_ty01:checked {border:none; background:url('/resources/shop/data/skin/kr/images/bg_ck01_on.png') no-repeat left top; background-size:22px 22px; -webkit-appearance:none; -webkit-border-radius:0; -moz-appearance:none; vertical-align:top;}

input.ck_my_rad01 {width:20px; height:20px; margin:0 8px 0 0; border:none; background:url('/resources/shop/data/skin/kr/images/bg_rad01_off.png') no-repeat left top; background-size:20px 20px; -webkit-appearance:none; -webkit-border-radius:0; -moz-appearance:none; vertical-align:top;}
input.ck_my_rad01:checked {border:none; background:url('/resources/shop/data/skin/kr/images/bg_rad01_on.png') no-repeat left top; background-size:20px 20px; -webkit-appearance:none; -webkit-border-radius:0; -moz-appearance:none; vertical-align:top;}
}
@media only screen and (max-width : 721px) {
.left_menu_ty01.mypage li.active a {text-decoration:underline;}

input.ck_my_ty01 {position:absolute; top:0; left:0; width:15px; height:15px; margin:0 8px 0 0; border:none; background:url('/resources/shop/data/skin/kr/images/bg_ck01_off.png') no-repeat left top; background-size:15px 15px; -webkit-appearance:none; -webkit-border-radius:0; -moz-appearance:none; vertical-align:top;}
input.ck_my_ty01:checked {border:none; background:url('/resources/shop/data/skin/kr/images/bg_ck01_on.png') no-repeat left top; background-size:15px 15px; -webkit-appearance:none; -webkit-border-radius:0; -moz-appearance:none; vertical-align:top;}

input.ck_my_rad01 {width:15px; height:15px; margin:3px 8px 0 0; border:none; background:url('/resources/shop/data/skin/kr/images/bg_rad01_off.png') no-repeat left top; background-size:15px 15px; -webkit-appearance:none; -webkit-border-radius:0; -moz-appearance:none; vertical-align:top;}
input.ck_my_rad01:checked {border:none; background:url('/resources/shop/data/skin/kr/images/bg_rad01_on.png') no-repeat left top; background-size:15px 15px; -webkit-appearance:none; -webkit-border-radius:0; -moz-appearance:none; vertical-align:top;}
}
</style>
<div class="left_menu_ty01 mypage">
	<!-- <aside class="category-filters">
		<div class="category-filters-section">
			<h3 class="widget-title-sm" >
				<b>????????????</b>
			</h3>
			<ul class="cateogry-filters-list" >
				<li>???<b>??????</b> : ${shop_so.getUserInfo().getUserName()}</li>
				<li>???<b>????????????</b> : ${shopLibFunction:getExchange(shop_so.getUserInfo().getXkey().sum_sale, wskin)}</li>
				<li>???<b>?????????</b> : ${shopLibFunction:getExchange(shop_so.getUserInfo().getXkey().emoney, wskin)}</li>
				<li>???<b>????????????</b> : ${stringUtil:getMoneyFormatInteger(shop_so.getUserInfo().getXkey().boxcnt)}???</li>
			</ul>
		</div>
	</aside>
	
	<div class="gap gap-small"></div> -->
	
	<aside class="category-filters">
		<div class="category-filters-section">
			<div style="font-weight:600; font-size:20px; color:#313131; line-height:20px;">????????????</div>
			<ul style="padding:18px 0 22px 18px;">
				<li <%="3".equals(tab_order) ? "class='active'" : ""%> style="padding:0 0 18px 0;"><a href="${ctx }/shop/mypage/mypage_orderlist" style="font-size:17px; color:#313131; line-height:17px;">??????????????????</a></li>
			</ul>

			<div style="font-weight:600; font-size:20px; color:#313131; line-height:20px;">????????????</div>
			<ul style="padding:18px 0 22px 18px;">
				<li <%="5".equals(tab_order) ? "class='active'" : ""%> style="padding:0 0 18px 0;"><a href="${ctx }/shop/mypage/mypage_coupon" style="font-size:17px; color:#313131; line-height:17px;">?????????</a></li>
				<li <%="4".equals(tab_order) ? "class='active'" : ""%> style="padding:0 0 18px 0;"><a href="${ctx }/shop/mypage/mypage_emoney" style="font-size:17px; color:#313131; line-height:17px;">??????????????? </a></li>
			</ul>

			<div style="font-weight:600; font-size:20px; color:#313131; line-height:20px;">????????????</div>
			<ul style="padding:18px 0 22px 18px;">
				<li <%="6".equals(tab_order) ? "class='active'" : ""%> style="padding:0 0 18px 0;"><a href="${ctx }/shop/mypage/mypage_wishlist" style="font-size:17px; color:#313131; line-height:17px;">???????????????</a></li>
				<li <%="10".equals(tab_order) ? "class='active'" : ""%> style="padding:0 0 18px 0;"><a href="/shop/goods/goods_cart" style="font-size:17px; color:#313131; line-height:17px;">????????????</a></li>
				<li <%="9".equals(tab_order) ? "class='active'" : ""%> style="padding:0 0 18px 0;"><a href="${ctx}/shop/mypage/mypage_qna_goods" style="font-size:17px; color:#313131; line-height:17px;">?????? ????????????</a></li>
				<li <%="8".equals(tab_order) ? "class='active'" : ""%> style="padding:0 0 18px 0;"><a href="${ctx}/shop/mypage/mypage_review" style="font-size:17px; color:#313131; line-height:17px;">????????????</a></li>
			</ul>

			<div style="font-weight:600; font-size:20px; color:#313131; line-height:20px;">????????????</div>
			<ul style="padding:18px 0 22px 18px;">
				<li <%="1".equals(tab_order) ? "class='active'" : ""%> style="padding:0 0 18px 0;"><a href="${ctx }/shop/member/myinfo" style="font-size:17px; color:#313131; line-height:17px;">??????????????????</a></li>
				<!-- <li <%="2".equals(tab_order) ? "class='active'" : ""%> style="padding:0 0 18px 0;"><a href="${ctx }/shop/member/hack" style="font-size:17px; color:#313131; line-height:17px;">????????????</a></li> -->
			</ul>



			<!-- <h3 class="widget-title-sm">
				<b>????????????</b>
			</h3>
			<ul class="cateogry-filters-list">
				<li <%="1".equals(tab_order) ? "class='active'" : ""%>><a href="${ctx }/shop/member/myinfo">??????????????????</a></li>
				<li <%="2".equals(tab_order) ? "class='active'" : ""%>><a href="${ctx }/shop/member/hack">????????????</a></li>
			</ul>
			<h3 class="widget-title-sm" >
				<b>???????????????</b>
			</h3>
			<ul class="cateogry-filters-list">
				<li <%="3".equals(tab_order) ? "class='active'" : ""%>><a href="${ctx }/shop/mypage/mypage_orderlist">????????????/????????????</a></li>
				<li <%="4".equals(tab_order) ? "class='active'" : ""%>><a href="${ctx }/shop/mypage/mypage_emoney">???????????????</a></li>
				<li <%="5".equals(tab_order) ? "class='active'" : ""%>><a href="${ctx }/shop/mypage/mypage_coupon">??????????????????</a></li>
				<li <%="6".equals(tab_order) ? "class='active'" : ""%>><a href="${ctx }/shop/mypage/mypage_wishlist">???????????????</a></li>
			</ul>
			<h3 class="widget-title-sm" onclick="javascript:location.href='${ctx}/shop/mypage/mypage_qna'" style="cursor:pointer;">
				<b <%="7".equals(tab_order) ? "style='background-color:#e4e4e4;'" : ""%>>1:1???????????????</b>
			</h3>
			<h3 class="widget-title-sm" onclick="javascript:location.href='${ctx}/shop/mypage/mypage_review'" style="cursor:pointer;">
				<b <%="8".equals(tab_order) ? "style='background-color:#e4e4e4;'" : ""%>>?????? ????????????</b>
			</h3>
			<h3 class="widget-title-sm" onclick="javascript:location.href='${ctx}/shop/mypage/mypage_qna_goods'" style="cursor:pointer;">
				<b <%="9".equals(tab_order) ? "style='background-color:#e4e4e4;'" : ""%>>?????? ????????????</b>
			</h3>
			<h3 class="widget-title-sm" onclick="javascript:location.href='${ctx}/shop/mypage/mypage_today'" style="cursor:pointer;">
				<b <%="10".equals(tab_order) ? "style='background-color:#e4e4e4;'" : ""%>>?????? ??? ????????????</b>
			</h3> -->
		</div>
	</aside>
</div>

