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

<div class="title title_top">성별 매출분석 <span>월별 매출을 조회합니다</span></div>

<c:set var="year" value="${statSexVO.year}" scope="request"/>
<c:set var="month" value="${statSexVO.month}" scope="request"/>
<c:set var="last" value="${statSexVO.last}" scope="request"/>

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
<tr><td class=rnd colspan=19></td></tr>
<tr class=rndbg>
	<th width="16%"><font class=small><b>일별통계</b></font></th>
	<th width="10%" colspan=2><font class=small><b>입금확인(남)</b></font></th>
	<th width="10%" colspan=2><font class=small><b>배송준비(남)</b></font></th>
	<th width="10%" colspan=2><font class=small><b>배송중(남)</b></font></th>
	<th width="10%" colspan=2><font class=small><b>배송완료(남)</b></font></th>
	<th width="10%" colspan=2><font class=small><b>입금확인(여)</b></font></th>
	<th width="10%" colspan=2><font class=small><b>배송준비(여)</b></font></th>
	<th width="10%" colspan=2><font class=small><b>배송중(여)</b></font></th>
	<th width="10%" colspan=2><font class=small><b>배송완료(여)</b></font></th>
</tr>
<tr><td class=rnd colspan=50></td></tr>
<tr height=25 class=small>
	<td align=center bgcolor="#F7F7F7"><b>일자</b></td>
	<td align=center><b>건</b></td>
	<td align=center bgcolor="#F7F7F7"><b>금액</b></td>
	<td align=center><b>건</b></td>
	<td align=center bgcolor="#F7F7F7"><b>금액</b></td>
	<td align=center><b>건</b></td>
	<td align=center bgcolor="#F7F7F7"><b>금액</b></td>
	<td align=center><b>건</b></td>
	<td align=center bgcolor="#F7F7F7"><b>금액</b></td>
	<td align=center><b>건</b></td>
	<td align=center bgcolor="#F7F7F7"><b>금액</b></td>
	<td align=center><b>건</b></td>
	<td align=center bgcolor="#F7F7F7"><b>금액</b></td>
	<td align=center><b>건</b></td>
	<td align=center bgcolor="#F7F7F7"><b>금액</b></td>
	<td align=center><b>건</b></td>
	<td align=center bgcolor="#F7F7F7"><b>금액</b></td>
</tr>
<tr><td class=rnd colspan=19></td></tr>

<c:forEach items="${statSexVO.statSexList}" var="statSexList" varStatus="st">

<tr height=25>
	<td align=center bgcolor="#F7F7F7"><font class=ver8 color=444444>${statSexList.day} (${dateUtil:getDayOfWeek(statSexList.odt,"ko")})</font></td>
	<td style="text-align:right;padding-right:5px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statSexList.rtList_m1_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statSexList.rtList_m1_price)}</b></font></td>
	<td style="text-align:right;padding-right:5px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statSexList.rtList_m2_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statSexList.rtList_m2_price)}</b></font></td>
	<td style="text-align:right;padding-right:5px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statSexList.rtList_m3_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statSexList.rtList_m3_price)}</b></font></td>
	<td style="text-align:right;padding-right:5px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statSexList.rtList_m4_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statSexList.rtList_m4_price)}</b></font></td>
	
	<td style="text-align:right;padding-right:5px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statSexList.rtList_w1_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statSexList.rtList_w1_price)}</b></font></td>
	<td style="text-align:right;padding-right:5px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statSexList.rtList_w2_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statSexList.rtList_w2_price)}</b></font></td>
	<td style="text-align:right;padding-right:5px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statSexList.rtList_w3_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statSexList.rtList_w3_price)}</b></font></td>
	<td style="text-align:right;padding-right:5px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statSexList.rtList_w4_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statSexList.rtList_w4_price)}</b></font></td>
	
	<!-- 합계 -->
	<c:set var="rtList_m1_cnt_sum"  value="${(statSexList.rtList_m1_cnt)+rtList_m1_cnt_sum }"/><!-- 입금확인(남) 건수 합 -->
	<c:set var="rtList_m1_price_sum"  value="${(statSexList.rtList_m1_price)+rtList_m1_price_sum }"/><!-- 입금확인(남) 건수 합 -->
	<c:set var="rtList_m2_cnt_sum"  value="${(statSexList.rtList_m2_cnt)+rtList_m2_cnt_sum }"/><!-- 배송준비(남) 건수 합 -->
	<c:set var="rtList_m2_price_sum"  value="${(statSexList.rtList_m2_price)+rtList_m2_price_sum }"/><!-- 배송준비(남) 건수 합 -->
	<c:set var="rtList_m3_cnt_sum"  value="${(statSexList.rtList_m3_cnt)+rtList_m3_cnt_sum }"/><!-- 배송중(남) 건수 합 -->
	<c:set var="rtList_m3_price_sum"  value="${(statSexList.rtList_m3_price)+rtList_m3_price_sum }"/><!-- 배송중(남) 건수 합 -->
	<c:set var="rtList_m4_cnt_sum"  value="${(statSexList.rtList_m4_cnt)+rtList_m4_cnt_sum }"/><!-- 배송완료(남) 건수 합 -->
	<c:set var="rtList_m4_price_sum"  value="${(statSexList.rtList_m4_price)+rtList_m4_price_sum }"/><!-- 배송완료(남) 건수 합 -->
	
	<c:set var="rtList_w1_cnt_sum"  value="${(statSexList.rtList_w1_cnt)+rtList_w1_cnt_sum }"/><!-- 입금확인(여) 건수 합 -->
	<c:set var="rtList_w1_price_sum"  value="${(statSexList.rtList_w1_price)+rtList_w1_price_sum }"/><!-- 입금확인(여) 건수 합 -->
	<c:set var="rtList_w2_cnt_sum"  value="${(statSexList.rtList_w2_cnt)+rtList_w2_cnt_sum }"/><!-- 배송준비(여) 건수 합 -->
	<c:set var="rtList_w2_price_sum"  value="${(statSexList.rtList_w2_price)+rtList_w2_price_sum }"/><!-- 배송준비(여) 건수 합 -->
	<c:set var="rtList_w3_cnt_sum"  value="${(statSexList.rtList_w3_cnt)+rtList_w3_cnt_sum }"/><!-- 배송중(여) 건수 합 -->
	<c:set var="rtList_w3_price_sum"  value="${(statSexList.rtList_w3_price)+rtList_w3_price_sum }"/><!-- 배송중(여) 건수 합 -->
	<c:set var="rtList_w4_cnt_sum"  value="${(statSexList.rtList_w4_cnt)+rtList_w4_cnt_sum }"/><!-- 배송완료(여) 건수 합 -->
	<c:set var="rtList_w4_price_sum"  value="${(statSexList.rtList_w4_price)+rtList_w4_price_sum }"/><!-- 배송완료(여) 건수 합 -->
	
	
	
	
	
</tr>
<tr><td colspan=19 class=rndline></td></tr>

</c:forEach>

<tr><td colspan=19 bgcolor=A3A3A3></td></tr>
<tr height=25 bgcolor="#C5C5C5">
	<td align=center bgcolor="#EDEDED">합계</td>
	<td style="text-align:right;padding-right:5px" bgcolor='white'><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_m1_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_m1_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor='white'><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_m2_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_m2_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor='white'><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_m3_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_m3_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor='white'><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_m4_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_m4_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor='white'><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_w1_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_w1_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor='white'><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_w2_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_w2_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor='white'><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_w3_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_w3_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor='white'><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_w4_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_w4_price_sum)}</b></font></td>
	
</tr>
<tr><td colspan=19 class=rndline></td></tr>
</table>

<div style="padding-top:15px"></div>

<div id=MSG01>
<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">위 성별 매출분석은 입금확인일(결제완료일) 기준이며, 주문취소금액을 제한 통계자료입니다.</td></tr>
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