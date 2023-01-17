<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %> 

<%--
<div style="padding: 25px 0px 5px 13px">
	<b>3. 지역별 배송정책</b> 
	<font class=extext>(도서산간 등 지역별로 배송금액을 지정할 수 있습니다)</font>
</div>

<br>

<table class="tb">
	<col class=cellC><col class=cellL>
	<tr>
		<td>지역별 배송금액</td>
		<td>
			<table id="" width="100%">
				<col><col align="right">
				<!--
				<c:set var="tmpOverValue"  value=""></c:set>
				<c:set var="tmpZipcodeNameValue"  value=""></c:set>
				
				<c:set var = "i" value="0"></c:set>
				-->
				<div>
					<a href="javascript:addOver()">
						<img src="/resources/shop/admin/img/btn_placeadd.gif">
					</a>
				</div>
				<c:forEach items="${fm.infoVO.deliveryOverPoicyList}" var="deliveryOverPolicy" varStatus="status">
					<tr>
						<td>
							아래 지역의 배송비를 
							<input type="text" name="infoVO.addDeliveryPrices" value="${deliveryOverPolicy.addDeliveryPrice}" class="rline" maxlength="9" onkeydown="onlynumber(event)" onkeyup="removeChar(event)">
							 원으로 지정 
							<a href="javascript:postcode(${status.index});">
								<img src="/resources/shop/admin/img/btn_area_search.gif" align="absmiddle" value="지역검색하기" />
							</a>
							<div class=extext style="padding-top:5px">
								<font>(반드시 <b>'지역검색하기'</b>를 눌러서 지역을 추가하세요)</font>
							</div>
						</td>
						<td>
							<a href="javascript:void(0)" onClick="delOver(this)"><img src="/resources/shop/admin/img/i_del.gif"></a>
						</td>
					</tr>
					<tr>
						<td colspan=2><textarea name="infoVO.addDeliveryCities" readonly="readonly" style="width:100%;height:50px" class="tline" id="postcode_${status.index}">${deliveryOverPolicy.cities}</textarea>
					</tr>
				</c:forEach>
			</table>

			<div class="extext_t">* 일반적으로 지역별 배송금액을 차별화하는 경우는 섬, 도서산간 등에 해당됩니다. (제주도,울릉도 등)</div>

		</td>
	</tr>
</table>
--%>

<div class="sub-cont-wrap">
	<div class="sub-title">3. 지역별 배송정책
		<span class="extext">(도서산간 등 지역별로 배송금액을 지정할 수 있습니다)</span>
	</div>
	<div class="div-tbl">
		<div class="th w-120">지역별 배송금액</div>
		<div>
			<c:set var="tmpOverValue"  value=""></c:set>
			<c:set var="tmpZipcodeNameValue"  value=""></c:set>

			<c:set var = "i" value="0"></c:set>
			<a href="javascript:addOver()" class="admin-btn-delivery-edit">지역추가입력</a>

			<div>
				<table id="tbOver" style="width: 100%;">
					<tbody>
						<c:forEach items="${fm.infoVO.deliveryOverPoicyList}" var="deliveryOverPolicy" varStatus="status">
							<tr>
								<td>
									아래 지역의 배송비를
									<input type="text" name="infoVO.addDeliveryPrices" value="${deliveryOverPolicy.addDeliveryPrice}" maxlength="9" onkeydown="onlynumber(event)" onkeyup="removeChar(event)">
									원으로 지정
									<a href="javascript:postcode(${status.index});">
										<img src="/resources/shop/admin/img/btn_area_search.gif" align="absmiddle" value="지역검색하기" />
									</a>
									<div class=extext style="padding-top:5px">
										<font>(반드시 <b>'지역검색하기'</b>를 눌러서 지역을 추가하세요)</font>
									</div>
								</td>
								<td>
									<a href="javascript:void(0)" class="admin-i-del" onclick="delOver(this)">삭제</a>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<textarea name="infoVO.addDeliveryCities" readonly="readonly" style="width:100%;height:50px" id="postcode_${status.index}">${deliveryOverPolicy.cities}</textarea>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div>
				<span class="extext_t">* 일반적으로 지역별 배송금액을 차별화하는 경우는 섬, 도서산간 등에 해당됩니다. (제주도,울릉도 등)</span>
			</div>

		</div>
	</div>
</div>	<!-- END sub-cont-wrap -->