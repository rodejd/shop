<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header_popup.jsp" %>

	<form id="orderCancelForm" method=post >
		<input type=hidden name=mode value="chkCancel">
		<input type=hidden name=ordno value="${orderVO.ordno}">
		<input type=hidden name=mno value="${orderVO.mno}">
		
		<c:forEach items="${snoList}" var="sno" varStatus="status">
			<input type=hidden name=sno value="${sno}">
		</c:forEach>
		
		
		<div style="padding-bottom:5px">
			&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align=absmiddle>
			<b style="color:494949">주문상품 취소완료 처리</b>
		</div>
	
		<table class="tb" border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
			<tr>
				<td>
					<table class=tb>
						<col class=cellC>
						<col class=cellL>
						<col class=cellC>
						<col class=cellL>
						
						<tr>
							<td width=130 nowrap><font class=small1 color=434343><b>처리담당자</b></font></td>
							<td width=50%><input type=text name=pName value="" required class="line" onkeydown="noSpecialCharacters(event)" maxlength="20"></td>
							<td width=130 nowrap><font class=small1 color=434343><b>사유</b></font></td>
							<td width=50%>
								<select name=code required>
									<option value=""> = 선택하세요 =</option>
									${webUtil:makeSelectCodeItem(codeUtil:codeitem('cancel'), '') }
								</select>
							</td>
						</tr>
						<tr>
							<td><font class=small1 color=434343><b>상세사유</b></font></td>
							<td colspan=3><input type=textarea name=memo value="${item.memo}" style="width: 100%; height: 65px" required class="tline" maxlength="255"></textarea></td>
						</tr>
						<%--
						<c:choose>
							<c:when test="${param_m == '0' and item.istep >= 1 and item.step ne 0}">
								<tr>
									<td height=26><font class=small1 color=434343><b>환불계좌정보</b></font></td>
									<td colspan=3>
										<div>
											<font class=small1 color=434343></font>
											<select name=bankcode>
												<option value="" > = 선택하세요 =
													${webUtil:makeSelectCodeItem(codeUtil:codeitem('bank'), stringUtil:sprintf('%02d', item.bankcode ))}
											</select>
											&nbsp;&nbsp; <font class=small1>계좌번호</font> <input type=text
												name=bankaccount value="${item.bankaccount}" class="line"
												onkeydown="onlynumber(event)" onkeyup="removeHangul(event)" maxlength="15">&nbsp;&nbsp; <font
												class=small1>예금주</font> <input type=text name=bankuser
												value="${item.bankuser}" class="line" maxlength="10">
										</div>
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<input type="hidden" name=bankcode value="1" />
								<input type="hidden" name=bankaccount value="0" />
								<input type="hidden" name=bankuser value="미지정" />
							</c:otherwise>
						</c:choose>
						 --%>
					</table>
				</td>
			</tr>
			
			<tr>
				<td colspan=4 class=noline align=left>
					<div align=center>
						<input type=image src="/resources/shop/admin/img/btn_confirm_o.gif" id="cancelConfirmBtn">
					</div>
				</td>
			</tr>
		</table>	
	</form>

	<script type="text/javascript">
		$(function(){
			table_design_load();	
		});
		
		$('#cancelConfirmBtn').on('click', function(){
			if ( chkForm($('#orderCancelForm')[0]) ) {
				ajaxCallJsonPost("/shop/admin/order/indb.cancel", 'orderCancelForm', function(data){
					if (data.RESULT == true) {
						alert('선택하신 상품이 취소/반품 처리되었습니다.');
						window.location.reload();
					} else {
						alert(data.RESULT_MSG);
						return false;
					}
				});
			}
		});
	</script>