<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %> 

<%
	String[] strImgTitArr = {"원본(확대)이미지", "메인노출이미지", "리스트이미지", "상세이미지"};
	String[] strImgArr = {"l", "i", "s", "m"};
	
	request.setAttribute("strImgTitArr", strImgTitArr);
	request.setAttribute("strImgArr", strImgArr);
%>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<!-- 웹에디터 활성화 스크립트 -->
<script src="/resources/shop/lib/meditor/mini_editor.js"></script>
<script language="javascript">	
	var goodsNotiList = null;
	
	$(function(){
		//상품옵션 사용시 stock 계산
		if($("input:radio[name=usegoodsadd]:checked").val() == 1){
			changeStock();
		}
		
		mini_editor("/resources/shop/lib/meditor/");
		
		chk_delivery_type();
		
		if( typeof(call_file_disabled) != "undefined" ){
			file_disabled();
		}
		
		// 상품고시 조회
		getGoodsNotiList();
		
		// 상품고시 all check
		$("#allGoodsNotiChk").on("change", function(){
			$("#tblGoodsNoti input:checkbox:not(#allGoodsNotiChk)").each(function(){
				$(this).prop("checked", $("#allGoodsNotiChk").is(":checked"));
			});
		});
	});
	
	// form validation
	function checkGoodsForm(){
		//validation 체크 추가

 		for(var i=0; i<$('input[type="text"]').length; i++){
 			if($('input[type="text"]')[i].dataset.reg != null && $('input[type="text"]')[i].dataset.reg != "" && $('input[type="text"]')[i].value != "") {
 				if(! (chkPatten($('input[type="text"]')[i], $('input[type="text"]')[i].dataset.reg)) ){
 					return false;
 				}
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
	     
    	 if($("#objCategory").find("tbody").length < 1){
 			alert("카테고리를 등록해주세요");
 			document.getElementsByName("cate[]")[0].focus();
 			return false;
 		}

	    
		
	     if(!$("#goodsnm").val()){
	    	 alert("상품의 이름을 입력해세요.");
	    	 $("#goodsnm").focus();
	    	 return false;
	     }
	     
	     if(!$("input[name=sellerGoodsViewVO\\.price]").val())
	     {
	    	 alert("판매가를 입력해세요.");
	    	 $("input[name=sellerGoodsViewVO\\.price]").focus();
	    	 return false;
	     }
	     if(!$("input[name=sellerGoodsViewVO\\.consumer]").val())
	     {
	    	 alert("정가를 입력해세요.");
	    	 $("input[name=sellerGoodsViewVO\\.consumer]").focus();
	    	 return false;
	     }
	     if(!$("input[name=sellerGoodsViewVO\\.supply]").val())
	     {
	    	 alert("매입가를 입력해세요.");
	    	 $("input[name=sellerGoodsViewVO\\.supply]").focus();
	    	 return false;
	     }
	     if(!$("input[name=sellerGoodsViewVO\\.stock]").val())
	     {
	    	 alert("재고량 입력해세요.");
	    	 $("input[name=sellerGoodsViewVO\\.stock]").focus();
	    	 return false;
	     }
	     
	     
	     if($("input:radio[name=useadd]:checked").val() == '1' ){
	    	 for(var i = 0; i < $("input[name=addoptnms]").length; i++){
	    		 if(!$("input[name=addoptnms]").eq(i).val()){
	    			 alert("옵션명을 입력해주세요");
	    			 $("input[name=addoptnms]").eq(i).focus();
	    			 return false;
	    		 }
	    	 }
	    	 

	     }
		 document.fm.submit(); 
	}
	
	// form validation
	function reqApprovalMod(){
		//validation 체크 추가

 		for(var i=0; i<$('input[type="text"]').length; i++){
 			if($('input[type="text"]')[i].dataset.reg != null && $('input[type="text"]')[i].dataset.reg != "" && $('input[type="text"]')[i].value != "") {
 				if(! (chkPatten($('input[type="text"]')[i], $('input[type="text"]')[i].dataset.reg)) ){
 					return false;
 				}
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
	     
    	 if($("#objCategory").find("tbody").length < 1){
 			alert("카테고리를 등록해주세요");
 			document.getElementsByName("cate[]")[0].focus();
 			return false;
 		}

	    
		
	     if(!$("#goodsnm").val()){
	    	 alert("상품의 이름을 입력해세요.");
	    	 $("#goodsnm").focus();
	    	 return false;
	     }
	     
	     if(!$("input[name=sellerGoodsViewVO\\.price]").val())
	     {
	    	 alert("판매가를 입력해세요.");
	    	 $("input[name=sellerGoodsViewVO\\.price]").focus();
	    	 return false;
	     }
	     if(!$("input[name=sellerGoodsViewVO\\.consumer]").val())
	     {
	    	 alert("정가를 입력해세요.");
	    	 $("input[name=sellerGoodsViewVO\\.consumer]").focus();
	    	 return false;
	     }
	     if(!$("input[name=sellerGoodsViewVO\\.supply]").val())
	     {
	    	 alert("매입가를 입력해세요.");
	    	 $("input[name=sellerGoodsViewVO\\.supply]").focus();
	    	 return false;
	     }
	     if(!$("input[name=sellerGoodsViewVO\\.stock]").val())
	     {
	    	 alert("재고량 입력해세요.");
	    	 $("input[name=sellerGoodsViewVO\\.stock]").focus();
	    	 return false;
	     }
	     
	     
	     if($("input:radio[name=useadd]:checked").val() == '1' ){
	    	 for(var i = 0; i < $("input[name=addoptnms]").length; i++){
	    		 if(!$("input[name=addoptnms]").eq(i).val()){
	    			 alert("옵션명을 입력해주세요");
	    			 $("input[name=addoptnms]").eq(i).focus();
	    			 return false;
	    		 }
	    	 }
	    	 

	     }
	    document.fm.action = ctx+"/shop/seller/goods/modifyApprovalReq";
		document.fm.submit();
	}
	
	// form validation
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
		/* if (!chkOption()) return false;
		if (!chkForm(obj)) return false; */
	}

	// 상품분류 추가하기
	function exec_add(){
		var ret = "";
		var str = new Array();
		var obj = document.forms[0]['cate[]'];
		
		// 카테고리  select
		obj = document.forms[0]['cate[]'];
		
		for(i = 0 ; i <obj.length ; i++){
			if(obj[i].value){
				str[str.length] = obj[i][obj[i].selectedIndex].text;
				ret = obj[i].value;
			}
		}
		
		if(!ret){
			alert('카테고리를 선택해주세요');
			return false;
		}
		
		for( i = 0 ; i < $('.category').length ; i++){
			if( $('.category')[i].value == ret ) {
				alert('이미 추가된 카테고리입니다');
				return false;
			}
		}
		
		// 카테고리 table
		obj = document.getElementById('objCategory');
		
		oTr = obj.insertRow(obj.rows.length);
		
		oTd = oTr.insertCell(0);
		oTd.innerHTML = str.join(" > ");
		
		oTd = oTr.insertCell(1);
		oTd.innerHTML = "<input type=text name=category[] class=category value='" + ret + "' style='display:none'>"
			+ "<input type=text name=sort[] value='' class='sortBox right' maxlength=10 style='display:none'>";
			
		oTd = oTr.insertCell(2);
		oTd.innerHTML = "<a href='javascript:void(0)' onClick='cate_del(this.parentNode.parentNode)'><img src='/resources/shop/admin/img/i_del.gif' align=absmiddle></a>";	
	}
	
	// 상품분류 삭제하기
	function cate_del(el){
		idx = el.rowIndex;
		var obj = document.getElementById('objCategory');
		obj.deleteRow(idx);
	}
	
	// TODO] 상품 이미지 팝업으로 보기 
	function popupImg(src,base){
		if (!src) src = encodeURIComponent(this.src);
		if (!base) base = "";
		window.open(src,'','width=1,height=1');
	}
	
	/*** 상품 이미지 ***/
	function preview(obj){
		var tmp = obj.parentNode.parentNode.parentNode.childNodes[2];
		tmp.innerHTML = "<tr><td><img src='" + obj.value + "' width=20 onload='if(this.height>this.width){this.height=20}' style='border:1 solid #cccccc' onclick=popupImg(this.src,'../') class=hand></td></tr>";
	}
	
	// 상품 이미지 추가하기
	function addfld(obj){
		var tb = document.getElementById(obj);
		var src = tb.rows[0].cells[0].getElementsByTagName('span')[0].innerHTML;
		
		oTr = tb.insertRow(tb.rows.length);
		oTd = oTr.insertCell(0);
		src = src.replace("[]0", "[]" + (tb.rows.length-1));
		oTd.innerHTML = "<a href='javascript:void(0)' onClick='delfld(this)'><img src='/resources/shop/admin/img/i_del.gif' align=absmiddle></a>	<span>" + src + "</span>";
		oTd = oTr.insertCell(1);
		oTd = oTr.insertCell(2);
		
	}
	
	// 상품 이미지 삭제하기
	function delfld(obj){
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
	
	// 추가옵션 항목 추가하기
	function add_addopt(){
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
			<td>판매금액에 <input type=text name=addopt[addprice][" + (oTr.rowIndex-1) + "][] size=9> 원 추가</td>\
		</tr>\
		</table>\
		";
		oTd = oTr.insertCell(2);
		oTd.className = "noline";
		oTd.align="center";
		oTd.innerHTML = "<input type=checkbox name=addoptreq[" + (oTr.rowIndex-1) + "] value=o>";
	}
	
	// 추가옵션 삭제하기
	function del_addopt(){
		var tbOption = document.getElementById('tbAdd');
		if (tbOption.rows.length>1) tbOption.deleteRow(tbAdd.rows.length-1);
	}
	
	// 추가옵션 서브항목 추가하기
	function add_subadd(obj){
		var idx = obj.parentNode.parentNode.rowIndex - 1;
		//obj = obj.parentNode.parentNode.childNodes(1).getElementsByTagName('table')[0];
		var table = $(obj).parent().siblings()[0].children[0];
		var tr = $("<tr></tr>");
		var td = $("<td></td>");
		td.html("<input type=text name=addopt[opt][" + idx + "][] style='width:270px' maxlength=20> 선택시");
		tr.append(td);
		td = $("<td></td>");
		td.html("판매금액에 <input type=text name=addopt[addprice][" + idx + "][] size=9 maxlength=10> 원 추가");
		tr.append(td);
		$(table).append(tr); 
	}
	
	// 배송비 타입 ["상품별 배송비 입력", "착불배송비"] 선택시 인풋박스 display
	function chk_delivery_type(){
		var obj = document.getElementsByName('deliveryType');
		
		if(obj[2].checked == true){
			document.getElementById('gdi').style.display="inline";
		}else{
			 document.getElementById('gdi').style.display="none";
		}

		if(obj[3].checked == true){
			document.getElementById('gdi2').style.display="inline";
		} else {
			document.getElementById('gdi2').style.display="none";
		}
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
				case 0: oTd.innerHTML = "<input type=text class='opt gray' name=opt1[] value='"+addOptVal+"' required label='1차옵션명'>";
				break;
				case 1:	oTd.innerHTML = "<input type=text name=option[price][] class='opt gray' onkeydown='onlynumber(event)' maxlength=10 value=''>"; break;
				case 2:	oTd.innerHTML = "<input type=text name=option[consumer][] class='opt gray' onkeydown='onlynumber(event)' maxlength=10 value=''>"; break;
				case 3:	oTd.innerHTML = "<input type=text name=option[supply][] class='opt gray' onkeydown='onlynumber(event)' maxlength=10 value=''>"; break;
				case 4:	oTd.innerHTML = "<input type=text name=option[optexplain][] class='opt gray' value=''>"; break;
				case 5: oTd.innerHTML = "<input type=text name=option[opt1Stock][] class='opt gray' onkeydown='onlynumber(event)' maxlength=10 value='' placeholder='재고량입력' onchange='changeStock()' >"; break;
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
	
	function react_goods(name){
		var tmp = new Array();
		var obj = document.getElementById('tb_'+name);
		for (var i=0;i<obj.rows.length;i++){
			tmp[tmp.length] = "<div style='float:left;width:50;border:1 solid #cccccc;margin:1px;' title='" + obj.rows[i].cells[1].getElementsByTagName('div')[0].innerText + "'>" + obj.rows[i].cells[0].innerHTML + "</div>";
		} 
		document.getElementById(name+'X').innerHTML = tmp.join("") + "<div style='clear:both'>";
	}
	
	
	
	/*** 상품 카테고리 선택 ***/
	var idxCategory;
	var preCurrposSel;

	function cate_mod(obj,el){
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
	function delopt1part(rid){
		var obj = document.getElementById(rid);
		var tbOption = document.getElementById('tbOption');
		if (tbOption.rows.length>2) tbOption.deleteRow(obj.rowIndex);
	}
	
	function delopt2part(cid){
		var obj = document.getElementById(cid);
		var tbOption = document.getElementById('tbOption');

		if (tbOption.rows[0].cells.length<7) return;
		for (i=0;i<tbOption.rows.length;i++){
			tbOption.rows[i].deleteCell(obj.cellIndex);
		}
	}
	
	/*** 자동으로 가격필드에 입력값 저장 ***/
	function autoPrice(obj){
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
	
	function open_box(name,isopen){
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
	
	function list_goods(name){
		var category = '';
		open_box(name,true);
		var els = document.forms[0][name+'[]'];
		for (var i=0;i<els.length;i++) if (els[i].value) category = els[i].value;
		var ifrm = $("#ifrm_" + name);
		var goodsnm = eval("document.forms[0].search_" + name + ".value");
		ifrm.attr("src", "iframeList?popView=Y&name=" + name + "&schCategory=" + category + "&schKey=goodsnm&schWord=" + goodsnm);
	}
	
	function go_list_goods(name){
		if (event.keyCode==13){
			list_goods(name);
			return false;
		}
	}
	
	function view_goods(name){
		open_box(name,false);
	}
	
	function remove_goods(name,obj){
		var tb = document.getElementById('tb_'+name);
		tb.deleteRow(obj.rowIndex);
		react_goods(name);
		
		if( 'refer' == name){
			$("#relationCnt", parent.document).text($("#tb_"+name+" tr").length);
		}
		
	}
	
	
	function spoit(name,obj){
		nameObj = name;
		iciRow = obj;
		iciHighlight();
	}
	
	function iciHighlight(){
		if (preRow) preRow.style.backgroundColor = "";
		iciRow.style.backgroundColor = "#FFF4E6";
		preRow = iciRow;
	}
	
	function vOption(mode)
	{
		if(mode == 'block'){
			openLayer('tbOption','block')
			$("#objOption").show();
			changeStock();
		}else{
			openLayer('tbOption','none')
			$("input[name=stock]").val("${goodsVO.intTotalStock}");
			$("#objOption").hide();
		}
		//document.fm.sellerGoodsViewVO.stock.disabled = !document.fm.stock.disabled;
	}

	
	function keydnTree(){
		if (iciRow==null) return;
		switch (event.keyCode){
			case 38: moveTree(-1); break;
			case 40: moveTree(1); break;
		}
		return false;
	}
	

	function moveTree(idx){
		if (document.getElementById("obj_"+nameObj).style.display!="block") return;
		var objTop = iciRow.parentNode.parentNode;
		var nextPos = iciRow.rowIndex+idx;
		if (nextPos==objTop.rows.length) nextPos = 0;
		objTop.moveRow(iciRow.rowIndex,nextPos);
		react_goods(nameObj);
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
 		oTd.innerHTML = "<input type='text' style='width: 100%' label='항목 "+(tb.rows.length-1)+"' name='goodsNotiList["+(tb.rows.length-2)+"].item' value='"+( null != obj ? obj.item : "")+"' />";
 		
 		// 내용
 		oTd = oTr.insertCell(3);
 		oTd.innerHTML = "<textarea style='width:100%;height:40px;' class='__tline' label='내용"+(tb.rows.length-1)+"' name='goodsNotiList["+(tb.rows.length-2)+"].memo' >"+(null != obj ? obj.memo : "")+"</textarea>";
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
			ajaxCallJson("/shop/seller/goods/notiRegister.dojson"
					, paramObj
					, function(result){
						alert("상품고시정보가 등록이 완료되었습니다.");
						getGoodsNotiList();
					});
		}
		
	}
	
	// 상품고시 리스트 조회
	function getGoodsNotiList(){
		ajaxCallJson("/shop/seller/goods/notiList.dojson"
				, {}
				, function(result){
					var i = 0;
					
					goodsNotiList = result.sellerGoodsFM.goodsNotiList;
					
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
	
	// 등록 신청 
	function reqApprovalReg(){
		//validation 체크 추가

 		for(var i=0; i<$('input[type="text"]').length; i++){
 			if($('input[type="text"]')[i].dataset.reg != null && $('input[type="text"]')[i].dataset.reg != "" && $('input[type="text"]')[i].value != "") {
 				if(! (chkPatten($('input[type="text"]')[i], $('input[type="text"]')[i].dataset.reg)) ){
 					return false;
 				}
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
	     
    	 if($("#objCategory").find("tbody").length < 1){
 			alert("카테고리를 등록해주세요");
 			document.getElementsByName("cate[]")[0].focus();
 			return false;
 		}

	    
		
	     if(!$("#goodsnm").val()){
	    	 alert("상품의 이름을 입력해세요.");
	    	 $("#goodsnm").focus();
	    	 return false;
	     }
	     
	     if(!$("input[name=sellerGoodsViewVO\\.price]").val())
	     {
	    	 alert("판매가를 입력해세요.");
	    	 $("input[name=sellerGoodsViewVO\\.price]").focus();
	    	 return false;
	     }
	     if(!$("input[name=sellerGoodsViewVO\\.consumer]").val())
	     {
	    	 alert("정가를 입력해세요.");
	    	 $("input[name=sellerGoodsViewVO\\.consumer]").focus();
	    	 return false;
	     }
	     if(!$("input[name=sellerGoodsViewVO\\.supply]").val())
	     {
	    	 alert("매입가를 입력해세요.");
	    	 $("input[name=sellerGoodsViewVO\\.supply]").focus();
	    	 return false;
	     }
	     if(!$("input[name=sellerGoodsViewVO\\.stock]").val())
	     {
	    	 alert("재고량 입력해세요.");
	    	 $("input[name=sellerGoodsViewVO\\.stock]").focus();
	    	 return false;
	     }
	     
	     
	     if($("input:radio[name=useadd]:checked").val() == '1' ){
	    	 for(var i = 0; i < $("input[name=addoptnms]").length; i++){
	    		 if(!$("input[name=addoptnms]").eq(i).val()){
	    			 alert("옵션명을 입력해주세요");
	    			 $("input[name=addoptnms]").eq(i).focus();
	    			 return false;
	    		 }
	    	 }
	    	 

	     }
		
		
		if(confirm("등록 신청 하시겠습니까?")){
			ajaxCallJson("/shop/seller/goods/registerApprovalReq.dojson"
					, {reqsno : '${sellerGoodsFM.sellerGoodsViewVO.reqsno}'}
					, function(result){
						alert("등록 신청이 완료되었습니다.");
						window.location.href=ctx+'/shop/seller/goods/registerApprovalList';
					});
		}
	}
	
	// 수정 신청 
	/* function reqApprovalMod(){
		if(confirm("수정 신청 하시겠습니까?")){
			//validation 체크 추가
	 		for(var i=0; i<$('input[type="text"]').length; i++){
	 			if($('input[type="text"]')[i].dataset.reg != null && $('input[type="text"]')[i].dataset.reg != "" && $('input[type="text"]')[i].value != "") {
	 				if(! (chkPatten($('input[type="text"]')[i], $('input[type="text"]')[i].dataset.reg)) ){
	 					return false;
	 				}
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
			
			document.fm.action = "/shop/seller/goods/modifyApprovalReq";
			document.fm.submit();
		}
	} */
	
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
		$("input[name=sellerGoodsViewVO\\.stock]").val(stockSum);
		
	}
	
</script>


<form name="fm" method="post" enctype="multipart/form-data" onsubmit="return chkForm2(this)" id="fm" action="indb" >
	<input type="hidden" name="procType" value="${sellerGoodsFM.procType}" />
	<input type="hidden" name="goodsno" value="${sellerGoodsFM.sellerGoodsViewVO.goodsno}" />
	<input type="hidden" name="reqsno" value="${sellerGoodsFM.sellerGoodsViewVO.reqsno}" />
	<input type="hidden" name="idx" value="0" />
	<input type="hidden" name="category" value="" />
	
	
	
		<div style="padding:8px 13px;background:#f7f7f7;border:3px solid #C6C6C6;margin-bottom:18px;" id="goodsInfoBox">
			<div>
				<c:choose>
					<c:when test="${ 0 < sellerGoodsFM.reqsno }">
						<font class=def>임시번호:</font> <span style="color:#FF7200;font:bold 14px verdana">${sellerGoodsFM.reqsno}</span>
					</c:when>
					<c:when test="${ 0 < sellerGoodsFM.goodsno }">
						<font class=def>고유번호:</font> <span style="color:#FF7200;font:bold 14px verdana">${sellerGoodsFM.goodsno}</span>
					</c:when>
				</c:choose>
			</div>
		</div>
	
	<!-- 상품 카테고리 선택 -->
	<div class="title title_top">
		상품분류정보<span>한상품에 여러개의 분류를 등록할 수 있습니다&nbsp;(다중분류기능지원)</span> 
	</div>
		
 	<div class="box" style="padding-left:3">
		<table width="790" cellpadding="0" cellspacing="1" border="1" bordercolor="#cccccc" style="border-collapse:collapse" >
			<tr>
				<td style="padding:7 7 7 10" bgcolor=f8f8f8>
					<table width="100%" cellpadding="0" cellspacing="1" id="objCategory">
						<col>
						<col width="50" style="padding-right:10">
						<col width="52" align="right">
					
					<!-- 수정일 경우 카테고리 목록 출력 -->	
					<c:if test="${!empty sellerGoodsFM.categoryList && fn:length(sellerGoodsFM.categoryList) > 0 }">
						<c:forEach items="${sellerGoodsFM.categoryList }" var="list">
							<tr>
								<td>${list.categorynm }</td>
								<td>
									<input type="text" name="category[]" value="${list.category}" class="category" style="display:none">
									<input type="text" name="sort[]" value="${list.sort}" class="sortBox right" maxlength="10" style='display:none'>
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
								<a href="javascript:exec_add();"><img src="/resources/shop/admin/img/i_regist_l.gif" vspace="4"></a><br>	
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
		
		<!-- 상품기본정보 -->
		<div class="title">상품기본정보<span>제조사, 원산지, 브랜드가 없는 경우 입력안해도 됩니다 </div>
		<table class="tb">
			<col class="cellC">
			<col class="cellL">
			<col class="cellC">
			<col class="cellL">
			<tr>
				<td width="120" nowrap>상품명</td>
				<td width="50%">
					<div style="height:25;padding-top:5">
						<input type="text" name="goodsnm" style="width:100%" value="${sellerGoodsFM.sellerGoodsViewVO.goodsnm}" required label="상품명" class="line" id="goodsnm" maxlength=50 >
					</div>
					<div style="height:23"><input type="checkbox" name="meta_title" value="1" class="null" ${'1' eq sellerGoodsFM.sellerGoodsViewVO.metatitle? 'checked="checked"' : '' }> 상품명을 상품상세페이지의 타이틀 태그에 입력됩니다.</div>
				</td>
				<td width="120" nowrap>
					상품코드
				</td>
				<td width="50%"><input type="text" name="goodscd" style="width:100%" value="${sellerGoodsFM.sellerGoodsViewVO.goodscd}" class="line" maxlength=20 data-reg="/^[A-Za-z0-9+]*$/" ></td>
			</tr>
			<tr>
				<td>원산지</td>
				<td>
					<input type="text" name="origin" value="${sellerGoodsFM.sellerGoodsViewVO.origin}" class="line" maxlength=20>
					<select onchange="this.form.origin.value=this.value;this.form.origin.focus()">
						<option value="">::: 목록보기 :::</option>
							<c:if test="${!empty sellerGoodsFM.originList && fn:length(sellerGoodsFM.originList) > 0 }">
								<c:forEach items="${sellerGoodsFM.originList }" var="list">
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
							<c:if test="${!empty sellerGoodsFM.brandList &&  fn:length(sellerGoodsFM.brandList)>0 }">
								<c:forEach items="${sellerGoodsFM.brandList }" var="list">
									<option value="${list.sno}" ${sellerGoodsFM.sellerGoodsViewVO.brandno eq list.sno ? 'selected="selected"' : '' }>
										${list.brandnm }
									</option>
								</c:forEach>
							</c:if>					
					</select>
					
					<font class="small1" color="444444"><a href="${ctx }/shop/seller/goods/brandList" target="_blank"><font class="extext_l"><img src="/resources/shop/admin/img/btn_brand_add.gif"></a>
				</td>
			</tr>
	
			<tr>
				<td>유사검색어</td>
				<td >
					<div style='padding-top:5px'><input type="text" name="keyword" value="${sellerGoodsFM.sellerGoodsViewVO.keyword }" style="width:100%" class="line" maxlength=100></div>
					<div style="height:23;padding-top:5px" class="extext">상품상세 페이지의 메타태그와 상품 검색시 키워드로 사용하실 수 있습니다.</font></div>
				</td>
			
			</tr>
		</table>
		
		<div style="padding-top:20px"></div>
		<div style="border-top:3px #efefef solid;"></div>
		
	<!-- 상품추가정보 -->
	<c:if test='${codeUtil:getConfValue("rental_free") eq "false" }'>
		<div class="title">
			상품추가정보<span>상품특성에 맞게 항목을 추가할 수 있습니다 (예. 감독, 저자, 출판사, 유통사, 상품영문명 등)</span>
		</div>
		
		<div class="noline" style="padding-bottom:5px">
			<input type="radio" name="useEx" value="1" ${'|||||' ne sellerGoodsFM.sellerGoodsViewVO.extitle ? 'checked' : ''} onclick="openLayer('tbEx','block')" onfocus="blur()"> 사용
			<input type="radio" name="useEx" value="0" ${'|||||' eq sellerGoodsFM.sellerGoodsViewVO.extitle  ? 'checked' : ''} onclick="openLayer('tbEx','none')" onfocus="blur()"> 사용안함
		</div>
			
		<table id="tbEx" class="tb" style="display:${'|||||' ne sellerGoodsFM.sellerGoodsViewVO.extitle ? 'block' : 'none'}">
			<col class="cellC"><col class="cellL"><col class="cellC"><col class="cellL">
			<tr>
			<c:set var="exTitleLen" value="${fn:length(sellerGoodsFM.sellerGoodsViewVO.extitle) }" />
			<c:set var= "ex_title" value="${fn:split(stringUtil:nullConv(sellerGoodsFM.sellerGoodsViewVO.extitle),'|')}"/>
			 
			<c:set var= "exc" value ="${sellerGoodsFM.sellerGoodsViewVO.ex1}|${sellerGoodsFM.sellerGoodsViewVO.ex2}|${sellerGoodsFM.sellerGoodsViewVO.ex3}|${sellerGoodsFM.sellerGoodsViewVO.ex4}|${sellerGoodsFM.sellerGoodsViewVO.ex5}|${sellerGoodsFM.sellerGoodsViewVO.ex6}" />
			<c:set var= "ex_content" value="${fn:split(stringUtil:nullConv(exc),'|')}"/>
			<c:set var= "exContentLen" value="${fn:length(exc) }"/>
			
			<c:forEach var="i" begin="0" end="5" step="1">		
				<c:set var="tmpExTitle" value="${exTitleLen >= i ? ex_title[i] : ''}" />
				<c:set var="tmpEx" value="${exContentLen >= i ? ex_content[i] : '' }" />
				<td>
				<input type="text" name="title${i+1 }" class="exTitle gray" value="${'' ne tmpExTitle ? tmpExTitle : '' }" maxlength=50></td>
				<td width="50%"><input type="text" name="ex${i+1}" value="${'' ne tmpEx ? tmpEx : '' }"
				style="width:100%" maxlength=100></td>
				${1 == i%2 ? '</tr><tr>':'' }
			</c:forEach>
				
			</tr>
		</table>
			
		<div style="border-bottom:3px #efefef solid;padding-top:20px"></div>
		
	</c:if>
	
	<!-- 상품 가격/재고 -->
	<div class="title">가격/재고/배송비<span>사이즈, 색상 등에 의해 가격이 여러개인 경우 가격옵션을 추가할 수 있습니다 
	</div>

	<table>
		<tr>
			<td>판매가</td>
			<td>
				<input type="text" name="sellerGoodsViewVO.price" size="10" value="${sellerGoodsFM.sellerGoodsViewVO.price}" onchange="autoPrice(this)" onblur="autoPrice(this)" onkeydown="autoPrice(this)" class="line"  maxlength=10 data-reg="/^[0-9]+$/" onkeyup="removeChar(event)" >
			</td>
			<td style="padding-left:10px">정가</td>
			<td>
				<input type="text" name="sellerGoodsViewVO.consumer" size="10" value="${sellerGoodsFM.sellerGoodsViewVO.consumer }" onchange="autoPrice(this)" onblur="autoPrice(this)" onkeydown="autoPrice(this)" class="line"  maxlength=10 data-reg="/^[0-9]+$/" onkeyup="removeChar(event)" >
			</td>
			<td style="padding-left:10px">매입가</td>
			<td>
				<input type="text" name="sellerGoodsViewVO.supply" size="10" value="${sellerGoodsFM.sellerGoodsViewVO.supply }" onchange="autoPrice(this)" onblur="autoPrice(this)" onkeydown="autoPrice(this)" class="line"  maxlength=10 data-reg="/^[0-9]+$/" onkeyup="removeChar(event)" >
			</td>
			<td style="padding-left:10px">재고량</td>
			<td>
				<c:choose>
					<c:when test="${ 0 < sellerGoodsFM.sellerGoodsViewVO.reqsno }">
						<input type="text" size="10" value="${sellerGoodsFM.sellerGoodsViewVO.stock}" disabled="disabled" />
						<input type="hidden" name="sellerGoodsViewVO.stock" value="${sellerGoodsFM.sellerGoodsViewVO.stock}" />
					</c:when>
					<c:otherwise>
						<input type="text" name="sellerGoodsViewVO.stock" size="10" value="" onchange="autoPrice(this)" onblur="autoPrice(this)" onkeydown="autoPrice(this)" class="line" maxlength=7 onkeyup="removeChar(event)" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>
	
	<div style="height:5px;font:0"></div>
	<table class="tb">
		<col class="cellC"><col class="cellL"><col class="cellC"><col class="cellL">
		<tr>
			<td width="120" nowrap>재고량연동</td>
			<td width="50%" class="noline">
				<input type="checkbox" name="usestock" value="o" ${'o' eq sellerGoodsFM.sellerGoodsViewVO.usestock ? 'checked="chekced"' : '' }> 주문시 재고량빠짐
				<div style="padding-top:3px"><font class=extext>(체크안하면 재고량 상관없이 무한정판매)</font></div>
			</td>
			<td width="120" nowrap>품절상품</td>
			<td width="50%" class="noline">
				<input type="checkbox" name="runout" value="1"  ${'1' eq sellerGoodsFM.sellerGoodsViewVO.runout ? 'checked="chekced"' : '' } > 품절된 상품입니다
			</td>
		</tr>
		<tr>
			<td>과세/비과세</td>
			<td class="noline">
				<input type="radio" name="tax" value="1" ${0 ne sellerGoodsFM.sellerGoodsViewVO.tax ? 'checked="checked"' : '' }> 과세
				<input type="radio" name="tax" value="0" ${0 eq sellerGoodsFM.sellerGoodsViewVO.tax ? 'checked="checked"' : '' }> 비과세
			</td>
			<td>가격대체문구</td>
			<td><input type="text" name="strprice" value="${sellerGoodsFM.sellerGoodsViewVO.strprice }" class="line" maxlength=10></td>
		</tr>
		<tr>
			<td>배송비</td>
			<td colspan="3">
				<table cellspacing="0" cellpadding="0" border="0">
					<tr height="40">
						<td>	
							<input type="radio" name=deliveryType value="0" ${0 eq sellerGoodsFM.sellerGoodsViewVO.deliverytype ? 'checked="checked"' : '' } class="null" onclick="chk_delivery_type();">기본배송정책에 따름&nbsp;
							<input type="radio" name="deliveryType" value="1" ${1 eq sellerGoodsFM.sellerGoodsViewVO.deliverytype ? 'checked="checked"' : '' }  class="null" onclick="chk_delivery_type();"> 무료배송&nbsp;
							<input type="radio" name="deliveryType" value="2" ${2 eq sellerGoodsFM.sellerGoodsViewVO.deliverytype ? 'checked="checked"' : '' } class="null" onclick="chk_delivery_type();">상품별 배송비 입력
							<span style="display:none;" id="gdi">&nbsp;
								<input type="text" name="goodsDelivery" value="${!empty sellerGoodsFM.sellerGoodsViewVO.goodsdelivery ? sellerGoodsFM.sellerGoodsViewVO.goodsdelivery:''}" size="8" onkeydown="onlynumber()">원
							</span>	&nbsp;
							<input type="radio" name="deliveryType" value="3" ${3 eq sellerGoodsFM.sellerGoodsViewVO.deliverytype ? 'checked' : '' } class="null" onclick="chk_delivery_type();">착불배송비
							<span style="display:none;" id="gdi2">&nbsp;<input type="text" name="goodsDelivery2" value="${sellerGoodsFM.sellerGoodsViewVO.goodsdelivery}" size="8" onkeydown="onlynumber()">원</span>
						</td>		
					</tr>
				</table>
				<div>
					<font class="extext">기본배송정책과 상품별 배송비 정책은 <a href="${ctx }/shop/seller/basic/delivery" target="_blank"><font class="extext_l">[기본관리 > 배송/택배사 설정]</font></a> 에서 관리 하실 수 있습니다.</font>
				</div>
			</td>
		</tr>
	</table>
	
	<div class="title">
		상품옵션 <span>이상품의 옵션이 여러개인경우 등록하세요 (색상, 사이즈 등)</span>
	</div>
	<div class="noline" style="padding-bottom: 5px">
		<input type="radio" name="usegoodsadd" value="1" ${'1' eq sellerGoodsFM.sellerGoodsViewVO.usegoodsadd ? 'checked' : ''} 
			onclick="vOption('block')" onfocus="blur()"> 사용 
		<input type="radio" name="usegoodsadd" value="0" ${'0' eq sellerGoodsFM.sellerGoodsViewVO.usegoodsadd || '' eq sellerGoodsFM.sellerGoodsViewVO.usegoodsadd ? 'checked' : ''}
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
			<input type="radio" name="opttype" value="single" ${'single' eq sellerGoodsFM.sellerGoodsViewVO.opttype ? 'checked' : '' } onfocus=blur()> 일체형
			<input type="radio" name="opttype" value="double" ${'double' eq sellerGoodsFM.sellerGoodsViewVO.opttype ? 'checked' : '' } onfocus=blur()> 분리형
		</span>
	</div>
		<table id="tbOption" border="1" bordercolor="#cccccc" style="display:${sellerGoodsFM.sellerGoodsViewVO.usegoodsadd eq '1' ? 'block' : 'none'}">
			<col class="cellC">
			<col class="cellL">
			<col class="cellC">
			<col class="cellL">
			<tr align="center">
				<td width="116">옵션명</td>
				<td>판매가</td>
				<td>정가</td>
				<td>매입가</td>
				<td>상품설명</td>
				<td>재고량</td>
				<c:if test="${null ne sellerGoodsFM.sellerGoodsViewVO.optnm}">
					<%--<c:set var="opt2NmArray" value="${fn:split(stringUtil:nullConv(goodsVO.goodsObj.optnm), '|')}"/>
						<c:forEach var="opt2nm" items="${opt2NmArray}" varStatus="st">
							<td><input type=text class='opt gray' name=opt2[] value="${opt2nm}"  label='2차옵션명'></td>
						</c:forEach>
					--%>
					<c:forEach var="opt2nm" items="${sellerGoodsFM.sellerGoodsViewVO.optNmArray}" varStatus="st">
							<td><input type=text class='opt gray' name=opt2[] value="${opt2nm}"  label='2차옵션명'></td>
					</c:forEach>
				</c:if>
			</tr>
			<tbody id="tbOptionBody">
				<c:if test='${sellerGoodsFM.goodsOptionList ne null}'>
					<c:forEach var="optList" items="${sellerGoodsFM.goodsOptionList}" varStatus="status">
						<tr>
							<td><input type=text class='opt gray' name=opt1[] value="${optList.opt1}"  label='1차옵션명' ></td>
							<td><input type=text name=option[price][] class='opt gray' onkeydown='onlynumber(event)' maxlength=10 value="${optList.price}" ></td>
							<td><input type=text name=option[consumer][] class='opt gray' onkeydown='onlynumber(event)' maxlength=10 value="${optList.consumer}" ></td>
							<td><input type=text name=option[supply][] class='opt gray' onkeydown='onlynumber(event)' maxlength=10 value="${optList.supply}" ></td>
							<td><input type=text name=option[optexplain][] class='opt gray' onkeydown='onlynumber(event)' value="${optList.optexplain}" ></td>
							<td><input type=text name=option[opt1Stock][] class='opt gray' onkeydown='onlynumber(event)' maxlength=10 value="${optList.stock}" onchange="changeStock()">
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

	
	
	<div style="border-bottom:3px #efefef solid;padding-top:20px"></div>
		
	<!-- 추가옵션 -->
	<c:if test='${codeUtil:getConfValue("rental_free") eq "false" }'>
		<div class="title">추가옵션/추가상품/사은품<span>추가옵션을 무제한 등록할 수 있으며, 추가상품을 판매하거나 사은품을 제공할 수도 있습니다 </div>
		<div class="noline" style="padding-bottom:5px">
			<input type="radio" name="useadd" ${sellerGoodsFM.sellerGoodsViewVO.useadd eq '1' ? "checked='checked'" : ""} onclick="openLayer('tbAdd','block')" onfocus=blur() value="1"> 사용
			<input type="radio" name="useadd" ${sellerGoodsFM.sellerGoodsViewVO.useadd ne '1' ? "checked='checked'" : ""} onclick="openLayer('tbAdd','none')" onfocus=blur() value="0"> 사용안함
		</div>
		
		<a href="javascript:add_addopt()"><img src="/resources/shop/admin/img/i_addoption.gif" align=absmiddle></a>
		<a href="javascript:del_addopt()"><img src="/resources/shop/admin/img/i_deloption.gif" align=absmiddle></a>
		
		<span class="small1" style="padding-left:5px">(옵션명에 아무 내용도 입력하지 않으면 해당 옵션은 삭제처리됩니다)</span>
		
		<div style="height:7px"></div>
		
		<table id="tbAdd" style="display:${sellerGoodsFM.sellerGoodsViewVO.useadd eq '1' ? 'block' : 'none'}" border="2" bordercolor="#cccccc" style="border-collapse:collapse">
			<tr bgcolor="#f7f7f7" align="center">
				<td>옵션명 <font class="small">(예. 악세사리)</font></td>
				<td>항목명 <font class="small">(예. 열쇠고리)</font></td>
				<td>가격 <font class="small" color="444444">(무료일때는 0원입력)</font></td>
				<td>구매시필수</td>
			</tr>
			<col valign="top" style="padding-top:5px">
			<col span="2"><col align="center" valign="top" style="padding-top:5px">
			<c:if test='${!empty sellerGoodsFM.goodsAddInfoList}'>
				<c:set var= "addOptNmArray" value="${fn:split(stringUtil:nullConv(sellerGoodsFM.sellerGoodsViewVO.addoptnm),'|')}"/>
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
								<c:forEach var="goodsAddList" items="${sellerGoodsFM.goodsAddInfoList}" varStatus="idx">
									<c:if test='${goodsAddList.step eq g.index}'>
										<tr>
											<td><input type="text" name="addopt[opt][${g.index}][]" value="${goodsAddList.opt}" style="width:270px"> 선택시</td>
											<td>판매금액에 <input type="text" name="addopt[addprice][${g.index}][]"  size="9" value="${goodsAddList.addprice}"  onkeydown="onlynumber()"> 원 추가</td>
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
	<div class="title">관련상품<span>이상품과 관련있는 상품을 추천하세요</div>
		
	<div style="padding-bottom:5px" class=noline>
		<input type="radio" name="relationis" value="0" onfocus="blur()" onclick="openLayer('divRefer','none')" ${"1" ne stringUtil:nullConv(sellerGoodsFM.sellerGoodsViewVO.relationis) ? "checked='checked'" : ""}>자동 
		<font class="small" color="#5A5A5A">(같은 분류 5개상품이 무작위로 보여짐)</font>
		&nbsp;&nbsp;&nbsp;
		<input type="radio" name="relationis" value="1" onfocus="blur()" onclick="openLayer('divRefer','block')" ${"1" eq stringUtil:nullConv(sellerGoodsFM.sellerGoodsViewVO.relationis) ? "checked='checked'" : ""}>수동
		<font class="small" color="#5A5A5A">
			(아래 직접 선택등록 | 현재 관련상품 : <b id="relationCnt">${fn:length(sellerGoodsFM.goodsRelationList)}</b>개)
		</font>
	</div>
	
	<div id="divRefer" style="display:${ '1' eq stringUtil:nullConv(sellerGoodsFM.sellerGoodsViewVO.relationis) ? 'block' : 'none'};position:relative;z-index:99">
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
				<c:if test="${!empty sellerGoodsFM.goodsRelationList}">
					<c:forEach var="relation" items="${sellerGoodsFM.goodsRelationList}" varStatus="idx">
						<tr onclick="spoit('refer',this)" ondblclick="remove_goods('refer',this)" class="hand">
						<td width="50" nowrap>
							<c:set var="subStr" value="${fn:substring(relation.imgs, 1, (fn:length(relation.imgs))) }" />
							<a href="../../goods/goods_view?goodsno=${relation.goodsno}" target="_blank">${shopLibFunction:goodsimg(subStr,"40","",4)}</a>
						</td>
						<td width="100%">
							<div>${relation.goodsnm}</div>
							<b><fmt:formatNumber value="${relation.price}" pattern="\#,###.##"/></b>
							<input type="hidden" name="e_refer[]" value="${relation.goodsno}">
						</td>
					</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
		<div id="referX" style="padding-top:3px"></div>
	</div>
	<div style="border-bottom:3px #efefef solid;padding-top:20px"></div>
	
	<script>react_goods('refer');</script>	
		
	<!-- 상품 이미지 -->
	<div class="title">상품 이미지</div>
	
	<table class="tb">
		<col class="cellC">
		<col class="cellL">
		<col class="cellC">
		<col class="cellL">
		<c:forEach var="i" begin="0" end="3" step="1">
			<c:choose>
				<c:when test="${i == 0 }">
					<c:set var= "tmpArray" value="${fn:split(stringUtil:nullConv(sellerGoodsFM.sellerGoodsViewVO.imgi),'|')}"/>
				</c:when>
				<c:when test="${i == 1 }">
					<c:set var= "tmpArray" value="${fn:split(stringUtil:nullConv(sellerGoodsFM.sellerGoodsViewVO.imgs),'|')}"/>
				</c:when>
				<c:when test="${i == 2 }">
					<c:set var= "tmpArray" value="${fn:split(stringUtil:nullConv(sellerGoodsFM.sellerGoodsViewVO.imgl),'|')}"/>
				</c:when>
				<c:when test="${i == 3 }">
					<c:set var= "tmpArray" value="${fn:split(stringUtil:nullConv(sellerGoodsFM.sellerGoodsViewVO.imgm),'|')}"/>
				</c:when>
			</c:choose>
			
			<tr>
				<td width="200">
					${strImgTitArr[i]}
				<!-- 2017-08-30 이미지 설명 추가 -->
				<c:if test="${i==0}">
					<br/><font class="extext">상품리스트 마우스오버시 노출되는 이미지</font>
				</c:if>
				</td>
				<td>
					<input type="hidden" name="orgNames_img_${sellerGoodsFM.imgsArray[i][0]}" value="${sellerGoodsFM.imgsArray[i][1]}"/> 
					<table id="tb_${sellerGoodsFM.imgsArray[i][0]}">
						<col valign="top" span="2">
						<c:set var= "tmpArray" value="${fn:split(stringUtil:nullConv(sellerGoodsFM.imgsArray[i][1]),'|')}"/>
						<c:if test='${0 eq fn:length(tmpArray)}'>
							<tr>
								<td>
									<!-- 확대이미지, 상세이미지 일경우 추가버튼 출력 -->
									<c:choose>
										<c:when test='${("l" eq sellerGoodsFM.imgsArray[i][0] || "m" eq sellerGoodsFM.imgsArray[i][0]) }'>
											<a href="javascript:addfld('tb_${sellerGoodsFM.imgsArray[i][0]}')"><img src="/resources/shop/admin/img/i_add.gif" align="absmiddle"></a>
										</c:when>
										<c:otherwise>
											<span style="display: inline-block; width: 36px;"></span>
										</c:otherwise>
									</c:choose>
									<span><input type="file" name="img_${sellerGoodsFM.imgsArray[i][0]}[]0" "style="width:300px" onChange="preview(this);" accept=".gif, .jpg, .png, .jpge, .bmp" >
									</span>
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
								<%-- 상세 이미지만 추가버튼 출력되게 수정 확대 이미지는 하나만 사용해서 추가가 필요없음 --%>
								<%-- <c:when test='${("l" eq goodsVO.imgsArray[i][0] || "m" eq goodsVO.imgsArray[i][0]) && 0 == g.index}'> --%>
								<c:when test='${("m" eq sellerGoodsFM.imgsArray[i][0]) && 0 == g.index}'>
									<a href="javascript:addfld('tb_${sellerGoodsFM.imgsArray[i][0]}')"><img src="/resources/shop/admin/img/i_add.gif" align="absmiddle"></a>
								</c:when>
								<c:otherwise>
									<span style="display: inline-block; width: 36px;"></span>
								</c:otherwise>
							</c:choose>
								<span><input type="file" name="img_${sellerGoodsFM.imgsArray[i][0]}[]${g.index}"  id="img_${sellerGoodsFM.imgsArray[i][0]}${g.index}" " style="width:300px" onChange="preview(this)" accept=".gif, .jpg, .png, .jpge, .bmp" ></span>
							</td>
							<td>
								<c:if test='${"" ne tmpArray}'>
									<%-- <div style="padding:0 0" class=noline><input type=checkbox name="del[img_${goodsVO.imgsArray[i][0]}][]${g.index}" value="${tmpArray}"><font class="small" color="#585858">삭제 (${tmpArray})</font></div> --%>
									<div style="padding:0 0" class=noline><input type=checkbox id="del_img_${sellerGoodsFM.imgsArray[i][0]}${g.index}" name="del[img_${sellerGoodsFM.imgsArray[i][0]}][]${g.index}" value="${tmpArray}"><font class="small" color="#585858">삭제 (${tmpArray})</font></div>
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
	</table>
			
	<div style="border-bottom:3px #efefef solid;padding-top:30px"></div>


	<!-- 상품고시를 사용하는곳이 없어 주석처리함 -->
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
			<td width="150"><input type="checkbox" id="allGoodsNotiChk" /></td>
			<td width="200">항목</td>
			<td>내용</td>
		</tr>
		<c:choose>
			<c:when test="${!empty sellerGoodsFM.goodsNotiList}">
				<c:forEach var="goodsNoti" items="${sellerGoodsFM.goodsNotiList}" varStatus="varStat">
					<tr>
						<td align="center">${varStat.count}</td>
						<td align="center"><input type="checkbox" /></td>
						<td><input type="text" style="width: 100%" label="항목 1" name="goodsNotiList[${varStat.index}].item" value="${goodsNoti.item}" /></td>
						<td>
							<textarea style="width:100%;height:40px;" class="__tline" label="내용 1" name="goodsNotiList[${varStat.index}].memo">${goodsNoti.memo}</textarea>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td align="center">1</td>
					<td align="center"><input type="checkbox" /></td>
					<td><input type="text" style="width: 100%" label="항목 1" name="goodsNotiList[0].item" /></td>
					<td>
						<textarea style="width:100%;height:40px;" class="__tline" label="내용 1" name="goodsNotiList[0].memo"></textarea>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table> 

	<div style="border-bottom:3px #efefef solid;padding-top:30px"></div> --%>
		
		<!-- 상품 설명 -->
		<div class="title">상품 설명</div>

		<table class="tb">
			<col class="cellC"><col class="cellL"><col class="cellC"><col class="cellL">
			<tr>
				<td>짧은설명</td>
				<td>
					<textarea name="shortdesc" style="width:100%;height:20px;" class="__tline" maxlength=100>${stringUtil:nullConv(sellerGoodsFM.sellerGoodsViewVO.shortdesc)}</textarea>
				</td>
			</tr>
		</table>
		
		<div style="height:6px;font:0"></div>
		<textarea name="longdesc" style="width:100%;height:400px" type="editor">${stringUtil:nullConv(sellerGoodsFM.sellerGoodsViewVO.longdesc)}</textarea>
		<div style="border-bottom:3px #efefef solid;padding-top:20px"></div>

		<!-- 관리 메모 -->
		<div class="title">요청 메모</div>
		<textarea name="approvalMemo" style="width:100%;height:60px" class="tline" maxlength=200>${stringUtil:nullConv(sellerGoodsFM.sellerGoodsViewVO.approvalMemo)}</textarea>
				
		<div class="button">
			<c:choose>
				<c:when test="${'Y' == sellerGoodsFM.popView}">
					<input type="button" class="btn btn-primary" value="닫기" onclick="self.close();" />
				</c:when>
				
				<c:when test="${ 0 < sellerGoodsFM.goodsno }">
					<if test="test="${ 1 != sellerGoodsFM.sellerGoodsViewVO.approvalStatus && 1 != sellerGoodsFM.sellerGoodsViewVO.reqAprlStat }" >
						<input type="button" class="btn btn-primary" value="수정신청" onclick="return reqApprovalMod();" />
					</if>
					
					<input type="button" class="btn btn-primary" value="목록" onclick="window.location.href='${ctx}/shop/seller/goods/list';" />
					<br /><br />
					<a href="${ctx }/shop/goods/goods_view?goodsno=${sellerGoodsFM.sellerGoodsViewVO.goodsno}" target="_blank"><img src="/resources/shop/admin/img/btn_goods_view.gif"></a>
					
				</c:when>
				
				<c:when test="${ 0 < sellerGoodsFM.reqsno }">
					<c:if test="${99 == sellerGoodsFM.sellerGoodsViewVO.approvalStatus}" >
						<input type="button" class="btn btn-primary" value="수정" onclick="return checkGoodsForm();" />
						<input type="button" class="btn btn-primary" value="등록 신청" onclick="return reqApprovalReg();" />
					</c:if>
					
					<input type="button" class="btn btn-primary" value="목록" onclick="window.location.href='${ctx}/shop/seller/goods/registerApprovalList';" />
				</c:when>
				<c:when test="${ 0 >= sellerGoodsFM.reqsno}">
					<input type="button" class="btn btn-primary" value="등록" onclick="return checkGoodsForm();" />
					<input type="button" class="btn btn-primary" value="목록" onclick="window.location.href='${ctx}/shop/seller/goods/registerApprovalList';" />
				</c:when>
			</c:choose>
		</div>		
</form>

<script>
		var optCheck = $("input:radio[name='usegoodsadd']:checked").val();
		if(Number(optCheck) == 1){
			$("#objOption").show();	
		}else{
			$("#objOption").hide();
		}
	</script>
