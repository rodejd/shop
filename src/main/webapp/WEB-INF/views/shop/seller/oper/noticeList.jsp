<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
	/*
	 * jQuery ready
	 */
	$(function(){
		// 검색버튼 클릭시
		$('#searchBtn').on('click', function(){
			$("#pageNo").val(1);
			$("#schType").val($('#tmpType options:selected').val());
			$("#schText").val($('#tmpText').val());
			$('#form').submit();
		});
		
		// 등록버튼 클릭
		$('#btn_register').on('click', function(){
			$('#form')
				.attr('action', ctx + '/shop/admin/seller/noticeInput')
				.submit();
		});
		
		//cssRound('MSG01');
	});
	
	function goPage(page){
		$("#pageNo").val(page);
		$('#form').submit();
	}
</script>

<%-- ================================================================================
* HTML
================================================================================ --%>

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<form id="form" method="get" action="${ctx }/shop/seller/oper/noticeList">
<input type=hidden id=pageNo name="pageNo" value="${sellerNoticeFM.pageNo}"/>

<input type=hidden id=schType name="schType" value="${sellerNoticeFM.schType}"/>
<input type=hidden id=schText name="schText" value="${sellerNoticeFM.schText}"/>

		<div class="title title_top">
			전체 공지사항
			<ul>
				<li><span>전체 쇼핑몰의 판매자들에게 공지를 하는 게시판입니다.</span></li>
			</ul>
			<br/>
		</div>
		<br/>
		<div>
		 	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		 		<tr>
				 	<td width="70%" align="left">검색된 목록 총 ${sellerNoticeFM.rowCount}건의 정보가 등록되어 있습니다.</td>
				 	<td width="30%" align="right">
				 		<select name="tmpType">
							<option value="" selected>전체</option>
							<option value="1" ${stringUtil:selected(sellerNoticeFM.schType, "1")}>제목</option>
							<option value="2" ${stringUtil:selected(sellerNoticeFM.schType, "2")}>내용</option>
						</select>
						<input type="text" name="tmpText" id="tmpText" value="${sellerNoticeFM.schText}" class="line">
						<input type="image" src="/resources/shop/admin/img/btn_search2.gif">
					</td>
				</tr>
			</table>
		</div>
		
		<table id="eventList" width=100% cellpadding=0 cellspacing=0> 
			<tr class=rndbg>
				<th width=100>NO.</th>
				<th>제목</th>
				<th width=100>등록자</th>
				<th width=100>등록일</th>
			</tr>
			<tr><td class=rnd colspan=4></td></tr>
<c:choose>
	<c:when test="${null != sellerNoticeFM.noticeList and 0 < sellerNoticeFM.noticeList.size()}">
		<c:forEach items="${sellerNoticeFM.noticeList}" var="list" varStatus="st">		
			<tr><td height=10 colspan=4></td></tr>
			<tr height=25>
				<td align=center style="padding-bottom:9"><font class=ver81>${(sellerNoticeFM.rowCount - st.index) - ( (noticeFM.pageNo - 1)  *  10 ) }</font></td>
				<td class=cell><a href="noticeView?noticeVO.sno=${list.sno}">${list.title}</a></td>
				<td align=center style="padding-bottom:5"><font class=ver8 color=EB4200>${list.regId}</font></td>
				<td align=center style="padding-bottom:5"><font class=ver8 color=EB4200>${list.regDt}</font></td>
			</tr>
			<tr><td colspan=4 class=rndline></td></tr>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr><td height=10 colspan=4></td></tr>
		<tr height=25>
			<td align=center colspan=4 style="padding-bottom:9"><font class=ver81>등록된 게시물이 없습니다.</font></td>
		</tr>
		<tr><td colspan=4 class=rndline></td></tr>
	</c:otherwise>
</c:choose>	
		</table>
		
		<!-- 페이징  -->
		<div align=center class=pageNavi>
			<tags:paginator currentPageNo="${sellerNoticeFM.pageNo}" rowCount="${sellerNoticeFM.rowCount}" pageSize="${sellerNoticeFM.pageSize}"  pageGroupSize="${sellerNoticeFM.pageGroupSize}" />
		</div>
		
</form>