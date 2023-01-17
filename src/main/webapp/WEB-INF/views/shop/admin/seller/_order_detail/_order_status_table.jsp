<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

   	<div class=title2>&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align=absmiddle><font color=508900>현주문상태</font></div>
	
	<table class=tb>
		<col class=cellC><col class=cellL>
		<tr>
			<td>주문상태</td>
			<td style="padding:2px 10px">
				<script>chk_step(1);</script>
				<table width=100%>
					<tr>
						<td>
							<font class=small1>
								<c:choose>
									<c:when test="${popupVO.rtData.step2 == '0'}">
										<select name=step onchange="chk_step(this.value)">
											${popupVO.selectOption}
										</select>
										<c:if test="${popupVO.iCancel > 0}">
											주문취소 ${popupVO.iCancel }건입니다.
										</c:if>
									
										<script>
											chk_step('${popupVO.rtData.step}');
										</script>
									</c:when>
									
									<c:otherwise>
										${popupVO.stepMsg }
										<input type=hidden name=step value="${popupVO.rtData.step}">
									</c:otherwise>
								</c:choose>
								&nbsp;
								<c:if test="${popupVO.cnt > 0 }">
									<img src="/resources/shop/admin/img/arrow_gray.gif" align =absmiddle>
									<font class=small1 color=444444>
										교환으로 인해 자동생성된 
										<font color=ED00A2>맞교환주문건</font> 
										(<c:forEach items="${popupVO.newordno}" var="ordno">
											<a href="javascript:popup('popup.order.jsp?ordno=${ordno}',800,600)">
												<font color=0074BA class=ver81>
													<b><u>${ordno}</u></b>
												</font>
											</a>,
										</c:forEach>) 이 있습니다.
									</font>
								</c:if>
								<c:if test="${popupVO.rtData.oldordnoN2S != 0}">
									&nbsp;
									<img src="/resources/shop/admin/img/arrow_gray.gif" align=absmiddle>
									<font class=small1 color=444444>이 주문은 
										<font color=ED00A2>교환요청건</font> 
										(<a href="javascript:popup('popup.order.jsp?ordno=${popupVO.rtData.oldordno}',800,600)">
											<font color=0074BA class=ver81>
												<b><u>${popupVO.rtData.oldordno}</u></b>
											</font>
										</a>) 으로 자동생성된 재주문 입니다.
									</font>
								</c:if>
							</font>
						</td>
						<td align="right">
							<c:if test="${popupVO.rtData.step2 >= 50}">
	<%-- 								<span><a href="indb.jsp?mode=faileRcy&ordno=${popupVO.ordno}&returnUrl=${popupVO.referer}&popup=${popupVO.popup}" onclick="return confirm('주문삭제는 현상태에서 단순히 데이타를 입금확인 상태로 변경하는 기능입니다.\n\n복원된주문은 다시 시도 실패로 변경이 불가합니다.\n\n선택하신 주문[<?=$ordno?>]을 정말로 입금확인으로 변경하시겠습니까?')"><img src="/resources/shop/admin/img/btn_order_try_return.gif"></a></span> --%>
							</c:if>
					
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