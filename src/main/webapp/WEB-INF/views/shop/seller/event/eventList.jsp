<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script type="text/javascript">
/*
 * jQuery ready
 */
$(function(){
	// 검색
	$('#searchBtn').on('click', function(){
		$('#eventSchForm').attr("action", ctx+"/shop/seller/event/eventList");
		// 승인상태
		$("#schAprlStat").val($('#eventSchForm input:radio[name="tmpAprlstat"]:checked').val());
		$('#eventSchForm').submit();
	});
	
});

<%-- // 게시 여부/승인여부 변경 --%>
function chk_status_modify(statVal, mode){
	var i = 0;
	var eventChks = null;
	var eventSchFrm = null;
	
	eventChks = $("#eventList input:checkbox:checked");
	eventSchFrm = $("#eventSchForm");
	
	if(0 >= eventChks.length){
		alert(("open_modify" == mode) ? "게시/게시취소 상품을 선택하세요." : "승인/반려 상품을 선택하세요.");
		return false;
	}
	
	if(confirm(("open_modify" == mode) ? "게시/게시취소 하시겠습니까?" : "승인/반려 하시겠습니까?")){
		eventChks.each(function(){
			eventSchFrm.append("<input type='hidden' name='eventArr' value='"+$(this).val()+"'/>");
			eventSchFrm.append("<input type='hidden' name='sellercodeArr' value='"+$(this).data("sellercode")+"'/>");
		});
		
		$("#statVal").val(statVal);
		$("#mode").val(mode);
		
		$('#eventSchForm').attr("action", "selEventIndb2");
		
		eventSchFrm.submit();
	}
}

function goPage(page){
	$('#eventSchForm').attr("action", ctx+"/shop/seller/event/eventList");
	$("#pageNo").val(Number(page));
	$('#eventSchForm').submit();
}

function sort(sort){
	$('#eventSchForm').attr("action", ctx+"/shop/seller/event/eventList");
	$('#schSort').val(sort);
	$('#eventSchForm').submit();
}
</script>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<form name="eventSchForm" id="eventSchForm" method="get" action="${ctx }/shop/seller/event/eventList">
	<input type="hidden" id="pageNo" name="pageNo" value="1"/>
	<input type="hidden" id="schSort" name="schSort" value=""/>  
	<input type="hidden" id="schAprlStat" name="schAprlStat" value="" />
	<input type="hidden" id="statVal" 	name="statVal"		value="" /> 
	<input type="hidden" id="mode" 		name="mode"			value="" /> 
	
	<div class="title title_top">이벤트리스트<span>이벤트페이지를 직접 디자인하고 이벤트상품들을 선정하실 수 있습니다 </span></div>
	
	<%-- 검색영역시작 --%>
	<table class=tb>
		<tr>
			<td>승인상태</td>
			<td>
				<input type=radio name="tmpAprlstat" value="" 	${empty sellerEventFM.schAprlStat ? 'checked="checked"' : '' } >전체
				<input type="radio" name="tmpAprlstat" value="1" ${sellerEventFM.schAprlStat eq '1' ? 'checked="checked"' : '' } /> ${codeUtil:getCodeName('AS', '1')}
				<input type="radio" name="tmpAprlstat" value="2" ${sellerEventFM.schAprlStat eq '2' ? 'checked="checked"' : '' } /> ${codeUtil:getCodeName('AS', '2')}
				<input type="radio" name="tmpAprlstat" value="3" ${sellerEventFM.schAprlStat eq '3' ? 'checked="checked"' : '' } /> ${codeUtil:getCodeName('AS', '3')}
			</td>
		</tr>
	</table>
	
	<div class=button_top><img src="/resources/shop/admin/img/btn_search2.gif" id="searchBtn" style='cursor:hand;' /></div>
	<%-- 검색영역끝--%>

	<div style="padding-top:15px"></div>
	
	<table width=100% cellpadding=0 cellspacing=0>
		<tr>
			<td class=pageInfo><font class=ver8>
				<c:set var="pages" value="${(sellerEventFM.rowCount * 10) / (sellerEventFM.pageSize * 10)} " />
				<c:set var="pageCnt" value="${pages + (1 - (pages % 1)) % 1}" />
				<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
				총 <b>${sellerEventFM.rowCount}</b>개, <b>${sellerEventFM.pageNo }</b> of <b>${var3 }</b> Pages
			</td>
			<td align=right>
				<table cellpadding=0 cellspacing=0 border=0>
					<tr>
						<td valign=bottom>
							이벤트번호
							<a href="javascript:sort('snoDESC')"><img id="snoDESC" src="/resources/shop/admin/img/list_up_off.gif"></a>
							<a href="javascript:sort('snoASC')"><img id="snoASC" src="/resources/shop/admin/img/list_down_off.gif"></a>
							이벤트시작일
							<a href="javascript:sort('sdateDESC') "><img id="sdateDESC" src="/resources/shop/admin/img/list_up_off.gif"></a>
							<a href="javascript:sort('sdateASC' )"><img id="sdateASC" src="/resources/shop/admin/img/list_down_off.gif"></a>
							
							이벤트만료일
							<a href="javascript:sort('edateDESC')"><img id="edateDESC" src="/resources/shop/admin/img/list_up_off.gif"></a>
							<a href="javascript:sort('edateASC')"><img id="edateASC" src="/resources/shop/admin/img/list_down_off.gif"></a>
						</td>
						<td style="padding-left:20px">
							<img src="/resources/shop/admin/img/sname_output.gif" align=absmiddle>
								<select name=pageSize onchange="this.form.submit()">
									<option value="10" ${stringUtil:selected(sellerEventFM.pageSize, "10")} >10개 출력</option>
									<option value="20" ${stringUtil:selected(sellerEventFM.pageSize, "20")} >20개 출력</option>
									<option value="40" ${stringUtil:selected(sellerEventFM.pageSize, "40")} >40개 출력</option>
									<option value="60" ${stringUtil:selected(sellerEventFM.pageSize, "60")} >60개 출력</option>
									<option value="100" ${stringUtil:selected(sellerEventFM.pageSize, "100")} >100개 출력</option>
								</select>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>


	<table id="eventList" width=100% cellpadding=0 cellspacing=0>
		<tr class=rndbg>
			<th width=100>선택</th>
			<th width=100>이벤트번호</th>
			<th>이벤트제목</th>
			<th width=100>이벤트시작일</th>
			<th width=100>이벤트만료일</th>
			<th width=100>판매사</th>
			<th width=100>게시여부</th>
			<th width=100>승인여부</th>
			<th width=100>승인상태</th>
			<th width=100>수정</th>
		</tr>
		<tr><td class=rnd colspan=10></td></tr>
		
<c:if test="${!empty sellerEventFM.eventList}">
	<c:forEach items="${sellerEventFM.eventList}" var="eventList" varStatus="status">
		<tr><td height=10 colspan=15></td></tr>
		<tr height=25>
			<td align=center style="padding-bottom:9">
				<font class=ver81>
					<input type="checkbox" name="chk[]" value="${eventList.sno}" data-sellercode="${eventList.sellerCd}" onclick="">
				</font>
			</td>
			<td align=center style="padding-bottom:9"><font class=ver81>${eventList.sno}</td>
			<td class=cell><font class=small1 color=555555><b>
				<a href="javascript:popup('${ctx }/shop/seller/event/eventRegister?popView=Y&mode=modify&schSno=${eventList.sno}',900,600);">${eventList.subject}</a>
			</b></font></td>
			<td align=center style="padding-bottom:5"><font class=ver8 color=EB4200>
				<c:if test="${ eventList.sdate != '' }">${dateUtil:getDateFormat("yyyy-MM-dd", eventList.sdate)}</c:if>
			</font></td>
			<td align=center style="padding-bottom:5"><font class=ver8 color=EB4200>
				<c:if test="${ eventList.edate != '' }">${dateUtil:getDateFormat("yyyy-MM-dd", eventList.edate)}</c:if>
			</font></td>
			<td align=center style="padding-bottom:9"><font class=ver81>${eventList.sellerNm}</td>
			<td align=center style="padding-bottom:9"><font class=ver81><img src="/resources/shop/admin/img/icn_${eventList.open}.gif"></td>
			<td align=center style="padding-bottom:9">
				<font class=ver81>
					<c:if test="${ eventList.approvalStatus == '2' }"><img src="/resources/shop/admin/img/icn_1.gif"></c:if>
					<c:if test="${ eventList.approvalStatus != '2' }"><img src="/resources/shop/admin/img/icn_0.gif"></c:if>
				</font>
			</td>
			<td align=center style="padding-bottom:9">${eventList.approvalStatusNm}</td>
			<td align=center style="padding-bottom:4">
				<a href="${ctx }/shop/seller/event/eventRegister?mode=modify&schSno=${eventList.sno}"><img src="/resources/shop/admin/img/i_edit.gif" border=0></a>
			</td>
		</tr>
	</c:forEach>
</c:if>
<c:if test="${empty sellerEventFM.eventList}">
			<tr><td align="center" colspan="12" style="padding-bottom:9;padding-top:9"><font class="ver81">검색 결과가 없습니다.</font></td></tr>
</c:if>
		<tr><td colspan="12" class="rndline"></td></tr>
	</table>

	<!-- 페이징  -->
	<tags:paginator currentPageNo="${sellerEventFM.pageNo}" rowCount="${sellerEventFM.rowCount}" pageSize="${sellerEventFM.pageSize}"  pageGroupSize="${sellerEventFM.pageGroupSize}" />
	
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td width="20%" height=35 style="padding-left:13px">
				<input type="image" class="button_top" src="/resources/shop/admin/img/i_display.gif" alt="게시" border="0" align='absmiddle' style="cursor:hand"
				   onclick="javaScript:chk_status_modify('1', 'open_modify'); return false;">
				<input type="image" class="button_top" src="/resources/shop/admin/img/i_display_cancel.gif" alt="게시취소" border="0" align='absmiddle' style="cursor:hand"
				   onclick="javaScript:chk_status_modify('0', 'open_modify'); return false;">
			</td>
		</tr>
	</table>
	
	<div id=MSG01>
		<table cellpadding=2 cellspacing=0 border=0 class=small_ex>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>이벤트제목을 클릭하면 이벤트 내용을 수정</font>할 수 있습니다.</td></tr>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>미리보기를 클릭하면 새창과 함께 이벤트페이지로 이동</font>합니다.</td></tr>
		</table>
	</div>
	<script>cssRound('MSG01')</script>
	
</form>
