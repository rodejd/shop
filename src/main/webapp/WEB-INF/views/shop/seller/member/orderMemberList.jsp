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


</script> 

<div class="title title_top">
	구매회원리스트<br/>
	<ul>
		<li><span>해당 판매자의 상품을 구매한 회원의 목록입니다.</span></li>
		<li><span>배송완료후 구매 회원에 등록됩니다.</span></li>
	</ul>
</div>

<div style="padding-top:40px"></div>

<form name="search" id="sellerSearchForm">
 <div>
 	<table width="100%" cellpadding="0" cellspacing="0" border="0">
 		<tr>
		 	<td width="70%" align="left">구매자 ${sellerMemberFM.rowCount}명 정보가 등록되어 있습니다.</td>
		 	<td width="30%" align="right">
		 		<select name="schType">
					<option value="all">전체</option>
					<option value="name"${stringUtil:selected(sellerMemberFM.schText, "sName")}>이름</option>
					<option value="nickname"${stringUtil:selected(sellerMemberFM.schText, "sNickname")}>닉네임</option>
					<option value="mid"${stringUtil:selected(sellerMemberFM.schText, "sid")}>아이디</option>
				</select>
				<input type="text" name="schText" value="${sellerMemberFM.schText}" class="line">
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
		<col width="100">
		<col width="100">
		<col width="100">
		<col width="100">
		<col width="100">
		<col width="100">
		<col width="100">
		<col width="100">
		<tr class="rndbg">
			<th>No.</th>
			<th>이름</th>
			<th>닉네임</th>
			<th>아이디</th>
			<th>구매횟수</th>
			<th>성별</th>
			<th>생년월일</th>
			<th>직업</th>
			<th>mobile</th>
		</tr>
	<c:choose>
		<c:when test="${null != sellerMemberFM.sellerordermemberlist and 0 < sellerMemberFM.sellerordermemberlist.size()}">	
			<c:forEach items="${sellerMemberFM.sellerordermemberlist}" var="list" varStatus="i" >
				<tr height=30 align="center">
					<td>
						${(sellerMemberFM.pageNo-1)*10+i.count} 
					</td>
					<td>
						<font color="#0074ba"><b>${list.name}</b></font>
					</td>
					<td>
						${list.nickname }
					</td>
					<td>
						${list.mid }
					</td>
					<td>
						${list.ordercount }
					</td>
					<td>
						${list.sex=='m' ? '남자':''}
						${list.sex=='f' ? '여자':''}
					</td>
					<td>
						<c:if test="${list.birthyear!='' }">
						${list.birthyear}/${fn:substring(list.birth, 0, 2)}/${fn:substring(list.birth, 2, 4)}
						</c:if>
					</td>
					<td>
						${list.job }
					</td>
					<td>
						${list.mobile }
					</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr height=30 align="center">
				<td colspan="10">
					등록된 내용이 없습니다.
				</td>
			</tr>
		</c:otherwise>
	</c:choose>
	
	
	</table>
	
	<!-- 페이징  -->
	<tags:paginator currentPageNo="${sellerMemberFM.pageNo}" rowCount="${sellerMemberFM.rowCount}" pageSize="${sellerMemberFM.pageSize}"  pageGroupSize="${sellerMemberFM.pageGroupSize}" />


</form>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

