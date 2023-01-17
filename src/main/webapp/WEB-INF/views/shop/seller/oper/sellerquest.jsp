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
	관리자 문의<br/>
	<ul>
		<li><span>관리자로부터 판매자에게 궁금한 사항이 접수되었습니다. 답변 부탁드립니다.</span></li>
	</ul>
</div>

<div style="padding-top:40px"></div>

 <div>
 	<table width="100%" cellpadding="0" cellspacing="0" border="0">
 		<tr>
		 	<td width="70%" align="left">질문내용 ${sellerNoticeFM.rowCount }개 등록되어 있습니다.</td>
		</tr>
	</table>
 </div>

<form name="frmList" method="post" onsubmit="return chkForm(this)">
	<input type=hidden id=pageNo name="pageNo" value="1"/>

	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<col width="30">
		<col width="100">
		<col width="100">
		<col width="100">
		<tr class="rndbg">
			<th>No.</th>
			<th>제목</th>
			<th>내용</th>
			<th>등록일/수정일</th>
		</tr>
		<c:choose>
			<c:when test="${null != sellerNoticeFM.questionlist and 0 < sellerNoticeFM.questionlist.size()}">
				<c:forEach items="${sellerNoticeFM.questionlist}" var="list" varStatus="i" >
					
					<tr height=30 align="center">
						<td>
							${(sellerNoticeFM.pageNo-1)*10+i.count} 
						</td>
						<td>
							<a href="${ctx }/shop/seller/oper/selleranswer?schType=qno&schText=${list.sno} ">
							<c:choose>
									<c:when test="${fn:length(list.title) > 20}">
			       					 ${fn:substring(list.title,0,20)}...
			       					</c:when>
			        	 			<c:otherwise>
			    	     	  		 ${list.title}
				          	 		</c:otherwise> 
				           		</c:choose>
							</a>
						</td>
						<td>
							<a href="${ctx }/shop/seller/oper/selleranswer?schType=qno&schText=${list.sno} ">
								<c:choose>
									<c:when test="${fn:length(list.contents) > 20}">
			       					 ${fn:substring(list.contents,0,20)}...
			       					</c:when>
			        	 			<c:otherwise>
			    	     	  		 ${list.contents}
				          	 		</c:otherwise> 
				           		</c:choose>
				       		</a>
						</td>
						<td>
							${dateUtil:formatDate(list.regDt, "yyyy-MM-dd")}
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr height=30 align="center">
					<td colspan="4">
						등록된 내용이 없습니다.
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	
	<!-- 페이징  -->
	<tags:paginator currentPageNo="${sellerNoticeFM.pageNo}" rowCount="${sellerNoticeFM.rowCount}" pageSize="${sellerNoticeFM.pageSize}"  pageGroupSize="${sellerNoticeFM.pageGroupSize}" />


</form>
