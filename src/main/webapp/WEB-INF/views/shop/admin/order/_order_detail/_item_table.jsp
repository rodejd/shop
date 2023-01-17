<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %> 

    <table class=tb cellpadding=4 cellspacing=0 >
		<tr height=25 bgcolor=#2E2B29 class=small4 style="padding-top:8px">
			<th style="display: none;"><font color=white>선택</font></th>
			<th><font color=white>번호</font></th>
			<th colspan=2><font color=white>상품명</font></th>
			<th><font color=white>판매사</font></th>
			<th><font color=white>수량</font></th>
			<th><font color=white>상품가격</font></th>
<!-- 			<th><font color=white>회원<br>할인</font></th> -->
			<th><font color=white>소계<br>(상품가격*수량)+옵션가</font></th>
			<th><font color=white>주문<br>결제가격</font></th>
			<th><font color=white>적립 적립금</font></th>
			<th><font color=white>배송비</font></th>
			<th><font color=white>추가배송비</font></th>
			<th><font color=white>매입가</font></th>
			<th><font color=white>처리상태</font></th>
		</tr>
		<col align=center span=3><col>
		<col align=center span=10>

		<c:set var="btnView" value="true" />
		<c:choose>
			<c:when test="${popupVO.rtList != null and popupVO.rtListSize > 0 }">
				<c:forEach items="${popupVO.rtList}" var="rt" varStatus="status">
					<input type=hidden name=sno value="${rt.sno}">
					<input type="hidden" name="goodsno" value="${rt.goodsno }"/>
					
					<c:if test="${rt.istep eq 56 or rt.istep eq 58 or (rt.step eq 3 and rt.istep >= 79) or (rt.step eq 4 and rt.istep >= 79) }">
						<c:set var="btnView" value="false" />
					</c:if>
					
					<tr bgcolor="${rt.bgColor}">
						<td width=35 nowrap class=noline style="display: none;">
							<input type=checkbox name=chk[] value="${rt.sno}" checked
								<%-- 
								<c:if test="${rt.istep eq 56 or rt.istep eq 58 or (rt.step eq 3 and rt.istep >= 79) or (rt.step eq 4 and rt.istep >= 79) }">
									disabled
								</c:if>
								--%>
							/>
						</td>
						
						<td width=35 nowrap style="text-align: center;">
							<font class=ver8 color=444444>
								${status.count}
							</font>
						</td>
						
						<td width=50 nowrap>
							<a href="#" target=_blank>
								<img src='${rt.subImgs}' width="40px" height="40px" style="border:1px solid cccccc;" onerror="this.src='/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/img/common/noimg_100.gif'"/>
							</a>
						</td>
						<td width=500 nowrap>
							<a href="javascript:popup('${ctx}/shop/admin/goods/register?mode=modify&goodsno=${rt.goodsno}&viewFlg=view',825,600)">
								<font class=small color=0074BA>
									${rt.goodsnm}
								</font>
							</a>
							<c:if test="${fn:trim(rt.optname) != '' and rt.optname != null}">
								<div style="padding-top:3">
									<font class=small1 color=6d6d6d>[상품옵션 : ${rt.optname}]</font>
								</div>
							</c:if>
							<c:set var="addopt" value="${rt.addopt }" />
							<c:if test="${addopt != null and addopt != '' and addopt != 'NULL'}">
								<%@ include file="orderitem_opt.jsp"%>
							</c:if>
							<div style="padding-top:3">
								<font class=small1 color=6d6d6d>
									브랜드 : ${rt.brandnm}
								</font>
							</div>
						</td>
						<td>${rt.sellerNm }</td>
						<td nowrap style="text-align: center;">
							${rt.ea}
							<input type="hidden" name="ea" value="${rt.ea}">
						</td>
						<td width="55" nowrap style="text-align: center;">
							${shopLibFunction:getExchange(rt.price, 'kr')}
							<input type="hidden" name="price" value="${rt.price}">
						</td>
						<!-- 소계 -->
						<td width=55 nowrap style="text-align: center;">${shopLibFunction:getExchange(rt.priceSum, 'kr')}</td>
						
						<!-- 주문결제가격 -->
						<td width=55 nowrap style="text-align: center;">
							${shopLibFunction:getExchange(popupVO.rtData.prnsettleprice, 'kr')}
						</td>
						
						<!-- 적립 적립금 -->
						<td width=70 nowrap style="text-align: center;">
							${shopLibFunction:getExchange(rt.addemoney, 'kr')}
						</td>
						
						<!-- 배송비 -->
						<td width=70 nowrap style="text-align: center;">
							${shopLibFunction:getExchange(rt.deliveryPrice, 'kr')}
						</td>
	
						<!-- 추가배송비 -->
						<td width=70 nowrap style="text-align: center;">
							<c:choose>
								<c:when test="${rt.sellerCd != null or rt.sellerCd != ''}">
									<span class="_add-delivery-price _add-delivery-price${rt.goodsno}" data-goodsno="${rt.goodsno}">
										${shopLibFunction:getExchange(rt.addDeliveryPrice, 'kr')}
									</span>							
								</c:when>
								<c:otherwise>
									<span class="_add-delivery-price _add-delivery-price${rt.goodsno}" data-goodsno="${rt.goodsno}">
										${shopLibFunction:getExchange(rt.addDeliveryPrice, 'kr')}
									</span>							
								</c:otherwise>
							</c:choose>
						</td>
						
						<td nowrap style="text-align: center;"><!-- 매입가 -->
							${shopLibFunction:getExchange(rt.supply, 'kr')}
							<input type="hidden" name="supply" value="${rt.supply}">
						</td>
					
						<td width=70 nowrap style="text-align: center;">
							<font class=small4>
								${shopLibFunction:r_stepi(rt.step, rt.istep) }
							</font>
							<!-- 
							<c:if test="${rt.istep == '41' or (rt.istep == '56' and rt.cyn == 'n' and rt.dyn == 'n')}">
								<div>
									<a href="indb/recovery?sno=${rt.sno}&ordno=${popupVO.ordno}&viewFlg=${param.viewFlg}" onclick="return confirm(' 복원처리하시겠습니까?')">
										<img src="/resources/shop/admin/img/btn_return.gif" border=0>
									</a>
								</div>
							</c:if>
							 -->
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
	
	<c:if test="${btnView}">
		<table cellpadding=0 cellspacing=0 width=100%>
			<tr>
				<td width=60% style="padding:5px 0 0 12px">
					<a href="javascript:chkCancel(0);"><img src="/resources/shop/admin/img/btn_cancelorder.gif" border=0></a>
				</td>
				<td width=40% align=right style="padding-right:5px">&nbsp;</td>
			</tr>
		</table>
	</c:if>
<script>

//	addDelivery(); _form 으로 스크립트 위치 변경
	function addDelivery_위치변경() {
		var $addDeliveryTags = $('._add-delivery-price');
		var $deliveryTags = $('._delivery-price');
		
		var flag = false;
		var sellerCd2 = "";
		for (var i = 0; i < $addDeliveryTags.length; i++) {
			
			var addDeliveryTag = $addDeliveryTags[i];
			var deliveryTag = $deliveryTags[i];
			
			var goodsno = addDeliveryTag.dataset.goodsno;			
			var sellerCd1 = deliveryTag.dataset.sellercd;
			// 같은 판매사의 상품인 경우 배송비, 추가배송비는 한번만 적용
			if (sellerCd1 == sellerCd2){
				addDeliveryTag.innerHTML = '0';
				deliveryTag.innerHTML = '0';			
			}
			/*
			if ($('._add-delivery-price' + goodsno).text() != 0 && flag) {
				addDeliveryTag.innerHTML = '0';
				deliveryTag.innerHTML = '0';
				flag = false;
			} else {
				flag = true;
			}
			*/
			sellerCd2 = sellerCd1;
		}
		
	}
</script>