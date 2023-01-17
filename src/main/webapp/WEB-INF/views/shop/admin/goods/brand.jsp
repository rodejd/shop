<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<link rel="stylesheet" type="text/css" href="/resources/shop/admin/DynamicTree.css">
<script src="/resources/shop/admin/DynamicTree.js"></script>
<script src="/resources/shop/admin/DynamicTreeSorting.js"></script>
<script language=javascript src="/resources/shop/admin/common.js"></script>
<script language="javascript">
/*
 * php script
 */

/*** 브랜드트리 하부노드 로딩 ***/
function openTree(obj, v)
{	
	
	var brandno = obj.parentNode.getElementsByTagName('input')[0].value;
	
	if (!(Boolean(brandno))){
//		brandno ="";
		brandno =0;
	};
	
	tree.sorting.ready(obj);
//	ifrmbrand.location.href = "iframe.brand?ifrmScroll=1&brandno="+brandno+"&brand=" + obj.parentNode.getElementsByTagName('input')[0].value;
	ifrmbrand.location.href = "iframe.brand?ifrmScroll=1&brandno="+brandno+"&selector=${selector}";
}

function loadHistory(brand)
{
	var el = "brand[]";
	var obj = document.getElementsByName(el);
	for (i=0;i<obj.length;i++){
		if (obj[i].value==brand){
			openTree(obj[i].parentNode);
			break;
		}
	}
}

function fnSetlistCount(tCount, uCount, nCount){
	$(".tCount").text(comma(tCount));
	$(".uCount").text(comma(uCount));
	$(".nCount").text(comma(nCount));
}
</script>

<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">

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
	
		<div class="title title_top">브랜드 관리<span>새로운 브랜드들을 등록하고 상품등록시 브랜드입력창에서 등록된 브랜드를 선택하게 합니다</span> </div>
		
		<form name="frmList" action="/shop/admin/goods/brand" method="POST">
			<table class="tb">
				<colgroup>
					<col class="cellC">
					<col class="cellL">
					<col class="cellC">
					<col class="cellL">
				</colgroup>
				<tbody>
					<tr>
						<td>사용여부</td>
						<td>
							<select name="schApprovalStatus">
								<option value="">전체</option>
								<option value="2" ${brandVO.schApprovalStatus eq '2' ? 'selected' : ''}>사용</option>
								<option value="3" ${brandVO.schApprovalStatus eq '3' ? 'selected' : ''}>미사용</option>
							</select>
						</td>
						<td>연관키워드</td>
						<td>
							<select name="schBrandMemo">
								<option value="">전체</option>
								<option value="Y" ${brandVO.schBrandMemo eq 'Y' ? 'selected' : ''}>있음</option>
								<option value="N" ${brandVO.schBrandMemo eq 'N' ? 'selected' : ''}>없음</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>브랜드명</td>
						<td><input type="text" name="schBrandnm" class="line" value="${brandVO.schBrandnm}" ></td>
						<td>브랜드 등록일</td>
						<td>
							<input type="text" name="schRegdt" value="${brandVO.schRegdt[0]}" onclick="calendar(event)" class="line" onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" maxlength="8"> -
							<input type="text" name="schRegdt" value="${brandVO.schRegdt[1]}" onclick="calendar(event)" class="line" onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" maxlength="8">
							<a href="javascript:setDate('schRegdt', ${dateUtil:getDate('yyyyMMdd')}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align="absmiddle"></a>
							<a href="javascript:setDate('schRegdt', ${dateUtil:getDateFrom('yyyyMMdd', -7)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align="absmiddle"></a>
							<a href="javascript:setDate('schRegdt', ${dateUtil:getDateFrom('yyyyMMdd', -15)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align="absmiddle"></a>
							<a href="javascript:setDate('schRegdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -1)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align="absmiddle"></a>
							<a href="javascript:setDate('schRegdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -2)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align="absmiddle"></a>
							<a href="javascript:setDate('schRegdt')"><img src="/resources/shop/admin/img/sicon_all.gif" align="absmiddle"></a>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="button_top"><input type="image" src="/resources/shop/admin/img/btn_search2.gif"></div>
		</form>
		
		<table class="tb" style="margin-top: 30px;">
			<colgroup>
				<col class="cellC">
				<col class="cellL">
				<col class="cellC">
				<col class="cellL">
				<col class="cellC">
				<col class="cellL">
			</colgroup>
			<tbody>
				<tr>
					<td align="center">총브랜드 수</td>
					<td class="tCount">0</td>
					<td align="center">사용브랜드 수</td>
					<td class="uCount">0</td>
					<td align="center">미사용브랜드 수</td>
					<td class="nCount">0</td>
				</tr>
			</tbody>
		</table>
		
		<table style="width:100%; margin-top: 5px;">
			<tr>
				<td valign=top>
					<form method=post action="indb">
						<input type=hidden name=mode value="chgBrandSort">
						<input type=hidden name=rtn_query>
						<div id=treeCategory class=scroll onmousewheel="return iciScroll(this)">
							<div style="padding-bottom:1px"><span><a id=node_top href="javascript:void(0)" onclick="openTree(this)" onfocus=blur()>TOP<input type=hidden name=brand[] value=""></a></span> (최상위)</div>
								<div class="DynamicTree"><div class="wrap" id="brandTree">
								<c:forEach items="${brandVO.brandList }"  var="list" varStatus="vnum">
									<div class="doc">
										<!-- 하위표시 이미지 추가 -->
										<img src="/resources/shop/admin/img/tree-leaf.gif" width="18" height="18" alt="">
										<span><a href="javascript:void(0)" onclick="openTree(this)" onfocus=blur()>
												${list.brandnm != '' ? list.brandnm : '_deleted' }<%-- <%= rtList.get(i, "brandnm") %> --%>
												<c:if test="${list.approvalStatus =='1'}">(승인 요청)</c:if>
												<input type=hidden name=brand[] value="${list.sno }" >
												
										</a></span>
									</div>
								</c:forEach>
								</div>
							</div>
						</div>
					</form>
					<div style="clear:both;"></div>
			
					<div id=MSG01>
						<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
							<tr>
								<td>
									<img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">TOP</font> 누르고 브랜드를 생성하세요. <br>
									<!-- <img src="../img/icon_list.gif" align="absmiddle">브랜드순서변경 = 키보드 상하이동키↓↑ -->
								</td>
							</tr>
						</table>
					</div>
					<script>cssRound('MSG01');</script>
				</td>
				<td valign=top width=100% style="padding-left:10px">
					<iframe id=ifrmbrand name=ifrmbrand src="iframe.brand?ifrmScroll=1&brandno=${brandVO.brandno}&selector=${selector}" style="width:100%;height:500px" frameborder=0></iframe>
				</td>
			</tr>
		</table>
	
	<script type="text/javascript">
		var tree = new DynamicTree("brandTree");
		//		tree.init();	// 내용이 출력이 안되므로 실행하지 말것
		tree.Sorting();
		
		if("" =='${brandVO.brandno}') {
			out.println("loadHistory('"+"${brandVO.brandno}"+"');");
		} else {
//			out.println("openTree(_ID('node_top'));");
		}
	</script>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

			<%@ include file="../common/bottom.jsp"%>
		</td>
	</tr>
</table>