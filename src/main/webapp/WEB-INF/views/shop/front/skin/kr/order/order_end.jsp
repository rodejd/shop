<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>



<%-- ================================================================================
* HTML CONTENT 시작
================================================================================ --%>
<div class="top_tit_ty01">
	<div class="tit_dp01">CHECK OUT</div>
</div>

<c:choose>
	<c:when test="${not empty orderInfo}">
		<!-- 서브 컨텐츠 -->
		<div id="content-area" class="container x-order checkout_wrap">
			<!-- 컨텐츠 -->
			<div class="content">
				
				<div class="check_end">
					<div class="tx01">주문이 완료되었습니다.</div>
					<div class="tx02">
						<p class="t02_01">주문번호 ${orderInfo.ordno}</p>
						<p class="t02_02">예상 적립급 ${shopLibFunction:getExchange(orderInfo.addemoney, 'kr')}</p>
					</div>
				</div>
				
				<c:if test="${orderInfo.settlekind eq 'c'}">
					<div class="ch_btn_btns">
						<a href="/shop/mypage/mypage_orderlist" class="btn01">주문내역 확인하기 </a>
						<a href="/shop/main/index" class="btn02">메인으로 가기</a>
					</div>
				</c:if>
				
				<c:if test="${orderInfo.settlekind eq 'a'}">
					<div class="check_end02">
						<div class="tx01">
							<p>입금계좌 : IK COMMERCE (아이케이커머스)</p>
							<p>계좌번호 : KB국민 929037-01-012299</p>
						</div>
						<div class="tx02">
							입금하실 금액 <span>${shopLibFunction:getExchange(orderInfo.price, 'kr')}(${shopLibFunction:getExchange(orderInfo.price, wskin)})</span>
						</div>
						<div class="tx03">
							무통장 입금 결제시 주문일로부터 24간 이내 입금확인이<span class="b_mob"></span>
							되지 않을 경우<span class="b_pc"></span>해당 주문건은 자동 취소처리가 됩니다.
						</div>
						<div class="tx03">입금 후 1:1 문의 혹은 유선연락 주시면 빠른 주문처리 가능합니다.</div>
					</div>
					<div class="ch_btn_btns">
						<a href="/shop/mypage/mypage_orderlist" class="btn01">주문내역 확인하기 </a>
						<a href="/shop/main/index" class="btn02">메인으로 가기</a>
					</div>
				</c:if>
			</div>
		
		</div>
		<!--// 서브 컨텐츠 -->
		</c:when>
		<c:otherwise>
			<div id="content-area" class="container x-order checkout_wrap">
				<div class="content">
					<div class="check_end">
						<div class="tx01">주문이 내역이 없습니다.</div>
					</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	
<!-- 전환페이지 설정 -->
<script type="text/javascript" src="//wcs.naver.net/wcslog.js"></script> 
<script type="text/javascript"> 
var _nasa={};
if(window.wcs) _nasa["cnv"] = wcs.cnv("1","${orderInfo.price}"); // 전환유형, 전환가치 설정해야함. 설치매뉴얼 참고
</script> 
	
<%-- ================================================================================
* HTML CONTENT 종료
================================================================================ --%>