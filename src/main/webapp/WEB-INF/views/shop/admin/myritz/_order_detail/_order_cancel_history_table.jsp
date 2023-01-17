<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
    
<div class=title2>&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align=absmiddle><font color=508900>취소내역리스트</font> <font class=small1 color=6d6d6d>(주문취소를 요청한 내역을 볼 수 있습니다)</font></div>
<table class=tb>
	<col class=cellC><col class=cellL>
	<c:forEach items="${popupVO.tmpRt11 }" var="tmpRt11">
		<tr>
			<td><font class=small>${tmpRt11.name}</font></td>
			<td>
				<div style="float:left" class=ver81>
					<font color=555555>
						<fmt:formatDate value="${tmpRt11.regdt}" pattern="yy/MM/dd hh:mm aa"/>
					</font>
				</div>
				<div style="float:right">
					<font class=small1>
						<c:choose>
							<c:when test="${tmpRt11.code == '0' }">
								주문취소복원
							</c:when>
							
							<c:otherwise>
								${tmpRt11.cancelCode }					
							</c:otherwise>
						</c:choose>
					</font>
				</div>
			</td>
		</tr>

		<tr>
			<td colspan=2 bgcolor=#ffffff align=left style="padding:5px">
				<input type="hidden" name="cancel" value="${tmpRt11.sno }">
				<table width=100%>
					<c:forEach items="${tmpRt11.tmpRt12 }" var="tmpRt12">
						<tr>
							<td><font class=small1 color=444444>- ${tmpRt12.goodsnm}</font> ${tmpRt12.ea}개</td>
							<td align=right><font class=small1 color=EA0095>${tmpRt12.rStepi}</font></td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan=2 bgcolor=#ffffff align=left style="padding:5px">
				<c:if test="${tmpRt11.memo != '' }">
					<div style="margin:5px" class=small><font color=0074BA>처리메모:</font> 
						<font color=555555>${tmpRt11.memo}</font>
					</div>
				</c:if>
			</td>
		</tr>
	</c:forEach>
</table>