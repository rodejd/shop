<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>


<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language=javascript src="/resources/shop/admin/common.js"></script>
<script language="javascript">

</script>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
/*
 * jQuery ready
 */
$(function(){
	
});
/*
 * php script
 */
var iciRow, preRow;

function spoit(obj)
{
iciRow = obj;
iciHighlight();
}

function iciHighlight()
{
if (preRow) preRow.style.backgroundColor = "";
iciRow.style.backgroundColor = "#FFF4E6";
preRow = iciRow;
}

function moveTree(idx, move)
{
var objTop = document.getElementById("moveTable");
var nextPos = iciRow.rowIndex+idx;
if (nextPos==objTop.rows.length) nextPos = 0;
if(objTop.moveRow) {
	objTop.moveRow(iciRow.rowIndex,nextPos);	
}else{
	if(move == 38){
		iciRow.parentNode.insertBefore(iciRow.parentNode.rows[iciRow.rowIndex],iciRow.parentNode.rows[nextPos]);
	} else{
		iciRow.parentNode.insertBefore(iciRow.parentNode.rows[nextPos],iciRow.parentNode.rows[iciRow.rowIndex]);
	}
	
}

}

function keydnTree()
{
if (iciRow==null) return;
switch (event.keyCode){
	case 38: moveTree(-1, 38); break;
	case 40: moveTree(1, 40); break;
}
return false;
}

document.onkeydown = keydnTree;

</script>


<iframe name="ifrmHidden" src="../../blank.jsp" style="display:block;width:100%;height:0px;border:0;" scrolling="yes"></iframe>
<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

		<div class="title title_top">분류페이지 상품진열 <span>각 분류페이지의 상품진열순서를 정하실 수 있습니다</span> 
		<!-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=product&no=8',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle hspace=2></a> -->		
		</div>

<form method=post>
		<table class=tb>
			<col class=cellC><col class=cellL>
			<tr>
				<td>분류선택</td>
				<td><script>new categoryBox('cate',4,'${sortVO.category}','multiple');</script></td>
			</tr>
		</table>
		<div class=button_top><input type=image src="/resources/shop/admin/img/btn_search2.gif"></div>

		<table cellpadding=0 cellspacing=0 class=small_tip bgcolor=F7F7F7 width=100%>
			<tr><td height=10></td></tr>
			<tr><td style="padding-left:20px"><img src="/resources/shop/admin/img/arrow_downorg.gif" align=absmiddle> 상품진열 순서변경 도움말</font></td></tr>
			<tr><td style="padding-left:20px"><img src="/resources/shop/admin/img/sa_cate_change.gif" style="border:2px solid #D4D3D3;"></td></tr>
			<tr><td height=10></td></tr>
		</table><div style="padding-top:15px"></div>

		<div align=right style="margin-bottom:3px;">
			<img src="/resources/shop/admin/img/sname_output.gif" align=absmiddle>
			<select name=limitCnt onchange="this.form.submit()">
				<option value="50" selected>50개 출력
				<option value="100" ${sortVO.limitCnt == '100' ? 'selected' : ''}>100개 출력
				<option value="200" ${sortVO.limitCnt == '200' ? 'selected' : ''}>200개 출력
				<option value="300" ${sortVO.limitCnt == '300' ? 'selected' : ''}>300개 출력
			</select>
		</div>
</form>

<form method=post action="category/indb2" target="ifrmHidden">
<input type=hidden name=mode value="sortGoods">
		<table width=100% border=1 bordercolor=#dfdfdf style="border-collapse:collapse" frame=hsides rules=rows id="moveTable">
		
		<c:forEach items="${sortVO.goodsList }" var="list" varStatus="vnum">
			
			<tr onclick="spoit(this)" id ="moveTr">
				<td align=center bgcolor=#f7f7f7 width=40 nowrap><font class=small1 color=444444>${vnum.index+1 }</font></td>
				<td width=100% style="padding-left:5px">
					<a href="../../goods/goods_view?goodsno=${list.goodsno}" target=_blank><%-- <%= this.goodsimg(rtList.get(i, "img_s"), "25", "align=absmiddle",1)%> --%></a> 
					&nbsp;<a href="javascript:popup('popup.register?mode=modify&goodsno=${list.goodsno}',825,600)">${list.goodsnm}</a>
				<td align=right style="padding-right:10px" width=100 nowrap><font class=ver8 color=444444>₩${stringUtil:getMoneyFormatInteger(list.price)}</td>
				<td align=center width=100 nowrap><font class=ver8 color=444444>${list.sort }</font>
					<input type=hidden name=arrSno value="${list.sno}">
					<input type=hidden name=arrSort value="${list.sort}">
				</td>
			</tr>
		
		</c:forEach>
		</table>

		<div class=button>
			<input type=image src="/resources/shop/admin/img/btn_save.gif">
			<a href="list"><img src='/resources/shop/admin/img/btn_list.gif'></a>
		</div>

		</form>

		<div id=MSG01>
			<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">분류별페이지마다 구매자에게 어필하는 상품을 효과적으로 순서를 정해 진열하세요.</td></tr>
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">구매자들은 보통 특정분류에서 상품을 조회하고 구매의욕을 갖게 되는데 이때 상품의 진열은 중요합니다.<td></tr>
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">각 분류별로 최대 300개의 상품순서를 변경하실 수 있습니다.</td></tr>
			</table>
		</div>
		<script>cssRound('MSG01')</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

			<%@ include file="../common/bottom.jsp"%>
		</td>
	</tr>
</table>