<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%//@ include file="/shop/common/inc/jspResource.jsp" %>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<style>
#coupon-layer-popup {
	position : fixed; 
	top : 240px; 
	left : 50%; 
	width : 700px; 
	height : 500px;
	margin-left : -370px; 
	padding : 20px;
	border:1px solid #ddd;
	z-index:10000000000000000000000000000000000000 !important;
}
#coupon-layer-popup .btn-layer-close {top:17px !important; right:15px !important;}
.pop-content {
	height : 400px;
	overflow: auto;
}
</style>

<div class="my_pay_list">
	<ul>
		<c:set var="priceSum" value="0"/>
		<c:set var="reserveSum" value="0"/>
		<c:if test="${not empty frontOrderVO.cartList }">
			<c:forEach var="cartList" items="${frontOrderVO.cartList }" varStatus="status" >
				<c:if test="${status.index eq 0 }">
					<input type="hidden" name="talkGoodsNm" value="${cartList.goodsnmKR}" />
					<input type="hidden" name="talkOptNm" value="${cartList.optName}" />
				</c:if>
				<input type="hidden" name="eaArr" value="${cartList.ea }" />
				<input type="hidden" name="goodsImgArr" value="${empty cartList.img ? '/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/common/noimg_100.gif' : cartList.img}" />
				<input type="hidden" name="priceArr" value="${cartList.price }" />
				<input type="hidden" name="goodsNmArr" value="${cartList.goodsnmKR}" />
				<input type="hidden" name="goodsnoArr" value="${cartList.goodsno}" />
				<input type="hidden" name="optSnoArr" value="${cartList.opt1}" />
			
				<c:set var="reserveInt" value="0"/>
				<c:set var="priceInt" value="0"/>
				<c:set var="eaInt" value="0"/>
				<c:set var="addpriceInt" value="0"/>
				<c:set var="optPrice" value="0" />
				
				<%-- cartList 에 addPriceSum 은 없다. --%>

				<c:if test="${not empty cartList.addPriceSum }">
					<c:set var="optPrice" value="${cartList.addPriceSum }" />
				</c:if>
				
				<c:if test="${not empty cartList.price }">
					<c:set var="priceInt" value="${cartList.price - optPrice }"/>
				</c:if>
				
				
				<c:if test="${not empty cartList.ea }">
					<c:set var="eaInt" value="${cartList.ea }"/>
				</c:if>
				
				<c:if test="${not empty cartList.reserve }">
					<c:set var="reserveInt" value="${cartList.reserve * eaInt}"/>
				</c:if>
				
				<c:set var="reserveSum" value="${reserveInt + reserveSum}"/>
				<c:set var="priceItem" value="${((priceInt + addpriceInt) * eaInt) + optPrice }"/>
				<c:set var="priceSum1" value="${priceSum + priceItem}" scope="request"/>
				<c:set var="priceSum" value="${priceSum + priceItem}" />
				<li>
					<div class="area01_01">
						<div class="buy-goods-img"><a href="/shop/goods/goods_view?goodsno=${cartList.goodsno}&category=${cartList.goods_category}"><img src="${cartList.img }" alt="상품이미지" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/common/noimg_100.gif'" /></a></div>
					</div>
					<div class="area01_02">
						<div class="tx01">${shopLibFunction:getGoodsBrandName(cartList.brandno)}</div>
						<div class="tx02" data-goods-ea="${cartList.ea}" data-goodsno="${cartList.goodsno}" data-goods-price="${cartList.price}" data-goods-category="${cartList.category}">${cartList.goodsnmKR }</div>
						<div class="tx03">
							<h4 class="_goodsno" data-goods-ea="${cartList.ea}" data-goodsno="${cartList.goodsno}" data-goods-price="${cartList.price}" data-goods-category="${cartList.category}">${cartList.goodsnm }</h4>
							<c:if test="${fn:trim(cartList.optName) != '' and cartList.optName != null}">
								<p>[상품옵션 : ${cartList.optName}]</p>
							</c:if>
							<c:if test="${not empty cartList.addopt and 'NULL' ne cartList.addopt}">
								<c:set var="addopt" value="${cartList.addopt }" />
								<%@ include file="../proc/orderitem_opt.jsp"%>
							</c:if>
						</div>
						<div class="tx04">${cartList.ea }개</div>
						<!-- <div>배송비 : ${cartList.delivery }</div> -->
						<div class="_seller-cd${cartList.sellerCd}" style="display:none;"></div>
					</div>
					<div class="area02">
						<div class="tb_out">
							<div class="tb_in">
								<!-- ${shopLibFunction:getExchange(cartList.price, 'kr')} -->
								${shopLibFunction:getExchange(cartList.price, wskin)} 원
							</div>
						</div>
					</div>
					<div class="area03">
						<div class="tb_out">
							<div class="tb_in">
								${shopLibFunction:getExchange(cartList.priceSum, 'kr')}
								<!-- (${shopLibFunction:getExchange(cartList.priceSum, wskin)}) -->
							</div>
						</div>
					</div>
					<div class="_goodsno-td-${cartList.goodsno} _goodsno-td-${cartList.category} area04">
						<div class="tb_out">
							<div class="tb_in">
								-
								<div class="_goodsno-price-${cartList.goodsno} _goodsno-price-${cartList.category} _goods-price-all">
									<c:if test="${not empty frontOrderVO.usedCouponInfoList }">
										<c:forEach var="couponList" items="${frontOrderVO.usedCouponInfoList}" varStatus="status" >
											<c:if test="${couponList.goodsno == cartList.goodsno || couponList.category == cartList.category}">
												<div style="margin-top: 10px;">
													<c:if test="${'' != couponList.category}">
														[카테고리]
													</c:if>
													<c:if test="${'' == couponList.category}">
														[상품쿠폰]
													</c:if>
													${couponList.coupon}
													<c:if test="${fn:indexOf(couponList.price, '%') == -1}">
														<fmt:formatNumber pattern="#,###">${couponList.price}</fmt:formatNumber> 원
													</c:if> 
													<c:if test="${fn:indexOf(couponList.price, '%') > -1}">
														${couponList.price}
													</c:if>
													<!-- abi << 적립구분하는 값은 추후 확인필요 -->
													할인
												</div>
											</c:if>
										</c:forEach>
									</c:if>
								</div>
								<div class="_goodsno-percent-${cartList.goodsno} _goodsno-percent-${cartList.category} _goods-percent-all text-align:center;">
									<c:if test="${not empty frontOrderVO.usedCouponInfoList }">
										<c:forEach var="couponList" items="${frontOrderVO.usedCouponInfoList}" varStatus="status" >
										</c:forEach>
									</c:if>
								</div>
							</div>
						</div>
					</div>
					<div class="area05">
						<div class="tb_out">
							<div class="tb_in">예상적립: ${reserveSum}</div>
						</div>
					</div>
					<div style="clear:both;"></div>
				</li>
			</c:forEach>
		</c:if>
		<div style="clear:both;"></div>
	</ul>
</div>
