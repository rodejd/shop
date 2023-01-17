<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>


<form method=post action="${ctx}/shop/admin/goods/common_deliveryInfo_register/indb" onsubmit="return checkForm010(this);">
	<input	type="hidden" name="flag" id="flag" value="N"> 
	<input  type="hidden" name="complateYn" id="complateYn" value="${complateYn }"/>

	<div class="title">배송정보 관리<span>배송정보 관리 페이지입니다.</span></div>

	<div style="padding:50px;text-align:center; border:1px solid #ddd;">
		<textarea name="contents" id="contents" type=editor style="width:100%;height:500px">${contents }</textarea>
		<script src="/resources/shop/lib/meditor/mini_editor.js"></script>
		<script>mini_editor("/resources/shop/lib/meditor/");</script>
	</div>

	<div class="button">
		<input type="image" src="/resources/shop/admin/img/btn_register.gif">&nbsp;
		<!-- <a href="#" target="_blank"><img src="../img/btn_list.gif"></a> -->
	</div>
</form>

<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>

<script type="text/javascript">
	$(document).ready(function(){
		var complateYn = $('#complateYn').attr('value');
		if(complateYn == "Y"){
			alert("배송정보가 등록 완료되었습니다.");
			location.href=ctx+'/shop/admin/goods/common_deliveryInfo_register';
		}
	});
	
	function checkForm010(obj){
		if(!Boolean($("#miniEditorIframe_contents").contents().find("body").html())){
			alert("내용을 확인해 주세요.");
			return false;
		}else{
			obj.flag.value ="Y";
		}
		return true;
	}
</script>