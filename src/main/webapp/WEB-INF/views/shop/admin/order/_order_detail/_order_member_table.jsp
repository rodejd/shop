<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<div class=title2>&nbsp;<img src="/resources/shop/admin/img/icon_process.gif" align=absmiddle><font color=508900>주문자정보</font></div>

<table class=tb>
	<col class=cellC><col class=cellL>
	<tr>
		<td>이름/ID</td>
		<td>
			<c:if test="${popupVO.rtData.mId != ''}">
				<span id="navig" name="navig" m_id='${popupVO.rtData.mId }' m_no='${popupVO.rtData.mno }' popup="${popupVO.popup}">
				</span>
			</c:if>
			<font color=0074BA><b>
				${popupVO.rtData.nameorder}
				<c:if test="${popupVO.rtData.nameorder != '' }">
					<c:if test="${popupVO.rtData.mId != '' }">
						/ ${popupVO.rtData.mId }
					</c:if>
				</c:if>
			</b></font>
		</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td>
			<font class=ver8>${popupVO.rtData.email}</font> 
		</td>
	</tr>
	<tr>
		<td>연락처</td>
		<td class=ver8>
			<%-- <input type="text" readonly="readonly" style="width: 95px; border: 1px solid rgb(204, 204, 204);" value="${popupVO.rtData.phoneorder}"/> / --%>
			<input type="text" readonly="readonly" style="width: 95px; border: 1px solid rgb(204, 204, 204);" value="${popupVO.rtData.mobileorder}"/>
		</td>
	</tr>
	<tr>
		<td>주문일</td>
		<td>
			<font class=ver8>
				<fmt:formatDate value="${popupVO.rtData.orddt}" pattern="yyyy-MM-dd hh:mm aa"/>
			</font>
		</td>
	</tr>
</table>