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

<div class="title title_top">연령별 회원분석</div>

<c:set var="year" value="${memAgeVO.year}" scope="request"/>
<c:set var="month" value="${memAgeVO.month}" scope="request"/>

<form method="post">

<table class="tb">
<col class="cellC"><col class="cellL">
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
	
	<input type="image" src="/resources/shop/admin/img/btn_search_s.gif" style="border:0" align="absmiddle" hspace="10">
	</td>
</tr>
</table>

</form>
<p/>
<table width="100%" cellpadding="0" cellspacing="0">
<tr><td class="rnd" colspan="15"></td></tr>
<tr class="rndbg">
	<th width="16%"><font class="small"><b>일별통계</b></font></th>
	<th colspan="2"><font class="small"><b>10대</b></font></th>
	<th colspan="2"><font class="small"><b>20대</b></font></th>
	<th colspan="2"><font class="small"><b>30대</b></font></th>
	<th colspan="2"><font class="small"><b>40대</b></font></th>
	<th colspan="2"><font class="small"><b>50대</b></font></th>
	<th colspan="2"><font class="small"><b>60이상</b></font></th>
</tr>
<tr height=25>
	<td align="center" bgcolor="#F7F7F7"><b>일자</b></td>
	<td align="center"><b>남</b></td>
	<td align="center" bgcolor="#F7F7F7"><b>여</b></td>
	<td align="center"><b>남</b></td>
	<td align="center" bgcolor="#F7F7F7"><b>여</b></td>
	<td align="center"><b>남</b></td>
	<td align="center" bgcolor="#F7F7F7"><b>여</b></td>
	<td align="center"><b>남</b></td>
	<td align="center" bgcolor="#F7F7F7"><b>여</b></td>
	<td align="center"><b>남</b></td>
	<td align="center" bgcolor="#F7F7F7"><b>여</b></td>
	<td align="center"><b>남</b></td>
	<td align="center" bgcolor="#F7F7F7"><b>여</b></td>	
</tr>
<tr><td class=rnd colspan=15></td></tr>

<c:forEach items="${memAgeVO.memAgeList}" var="memAgeList" varStatus="st">

<tr height=25>
	<td align="center" bgcolor="#F7F7F7"><font class="ver8" color="444444">${memAgeList.day} (${dateUtil:getDayOfWeek(memAgeList.rdt,"ko")})</font></td>
	<td style="text-align:right;padding-right:10px"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(memAgeList.rtList_10_m_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color=EC4E00><b>${stringUtil:getMoneyFormatInteger(memAgeList.rtList_10_w_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(memAgeList.rtList_20_m_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color=EC4E00><b>${stringUtil:getMoneyFormatInteger(memAgeList.rtList_20_w_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(memAgeList.rtList_30_m_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color=EC4E00><b>${stringUtil:getMoneyFormatInteger(memAgeList.rtList_30_w_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(memAgeList.rtList_40_m_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color=EC4E00><b>${stringUtil:getMoneyFormatInteger(memAgeList.rtList_40_w_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(memAgeList.rtList_50_m_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color=EC4E00><b>${stringUtil:getMoneyFormatInteger(memAgeList.rtList_50_w_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(memAgeList.rtList_60_m_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color=EC4E00><b>${stringUtil:getMoneyFormatInteger(memAgeList.rtList_60_w_cnt)}</b></font></td>
	
	<!-- 합계 -->
	<c:set var="rtList_10_m_cnt_sum"  value="${(memAgeList.rtList_10_m_cnt)+rtList_10_m_cnt_sum }"/><!-- 10대(남) 건수 합 -->
	<c:set var="rtList_10_w_cnt_sum"  value="${(memAgeList.rtList_10_w_cnt)+rtList_10_w_cnt_sum }"/><!-- 10대(여) 건수 합 -->
	<c:set var="rtList_20_m_cnt_sum"  value="${(memAgeList.rtList_20_m_cnt)+rtList_20_m_cnt_sum }"/><!-- 20대(남) 건수 합 -->
	<c:set var="rtList_20_w_cnt_sum"  value="${(memAgeList.rtList_20_w_cnt)+rtList_20_w_cnt_sum }"/><!-- 20대(여) 건수 합 -->
	<c:set var="rtList_30_m_cnt_sum"  value="${(memAgeList.rtList_30_m_cnt)+rtList_30_m_cnt_sum }"/><!-- 30대(남) 건수 합 -->
	<c:set var="rtList_30_w_cnt_sum"  value="${(memAgeList.rtList_30_w_cnt)+rtList_30_w_cnt_sum }"/><!-- 30대(여) 건수 합 -->
	<c:set var="rtList_40_m_cnt_sum"  value="${(memAgeList.rtList_40_m_cnt)+rtList_40_m_cnt_sum }"/><!-- 40대(남) 건수 합 -->
	<c:set var="rtList_40_w_cnt_sum"  value="${(memAgeList.rtList_40_w_cnt)+rtList_40_w_cnt_sum }"/><!-- 40대(여) 건수 합 -->
	<c:set var="rtList_50_m_cnt_sum"  value="${(memAgeList.rtList_50_m_cnt)+rtList_50_m_cnt_sum }"/><!-- 50대(남) 건수 합 -->
	<c:set var="rtList_50_w_cnt_sum"  value="${(memAgeList.rtList_50_w_cnt)+rtList_50_w_cnt_sum }"/><!-- 50대(여) 건수 합 -->
	<c:set var="rtList_60_m_cnt_sum"  value="${(memAgeList.rtList_60_m_cnt)+rtList_60_m_cnt_sum }"/><!-- 60대(남) 건수 합 -->
	<c:set var="rtList_60_w_cnt_sum"  value="${(memAgeList.rtList_60_w_cnt)+rtList_60_w_cnt_sum }"/><!-- 60대(여) 건수 합 -->
</tr>
<tr><td colspan=13 class=rndline></td></tr>
</c:forEach>

<tr><td colspan=13 bgcolor=A3A3A3></td></tr>
<tr height=25 bgcolor="#C5C5C5">
	<td align="center" bgcolor="#EDEDED">합계</td>
	<td style="text-align:right;padding-right:10px" bgcolor="white"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(rtList_10_m_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color="1259C3"><b>${stringUtil:getMoneyFormatInteger(rtList_10_w_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="white"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(rtList_20_m_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color="1259C3"><b>${stringUtil:getMoneyFormatInteger(rtList_20_w_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="white"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(rtList_30_m_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color="1259C3"><b>${stringUtil:getMoneyFormatInteger(rtList_30_w_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="white"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(rtList_40_m_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color="1259C3"><b>${stringUtil:getMoneyFormatInteger(rtList_40_w_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="white"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(rtList_50_m_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color="1259C3"><b>${stringUtil:getMoneyFormatInteger(rtList_50_w_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="white"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(rtList_60_m_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color="1259C3"><b>${stringUtil:getMoneyFormatInteger(rtList_60_w_cnt_sum)}</b></font></td>
</tr>
<tr><td colspan=13 class=rndline></td></tr>
</table>
<p/>
<div id=MSG01>
<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">위 연령별/성별 회원분석은 입금확인일(결제완료일) 기준이며, 주문취소금액을 제한 통계자료입니다.</td></tr>
</table>
</div>
<script>cssRound('MSG01','#F7F7F7')</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<%-- ================================================================================
* 공통 HTML CONTENT 종료
================================================================================ --%>


<%@ include file="../../common/bottom.jsp" %>
		</td>
	</tr>
</table>