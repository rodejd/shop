<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>
<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language=javascript src="/resources/shop/admin/common.js"></script>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
	/*
	 * jQuery ready
	 */
	$(function(){
		
	});
	
	var setPeriod = function(dt1, dt2){
		if ( null != dt1 ){
			$('[name=sregdt1]').val(dt1);
		} else {
			$('[name=sregdt1]').val('');
		}
		
		if ( null != dt2 ){
			$('[name=sregdt2]').val(dt2);
		} else {
			$('[name=sregdt2]').val('');
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
<form class="js_frmList" action="hack" method="post">
	<input type="hidden" class="pageNo" name="pageNo"  value="${hackVO.pageNo != '' ? hackVO.pageNo : '1' }"/>
	<input type="hidden" name="nolist"  id="nolist"/>
	
	<div class="title title_top">회원탈퇴/삭제내역<span>탈퇴 또는 삭제한 회원들의 내역을 확인합니다 
	</div>
	<table class="tb">
		<col class="cellC" /><col class="cellL" style="width:250" />
		<col class="cellC" /><col class="cellL" />
		<tr>
			<td>키워드검색</td>
			<td>
				<select name="skey">
					<option value="all"   > 통합검색 </option>
					<option value="m_id"  > 아이디 </option>
					<option value="name"  > 이름 </option>
				</select> 
				<input type="text" NAME="sword" value="${hackVO.sword }" class="line" />
			</td>
			<td width=170>스킨검색</td>
			<td>
				<select name="schSkin">
					<option value="">전체</option>
					${webUtil:makeSelectCodeItem((codeUtil:codeitem('skin')), hackVO.schSkin) }
				</select>
			</td>
		</tr>
		<tr>
			<td>처리형태</td>
			<td>
				<select name="sactor">
					<option value="" >전체</option>
					<option value="1"> 본인탈퇴 </option>
					<option value="0"> 강제삭제 </option>
				</select>
			</td>
			<td>그룹</td>
			<td>
				<select name="slevel">
					<option value="">전체</option>
					<c:forEach items="${codeUtil:getMemberGrp()}" var="mglist" varStatus="st">
						<option value="${mglist.kLevel}" ${stringUtil:selected(hackVO.slevel, fn:trim(mglist.kLevel))}>${mglist.grpnm} - lv[${mglist.kLevel}]</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>탈퇴/삭제일</td>
			<td colspan=3>
				<input type="text" name="sregdt1" value="${hackVO.sregdt1 }" onclick="calendar(event)" class="cline" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);"/> ~
				<input type="text" name="sregdt2" value="${hackVO.sregdt2 }" onclick="calendar(event)" class="cline" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);"/>
			 	<a href="javascript:setPeriod(${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align="absmiddle" /></a>
				<a href="javascript:setPeriod(${dateUtil:getDateFormatFrom('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-7)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align="absmiddle" /></a>
				<a href="javascript:setPeriod(${dateUtil:getDateFormatFrom('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-15)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align="absmiddle" /></a>
				<a href="javascript:setPeriod(${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-1)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align="absmiddle" /></a>
				<a href="javascript:setPeriod(${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-2)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align="absmiddle" /></a>
				<a href="javascript:setPeriod()"><img src="/resources/shop/admin/img/sicon_all.gif" align="absmiddle" /></a>  
			</td>
		</tr>
	</table>
	<div class="button_top"><input type="image" src="/resources/shop/admin/img/btn_search2.gif" /></div>
	
	<table width="100%">
		<tr>
			<td class="pageInfo">
			총 <b>${hackVO.totalCount }</b>개, 검색 <b>${hackVO.rowCount }</b>개, <b>${hackVO.pageNo}</b></b> of ${pageCnt } Pages
			</td>
			<td align="right">
				<select name="sort" onchange="this.form.submit();">
					<option value="regdt desc" ${stringUtil:selected(hackVO.sort, "regdt desc")}>- 탈퇴일 정렬↑</option>
					<option value="regdt asc"  ${stringUtil:selected(hackVO.sort, "regdt asc")}>- 탈퇴일 정렬↓</option>
					<optgroup label="------------"></optgroup>
					<option value="m_id desc" ${stringUtil:selected(hackVO.sort, "m_id desc")}>- 아이디 정렬↑</option>
					<option value="m_id asc"  ${stringUtil:selected(hackVO.sort, "m_id asc")}>- 아이디 정렬↓</option>
					<option value="name desc" ${stringUtil:selected(hackVO.sort, "name desc")}>- 이름 정렬↑</option>
					<option value="name asc"  ${stringUtil:selected(hackVO.sort, "name asc")}>- 이름 정렬↓</option>
				</select>&nbsp;
				<select name="pageSize" onchange="this.form.submit()">
					<option value="10" ${stringUtil:selected(hackVO.pageSize, "10")}>10개 출력</option>
					<option value="20" ${stringUtil:selected(hackVO.pageSize, "20")}>20개 출력</option>
					<option value="40" ${stringUtil:selected(hackVO.pageSize, "40")}>40개 출력</option>
					<option value="60" ${stringUtil:selected(hackVO.pageSize, "60")}>60개 출력</option>
					<option value="100"${stringUtil:selected(hackVO.pageSize, "100")}>100개 출력</option>
				</select>
			</td>
		</tr>  
	</table>
</form>

	<table width=100% cellpadding=0 cellspacing=0>
		<tr><td class=rnd colspan=10></td></tr>
		<tr class=rndbg>
			<th width="50">선택</th>
			<th width="60">번호</th>
			<th width="60">국가</th>
			<th width="120">아이디</th>
			<th width="140">이름</th>
			<th width="140">통관고유부호</th>
			<th width="80">처리형태</th>
			<th width="100">탈퇴/삭제일</th>
			<th width="80">재가입여부</th>
			<th width="60">탈퇴사유</th>
			
		</tr>
		<tr><td class=rnd colspan=10></td></tr>
		<tr><td colspan=10 height=4></td></tr>
		<tr><td height=4 colspan=10></td></tr>

		<c:choose>
			<c:when test="${hackVO.rowCount > 0}">
				<c:forEach items="${hackVO.hackList}" var="list" varStatus="vnum">
			 		<tr height=25 align="center" onmouseover='this.style.background="#F7F7F7"' onmouseout='this.style.background=""'>
			 			<td class="noline"><input class="js_confirmyn" type=checkbox value="${list.sno}"></td>
						<td><font class=ver8 color=616161>${(hackVO.rowCount - vnum.index) - ( hackVO.rowStart ) }</font></td>
						<td><font class="ver81" color="#616161">${codeUtil:getCodeName("skin", list.skin)}</font></td><!-- 스킨 -->
			 			<td><font class=ver81 color=0074BA><b>${list.mId}</b></font></td>
						<td>${list.name}</td>
						<td>${list.customsnum}</td>
						<td><font class=extext><b>${list.actor == 1 ? "본인탈퇴" : "강제삭제"}</b></font></td>
						<td><font class=ver81 color=616161>${list.formatDate}</font></td>
						<td><font class=small color=616161>${list.rejoin == 1 ? "불가능" : "가능"}</font></td>
						<td><a href="javascript:popupLayer('../member/hack_register?mode=modify&sno=${list.sno}')"><img src="/resources/shop/admin/img/btn_viewbbs.gif"></a></td>
					</tr>
					<tr><td height=4 colspan=10></td></tr>
					<tr><td colspan=12 class=rndline></td></tr> 
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr height=25 align="center" onmouseover='this.style.background="#F7F7F7"' onmouseout='this.style.background=""'>
					<td colspan="9" align="center">내역이 없습니다.<td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<c:if test="${hackVO.rowCount > 0}">
		<div align=center class=pageNavi>
			<b><tags:paginator currentPageNo="${hackVO.pageNo}" rowCount="${hackVO.rowCount}" pageSize="${hackVO.pageSize}"  pageGroupSize="${hackVO.pageGroupSize}" /></b>
		</div>
	</c:if>



<div align=right style="padding-right:10px;">
	<img src="/resources/shop/admin/img/btn_allselect_s.gif" alt="전체선택"  border="0" align="absmiddle" style="cursor:hand" 
		 onclick="javascript:checkPubAllSordes( 'select', ${hackVO.rowCount} );">
	
	<img src="/resources/shop/admin/img/btn_allreselect_s.gif" alt="선택반전"  border="0" align="absmiddle" style="cursor:hand" 
		 onclick="javascript:checkPubAllSordes( 'reflect', ${hackVO.rowCount} );">
	
	<img src="/resources/shop/admin/img/btn_alldeselect_s.gif" alt="선택해제"  border="0" align="absmiddle" style="cursor:hand" 
	 	 onclick="javascript:checkPubAllSordes( 'deselect', ${hackVO.rowCount} );">
	
	<img src="/resources/shop/admin/img/btn_alldelet_s.gif" alt="선택삭제" border="0" align="absmiddle" style="cursor:hand" 
		 onclick="javaScript:act_delete();">
</div>

<div style="padding-top:15px"></div>



<div id=MSG01>
	<table cellpadding=2 cellspacing=0 border=0 class=small_ex>
		<tr>
			<td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">현재 보이는 리스트에서 회원을 삭제하면 재가입이 바로 가능합니다.</td>
		</tr>
	</table>
</div>

<script>
	cssRound('MSG01');
</script>

<div style="padding-top:15px"></div>


<SCRIPT LANGUAGE=JavaScript>
/*-------------------------------------
 삭제
-------------------------------------*/
	function act_delete(){
		var $checkBoxArr = $('.js_confirmyn');
		if($checkBoxArr.length > 0) {
			var $form = $('.js_frmList');
			
			if ( PubChkSelect( $checkBoxArr ) == false ){
				alert( "삭제하실 회원을 선택하여 주십시요." );
				return;
			}
		
			if ( confirm( "선택한 회원을 정말 삭제하시겠습니까?\n삭제후에는 해당 회원의 재가입이 가능해집니다." ) == false ) return;
		
			var idx = 0;
			var codes = new Array();
			var count = $checkBoxArr.length;
		
			if ( count == undefined ) codes[ idx++ ] = $checkBoxArr.value;
			else {
		
				for (var i = 0; i < count ; i++ ){
					if ( $checkBoxArr[i].checked ) codes[ idx++ ] = $checkBoxArr[i].value;
				}
			}
		
			$('#nolist').val(codes.join(';'));
			$form.attr('action', 'hack/indb?mode=delete') ;
			$form.submit() ;
		} else {
			alert( '데이타가 존재하지 않습니다.' );
		}
	}
	
	function optionSelect($selectHidden, $selectTag) {
		var selectHidden = $selectHidden.val();
		var selectTag = $selectTag.select()[0];
		for(i = 0, size = selectTag.length; i < size; i++) {
			if(selectTag[i].value == selectHidden) {
				selectTag[i].selected = true;
				break;
			}
		}
	}
	
	function goPage(page){
		$('.pageNo').val(page);
		$('.js_frmList').submit();
	}
	
	function checkPubAllSordes(select, listSize) {
		if(listSize > 0) {
			PubAllSordes(select, $('.js_confirmyn'));
		} else {
			alert( '데이타가 존재하지 않습니다.' );
		}
	}
</SCRIPT>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<%@ include file="../common/bottom.jsp" %>
		</td>
	</tr>
</table>
