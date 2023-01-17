<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<!-- 쿠폰 레이어 팝업 -->
	<div class="p_mob_in_n">
		<div class="<!--pop-tit-wrap-->">
			<h1 class="pop_tit_s_n">할인쿠폰</h1>
			<a href="#" class="btn-layer-close" style="top:80;">닫기</a>
		</div>
		<div class="pop-content">
			<table summary="" class="table table table-shopping-cart pop_coupon_web">
				<colgroup>
					<col />
					<col style="width:16%;" />
					<col style="width:16%;" />
					<col style="width:22%;" />
					<col style="width:16%;" />
					
				</colgroup>
				<thead>
					<tr>
						<th scope="col">쿠폰</th>
						<th scope="col">유효기간</th>
						<th scope="col">할인금액</th>
						<th scope="col">사용조건</th>
						<th scope="col">선택</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${ !empty(couponList) }">               
							<c:forEach var="Cou" items="${couponList}" varStatus="var_Cou">
								<tr>
									<td>${Cou.coupon}</td>
									<td>${Cou.sdate} ~ ${Cou.edate}</td>
									<td>${Cou.price}</td>
									<td>₩${Cou.excPrice } 이상 구매 시</td>
									<td>
										<a href="javascript:;" class="btn btn-primary btnCouponAdd" <%-- btnCouponAdd-개발추가 --%>
										   data-applysno="${Cou.applysno}" <%-- 적용일련번호 --%> 
										   data-couponcd="${Cou.couponcd}" <%-- 쿠폰번호 --%>
										   data-couponnm="${Cou.coupon}" <%-- 쿠폰명 --%>
										   data-price="${Cou.price}" <%-- 쿠폰금액 --%>
										   data-maxprice="${Cou.maxprice}"  <%-- 최대 할인액 --%>
										   data-excPrice="${Cou.excPrice}" <%-- 쿠폰사용제한(이상 구매시에만 사용가능) --%>
											<span class="txt-yellow">
												선택
											</span>
										</a>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="5"><b>보유하신 쿠폰이 없습니다.</b></td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>

			<div class="pop_coupon_mob">
				<c:choose>
					<c:when test="${ !empty(couponList) }">               
						<c:forEach var="Cou" items="${couponList}" varStatus="var_Cou">
							<ul>
								<li style="position:relative; padding:20px 0; border-bottom:1px solid #ddd;">
									<div style="font-weight:bold; font-size:14px;">${Cou.coupon}</div>
									<div style="font-size:12px; padding:8px 0 0 0;">유효기간 : ${Cou.sdate} ~ ${Cou.edate}</div>
									<div style="font-size:12px;">할인금액 : ${Cou.price}</div>
									<div style="font-size:12px;">사용조건 : ₩${Cou.excPrice } 이상 구매 시</div>
									<div style="position:absolute; top:20px; right:0;">
										<a href="javascript:;" style="font-size:11px; color:#fff; background:#000; border:none;" class="btn btn-primary btnCouponAdd" <%-- btnCouponAdd-개발추가 --%>
										   data-applysno="${Cou.applysno}" <%-- 적용일련번호 --%> 
										   data-couponcd="${Cou.couponcd}" <%-- 쿠폰번호 --%>
										   data-couponnm="${Cou.coupon}" <%-- 쿠폰명 --%>
										   data-price="${Cou.price}" <%-- 쿠폰금액 --%>
										   data-maxprice="${Cou.maxprice}"  <%-- 최대 할인액 --%>
										   data-excPrice="${Cou.excPrice}" <%-- 쿠폰사용제한(이상 구매시에만 사용가능) --%>
											<span class="txt-yellow">
												선택
											</span>
										</a>
									</div>
								</li>
							</ul>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5"><b>보유하신 쿠폰이 없습니다.</b></td>
						</tr>
					</c:otherwise>
				</c:choose>
			</div>
		</div> 
	</div>

<style>
@media only screen and (min-width : 721px) {
#coupon-layer-popup {
position: fixed;
top: 240px;
left: 50%;
width: 700px;
height: 500px;
margin-left: -370px;
padding: 20px;
border: 1px solid #ddd;
z-index: 10000000000000000000000000000000000000 !important;
}
.pop_coupon_web {display:block;}
.pop_coupon_mob {display:none;}
.pop_tit_s_n {}
#coupon-layer-popup .btn-layer-close {}
}
@media only screen and (max-width : 721px) {
#coupon-layer-popup {
position: fixed;
top: 140px;
left: 0;
width: 100%;
height: 490px;
margin: 0 auto;
padding: 0;
border: none;
z-index: 10000000000000000000000000000000000000 !important;
background:none !important;
}
.pop_coupon_web {display:none;}
.pop_coupon_mob {display:block;}
.pop_tit_s_n {font-size:20px;}
#coupon-layer-popup .btn-layer-close {top:14px !important;}
.p_mob_in_n {padding:15px;margin:0 10px; background:#fff;}
}
</style>