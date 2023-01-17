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

<div class="title title_top">지역별 회원분석</div>

<c:set var="year" value="${memAreaVO.year}" scope="request"/>
<c:set var="month" value="${memAreaVO.month}" scope="request"/>

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
<tr><td class="rnd" colspan="21"></td></tr>
<tr class="rndbg">
	<th width="16%"><font class=small><b>일별통계</b></font></th>
	<th  colspan="2"><font class=small><b>서울</b></font></th>
	<th  colspan="2"><font class=small><b>경기</b></font></th>
	<th  colspan="2"><font class=small><b>경남</b></font></th>
	<th  colspan="2"><font class=small><b>경북</b></font></th>
	<th  colspan="2"><font class=small><b>전남</b></font></th>
	<th  colspan="2"><font class=small><b>전북</b></font></th>
	<th  colspan="2"><font class=small><b>충남</b></font></th>
	<th  colspan="2"><font class=small><b>충북</b></font></th>
	<th  colspan="2"><font class=small><b>제주</b></font></th>
	<th  colspan="2"><font class=small><b>강원</b></font></th>
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
	<td align="center"><b>남</b></td>
	<td align="center" bgcolor="#F7F7F7"><b>여</b></td>
	<td align="center"><b>남</b></td>
	<td align="center" bgcolor="#F7F7F7"><b>여</b></td>
	<td align="center"><b>남</b></td>
	<td align="center" bgcolor="#F7F7F7"><b>여</b></td>
	<td align="center"><b>남</b></td>
	<td align="center" bgcolor="#F7F7F7"><b>여</b></td>
</tr>
<tr><td class=rnd colspan=21></td></tr>

<c:forEach items="${memAreaVO.memAreaList}" var="memAreaList" varStatus="st">

<tr height=25>
	<td align="center" bgcolor="#F7F7F7"><font class="ver8" color="444444">${memAreaList.day} (${dateUtil:getDayOfWeek(memAreaList.rdt,"ko")})</td>
	<td style="text-align:right;padding-right:10px"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_su_m_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color=EC4E00><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_su_w_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_gg_m_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color=EC4E00><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_gg_w_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_gn_m_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color=EC4E00><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_gn_w_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_gb_m_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color=EC4E00><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_gb_w_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_jn_m_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color=EC4E00><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_jn_w_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_jb_m_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color=EC4E00><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_jb_w_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_cn_m_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color=EC4E00><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_cn_w_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_cb_m_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color=EC4E00><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_cb_w_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_jj_m_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color=EC4E00><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_jj_w_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_gw_m_cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color=EC4E00><b>${stringUtil:getMoneyFormatInteger(memAreaList.rtList_gw_w_cnt)}</b></font></td>
	
	<!-- 합계 -->
	<c:set var="rtList_su_m_cnt_sum"  value="${(memAreaList.rtList_su_m_cnt)+rtList_su_m_cnt_sum }"/><!-- 서울(남) 건수 합 -->
	<c:set var="rtList_su_w_cnt_sum"  value="${(memAreaList.rtList_su_w_cnt)+rtList_su_w_cnt_sum }"/><!-- 서울(여) 건수 합 -->
	<c:set var="rtList_gg_m_cnt_sum"  value="${(memAreaList.rtList_gg_m_cnt)+rtList_gg_m_cnt_sum }"/><!-- 경기(남) 건수 합 -->
	<c:set var="rtList_gg_w_cnt_sum"  value="${(memAreaList.rtList_gg_w_cnt)+rtList_gg_w_cnt_sum }"/><!-- 경기(여) 건수 합 -->
	<c:set var="rtList_gn_m_cnt_sum"  value="${(memAreaList.rtList_gn_m_cnt)+rtList_gn_m_cnt_sum }"/><!-- 경남(남) 건수 합 -->
	<c:set var="rtList_gn_w_cnt_sum"  value="${(memAreaList.rtList_gn_w_cnt)+rtList_gn_w_cnt_sum }"/><!-- 경남(여) 건수 합 -->
	<c:set var="rtList_gb_m_cnt_sum"  value="${(memAreaList.rtList_gb_m_cnt)+rtList_gb_m_cnt_sum }"/><!-- 경북(남) 건수 합 -->
	<c:set var="rtList_gb_w_cnt_sum"  value="${(memAreaList.rtList_gb_w_cnt)+rtList_gb_w_cnt_sum }"/><!-- 경북(여) 건수 합 -->
	<c:set var="rtList_jn_m_cnt_sum"  value="${(memAreaList.rtList_jn_m_cnt)+rtList_jn_m_cnt_sum }"/><!-- 전남(남) 건수 합 -->
	<c:set var="rtList_jn_w_cnt_sum"  value="${(memAreaList.rtList_jn_w_cnt)+rtList_jn_w_cnt_sum }"/><!-- 전남(여) 건수 합 -->
	<c:set var="rtList_jb_m_cnt_sum"  value="${(memAreaList.rtList_jb_m_cnt)+rtList_jb_m_cnt_sum }"/><!-- 전북(남) 건수 합 -->
	<c:set var="rtList_jb_w_cnt_sum"  value="${(memAreaList.rtList_jb_w_cnt)+rtList_jb_w_cnt_sum }"/><!-- 전북(여) 건수 합 -->
	<c:set var="rtList_cn_m_cnt_sum"  value="${(memAreaList.rtList_cn_m_cnt)+rtList_cn_m_cnt_sum }"/><!-- 충남(남) 건수 합 -->
	<c:set var="rtList_cn_w_cnt_sum"  value="${(memAreaList.rtList_cn_w_cnt)+rtList_cn_w_cnt_sum }"/><!-- 충남(여) 건수 합 -->
	<c:set var="rtList_cb_m_cnt_sum"  value="${(memAreaList.rtList_cb_m_cnt)+rtList_cb_m_cnt_sum }"/><!-- 충북(남) 건수 합 -->
	<c:set var="rtList_cb_w_cnt_sum"  value="${(memAreaList.rtList_cb_w_cnt)+rtList_cb_w_cnt_sum }"/><!-- 충북(여) 건수 합 -->
	<c:set var="rtList_jj_m_cnt_sum"  value="${(memAreaList.rtList_jj_m_cnt)+rtList_jj_m_cnt_sum }"/><!-- 제주(남) 건수 합 -->
	<c:set var="rtList_jj_w_cnt_sum"  value="${(memAreaList.rtList_jj_w_cnt)+rtList_jj_w_cnt_sum }"/><!-- 제주(여) 건수 합 -->
	<c:set var="rtList_gw_m_cnt_sum"  value="${(memAreaList.rtList_gw_m_cnt)+rtList_gw_m_cnt_sum }"/><!-- 강원(남) 건수 합 -->
	<c:set var="rtList_gw_w_cnt_sum"  value="${(memAreaList.rtList_gw_w_cnt)+rtList_gw_w_cnt_sum }"/><!-- 강원(여) 건수 합 -->
	
	
</tr>
<tr><td colspan=21 class=rndline></td></tr>

</c:forEach>


<tr><td colspan=21 bgcolor=A3A3A3></td></tr>
<tr height=25 bgcolor="#C5C5C5">
	<td align="center" bgcolor="#EDEDED">
		${stringUtil:getMoneyFormatInteger( rtList_su_m_cnt_sum + rtList_su_w_cnt_sum+ rtList_gg_m_cnt_sum + rtList_gg_w_cnt_sum+ rtList_gn_m_cnt_sum + rtList_gn_w_cnt_sum
			+ rtList_gb_m_cnt_sum + rtList_gb_w_cnt_sum+ rtList_jn_m_cnt_sum + rtList_jn_w_cnt_sum+ rtList_jb_m_cnt_sum + rtList_jb_w_cnt_sum
			+ rtList_cn_m_cnt_sum + rtList_cn_w_cnt_sum+ rtList_cb_m_cnt_sum + rtList_cb_w_cnt_sum+ rtList_jj_m_cnt_sum + rtList_jj_w_cnt_sum
			+ rtList_gw_m_cnt_sum + rtList_gw_w_cnt_sum
		)}
	</td>
	<td style="text-align:right;padding-right:10px" bgcolor="white"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(rtList_su_m_cnt_sum )}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color="1259C3"><b>${stringUtil:getMoneyFormatInteger(rtList_su_w_cnt_sum )}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="white"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(rtList_gg_m_cnt_sum )}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color="1259C3"><b>${stringUtil:getMoneyFormatInteger(rtList_gg_w_cnt_sum )}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="white"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(rtList_gn_m_cnt_sum )}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color="1259C3"><b>${stringUtil:getMoneyFormatInteger(rtList_gn_w_cnt_sum )}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="white"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(rtList_gb_m_cnt_sum )}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color="1259C3"><b>${stringUtil:getMoneyFormatInteger(rtList_gb_w_cnt_sum )}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="white"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(rtList_jn_m_cnt_sum )}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color="1259C3"><b>${stringUtil:getMoneyFormatInteger(rtList_jn_w_cnt_sum )}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="white"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(rtList_jb_m_cnt_sum )}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color="1259C3"><b>${stringUtil:getMoneyFormatInteger(rtList_jb_w_cnt_sum )}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="white"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(rtList_cn_m_cnt_sum )}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color="1259C3"><b>${stringUtil:getMoneyFormatInteger(rtList_cn_w_cnt_sum )}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="white"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(rtList_cb_m_cnt_sum )}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color="1259C3"><b>${stringUtil:getMoneyFormatInteger(rtList_cb_w_cnt_sum )}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="white"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(rtList_jj_m_cnt_sum )}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color="1259C3"><b>${stringUtil:getMoneyFormatInteger(rtList_jj_w_cnt_sum )}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="white"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(rtList_gw_m_cnt_sum )}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color="1259C3"><b>${stringUtil:getMoneyFormatInteger(rtList_gw_w_cnt_sum )}</b></font></td>
</tr>
<tr><td colspan=21 class=rndline></td></tr>
</table>
<p/>
<div id=MSG01>
<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">위 지역별 회원분석은 입금확인일(결제완료일) 기준이며, 주문취소금액을 제한 통계자료입니다.</td></tr>
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