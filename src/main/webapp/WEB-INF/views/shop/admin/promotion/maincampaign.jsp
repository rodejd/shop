<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>
<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language=javascript src="/resources/shop/admin/common.js"></script>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
	/*
	 * jQuery ready
	 */
	$(function(){
		//등록
		$(".btn_register").on("click",function(){
			$("#mode").val("register");
			$('#schForm').attr('action', '/shop/admin/promotion/campaign/register').submit();
		});
	});
	
	// 전시기간
	var setPeriod = function(dt1, dt2){
		if ( null != dt1 ){
			$('[name=ssdt]').val(dt1);
		} else {
			$('[name=ssdt]').val('');
		}
		
		if ( null != dt2 ){
			$('[name=sedt]').val(dt2);
		} else {
			$('[name=sedt]').val('');
		}
	};
	
	//수정
	function goView(_sno){	
		$("#sno").val(_sno);
		$("#mode").val("modify");
	 	$('#schForm').attr('action', '/shop/admin/promotion/campaign/register').submit();
	}
	
	//삭제
	function fnDelete(_sno){
		if( !confirm("삭제하시겠습니까?") ) return;
		
		$("#sno").val(_sno);
		$("#mode").val("delete");
		$('#schForm').attr('action', '/shop/admin/promotion/campaign/indb').submit();
	}
	
	function goPage(page){
		$("#pageNo").val(page);
		$('#schForm').attr('action', '/shop/admin/promotion/campaign/list').submit();
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
<form id="schForm" action="/shop/admin/promotion/campaign/list" method="post">
	<input type="hidden" id="pageNo" name="pageNo"  value="${mainCampaignVO.pageNo != '' ? mainCampaignVO.pageNo : '1' }"/>
	<input type="hidden" id="sno" name="sno" value="0"/>
	<input type="hidden" id="mode" name="mode" />
	
	<div class="title title_top">메인 캠페인 관리</div>
	<table class="tb">
		<col class="cellC" /><col class="cellL" style="width:250" />
		<col class="cellC" /><col class="cellL" />
		<tr>
			<td>제목 검색</td>
			<td>
				<input type="text" name="sword" value="${mainCampaignVO.sword }" class="line" />
			</td>
			<td width=170>스킨검색</td>
			<td>
				<select name="schSkin">
					<option value="">전체</option>
					<option value="al" ${stringUtil:selected(mainCampaignVO.schSkin, "al")}>ALL</option>
					${webUtil:makeSelectCodeItem((codeUtil:codeitem('skin')), mainCampaignVO.schSkin) }
				</select>
			</td>
		</tr>
		<tr>
			<td>전시기간</td>
			<td colspan=3>
				<input type="text" name="ssdt" value="${mainCampaignVO.ssdt }" onclick="calendar(event)" class="cline" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);"/> ~
				<input type="text" name="sedt" value="${mainCampaignVO.sedt }" onclick="calendar(event)" class="cline" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);"/>
			 	<a href="javascript:setPeriod(${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align="absmiddle" /></a>
				<a href="javascript:setPeriod(${dateUtil:getDateFormatFrom('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-7)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align="absmiddle" /></a>
				<a href="javascript:setPeriod(${dateUtil:getDateFormatFrom('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-15)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align="absmiddle" /></a>
				<a href="javascript:setPeriod(${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-1)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align="absmiddle" /></a>
				<a href="javascript:setPeriod(${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-2)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align="absmiddle" /></a>
				<a href="javascript:setPeriod()"><img src="/resources/shop/admin/img/sicon_all.gif" align="absmiddle" /></a>  
			</td>
		</tr>
	</table>
	<div class="button_top"><input type="image" src="/resources/shop/admin/img/btn_search2.gif" /></div>
	
	<table width="100%">
		<tr>
			<td class="pageInfo">
				<c:set var="pages" value="${(mainCampaignVO.rowCount * 10) / (mainCampaignVO.pageSize * 10)} " />
				<c:set var="pageCnt" value="${pages + (1 - (pages % 1)) % 1}" />
				<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
				총 <font class="ver8"><b>${mainCampaignVO.totalCnt}</b>개, 검색 <b>${mainCampaignVO.rowCount}</b>개, <b>${mainCampaignVO.pageNo}</b> of ${var3} Pages</font>
			</td>
		</tr>  
	</table>

	<table class="listTable" width=100% cellpadding=0 cellspacing=0>
		<col style="width:10%;" />
		<col style="width:10%;" />
		<col />
		<col style="width:20%;" />
		<col style="width:10%;" />
		<col style="width:10%;" />
		<col style="width:10%;" />
		<tr class=rndbg>
			<th>번호</th>
			<th>스킨</th>
			<th>캠페인명</th>
			<th>기간</th>
			<th>전시여부</th>
			<th>등록일</th>
			<th>관리</th>
		</tr>
		<c:choose>
			<c:when test="${not empty mainCampaignVO.campaignList}">
				<c:forEach items="${mainCampaignVO.campaignList}" var="list" varStatus="vnum">
			 		<tr height=25 align="center" onmouseover='this.style.background="#F7F7F7"' onmouseout='this.style.background=""'>
						<td><font class=ver8 color=616161>${(mainCampaignVO.rowCount - vnum.index) - ( mainCampaignVO.rowStart ) }</font></td>
						<td><font class="ver81" color="#616161">${list.skin eq 'al' ? 'ALL' : codeUtil:getCodeName("skin", list.skin)}</font></td><!-- 스킨 -->
						<td style="text-align: left;"><a href="javascript:;" onclick="javascript:goView('${list.sno}');"><font color="#0074ba"><b>${list.title}</b></font></a></td>
						<td>${dateUtil:getDateFormat("yyyy-MM-dd", list.sdt)} ~ ${dateUtil:getDateFormat("yyyy-MM-dd", list.edt)}</td>
						<td>${list.useYn eq 'Y' ? '전시중' : '전시대기'}</td>
						<td><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd"/></td>
						<td><a href="javascript:fnDelete('${list.sno}');"><img src="/resources/shop/admin/img/i_del.gif"></a></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr height=25 align="center" onmouseover='this.style.background="#F7F7F7"' onmouseout='this.style.background=""'>
					<td colspan="7" align="center">내역이 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<c:if test="${not empty mainCampaignVO.campaignList}">
		<div align=center class=pageNavi>
			<b><tags:paginator currentPageNo="${mainCampaignVO.pageNo}" rowCount="${mainCampaignVO.rowCount}" pageSize="${mainCampaignVO.pageSize}"  pageGroupSize="${mainCampaignVO.pageGroupSize}" /></b>
		</div>
	</c:if>
	
	<div class="button">
		<a href="javascript:;" class="btn_register"><img src="/resources/shop/admin/img/btn_register.gif"></a>
	</div>
</form>
<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>
