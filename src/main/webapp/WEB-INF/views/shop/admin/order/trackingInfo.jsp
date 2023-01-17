<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>배송조회</title>
		<link rel="stylesheet" href="/shop/data/skin/kr/css/bootstrap.css">
		<style>
			body {
				background-color: #ededed;
			}
			table {
				background-color: white;
			}
		</style>
	</head>
	<body>
		<div class="container">
			<c:choose>
				<c:when test="${trackingInfo.status == false}">
					<table class="table table table-shopping-cart">
						<tr>
							<td>
								<h4>${trackingInfo.msg}</h4>
							</td>
						</tr>
					</table>
				</c:when>
				<c:otherwise>
						<div>
							<h3>배송지 정보</h3>
						</div>
						<table class="table table table-shopping-cart">
							<tr>
								<th>보내는 분</th>
								<th>상품명</th>
								<th>배송지</th>
								<th>받는 분</th>
							</tr>
							<tr>
								<td>${trackingInfo.senderName}</td>
								<td>${trackingInfo.itemName}</td>
								<td>${trackingInfo.receiverAddr}</td>
								<td>${trackingInfo.receiverName}</td>
							</tr>
						</table>
						
						<div>
							<h3>운송장 정보</h3>
						</div>
						<table class="table table table-shopping-cart">
							<tr>
								<th>택배사</th>
								<th>송장번호</th>
								<th>배송기사</th>
								<th>연락처</th>
							</tr>
							<tr>
								<td>${trackingInfo.deliveryComp}</td>
								<td>${trackingInfo.invoiceNo}</td>
								<td>${trackingInfo.lastStateDetail.manName}</td>
								<td>${trackingInfo.lastStateDetail.telno}</td>
							</tr>
						</table>
						
						<div>
							<h3>배송 진행 내역</h3>
						</div>
						<table class="table table table-shopping-cart">
							<tr>
								<th>처리일시</th>
								<th>현재위치</th>
								<th>배송상태</th>
							</tr>
							<c:set var="trackingDetailsLength" value="${fn:length(trackingInfo.trackingDetails)}"/>
							<c:forEach items="${trackingInfo.trackingDetails}" varStatus="status">
								<tr>
									<td>${trackingInfo.trackingDetails[trackingDetailsLength - status.count].timeString }</td>
									<td>${trackingInfo.trackingDetails[trackingDetailsLength - status.count].where }</td>
									<td>${trackingInfo.trackingDetails[trackingDetailsLength - status.count].kind }</td>
								</tr>
							</c:forEach>
						</table>
				</c:otherwise>
			</c:choose>
		</div>
	</body>
	<script type="text/javascript">
		opener.location.reload();
	</script>
</html>