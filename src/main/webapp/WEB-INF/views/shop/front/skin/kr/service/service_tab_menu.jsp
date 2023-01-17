<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script>
$(document).ready(function(){
	$(".q_list").click(function(){
		$(this).parent().find(".q_list").removeClass("on");
		$(this).addClass("on");
	});
});
</script>
<style>
.product-page-qa-question:before {position:absolute; top:0; left:38px; font-weight:400; font-size:17px; color:#2b2b2b; line-height:17px; content: 'Q.';}
.q_list.on .product-page-qa-question:before {color:#838383;}
.product-page-qa-answer:before {position:absolute; top:50px; left:38px; font-weight:400; font-size:17px; color:#2b2b2b; line-height:23px; content: 'A.';}
</style>

<div class="left_menu_ty01">
	<aside class="category-filters">
		<div class="category-filters-section">
			<!-- <h3 class="widget-title-sm">
				<b>고객센터</b>
			</h3> -->
			<ul class="cateogry-filters-list">
				<li ${param.tab_order == 3 ? 'class="active"' : ''}><a href="${ctx }/shop/mypage/mypage_qna">1:1문의게시판</a></li>
				<li ${param.tab_order == 1 ? 'class="active"' : ''}><a href="${ctx }/shop/service/faq?sitemcd=01">FAQ</a></li><!-- /faq?pageNo=1 -->
				<li ${param.tab_order == 7 ? 'class="active"' : ''}><a href="${ctx }/shop/board/list?id=notice">공지사항</a></li>
				<li ${param.tab_order == 8 ? 'class="active"' : ''}><a href="${ctx }/shop/service/agreement">이용약관</a></li>
				<li ${param.tab_order == 9 ? 'class="active"' : ''}><a href="${ctx }/shop/service/company">회사소개</a></li>
				<!-- <li ${param.tab_order == 2 ? 'class="active"' : ''}><a href="${ctx }/shop/service/guide">이용안내</a></li>
				<li ${param.tab_order == 4 ? 'class="active"' : ''}><a href="${ctx }/shop/member/find_id">ID찾기</a></li>
				<li ${param.tab_order == 5 ? 'class="active"' : ''}><a href="${ctx }/shop/member/find_pwd">비밀번호찾기</a></li>
				<li ${param.tab_order == 6 ? 'class="active"' : ''}><a href="${ctx }/shop/member/myinfo">마이페이지</a></li> -->
			</ul>
		</div>
	</aside>
</div>