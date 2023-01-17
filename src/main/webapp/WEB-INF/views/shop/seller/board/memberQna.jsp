<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>



<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

	<form name=frmList id=frmList class="frmList">
	<input type=hidden id=pageNo name="sellerMemberQnaVO.pageNo" value="${sellerBoardFM.sellerMemberQnaVO.pageNo}"/>
	<div class="title title_top">1:1 문의관리<span>1:1문의를 통해 들어온 고객문의를 관리할 수 있습니다</span> <!-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=board&no=6',870,800)"><img src="/resources/shop/admin/img/btn_q.gif" border=0 align=absmiddle hspace=2></a>--></div>
	<table class=tb>
	<col class=cellC><col class=cellL><col class=cellC><col class=cellL width=40%>
	<tr>
		<td>키워드검색전송</td>
		<td>
		<select name="sellerMemberQnaVO.skey">
		<option value="all" ${stringUtil:selected(sellerBoardFM.sellerMemberQnaVO.skey, "all")} > 통합검색 </option>
		<option value="subject" ${stringUtil:selected(sellerBoardFM.sellerMemberQnaVO.skey, "subject")}> 제목 </option>
		<option value="contents" ${stringUtil:selected(sellerBoardFM.sellerMemberQnaVO.skey, "contents")}> 문의 </option>
		<option value="m_id" ${stringUtil:selected(sellerBoardFM.sellerMemberQnaVO.skey, "m_id")}> 작성자 </option>
		</select> <input type="text" NAME="sellerMemberQnaVO.sword" value="${sellerBoardFM.sellerMemberQnaVO.sword != null ? sellerBoardFM.sellerMemberQnaVO.sword : '' }" class=line>
		</td>
	
	</tr>
	<tr>
		<td>등록일 </td>
		<td colspan="3">
		<input type=text name=sellerMemberQnaVO.sregdt value="${sellerBoardFM.sellerMemberQnaVO.sregdt_0 }" onclick="calendar(event)" class=line onkeydown="onlynumber()"> -
		<input type=text name=sellerMemberQnaVO.sregdt value="${sellerBoardFM.sellerMemberQnaVO.sregdt_1 }" onclick="calendar(event)" class=line onkeydown="onlynumber()">
		<a href="javascript:setDate('sellerMemberQnaVO.sregdt', ${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align=absmiddle></a>
		<a href="javascript:setDate('sellerMemberQnaVO.sregdt', ${dateUtil:getDateFormatFrom('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-7)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align=absmiddle></a>
		<a href="javascript:setDate('sellerMemberQnaVO.sregdt', ${dateUtil:getDateFormatFrom('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-15)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align=absmiddle></a>
		<a href="javascript:setDate('sellerMemberQnaVO.sregdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-1)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align=absmiddle></a>
		<a href="javascript:setDate('sellerMemberQnaVO.sregdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-2)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align=absmiddle></a>
		<a href="javascript:setDate('sellerMemberQnaVO.sregdt')"><img src="/resources/shop/admin/img/sicon_all.gif" align=absmiddle></a>
		</td>
	</tr>
	</table>
	<div class=button_top><input type=image src="/resources/shop/admin/img/btn_search2.gif"></div>
	
	<table width=100%>
	<tr>
		<td class=pageInfo><font class=ver8>
		<c:set var="pages" value="${(sellerBoardFM.sellerMemberQnaVO.rowCount*10) / (sellerBoardFM.sellerMemberQnaVO.pageSize*10)} " />
		<c:set var="pageCnt" value="${pages+(1-(pages%1))%1}" />
		<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
		총 <b>${sellerBoardFM.sellerMemberQnaVO.totalCnt }</b>개, 검색 <b>${sellerBoardFM.sellerMemberQnaVO.rowCount}</b>개, <b>${sellerBoardFM.sellerMemberQnaVO.pageNo }</b> of <b>${var3 }</b> Pages
		</td>
		<td align=right>
		<select name="sellerMemberQnaVO.sort" onchange="this.form.submit();">
		<option value="ask desc" ${stringUtil:selected(sellerBoardFM.sellerMemberQnaVO.sort, "ask desc")}>- 문답순 정렬↑</option>
		<option value="regdt desc" ${stringUtil:selected(sellerBoardFM.sellerMemberQnaVO.sort, "regdt desc")}>- 등록일 정렬↑</option>
		<option value="regdt asc" ${stringUtil:selected(sellerBoardFM.sellerMemberQnaVO.sort, "regdt asc")}>- 등록일 정렬↓</option>
	    <optgroup label="------------"></optgroup>
		<option value="subject desc" ${stringUtil:selected(sellerBoardFM.sellerMemberQnaVO.sort, "subject desc")}>- 제목 정렬↑</option>
		<option value="subject asc" ${stringUtil:selected(sellerBoardFM.sellerMemberQnaVO.sort, "subject asc")}>- 제목 정렬↓</option>
		</select>&nbsp;
		<select name=sellerMemberQnaVO.pageSize onchange="this.form.submit()">
			<option value="10" ${stringUtil:selected(sellerBoardFM.sellerMemberQnaVO.pageSize, "10")} >10개 출력
			<option value="20" ${stringUtil:selected(sellerBoardFM.sellerMemberQnaVO.pageSize, "20")} >20개 출력
			<option value="40" ${stringUtil:selected(sellerBoardFM.sellerMemberQnaVO.pageSize, "40")} >40개 출력
			<option value="60" ${stringUtil:selected(sellerBoardFM.sellerMemberQnaVO.pageSize, "60")} >60개 출력
			<option value="100" ${stringUtil:selected(sellerBoardFM.sellerMemberQnaVO.pageSize, "100")} >100개 출력
			</select>
		</td>
	</tr>
	</table>
	</form>
	
	<form method="post" action="" name="fmList">
	<table width=100% cellpadding=0 cellspacing=0>
	<tr><td class=rnd colspan=10></td></tr>
	<tr class=rndbg>
		<th width="60">번호</th>
		<th>제목</th>
		<th width="80">질문유형</th>
		<th width="80">작성자</th>
		<th width="80">작성일</th>
		<th width="50">답변</th>
		<th width="50">삭제</th>
	</tr>
	<tr><td class=rnd colspan=10></td></tr>
	</table>
	
	
		<c:forEach items="${sellerBoardFM.sellerMemberQnaVO.memQnaList }" var="list" varStatus="vnum">
		
			<c:if test="${list.sno == list.parent}">
				<div style="border-top-width:1; border-top-style:solid; border-top-color:#DCD8D6;">
				<table width=100% cellpadding=0 cellspacing=0 onclick="view_content(this, event);" class=hand>
				<tr><td height=4 colspan=10></td></tr> 
				<tr height=25 align="center" onmouseover=this.style.background="#F7F7F7" onmouseout=this.style.background="">
					<td width="60"><font class=ver8 color=616161>${(sellerBoardFM.sellerMemberQnaVO.rowCount - vnum.index) - ( sellerBoardFM.sellerMemberQnaVO.rowStart ) }</td>
					<td align="left" style="line-height:17px"><font color=333333>${list.subject}</font> <font class=ver8 color=FF6709>( ${list.recnt} )</font></td>
					<td width="80" align="center"><font class=small color=444444>${ list.itemcd != null && list.itemcd != '00' ? codeUtil:getCodeName("question", list.itemcd) : '' }</font></td>
					<td width="80"><span id="navig" name="navig" m_id='${list.mid}' m_no='${list.mno}'><font color=0074BA class=ver8><b>${list.name}</b></font></span></td>
					<td width="80"><font class=ver8 color=333333><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd"/></font></td>
					<td width="50"><a href="javascript:popupLayer('${ctx }/shop/seller/board/memberQnaRegister?sellerMemberQnaVO.mode=reply&sellerMemberQnaVO.sno=${list.sno}&popView=Y',630,600)">
						<img src="/resources/shop/admin/img/i_reply.gif"></a></td>
					<td width="50" class="noline"><input type=checkbox name=confirmyn value="${list.sno }"></td>
				</tr>
				<tr><td height=4 colspan=10></td></tr>
				</table>
				<div style="display:none;padding:5 10 10 63;">
	
				<c:if test="${ordno != 0 && ordcnt == 1 }">
					<div><font class=small color=444444>[주문번호 <a href="javascript:popup('../../order/popup.order?ordno=${list.ordno }',800,600)"><font class=ver81 color=0074BA>${list.ordno }</font></a><a href="javascript:popup('../../order/popup.order.jsp?ordno=1237515434764',800,600)"><img src="/resources/shop/admin/img/btn_vieworder.gif" border=0 align=absmiddle hspace=2></a>]</font></div>
				</c:if>
				<c:if test="${ordno == 0 && ordcnt == 0 }">
					<div></div>
				</c:if>
	
				<div><font color=484848>${webUtil:simpleEncode(list.contents)}</font></div>
				</div>
				</div>	
			</c:if>
			
			
			<c:if test="${list.sno != list.parent}">
				<div style="border-top-width:1; border-top-style:dotted; border-top-color:#DCD8D6;">
				<table width=100% cellpadding=0 cellspacing=0 onclick="view_content(this, event);" class=hand>
				<tr><td height=4 colspan=10></td></tr>
				<tr height=25 align="center" onmouseover=this.style.background="#F7F7F7" onmouseout=this.style.background="">
					<td width="60"><font class=ver8 color=616161>${sellerBoardFM.sellerMemberQnaVO.totalCnt - vnum.index}</td>
					<td align="left" style="line-height:17px"><img src="/resources/shop/admin/img/btn_reply.gif" border=0 align=absmiddle> <font color=333333>${webUtil:simpleEncode(list.subject)}</font></td>
					<td width="80" align="left"></td>
					<td width="80"><span id="navig" name="navig" mid='${list.mid}' mno='${list.mno}'><font style="color:#616161;" class=ver8>${list.name }</font></span></td>
					<td width="80"><font class=ver8 color=333333><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd"/></font></td>
					<!-- <td width="50"></td> -->
					<td width="50"><a href="javascript:popupLayer('${ctx }/shop/seller/board/memberQnaRegister?sellerMemberQnaVO.mode=modify&sellerMemberQnaVO.sno=${list.sno}&popView=Y',630,600)">
					<img src="/resources/shop/admin/img/i_edit.gif"></a></td>
					<td width="50" class="noline"><input type=checkbox name=confirmyn value="${list.sno }"></td>
				</tr>
				<tr><td height=4 colspan=10></td></tr>
				</table>
				<div style="display:none;padding:5 10 10 97;"><font color=484848>${webUtil:simpleEncode(list.contents)}</font></div>
				</div>
			</c:if>
		
		</c:forEach>
	
	<div style="border-bottom-width:1; border-bottom-style:solid; border-bottom-color:#DCD8D6;width:100%;height:1px;font-size:0px;"></div>
	<INPUT TYPE="hidden" style="width:300" NAME="nolist" >
	</form>

	<!-- 페이징  -->
	<div align=center class=pageNavi>
		<b><tags:paginator currentPageNo="${sellerBoardFM.sellerMemberQnaVO.pageNo}" rowCount="${sellerBoardFM.sellerMemberQnaVO.rowCount}" pageSize="${sellerBoardFM.sellerMemberQnaVO.pageSize}"  pageGroupSize="${sellerBoardFM.sellerMemberQnaVO.pageGroupSize}" /></b>
	</div>

	<div><input type="image" class="button_top" src="/resources/shop/admin/img/btn_allselect_s.gif" 	alt="전체선택" border="0" align='absmiddle' style="cursor:hand" 
			<c:if test="${fn:length(sellerBoardFM.sellerMemberQnaVO.memQnaList)!=0}">
				onclick="javascript:PubAllSordes( 'select', fmList['confirmyn'] );"
			</c:if>
			<c:if test="${fn:length(sellerBoardFM.sellerMemberQnaVO.memQnaList)!=0}">
				onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
			</c:if>
			>
	
	<input type="image" class="button_top" src="/resources/shop/admin/img/btn_allreselect_s.gif" alt="선택반전" border="0" align='absmiddle' style="cursor:hand"
			<c:if test="${fn:length(sellerBoardFM.sellerMemberQnaVO.memQnaList)!=0}">
				onclick="javascript:PubAllSordes( 'reflect', fmList['confirmyn'] );"
			</c:if>
			<c:if test="${fn:length(sellerBoardFM.sellerMemberQnaVO.memQnaList)!=0}">
				onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
			</c:if>
			>
	
	<input type="image" class="button_top" src="/resources/shop/admin/img/btn_alldeselect_s.gif" alt="선택해제" border="0" align='absmiddle' style="cursor:hand"
			<c:if test="${fn:length(sellerBoardFM.sellerMemberQnaVO.memQnaList)!=0}">
				onclick="javascript:PubAllSordes( 'deselect', fmList['confirmyn'] );"
			</c:if>
			<c:if test="${fn:length(sellerBoardFM.sellerMemberQnaVO.memQnaList)!=0}">
				onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
			</c:if>
			>
	<input type="image" class="button_top" src="/resources/shop/admin/img/btn_alldelet_s.gif" 	alt="선택삭제" border="0" align='absmiddle' style="cursor:hand"
			<c:if test="${fn:length(sellerBoardFM.sellerMemberQnaVO.memQnaList)!=0}">
				onclick="javaScript:act_delete();"
			</c:if>
			<c:if test="${fn:length(sellerBoardFM.sellerMemberQnaVO.memQnaList)!=0}">
				onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
			</c:if>
			>
	</div>

<div style="padding-top:15px"></div>

<div id=MSG01>
<table cellpadding=2 cellspacing=0 border=0 class=small_ex>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>문의제목을 클릭하면 글내용이 열리며, 다시 제목을 클릭하면 내용이 닫히게됩니다.</td></tr>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>작성자를 클릭하시면 회원정보와 함께 회원주문내역 등을 보실 수 있습니다.</td></tr>
</table>
</div>
<script>cssRound('MSG01')</script>



<script language="javascript">

function goPage(page){
	$('#pageNo').val(page);
	$('#frmList').submit();
}
var preContent;

function view_content(obj, e)
{
	if ( document.all && ( e.srcElement.tagName == 'A' || e.srcElement.tagName == 'IMG' || e.srcElement.tagName == 'INPUT' ) ) return;
	else if ( !document.all && ( e.target.tagName == 'A' || e.target.tagName == 'IMG' || e.srcElement.tagName == 'INPUT' ) ) return;

	var div = obj.parentNode;

	if ( document.all ) obj = div.childNodes[ 1 ]; else obj = div.childNodes[ 3 ]; // 모질라 경우 줄내림도 #text 임

	if (preContent && obj!=preContent){
		obj.style.display = "block";
		preContent.style.display = "none";
	}
	else if (preContent && obj==preContent) preContent.style.display = ( preContent.style.display == "none" ? "block" : "none" );
	else if (preContent == null ) obj.style.display = "block";

	preContent = obj;
}

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
			if ( fmList['confirmyn'][i].checked ) codes[ idx++ ] = fmList['confirmyn'][i].value;
		}
	}

	fmList.nolist.value = codes.join( ";" );
	fmList.action = "memberQnaIndb?sellerMemberQnaVO.mode=delete" ;
	fmList.submit() ;
}
</script>


<script>window.onload = function(){ UNM.inner();};</script>

<script>
linecss();
table_design_load();
</script>

</tr>
</table>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>
