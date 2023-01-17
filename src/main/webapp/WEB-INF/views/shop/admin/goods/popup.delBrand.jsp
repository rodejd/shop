<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script type="text/javascript" src="/resources/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery.ajax.js"></script>

<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>
<%@ include file="../common/header_popup.jsp" %>
<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
/*
 * jQuery ready
 */
$(function(){
	
});

/*
 * php javascript
 */
function chkForm2(obj){
 	return chkForm(obj);
	parent.closeLayer();
	parent.saveHistory(parent.form);
}

</script>
<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<form name=form method=post action="brand/indb" onsubmit="return chkForm2(this)">
<input type=hidden name=mode value="del_brand">
<input type=hidden name=sno value="${brandVO.brandObj.sno}">
<input type=hidden name=oldImgPC value="${brandVO.brandObj.imgPC}">
<input type=hidden name=oldImgMO value="${brandVO.brandObj.imgMO}">

<div class="title title_top">브랜드 삭제</div>

<table class=tb>
<col class=cellC><col class=cellL>
<tr height=26>
	<td>삭제 브랜드</td>
	<td>TOP > ${brandVO.brandObj.brandnm}</td>
</tr>
<tr>
	<td>연결상품수</td>
	<td><b>${brandVO.brandCnt}</b>개</td>
</tr>
<tr>
	<td>주의사항</td>
	<td class=small1 style="color:#5B5B5B;padding:5px;">
		상단HTML에 쓰인 이미지는 다른 곳에서도 사용하고 있을 수 있으므로 자동 삭제되지 않습니다.<br>
		'디자인관리 > webFTP이미지관리 > data > editor'에서 이미지체크 후 삭제관리하세요.
	</td>
</tr>
</table>

<div class="button_popup">
<input type=image src="/resources/shop/admin/img/btn_confirm_s.gif">
<a href="javascript:parent.closeLayer()"><img src="/resources/shop/admin/img/btn_cancel_s.gif"></a>
</div>

</form>

<script>table_design_load();</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

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