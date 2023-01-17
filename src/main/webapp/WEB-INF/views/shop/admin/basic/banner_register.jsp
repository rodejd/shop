<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>


<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<form method="post" enctype="multipart/form-data" id="fm" action="indb">
	<input type="hidden" name="sno" value="${bannerVO.sno}">
	<input type="hidden" name="mode" value="${bannerVO.mode}">
	<input type="hidden" name="type" value="${bannerVO.type}">
	<input type="hidden" name="oldImg" value="${bannerVO.bannerObj.img}">
	<input type="hidden" name="oldImgMobile" value="${bannerVO.bannerObj.imgMobile}">
	<div class="title title_top" id="mainBannerTitleName">
	<c:choose>
		<c:when test="${bannerVO.type eq 'main1' }">
			메인1배너
		</c:when>
		<c:when test="${bannerVO.type eq 'main2' }">
			메인2배너
		</c:when>
		<c:when test="${bannerVO.type eq 'main3' }">
			메인3배너
		</c:when>
		<c:when test="${bannerVO.type eq 'main4' }">
			메인4배너
		</c:when>
		<c:otherwise>
			상단롤링배너
		</c:otherwise>
	</c:choose>
	</div>
	<table class=tb>
		<col class=cellC><col class=cellL>
		<tr>
			<td>배너제목</td>
			<td><input type="text" required name="title" style="width:50%" value="${bannerVO==null ? '' : bannerVO.bannerObj.title }" maxlength="15"> <font class="extext">※ 15자 이내</font></td>
		</tr>
<c:choose>
<c:when test="${bannerVO.type eq 'top' }">
		<tr>
			<!-- <td rowspan="2">이미지</td> -->
			<td>이미지</td>
			<td><span class="itm">PC용</span><input type="file" id="imgPc"name="imgPc" ${bannerVO.bannerObj.img==null ? 'required' : '' } accept=".gif, .jpg, .png, .jpge, .bmp" > <font class="extext" >※ 이미지 사이즈 : 2000px * 1200px <c:if test="${not empty bannerVO.bannerObj.img}"> (<a href="${bannerVO.bannerObj.img}" target="_blank">${bannerVO.bannerObj.img}</a> ) </c:if></font></td>
		</tr>
		<%-- <tr>
			<td><span class="itm">Mobile용</span><input type="file" id="imgMobileFile" name="imgMobileFile" ${bannerVO.bannerObj.imgMobile==null ? 'required' : '' }> <font class="extext">※ 이미지 사이즈 : 640px * 960px  (${bannerVO.bannerObj.imgMobile==null ? '' : bannerVO.bannerObj.imgMobile })</font></td>
		</tr> --%>
 </c:when>
<c:otherwise>
		<tr>
			<td>이미지</td>
			<td><span class="itm">PC용</span><input type="file" name="imgPc"  id="imgPc"  ${bannerVO.bannerObj.img==null ? 'required' : '' } accept=".gif, .jpg, .png, .jpge, .bmp" >
				<font class="extext">
					※ 이미지 사이즈 : 
					<c:choose>
						<c:when test="${bannerVO.type eq 'main1' }">
							370px * 200px  
						</c:when>
						<c:when test="${bannerVO.type eq 'main2' }">
							562.5px * 200px  
						</c:when>
						<c:otherwise>
							1140px * 200px  
						</c:otherwise>
					</c:choose>
					<c:if test="${not empty bannerVO.bannerObj.img}"> (<a href="${bannerVO.bannerObj.img}" target="_blank">${bannerVO.bannerObj.img}</a> ) </c:if>
				</font>
			</td>
		</tr>
</c:otherwise>	
 </c:choose>
		<tr>
			<td>URL</td>
			<td><input type="text" name="linkaddr" style="width:50%" required value="${bannerVO==null ? '' : bannerVO.bannerObj.linkaddr }"></td>
		</tr>
		<tr>
			<td>타겟</td>
			<td>
				<input type="radio" required name="target" value="_blank"  ${bannerVO==null ? '' : bannerVO.bannerObj.target eq '_blank' ? 'checked' : '' }> <label for="">새창</label> 
				<input type="radio" required name="target" value="_self"  ${bannerVO==null ? '' : bannerVO.bannerObj.target eq '_self' ? 'checked' : '' }> <label for="">현재창</label>
			</td>
		</tr>
		<tr>
			<td>우선순위</td>
			<td>
				<input type="text" required name="sort" value="${bannerVO==null ? '' : bannerVO.bannerObj.sort }" class="line" style="width:40px;" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" maxlength="2">
				<font class="extext">※ 00~99, 낮은 숫자의 우선순위가 높게 적용됩니다.</font>
			</td>
		</tr>
		<tr>
			<td>사용여부</td>
			<td>
				<input type="radio" name="used" required value="Y"  ${bannerVO==null ? '' : bannerVO.bannerObj.used eq 'Y' ? 'checked' : '' }> <label for="">사용</label> 
				<input type="radio" name="used" required value="N" ${bannerVO==null ? '' : bannerVO.bannerObj.used eq 'N' ? 'checked' : '' }> <label for="">중지</label>
			</td>
		</tr>
	</table>

	<div class="button">
	
	<c:choose>
		<c:when test="${bannerVO.mode eq 'modify' }">
			<input type="image" src="/resources/shop/admin/img/btn_modify.gif">
		</c:when>
		
		<c:otherwise>
			<input type="image" src="/resources/shop/admin/img/btn_register.gif">
		</c:otherwise>
	</c:choose>
	
		<a href="list?type=${bannerVO.type}"><img src="/resources/shop/admin/img/btn_list.gif"></a>
	</div>
</form>


<%@ include file="../common/bottom.jsp" %>
	</td>
</tr>
</table>

<script language="javascript">


</script>