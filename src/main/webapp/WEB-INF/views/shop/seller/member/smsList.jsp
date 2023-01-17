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
	SMS 발송리스트<br/>
</div>

<div style="padding-top:40px"></div>

<form name="frmList" method="post" onsubmit="return chkForm(this)">

<!-- type1 = 개별발송 -->
<!-- 2 = 회원그룹별발송 -->
<!-- 3 = 전체회원발송 -->
<!-- 4 = SMS일괄발송(회원선택) -->
<!-- 5 = SMS일괄발송(검색) -->
<!-- 6 = SMS주소록 회원선택 -->
<!-- 7= SMS주소록 회원검색 -->


	<div>
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
	 		<tr>
			 	<td width="70%" align="left">SMS 발송 총 ${sellerSmsFM.rowCount} 건 정보가 등록되어 있습니다.</td>
			 	<td width="30%" align="right">
			 		<select name="skey">
						<option value="all">전체</option>
						<option value="sMsg"${stringUtil:selected(sellerSmsFM.skey, "sMsg")}>메시지</option>
<%-- 						<option value="nickname"${stringUtil:selected(sellerMemberFM.schText, "sNickname")}>닉네임</option> --%>
<%-- 						<option value="mid"${stringUtil:selected(sellerMemberFM.schText, "sid")}>아이디</option> --%>
					</select>
					<input type="text" name="sword" value="${sellerSmsFM.sword}" class="line">
					<input type="image" src="/resources/shop/admin/img/btn_search2.gif">
				</td>
			</tr>
		</table>
	</div>


	<input type=hidden id=pageNo name="pageNo" value="1"/>

	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<col width="30">
		<col width="100">
		<col width="100">
		<col width="100">
		<col width="100">
		<col width="100">
		<tr class="rndbg">
			<th>No.</th>
			<th>메시지</th>
			<th>타입</th>
			<th>휴대폰이름</th>
			<th>보낸횟수</th>
			<th>등록일시</th>
		</tr>
	<c:choose>
		<c:when test="${sellerSmsFM.sellerSmsList != null &&  fn:length(sellerSmsFM.sellerSmsList) >0 }">	
			<c:forEach items="${sellerSmsFM.sellerSmsList}" var="list" varStatus="i" >
				<tr height=30 align="center">
					<td>
						${(sellerSmsFM.pageNo-1)*10+i.count} 
					</td>
					<td>
						<font color="#0074ba"><b>
				        <c:choose>
							<c:when test="${fn:length(list.msg) > 31}">
				        		<c:out value="${fn:substring(list.msg,0,30)}"/>....
							</c:when>
				        	<c:otherwise>				
				        		<c:out value="${list.msg}"/>
				        	</c:otherwise> 
						</c:choose>
						</b></font>
					</td>
					<td>
						${list.type }
					</td>
					<td>
						${list.toTran }
					</td>
					<td>
						${list.cnt }
					</td>
					<td>
						${list.regdt }
					</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr height=30 align="center">
				<td colspan="6">
					등록된 내용이 없습니다.
				</td>
			</tr>
		</c:otherwise>
	</c:choose>
	
	
	</table>
	
	<!-- 페이징  -->
	<tags:paginator currentPageNo="${sellerSmsFM.pageNo}" rowCount="${sellerSmsFM.rowCount}" pageSize="${sellerSmsFM.pageSize}"  pageGroupSize="${sellerSmsFM.pageGroupSize}" />


</form>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

