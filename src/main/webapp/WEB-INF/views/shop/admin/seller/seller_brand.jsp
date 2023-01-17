<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp"%>
<%@ include file="../common/left.jsp"%>

<%@ page import="static com.wepinit.wepinit_shop.xmall.common.ShopLibFunction.*"%>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language="javascript">
// if(window.addEventListener) 
// {
// 	window.addEventListener('load',linecss,false); 
// }
// else 
// {
// 	window.attachEvent('onload',linecss); 
// }

// if( '${result}' == 1 ){
// 	parent.location.reload();
// 	parent.closeLayer();
// }
</script>

<style type="text/css">
	.test {
		background-color : aliceblue;
		color : maroon;
		font-family: arial;  
		font-weight: bold;
		border: 1px solid silver;
	}
	.aster {
		color : red;
		padding-left : 2px;
	}
</style>


<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>


<script>
linecss();
table_design_load();
function goPage(page){
	$('#pageNo').val(page);
	
	document.frmList.submit();
}


</script> 

<div class="title title_top">
	브랜드관리<br/>
	<ul>
		<li><span>등록을 원하는 브랜드가 있으시면 등록해 주세요.</span></li>
		<li><span>브랜드가 승인이 나면 상품등록 하실때 브랜드 필드에 추가됩니다.</span></li>
	</ul>
</div>

<div style="padding-top:40px"></div>

<form name="search" id="sellerSearchForm">
 <div>
 	<table width="100%" cellpadding="0" cellspacing="0" border="0">
 		<tr>
		 	<td width="70%" align="left">검색된 브랜드 목록 총 ${BrandVO.rowCount}건의 정보가 등록되어 있습니다.</td>
		 	<td width="30%" align="right">
		 		<select name="skey">
					<option value="all">전체</option>
					<option value="sBrandnm"${stringUtil:selected(BrandVO.skey, "sBrandnm")}>브랜드명</option>
					<option value="sSellerNm"${stringUtil:selected(BrandVO.skey, "sSellerNm")}>판매자명</option>
					
				</select>
				<input type="text" name="sword" value="${BrandVO.sword}" class="line">
				<input type="image" src="/resources/shop/admin/img/btn_search2.gif">
			</td>
		</tr>
	</table>
 </div>
</form>

<form name="frmList" method="post" onsubmit="return chkForm(this)">
	<input type=hidden id=pageNo name="pageNo" value="1"/>

	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<col width="30">
		<col width="100">
		<col width="200">
		<col width="200">
		<col width="200">
		<tr class="rndbg">
			<th>No.</th>
			<th>브랜드명</th>
			<th>판매자 명</th>
			<th>승인</th>
			<th>관리</th>
		</tr>
		
	<c:forEach items="${BrandVO.sellerBrandList}" var="list" varStatus="i" >
		<tr height=30 align="center">
			<td>
				${(BrandVO.pageNo-1)*10+i.count} 
			</td>
			<td>
				<font color="#0074ba"><b>${list.brandnm}</b></font>
			</td>
			<td>
				<c:choose>
				    <c:when test="${empty list.sellerNm}">
				        	관리자
				    </c:when>
				    <c:otherwise>
				        ${list.sellerNm}
				    </c:otherwise>
				</c:choose>
			</td>
			<td>
				<c:if test="${list.approvalStatus =='1'}">
					승인대기
				</c:if>
				<c:if test="${list.approvalStatus =='2'}">
					<font color="#0074ba">승인</font>
				</c:if>
				<c:if test="${list.approvalStatus =='3'}">
					<font color="red">반려</font>
				</c:if>
			</td>
			<td>
<!-- 			수정 삭제 -->
			
				<a href="${ctx}/shop/admin/seller/goodsBrand?brandno=${list.sno}&sellerCd=${list.sellerCd}" target="_blank">
				<img src="/resources/shop/admin/img/i_edit.gif" ></a>
				<a href="javascript:popupLayer('${ctx}/shop/admin/seller/popup.sellerDelBrand?brandno='+${list.sno});">
				<img src="/resources/shop/admin/img/i_del.gif"></a>
			</td>
			
		</tr>
	</c:forEach>

	</table>
	
	<!-- 페이징  -->
	<tags:paginator currentPageNo="${BrandVO.pageNo}" rowCount="${BrandVO.rowCount}" pageSize="${BrandVO.pageSize}"  pageGroupSize="${BrandVO.pageGroupSize}" />


</form>
<script>cssRound("MSG02");</script>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>


<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>