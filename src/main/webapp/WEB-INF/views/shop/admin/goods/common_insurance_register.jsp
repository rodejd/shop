<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>
<script type="text/javascript">
	$(document).ready(function(){
		var complateYn = $('#complateYn').attr('value');
		if(complateYn == "Y"){
			alert("보험인증 공통이미지가 등록 완료되었습니다.");
			location.href=ctx+'/shop/admin/goods/common_insurance_register';
		}
	});
	
	function checkForm010(obj){
		if(!Boolean(obj.img.value)){
			alert("이미지를 확인해 주세요.");
			return false;
		}else{
			obj.img_flag.value ="Y";
		}
		return true;
	}
</script>
<form name="form010" method=post action="common_insurance_register/indb" enctype="multipart/form-data" onsubmit="return checkForm010(this);">
	<input	type="hidden" name="img_flag" id="img_flag" value="N"> 
	<input  type="hidden" name="complateYn" id="complateYn" value="${complateYn }"/>
	
	<div class="title">보험인증관리<span>보험인증 이미지 관리 페이지입니다.</span></div>
	<table class="tb">
		<col style="width:120px" class="cellC">
		<col />
		<tr>
			<td>이미지</td>
			<td>
				<input type="file" name="img" id="img" accept=".gif, .jpg, .png, .jpge, .bmp" > 권장사이즈: 938 * 300&nbsp; *등록이미지명:${img}
			</td>
		</tr>
	</table>
	<c:if test="${img != '' }">
		<input	type="hidden" name="old_img" id="old_img" value="${img }"> 
	</c:if>

	<div class="button">
		<input type="image" src="/resources/shop/admin/img/btn_register.gif">&nbsp;
		<!-- <a href="#" target="_blank"><img src="../img/btn_list.gif"></a> -->
	</div>
</form>


<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>