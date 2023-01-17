<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>


<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<header class="page-header page-header-banner x-service-h">
	<div class="container">
		<div class="page-header-banner-inner">
			<h3 class="page-title">고객센터</h3>
		</div>
	</div>
</header>

<div class="x-customer container">
	<div class="tabbable product-tabs">
		<!-- 고객센터 공통탭메뉴 처리 -->
		<jsp:include page="service_tab_menu.jsp" flush="true"/>

		<div class="col-md-10">
			<div class="tab-content nb">
				<div class="tab-pane fade in active">
					<div class="row row-col-gap">
						<div class="col-md-12">
							<div class="row row-eq-height product-overview-section">
								<div class="col-md-12">
									<img class="product-overview-img img-style-01" src="/resources/shop/data/skin/default2/images/contact-us-bc.jpg" alt="Image Alternative text" title="Image Title"/>
								</div>
							</div>
							<div class="row contact-wrap">
								<div class="col-md-6">
									<div class="product-overview-text">
									<h6 class="widget-title"><i class="fa fa-phone"></i> 연락처</h6>
			                            <ul class="contact-list">
			                                <li>
			                                    <h5>Phone Number</h5>
			                                    <p>${serviceVO.cusCompPhone}</p>
			                                </li>
			                                <li>
			                                    <h5>FAX Number</h5>
			                                    <p>${serviceVO.cusCompFax}</p>
			                                </li>
			                                <li>
			                                    <h5>Email</h5><a href="#">${serviceVO.cusAdminEmail}</a>
			                                </li>
		                                </ul>
									</div>
								</div>
								<div class="col-md-6">
									<div class="product-overview-text">
									<h6 class="widget-title"><i class="fa fa-clock-o"></i> 근무시간</h6>
			                            <ul class="contact-list">
			                                <li>
			                                    <h5>평일</h5>
			                                    <p>09:00 ~ 18:00</p>
			                                </li>
			                                <li>
			                                    <h5>토요일</h5>
			                                    <p>09:00 ~ 13:00</p>
			                                </li>
			                                <li>
			                                    <h5>일요일 공휴일 휴무</h5><a href="#">휴무</a>
			                                </li>
			                            </ul>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="row row-col-gap">
						<div class="col-md-6">
							<div class="list-group">
								<a href="#" class="list-group-item s-title">입금계좌안내</a>
								<c:if test="${ !empty(bankList) }">
									<c:forEach items="${bankList}" var="bankList" varStatus="status">
										<a href="#" class="list-group-item">${bankList.bank}(${bankList.name})${bankList.account}</a>
									</c:forEach>
								</c:if>
							</div>
						</div>
						<!-- <div class="col-md-4">
							<div class="list-group">
							  <a href="#" class="list-group-item list-group-item-info">마이쇼핑</a>
							  <a href="../mypage/mypage_orderlist" class="list-group-item">주문내역/배송조회</a>
							  <a href="../mypage/mypage_emoney" class="list-group-item">적립금내역</a>
							  <a href="../mypage/mypage_coupon" class="list-group-item">할인쿠폰내역</a>
							  <a href="../mypage/mypage_wishlist" class="list-group-item">상품보관함</a>
							  <a href="../mypage/mypage_qna" class="list-group-item">1:1문의게시판</a>
							  <a href="../mypage/mypage_review" class="list-group-item">나의 상품후기</a>
							</div>
						</div> -->
						<div class="col-md-6">
							<div class="list-group">
							  <a href="#" class="list-group-item s-title">자주하는 질문 BEST5</a>
							  	<c:if test="${!empty(faqList)}">
									<c:forEach items="${faqList}" var="faqList" varStatus="status">
										<a href="../service/faq?sitemcd=${faqList.itemcd}&ssno=${faqList.sno }" class="list-group-item">[${faqList.itemnm}] ${faqList.question}</a>
										<%--<img src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/common/cs_num_${faqList.itemcd}.gif" align=absmiddle> 
										분류번호 대신 분류이름 으로 하기위해 주석처리 기존엔 위 a태그 안에 삽입되어있음--%>
							  		</c:forEach>
								</c:if>
							</div>
						</div>
					</div>
					
					<div class="row" data-gutter="15">
						<div class="col-md-6">
							<div class="banner" style="height:200px; border:2px solid #ddd;">
								<a class="banner-link" href="../mypage/mypage_qna"></a>
								<div class="banner-caption-left banner-caption-dark02">
									<h5 class="banner-title">1:1친절상담</h5>
									<p class="banner-desc">모든 상담! 친절 답변!</p>
									<p class="banner-shop-now">
										GO <i class="fa fa-caret-right"></i>
									</p>
								</div>
								<img class="banner-img" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/test_banner/28-i.png" alt="Image Alternative text" title="Image Title" style="bottom: 0px; right: 0px; width: 240px;" />
							</div>
						</div>
						<div class="col-md-6">
							<div class="banner" style="height:200px; background-color:#ddd;">
								<a class="banner-link" href="../service/guide"></a>
								<div class="banner-caption-left">
									<h5 class="banner-title">이용안내</h5>
									<p class="banner-desc">쇼핑몰 사용법을 배워요</p>
									<p class="banner-shop-now">
										GO<i class="fa fa-caret-right"></i>
									</p>
								</div>
								<img class="banner-img" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/test_banner/29-i.png" alt="Image Alternative text" title="Image Title" style="bottom: 0px; right: 0; width: 238px;" />
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>


	</div>
	<div class="gap"></div>
</div>
<div class="gap"></div>
