<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../../common/header.jsp" %>
<%@ include file="../../common/left.jsp" %>


<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<div class="title title_top">매출통계 <span>월별 주문, 입금, 배송별 매출을 조회합니다</span></div>

<c:set var="year" value="${statSaleVO.year}" scope="request"/>
<c:set var="month" value="${statSaleVO.month}" scope="request"/>
<c:set var="last" value="${statSaleVO.last}" scope="request"/>

<form method=post>

<table class=tb>
<col class=cellC><col class=cellL>
<tr>
	<td>기간설정</td>
	<td>
	
	<select name=year>
	<c:forEach var="i" begin="${ year-2}" end="${ year+2}">
		<option value="${i }" <c:out value="${year eq i ? 'selected' : '' }"/> >${i }</option>
	</c:forEach>
	</select>년
	
	<select name=month>
	<c:forEach var="j" begin="1" end="12">
		<option value="${stringUtil:lpad(j,2,'0')}" <c:out value="${month eq j ? 'selected' : '' }"/> >${stringUtil:lpad(j,2,'0')}</option>
	</c:forEach>	
	</select>월
	
	<input type=image src="/resources/shop/admin/img/btn_search_s.gif" style="border:0" align=absmiddle hspace=10>
	
	</td>
</tr>
</table>

</form>

<table width=100% cellpadding=0 cellspacing=0>
<tr><td height=30 align=right style="padding-right:15"><font class=extext>* 아래 자료는 <b>입금확인일(결제완료일)</b> 기준이며, <b>주문취소금액을 뺀</b> 통계자료입니다.</td></tr>
</table>

<table width=100% cellpadding=0 cellspacing=0>
<tr><td class=rnd colspan=10></td></tr>
<tr class=rndbg>
	<th width="16%"><font class=small><b>일별통계</th>
	<th width="8%" bgcolor=63544B><font class=small><b>주문건수</th>
	<th width="12%"><font class=small><b>주문금액</th>
	<th width="8%" bgcolor=63544B><font class=small><b>결제건수</th>
	<th width="12%"><font class=small><b>결제금액</th>
	<th width="8%" bgcolor=63544B><font class=small><b>배송건수</th>
	<th width="12%"><font class=small><b>배송중/배송완료</th>
	<th width="12%" bgcolor=63544B><font class=small><b>매입금액</th>
	<th width="12%"><font class=small><b>순매출액</th>
</tr>
<tr><td class=rnd colspan=10></td></tr>

<c:forEach items="${statSaleVO.statSaleList}" var="statSaleList" varStatus="st">
	
<tr height=25>

	<td align=center bgcolor="#F7F7F7"><font class=ver8 color=444444>${statSaleList.day} (${dateUtil:getDayOfWeek(statSaleList.dt,"ko")})</td>
	<td style="text-align:right;padding-right:10px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statSaleList.rtList1_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statSaleList.rtList1_sum_prn_settleprice)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statSaleList.rtList2_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statSaleList.rtList2_sum_prn_settleprice)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statSaleList.rtList3_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statSaleList.rtList3_sum_prn_settleprice)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statSaleList.rtList2_sum_supply)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statSaleList.rtList2_net_sales)}</b></font></td>
	
	<!-- 합계 -->
	<c:set var="rtList1_cnt_sum"  value="${(statSaleList.rtList1_cnt)+rtList1_cnt_sum }"/><!-- 주문건수 합 -->
	<c:set var="rtList1_sum_prn_settleprice_sum"  value="${(statSaleList.rtList1_sum_prn_settleprice)+rtList1_sum_prn_settleprice_sum }"/><!-- 주문금액 합 -->
	<c:set var="rtList2_cnt_sum"  value="${(statSaleList.rtList2_cnt)+rtList2_cnt_sum }"/><!-- 주문건수 합 -->
	<c:set var="rtList2_sum_prn_settleprice_sum"  value="${(statSaleList.rtList2_sum_prn_settleprice)+rtList2_sum_prn_settleprice_sum }"/><!-- 결제건수 합 -->
	<c:set var="rtList3_cnt_sum"  value="${(statSaleList.rtList3_cnt)+rtList3_cnt_sum }"/><!-- 배송건수 합 -->
	<c:set var="rtList3_sum_prn_settleprice_sum"  value="${(statSaleList.rtList3_sum_prn_settleprice)+rtList3_sum_prn_settleprice_sum }"/><!-- 배송중/배송완료 합 -->
	<c:set var="rtList2_sum_supply_sum"  value="${(statSaleList.rtList2_sum_supply)+rtList2_sum_supply_sum }"/><!-- 매입금액 합 -->
	<c:set var="rtList2_net_sales_sum"  value="${(statSaleList.rtList2_net_sales)+rtList2_net_sales_sum }"/><!-- 순매출액 합 -->
	
	
	
</tr>
<tr><td colspan=10 class=rndline></td></tr>
</c:forEach>

<tr><td colspan=10 bgcolor=A3A3A3></td></tr>
<tr height=25 bgcolor="#C5C5C5">
	<td align=center bgcolor="#EDEDED">합계</td>
	<td style="text-align:right;padding-right:10px" bgcolor=white><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList1_cnt_sum)}</b></font>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList1_sum_prn_settleprice_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor=white><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList2_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList2_sum_prn_settleprice_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor=white><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList3_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList3_sum_prn_settleprice_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor=white><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList2_sum_supply_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList2_net_sales_sum)}</b></font></td>
</tr>
<tr><td colspan=10 class=rndline></td></tr>
</table>


<div style="padding-top:15px"></div>

<div id=MSG01>
<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">위 매출통계자료는 입금확인일(결제완료일) 기준이며, 주문취소금액을 제한 통계자료입니다.</td></tr>
<tR><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">순매출액은 결제금액 - 매입금액로 계산됩니다.</td></tr>
<tR><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">상품등록시 상품의 매입가를 정확히 입력하여야만 정확한 순매출액을 확인할 수 있습니다.</td></tr>
</table>
</div>
<script>cssRound('MSG01')</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<%@ include file="../../common/bottom.jsp" %>
		</td>
	</tr>
</table>