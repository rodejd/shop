<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- 2017-08-23 : mode 를 deliveryVO.mode 로 수정 --%>
<script src="/resources/js/jquery/jquery-1.10.2.min.js	"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery.ajax.js"></script>
<script type="text/javascript">
	if('${deliveryVO.result}' == 1){
		parent.location.reload();
		parent.closeLayer();
		
	}
	function deliverynoCheck(){
		
		var deliveryno = $("#deliveryno").val();
		if(!deliveryno){
			alert("고유번호를 입력해주세요");
			return false;
		}
		
		ajaxCallJson(
					"/shop/admin/basic/deliverynoCheck"
					, {	'deliveryno' : $("#deliveryno").val() }
					,function (data) {
						if(data == 0){
							alert("사용가능한 고유번호입니다")
							$('#deliveryNoCk').val("1");
							return false;
						} else {
							alert('같은 고유번호가 있습니다');
							return false;
						}
					}
					, function(e){
						alert("error >> "+e);
					});
	}
	
	function resultCk(){
		var mode = $('#mode').val();		
		var deliveryNoCk = $('#deliveryNoCk').val();

		if(mode === 'registerDelivery') {
			if(deliveryNoCk === '0'){
				alert("중복체크를 해주세요");
				return false;
			}
		}
		$('form').submit();
	}
	
</script>
<c:choose>
	<%-- 2017-08-23 : 택배사 등록인 경우 --%>
	<c:when test="${deliveryVO.mode eq 'registerDelivery'}">
		<c:set var="title" value="${'택배사 추가'}"></c:set>
		<c:set var="deliveryNo" value=""></c:set>	
	</c:when>
	
	<%-- 2017-08-23 : 택배사 수정인 경우 --%>
	<c:otherwise>
		<c:set var="title" value="${'택배사 수정'}"></c:set>
		<c:set var="rtList" value="${deliveryList }"></c:set>
		<c:set var="deliveryComp" value="${rtList.deliverycomp}"></c:set>
		<c:set var="deliveryNo" value="${rtList.deliveryno}"></c:set>	
	</c:otherwise>
</c:choose>
	
	<script src="/resources/shop/admin/common.js"></script>
	<link rel="styleSheet" href="/resources/shop/admin/style.css">
	
	<form method=post  id="deliveryForm" action="popup_delivery/indb" onsubmit="return chkForm(this);">  <!-- warning action경로 -->
		<input type=hidden id = "mode" name="mode" value="${deliveryVO.mode}">
		<input type=hidden name=deliveryno1 value="${deliveryNo}">
		<input type=hidden id="deliveryNoCk"name="deliveryNoCk" value="0">
		
		<div class="title title_top">${title}</div>
		<table class=tb>
			<col class=cellC><col class=cellL>

			<tr>
				<td width=100 nowrap>고유번호</td>
				<td width=50%><input name="deliveryno" id="deliveryno" value="${deliveryNo}" maxlength="2" <c:if test="${deliveryVO.mode eq 'modifyDelivery'}">readonly</c:if> /></td>
				<c:if test="${deliveryVO.mode eq 'registerDelivery'}">
				<td>
					<a class="btn btn-primary" id="deliverynoCheck" onclick="deliverynoCheck();">중복체크 </a>
				</td>
				</c:if>
			</tr>
			
			<tr>
				<td>택배사명</td>
				<td><input type=text name=deliverycomp value="${deliveryComp}" required label="택배사명" class="line" maxlength="20" onkeydown="noSpecialCharacters()"></td>
			</tr>
			<tr>
				<td>배송추적주소</td>
				<td><textarea name=deliveryurl style="width:100%;height:50;word-break:break-all;" class="tline">${rtList.deliveryurl}</textarea>
					<div style="padding-top:4px"></div>
					<font class=extext>
						택배사에서 제공하는 배송추적주소를 넣으면 됩니다.<br>
<c:if test="${deliveryVO.mode eq 'modifyDelivery'}">
<%-- 2017-08-23 : 수정시에만 출력되는 부분이어야 하므로 조건문 추가 --%>
						기본적으로 주소는 입력되어 있습니다.<br>
</c:if>
						해당 택배사 홈페이지에 가시면 확인하실 수 있습니다.</font>
					<div style="padding-top:4px"></div>
				</td>
			</tr>
			
		</table>
		
		<div class="button_popup">
			<input type="image" src="/resources/shop/admin/img/btn_regist.gif" onclick="return resultCk();">
			<a href="javascript:parent.location.reload();closeLayer()"><img src="/resources/shop/admin/img/btn_cancel_s.gif"></a>
		</div>
		
	</form>
	
	<script>table_design_load();
		linecss(document.form);
	</script>
