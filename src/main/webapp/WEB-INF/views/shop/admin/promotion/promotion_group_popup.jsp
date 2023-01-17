<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="/WEB-INF/views/shop/admin/common/header_popup.jsp" %>

		<script type="text/javascript">
			function chkForm(frm){
				var list = [];
				$("[name='grnm[]']").each(function(i){
					if ($(this).val().trim() != ""){
						var grp = {};
						grp.grnm = $(this).val().trim();
						grp.grno = $("[name='grno[]']").eq(i).val();
						grp.sort = $("[name='sort[]']").eq(i).val();
						grp.useYn = $("[name='useYn[]']").eq(i).prop("checked") ? "Y" : "N";
						grp.gdel = $(this).is(':visible') ? "N" : "Y";
						list.push(grp);
					}
				});
				
				$.ajax({
					type: "post",
					url: "groupPopup",
					headers: { "Content-Type" : "application/json" },
					data: JSON.stringify({"pmno": frm.pmno.value, "list": list}),
					dataType: "json",
					success: function(rst){
						//alert("총 " + rst.tot + "건, 추가:" + rst.ins + ", 삭제:" + rst.del);
						parent.location.reload();
					},
					error: function(request, status, error){
						console.log("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
					}
				});
				
				return false;
			}
			
			$(function(){
				$(".btnAdd").on("click", function(e){
					var html = '<tr class="trClass">';
					html += '	<td><input type="text" name="grnm[]" value="" style="width:100%" maxlength="100" required></td>';
					html += '	<td><input type="text" name="sort[]" value="" onkeydown="onlynumber(event)" maxlength="2" size="2"></td>';
					html += '	<td><input type="checkbox" name="useYn[]" value="Y" checked /></td>';
					html += '	<td><img src="/resources/shop/admin/img/i_del.gif" class="btnDel" style="cursor:pointer">';
					html += '	<input type="hidden" name="grno[]" value="0"></td>';
					html += '</tr>';
					$(".listTable tbody").append(html);
				});
			});
			
			$(document).on("click", ".btnDel", function(){
				if ($(this).next("input").val() == "0"){
					$(this).parent().parent().remove();
				}else{
					$(this).parent().parent().hide();
				}
			});
		</script>
		
		<form method="post" action="groupPopup" onsubmit="return chkForm(this);">
			<input type="hidden" name="pmno" value="${promotionGrpVO.pmno}">
			
			<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
				<tr>
					<td valign="top" style="padding-left:12px">
						<div class="title title_top">구분 타이틀 정보 <img src="/resources/shop/admin/img/i_add.gif" class="btnAdd" style="cursor:pointer"></div>  
						
						<table class="listTable" style="table-layout: unset;">
							<thead>
								<tr>
									<th>구분명</th>
									<th>순선순위</th>
									<th>노출여부</th>
									<th>삭제</th>
							</tr>
							</thead>
							<tbody>
								<c:forEach items="${promotionGrpVO.groupList}" var="list" varStatus="status">
									<tr class="trClass">
										<td><input type="text" name="grnm[]" value="${list.grnm}" style="width:100%" maxlength="100" required></td>
										<td><input type="text" name="sort[]" value="${list.sort}" onkeydown="onlynumber(event)" maxlength="2" size="2"></td>
										<td><input type="checkbox" name="useYn[]" value="Y" ${list.useYn eq 'Y' ? 'checked' : ''} /></td>
										<td>
											<img src="/resources/shop/admin/img/i_del.gif" class="btnDel" style="cursor:pointer">
											<input type="hidden" name="grno[]" value="${list.grno}">
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<div class="button">
							<input type="image" src="/resources/shop/admin/img/btn_save.gif">
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>