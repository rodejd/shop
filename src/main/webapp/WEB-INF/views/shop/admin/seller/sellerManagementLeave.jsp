<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp"%>
<%@ include file="../common/left.jsp"%>

<%@ page import="static com.wepinit.wepinit_shop.xmall.common.ShopLibFunction.*"%>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language="javascript" src="/resources/shop/admin/common.js"></script>
<script language="javascript">

	// 선택 삭제
	function act_delete() {

		if ( PubChkSelect( fmList['confirmyn'] ) == false || PubChkSelect( fmList['confirmyn'] ) == undefined ){
			alert( "삭제하실 판매사를 선택하여 주십시요." );
			return;
		}
	
		if ( confirm( "선택한 판매자를 정말 삭제하시겠습니까?\n삭제 후 복구할 수 없습니다." ) == false ) return;
	
		var idx = 0;
		var codes = new Array();
		var count = fmList['confirmyn'].length;
	
		if ( count == undefined ) codes[ idx++ ] = fmList['confirmyn'].value;
		else {
	
			for ( i = 0; i < count ; i++ ){
				if ( fmList['confirmyn'][i].checked ) codes[ idx++ ] = fmList['confirmyn'][i].value;
			}
		}
	
		$("#nolist").val(codes.join( ";" ));
		$("#mode").val("delete");
		$("#fmList").attr("action", "../seller/indb");
		$("#fmList").submit();
		
	}
	
	// 페이지 이동
	function goPage(page){
		$("#pageNo").val(page);
		$('#fmList').submit();
	}
	

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

<div class="title title_top">
	판매자탈퇴목록<br/>
	<ul>
		<li><span>탈퇴신청을 한  공급업체 목록입니다.</span></li>
	</ul>
</div>

<div style="padding-top:40px"></div>

<form id="fmList" name="fmList" method="post" action="leaveList">

	<input type="hidden" id="pageNo" name="pageNo" value="${managementFM.pageNo}"/>
	<input type="hidden" id="mode"     name="mode" />
	<input type="hidden" id="sellerCd" name="sellerCd" />
	<input type="hidden" id="nolist"   name="nolist" />

	<div>
	 	<table width="100%" cellpadding="0" cellspacing="0" border="0">
	 		<tr>
			 	<td width="70%" align="left">총 ${managementFM.sellerObj.totalApp} 건의 탈퇴신청 공급업체가 있습니다.</td>
			 	<td width="30%" align="right">
			 		<select name="skey">
						<option value="all" ${stringUtil:selected(managementFM.skey, "all")}>전체</option>
						<option value="sId"	${stringUtil:selected(managementFM.skey, "sId")}>아이디</option>
						<option value="sSellerNm" ${stringUtil:selected(managementFM.skey, "sSellerNm")}>회사명</option>
						<option value="sCompanyRegNo" ${stringUtil:selected(managementFM.skey, "sCompanyRegNo")}>사업자번호</option>
						<option value="sManagerNm" ${stringUtil:selected(managementFM.skey, "sManagerNm")}>담당자</option>
						<option value="sManagerHp" ${stringUtil:selected(managementFM.skey, "sManagerHp")}>휴대전화번호</option>
						<option value="sManagerTel" ${stringUtil:selected(managementFM.skey, "sManagerTel")}>전화번호</option>
						<option value="sManagerEmail" ${stringUtil:selected(managementFM.skey, "sManagerEmail")}>이메일</option>
					</select>
					<input type="text" name="sword" value="${managementFM.sword}" class="line">
					<input type="image" src="/resources/shop/admin/img/btn_search2.gif">
				</td>
			</tr>
		</table>
	</div>

	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<col width="30">
		<col width="100">
		<col width="200">
		<col width="100">
		<col width="200">
		<col width="200">
		<col width="100">
		<col width="100">
		<col width="60">
		<tr class="rndbg">
			<th>번호</th>
			<th>아이디</th>
			<th>업체명</th>
			<th>담당자</th>
			<th>전화번호</th>
			<th>휴대폰</th>
			<th>신청일</th>
			<th>상세정보</th>
			<th>선택</th>
		</tr>
	
	<c:choose>
		<c:when test="${managementFM.sellerManagementList != null &&  fn:length(managementFM.sellerManagementList) >0 }">
			<c:forEach items="${managementFM.sellerManagementList}" var="list" varStatus="i">
				<tr height=30 align="center">
					<td>
						${(managementFM.pageNo-1)*10+i.count}
					</td>
					<td>
						${list.id}
					</td>
					<td>
						${list.sellerNm}
					</td>
					<td>
						${list.managerNm}
					</td>
					<td>
						${list.managerTel}
					</td>
					<td>
						${list.managerHp}
					</td>
					<td>
						<fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd hh:mm"/>
					</td>
					<td>
						<input type="image" class="button_top" src="/resources/shop/admin/img/codi/icon_imgview.gif" 	alt="상세보기" border="0" align='absmiddle' style="cursor:hand"
					   		   onclick="javascript:popupLayer('${ctx}/shop/admin/seller/sellerManagementPopup?sellerCd=${list.sellerCd}',800,600); return false;"/>
					</td>
					<td class="noline"><input type=checkbox name=confirmyn value="${list.sellerCd }"></td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr bgcolor="" height="10">
				<td colspan="9" align="center"> <br/> 탈퇴신청한 판매사가 존재하지 않습니다. </td>
			</tr>
		</c:otherwise>
	</c:choose>


	</table>
	
	
	
</form>
	<!-- 페이징  -->
	<tags:paginator currentPageNo="${managementFM.pageNo}" rowCount="${managementFM.rowCount}" pageSize="${managementFM.pageSize}"  pageGroupSize="${managementFM.pageGroupSize}" />

	<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td width="20%" height=35 style="padding-left:13px">
			</td>
			<td width="20%" height=35 align="right" style="padding-left:13px">
				<input type="image" class="button_top" src="/resources/shop/admin/img/btn_alldelet_s.gif" alt="선택삭제" border="0" align='absmiddle' style="cursor:hand"
					   onclick="javaScript:act_delete(); return false;"/>
				<!-- <input type="image" class="button_top" src="/resources/shop/admin/img/btn_leave_s.gif" 	alt="탈퇴승인" border="0" align='absmiddle' style="cursor:hand"
					   onclick="javaScript:act_confirm(); return false;"/> -->
			</td>
		</tr>
	</table>

<script>cssRound("MSG02");</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>


<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>