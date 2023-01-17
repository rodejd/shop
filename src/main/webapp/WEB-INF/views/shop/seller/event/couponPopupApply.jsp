<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<div class="title title_top">쿠폰발급 회원내역</div>
<table width="100%" cellpadding="0" cellspacing="0" border="0" >
	<tr><td class=rnd colspan=4></td></tr>
	<tr class=rndbg>
		<th>번호</th>
		<th>아이디</th>
		<th>이름</th>
		<th>사용 여부</th>
	</tr>         
	<tr><td class=rnd colspan=4></td></tr>
	<col width=50 align=center>
	<col width=150 align=center>
	<col width=150 align=center>
	<col align=left>
 	<c:forEach items="${sellerCouponFM.couponApplyMember }" var="list"  varStatus="status">
		<tr><td height=4 colspan=5></td></tr>
		<tr height=25 align="center">
			<td><font class=ver81 color=616161>${(couponVO.rowCount - status.index) - ( couponVO.rowStart ) }</font></td>
			<td><font class=ver81 color=616161>${list.mid }</font></td>
			<td><font class=ver81 color=0074BA>${list.name }</font></td>
			<td>${list.used == 0 ? '<font class=ver81 color=333333>미사용</font>' : '<font color="EA0095">사용</font>' }</td>
		</tr>
		<tr><td height=4 colspan=5></td></tr>
		<tr><td colspan=5 class=rndline></td></tr>
	</c:forEach>
</table>
<div style="text-align:center; padding-top:30px;">
	<input type="button" class="btn btn-primary" value="닫기" onclick="self.close();" />
</div>