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
	// 판매사명 클릭시 clear
	$('#schMyritzNm').on('click', function() {
		$('#schMyritzCd').val('');
		$('#schMyritzNm').val('');
		$('#schMyritzNm').focus();
	});
});

function delCoupon(couponcd){
	var f = document.forms[0];
	if(confirm('쿠폰을 삭제하시면 발급정보들도 같이 사라집니다.\n삭제 후 원래 쿠폰정보는 복구하실 수 없습니다.\n정말 삭제하시겠습니까?')){
		f.mode.value = 'delete';
		f.couponcd.value = couponcd;
		ajaxCallJsonPost("/shop/admin/myritz/myritzIndbCoupon", "couponSchForm", function(data){
			if (data.RESULT) {
				alert('삭제하였습니다.');
				window.location.href = ctx+'/shop/admin/myritz/myritzCouponList';
			} else {
				alert(data.RESULT_MSG);
				return false;
			}
			
		});
	}
}

function chkGoodsType(){
	if(document.all.goodstype[2].checked) document.getElementById('category_id').style.display='block';
	else document.getElementById('category_id').style.display='none';
}

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
		
		$("#couponSchForm").attr("action", "myritzIndbCoupon2");
		
		couponSchFrm.submit();
	}
}

</script>

<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
/*
 * jQuery ready
 */
$(function(){
	// 검색
	$('#searchBtn').on('click', function(){
		$("#couponSchForm").attr("action", ctx+"/shop/admin/myritz/myritzCouponList");
		// 적용상품범위 -> 특정상품 및 특정카테고리의 경우 선택된 카테고리값 취득 (마지막뎁스의 카티고리값)
		$('#category').val("");	// 초기화
		if ("1" == $('input:radio[name="goodstype"]:checked').val()) {
			$('select[name="cate"] option:selected').each(function(){
				if ($(this).val() != "") {
					$('#category').val($(this).val());
				} 
			});
		}
		$('#couponSchForm').submit();
		
	});
});

function goPage(page){
	$("#couponSchForm").attr("action", ctx+"/shop/admin/myritz/myritzCouponList");
	$("#pageNo").val(Number(page));
	$('#couponSchForm').submit();
}

function sort(sort){
	$("#couponSchForm").attr("action", ctx+"/shop/admin/myritz/myritzCouponList");
	$('#schSort').val(sort);
	$('#couponSchForm').submit();
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

<div class="title title_top">쿠폰리스트<span>고객에게 발급된 쿠폰을 관리하거나 쿠폰을 발급합니다. </span></div>
<table width=100% cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td>
			<form name="couponSchForm" id="couponSchForm" action="${ctx}/shop/admin/myritz/myritzCouponList" method="get">
				<input type="hidden" id="statVal" name="statVal" value="" /> 
				<input type="hidden" id="mode" name="mode" value="${couponVO.mode}">
				<input type="hidden" name="couponcd" value="">
				<input type="hidden" name="category" id="category" value="">
				<input type=hidden id=pageNo name="pageNo" value="1"/>
				<input type="hidden" id="schSort" name="schSort" value=""/> 
				<table class=tb>
					<col class="cellC" />
					<col class="cellL" style="width:716" />
					<col class="cellC" />
					<col class="cellL" style="width:716" />
					<tr>
						<td><font class=small1>쿠폰검색 (통합)</font></td>
						<td>
							<select name=skey>
								<option value="all"> = 통합검색 =
								<option value="couponcd" <c:if test="${ couponVO.skey == 'couponcd' }">selected</c:if> > 쿠폰번호
								<option value="coupon" <c:if test="${ couponVO.skey == 'coupon' }">selected</c:if> > 쿠폰명
							</select>
							<input type=text name=sword value="${couponVO.sword}" class=line>
						</td>
						<td><font class=small1>노출스킨</font></td>
						<td>
							<select name="schSkin">
								<option value="">전체</option>
								<option value="al" ${stringUtil:selected(couponVO.schSkin, "al")}>ALL</option>
								${webUtil:makeSelectCodeItem((codeUtil:codeitem('skin')), couponVO.schSkin) }
							</select>
						</td>
					</tr>
					<tr>
						<td><font class=small1>판매사명</font></td>
						<td colspan=3>
							<input type="text" name="schMyritzNm" id="schMyritzNm" value="${couponVO.schMyritzNm}" class="line" style="height:22px" />
							<input type="button" value="판매사 조회" onclick="popupLayer('${ctx}/shop/admin/common/myritzSearchPopup', 600, 500);" />
							<input type="hidden" name="schMyritzCd" id="schMyritzCd" value="${couponVO.schMyritzCd}" />
						</td>
					</tr>
					<tr>
						<td><font class=small1>적용상품범위</font></td>
						<td colspan=3>
							<select name="goodstype" >
								<option value="" >선택 
								<option value="0"  <c:if test="${ couponVO.goodstype == '0' }">selected</c:if> >전체상품 
								<option value="1"  <c:if test="${ couponVO.goodstype == '1' }">selected</c:if> >특정 상품 및 특정 카테고리
							</select>
							<%-- <div style="padding:5,0,0,0"><font class=small1 color=555555>
								<input type=radio name=goodstype value='' onclick="chkGoodsType();" class=null  <c:if test="${ couponVO.goodstype == '' }">checked</c:if> > 전체
								<input type=radio name=goodstype value='0' onclick="chkGoodsType();" class=null <c:if test="${ couponVO.goodstype == '0' }">checked</c:if> > 전체상품
								<input type=radio name=goodstype value='1' onclick="chkGoodsType();" class=null <c:if test="${ couponVO.goodstype == '1' }">checked</c:if> > 특정 상품 및 특정 카테고리</font>
							</div> --%>
							<div style='display:none;' id='category_id'>
								<div style="padding:5,0,5,0">분류선택 : <script>new categoryBox('cate',4,'${couponVO.category}','');</script></div>
								<div style="padding:0,0,5,0">
									<select name="gkey">
										<option value="goodsnm" <c:if test="${ couponVO.gkey == 'goodsnm' }">selected</c:if> >상품명
										<option value="goodsno" <c:if test="${ couponVO.gkey == 'goodsno' }">selected</c:if> >고유번호
										<option value="goodscd" <c:if test="${ couponVO.gkey == 'goodscd' }">selected</c:if> >상품코드
										<option value="keyword" <c:if test="${ couponVO.gkey == 'keyword' }">selected</c:if> >유사검색어
									</select>
									<input type=text name="gword" class=lline style="width:200" value="${couponVO.gword}">
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td><font class=small1>쿠폰발급방식</font></td>
						<td colspan=3>
							<font class=small1 color=555555>
								<input class=null type=checkbox name='coupontype' value='0' ${stringUtil:checkedArry(couponVO.coupontype, '0')} >운영자발급
								<input class=null type=checkbox name='coupontype' value='1' ${stringUtil:checkedArry(couponVO.coupontype, '1')} >회원직접다운로드
								<input class=null type=checkbox name='coupontype' value='2' ${stringUtil:checkedArry(couponVO.coupontype, '2')} >회원가입자동발급	
								<input class=null type=checkbox name='coupontype' value='3' ${stringUtil:checkedArry(couponVO.coupontype, '3')} >구매후 자동발급
							</font>
						</td>
					</tr>
					<tr>
						<td><font class=small1>쿠폰발행일/기간</font></td>
						<td colspan=3>
							<span class="noline small1" style="color:5C5C5C; margin-right:20px;">
								<select name=dtkind>
									<option value="regdt" <c:if test="${ couponVO.dtkind == 'regdt' }">selected</c:if> >생성일
									<option value="sddate" <c:if test="${ couponVO.dtkind == 'sddate' }">selected</c:if> >적용기간
								</select>
							</span>
							<input type=text name=regdt value="${couponVO.regdt[0] }" onclick="calendar(event)" onkeydown="onlynumber()" size=12 class=line> -
							<input type=text name=regdt value="${couponVO.regdt[1] }" onclick="calendar(event)" onkeydown="onlynumber()" size=12 class=line>
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
							<c:set var="pages" value="${(couponVO.rowCount * 10) / (couponVO.pageSize * 10)} " />
							<c:set var="pageCnt" value="${pages + (1 - (pages % 1)) % 1}" />
							<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
							총 <b>${couponVO.rowCount}</b>개, <b>${couponVO.pageNo }</b> of <b>${var3 }</b> Pages
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
											<option value="10" ${stringUtil:selected(couponVO.pageSize, "10")} >10개 출력</option>
											<option value="20" ${stringUtil:selected(couponVO.pageSize, "20")} >20개 출력</option>
											<option value="40" ${stringUtil:selected(couponVO.pageSize, "40")} >40개 출력</option>
											<option value="60" ${stringUtil:selected(couponVO.pageSize, "60")} >60개 출력</option>
											<option value="100" ${stringUtil:selected(couponVO.pageSize, "100")} >100개 출력</option>
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

<div align=right style="padding:10 5 3 0">※ <font class=small1 color=333333>운영자가 회원에게 발급하는 쿠폰은 아래 <font color=EA0095><b>회원발급하기</b></font> 버튼을 눌러 회원에게 쿠폰을 발급하세요.</font></div>

<table id="couponList" width=100% cellpadding=0 cellspacing=0 border=0>
	<tr class=rndbg>
		<th><font class=small1>선택</font></th>
		<th><font class=small1>쿠폰번호</font></th>
		<th><font class=small1>노출스킨</font></th>
		<th><font class=small1>쿠폰명</font></th>
		<th><font class=small1>쿠폰종류</font></th>
		<th><font class=small1>쿠폰생성일</font></th>
		<th><font class=small1>기능</font></th>
		<th><font class=small1>할인금액(율)</font></th>
		<th><font class=small1>적용기간</font></th>
		<th align=left style="padding-left:3"><font class=small1>발급/조회(발급수)</font></th>
		<th><font class=small1>판매사명</font></th>
		<th><font class=small1>게시여부</font></th>
		<th><font class=small1>승인여부</font></th>
		<th><font class=small1>승인상태</font></th>
		<th><font class=small1>수정/삭제</font></th>
	</tr>
<c:if test="${!empty couponVO.couponList}">	
	<c:forEach items="${couponVO.couponList}" var="list" varStatus="i">
	<tr height=35>
		<td class="noline" align="center">
			<input type="checkbox" name="chk[]" value="${list.couponcd}" onclick="">
		</td>
		<td align=center><font class=small1 color=555555><b>${list.couponcd}</b></font></td>
		<td align=center><font class=small1 color=555555>${list.skin eq 'al' ? 'ALL' : codeUtil:getCodeName("skin", list.skin)}</font></td>
		<td align=center><font class=small1 color=555555><b>${list.coupon}</b></font></td>
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
			<font class=ver71 color=555555>${dateUtil:formatDate(list.regdt, "yyyy-MM-dd HH:mm:ss")}</font>
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
				<c:if test="${!fn:endsWith(list.price, '%')}">₩${stringUtil:getMoneyFormatInteger( list.price )}</c:if>
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
				<a href="${ctx}/shop/admin/myritz/myritzCouponApply?couponcd=${list.couponcd }">
				<c:choose>
					<c:when test="${'0' == list.coupontype}">
						<img src="/resources/shop/admin/img/btn_coupon_mem_issue.gif" align="absmiddle"> <font color=EA0095><b>(${list.cnt })</b></font>
					</c:when>
					<c:otherwise>
						<img src="/resources/shop/admin/img/btn_coupon_mem_view.gif" align="absmiddle"> <font color=0074BA><b>(${list.cnt })</b></font>					
					</c:otherwise>
				</c:choose>
				</a>
			</font>
		</td>
		<td align=center style="font-family: verdana;font-size:7pt;letter-spacing:-1">
			<font class=ver71 color=555555>${list.myritzNameList}</font>
		</td>
		<td align=center style="font-family: verdana;font-size:7pt;letter-spacing:-1">
			<font class=ver71 color=555555><img src="/resources/shop/admin/img/icn_${list.open}.gif"></font>
		</td>
		<td align=center style="font-family: verdana;font-size:7pt;letter-spacing:-1">
			<font class=ver71 color=555555>
				<c:if test="${ list.approvalstatus == '2' }"><img src="/resources/shop/admin/img/icn_1.gif"></c:if>
				<c:if test="${ list.approvalstatus != '2' }"><img src="/resources/shop/admin/img/icn_0.gif"></c:if>
			</font>
		</td>
		<td align=center style="padding-bottom:9">${list.approvalstatusnm}</td>
		<td align=center>
			<font class=small1 color=555555>
				<a href="${ctx}/shop/admin/myritz/myritzCouponRegister?couponcd=${list.couponcd}&mode=modify"><img src="/resources/shop/admin/img/i_edit.gif"></a> 
				<a href="javascript:delCoupon(${list.couponcd})"><img src="/resources/shop/admin/img/i_del.gif"></a>
			</font>
		</td>
	</tr>
	</c:forEach>
</c:if>	
<c:if test="${empty couponVO.couponList}">
	<tr><td align="center" colspan="14" style="padding-bottom:9;padding-top:9"><font class="ver81">검색 결과가 없습니다.</font></td></tr>
</c:if>
	<tr><td colspan=14 class=rndline></td></tr>
</table>

<%-- 페이징 시작 --%>
<tags:paginator currentPageNo="${couponVO.pageNo}" rowCount="${couponVO.rowCount}" pageSize="${couponVO.pageSize}"  pageGroupSize="${couponVO.pageGroupSize}" />
<%-- 페이징 끝 --%>

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
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>즉시발급쿠폰의 경우는 '발급하기'를 클릭하여 직접 회원에게 발급해야 합니다.</td></tr>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>자동으로 발급되는 쿠폰들은 '조회하기'를 누르면 쿠폰발급내용과 발급받은 회원을 조회할 수 있습니다.</td></tr>
	</table>
</div>
<script>cssRound('MSG01');chkGoodsType();</script>

<script>
linecss();
table_design_load();
</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<%@ include file="../common/bottom.jsp" %>
	</td>
</tr>
</table>
<%-- ================================================================================
* 공통 HTML CONTENT 종료
================================================================================ --%>

<%-- ================================================================================
=====================================================================================
* 화면 종료
=====================================================================================
================================================================================ --%>
