<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
    
<div class=title2>&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align=absmiddle><font color=508900>취소내역리스트</font> <font class=small1 color=6d6d6d>(주문취소를 요청한 내역을 볼 수 있습니다)</font></div>
<c:forEach items="${popupVO.tmpRt11 }" var="tmpRt11" varStatus="status">
	<table class=tb ${status.index ne 0 ? 'style="margin-top: 10px;"' : '' }>
		<col class=cellC><col class=cellL>
		
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
							${tmpRt11.cancelCode }
						</font>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan=2 bgcolor=#ffffff align=left style="padding:5px">
					<c:if test="${not empty tmpRt11.bankcode}">
							<div style="margin: 7px 0; color: 6d6d6d; font-weight: 800;"><img src="/resources/shop/admin/img/arrow_gray.gif" align="absmiddle"> 환불계좌 정보</div>
							<table border="1" bordercolor="#e6e6e6" style="width: 100%; border-collapse: collapse;">
								<colgroup>
									<col width="30%">
									<col width="70%">
								</colgroup>
								<tr>
									<td style="padding-left: 5px;">환불 계좌은행</td>
									<td style="padding-left: 5px;">${codeUtil:getCodeName('bank',tmpRt11.bankcode)}</td>
								</tr>
								<tr>
									<td style="padding-left: 5px;">환불 계좌</td>
									<td style="padding-left: 5px;">${tmpRt11.bankaccount}</td>
								</tr>
								<tr>
									<td style="padding-left: 5px;">환불 계좌 예금주명</td>
									<td style="padding-left: 5px;">${tmpRt11.bankuser}</td>
								</tr>
							</table>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan=2 bgcolor=#ffffff align=left style="padding:5px">
					<c:if test="${tmpRt11.memo != '' }">
						<div style="margin:5px" class=small><font color=0074BA style="font-weight: 800;"><img src="/resources/shop/admin/img/arrow_gray.gif" align="absmiddle"> 처리메모:</font> 
							<font color=555555>${tmpRt11.memo}</font>
						</div>
					</c:if>
				</td>
			</tr>
	</table>
</c:forEach>