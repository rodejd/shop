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

<div class="title title_top">지역별 매출분석 <span>월별 매출을 조회합니다</span></div>

<c:set var="year" value="${statAreaVO.year}" scope="request"/>
<c:set var="month" value="${statAreaVO.month}" scope="request"/>
<c:set var="last" value="${statAreaVO.last}" scope="request"/>

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
	<th width="9%" colspan=2><font class=small><b>서울</b></font></th>
	<th width="9%" colspan=2><font class=small><b>경기</b></font></th>
	<th width="9%" colspan=2><font class=small><b>경남</b></font></th>
	<th width="9%" colspan=2><font class=small><b>경북</b></font></th>
	<th width="9%" colspan=2><font class=small><b>전남</b></font></th>
	<th width="9%" colspan=2><font class=small><b>전북</b></font></th>
	<th width="9%" colspan=2><font class=small><b>충남</b></font></th>
	<th width="9%" colspan=2><font class=small><b>충북</b></font></th>
	<th width="9%" colspan=2><font class=small><b>제주</b></font></th>
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
	<td align=center><b>건</b></td>
	<td align=center bgcolor="#F7F7F7"><b>금액</b></td>
</tr>
<tr><td class=rnd colspan=19></td></tr>

<c:forEach items="${statAreaVO.statAreaList}" var="statAreaList" varStatus="st">
<tr height=25>
	<td align=center bgcolor="#F7F7F7"><font class=ver8 color=444444>${statAreaList.day} (${dateUtil:getDayOfWeek(statAreaList.odt,"ko")})</font></td>
	<td style="text-align:right;padding-right:5px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statAreaList.rtList_su_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statAreaList.rtList_su_price)}</b></font></td>
	<td style="text-align:right;padding-right:5px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statAreaList.rtList_gg_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statAreaList.rtList_gg_price)}</b></font></td>
	<td style="text-align:right;padding-right:5px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statAreaList.rtList_gn_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statAreaList.rtList_gn_price)}</b></font></td>
	<td style="text-align:right;padding-right:5px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statAreaList.rtList_gb_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statAreaList.rtList_gb_price)}</b></font></td>
	<td style="text-align:right;padding-right:5px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statAreaList.rtList_jn_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statAreaList.rtList_jn_price)}</b></font></td>
	<td style="text-align:right;padding-right:5px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statAreaList.rtList_jb_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statAreaList.rtList_jb_price)}</b></font></td>
	<td style="text-align:right;padding-right:5px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statAreaList.rtList_cn_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statAreaList.rtList_cn_price)}</b></font></td>
	<td style="text-align:right;padding-right:5px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statAreaList.rtList_cb_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statAreaList.rtList_cb_price)}</b></font></td>
	<td style="text-align:right;padding-right:5px"><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(statAreaList.rtList_jj_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=EC4E00><b>${stringUtil:getMoneyFormatInteger(statAreaList.rtList_jj_price)}</b></font></td>
	
	<!-- 합계 -->
	<c:set var="rtList_su_cnt_sum"  value="${(statAreaList.rtList_su_cnt)+rtList_su_cnt_sum }"/><!-- 서울 건수 합 -->
	<c:set var="rtList_su_price_sum"  value="${(statAreaList.rtList_su_price)+rtList_su_price_sum }"/><!-- 서울 금액 합 -->
	<c:set var="rtList_gg_cnt_sum"  value="${(statAreaList.rtList_gg_cnt)+rtList_gg_cnt_sum }"/><!-- 경기 건수 합 -->
	<c:set var="rtList_gg_price_sum"  value="${(statAreaList.rtList_gg_price)+rtList_gg_price_sum }"/><!-- 경기 금액 합 -->
	<c:set var="rtList_gn_cnt_sum"  value="${(statAreaList.rtList_gn_cnt)+rtList_gn_cnt_sum }"/><!-- 경남 건수 합 -->
	<c:set var="rtList_gn_price_sum"  value="${(statAreaList.rtList_gn_price)+rtList_gn_price_sum }"/><!-- 경남 금액 합 -->
	<c:set var="rtList_gb_cnt_sum"  value="${(statAreaList.rtList_gb_cnt)+rtList_gb_cnt_sum }"/><!-- 경북 건수 합 -->
	<c:set var="rtList_gb_price_sum"  value="${(statAreaList.rtList_gb_price)+rtList_gb_price_sum }"/><!-- 경북 금액 합 -->
	<c:set var="rtList_jn_cnt_sum"  value="${(statAreaList.rtList_jn_cnt)+rtList_jn_cnt_sum }"/><!-- 전남 건수 합 -->
	<c:set var="rtList_jn_price_sum"  value="${(statAreaList.rtList_jn_price)+rtList_jn_price_sum }"/><!-- 전남 금액 합 -->
	<c:set var="rtList_jb_cnt_sum"  value="${(statAreaList.rtList_jb_cnt)+rtList_jb_cnt_sum }"/><!-- 전북 건수 합 -->
	<c:set var="rtList_jb_price_sum"  value="${(statAreaList.rtList_jb_price)+rtList_jb_price_sum }"/><!-- 전북 금액 합 -->
	<c:set var="rtList_cn_cnt_sum"  value="${(statAreaList.rtList_cn_cnt)+rtList_cn_cnt_sum }"/><!-- 충남 건수 합 -->
	<c:set var="rtList_cn_price_sum"  value="${(statAreaList.rtList_cn_price)+rtList_cn_price_sum }"/><!-- 충남 금액 합 -->
	<c:set var="rtList_cb_cnt_sum"  value="${(statAreaList.rtList_cb_cnt)+rtList_cb_cnt_sum }"/><!-- 충북 건수 합 -->
	<c:set var="rtList_cb_price_sum"  value="${(statAreaList.rtList_cb_price)+rtList_cb_price_sum }"/><!-- 충북 금액 합 -->
	<c:set var="rtList_jj_cnt_sum"  value="${(statAreaList.rtList_jj_cnt)+rtList_jj_cnt_sum }"/><!-- 제주 건수 합 -->
	<c:set var="rtList_jj_price_sum"  value="${(statAreaList.rtList_jj_price)+rtList_jj_price_sum }"/><!-- 제주 금액 합 -->
	
</tr>
<tr><td colspan=19 class=rndline></td></tr>
</c:forEach>

<tr><td colspan=19 bgcolor=A3A3A3></td></tr>
<tr height=25 bgcolor="#C5C5C5">
	<td align=center bgcolor="#EDEDED">합계</td>
	<td style="text-align:right;padding-right:5px" bgcolor='white'><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_su_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_su_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor='white'><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_gg_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_gg_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor='white'><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_gn_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_gn_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor='white'><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_gb_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_gb_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor='white'><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_jn_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_jn_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor='white'><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_jb_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_jb_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor='white'><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_cn_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_cn_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor='white'><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_cb_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_cb_price_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor='white'><font class=ver8 color=6C6C6C><b>${stringUtil:getMoneyFormatInteger(rtList_jj_cnt_sum)}</b></font></td>
	<td style="text-align:right;padding-right:5px" bgcolor="#f7f7f7"><font class=ver8 color=1259C3><b>${stringUtil:getMoneyFormatInteger(rtList_jj_price_sum)}</b></font></td>
</tr>
<tr><td colspan=19 class=rndline></td></tr>
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