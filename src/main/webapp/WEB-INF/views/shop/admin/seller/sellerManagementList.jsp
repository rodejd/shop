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

	function dnXls(){
		// 엑셀 다운로드의 검색 조건은 주문내역의 검색 조건값을 사용합니다.
		$('form[name=frmDnXls]').empty();
		
		var domTarget = $('#fmList').serializeArray();
		$(domTarget).each(function(i, obj){
			$('<input>', {
			    type	: 'hidden',
			    name	: obj.name,
			    value	: obj.value
			}).appendTo('form[name=frmDnXls]');
		});
		
// 		$("#schType").val($('#tmpType options:selected').val());
// 		$("#schText").val($('#tmpText').val());
		
// 		alert($("select[name=skey]").val());
// 		alert($("[name='sword']").eq(0).val());
		
// 		return;
 		$('form[name=frmDnXls]').find('input[name="skey"]').val($("select[name=skey]").val());
 		$('form[name=frmDnXls]').find('input[name="sword"]').val($("[name='sword']").eq(0).val());
		$('form[name=frmDnXls]').attr('action', "../seller/sellerExcelDown");
		$('form[name=frmDnXls]').submit();
	}

	
	// 판매사 등록 페이지 이동
	function act_register() {
	
		$("#mode").val("");
		$("#fmList").attr("action", "../seller/register");
		$("#fmList").submit();

	}
	
	// 선택 삭제
	function act_delete() {

		if ( PubChkSelect( fmList['confirmyn'] ) == false || PubChkSelect( fmList['confirmyn'] ) == undefined ){
			alert( "삭제하실 판매사를 선택하여 주십시요." );
			return;
		}

		if ( confirm( "선택한 판매사를 정말 삭제하시겠습니까?\n삭제 후 복구할 수 없습니다." ) == false ) return;

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
	
	// 수정페이지로 이동
	function goModify(sellerCd) {
		
		$("#sellerCd").val(sellerCd);
		$("#mode").val("modify");
		$("#view").val("list");
		$("#fmList").attr("action", "../seller/register");
		$("#fmList").submit();

	}
	
	// 페이지 이동
	function goPage(page){
		$("#pageNo").val(page);
		$('#fmList').submit();
	}
	
	$( document ).ready(function() {

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

<div class="title title_top">
	판매자신청목록<br/>
</div>

<div style="padding-top:40px"></div>

<form id="fmList" name="fmList" method="post" action="list" >
	<input type="hidden" id="pageNo"   name="pageNo" value="${managementFM.pageNo}"/>
	<input type="hidden" id="mode"     name="mode" />
	<input type="hidden" id="sellerCd" name="sellerCd" />
	<input type="hidden" id="view"     name="view" />
	<input type="hidden" id="nolist"   name="nolist" />

	<div>
	 	<table width="100%" cellpadding="0" cellspacing="0" border="0">
	 		<tr>
			 	<td width="70%" align="left"/>
			 	<td width="30%" align="right">
			 		<select name="skey" id="skey">
						<option value="all" ${stringUtil:selected(managementFM.skey, "all")}>전체</option>
						<option value="sId"	${stringUtil:selected(managementFM.skey, "sId")}>아이디</option>
						<option value="sSellerNm" ${stringUtil:selected(managementFM.skey, "sSellerNm")}>회사명</option>
						<option value="sCompanyRegNo" ${stringUtil:selected(managementFM.skey, "sCompanyRegNo")}>사업자번호</option>
						<option value="sManagerNm" ${stringUtil:selected(managementFM.skey, "sManagerNm")}>담당자</option>
						<option value="sManagerHp" ${stringUtil:selected(managementFM.skey, "sManagerHp")}>휴대전화번호</option>
						<option value="sManagerTel" ${stringUtil:selected(managementFM.skey, "sManagerTel")}>전화번호</option>
						<option value="sManagerEmail" ${stringUtil:selected(managementFM.skey, "sManagerEmail")}>이메일</option>
					</select>
					<input type="text" name="sword" id="sword" value="${managementFM.sword}" class="line" />
					<input name="searchBtn" id="searchBtn" type="image" src="/resources/shop/admin/img/btn_search2.gif" />
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
			<th><a href="javascript:chkBox(document.getElementsByName('confirmyn'),'rev')" class=white>선택</a></th>
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
						<a href="#" onclick="javascript:goModify('${list.sellerCd}'); return false;" ><font color="#0074ba"><b>${list.sellerNm}</b></font></a>
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
					<td class="noline"><input type=checkbox name=confirmyn value="${list.sellerCd }"/></td>
				</tr>
			</c:forEach>
	
		</c:when>
		
		<c:otherwise>
			<tr><td height=10 colspan=9></td></tr>
			<tr height=25>
				<td align=center colspan=9 style="padding-bottom:9"><font class=ver81> 신청된 판매자가 존재하지 않습니다.</font></td>
			</tr>
			<tr><td colspan=9 class=rndline></td></tr>
		</c:otherwise>

	</c:choose>

	</table>

	<!-- 페이징  -->
	<div align=center class=pageNavi>
		<tags:paginator currentPageNo="${managementFM.pageNo}" rowCount="${managementFM.rowCount}" pageSize="${managementFM.pageSize}"  pageGroupSize="${managementFM.pageGroupSize}" />
	</div>

	<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td width="20%" height=35 style="padding-left:13px">
				<input type="image" class="button_top" src="/resources/shop/admin/img/btn_seller_add.gif" 	alt="판매사 등록" border="0" align='absmiddle' style="cursor:hand"
					   onclick="javaScript:act_register(); return false;">
			</td>
			
			<td width="20%" height=35 align="center" >
				<input type="image" class="button_top" src="/resources/shop/admin/img/btn_excel_download.gif" 	alt="엑셀다운로드" border="0" align='absmiddle' style="cursor:hand"
					   onclick="javaScript:dnXls(); return false;">
			</td>
			
			<td width="20%" height=35 align="right" style="padding-left:13px">
				<input type="image" class="button_top" src="/resources/shop/admin/img/btn_alldelet_s.gif" 	alt="선택삭제" border="0" align='absmiddle' style="cursor:hand"
					   onclick="javaScript:act_delete(); return false;">
			</td>
		</tr>
	</table>

</form>

<form name="frmDnXls" method="post">
	<input type="hidden" name="skey"/>
	<input type="hidden" name="sword"/>
</form>

<script>cssRound("MSG02");</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>


<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>