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


<script Language=javascript>
/*** 추가관리자 ***/
function addfld(obj)
{
	var tb = document.getElementById(obj);
	oTr = tb.insertRow();
	oTd = oTr.insertCell();
	oTd.innerHTML = "<span>" + tb.rows[0].cells[0].getElementsByTagName('span')[0].innerHTML + "</span> <a href='javascript:void(0);' onClick='delfld(this)'><img src='/resources/shop/admin/img/i_del.gif' align='absmiddle' /></a>";
	
	/* 2017-09-01 추가 - 생성된 input의 value 값 지워주기 */
	var field = oTd.getElementsByTagName('span')[0];
	field.getElementsByTagName('input')[0].value ="";
	field.getElementsByTagName('input')[1].value ="";
	field.getElementsByTagName('input')[2].value ="";
	
	oTd = oTr.insertCell();
	oTd = oTr.insertCell();
}

function delfld(obj)
{
	var tb = obj.parentNode.parentNode.parentNode.parentNode;
	tb.deleteRow(obj.parentNode.parentNode.rowIndex);
}
</script>
<form method="post" action="auto_indb">
<input type="hidden" name="mode" value="auto" />

<div class="title title_top">
	<font face="굴림" color="black"><b>SMS</b></font> 관리자정보 입력<span>SMS 관리자정보를 입력하세요</span>
	<!-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=member&no=8',870,800)"><img src="../img/btn_q.gif" border="0" align="absmiddle" hspace="2" /></a> -->
</div>

<table class="tb">
<col class="cellC"><col class="cellL">
<tr>
	<td>SMS 비밀번호 인증</td>
	<td>
		<input type="password" name="smsPass" value="${codeUtil:getConfValue("smsPass") }" maxlength="4" onkeydown="onlynumber();"  class="line"/>
		<%-- <font class="extext"><a href="https://www.godo.co.kr/mygodo/login.php?returnURL=<%=returnURL%>" target="_blank"><font class=extext_l>[마이고도 > 나의 쇼핑몰]</font></a> 에서 비밀번호를 먼저 등록하고, 동일한 비밀번호를 입력하세요</font> --%>
	</td>
</tr>
<tr>
	<td>회신전화번호</td>
	<td>
	<c:set var="smsRecall" value='${fn:split( codeUtil:getConfValue("smsRecall"), "-") }'/>
	<c:forEach var="s1" items="${smsRecall}" varStatus="s">
		<c:if test="${s.count==1}"><c:set var="smsRecall1" value="${s1}"/></c:if>  
    	<c:if test="${s.count==2}"><c:set var="smsRecall2" value="${s1}"/></c:if>  
    	<c:if test="${s.count==3}"><c:set var="smsRecall3" value="${s1}"/></c:if>
	</c:forEach>
	<input type="text" name="smsRecall[]" size="4" maxlength="3" value="${smsRecall1 }" onkeydown="onlynumber();" class="line" /> -
	<input type="text" name="smsRecall[]" size="4" maxlength="4" value="${smsRecall2 }" onkeydown="onlynumber();"  class="line"/> -
	<input type="text" name="smsRecall[]" size="4" maxlength="4" value="${smsRecall3 }" onkeydown="onlynumber();"  class="line"/>
	<font class="extext">고객이 받는 메세지에 회신번호로 찍히는 전화번호 (샵 고객센터 전화번호)</font></td>
</tr>
<tr>
	<td>관리자 핸드폰</td>
	<td>
	<c:set var="smsAdmin" value='${fn:split( codeUtil:getConfValue("smsAdmin"), "-") }'/>
	<c:forEach var="s1" items="${smsAdmin}" varStatus="s">
		<c:if test="${s.count==1}"><c:set var="smsAdmin1" value="${s1}"/></c:if>  
    	<c:if test="${s.count==2}"><c:set var="smsAdmin2" value="${s1}"/></c:if>  
    	<c:if test="${s.count==3}"><c:set var="smsAdmin3" value="${s1}"/></c:if>
	</c:forEach>
	<input type="text" name="smsAdmin[]" size="4" maxlength="3" value="${smsAdmin1}" onkeydown="onlynumber();"  class="line"/> -
	<input type="text" name="smsAdmin[]" size="4" maxlength="4" value="${smsAdmin2}" onkeydown="onlynumber();"  class="line"/> -
	<input type="text" name="smsAdmin[]" size="4" maxlength="4" value="${smsAdmin3}" onkeydown="onlynumber();"  class="line"/>
	<font class="extext">관리자에게도 메세지를 통보할 때 필요한 전화번호 (관리자 핸드폰 번호)</td>
</tr>
<tr>
	<td>추가 관리자</td>
	<td>

	<table id="addadminField" cellpadding="0" cellspacing="0" border="0" style="border-collapse:collapse">
	<tr>
		<td>
		<c:set var="smsAddAdmin" value='${fn:split( codeUtil:getConfValue("smsAddAdmin") , "|") }'/>
		<c:forEach var="s1" items="${smsAddAdmin}" varStatus="s">
			<c:if test="${s.count==1}"><c:set var="smsAddAdmin0" value="${s1}"/></c:if>
		</c:forEach>
		
		<c:set var="smsAddAdmin" value='${fn:split( smsAddAdmin0, "-") }'/>
		<c:forEach var="s1" items="${smsAddAdmin}" varStatus="s">
			<c:if test="${s.count==1}"><c:set var="smsAddAdmin00" value="${s1}"/></c:if>
			<c:if test="${s.count==2}"><c:set var="smsAddAdmin01" value="${s1}"/></c:if>
			<c:if test="${s.count==3}"><c:set var="smsAddAdmin02" value="${s1}"/></c:if>
		</c:forEach>
		
		<span>
		<input type="text" name="smsAddAdmin1[]" size="4" maxlength="3" value="${smsAddAdmin00}" onkeydown="onlynumber();" class="line" /> -
		<input type="text" name="smsAddAdmin2[]" size="4" maxlength="4" value="${smsAddAdmin01}" onkeydown="onlynumber();" class="line" /> -
		<input type="text" name="smsAddAdmin3[]" size="4" maxlength="4" value="${smsAddAdmin02}" onkeydown="onlynumber();" class="line" />
		</span>
				<a href="javascript:addfld('addadminField');"><img src="/resources/shop/admin/img/i_add.gif" align="absmiddle" /></a>
		<font class="extext">관리자 이외의 추가로 받아야 할 담당자가 있을때 필요한 전화번호</td>

		</td>
	</tr>
	</table>
<c:set var="smsAddAdmin" value='${fn:split( codeUtil:getConfValue("smsAddAdmin") , "|") }'/>
<c:forEach var="s1" items="${smsAddAdmin}" varStatus="s">
<c:if test="${not s.first}">

<table id="addadminField${s}" cellpadding="0" cellspacing="0" border="0" style="border-collapse:collapse">
	<tr>
		<td>
		<a href="javascript:void(0);" onClick="delfld(this)"><img src="/resources/shop/admin/img/i_del.gif" align="absmiddle" /></a>
		<span>
		<c:set var="smsAddAdmin_i" value='${fn:split( s1, "-") }'/>
		<c:forEach var="t1" items="${smsAddAdmin_i}" varStatus="t">
			<c:if test="${t.count==1}"><c:set var="smsAddAdmin_0" value="${t1}"/></c:if>
			<c:if test="${t.count==2}"><c:set var="smsAddAdmin_1" value="${t1}"/></c:if>
			<c:if test="${t.count==3}"><c:set var="smsAddAdmin_2" value="${t1}"/></c:if>
		</c:forEach>
		
		<input type="text" name="smsAddAdmin1[]" size="4" maxlength="3" value="${smsAddAdmin_0}" onkeydown="onlynumber();" /> -
		<input type="text" name="smsAddAdmin2[]" size="4" maxlength="4" value="${smsAddAdmin_1}" onkeydown="onlynumber();" /> -
		<input type="text" name="smsAddAdmin3[]" size="4" maxlength="4" value="${smsAddAdmin_2}" onkeydown="onlynumber();" />
		<font class="extext">관리자이외 추가로 받아야 할 담당자가 있을때 필요한 전화번호</font>
		</span>
		</td>
	</tr>
	</table> 
</c:if>
</c:forEach>
	</td>
</tr>
</table>


<div id="MSG01">
<table cellpadding="1" cellspacing="0" border="0" class="small_ex">
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle" />회신번호란 고객에게 메세지를 발송시 회신번호로 찍히는 전화번호입니다. 상점전화번호 또는 핸드폰번호를 입력하세요</td></tr>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle" />관리자핸드폰은 아래 자동발송기능에서 관리자가 메세지를 받고자 할 때 필요합니다</td></tr>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle" />추가관리자는 관리자이외 추가로 받아야 할 담당자가 있을때 사용을 하실수 있습니다.</td></tr>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle" />SMS 포인트가 충전되어 있어야 발송이 가능합니다. <!-- <a href="sms/pay"><font color=white><u>[SMS 포인트 충전하기]</u></font></a> 에서 충전하세요 --></td></tr>
</table>
</div>
<script>cssRound('MSG01');</script>

<div style="padding-top:20px"></div>

<div class="title title_top">
	<font face="굴림" color="black"><b>SMS</b></font> 자동발송/문구설정 <span>아래 사항을 체크하시면 메세지가 자동발송됩니다. 내용을 수정한 후 등록버튼을 누르세요.</span>
</div>

<table width="800">
<tr>
<c:forEach items="${ smsAutoVO.gdSmsAutoSetList }" var="gdSmsAutoSetList" varStatus="st">
	<td>

	<table border="1" bordercolor="#cccccc" style="border-collapse:collapse;">
	<tr>
		<td colspan="2" class="noline" width="350" height="25">&nbsp;&nbsp;<font color="#0074ba"><b>${gdSmsAutoSetList.msgTitle}</b></font></td>
	</tr>
	<tr>
		<td align="center" style="padding-bottom:5px" valign="top">
		<!-- 회원접근거부여부(여(1), 부(0) -->

		<c:if test="${fn:substring( gdSmsAutoSetList.denySet, 0, 1) != 1 }">
		<table cellpadding="0" cellspacing="0">
		<tr><td><img src="/resources/shop/admin/img/sms_top.gif" /></td></tr>
		<tr>
			<td background="/resources/shop/admin/img/sms_bg.gif" align="center" height="81" align="center">
				<textarea name="auto[${gdSmsAutoSetList.smsType}]['msg_c']" cols="16" rows="5" style="font:9pt 굴림체;overflow:hidden;border:0;background-color:transparent;" onkeydown="return chkLength(this)">${gdSmsAutoSetList.msgC}</textarea>
			</td>
		</tr>
		<tr><td><img src="/resources/shop/admin/img/sms_bottom.gif" /></td></tr>
		<tr><td height=3></td></tr>
		</table>
		</c:if>
		<c:if test="${fn:substring( gdSmsAutoSetList.denySet, 0, 1) == 1 }">
		<img src="/resources/shop/admin/img/sms_only_admin.gif" />
		</c:if>
		<div><input type="checkbox" name="auto[${gdSmsAutoSetList.smsType}]['send_c']" ${stringUtil:checked(gdSmsAutoSetList.sendC, 'on')} ${ ( fn:substring( gdSmsAutoSetList.denySet, 0, 1) == 1 ) ? 'disabled' : ''} class="null" />고객에게 자동발송</div>

		</td>
		<td align="center" style="padding-bottom:5px" valign="top">
		
		<!-- 관리자접근거부여부(여(1), 부(0) -->

		<c:if test="${fn:substring( gdSmsAutoSetList.denySet, 1, 2) != 1 }">
			<div style="display:none;">
			 <table cellpadding="0" cellspacing="0">
			<tr><td><img src="/resources/shop/admin/img/sms_top.gif" /></td></tr>
			<tr>
				<td background="/resources/shop/admin/img/sms_bg.gif" align="center" height="81" align="center">
					<textarea name="auto[${gdSmsAutoSetList.smsType}]['msg_a']" cols="16" rows="5" style="font:9pt 굴림체;overflow:hidden;border:0;background-color:transparent;" onkeydown="return chkLength(this)"> ${gdSmsAutoSetList.msgA}</textarea>
				</td>
			</tr>
			<tr><td><img src="/resources/shop/admin/img/sms_bottom.gif" /></td></tr>
			<tr><td height=3></td></tr>
			</table>
		</c:if>
		<c:if test="${fn:substring( gdSmsAutoSetList.denySet, 1, 2) == 1 }">
			<div style="display:none;">
			<img src="/resources/shop/admin/img/sms_only_user.gif" />
			</div>
		</c:if>
		<div style="display:none;"><input type="checkbox" name="auto[${gdSmsAutoSetList.smsType}]['send_a']" ${stringUtil:checked(gdSmsAutoSetList.sendA, 'on')} ${ ( fn:substring( gdSmsAutoSetList.denySet, 1, 2) == 1 ) ? 'disabled' : ''} class="null" />관리자에게도 발송</div>
		<div style="display:none;"><input type="checkbox" name="auto[${gdSmsAutoSetList.smsType}]['send_m']" ${stringUtil:checked(gdSmsAutoSetList.sendM, 'on')} ${ ( fn:substring( gdSmsAutoSetList.denySet, 2, 3) == 1 ) ? 'disabled' : ''} class="null" />추가관리자에게도 발송</div>
		</td>
	</tr>
	</table> 
	</div>

	</td>
	<c:if test="${ (st.index % 2) == 1  }">
		</tr><tr>
	</c:if>
</c:forEach>

</tr>
</table>

<div class="button">
<table width="800" border="0" align="left">
<tr><td width="380" align="right"><input type="image" src="/resources/shop/admin/img/btn_register.gif" /></td>
<td width="10"></td>
<td width="410" align="left"><!-- <a href="javascript:history.back();"><img src="/resources/shop/admin/img/btn_cancel.gif" /></a> --></td>
</tr></table>
</div>

</form>





<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>


<%@ include file="../../common/bottom.jsp" %>
		</td>
	</tr>
</table>