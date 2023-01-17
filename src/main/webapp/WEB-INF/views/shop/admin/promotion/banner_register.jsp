<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<script type="text/javascript">
	function chkBanner(frm){
		if (frm.title.value.trim() == ""){
			alert("배너제목을 입력해주세요.");
			frm.title.focus();
			return false;
		}
		
		if (frm.sdt.value.trim() == ""){
			alert("기간 시작일을 입력해주세요.");
			frm.sdt.focus();
			return false;
		}
		
		if (frm.edt.value.trim() == ""){
			alert("기간 종료일을 입력해주세요.");
			frm.edt.focus();
			return false;
		}
		
		if (frm.mode.value == "register"){
			if (frm.imgFile.value.trim() == ""){
				alert("PC 이미지를 입력해주세요.");
				frm.imgFile.focus();
				return false;
			}
			
			if (frm.imgMobileFile.value.trim() == ""){
				alert("모바일 이미지를 입력해주세요.");
				frm.imgMobileFile.focus();
				return false;
			}
		}
		
		//시작시간, 종료시간 설정
		$("#stm").val($("#shour").val() + ":" + $("#stime").val() + ":00");
		$("#etm").val($("#ehour").val() + ":" + $("#etime").val() + ":59");
		
		return true;
	}
</script>

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">
		<form id="frmRegi" name="frmRegi" method="post" action="indb" enctype="multipart/form-data" onsubmit="return chkBanner(this);">
			<input type="hidden" id="schSkin" name="schSkin" value="${bannerVO.schSkin}"/>
			<input type="hidden" id="schLoccd" name="schLoccd" value="${bannerVO.schLoccd}"/>
			<input type="hidden" id="schKey" name="schKey" value="${bannerVO.schKey}"/>
			<input type="hidden" id="schWord" name="schWord" value="${bannerVO.schWord}"/>
			<input type="hidden" id="schUsed" name="schUsed" value="${bannerVO.schUsed}"/>
			<input type="hidden" id="schSort" name="schSort" value="${bannerVO.schSort}"/>
			<input type="hidden" id="pageNo" name="pageNo" value="${bannerVO.pageNo}"/>
			
			<input type="hidden" name="mode" value="${bannerVO.mode}">
			<input type="hidden" name="sno" value="${bannerVO.sno}">
			
			<div class="title title_top">배너정보</div>
			<table class=tb>
				<col class=cellC><col class=cellL>
				<tr>
					<td>노출스킨</td>
					<td>
						<select name="skin" required>
							${webUtil:makeSelectCodeItem((codeUtil:codeitem('skin')), bannerVO==null ? '' : bannerVO.bannerObj.skin)}
						</select>
					</td>
				</tr>
				<tr>
					<td>위치</td>
					<td>
						<select name="loccd" required>
							${webUtil:makeSelectCodeItem((codeUtil:codeitem('bnloccd')), bannerVO==null ? '' : bannerVO.bannerObj.loccd)}
						</select>
					</td>
				</tr>
				<tr>
					<td>배너제목</td>
					<td><input type="text" name="title" style="width:50%" value="${bannerVO==null ? '' : bannerVO.bannerObj.title}" maxlength="15" required> <font class="extext">※ 15자 이내</font></td>
				</tr>
				<tr class="tr_img">
					<td rowspan="2">이미지</td>
					<td>
						<span class="itm" style="width: 80px;">PC 이미지</span>
						<input type="file" id="imgFile"name="imgFile" accept=".gif, .jpg, .png, .jpge, .bmp" > 
						<font class="extext" > <c:if test="${not empty bannerVO.bannerObj.img}"> (<a href="${bannerVO.bannerObj.img}" class="pcImgView" target="_blank">${bannerVO.bannerObj.img}</a> ) </c:if></font>
					</td>
				</tr>
				<tr class="tr_img">
					<td>
						<span class="itm" style="width: 80px;">모바일 이미지</span>
						<input type="file" id="imgMobileFile"name="imgMobileFile" accept=".gif, .jpg, .png, .jpge, .bmp" > 
						<font class="extext" > <c:if test="${not empty bannerVO.bannerObj.imgMobile}"> (<a href="${bannerVO.bannerObj.imgMobile}" class="moImgView" target="_blank">${bannerVO.bannerObj.imgMobile}</a> ) </c:if></font>
					</td>
				</tr>
				<tr>
					<td>URL</td>
					<td><input type="text" name="linkaddr" style="width:50%" required value="${bannerVO==null ? '' : bannerVO.bannerObj.linkaddr}"></td>
				</tr>
				<tr>
					<td>타겟</td>
					<td>
						<input type="radio" required name="target" value="_blank"  ${bannerVO==null ? '' : bannerVO.bannerObj.target eq '_blank' ? 'checked' : ''}> <label for="">새창</label> 
						<input type="radio" required name="target" value="_self"  ${bannerVO==null ? '' : bannerVO.bannerObj.target eq '_self' || bannerVO.bannerObj.target==null ? 'checked' : ''}> <label for="">현재창</label>
					</td>
				</tr>
				<tr>
					<td>Copy1</td>
					<td><input type="text" name="copy1" style="width:50%" value="${bannerVO==null ? '' : bannerVO.bannerObj.copy1}" maxlength="100"> <font class="extext">※ 100자 이내</font></td>
				</tr>
				<tr>
					<td>Copy2</td>
					<td><input type="text" name="copy2" style="width:50%" value="${bannerVO==null ? '' : bannerVO.bannerObj.copy2}" maxlength="100"> <font class="extext">※ 200자 이내</font></td>
				</tr>
				<tr>
					<td>우선순위</td>
					<td>
						<input type="text" required name="sort" value="${bannerVO==null ? '' : bannerVO.bannerObj.sort}" class="line" style="width:40px;" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" maxlength="2">
						<font class="extext">※ 00~99, 낮은 숫자의 우선순위가 높게 적용됩니다.</font>
					</td>
				</tr>
				<tr>
					<td>사용여부</td>
					<td>
						<input type="radio" name="used" required value="Y"  ${bannerVO==null ? '' : bannerVO.bannerObj.used eq 'Y' || bannerVO.bannerObj.used==null ? 'checked' : ''}> <label for="">사용</label> 
						<input type="radio" name="used" required value="N" ${bannerVO==null ? '' : bannerVO.bannerObj.used eq 'N' ? 'checked' : ''}> <label for="">중지</label>
					</td>
				</tr>
			</table>
		
			<div class="button">
				<c:choose>
					<c:when test="${bannerVO.mode eq 'modify'}">
						<input type="image" src="/resources/shop/admin/img/btn_modify.gif">
					</c:when>
					
					<c:otherwise>
						<input type="image" src="/resources/shop/admin/img/btn_register.gif">
					</c:otherwise>
				</c:choose>
			
				<a href="list?schSkin=${bannerVO.schSkin}&schLoccd=${bannerVO.schLoccd}&schKey=${bannerVO.schKey}&schWord=${bannerVO.schWord}&schUsed=${bannerVO.schUsed}&schSort=${bannerVO.schSort}&pageNo=${bannerVO.pageNo}"><img src="/resources/shop/admin/img/btn_list.gif"></a>
			</div>
		</form>

		<%@ include file="../common/bottom.jsp" %>
	</td>
</tr>
</table>