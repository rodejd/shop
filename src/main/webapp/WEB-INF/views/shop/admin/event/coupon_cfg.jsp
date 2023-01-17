<%--
/************************************************************************************
* 프로그램명 			: coupon_cfg.jsp
* 관련 SERVICE명 		: 
* 프로그램 내용 		: XMall > 관리자 > 이벤트/쿠폰관리 > 쿠폰기능설정 
* 작성자	   		 	: PMG
* 작성일자 				: 2017-01-23
**************************************************************************************
* 수정자  	수정일자	수정내용
*************************************************************************************
* CAR		2017-01-23	신규등록
************************************************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%-- ================================================================================
* 공통 상단 include
* 공통 java class 지정 및 변수 설정
================================================================================ --%>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
/*
 * jQuery ready
 */
/* $(function(){
	$('#submitBtn').on('click', function(){

		var _form = document.forms['couponCfgForm'];
		if (chkForm(_form)) {
			// 쿠폰기능사용여부
			$('input:radio[name="use_yn"]').each(function(){
				if($(this).prop("checked")) {
					$(this).val("y");
				} else {
					$(this).val("n");
				}
			});
			// 중복할인여부
			$('input:radio[name="range"]').each(function(){
				if($(this).prop("checked")) {
					$(this).val("y");
				} else {
					$(this).val("n");
				}
			});
			// 쿠폰 사용제한
			$('input:radio[name="cfgCoupon"]').each(function(){
				if($(this).prop("checked")) {
					$(this).val("y");
				} else {
					$(this).val("n");
				}
			});
			var setVals = ["","",""];
			setVals[0] = $('input:radio[name="use_yn"]').map(function(){
				return $(this).val();
			}).get().join('^'); 
			setVals[1] = $('input:radio[name="range"]').map(function(){
				return $(this).val();
			}).get().join('^'); 
			setVals[2] = $('input:radio[name="cfgCoupon"]').map(function(){
				return $(this).val();
			}).get().join('^'); 
			$('#cfgChkVal').val($(setVals).get().join('^'));
			
			$(_form).attr('action', "/shop/admin/event/indb.coupon");
			$(_form).submit();  
		}
	});
}); */
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

		<div class="title title_top">쿠폰기능설정<span>쿠폰사용 여부 및 기능을 설정합니다. </span></div>
		<form method=post name="couponCfgForm" action="indb.coupon">
			<input type="hidden" name="mode" value="config">
			<input type="hidden" name="cfgChkVal" id="cfgChkVal" value="">
			<table class=tb>
				<col class=cellC><col class=cellL>
				<tr>
					<td>쿠폰기능 사용여부</td>
					<%-- <td><input type=radio name="use_yn" value="1" class=null <c:if test="${ cfgcoupon[0] == 'y' }">checked</c:if> > 사용&nbsp; <input type=radio name="use_yn" value="0" class=null <c:if test="${ cfgcoupon[1] == 'y' }">checked</c:if> > 사용하지않음</td> --%>
					<c:set var="use_yn" value="${shopLibFunction:getProperty('use_yn')}" />
					<td><input type=radio name="use_yn" value="0" class=null <c:if test="${ use_yn == '0'}">checked</c:if> > 사용&nbsp; <input type=radio name="use_yn" value="1" class=null <c:if test="${ use_yn == '1'}">checked</c:if> > 사용하지않음</td>
				</tr>
				<tr>
					<td>중복할인여부</td>
					<%-- <td><input type=radio name="range" value="0" class=null <c:if test="${ cfgcoupon[2] == 'y' }">checked</c:if> > 쿠폰할인, 회원할인 동시사용 가능&nbsp; <input type=radio name="range" value="2" class=null <c:if test="${ cfgcoupon[3] == 'y' }">checked</c:if> > 쿠폰할인만 사용&nbsp; <input type=radio name="range" value="1" class=null <c:if test="${ cfgcoupon[4] == 'y' }">checked</c:if> > 회원할인만 사용 <div style="padding:4 0 0 25"><font class=extext>회원할인이란 회원그룹별로 적용되는 할인혜택입니다.</font></div></td> --%>
					<c:set var="range" value="${shopLibFunction:getProperty('range')}" />
					<td><input type=radio name="range" value="0" class=null <c:if test="${range == '0' }">checked</c:if> > 쿠폰할인, 회원할인 동시사용 가능&nbsp; <input type=radio name="range" value="1" class=null <c:if test="${range == '1' }">checked</c:if> > 쿠폰할인만 사용&nbsp; <input type=radio name="range" value="2" class=null <c:if test="${range == '2' }">checked</c:if> > 회원할인만 사용 <div style="padding:4 0 0 25"><font class=extext>회원할인이란 회원그룹별로 적용되는 할인혜택입니다.</font></div></td>
				</tr>
				<tr>
					<td colspan=2 bgcolor=white align=left valign=top>
						<div style="padding:3 0 0 15"><font class=extext>중복할인이란 쿠폰할인 또는 회원그룹에 따른 할인, 두가지 할인혜택을 제공 할 것인가를 정하는 정책입니다.</font></div>
						<div style="padding:2 0 0 15"><font class=extext>회원할인은 '회원관리 ⇒ <a href="${ctx}/shop/admin/member/group/list" target=_blank><u>회원그룹관리</u></a>' 메뉴를 통해 미리 특정회원들을 그룹으로 묶어서 구매시 할인혜택을 제공할 수 있습니다.</font></div>
						<div style="padding-top:10"></div>
					</td>
				</tr>
				<tr>
					<td>쿠폰 사용제한</td>
					<%-- <td><input type=radio name="cfgCoupon" value="1" class=null <c:if test="${ cfgcoupon[5] == 'y' }">checked</c:if> > 하나의 주문에 여러 쿠폰 사용가능&nbsp; <input type=radio name="cfgCoupon" value="0" class=null <c:if test="${ cfgcoupon[6] == 'y' }">checked</c:if> > 하나의 주문에는 오직 한개의 쿠폰만 사용</td> --%>
					<c:set var="coupon_double" value="${shopLibFunction:getProperty('double')}" />
					<td><input type=radio name="coupon_double" value="0" class=null <c:if test="${ coupon_double == '0' }">checked</c:if> > 하나의 주문에 여러 쿠폰 사용가능&nbsp; <input type=radio name="coupon_double" value="1" class=null <c:if test="${ coupon_double == '1' }">checked</c:if> > 하나의 주문에는 오직 한개의 쿠폰만 사용</td>
				</tr>
				<tr>
					<td colspan=2 bgcolor=white align=left valign=top>
						<div style="padding:3 0 0 15"><font  class=extext>하나의 주문에 쿠폰을 사용할 수 있는 갯수를 제한 할 수 있습니다.</font></div>
						<div style="padding:2 0 0 15"><font  class=extext>여러개의 쿠폰을 발행한 경우, 한 주문당 여러개의 쿠폰을 사용하게 할 것인지, 한개의 쿠폰만 사용하게 할 것인지 정합니다.</font></div>
						<div style="padding-top:10"></div>
					</td>
				</tr>
			</table>
			<div class=button>
			<input type=image src="/resources/shop/admin/img/btn_modify.gif" id="submitBtn">
			<!-- <a href="javascript:history.back();"><img src="/resources/shop/admin/img/btn_cancel.gif"></a> -->
			</div>
		</form>
		
		<script>
		linecss();
		table_design_load();
		</script>

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