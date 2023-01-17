<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<div class=title2>
	&nbsp;
	<img src="/resources/shop/admin/img/icon_process.gif" align=absmiddle>
	<font color=508900>
		배송정보
	</font>
</div>

<table class=tb>
	<tr>
		<td class="cellC cellM">송장번호</td>
		<td class="cellC cellM">지불방식</td>
		<td class="cellC cellM">배송상태</td>
		<td class="cellC cellM">배송일</td>
		<td class="cellC cellM">반품 택배사</td>
		<td class="cellC cellM">반품 송장번호</td>
	</tr>
		<tr>
			<td class="cellM">
				<c:choose>
					<c:when test="${popupVO.deliveryInfo.level == 6}">
						<input type="hidden" name="deliverycode" value="${popupVO.deliveryInfo.invoice}" class="line" />
						<input type="hidden" name="deliveryno" value="${popupVO.deliveryInfo.deliveryCompCd}" class="line" />
						
						<font color="0074BA">
							<c:forEach items="${popupVO.deliveryCompList}" var="deliveryComp">
								<c:if test="${deliveryComp.deliveryno eq popupVO.deliveryInfo.deliveryCompCd}">
									${deliveryComp.deliverycomp} / 
								</c:if> 
							</c:forEach>
							${popupVO.deliveryInfo.invoice}
						</font>
						<div>
							<font class=small1 color=444444>이미 배송이 완료된 상품입니다.</font>
						</div>	
					</c:when>
					
					<%-- 20191126 이현빈  주문상태 취소일 시  --%>
					<c:when test="${popupVO.deliveryInfo.istep eq 56 or popupVO.deliveryInfo.istep eq 40 or popupVO.deliveryInfo.istep >= 80}">
						<font class=small1 color=444444>주문상태 : ${shopLibFunction:r_stepi(popupVO.rtData.step, popupVO.deliveryInfo.istep) }</font>
					</c:when>
					
					<c:when test="${( (popupVO.rtData.step >= 1 and popupVO.rtData.step <= 4) or (popupVO.rtData.step >= 10 and popupVO.rtData.step <= 12) ) }">
					<%-- //${popupVO.rtData.step } //${popupVO.deliveryInfo.istep } --%>
					
								<select name=deliveryno class="_delivery-select" style="width: 100px;">
									<option value="00">==택배사==</option>
									<c:forEach items="${popupVO.deliveryCompList}" var="deliveryComp">
										<option value="${deliveryComp.deliveryno}" ${deliveryComp.deliveryno eq popupVO.deliveryInfo.deliveryCompCd ? 'selected' : ''}>${deliveryComp.deliverycomp}</option>
									</c:forEach>
								</select>
								<input type="text" name="deliverycode" value="${popupVO.deliveryInfo.invoice}" class="line _delivery-code" maxlength="20" />
						<%-- 
						<c:choose>
							<c:when test="${popupVO.sweetTrackerAPIKey != ''}">
								<a href="javascript:popup('${ctx}/shop/admin/sweetTracker/trackingInfo?deliverycode=${deliverycode}&deliveryno=${deliveryNo}',550,600)">
									<img src="/resources/shop/admin/img/btn_delifind.gif" border=0/>
								</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:popup('${deliveryUrl}${deliverycode }',800,500)">
									<img src="/resources/shop/admin/img/btn_delifind.gif" border=0/>
								</a>
							</c:otherwise>
						</c:choose>
						--%>
					</c:when>
					
 					<c:otherwise>
 						<input type="hidden" name="opt"	value="${popupVO.deliveryInfo.opt }" class="line" />
						<font class=small1 color=444444>입금이 완료된 후 송장번호 입력이 가능합니다.</font>
					</c:otherwise>
				</c:choose>
			</td>
			
			<td class="cellM">
				<c:choose>
					<c:when test="${popupVO.deliveryInfo.paymentTerms eq '0'}">무료</c:when>
					<c:when test="${popupVO.deliveryInfo.paymentTerms eq '1'}">선불</c:when>
					<c:when test="${popupVO.deliveryInfo.paymentTerms eq '2'}">착불</c:when>
				</c:choose>
			</td>

			<td class="cellM">
				<c:if test="${popupVO.deliveryInfo.level == 6}">배송완료</c:if>
			</td>
			
			<td class="cellM">
				${popupVO.deliveryInfo.deliveryEdt }
			</td>
			
			<td class="cellM">
				${popupVO.deliveryInfo.returnDeliveryCompNm }
			</td>
			
			<td class="cellM">
				${popupVO.deliveryInfo.returnInvoice }
			</td>
		</tr>
</table>

<div style="padding-top:8px" class="extext">
	* 주문 단계가 [결제완료] 이상인 경우에만 송장번호 입력이 가능합니다.
</div>
<div style="padding-top:8px" class="extext">
	* 택배사와 송장번호가 모두 입력되지 않으면 쇼핑몰 화면에서 배송추적조회를 할 수 없으니 유의해주시기 바랍니다.
</div>
