<%--
/************************************************************************************
* 프로그램명 			: 주문 엑셀 다운로드
* 관련 SERVICE명 		: 
* 프로그램 내용 		: XMall > 관리자 > 주문관리 > 리스트 > 다운로드 
* 작성자	   		 	: CAR
* 작성일자 				: 2017-01-19
*************************************************************************************
* 수정자  	수정일자	수정내용
*************************************************************************************
* 
************************************************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
/*
 * jQuery ready
 */
$(function(){
	
});
</script>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<table border="1">
	<tr bgcolor="#f7f7f7">
	<c:forEach items="${selConfList}" var="arry">
		<c:if test="${ arry[2] == 'y' }"><th>${arry[0] }</th></c:if>
	</c:forEach>
	</tr>
	<c:forEach items="${orderList}" var="list" varStatus="i">
		<c:if test="${ orderExcelVO.mode == 'order' }">
			<tr>
			<c:forEach items="${selConfList}" var="arry">
				<c:if test="${arry[2] == 'y'}">
					<c:choose>
						<c:when test="${arry[1] == 'no'}"><td>${i.index + 1 }</td></c:when>
						<c:when test="${arry[1] == 'ordno'}"><td style="mso-number-format:'\@';">${list.ordno}</td></c:when>
						<c:when test="${arry[1] == 'nameorder'}"><td>${list.nameorder}</td></c:when>
						<c:when test="${arry[1] == 'email'}"><td>${list.email}</td></c:when>
						<c:when test="${arry[1] == 'phoneorder'}"><td>${list.phoneorder}</td></c:when>
						<c:when test="${arry[1] == 'mobileorder'}"><td>${list.mobileorder}</td></c:when>
						<c:when test="${arry[1] == 'namereceiver'}"><td>${list.namereceiver}</td></c:when>
						<c:when test="${arry[1] == 'phonereceiver'}"><td>${list.phonereceiver}</td></c:when>
						<c:when test="${arry[1] == 'mobilereceiver'}"><td>${list.mobilereceiver}</td></c:when>
						<c:when test="${arry[1] == 'zipcode'}"><td>${list.zipcode}</td></c:when>
						<c:when test="${arry[1] == 'address'}"><td>${list.address}</td></c:when>
						<c:when test="${arry[1] == 'memo'}"><td>${list.memo}</td></c:when>
						<c:when test="${arry[1] == 'settlekind'}"><td>${shopLibFunction:r_settlekind(list.settlekind)}</td></c:when>
						<c:when test="${arry[1] == 'settleprice'}"><td>${list.seller_price}</td></c:when>
						<c:when test="${arry[1] == 'orddt'}"><td>${dateUtil:formatDate(list.orddt, "yyyy-MM-dd")}</td></c:when>
						<c:when test="${arry[1] == 'step'}"><td>${shopLibFunction:r_istep(list.istep)}</td></c:when>
						<c:when test="${arry[1] == 'deliveryno'}"><td>${list.deliveryno}</td></c:when>
						<c:when test="${arry[1] == 'deliverycode'}"><td>${list.deliverycode}</td></c:when>
						<c:when test="${arry[1] == 'ddt'}"><td>${dateUtil:formatDate(list.ddt, "yyyy-MM-dd HH:mm:ss")}</td></c:when>
						<c:when test="${arry[1] == 'sellerNm'}"><td>${list.sellerNm}</td></c:when>
						<c:otherwise><td>&nbsp;</td></c:otherwise>
					</c:choose>
				</c:if>
			</c:forEach>
			</tr>
		</c:if>
		<c:if test="${ orderExcelVO.mode == 'goods' }">
			<tr>
			<c:forEach items="${selConfList}" var="arry">
				<c:if test="${arry[2] == 'y'}">
					<c:choose>
						<c:when test="${arry[1] == 'no'}"><td>${i.index + 1 }</td></c:when>
						<c:when test="${arry[1] == 'sno'}"><td>${list.sno}</td></c:when>
						<c:when test="${arry[1] == 'ordno'}"><td style="mso-number-format:'\@';">${list.ordno}</td></c:when>
						<c:when test="${arry[1] == 'nameorder'}"><td>${list.nameorder}</td></c:when>
						<c:when test="${arry[1] == 'email'}"><td>${list.email}</td></c:when>
						<c:when test="${arry[1] == 'phoneorder'}"><td>${list.phoneorder}</td></c:when>
						<c:when test="${arry[1] == 'mobileorder'}"><td>${list.mobileorder}</td></c:when>
						<c:when test="${arry[1] == 'namereceiver'}"><td>${list.namereceiver}</td></c:when>
						<c:when test="${arry[1] == 'phonereceiver'}"><td>${list.phonereceiver}</td></c:when>
						<c:when test="${arry[1] == 'mobilereceiver'}"><td>${list.mobilereceiver}</td></c:when>
						<c:when test="${arry[1] == 'zipcode'}"><td>${list.zipcode}</td></c:when>
						<c:when test="${arry[1] == 'address'}"><td>${list.address}</td></c:when>
						<c:when test="${arry[1] == 'memo'}"><td>${list.memo}</td></c:when>
						<c:when test="${arry[1] == 'settlekind'}"><td>${shopLibFunction:r_settlekind(list.settlekind)}</td></c:when>
						<c:when test="${arry[1] == 'orddt'}"><td>${dateUtil:formatDate(list.orddt, "yyyy-MM-dd")}</td></c:when>
						<c:when test="${arry[1] == 'step'}"><td>${shopLibFunction:r_istep(list.istep)}</td></c:when>
						<c:when test="${arry[1] == 'goodsnm'}"><td>${list.goodsnm}</td></c:when>
						<c:when test="${arry[1] == 'goodscd'}"><td>${list.goodscd}</td></c:when>
						<c:when test="${arry[1] == 'addopt'}"><td>${list.addopt}</td></c:when>
						<c:when test="${arry[1] == 'origin'}"><td>${list.origin}</td></c:when>
						<c:when test="${arry[1] == 'brandnm'}"><td>${list.brandnm}</td></c:when>
						<c:when test="${arry[1] == 'ea'}"><td>${list.ea}</td></c:when>
						<c:when test="${arry[1] == 'supply'}"><td>${list.supply}</td></c:when>
						<c:when test="${arry[1] == 'price'}"><td>${list.goodsprice}</td></c:when>
						<c:when test="${arry[1] == 'sprice'}"><td>${list.seller_price}</td></c:when>
						<c:when test="${arry[1] == 'deliveryno'}"><td>${list.deliveryno}</td></c:when>
						<c:when test="${arry[1] == 'deliverycode'}"><td>${list.deliverycode}</td></c:when>
						<c:when test="${arry[1] == 'ddt'}"><td>${dateUtil:formatDate(list.ddt, "yyyy-MM-dd HH:mm:ss")}</td></c:when>
						<c:when test="${arry[1] == 'sellerNm'}"><td>${list.sellerNm}</td></c:when>
						<c:otherwise><td>&nbsp;</td></c:otherwise>
					</c:choose>
				</c:if>
			</c:forEach>
			</tr>
		</c:if>
	</c:forEach>
</table>