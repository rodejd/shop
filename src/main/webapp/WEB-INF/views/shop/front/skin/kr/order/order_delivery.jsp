<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 선불배송비가 없으면 무료 표시, 있으면 금액 표시 --%>
<c:if test="${frontOrderVO.totalDeliveryInfoVO.prepaidDelivery != null}">

	<p id="paper_delivery_msg1">배송비 선불 : 
		<c:choose>
			<c:when test="${frontOrderVO.totalDeliveryInfoVO.prepaidDelivery == '0'}">
				무료
			</c:when>
			<c:otherwise>
				<span id="paper_delivery">
					<fmt:formatNumber pattern="#,###">${frontOrderVO.totalDeliveryInfoVO.prepaidDelivery }</fmt:formatNumber>
				</span> 원
			</c:otherwise>
		
		</c:choose>
	</p>
</c:if>

<%-- 후불배송비 표시 --%>
<c:choose>
	<c:when test="${frontOrderVO.totalDeliveryInfoVO.postpaidDelivery != null }">
		<c:if test="${frontOrderVO.totalDeliveryInfoVO.postpaidDelivery != '0'}">
			<p id="paper_delivery_msg1">배송비 착불 : 
				<fmt:formatNumber pattern="#,###">${frontOrderVO.totalDeliveryInfoVO.postpaidDelivery }</fmt:formatNumber> 원
			</p>
		</c:if>
	</c:when>
	<c:otherwise>
		<p id="paper_delivery_msg1">배송비 착불 무료</p>
	</c:otherwise>
</c:choose>
<div class="js_addDelivery" style="display:none;">
	추가배송비 : <label class="js_addDeliverySido"></label>(<label class="js_addDeliveryPrice">0</label>원 )
</div>
