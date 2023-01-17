<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ page import="static com.wepinit.wepinit_shop.xmall.common.ShopLibFunction.*"%>
<%@ page import="com.wepinit.wepinit_shop.xcube.util.StringUtil"%>

<script language=javascript  src="/resources/shop/admin/common.js"></script>
<script language=javascript  src="/resources/shop/admin/jquery.number.min.js"></script>

<script language="javascript">
	var goodsNotiList = null;
	
	/* 2017-08-24 추가 적립금 수정 */
	$(document).ready(function(){
		getGoodsNotiList();
		
		//상품옵션 사용시 stock 계산
		if($("input:radio[name=usegoodsadd]:checked").val() == 1){
			changeStock();
		}	
		
	});
	
	// 총 재고량
	function totalStock(){
		var optStock = $('input[name=optStock]');
		var stock = 0;

		for(var i=0;i<optStock.length ; i++){
			stock = stock+Number($(optStock[i]).val());
		}
		$("input[name=stock]").val(stock);
		
	};
	
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
 		if('1' == $('input[name="useSeller"]:checked').val()){
			if($('#schSellerNm').val().length < 1){
				alert("판매사를 선택해주세요");
				$('#schSellerNm').focus();
				return false;
			}
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
		
		if($("input:radio[name=useadd]:checked").val() == '1' ){
	    	for(var i = 0; i < $("input[name=addoptnms]").length; i++){
	    		if(!$("input[name=addoptnms]").eq(i).val()){
	    			alert("옵션명을 입력해주세요");
	    			$("input[name=addoptnms]").eq(i).focus();
	    			return false;
	    		}
	    	}
		}
	}

	function popupImg(src,base)
	{
		if (!src) src = encodeURIComponent(this.src);
		if (!base) base = "";
		window.open(src,'img','width=600,height=500');
	}
	
	function exec_add()
	{
		var ret;
		var str = new Array();
		var obj = document.forms[0]['ncate[]'];
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
			<input type=text name=csort[] value='<%--=time()--%>' class='sortBox right' maxlength=10 ${goodsVO.strHieen} readonly>\
			";
			oTd = oTr.insertCell(2);
			// oTd.innerHTML = "<!--<img src='/resources/shop/admin/img/i_select.gif' onClick=\"cate_mod(document.forms[0]['ncate[]'][0],this.parentNode.parentNode)\" class=hand>--> <a href='javascript:void(0)' onClick='cate_del(this.parentNode.parentNode)'><img src='/resources/shop/admin/img/i_del.gif' align=absmiddle></a>";
			oTd.innerHTML = `<a href="javascript:void(0)" class="admin-i-del" onClick="cate_del(this.parentNode.parentNode)">삭제</a>`;
		}
	}
	
	/*** 상품 이미지 ***/
	/*
	function preview(obj)
	{
		var tmp = obj.parentNode.parentNode.parentNode.childNodes[2];
		tmp.innerHTML = "<tr><td><img src='" + obj.value + "' width=20 onload='if(this.height>this.width){this.height=20}' style='border:1 solid #cccccc' onclick=popupImg(this.src,'../') class=hand></td></tr>";
	}
	*/
	function addfld(obj)
	{
		var tb = document.getElementById(obj);
		var src = tb.rows[0].cells[0].getElementsByTagName('span')[0].innerHTML;
		
		oTr = tb.insertRow(tb.rows.length);
		oTd = oTr.insertCell(0);
		src = src.replace("[]0", "[]" + (tb.rows.length-1));
		src = src.replace("img_m0", "img_m" + (tb.rows.length-1));
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
			<td>판매금액에 ₩<input type=text name=addopt[addprice][" + (oTr.rowIndex-1) + "][] size=9 onkeyup='removeChar(event)'> 추가</td>\
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
		td.html("<input type=text name=addopt[opt][" + idx + "][] style='width:270px' maxlength=255> 선택시");
		tr.append(td);
		td = $("<td></td>");
		td.html("판매금액에 ₩<input type=text name=addopt[addprice][" + idx + "][] size=9 onkeyup='removeChar(event)''> 추가");
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
		changeStock();
		var fm = document.forms[0];
		var tbOption = document.getElementById('tbOption');
		var Rcnt = tbOption.rows.length;
		var addOptVal = document.getElementsByName("optnm")[0].value;
		
		if(5 < Number(Rcnt)){
			return alert("옵션은 최대 5개까지 추가 할 수 있습니다.");
		}
		
		var optionStockArrayIndex = Rcnt - 1;
		oTr = tbOption.insertRow(tbOption.rows.length);
		oTr.id = "trid_" + optionStockArrayIndex;

		for (i=0;i<tbOption.rows[0].cells.length;i++){
			oTd = oTr.insertCell(i);
			switch (i){
				case 0: oTd.innerHTML = "<input type=text class='opt gray' name=opt1[] value='"+addOptVal+"' required label='1차옵션명'>"; break;
				case 1:	oTd.innerHTML = "<input type=text name=option[consumer][] class='opt gray consumer' data-reg=\"/^[0-9.]+$/\" onkeyup='removeChar(event)' maxlength=10 value='" + fm.consumer.value + "'>"; break;
				case 2:	oTd.innerHTML = "<input type=text name=option[price][] class='opt gray price' data-reg=\"/^[0-9.]+$/\" onkeyup='removeChar(event)' maxlength=10 value='" + fm.price.value + "'>"; break;
				case 3:	oTd.innerHTML = "<input type=text name=option[priceRate][] class='opt gray priceRate' data-reg=\"/^[0-9.]+$/\" onkeyup='removeChar(event)' maxlength=10 value='" + fm.priceRate.value + "'>"; break;
				case 4:	oTd.innerHTML = "<input type=text name=option[supply][] class='opt gray' data-reg=\"/^[0-9.]+$/\" onkeyup='removeChar(event)' maxlength=10 value='" + fm.supply.value + "'>"; break;
				case 5:	oTd.innerHTML = "<input type=text name=option[supply2][] class='opt gray' data-reg=\"/^[0-9.]+$/\" onkeyup='removeChar(event)' maxlength=10 value='" + fm.supply2.value + "'>"; break;
				case 6:	oTd.innerHTML = "<input type=text name=option[margin][] class='opt gray' data-reg=\"/^[0-9.]+$/\" onkeyup='removeChar(event)' maxlength=10 value='" + fm.margin.value + "'>"; break;
				case 7:	oTd.innerHTML = "<input type=text name=option[optexplain][] class='opt gray' value=''>"; break;
				case 8: oTd.innerHTML = "<input type=text name=option[opt1Stock][] class='opt gray' onkeydown='onlynumber(event)' maxlength=10 value='' placeholder='재고량입력' onchange='changeStock()' >"; break;
				case 9: oTd.innerHTML = "<input type=hidden name=option[priceMyRitz][] class='opt gray' maxlength=10 value='" + fm.priceMyRitz.value + "' placeholder='MyRitz공급가입력' >"; break;
				default: oTd.innerHTML = "<input type=text name=option[stock][] class='opt gray' onkeydown='onlynumber(event)' maxlength=10 value= '' placeholder='재고량입력' onchange='changeStock()' ><input type=hidden name=option[optno][]>";
				//alert(oTd.innerHTML);
				break;
			}
		}
	}
	function addopt2()
	{
		
		changeStock();
		var name;
		var tbOption = document.getElementById('tbOption');
		var addOptVal2 = document.getElementsByName("optnm")[1].value;

		if (tbOption.rows.length < 2){
			alert('1차옵션을 먼저 추가해주세요');
			return;
		}

		var Ccnt = tbOption.rows[0].cells.length;
		
		if(10 < Number(Ccnt)){
			return alert("옵션은 최대 5개까지 추가가 가능합니다");
		}
		
		for (i=0;i<tbOption.rows.length;i++){
			oTd = tbOption.rows[i].insertCell(tbOption.rows[i].cells.length);
			if(!i)oTd.id = "tdid_"+Ccnt;
			oTd.innerHTML = (i) ? "<input type=text name=option[stock][] class='opt gray' onkeydown='onlynumber(event)' onkeyup='removeHangul(event);' onchange='changeStock()' maxlength=10 placeholder='재고량입력'\"><input type=hidden name=option[optno][]>" 
					            : "<input type=text class='opt gray' name=opt2[] value='"+addOptVal2+"' required label='2차옵션명'>";
		}
	}
	function delopt1()
	{
		var tbOption = document.getElementById('tbOption');
		if (tbOption.rows.length>2) tbOption.deleteRow(tbOption.rows.length-1);
		changeStock();
	}
	function delopt2()
	{
		var tbOption = document.getElementById('tbOption');
		if (tbOption.rows[0].cells.length<7) return;
		for (i=0;i<tbOption.rows.length;i++){
			tbOption.rows[i].deleteCell(tbOption.rows[i].cells.length-1);
		}
		changeStock();
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
			if (document.getElementsByName("ncate[]")[0].value) exec_add();
			else {
				alert("카테고리를 등록해주세요");
				document.getElementsByName("ncate[]")[0].focus();
				return false;
			}
		} */
		if($("#objCategory").find("tbody").length < 1){
			alert("카테고리를 등록해주세요");
			document.getElementsByName("ncate[]")[0].focus();
			return false;
		}
		if (!chkOption()) return false;
		if (!chkForm(obj)) return false;
		//상품옵션 사용안할시 안에 데이터들은 없는거로처리
		if ($("input:radio[name='usegoodsadd']:checked").val() == 0 ){
			$("input[name*='option[']").attr("disabled", false);
			$("input[name='opt1[]']").attr("disabled", false);
			$("input[name='opt2[]']").attr("disabled", false);
			
		}
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
		if (e==8 || e==9 || e==13 || e==46 || e==110 || e==190) return;
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
	function vOption(mode)
	{
		//if(mode == 'block'){
		if( $("[name=usegoodsadd]:checked").val() == "1" ){
			openLayer('tbOption','block')
			$("#objOption").show();
			changeStock();
			$("input[name=stock]").prop("readonly", true);
		}else{
			openLayer('tbOption','none')
			$("input[name=stock]").val("${goodsVO.intTotalStock}");
			$("#objOption").hide();
			$("input[name=stock]").prop("readonly", false);
		}
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
	
	function changeStock(){
		var stockSum = 0; //총 재고량
		var opt1Arr = $("input[name='option[opt1Stock][]']"); //option1 재고량
		var opt2Arr = $("input[name='option[stock][]']"); 	  //option2 재고량
		//option2를 추가했을 시 option1 재고량은 하나의 row 재고량의 합
		if(opt2Arr.length > 0){
			var optIdx = 0;
			var sum = 0;
			var idx = opt2Arr.length / opt1Arr.length;
			
			$.each(opt2Arr ,function(index, item){
				sum += Number(item.value);
				
				if((index+1) % idx == 0){
					opt1Arr.eq(optIdx).val(sum)
					optIdx ++;
					sum = 0;
				}
			});
		}
		//opt1 재고량을 합해서 총 재고량 값에 대입
		$.each(opt1Arr ,function(index, item){
			stockSum += Number(item.value);
		});
		$("input[name=stock]").val(stockSum);
		
	}

	$(document).on("change", ".consumer, .priceRate", function(){
		var consumer = $(this).parent().parent().find(".consumer");
		var price = $(this).parent().parent().find(".price");
		var priceRate = $(this).parent().parent().find(".priceRate");
		
		var value = setFloat(setFloat(consumer.val()) - (setFloat(consumer.val()) * setFloat(priceRate.val()) / 100));
		$(".price").val(setFloat(value,'Y'));
	});

	$(document).on("change", ".consumer, .price", function(){
		var consumer = $(this).parent().parent().find(".consumer");
		var price = $(this).parent().parent().find(".price");
		var priceRate = $(this).parent().parent().find(".priceRate");
		
		//var value = $.number(($.number(consumer.val(), 2) - $.number(price.val(), 2)) / $.number(consumer.val(), 2) * 100, 2);
		//$(".priceRate").val(value);
		var value = (setFloat(setFloat(consumer.val()) - setFloat(price.val())) / setFloat(consumer.val()) * 100).toFixed(2);
		$(".priceRate").val(setFloat(value,'Y'));		
	});
	
	// 적립금 소수점 2자리 처리
	function setFloat(value, fixed){
		var returnVal = Math.round(value * 100) / 100;
		if(fixed == "Y"){
			returnVal = (Math.round(value * 100) / 100).toFixed(2); //return String
		}
		return returnVal;
	}
</script>

<form name="fm" method="post" enctype="multipart/form-data" onsubmit="return chkForm2(this)" id="fm" action="indb" >
	<input type="hidden" name="qstr" value="pageNo=${goodsVO.pageNo}&pageSize=${goodsVO.pageSize}&sort=${goodsVO.sort}&schRegty=${goodsVO.schRegty}&schRegdt=${goodsVO.schRegdt[0]}&schRegdt=${goodsVO.schRegdt[1]}&schSellerNm=${goodsVO.schSellerNm}&schSellerCd=${goodsVO.schSellerCd}&cate=${goodsVO.cate[0]}&cate=${goodsVO.cate[1]}&cate=${goodsVO.cate[2]}&cate=${goodsVO.cate[3]}&schBrand=${goodsVO.schBrand}&schSeasonNm=${goodsVO.schSeasonNm}&schKey=${goodsVO.schKey}&schWord=${goodsVO.schWord}&schOpen=${goodsVO.schOpen}&schSoldOut=${goodsVO.schSoldOut}&schSpec=${goodsVO.schSpec}&schManageYn=${goodsVO.schManageYn}">
	
	<input type="hidden" name="mode" value="${goodsVO.mode }">
	<input type="hidden" name="goodsno" value="${goodsVO.goodsno}">
	<input type="hidden" name="m_id" value="${sessionScope.xmall_admin.ADMINUSER.xkey.m_id}">
	<input type="hidden" name="idx" value="0">
	<input type="hidden" name="category" value="">

	<c:if test="${'' ne goodsVO.goodsno }">
		 <div style="padding:8px 13px;background:#f7f7f7;border:3px solid #C6C6C6;margin-bottom:18px;" id="goodsInfoBox">
			<c:choose>
				<c:when test="${ 0 < goodsVO.reqsno }">
					<font class=def>임시번호:</font> <span style="color:#FF7200;font:bold 14px verdana">${goodsVO.reqsno}</span>
				</c:when>
				<c:when test="${ 0 < goodsVO.goodsno }">
					<font class=def>고유번호:</font> <span style="color:#FF7200;font:bold 14px verdana">${goodsVO.goodsno}</span>
				</c:when>
			</c:choose>
		</div>  
	</c:if>
	
	<!-- 상품 카테고리 선택 -->
	<div class="title title_top">
		상품분류정보<span>한상품에 여러개의 분류를 등록할 수 있습니다&nbsp;(다중분류기능지원)</span> 
	</div>
	
 	<div class="box" style="padding-left:3px">
		<table width="790" cellpadding="0" cellspacing="1" border="1" bordercolor="#cccccc" style="border-collapse:collapse">
			<tr>
				<td style="padding:7px 7px 7px 10px" bgcolor=f8f8f8>
					<table width="100%" cellpadding="0" cellspacing="1" id="objCategory">
						<col>
						<col width="50" style="padding-right:10px">
						<col width="52" align="right">
						<!-- 수정일 경우 카테고리 목록 출력 -->	
						<c:if test="${!empty goodsVO.categoryList && fn:length(goodsVO.categoryList)>0 }">
							<c:forEach items="${goodsVO.categoryList }" var="list">
								<tr>
									<td id="currPosition">${list.categoryNm }</td>
									<td>
										<input type="text" name="category[]" value="${list.category }" style="display:none">
										<input type="text" name="csort[]" value="${list.sort}" class="sortBox right" maxlength="10" ${goodsVO.strHieen} readonly>
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
	<div style="padding-top:10px" id="goodsCategory">
		<table>
			<tr>
				<td>
					<script>new categoryBox('ncate[]', 4, '', 'multiple');</script>
				</td>
				<td valign="top">
					<table width="100%" cellpadding="0" cellspacing="0" id="objCategory">
						<tr>
							<td height="55" valign="top">
<%--								<a href="javascript:exec_add()"><img src="/resources/shop/admin/img/i_regist_l.gif" vspace="4"></a><br>--%>
								<a href="javascript:exec_add()" class="admin-i-regist-l">분류 등록하기</a><br>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	
	
	<!-- 상품기본정보 -->
	<div class="sub-cont-wrap">
	<div class="title">판매사 정보</div>
	
	<div class="noline" style="padding-bottom:5px">
		<c:if test="${goodsVO.mode eq 'modify'}">
			<label>
				<input type="radio" name="tempUseSeller" value="0" onchange="$('#tbSeller').hide();" <c:if test="${goodsVO.goodsObj.sellerCd == null || '' == goodsVO.goodsObj.sellerCd}">checked</c:if> disabled="disabled"> 관리자 등록
			</label>
			<label>
				<input type="radio" name="tempUseSeller" value="1" onchange="$('#tbSeller').show();" <c:if test="${goodsVO.goodsObj.sellerCd != null && '' != goodsVO.goodsObj.sellerCd}">checked</c:if> disabled="disabled"> 판매사 등록
			</label>
			<input type="hidden" name="useSeller" value="${'' eq goodsVO.goodsObj.sellerCd? '0' : '1' }" />
		</c:if>
		<c:if test="${goodsVO.mode ne 'modify'}">
			<label>
				<input type="radio" name="useSeller" value="0" onchange="$('#tbSeller').hide();" <c:if test="${goodsVO.goodsObj.sellerCd == null || '' == goodsVO.goodsObj.sellerCd}">checked</c:if> > 관리자 등록
			</label>
			<label>
				<input type="radio" name="useSeller" value="1" onchange="$('#tbSeller').show();" <c:if test="${goodsVO.goodsObj.sellerCd != null && '' != goodsVO.goodsObj.sellerCd}">checked</c:if> > 판매사 등록
			</label>
		</c:if>
	</div>

	<%--
	<table id="tbSeller" class="tb"${goodsVO.goodsObj.sellerCd == null || '' == goodsVO.goodsObj.sellerCd ? ' style="display:none;"' : ''}>
		<col class="cellC">
		<col class="cellL">
		<tr>
			<td>판매사</td>
			<td>
				<input type="text" name="schSellerNm" id="schSellerNm" value="${'' ne goodsVO.goodsObj.sellerNm? goodsVO.goodsObj.sellerNm : goodsVO.schSellerNm }" class="line" style="height:22px" readonly="readonly"/>
				<input type="button" value="판매사 조회" onclick="popupLayer('${ctx}/shop/admin/common/sellerSearchPopup', 600, 500);" />
				<input type="hidden" name="schSellerCd" id="schSellerCd" value="${'' ne goodsVO.goodsObj.sellerCd? goodsVO.goodsObj.sellerCd : goodsVO.schSellerCd }" />
			</td>
		</tr>
		<!-- <tr>
			<td>승인여부</td>
			<td><input type="checkbox" /></td>
		</tr> -->
	</table>
	--%>

		<div id="tbSeller" class="div-tbl inp-tbl" ${goodsVO.goodsObj.sellerCd == null || '' == goodsVO.goodsObj.sellerCd ? ' style="display:none;"' : ''}>
			<div class="th w-120">판매사</div>
			<div>
				<input type="text" name="schSellerNm" id="schSellerNm" value="${'' ne goodsVO.goodsObj.sellerNm? goodsVO.goodsObj.sellerNm : goodsVO.schSellerNm }" class="line" style="height:22px" readonly="readonly"/>
				<input type="button" value="판매사 조회" onclick="popupLayer('${ctx}/shop/admin/common/sellerSearchPopup', 600, 500);" />
				<input type="hidden" name="schSellerCd" id="schSellerCd" value="${'' ne goodsVO.goodsObj.sellerCd? goodsVO.goodsObj.sellerCd : goodsVO.schSellerCd }" />
			</div>
		</div>
	
<%--		<div style="padding-top:10px"></div>--%>
	</div>	<!-- END sub-cont-wrap -->
	
	<!-- 상품기본정보 -->
	<div class="sub-cont-wrap hr">
	<div class="title">상품기본정보
		<span>원산지, 브랜드가 없는 경우 입력안해도 됩니다</span>
	</div>
	<!-- 여기부터 -->
	<%--
	<table class="tb">
		<col class="cellC">
		<col class="cellL">
		<col class="cellC">
		<col class="cellL">
		<tr>
			<td width="120" nowrap>상품명</td>
			<td width="50%">
				<div style="height:75;padding-top:5">
					영문 <input type="text" name="goodsnm" style="width:95%" value="${goodsVO.goodsObj.goodsnm}" required label="영문 상품명" class="line" id="goodsnm" maxlength="250" placeholder="영문"><br>
					국문 <input type="text" name="goodsnmKR" style="width:95%" value="${goodsVO.goodsObj.goodsnmKR}" required label="국문 상품명" class="line" id="goodsnmKR" maxlength="250" placeholder="국문"><br>
					중문 <input type="text" name="goodsnmCN" style="width:95%" value="${goodsVO.goodsObj.goodsnmCN}" required label="중문 상품명" class="line" id="goodsnmCN" maxlength="250" placeholder="중문">
				</div>
			<div style="height:23"><input type="checkbox" name="meta_title" value="1" class="null" ${'1' eq goodsVO.goodsObj.metatitle? 'checked' : '' }>상품명을 상품상세페이지의 타이틀 태그에 입력됩니다.</div></td>
			<td width="120" nowrap>
				상품코드
			</td>
			<td width="50%">
				<c:choose>
					<c:when test="${goodsVO.mode eq 'register'}">
						<input type="hidden" readonly="readonly" name="goodscd" style="width:100%" value="${goodsVO.goodsObj.goodscd}" class="line" maxlength=20 data-reg="/^[A-Za-z0-9+]*$/" >
						<div>* 상품코드는 자동으로 생성됩니다.</div>
					</c:when>
					<c:otherwise>
						<input type="text" readonly="readonly" name="goodscd" style="width:100%" value="${goodsVO.goodsObj.goodscd}" class="line" maxlength=20 data-reg="/^[A-Za-z0-9+]*$/" >
						<div>* 상품코드는 수정할 수 없습니다.</div>
					</c:otherwise>
				</c:choose>
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
				<font class="small1" color="444444"><a href="brand" target="_blank"><font class="extext_l"><img src="/resources/shop/admin/img/btn_brand_add.gif"></a></font>
			</td>
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
			<td>Bin 코드</td>
			<td><input type="text" name="binCd" style="width:100%" value="${goodsVO.goodsObj.binCd}" class="line" ></td>
			<td>Simn 코드</td>
			<td><input type="text" name="simnCd" style="width:100%" value="${goodsVO.goodsObj.simnCd}" class="line" ></td>
		</tr>
		<tr>
			<td>시즌</td>
			<td><input type="text" name="seasonNm" style="width:100%" value="${goodsVO.goodsObj.seasonNm}" class="line" ></td>
			<td>EU 구분</td>
			<td>
				<input type="radio" name="euYn" value="Y" ${'Y' eq goodsVO.goodsObj.euYn ? 'checked' : '' }> Y
				<input type="radio" name="euYn" value="N" ${'Y' ne goodsVO.goodsObj.euYn ? 'checked' : '' }> N
			</td>
		</tr>
		<tr>
			<td>배송출발국가</td>
			<td><input type="text" name="shippingOrigin" style="width:100%" value="${goodsVO.goodsObj.shippingOrigin}" class="line" ></td>
			<td>SCM 등록일</td>
			<td>${goodsVO.goodsObj.scmRegdt}</td>
		</tr>
		<tr>
			<td>유사검색어</td>
			<td<c:if test="${goodsVO.mode ne 'modify'}"> colspan="3"</c:if>>
				<div style='padding-top:5px'><input type="text" name="keyword" value="${goodsVO.goodsObj.keyword }" style="width:100%" class="line" maxlength=100></div>
				<div style="height:23;padding-top:5px" class="extext">상품상세 페이지의 메타태그와 상품 검색시 키워드로 사용하실 수 있습니다.</font></div>
			</td>
			<c:if test="${goodsVO.mode eq 'modify' }">
				<c:choose>
					<c:when test="${sessionScope.xmall_admin.ADMINUSER.xkey.level >= '99' }">
						<td>게시여부</td>
						<td>
							<input type="checkbox" name="open" value="1"  ${goodsVO.goodsObj.open eq '1' ? 'checked':''}>보이기
							<font class="extext">(체크해제시 화면에서 안보임)</font>
						</td>
					</c:when>
					<c:otherwise>
						<td>게시여부</td>
						<td>
							<c:if test="${goodsVO.goodsObj.open eq '1' }">
								<img src="/resources/shop/admin/img/icn_${goodsVO.goodsObj.open}.gif">
							</c:if>
							<c:if test="${goodsVO.goodsObj.open eq '0' }">
								<img src="/resources/shop/admin/img/icn_${goodsVO.goodsObj.open}.gif">
							</c:if>
						</td>
					</c:otherwise>
				</c:choose>
			</c:if>
		</tr>
	</table>
	--%>

		<!-- TEST -->
		<div class="div-tbl inp-tbl" style="table-layout: fixed;">
			<div class="row">
				<div class="th w-120">상품명</div>
				<div>
					<div>
						<label>
							영문
							<input type="text" name="goodsnm" value="${goodsVO.goodsObj.goodsnm}" required label="영문 상품명" id="goodsnm" maxlength="250" placeholder="영문" style="width: calc(100% - 35px);">
						</label>
					</div>
					<div>
						<label>
							국문 <input type="text" name="goodsnmKR" value="${goodsVO.goodsObj.goodsnmKR}" required label="국문 상품명" id="goodsnmKR" maxlength="250" placeholder="국문" style="width: calc(100% - 35px);">
						</label>
					</div>
					<div class="mg-bottom-5">
						<label>
							중문 <input type="text" name="goodsnmCN" value="${goodsVO.goodsObj.goodsnmCN}" required label="중문 상품명" id="goodsnmCN" maxlength="250" placeholder="중문" style="width: calc(100% - 35px);">
						</label>
					</div>
					<div>
						<label>
							<input type="checkbox" name="meta_title" value="1" class="null" ${'1' eq goodsVO.goodsObj.metatitle? 'checked' : '' }>
							상품명을 상품상세페이지의 타이틀 태그에 입력됩니다.
						</label>
					</div>
				</div>
				<div class="th w-120">상품코드</div>
				<div>
					<c:choose>
						<c:when test="${goodsVO.mode eq 'register'}">
							<input type="hidden" readonly="readonly" name="goodscd" value="${goodsVO.goodsObj.goodscd}" maxlength="20" data-reg="/^[A-Za-z0-9+]*$/" >
							<div>* 상품코드는 자동으로 생성됩니다.</div>
						</c:when>
						<c:otherwise>
							<input type="text" readonly="readonly" name="goodscd" value="${goodsVO.goodsObj.goodscd}" class="mg-bottom-5" maxlength="20" data-reg="/^[A-Za-z0-9+]*$/" >
							<div>* 상품코드는 수정할 수 없습니다.</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>	<!-- END row -->

			<div class="row">
				<div class="th">브랜드</div>
				<div>
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
					<a href="brand" target="_blank" class="admin-btn-blue">브랜드추가하기</a>
				</div>
				<div class="th">원산지</div>
				<div>
					<input type="text" name="origin" value="${goodsVO.goodsObj.origin }" class="line" maxlength=20>
					<select onchange="this.form.origin.value=this.value;this.form.origin.focus()">
						<option value="">::: 목록보기 :::</option>
						<c:if test="${!empty goodsVO.originList && fn:length(goodsVO.originList)>0 }">
							<c:forEach items="${goodsVO.originList }" var="list">
								<!-- 빈값 체크, TODO - select 에서 필터링 -->
								<c:if test="${list.origin != ''}">
									<option value="${list.origin }" >${list.origin }</option>
								</c:if>
							</c:forEach>
						</c:if>
					</select>
				</div>
			</div>	<!-- END row -->

			<div class="row">
				<div class="th">Bin 코드</div>
				<div><input type="text" name="binCd" style="width:100%" value="${goodsVO.goodsObj.binCd}"></div>
				<div class="th">Simn 코드</div>
				<div><input type="text" name="simnCd" style="width:100%" value="${goodsVO.goodsObj.simnCd}"></div>
			</div>	<!-- END row -->

			<div class="row">
				<div class="th">시즌</div>
				<div><input type="text" name="seasonNm" style="width:100%" value="${goodsVO.goodsObj.seasonNm}"></div>
				<div class="th">EU 구분</div>
				<div>
					<label>
						<input type="radio" name="euYn" value="Y" ${'Y' eq goodsVO.goodsObj.euYn ? 'checked' : '' }> Y
					</label>
					<label>
						<input type="radio" name="euYn" value="N" ${'Y' ne goodsVO.goodsObj.euYn ? 'checked' : '' }> N
					</label>
				</div>
			</div>	<!-- END row -->

			<div class="row">
				<div class="th">배송출발국가</div>
				<div><input type="text" name="shippingOrigin" style="width:100%" value="${goodsVO.goodsObj.shippingOrigin}"></div>
				<div class="th">SCM 등록일</div>
				<div>${goodsVO.goodsObj.scmRegdt}</div>
			</div>	<!-- END row -->

			<div class="row">
				<div class="th">유사검색어</div>
				<div<c:if test="${goodsVO.mode ne 'modify'}"> colspan="3"</c:if>>
					<div class="mg-bottom-5"><input type="text" name="keyword" value="${goodsVO.goodsObj.keyword }" style="width:100%" maxlength=100></div>
					<div class="extext">상품상세 페이지의 메타태그와 상품 검색시 키워드로 사용하실 수 있습니다.</div>
				</div>
				<c:if test="${goodsVO.mode eq 'modify' }">
					<c:choose>
						<c:when test="${sessionScope.xmall_admin.ADMINUSER.xkey.level >= '99' }">
							<div class="th">게시여부</div>
							<div>
								<div class="mg-bottom-5">
									<label>
										<input type="checkbox" name="open" value="1"  ${goodsVO.goodsObj.open eq '1' ? 'checked':''}> 보이기
									</label>
								</div>
								<span class="extext">(체크해제시 화면에서 안보임)</span>
							</div>
						</c:when>
						<c:otherwise>
							<div class="th">게시여부</div>
							<div>
								<c:if test="${goodsVO.goodsObj.open eq '1' }">
									<img src="/resources/shop/admin/img/icn_${goodsVO.goodsObj.open}.gif">
								</c:if>
								<c:if test="${goodsVO.goodsObj.open eq '0' }">
									<img src="/resources/shop/admin/img/icn_${goodsVO.goodsObj.open}.gif">
								</c:if>
							</div>
						</c:otherwise>
					</c:choose>
				</c:if>
			</div>	<!-- END row -->

		</div>	<!-- END div-tbl -->
	</div>	<!-- END sub-cont-wrap -->

	<!--
	<div style="padding-top:20px"></div>
	<div style="border-top:3px #efefef solid;"></div>
	-->

	<!-- 상품추가정보 -->
	<c:if test='${codeUtil:getConfValue("rental_free") eq "false" }'>
		<div class="sub-cont-wrap hr">
		<div class="title">상품추가정보
			<span>상품특성에 맞게 항목을 추가할 수 있습니다 (예. 감독, 저자, 출판사, 유통사, 상품영문명 등) </span>
		</div>
		
		<div class="noline" style="padding-bottom:5px">
			<label>
				<input type="radio" name="useEx" value="1" ${'|||||' ne goodsVO.goodsObj.extitle ? 'checked' : ''} onclick="openLayer('tbEx','block')" onfocus="blur()"> 사용
			</label>
			<label>
				<input type="radio" name="useEx" value="0" ${'|||||' eq goodsVO.goodsObj.extitle  ? 'checked' : ''} onclick="openLayer('tbEx','none')" onfocus="blur()"> 사용안함
			</label>
		</div>
			
		<table id="tbEx" class="tb" style="display:${'|||||' ne goodsVO.goodsObj.extitle ? 'block' : 'none'}">
			<col class="cellC"><col class="cellL"><col class="cellC"><col class="cellL">
			<tr>
			<c:set var="exTitleLen" value="${fn:length(goodsVO.goodsObj.extitle) }" />
			<c:set var= "ex_title" value="${fn:split(stringUtil:nullConv(goodsVO.goodsObj.extitle),'|')}"/>
			<c:forEach var="i" begin="0" end="5" step="1">		
				<c:set var="tmpExTitle" value="${exTitleLen >= i ? ex_title[i] : ''}" />
				<td><input type="text" name="title${i+1 }" class="exTitle gray" value="${'' ne tmpExTitle ? tmpExTitle : '' }" maxlength=50></td>
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
		</div>	<!-- END sub-cont-wrap -->
		<!--
		<div style="border-bottom:3px #efefef solid;padding-top:20px"></div>
		-->
	</c:if>

	<!-- 상품적립금 -->
	<div class="sub-cont-wrap">
	<div class="title">적립금<span>이 상품 주문시 적립금의 적립여부를 설정합니다.</span></div>
	<div>
		<label for="use_emoney1"><input type="radio" id="use_emoney1" name="use_emoney" ${'1' eq goodsVO.goodsObj.useemoney ? 'checked' : '' } value="1" onfocus=blur()> 사용</label>
		<label for="use_emoney0"><input type="radio" id="use_emoney0" name="use_emoney" ${'0' eq goodsVO.goodsObj.useemoney ? 'checked' : '' } value="0" onfocus=blur()> 사용안함 </label>
	</div>
<%--	<div style="border-bottom:3px #efefef solid;padding-top:40px"></div>--%>
	</div>	<!-- END sub-cont-wrap -->

	<!-- 상품 가격/재고 -->
	<div class="title">가격/재고/배송비<span>사이즈, 색상 등에 의해 가격이 여러개인 경우 가격옵션을 추가할 수 있습니다</span></div>
	<table>
		<tr>
			<td>리테일가</td>
			<td>
				<input type="text" name="consumer" size="10" value="<fmt:formatNumber value="${goodsVO.consumer}" pattern="0.00"/>" onchange="autoPrice(this)" onblur="autoPrice(this)" onkeydown="autoPrice(this)" class="line consumer"  maxlength=10 data-reg="/^[0-9.]+$/" onkeyup="removeChar(event)" >
			</td>
			<td style="padding-left:10px">즉시할인가</td>
			<td>
				<input type="text" name="price" size="10" value="<fmt:formatNumber value="${goodsVO.price1}" pattern="0.00"/>" onchange="autoPrice(this)" onblur="autoPrice(this)" onkeydown="autoPrice(this)" class="line price"  maxlength=10 data-reg="/^[0-9.]+$/" onkeyup="removeChar(event)" >
			</td>
			<td style="padding-left:10px">즉시할인율</td>
			<td>
				<input type="text" name="priceRate" size="10" value="<fmt:formatNumber value="${goodsVO.priceRate}" pattern="0.00"/>" onchange="autoPrice(this)" onblur="autoPrice(this)" onkeydown="autoPrice(this)" class="line priceRate"  maxlength=10 data-reg="/^[0-9.]+$/" onkeyup="removeChar(event)" >
			</td>
			<td style="padding-left:10px">바잉원가(정책)</td>
			<td>
				<input type="text" name="supply" size="10" value="<fmt:formatNumber value="${goodsVO.supply}" pattern="0.00"/>" onchange="autoPrice(this)" onblur="autoPrice(this)" onkeydown="autoPrice(this)" class="line"  maxlength=10 data-reg="/^[0-9.]+$/" onkeyup="removeChar(event)" >
			</td>
			<td style="padding-left:10px">바잉원가(DATA)</td>
			<td>
				<input type="text" name="supply2" size="10" value="<fmt:formatNumber value="${goodsVO.supply2}" pattern="0.00"/>" onchange="autoPrice(this)" onblur="autoPrice(this)" onkeydown="autoPrice(this)" class="line"  maxlength=10 data-reg="/^[0-9.]+$/" onkeyup="removeChar(event)" >
			</td>
			<td style="padding-left:10px">공헌이익율</td>
			<td>
				<input type="text" name="margin" size="10" value="<fmt:formatNumber value="${goodsVO.margin}" pattern="0.00"/>" onchange="autoPrice(this)" onblur="autoPrice(this)" onkeydown="autoPrice(this)" class="line"  maxlength=10 data-reg="/^[0-9.]+$/" onkeyup="removeChar(event)" >
			</td>
			<td style="padding-left:10px">재고량</td>
			<td>
				<input type="text" name="stock" size="10" value="${goodsVO.intTotalStock}" onchange="autoPrice(this)" onblur="autoPrice(this)" onkeydown="autoPrice(this)" class="line" maxlength=7 onkeyup="removeChar(event)" ${'1' eq goodsVO.goodsObj.useGoodsAdd ? 'readonly' : ''}>
			</td>
			<td style="padding-left:10px">MyRitz공급가</td>
			<td>
				<input type="text" name="priceMyRitz" size="10" value="${goodsVO.priceMyRitz}" class="line" maxlength=10 data-reg="/^[0-9]+$/">
			</td>
		</tr>
	</table>

	<div style="height:5px;"></div>
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
								₩<input type="text" name="goodsDelivery" value="<fmt:formatNumber value="${goodsVO.goodsObj.goodsdelivery}" pattern="0.00"/>" size="8" onkeyup="removeChar(event)">
							</span>	&nbsp;
							<input type="radio" name="deliveryType" value="3" ${3 eq goodsVO.goodsObj.deliverytype ? 'checked' : '' } class="null" onclick="chk_delivery_type();">착불배송비
							<span style="display:none;" id="gdi2">&nbsp;₩<input type="text" name="goodsDelivery2" value="<fmt:formatNumber value="${goodsVO.goodsObj.goodsdelivery}" pattern="0.00"/>" size="8" onkeyup="removeChar(event)"></span>
						</td>		
					</tr>
				</table>
				<div>
					<font class="extext">기본배송정책과 상품별 배송비 정책은 <a href="../basic/delivery" target="_blank"><font class="extext_l">[기본관리 > 배송/택배사 설정]</font></a> 에서 관리 하실 수 있습니다.</font>
				</div>
			</td>
		</tr>
	</table>

	<div class="title">
		상품옵션 <span>이상품의 옵션이 여러개인경우 등록하세요 (색상, 사이즈 등)</span>
	</div>
	<div class="noline" style="padding-bottom: 5px">
		<input type="radio" name="usegoodsadd" value="1" ${'1' eq goodsVO.goodsObj.useGoodsAdd ? 'checked' : ''} 
			onclick="vOption('block')" onfocus="blur()"> 사용 
		<input type="radio" name="usegoodsadd" value="0" ${'0' eq goodsVO.goodsObj.useGoodsAdd || empty goodsVO.goodsObj.useGoodsAdd ? 'checked' : ''}
			onclick="vOption('none')" onfocus="blur()"> 사용안함 
	</div>
	<div id="objOption">
		<font class="small" color="black"><b>옵션명1</b></font> : 
		<input type="text" name="optnm" value="">
		
		<a href="javascript:addopt1()" onfocus=blur()>
			<img src="/resources/shop/admin/img/i_add.gif" align="absmiddle">
		</a> 
		<a href="javascript:delopt1()" onfocus=blur()>
			<img src="/resources/shop/admin/img/i_del.gif" align="absmiddle">
		</a>
		
		<font class="small" color="black"><b>옵션명2</b></font> : 
		<input type="text" name="optnm" value="">
		
		<a href="javascript:addopt2()" onfocus='blur()'>
			<img src="/resources/shop/admin/img/i_add.gif" align="absmiddle">
			</a> 
		<a href="javascript:delopt2()" onfocus='blur()'>
			<img src="/resources/shop/admin/img/i_del.gif" align="absmiddle">
		</a>
		
		<span class="noline">
			<b>옵션출력방식</b> :
			<input type="radio" name="opttype" value="single" ${'single' eq goodsVO.goodsObj.opttype ? 'checked' : '' } onfocus=blur()> 일체형
			<input type="radio" name="opttype" value="double" ${'double' eq goodsVO.goodsObj.opttype ? 'checked' : '' } onfocus=blur()> 분리형
		</span>
	</div>
		<table id="tbOption" border="1" bordercolor="#cccccc" style="display:${goodsVO.goodsObj.useGoodsAdd eq '1' ? 'block' : 'none'}">
			<col class="cellC">
			<col class="cellL">
			<col class="cellC">
			<col class="cellL">
			<tr align="center">
				<td width="116">옵션명</td>
				<td>리테일가</td>
				<td>즉시할인가</td>
				<td>즉시할인율</td>
				<td>바잉원가(정책)</td>
				<td>바잉원가(DATA)</td>
				<td>공헌이익율</td>
				<%-- <td>적립금</td> --%>
				<td>상품설명</td>
				<td>재고량</td>
				<c:if test="${null ne goodsVO.goodsObj.optnm}">
					<%--<c:set var="opt2NmArray" value="${fn:split(stringUtil:nullConv(goodsVO.goodsObj.optnm), '|')}"/>
						<c:forEach var="opt2nm" items="${opt2NmArray}" varStatus="st">
							<td><input type=text class='opt gray' name=opt2[] value="${opt2nm}"  label='2차옵션명'></td>
						</c:forEach>
					--%>
					<c:forEach var="opt2nm" items="${goodsVO.optNmArray}" varStatus="st">
							<td><input type=text class='opt gray' name=opt2[] value="${opt2nm}"  label='2차옵션명'></td>
					</c:forEach>
				</c:if>
			</tr>
			<tbody id="tbOptionBody">
				<c:if test='${goodsVO.goodsOptionList ne null}'>
					<c:forEach var="optList" items="${goodsVO.goodsOptionList}" varStatus="status">
						<tr>
							<td><input type=text name=opt1[] class='opt gray' value="${optList.opt1}"  label='1차옵션명' ></td>
							<td><input type=text name=option[consumer][] class='opt gray consumer' onkeyup="removeChar(event)" maxlength=10 value="<fmt:formatNumber value="${optList.consumer}" pattern="0.00"/>" ></td>
							<td><input type=text name=option[price][] class='opt gray price' onkeyup="removeChar(event)" maxlength=10 value="<fmt:formatNumber value="${optList.price}" pattern="0.00"/>" ></td>
							<td><input type=text name=option[priceRate][] class='opt gray priceRate' onkeyup="removeChar(event)" maxlength=10 value="<fmt:formatNumber value="${optList.priceRate}" pattern="0.00"/>" ></td>
							<td><input type=text name=option[supply][] class='opt gray' onkeyup="removeChar(event)" maxlength=10 value="<fmt:formatNumber value="${optList.supply}" pattern="0.00"/>" ></td>
							<td><input type=text name=option[supply2][] class='opt gray' onkeyup="removeChar(event)" maxlength=10 value="<fmt:formatNumber value="${optList.supply2}" pattern="0.00"/>" ></td>
							<td><input type=text name=option[margin][] class='opt gray' onkeyup="removeChar(event)" maxlength=10 value="<fmt:formatNumber value="${optList.margin}" pattern="0.00"/>" ></td>
							<td><input type=text name=option[optexplain][] class='opt gray' value="${optList.optexplain}" ></td>
							<td><input type=text name=option[opt1Stock][] class='opt gray' onkeydown='onlynumber(event)' maxlength=10 value="${optList.stock}" onchange="changeStock()">
							<input type=hidden name=option[priceMyRitz][] class='opt gray' maxlength=10 value="${optList.priceMyRitz}">
							<input type=hidden name=option[optno][]></td>
							<c:if test="${optList.stocks != null }">
								<c:forEach var="stocks" items="${optList.stocks}" varStatus="st">
									<td><input type=text name=option[stock][] class='opt gray' onkeydown='onlynumber(event)' maxlength=10 value="${stocks}" onchange="changeStock()"></td>
								</c:forEach>
							</c:if>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>	
			
		</table>



<!-- 관련상품 -->
	<div class="title">관련상품<span>이상품과 관련있는 상품을 추천하세요 </span>
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
	<div class="title">상품 이미지<span>아래 자동리사이즈 되는 기능을 활용하면 더욱 편리합니다. </span>
	</div>

	<div style="padding:0 0 10 14">처음 상품이미지를 등록하신다면, 반드시 
		<a href="imgsize" target=_blank><img src="/resources/shop/admin/img/i_imgsize.gif" border="0" align="absmiddle"></a> 먼저 설정하세요!&nbsp;&nbsp;
	</div>

	<table class="tb">
		<col class="cellC"><col class="cellL">
		<c:if test='${null ne goodsVO.imgsArray}'>
			<c:forEach var="i" begin="0" end="3" step="1">
				<tr>
					<td>
						${goodsVO.strImg[i]}
						<%-- <c:if test='${"l" ne goodsVO.imgsArray[i][0]}'>
							<div class="noline" style="font:11px dotum;letter-spacing:-1px;"><input type="checkbox" name="copy_${i}"> <font class="extext"><b>자동리사이즈 사용</b></font></div>
						</c:if> --%>
						<!-- 2017-08-30 이미지 설명 추가 -->
						<c:if test="${i==0 }">
							<br/><font class="extext">상품리스트의 마우스오버시 노출되는 이미지</font>
						</c:if>
					</td>
					<td>
						<input type="hidden" name="orgNames_img_${goodsVO.imgsArray[i][0]}" value="${goodsVO.imgsArray[i][1]}"/> 
						<table id="tb_${goodsVO.imgsArray[i][0]}">
							<col valign="top">
							<col valign="top">
							<col valign="top">
							<c:set var= "tmpArray" value="${fn:split(stringUtil:nullConv(goodsVO.imgsArray[i][1]),'|')}"/>
							<c:if test='${0 eq fn:length(tmpArray)}'>
								<tr>
									<td>
										<!-- 확대이미지, 상세이미지 일경우 추가버튼 출력 -->
										<c:choose>
											<c:when test='${("l" eq goodsVO.imgsArray[i][0] || "m" eq goodsVO.imgsArray[i][0]) }'>
												<a href="javascript:addfld('tb_${goodsVO.imgsArray[i][0]}')"><img src="/resources/shop/admin/img/i_add.gif" align="absmiddle"></a>
											</c:when>
											<c:otherwise>
												<span style="display: inline-block; width: 36px;"></span>
											</c:otherwise>
										</c:choose>
										<%-- <span><input type="file" name="img_${goodsVO.imgsArray[i][0]}[]0"  style="width:300px" onChange="preview(this)" accept=".gif, .jpg, .png, .jpge, .bmp" ></span> --%>
										<span><input type="file" name="img_${goodsVO.imgsArray[i][0]}[]0"  style="width:300px" accept=".gif, .jpg, .png, .jpge, .bmp" ></span>
									</td>
									<td></td>
									<td></td>
								</tr>
							</c:if>
							<c:forEach var="tmpArray" items="${tmpArray}" varStatus="g">
								<tr>
									<td>
										<!-- 확대이미지, 상세이미지 일경우 추가버튼 출력 -->
										<c:choose>
											<%-- 상세 이미지만 추가버튼 출력되게 수정 확대 이미지는 하나만 사용해서 추가가 필요없음 --%>
											<%-- <c:when test='${("l" eq goodsVO.imgsArray[i][0] || "m" eq goodsVO.imgsArray[i][0]) && 0 == g.index}'> --%>
											<c:when test='${("m" eq goodsVO.imgsArray[i][0]) && 0 == g.index}'>
												<a href="javascript:addfld('tb_${goodsVO.imgsArray[i][0]}')"><img src="/resources/shop/admin/img/i_add.gif" align="absmiddle"></a>
											</c:when>
											<c:otherwise>
												<span style="display: inline-block; width: 36px;"></span>
											</c:otherwise>
										</c:choose>
										<span><input type="file" name="img_${goodsVO.imgsArray[i][0]}[]${g.index}"  id="img_${goodsVO.imgsArray[i][0]}${g.index}" " style="width:300px" accept=".gif, .jpg, .png, .jpge, .bmp" ></span>
									</td>
									<td>
										<c:if test='${"" ne tmpArray}'>
											<%-- <div style="padding:0 0" class=noline><input type=checkbox name="del[img_${goodsVO.imgsArray[i][0]}][]${g.index}" value="${tmpArray}"><font class="small" color="#585858">삭제 (${tmpArray})</font></div> --%>
											<div style="padding-right: 5px;" class="noline">
												<input type=checkbox id="del_img_${goodsVO.imgsArray[i][0]}${g.index}" name="del[img_${goodsVO.imgsArray[i][0]}][]${g.index}" value="${tmpArray}">
												<font class="small" color="#585858" style="word-break: break-all;">삭제 (${tmpArray})</font>
											</div>
										</c:if>
									</td>
									<td>
										<c:if test='${"" ne tmpArray}'>
											<%-- <c:set var='tmpStr1' value='${fn:substring(tmpArray,fn:indexOf(tmpArray,"]")+1,fn:length(tmpArray))}'/> --%>
											<c:set var='tmpStr1' value='${tmpArray}'/>
											<c:set var="tempStyle1" value="style='border:1 solid #cccccc' onclick=popupImg('"/>
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

		<!-- 상품고시를 사용하지않아 주석처리 -->
		<!-- 상품 고시 -->
		<!-- <div class="title">상품 고시</div>
			
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
		</div> -->
			
		<div style="height:7px"></div>
			
		<!-- 사용하는곳이 없는 항목 -->
		<!-- <div class="noline" style="padding-bottom:5px">
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
				<c:when test="${!empty sellerGoodsFM.goodsNotiList}">
					<c:forEach var="goodsNoti" items="${sellerGoodsFM.goodsNotiList}" varStatus="varStat">
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
	
		<div style="border-bottom:3px #efefef solid;padding-top:30px"></div> -->
		
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
			<c:set var='returnUrl' value='${ctx}/shop/admin/goods/list?pageNo=${goodsVO.pageNo}&pageSize=${goodsVO.pageSize}&sort=${goodsVO.sort}&schRegty=${goodsVO.schRegty}&schRegdt=${goodsVO.schRegdt[0]}&schRegdt=${goodsVO.schRegdt[1]}&schSellerNm=${goodsVO.schSellerNm}&schSellerCd=${goodsVO.schSellerCd}&cate=${goodsVO.cate[0]}&cate=${goodsVO.cate[1]}&cate=${goodsVO.cate[2]}&cate=${goodsVO.cate[3]}&schBrand=${goodsVO.schBrand}&schSeasonNm=${goodsVO.schSeasonNm}&schKey=${goodsVO.schKey}&schWord=${goodsVO.schWord}&schOpen=${goodsVO.schOpen}&schSoldOut=${goodsVO.schSoldOut}&schSpec=${goodsVO.schSpec}&schManageYn=${goodsVO.schManageYn}'/>
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
	<script>
		var optCheck = $("input:radio[name='usegoodsadd']:checked").val();
		if(Number(optCheck) == 1){
			$("#objOption").show();	
		}else{
			$("#objOption").hide();
		}
	</script>


	