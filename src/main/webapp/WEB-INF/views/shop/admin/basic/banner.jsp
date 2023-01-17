<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>


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
$( document ).ready(function() {
// 	$("#mainBannerTitleName").html($("#navi2").html());
// 	alert(menuName);
});
</script>
<form name="frm" id="frm"action="">
	<div class="title title_top">
		<c:choose>
		<c:when test="${bannerVO.type eq 'main1' }">
			메인1배너
		</c:when>
		<c:when test="${bannerVO.type eq 'main2' }">
			메인2배너
		</c:when>
		<c:when test="${bannerVO.type eq 'main3' }">
			메인3배너
		</c:when>
		<c:when test="${bannerVO.type eq 'main4' }">
			메인4배너
		</c:when>
		<c:otherwise>
			상단롤링배너
		</c:otherwise>
	</c:choose>
	</div>

	<table class="listTable">
		<col style="width:10%;" /><col /><col style="width:10%;" /><col style="width:10%;" /><col style="width:10%;" /><col style="width:10%;" /><col style="width:10%;" />
		<tr>
			<th>No.</th>
			<th>배너제목</th>
			<th>사용여부</th>
			<th>타겟</th>
			<th>우선순위</th>
			<th>등록일</th>
			<th>수정/삭제</th>
		</tr>
		<c:forEach items="${bannerVO.bannerList}" var="bannerList" varStatus="status">
		
		<tr class="trClass">
			<td>${(bannerVO.rowCount - status.index) - ( (bannerVO.pageNo - 1)  *  10 ) }</td>
			<td><a href="register?type=${bannerVO.type}&mode=modify&sno=${bannerList.sno }">${bannerList.title }</a></td>
			<td>${bannerList.used eq 'Y' ? '사용':'중지'} </td>
			<td>${bannerList.target eq '_self' ? '현재창':'새창'}</td>
			<td>${bannerList.sort }</td>
			<td><fmt:formatDate value="${bannerList.regdt}" pattern="yyyy.MM.dd"/></td>
			<td><a href="register?type=${bannerVO.type}&mode=modify&sno=${bannerList.sno }"><img src="/resources/shop/admin/img/i_edit.gif"></a> <a href="javascript:void(0);" onclick="javascript:banner_del('${bannerList.sno }','${bannerList.img }', '${bannerList.imgMobile }');"><img src="/resources/shop/admin/img/i_del.gif"></a></td>
		</tr>
	
		</c:forEach>
	</table>
	<!-- 페이징  -->
	<tags:paginator currentPageNo="${bannerVO.pageNo}" rowCount="${bannerVO.rowCount}" pageSize="${bannerVO.pageSize}"  pageGroupSize="${bannerVO.pageGroupSize}" />

	<div class="button">
		<a href="register?type=${bannerVO.type}&mode=register"><img src="/resources/shop/admin/img/btn_register.gif"></a>
	</div>
	<input type=hidden id=pageNo name="pageNo" value=""/>
	<input type=hidden id=type name="type" value="${bannerVO.type}"/>
</form>

<script>
	function banner_del(idx, img, imgMobile){
		if(confirm("정말 삭제하시겠습니까?") == true){
			
			location.href = 'indb?type=${bannerVO.type}&mode=delete&sno='+idx +'&oldImg='+img;
		}else{
			return;
		}
	}
	function goPage(page){
		$("#pageNo").val(page);
		document.frm.submit();
// 		window.location.href="list?type=${bannerVO.type}&pageNo="+page;
	}
</script>


<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>