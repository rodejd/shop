<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="/WEB-INF/views/shop/admin/common/header_popup.jsp" %>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>

<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<div class="title title_top">SMS 주소록 정보</div>

<form name="frmMember" method="post" action="address_indb" onsubmit="return chkForm(this);">
<input type="hidden" name="mode" value="${smsAddressVO.mode}">
<c:if test="${ !empty smsAddressVO.smsAddressObj.sno }">
<input type="hidden" name="sno" value="${smsAddressVO.smsAddressObj.sno}">
</c:if>
<table class="tb">
<col class="cellC"><col class="cellL">
<tr>
	<td>그룹</td>
	<td>
	<div>
	<span class="noline"><input type="radio" name="grpChk" value="Def" checked />기존그룹명 : </span>
	
	<select name="smsGrp">
	<option value="">==그룹선택==</option>
	<c:forEach items="${smsAddressVO.smsAddressGroupList}" var="sagList" varStatus="st">
		<option value="${sagList.smsGrp}" ${stringUtil:selected(smsAddressVO.smsAddressObj.smsGrp, sagList.smsGrp )}>${sagList.smsGrp}</option>
	</c:forEach>
	</select>
	</div>
	
	<div>
	<span class="noline"><input type="radio" name="grpChk" value="New" />신규그룹명 : </span>
	<input type="text" NAME="smsGrpNew" value="" class="line"/>
	</div>
	</td>
</tr>
<tr>
	<td>이름</td>
	<td><input type="text" NAME="smsName" value="${smsAddressVO.smsAddressObj.smsName}<%-- <%=bEditFlag?rtItem.get("sms_name"):""%> --%>" required  class="line" /></td>
</tr>
<tr>
	<td>핸드폰번호</td>
	<td>
	
	<c:set var="smsMobile" value='${fn:split( smsAddressVO.smsAddressObj.smsMobile , "-") }'/>
	<c:forEach var="s1" items="${smsMobile}" varStatus="s">
		<c:if test="${s.count==1}"><c:set var="smsMobile1" value="${s1}"/></c:if>  
    	<c:if test="${s.count==2}"><c:set var="smsMobile2" value="${s1}"/></c:if>  
    	<c:if test="${s.count==3}"><c:set var="smsMobile3" value="${s1}"/></c:if>
	</c:forEach>
	<input type="text" NAME="smsMobile" size="4" maxlength="3" value="${smsMobile1}<%-- <%=bEditFlag?sms_mobile[0]:""%> --%>" onkeydown="onlynumber();" require  class="line" /> -
	<input type="text" NAME="smsMobile" size="4" maxlength="4" value="${smsMobile2}<%-- <%=bEditFlag?sms_mobile[1]:""%> --%>" onkeydown="onlynumber();" require class="line" /> -
	<input type="text" NAME="smsMobile" size="4" maxlength="4" value="${smsMobile3}<%-- <%=bEditFlag?sms_mobile[2]:""%> --%>" onkeydown="onlynumber();" require class="line" />
	</td>
</tr>
<tr>
	<td>성별</td>
	<td class="noline">
	<input type="radio" name="sex" value="M" ${stringUtil:checked(smsAddressVO.smsAddressObj.sex, 'M')} />남자
	<input type="radio" name="sex" value="F" ${stringUtil:checked(smsAddressVO.smsAddressObj.sex, 'F')} />여자
	</td>
</tr>
<tr>
	<td>비고</td>
	<td><input type="text" NAME="smsEtc" value="${smsAddressVO.smsAddressObj.smsEtc}" style="width:100%"  class="line" /></td>
</tr>
</table><p>

<div class="button_popup" align="center">
<input type="image" src="/resources/shop/admin/img/btn_confirm_s.gif" />
</div>

</form>

<script>table_design_load();</script>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>


<script language="javascript">
if( '${result}' == 1 ){
	parent.location.reload();
	parent.closeLayer();
}
</script>