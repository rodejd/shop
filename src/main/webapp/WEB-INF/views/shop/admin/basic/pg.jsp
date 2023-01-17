<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script language=javascript src="/resources/shop/admin/common.js"></script>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>
<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language=javascript src="/resources/shop/admin/common.js"></script>
<script language="javascript">
var pgType= new Array('inicis','kcp','dacom');
	/*
	 * php script
	 */
	 function chgifrm(src,k){
		for(var i=0;i<3;i++){
			if(i == k){
				document.getElementsByName('pgtd')[i].style.background='#627dce';
				document.getElementsByName('pgb')[i].style.color='#ffffff';
			}else{
				document.getElementsByName('pgtd')[i].style.background='#ffffff';
				document.getElementsByName('pgb')[i].style.color='#627dce';
			}
		}
	}
</script>

<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>
<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
/*
 * php script
 */
 function chgifrm(src,k){
	document.getElementById('pgifrm').src = 'pg/pgSelect?target='+src;
	for(var ix=0;ix<3;ix++){
		if(pgType[ix]==src){
			k=ix;
		}
	}
	for(var i=0;i<3;i++){
		if(i == k){
			document.getElementsByName('pgtd')[i].style.background='#627dce';
			document.getElementsByName('pgb')[i].style.color='#ffffff';
		}else{
			document.getElementsByName('pgtd')[i].style.background='#ffffff';
			document.getElementsByName('pgb')[i].style.color='#627dce';
		}
	}
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

 <div class="title title_top">
전자결제서비스(PG) 업체 선택<span>신용카드 결제 및 기타결제방식은 반드시 전자결제서비스 업체와 계약을 맺으시기 바랍니다</span> 
</div>

<table border=5 bordercolor=#627dce style="border-collapse:collapse">
<tr><td colspan=3 align=center style="padding: 10px 0px 10px 12px"><font color=627dce>아래 전자결제서비스(PG) 업체중 계약을 맺은 한곳만 클릭한 후 정보를 입력하세요</font></td></tr>
<tr align=center height=40>
	<td width="190" id="pgtd" name="pgtd"><a href="javascript:chgifrm('inicis',0)"><b id="pgb" name="pgb">이니시스</b></a></td>
	<td width="190" id="pgtd" name="pgtd"><a href="javascript:chgifrm('kcp',1)"><b id="pgb" name="pgb">KCP</b></a></td>	
	<td width="190" id="pgtd" name="pgtd"><a href="javascript:chgifrm('dacom',2)"><b id="pgb" name="pgb">LG데이콤</b></a></td>
<!-- 	<td width="190" id="pgtd" name="pgtd"><a href="javascript:chgifrm('allat',3)"><b id="pgb" name="pgb">올앳</b></a></td> -->
<!-- 	<td> -->
<!-- 	<a href="javascript:chgifrm('agspay.jsp')">올더게이트</a> -->
<!-- 	</td> -->
</tr>
</table>  

<div style="padding-top: 20px"></div>

<table width="100%" cellpadding=0 cellspacing=0 border=0>
<tr>
	<td>
	<iframe id="pgifrm" src="target" width="100%" frameborder="0" marginwidth="0" marginheight="0" width="100%" height="10" scrolling="no"></iframe>
	</td>
</tr>
</table>
<script>
$(function(){
	chgifrm('${pgVO.settlePg}',0);
	linecss();
	table_design_load();
});

</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>


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
