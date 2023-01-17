<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
/*
 * jQuery ready
 */
 $(document).ready(function() {
	 var ck = "${eventVO.eventObj.wview}";
	 if(ck=='n'){
		 $("#wviewn").attr("checked",true);
	 }else{
		 $("#wviewy").attr("checked",true);
	 }
	 
	 //submit시 이벤트기간 확인
	 $('.submitImg').on('click', function() {
		 
		if ($('[name=schSellerNm]').val() == ''){
			alert("판매사를 선택 해주세요");
			$('[name=schSellerNm]').focus();
			return false;
		}
		 
		var startDate = $('[name=sdate]').val();
		var endDate = $('[name=edate]').val();
		 
		if (startDate > endDate) {
			alert("이벤트 기간을 정확하게 입력해주세요");
			$('[name=sdate]').focus();
			return false;
		}
		 
		var approvalValue = $('input:radio[name="statusRadio"]:checked').val();
		if (approvalValue != '2' && approvalValue != '3'){ //저장시 승인, 반려가 아닌경우 승인대기로 변경
			$('[name=approvalstatus]').val('1'); //승인요청
		}else{
			$('[name=approvalstatus]').val(approvalValue);
		}
		
		var sellerCdValue = $('#schSellerCd').val();
		if('' != sellerCdValue && sellerCdValue != null && sellerCdValue != undefined){
			$('#sellercode').val(sellerCdValue);
		}
		
	});
	 
	//전체 적용 시 판매사 clear
	$("input[name='sellerRadio']").change(function(){
		if ($(this).val() === 'a') {
			$('#schSellerCd').val('');
			$('#schSellerNm').val('');
		}
	});
	
 });
</script>

<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

		<div class="title title_top">이벤트만들기<span>이벤트페이지를 직접 디자인하고 이벤트상품들을 선정하실 수 있습니다 </span><!-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=event&no=3',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle hspace=2></a>&nbsp; <a href="../design/design.banner.jsp"><font class=extext_l>[이벤트배너등록하기]</font></a> --></div>
		<form method="post" action="sellerEventIndb" enctype="multipart/form-data">
			<input type=hidden name=mode value="${eventVO.mode }">
			<c:if test="${eventVO.mode eq 'modify' }">
				<input type=hidden name=sno value="${eventVO.eventObj.sno}">
			</c:if>
			<input type=hidden name=clipb value="">
			<input type=hidden name="approvalstatus" id="approvalstatus" value="${eventVO.eventObj.approvalstatus}">
			<input type="hidden" name="sellercode" id="sellercode" value="${eventVO.eventObj.sellercode}" />
			<table class=tb>	
				<col class=cellC><col class=cellL>
				<tr>
					<td>판매사</td>
					<td>
						<input type="text" name="schSellerNm" id="schSellerNm" value="${eventVO.eventObj.sellername}" class="line" style="height:22px" readonly="readonly" />
						<input type="button" value="판매사 조회" onclick="popupLayer('${ctx}/shop/admin/common/sellerSearchPopup', 600, 500);" />
						<input type="hidden" name="schSellerCd" id="schSellerCd" value="${eventVO.eventObj.sellercode}" />
					</td>
				</tr>
				<tr>
					<td>이벤트제목</td>
					<td><input type=text name=subject style="width:600px" value="${eventVO.eventObj.subject}" required class=line maxlength="50"></input></td>
				</tr>
				<tr>
					<td>배너 이미지</td>
					<td><input type=file name="attach_file" style="width:300px" onChange="preview(this)" accept=".gif, .jpg, .png, .jpge, .bmp" >${ empty eventVO.eventObj.attach ? "" : eventVO.eventObj.attach }
					<input type="hidden" name="old_attach" value="${eventVO.eventObj.attach}"></td>
				</tr>
				<tr>
					<td>이벤트기간</td>
					<td>
						<input type=text name=sdate value="${eventVO.eventObj.sdate}" onclick="calendar(event)" required class="line" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" maxlength="8"> -
						<input type=text name=edate value="${eventVO.eventObj.edate}" onclick="calendar(event)" required class="line" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" maxlength="8">
						<font class="extext">기간을 입력하면 종료일 자정까지 효력이 발휘됩니다</font>
					</td>
				</tr>
				<tr>
					<td>당첨자 노출여부</td>
					<td><input type=radio name="wview" id="wviewn" value="n"> 미노출 <input type="radio" name="wview" id="wviewy" value="y"> 노출 </td>
				</tr>
				<tr>
					<td>이벤트내용<br>디자인 & HTML입력</td>
					<td>
						<textarea name="body" style="width:100%;height:350px" type="editor">${eventVO.eventObj.body}</textarea>
					</td>
				</tr>
				<tr>
					<td>합격자발표</td>
					<td>
						<textarea name="win" style="width:100%;height:350px" type="editor">${eventVO.eventObj.win}</textarea>
						<script src="/resources/shop/lib/meditor/mini_editor.js"></script>
						<script>mini_editor("/resources/shop/lib/meditor/");</script>
					</td>
				</tr>
				<tr>
					<td>게시여부</td>
					<td>
						<input type="radio" name="open" id="open_y" value="1" <c:if test="${1 == eventVO.eventObj.open}">checked</c:if>> 게시 
						<input type="radio" name="open" id="open_n" value="0" <c:if test="${1 != eventVO.eventObj.open}">checked</c:if>> 게시취소 
					</td>
				</tr>
				<c:if test="${eventVO.eventObj.approvalstatus != null && '' != eventVO.eventObj.approvalstatus}">
					<tr>
						<td>승인여부</td>
						<td>
							상태 : ${eventVO.eventObj.approvalstatusnm}
							&nbsp;&nbsp;&nbsp;
							<input type="radio" name="statusRadio" id="approvalstatus_2" value="2" <c:if test="${'2' == eventVO.eventObj.approvalstatus}">checked</c:if>> 승인 
							<input type="radio" name="statusRadio" id="approvalstatus_3" value="3" <c:if test="${'3' == eventVO.eventObj.approvalstatus}">checked</c:if>> 반려
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<textarea name="memo" id="memo" style="width:350px">${eventVO.eventObj.memo}</textarea>
						</td>
					</tr>
				</c:if>
			</table>
			<div class=button>
				<c:choose>
					<c:when test="${eventVO.mode eq 'modify' }">
						<input type=image src="/resources/shop/admin/img/btn_modify.gif" class="submitImg" />
					</c:when>
					<c:otherwise>
						<input type=image src="/resources/shop/admin/img/btn_register.gif" class="submitImg" />
					</c:otherwise>
				</c:choose>
				<a href="${ctx}/shop/admin/seller/sellerEventList"><img src="/resources/shop/admin/img/btn_cancel.gif"></a>
			</div>
<!-- 			<div style="padding-top:10px"></div>
			<div id=MSG01>
				<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
					<tr>
						<td><img src="../img/icon_list.gif" align="absmiddle">이벤트관련 배너를 만드시려면 <a href="../design/design.banner.jsp"><font color=white><u>[배너관리]</u></font></a> 로 가서 배너를 등록하시면 됩니다.</td>
					</tr>
				</table>
			</div>
			<script>cssRound('MSG01')</script> -->

		</form>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>


<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>