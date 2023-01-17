<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<div class="left_menu_ty01">
	<aside class="category-filters">
		<div class="category-filters-section">
			<!-- <h3 class="widget-title-sm">
				<b>고객센터</b>
			</h3> -->
			<ul class="cateogry-filters-list">
				<!-- <li ${param.tab_order == 1 ? 'class="active"' : ''}><a href="${ctx }/shop/service/qualityPolicy">품질보증 정책</a></li> -->
				<li ${param.tab_order == 2 ? 'class="active"' : ''}><a href="${ctx }/shop/service/orderPolicy">주문배송 정책</a></li>
				<li ${param.tab_order == 3 ? 'class="active"' : ''}><a href="${ctx }/shop/service/exchangePolicy">교환반품 정책</a></li>
				<li ${param.tab_order == 4 ? 'class="active"' : ''}><a href="${ctx }/shop/service/ratingPolicy">고객등급 정책</a></li>
				<li ${param.tab_order == 5 ? 'class="active"' : ''}><a href="${ctx }/shop/service/payPolicy">결제/가격/적립금 정책</a></li>
				<!-- <li ${param.tab_order == 6 ? 'class="active"' : ''}><a href="${ctx }/shop/service/asPolicy">A/S 정책</a></li> -->
				<li ${param.tab_order == 7 ? 'class="active"' : ''}><a href="${ctx }/shop/service/privacyPolicy">개인정보취급방침</a></li>
			</ul>
		</div>
	</aside>
</div>