<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ page import="static com.wepinit.wepinit_shop.xmall.common.ShopLibFunction.*"%>
<%@ page import="com.wepinit.wepinit_shop.xcube.util.StringUtil"%>

<script language=javascript  src="/resources/shop/admin/common.js"></script>

	<script language="javascript">
	
	var goodsNotiList = null;
	
	/* 2017-08-24 추가 적립금 수정 */
	$(document).ready(function(){
		if($('input:radio[name=use_emoney]')[1].checked) {
			openLayer('reserve', 'block');
			$('#reserve').css('border', '1px solid rgb(204, 204, 204)')
		}
		$('input:radio[name=use_emoney]').click(function(){ 
			if($('input:radio[name=use_emoney]')[1].checked){
				openLayer('reserve', 'block');
			} else {
				openLayer('reserve', 'none');
			}
		});
		
		getGoodsNotiList();
		
	});
	
	function checkGoodsForm(){
		//validation 체크 추가
 		for(var i=0; i<$('input[type="text"]').length; i++){
 			if($('input[type="text"]')[i].dataset.reg != null && $('input[type="text"]')[i].dataset.reg != "" && $('input[type="text"]')[i].value != "") {
 				if(! (chkPatten($('input[type="text"]')[i], $('input[type="text"]')[i].dataset.reg)) ){
 					return false;
 				}
 			}
		}
		
		//판매사 체크 추가
		if($('#schMyritzNm').val().length < 1){
			alert("판매사를 선택해주세요");
			$('#schMyritzNm').focus();
			return false;
		}
		
		//2017-08-31 추가 - 파일 삭제 체크 확인
		$('input[type="file"]').each(function(){
			//선택된 파일을 찾음
			if($(this).val()!= ""){
				var imgId = $(this).attr('id');
				imgId = "del_"+ imgId;
				if($('#'+imgId).length > 0) {
					if(!($('#'+imgId).prop('checked'))){
						alert("파일을 수정하시려면 삭제버튼을 체크해주세요");
						event.preventDefault();
						return false;
					}	
				}
			}
		});
	}

	function popupImg(src,base)
	{
		if (!src) src = encodeURIComponent(this.src);
		if (!base) base = "";
		window.open(src,'','width=1,height=1');
	}
	
	function exec_add()
	{
		var ret;
		var str = new Array();
		var obj = document.forms[0]['cate[]'];
		for (i=0;i<obj.length;i++){
			if (obj[i].value){
				str[str.length] = obj[i][obj[i].selectedIndex].text;
				ret = obj[i].value;str
			}
		}
		if (!ret){
			alert('카테고리를 선택해주세요');
			return;
		}
		
		var isAddedCategory = false;		//추가된 카테고리인지 여부
		$('.category').each(function() {
			if ($(this).val() == ret ) {
				alert('이미 추가된 카테고리입니다');
				isAddedCategory = true;
			}
		});
		
		if(!isAddedCategory) {
			var obj = document.getElementById('objCategory');
			oTr = obj.insertRow(obj.rows.length);
			oTd = oTr.insertCell(0);
			oTd.id = "currPosition";
			oTd.innerHTML = str.join(" > ");
			oTd = oTr.insertCell(1);
			oTd.innerHTML = "\
			<input type=text name=category[] class=category value='" + ret + "' style='display:none'>\
			<input type=text name=sort[] value='<%--=time()--%>' class='sortBox right' maxlength=10 ${goodsVO.strHieen}>\
			";
			oTd = oTr.insertCell(2);
			oTd.innerHTML = "<!--<img src='/resources/shop/admin/img/i_select.gif' onClick=\"cate_mod(document.forms[0]['cate[]'][0],this.parentNode.parentNode)\" class=hand>--> <a href='javascript:void(0)' onClick='cate_del(this.parentNode.parentNode)'><img src='/resources/shop/admin/img/i_del.gif' align=absmiddle></a>";	
		}
	}
	
	/*** 상품 이미지 ***/
	function preview(obj)
	{
		var tmp = obj.parentNode.parentNode.parentNode.childNodes[2];
		tmp.innerHTML = "<tr><td><img src='" + obj.value + "' width=20 onload='if(this.height>this.width){this.height=20}' style='border:1 solid #cccccc' onclick=popupImg(this.src,'../') class=hand></td></tr>";
	}
	function addfld(obj)
	{
		var tb = document.getElementById(obj);
		var src = tb.rows[0].cells[0].getElementsByTagName('span')[0].innerHTML;
		
		oTr = tb.insertRow(tb.rows.length);
		oTd = oTr.insertCell(0);
		src = src.replace("[]0", "[]" + (tb.rows.length-1));
		oTd.innerHTML = "<a href='javascript:void(0)' onClick='delfld(this)'><img src='/resources/shop/admin/img/i_del.gif' align=absmiddle></a>	<span>" + src + "</span>";
		oTd = oTr.insertCell(1);
		oTd = oTr.insertCell(2);
	}
	function delfld(obj)
	{
		var tb		= obj.parentNode.parentNode.parentNode.parentNode;
		var delNum 	= obj.parentNode.parentNode.rowIndex;
		var maxNode = tb.rows.length;
		var span;

		for (var i=1 ; i < maxNode ; i++ ) {
			if ( i > delNum ) {
				span = tb.rows[i].cells[0].getElementsByTagName('span')[0];
				if ( "file" == span.childNodes[0].type) {
					span.innerHTML = span.innerHTML.replace(span.childNodes[0].name, tb.id + "[]" + (i-1));
				}
			}
		}
		tb.deleteRow(delNum);
	}
	
	function cate_del(el)
	{
		idx = el.rowIndex;
		var obj = document.getElementById('objCategory');
		obj.deleteRow(idx);
	}
	
	/*** 추가옵션 ***/
	function add_addopt()
	{
		var tbAdd = document.getElementById('tbAdd');
		oTr = tbAdd.insertRow(tbAdd.rows.length);
		oTd = oTr.insertCell(0);
		oTd.innerHTML = "<input type=text name=addoptnms> <a href='javascript:void(0)' onClick='add_subadd(this)'><img src='/resources/shop/admin/img/i_proadd.gif' align=absmiddle></a>";
		oTd = oTr.insertCell(1);
		oTd.colSpan = 2;
		oTd.innerHTML = "\
		<table>\
		<tr>\
			<td><input type=text name=addopt[opt][" + (oTr.rowIndex-1) + "][] style='width:270px'> 선택시</td>\
			<td>판매금액에 ₩<input type=text name=addopt[addprice][" + (oTr.rowIndex-1) + "][] size=9> 추가</td>\
		</tr>\
		</table>\
		";
		oTd = oTr.insertCell(2);
		oTd.className = "noline";
		oTd.align="center";
		oTd.innerHTML = "<input type=checkbox name=addoptreq[" + (oTr.rowIndex-1) + "] value=o>";
		
	}
	function del_addopt()
	{
		var tbOption = document.getElementById('tbAdd');
		if (tbOption.rows.length>1) tbOption.deleteRow(tbAdd.rows.length-1);
	}
	function add_subadd(obj)
	{
		
		var idx = obj.parentNode.parentNode.rowIndex - 1;
		//obj = obj.parentNode.parentNode.childNodes(1).getElementsByTagName('table')[0];
		var table = $(obj).parent().siblings()[0].children[0];
		var tr = $("<tr></tr>");
		var td = $("<td></td>");
		td.html("<input type=text name=addopt[opt][" + idx + "][] style='width:270px' maxlength=20> 선택시");
		tr.append(td);
		td = $("<td></td>");
		td.html("판매금액에 ₩<input type=text name=addopt[addprice][" + idx + "][] size=9 maxlength=10> 추가");
		tr.append(td);
		$(table).append(tr); 
	}
	
	function chk_delivery_type(){
		var obj = document.getElementsByName('deliveryType');
		if(obj[2].checked == true) document.getElementById('gdi').style.display="inline";
		else document.getElementById('gdi').style.display="none";

		if(obj[3].checked == true) document.getElementById('gdi2').style.display="inline";
		else document.getElementById('gdi2').style.display="none";
	}
	/*** 상품 가격/재고 ***/
	function addopt1()
	{
		var name;
		var fm = document.forms[0];
		var tbOption = document.getElementById('tbOption');
		var Rcnt = tbOption.rows.length;
		var addOptVal = document.getElementsByName("optnms")[0].value;
		
		var optionStockArrayIndex = Rcnt - 1;
		oTr = tbOption.insertRow(tbOption.rows.length);
		oTr.id = "trid_" + optionStockArrayIndex;

		for (i=0;i<tbOption.rows[0].cells.length;i++){
			oTd = oTr.insertCell(i);
			switch (i){
				case 0: oTd.innerHTML = "<input type=text class='opt gray' name=opt1[] value='"+addOptVal+"' required label='1차옵션명' ondblclick=\"delopt1part('"+oTr.id+"')\" >";
				break;
				case 1:	oTd.innerHTML = "<input type=text name=option[price][] class='opt gray' value='" + fm.price.value + "'>"; break;
				case 2:	oTd.innerHTML = "<input type=text name=option[consumer][] class='opt gray' value='" + fm.consumer.value + "'>"; break;
				case 3:	oTd.innerHTML = "<input type=text name=option[supply][] class='opt gray' value='" + fm.supply.value + "'>"; break;
				default: oTd.innerHTML = "<input type=text name=option[stock][" + optionStockArrayIndex + "] class='opt gray' value='재고' onclick='chkOptName(this)' onblur=\"chkOptNameOver(this)\"><input type=hidden name=option[optno][]>";
				//alert(oTd.innerHTML);
				break;
			}
		}
	}
	function addopt2()
	{
		var name;
		var tbOption = document.getElementById('tbOption');
		var addOptVal2 = document.getElementsByName("optnms")[1].value;
// 		if (tbOption.rows.length<3){
		if (tbOption.rows.length < 2){
			alert('1차옵션을 먼저 추가해주세요');
			return;
		}

		var Ccnt = tbOption.rows[0].cells.length;
		
		for (i=0;i<tbOption.rows.length;i++){
			oTd = tbOption.rows[i].insertCell(tbOption.rows[i].cells.length);
			if(!i)oTd.id = "tdid_"+Ccnt;
			oTd.innerHTML = (i) ? "<input type=text name=option[stock][" + (i-1) + "] class='opt gray'  value='"+addOptVal2+"'\"><input type=hidden name=option[optno][]>" 
					            : "<input type=text class='opt gray' name=opt2[] value='"+addOptVal2+"' required label='2차옵션명' ondblclick=\"delopt2part('"+oTd.id+"')\" >";
		}
	}
	function delopt1()
	{
		var tbOption = document.getElementById('tbOption');
		if (tbOption.rows.length>2) tbOption.deleteRow(tbOption.rows.length-1);
	}
	function delopt2()
	{
		var tbOption = document.getElementById('tbOption');
		if (tbOption.rows[0].cells.length<7) return;
		for (i=0;i<tbOption.rows.length;i++){
			tbOption.rows[i].deleteCell(tbOption.rows[i].cells.length-1);
		}
	}
	
	function react_goods(name)
	{
		var tmp = new Array();
		var obj = document.getElementById('tb_'+name);
		for (var i=0;i<obj.rows.length;i++){
			tmp[tmp.length] = "<div style='float:left;width:50;border:1 solid #cccccc;margin:1px;' title='" + obj.rows[i].cells[1].getElementsByTagName('div')[0].innerText + "'>" + obj.rows[i].cells[0].innerHTML + "</div>";
		} 
		document.getElementById(name+'X').innerHTML = tmp.join("") + "<div style='clear:both'>";
	}
	
	/*** 폼체크 ***/
	function chkForm2(obj)
	{
		/* if (typeof(obj['category[]'])=="undefined"){
			if (document.getElementsByName("cate[]")[0].value) exec_add();
			else {
				alert("카테고리를 등록해주세요");
				document.getElementsByName("cate[]")[0].focus();
				return false;
			}
		} */
		if($("#objCategory").find("tbody").length < 1){
			alert("카테고리를 등록해주세요");
			document.getElementsByName("cate[]")[0].focus();
			return false;
		}
		if (!chkOption()) return false;
		if (!chkForm(obj)) return false;
	}
	
	/*** 상품 카테고리 선택 ***/
	var idxCategory;
	var preCurrposSel;

	function cate_mod(obj,el)
	{
		el.style.background = "#EFF5F9";
		idx = el.rowIndex;
		var objX = document.getElementsByName('category[]');
		var val = objX[idx].value;
		idxCategory = idx;
		if (preCurrposSel && preCurrposSel!=el) preCurrposSel.style.background = "#FFFFFF";
		preCurrposSel = el;
		categoryBox_request(obj,val);
	}
	
	/* 옵션 부분 삭제 */
	function delopt1part(rid)
	{
		var obj = document.getElementById(rid);
		var tbOption = document.getElementById('tbOption');
		if (tbOption.rows.length>2) tbOption.deleteRow(obj.rowIndex);
	}
	function delopt2part(cid)
	{
		var obj = document.getElementById(cid);
		var tbOption = document.getElementById('tbOption');

		if (tbOption.rows[0].cells.length<7) return;
		for (i=0;i<tbOption.rows.length;i++){
			tbOption.rows[i].deleteCell(obj.cellIndex);
		}
	}
	
	/*** 자동으로 가격필드에 입력값 저장 ***/
	function autoPrice(obj)
	{
		var e = event.keyCode;
		window.status = e;
		if (e>=48 && e<=57) return;
		if (e>=96 && e<=105) return;
		if (e>=37 && e<=40) return;
		if (e==8 || e==9 || e==13 || e==46) return;
		event.returnValue = false;
		
		var name = obj.name;
		var el = document.getElementsByName('option[' + name + '][]');
		if( name === 'stock'){
			el = document.getElementsByName('option[' + name + '][0]');
		}
		
		if(el.length>0){
			for(var i=0;i<el.length;i++){
				el[i].value = obj.value;
			}
		}
		//el[0].value = obj.value;
	}
	
	/*** 관련상품 ***/
	var iciRow, preRow, nameObj;
	function open_box(name,isopen)
	{
		var mode;
		var isopen = (isopen || document.getElementById('obj_'+name).style.display!="block") ? true : false;
		mode = (isopen) ? "block" : "none";
		document.getElementById('obj_'+name).style.display = document.getElementById('obj2_'+name).style.display = mode;
		if(mode == "none") {
			 iciRow = null;
			 preRow = null;
			 nameObj = null;	
		}
	}
	function list_goods(name)
	{
		var category = '';
		open_box(name,true);
		var els = document.forms[0][name+'[]'];
		for (var i=0;i<els.length;i++) if (els[i].value) category = els[i].value;
		var ifrm = $("#ifrm_" + name);
		var goodsnm = eval("document.forms[0].search_" + name + ".value");
		ifrm.attr("src", "iframeList?name=" + name + "&schCate=" + category + "&schKey=all&schWord=" + goodsnm);
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
	function vOption()
	{
		document.fm.stock.disabled = !document.fm.stock.disabled;
		openLayer('objOption');
	}

	document.onkeydown = keydnTree;
	
	
	
	// 상품고시 항목 추가
	function addGoodsNoti(obj){
		var tb = null;
		var oTd = null;
		var oTr = null;
		
		tb = document.getElementById("tblGoodsNoti");
		
		oTr = tb.insertRow(tb.rows.length);
		
		// no
 		oTd = oTr.insertCell(0);
 		oTd.innerHTML = tb.rows.length-1;
 		oTd.align= "center";
 		
 		// checkbox
 		oTd = oTr.insertCell(1);
 		oTd.innerHTML = "<input type='checkbox' />";
 		oTd.align= "center";
 		
 		// 항목
 		oTd = oTr.insertCell(2);
 		oTd.innerHTML = "<input type='text' style='width: 100%' label='항목 "+(tb.rows.length-1)+"' name='goodsNotiItem[]' value='"+( null != obj ? obj.item : "")+"' />";
 		
 		// 내용
 		oTd = oTr.insertCell(3);
 		oTd.innerHTML = "<textarea style='width:100%;height:40px;' class='__tline' label='내용"+(tb.rows.length-1)+"' name='goodsNotiMemo[]' >"+(null != obj ? obj.memo : "")+"</textarea>";
	}
	
	// 상품고시 선택 항목 삭제
	function removeGoodsNoti(){
		var i = 0;
		var tblGoodsNoti = null;
		var tblGoodsNotiChks = null;
		
		tblGoodsNoti = document.getElementById('tblGoodsNoti');
		tblGoodsNotiChks = $("#tblGoodsNoti input:checkbox:not(#allGoodsNotiChk):checked");
		
 		if(0 >= tblGoodsNotiChks.length){
 			alert("삭제 할 항목을 선택하여 주세요.");
 			return false;
 		}
 		
 		if(confirm("삭제하시겠습니까?")){
	 		// TR 삭제
	 		tblGoodsNotiChks.each(function(){
	 			$(this).closest("tr").remove();
	 		});
	 		
	 		// 상품 고시 테이블 <= 1 인경우 항목 추가
	 		// 상품 고시 테이블 > 1 인경우 number 수정
	 		if( 1 >= tblGoodsNoti.rows.length ){
	 			addGoodsNoti();
	 		} else {
		 		for( i = 1; i < tblGoodsNoti.rows.length; i++ ){
		 			// no
		 			tblGoodsNoti.rows[i].cells[0].innerHTML = i;
		 		}
	 		}
 		}
	}
	
	// 고시정보 등록
	function registerGoodsNoti(){
		var i = 0;
		var j = 0;
		var tblGoodsNoti = null;
		var validInput = null;
		var paramObj = {};
		
		tblGoodsNoti = $("#tblGoodsNoti tr");
			
		// 고시정보 명 check
		if( !chkText($("#goodsNotiGrpNm")[0], $("#goodsNotiGrpNm").val()) ){
			return false;
		}
		
		paramObj["goodsNotiList[0].goodsGroup"] = $("#goodsNotiGrpNm").val();
		
		// 항목 리스트 check
		for(i = 1; i < tblGoodsNoti.length ; i++){
			if( !chkText($(tblGoodsNoti[i]).find("input:text")[0], $(tblGoodsNoti[i]).find("input:text").val()) ){
				return false;
			}
			
			if( !chkText($(tblGoodsNoti[i]).find("textarea")[0], $(tblGoodsNoti[i]).find("textarea").val()) ){
				return false;
			}
			
			paramObj["goodsNotiList["+i+"].item"] = $(tblGoodsNoti[i]).find("input:text").val();
			paramObj["goodsNotiList["+i+"].memo"] = $(tblGoodsNoti[i]).find("textarea").val();
		}
		
		
		if(confirm("아래의 상품고시 목록을 저장하시겠습니까?")){
			ajaxCallJson("/shop/admin/goods/notiRegister.dojson"
					, paramObj
					, function(result){
						alert("상품고시정보가 등록이 완료되었습니다.");
						getGoodsNotiList();
					});
		}
		
	}
	
	// 상품고시 리스트 조회
	function getGoodsNotiList(){
		ajaxCallJson("/shop/admin/goods/notiList.dojson"
				, {}
				, function(result){
					var i = 0;
					
					goodsNotiList = result.goodsVO.goodsNotiList;
					
					$("#selGoodsNotiList").html("<option value=''>:::목록보기 :::</option>");
					
					for( i = 0 ; i < goodsNotiList.length; i++){
						if( 0 == goodsNotiList[i].itemNo ){
							$("#selGoodsNotiList").append("<option value='"+goodsNotiList[i].goodsGroupNo+"'>"+goodsNotiList[i].goodsGroup+"</option>");
						}
					}
				});
	}
	
	// 상품고시 선택
	function setGoodsNoti(){
		var i = 0;
 		
		if( $("#selGoodsNotiList").val() == ""){
			return false;
		}
		
		$("#tblGoodsNoti tr:gt(0)").remove();
 		
 		for(i = 0 ; i < goodsNotiList.length; i++){
 			if( goodsNotiList[i].goodsGroupNo == $("#selGoodsNotiList").val() && 0 < goodsNotiList[i].itemNo){
	 			addGoodsNoti({item : goodsNotiList[i].item, memo : goodsNotiList[i].memo});
 			}
 		}
	}
	
	
	</script>


<div>

</div>


<form name="fm" method="post" enctype="multipart/form-data" onsubmit="return chkForm2(this)" id="fm" action="goodsIndb" >
<input type="hidden" name="mode" value="${goodsVO.mode }">
<input type="hidden" name="goodsno" value="${goodsVO.goodsno}">
<input type="hidden" name="m_id" value="${sessionScope.xmall_admin.ADMINUSER.xkey.m_id}">
<input type="hidden" name="idx" value="0">
<input type="hidden" name="category" value="">

<c:if test="${'' ne goodsVO.goodsno }">
	<div style="padding:8px 13px;background:#f7f7f7;border:3px solid #C6C6C6;margin-bottom:18px;" id="goodsInfoBox">
		<div><font class=def>고유번호:</font> <span style="color:#FF7200;font:bold 14px verdana">${goodsVO.goodsno}</span></div>
	</div>
</c:if>
<!-- 상품 카테고리 선택 -->
	<div class="title title_top">
		상품분류정보<span>한상품에 여러개의 분류를 등록할 수 있습니다&nbsp;(다중분류기능지원)</span> 
	</div>
	
 	<div class="box" style="padding-left:3">
		<table width="790" cellpadding="0" cellspacing="1" border="1" bordercolor="#cccccc" style="border-collapse:collapse">
			<tr>
				<td style="padding:7 7 7 10" bgcolor=f8f8f8>
					<table width="100%" cellpadding="0" cellspacing="1" id="objCategory">
						<col>
						<col width="50" style="padding-right:10">
						<col width="52" align="right">
					
					<!-- 수정일 경우 카테고리 목록 출력 -->	
					<c:if test="${!empty goodsVO.categoryList && fn:length(goodsVO.categoryList)>0 }">
						<c:forEach items="${goodsVO.categoryList }" var="list">
							<tr>
								<td id="currPosition">${list.categoryNm }</td>
								<td>
									<input type="text" name="category[]" value="${list.category }" style="display:none">
									<input type="text" name="sort[]" value="${list.sort}" class="sortBox right" maxlength="10" ${goodsVO.strHieen } readonly>
								</td>
								<td>
									<a href="javascript:void(0)" onClick="cate_del(this.parentNode.parentNode)"><img src="/resources/shop/admin/img/i_del.gif" border="0" align="absmiddle"></a>
									
								</td>
							</tr>
						</c:forEach>
					</c:if>

					</table>
				 </td>
			</tr>
		</table>
	</div>
	
	<!-- 상품카테고리 리스트박스 -->
	<div style="padding-top:10" id="goodsCategory">
		<table>
			<tr>
				<td>
					<script>new categoryBox('cate[]', 4, '', 'multiple');</script>
				</td>
				<td valign="top">
					<table width="100%" cellpadding="0" cellspacing="0" id="objCategory">
						<tr>
							<td height="55" valign="top">
								<a href="javascript:exec_add()"><img src="/resources/shop/admin/img/i_regist_l.gif" vspace="4"></a><br>	
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	
	
	<!-- 상품기본정보 -->
	<div class="title">판매사 정보<span></div>
	
	<div class="noline" style="padding-bottom:5px">
		<input type="radio" name="useMyritz" value="1" checked > 판매사 등록
	</div>
	
	<table id="tbMyritz" class="tb">
		<col class="cellC">
		<col class="cellL">
		<tr>
			<td>판매사</td>
			<td>
				<input type="text" name="schMyritzNm" id="schMyritzNm" value="${'' ne goodsVO.goodsObj.myritzNm? goodsVO.goodsObj.myritzNm : goodsVO.schMyritzNm }" class="line" style="height:22px" disabled="disabled"/>
				<input type="button" value="판매사 조회" onclick="popupLayer('${ctx}/shop/admin/common/myritzSearchPopup', 600, 500);" />
				<input type="hidden" name="schMyritzCd" id="schMyritzCd" value="${'' ne goodsVO.goodsObj.myritzCd? goodsVO.goodsObj.myritzCd : goodsVO.schMyritzCd }" />
			</td>
		</tr>
		<!-- <tr>
			<td>승인여부</td>
			<td><input type="checkbox" /></td>
		</tr> -->
	</table>
	
	<div style="padding-top:10px"></div>
	
	<!-- 상품기본정보 -->
	<div class="title">상품기본정보<span>원산지, 브랜드가 없는 경우 입력안해도 됩니다 </div>
	
	<table class="tb">
		<col class="cellC">
		<col class="cellL">
		<col class="cellC">
		<col class="cellL">
		<tr>
			<td width="120" nowrap>상품명</td>
			<td width="50%"><div style="height:25;padding-top:5"><input type="text" name="goodsnm" style="width:100%" value="${goodsVO.goodsObj.goodsnm}" required label="상품명" class="line" id="goodsnm" maxlength=50 ></div>
			<div style="height:23"><input type="checkbox" name="meta_title" value="1" class="null" ${'1' eq goodsVO.goodsObj.metatitle? 'checked' : '' }>상품명을 상품상세페이지의 타이틀 태그에 입력됩니다.</div></td>
			<td width="120" nowrap>상품코드</td>
			<td width="50%"><input type="text" name="goodscd" style="width:100%" value="${goodsVO.goodsObj.goodscd}" class="line" maxlength=20 data-reg="/^[A-Za-z0-9+]*$/" ></td>
		</tr>
		<tr>
			<td>원산지</td>
			<td>
				<input type="text" name="origin" value="${goodsVO.goodsObj.origin }" class="line" maxlength=20>
				<select onchange="this.form.origin.value=this.value;this.form.origin.focus()">
					<option value="">::: 목록보기 :::</option>
						<c:if test="${!empty goodsVO.originList && fn:length(goodsVO.originList)>0 }">
							<c:forEach items="${goodsVO.originList }" var="list">
								<option value="${list.origin }" >
									${list.origin }
								</option>
							</c:forEach>
						</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td>브랜드</td>
			<td>
				<select name="brandno">
					<option value="0">::: 브랜드 선택 :::</option>
						<c:if test="${!empty goodsVO.goodsBrandList &&  fn:length(goodsVO.goodsBrandList)>0 }">
							<c:forEach items="${goodsVO.goodsBrandList }" var="list">
								<option value="${list.sno}"  ${goodsVO.goodsObj.brandno eq list.sno ? 'selected' : '' }>
									${list.brandnm }
								</option>
							</c:forEach>
						</c:if>					
				</select>
				
				
				<font class="small1" color="444444"><a href="brand" target="_blank"><font class="extext_l"><img src="/resources/shop/admin/img/btn_brand_add.gif"></a>
			</td>
		</tr>

		<tr>
			<td>유사검색어</td>
			<td >
				<div style='padding-top:5px'><input type="text" name="keyword" value="${goodsVO.goodsObj.keyword }" style="width:100%" class="line" maxlength=100></div>
				<div style="height:23;padding-top:5px" class="extext">상품상세 페이지의 메타태그와 상품 검색시 키워드로 사용하실 수 있습니다.</font></div>
			</td>
		</tr>
		<c:if test="${goodsVO.mode eq 'modify' }">
			<c:choose>
				<c:when test="${sessionScope.xmall_admin.ADMINUSER.xkey.level eq '99' }">
					<tr>
						<td>게시여부</td>
						<td colspan="3" >
							<input type="checkbox" name="open" value="1"  ${goodsVO.goodsObj.open eq '1' ? 'checked':''}>보이기
							<font class="extext">(체크해제시 화면에서 안보임)</font>
							
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td>게시여부</td>
						<td colspan="3" >
							<c:if test="${goodsVO.goodsObj.open eq '1' }">
								<img src="/resources/shop/admin/img/icn_${goodsVO.goodsObj.open}.gif">
							</c:if>
							<c:if test="${goodsVO.goodsObj.open eq '0' }">
								<img src="/resources/shop/admin/img/icn_${goodsVO.goodsObj.open}.gif">
							</c:if>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</c:if>
	</table>
	<div style="padding-top:20px"></div>
	<div style="border-top:3px #efefef solid;"></div>
	
<!-- 상품추가정보 -->
<c:if test='${codeUtil:getConfValue("rental_free") eq "false" }'>
	
	<div class="title">상품추가정보<span>상품특성에 맞게 항목을 추가할 수 있습니다 (예. 감독, 저자, 출판사, 유통사, 상품영문명 등) </span>
	</div>
	
	<div class="noline" style="padding-bottom:5px">
		<input type="radio" name="useEx" value="1" ${'|||||' ne goodsVO.goodsObj.extitle ? 'checked' : ''} onclick="openLayer('tbEx','block')" onfocus="blur()"> 사용
		<input type="radio" name="useEx" value="0" ${'|||||' eq goodsVO.goodsObj.extitle  ? 'checked' : ''} onclick="openLayer('tbEx','none')" onfocus="blur()"> 사용안함
	</div>
		
	<table id="tbEx" class="tb" style="display:${'|||||' ne goodsVO.goodsObj.extitle ? 'block' : 'none'}">
		<col class="cellC"><col class="cellL"><col class="cellC"><col class="cellL">
		<tr>
		<c:set var="exTitleLen" value="${fn:length(goodsVO.goodsObj.extitle) }" />
		<c:set var= "ex_title" value="${fn:split(stringUtil:nullConv(goodsVO.goodsObj.extitle),'|')}"/>
		<c:forEach var="i" begin="0" end="5" step="1">		
			<c:set var="tmpExTitle" value="${exTitleLen >= i ? ex_title[i] : ''}" />
			<td>
			<input type="text" name="title${i+1 }" class="exTitle gray" value="${'' ne tmpExTitle ? tmpExTitle : '' }" maxlength=50></td>
			<td width="50%"><input type="text" name="ex${i+1}" value=
			<c:choose>
				<c:when test="${i eq 0 }">
					"${goodsVO.goodsObj.ex1}"
				</c:when>
				<c:when test="${i eq 1 }">
					"${goodsVO.goodsObj.ex2}"
				</c:when>
				<c:when test="${i eq 2 }">
					"${goodsVO.goodsObj.ex3}"
				</c:when>
				<c:when test="${i eq 3 }">
					"${goodsVO.goodsObj.ex4}"
				</c:when>
				<c:when test="${i eq 4 }">
					"${goodsVO.goodsObj.ex5}"
				</c:when>
				<c:otherwise>
					"${goodsVO.goodsObj.ex6}"
				</c:otherwise>
			</c:choose>
			style="width:100%" maxlength=100></td>
			${1 == i%2 ? '</tr><tr>':'' }
		</c:forEach>
			
		</tr>
	</table>
		
	<div style="border-bottom:3px #efefef solid;padding-top:20px"></div>
	
</c:if>

<!-- 상품적립금 -->
	<div class="title">적립금<span>이 상품 주문시 적립금의 적립여부를 설정합니다.</span></div>
	<div class="noline" style="padding-bottom:5px">
 		<div>
			<input type="radio" name="use_emoney" ${'0' eq goodsVO.goodsObj.useemoney ? 'checked' : '' } value="0" onfocus=blur()> 적립금설정의 정책을 적용합니다. <font class="extext">(이 상품의 적립금을 
			<a href="${ctx}/shop/admin/basic/emoney" target="_blank"><font class="extext_l">[기본관리 > 적립금설정 > 상품 적립금 지급에 대한 정책]</font></a> 에서 설정한 정책을 따릅니다)</font>
		</div>
		<div style="float:left;padding-top:5px">
			<input type="radio" name="use_emoney" ${'0' ne goodsVO.goodsObj.useemoney ? 'checked' : '' } value="1" onfocus=blur()> 적립금을 따로 입력합니다. 
		</div>
		<div style="float:left;margin-left:5;padding-top:5px">
			<font class="extext">(이 상품의 적립금은 상품별 등록 적립금으로 제공합니다)</font>
		</div>
	</div>
	<div style="border-bottom:3px #efefef solid;padding-top:40px"></div>
	
<!-- 상품 가격/재고 -->
	<div class="title">가격/재고/배송비<span>사이즈, 색상 등에 의해 가격이 여러개인 경우 가격옵션을 추가할 수 있습니다 
	</div>

	<table>
		<tr>
			<td>판매가</td>
			<td>
				<input type="text" name="price" size="10" value="${goodsVO.price1 }" onchange="autoPrice(this)" onblur="autoPrice(this)" onkeydown="autoPrice(this)" class="line"  maxlength=10 data-reg="/^[0-9]+$/" onkeyup="removeChar(event)" >
			</td>
			<td style="padding-left:10px">정가</td>
			<td>
				<input type="text" name="consumer" size="10" value="${goodsVO.consumer }" onchange="autoPrice(this)" onblur="autoPrice(this)" onkeydown="autoPrice(this)" class="line"  maxlength=10 data-reg="/^[0-9]+$/" onkeyup="removeChar(event)" >
			</td>
			<td style="padding-left:10px">매입가</td>
			<td>
				<input type="text" name="supply" size="10" value="${goodsVO.supply }" onchange="autoPrice(this)" onblur="autoPrice(this)" onkeydown="autoPrice(this)" class="line"  maxlength=10 data-reg="/^[0-9]+$/" onkeyup="removeChar(event)" >
			</td>
			<td style="padding-left:10px">재고량</td>
			<td>
				<input type="text" name="stock" size="10" value="${goodsVO.intTotalStock}" onchange="autoPrice(this)" onblur="autoPrice(this)" onkeydown="autoPrice(this)" class="line" onkeyup="removeChar(event)" >
			</td>
		</tr>
	</table>

	<div style="height:5px;font:0"></div>
	<table class="tb">
		<col class="cellC"><col class="cellL"><col class="cellC"><col class="cellL">
		<tr>
			<td width="120" nowrap>재고량연동</td>
			<td width="50%" class="noline">
				<input type="checkbox" name="usestock" value="o" ${'o' eq goodsVO.goodsObj.usestock ? 'checked' : '' }> 주문시 재고량빠짐
				<div style="padding-top:3px"><font class=extext>(체크안하면 재고량 상관없이 무한정판매)</font></div>
			</td>
			<td width="120" nowrap>품절상품</td>
			<td width="50%" class="noline">
				<input type="checkbox" name="runout" value="1"  ${'1' eq goodsVO.goodsObj.runout ? 'checked' : '' } > 품절된 상품입니다
			</td>
		</tr>
		<tr>
			<td>과세/비과세</td>
			<td class="noline">
				<input type="radio" name="tax" value="1" ${0 ne goodsVO.goodsObj.tax ? 'checked' : '' }> 과세
				<input type="radio" name="tax" value="0" ${0 eq goodsVO.goodsObj.tax ? 'checked' : '' }> 비과세
			</td>
			<td>가격대체문구</td>
			<td><input type="text" name="strprice" value="${goodsVO.goodsObj.strprice }" class="line" maxlength=10></td>
		</tr>
		<tr>
			<td>배송비</td>
			<td colspan="3">
				<table cellspacing="0" cellpadding="0" border="0">
					<tr height="40">
						<td>	
							<input type="radio" name=deliveryType value="0" ${0 eq goodsVO.goodsObj.deliverytype ? 'checked' : '' } class="null" onclick="chk_delivery_type();">기본배송정책에 따름&nbsp;
							<input type="radio" name="deliveryType" value="1" ${1 eq goodsVO.goodsObj.deliverytype ? 'checked' : '' }  class="null" onclick="chk_delivery_type();"> 무료배송&nbsp;
							<input type="radio" name="deliveryType" value="2" ${2 eq goodsVO.goodsObj.deliverytype ? 'checked' : '' } class="null" onclick="chk_delivery_type();">상품별 배송비 입력
							<span style="display:none;" id="gdi">&nbsp;
								₩<input type="text" name="goodsDelivery" value="${!empty goodsVO.goodsObj.goodsdelivery ? goodsVO.goodsObj.goodsdelivery:''}" size="8" onkeydown="onlynumber()">
							</span>	&nbsp;
							<input type="radio" name="deliveryType" value="3" ${3 eq goodsVO.goodsObj.deliverytype ? 'checked' : '' } class="null" onclick="chk_delivery_type();">착불배송비
							<span style="display:none;" id="gdi2">&nbsp;₩<input type="text" name="goodsDelivery2" value="${goodsVO.goodsObj.goodsdelivery}" size="8" onkeydown="onlynumber()"></span>
						</td>		
					</tr>
				</table>
				<div>
					<font class="extext">기본배송정책과 상품별 배송비 정책은 <a href="../basic/delivery" target="_blank"><font class="extext_l">[기본관리 > 배송/택배사 설정]</font></a> 에서 관리 하실 수 있습니다.</font>
				</div>
			</td>
		</tr>
	</table>
	 <div style="padding: 10px 10px 10px 0px">
<!-- 		<a href="#none" onclick="vOption();" onfocus=blur()><img src="../img/btn_priceopt_add.gif" align="absmiddle"></a>  -->
<!-- 		 <font class="small" color="444444">이상품의 옵션이 여러개인경우 등록하세요 (색상, 사이즈 등)</font> -->
		<%//<a href="javascript:alert('준비중입니다..')"><!-- javascript:popup('http://guide.godo.co.kr/guide/php/ex_infoprice.html',730,700) --><img src="../img/icon_sample.gif" border="0" align=absmiddle></a>%>
	</div> 
	<div id="objOption" style="display:none">
		<div style="padding-bottom:10;display:none"" >
			<font class="small" color="black"><b>옵션명1</b></font> : <input type="text" name="optnm[]" value="${null ne goodsVO.optNmArray ? goodsVO.optNmArray[0] : '' }">
			<a href="javascript:addopt1()" onfocus=blur()><img src="/resources/shop/admin/img/i_add.gif" align="absmiddle"></a> 
			<a href="javascript:delopt1()" onfocus=blur()><img src="/resources/shop/admin/img/i_del.gif" align="absmiddle"></a>
			<span style="width:20"></span>
			<font class="small" color="black"><b>옵션명2</b></font> : <input type="text" name="optnm[]" value="${null ne goodsVO.optNmArray ? goodsVO.optNmArray[1] : '' }">
			<a href="javascript:addopt2()" onfocus='blur()'><img src="/resources/shop/admin/img/i_add.gif" align="absmiddle"></a> 
			<a href="javascript:delopt2()" onfocus='blur()'><img src="/resources/shop/admin/img/i_del.gif" align="absmiddle"></a>
			<span style="width:20"></span>
			<span class="noline">
				<b>옵션출력방식</b> :
				<input type="radio" name="opttype" value="single"  ${'single' eq goodsVO.goodsObj.opttype ? 'checked' : '' }> 일체형
				<input type="radio" name="opttype" value="double"  ${'double' eq goodsVO.goodsObj.opttype ? 'checked' : '' }> 분리형
			</span>
		</div>
		<c:if test="${goodsVO.goodsOptionList ne null }">
			<script>vOption();</script>
		</c:if>
		<div style="display:none"><font class="extext">등록한 옵션명1과 옵션명2를 더블클릭하시여 옵션을 삭제하실 수 있습니다.</font>
		<table id="tbOption" border="1" bordercolor="#cccccc" style="border-collapse:collapse">
			<tr align="center">
				<td width="116"></td>
				<td>판매가</td>
				<td>정가</td>
				<td>매입가</td>
				<td>적립금</td>
	<c:choose>
		<c:when test="${goodsVO.optTitle ne null and  fn:length(goodsVO.optTitle) > 0 }">
			<c:forEach items="${goodsVO.optTitle}" var="optTitle" varStatus="status">
				<td id='tdid_${status.index }'>
					<input type="text" name="opt2[]" 
					<c:choose>
						<c:when test="${stringUtil:nullConv(optTitle) ne '' }">
						class=fldtitle value="${optTitle}"
						</c:when>
						<c:otherwise>
						class=opt gray value=''
						</c:otherwise>
					</c:choose>
					<c:if test="${status.index>1}">
						ondblclick="delopt2part('tdid_${status.index+4}')"
					</c:if>
						onclick="chkOptName(this)" onblur="chkOptNameOver(this)">
				</td>
			</c:forEach>
		</c:when>
		<c:otherwise>
				<td id='tdid_0'>
					<input type="text" name="opt2[]" class="opt" gray value='' onclick="chkOptName(this)" onblur="chkOptNameOver(this)">
				</td>
		</c:otherwise>
	</c:choose>
			</tr>	
			<c:set var="goodsOptionListSize" value="${fn:length(goodsVO.goodsOptionList)}"/>
<c:choose>		
<c:when test="${null ne goodsVO.goodsOptionList and  fn:length(goodsVO.goodsOptionList) > 0 }">
	<c:set var= "idx" value="${goodsVO.goodsOptionList ne null and  goodsOptionListSize > 0 ? fn:length(goodsVO.goodsOptionList) : 1}"/>
<%-- 	<c:forEach begin="0" end="${idx}" step="1" var="index"> --%>
	<c:forEach items="${goodsVO.goodsOptionList}" var="goodsOptionList" varStatus="status">
    		<tr id="trid_${status.index}">
				<td width="116" nowrap>
					<input type="text" name="opt1[]" 
					<c:choose>
						<c:when test="${stringUtil:nullConv(goodsOptionList.opt1) ne '' }">
						class=fldtitle value=${goodsOptionList.opt1}
						</c:when>
						<c:otherwise>
						class='opt gray' value=''    
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${stringUtil:nullConv(goodsOptionList.opt1) ne ''}">
						class=fldtitle value=${goodsOptionList.opt1}
						</c:when>
						<c:otherwise>
						class='opt gray' value=''
						</c:otherwise>
					</c:choose>
					<c:choose>
					<c:when test="${1 ne index}">
					ondblclick='delopt1part("trid_${status.index}")'
					</c:when>
					<c:otherwise>
					</c:otherwise>
					</c:choose>
<%-- 					${1 ne index ? "ondblclick='delopt1part("trid_" + ${index} + "")'" : ""}  --%>
					
					onclick="chkOptName(this)" onblur="chkOptNameOver(this)">
				</td>
				<td><input type="text" name="option[price][]" class="opt gray" value="${stringUtil:nullConv(goodsOptionList.price)}"></td>
				<td><input type="text" name="option[consumer][]" class="opt gray" value="${stringUtil:nullConv(goodsOptionList.consumer)}"></td>
				<td><input type="text" name="option[supply][]" class="opt gray" value="${stringUtil:nullConv(goodsOptionList.supply)}"></td>
				<c:if test='${stringUtil:nullConv(goodsOptionList.stock) ne ""}'>
					<c:set var= "tmpArray" value="${fn:split(stringUtil:nullConv(goodsOptionList.stock),'|')}"/>
					<c:forEach var="tmpArray" items="${tmpArray}" varStatus="g">
						<c:set var= "tmp" value="${stringUtil:nullConv(tmpArray)}"/>
						<td>
							<input type="text" name="option[stock][${status.index}]" 
							<c:choose>
								<c:when test="${'' ne tmp}">
									class='opt' value='${tmp}'
								</c:when>
								<c:otherwise>
									class='opt gray' value='재고'
								</c:otherwise>
							</c:choose>
							onclick="chkOptName(this)" onblur="chkOptNameOver(this)">
							<input type="hidden" name="option[optno][]" value="">
						</td>
					</c:forEach>
				</c:if>
			</tr>
	</c:forEach>
</c:when>
<c:otherwise>
		<tr id="trid_${goodsOptionListSize}">
 			<td width="116" nowrap>
 				<input type="text" name="opt1[]" class='opt gray' value='' 	${goodsOptionListSize ne 0 ? "ondblclick='delopt1part(\"trid_0\")'" : ""} onclick="chkOptName(this)" onblur="chkOptNameOver(this)">
 			</td>
 			<td><input type="text" name="option[price][]" class="opt gray" value=""></td>
 			<td><input type="text" name="option[consumer][]" class="opt gray" value=""></td>
 			<td><input type="text" name="option[supply][]" class="opt gray" value=""></td>
 			<td>
 				<input type="text" name="option[stock][0]" class='opt gray' value='재고' onclick="chkOptName(this)" onblur="chkOptNameOver(this)">
 				<input type="hidden" name="option[optno][]" value="">
 			</td>
		</tr>	
</c:otherwise>
</c:choose>
		</table>
		</div>
	</div>
	<div style="border-bottom:3px #efefef solid;padding-top:20px"></div>
	
<!-- 추가옵션 -->
<c:if test='${codeUtil:getConfValue("rental_free") eq "false" }'>
	<div class="title">추가옵션/추가상품/사은품<span>추가옵션을 무제한 등록할 수 있으며, 추가상품을 판매하거나 사은품을 제공할 수도 있습니다 
	</div>
	<div class="noline" style="padding-bottom:5px">
		<%-- <input type="radio" name="useadd" ${goodsVO.goodsAddList ne null ? "checked" : ""} onclick="openLayer('tbAdd','block')" onfocus=blur()> 사용
		<input type="radio" name="useadd" ${goodsVO.goodsAddList eq null ? "checked" : ""} onclick="openLayer('tbAdd','none')" onfocus=blur()> 사용안함 --%>
		<input type="radio" name="useadd" ${goodsVO.goodsObj.useadd eq '1' ? "checked" : ""} onclick="openLayer('tbAdd','block')" onfocus=blur() value="1"> 사용
		<input type="radio" name="useadd" ${goodsVO.goodsObj.useadd eq '0' ? "checked" : ""} onclick="openLayer('tbAdd','none')" onfocus=blur() value="0"> 사용안함
	</div>

	<a href="javascript:add_addopt()"><img src="/resources/shop/admin/img/i_addoption.gif" align=absmiddle></a>
	<a href="javascript:del_addopt()"><img src="/resources/shop/admin/img/i_deloption.gif" align=absmiddle></a>
	<span class="small1" style="padding-left:5px">(옵션명에 아무 내용도 입력하지 않으면 해당 옵션은 삭제처리됩니다)</span>

	<div style="height:7px"></div>

	<%-- <table id="tbAdd" style="display:${goodsVO.goodsAddList ne null ? 'block' : 'none'}" border="2" bordercolor="#cccccc" style="border-collapse:collapse"> --%>
	<table id="tbAdd" style="display:${goodsVO.goodsObj.useadd eq '1' ? 'block' : 'none'}" border="2" bordercolor="#cccccc" style="border-collapse:collapse">
		<tr bgcolor="#f7f7f7" align="center">
			<td>옵션명 <font class="small">(예. 악세사리)</font></td>
			<td>항목명 <font class="small">(예. 열쇠고리)</font></td>
			<td>가격 <font class="small" color="444444">(무료일때는 0원입력)</font></td>
			<td>구매시필수</td>
		</tr>
		<col valign="top" style="padding-top:5px">
		<col span="2"><col align="center" valign="top" style="padding-top:5px">
		<c:if test='${goodsVO.goodsAddList ne null}'>
			<c:set var= "addOptNmArray" value="${fn:split(stringUtil:nullConv(goodsVO.goodsObj.addoptnm),'|')}"/>
			<c:forEach var="addOptNmArray" items="${addOptNmArray}" varStatus="g">
				<c:set var= "tmpArray" value="${fn:split(stringUtil:nullConv(addOptNmArray),'^')}"/>
				<tr>
					<td>
						<input type="text" name="addoptnms" value="${tmpArray[0]}">
						<a href="javascript:void(0)" onClick="add_subadd(this)"><img src="/resources/shop/admin/img/i_proadd.gif" align="absmiddle" border="0"></a>
					</td>
					<td colspan="2">
						<table>
							<col><col align="center">
							<c:forEach var="goodsAddList" items="${goodsVO.goodsAddList}" varStatus="idx">
								<c:if test='${goodsAddList.step eq g.index}'>
									<tr>
										<td><input type="text" name="addopt[opt][${g.index}][]" value="${goodsAddList.opt}" style="width:270px"> 선택시</td>
										<td>판매금액에 ₩<input type="text" name="addopt[addprice][${g.index}][]"  size="9" value="${goodsAddList.addprice}"  onkeydown="onlynumber()"> 추가</td>
									</tr>
								</c:if>
							</c:forEach>
						</table>
					</td>
					<td class="noline" align="center"><input type="checkbox" name="addoptreq[${g.index}]" value="o" ${fn:length(tmpArray)>1 && "o" eq stringUtil:nullConv(tmpArray[1]) ? "checked" : "" }></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<div style="border-bottom:3px #efefef solid;padding-top:20px"></div>
</c:if>
<!-- 관련상품 -->
	<div class="title">관련상품<span>이상품과 관련있는 상품을 추천하세요 
	</div>
	<div style="padding-bottom:5px" class=noline>
		<input type="radio" name="relationis" value="0" onfocus="blur()" onclick="openLayer('divRefer','none')" ${"1" ne stringUtil:nullConv(goodsVO.goodsObj.relationis) ? "checked" : ""}>자동 
		<font class="small" color="#5A5A5A">(같은 분류 5개상품이 무작위로 보여짐)</font>
		&nbsp;&nbsp;&nbsp;
		<input type="radio" name="relationis" value="1" onfocus="blur()" onclick="openLayer('divRefer','block')" ${"1" eq stringUtil:nullConv(goodsVO.goodsObj.relationis) ? "checked" : ""}>수동
		<font class="small" color="#5A5A5A">(아래 직접 선택등록) (현재 관련상품 : <b>${goodsVO.relationCnt}</b>개)</font>
	</div>
	<div id="divRefer" style="display:${goodsVO.relationCnt >0 ? "block" : "none"};position:relative;z-index:99">
		<div style="padding-bottom:3px">
			<script>new categoryBox('refer[]',4,'','');</script>
			<input type="text" name=search_refer onkeydown="return go_list_goods('refer')">
			<a href="javascript:list_goods('refer')"><img src="/resources/shop/admin/img/i_search.gif" align=absmiddle></a>
			<a href="javascript:view_goods('refer')"><img src="/resources/shop/admin/img/i_openclose.gif" align=absmiddle></a>
		</div>
		<div id="obj_refer" class="box1">
			<iframe id="ifrm_refer" style="width:100%;height:100%" frameborder="0"></iframe>
		</div>
		<div id="obj2_refer" class="box2 scroll" onselectstart="return false" onmousewheel="return iciScroll(this)">
			<div class="boxTitle">- 등록된 관련상품 <font class="small" color="#F2F2F2">(삭제하려면 더블클릭)</font></div>
			<table id="tb_refer" class="tb">
				<col width="50">
				<c:forEach var="relationList" items="${goodsVO.relationList}" varStatus="idx">
					<tr onclick="spoit('refer',this)" ondblclick="remove_goods('refer',this)" class="hand">
					<td width="50" nowrap>
						<c:set var="subStr" value="${fn:substring(relationList.imgs, 1, (fn:length(relationList.imgs))) }" />
						<a href="../../goods/goods_view?goodsno=${relationList.goodsno}" target="_blank">${shopLibFunction:goodsimg(subStr,"40","",4)}</a>
					</td>
					<td width="100%">
						<div>${relationList.goodsnm}</div>
						<b><fmt:formatNumber value="${relationList.price}" pattern="\#,###.##"/></b>
						<input type="hidden" name="e_refer[]" value="${relationList.goodsno}">
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<div id="referX" style="padding-top:3px"></div>
	</div>
	<div style="border-bottom:3px #efefef solid;padding-top:20px"></div>

	<script>react_goods('refer');</script>	
	
<!-- 상품 이미지 -->
	<div class="title">상품 이미지<span>아래 자동리사이즈 되는 기능을 활용하면 더욱 편리합니다. 
	</div>

	<div style="padding:0 0 10 14">처음 상품이미지를 등록하신다면, 반드시 
		<a href="imgsize" target=_blank><img src="/resources/shop/admin/img/i_imgsize.gif" border="0" align="absmiddle"></a> 먼저 설정하세요!&nbsp;&nbsp;
	</div>

	<table class="tb">
		<col class="cellC"><col class="cellL"><col class="cellC"><col class="cellL">
		<c:if test='${null ne goodsVO.imgsArray}'>
			<c:forEach var="i" begin="0" end="3" step="1">
				<tr>
				<td>
					${goodsVO.strImg[i] }
				<%-- <c:if test='${"l" ne goodsVO.imgsArray[i][0]}'>
					<div class="noline" style="font:11px dotum;letter-spacing:-1px;"><input type="checkbox" name="copy_${i}"> <font class="extext"><b>자동리사이즈 사용</b></font></div>
				</c:if> --%>
				<!-- 2017-08-30 이미지 설명 추가 -->
				<c:if test="${i==2 }">
					<br/><font class="extext">상품리스트의 마우스오버시 노출되는 이미지</font>
				</c:if>
				</td>
			<td>
				<input type="hidden" name="orgNames_img_${goodsVO.imgsArray[i][0]}" value="${goodsVO.imgsArray[i][1]}"/> 
				<table id="tb_${goodsVO.imgsArray[i][0]}">
					<col valign="top" span="2">
					<c:set var= "tmpArray" value="${fn:split(stringUtil:nullConv(goodsVO.imgsArray[i][1]),'|')}"/>
					<c:if test='${0 eq fn:length(tmpArray)}'>
						<tr>
							<td>
<!-- 							 확대이미지, 상세이미지 일경우 추가버튼 출력 -->
							<c:choose>
								<c:when test='${("l" eq goodsVO.imgsArray[i][0] || "m" eq goodsVO.imgsArray[i][0]) }'>
									<a href="javascript:addfld('tb_${goodsVO.imgsArray[i][0]}')"><img src="/resources/shop/admin/img/i_add.gif" align="absmiddle"></a>
								</c:when>
								<c:otherwise>
									<font color="white">.........</font>
								</c:otherwise>
							</c:choose>
								<%-- <span><input type="file" name="img_${goodsVO.imgsArray[i][0]}[]0"  style="width:300px" onChange="preview(this)" accept=".gif, .jpg, .png, .jpge, .bmp" ></span> --%>
								<span><input type="file" name="img_${goodsVO.imgsArray[i][0]}[]0"  style="width:300px" onChange="preview(this)" accept=".gif, .jpg, .png, .jpge, .bmp" ></span>
							</td>
							<td>
							</td>
							<td>
							</td>
							</tr>
					</c:if>
					<c:forEach var="tmpArray" items="${tmpArray}" varStatus="g">
						<tr>
							<td>
<!-- 							 확대이미지, 상세이미지 일경우 추가버튼 출력 -->
							<c:choose>
								<c:when test='${("l" eq goodsVO.imgsArray[i][0] || "m" eq goodsVO.imgsArray[i][0]) && 0 == g.index}'>
									<a href="javascript:addfld('tb_${goodsVO.imgsArray[i][0]}')"><img src="/resources/shop/admin/img/i_add.gif" align="absmiddle"></a>
								</c:when>
								<c:otherwise>
									<font color="white">.........</font>
								</c:otherwise>
							</c:choose>
								<span><input type="file" name="img_${goodsVO.imgsArray[i][0]}[]${g.index}"  id="img_${goodsVO.imgsArray[i][0]}${g.index}" " style="width:300px" onChange="preview(this)" accept=".gif, .jpg, .png, .jpge, .bmp" ></span>
							</td>
							<td>
								<c:if test='${"" ne tmpArray}'>
									<%-- <div style="padding:0 0" class=noline><input type=checkbox name="del[img_${goodsVO.imgsArray[i][0]}][]${g.index}" value="${tmpArray}"><font class="small" color="#585858">삭제 (${tmpArray})</font></div> --%>
									<div style="padding:0 0" class=noline><input type=checkbox id="del_img_${goodsVO.imgsArray[i][0]}${g.index}" name="del[img_${goodsVO.imgsArray[i][0]}][]${g.index}" value="${tmpArray}"><font class="small" color="#585858">삭제 (${tmpArray})</font></div>
								</c:if>
							</td>
							<td>
								<c:if test='${"" ne tmpArray}'>
<%-- 									<c:set var='tmpStr1' value='${fn:substring(tmpArray,fn:indexOf(tmpArray,"]")+1,fn:length(tmpArray))}'/> --%>
									<c:set var='tmpStr1' value='${tmpArray}'/>
									<c:set var="tempStyle1" value="style='border:1 solid #cccccc' onclick=popupImg('/shop/data/upload/goods/"/>
									<c:set var="tempStyle2" value="','../') class=hand"/>
									<c:set var="tmpStr2">${tempStyle1}${tmpStr1}${tempStyle2}</c:set>
									${shopLibFunction:goodsimg(tmpArray,"20",tmpStr2,4)} 
								</c:if>
								</td>
							</tr>
					</c:forEach>
					</table>
				</td>
			</tr>
			</c:forEach>
		</c:if>
		</table>
		<div style="border-bottom:3px #efefef solid;padding-top:30px"></div>

		<!-- 상품 고시 -->
		<div class="title">상품 고시</div>
			
		<div class="box" style="padding-left:3">
			<table cellpadding="0" cellspacing="1" border="1" bordercolor="#cccccc" style="border-collapse:collapse" >
				<tr>
					<td style="padding:7 7 7 10" bgcolor=f8f8f8>
						<table width="100%" cellpadding="0" cellspacing="1">
							<tr>
								<td style="padding:10 10 5 10">
									<input type="text" id="goodsNotiGrpNm" label="고시정보 명" placeholder="고시정보 명"/> <input type="button" value="고시정보등록" onclick="registerGoodsNoti();" />
								</td>
							</tr>
							<tr>
								<td style="padding:0 10 7 10"></td>
							</tr>
						</table>
					 </td>
				</tr>
			</table>
		</div>
			
		<div style="height:7px"></div>
			
		<div class="noline" style="padding-bottom:5px">
			<select id="selGoodsNotiList" onchange="setGoodsNoti();">
				<option value="">:::목록보기 :::</option>
			</select>
			<input type="button" value="항목추가" onclick="addGoodsNoti();" />
			<input type="button" value="항목선택삭제" onclick="removeGoodsNoti();" />
		</div>
			
		<table class="tb" id="tblGoodsNoti" style="width:700px;">
			<tr bgcolor="#f7f7f7" align="center">
				<td width="150"></td>
				<td width="150"><input type="checkbox" id="allGoodsNotiChk" onchange="$('#tblGoodsNoti input:checkbox:not(#allGoodsNotiChk)').each(function(){$(this).prop('checked', $('#allGoodsNotiChk').is(':checked'));});" /></td>
				<td width="200">항목</td>
				<td>내용</td>
			</tr>
			<c:choose>
				<c:when test="${!empty myritzGoodsFM.goodsNotiList}">
					<c:forEach var="goodsNoti" items="${myritzGoodsFM.goodsNotiList}" varStatus="varStat">
						<tr>
							<td align="center">${varStat.count}</td>
							<td align="center"><input type="checkbox" /></td>
							<td><input type="text" style="width: 100%" label="항목 1" name="goodsNotiItem[]" value="${goodsNoti.item}" /></td>
							<td>
								<textarea style="width:100%;height:40px;" class="__tline" label="내용 1" name="goodsNotiMemo[]">${goodsNoti.memo}</textarea>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td align="center">1</td>
						<td align="center"><input type="checkbox" /></td>
						<td><input type="text" style="width: 100%" label="항목 1" name="goodsNotiItem[]" /></td>
						<td>
							<textarea style="width:100%;height:40px;" class="__tline" label="내용 1" name="goodsNotiMemo[]"></textarea>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	
		<div style="border-bottom:3px #efefef solid;padding-top:30px"></div>
		
		<!-- 상품 설명 -->
		<div class="title">상품 설명 
		</div>

		<table class="tb">
			<col class="cellC"><col class="cellL"><col class="cellC"><col class="cellL">
			<tr>
				<td>짧은설명</td>
				<td>
					<textarea name="shortdesc" style="width:100%;height:20px;" class="__tline" maxlength=100>${stringUtil:nullConv(goodsVO.goodsObj.shortdesc)}</textarea>
				</td>
			</tr>
		</table>
		
		<div style="height:6px;font:0"></div>
		<textarea name="longdesc" style="width:100%;height:400px" type="editor">${stringUtil:nullConv(goodsVO.goodsObj.longdesc)}</textarea>
		<div style="border-bottom:3px #efefef solid;padding-top:20px"></div>

		<!-- 요청 메모 -->
		<div class="title">판매사 요청 메모</div>
		<textarea name="approvalMemo" style="width:100%;height:60px" class="tline" maxlength=200>${stringUtil:nullConv(goodsVO.goodsObj.approvalMemo)}</textarea>
		
		<!-- 관리 메모 -->
		<c:if test='${codeUtil:getConfValue("rental_free") eq "false" }'>
			<div class="title">관리 메모</div>
			<textarea name="memo" style="width:100%;height:60px" class="tline" maxlength=200>${stringUtil:nullConv(goodsVO.goodsObj.memo)}</textarea>
		</c:if>



		<div class="button">

			<input type="image" src="/resources/shop/admin/img/btn_${goodsVO.mode }.gif" onclick="return checkGoodsForm()">
			<c:if test='${"modify" eq goodsVO.mode}'>
			</c:if>
			<c:set var='returnUrl' value='/shop/admin/myritz/goodsList'/>
			<a href='${returnUrl}'><img src='/resources/shop/admin/img/btn_list.gif'></a>
			<c:if test="${'' ne goodsVO.goodsno}">
				&nbsp;<a href="../../goods/goods_view?goodsno=${goodsVO.goodsno}" target="_blank"><img src="/resources/shop/admin/img/btn_goods_view.gif"></a>
			</c:if>
		</div>		
</form>

	<!-- 웹에디터 활성화 스크립트 -->
	<script src="/resources/shop/lib/meditor/mini_editor.js"></script>
	<SCRIPT LANGUAGE="JavaScript">
		mini_editor("/resources/shop/lib/meditor/"); 
		chk_delivery_type();
	
		if ( typeof(call_file_disabled) == "undefined" );
		else file_disabled();
	</SCRIPT>


	