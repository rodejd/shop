<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>	
	
<div class=title2>&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align=absmiddle><font color=508900>결제금액정보</font></div>

<table class=tb>
	<col class=cellC><col class=cellL>
	<tr>
		<td>주문금액</td>
		<td width=110 align=right>
			<font class=ver8>${shopLibFunction:getExchange(popupVO.goodsPrice + popupVO.rtData.delivery + popupVO.rtData.addDelivery, 'kr')}</font>
		</td>
		<td>
			<img src="/resources/shop/admin/img/arrow_gray.gif" align=absmiddle>
			<font class=small color=444444>상품가격</font> (
			<font color=0074BA class=ver81>${shopLibFunction:getExchange(popupVO.goodsPrice, 'kr')}</font>)
		</td>
	</tr>
	<tr>
		<td>할인액</td>
		<td align=right><font class=ver8>- ₩${popupVO.discount}</font></td>
		<td>
			<c:if test="${not empty popupVO.tmpRt8}">
					<c:forEach items="${popupVO.tmpRt8}" var="tmpRt" varStatus="status">
						<c:if test="${tmpRt.dc > 0.00}">
							<c:if test="${status.index eq 0}">
								<div><img src="/resources/shop/admin/img/arrow_gray.gif" align="absmiddle"> 할인내역</div>
							</c:if>
					
							<div style="margin-top: 5px;">
								<font class=small color=444444> 
								+ ${tmpRt.coupon} (<font color=0074BA class=ver81>${shopLibFunction:getExchange(tmpRt.dc, 'kr')}</font>)
								</font>
							</div>
						</c:if>
					</c:forEach>
				
			</c:if>
		</td>
	</tr>
	<tr>
		<td>결제금액</td>
		<td align=right><font class=ver8>${shopLibFunction:getExchange(popupVO.rtData.prnsettleprice, 'kr')}</font></td>
	</tr>
</table>