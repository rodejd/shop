<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script type="text/javascript">
/*
 * jQuery ready
 */
 $(document).ready(function() {
	 var ck = "${SellerEventFM.eventVO.wview}";
	 if(ck=='n'){
		 $("#wviewn").attr("checked",true);
	 }else{
		 $("#wviewy").attr("checked",true);
	 }
	 
	 //submit시 이벤트기간 확인
	 $('.submitImg').on('click', function() {
		var startDate = $('#sdate').val();
		var endDate = $('#edate').val();
		 
		if (startDate > endDate) {
			alert("이벤트 기간을 정확하게 입력해주세요");
			$('#sdate').focus();
			return false;
		}
		 
	});
	
 });
</script>

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

		<div class="title title_top">이벤트만들기<span>이벤트페이지를 직접 디자인하고 이벤트상품들을 선정하실 수 있습니다 </span></div>
		<form method="post" action="selEventIndb" enctype="multipart/form-data">
			<input type="hidden" name="mode" value="${SellerEventFM.mode }">
			<c:if test="${'modify' == SellerEventFM.mode}">
				<input type="hidden" name="eventVO.sno" value="${SellerEventFM.eventVO.sno}">
			</c:if>
			<!-- <input type="hidden" name="clipb" value=""> -->
			<input type="hidden" name="eventVO.approvalStatus" id="approvalStatus" value="1"> <!-- 저장시 승인요청 상태로 변경 -->
			<input type="hidden" name="eventVO.memo" id="memo" value=""> <!-- 저장시 메모 초기화 -->
			<c:if test="${setSellerCd != null && '' != setSellerCd}">
				<input type="hidden" name="eventVO.sellerCd" id="sellerCd" value="${setSellerCd}" />
			</c:if>
			<table class=tb>	
				<col class=cellC><col class=cellL>
				<tr>
					<td>이벤트제목</td>
					<td><input type="text" name="eventVO.subject" style="width:600px" value="${SellerEventFM.eventVO.subject}" required class="line" maxlength="50"></input></td>
				</tr>
				<tr>
					<td>배너 이미지</td>
					<td><input type="file" name="attachFile" style="width:300px" onChange="preview(this)" accept=".gif, .jpg, .png, .jpge, .bmp" >${ empty SellerEventFM.eventVO.attach ? "" : SellerEventFM.eventVO.attach }
					<input type="hidden" name="oldAttach" value="${SellerEventFM.eventVO.attach}"></td>
				</tr>
				<tr>
					<td>이벤트기간</td>
					<td>
						<input type="text" name="eventVO.sdate" id="sdate" value="${SellerEventFM.eventVO.sdate}" onclick="calendar(event)" required class="line" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" maxlength="8"> -
						<input type="text" name="eventVO.edate" id="edate" value="${SellerEventFM.eventVO.edate}" onclick="calendar(event)" required class="line" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" maxlength="8">
						<font class="extext">기간을 입력하면 종료일 자정까지 효력이 발휘됩니다</font>
					</td>
				</tr>
				<tr>
					<td>당첨자 노출여부</td>
					<td><input type=radio name="eventVO.wview" id="wviewn" value="n"> 미노출 <input type="radio" name="eventVO.wview" id="wviewy" value="y"> 노출 </td>
				</tr>
				<tr>
					<td>이벤트내용<br>디자인 & HTML입력</td>
					<td>
						<textarea name="eventVO.body" style="width:100%;height:350px" type="editor">${SellerEventFM.eventVO.body}</textarea>
					</td>
				</tr>
				<tr>
					<td>합격자발표</td>
					<td>
						<textarea name="eventVO.win" style="width:100%;height:350px" type="editor">${SellerEventFM.eventVO.win}</textarea>
						<script src="/resources/shop/lib/meditor/mini_editor.js"></script>
						<script>mini_editor("/resources/shop/lib/meditor/");</script>
					</td>
				</tr>
				<tr>
					<td>게시여부</td>
					<td>
						<input type="radio" name="eventVO.open" id="open_y" value="1" <c:if test="${1 == SellerEventFM.eventVO.open}">checked</c:if>> 게시 
						<input type="radio" name="eventVO.open" id="open_n" value="0" <c:if test="${1 != SellerEventFM.eventVO.open}">checked</c:if>> 게시취소 
					</td>
				</tr>
				<c:if test="${SellerEventFM.eventVO.approvalStatus != null && '' != SellerEventFM.eventVO.approvalStatus}">
					<tr>
						<td>승인여부</td>
						<td>
							상태 : ${SellerEventFM.eventVO.approvalStatusNm}
							<textarea style="width:350px" readonly="readonly">${SellerEventFM.eventVO.memo}</textarea>
						</td>
					</tr>
				</c:if>
			</table>
			<div class="button">
				<c:choose>
					<c:when test="${'Y' eq SellerEventFM.popView}">
						<input type="button" class="btn btn-primary" value="닫기" onclick="self.close();" />
					</c:when>
					<c:otherwise>
						<input type=image src="/resources/shop/admin/img/btn_${'modify' eq SellerEventFM.mode ? 'modify' : 'register'}.gif" class="submitImg" />
						<a href="${ctx }/shop/seller/event/eventList"><img src="/resources/shop/admin/img/btn_cancel.gif"></a>
					</c:otherwise>
				</c:choose>
			</div>
		</form>

		</td>
	</tr>
</table>