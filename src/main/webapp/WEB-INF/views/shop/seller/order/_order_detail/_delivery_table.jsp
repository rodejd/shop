<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<td class="cellC cellM">판매사</td>
		<td class="cellC cellM">상품명</td>
		<td class="cellC cellM">송장번호</td>
		<!-- <td class="cellC cellM">배송비</td> -->
		<td class="cellC cellM">지불방식</td>
		<!-- <td class="cellC cellM">추가배송비</td> -->		
		<td class="cellC cellM">배송상태</td>
		<td class="cellC cellM">배송일</td>
	</tr>
	<c:forEach items="${popupVO.delivery}" var="delivery" varStatus="vStatus">
		<tr>
			<td class="cellM">
				${delivery.sellerNm}
			</td>
	
			<td class="cellM">
				${delivery.goodsnm }
			</td>
			
			<td class="cellM">
				<c:choose>
					<c:when test="${delivery.level == 6}">
					
						<input type="hidden" name="deliverycode" value="${delivery.invoice}" class="line" />
						<input type="hidden" name="opt"	value="${delivery.opt }" class="line" />
						
						<font color="0074BA">
							<c:forEach items="${delivery.deliveryCompList}" var="deliveryComp">
								<c:if test="${deliveryComp.deliveryno eq delivery.deliveryCompCd}">
									${deliveryComp.deliverycomp} / 
									<input type="hidden" name="deliveryno" value="${delivery.deliveryCompCd}" class="line" />
								</c:if> 
							</c:forEach>
							${delivery.invoice}
						</font>
						<font class=small1 color=444444>이미 배송이 완료된 상품입니다.</font>	
					</c:when>
					
					<c:when test="${39 < delivery.istep && delivery.istep < 43 || delivery.istep eq 44 }">
						<font class=small1 color=444444>주문상태 : ${shopLibFunction:r_istep(delivery.istep)}</font>
					</c:when>
					
					<c:when test="${popupVO.rtData.step >= 1 and popupVO.rtData.step < 4 or popupVO.rtData.step2 == 61}">					
						<select name=deliveryno class="_delivery-select" >
							<option value="00">==택배사==</option>
							<c:forEach items="${delivery.deliveryCompList}" var="deliveryComp">
								<option value="${deliveryComp.deliveryno}" 
								<c:if test="${deliveryComp.deliveryno eq delivery.deliveryCompCd}">
									selected
									<c:set var="deliveryUrl" value="${deliveryComp.deliveryurl }" />
									<c:set var="deliveryNo" value="${deliveryComp.deliveryno }"/>
								</c:if> 
								data-delivery-url="${deliveryComp.deliveryurl }">
									${deliveryComp.deliverycomp}
								</option>
							</c:forEach>
						</select>
						<input type="hidden" name="opt"	value="${delivery.opt }" class="line" />
						<input type="text" name="deliverycode" value="${delivery.invoice}" class="line _delivery-code" maxlength="20" />
							
						<c:set var="deliverycode" value="${delivery.invoice}" />
						
						<c:choose>
							<c:when test="${popupVO.sweetTrackerAPIKey != ''}">
								<a href="javascript:popup('${ctx }/shop/admin/sweetTracker/trackingInfo?deliverycode=${deliverycode}&deliveryno=${deliveryNo}',550,600)">
									<img src="/resources/shop/admin/img/btn_delifind.gif" border=0/>
								</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:popup('${deliveryUrl}${deliverycode }',800,500)">
									<img src="/resources/shop/admin/img/btn_delifind.gif" border=0/>
								</a>
							</c:otherwise>
						</c:choose>
					</c:when>
					
 					<c:otherwise>
 						<input type="hidden" name="opt"	value="${delivery.opt }" class="line" />
						<font class=small1 color=444444>입금이 완료된 후 송장번호 입력이 가능합니다.</font>
					</c:otherwise>
				</c:choose>
			</td>
			
<%-- 			<td class="cellM">
				<c:if test="${vStatus.index == 0}">
					<fmt:formatNumber pattern="#,###">
						<c:choose>
							<c:when test="${delivery.paymentTerms == '무료'}">
								0
							</c:when>
							<c:otherwise>
								${delivery.deliveryPrice}
							</c:otherwise>
						</c:choose>
					</fmt:formatNumber> 원
				</c:if>
			</td> --%>
			<td class="cellM">
				<c:if test="${vStatus.index == 0}">${delivery.paymentTerms}</c:if>
			</td>
<%-- 			<td class="cellM">
				<c:if test="${vStatus.index == 0}">
					<fmt:formatNumber value="${delivery.addDeliveryPrice}" pattern="#,###"/> 원
				</c:if>
			</td>	 --%>		
			<td class="cellM">
				${delivery.deliveryStatus }
			</td>
			
			<td class="cellM">
				${delivery.deliveryEdt }
			</td>
		</tr>
	</c:forEach>
</table>

<div style="padding-top:8px" class="extext">
	* 주문 단계가 [입금확인] 이상인 경우에만 송장번호 입력이 가능합니다.
</div>
<div style="padding-top:8px" class="extext">
	* 택배사와 송장번호가 모두 입력되지 않으면 쇼핑몰 화면에서 배송추적조회를 할 수 없으니 유의해주시기 바랍니다.
</div>
