<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>

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
 
	/* 폼: 생성 */
	function formCreate(nm, mt, at, tg) {
		var f = document.createElement("form");
		f.name = nm;
		f.method = mt;
		f.action = at;
		f.target = tg ? tg : "_self";
		return f;
	}
	/* 폼: 인풋 생성 */
	function formInput(f, n, v) {
		var i = document.createElement("input");
		i.type = "hidden";
		i.name = n;
		i.value = v;
		f.insertBefore(i, null);
		return f;
	}
	/* 폼: 전송 */
	function formSubmit(f) {
		document.body.appendChild(f);
		f.submit();
	}

	function brandgo(brandno, sellerCd) {
		var frm = formCreate('frmbrand', 'post', 'brand', '');
		frm = formInput(frm, 'brandno', brandno);
		frm = formInput(frm, 'sellerCd', sellerCd);
		formSubmit(frm);
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

<form name="search" id="sellerSearchForm" >
 <div>
 	<table width="100%" cellpadding="0" cellspacing="0" border="0">
 		<tr>
		 	<td width="70%" align="left">검색된 브랜드 목록 총 ${BrandVO.rowCount}건의 정보가 등록되어 있습니다.</td>
		 	<td width="30%" align="right">
		 		<select name="sellerBrandVO.skey">
					<option value="all">전체</option>
					<option value="sBrandnm"${stringUtil:selected(BrandVO.skey, "sBrandnm")}>브랜드명</option>
<%-- 					<option value="sSellerNm"${stringUtil:selected(BrandVO.skey, "sSellerNm")}>판매자명 --%>
					
				</select>
				<input type="text" name="sellerBrandVO.sword" value="${BrandVO.sword}" class="line">
				<input type="image" src="/resources/shop/admin/img/btn_search2.gif">
			</td>
		</tr>
	</table>
 </div>
</form>

<form name="frmList" method="post" >
	<input type=hidden id=pageNo name="pageNo" value="1"/>

	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<col width="30">
		<col width="200">
		<col width="200">
		<col width="200">
		<col width="200">
		<tr class="rndbg">
			<th>No.</th>
			<th>브랜드명</th>
			<th>판매자 명</th>
			<th>승인</th>
			<th>관리</th>
			<th>관리자메모</th>
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
				${list.sellerNm }
			</td>
			<td>
				<c:if test="${list.approvalStatus =='1'}">
					승인요청
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
			
<%-- 				<a href="brand?brandno=${list.sno}&sellerCd=${list.sellerCd}"> --%>
				<img src="/resources/shop/admin/img/i_edit.gif" onclick="brandgo('${list.sno}','${list.sellerCd}')">
			</td>
			
			<td>
				${list.brandMemo  }
			</td>
		</tr>
	</c:forEach>
	
	<tr>
		<td colspan="6" align="right" >
			<br>
			<img src ="/shop/admin/img/btn_register.gif" onclick="brandgo('0','${list.sellerCd}')">
		</tr>
	</tr>
	
	</table>
	
	<!-- 페이징  -->
	<tags:paginator currentPageNo="${BrandVO.pageNo}" rowCount="${BrandVO.rowCount}" pageSize="${BrandVO.pageSize}"  pageGroupSize="${BrandVO.pageGroupSize}" />


</form>
<!-- <script>cssRound("MSG02");</script> -->
