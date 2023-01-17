<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%@ page import="static com.wepinit.wepinit_shop.xmall.common.ShopLibFunction.*"%>

<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>

<form name=frmList class="frmList">
<input type="hidden" class="pageNo" name="pageNo" value="1" />
<div class="title title_top">공지사항<span></span></div>
<table class=tb>
<col class=cellC><col class=cellL>
<tr>
	<td>키워드</td>
	<td>
		<select name="skey">
			<option value="all"  ${stringUtil:selected(noticeVO.skey,"all") }> 통합검색 </option>
			<option value="name"  ${stringUtil:selected(noticeVO.skey, "name")}> 작성자 </option>
			<option value="subject"  ${stringUtil:selected(noticeVO.skey, "subject")}> 제목 </option>
			<option value="contents"  ${stringUtil:selected(noticeVO.skey, "contents")} > 내용 </option>
		</select> 
		<input type="text" NAME="sword" value="${noticeVO.sword }" class=line>
	</td>
</tr>
<tr>
	<td>등록일</td>
	<td>
	<input type=text name=sregdt value="${noticeVO.sregdt_0 }" onclick="calendar(event)" class=line onkeydown="onlynumber()"> -
	<input type=text name=sregdt value="${noticeVO.sregdt_1 }" onclick="calendar(event)" class=line onkeydown="onlynumber()">
	<a href="javascript:setDate('sregdt', ${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align=absmiddle></a>
	<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFrom('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-7)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align=absmiddle></a>
	<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFrom('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-15)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align=absmiddle></a>
	<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-1)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align=absmiddle></a>
	<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-2)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align=absmiddle></a>
	<a href="javascript:setDate('sregdt')"><img src="/resources/shop/admin/img/sicon_all.gif" align=absmiddle></a>
	</td>
</tr>
</table>
<div class=button_top><input type=image src="/resources/shop/admin/img/btn_search2.gif"></div>

<table width=100%>
<tr>
</tr>
</table>
</form>

<form method="post" action="" name="fmList" id="fmList" enctype="multipart/form-data">
<INPUT TYPE="hidden" name="allmodify">
<table width=100% cellpadding=0 cellspacing=0>
<tr><td class=rnd colspan=10></td></tr>
<tr class=rndbg>
	<th width="60">번호</th>
	<th>제목</th>
	<th width="50">첨부</th>
	<th width="70">등록일</th>
	<th width="50">순서</th>
	<th width="50">삭제</th>
</tr>
<tr><td class=rnd colspan=10></td></tr>

	<c:choose>
		<c:when test="${noticeVO.noticeList != null &&  fn:length(noticeVO.noticeList) >0 }">
			<c:forEach items="${noticeVO.noticeList }" var="list" varStatus="vnum">
				<tr><td height=4 colspan=10>
				<input type="hidden" name="no" value="${list.no }" />
				</td></tr>
				<tr height=25 align="center">
					<td>${(noticeVO.rowCount - vnum.index) - ( noticeVO.rowStart ) }</td>
					<td align=left><a href="register?mode=modify&no=${list.no}">${list.subject}</a></td>
					<td>${not empty list.newfile ? 'O' : ''}</td>
					<td><font class=ver8 color=444444><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd"/></font></font></td>
					<td align="center">
						<input type="text" size="25" name="sort" value="${list.sort}" style="width:30;text-align:center" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" maxlength="3">
					</td>
					<td class="noline"><input type=checkbox name=confirmyn value="${list.no}"></td>
				</tr>
				<tr><td colspan=10 class=rndline></td></tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr><td height=4 colspan=6></td></tr>
			<tr height=25><td align=center colspan=7> NO_LIST_DATA </td></tr>
			<tr><td height=4></td></tr>
			<tr><td colspan=7 class=rndline></td></tr>
		</c:otherwise>
	</c:choose>

</table>
<INPUT TYPE="hidden" style="width:300" NAME="nolist">
</form>

	<!-- 페이징  -->
<div align=center class=pageNavi>
	<b><tags:paginator currentPageNo="${noticeVO.pageNo}" rowCount="${noticeVO.rowCount}" pageSize="${noticeVO.pageSize}"  pageGroupSize="${noticeVO.pageGroupSize}" /></b>
</div>

<div style="float:left;"><input type="image" src="/resources/shop/admin/img/btn_allselect_s.gif" 	alt="전체선택" border="0" align='absmiddle'  
		<c:if test="${fn:length(noticeVO.noticeList)!=0}">
			onclick="javascript:PubAllSordes( 'select', fmList['confirmyn'] );"
		</c:if>
		<c:if test="${fn:length(noticeVO.noticeList)!=0}">
			onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
		</c:if>
		>

<input type="image"  src="/resources/shop/admin/img/btn_allreselect_s.gif" alt="선택반전" border="0" align='absmiddle' class="button_top" 
		<c:if test="${fn:length(noticeVO.noticeList)!=0}">
			onclick="javascript:PubAllSordes( 'reflect', fmList['confirmyn'] );"
		</c:if>
		<c:if test="${fn:length(noticeVO.noticeList)!=0}">
			onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
		</c:if>
		>

<input type="image"  src="/resources/shop/admin/img/btn_alldeselect_s.gif" alt="선택해제" border="0" align='absmiddle' class="button_top" 
		<c:if test="${fn:length(noticeVO.noticeList)!=0}">
			onclick="javascript:PubAllSordes( 'deselect', fmList['confirmyn'] );"
		</c:if>
		<c:if test="${fn:length(noticeVO.noticeList)!=0}">
			onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
		</c:if>
		>
<input type="image"  src="/resources/shop/admin/img/btn_alldelet_s.gif" 	alt="선택삭제" border="0" align='absmiddle' class="button_top" 
		<c:if test="${fn:length(noticeVO.noticeList)!=0}">
			onclick="javaScript:act_delete();"
		</c:if>
		<c:if test="${fn:length(noticeVO.noticeList)!=0}">
			onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
		</c:if>
		>
</div>

<div style="float:right;">
<input type="image" class="button_top"  src="/resources/shop/admin/img/btn_allmodify_s.gif" id="modifySort" alt="순서수정" border="0" align='absmiddle' />
<a href="register"><img src="/resources/shop/admin/img/btn_regist_s.gif" alt="등록" border=0 class="button_top" align=absmiddle></a>
</div>

<div style="padding-top:35;"></div>

<div id=MSG01>
<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
<tr><td><img src="/resources/shop/admin/img/arrow_blue.gif" align=absmiddle>'출력순서' 변경</font></td></tr>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>유저모드 : 오름차순 출력</td></tr>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>방법1) 일괄수정 : 각 '출력순서' 칸을 입력 후 [일괄수정]을 클릭하시면 됩니다.</td></tr>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>베스트>로 선정해놓으면 베스트문의 5개가 고객센터 메인화면에 나오게 됩니다.</td></tr>

</table>
</div>
<script>cssRound('MSG01')</script>


<script type="text/javascript">
$(function() {
	
	//2017-09-21 순서(sort) 중복 검사 추가
	$('#modifySort').on('click', function() {
 		//input value 값 배열로 저장
		var sortGroup = $('[name=sort]').map(function() {
			return this.value;
		}).get();
		
		//배열 정렬
		sortGroup.sort(function(a, b) {
			return a - b;
		});
		
		//정렬된 배열의 이웃하는 값 비교
		var length = sortGroup.length;
		for (var i=0; i<length-1; i++) {
			if (sortGroup[i] == sortGroup[i+1]) {
				alert("순서가 중복되었습니다.");
				return false;
			}
		}
		sortAjax();
	});
});


function goPage(page){
	$('.pageNo').val(page);
	$('.frmList').submit();
}

//순서 일괄 수정
function sortAjax(){
	var fmList = document.fmList;
	var no = fmList.no;
	var noArr = new Array();
	
	for(i=0; i<no.length; i++){
		noArr[i] = no[i].value;
	}
	
	var sort = fmList.sort;
	var sortArr = new Array();
	for(i=0; i<sort.length; i++){
		sortArr[i] =sort[i].value;
	}
	
	jQuery.ajaxSettings.traditional = true;
	
	ajaxCallJson("sort.dojson"
					,{no:noArr
					,sort:sortArr}
					, function(result){
						if (null != result.existingSort) {
							var errorMsg = result.existingSort.join(',');
							alert("중복 되는 "+errorMsg+" 를 제외하고, 요청하신 내용이 처리 완료되었습니다.");
						} else {
							alert("요청하신 내용이 처리 완료되었습니다.");	
						}
						location.reload();
					}
					, function(e){
						alert("일시적인 오류가 발생했습니다.");
						alert(e);
					});
}

//수정 후 함수
function act_allmodify(){

	var fs = document.fmList; // 리스트폼

	if( fs['code'] == null ){
		alert('선택된 사항이 없습니다.');
		return; // 레코드가 1미만인 경우
	}
	
	var fieldnm = new Array( 'code', 'sort', 'itemcd', 'best', 'bestsort' ); // 필드명
	var csField = new Array(); // 필드데이타저장

	// 필드데이타초기화
	fieldnm.each(function(item) { 
		csField[item] = '';
	}); 
	

	var count = fs['code'].length;	// 레코드수

	if( count == undefined ){ // 레코드수가 1개 인 경우

		fieldnm.each(function(item) { 
			var Obj = eval( "fs['" + item + "']" );
			if( Obj.type != 'checkbox' ) csField[item] += Obj.value + ";"; else csField[item] += Obj.checked + ";";
		});
	}
	else { // 레코드수가 2개 이상인 경우

		for( var i = 0; i < count; i++ ){

			fieldnm.each(function(item) { 
				var Obj = eval( "fs['" + item + "']" );
				if( Obj[i].type != 'checkbox' ){
					csField[item] += Obj[i].value + ";"; 
				}else{
					csField[item] += Obj[i].checked + ";";
					
				}
			});
		}
	}

	fieldnm.each(function(item) { 
		fs.allmodify.value += item + '==' + csField[item] + '||';
	});
	
	fmList.action = "indb?mode=allmodify";
	fmList.submit() ;
}

/*-------------------------------------
삭제
-------------------------------------*/
function act_delete(){

	if ( PubChkSelect( fmList['confirmyn'] ) == false ){
		alert( "삭제하실 내역을 선택하여 주십시요." );
		return;
	}

	if ( confirm( "선택한 아이템을 정말 삭제하시겠습니까?\n삭제 후 복구할 수 없습니다." ) == false ) return;

	var idx = 0;
	var codes = new Array();
	var count = fmList['confirmyn'].length;

	if ( count == undefined ) codes[ idx++ ] = fmList['confirmyn'].value;
	else {

		for ( i = 0; i < count ; i++ ){
			if ( fmList['confirmyn'][i].checked ){
				codes[ idx++ ] = fmList['confirmyn'][i].value;
			}
		}
	}

	fmList.nolist.value = codes.join( ";" );
	fmList.action = "indb?mode=delete" ;
	fmList.submit() ;
}

</script>

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