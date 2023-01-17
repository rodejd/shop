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

<header class="page-header page-header-banner x-service-h">
	<div class="container">
		<div class="page-header-banner-inner">
			<h1 class="page-title">사이트맵</h1>
		</div>
	</div>
</header>

<div class="x-sitemap container">
	<div class="tabbable product-tabs">
		<div class="col-md-12">
			<div class="tab-content nb">
				<%-- <div class="row">
					<div class="col-md-12">
						<h3>Category</h3>
						<hr/>
						<ul class="product-accessory-list">
							<li class="product-accessory">
								<c:if test="${!empty(appMenuCategoryList)}">
									<c:forEach items="${appMenuCategoryList}"  var="appMenuCategoryList"  varStatus="status">
										
										<c:if test="${ '3' eq  appMenuCategoryList.cateLength}" >
											<c:if test="${!status.first}">
													</p>
												</div>
											</div>
											<div class="gap-small"></div>
											<hr/>
											</c:if>
											
											<div class="row">
												<div class="col-md-12">
													<h3><a href="../goods/goods_list.jsp?category=${ appMenuCategoryList.category }"><i class="fa fa-gittip padding-right-sm"></i> ${ appMenuCategoryList.catnm }</a></h3>
													<c:if test="${!status.last}">
													<p class="text-success"> <i class="fa fa-ellipsis-v"></i>
													</c:if>
											
											<c:if test="${status.last}">
													<p class="text-success">등록된 하위 상품이 존재하지 않습니다.</p>
												</div>
											</div>
											<div class="gap-small"></div>
											<hr/>
											</c:if>
										</c:if>
										
										<c:if test="${ '3' ne  appMenuCategoryList.cateLength && appMenuCategoryList.hidden eq 0}" >
										<c:if test="${ '3' ne  appMenuCategoryList.cateLength}" >
											<a href="../goods/goods_list.jsp?category=${ appMenuCategoryList.category }">${ appMenuCategoryList.catnm }</a> <i class="fa fa-ellipsis-v"></i> 
											<c:if test="${!status.last}"> | </c:if>
										</c:if>
										
									</c:forEach>
								</c:if>
								
							</li>
						</ul>
					</div>
				</div> --%>
				<div class="row">
					<div class="col-md-4">
						<div class="list-group">
						  <a href="#" class="list-group-item list-group-item-sitemap">MemberShip(회원관련)</a>
						  <a href="${ctx }/shop/member/login" class="list-group-item">로그인</a>
						  <a href="${ctx }/shop/service/agreement" class="list-group-item">전자상거래 이용약관</a>
						  <a href="${ctx }/shop/member/join" class="list-group-item">회원가입</a>
						  <a href="${ctx }/shop/member/find_id" class="list-group-item">아이디찾기</a>
						  <a href="${ctx }/shop/member/find_pwd" class="list-group-item">비밀번호찾기</a>
						  <a href="${ctx }/shop/member/myinfo" class="list-group-item">회원정보수정</a>
						  <a href="${ctx }/shop/member/hack" class="list-group-item">회원탈퇴</a>
						</div>
						
						<div class="list-group">
						  <a href="#" class="list-group-item list-group-item-sitemap">MemberShip(나의글모음)</a>
						  <a href="${ctx }/shop/mypage/mypage_qna" class="list-group-item">1:1문의</a>
						  <a href="${ctx }/shop/mypage/mypage_qna_goods" class="list-group-item">상품Q&A</a>
						  <a href="${ctx }/shop/mypage/mypage_review" class="list-group-item">이용후기</a>
						</div>
					</div>
					<div class="col-md-4">
						<div class="list-group">
						  <a href="#" class="list-group-item list-group-item-sitemap">Service</a>
						  <a href="${ctx }/shop/goods/goods_cart" class="list-group-item">장바구니</a>
						  <a href="${ctx }/shop/mypage/mypage_orderlist" class="list-group-item">주문/배송조회</a>
						  <a href="${ctx }/shop/goods/goods_search" class="list-group-item">상세검색</a>
						  <a href="${ctx }/shop/mypage/mypage_today" class="list-group-item">최근본상품</a>
						  <a href="${ctx }/shop/mypage/mypage_emoney" class="list-group-item">적립금</a>
						  <a href="${ctx }/shop/mypage/mypage_wishlist" class="list-group-item">상품보관함</a>
						</div>
						
						<div class="list-group">
						  <a href="#" class="list-group-item list-group-item-sitemap">Community</a>
						  <a href="${ctx }/shop/board/list?id=notice" class="list-group-item">공지사항</a>
						  <a href="${ctx }/shop/mypage/mypage_review" class="list-group-item">이용후기</a>
						  <a href="${ctx }/shop/mypage/mypage_qna_goods" class="list-group-item">상품문의</a>
						</div>
					</div>
					<div class="col-md-4">
						<div class="list-group">
						  <a href="#" class="list-group-item list-group-item-sitemap">Customer Center</a>
						  <a href="${ctx }/shop/service/customer" class="list-group-item">고객센터</a>
						  <a href="${ctx }/shop/mypage/mypage_qna" class="list-group-item">1:1문의</a>
						  <a href="${ctx }/shop/member/myinfo" class="list-group-item">마이페이지</a>
						  <a href="${ctx }/shop/service/company" class="list-group-item">회사소개</a>
						  <a href="${ctx }/shop/service/private" class="list-group-item">개인정보보호정책</a>
						  <a href="${ctx }/shop/service/faq" class="list-group-item">자주하는질문</a>
						  <a href="${ctx }/shop/service/cooperation" class="list-group-item">광고/제휴문의</a>
						  <a href="${ctx }/shop/service/guide" class="list-group-item">이용안내</a>
						  <a href="${ctx }/shop/service/sitemap" class="list-group-item">사이트맵</a>
						  
						</div>
					</div>
				</div>
				
				<div class="gap-small"></div>
			</div>
		</div>
	</div>
	<div class="gap-small"></div>
</div>
<div class="gap-small"></div>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>
