<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ page import="com.wepinit.wepinit_shop.xmall.common.ShopLibFunction" %>

<link rel="styleSheet" href="../style.css">
<script>
	function move(idx){
		var objName = '${goodsVO.name}';
		var tb0 = document.getElementById('tb0');
		var tb = parent.document.getElementById('tb_${goodsVO.name}');

		oTr = tb.insertRow(0);
		oTd = oTr.insertCell(-1);
		oTd.innerHTML = tb0.rows[idx].cells[0].innerHTML;
		oTd = oTr.insertCell(-1);
		oTd.innerHTML = tb0.rows[idx].cells[1].innerHTML;

		tb.rows[0].className = "hand";
		tb.rows[0].onclick = function(){ parent.spoit(objName, this); }
		tb.rows[0].ondblclick = function(){ parent.remove_goods(objName, this); }
		parent.react_goods('${goodsVO.name}');
	}
	
	function goPage(page){
		//model값 가져오기
		var category = '${goodsVO.schCate}';
		var name = '${goodsVO.name}';
		var goodsnm = '${goodsVO.goodsnm}';
		
//		window.location.href="http://localhost/shop/admin/goods/_goodslist?pageNo="+page;
		window.location.href=ctx+"/shop/admin/goods/iframeList?pageNo="+page+"&name="+name+"&schCate=" + category + "&schKey=all&schWord="+goodsnm;
	}
	
</script>

<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>
<%@ include file="../common/header_popup.jsp" %>
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

<!-- <body onselectstart="return false" scroll=no style="margin:0"> -->

<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

	<div id=register_goods style="padding:3px">
		<div class=boxTitle>- 상품리스트${goodsVO.name} <font class=small color=#F2F2F2>(등록하려면 더블클릭)</font></div>

		<table id=tb0 class=tb>
	
	<c:if test="${!empty goodsVO.goodsList && fn:length(goodsVO.goodsList)>0 }">
		<c:forEach items="${goodsVO.goodsList }" var="list" varStatus="status">
			<c:set var="bgColor" value="${0<((status.index)%2) ? '#f7f7f7' : '#ffffff' }"></c:set>

			<tr bgcolor="${bgColor }" ondblclick="move(this.rowIndex)" class=hand>
				<td width=50 nowrap>
				<c:choose>
					<c:when test="${'001' eq fn:substring(category, 0, 3) }">
						<a href="javascript:;">					
							${shopLibFunction:goodsimg(list.imgs,"40","",4)}
						</a>
					</c:when>
					
					<c:otherwise>

					<a href="javascript:;">
						${shopLibFunction:goodsimg(list.imgs,"40","",4)}
					</a>

					</c:otherwise>
					
				</c:choose>

				</td>
				<td width=100% nowrap>
					<div style="overflow:hidden;">${list.goodsnm}</div>
					<b><fmt:formatNumber value="${list.price}" pattern="₩#,###"/></b>
					<input type="hidden" name="e_${goodsVO.name }[]" value="${list.goodsno }">
				</td>
			</tr>

		</c:forEach>
	
	</c:if>
		</table>

	<!-- 페이징  -->
	<tags:paginator currentPageNo="${goodsVO.pageNo}" rowCount="${goodsVO.rowCount}" pageSize="${goodsVO.pageSize}"  pageGroupSize="${goodsVO.pageGroupSize}" />
		
	</div>
