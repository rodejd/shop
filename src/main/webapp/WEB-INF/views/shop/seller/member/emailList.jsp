<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language="javascript">

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


<script>
linecss();
table_design_load();
</script> 

<div class="title title_top">
	이메일 발송리스트<br/>
</div>

<div style="padding-top:40px"></div>

<form id="fmList" name="fmList" method="post" action="emailList" >
	<input type="hidden" id="pageNo"   name="pageNo" value="${sellerEmailFM.pageNo}"/>

	<div>
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
	 		<tr>
			 	<td width="70%" align="left">이메일 발송 총 ${sellerEmailFM.rowCount} 건 정보가 등록되어 있습니다.</td>
			 	<td width="30%" align="right">
			 		<select name="skey">
						<option value="all">전체</option>
						<option value="sToName"${stringUtil:selected(sellerEmailFM.skey, "sToName")}>받는사람</option>
						<option value="sToEmail"${stringUtil:selected(sellerMemberFM.skey, "sToEmail")}>받는 이메일주소</option>
						<option value="sFromName"${stringUtil:selected(sellerMemberFM.skey, "sFromName")}>보내는사람</option>
						<option value="sFromEmail"${stringUtil:selected(sellerMemberFM.skey, "sFromEmail")}>보내는 이메일주소</option>
						<option value="sSubject"${stringUtil:selected(sellerMemberFM.skey, "sSubject")}>이메일 제목</option>
						<option value="sContents"${stringUtil:selected(sellerMemberFM.skey, "sContents")}>이메일 내용</option>
					</select>
					<input type="text" name="sword" value="${sellerEmailFM.sword}" class="line">
					<input type="image" src="/resources/shop/admin/img/btn_search2.gif">
				</td>
			</tr>
		</table>
	</div>

	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<col width="30">
		<col width="100">
		<col width="100">
		<col width="100">
		<col width="100">
		<col width="100">
<%-- 		<col width="100"> --%>
<%-- 		<col width="100"> --%>
<%-- 		<col width="100"> --%>
		<col width="100">
		<col width="100">
		<col width="100">
		<col width="100">
		<col width="100">
		<col width="100">
		<tr class="rndbg">
			<th>No.</th>
			<th>받는사람</th>
			<th>받는 이메일주소</th>
			<th>보내는사람</th>
			<th>보내는 이메일주소</th>
			<th>이메일 제목</th>
<!-- 			<th>기타1</th> -->
<!-- 			<th>기타2</th> -->
<!-- 			<th>기타3</th> -->
			<th>이메일내용</th>
			<th>발송여부</th>
			<th>발송일자</th>
			<th>등록일자</th>
			<th>수신여부</th>
			<th>수신일자</th>
		</tr>
	<c:choose>
		<c:when test="${sellerEmailFM.sellerEmailList != null &&  fn:length(sellerEmailFM.sellerEmailList) >0 }">	
			<c:forEach items="${sellerEmailFM.sellerEmailList}" var="list" varStatus="i" >
				<tr height=30 align="center">
					<td>
						${(sellerEmailFM.pageNo-1)*10+i.count} 
					</td>
					<td>
						${list.toName }
					</td>
					<td>
						${list.toEmail }
					</td>
					<td>
						${list.fromName }
					</td>
					<td>
						${list.fromEmail }
					</td>
					<td>
						${list.subject }
					</td>
<!-- 					<td> -->
<%-- 						${list.etc1 } --%>
<!-- 					</td> -->
<!-- 					<td> -->
<%-- 						${list.etc2 } --%>
<!-- 					</td> -->
<!-- 					<td> -->
<%-- 						${list.etc3 } --%>
<!-- 					</td> -->
					<td>
						<font color="#0074ba"><b>
				        <c:choose>
							<c:when test="${fn:length(list.contents) > 31}">
				        		<c:out value="${fn:substring(list.contents,0,30)}"/>....
							</c:when>
				        	<c:otherwise>				
				        		<c:out value="${list.contents}"/>
				        	</c:otherwise> 
						</c:choose>
						</b></font>
					</td>
					<td>
						${list.tranYn }
					</td>
					<td>
						${list.tranDt }
					</td>
					<td>
						${list.regDt }
					</td>
					<td>
						${list.recvYn }
					</td>
					<td>
						${list.recvDt }
					</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr height=30 align="center">
				<td colspan="12">
					등록된 내용이 없습니다.
				</td>
			</tr>
		</c:otherwise>
	</c:choose>
	
	
	</table>
	
	<!-- 페이징  -->
	<tags:paginator currentPageNo="${sellerEmailFM.pageNo}" rowCount="${sellerEmailFM.rowCount}" pageSize="${sellerEmailFM.pageSize}"  pageGroupSize="${sellerEmailFM.pageGroupSize}" />


</form>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

