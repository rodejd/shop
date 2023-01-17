<%--
/************************************************************************************
* 프로그램명 			: fieldset.jsp 
* 관련 SERVICE명 		: 
* 프로그램 내용 		: XMall > 관리자 > 회원관리 > 회원가입관리
* 작성자	   		 	: 이균
* 작성일자 				: 2014-01-10
*************************************************************************************
* 수정자  	수정일자	수정내용
*************************************************************************************
* 이균		2014-01-10	화면스펙조정
************************************************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page info="fieldset.jsp"  errorPage="/common/error_jsp.jsp"  %>

<%-- ================================================================================
* 공통 상단 include
* 공통 java class 지정 및 변수 설정
================================================================================ --%>
<%-- ================================================================================
* 공통변수의 할당 부분
================================================================================ --%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%-- ================================================================================
* 업무 시작 try 부분
================================================================================ --%>
<%-- <%@ include file="../../conf/fieldset.jsp" %> --%>
<%-- ================================================================================
* 업무별 거래 로직
================================================================================ --%>
<c:set var = "useFieldList" value="${fieldsetVO.useFieldList}"></c:set>
<c:set var="reqFieldList" value="${fieldsetVO.reqFieldList}"></c:set>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language=javascript src="/resources/shop/admin/common.js"></script>
<script language="javascript">
	/*
	 * php script
	 */
function chkBox2(El,mode,mode2)
{
	if (!El) return;
	for (var i=0;i<El.length;i++){
		El[i].checked = (mode=='rev') ? !El[i].checked : mode;
		chk(El[i].key,mode2);
	}
}

function chk(obj,mode)
{
		
	var objChkUse = document.getElementById("chkUseField["+obj+"]")
	var objChkReq = document.getElementById("chkReqField["+obj+"]");
	var objUseVal = document.getElementById("useField["+obj+"]");
	var objReqVal = document.getElementById("reqField["+obj+"]");
	
	if(mode=='use') {
		if(objChkUse.checked==true){
			objUseVal.value="checked";
		}else{
			objChkReq.checked = false;
			objReqVal.value="";
			objUseVal.value="";
		}
	}else if(mode=='req'){
		if(objChkReq.checked==true){
			objChkUse.checked = true;
			objUseVal.value="checked";
			objReqVal.value="checked";
		}else{
			objReqVal.value="";
		}
	}
}

</script>

<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

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

<form name="frmField" method="post" action="fieldset/indb">
<input type="hidden" name="mode" value="fieldset" />

<div class="title title_top">회원가입 정책관리<span>회원가입에 필요한 각종 정책을 정합니다</span> <%-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=member&no=3',870,800)"><img src="../img/btn_q.gif" border="0" hspace="2" align="absmiddle" /></a> --%></div>
<table class="tb">
<col class="cellC"><col class="cellL">
<tr>
	<td>회원인증절차</td>
	<td class="noline">
	<input type="radio" name="status" value="1" ${fieldsetVO.status eq '1' ? 'checked' : ''} />인증절차없음&nbsp;
	<input type="radio" name="status" value="0" ${fieldsetVO.status ne '1' ? 'checked' : ''} />관리자 인증 후 가입	<font class="extext">(관리자 승인 후 가입처리할 수 있습니다)</font></td>
</tr>
<tr>
	<td>회원재가입기간</td>
	<td>
	<div style="padding-top:5"></div>
	회원탈퇴 및 회원삭제 후 <input type="text" name="rejoin" value="${fieldsetVO.rejoin}" size="4" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" class="rline" /> 일 동안 재가입할 수 없습니다

	<div style="padding-top:5"></div>

	<table cellpadding="0" cellspacing="0" border="0">
	<tr><td height="5"></td></tr>
	<tr><td><font class="extext">아래 회원가입 항목 중에서 '주민등록번호'를 체크해야만 재가입 기간을 적용할 수 있습니다</font></td></tr>
	<tr><td style="padding: 2px 0px 0px 0px"><font class="extext">'회원탈퇴/삭제내역'에서 재가입여부를 확인할 수 있습니다.</font></td></tr>
	<tr><td height="5"></td></tr>
	</table>

	<div style="padding-top:5"></div>
	</td>
</tr>
<tr>
	<td>가입불가 ID</td>
	<td>
	<textarea name="unableid" style="width:100%;height:60px" class="tline">${fieldsetVO.unableid}</textarea>

	<table cellpadding="0" cellspacing="0" border="0">
	<tr><td height="5"></td></tr>
	<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle" /><font class="extext">회원가입을 제한할 ID를 입력하세요. 컴마로 구분합니다</font></td></tr>
	<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle" /><font class="extext">주요 제한 ID : </font><font class=ver7 color=627dce>admin,administration,administrator,master,webmaster,manage,manager</font></td></tr>
	<tr><td height="5"></td></tr>
	</table>

	</td>
</tr>
<tr>
	<td width=125>회원가입시 적립금지급</td>
	<td>₩<input type="text" name="emoney" value="${fieldsetVO.emoney}" size="10" class="rline" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" /> <font class="extext">(미적용시 0 입력)</font></td>
</tr>
<tr>
	<td>회원가입시 쿠폰지급</td>
	<td>회원가입쿠폰을 제공하고 싶다면 <a href="../event/coupon_register" target=_blank><font class=extext_l>[쿠폰만들기]</font></a> 에서 쿠폰을 발행하세요. '회원가입자동발급' 방식으로 발급하세요</td>
</tr>
<tr>
	<td>가입시 회원그룹</td>
	<td>회원가입 후 바로 
	
	<c:set var="grp" value="${fieldsetVO.grp}"></c:set>
	<select name="grp">
		<!-- <option value="">↓그룹선택</option> -->
		<c:forEach items="${codeUtil:getMemberGrp()}" var="mglist" varStatus="st">
			<c:if test="${mglist.kLevel < 80 }">
				<option value="${mglist.kLevel}" ${stringUtil:selected(fieldsetVO.grp, fn:trim(mglist.kLevel))}>${mglist.grpnm} - lv[${mglist.kLevel}]</option>
			</c:if>
		</c:forEach>
	</select>
	
<%-- 	<select name="grp"> 
	<c:set var="grp" value="${fieldsetVO.grp}"></c:set>
	<c:set var="garr" value="${fieldsetVO.garr}"></c:set>
	<c:forEach items="${garr}" var="map" varStatus="status">
		<option value="${map.key}" ${grp eq map.value ? 'selected' : ''}> ${map.value} - lv[${map.key}]</option>
	</c:forEach>
	</select> --%> 
	그룹에 속하도록 합니다. <font class="extext">('회원그룹관리'에서 그룹을 만드세요)</font> &nbsp;<a href="../member/group/list" target="_new"><font class="extext_l">[그룹관리바로가기]</font></a></td>
</tr>
<tr>
	<td>추천인 설정</td>
	<td>
	  <div>신규가입고객이 기입한 추천인에게 적립금 ₩<input type="text" name="recommEmoney" value="${fieldsetVO.recommEmoney}" size="10" class="rline" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" /> 지급 <font class="extext">(미적용시 0 입력)</font></div>
	  <div>신규가입시 추천인을 기입하면 적립금 ₩<input type="text" name="recommAddEmoney" value="${fieldsetVO.recommAddEmoney}" size="10" class="rline" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" /> 추가 지급 <font class="extext">(미적용시 0 입력)</font></div>
	  
	</td>
</tr>
<%-- 
<tr height="30">
	<td>실명확인 <a href="./realname.jsp" target="_new"><img src="../img/btn_question.gif" /></a></td>
	<td>
	<%
	if(realname.get("useyn").equals("y") && !realname.get("id").equals("")){
	%>
	<div>정확한 실명확인 절차로 회원DB의 정확도와 쇼핑몰의 신뢰도가 높아집니다.&nbsp;&nbsp;<a href="./realname.jsp" target="_new"><font class=extext_l>[실명확인관리 바로가기]</font></a></div>
	<%
	}else{
	%>
	<div>정확한 실명확인 절차로 회원DB의 정확도와 쇼핑몰의 신뢰도가 높아집니다.</div>
	<div>현재 실명확인 서비스를 <img src="../img/btn_nouse.gif" align="absmiddle">이십니다.</div>
	<div class="extext_t">서비스 개시를 위해 약정서를 한국신용정보㈜로 보내주세요&nbsp;&nbsp;<a href="./realname.jsp" target="_new"><font class=extext_l>[실명확인관리 바로가기]</font></a></div>
	<%
	}
	%>	
	</td>
</tr>
 --%>
</table>




<div class="title">회원가입 항목관리<span>회원가입에 필요한 각종 항목 및 옵션을 정합니다</span> <%-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=member&no=3',870,800)"><img src="../img/btn_q.gif" border="0" hspace="2" align="absmiddle" /></a> --%></div>

<table width="100%" border="1" bordercolor="#efefef" style="border-collapse:collapse">
<tr height="25" bgcolor="#f7f7f7">
	<th>필드명</th>
	<th><a href="javascript:void(0)" onclick="chkBox2(document.frmField.elements['chkUse[]'],'rev','use');">사용여부</a></th>
	<th><a href="javascript:void(0)" onclick="chkBox2(document.frmField.elements['chkReq[]'],'rev','req');">필수사항</a></th>
	<th>필드명</th>
	<th><a href="javascript:void(0)" onclick="chkBox2(document.frmField.elements['chkUse[]'],'rev','use');">사용여부</a></th>
	<th><a href="javascript:void(0)" onclick="chkBox2(document.frmField.elements['chkReq[]'],'rev','req');">필수사항</a></th>
</tr>
<col align="center" width="20%" bgcolor="#f7f7f7"><col align="center" width="15%" span="2">
<col align="center" width="20%" bgcolor="#f7f7f7"><col align="center" width="15%" span="2">
<tbody style="height:25">

	 <c:if test="${fn:length(useFieldList)>0}">
	 	<c:set var="idx" value="0"></c:set>
	 	<c:forEach items="${useFieldList}" var="list" varStatus="ustatus">
	 	
	
	<td align=left style="padding-left:10px">${list.dim2Var}(<font class=ver7 color='3853a5'>${list.dim1Var}</font>)</td>
	<td class="noline">
		<font class="def">
			<input type="hidden" id="useField[${list.dim1Var}]" name="useField" value="${list.dimVal}" />
			<input type="hidden" id="useFieldName[${list.dim1Var}]" name="useFieldName" value="${list.dim1Var}" />
			<input type="checkbox" id="chkUseField[${list.dim1Var}]" name="chkUseField" ${list.dimVal} key="${list.dim1Var}" onClick="chk('${list.dim1Var}','use');" value="checked"/> 사용
		</font>
	</td>
	<%-- <td class="noline"><font class="def"><input type="checkbox" id="chkReq[]" name="reqField[<%=useFieldList.get(i, "dim1_var")%>]" <%=ShopConfig.getProperty("fieldset", "reqField", useFieldList.get(i, "dim1_var"))%> key="<%=useFieldList.get(i, "dim1_var")%>" onClick="chk('<%=useFieldList.get(i, "dim1_var")%>','req');" /> 필수</font></td> --%>
	<td class="noline">
		<font class="def">
			<c:forEach items="${reqFieldList}" var="rlist" varStatus="rstatus">
				<c:if test="${list.dim1Var eq rlist.dim1Var}">
					<input type="hidden" name="flag" value="req" />
					<input type="hidden" id="reqField[${list.dim1Var}]" name="reqField" value="${rlist.dimVal}" />
					<input type="hidden" id="reqFieldName[${list.dim1Var}]" name="reqFieldName" value="${rlist.dim1Var}" />
				</c:if>
			</c:forEach>
			<c:forEach items="${reqFieldList}" var="rlist" varStatus="rstatus">
			 	<c:if test="${list.dim1Var eq rlist.dim1Var}">
					<input type="checkbox" id="chkReqField[${list.dim1Var}]" name="chkReqField" ${rlist.dimVal} key="${list.dim1Var}" onClick="chk('${list.dim1Var}','req');" /> 필수 ${fn:length(reqFieldList) }
				</c:if>
			</c:forEach>
		</font>
	</td>
	<c:if test="${(ustatus.index+1)%2 == 0}">
		<tr></tr>
	</c:if>
	</c:forEach>
</c:if>

</tr>
</table>

<div class="button">
<input type="image" src="/resources/shop/admin/img/btn_register.gif" />
<!-- <a href="javascript:history.back();"><img src="../img/btn_cancel.gif" /></a> -->
</div>

</form>

	<div id="MSG02">
	<table cellpadding="1" cellspacing="0" border="0" class="small_ex">
	<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle" />회원가입시 입력하는 항목들을 정하는 곳입니다.</td></tr>
	<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle" />원하는 필드항목을 체크하고, 필수사항인지의 여부를 체크하시면 됩니다. 추가로 항목을 만드실 수도 있습니다.</td></tr>
	</table>
	</div>
	<script>cssRound('MSG02');</script>

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






