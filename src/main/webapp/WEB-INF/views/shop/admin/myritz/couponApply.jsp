<%--
/************************************************************************************
* 프로그램명 			: coupon_apply.jsp 
* 관련 SERVICE명 		: 
* 프로그램 내용 		: XMall > 관리자 > 이벤트/쿠폰관리 ? 쿠폰발급/조회 
* 작성자	   		 	: PMG
* 작성일자 				: 2017-01-24
*************************************************************************************
* 수정자  	수정일자	수정내용
*************************************************************************************
* CAR		2017-01-24	신규등록
************************************************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%-- ================================================================================
* 공통 상단 include
* 공통 java class 지정 및 변수 설정
================================================================================ --%>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>
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
	
	// 등록버튼 클릭시
	$('#couponSubmitBtn').on('click', function(){
		if (checkform1($('#couponAddForm')[0])) {
			
			ajaxCallJsonPost("/shop/admin/myritz/myritzIndbCoupon", "couponAddForm", function(data){
				if (data.RESULT) {
					alert('등록하였습니다.');
					location.reload(true);
				} else {
					alert(data.RESULT_MSG);
					return false;
				}
				
			});
			event.stopPropagation();
			event.preventDefault();
		}
	});
	
	/* 2017-09-06 추가 - 발급 쿠폰 사용가능 여부 수정 시 */
	$('#modifyApplyBtn').on('click', function(){
		
		var snoGroup = new Array();		//발급 쿠폰 사용가능 여부 배열
		var statusGroup = new Array();		//발급 쿠폰 일련번호 배열
		
		for(var i=0; i<$('[name="snoGroup"]').length; i++){
			snoGroup[i] = $('[name="snoGroup"]')[i].value;
		}
		for(var i=0; i<$('.statusGroup:checked').length; i++){
			statusGroup[i] = $('.statusGroup:checked')[i].value;
		}
		
		jQuery.ajaxSettings.traditional = true;  
		
		ajaxCallJson("/shop/admin/myritz/myritzIndbCoupon", 
				{mode:"modifyApply",
				snoGroup:snoGroup,
				statusGroup:statusGroup}, 
				function(data){
			if(data.result) {
				alert('요청하신 내용이 처리 완료되었습니다.');
				location.reload(true);
			}
		})
	});
	
});



function del_options(el)
{
	idx = el.rowIndex;
	var obj = document.getElementById('m_ids');
	obj.deleteRow(idx);
}

function checkform1(f){

	if(f.membertype[1].checked == true && f.membergrpsno.selectedIndex == 0){
		alert('회원그룹을 선택하세요!!');
		f.membergrpsno.focus();
		return false;
	}
	if(f.membertype[2].checked == true){
		if(!document.getElementsByName('mids').length){
			alert('회원을 선택해주세요!!');
			return false;
		}
	}
	return true;
}

function delApply(sno){
	var f = document.hiddenform;
	f.mode.value = "delApply";
	f.couponcd.value = '${couponInfo.couponcd}';
	f.sno.value = sno;
	ajaxCallJsonPost("/shop/admin/myritz/myritzIndbCoupon", "hiddenform", function(data){
		if (data.RESULT) {
			alert('삭제하였습니다.');
			location.reload(true);
		} else {
			alert('일시적인 오류가 발생했습니다.잠시후 다시 시도해 주세요.');
			return false;
		}
		
	});
/* 	event.stopPropagation();
	event.preventDefault(); */
}

function goPage(page){
	$("#pageNo").val(Number(page));
	$('#pageform').submit();
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

<form name="hiddenform" id="hiddenform" method="post">
	<input type="hidden" name="mode">
	<input type="hidden" name="couponcd">
	<input type="hidden" name="sno">
</form>

<form name="pageform" id="pageform" method="post">
	<input type="hidden" name="couponcd" value="${couponInfo.couponcd}">
	<input type=hidden id=pageNo name="pageNo" value="1"/>
</form>
<div class="title title_top">쿠폰발급/조회<span>쿠폰을 직접 발급하고 관리할 수 있습니다.</span> </div>

<div style="padding:3 0 5 8"><img src="/resources/shop/admin/img/ico_arrow_down.gif" align=absmiddle> <font color=0074BA><b>쿠폰발급내용</b></font></div>

<table class="tb" border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
	<tr class="cellC" align="center" height="25">
		<th align="center" width="100">번호</th>
		<th align="center">쿠폰명</th>
		<th align="center">쿠폰발급방식</th>
		<th align="center" width="120">생성일</th>
		<th align="center" width="100">할인금액(율)</th>
		<th align="center" width="150">사용기간</th>
		<th align="center" width="70">기능</th>
	</tr>
	<tr height=25>
		<td align=center><font class=ver71 color=444444>${couponInfo.couponcd}</font></td>
		<td class=cellL align=center>
			<a href="${ctx}/shop/admin/myritz/myritzCouponRegister?couponcd=${couponInfo.couponcd}&mode=modify" target='_blank'><font color=0074BA>${couponInfo.coupon}</font></a>
		</td>
		<td align=center>
			<c:choose>
				<c:when test="${'0' == couponInfo.coupontype}">운영자발급</c:when>
				<c:when test="${'1' == couponInfo.coupontype}">회원직접다운로드</c:when>
				<c:when test="${'2' == couponInfo.coupontype}">회원가입자동발급</c:when>
				<c:when test="${'3' == couponInfo.coupontype}">구매후 자동발급</c:when>
				<c:otherwise>&nbsp;</c:otherwise>
			</c:choose>
		</td>
		<td align=center><font class=ver71 color=444444>${dateUtil:formatDate(couponInfo.regdt, "yyyy-MM-dd HH:mm:ss")}</font></td>
		<td align=center><font class=ver71 color=444444><c:if test="${!fn:endsWith(couponInfo.price, '%')}">₩</c:if>${couponInfo.price }</font></td>
		<td align=center>
			<font class=ver71 color=444444>
				<c:choose>
					<c:when test="${'1' == couponInfo.priodtype}">발급 후 ${couponInfo.sdate } 일</c:when>
					<c:otherwise>${couponInfo.sdate }<br/>~${couponInfo.edate }</c:otherwise>
				</c:choose>
			</font>
		</td>
		<td align=center>
			<font class=small1 color=444444>
				<c:choose>
					<c:when test="${'0' == couponInfo.ability}">할인</c:when>
					<c:when test="${'1' == couponInfo.ability}">적립</c:when>
					<c:otherwise>&nbsp;</c:otherwise>
				</c:choose>
			</font>
		</td>
	</tr>
</table>
<p>

<div style="padding:3 0 5 8"><img src="/resources/shop/admin/img/ico_arrow_down.gif" align=absmiddle> <font color=0074BA><b>이 쿠폰을 발급받은 회원리스트</b></font> <font class=extext>(삭제버튼을 클릭하면 해당 회원에게 발급된 쿠폰이 취소됩니다)</font></div>
<table class=tb border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
	<tr class=cellC align=center height=25>
		<th width=50 align=center>순번</th>
		<c:if test="${'1' == couponInfo.coupontype}">
			<th align=center>발급 상품</th>
		</c:if>
		<th align=center>발급받은 회원</th>
		<th align=center>발급일/사용일</th>
		<th width="150" align="center">사용가능여부</th>
		<th width="50" align="center">사용 수</th>
		<th align=center>삭제</th>
	</tr>
	
	<c:choose>
		<c:when test="${couponVO.couponApplyGrpList != null &&  fn:length(couponVO.couponApplyGrpList) >0 }">	
			<c:forEach items="${couponVO.couponApplyGrpList}" var="grplist" varStatus="i">
			<tr heigth="25">
				<td align=center><font class=ver71 color=777777>${i.index+1}</font></td>
				<c:if test="${'1' == couponInfo.coupontype}"><%-- 1으로 발급 불가 (등록에서 막혀 있음) --%>
				<td align="left" style="padding-left:5">
					<div style="text-overflow:ellipsis;overflow:hidden;width:300px" nowrap>
						<div style="float:left">
							<a href="../../goods/goods_view?goodsno=${grplist.goodsno}&category=${grplist.category}" target=_blank>
								<img src='/resources/shop/data/upload/goods/${fn:substringAfter(grplist.imgs, "|")}' width="40px" height="40px" style="border: 1px solid #efefef;" onerror="onErrorImg(this, 'noimg_100.gif', '${wskin }');" />
								<%-- ${grplist.img_s}${shopLibFunction:goodsimg(grplist.img_s,"40","style='border:1 solid #efefef'",1)} --%>
							</a>
						</div>
						<div style="float:left;padding:15,0,0,10">
							<a href="javascript:popup('../goods/register?viewFlg=view&mode=modify&goodsno=${grplist.goodsno}',825,600)">
							<%-- <a href="javascript:popup('../goods/popup.register?mode=modify&goodsno=${grplist.goodsno}',825,600)"> --%>
								<font  color=0074BA>${grplist.goodsnm}</font>
							</a>
						</div>
					</div>
				</td>
				</c:if>
				<td align=left style="padding-left:9">
				<a href="javascript:popupLayer('${ctx}/shop/admin/myritz/myritzPopupApply?sno=${grplist.sno }', 500, 500)" >
				<c:choose>
					<c:when test="${'0' == grplist.membertype}">전체회원 </c:when>
					<c:when test="${'1' == grplist.membertype}">${grplist.grpnm } </c:when>
					<c:when test="${'2' == grplist.membertype}">
						개별회원
						<%-- <span id="navig" name="navig" m_id="${grplist.mid }" m_no="${grplist.mno }" popup="">
							<font class="ver81" color="0074BA"><b>${grplist.mid }</b></font>
						</span> --%>
					</c:when>
					<c:otherwise>&nbsp;</c:otherwise>
				</c:choose>
				</a>
				</td>
				<td align=center>
					<font class=ver71 color=666666><fmt:formatDate value="${grplist.regdt}"  type="both" dateStyle="short" timeStyle="short" pattern="yy/MM/dd hh:mm aa"/></font>
					<div style="padding-top:2"><font color=00899d><fmt:formatDate value="${grplist.mregdt}"  type="both" dateStyle="short" timeStyle="short" pattern="yy/MM/dd hh:mm aa"/></font></div>
				</td>
				<!-- 2017-09-05 추가 - 사용가능 여부 수정기능 추가 -->
				<td align="center">
					<input type="hidden" name="snoGroup" value="${grplist.sno }" />
					<input type="radio"  class="statusGroup" name="statusGroup[${i.index }]" value="1" ${grplist.status == 1 ? 'checked' : '' } /> 사용가능
					<input type="radio"  class="statusGroup" name="statusGroup[${i.index }]" value="0" ${grplist.status != 1 ? 'checked' : '' } /> 사용불가능
				</td>
				<!-- 사용 수 추가 -->
				<td align="center">
				<font color="EA0095"><b>${grplist.usedCount }</b></font><font color="888888"> / ${grplist.totalCount }</font>
				</td>
				<td align=center style="padding-top:4">
				<c:choose>
					<%-- <c:when test="${'0' == grplist.couCnt}"> --%>
					<c:when test="${'0' == grplist.usedCount }">
						<a href="javascript:delApply('${grplist.sno}');"><img src="/resources/shop/admin/img/btn_coupon_cancel.gif"></a>
					</c:when>
					<c:otherwise><font class=small1 color=888888><b>쿠폰사용완료</b></font></c:otherwise>
				</c:choose>
				</td>
			</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr height=25>
			   <td align=center colspan=<c:choose><c:when test="${'1' == couponInfo.coupontype}">7</c:when><c:otherwise>6</c:otherwise></c:choose>><font class=small1 color=6d6d6d>현재 이 쿠폰을 발급받은 회원이 없습니다.</font></td>
			</tr>
		</c:otherwise>
	</c:choose>
</table>

<c:if test="${fn:length(couponVO.couponApplyGrpList) > 0}">
	<div class="button" style="margin-top:20px;margin-bottom:20px;" align="right">
		<input type=image src="/resources/shop/admin/img/btn_modify.gif" id="modifyApplyBtn">
	</div>
</c:if>
	

<%-- 페이징 시작 --%>
<tags:paginator currentPageNo="${couponVO.pageNo}" rowCount="${couponVO.rowCount}" pageSize="${couponVO.pageSize}"  pageGroupSize="${couponVO.pageGroupSize}" />
<%-- 페이징 끝 --%>

<p>
<c:if test="${'0' == couponInfo.coupontype}">
<div style="height:10"></div>
<div style="padding:3 0 5 8"><img src="/resources/shop/admin/img/ico_arrow_down.gif" align=absmiddle> <font color=0074BA><b>이 쿠폰을 제공할 회원선택</b></font> <font class=extext>(쿠폰을 지급할 회원을 추가하려면 아래에서 회원을 선택하세요)</font></div>		
<form name="couponAddForm" id="couponAddForm" method=post >
<input type=hidden name=mode value="applyAdd">
<input type=hidden name=couponcd value="${couponInfo.couponcd}">
<!-- <input type=hidden name=sno value=""> -->

	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td valign="top">
				<table class="tb" border="1" bordercolor="#e6e6e6" cellpadding="5" style="width: 100%; border-collapse: collapse;">
					<col class="cellC"	style="padding:5,0,5,5"><col class="cellL" style="padding:5,0,5,5">
					<tr>
						<td align="left"><input type="radio" name="membertype" value="0" class="null" checked>전체회원발급</td>
						<td>전체회원(현재 총 <font color="EA0095"><b>${couponVO.couponGrpTotal}명</b></font>)에게 쿠폰을 발급합니다.</td>
					</tr>
					<tr>
						<td align="left"><input type="radio" name="membertype" value="1" class="null" >그룹별발급</td>
						<td>
							<select name="membergrpsno" >
								<option value="">해당그룹을 선택하세요.</option>
								<c:choose>
								<c:when test="${couponVO.couponGrpList != null &&  fn:length(couponVO.couponGrpList) >0 }">	
									<c:forEach items="${couponVO.couponGrpList}" var="list" varStatus="i">
									<option value="${list.sno}">${list.grpnm}(${list.cnt}명)</option>
									</c:forEach>
								</c:when>
								</c:choose>
							</select>
						</td>
					</tr>
					<tr>
						<td height="170" align="left" valign="top"><input type="radio" name="membertype" value="2" class="null" >회원개별발급</td>
						<td valign="top">
							<div style="padding-top:4"><a href="javascript:popup('myritzPopupMember',800,600);"><img src="/resources/shop/admin/img/arrow_blue.gif" align=absmiddle><font color=0074BA><b>[회원검색하기]</b></font></a></div>
							<div class="box">
								<table width="300" id="m_ids">
									<col><col width="50" style="padding-right:10"><col width="52" align="right">
								</table>
							</div>
						</td>	
					</tr>
				</table>
			</td>
			<%-- 기존 SMS 발송기능 사용 안함으로 영역 삭제 조치 --%>
		</tr>
	</table>
	
</form>
</c:if>

<div style="padding-top:15px"></div>
<div class="button" align="center">
	<c:if test="${'0' == couponInfo.coupontype}">
		<input type=image src="/resources/shop/admin/img/btn_register.gif" id="couponSubmitBtn">
	</c:if>	
	<a href="${ctx}/shop/admin/myritz/myritzCouponList"><img src="/resources/shop/admin/img/btn_cancel.gif"></a>
</div>

<div style="padding-top:15px"></div>
<div id=MSG01>
	<table cellpadding=2 cellspacing=0 border=0 class=small_ex>
		<tr>
			<td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>삭제버튼을 클릭하면 해당 회원에게 발급된쿠폰이 취소됩니다.</td></tr>
		<tr>
			<td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>'쿠폰사용완료'란 해당 회원이 이미 쿠폰을 사용하여 완료됨을 의미합니다.</td></tr>
	</table>
</div>
<script>cssRound('MSG01');</script>
<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

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
