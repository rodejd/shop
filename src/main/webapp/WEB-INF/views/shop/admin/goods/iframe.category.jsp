<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script type="text/javascript" src="/resources/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery.ajax.js"></script>

<style>
body {
	margin: 0
}
</style>
<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language=javascript src="/resources/shop/admin/common.js"></script>
<script language="javascript">
	/* 2017-08-22 추가 */
	function emptySub(){
		if(document.form.category.value == ""){
			if(document.form.sub.value == ""){
				alert("분류명을 입력하세요.");
				return false;
			}			
		}
		return true;
	}

	function goSubmit(){
		var sub = $('input[name=sub]').val();
		var blank_pattern = /[\s]/g;
		
		/* if(!sub){
			alert("이름을 넣어주세요");
			return false;
		} else{
			if(blank_pattern.test(sub)){
				alert("빈공백을 넣을수 없습니다");
				return false;
			}
		} */
		
		if(emptySub()){
			document.form.submit();
			window.parent.location.reload();
		}
	}

	/*** 관련상품 ***/
	function open_box(name,isopen)
	{
		var mode;
		var isopen = (isopen || document.getElementById('obj_'+name).style.display!="block") ? true : false;
		mode = (isopen) ? "block" : "none";
		document.getElementById('obj_'+name).style.display = document.getElementById('obj2_'+name).style.display = mode;
		if (document.getElementById('obj_'+name).style.display!="block") iciRow = null;
	}
	function list_goods(name)
	{
		var category = '';
		open_box(name,true);
		var els = document.forms[0][name+'[]'];
		for (i=0;i<els.length;i++) if (els[i].value) category = els[i].value;
		var ifrm = document.getElementById('ifrm_' + name);
		var goodsnm = eval("document.forms[0].search_" + name + ".value");
		ifrm.src = "iframeList?name=" + name + "&schCate=" + category + "&schKey=all&schWord=" + goodsnm;
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
			tmp[tmp.length] = "<div style='float:left;border:1px solid #cccccc;margin:1px;' title='" + obj.rows[i].cells[1].getElementsByTagName('div')[0].innerHTML + "'>" + obj.rows[i].cells[0].innerHTML + "</div>";
		}
		document.getElementById(name+'X').innerHTML = tmp.join("") + "<div style='clear:both'>";
		parent.document.getElementById('ifrmCategory').style.height = document.body.scrollHeight;
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
	
		var cln1 = iciRow.cells[0].cloneNode(true);
		var cln2 = iciRow.cells[1].cloneNode(true);
		objTop.deleteRow(iciRow.rowIndex);
		oTr = objTop.insertRow(nextPos);
		oTd = oTr.appendChild(cln1);
		oTd = oTr.appendChild(cln2);
		oTr.className = "hand";
		oTr.onclick = function(){ spoit(nameObj,this); }
		oTr.ondblclick = function(){ remove_goods(nameObj,this); }
	
		iciRow = oTr;
		iciHighlight();
		react_goods(nameObj);
	}
	function keydnTree(e)
	{
		if (iciRow==null) return;
		e = e ? e : event;
		switch (e.keyCode){
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
<html>
<head>
<title>'xMall ||| Shoppingmall 관리자모드'</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="styleSheet" href="/resources/shop/admin/style.css">
<?=$scriptLoad?>
<script language="javascript">
if(window.addEventListener) 
{
	window.addEventListener('load',linecss,false); 
}
else 
{
	window.attachEvent('onload',linecss); 
}
</script>
<div id="dynamic"></div>
<!-- <iframe name="ifrmHidden" src="../../blank.jsp" style="display:none"></iframe> -->
<div id="jsmotion"></div>
<body class="scroll">

	<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
	<script type="text/javascript">
if( '${result}' == 1 ){
	parent.parent.location.reload();
	parent.closeLayer();
}
</script>

	<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

	<!-- <form name=form method=post action="category/indb" target="ifrmHidden"  enctype="multipart/form-data"> -->
	<form name=form method=post action="category/indb" enctype="multipart/form-data">
		<input type=hidden name=mode value="mod_category"> 
		<input type=hidden name=category value="${cateVO.category}"> 
		<input type=hidden name=infoyn value='${cateVO.infoyn}'>
		<%-- <input type="hidden" name="category_insert_yn" value="<%= ( null != cateList && !cateList.hasNoEntity() ) ? "Y" : "N" %>"/> --%>

		<div class="title_sub" style="margin: 0">분류만들기/수정/삭제<span>분류명을 생성하고 수정, 삭제합니다. 
		<font class=extext>(입력후 반드시 아래 수정버튼을 누르세요)</font></span>
			<%-- <%//<a href="javascript:alert('준비중입니다..')"><!-- javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=product&no=6',870,800) --><img src="../img/btn_q.gif" border=0 align=absmiddle hspace=2></a>%> --%>
		</div>

		<table class=tb>
			<col class=cellC>
			<col class=cellL>
			<tbody style="height: 26px">
				<tr>
					<td>현재분류</td>
					<td><c:choose>
							<c:when test="${cateVO.category != '' }">
								${shopLibFunction:getCurrPosition(cateVO.category, '0', 'kr')}
								<a href="../../goods/goods_list?category=${cateVO.category }" target=_blank> <img src="/resources/shop/admin/img/i_nowview.gif" border=0 align=absmiddle hspace=10></a>
							</c:when>
							<c:otherwise>
								전체분류
								<!-- <a href='../goods/goods_list.jsp'>전체분류</a> -->
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td>이 분류의 상품수</td>
					<td><b>${stringUtil:getMoneyFormatInteger(cateVO.goodsCnt)}</b>개가 등록되어 있습니다. <font class=extext>(하위분류까지 포함)</font></td>
				</tr>

				<c:if test="${cateVO.category != '' }">

					<tr>
						<td>현재분류명(영문)</td>
						<td><input type=text name=catnm class=lline required value="${cateVO.categoryObj.catnm}" label="현재분류명(영문)" maxlen="30"> &nbsp; 분류코드 : <b>${cateVO.category}</b></td>
					</tr>
					<tr>
						<td>현재분류명(국문)</td>
						<td><input type=text name=catnmKR class=lline value="${cateVO.categoryObj.catnmKR}" label="현재분류명(국문)" maxlen="30">
					</tr>
					<tr>
						<td>현재분류명(중문)</td>
						<td><input type=text name=catnmCN class=lline value="${cateVO.categoryObj.catnmCN}" label="현재분류명(중문)" maxlen="30">
					</tr>
					
					<tr>
						<td>연관 키워드</td>
						<td><input type=text name=catMemo class=lline value="${cateVO.categoryObj.catMemo}" label="연관 키워드">
					</tr>

					<%-- 			<tr>
				<td>분류이미지 등록</td>
				<td>
					<input type=file name="attachFile0"> <input type="checkbox" name="chkimg0" value="1" class="null"> 삭제
					
					<c:if test="${cateVO.oldattach0 != '' }">
						<input type="hidden" name="oldattach0" value="${cateVO.oldattach0 }"/>
						<div><img src="../../data/category/${cateVO.oldattach0 }"></div>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>마우스오버이미지<br>등록</td>
				<td>	
					<input type=file name="attachFile1"> <input type="checkbox" name="chkimg1" value="1" class="null"> 삭제
					<c:if test="${cateVO.oldattach1 != '' }">
						<input type="hidden" name="oldattach1" value="${cateVO.oldattach1 }"/>
						<div><img src="../../data/category/${cateVO.oldattach1 }"></div>
					</c:if>
				</td>
			</tr> --%>

					<c:if test="${shopLibFunction:getProperty('ecCode') != 'rental_mxfree' }">

						<tr>
							<td>사용여부</td>
							<td class=noline>
								<input type=radio name=hidden value=0 ${cateVO.categoryObj.hidden == 0 ? 'checked' : ''}> 사용
								<input type=radio name=hidden value=1 ${cateVO.categoryObj.hidden == 1 ? 'checked' : ''}> 미사용
							</td>
						</tr>

					</c:if>
					
					<c:if test="${7 > (fn:length(cateVO.category)) && (fn:length(cateVO.category)) > 3 && '001' eq (fn:substring(cateVO.category, 0, 3)) }">

						<tr>
							<td>볼드처리</td>
							<td class=noline><c:choose>
									<c:when test="${0 < shopLibFunction:getCateHideCnt(cateVO.category) }">
										<input type=hidden name=boldflag 	value='${cateVO.categoryObj.boldflag}'>
										<font class=small1 color=E83700>상위분류가 감춤이므로 자동감춤</font>
										<font color=0074BA>(이 분류를 보이게 하려면 먼저, 상위분류를 보이는 상태로 바꾸고나서 변경하세요)</font>
									</c:when>
									<c:otherwise>
										<input type=radio name=boldflag value=1 ${cateVO.categoryObj.boldflag == '1' ? 'checked' : ''}> Yes
										<input type=radio name=boldflag value=0 ${cateVO.categoryObj.boldflag != '1'  ? 'checked' : ''}> No
									</c:otherwise>
								</c:choose></td>
						</tr>

					</c:if>
				</c:if>

				<c:if test="${9 >= (fn:length(cateVO.category)) }">
					<tr>
						<td>하위 카테고리(영문)</td>
						<td><input type=text name=sub label="하위분류생성" maxlen="30" class="line" > <font class=extext>현재분류의 하위분류를 생성합니다</font></td>
					</tr>
					<tr>
						<td>하위 카테고리(국문)</td>
						<td><input type=text name=subKR label="하위분류생성" maxlen="30" class="line" ></td>
					</tr>
					<tr>
						<td>하위 카테고리(중문)</td>
						<td><input type=text name=subCN label="하위분류생성" maxlen="30" class="line" ></td>
					</tr>
					
					<tr>
						<td>하위 카테고리<br>연관 키워드</td>
						<td><input type=text name=subMemo class=lline label="연관 키워드">
					</tr>
				</c:if>

				<c:if test="${cateVO.category != '' }">

					<%-- <tr>
				<td>접근권한</td>
				<td>
					<select name="level">
					<option value="" selected>제한없음</option>
			<%
			if ( null != grpList ) {
				i = 0;
				while ( i < grpList.getRowCount() ) {
			%>
					<option value="<%= grpList.get(i, "k_level") %>" <%= (grpList.get(i, "k_level").equals(rtList.get(0, "k_level"))) ? "selected" : "" %>><%= grpList.get(i, "grpnm") %> - lv[<%= grpList.get(i, "level") %>]</option>
			<%
					i++;
				}
			}
			%>
					</select> 이상의 그룹에게만 접근을 허용합니다.
				</td>
			</tr> --%>
					<tr>
						<td>분류삭제</td>
						<td><a href="javascript:if (document.form.category.value) parent.popupLayer('popup.delCategory?category='+document.form.category.value);else alert('전체분류는 삭제대상이 아닙니다');">
							<img src="/resources/shop/admin/img/i_del.gif" border=0 align=absmiddle></a> <font class=extext>분류삭제시 하위분류도 함께 삭제됩니다. 신중히 삭제하세요.</font></td>
					</tr>

				</c:if>
		</table>

		<%--c:if test="${cateVO.category != '' && cateVO.category != null }"  관리자와 사용자와의 연동이 안되어서 아래는 주석처리 차후 사용자와 연동가능할경우 다시 구현할것 --%>
		<c:if test="false == true">
			<div class="title_sub">
				분류페이지 상단부분 꾸미기<span>분류페이지의 추천상품을 선정하고 HTML을 이용하여 꾸미기합니다</span>
				<%-- <%//<a href="javascript:alert('준비중입니다..')"><!-- javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=product&no=6',870,800)  --><img src="../img/btn_q.gif" border=0 align=absmiddle hspace=2></a>%> --%>
			</div>
			<table class=tb>
				<col class=cellC>
				<col class=cellL>
				<tr>
					<td>이 분류의<br>추천상품 선정
						<div style="padding-top: 3px"></div> <%-- <%//<a href="javascript:alert('준비중입니다..')"><!-- popup('http://guide.godo.co.kr/guide/php/ex_display.html',850,523) --><img src="../img/icon_sample.gif" border="0" align=absmiddle hspace=2></a>%> --%></td>
					<td style="padding: 5px">

						<div id=divRefer style="position: relative; z-index: 99">
							<div style="padding-bottom: 3px">

								<script>new categoryBox('refer[]',4,'','');</script>

								<input type=text name=search_refer onkeydown="return go_list_goods('refer')"> <a href="javascript:list_goods('refer')">
								<img src="/resources/shop/admin/img/i_search.gif" align=absmiddle></a> <a href="javascript:view_goods('refer')"><img src="/resources/shop/admin/img/i_openclose.gif" align=absmiddle></a>
							</div>
							<div id=obj_refer class=box1>
								<iframe id=ifrm_refer style="width: 100%; height: 100%"
									frameborder=0></iframe>
							</div>
							<div id=obj2_refer class="box2 scroll"
								onmousewheel="return iciScroll(this)">
								<div class=boxTitle>
									- 등록된 관련상품 <font class=small color=#F2F2F2>(삭제하려면 더블클릭)</font>
								</div>
								<table id=tb_refer class=tb>
									<col width=50>

									<c:forEach items="${cateVO.hitList }" var="list"
										varStatus="vnum">

										<tr onclick="spoit('refer',this)" ondblclick="remove_goods('refer',this)" class=hand>
											<td width=50 nowrap><a href="../../goods/goods_view?goodsno=${list.goodsno}" target=_blank>${shopLibFunction:goodsimg(list.imgs, '40', '',1)}</a></td>
											<td width=100%>
												<div>${list.goodsnm}</div> <b>${list.price}}</b> <input type=hidden name=erefer value="${goodsno}">
											</td>
										</tr>

									</c:forEach>
								</table>
							</div>
							<div id=referX style="font-size: 0"></div>
						</div> <script>react_goods('refer');</script>

					</td>
				</tr>
				<tr>
					<td>디스플레이유형</td>
					<td>
						<table>
							<col align=center span=3>
							<tr>
								<td><img src="/resources/shop/admin/img/goodalign_style_01.gif"></td>
								<td><img src="/resources/shop/admin/img/goodalign_style_02.gif"></td>
								<td><img src="/resources/shop/admin/img/goodalign_style_03.gif"></td>
							</tr>
							<tr class=noline>
								<td><input type=radio name=rtpl value="tpl_01" ${'tpl_01' eq cateVO.categoryInfoObj.rtpl ? 'checked' : ''}></td>
								<td><input type=radio name=rtpl value="tpl_02" ${'tpl_02' eq cateVO.categoryInfoObj.rtpl ? 'checked' : ''}></td>
								<td><input type=radio name=rtpl value="tpl_03" ${'tpl_03' eq cateVO.categoryInfoObj.rtpl ? 'checked' : ''}></td>
							</tr>
						</table>
					</td>
				</tr>
				<!--
			<tr>
			<td>이미지사이즈</td>
			<td><input type=text name=rsize value="<%//= categoryInfo[4]%>"> <font class=ver8>pixel</font></td>
			</tr>
			-->
				<tr>
					<td>추천상품 출력수</td>
					<td><input type=text name=rpagenum value="${cateVO.categoryInfoObj.rpagenum}" class="rline"> 개 <font class=extext>보여질 추천상품개수를 넣으세요</td>
				</tr>
				<tr>
					<td>라인당 상품수</td>
					<td><input type=text name=rcols value="${'0' eq cateVO.categoryInfoObj.rcols ? '' : cateVO.categoryInfoObj.rcols}" class="rline"> 개 <font class=extext>한줄에 보여질 상품개수를 넣으세요 (5개 이하 권장)</td>
				</tr>
				<tr>
					<td>상단꾸미기<br>
					<font class=extext>(HTML 버튼을 누르면 소스수정이 가능합니다)</font></td>
					<td height=300 style="padding: 5px"><textarea name=body style="width: 100%; height: 300px" type=editor>${cateVO.categoryInfoObj.body}</textarea>
						<!-- =stripslashes($lstcfg[body]) --> <script src="/resources/shop/lib/meditor/mini_editor.js"></script> <script>mini_editor("/resources/shop/lib/meditor/");</script>
					</td>
				</tr>
			</table>

			<div class="title_sub">
				분류페이지 리스트부분 꾸미기<span>상품분류페이지 하단의 리스트부분을 꾸밉니다</span>
				<%
					//<a href="javascript:alert('준비중입니다..')"><!-- javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=product&no=6',870,800) --><img src="../img/btn_q.gif" border=0 align=absmiddle hspace=2></a>
				%>
			</div>

			<table class=tb>
				<col class=cellC>
				<col class=cellL>
				<tr>
					<td>디스플레이유형</td>
					<td>
						<table>
							<col align=center span=3>
							<tr>
								<td><img src="/resources/shop/admin/img/goodalign_style_01.gif"></td>
								<td><img src="/resources/shop/admin/img/goodalign_style_02.gif"></td>
								<td><img src="/resources/shop/admin/img/goodalign_style_03.gif"></td>
							</tr>
							<tr class=noline>
								<td><input type=radio name=tpl value="tpl_01" ${cateVO.categoryInfoObj.tpl != 'tpl_01' ? '' : 'checked' }></td>
								<td><input type=radio name=tpl value="tpl_02" ${cateVO.categoryInfoObj.tpl != 'tpl_02' ? '' : 'checked' }></td>
								<td><input type=radio name=tpl value="tpl_03" ${cateVO.categoryInfoObj.tpl != 'tpl_03' ? '' : 'checked' }></td>
							</tr>
						</table>
					</td>
				</tr>
				<!-- <tr>
			<td>이미지사이즈</td>
			<td><input type=text name=size value="<%//= categoryInfo[8]%>"> <font class=ver8>pixel</font></td>
			</tr> -->
				<tr>
					<td>페이지당 상품출력수</td>
					<!-- <?=@implode(",",$lstcfg[page_num])?> -->
					<td><input type=text name=pagenum value="${cateVO.categoryInfoObj.pagenum}" option="regPNum" msgO="페이지당 상품출력수를 20,40,60,80 과 같이 입력해주십시요" onkeydown="onlynumber();" class="rline">
						개 <font class=extext>예) 20,40,60,80 (첫번째숫자는 기본출력수, 다음숫자부터는 출력수량조정) 
						<%
						//<a href="javascript:alert('준비중입니다..')"><!-- javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=product&no=6',870,800) --><img src="../img/icon_sample.gif" border=0 align=absmiddle hspace=2></a>
					%>
					</td>
				</tr>
				<tr>
					<td>라인당 상품수</td>
					<td><input type=text name=cols value="${cateVO.categoryInfoObj.cols == 0 ? '' : cateVO.categoryInfoObj.cols }" onkeydown="onlynumber();" class="rline"> 개 <font class=extext>한줄에 보여질 상품개수를 넣으세요 (5개 이하 권장)</td>
				</tr>
				<tr>
					<td height=28 colspan=2 class=extext style="padding-bottom: 2">상품들의 진열순서의 변경은 <a href="${ctx}/shop/admin/goods/sort" target=_blank>
					<font class=extext_l>[분류페이지 상품진열]</font></a> 에서 손쉽게 수정가능합니다.
				</tr>
				<tr>
					<td>하위분류 동일적용</td>
					<td><c:if test="${cateVO.category != '' }">
							<input type="checkbox" name="chkdesign" value="1" class="null">하위분류에도 위에서 설정한 내용들을 동일하게 적용합니다.
				</c:if>
						<div style="padding-top: 3px" class=extext>위의 '분류페이지 상단부분 꾸미기와 '분류페이지 리스트부분 꾸미기'에서 설정한 내용을 하위분류에도 동일하게 적용시키는 기능입니다</div></td>
				</tr>
			</table>

		</c:if>
		<!-- <div class="button"><input type=image src="../img/btn_modify.gif"></div> -->
		<div class="button">
			<img src="/resources/shop/admin/img/btn_modify.gif" onclick="goSubmit();"
				style="cursor: hand;">
		</div>

	</form>

	<div id=MSG01>
		<table class="small_ex">
			<tr>
				<td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>상품분류탐색기에서 1차분류만들기 (최상위분류)를 누르면 1차분류를 생성할 수 있습니다.<br> 
				<!-- img src="../img/icon_list.gif" align=absmiddle>분류페이지상단에서 이벤트나 배너를 배치하여 차별화될 수 있게 디자인해보세요.<br> 
				<img src="../img/icon_list.gif" align=absmiddle>분류순서변경은 해당분류를 선택후 키보드의 상하이동키↓↑로 조정하고 수정을 눌러 저장합니다.</td-->
			</tr>
		</table>
	</div>

	<script>cssRound('MSG01')</script>

	<script>
	table_design_load();
	window.onload = function(){
		parent.document.getElementById('ifrmCategory').style.height = document.body.scrollHeight;
	}
	
	if ( "sub" == '${cateVO.focus}' ) {
	document.form.sub.focus();
	}
</script>

	<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

	<%-- ================================================================================
=====================================================================================
* 화면 종료
=====================================================================================
================================================================================ --%>