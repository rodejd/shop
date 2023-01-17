<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
	$(function(){
		//캠페인 타입
		$("#gbn").on("change", function(){
			$("#pcImgFile").val("");
			$("#mobileImg").val("");
			
			if( $(this).val() == "I" ){
				$(".tr_img").show();
			}else{
				$(".tr_img").hide();
			}
		});
		
		//저장
		$(".btn_submit").on("click",function(){
			if( $("#skin").val() == "" ){
				alert("스킨을 선택해주세요.");
				$("#skin").focus();
				return;
			}
			if( $.trim($("#title").val()) == "" ){
				alert("캠페인명을 입력해주세요");
				$("#title").focus();
				return;
			}
			
			if( $.trim($("#gbn").val()) == "" ){
				alert("캠페인 타입을 선택해주세요");
				$("#gbn").focus();
				return;
			}
			
			if( $("#gbn").val() == "I"){
				if( $("#pcImgFile").val() == "" && $(".pcImgView").length == 0 ){
					alert("PC이미지를 등록해주세요");
					$("#pcImgFile").focus();
					return;
				}
				
				if( $("#mobileImg").val() == "" && $(".moImgView").length == 0 ){
					alert("모바일 이미지를 등록해주세요");
					$("#mobileImg").focus();
					return;
				}
			}
			
			if( $.trim($("#sdt").val()) == "" ){
				alert("기간 시작일을 입력해주세요.");
				$("#sdt").focus();
				return;
			}
			if( $.trim($("#shour").val()) == "" ){
				alert("기간 시작시간을 선택해주세요.");
				$("#shour").focus();
				return;
			}
			if( $.trim($("#stime").val()) == "" ){
				alert("기간 시작분을 선택해주세요.");
				$("#stime").focus();
				return;
			}
			
			if( $.trim($("#edt").val()) == "" ){
				alert("기간 종료일을 입력해주세요.");
				$("#edt").focus();
				return;
			}
			if( $.trim($("#ehour").val()) == "" ){
				alert("기간 종료시간을 선택해주세요.");
				$("#ehour").focus();
				return;
			}
			if( $.trim($("#etime").val()) == "" ){
				alert("기간 종료분을 선택해주세요.");
				$("#etime").focus();
				return;
			}
			
			if( $("[name=useYn]:checked").length == 0 ){
				alert("사용여부를 선택해주세요.");
				$("#useYn").focus();
				return;
			}
			
			//시작시간, 종료시간 설정
			$("#stm").val($("#shour").val() + ":" + $("#stime").val() + ":00");
			$("#etm").val($("#ehour").val() + ":" + $("#etime").val() + ":00");
			
			var txt = "저장";
			if("${mainCampaignVO.mode}" == "modify") txt = "수정";
			
			if( !confirm(txt + " 하시겠습니까?") ) return;
			$("#writeForm").attr("action", "/shop/admin/promotion/campaign/indb").submit();
		});
	});
	
	// 전시기간
	var setPeriod = function(dt1, dt2){
		if ( null != dt1 ){
			$('[name=sdt]').val(dt1);
		} else {
			$('[name=sdt]').val('');
		}
		
		if ( null != dt2 ){
			$('[name=edt]').val(dt2);
		} else {
			$('[name=edt]').val('');
		}
	};
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
<form id="writeForm" method="post" action="/shop/admin/promotion/campaign/indb" enctype="multipart/form-data">
	<input type="hidden" name="sno" value="${mainCampaignVO.sno}">
	<input type="hidden" name="mode" value="${mainCampaignVO.mode}">
	<input type="hidden" id="stm" name="stm" value="">
	<input type="hidden" id="etm" name="etm" value="">
	
	<div class="title title_top">메인 캠페인 관리</div>
	<table class=tb>
		<col class=cellC><col class=cellL>
		<tr>
			<td>스킨명</td>
			<td>
				<select id="skin" name="skin" class="cline" style="width: 100px;">
					<option value="">전체</option>
					<option value="al" ${stringUtil:selected(mainCampaignVO.campaignObj.skin, "al")}>ALL</option>
					${webUtil:makeSelectCodeItem((codeUtil:codeitem('skin')), mainCampaignVO.campaignObj.skin) }
				</select>
			</td>
		</tr>
		<tr>
			<td>캠페인명</td>
			<td><input type="text" id="title" name="title" class="cline" style="width:50%; text-align: left;" value="${mainCampaignVO.campaignObj.title}" maxlength="50"></td>
		</tr>
		<tr>
			<td>캠페인 타입</td>
			<td>
				<select id="gbn" name="gbn" class="cline" style="width: 120px;" >
					<option value="T" ${stringUtil:selected(mainCampaignVO.campaignObj.gbn, "T")}>TEX형</option>
					<option value="I" ${stringUtil:selected(mainCampaignVO.campaignObj.gbn, "I")}>배너(이미지)형</option>
				</select>
			</td>
		</tr>
		<tr class="tr_img" ${mainCampaignVO.mode eq 'register' or mainCampaignVO.campaignObj.gbn eq 'T' ? 'style="display:none;"' : ''} >
			<td rowspan="2">이미지</td>
			<td><span class="itm" style="width: 80px;">PC 이미지</span><input type="file" id="pcImgFile"name="pcImgFile" accept=".gif, .jpg, .png, .jpge, .bmp" > <font class="extext" > <c:if test="${not empty mainCampaignVO.campaignObj.pcImg}"> (<a href="${mainCampaignVO.campaignObj.pcImg}" class="pcImgView" target="_blank">${mainCampaignVO.campaignObj.pcImg}</a> ) </c:if></font></td>
		</tr>
		<tr class="tr_img" ${mainCampaignVO.mode eq 'register' or mainCampaignVO.campaignObj.gbn eq 'T' ? 'style="display:none;"' : ''}>
			<td><span class="itm" style="width: 80px;">모바일 이미지</span><input type="file" id="mobileImgFile"name="mobileImgFile" accept=".gif, .jpg, .png, .jpge, .bmp" > <font class="extext" > <c:if test="${not empty mainCampaignVO.campaignObj.mobileImg}"> (<a href="${mainCampaignVO.campaignObj.mobileImg}" class="moImgView" target="_blank">${mainCampaignVO.campaignObj.mobileImg}</a> ) </c:if></font></td>
		</tr>
		<tr>
			<td>기간</td>
			<td colspan=3>
				<input type="text" id="sdt" name="sdt" value="${mainCampaignVO.campaignObj.sdt}" onclick="calendar(event)" class="cline" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);"/>
				<c:set var="stm" value="${fn:split(mainCampaignVO.campaignObj.stm,':')}" />
				<select id="shour" name="shour" class="cline" style="width: 120px;">
					<option value="">시작시간 선택</option>
					<c:forEach begin="0" end="23" step="1" varStatus="status">
						<fmt:formatNumber var="hour" minIntegerDigits="2" value="${status.index}" type="number"/>
						<option value="${hour}" ${stringUtil:selected(stm[0], hour)}>${hour}시</option>
					</c:forEach>
				</select>
				<select id="stime" name="stime" class="cline" style="width: 120px;">
					<option value="">시작분 선택</option>
					<c:forEach begin="0" end="59" step="1" varStatus="status">
						<fmt:formatNumber var="time" minIntegerDigits="2" value="${status.index}" type="number"/>
						<option value="${time}" ${stringUtil:selected(stm[1], time)}>${time}분</option>
					</c:forEach>
				</select>
				~
				<input type="text" id="edt" name="edt" value="${mainCampaignVO.campaignObj.edt}" onclick="calendar(event)" class="cline" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);"/>
				<c:set var="etm" value="${fn:split(mainCampaignVO.campaignObj.etm,':')}" />
				<select id="ehour" name="ehour" class="cline" style="width: 120px;">
					<option value="">종료시간 선택</option>
					<c:forEach begin="0" end="23" step="1" varStatus="status">
						<fmt:formatNumber var="hour" minIntegerDigits="2" value="${status.index}" type="number"/>
						<option value="${hour}" ${stringUtil:selected(etm[0], hour)}>${hour}시</option>
					</c:forEach>
				</select>
				<select id="etime" name="etime" class="cline" style="width: 120px;">
					<option value="">종료분 선택</option>
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
		<!-- 
		<tr>
			<td>우선순위</td>
			<td>
				<input type="text" id="sort" name="sort" value="${mainCampaignVO.campaignObj.sort}" class="line" style="width:40px;" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" maxlength="2">
				<font class="extext">※ 00~99, 낮은 숫자의 우선순위가 높게 적용됩니다.</font>
			</td>
		</tr>
		 -->
		<tr>
			<td>사용여부</td>
			<td>
				<input type="radio" id="useYn1" name="useYn" value="Y" ${empty mainCampaignVO.campaignObj.useYn or mainCampaignVO.campaignObj.useYn eq 'Y' ? 'checked' : '' }> <label for="useYn1">사용</label> 
				<input type="radio" id="useYn2" name="useYn" value="N" ${mainCampaignVO.campaignObj.useYn eq 'N' ? 'checked' : '' }> <label for="useYn2">중지</label>
			</td>
		</tr>
	</table>

	<div class="button">
		<c:choose>
			<c:when test="${mainCampaignVO.mode eq 'modify' }">
				<a href="javascript:;" class="btn_submit"><img src="/resources/shop/admin/img/btn_modify.gif"></a>
			</c:when>
			<c:otherwise>
				<a href="javascript:;" class="btn_submit"><img src="/resources/shop/admin/img/btn_register.gif"></a>
			</c:otherwise>
		</c:choose>
		<a href="/shop/admin/promotion/campaign/list"><img src="/resources/shop/admin/img/btn_list.gif"></a>
	</div>
</form>


<%@ include file="../common/bottom.jsp" %>
	</td>
</tr>
</table>