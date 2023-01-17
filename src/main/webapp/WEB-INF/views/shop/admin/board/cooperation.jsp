<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>


<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language=javascript src="/resources/shop/admin/common.js"></script>
<script language="javascript">
	/*
	 * php script
	 */
</script>

<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
/*
 * jQuery ready
 */
$(function(){
	
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

<form name=frmList class="frmList">
<input type="hidden" class="pageNo" name="pageNo" value="1" />
<div class="title title_top">광고·제휴문의<span>광고제휴건으로 들어온 문의를 처리하실 수 있습니다</span> <!-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=board&no=7',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle hspace=2></a> --></div>
<table class=tb>
<col class=cellC><col class=cellL><col class=cellC><col class=cellL>
<tr>
	<td>키워드검색</td>
	<td>
	<select name="skey">
	<option value="all" ${stringUtil:selected(cooperVO.skey, 'all')}> 통합검색 </option>
	<option value="title" ${stringUtil:selected(cooperVO.skey, 'title')}> 문의제목 </option>
	<option value="content" ${stringUtil:selected(cooperVO.skey, 'content')}> 문의내용 </option>
	<option value="reply" ${stringUtil:selected(cooperVO.skey, 'reply')}> 답변 </option>
	<option value="name" ${stringUtil:selected(cooperVO.skey, 'name')}> 이름 </option>
	</select> <input type="text" NAME="sword" value="${cooperVO.sword}" class=line>
	</td>
	<td>문의분야</td>
	<td>
	<select name="sitemcd">
	<option value="" ${stringUtil:selected(cooperVO.sitemcd, '')}> - 전체 - </option>
	
	${webUtil:makeSelectCodeItem((codeUtil:codeitem('cooperation')), cooperVO.sitemcd) }
		
	</select>
	</td>
</tr>
<tr>
	<td>답변여부</td>
	<td class=noline>
	<label for="r1"><input type="checkbox" id="sreplyyn" name="sreplyyn" value="Y"  ${stringUtil:checked(cooperVO.sreplyyn, 'Y')} onclick="oneCheckbox(this, 'sreplyyn');"> 답변 후 </label>
	<label for="r2"><input type="checkbox" id="sreplyyn" name="sreplyyn" value="N"  ${stringUtil:checked(cooperVO.sreplyyn, 'N')} onclick="oneCheckbox(this, 'sreplyyn');"> 답변 전 </label>
	</td>
	<td>답변메일여부</td>
	<td class=noline>
	<label for="r3"><input type="checkbox" id="r3" name="smailyn" value="Y"  ${stringUtil:checked(cooperVO.smailyn, 'Y')} onclick="oneCheckbox(this, 'smailyn');"> 전송 후 </label>
	<label for="r4"><input type="checkbox" id="r4" name="smailyn" value="N"  ${stringUtil:checked(cooperVO.smailyn, 'N')} onclick="oneCheckbox(this, 'smailyn');"> 전송 전 </label>
	</td>
</tr>
<tr>
	<td>접수일</td>
	<td colspan="3">
	<input type=text name=sregdt value="${cooperVO.sregdt_0}" onclick="calendar(event)" class=line onkeydown="onlynumber()"> -
	<input type=text name=sregdt value="${cooperVO.sregdt_1}" onclick="calendar(event)" class=line onkeydown="onlynumber()">
	<a href="javascript:setDate('sregdt',${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align=absmiddle></a>
	<a href="javascript:setDate('sregdt',${dateUtil:getDateFormatFrom('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -7)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align=absmiddle></a>
	<a href="javascript:setDate('sregdt',${dateUtil:getDateFormatFrom('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -15)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align=absmiddle></a>
	<a href="javascript:setDate('sregdt',${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'), -1)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align=absmiddle></a>
	<a href="javascript:setDate('sregdt',${dateUtil:getDateFormatFromMonth('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -2)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align=absmiddle></a>
	<a href="javascript:setDate('sregdt')"><img src="/resources/shop/admin/img/sicon_all.gif" align=absmiddle></a>
	</td>
</tr>
<tr>
	<td>답변일</td>
	<td colspan="3">
	<input type=text name=sreplydt value="${cooperVO.sreplydt_0}" onclick="calendar(event)" class=line onkeydown="onlynumber()"> -
	<input type=text name=sreplydt value="${cooperVO.sreplydt_1}" onclick="calendar(event)" class=line onkeydown="onlynumber()">
	<a href="javascript:setDate('sreplydt',${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align=absmiddle></a>
	<a href="javascript:setDate('sreplydt',${dateUtil:getDateFormatFrom('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -7)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align=absmiddle></a>
	<a href="javascript:setDate('sreplydt',${dateUtil:getDateFormatFrom('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -15)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align=absmiddle></a>
	<a href="javascript:setDate('sreplydt',${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'), -1)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align=absmiddle></a>
	<a href="javascript:setDate('sreplydt',${dateUtil:getDateFormatFromMonth('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -2)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align=absmiddle></a>
	<a href="javascript:setDate('sreplydt')"><img src="/resources/shop/admin/img/sicon_all.gif" align=absmiddle></a>
	</td>
</tr>
<tr>
	<td>메일전송일</td>
	<td colspan="3">
	<input type=text name=smaildt value="${cooperVO.smaildt_0}" onclick="calendar(event)" class=line onkeydown="onlynumber()"> -
	<input type=text name=smaildt value="${cooperVO.smaildt_1}" onclick="calendar(event)" class=line onkeydown="onlynumber()">
	<a href="javascript:setDate('smaildt',${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align=absmiddle></a>
	<a href="javascript:setDate('smaildt',${dateUtil:getDateFormatFrom('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -7)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align=absmiddle></a>
	<a href="javascript:setDate('smaildt',${dateUtil:getDateFormatFrom('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -15)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align=absmiddle></a>
	<a href="javascript:setDate('smaildt',${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'), -1)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align=absmiddle></a>
	<a href="javascript:setDate('smaildt',${dateUtil:getDateFormatFromMonth('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -2)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align=absmiddle></a>
	<a href="javascript:setDate('smaildt')"><img src="/resources/shop/admin/img/sicon_all.gif" align=absmiddle></a>
	</td>
</tr>
</table>
<div class=button_top><input type=image src="/resources/shop/admin/img/btn_search2.gif"></div>

<table width=100%>
<tr>
	<td class=pageInfo><font class=ver8>
	<c:set var="pages" value="${(cooperVO.rowCount*10) / (cooperVO.pageSize*10)} " />
	<c:set var="pageCnt" value="${pages+(1-(pages%1))%1}" />
	<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
	총 <b>${cooperVO.totalCount }</b>개, 검색 <b>${cooperVO.rowCount}</b>개, <b>${cooperVO.pageNo }</b> of <b>${var3 }</b><%-- <%=pageUtil.getM_iTotalPages()%> --%> Pages
	</td>
	<td align=right>
	<select name="sort" onchange="this.form.submit();">
	<option value="regdt desc" ${stringUtil:selected(cooperVO.sort, "regdt desc")}>- 등록일 정렬↑</option>
	<option value="regdt asc" ${stringUtil:selected(cooperVO.sort, "regdt asc")} >- 등록일 정렬↓</option>
    <optgroup label="------------"></optgroup>
    <option value="title desc" ${stringUtil:selected(cooperVO.sort, "title desc")} >- 문의제목 정렬↑</option>
    <option value="title asc" ${stringUtil:selected(cooperVO.sort, "title asc")} >- 문의제목 정렬↓</option>
    <option value="itemcd desc" ${stringUtil:selected(cooperVO.sort, "itemcd desc")} >- 문의분야 정렬↑</option>
    <option value="itemcd asc" ${stringUtil:selected(cooperVO.sort, "itemcd asc")} >- 문의분야 정렬↓</option>
	</select>&nbsp;
	<select name=pageSize onchange="this.form.submit()">
		<option value="10" ${stringUtil:selected(cooperVO.pageSize, "10")}>10개 출력
		<option value="20" ${stringUtil:selected(cooperVO.pageSize, "20")}>20개 출력
		<option value="40" ${stringUtil:selected(cooperVO.pageSize, "40")}>40개 출력
		<option value="60" ${stringUtil:selected(cooperVO.pageSize, "60")}>60개 출력
		<option value="100" ${stringUtil:selected(cooperVO.pageSize, "100")}>100개 출력
		</select>
	</td>
</tr>
</table>
</form>


<form method="post" action="" name="fmList">
<INPUT TYPE="hidden" name="allmodify">
<table width=100% cellpadding=0 cellspacing=0>
<tr><td class=rnd colspan=10></td></tr>
<tr class=rndbg>
	<th width="60">번호</th>
	<th width="110">분야</th>
	<th>문의제목</th>
	<th width="70">글쓴이</th>
	<th width="70">접수일</th>
	<th width="70">답변일</th>
<!-- 	<th width="70">답변메일</th> -->
	<th width="60">답변</th>
	<th width="50">삭제</th>
</tr>
<tr><td class=rnd colspan=10></td></tr>

	<c:choose>
		<c:when test="${cooperVO.cooperList != null &&  fn:length(cooperVO.cooperList) >0 }">
		
			<c:forEach items="${cooperVO.cooperList }"  var="list" varStatus="vnum">
				<INPUT TYPE="hidden" NAME="code" VALUE="${list.sno}">
				<tr><td height=4 colspan=10></td></tr>
				<tr height=25 align="center">
					<td><font class=ver81 color=444444>${(cooperVO.rowCount - vnum.index) - ( cooperVO.rowStart ) }</td>
					<td>
					${codeUtil:getCodeName("cooperation", list.itemcd)}
					<%-- <select name="itemcd" style="width:80;">
					<option value=""> - 문의분야 - </option>
					${webUtil:makeSelectCodeItem((codeUtil:codeitem('cooperation')), list.itemcd) }
					</select> --%>
					</td>
					<td align=left><a href="register?mode=modify&sno=${list.sno}">${null != list.replydt ? '' : '<b>'}${list.title}</a></td>
					<td>${list.name}</td>
					<td><font class=ver81 color=444444><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd"/></font></td>
					<%-- <td><font class=ver81 color=444444>${!'' eq (stringUtil:replace(list.replydt, '.', '')) ? list.replydt : '미답변' }</font></td> --%>
					
					<c:choose>
						<c:when test="${null eq list.reply || '' eq list.reply }">
							<td><font class=ver81 color=444444> 미답변 </font></td>
						</c:when>
						<c:otherwise>
						<td><font class=ver81 color=444444> <fmt:formatDate value="${list.replydt}" pattern="yyyy-MM-dd"/> </font></td>
						</c:otherwise>
					</c:choose>
					
					<td STYLE="PADDING-TOP:3PX;"><a href="register?mode=modify&sno=${list.sno}"><img src="/resources/shop/admin/img/i_reply.gif"></a></td>
					<td class="noline"><input type=checkbox name=confirmyn value="${list.sno}"></td>
				</tr>
				<tr><td height=4 colspan=10></td></tr>
				<tr><td colspan=10 class=rndline></td></tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
				<tr><td height=4 colspan=6></td></tr>
				<tr height=25><td align=center colspan=6> NO_LIST_DATA </td></tr>
				<tr><td height=4></td></tr>
				<tr><td colspan=7 class=rndline></td></tr>
		</c:otherwise>
	
	</c:choose>
	
</table>
<INPUT TYPE="hidden" style="width:300" NAME="nolist">
</form>

<!-- 페이징 -->
<div align=center class=pageNavi>
	<b><tags:paginator currentPageNo="${cooperVO.pageNo}" rowCount="${cooperVO.rowCount}" pageSize="${cooperVO.pageSize}"  pageGroupSize="${cooperVO.pageGroupSize}" /></b>
</div>

<div style="float:left;"><input type="image" class="button_top" src="/resources/shop/admin/img/btn_allselect_s.gif" 	alt="전체선택" border="0" align='absmiddle' style="cursor:hand" 
		<c:if test="${fn:length(cooperVO.cooperList)!=0}">
			onclick="javascript:PubAllSordes( 'select', fmList['confirmyn'] );"
		</c:if>
		<c:if test="${fn:length(cooperVO.cooperList)!=0}">
			onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
		</c:if>
		>

<input type="image" class="button_top" src="/resources/shop/admin/img/btn_allreselect_s.gif" alt="선택반전" border="0" align='absmiddle' style="cursor:hand"
		<c:if test="${fn:length(cooperVO.cooperList)!=0}">
			onclick="javascript:PubAllSordes( 'reflect', fmList['confirmyn'] );"
		</c:if>
		<c:if test="${fn:length(cooperVO.cooperList)!=0}">
			onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
		</c:if>
		>

<input type="image" class="button_top" src="/resources/shop/admin/img/btn_alldeselect_s.gif" alt="선택해제" border="0" align='absmiddle' style="cursor:hand"
		<c:if test="${fn:length(cooperVO.cooperList)!=0}">
			onclick="javascript:PubAllSordes( 'deselect', fmList['confirmyn'] );"
		</c:if>
		<c:if test="${fn:length(cooperVO.cooperList)!=0}">
			onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
		</c:if>
		>
<input type="image" class="button_top" src="/resources/shop/admin/img/btn_alldelet_s.gif" 	alt="선택삭제" border="0" align='absmiddle' style="cursor:hand"
		<c:if test="${fn:length(cooperVO.cooperList)!=0}">
			onclick="javaScript:act_delete();"
		</c:if>
		<c:if test="${fn:length(cooperVO.cooperList)!=0}">
			onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
		</c:if>
		>
</div>

<div style="float:right;">
</div>

<div style="padding-top:35;"></div>

<div id=MSG01>
<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>답변글이 없는 경우 문의 제목이 굵게 출력됩니다.</td></tr>
</table>
</div>
<script>cssRound('MSG01')</script>

<SCRIPT LANGUAGE="JavaScript">

function goPage(page){
	$('.pageNo').val(page);
	$('.frmList').submit();
}
/*-------------------------------------
 일괄수정
-------------------------------------*/
function act_allmodify(){

	var fs = document.fmList; // 리스트폼

	if( fs['code'] == null ) return; // 레코드가 1미만인 경우

	var fieldnm = new Array( 'code', 'itemcd' ); // 필드명
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
				if( Obj[i].type != 'checkbox' ) csField[item] += Obj[i].value + ";"; else csField[item] += Obj[i].checked + ";";
			});

		}
	}

	fieldnm.each(function(item) {
		fs.allmodify.value += item + '==' + csField[item] + '||';
	});

	fmList.action = "indb?mode=allmodify";
	fmList.submit() ;
}
</SCRIPT>



<SCRIPT LANGUAGE=JavaScript>

/*-------------------------------------
 삭제
-------------------------------------*/
function act_delete(){
	console.log("@@삭제합니당@@")
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
			if ( fmList['confirmyn'][i].checked ) codes[ idx++ ] = fmList['confirmyn'][i].value;
		}
	}

	fmList.nolist.value = codes.join( ";" );
	fmList.action = "indb?mode=delete" ;
	fmList.submit() ;
}
</SCRIPT>

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


