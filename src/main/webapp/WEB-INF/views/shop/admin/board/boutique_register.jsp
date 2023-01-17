<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<script type="text/javascript">
	function chkBoutique(frm){
		if (frm.title.value.trim() == ""){
			alert("제목을 입력해주세요.");
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
			
			if (frm.imgmFile.value.trim() == ""){
				alert("모바일 이미지를 입력해주세요.");
				frm.imgmFile.focus();
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
		<form id="frmRegi" name="frmRegi" method="post" action="indb" enctype="multipart/form-data" onsubmit="return chkBoutique(this);">
			<input type="hidden" id="schSkin" name="schSkin" value="${boutiqueVO.schSkin}"/>
			<input type="hidden" id="schKey" name="schKey" value="${boutiqueVO.schKey}"/>
			<input type="hidden" id="schWord" name="schWord" value="${boutiqueVO.schWord}"/>
			<input type="hidden" id="schUsed" name="schUsed" value="${boutiqueVO.schUsed}"/>
			<input type="hidden" id="schSort" name="schSort" value="${boutiqueVO.schSort}"/>
			<input type="hidden" id="pageNo" name="pageNo" value="${boutiqueVO.pageNo}"/>
			
			<input type="hidden" name="mode" value="${boutiqueVO.mode}">
			<input type="hidden" name="sno" value="${boutiqueVO.sno}">
			
			<div class="title title_top">부티크정보</div>
			<table class=tb>
				<col class=cellC><col class=cellL>
				<tr>
					<td>노출스킨</td>
					<td>
						<select name="skin" required>
							${webUtil:makeSelectCodeItem((codeUtil:codeitem('skin')), boutiqueVO==null ? '' : boutiqueVO.boutiqueObj.skin)}
						</select>
					</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" style="width:50%" value="${boutiqueVO==null ? '' : boutiqueVO.boutiqueObj.title}" maxlength="15" required> <font class="extext">※ 15자 이내</font></td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea name="content" cols="60" rows="9" style="width:90%;" class="tline" maxlength="2000">${boutiqueVO==null ? '' : boutiqueVO.boutiqueObj.content}</textarea>
					</td>
				</tr>
				<tr class="tr_img">
					<td rowspan="2">이미지</td>
					<td>
						<span class="itm" style="width: 80px;">PC 이미지</span>
						<input type="file" id="imgFile"name="imgFile" accept=".gif, .jpg, .png, .jpge, .bmp" > 
						<font class="extext" > <c:if test="${not empty boutiqueVO.boutiqueObj.img}"> (<a href="${boutiqueVO.boutiqueObj.img}" class="pcImgView" target="_blank">${boutiqueVO.boutiqueObj.img}</a> ) </c:if></font>
					</td>
				</tr>
				<tr class="tr_img">
					<td>
						<span class="itm" style="width: 80px;">모바일 이미지</span>
						<input type="file" id="imgmFile"name="imgmFile" accept=".gif, .jpg, .png, .jpge, .bmp" > 
						<font class="extext" > <c:if test="${not empty boutiqueVO.boutiqueObj.imgm}"> (<a href="${boutiqueVO.boutiqueObj.imgm}" class="moImgView" target="_blank">${boutiqueVO.boutiqueObj.imgm}</a> ) </c:if></font>
					</td>
				</tr>
				<tr>
					<td>URL</td>
					<td><input type="text" name="linkaddr" style="width:50%" required value="${boutiqueVO==null ? '' : boutiqueVO.boutiqueObj.linkaddr}"></td>
				</tr>
				<tr>
					<td>타겟</td>
					<td>
						<input type="radio" required name="target" value="_blank"  ${boutiqueVO==null ? '' : boutiqueVO.boutiqueObj.target eq '_blank' ? 'checked' : ''}> <label for="">새창</label> 
						<input type="radio" required name="target" value="_self"  ${boutiqueVO==null ? '' : boutiqueVO.boutiqueObj.target eq '_self' || boutiqueVO.boutiqueObj.target==null ? 'checked' : ''}> <label for="">현재창</label>
					</td>
				</tr>
				<tr>
					<td>우선순위</td>
					<td>
						<input type="text" required name="sort" value="${boutiqueVO==null ? '' : boutiqueVO.boutiqueObj.sort}" class="line" style="width:40px;" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" maxlength="2">
						<font class="extext">※ 00~99, 낮은 숫자의 우선순위가 높게 적용됩니다.</font>
					</td>
				</tr>
				<tr>
					<td>사용여부</td>
					<td>
						<input type="radio" name="used" required value="Y"  ${boutiqueVO==null ? '' : boutiqueVO.boutiqueObj.used eq 'Y' || boutiqueVO.boutiqueObj.used==null ? 'checked' : ''}> <label for="">사용</label> 
						<input type="radio" name="used" required value="N" ${boutiqueVO==null ? '' : boutiqueVO.boutiqueObj.used eq 'N' ? 'checked' : ''}> <label for="">중지</label>
					</td>
				</tr>
			</table>
		
			<div class="button">
				<c:choose>
					<c:when test="${boutiqueVO.mode eq 'modify'}">
						<input type="image" src="/resources/shop/admin/img/btn_modify.gif">
					</c:when>
					
					<c:otherwise>
						<input type="image" src="/resources/shop/admin/img/btn_register.gif">
					</c:otherwise>
				</c:choose>
			
				<a href="list?schSkin=${boutiqueVO.schSkin}&schKey=${boutiqueVO.schKey}&schWord=${boutiqueVO.schWord}&schUsed=${boutiqueVO.schUsed}&schSort=${boutiqueVO.schSort}&pageNo=${boutiqueVO.pageNo}"><img src="/resources/shop/admin/img/btn_list.gif"></a>
			</div>
		</form>

		<%@ include file="../common/bottom.jsp" %>
	</td>
</tr>
</table>