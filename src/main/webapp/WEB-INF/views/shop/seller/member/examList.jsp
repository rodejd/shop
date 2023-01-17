<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="/WEB-INF/views/shop/admin/common/header_popup.jsp" %>

<html>
<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<style>
body {margin:0}
</style>

<script>
/*
 * jQuery ready
 */
function goPage(page){
	window.location.href= ctx + "/shop/seller/member/examList?category=${sellerSmsFM.category}&pageNo="+page;
}

function selectMsg(msg)
{
	if(parent.document.forms[0].msg != null) fobj = parent.document.forms[0];
	else if(parent.document.forms[1].msg != null) fobj = parent.document.forms[1];
	fobj.msg.value = msg;
	parent.chkLength(fobj.msg);
}
</script>

<div style="width:584px">

<table cellpadding=0 cellspacing=0 border=0>
<tr>
<c:forEach items="${sellerSmsFM.gdSmsSampleList}" var="gdSmsSampleList" varStatus="st">	
	<td>
	<table>
	<tr>
		<td>

		<table width=146 cellpadding=0 cellspacing=0 border=0>
			<tr>
				<td height=41 background="/resources/shop/admin/img/sms_top.gif" align=right valign=top style="padding:15px 17px 0 0;font:8pt tahoma">

				</td>
			</tr>
			<tr>
				<td background="/resources/shop/admin/img/sms_bg.gif" align=center height="81">
					<textarea name=msg style="font:9pt 굴림체;overflow:hidden;border:0;background-color:transparent;width:98px;height:74px;" readonly class=hand onclick="selectMsg(this.value)">${gdSmsSampleList.msg}</textarea>
				</td>
			</tr>
			<tr>
				<td height=28 background="/resources/shop/admin/img/sms_bottom.gif" align=center>${gdSmsSampleList.subject}</td>
			</tr>
		</table>

		</td>
	</tr>
	</table>

	</td>
	<c:if test="${ (st.count % 4) == 0  }">
		</tr><tr>
	</c:if>
</c:forEach>
</tr>
</table>

<!-- 페이징  -->
<tags:paginator currentPageNo="${sellerSmsFM.pageNo}" rowCount="${sellerSmsFM.rowCount}" pageSize="${sellerSmsFM.pageSize}"  pageGroupSize="${sellerSmsFM.pageGroupSize}" />

</div>

<script>
window.onload = function(){
	parent.document.getElementById('ifrmSms').style.height = document.body.scrollHeight;
}
</script>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<%-- ================================================================================
* 공통 HTML CONTENT 종료
================================================================================ --%>
