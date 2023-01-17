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
<script src="/resources/shop/admin/DynamicTreeShifting.js"></script>
<script src="/resources/shop/admin/DynamicTreeHidding.js"></script>
<script language=javascript src="/resources/shop/admin/common.js"></script>
<script language="javascript">
	/*
	 * php script
	 */
	/*** 분류트리 하부노드 로딩 ***/
	function openTree(obj, chkable){
		tree.sorting.ready(obj);
		if (chkable && ifrmCategory.document.getElementsByName('category')[0] != undefined){
			if (ifrmCategory.document.getElementsByName('category')[0].value == obj.getElementsByTagName('input')[0].value) return;
		}
		ifrmCategory.location.href = "iframe.category?ifrmScroll=1&category=" + obj.getElementsByTagName('input')[0].value + "<%-- <%= StringUtil.nullConv(request.getQueryString(), "") %> --%>";
	}

	function loadHistory(category, chkable){
		var len = category.length / 3;
		var el = "cate" + len + "[]";
		var obj = document.getElementsByName(el);
		for (var i=0;i<obj.length;i++){
			if (obj[i].value==category){
				openTree(obj[i].parentNode, chkable);
				break;
			}
		}
	}
	
	//엑셀 다운로드
	function excelDownload(){
		$("#excelForm").submit();
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
/*
 * jQuery ready
 */
$(function(){
	
});
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
<form id="excelForm" method=post action="/shop/admin/goods/category/excelDownload">
</form>


		<div class="title title_top">상품분류[카테고리]관리<span>편리하게 상품분류 및 분류페이지를 관리하실 수 있습니다</span> </div>
		<table width=100%>
			<tr>
				<td valign=top>
				
<form method=post action="indb">
<input type=hidden name=mode value="chgCategorySort">
<input type=hidden name=category value="${cateVO.category}">

					<div style="padding: 0 0 3 5">
				<!-- if ( !preg_match( "/^rental_mxfree/i", $godo[ecCode] ) ){ -->
						<img src="/resources/shop/admin/img/i_cate_eye_on.gif" align=absmiddle><font class=small1 color=444444>사용</font>
						<img src="/resources/shop/admin/img/i_cate_eye_off.gif" align=absmiddle><font class=small1 color=444444>미사용</font>
				<!-- } -->
					</div>

					<!-- ====================================================================== 
					--	카테고리 tree 영역 
					======================================================================== -->
					<div id=treeCategory class=scroll onmousewheel="return iciScroll(this)">

						<div style="padding-bottom:1px">
							<a id=node_top href="javascript:void(0)" onclick="openTree(this)" onfocus=blur()>1차분류만들기<input type=hidden name=cate[] value=""></a> (최상위분류)
						</div>
						
						<!-- ====================================================================== 
						--	데이터 tree 영역 
						======================================================================== -->
						<div class="DynamicTree"><div class="wrap" id="tree"></div></div>

					</div>

</form>

					<div style="clear:both;"></div>

					<div style="text-align: center; margin-top: 10px;">
						<input type="image" class="button_top" src="/resources/shop/admin/img/btn_excel_download.gif" alt="엑셀다운로드" border="0" align="absmiddle" style="cursor:hand" onclick="javaScript:excelDownload(); return false;">
					</div>
					
					<div id=MSG01>
						<table cellpadding=1 cellspacing=0 border=0 class=small_ex style="padding-top:0px;">
							<tr><td>
								<div><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">1차분류만들기을 누르고 1차분류를 생성하세요.</div>
								<div style="padding-top:2"><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">1차분류를 누르고 2차분류를 생성하세요.</div>
								<div style="padding-top:2"><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle"><img src="/resources/shop/admin/img/icon_plus.gif" align=absmiddle> 를 누르면 하위분류가 보입니다.</div>
								<!-- div style="padding-top:2"><img src="../img/icon_list.gif" align="absmiddle">분류순서변경 = 키보드 상하이동키 이용</div-->
						<!-- if ( !preg_match( "/^rental_mxfree/i", $godo[ecCode] ) ){ -->
								<div style="padding-top:2"><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">분류감춤기능 = 아래처럼 손쉽게 설정</div>
								<div style="padding:2 0 0 10"><img src="/resources/shop/admin/img/i_cate_eye_on.gif" align=absmiddle> : 클릭하면 감춤모드로 설정</div>
								<div style="padding:2 0 0 10"><img src="/resources/shop/admin/img/i_cate_eye_off.gif" align=absmiddle> : 클릭하면 보임모드로 설정</div>
								<!-- div style="padding-top:2"><img src="../img/icon_list.gif" align="absmiddle">분류이동기능 (아래 사용방법 참조)</div-->
						<!-- } -->
							</td></tr>
						</table>
					</div>

<script>cssRound('MSG01')</script>

				</td>
				<td valign=top width=100% style="padding-left:10px">
					<iframe id=ifrmCategory name=ifrmCategory src="iframe.category?ifrmScroll=1${cateVO.category !=null && cateVO.category != '' ? '&category='+cateVO.category : '' }" style="width:100%;height:500px" frameborder=0></iframe>
				</td>
			</tr>
		</table>

<script type="text/javascript">
	var tree = new DynamicTree("tree");
	tree.category = '${cateVO.category != null ? cateVO.category : "" }';
	tree.useHidding = true;
	tree.useShifting = true;
	tree.init();
	tree.Sorting();
</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

			<%@ include file="../common/bottom.jsp"%>
		</td>
	</tr>
</table>
