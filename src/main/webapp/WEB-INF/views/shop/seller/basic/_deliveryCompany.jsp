<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<div class=title title_top>
	<font color=black>택배사/배송추적 설정</font>
	<span>사용하는 택배사를 선택하고 배송추적 주소를 넣으세요</span> 
</div>

<div class="rndline2"></div>

<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td>
			<table cellpadding="0" cellspacing="10" border="0">
				<tr>
					<td>
						&nbsp;&nbsp;
						<img src="/resources/shop/admin/img/arrow_downorg.gif" align=absmiddle> 
						<font class=man>택배사 전체리스트 </font>
						<font class=small1>(더블클릭하세요)</font>
					</td>
					<td></td>
					<td>
						&nbsp;&nbsp;
						<img src="/resources/shop/admin/img/arrow_downorg.gif" align=absmiddle> 
						<font class=man>이용 택배사</font> 
						<font class=small1>(삭제시 더블클릭)</font>
					</td>
				</tr>
				<tr>
					<td>
						<select class="deliveryTmp" name="deliveryCompanies" multiple style="width:200px;height:156px" ondblclick="move('right')">
							<c:forEach items="${fm.infoVO.deliveryCompanyList}" var="deliveryCompany">
								<option value="${deliveryCompany.deliveryno}">${deliveryCompany.deliverycomp }</option>
							</c:forEach>
						</select>
					</td>
					<td style="font-size:36px">
						<a href="javascript:move('right')"><font class="color_r">▶</font></a><p>
						<a href="javascript:move('left')"><font class="color_l">◀</font></a>
					</td>
					<td>
						<select class="delivery" name="infoVO.useDeliveryComp" multiple style="width:200px;height:156px" ondblclick="move('left')">
							<c:forEach items="${fm.infoVO.sellerUseDeliveryCompanyList}" var="useComp">
								<option value="${useComp.deliveryno}">${useComp.deliverycomp }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>

			<div style="padding-top:10px"></div>
		</td>
	</tr>
</table>