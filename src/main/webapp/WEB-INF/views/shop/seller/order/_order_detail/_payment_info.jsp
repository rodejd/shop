<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
	
<div class=title2>&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align=absmiddle><font color=508900>결제금액정보</font></div>

<table class=tb>
	<col class=cellC><col class=cellL>
	<tr>
		<td>주문금액</td>
		<td width=110 align=right>
			<font class=ver8><fmt:formatNumber value="${popupVO.goodsPrice + popupVO.rtData.delivery + popupVO.rtData.addDelivery}" pattern="#,###"/></font>원
		</td>
		<td>
			<img src="/resources/shop/admin/img/arrow_gray.gif" align=absmiddle>
			<font class=small color=444444>상품총가격</font> (
			<font color=0074BA class=ver81><fmt:formatNumber value="${popupVO.goodsPrice}" pattern="#,###"/></font>원)
			<c:if test="${popupVO.rtData.delivery != 0}">
				+ 배송비 선불 (<font color=0074BA class=ver81><fmt:formatNumber value="${popupVO.rtData.delivery}" pattern="#,###"/></font>원)
			</c:if>
			<c:if test="${popupVO.rtData.addDelivery != 0}">
				+ 추가배송비 (<font color=0074BA class=ver81><fmt:formatNumber value="${popupVO.rtData.addDelivery}" pattern="#,###"/></font>원)
			</c:if>
		</td>
	</tr>
	<tr>
		<td>할인액</td>
		<td align=right><font class=ver8>- <fmt:formatNumber value="${popupVO.discount}" pattern="#,###"/></font>원</td>
		<td>
			<img src="/resources/shop/admin/img/arrow_gray.gif" align=absmiddle>
			<font class=small color=444444>
				<c:if test="${popupVO.memberDc > 0 }">
					회원할인 (<font color=0074BA class=ver81><fmt:formatNumber value="${popupVO.memberDc}" pattern="#,###"/></font>원)
				</c:if>
				<c:if test="${popupVO.coupon > 0}">
					+ 쿠폰할인 (<font color=0074BA class=ver81><fmt:formatNumber value="${popupVO.coupon}" pattern="#,###"/></font>원)
				</c:if>
				<!-- 20191203 이현빈 판매사 적립금 내용 삭제  -->
				<%-- <c:if test="${popupVO.rtData.emoney > 0 }">
					+ 적립금사용 (<font color=0074BA class=ver81><fmt:formatNumber value="${popupVO.rtData.emoney}" pattern="#,###"/></font>원)
				</c:if> --%>
				<%-- 2017-08-24 : 에누리에 대한 정확한 의미를 알 수 없어 논의 후 주석처리 함. (지은정)
				에누리 <input type=text name=enuri value='<fmt:formatNumber value="${popupVO.rtData.enuri}" pattern="#,###"/>' size=6 class='ver81 right' style='color:#0074BA'> 원 --%>
			</font>
		</td>
	</tr>
	<%-- <tr>
		<td>결제금액</td>
		<td align=right><font color=0074BA class=ver8><b><fmt:formatNumber value="${popupVO.rtData.settleprice}" pattern="#,###"/></b></font>원</td>
		<td>
			<font class=small color=444444>
				<c:if test="${popupVO.settleprice != popupVO.rtData.settleprice }">
					<img src="/resources/shop/admin/img/arrow_gray.gif" align=absmiddle>
					최초주문금액  (
					<font color=0074BA class=ver81>
						<fmt:formatNumber value="${popupVO.rtData.settleprice}" pattern="#,###"/>
					</font>원) / 취소금액 합계 (
				 	<font color=0074BA class=ver81>
				 		<fmt:formatNumber value="${popupVO.settleprice - popupVO.rtData.settleprice}" pattern="#,###"/>
			 		</font>원)
				</c:if>
			</font>
		</td>
	</tr> --%>
</table>
	