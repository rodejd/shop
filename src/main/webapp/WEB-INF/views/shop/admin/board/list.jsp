<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- ================================================================================
* 공통 상단 include
* 공통 java class 지정 및 변수 설정
================================================================================ --%>


<%-- ================================================================================
* 공통변수의 할당 부분
================================================================================ --%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %> 
<%-- ================================================================================
* 업무 시작 try 부분
================================================================================ --%>

<%-- ================================================================================
* 업무별 거래 로직
================================================================================ --%>
<c:set var="vnum" value="${boardVO.vnum}"></c:set>
<c:set var="rtList" value="${boardVO.boardList}"></c:set>
<c:if test="${boardVO.event}">
	<script> alert('쿠폰관리 권한이 없습니다.');  location.href=ctx+'/shop/admin/basic/index';</script>
</c:if>
<c:if test="${boardVO.dropEvent}">
	<script> end(boardVO.id);</script>
</c:if>
<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language=javascript src="/resources/shop/admin/common.js"></script>

<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">

function end(id)
{
	alert(id+" 게시판이 정상적으로 삭제되었습니다.");
	parent.location.reload();
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
<!-- 2014.05.26 수정 start:매뉴얼 링크 주석처리 -->
<div class="title title_top">게시판리스트<span>생성된 게시판을 수정하고 관리합니다</span> <!-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=board&no=2',870,800)"><img src="../img/btn_q.gif" border=0 hspace=2 align=absmiddle></a> --></div>
<!-- 2014.05.26 수정 end-->

<div style="padding-top:5px"></div>
<div class="pageInfo ver8">총 <b>${boardVO.rowCount}</b> 게시판, <b>${boardVO.pageNo}</b>  of  ${boardVO.pageGroupSize } Pages</div>
<%-- <div class=pageInfo><font class=ver8>총 <b><%= pageUtil.getM_iTotalItems() %></b> 게시판, <b><%= pageUtil.getM_iTotalPages() %></b> of <%= pageUtil.getM_iCurrentPage() %> Pages</font></div> --%>

<table width=100% cellpadding=0 cellspacing=0>
<tr class=rndbg>
	<th>번호</th>
	<th>아이디</th>
	<th>게시판이름</th>
	<th>게시글수</th>
	<th>스킨타입</th>
	<th>사용자화면</th>
	<!--<th>정리</th>-->
	<th>수정</th>
	<th>삭제</th>
</tr>
<tr><td class="rnd" colspan="8"></td></tr>
<tr><td height="3" colspan="8"></td></tr>
<col width="100" align="center" span="2"/>
<col width="*" align="center"/>
<col width="80" align="center" span="5"/>
<c:set var="rtListSetup" value="${boardVO.boardSetupList}"></c:set>
 
<c:forEach items="${rtList}" var="list" varStatus="status">
	<tr height=30>
		<td align="center"><font class=ver8 color=444444>${boardVO.vnum[fn:length(rtList)-status.index-1]}</font></td>
		<td align="center"><a href="register?mode=modify&id=${list.id}"><font class=ver8 color=0074BA><b>${list.id}</b></font></a></td>
		<td align="center">${rtListSetup[status.index].bdName}</td>
		<td align="center"><font class=ver8>${list.num}</font></td>
		<td align="center"><font class=ver8>${rtListSetup[status.index].bdSkin}</font></td>
		<td align="center"><a href="../../board/list?id=${list.id}" target=_blank><img src="/resources/shop/admin/img/btn_viewbbs.gif" border=0></a></td>
		<!--<td><a href="indb.jsp?mode=inf&id=bbs" target=ifrmHidden>[정리]</a></td>-->
		<td align="center"><a href="register?mode=modify&id=${list.id}"><img src="/resources/shop/admin/img/i_edit.gif"></a></td>
		<td align="center">
		<c:if test="${list.id ne 'notice'}">
			<a href="board/indb?mode=drop&id=${list.id}" onclick="return confirm('삭제된 게시판은 복구가 불가능합니다. 정말로 삭제하시겠습니까?\n')" ><img src="/resources/shop/admin/img/i_del.gif"></a>
		</c:if>
		</td>
	</tr>
	<tr><td height=4 colspan=9></td></tr>
	<tr><td colspan=9 class=rndline></td></tr>
</c:forEach>
</table>

<%-- <div align=center class=pageNavi><font class=ver8> <b><%= page_list %></b> </font></div> --%>
<tags:paginator currentPageNo="${boardVO.pageNo}" rowCount="${boardVO.rowCount}" pageSize="${boardVO.pageSize}"  pageGroupSize="${boardVO.pageGroupSize}" />
<div id=MSG01>
<table cellpadding=2 cellspacing=0 border=0 class=small_ex>
<!--<tr><td><img src="../img/icon_list.gif" align=absmiddle>각 게시판관리는 <font color=0074BA>사용자페이지에서 직접 관리자가 관리</font>할 수 있습니다.</td></tr>-->
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>글올리기, 질문의 답변, 수정, 삭제 등은 사용자화면 <img src="/resources/shop/admin/img/btn_viewbbs.gif" align=absmiddle> 버튼을 눌러 사용자페이지의 게시판에서 직접 관리하세요.</td></tr>

</table>

</div>
<script>cssRound('MSG01');</script>

<div style="padding-top:15px"></div>


<!-- <script>cssRound('MSG02');</script> -->

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

<%-- ================================================================================
* 업무 시작 catch 부분
================================================================================ --%>

