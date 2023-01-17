<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%-- ================================================================================
* 공통 상단 include
* 공통 java class 지정 및 변수 설정
================================================================================ --%>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<script language="javascript">
/*
 * jQuery ready
 */
$(function(){
	// 검색
	$('#searchBtn').on('click', function(){
		$('#eventSchForm').submit();
	});
	
	// 판매사명 클릭시 clear
	$('#schSellerNm').on('click', function() {
		$('#schSellerCd').val('');
		$('#schSellerNm').val('');
		$('#schSellerNm').focus();
	});
});

<%-- // 게시 여부/승인여부 변경 --%>
function chk_status_modify(statVal, mode){
	var i = 0;
	var eventChks = null;
	var statusModifyFrm = null;
	
	eventChks = $("#eventList input:checkbox:checked");
	statusModifyFrm = $("#statusModifyFrm");
	
	if(0 >= eventChks.length){
		alert(("open_modify" == mode) ? "게시/게시취소 상품을 선택하세요." : "승인/반려 상품을 선택하세요.");
		return false;
	}
	
	if(confirm(("open_modify" == mode) ? "게시/게시취소 하시겠습니까?" : "승인/반려 하시겠습니까?")){
		eventChks.each(function(){
			statusModifyFrm.append("<input type='hidden' name='eventArr' value='"+$(this).val()+"'/>");
			statusModifyFrm.append("<input type='hidden' name='sellercodeArr' value='"+$(this).data("sellercode")+"'/>");
		});
		
		$("#statVal").val(statVal);
		$("#mode").val(mode);
		
		statusModifyFrm.submit();
	}
}

function goPage(page){
	$("#pageNo").val(Number(page));
	$('#eventSchForm').submit();
}

function sort(sort){
	$('#schSort').val(sort);
	$('#eventSchForm').submit();
}

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

		<div class="title title_top">이벤트리스트<span>이벤트페이지를 직접 디자인하고 이벤트상품들을 선정하실 수 있습니다 </span><!-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=event&no=2',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle hspace=2></a> --></div>
		<table width=100% cellpadding=0 cellspacing=0 border=0>
			<tr>
				<td>
					<form name="eventSchForm" id="eventSchForm" action="${ctx}/shop/admin/seller/sellerEventList" method="get">
						<input type=hidden id=pageNo name="pageNo" value="1"/>
						<input type="hidden" id="schSort" name="schSort" value=""/>
						<table class=tb>
							<col class=cellC><col class=cellL style="width:250"><col class=cellC><col class=cellL>
							<tr>
								<td><font class=small1>판매사명</font></td>
								<td colspan=3>
									<input type="text" name="schSellerNm" id="schSellerNm" value="${eventVO.schSellerNm}" class="line" style="height:22px" />
									<input type="button" value="판매사 조회" onclick="popupLayer('${ctx}/shop/admin/common/sellerSearchPopup', 600, 500);" />
									<input type="hidden" name="schSellerCd" id="schSellerCd" value="${eventVO.schSellerCd}" />
								</td>
							</tr>
						</table>
						<div class="button_top" style="float:center"><input type=image src="/resources/shop/admin/img/btn_search2.gif" id="searchBtn"></div>
						<table width=100% cellpadding=0 cellspacing=0>
							<tr>
								<td class=pageInfo><font class=ver8>
									<c:set var="pages" value="${(eventVO.rowCount * 10) / (eventVO.pageSize * 10)} " />
									<c:set var="pageCnt" value="${pages + (1 - (pages % 1)) % 1}" />
									<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
									총 <b>${eventVO.rowCount}</b>개, <b>${eventVO.pageNo }</b> of <b>${var3 }</b> Pages
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
														<option value="10" ${stringUtil:selected(eventVO.pageSize, "10")} >10개 출력</option>
														<option value="20" ${stringUtil:selected(eventVO.pageSize, "20")} >20개 출력</option>
														<option value="40" ${stringUtil:selected(eventVO.pageSize, "40")} >40개 출력</option>
														<option value="60" ${stringUtil:selected(eventVO.pageSize, "60")} >60개 출력</option>
														<option value="100" ${stringUtil:selected(eventVO.pageSize, "100")} >100개 출력</option>
													</select>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
		
		<form id="statusModifyFrm" method="post" action="sellerEventIndb2">
		<input type="hidden" id="statVal" 	name="statVal"		value="" /> 
		<input type="hidden" id="mode" 		name="mode"			value="" /> 
			
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
				<th width=100>수정/삭제</th>
			</tr>
			<tr><td class=rnd colspan=10></td></tr>
<c:if test="${!empty eventVO.eventList}">		
	<c:forEach items="${eventVO.eventList}" var="eventList" varStatus="st">		
			<tr><td height=10 colspan=15></td></tr>
			<tr height=25>
				<td align=center style="padding-bottom:9">
					<font class=ver81>
						<input type="checkbox" name="chk[]" value="${eventList.sno}" data-sellercode="${eventList.sellercode}" onclick="">
					</font>
				</td>
				<td align=center style="padding-bottom:9"><font class=ver81>${eventList.sno}</font></td>
				<td class=cell><font class=small1 color=555555><b>${eventList.subject}</b></font></td>
				<td align=center style="padding-bottom:5"><font class=ver8 color=EB4200>
					<c:if test="${ eventList.sdate != '' }">${dateUtil:getDateFormat("yyyy-MM-dd", eventList.sdate)}</c:if>
				</font></td>
				<td align=center style="padding-bottom:5"><font class=ver8 color=EB4200>
					<c:if test="${ eventList.edate != '' }">${dateUtil:getDateFormat("yyyy-MM-dd", eventList.edate)}</c:if>
				</font></td>
				<td align=center style="padding-bottom:9"><font class=ver81>${eventList.sellername}</td>
				<td align=center style="padding-bottom:9"><font class=ver81><img src="/resources/shop/admin/img/icn_${eventList.open}.gif"></td>
				<td align=center style="padding-bottom:9">
					<font class=ver81>
						<c:if test="${ eventList.approvalstatus == '2' }"><img src="/resources/shop/admin/img/icn_1.gif"></c:if>
						<c:if test="${ eventList.approvalstatus != '2' }"><img src="/resources/shop/admin/img/icn_0.gif"></c:if>
					</font>
				</td>
				<td align=center style="padding-bottom:9">${eventList.approvalstatusnm}</td>
				<td align=center style="padding-bottom:4">
					<a href="sellerEventRegister?mode=modify&sno=${eventList.sno}"><img src="/resources/shop/admin/img/i_edit.gif" border=0></a>
					<a href="sellerEventIndb?mode=delete&sno=${eventList.sno}&old_attach=${eventList.attach}" onclick="return confirm('정말로 삭제하시겠습니까?\n\n이벤트내용에 쓰인 이미지는 다른 곳에서도 사용하고 있을 수 있으므로 자동 삭제되지 않습니다. \n\'디자인관리 > webFTP이미지관리 > data > editor\'에서 이미지체크 후 삭제관리하세요.')"><img src="/resources/shop/admin/img/i_del.gif"></a>
				</td>
			</tr>
	</c:forEach>	
</c:if>	
<c:if test="${empty eventVO.eventList}">
			<tr><td align="center" colspan="12" style="padding-bottom:9;padding-top:9"><font class="ver81">검색 결과가 없습니다.</font></td></tr>
</c:if>
			<tr><td colspan=12 class=rndline></td></tr>
		</table>
		<!-- 페이징  -->
		<div align=center class=pageNavi>
			<tags:paginator currentPageNo="${eventVO.pageNo}" rowCount="${eventVO.rowCount}" pageSize="${eventVO.pageSize}"  pageGroupSize="${eventVO.pageGroupSize}" />
		</div>
		
		</form>
		
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td width="20%" height=35 style="padding-left:13px">
					<input type="image" class="button_top" src="/resources/shop/admin/img/i_display.gif" alt="게시" border="0" align='absmiddle' style="cursor:hand"
					   onclick="javaScript:chk_status_modify('1', 'open_modify'); return false;">
					<input type="image" class="button_top" src="/resources/shop/admin/img/i_display_cancel.gif" alt="게시취소" border="0" align='absmiddle' style="cursor:hand"
					   onclick="javaScript:chk_status_modify('0', 'open_modify'); return false;">
					<input type="image" class="button_top" src="/resources/shop/admin/img/i_approval.jpg" alt="승인" border="0" align='absmiddle' style="cursor:hand"
					   onclick="javaScript:chk_status_modify('2', 'approvalstatus_modify'); return false;">
					<input type="image" class="button_top" src="/resources/shop/admin/img/i_reject.gif" alt="반려" border="0" align='absmiddle' style="cursor:hand"
					   onclick="javaScript:chk_status_modify('3', 'approvalstatus_modify'); return false;">
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

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>