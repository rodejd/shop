<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<script language="javascript">
var selOption = null;
function checkform(form){
	if (form.couponname.value.trim() == "") {
		alert('쿠폰이름을 입력하세요');
		form.couponname.focus();
		return false;
	}
	if (form.summa.value.trim() == "") {
		alert('쿠폰설명을 입력하세요');
		form.summa.focus();
		return false;
	}
	if (form.price.value.trim() == "" || form.price.value.trim() == "0") {
		alert('할인금액을 입력하세요');
		form.price.focus();
		return false;
	}
	if (form.goodstype.value == "1") {
		var fieldname1 = eval("form.elements['categoryArr']");
		var fieldname2 = eval("form.elements['e_refer[]']");
		if (fieldname1 == undefined && fieldname2 == undefined) {
			alert('선택 상품을 입력하세요.');
			return false;
		}
	}
	if (form.priodtype.value == "0") {
		if (form.sdate.value == "" || form.edate.value == "") {
			alert("적용기간을 입력하세요.");
			form.sdate.focus();
			return false;
		}
	} else {
		if (form.priod.value == "") {
			alert("적용기간을 입력하세요.");
			form.priod.focus();
			return false;
		}
	}
	
	$('#coupontypesel').val($('input:radio[name="coupontype"]:checked').val());	// 선택된 쿠폰발급방식
	$('#abilitysel').val($('input:radio[name="ability"]:checked').val());		// 선택된 쿠폰기능
	
	var approvalValue = $('input:radio[name="statusRadio"]:checked').val();
	if (approvalValue != '2' && approvalValue != '3') { //저장시 승인, 반려가 아닌경우 승인대기로 변경
		$('[name=approvalstatus]').val('1'); //승인요청
	} else {
		$('[name=approvalstatus]').val(approvalValue);
	}
	
	return true;
}

function exec_add() {
	var ret;
	var str = new Array();
	var obj = document.forms[0]['cate'];
	for (var i = 0; i < obj.length; i++) {
		if (obj[i].value) {
			str[str.length] = obj[i][obj[i].selectedIndex].text;
			ret = obj[i].value;
		}
	}
	if (!ret) {
		alert('카테고리를 선택해주세요');
		return;
	}
	var obj = document.getElementById('objCategory');
	oTr = obj.insertRow();
	oTd = oTr.insertCell();
	oTd.id = "currPosition";
	oTd.innerHTML = str.join(" > ");
	oTd = oTr.insertCell();
	oTd.innerHTML = "\<input type=text name=categoryArr value=" + ret + " style='display:none'>";
	oTd = oTr.insertCell();
	oTd.innerHTML = "<a href='javascript:void(0)' onClick='cate_del(this.parentNode.parentNode)'><img src='/resources/shop/admin/img/i_del.gif' align=absmiddle></a>";
}

function cate_del(el) {
	idx = el.rowIndex;
	var obj = document.getElementById('objCategory');
	obj.deleteRow(idx);
}

function open_box(name, isopen) {
	var mode;
	var isopen = (isopen || document.getElementById('obj_'+name).style.display!="block") ? true : false;
	mode = (isopen) ? "block" : "none";
	document.getElementById('obj_'+name).style.display = document.getElementById('obj2_'+name).style.display = mode;
}

function list_goods(name) {
	var category = '';
	var els = document.forms[0][name];
	var str = document.forms[0]['refer'];
	var lstr = [];
	lstr = str[0];
	/* if(lstr.value=="") {
		alert("카테고리를 선택해주세요"+lstr.value);
		return false;
	} */
	open_box(name,true);
	for (i=0;i<els.length;i++) if (els[i].value) category = els[i].value;
	var ifrm = eval("ifrm_" + name);
	var goodsnm = eval("document.forms[0].search_" + name + ".value");
	//ifrm.location.href = "../goods/_goodslist.jsp?name=" + name + "&category=" + category + "&goodsnm=" + goodsnm;
	document.all["ifrm_" + name].src = ctx + "/shop/admin/goods/iframeList?name=" + name + "&schCate=" + category + "&schKey=all&schWord=" + goodsnm; 
}

function go_list_goods(name) {
	if (event.keyCode==13) {
		list_goods(name);
		return false;
	}
}

function view_goods(name) {
	open_box(name,false);
}

function remove_goods(name, obj) {
	var tb = document.getElementById('tb_'+name);
	tb.deleteRow(obj.rowIndex);
	react_goods(name);
}

function react_goods(name) {
	var tmp = new Array();
	var obj = document.getElementById("tb_"+name);
	for (var i=0;i<obj.rows.length;i++) {
		tmp[tmp.length] = "<div style='float:left;border:1 solid #cccccc;margin:1px;' title='" + obj.rows[i].cells[1].getElementsByTagName('div')[0].innerText + "'>" + obj.rows[i].cells[0].innerHTML + "</div>";
	}
	document.getElementById(name+'X').innerHTML = tmp.join("") + "<div style='clear:both'>";
}

var iciRow, preRow, nameObj;
function spoit(name,obj) {
	nameObj = name;
	iciRow = obj;
	iciHighlight();
}

function iciHighlight() {
	if (preRow) preRow.style.backgroundColor = "";
	iciRow.style.backgroundColor = "#FFF4E6";
	preRow = iciRow;
}

function moveTree(idx) {
	if (document.getElementById("obj_"+nameObj).style.display!="block") return;
	var objTop = iciRow.parentNode.parentNode;
	var nextPos = iciRow.rowIndex+idx;
	if (nextPos==objTop.rows.length) nextPos = 0;
	objTop.moveRow(iciRow.rowIndex,nextPos);
	react_goods(nameObj);
}
function keydnTree() {
	if (iciRow==null) return;
	switch (event.keyCode) {
		case 38: moveTree(-1); break;
		case 40: moveTree(1); break;
	}
	return false;
}

function displayLayer(layerid) {
	if (layerid == undefined || layerid == 'priodid') layerid = 'priodid0';
	document.getElementById('priodid0').style.display='none';
	document.getElementById('priodid1').style.display='none';
	document.getElementById(layerid).style.display='block';
}

function chk_msg(val) {
	if (val == 1) document.getElementById('actid').innerHTML='를 적립합니다';
	else document.getElementById('actid').innerHTML='를 할인합니다';
}

function chk_coupontype() {
	var f = document.forms[0];
	document.getElementById('tgt').innerHTML = '총 구매금액 중';

	if (f.coupontype[1].checked) {
		document.getElementById('tgt').innerHTML = '발급 상품 금액의';
		return;
	}
}

function chg_perc(val) {
	if (val == "%") {
		openLayer("divmaxp");
	} else {
		closeLayer("divmaxp");
	}
}

document.onkeydown = keydnTree;
</script>

<script type="text/javascript">
$(function(){
	// 초기값 설정
	if('modify' != '${param.mode}'){
		$('input:radio[name="coupontype"][value="0"]').prop("checked", true);
		$('input:radio[name="ability"][value="0"]').prop("checked", true);
		$('input:radio[name="goodstype"][value="0"]').prop("checked", true);
		$('input:radio[name="priodtype"][value="0"]').prop("checked", true);
	}
	
	//회원직접 다운로드일 경우 openLayer
	if ($('input:radio[name=coupontype]:checked').val() == 1) {
		chk_coupontype();
		openLayer("divdcnt");
	}
});

function closeLayer(obj,mode) {
	obj = _ID(obj);
	if (mode) obj.style.display = mode;
	else obj.style.display = "none";
}
</script>

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<div class='title title_top'>쿠폰만들기<span>고객에게 발급할 쿠폰을 만듭니다. </span><!-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=event&no=12',870,800)"><img src="/resources/shop/admin/img/btn_q.gif" border=0 align=absmiddle hspace=2></a> --></div>

<form id="couponForm" name="couponForm" method="post" action="sellerIndbCoupon" enctype="multipart/form-data" onsubmit="return checkform(this);">
	<input type="hidden" name="mode" value="${couponVO.mode}">
	<input type="hidden" name="couponcd"  value="${couponVO.couponcd}">
	<input type="hidden" name="coupontypesel" id="coupontypesel" value="">
	<input type="hidden" name="abilitysel" id="abilitysel" value="">
	<input type="hidden" name="approvalstatus" id="approvalstatus" value="${couponInfo.approvalstatus}">

	<table class=tb>
		<col class=cellC><col class=cellL style='padding:5,0,5,5'>

		<tr>
			<td>노출스킨</td>
			<td>
				<select id="skin" name="skin" class="cline" style="width: 100px;">
					<option value="al" ${stringUtil:selected(couponInfo.skin, "al")}>ALL</option>
					${webUtil:makeSelectCodeItem((codeUtil:codeitem('skin')), couponInfo.skin) }
				</select>
			</td>
		</tr>
		<tr>
			<td>쿠폰이름</td>
			<td><input type=text name='couponname' size=40 maxlength=30 value="${couponInfo.coupon}" required class=line> <font class=extext>ex) 오픈기념쿠폰, 추석할인쿠폰</font></td>
		</tr>
		<tr>
			<td>쿠폰설명</td>
			<td><input type=text name='summa' size=40 maxlength=70 value="${couponInfo.summa}" class=line> <font class=extext> ex) 특가이벤트! 여름상품 10% 할인쿠폰</font></td>
		</tr>
		<tr>
			<td>쿠폰이미지</td>
			<td>
				<input type="file" id="imgFile"name="imgFile" accept=".gif, .jpg, .png, .jpge, .bmp" > 
				<font class="extext" > <c:if test="${not empty couponInfo.couponimg}"> (<a href="${couponInfo.couponimg}" class="moImgView" target="_blank">${couponInfo.couponimg}</a> ) </c:if></font>
				<input type="hidden" name="couponimg" id="couponimg" value="${couponInfo.couponimg}">
			</td>
		</tr>
		<tr>
			<td>쿠폰발급방식</td>
			<td>
				<div><input type=radio name=coupontype value='0' class=null <c:if test="${'0' == couponInfo.coupontype}">checked</c:if> onclick="chk_coupontype(); closeLayer('divdcnt');"> 운영자발급 <font class=extext>(쿠폰등록 후 쿠폰리스트에서 운영자가 특정회원에게 발급합니다)</font></div>
				<div><input type=radio name=coupontype value='1' class=null <c:if test="${'1' == couponInfo.coupontype}">checked</c:if> onclick="chk_coupontype(); openLayer('divdcnt');"> 회원직접다운로드 <font class=extext>(상품상세정보에서 회원이 직접 쿠폰을 다운로드받습니다)</font></div>
				<div id='divdcnt' style="padding:3 0 10;display:none;">
			    	<table border=1 bordercolor=#cccccc style="border-collapse:collapse" width=635>
						<tr>
							<td bgcolor=white style="padding:5 0 7 2">
								<div style='padding-left:10'>이 쿠폰의 총 다운로드 횟수를 <input type='text' style='text-align:right' name='dncnt' size=3 value="${couponInfo.dncnt}" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" maxlength='9'>회로 제한합니다 <font class=extext>(공란으로 두면  무제한)</font></div>
							</td>
						</tr> 
					</table>
				</div>
				<div><input type=radio name=coupontype value='2' class=null <c:if test="${'2' == couponInfo.coupontype}">checked</c:if> onclick="chk_coupontype(); closeLayer('divdcnt');"> 회원가입자동발급 <font class=extext>(회원가입시 자동발급됩니다)</font></div> 
				<div><input type=radio name=coupontype value='3' class=null <c:if test="${'3' == couponInfo.coupontype}">checked</c:if> onclick="chk_coupontype(); closeLayer('divdcnt');"> 구매후 자동발급 <font class=extext>(구매후 배송완료시에 자동발급됩니다)</font></div>
				<div style="padding-top:4"></div>
			</td>
		</tr>
		<tr style="display:none">
			<td>쿠폰기능</td>
			<td>
				<input type=radio name=ability value='0' class=null <c:if test="${'0' == couponInfo.ability}">checked</c:if> onclick='chk_msg(this.value);'> 할인쿠폰을 발행합니다 <font class=extext>(구매시 바로 할인되는 쿠폰)</font>&nbsp;&nbsp;
				<%-- <input type=radio name=ability value='1' class=null <c:if test="${'1' == couponInfo.ability}">checked</c:if> onclick='chk_msg(this.value);'> 적립쿠폰을 발행합니다 <font class=extext>(구매 후(배송완료) 적립되는 쿠폰)</font>  --%>
			</td>
		</tr>
		<tr>
			<td>쿠폰금액</td>
			<td>
				<div>
					<span id="tgt">총 구매금액 중</span>
					<input type="text" class="line" name="price" size="10" style="text-align:right" maxlength="15" value="${fn:replace(couponInfo.price, '%', '')}" required onkeydown="onlynumber(event);" onkeyup="removeHangul(event);">&nbsp;
					<select name="perc" onchange="chg_perc(this.value);">
						<option value="유로" <c:if test="${!fn:endsWith(couponInfo.price, '%')}">selected</c:if>>유로</option>
						<option value="%" <c:if test="${fn:endsWith(couponInfo.price, '%')}">selected</c:if>>%</option>
					</select><span id="actid">를 할인합니다</span>
				</div>
				<div id='divmaxp' style="padding:3 0 10;<c:if test="${!fn:endsWith(couponInfo.price, '%')}">display:none;</c:if>">
			    	<table border=1 bordercolor=#cccccc style="border-collapse:collapse" width=635>
						<tr>
							<td bgcolor=white style="padding:5 0 7 2">
								<div style='padding-left:10'>이 쿠폰의 최대 할인액을 <input type='text' style='text-align:right' name='maxprice' size=10 maxlength="15" value="${couponInfo.maxprice}" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);"> 유로로 제한합니다 <font class=extext>(공란으로 두면  무제한)</font></div>
							</td>
						</tr> 
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td>쿠폰발급상품</td>
			<td>
				<table width=100% cellpadding=0 cellspacing=0>
					<tr>
						<td><input type=radio name=goodstype value='1' class=null checked > 상품에 발급합니다 <font class=extext>(아래에서 검색후 선정)</font></td>
					</tr>
					<tr>
						<td height=5></td>
					</tr>
					<tr>
						<td>
							<div style=padding-left:8><font class=small1 color=FF0066><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">상품 선정 (상품검색 후 선정)</font></div>
							<div id=divRefer style="position:relative;z-index:99;padding-left:8">
								<div style="padding-bottom:3px">
									<script>new categoryBox('refer',4,'','');</script>
									<input type=text name=search_refer onkeydown="return go_list_goods('refer')">
									<a href="javascript:list_goods('refer')"><img src="/resources/shop/admin/img/i_search.gif" align=absmiddle></a>
									<a href="javascript:view_goods('refer')"><img src="/resources/shop/admin/img/i_openclose.gif" align=absmiddle></a>
								</div>
								<div id=obj_refer class=box1><iframe id=ifrm_refer style="width:100%;height:100%" frameborder=0></iframe></div>
								<div id=obj2_refer class="box2 scroll" onselectstart="return false" onmousewheel="return iciScroll(this)">
									<div class=boxTitle>- 등록된 상품 <font class=small color=#F2F2F2>(삭제하려면 더블클릭)</font></div>
									<table id="tb_refer" class="tb">
										<col width=50>
										<c:if test="${couponGoodsList != null &&  fn:length(couponGoodsList) >0 }">	
											<c:forEach items="${couponGoodsList}" var="good" varStatus="i">
											<tr onclick="spoit('refer', this)" ondblclick="remove_goods('refer',this)" class="hand">
												<td width=50 nowrap>
													<a href="${ctx}/shop/goods/goods_view?goodsno=${good.goodsno}" target=_blank>
													<c:set var="subStr" value="${fn:substring(good.img_s, 1, (fn:length(good.img_s))) }" />
													${shopLibFunction:goodsimg(subStr,"40,40","",4)}
												</td>
												<td width=100%>
													<div>${good.goodsnm}</div>
													<b>${good.price}</b>
													<input type="hidden" name="e_refer[]" value="${good.goodsno}" >
												</td>
											</tr>	
											</c:forEach>
										</c:if>
									</table>
								</div>
								<div id=referX style="font:0"></div>
							</div>
							<div>
								<script>react_goods('refer');</script>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>적용기간</td>
			<td>
				<input type=radio class=null name=priodtype value=0 <c:if test="${'0' == couponInfo.priodtype}">checked</c:if> onclick="javascript:displayLayer('priodid0')" > 시작일, 종료일 선택
				&nbsp;&nbsp;
				<input type=radio class=null name=priodtype value=1 <c:if test="${'1' == couponInfo.priodtype}">checked</c:if> onclick="javascript:displayLayer('priodid1')" > 발급일로부터 기간 제한
				<div id=priodid0 style="display:block;height:50;padding-top:10">
					<input type=text name=sdate id=sdate value="<c:if test="${'0' == couponInfo.priodtype}">${couponInfo.sdate}</c:if>" option='regDateTime' label='발급기간' class=line  maxlength="20"> - 
					<input type=text name=edate id=edate value="<c:if test="${'0' == couponInfo.priodtype}">${couponInfo.edate}</c:if>" option='regDateTime' label='발급기간' class=line maxlength="20">	
					<font class=extext> ex) 2007-09-10 12:30:30</font>
					<div style='height:15;padding-top:5'><font class=extext>&nbsp;시간까지 정확히 입력하세요</font></div>
				</div>
				<div id=priodid1 style="display:none;height:25;padding-top:10">
					&nbsp; 쿠폰발급일로부터 <input type=text name=priod id=priod value="<c:if test="${'1' == couponInfo.priodtype}">${couponInfo.sdate}</c:if>" size=5 maxlength=3 onkeydown="onlynumber(event);" onkeyup="removeHangul(event);"> 일까지 사용기간을 제한합니다.
				</div>
			</td>
		</tr>
		<tr>
			<td>쿠폰사용제한</td>
			<td>
				₩<input type=text name=excPrice size=10 style="text-align:right" maxlength=10 value="${couponInfo.excPrice}" class=line onkeydown="onlynumber(event);" onkeyup="removeHangul(event);"> 이상 구매시에만 사용가능 <font class=extext>(공란으로 두면 구매금액에 상관없이 사용이 가능합니다)</font>
			</td>
		</tr>
		<tr>
			<td>게시여부</td>
			<td>
				<input type="radio" name="open" id="open_y" value="1" <c:if test="${1 == couponInfo.open}">checked</c:if>> 게시  
				<input type="radio" name="open" id="open_n" value="0" <c:if test="${1 != couponInfo.open}">checked</c:if>> 게시취소 
			</td>
		</tr>
		<c:if test="${couponInfo.approvalstatus != null && '' != couponInfo.approvalstatus}">
			<tr>
				<td>승인여부</td>
				<td>
					상태 : ${couponInfo.approvalstatusnm}
					&nbsp;&nbsp;&nbsp;
					<input type="radio" name="statusRadio" id="approvalstatus_2" value="2" <c:if test="${'2' == couponInfo.approvalstatus}">checked</c:if>> 승인 
					<input type="radio" name="statusRadio" id="approvalstatus_3" value="3" <c:if test="${'3' == couponInfo.approvalstatus}">checked</c:if>> 반려
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<textarea name="memo" id="memo" style="width:350px">${couponInfo.memo}</textarea> 
				</td>
			</tr>
		</c:if>
	</table>

	<div class=button>
		<c:choose>
			<c:when test="${'register' == couponVO.mode}"><input type=image class="couponSubmitBtn" src="/resources/shop/admin/img/btn_register.gif"></c:when>
			<c:otherwise><input type=image class="couponSubmitBtn" src="/resources/shop/admin/img/btn_modify.gif"></c:otherwise>
		</c:choose>
		<a href="${ctx}/shop/admin/seller/sellerCouponList"><img src="/resources/shop/admin/img/btn_cancel.gif"></a>
	</div>
</form>
<script>chk_msg(0);chk_coupontype();displayLayer('priodid${couponInfo.priodtype}');</script><div style="padding-top:10px"></div>
<div id=MSG01>
	<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">회원직접다운로드쿠폰을 제외한 다른 쿠폰들은 발급받은 회원1명 당 쿠폰사용은 1회로 제한 됩니다.</td></tr>
	</table>
</div>
<script>cssRound('MSG01');</script>

<script>
linecss();
table_design_load();
</script>

<%@ include file="../common/bottom.jsp" %>
	</td>
</tr>
</table>