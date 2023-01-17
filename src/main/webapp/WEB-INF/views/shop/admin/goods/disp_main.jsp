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
	/*
	 * php script
	 */
	var r_list = new Array('step0','step1','step2','step3','step4');
	
	function open_box(name,isopen)
	{
		show_elements("select", eval("obj2_"+name));
		var mode;
		var isopen = (isopen || document.getElementById('obj_'+name).style.display!="block") ? true : false;
		for (i=0;i<r_list.length;i++){
			mode = (r_list[i]==name && isopen) ? "block" : "none";
			document.getElementById('obj_'+r_list[i]).style.display = document.getElementById('obj2_'+r_list[i]).style.display = mode;
		}
		if (document.getElementById('obj_'+name).style.display=="block") hide_elements("select", eval("obj2_"+name));
		else iciRow = null;
	}
	
	function list_goods(name)
	{
		var category = '';
		open_box(name,true);
		var els = document.forms[0][name+'[]'];
		for (i=0;i<els.length;i++) if (els[i].value) category = els[i].value;
		var ifrm = eval("ifrm_" + name);
		var goodsnm = eval("document.forms[0].search_" + name + ".value");
		ifrm.src = ctx+"/shop/admin/goods/iframeList?name=" + name + "&schCate=" + category + "&schKey=all&schWord=" + goodsnm;
	}
	function go_list_goods(name){
		if (event.keyCode==13){
			list_goods(name);
			return false;
		}
	}
	function view_goods(name)
	{
		open_box(name,false);
	}
	function remove_goods(name,obj)
	{
		var tb = document.getElementById('tb_'+name);
		tb.deleteRow(obj.rowIndex);
		react_goods(name);
	}
	
	function react_goods(name)
	{
		var tmp = new Array();
		var obj = document.getElementById('tb_'+name);
		for (i=0;i<obj.rows.length;i++){
			tmp[tmp.length] = "<div style='float:left;width:50;border:1 solid #cccccc;margin:1px;' title='" + obj.rows[i].cells[1].getElementsByTagName('div')[0].innerText + "'>" + obj.rows[i].cells[0].innerHTML + "</div>";
		}
		document.getElementById(name+'X').innerHTML = tmp.join("") + "<div style='clear:both'>";
	}
	
	function hide_elements(tagName, menu){
		windowed_element_visibility(tagName, -1, menu)
	}
	
	function show_elements(tagName, menu){
		windowed_element_visibility(tagName, +1, menu)
	}
	
	function windowed_element_visibility(tagName, change, menu){
		var els = document.getElementsByTagName(tagName);
		var rect = new element_rect(menu)
		for (i=0; i < els.length; i++){
			var el = els.item(i);
			if (elements_overlap(el, rect)){
				if (change==-1){
					if (el.name!='yearS' && el.name!='monthS') el.style.visibility = "hidden";
				} else el.style.visibility = "visible";
			}
		}
	}
	
	function element_rect(el){
		var left = 0
		var top = 0
		this.width = el.offsetWidth
		this.height = el.offsetHeight
		while (el){
			left += el.offsetLeft
			top += el.offsetTop
			el = el.offsetParent
		}
		this.left = left;
		this.top = top;
	}
	
	function elements_overlap(el, rect){
		var r = new element_rect(el);
		return ((r.left < rect.left + rect.width) && (r.left + r.width > rect.left) && (r.top < rect.top + rect.height) && (r.top + r.height > rect.top))
	}
	
	var iciRow, preRow, nameObj;
	
	function spoit(name,obj)
	{
		nameObj = name;
		iciRow = obj;
		iciHighlight();
	}
	
	function iciHighlight()
	{
		if (preRow) preRow.style.backgroundColor = "";
		iciRow.style.backgroundColor = "#FFF4E6";
		preRow = iciRow;
	}
	
	function moveTree(idx)
	{
		if (document.getElementById("obj_"+nameObj).style.display!="block") return;
		var objTop = iciRow.parentNode.parentNode;
		var nextPos = iciRow.rowIndex+idx;
		if (nextPos==objTop.rows.length) nextPos = 0;
		objTop.moveRow(iciRow.rowIndex,nextPos);
		react_goods(nameObj);
	}
	
	function keydnTree()
	{
		if (iciRow==null) return;
		switch (event.keyCode){
			case 38: moveTree(-1); break;
			case 40: moveTree(1); break;
		}
		return false;
	}
	
	document.onkeydown = keydnTree;
	
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
		
<form id=form action="dispMain/indb" target="" method=post>
<input type=hidden name=mode value="disp_main">
<c:forEach var="i" begin="0" end="4" step="1">
		<div class=title ${i eq 0 ? "style='margin-top:0'":""}>
			메인페이지 상품진열 ${i+1} 
			<span><a href="../../main/index" target=_blank><font color=0074BA>[메인화면보기]</font></a></span>
		</div>

		<table class=tb>
			<col class=cellC><col class=cellL>
			<tr>
				<td>설명</td>
				<td>
					<input type=text name=title value="${dispMainVO.list[i].title}" class=lline maxlength="20"> 
				</td>
			</tr>
			
			<tr>
				<td>사용유무</td>
				<td class=noline>
					<input type=checkbox name=chk[${i}]  ${dispMainVO.list[i].chk eq "on" ? "checked" : "" }> 체크시 메인페이지에 출력이됩니다
				</td>
			</tr>
			<tr>
				<td>이미지 선택</td>
				<td>
					<select name=img>
						<option value="img_s" ${dispMainVO.list[i].img eq "img_s" ? "selected" : "" }> 리스트이미지 (${shopLibFunction:getProperty("img_s")}px)
						<option value="img_i" ${dispMainVO.list[i].img eq "img_i" ? "selected" : "" }> 메인이미지 (${shopLibFunction:getProperty("img_i")}px)
					</select>
					<font class=extext>적당한 사이즈의 이미지를 고르세요</font>
				</td>
			</tr>
			<tr>
				<td>메인출력 상품수</td>
				<td><input type=text name=page_num value="${dispMainVO.list[i].page_num}" class="rline" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" maxlength="2"/> 개 <font class=extext>메인페이지에 보여지는 총 상품수입니다</td>
			</tr>
			<tr>
				<td>
					진열할 상품선정<div style="padding-top:3px"></div>
					<div style="padding-top:3px"></div>
				</td>
				<td>
					<div style="padding-top:5px;z-index:-10">
						<script>new categoryBox('step${i}[]',4,'','');</script>
						<input type=text name=search_step${i} onkeydown="return go_list_goods('step${i}')">
						<a href="javascript:list_goods('step${i}')"><img src="/resources/shop/admin/img/i_search.gif" border=0></a>
						<a href="javascript:view_goods('step${i}')"><img src="/resources/shop/admin/img/i_openclose.gif" border=0></a>
					</div>
					
					<div style="position:relative;">
						<div id=obj_step${i} class=box1><iframe id=ifrm_step${i} style="width:100%;height:100%" frameborder=0></iframe></div>
						<div id="obj2_step${i}" class="box2 scroll" onselectstart="return false" onmousewheel="return iciScroll(this)">
							<div class=boxTitle>- 메인상품디스플레이 <font class=small1 color=white>(삭제시 더블클릭${i}):::::::::${fn:length(dispMainVO.arrMap[i])}</font></div>
							<table id=tb_step${i} class=tb>
								<col width=50>
								<c:choose>
									<c:when test="${i eq 0 }">
										<c:set var="imgList" value="${dispMainVO.arrMap['0']}" />
									</c:when>
									<c:when test="${i eq 1 }">
										<c:set var="imgList" value="${dispMainVO.arrMap['1']}" />
									</c:when>
									<c:when test="${i eq 2 }">
										<c:set var="imgList" value="${dispMainVO.arrMap['2']}" />
									</c:when>
									<c:when test="${i eq 3 }">
										<c:set var="imgList" value="${dispMainVO.arrMap['3']}" />
									</c:when>
									<c:otherwise>
										<c:set var="imgList" value="${dispMainVO.arrMap['4']}" />
									</c:otherwise>
								</c:choose>
								<c:if test='${imgList ne null}'>
									<c:forEach items="${imgList}" var="listImg" varStatus="status">
										<c:set var="subMap" value="${listImg}" />
										<tr onclick="spoit('step${i}',this)" ondblclick="remove_goods('step${i}',this)" class="hand">
										<td width=50 nowrap><a href="javascript:;">
										${shopLibFunction:goodsimg(subMap.img_s,"40,40","",4)}
										</a></td>
										<td width=100%>
											<div>${subMap.goodsnm}</div>
											<c:set var="price" value="${subMap.price}" />
											<b><fmt:formatNumber value="${price}" pattern="\#,###.##"/></b>
											<input type=hidden name=e_step${i}[] value="${subMap.goodsno}">
										</td>
									</tr>
									</c:forEach>
								</c:if>
							</table>
						</div>
						<div style="padding-top:2px"></div>
						<font class=extext>진열할 상품을 많이 선정해놓으세요! 선정한 상품들은 메인에서도 보여지고 해당페이지에서도 보여집니다! <%//<a href="javascript:alert('준비중입니다..')"><!-- javascript:popup('http://guide.godo.co.kr/guide/php/ex_display.html',850,523) --><img src="../img/icon_sample.gif" border="0" align=absmiddle hspace=2></a><!--상품이미지를 클릭하면 해당상품 화면을 볼 수 있습니다--></font>%>
						<div id=step${i}X style="padding-top:3px"></div>
						<script>react_goods('step${i}');</script>
					</div>
				</td>
			</tr>
		</table>
		<input type="hidden" name="cols" value="0"/>
  		<input type="hidden" name=tpl[${i}] value="tpl_01"/>
</c:forEach>

		<div class=button>
			<input type=image src="/resources/shop/admin/img/btn_save.gif">
			<!-- <a href="list"><img src='../img/btn_list.gif'></a> -->
		</div>

</form>

		<div id=MSG01>
			<table cellpadding=2 cellspacing=0 border=0 class=small_ex>
				<!--<tr><td><img src="../img/arrow_blue.gif" align=absmiddle>상품디스플레이 선정방법</td></tr>-->
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>분류를 선택하고 검색버튼을 누르세요. 또는 검색어를 입력후 검색버튼을 누르세요.</td></tr>
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>검색된 상품을 더블클릭하면 진열되며, 선정된 상품을 더블클릭하면 삭제됩니다.</td></tr>
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>상품진열 순서를 변경하시려면 변경할 상품을 선택한 후 키보드의 이동키 Up/Down키로 이동하시면 됩니다.</td></tr>
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>펼쳐진 창을 닫고 아래의 저장버튼을 누르셔야 최종 저장됩니다.</td></tr>
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>진열할 수 있는 상품의 갯수는 무제한입니다.</td></tr>
			</table>
		</div>
		<script>cssRound('MSG01')</script>

		
<script type="text/javascript">

$(document).ready(function(){
 	for(var i=0; i<$('input[type="text"]').length; i++){
		var field = $('input[type="text"]')[i];
 		chkMaxLength(field, $(this).attr('maxlength'));
	}
});

</script>
<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>
<%@ include file="../common/bottom.jsp" %>
	</td>
</tr>
</table>
<%-- ================================================================================
* 공통 HTML CONTENT 종료
================================================================================ --%>

<%-- ================================================================================
=====================================================================================
* 화면 종료
=====================================================================================
================================================================================ --%>

<%-- ================================================================================
* 업무 시작 catch 부분
================================================================================ --%>
