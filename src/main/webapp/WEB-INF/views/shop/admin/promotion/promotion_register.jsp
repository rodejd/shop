<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<script type="text/javascript">
	function setPeriod(sdt, edt){
		if ( null != sdt ){
			document.frmRegi.sdt.value = sdt;
		} else {
			document.frmRegi.sdt.value = "";
		}
		
		if ( null != edt ){
			document.frmRegi.edt.value = edt;
		} else {
			document.frmRegi.edt.value = "";
		}
	}
	
	function chkPromotion(frm){
		if (frm.title.value.trim() == ""){
			alert("기획전제목을 입력해주세요.");
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
			if (frm.pcImgFile.value.trim() == ""){
				alert("PC 이미지를 입력해주세요.");
				frm.pcImgFile.focus();
				return false;
			}
			
			if (frm.mobileImgFile.value.trim() == ""){
				alert("모바일 이미지를 입력해주세요.");
				frm.mobileImgFile.focus();
				return false;
			}
		}
		
		if (frm.copy1.value.trim() == ""){
			alert("Copy1을 입력해주세요.");
			frm.copy1.focus();
			return false;
		}
		
		if (frm.copy2.value.trim() == ""){
			alert("Copy2를 입력해주세요.");
			frm.copy2.focus();
			return false;
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
		<form id="frmRegi" name="frmRegi" method="post" action="indb" enctype="multipart/form-data" onsubmit="return chkPromotion(this);">
			<input type="hidden" id="schUseYn" name="schUseYn" value="${promotionVO.schUseYn}"/>
			<input type="hidden" id="schSkin" name="schSkin" value="${promotionVO.schSkin}"/>
			<input type="hidden" id="schWord" name="schWord" value="${promotionVO.schWord}"/>
			<input type="hidden" id="schSdt" name="schSdt" value="${promotionVO.schSdt}"/>
			<input type="hidden" id="schEdt" name="schEdt" value="${promotionVO.schEdt}"/>
			<input type="hidden" id="pageNo" name="pageNo" value="${promotionVO.pageNo}"/>
			
			<input type="hidden" name="mode" value="${promotionVO.mode}">
			<input type="hidden" name="pmno" value="${promotionVO.pmno}">
			<input type="hidden" id="stm" name="stm" value="">
			<input type="hidden" id="etm" name="etm" value="">
			
			<div class="title title_top">기획전정보</div>
			<table class=tb>
				<col class=cellC><col class=cellL>
				<tr>
					<td>노출스킨</td>
					<td>
						<select name="skin" required>
							${webUtil:makeSelectCodeItem((codeUtil:codeitem('skin')), promotionVO==null ? '' : promotionVO.promotionObj.skin)}
						</select>
					</td>
				</tr>
				<tr>
					<td>위치</td>
					<td>
						<select name="loccd" required>
							${webUtil:makeSelectCodeItem((codeUtil:codeitem('pmloccd')), promotionVO==null ? '' : promotionVO.promotionObj.loccd)}
						</select>
					</td>
				</tr>
				<tr>
					<td>기획전제목</td>
					<td><input type="text" name="title" style="width:50%" value="${promotionVO==null ? '' : promotionVO.promotionObj.title}" maxlength="100" required> <font class="extext">※ 100자 이내</font></td>
				</tr>
				<tr>
					<td>기간</td>
					<td colspan=3>
						<input type="text" id="sdt" name="sdt" value="${promotionVO.promotionObj.sdt}" onclick="calendar(event)" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" size="10"/>
						<c:set var="stm" value="${fn:split(promotionVO.promotionObj.stm,':')}" />
						<select id="shour" name="shour">
							<c:forEach begin="0" end="23" step="1" varStatus="status">
								<fmt:formatNumber var="hour" minIntegerDigits="2" value="${status.index}" type="number"/>
								<option value="${hour}" ${stringUtil:selected(stm[0], hour)}>${hour}시</option>
							</c:forEach>
						</select>
						<select id="stime" name="stime">
							<c:forEach begin="0" end="59" step="1" varStatus="status">
								<fmt:formatNumber var="time" minIntegerDigits="2" value="${status.index}" type="number"/>
								<option value="${time}" ${stringUtil:selected(stm[1], time)}>${time}분</option>
							</c:forEach>
						</select>
						~
						<input type="text" id="edt" name="edt" value="${promotionVO.promotionObj.edt}" onclick="calendar(event)" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" size="10"/>
						<c:set var="etm" value="${fn:split(promotionVO.promotionObj.etm,':')}" />
						<select id="ehour" name="ehour">
							<c:forEach begin="0" end="23" step="1" varStatus="status">
								<fmt:formatNumber var="hour" minIntegerDigits="2" value="${status.index}" type="number"/>
								<option value="${hour}" ${stringUtil:selected(etm[0], hour)}>${hour}시</option>
							</c:forEach>
						</select>
						<select id="etime" name="etime">
							<c:forEach begin="0" end="59" step="1" varStatus="status">
								<fmt:formatNumber var="time" minIntegerDigits="2" value="${status.index}" type="number"/>
								<option value="${time}" ${stringUtil:selected(etm[1], time)}>${time}분</option>
							</c:forEach>
						</select>
					 	<a href="javascript:setPeriod(${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align="absmiddle" /></a>
						<a href="javascript:setPeriod(${dateUtil:getDateFormatFrom('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-7)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align="absmiddle" /></a>
						<a href="javascript:setPeriod(${dateUtil:getDateFormatFrom('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-15)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align="absmiddle" /></a>
						<a href="javascript:setPeriod(${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-1)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align="absmiddle" /></a>
						<a href="javascript:setPeriod(${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-2)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align="absmiddle" /></a>
					</td>
				</tr>
				<tr class="tr_img">
					<td rowspan="2">이미지</td>
					<td>
						<span class="itm" style="width: 80px;">PC 이미지</span>
						<input type="file" id="pcImgFile"name="pcImgFile" accept=".gif, .jpg, .png, .jpge, .bmp" > 
						<font class="extext" > <c:if test="${not empty promotionVO.promotionObj.pcImg}"> (<a href="${promotionVO.promotionObj.pcImg}" class="pcImgView" target="_blank">${promotionVO.promotionObj.pcImg}</a> ) </c:if></font>
					</td>
				</tr>
				<tr class="tr_img">
					<td>
						<span class="itm" style="width: 80px;">모바일 이미지</span>
						<input type="file" id="mobileImgFile"name="mobileImgFile" accept=".gif, .jpg, .png, .jpge, .bmp" > 
						<font class="extext" > <c:if test="${not empty promotionVO.promotionObj.mobileImg}"> (<a href="${promotionVO.promotionObj.mobileImg}" class="moImgView" target="_blank">${promotionVO.promotionObj.mobileImg}</a> ) </c:if></font>
					</td>
				</tr>
				<tr>
					<td>Copy1</td>
					<td><input type="text" name="copy1" style="width:50%" value="${promotionVO==null ? '' : promotionVO.promotionObj.copy1}" maxlength="100" required> <font class="extext">※ 100자 이내</font></td>
				</tr>
				<tr>
					<td>Copy2</td>
					<td><input type="text" name="copy2" style="width:50%" value="${promotionVO==null ? '' : promotionVO.promotionObj.copy2}" maxlength="100" required> <font class="extext">※ 100자 이내</font></td>
				</tr>
				<tr>
					<td>사용여부</td>
					<td>
						<input type="radio" id="useY" name="useYn" required value="Y"  ${promotionVO==null ? '' : promotionVO.promotionObj.useYn eq 'Y' || promotionVO.promotionObj.useYn==null ? 'checked' : ''}> <label for="useY">사용</label> 
						<input type="radio" id="useN" name="useYn" required value="N" ${promotionVO==null ? '' : promotionVO.promotionObj.useYn eq 'N' ? 'checked' : ''}> <label for="useN">중지</label>
					</td>
				</tr>
				<c:if test="${promotionVO.mode eq 'modify'}">
				<tr>
					<td>상품</td>
					<td>${promotionVO.promotionObj.goodsCnt} <a href="goods?pmno=${promotionVO.pmno}&schUseYn=${promotionVO.schUseYn}&schSkin=${promotionVO.schSkin}&schWord=${promotionVO.schWord}&schSdt=${promotionVO.schSdt}&schEdt=${promotionVO.schEdt}&pageNo=${promotionVO.pageNo}"><img src="/resources/shop/admin/img/i_add.gif" align="absmiddle"></a></td>
				</tr>
				</c:if>
			</table>
			
			<div class="button">
				<c:choose>
					<c:when test="${promotionVO.mode eq 'modify'}">
						<input type="image" src="/resources/shop/admin/img/btn_modify.gif">
					</c:when>
					
					<c:otherwise>
						<input type="image" src="/resources/shop/admin/img/btn_register.gif">
					</c:otherwise>
				</c:choose>
			
				<a href="list?schUseYn=${promotionVO.schUseYn}&schSkin=${promotionVO.schSkin}&schWord=${promotionVO.schWord}&schSdt=${promotionVO.schSdt}&schEdt=${promotionVO.schEdt}&pageNo=${promotionVO.pageNo}"><img src="/resources/shop/admin/img/btn_list.gif"></a>
			</div>
		</form>

		<%@ include file="../common/bottom.jsp" %>
	</td>
</tr>
</table>