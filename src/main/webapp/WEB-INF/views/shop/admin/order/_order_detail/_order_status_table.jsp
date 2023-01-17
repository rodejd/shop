<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %> 

   	<div class=title2>&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align=absmiddle><font color=508900>현주문상태</font></div>
	
	<table class=tb>
		<col class=cellC><col class=cellL>
		<tr>
			<td>주문상태</td>
			<td style="padding:2px 10px">
				<table width=100%>
					<tr>
						<td>
							<font class=small1>
								<c:choose>
									<c:when test="${ popupVO.rtData.step2 >= 79 }">
										<select name="step">
											<option value="79" ${stringUtil:selected('79', popupVO.rtData.step2)}>반품신청</option>
											${webUtil:makeSelectCodeItem((codeUtil:codeitem('cancelstep')), popupVO.rtData.step2) }
										</select>
									</c:when>
									<c:when test="${ popupVO.rtData.step2 eq 40 or popupVO.rtData.step2 eq 56 or popupVO.rtData.step2 eq 58 }">
										${shopLibFunction:r_stepi(popupVO.rtData.step, popupVO.rtData.step2) }
										<input type="hidden" name="step" value="${popupVO.rtData.step }" />
									</c:when>
									<c:otherwise>
										<select name=step >
											${webUtil:makeSelectCodeItem((codeUtil:codeitem('step')), popupVO.rtData.step) }
										</select>
									</c:otherwise>
								</c:choose>
							</font>
						</td>
						<td align="right">
							<span style='width:80'>
								<a href="javascript:deleteOrder('${popupVO.ordno}');" >
									<img src="/resources/shop/admin/img/btn_delete_order.gif">
								</a>
							</span>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>