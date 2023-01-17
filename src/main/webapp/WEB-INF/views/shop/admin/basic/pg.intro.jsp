<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>


<script language="javascript">
var IntervarId;
function resizeFrame()
{
	var i_height = eval( window.status );
	var oFrame = document.getElementsByName("innaver")[0];
	i_height -= (oFrame.offsetHeight-oFrame.clientHeight);
	oFrame.style.height = i_height;
	window.status = "완료";

	if ( IntervarId ) clearInterval( IntervarId );
}
</script>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

	<div class="title title_top">전자지불서비스 안내 [필독]<span>올앳, 이니시스, 데이콤, KCP와 제휴를 맺고 전자지불서비스를 제공하고 있습니다.</span></div>
<%-- 	<iframe name="innaver" src="http://www.godo.co.kr/service/sub_06_02.php?iframe=yes&ifrParentDomain=<%=request.getServerName() %>" onLoad="IntervarId = setInterval( 'resizeFrame()', 100 );" frameborder="0" marginwidth="0" marginheight="0" width="100%" height="1000"></iframe> --%>
	
<%@ include file="../common/bottom.jsp" %>
	</td>
</tr>
</table>
<script>
linecss();
table_design_load();
</script>
