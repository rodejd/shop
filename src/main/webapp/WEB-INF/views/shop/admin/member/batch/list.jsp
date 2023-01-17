<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language=javascript src="/resources/shop/admin/common.js"></script>
<script language="javascript">
	/*
	 * php script
	 */
function iciSelect(obj){
	var row = obj.parentNode.parentNode;
	row.style.background = (obj.checked) ? "#F0F4FF" :"#FFFFFF";
}
function veiwFunc(fObj){
	var func;
	var areas = new Array('emoney', 'level', 'status', 'sms', 'email');
	for (i=0; i < fObj['func'].length; i++){
		if (fObj['func'][i].checked === false) {
			openLayer('obj' + areas[i],'none');
		} else if (fObj['func'][i].checked === true){
			openLayer('obj' + areas[i],'block');
			func = fObj['func'][i].value;
		}
	}
	if (func == 'sms') document.ifrmSms.location.reload();
	if (func == 'email'){
		fObj.target = "ifrmEmail";
		fObj.action = "email.jsp?ifrmScroll=1";
		fObj.submit();
	}
}
function chkFuncForm(fObj)
{
	var func;
	for (i=0; i < fObj['func'].length; i++){
		if (fObj['func'][i].checked === true) func = fObj['func'][i].value;
	}	
	if (func == 'email') return false;
	
	if (fObj['query'].value == ""){
		alert("일괄처리할 회원을 먼저 검색하세요.");
		return false;
	}
	
	/*
	if(document.getElementsByName("chk[]").length > 0) {
		var chkcnt = false;
		for(j=0; j < document.getElementsByName("chk[]").length; j++) {
			if(document.getElementsByName("chk[]")[j].checked == true) {
				chkcnt = true;
				break;
			}
		}

		if(!chkcnt) {
			alert("선택된 회원이 없습니다.");
			return false;
		}
	} else {
		alert("일괄처리할 회원을 먼저 검색하세요.");
		return false;
	}
	*/
	
	if (fObj['type'].value == "select" && isChked(document.getElementsByName('chk')) === false){
		if (document.getElementsByName('chk').length) document.getElementsByName('chk')[0].focus();
		return false;
	}
	
	
	if (func == 'emoney' && fObj['emoney'].value == ''){
		alert("적립금을 입력하세요.");
		fObj['emoney'].focus();
		return false;
	}
	if (func == 'emoney' && fObj['memo'].value == ''){
		alert("지급이유를 선택하세요.");
		fObj['memo'].focus();
		return false;
	}
	if (func == 'level' && fObj['level'].value == ''){
		alert("그룹을 선택하세요.");
		fObj['level'].focus();
		return false;
	}
	if (func == 'status' && fObj['status'][0].checked === false && fObj['status'][1].checked === false){
		alert("승인여부를 선택하세요.");
		fObj['status'][0].focus();
		return false;
	}
	/* if(func == 'sms'){
		if(fObj["msg"].value ==""){			
			alert("메세지를 입력해주세요.");
			fObj["msg"].focus();
		}
	} */
	fObj.target = (func == 'sms' ? "ifrmHidden" : "_self");
	fObj.action = ctx+"/shop/admin/member/indb?mode=batch_" + func;
	return true;
}

// 가입일,최종로그인(kkh)
function setEachDate(objStart, objEnd, from, to){		
	var objStart = document.getElementsByName(objStart);
	var objEnd = document.getElementsByName(objEnd);		
	
	objStart[0].value = (from) ? from : "";
	objEnd[0].value=(from) ? to : "";			
} 

function goPage(page){
	$('#pageNo').val(page);
	
	document.form1.submit();
}

function setPageNo(){
	$('#pageNo').val(1);
}

function goSend() {

	var fObj = document.fmList;
	var form1 = document.form1;
	
	var func;
	for (i=0; i < fObj['func'].length; i++){
		if (fObj['func'][i].checked === true){
			func = fObj['func'][i].value;
		}
	}
	
	//if (func == 'email') return false;
	
	/* if (fObj['query'].value == ""){
		alert("일괄처리할 회원을 먼저 검색하세요.");
		return false;
	} */
	
	//if (fObj['type'].value == "select" && isChked(document.getElementsByName('chk')) === false){
	if (fObj['type'].value == "4" && isChked(document.getElementsByName('chk')) === false){
		if (document.getElementsByName('chk').length){
			document.getElementsByName('chk')[0].focus();
			return;
		}
	}
	
	if (fObj['type'].value == "5"){
		$('#skey').val( form1['skey'].value );
		$('#sword').val( form1['sword'].value );
		$('#sstatus').val( form1['sstatus'].value );
		$('#slevel').val( form1['slevel'].value );
		$('#ssum_salemin').val( form1['ssum_salemin'].value );
		$('#ssum_salemax').val( form1['ssum_salemax'].value );
		$('#semoneymin').val( form1['semoneymin'].value );
		$('#semoneymax').val( form1['semoneymax'].value );
		$('#sregdt_0').val( form1['sregdt'][0].value );
		$('#sregdt_1').val( form1['sregdt'][1].value );
		$('#slastdt_0').val( form1['slastdt'][0].value );
		$('#slastdt_1').val( form1['slastdt'][1].value );
		$('#sex').val( form1['sex'].value );
		$('#mailling').val( form1['mailling'].value );
		$('#scnt_loginmin').val( form1['scnt_loginmin'].value );
		$('#scnt_loginmax').val( form1['scnt_loginmax'].value );
		$('#dormancy').val( form1['dormancy'].value );
		$('#birthtype').val( form1['birthtype'].value );
		$('#birthdatemin').val( form1['birthdatemin'].value );
		$('#birthdatemax').val( form1['birthdatemax'].value );
		$('#marriyn').val( form1['marriyn'].value );
		$('#marridatemin').val( form1['marridatemin'].value );
		$('#marridatemax').val( form1['marridatemax'].value );
	}
	
	if (func == 'emoney' && fObj['emoney'].value == ''){
		alert("적립금을 입력하세요.");
		fObj['emoney'].focus();
		return;
	}
	if (func == 'emoney' && fObj['memo'].value == ''){
		alert("지급이유를 선택하세요.");
		fObj['memo'].focus();
		return;
	}
	if (func == 'level' && fObj['level'].value == ''){
		alert("그룹을 선택하세요.");
		fObj['level'].focus();
		return;
	}
	if (func == 'status' && fObj['status'][0].checked === false && fObj['status'][1].checked === false){
		alert("승인여부를 선택하세요.");
		fObj['status'][0].focus();
		return;
	}
	if(func == 'sms'){
		if(fObj["msg"].value ==""){			
			alert("메세지를 입력해주세요.");
			fObj["msg"].focus();
			return;
		}
	}
	
	//if(func == 'sms'){
		ajaxCallJsonPost("/shop/admin/member/batch/indb.dojson", "fmList", function(result){
		//ajaxCallJsonPost("/shop/admin/member/sms/send.dojson", "fmList", function(result){
			alert(result.total + '건이 처리되었습니다.');
			//location.href="/shop/admin/member/batch/list?func=sms";
			
			goPage(1);
		});
	//}
}
</script>

<form name="form1">
<input type="hidden" name="func" value="${memberVO.func}" />
<input type="hidden" name="indicate" value="search" />
<input type="hidden" name="mode1" value="list" />
<input type="hidden" name="pageNo" id="pageNo" value="${memberVO.pageNo == '' ? 1 : memberVO.pageNo}" />

<div class="title title_top">회원일괄관리<span>각종 회원관련 항목들을 일괄로 처리할 수 있습니다</span> <%-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=member&no=9',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle></a> --%></div>
<div style="padding:10 0 5 5"><font class="def1" color="#000000"><b><font size="3">①</font> 먼저 아래에서 일괄처리할 회원을 검색합니다.</b></font></div>
<%@ include file="../_listForm.jsp" %> 

<div class="button_top"><input type="image" src="/resources/shop/admin/img/btn_search2.gif" onclick="setPageNo()"/></div>
<table width="100%">
<tr>
	<td class="pageInfo">
	<c:set var="pages" value="${(memberVO.rowCount*10) / (memberVO.pageSize*10)} " />
	<c:set var="pageCnt" value="${pages+(1-(pages%1))%1}" />
	<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
		총 <font class="ver8"><b>${memberVO.totalCount}</b>명, 검색 <b>${memberVO.rowCount}</b>명,<b>${memberVO.pageNo} Pages</b> of ${var3} <br>
	</td>
	<td align="right">
	<select name="sort" onchange="this.form.submit();">
		<option value="regdt desc" ${stringUtil:selected(memberVO.sort, "regdt desc")}>- 가입일 정렬↑</option>
		<option value="regdt asc" ${stringUtil:selected(memberVO.sort, "regdt asc")}>- 가입일 정렬↓</option>
		<option value="last_login desc" ${stringUtil:selected(memberVO.sort, "last_login desc")}>- 최종로그인 정렬↑</option>
		<option value="last_login asc" ${stringUtil:selected(memberVO.sort, "last_login asc")}>- 최종로그인 정렬↓</option>
		<option value="cnt_login desc" ${stringUtil:selected(memberVO.sort, "cnt_login desc")}>- 방문수 정렬↑</option>
		<option value="cnt_login asc" ${stringUtil:selected(memberVO.sort, "cnt_login asc")}>- 방문수 정렬↓</option>
	    <optgroup label="------------"></optgroup>
		<option value="name desc" ${stringUtil:selected(memberVO.sort, "name desc")}>- 이름 정렬↑</option>
		<option value="name asc" ${stringUtil:selected(memberVO.sort, "name asc")}>- 이름 정렬↓</option>
		<option value="m_id desc" ${stringUtil:selected(memberVO.sort, "m_id desc")}>- 아이디 정렬↑</option>
		<option value="m_id asc" ${stringUtil:selected(memberVO.sort, "m_id asc")}>- 아이디 정렬↓</option>
	    <optgroup label="------------"></optgroup>
		<option value="emoney desc" ${memberVO.sort == 'emoney desc' ? "selected" : "" }>- 적립금 정렬↑</option>
		<option value="emoney asc" ${memberVO.sort == 'emoney asc' ? "selected" : "" }>- 적립금 정렬↓</option>
		<option value="sum_sale desc" ${memberVO.sort == 'sum_sale desc' ? "selected" : "" }>- 구매금액 정렬↑</option>
		<option value="sum_sale asc" ${memberVO.sort == 'sum_sale asc' ? "selected" : "" }>- 구매금액 정렬↓</option>
	</select>&nbsp;
	<select name="pageSize" onchange="this.form.submit();">
		<option value="10" ${stringUtil:selected(memberVO.pageSize, "10")}>10개 출력</option>
		<option value="20" ${stringUtil:selected(memberVO.pageSize, "20")}>20개 출력</option>
		<option value="40" ${stringUtil:selected(memberVO.pageSize, "40")}>40개 출력</option>
		<option value="60" ${stringUtil:selected(memberVO.pageSize, "60")}>60개 출력</option>
		<option value="100" ${stringUtil:selected(memberVO.pageSize, "100")}>100개 출력</option>
	</select>
	</td>
</tr>
</table>
</form>

<!-- <form name="fmList" method="post" onsubmit="return chkFuncForm(this)"> -->
<form name="fmList" id="fmList" method="post">
<%-- <input type=text name=mode>
<input type=text name=query value="<%=query%>"> --%>
<!-- 검색된 회원 일괄관리용 -->
<input type="hidden" name="skey" 				id="skey" 				/>
<input type="hidden" name="sword"             id="sword"           />
<input type="hidden" name="sstatus"           id="sstatus"         />
<input type="hidden" name="slevel"            id="slevel"          />
<input type="hidden" name="ssum_salemin"      id="ssum_salemin"    />
<input type="hidden" name="ssum_salemax"      id="ssum_salemax"    />
<input type="hidden" name="semoneymin"        id="semoneymin"      />
<input type="hidden" name="semoneymax"        id="semoneymax"      />
<input type="hidden" name="sregdt"            id="sregdt_0"          />
<input type="hidden" name="sregdt"            id="sregdt_1"          />
<input type="hidden" name="slastdt"           id="slastdt_0"         />
<input type="hidden" name="slastdt"           id="slastdt_1"         />
<input type="hidden" name="sex"               id="sex"             />
<input type="hidden" name="mailling"          id="mailling"        />
<input type="hidden" name="scnt_loginmin"     id="scnt_loginmin"   />
<input type="hidden" name="scnt_loginmax"     id="scnt_loginmax"   />
<input type="hidden" name="dormancy"          id="dormancy"        />
<input type="hidden" name="birthtype"         id="birthtype"       />
<input type="hidden" name="birthdatemin"      id="birthdatemin"    />
<input type="hidden" name="birthdatemax"      id="birthdatemax"    />
<input type="hidden" name="marriyn"           id="marriyn"         />
<input type="hidden" name="marridatemin"      id="marridatemin"    />
<input type="hidden" name="marridatemax"      id="marridatemax"    />



<table width=100% cellpadding=0 cellspacing=0 border=0>
<tr><td class=rnd colspan=13></td></tr>
<tr class=rndbg>
	<th><a href="javascript:chkBox(document.getElementsByName('chk'),'rev')" class=white>선택</a></th>
	<th>번호</th>
	<th>이름</th>
	<th>아이디</th>
	<th>그룹</th>
	<th>적립금</th>
	<th>구매금액</th>
	<th>방문수</th>
	<th>가입일</th>
	<th>최종로그인</th>
	<th>메일링</th>
	<th>승인</th>
</tr>
<tr><td class=rnd colspan=13></td></tr>
<col width=30 align=center>
<col width=60 align=center>
<col width=80 align=center span=3>
<col width=80 align=right span=2>
<col width=50 align=center>
<col width=80 align=center span=2>
<col width=50 align=center>
<col width=30 align=center>

<c:forEach items="${memberVO.gdMemberList}" var="list" varStatus="st">
<tr height=30 align="center">
	<td class="noline"><input type="checkbox" name="chk" value="${list.mno}" onclick="iciSelect(this);"></td>
	<td><font class="ver81" color="#616161">${(memberVO.rowCount - st.index) - ( memberVO.rowStart ) }</font></td>
	<td>
	<span id="navig" name="navig" m_id="${list.mid}" m_no="${list.mno}"><font color="#0074ba"><b>${list.name}</b></font></span>
	<c:if test="${not empty list.nickname }">
		<br /><div style="padding-top:2"><img src="/resources/shop/admin/img/icon_nic.gif" align="absmiddle" /><font class="small1" color="#488d00">${list.nickname}</font></div>
	</c:if>
	</td>
	<td><span id="navig" name="navig" m_id="${list.mid}" m_no="${list.mno}"><font class="ver81" color="#0074ba"><b>${list.mid}</b></font></span></td>
	
	<td>
		<c:forEach items="${codeUtil:getMemberGrp()}" var="mglist" varStatus="st">
			<c:if test="${mglist.kLevel == list.klevel}">
				<font class="def">${mglist.grpnm}</font><br>
			</c:if>
		</c:forEach>
	</td>
	<td align="center"><a href="javascript:popupLayer('${ctx}/shop/admin/member/popup.emoney?mno=${list.mno}',600,500);"><font class="ver81" color="#0074ba">₩<b>${stringUtil:getMoneyFormatInteger(list.emoney)}</b></font></a></td>
	<td align="center"><a href="javascript:popup('${ctx}/shop/admin/member/orderlist?mno=${list.mno}',500,600);"><font class="ver81" color="#0074ba">₩<b>${stringUtil:getMoneyFormatInteger(list.sumsale)}</b></font></a></td>
	<td><font class="ver81" color="#616161">${list.cntlogin}</font></td>
	<td><font class="ver81" color="#616161"><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd"/></font></td>
	<td><font class="ver81" color="#616161"><fmt:formatDate value="${list.lastlogin}" pattern="yyyy-MM-dd"/></font></td>
	<td><font class="small" color="#616161">${list.mailling == "y" ? "허용" : "거부" }</font></td>
	<td><font class="small" color="#616161">${list.status == "1" ? "승인" : "미승인" }</font></td>
</tr>
</c:forEach>

<tr><td colspan="14" class="rndline"></td></tr>
</table>

<!-- 페이징  -->
<table cellpadding="0" cellspacing="0" border="0" width="100%">
<tr>
	<td width="20%" height=35 style="padding-left:13px"><a href="javascript:chkBox(document.getElementsByName('chk'),'rev')"><img src="/resources/shop/admin/img/btn_allchoice.gif"></a></td>
	<td width="60%" align="center">
		<font class="ver8">
		<tags:paginator currentPageNo="${memberVO.pageNo}" rowCount="${memberVO.rowCount}" pageSize="${memberVO.pageSize}"  pageGroupSize="${memberVO.pageGroupSize}" />
		</font>
	</td>
	<td width="20%"></td>
</tr>
</table>

<div style="padding:20 0 5 5">
<table cellpadding=0 cellspacing=0 border=0>
<tr valign=top>
	<td>
	<font class="def1" color="#000000"><b><font size="3">②</font><b></font>
	<select name=type onChange="veiwFunc(this.form)">
	<option value="4">선택한 회원들에게</option>
	<option value="5">검색한 회원 모두에게</option>
	</select>
	</td>
	<td width=15></td>
	<td class=noline><font color=#000000><b>
	<div><label for="func1"><input type=radio name=func id=func1 onClick="veiwFunc(this.form)" value="emoney" ${stringUtil:checked(memberVO.func, 'emoney')}>적립금을 지급 또는 차감합니다.</label></div>
	<div><label for="func2"><input type=radio name=func id=func2 onClick="veiwFunc(this.form)" value="level" ${stringUtil:checked(memberVO.func, 'level')}>회원그룹을 변경합니다.</label></div>
	<%-- <div><label for="func3"><input type=radio name=func id=func3 onClick="veiwFunc(this.form)" value="status" ${stringUtil:checked(memberVO.func, 'status')}>회원승인상태를 변경합니다.</label></div> --%>
	<%-- <div><label for="func4"><input type=radio name=func id=func4 onClick="veiwFunc(this.form)" value="sms" ${stringUtil:checked(memberVO.func, 'sms')}>SMS를 발송합니다.</label></div> --%>	
	</b></td>
</tr>
</table>
</div>


<!-- 적립금지급/차감 : Start -->
<div id="objemoney" style="display:none; padding-top:30">
<div class="title title_top">적립금 일괄지급/차감<span>적립금을 지급/차감합니다</span></div>
<table class=tb>
<col class=cellC><col class=cellL>
<tr>
	<td>지급액/차감액</td>
	<td>
		₩<input type=text name=emoney size=8 style="rline" onkeyup="removeHangul(event);" maxlength="8"> 
		<font class=extext>(차감시에는 마이너스 금액으로 입력하세요) ex) </font><font class=ver7 color=627dce>-200 </font>
	</td>
</tr>
<tr>
	<td>이유</td>
	<td>
	<select name="memo" onchange="openLayer('direct', (this.value=='direct' ? 'block' : 'none') )" style="float:left;">
	<option value="">- 선택하세요 -</option>
	<option value="신규회원 가입">신규회원 가입</option>
	<option value="상품구매 시 포인트 적립">상품구매 시 포인트 적립</option>
	<option value="상품구매 시 포인트 사용">상품구매 시 포인트 사용</option>
	<option value="상품후기 작성 포인트 적립">상품후기 작성 포인트 적립</option>
	<option value="direct">☞ 직접입력</option>
	</select>
	<div id="direct" style="display:none;"><input type=text name=direct_memo size=30></div>
	</td>
</tr>
</table>
<div class=button_top><input type=image onclick="goSend(); return false;" src="/resources/shop/admin//img/btn_modify.gif"></div>
</div>
<!-- 적립금지급/차감 : End -->



<!-- 그룹조정 : Start -->
<div id="objlevel" style="display:none; padding-top:30">
<div class="title title_top">회원그룹 일괄변경<span>회원그룹을 변경합니다.</span></div>
<table class=tb>
<col class=cellC><col class=cellL>
<tr>
	<td>회원그룹</td>
	<td>
				
	<select name="level">
	<c:if test="${!empty codeUtil:getMemberGrp() && fn:length(codeUtil:getMemberGrp()) > 0 }">

		<c:forEach items="${codeUtil:getMemberGrp()}" var="mglist" varStatus="st">
					<option value="${mglist.kLevel}" ${stringUtil:selected(memberVO.slevel, fn:trim(mglist.kLevel))}>${mglist.grpnm} - lv[${mglist.kLevel}]</option>
		</c:forEach>
	</c:if>
	</select>

	</td>
</tr>
</table>
<div class=button_top><input type=image onclick="goSend(); return false;" src="/resources/shop/admin/img/btn_modify.gif"></div>
</div>
<!-- 그룹조정 : End -->



<!-- 승인변경 : Start -->
<div id="objstatus" style="display:none; padding-top:30">
<div class="title title_top">회원승인상태 일괄변경<span>승인상태를 변경합니다.</span></div>
<table class=tb>
<col class=cellC><col class=cellL>
<tr>
	<td>승인상태</td>
	<td class=noline>
	<input type=radio name=status value="1">승인
	<input type=radio name=status value="0">미승인
	</td>
</tr>
</table>
<div class=button_top><input type=image onclick="goSend(); return false;" src="/resources/shop/admin/img/btn_modify.gif"></div>

</div>
<!-- 승인변경 : End -->



<!-- SMS보내기 : Start -->
<div id="objsms" style="display:none; padding-top:30">
<div class="title title_top">SMS 발송하기<span>회원들에게 SMS를 전송합니다 </span></div>

<%@ include file="../sms/_smsForm.jsp" %>

</div>
<!-- SMS보내기 : End -->



<!-- 메일보내기 : Start -->
<!-- <div id="objemail" style="display:none; padding-top:30">
<iframe name=ifrmEmail style="width:100%;height:730px" frameborder=0></iframe>
<div id=MSG01>
<table cellpadding=1 cellspacing=0 border=0 class=small_tip>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle><font color=red>메일수신을 승낙한 회원에게만 메일을 보내려면</font> 위 검색항목 중 <font color=0074BA>메일수신여부에서 '수신'으로 선택하고 검색 후</font> 메일을 발송하시면 됩니다.</td></tr>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">메일 내용에 쓰인 이미지는 '디자인관리 > webFTP이미지관리 > data > editor'에서 이미지체크 후 삭제관리하세요.</td></tr>
</table>
</div>
<script language="JavaScript" type="text/JavaScript">cssRound('MSG01','#F7F7F7')</script>
</div> -->
<!-- 메일보내기 : End -->

</form>
<!-- <script language="JavaScript" type="text/JavaScript">veiwFunc(document.fmList);</script> -->
<script language="JavaScript" type="text/JavaScript">window.onload = function(){ (typeof(UNM) != "undefined" ? UNM.inner() : ''); veiwFunc(document.fmList);};</script>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>



<%@ include file="../../common/bottom.jsp" %>
		</td>
	</tr>
</table>