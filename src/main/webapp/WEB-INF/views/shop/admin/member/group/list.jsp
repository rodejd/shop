<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../../common/header.jsp" %>
<%@ include file="../../common/left.jsp" %>

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
<div class="title title_top">회원그룹관리<span>회원별로 각각 다른 그룹을 만들어 차별화된 할인혜택을 제공할 수 있습니다 </span></div>
 
<%@ include file="groupForm.jsp" %> 


<div id="MSG01">
<table cellpadding="2" cellspacing="0" border="0" class="small_ex">
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle" />그룹별로 각기 다른 가격 할인율을 적용시키실 수 있습니다.</td></tr>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle" />관리자는 기본적으로 쇼핑몰의 회원이 되며 100 레벨의 그룹레벨을 갖게됩니다.</td></tr>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle" />관리자를 추가하려면 회원가입후 해당회원을 관리자 그룹으로 지정하시면 됩니다.</td></tr>
</table>
</div> 
<script>cssRound('MSG01');</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<%@ include file="../../common/bottom.jsp" %>
	</td>
</tr>
</table>
