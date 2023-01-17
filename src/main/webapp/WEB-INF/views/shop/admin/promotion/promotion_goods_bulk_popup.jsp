<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="/WEB-INF/views/shop/admin/common/header_popup.jsp" %>

		<script type="text/javascript">
			function chkForm(frm){
				var scmcd = frm.scmcd.value.trim();
				if (scmcd == ""){
					alert("SCM 상품번호를 입력해주세요.");
					frm.scmcd.focus();
					return false;
				}
				
				$.ajax({
					type: "post",
					url: "goodsIns",
					headers: { "Content-Type" : "application/json" },
					data: JSON.stringify({"pmno": frm.pmno.value, "scmcd": scmcd, "grno": frm.grno.value}),
					dataType: "json",
					success: function(rst){
						alert("총 " + (rst.tot) + "건, 성공:" + rst.cnt + ", 실패:" + rst.err);
						parent.location.reload();
					},
					error: function(request, status, error){
						console.log("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
					}
				});
				
				return false;
			}
		</script>
		
		<form method="post" action="goodsIns" onsubmit="return chkForm(this);">
			<input type="hidden" name="pmno" value="${promotionGoodsVO.pmno}">
			
			<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
				<tr>
					<td valign="top" style="padding-left:12px">
						<div class="title title_top">상품 대량등록</span></div>  
						
						<table width=100% border=1 bordercolor=#B9B9B9 style="border-collapse:collapse">
							<tr bgcolor=#F1F1F1 height=25>
								<th><font class=small color=262626><b>SCM 상품번호 입력하기</th>
							</tr>
							<tr>
								<td><textarea name="scmcd" style="width:100%;height:260px" required></textarea></td>
							</tr>
						</table>
						
						<c:choose>
							<c:when test="${!empty promotionGoodsVO.groupList && fn:length(promotionGoodsVO.groupList) > 0}">
								<table class="tb" border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse; margin-top: 10px;">
									<col class="cellC" />
									<col class="cellL" />
									<tr>
										<td>구분</td>
										<td>
											<select name="grno">
												<c:forEach items="${promotionGoodsVO.groupList}" var="list">
													<option value="${list.grno}">${list.grnm}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
								</table>
							</c:when>
							<c:otherwise>
								<input type="hidden" name="grno" value="0" />
							</c:otherwise>
						</c:choose>
						
						<div class="button">
							<input type="image" src="/resources/shop/admin/img/btn_register.gif">
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>