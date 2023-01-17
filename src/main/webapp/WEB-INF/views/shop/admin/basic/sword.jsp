<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<script type="text/javascript">
function chkval(){
	
}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="styleSheet" href="/resources/shop/admin/style.css">
<script src="/resources/shop/admin/common.js"></script>

<div class="sub-cont-wrap">
<form method="post" name="fm" action="sword/indb" onsubmit="return chkForm(this);">
	<%--
	<div style="display:inline-block">
		<div style="display:inline-block">
			<div class="title title_top">추천 검색어<span> </span></div>
			<table width="325" cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr class="rndbg" style="padding-top:2">
						<th><font class="small1"><b>번호</b></font></th>
						<th><font class="small1"><b>검색어</b></font></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${searchWordVO.recommendList}" var="list" varStatus="status">
						<tr align="center">
							<td>${status.count}</td>
							<td><input type="text" name="recommendList[${status.index}].word" value="${list.word}" class="line" style="width:100%" maxlength="50"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div style="display:inline-block">
			<div class="title title_top">인기 검색어<span> </span></div>
			<table width="400" cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr class="rndbg" style="padding-top:2">
						<th><font class="small1"><b>번호</b></font></th>
						<th><font class="small1"><b>검색어</b></font></th>
						<th><font class="small1"><b>기호</b></font></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${searchWordVO.popularList}" var="list" varStatus="status">
						<tr align="center">
							<td>${status.count}</td>
							<td><input type="text" name="popularList[${status.index}].word" value="${list.word}" class="line" style="width:100%" maxlength="50" required></td>
							<td>
								<select name="popularList[${status.index}].mark" required>
									<option value="0"${list.mark eq '0' ? ' selected' : ''}>▲</option>
									<option value="1"${list.mark eq '1' ? ' selected' : ''}>ㅡ</option>
									<option value="2"${list.mark eq '2' ? ' selected' : ''}>▽</option>
								</select>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		--%>

		<div style="width: 100%; display: table; table-layout: fixed;">
			<!-- 추천 검색어 -->
			<div style="display: table-cell; padding-right: 10px;">
				<div class="title title_top">추천 검색어</div>
				<table class="stripe-tbl inp-tbl">
					<thead>
						<tr>
							<th>번호</th>
							<th>검색어</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${searchWordVO.recommendList}" var="list" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td style="text-align: left;">
									<input type="text" class="full" name="recommendList[${status.index}].word" value="${list.word}" maxlength="50">
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<!-- 인기 검색어 -->
			<div style="display: table-cell; padding-left: 10px;">
				<div class="title title_top">인기 검색어</div>
				<table class="stripe-tbl inp-tbl">
					<colgroup>
						<col style="width: 10%;">
						<col>
						<col style="width: 10%; min-width: 50px;">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>검색어</th>
							<th>기호</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${searchWordVO.popularList}" var="list" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td>
									<input type="text" name="popularList[${status.index}].word" value="${list.word}" class="full" maxlength="50" required>
								</td>
								<td>
									<select class="full" name="popularList[${status.index}].mark" style="text-align: center;" required>
										<option value="0"${list.mark eq '0' ? ' selected' : ''}>▲</option>
										<option value="1"${list.mark eq '1' ? ' selected' : ''}>ㅡ</option>
										<option value="2"${list.mark eq '2' ? ' selected' : ''}>▽</option>
									</select>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>


<%--		<div style="padding-top:40px;text-align:center">--%>
		<div class="button">
<%--			<input type="image" src="/resources/shop/admin/img/btn_save.gif" style="border:0;">--%>
			<input type="submit" class="admin-btn" value="저장" />

		</div>
	</div>
</form>
</div>

<%--<div style="padding-top:40px"></div>--%>

<%@ include file="../common/bottom.jsp" %>