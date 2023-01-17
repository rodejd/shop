<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 

    <table class=tb cellpadding=4 cellspacing=0 >
		<tr height=25 bgcolor=#2E2B29 class=small4 style="padding-top:8px">
			<th><font color=white>선택</font></th>
			<th><font color=white>번호</font></th>
			<th colspan=2><font color=white>상품명</font></th>
			<th><font color=white>판매사</font></th>
			<th><font color=white>수량</font></th>
			<th><font color=white>상품가격</font></th>
<!-- 			<th><font color=white>회원<br>할인</font></th> -->
			<th><font color=white>소계<br>(상품가격*수량)+옵션가</font></th>
			<th><font color=white>주문<br>결제가격</font></th>
			<th><font color=white>배송비</font></th>
			<th><font color=white>추가배송비</font></th>
			<th><font color=white>매입가</font></th>
			<th><font color=white>처리상태</font></th>

			<c:if test="${'0' != popupVO.deliveryBasis }">
				<th nowrap><font color=white >택배사/송장번호</font></th>			
			</c:if>
		</tr>
		<col align=center span=3><col>
		<col align=center span=10>

		<c:choose>
			<c:when test="${popupVO.rtList != null and popupVO.rtListSize > 0 }">
				<c:forEach items="${popupVO.rtList}" var="rt" varStatus="status">
					<input type=hidden name=sno value="${rt.sno}">
					<input type="hidden" name="goodsno" value="${rt.goodsno}"/>
					<tr bgcolor="${rt.bgColor}">
						<td width=35 nowrap class=noline>
							<input type=checkbox name=chk[] value="${rt.sno}" 
								<c:if test="${(rt.istep >= 41 and rt.istep <= 44) or rt.istep == 61 or rt.istep == 71}">
									disabled
								</c:if>
							/>
						</td>
						
						<td width=35 nowrap>
							<font class=ver8 color=444444>
								${status.count}
							</font>
						</td>
						
						<td width=50 nowrap>
<%-- 							<a href="../../goods/goods_view.jsp?goodsno=${rt.goodsno}" target=_blank> --%>
							<a href="#" target=_blank>
								<img src='/resources/shop/data/upload/goods/${rt.subImgs}' width="40px" height="40px" style="border:1px solid cccccc;" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/common/noimg_100.gif'"/>
							</a>
						</td>
						<td width=250 nowrap>
							<a href="javascript:popup('${ctx}/shop/admin/goods/register?mode=modify&goodsno=${rt.goodsno}&viewFlg=view',825,600)">
								<font class=small color=0074BA>${rt.goodsnm}</font>
								<c:set var="addopt" value="${rt.addopt }" />
								<c:if test="${addopt != null and addopt != '' and addopt != 'NULL'}">
									<%@ include file="orderitem_opt.jsp"%>
								</c:if>
							</a>
							<div style="padding-top:3">
								<font class=small1 color=6d6d6d>
									브랜드 : ${rt.brandnm}
								</font>
							</div>
						</td>
						<td>${rt.myritzNm }</td>
						<td nowrap>
							<input type=text name=ea value="${rt.ea}" size=3 class=right>
						</td>
						<td nowrap>
							<input type=text name=price value="${rt.price}" size=7 class=right maxlength="10" onkeydown="onlynumber(event);"onkeyup="removeHangul(event);" >
						</td>						
						<!-- 소계 -->
						<td width=55 nowrap><fmt:formatNumber value="${rt.priceSum}" pattern="#,###"/></td>
						<!-- 주문결제가격 -->
						<td width=55 nowrap><fmt:formatNumber value="${popupVO.rtData.settleprice}" pattern="#,###"/></td>
												
						<td nowrap>
							<c:choose>
								<c:when test="${rt.paymentTerms == '무료'}">
									무료
								</c:when>
								<c:otherwise>
									<span class="_delivery-price _delivery-price${rt.goodsno}" data-goodsno="${rt.goodsno}" data-myritzCd="${rt.myritzCd}">
										<fmt:formatNumber value="${rt.deliveryPrice}" pattern="#,###"/>
									</span>	
								</c:otherwise>
							</c:choose>
						</td>
						
						<td>
							<c:choose>
								<c:when test="${rt.myritzCd != null or rt.myritzCd != ''}">
									<span class="_add-delivery-price _add-delivery-price${rt.goodsno}" data-goodsno="${rt.goodsno}">
										<fmt:formatNumber value="${rt.addDeliveryPrice }" pattern="#,###"/>
									</span>
								</c:when>
								<c:otherwise>
									<span class="_add-delivery-price _add-delivery-price${rt.goodsno}" data-goodsno="${rt.goodsno}">
										<fmt:formatNumber value="${rt.addDeliveryPrice }" pattern="#,###"/>
									</span>										
								</c:otherwise>
							</c:choose>
						</td>
						
						<td nowrap>
							<input type=text name=supply value="${rt.supply}" size=7 class=right maxlength="10" onkeydown="onlynumber(event);"onkeyup="removeHangul(event);">
						</td>
					
					
						<td width=70 nowrap>
							<font class=small4>
								${rt.rIstep}
							</font>
							<c:if test="${rt.istep == '41' or (rt.istep == '44' and rt.cyn == 'n' and rt.dyn == 'n')}">
								<div>
									<a href="indb/recovery?sno=${rt.sno}&ordno=${popupVO.ordno}&viewFlg=${param.viewFlg}" onclick="return confirm(' 복원처리하시겠습니까?')">
										<img src="/resources/shop/admin/img/btn_return.gif" border=0>
									</a>
								</div>
							</c:if>
						</td>
						
						<c:if test="${'0' != popupVO.deliveryBasis }">		
							<td>
								<div nowrap>
									<font class=small color=555555>
										<b>${rt.dvno}</b>
									</font>
								</div>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			
			</c:when>
			<c:otherwise>
				<tr bgcolor="">
					<td colspan="13"> 해당 항목의 주문상품이 존재하지 않습니다. </td>
				</tr>
			</c:otherwise>
		</c:choose>

	</table>

<script>
//	addDelivery(); _form.jsp로 이동
	function addDelivery_사용안함() {
		var $addDeliveryTags = $('._add-delivery-price');
		var $deliveryTags = $('._delivery-price');
		
		var flag = false;
		
		for (var i = 0; i < $addDeliveryTags.length; i++) {
			
			var addDeliveryTag = $addDeliveryTags[i];
			var deliveryTag = $deliveryTags[i];
			
			var goodsno = addDeliveryTag.dataset.goodsno;
			
			if ($('._add-delivery-price' + goodsno).text() != 0 && flag) {
				addDeliveryTag.innerHTML = '0';
				deliveryTag.innerHTML = '0';
				flag = false;
			} else {
				flag = true;
			}
		}
		
	}
</script>