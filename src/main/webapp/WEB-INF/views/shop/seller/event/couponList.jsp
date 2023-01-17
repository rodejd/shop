<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script language="javascript">
/*
 * jQuery ready
 */
 $(function(){
	// 검색
	$('#searchBtn').on('click', function(){
		$("#couponSchForm").attr("action", ctx+"/shop/seller/event/couponList");
		$('#couponSchForm').submit();
	});
});

<%-- // 게시 여부/승인여부 변경 --%>
function chk_status_modify(statVal, mode){
	var i = 0;
	var couponChks = null;
	var couponSchFrm = null;
	
	couponChks = $("#couponList input:checkbox:checked");
	couponSchFrm = $("#couponSchForm");
	
	if(0 >= couponChks.length){
		alert(("open_modify" == mode) ? "게시/게시취소 상품을 선택하세요." : "승인/반려 상품을 선택하세요.");
		return false;
	}
	
	if(confirm(("open_modify" == mode) ? "게시/게시취소 하시겠습니까?" : "승인/반려 하시겠습니까?")){
		couponChks.each(function(){
			couponSchFrm.append("<input type='hidden' name='couponArr' value='"+$(this).val()+"'/>");
		});
		
		$("#statVal").val(statVal);
		$("#mode").val(mode);
		
		$("#couponSchForm").attr("action", "selIndbCoupon2");
		
		couponSchFrm.submit();
	}
}

function goPage(page){
	$("#couponSchForm").attr("action", ctx+"/shop/seller/event/couponList");
	$("#pageNo").val(Number(page));
	$('#couponSchForm').submit();
}

function sort(sort){
	$("#couponSchForm").attr("action", ctx+"/shop/seller/event/couponList");
	$('#schSort').val(sort);
	$('#couponSchForm').submit();
}

</script>

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">
<div class="title title_top">쿠폰리스트<span>고객에게 발급된 쿠폰을 관리하거나 쿠폰을 발급합니다. </span></div>
<table width=100% cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td>
			<form name="couponSchForm" id="couponSchForm" action="${ctx }/shop/seller/event/couponList" method="get">
				<input type="hidden" id="statVal" name="statVal" value="" /> 
				<input type="hidden" id="mode" name="mode" value="${sellerCouponFM.mode}">
				<input type="hidden" name="couponcd" value="0">
				<input type="hidden" name="category" id="category" value="">
				<input type=hidden id=pageNo name="pageNo" value="1"/>
				<input type="hidden" id="schSort" name="schSort" value=""/> 
				<table class=tb>
					<col class=cellC><col class=cellL style="width:250"><col class=cellC><col class=cellL>
					<tr>
						<td><font class=small1>쿠폰검색 (통합)</font></td>
						<td>
							<select name=skey>
								<option value="all"> = 통합검색 =
								<option value="couponcd" <c:if test="${ sellerCouponFM.skey == 'couponcd' }">selected</c:if> > 쿠폰번호
								<option value="coupon" <c:if test="${ sellerCouponFM.skey == 'coupon' }">selected</c:if> > 쿠폰명
							</select>
							<input type=text name=sword value="${sellerCouponFM.sword}" class=line>
						</td>
						<td><font class=small1>쿠폰기능</font></td>
						<td><font class=small1 color=555555>
							<input type=checkbox name='ability' value='0' ${stringUtil:checkedArry(sellerCouponFM.ability, '0')} >할인
							<input type=checkbox name='ability' value='1' ${stringUtil:checkedArry(sellerCouponFM.ability, '1')} >적립
							</font>
						</td>
					</tr>
					<tr>
						<td><font class=small1>쿠폰발급방식</font></td>
						<td colspan=3>
							<font class=small1 color=555555>
								<input class=null type=checkbox name='coupontype' value='0' ${stringUtil:checkedArry(sellerCouponFM.coupontype, '0')} >운영자발급
								<input class=null type=checkbox name='coupontype' value='1' ${stringUtil:checkedArry(sellerCouponFM.coupontype, '1')} >회원직접다운로드
								<input class=null type=checkbox name='coupontype' value='2' ${stringUtil:checkedArry(sellerCouponFM.coupontype, '2')} >회원가입자동발급	
								<input class=null type=checkbox name='coupontype' value='3' ${stringUtil:checkedArry(sellerCouponFM.coupontype, '3')} >구매후 자동발급
							</font>
						</td>
					</tr>
					<tr>
						<td><font class=small1>쿠폰발행일/기간</font></td>
						<td colspan=3>
							<span class="noline small1" style="color:5C5C5C; margin-right:20px;">
								<select name=dtkind>
									<option value="regdt" <c:if test="${ sellerCouponFM.dtkind == 'regdt' }">selected</c:if> >생성일
									<option value="sddate" <c:if test="${ sellerCouponFM.dtkind == 'sddate' }">selected</c:if> >적용기간
								</select>
							</span>
							<input type=text name=regdt value="${sellerCouponFM.regdt[0] }" onclick="calendar(event)" onkeydown="onlynumber()" size=12 class=line> -
							<input type=text name=regdt value="${sellerCouponFM.regdt[1] }" onclick="calendar(event)" onkeydown="onlynumber()" size=12 class=line>
							<a href="javascript:setDate('regdt', ${dateUtil:getDate('yyyyMMdd')}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align=absmiddle></a>
							<a href="javascript:setDate('regdt', ${dateUtil:getDateFrom('yyyyMMdd', -7)} , ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align=absmiddle></a>
							<a href="javascript:setDate('regdt', ${dateUtil:getDateFrom('yyyyMMdd', -12)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align=absmiddle></a>
							<a href="javascript:setDate('regdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'), -1)} , ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align=absmiddle></a>
							<a href="javascript:setDate('regdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'), -2)} , ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align=absmiddle></a>
							<a href="javascript:setDate('regdt')"><img src="/resources/shop/admin/img/sicon_all.gif" align=absmiddle></a>
						</td>
					</tr>
				</table>
				<div class="button_top" style="float:center"><input type=image src="/resources/shop/admin/img/btn_search2.gif" id="searchBtn"></div>
				<table width=100% cellpadding=0 cellspacing=0>
					<tr>
						<td class=pageInfo><font class=ver8>
							<c:set var="pages" value="${(sellerCouponFM.rowCount * 10) / (sellerCouponFM.pageSize * 10)} " />
							<c:set var="pageCnt" value="${pages + (1 - (pages % 1)) % 1}" />
							<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
							총 <b>${sellerCouponFM.rowCount}</b>개, <b>${sellerCouponFM.pageNo }</b> of <b>${var3 }</b> Pages
						</td>
						<td align=right>
							<table cellpadding=0 cellspacing=0 border=0>
								<tr>
									<td valign=bottom>
										쿠폰번호
										<a href="javascript:sort('couponcdDESC')"><img id="couponcdDESC" src="/resources/shop/admin/img/list_up_off.gif"></a>
										<a href="javascript:sort('couponcdASC')"><img id="couponcdASC" src="/resources/shop/admin/img/list_down_off.gif"></a>
										쿠폰명
										<a href="javascript:sort('couponDESC')"><img id="couponDESC" src="/resources/shop/admin/img/list_up_off.gif"></a>
										<a href="javascript:sort('couponASC')"><img id="couponASC" src="/resources/shop/admin/img/list_down_off.gif"></a>
										쿠폰생성일
										<a href="javascript:sort('regdtDESC') "><img id="regdtDESC" src="/resources/shop/admin/img/list_up_off.gif"></a>
										<a href="javascript:sort('regdtASC' )"><img id="regdtASC" src="/resources/shop/admin/img/list_down_off.gif"></a>
									</td>
									<td style="padding-left:20px">
										<img src="/resources/shop/admin/img/sname_output.gif" align=absmiddle>
										<select name=pageSize onchange="this.form.submit()">
											<option value="10" ${stringUtil:selected(sellerCouponFM.pageSize, "10")} >10개 출력</option>
											<option value="20" ${stringUtil:selected(sellerCouponFM.pageSize, "20")} >20개 출력</option>
											<option value="40" ${stringUtil:selected(sellerCouponFM.pageSize, "40")} >40개 출력</option>
											<option value="60" ${stringUtil:selected(sellerCouponFM.pageSize, "60")} >60개 출력</option>
											<option value="100" ${stringUtil:selected(sellerCouponFM.pageSize, "100")} >100개 출력</option>
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

<table id="couponList" width=100% cellpadding=0 cellspacing=0 border=0>
	<tr class=rndbg>
		<th><font class=small1>선택</font></th>
		<th><font class=small1>쿠폰번호</font></th>
		<th><font class=small1>쿠폰명</font></th>
		<th><font class=small1>쿠폰종류</font></th>
		<th><font class=small1>쿠폰생성일</font></th>
		<th><font class=small1>기능</font></th>
		<th><font class=small1>할인금액(율)</font></th>
		<th><font class=small1>적용기간</font></th>
		<th align=left style="padding-left:3"><font class=small1>발급/조회(발급수)</font></th>
		<th><font class=small1>게시여부</font></th>
		<th><font class=small1>승인여부</font></th>
		<th><font class=small1>승인상태</font></th>
		<th><font class=small1>수정</font></th>
	</tr>
	
	<tr><td colspan="14" class="rnd"></td></tr>
	<tr><td height="4" colspan="14"></td></tr>
	<c:choose>
		<c:when test="${!empty sellerCouponFM.couponList}">
			<c:forEach items="${sellerCouponFM.couponList}" var="list" varStatus="i">
				<tr height=35>
					<td class="noline" align="center">
						<input type="checkbox" name="chk[]" value="${list.couponcd}" onclick="">
					</td>
					<td align=center style="font-family: verdana;font-size:7pt;letter-spacing:-1">
						<font class=ver71 color=555555>${list.couponcd}</font>
					</td>
					<td align=center>
						<font class=small1 color=555555>
							<a href="javascript:popup('${ctx }/shop/seller/event/couponRegister?popView=Y&schCouponcd=${list.couponcd}&mode=modify',900,600);"><b>${list.coupon}</b></a>
						</font>
					</td>
					<td align=center>
						<font class=small1 color=0074BA><b>
							<c:choose>
								<c:when test="${'0' == list.coupontype}">운영자발급</c:when>
								<c:when test="${'1' == list.coupontype}">회원직접다운로드</c:when>
								<c:when test="${'2' == list.coupontype}">회원가입자동발급</c:when>
								<c:when test="${'3' == list.coupontype}">구매후 자동발급</c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose>
						</b></font>
					</td>
					<td align=center style="font-family: verdana;font-size:7pt;letter-spacing:-1">
						<font class=ver71 color=555555>${list.regdt}</font>
					</td>
					<td align=center>
						<font class=small1 color=0074BA><b>
							<c:choose>
								<c:when test="${'0' == list.ability}">할인</c:when>
								<c:when test="${'1' == list.ability}">적립</c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose>
						</b></font>
					</td>
					<td align=center>
						<font class=small color=EA0095><b>
							<c:if test="${!fn:endsWith(list.price, '%')}">${stringUtil:getMoneyFormatInteger( list.price )}원</c:if>
							<c:if test="${fn:endsWith(list.price, '%')}">${list.price }</c:if>
						</b></font>
					</td>
					<td align=center>
						<div><font class=small color=555555>
							<c:choose>
								<c:when test="${'1' == list.priodtype}">발급 후 ${list.sdate } 일</c:when>
								<c:otherwise>${list.sdate }<br/>~${list.edate }</c:otherwise>
							</c:choose>
						</font></div>
					</td>
					<td align=left style="padding-left:5">
						<font class=small1 color=555555>
							<a href="javascript:popup('${ctx }/shop/seller/event/selCouponApply?popView=Y&schCouponcd=${list.couponcd }',900,600)">
								<img src="/resources/shop/admin/img/btn_coupon_mem_view.gif" align="absmiddle"> <font color=0074BA><b>(${list.cnt })</b>					
							</a>
						</font>
					</td>
					<td align=center style="font-family: verdana;font-size:7pt;letter-spacing:-1">
						<font class=ver71 color=555555><img src="/resources/shop/admin/img/icn_${list.open}.gif"></font>
					</td>
					<td align=center style="font-family: verdana;font-size:7pt;letter-spacing:-1">
						<font class=ver71 color=555555>
							<c:if test="${ list.approvalStatus == '2' }"><img src="/resources/shop/admin/img/icn_1.gif"></c:if>
							<c:if test="${ list.approvalStatus != '2' }"><img src="/resources/shop/admin/img/icn_0.gif"></c:if>
						</font>
					</td>
					<td align=center style="padding-bottom:9">${list.approvalStatusNm}</td>
					<td align=center>
						<font class=small1 color=555555>
							<a href="${ctx }/shop/seller/event/couponRegister?schCouponcd=${list.couponcd}&mode=modify"><img src="/resources/shop/admin/img/i_edit.gif"></a> 
						</font>
					</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr><td align="center" colspan="14" style="padding-bottom:9;padding-top:9"><font class="ver81">검색 결과가 없습니다.</font></td></tr>
		</c:otherwise>
	</c:choose>
	
	<tr><td height="4" colspan="14"></td></tr>
	<tr><td colspan="14" class="rndline"></td></tr>
</table>

<%-- 페이징 시작 --%>
<tags:paginator currentPageNo="${sellerCouponFM.pageNo}" rowCount="${sellerCouponFM.rowCount}" pageSize="${sellerCouponFM.pageSize}"  pageGroupSize="${sellerCouponFM.pageGroupSize}" />
<%-- 페이징 끝 --%>

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
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>즉시발급쿠폰의 경우는 '발급하기'를 클릭하여 직접 회원에게 발급해야 합니다.</td></tr>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>자동으로 발급되는 쿠폰들은 '조회하기'를 누르면 쿠폰발급내용과 발급받은 회원을 조회할 수 있습니다.</td></tr>
	</table>
</div>
<script>cssRound('MSG01');</script>

<script>
linecss();
table_design_load();
</script>

	</td>
</tr>
</table>

