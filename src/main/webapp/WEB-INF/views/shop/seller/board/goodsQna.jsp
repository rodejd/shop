<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<form name=frmList id=frmList class="frmList">
	<input type=hidden id=pageNo name="sellerGoodsQnaVO.pageNo" value="${sellerBoardFM.sellerGoodsQnaVO.pageNo}"/>
	<!-- 2014.05.26 수정 start:매뉴얼 링크 주석처리 -->
	<div class="title title_top">상품문의관리 <span>고객들이 질문한 상품문의를 살펴보실 수 있습니다 <!-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=board&no=4',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle hspace=2></a> --></div>
	<!-- 2014.05.26 수정 end-->
	<table class=tb>
		<col class=cellC><col class=cellL>
		<tr>
			<td>분류선택</td>
			<td> <script>new categoryBox('sellerGoodsQnaVO.cate',${shopLibFunction:cateStep()}, '${sellerBoardFM.sellerGoodsQnaVO.cateVal}' ,'', 'frmList');</script></td>
		</tr>
		<tr>
	<td>키워드검색전송</td>
	<td>
	<select name="sellerGoodsQnaVO.skey">
	<option value="all" ${stringUtil:selected(sellerBoardFM.sellerGoodsQnaVO.skey, "all")} > 통합검색 </option>
	<option value="subject" ${stringUtil:selected(sellerBoardFM.sellerGoodsQnaVO.skey, "subject")}> 제목 </option>
	<option value="contents" ${stringUtil:selected(sellerBoardFM.sellerGoodsQnaVO.skey, "contents")}> 문의 </option>
	<option value="mid" ${stringUtil:selected(sellerBoardFM.sellerGoodsQnaVO.skey, "mid")}> 작성자 </option>
	<option value="goodnm" ${stringUtil:selected(sellerBoardFM.sellerGoodsQnaVO.skey, "goodnm")}>상품명</option>
	</select> <input type="text" NAME="sellerGoodsQnaVO.sword" value="${sellerBoardFM.sellerGoodsQnaVO.sword != null ? sellerBoardFM.sellerGoodsQnaVO.sword : '' }" class=line>
	</td>
		</tr>
		<tr>
			<td>등록일</td>
			<td colspan="3">
			<input type=text name=sellerGoodsQnaVO.sregdt value="${sellerBoardFM.sellerGoodsQnaVO.sregdt_0 }" onclick="calendar(event)" class=line onkeydown="onlynumber()"> -
			<input type=text name=sellerGoodsQnaVO.sregdt value="${sellerBoardFM.sellerGoodsQnaVO.sregdt_1 }" onclick="calendar(event)" class=line onkeydown="onlynumber()">
			<a href="javascript:setDate('sellerGoodsQnaVO.sregdt', ${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align=absmiddle></a>
			<a href="javascript:setDate('sellerGoodsQnaVO.sregdt', ${dateUtil:getDateFormatFrom('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-7)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align=absmiddle></a>
			<a href="javascript:setDate('sellerGoodsQnaVO.sregdt', ${dateUtil:getDateFormatFrom('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-15)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align=absmiddle></a>
			<a href="javascript:setDate('sellerGoodsQnaVO.sregdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-1)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align=absmiddle></a>
			<a href="javascript:setDate('sellerGoodsQnaVO.sregdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'),-2)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align=absmiddle></a>
			<a href="javascript:setDate('sellerGoodsQnaVO.sregdt')"><img src="/resources/shop/admin/img/sicon_all.gif" align=absmiddle></a>
			</td>
</tr>
	</table>
	<div class=button_top><input type=image src="/resources/shop/admin/img/btn_search2.gif"></div>


<div style="padding-top:10px"></div>

<%-- <form method="post" action="../../board/goods_qna_indb.jsp?mode=set" name="fmSet">
	<!-- 2014.05.26 수정 start:매뉴얼 링크 주석처리 -->
	<div class="title title_top" style="display:none;">상품문의게시판 설정 <span>리스트 갯수 제한 및 글쓰기에 대한 권한을 설정하실 수 있습니다</span> <!-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=board&no=4',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle hspace=2></a> --></div>
	<!-- 2014.05.26 수정 end-->
	<table class=tb style="display:none;">
		<col class=cellC><col class=cellL>
		<tr style="display:none;">
			<td>리스트 갯수</td>
			<td><input type="text" name="qnaListCnt" value='<%=qnaListCnt%>' size="6" class="rline" onkeydown="onlynumber();" /> 개</td>
		</tr>
		<tr style="display:none;">
			<td>글쓰기 권한</td>
			<td class=noline>
				<input type="radio" name="qnaWriteAuth" value="" <%=StringUtil.checked(cfg("qnaWriteAuth"),"") %> > 회원만 가능&nbsp;&nbsp;
				<input type="radio" name="qnaWriteAuth" value="free" <%=StringUtil.checked(cfg("qnaWriteAuth"),"free") %>> 비회원도 가능
			</td>
		</tr>
	</table>
	<div class=button_top style="display:none;"><input type=image src="../img/btn_save3.gif"></div>
</form> --%>

<div style="padding-top:5px"></div>

<table width=100%>
	<tr>
		<td class=pageInfo><font class=ver8>
			<c:set var="pages" value="${(sellerBoardFM.sellerGoodsQnaVO.rowCount*10) / (sellerBoardFM.sellerGoodsQnaVO.pageSize*10)} " />
			<c:set var="pageCnt" value="${pages+(1-(pages%1))%1}" />
			<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
			총 <b>${sellerBoardFM.sellerGoodsQnaVO.totalCount }</b>개, 검색 <b>${sellerBoardFM.sellerGoodsQnaVO.rowCount}</b>개, <b>${sellerBoardFM.sellerGoodsQnaVO.pageNo }</b> of <b>${var3 }</b> Pages
		</td>
		<td align=right>
			<select name="sellerGoodsQnaVO.sort" onchange="this.form.submit();">
			<!-- <select name="sort" onchange="frmList.sort.value=this.value; frmList.submit();"> -->
				<option value="ask desc" ${stringUtil:selected(sellerBoardFM.sellerGoodsQnaVO.sort, "ask desc")}>- 문답순 정렬↑</option>
				<option value="regdt desc" ${stringUtil:selected(sellerBoardFM.sellerGoodsQnaVO.sort, "regdt desc")}>- 등록일 정렬↑</option>
				<option value="regdt asc" ${stringUtil:selected(sellerBoardFM.sellerGoodsQnaVO.sort, "regdt asc")}>- 등록일 정렬↓</option>
			    <optgroup label="------------"></optgroup>
				<option value="subject desc" ${stringUtil:selected(goodsQnaVO.sort, "subject desc")}>- 제목 정렬↑</option>
				<option value="subject asc" ${stringUtil:selected(goodsQnaVO.sort, "subject asc")}>- 제목 정렬↓</option>
			</select>&nbsp;
			<select name=sellerGoodsQnaVO.pageSize  onchange="frmList.submit();">
				<option value="10" ${stringUtil:selected(sellerBoardFM.sellerGoodsQnaVO.pageSize, "10")} >10개 출력
				<option value="20" ${stringUtil:selected(sellerBoardFM.sellerGoodsQnaVO.pageSize, "20")} >20개 출력
				<option value="40" ${stringUtil:selected(sellerBoardFM.sellerGoodsQnaVO.pageSize, "40")} >40개 출력
				<option value="60" ${stringUtil:selected(sellerBoardFM.sellerGoodsQnaVO.pageSize, "60")} >60개 출력
				<option value="100" ${stringUtil:selected(sellerBoardFM.sellerGoodsQnaVO.pageSize, "100")} >100개 출력
			</select>
		</td>
	</tr>
</table>
</form>

<form method="post" action="indb" name="fmList">
	<table width=100% cellpadding=0 cellspacing=0>
		<tr><td class=rnd colspan=10></td></tr>
		<tr class=rndbg>
			<th width="60">번호</th>
			<th width="70">이미지</th>
			<th>상품명/제목</th>
			<th width="80">작성자</th>
			<th width="80">작성일</th>
			<th width="50">답변</th>
			<th width="50">수정</th>
			<th width="50">삭제</th>
		</tr>
		<tr><td class=rnd colspan=10></td></tr>
	</table>
	
	<c:forEach items="${sellerBoardFM.sellerGoodsQnaVO.goodsQnaList }" var="list" varStatus="vnum">
		<c:if test="${list.sno == list.parent }">
			<div style="border-top-width:1; border-top-style:solid; border-top-color:#DCD8D6;">
				<table width=100% cellpadding=0 cellspacing=0 onclick="view_content(this, event);" class=hand>
					<tr><td height=4 colspan=10></td></tr>
					<tr height=25 align="center" onmouseover="this.style.background='#F7F7F7'" onmouseout="this.style.background=''">
						<td width="60"><font class=ver8 color=616161>${(sellerBoardFM.sellerGoodsQnaVO.rowCount - vnum.index) - ( sellerBoardFM.sellerGoodsQnaVO.rowStart ) }</font></td>
						<td width="70"><a href="${ctx }/shop/goods/goods_view?goodsno=${list.goodsno }" target=_blank>
									<img src='/resources/shop/data/upload/goods/${fn:substringAfter(list.imgs, "|")}' width="40px" height="40px" style="border: 1px solid #efefef;" onerror="onErrorImg(this, 'noimg_100.gif', '${wskin }');" /></a></td>
						
						<td align="left" style="line-height:17px">
							<div style="color:#999999"><a href="javascript:popup('${ctx }/shop/seller/goods/register?popView=Y&goodsno=${list.goodsno}',825,600)" style="color:#0074BA;" class=small>[ ${list.goodsnm } ]</a></div>
							<font color=333333>${webUtil:simpleEncode(list.subject)}</font> 
							<font class=ver8 color=FF6709>(${list.recnt})</font>
						</td>
						<td width="80">${list.mid != null ? list.mid : list.name }</td>
						<td width="80"><font class=ver8 color=333333><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd"/></font></td>
						<td width="50"><a href="javascript:popupLayer('goodsQnaRegister?sellerGoodsQnaVO.mode=reply&sellerGoodsQnaVO.sno=${list.sno}&sellerGoodsQnaVO.goodsno=${list.goodsno}&popView=Y',630,600)"><img src="/resources/shop/admin/img/i_reply.gif"></a></td>
						<td width="50">
<%-- 						<a href="javascript:popupLayer('../../board/goods_qna/register?mode=modify&sno=${list.sno}&goodsno=${list.goodsno}')"><img src="/resources/shop/admin/img/i_edit.gif"></a> --%>
						</td>
						<td width="50" class="noline"><input type=checkbox name=confirmyn value="${list.sno }"></td>
					</tr>
					<tr><td height=4 colspan=10></td></tr>
				</table>
				<div style="display:none;padding:5 10 10 130;"><font color=484848>${webUtil:simpleEncode(list.contents)}</font></div>
			</div>
		</c:if>
		
		<c:if test="${list.sno != list.parent }">
			<div style="border-top-width:1; border-top-style:dotted; border-top-color:#DCD8D6;">
				<table width=100% cellpadding=0 cellspacing=0 onclick="view_content(this, event);" class=hand>
					<tr><td height=4 colspan=10></td></tr>
					<tr height=25 align="center" onmouseover="this.style.background='#F7F7F7'" onmouseout="this.style.background=''">
						<td width="60"><font class=ver8 color=616161>${(sellerBoardFM.sellerGoodsQnaVO.rowCount - vnum.index) - ( sellerBoardFM.sellerGoodsQnaVO.rowStart ) }</td>
						<td width="70"><img src="../../img/btn_reply.gif"></td>
						<td align="left" style="line-height:17px"><font color=333333>${webUtil:simpleEncode(list.subject)}</font></td>
						<td width="80">${list.mid != null ? list.mid : list.name }</td>
						<td width="80"><font class=ver8 color=333333><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd"/></font></td>
						<td width="50"></td>
						<td width="50">
							<c:if test="${sellerBoardFM.sellerNm == list.name}">
								<a href="javascript:popupLayer('goodsQnaRegister?sellerGoodsQnaVO.mode=modify&sellerGoodsQnaVO.sno=${list.sno}&popView=Y',630,600)"><img src="/resources/shop/admin/img/i_edit.gif"></a>
							</c:if>	
						</td>
						<td width="50" class="noline"><input type=checkbox name=confirmyn value="${list.sno}"></td>
					</tr>
					<tr><td height=4 colspan=10></td></tr>
				</table>
				<div style="display:none;padding:5 10 10 130;"><font color=484848>${webUtil:simpleEncode(list.contents)}</font></div>
			</div>
		</c:if>
	</c:forEach>
	
	<div style="border-bottom-width:1; border-bottom-style:solid; border-bottom-color:#DCD8D6;width:100%;height:1px;font-size:0px;"></div>
	<INPUT TYPE="hidden" style="width:300" NAME="nolist">
</form>

<!-- 페이징 -->
<div align=center class=pageNavi><font class=ver8><b>
<tags:paginator currentPageNo="${sellerBoardFM.sellerGoodsQnaVO.pageNo}" rowCount="${sellerBoardFM.sellerGoodsQnaVO.rowCount}" pageSize="${sellerBoardFM.sellerGoodsQnaVO.pageSize}"  pageGroupSize="${sellerBoardFM.sellerGoodsQnaVO.pageGroupSize}" />
</b></font></div>

<div><input type="image" class="button_top" src="/resources/shop/admin/img/btn_allselect_s.gif" 	alt="전체선택" border="0" align='absmiddle' style="cursor:hand" 
		<c:if test="${fn:length(sellerBoardFM.sellerGoodsQnaVO.goodsQnaList)!=0}">
			onclick="javascript:PubAllSordes( 'select', fmList['confirmyn'] );"
		</c:if>
		<c:if test="${fn:length(sellerBoardFM.sellerGoodsQnaVO.goodsQnaList)!=0}">
			onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
		</c:if>
		>

<input type="image" class="button_top" src="/resources/shop/admin/img/btn_allreselect_s.gif" alt="선택반전" border="0" align='absmiddle' style="cursor:hand"
		<c:if test="${fn:length(sellerBoardFM.sellerGoodsQnaVO.goodsQnaList)!=0}">
			onclick="javascript:PubAllSordes( 'reflect', fmList['confirmyn'] );"
		</c:if>
		<c:if test="${fn:length(sellerBoardFM.sellerGoodsQnaVO.goodsQnaList)!=0}">
			onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
		</c:if>
		>

<input type="image" class="button_top" src="/resources/shop/admin/img/btn_alldeselect_s.gif" alt="선택해제" border="0" align='absmiddle' style="cursor:hand"
		<c:if test="${fn:length(sellerBoardFM.sellerGoodsQnaVO.goodsQnaList)!=0}">
			onclick="javascript:PubAllSordes( 'deselect', fmList['confirmyn'] );"
		</c:if>
		<c:if test="${fn:length(sellerBoardFM.sellerGoodsQnaVO.goodsQnaList)!=0}">
			onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
		</c:if>
		>
<input type="image" class="button_top" src="/resources/shop/admin/img/btn_alldelet_s.gif" 	alt="선택삭제" border="0" align='absmiddle' style="cursor:hand"
		<c:if test="${fn:length(sellerBoardFM.sellerGoodsQnaVO.goodsQnaList)!=0}">
			onclick="javaScript:act_delete();"
		</c:if>
		<c:if test="${fn:length(sellerBoardFM.sellerGoodsQnaVO.goodsQnaList)!=0}">
			onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
		</c:if>
		>
</div>

<div style="padding-top:15px"></div>

<div id=MSG01>
	<table cellpadding=2 cellspacing=0 border=0 class=small_ex>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>문의제목을 클릭하면 글내용이 열리며, 다시 제목을 클릭하면 내용이 닫히게됩니다.</td></tr>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>상품이미지를 클릭</font>하면 새창과 함께 상품상세페이지로 이동합니다.</td></tr>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align=absmiddle>작성자를 클릭하시면 회원정보와 함께 회원주문내역 등을 보실 수 있습니다.</td></tr>
	</table>
</div>

<script language="javascript">
cssRound('MSG01');

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
</script>



<SCRIPT LANGUAGE=JavaScript>

function goPage(page){
	$('#pageNo').val(page);
	$('#frmList').submit();
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
			if ( fmList['confirmyn'][i].checked ) codes[ idx++ ] = fmList['confirmyn'][i].value;
		}
	}

	fmList.nolist.value = codes.join( ";" );
	fmList.action = "../../seller/board/goodsQnaIndb?sellerGoodsQnaVO.mode=delete" ;
	fmList.submit() ;
}
</SCRIPT>

<script>window.onload = function(){ UNM.inner();};</script>

<script>
linecss();
table_design_load();
</script>
