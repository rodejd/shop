<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../../common/header.jsp" %>
<%@ include file="../../common/left.jsp" %>


<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
/*
 * jQuery ready
 */
function goPage(page){
	window.location.href="goods?year=${popuGoodsVO.year}&month=${popuGoodsVO.month}&pageNo="+page;
}
</script>

<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<div class="title title_top">인기 상품분석</div>

<c:set var="year" value="${popuGoodsVO.year}" scope="request"/>
<c:set var="month" value="${popuGoodsVO.month}" scope="request"/>

<form method=get>
<input type="hidden" name="category" value="" />
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
<p>
<table width="100%" cellpadding="0" cellspacing="0">
<tr><td class="rnd" colspan="10"></td></tr>
<tr class="rndbg">
	<th><font class="small"><b>번호</th>
	<th bgcolor="63544B"><font class="small"><b>상품명</b></font></th>
	<th><font class="small"><b>구매자수</b></font></th>
	<th bgcolor="63544B"><font class="small"><b>구매수량</b></font></th>
	<th><font class="small"><b>매출액</b></font></th>	
</tr>
<tr><td class=rnd colspan="10"></td></tr>

<c:forEach items="${popuGoodsVO.popuGoodsList}" var="popuGoodsList" varStatus="st">

<tr height=25>
	<td align=center bgcolor="#F7F7F7"><font class="ver8" color="444444">${(popuGoodsVO.rowCount - st.index) - ( (popuGoodsVO.pageNo - 1)  *  10 ) }</font></td>
	<td style="padding-left:10px"><font class="small" color="777777"><b>${popuGoodsList.goodsnm}</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="#F7F7F7"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(popuGoodsList.cnt)}</b></font></td>
	<td style="text-align:right;padding-right:10px"><font class="ver8" color="EC4E00"><b>${stringUtil:getMoneyFormatInteger(popuGoodsList.ea)}</b></font></td>	
	<td style="text-align:right;padding-right:10px" bgcolor="#f7f7f7"><font class="ver8" color="EC4E00"><b>${stringUtil:getMoneyFormatInteger(popuGoodsList.price)}</b></font></td>
	
	<!-- 합계 -->
	<c:set var="cnt_sum"  value="${(popuGoodsList.cnt)+cnt_sum }"/><!-- 구매자수 합 -->
	<c:set var="ea_sum"  value="${(popuGoodsList.ea)+ea_sum }"/><!-- 구매수량 합 -->	
	<c:set var="price_sum"  value="${(popuGoodsList.price)+price_sum }"/><!-- 매출액 합 -->
</tr>
<tr><td colspan="10" class="rndline"></td></tr>

</c:forEach>

<tr><td colspan="10" bgcolor="A3A3A3"></td></tr>
<tr height=25 bgcolor="#C5C5C5">
	<td align=center bgcolor="#EDEDED">합계</td>
	<td align=center bgcolor="white"><font class="ver8" color="6C6C6C">-</font></td>	
	<td style="text-align:right;padding-right:10px" bgcolor="#EDEDED"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(cnt_sum) }</b></font></td>
	<td style="text-align:right;padding-right:10px" bgcolor="white"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(ea_sum) }</b></font></td>	
	<td style="text-align:right;padding-right:10px" bgcolor="#EDEDED"><font class="ver8" color="6C6C6C"><b>${stringUtil:getMoneyFormatInteger(price_sum) }</b></font></td>
</tr>
<tr><td colspan="10" class="rndline"></td></tr>
</table>
	<!-- 페이징  -->
	<tags:paginator currentPageNo="${popuGoodsVO.pageNo}" rowCount="${popuGoodsVO.rowCount}" pageSize="${popuGoodsVO.pageSize}"  pageGroupSize="${popuGoodsVO.pageGroupSize}" />
<div align=center style='padding:20'></div>
<p />
<div id=MSG01>
<table cellpadding=1 cellspacing=0 border=0 class="small_ex">
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">위 인기 상품분석은 입금확인일(결제완료일) 기준이며, 주문취소금액을 제한 통계자료입니다.</td></tr>
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