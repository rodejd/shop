<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../../common/header.jsp" %>
<%@ include file="../../common/left.jsp" %>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language=javascript src="/resources/shop/admin/common.js"></script>

<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<script language="JavaScript" type="text/JavaScript">
function iciSelect(obj)
{
	var row = obj.parentNode.parentNode;
	row.style.background = (obj.checked) ? "#F0F4FF" :"#FFFFFF";
}

function delSMSAddress(fm)
{
	if (!isChked(document.getElementsByName('chk'))) return;
	if (!confirm('선택한 주소록을 정말로 하시겠습니까?')) return;
	fm.target = "_self";
	fm.mode.value = "delete";
	fm.action = "address_indb";
	fm.submit();
	
}

function inputMode(fm){
	
	fm.target = "ifrmHidden";
	fm.mode.value = "send_sms";
	fm.action = "indb.jsp";
}

function chkForm2(fm)
{
	if(fm.mode.value == "" || fm.mode.value == "send_sms"){
		inputMode(fm);
	
		var ckN=fm["type"].length;
		var chk;
		var chk_type="";
		for(chk = 0; chk < ckN; chk++){
			if (fm["type"][chk].checked == true){
				chk_type = fm["type"][chk].value;
			}
		}
		if (chk_type == ""){
			alert("SMS 발송방법을 선택해 주세요");
			return false;
		}else if (chk_type == "5"){
			if (!isChked(document.getElementsByName('chk[]'))) return;
		}
		
		chkForm(fm);
	}else{
		delSMSAddress(fm);
	}
}

function goSend() {

	alert('관리자에게 문의해주시기 바랍니다.');
	return false;
	
	var fObj = document.fmList;
	var form1 = document.form1;
	
	var ckN=fObj["type"].length;
	var chk_type="";
	for(i = 0; i < ckN; i++){
		if (fObj["type"][i].checked == true){
			chk_type = fObj["type"][i].value;
		}
	}
	
	if (chk_type == ""){
		alert("SMS 발송방법을 선택해 주세요");
		fObj["type"][0].focus();
		return;
	}
	
	if(fObj["msg"].value ==""){			
		alert("메세지를 입력해주세요.");
		fObj["msg"].focus();
		return;
	}
	
	if (fObj['type'].value == "6" && isChked(document.getElementsByName('chk')) === false){
		if (document.getElementsByName('chk').length){
			document.getElementsByName('chk')[0].focus();
			return;
		}
	} 
	
	if (fObj['type'].value == "7"){
		
		$('#skey').val( form1['skey'].value );
		$('#sword').val( form1['sword'].value );
		$('#slevel').val( form1['slevel'].value );
		$('#sregdt_0').val( form1['sregdt'][0].value );
		$('#sregdt_1').val( form1['sregdt'][1].value );
		
		$('#sex').val( form1['sex'].value );
	}
	
	ajaxCallJsonPost("/shop/admin/member/sms/send.dojson", "fmList", function(result){
		ajaxCallJsonPost("/shop/admin/member/sms/sendSmsAddress.dojson", "fmList", function(result){
		alert(result.total + '건이 처리되었습니다.');
		goPage(1);
		});
	})
}

function setPageNo(){
	$('#pageNo').val(1);
}

function goPage(page){
	$('#pageNo').val(page);
	
	document.form1.submit();
}
</script>


<form name="form1" method="post">

<input type="hidden" name="pageNo" id="pageNo" value="${smsAddressVO.pageNo == '' ? 1 : smsAddressVO.pageNo}" />

<div class="title title_top">SMS 주소록<span>현재 내 쇼핑몰의 SMS 주소록을 파악하고 SMS를 보낼 수 있습니다</span></div>
<table class="tb">
<col class="cellC"><col class="cellL" style="width:250">
<col class="cellC"><col class="cellL">
<tr>
	<td>키워드검색</td>
	<td>
	<select name="skey">	
		<option value="all" ${stringUtil:selected(smsAddressVO.skey, "all")}> 통합검색 </option>
		<option value="smsName" ${stringUtil:selected(smsAddressVO.skey, "smsName")}> 이름 </option>
		<option value="smsMobile" ${stringUtil:selected(smsAddressVO.skey, "smsMobile")}> 핸폰번호 </option>
	</select>
	<input type="text" name="sword" value="${smsAddressVO.sword}" class="line" />
	</td>
	<td>그룹</td>
	<td>
	<select name="slevel">
	<option value="">==그룹선택==</option>
	<c:forEach items="${smsAddressVO.smsAddressGroupList}" var="sagList" varStatus="st">
		<option value="${sagList.smsGrp}" ${stringUtil:selected(smsAddressVO.slevel, sagList.smsGrp )}>${sagList.smsGrp}</option>
	</c:forEach>
	</select>
	</td>
</tr>
<tr>
	<td>작성일</td>
	<td colspan="3">
	<input type="text" name="sregdt" value="${smsAddressVO.sregdt[0]}" size="10" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" onclick="calendar(event);" class="cline" /> ~
	<input type="text" name="sregdt" value="${smsAddressVO.sregdt[1]}" size="10" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" onclick="calendar(event);" class="cline" />
	<a href="javascript:setDate('sregdt', ${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align="absmiddle" /></a>
	<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFrom('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -7)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align="absmiddle" /></a>
	<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFrom('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -15)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align="absmiddle" /></a>
	<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'), -1)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align="absmiddle" /></a>
	<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -2)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align="absmiddle" /></a>
	<a href="javascript:setDate('sregdt')"><img src="/resources/shop/admin/img/sicon_all.gif" align="absmiddle" /></a>
	</td>
</tr>
<tr>
	<td>성별</td>
	<td class="noline">
	<input type="radio" name="sex" value="" ${stringUtil:checked(smsAddressVO.sex, '')}/>전체
	<input type="radio" name="sex" value="m" ${stringUtil:checked(smsAddressVO.sex, 'm')} />남자
	<input type="radio" name="sex" value="f" ${stringUtil:checked(smsAddressVO.sex, 'f')} />여자
	<%-- </td>
	<td>남은 SMS 건수</td>
	<td><span style="font-weight:bold"><font class="ver9" color="0074ba"><b>
	<fmt:formatNumber value="${codeUtil:getConfValue('smsPt')}" pattern="#,###,###,###"/>
	
	</b></span><font color="262626">건</font></td> --%>
</tr>																							
</table>


<div class="button_top">
<input type="image" src="/resources/shop/admin/img/btn_search2.gif" onclick="setPageNo()"/>
<a href="javascript:popupLayer('${ctx}/shop/admin/member/sms/popup_sms_address?mode=register',600,330)"><img src="/resources/shop/admin/img/btn_address_add.gif" /></a>
</div>

<table width="100%">
<tr>
	<td class="pageInfo">
		<c:set var="pages" value="${(smsAddressVO.rowCount*10) / (smsAddressVO.pageSize*10)} " />
		<c:set var="pageCnt" value="${pages+(1-(pages%1))%1}" />
		<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
	총 <font class="ver8"><b>${smsAddressVO.rowCount}</b>명, 검색 <b>${smsAddressVO.rowCount}</b>명,<b>${smsAddressVO.pageNo}</b> of ${var3} Pages <br>
	</td>
	<td align="right">
	<select name="sort" onchange="this.form.submit();">
	<option value="regdt desc" ${stringUtil:selected(smsAddressVO.sort, "regdt desc")}>- 작성일 정렬↑</option>
	<option value="regdt asc" ${stringUtil:selected(smsAddressVO.sort, "regdt asc")}>- 작성일 정렬↓</option>
    <optgroup label="------------"></optgroup>
    <option value="sms_grp desc" ${stringUtil:selected(smsAddressVO.sort, "sms_grp desc")}>- 그룹 정렬↑</option>
    <option value="sms_grp asc" ${stringUtil:selected(smsAddressVO.sort, "sms_grp asc")}>- 그룹 정렬↓</option>
    <option value="sms_name desc" ${stringUtil:selected(smsAddressVO.sort, "sms_name desc")}>- 이름 정렬↑</option>
    <option value="sms_name asc" ${stringUtil:selected(smsAddressVO.sort, "sms_name asc")}>- 이름 정렬↓</option>
    <option value="sms_mobile desc" ${stringUtil:selected(smsAddressVO.sort, "sms_mobile desc")}>- 핸드폰번호 정렬↑</option>
    <option value="sms_mobile asc" ${stringUtil:selected(smsAddressVO.sort, "sms_mobile asc")}>- 핸드폰번호 정렬↓</option>
	</select>&nbsp;
	<select name="pageSize" onchange="this.form.submit();">
		<option value="10" ${stringUtil:selected(smsAddressVO.pageSize, "10")}>10개 출력</option>
		<option value="20" ${stringUtil:selected(smsAddressVO.pageSize, "20")}>20개 출력</option>
		<option value="40" ${stringUtil:selected(smsAddressVO.pageSize, "40")}>40개 출력</option>
		<option value="60" ${stringUtil:selected(smsAddressVO.pageSize, "60")}>60개 출력</option>
		<option value="100" ${stringUtil:selected(smsAddressVO.pageSize, "100")}>100개 출력</option>
	</select>
	
	</td>
</tr>
</table>
</form>

<form name="fmList"  id="fmList" method="post"><!--  onsubmit="return chkForm2(this);"> -->
<input type="hidden" name="mode" />

<!-- 검색된 회원 일괄관리용 -->
<input type="hidden" name="skey" 				id="skey" 				/>
<input type="hidden" name="sword"             id="sword"           />
<input type="hidden" name="slevel"            id="slevel"          />
<input type="hidden" name="sregdt"            id="sregdt_0"          />
<input type="hidden" name="sregdt"            id="sregdt_1"          />
<input type="hidden" name="sex"               id="sex"             />

<table width="100%" cellpadding="0" cellspacing="0" border="0">
<tr><td class="rnd" colspan="14"></td></tr>
<tr class="rndbg">
	<th><a href="javascript:chkBox(document.getElementsByName('chk'),'rev');" class="white">선택</a></th>
	<th>번호</th>
	<th>그룹</th>
	<th>이름</th>
	<th>핸드폰번호</th>
	<th>성별</th>
	<th>비고</th>
	<th>작성일자</th>
	<th>수정</th>
</tr>
<tr><td class="rnd" colspan="14"></td></tr>
<col width="5%" align="center">
<col width="5%" align="center">
<col width="10%" align="center">
<col width="10%" align="center">
<col width="15%" align="left">
<col width="5%" align="center">
<col width="30%" align="left">
<col width="15%" align="center">
<col width="5%" align="center">
<%-- <%
	String sexStr="";
	for(int i=0;i<rtList.getRowCount();i++){
		
		if(rtList.get(i, "sex").equals("F")) sexStr	= "여성";
		else if(rtList.get(i, "sex").equals("M")) sexStr	= "남성";
		else sexStr = "";
%> --%>
<c:forEach items="${smsAddressVO.smsAddressList}" var="list" varStatus="st">
<tr height="30" align="center">
	<td class="noline"><input type="checkbox" name="chk" value="${list.sno}" onclick="iciSelect(this);"></td>
	<td><font class="ver81" color="616161">${(smsAddressVO.rowCount - st.index) - ( (smsAddressVO.pageNo - 1)  *  10 ) }</font></td>
	<td><font color="0074ba"><b>${list.smsGrp}</b></font></td>
	<td><font color="0074ba"><b>${list.smsName}</b></font></td>
	<td><font class=ver71 color="0074ba">${list.smsMobile}</font></td>
	<td><font class="ver81" color="616161">${list.sex == 'M' ? '남자' : '여자'}</font></td>
	<td><font class="small" color="616161">${list.smsEtc}</font></td>
	<td><font class="ver81" color="616161"><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd hh:mm a"/></font></td>
	<td><a href="javascript:popupLayer('${ctx}/shop/admin/member/sms/popup_sms_address?mode=modify&sno=${list.sno}',600,330)"><img src="/resources/shop/admin/img/i_edit.gif" /></a></td>
</tr>
<tr><td colspan="14" class="rndline"></td></tr>
<%-- <% } %> --%>
</c:forEach>
</table>

<table cellpadding="0" cellspacing="0" border="0" width="100%">
<tr><td width="20%" height="35" style="padding-left:35px"><a href="javascript:delSMSAddress(document.fmList)"><img src="/resources/shop/admin/img/btn_member_del.gif" border="0" /></a></td>
<td width="60%" align="center">
	<font class="ver8"><tags:paginator currentPageNo="${smsAddressVO.pageNo}" rowCount="${smsAddressVO.rowCount}" pageSize="${smsAddressVO.pageSize}"  pageGroupSize="${smsAddressVO.pageGroupSize}" /></font>
</td>
<td width="20%"></td>
</tr></table>
 

<div id="MSG01">
<table cellpadding="1" cellspacing="0" border="0" class="small_ex">
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle" />기본적인 회원 관리이외에 업체,제휴처, 친구등의 핸드폰번호를 관리 할수 있으며, 선택, 검색을 통해서 SMS를 보낼 수 있습니다.</td></tr>
</table>
</div>
<script language="JavaScript" type="text/JavaScript">cssRound('MSG01');</script>



<!-- SMS보내기 : Start -->
<div id="objsms" style="padding-top:30">
<div class="title title_top">SMS 발송하기</div>
<div class="noline"><label for="func1"><input type="radio" name="type" id="func1" onclick='inputMode(document.fmList);' value="6" /><font color="ea0095"><b>선택한 주소록 목록을 발송합니다.</b></font></label></div>
<div class="noline"><label for="func2"><input type="radio" name="type" id="func2" onclick='inputMode(document.fmList);' value="7" /><font color="ea0095"><b>검색한 주소록 목록을 발송합니다.</b></font></label></div>

<%@ include file="_smsForm.jsp" %>

<!-- SMS보내기 : End -->

</form>

<script language="JavaScript" type="text/JavaScript">window.onload = function(){ UNM.inner();};</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<script language="javascript">
if( '${result}' == 1 ){
	location.href=ctx+"/shop/admin/member/sms/address";
}
</script>


<%@ include file="../../common/bottom.jsp" %>
		</td>
	</tr>
</table>