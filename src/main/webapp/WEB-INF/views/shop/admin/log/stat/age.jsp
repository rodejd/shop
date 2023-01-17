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

<div class="title title_top">연령별 매출분석 <span>월별 매출을 조회합니다</span></div>

<c:set var="year" value="${statAgeVO.year}" scope="request"/>
<c:set var="month" value="${statAgeVO.month}" scope="request"/>

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
	
	<input type=image src="/resources/shop/admin/img/btn_search_s.gif" style="border:0" align=absmiddle hspace=10/>
	</td>
</tr>
</table>

</form>

<table width=100% cellpadding=0 cellspacing=0>
<tr><td height=30 align=right style="padding-right:15"><font class=extext>* 아래 자료는 <b>입금확인일(결제완료일)</b> 기준이며, <b>주문취소금액을 제한</b> 통계자료입니다.</td></tr>
</table>

<table width=100% cellpadding=0 cellspacing=0>
<tr><td class=rnd colspan=13></td></tr>
<tr class=rndbg>
	<th width="16%" ><font class=small><b>일별통계</b></font></th>
	<th width="14%" colspan=2><font class=small><b>10대</b></font></th>
	<th width="14%" colspan=2><font class=small><b>20대</b></font></th>
	<th width="14%" colspan=2><font class=small><b>30대</b></font></th>
	<th width="14%" colspan=2><font class=small><b>40대</b></font></th>
	<th width="14%" colspan=2><font class=small><b>50대</b></font></th>
	<th width="14%" colspan=2><font class=small><b>60 이상</b></font></th>
</tr>
<tr><td class=rnd colspan=13></td></tr>
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
	<td align=center><b>건수</b></td>
	<td align=center bgcolor="#F7F7F7"><b>금액</b></td>
</tr>
<tr><td class=rnd colspan=13></td></tr>

<c:forEach items="${statAgeVO.statAgeList}" var="statAgeList" varStatus="st">

<tr height=25>
	<td align=center bgcolor="#F7F7F7"><font class=ver8 color=444444>${statAgeList.day} (${dateUtil:getDayOfWeek(statAgeList.odt,"ko")})</font></td>
	<td style="text-align:right;padding-right:10px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statAgeList.rtList_10_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statAgeList.rtList_10_price)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statAgeList.rtList_20_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statAgeList.rtList_20_price)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statAgeList.rtList_30_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statAgeList.rtList_30_price)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statAgeList.rtList_40_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statAgeList.rtList_40_price)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statAgeList.rtList_50_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statAgeList.rtList_50_price)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statAgeList.rtList_60_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statAgeList.rtList_60_price)}</b></font></td>
	
	<!-- 합계 -->
	<c:set var="rtList_10_cnt_sum"  value="${(statAgeList.rtList_10_cnt)+rtList_10_cnt_sum }"/><!-- 10대 건수 합 -->
	<c:set var="rtList_10_price_sum"  value="${(statAgeList.rtList_10_price)+rtList_10_price_sum }"/><!-- 10대 금액 합 -->
	<c:set var="rtList_20_cnt_sum"  value="${(statAgeList.rtList_20_cnt)+rtList_20_cnt_sum }"/><!-- 20대 건수 합 -->
	<c:set var="rtList_20_price_sum"  value="${(statAgeList.rtList_20_price)+rtList_20_price_sum }"/><!-- 20대 금액 합 -->
	<c:set var="rtList_30_cnt_sum"  value="${(statAgeList.rtList_30_cnt)+rtList_30_cnt_sum }"/><!-- 30대 건수 합 -->
	<c:set var="rtList_30_price_sum"  value="${(statAgeList.rtList_30_price)+rtList_30_price_sum }"/><!-- 30대 금액 합 -->
	<c:set var="rtList_40_cnt_sum"  value="${(statAgeList.rtList_40_cnt)+rtList_40_cnt_sum }"/><!-- 40대 건수 합 -->
	<c:set var="rtList_40_price_sum"  value="${(statAgeList.rtList_40_price)+rtList_40_price_sum }"/><!-- 40대 금액 합 -->
	<c:set var="rtList_50_cnt_sum"  value="${(statAgeList.rtList_50_cnt)+rtList_50_cnt_sum }"/><!-- 50대 건수 합 -->
	<c:set var="rtList_50_price_sum"  value="${(statAgeList.rtList_50_price)+rtList_50_price_sum }"/><!-- 50대 금액 합 -->
	<c:set var="rtList_60_cnt_sum"  value="${(statAgeList.rtList_60_cnt)+rtList_60_cnt_sum }"/><!-- 60대 건수 합 -->
	<c:set var="rtList_60_price_sum"  value="${(statAgeList.rtList_60_price)+rtList_60_price_sum }"/><!-- 60대 금액 합 -->
	
	
</tr>
<tr><td colspan=13 class=rndline></td></tr>

</c:forEach>

<tr><td colspan=13 bgcolor=A3A3A3></td></tr>
<tr height=25 bgcolor="#C5C5C5">
	<td align=center bgcolor="#EDEDED">합계</td>
	<td style="text-align:right;padding-right:10px" bgcolor=white><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_10_cnt_sum)}</b></font>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_10_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor=white><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_20_cnt_sum)}</b></font>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_20_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor=white><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_30_cnt_sum)}</b></font>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_30_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor=white><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_40_cnt_sum)}</b></font>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_40_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor=white><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_50_cnt_sum)}</b></font>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_50_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor=white><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_60_cnt_sum)}</b></font>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_60_price_sum)}</b></font></td>
</tr>
<tr><td colspan=13 class=rndline></td></tr>
</table>

<div style="padding-top:15px"></div>

<div id=MSG01>
<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">위 연령별분석은 입금확인일(결제완료일) 기준이며, 주문취소금액을 제한 통계자료입니다.</td></tr>
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