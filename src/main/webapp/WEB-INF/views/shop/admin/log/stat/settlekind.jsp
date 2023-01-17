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

<div class="title title_top">결제수단 분석 <span>월별 주문, 입금, 배송별 매출을 조회합니다</span></div>

<c:set var="year" value="${statSettlekindVO.year}" scope="request"/>
<c:set var="month" value="${statSettlekindVO.month}" scope="request"/>
<c:set var="last" value="${statSettlekindVO.last}" scope="request"/>

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
<tr><td height=30 align=right style="padding-right:15"><font class=extext>* 아래 자료는 <b>입금확인일(결제완료일)</b> 기준이며, <b>주문취소금액을 제한</b> 통계자료입니다.</td></tr>
</table>

<table width=100% cellpadding=0 cellspacing=0>
<tr><td class=rnd colspan=11></td></tr>
<tr class=rndbg>
	<th width="16%" ><font class=small><b>일별통계</b></font></th>
	<th width="16%" colspan=2><font class=small><b>무통장</b></font></th>
	<th width="16%" colspan=2><font class=small><b>신용카드</b></font></th>
	<th width="16%" colspan=2><font class=small><b>계좌이체</b></font></th>
	<th width="16%" colspan=2><font class=small><b>가상계좌</b></font></th>
	<th width="16%" colspan=2><font class=small><b>핸드폰</b></font></th>
</tr>
<tr><td class=rnd colspan=11></td></tr>
<tr height=25>
	<td align=center bgcolor="#F7F7F7"><b>일자</b></td>
	<td align=center><b>건수</b></td>
	<td align=center bgcolor="#F7F7F7"><b>금액</b></td>
	<td align=center><b>건수</b></td>
	<td align=center bgcolor="#F7F7F7"><b>금액</b></td>
	<td align=center><b>건수</b></td>
	<td align=center bgcolor="#F7F7F7"><b>금액</b></td>
	<td align=center><b>건수</b></td>
	<td align=center bgcolor="#F7F7F7"><b>금액</b></td>
	<td align=center><b>건수</b></td>
	<td align=center bgcolor="#F7F7F7"><b>금액</b></td>
</tr>

<tr><td class=rnd colspan=11></td></tr>

<c:forEach items="${statSettlekindVO.statSettlekindList}" var="statSettlekindList" varStatus="st">

<tr height=25>
	<td align=center bgcolor="#F7F7F7"><font class=ver8 color=444444>${statSettlekindList.day} (${dateUtil:getDayOfWeek(statSettlekindList.odt,"ko")})</font></td>
	<td style="text-align:right;padding-right:10px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statSettlekindList.rtList_a_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statSettlekindList.rtList_a_price)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statSettlekindList.rtList_c_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statSettlekindList.rtList_c_price)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statSettlekindList.rtList_o_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statSettlekindList.rtList_o_price)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statSettlekindList.rtList_v_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statSettlekindList.rtList_v_price)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statSettlekindList.rtList_h_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statSettlekindList.rtList_h_price)}</b></font></td>
	
	<!-- 합계 -->
	<c:set var="rtList_a_cnt_sum"  value="${(statSettlekindList.rtList_a_cnt)+rtList_a_cnt_sum }"/><!-- 무통장 건수 -->
	<c:set var="rtList_a_price_sum"  value="${(statSettlekindList.rtList_a_price)+rtList_a_price_sum }"/><!-- 무통장 금액 -->
	<c:set var="rtList_c_cnt_sum"  value="${(statSettlekindList.rtList_c_cnt)+rtList_c_cnt_sum }"/><!-- 신용카드 건수 -->
	<c:set var="rtList_c_price_sum"  value="${(statSettlekindList.rtList_c_price)+rtList_c_price_sum }"/><!-- 신용카드 금액 -->
	<c:set var="rtList_o_cnt_sum"  value="${(statSettlekindList.rtList_o_cnt)+rtList_o_cnt_sum }"/><!-- 계좌이체 건수 -->
	<c:set var="rtList_o_price_sum"  value="${(statSettlekindList.rtList_o_price)+rtList_o_price_sum }"/><!-- 계좌이체 금액 -->
	<c:set var="rtList_v_cnt_sum"  value="${(statSettlekindList.rtList_v_cnt)+rtList_v_cnt_sum }"/><!-- 가상계좌 건수 -->
	<c:set var="rtList_v_price_sum"  value="${(statSettlekindList.rtList_v_price)+rtList_v_price_sum }"/><!-- 가상계좌 금액 -->
	<c:set var="rtList_h_cnt_sum"  value="${(statSettlekindList.rtList_h_cnt)+rtList_h_cnt_sum }"/><!-- 핸드폰 건수 -->
	<c:set var="rtList_h_price_sum"  value="${(statSettlekindList.rtList_h_price)+rtList_h_price_sum }"/><!-- 핸드폰 금액 -->
	
</tr>
<tr><td colspan=11 class=rndline></td></tr>

</c:forEach>

<tr><td colspan=11 bgcolor=A3A3A3></td></tr>
<tr height=25 bgcolor="#C5C5C5">
	<td align=center bgcolor="#EDEDED">합계</td>
	<td style="text-align:right;padding-right:10px" bgcolor=white><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_a_cnt_sum)}</b></font>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_a_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor=white><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_c_cnt_sum)}</b></font>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_c_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor=white><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_o_cnt_sum)}</b></font>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_o_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor=white><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_v_cnt_sum)}</b></font>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_v_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor=white><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_h_cnt_sum)}</b></font>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_h_price_sum)}</b></font></td>
	
</tr>

<tr><td colspan=11 class=rndline></td></tr>
</table>

<div style="padding-top:15px"></div>

<div id=MSG01>
<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">위 결제수단분석자료는 입금확인일(결제완료일) 기준이며, 주문취소금액을 제한 통계자료입니다.</td></tr>
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