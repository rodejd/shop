<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp"%>
<%@ include file="../common/left.jsp"%>

<%@ page import="static com.wepinit.wepinit_shop.xmall.common.ShopLibFunction.*"%>
<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script type="text/javascript">
function goPage(page){
	$('#pageNo').val(page);
	
	document.frmList.submit();
}
function checkdel(){
	if(confirm("선택한 문의를 삭제 하시겠습니까?")){
		document.frmList.action="questDelete";
		document.frmList.submit();
	}
	return false;
}
$(document).ready(function(){
	$('.js_allCheck').on('click', function(event) {
		if(this.checked) {
			$('input[name=delcheck]').prop('checked', true);
			event.stopPropagation();
		} else {
			$('input[name=delcheck]').prop('checked', false);
			event.stopPropagation();
		}
	});
});

</script>
<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<div class="title title_top">
관리자 문의내용<br/>
	<ul>
		<li><span>판매자에게 문의한 내용입니다.</span></li>
	</ul>
</div>

<div style="padding-top:40px"></div>

 <div>
 	<table width="100%" cellpadding="0" cellspacing="0" border="0">
 		<tr>
		 	<td width="70%" align="left">문의 ${adminMyritzQuestAnswerFM.rowCount }개 등록되어 있습니다.</td>
		</tr>
	</table>
 </div>
<form name="frmList" method="post" onsubmit="return chkForm(this)">
	<input type=hidden id=pageNo name="pageNo" value="1"/>

	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<col width="30">
		<col width="30">
		<col width="100">
		<col width="100">
		<col width="100">
		<col width="30">
		<tr class="rndbg">
			<th>No.</th>
			<th>업체</th>
			<th>제목</th>
			<th>내용</th>
			<th>등록일/수정일</th>
			<th>선택삭제 <input type="checkbox"  class="js_allCheck" ></th>
		</tr>
		<c:choose>
			<c:when test="${null != adminMyritzQuestAnswerFM.questionlist and 0 < adminMyritzQuestAnswerFM.questionlist.size()}">
				<c:forEach items="${adminMyritzQuestAnswerFM.questionlist}" var="list" varStatus="i" >
					
					<tr height=30 align="center">
						<td>
							${(adminMyritzQuestAnswerFM.pageNo-1)*10+i.count} 
						</td>
						<td>
							${list.myritzNm }
						</td>
						<td>
							<a href="${ctx}/shop/admin/myritz/adminanswer?schType=qno&schText=${list.sno} ">
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
							<a href="${ctx}/shop/admin/myritz/adminanswer?schType=qno&schText=${list.sno} ">
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
						<td><input type="checkbox" id=delcheck name=delcheck value="${list.sno}"></td>
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
	<tags:paginator currentPageNo="${adminMyritzQuestAnswerFM.pageNo}" rowCount="${adminMyritzQuestAnswerFM.rowCount}" pageSize="${adminMyritzQuestAnswerFM.pageSize}"  pageGroupSize="${adminMyritzQuestAnswerFM.pageGroupSize}" />

		<div style="padding:20px" align="center" class="noline">
			<div class="button">
				<a href="${ctx}/shop/admin/myritz/adminQuest">
					<img src="/resources/shop/admin/img/btn_register.gif">
				</a>
					<img src="/resources/shop/admin/img/btn_del.gif" onClick="checkdel()" style="cursor:pointer">
			</div>
		</div>
</form>
		
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