<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp"%>
<%@ include file="../common/left.jsp"%>

<%@ page import="static com.wepinit.wepinit_shop.xmall.common.ShopLibFunction.*"%>

<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white"
	border="0">
	<tr>
		<td valign="top" style="padding-left: 12px">
<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>



<form name=frmList class="frmList">
	<input type="hidden" class="pageNo" name="pageNo" value="1" />
	<!-- 2014.05.26 수정 start:매뉴얼 링크 주석처리 -->
	<div class="title title_top">상품문의관리 <span>고객들이 질문한 상품문의를 살펴보실 수 있습니다 <!-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=board&no=4',870,800)"><img src="../img/btn_q.gif" border=0 align=absmiddle hspace=2></a> --></div>
	<!-- 2014.05.26 수정 end-->
	<table class=tb>
		<colgroup>
			<col class="cellC">
			<col class="cellL" style="width:540px">
			<col class="cellC">
			<col class="cellL" style="width:400px">
			<col class="cellC">
			<col class="cellL" style="width:380px">
		</colgroup>
		<tr>
			<td>판매사명</td>
			<td>
				<input type="text" name="schSellerNm" id="schSellerNm" value="${goodsQnaVO.schSellerNm}" class="line" style="height:22px" />
				<input type="button" value="판매사 조회" onclick="popupLayer('${ctx}/shop/admin/common/sellerSearchPopup', 600, 500);" />
			</td>
			<td>분류선택</td>
			<td colspan="3">
				<script>new categoryBox('cate',${shopLibFunction:cateStep()}, '${goodsQnaVO.cateVal}' ,'', 'frmList');</script>
			</td>
		</tr>
		<tr>
			<td>키워드검색전송</td>
			<td>
				<select name="skey">
				<option value="all" ${stringUtil:selected(goodsQnaVO.skey, "all")} > 통합검색 </option>
				<option value="subject" ${stringUtil:selected(goodsQnaVO.skey, "subject")}> 제목 </option>
				<option value="contents" ${stringUtil:selected(goodsQnaVO.skey, "contents")}> 문의 </option>
				<option value="mid" ${stringUtil:selected(goodsQnaVO.skey, "mid")}> 작성자 </option>
				<option value="goodnm" ${stringUtil:selected(goodsQnaVO.skey, "goodnm")}>상품명</option>
				</select> <input type="text" NAME="sword" value="${goodsQnaVO.sword != null ? goodsQnaVO.sword : '' }" class=line>
			</td>
			<td>노출스킨</td>
			<td>
				<select name="schSkin">
					<option value="">전체</option>
					${webUtil:makeSelectCodeItem((codeUtil:codeitem('skin')), goodsQnaVO.schSkin) }
				</select>
			</td>
			<td>답변여부</td>
			<td>
				<select name="schReply">
					<option value="">전체</option>
					<option value="Y"${stringUtil:selected(memQnaVO.schReply, "Y")}>답변</option>
					<option value="N"${stringUtil:selected(memQnaVO.schReply, "N")}>미답변</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>등록일</td>
			<td colspan="5">
				<input type=text name=sregdt value="${goodsQnaVO.sregdt_0 }" onclick="calendar(event)" class=line onkeydown="onlynumber()"> -
				<input type=text name=sregdt value="${goodsQnaVO.sregdt_1 }" onclick="calendar(event)" class=line onkeydown="onlynumber()">
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


<div style="padding-top:10px"></div>

<table width=100%>
	<tr>
		<td class=pageInfo><font class=ver8>
			<c:set var="pages" value="${(goodsQnaVO.rowCount*10) / (goodsQnaVO.pageSize*10)} " />
			<c:set var="pageCnt" value="${pages+(1-(pages%1))%1}" />
			<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
			총 <b>${goodsQnaVO.totalCount }</b>개, 검색 <b>${goodsQnaVO.rowCount}</b>개, <b>${goodsQnaVO.pageNo }</b> of <b>${var3 }</b> Pages
		</td>
		<td align=right>
			<select name="sort" onchange="this.form.submit();">
			<!-- <select name="sort" onchange="frmList.sort.value=this.value; frmList.submit();"> -->
				<option value="ask desc" ${stringUtil:selected(goodsQnaVO.sort, "ask desc")}>- 문답순 정렬↑</option>
				<option value="regdt desc" ${stringUtil:selected(goodsQnaVO.sort, "regdt desc")}>- 등록일 정렬↑</option>
				<option value="regdt asc" ${stringUtil:selected(goodsQnaVO.sort, "regdt asc")}>- 등록일 정렬↓</option>
			    <optgroup label="------------"></optgroup>
				<option value="subject desc" ${stringUtil:selected(goodsQnaVO.sort, "subject desc")}>- 제목 정렬↑</option>
				<option value="subject asc" ${stringUtil:selected(goodsQnaVO.sort, "subject asc")}>- 제목 정렬↓</option>
			</select>&nbsp;
			<select name=pageSize  onchange="frmList.pageSize.value=this.value; frmList.submit();">
				<option value="10" ${stringUtil:selected(goodsQnaVO.pageSize, "10")} >10개 출력
				<option value="20" ${stringUtil:selected(goodsQnaVO.pageSize, "20")} >20개 출력
				<option value="40" ${stringUtil:selected(goodsQnaVO.pageSize, "40")} >40개 출력
				<option value="60" ${stringUtil:selected(goodsQnaVO.pageSize, "60")} >60개 출력
				<option value="100" ${stringUtil:selected(goodsQnaVO.pageSize, "100")} >100개 출력
			</select>
		</td>
	</tr>
</table>
</form>

<form method="post" action="indb" name="fmList">
	<table width=100% cellpadding=0 cellspacing=0>
		<tr><td class=rnd colspan=9></td></tr>
		<tr class=rndbg>
			<th width="60">번호</th>
			<th width="70">이미지</th>
			<th>상품명/제목</th>
			<th width="50">노출스킨</th>
			<th width="100">판매사</th>
			<th width="180">작성자</th>
			<th width="80">작성일</th>
			<th width="50">답변</th>
			<th width="50">수정</th>
			<th width="50">삭제</th>
		</tr>
		<tr><td class=rnd colspan=9></td></tr>
	</table>
	
	<c:forEach items="${goodsQnaVO.goodsQnaList }" var="list" varStatus="vnum">
		<c:if test="${list.sno == list.parent }">
			<div style="border-top-width:1; border-top-style:solid; border-top-color:#DCD8D6;">
				<table width=100% cellpadding=0 cellspacing=0 onclick="view_content(this, event);" class=hand>
					<tr><td height=4 colspan=9></td></tr>
					<tr height=25 align="center" onmouseover="this.style.background='#F7F7F7'" onmouseout="this.style.background=''">
						<td width="60"><font class=ver8 color=616161>${(goodsQnaVO.rowCount - vnum.index) - ( goodsQnaVO.rowStart ) }</font></td>
						<td width="70"><a href="${ctx}/shop/goods/goods_view?goodsno=${list.goodsno }" target=_blank>
									<img src='${list.imgs}' width="40px" height="40px" style="border: 1px solid #efefef;" onerror="onErrorImg(this, 'noimg_100.gif', '${wskin }');" /></a></td>
						
						<td align="left" style="line-height:17px">
							<div style="color:#999999"><a href="javascript:popup('../../goods/register?viewFlg=view&mode=modify&goodsno=${list.goodsno}',825,600)" style="color:#0074BA;" class=small>[ ${list.goodsnm } ]</a></div>
							<font color=333333>${webUtil:simpleEncode(list.subject)}</font> 
							<font class=ver8 color=FF6709>(${list.recnt})</font>
						</td>
						<td width="80" align="center"><font class=small color=444444>${not empty list.skin ? codeUtil:getCodeName("skin", list.skin) : '' }</font></td>
						<td width="100" align="center">${list.sellerNm}</td>
						<td width="180">${list.mid != null ? list.mid : list.name }</td>
						<td width="80"><font class=ver8 color=333333><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd"/></font></td>
						<td width="50"><a href="javascript:popupLayer('../../board/goods_qna/register?mode=reply&sno=${list.sno}&goodsno=${list.goodsno}')"><img src="/resources/shop/admin/img/i_reply.gif"></a></td>
						<td width="50"><a href="javascript:popupLayer('../../board/goods_qna/register?mode=modify&sno=${list.sno}&goodsno=${list.goodsno}')"><img src="/resources/shop/admin/img/i_edit.gif"></a></td>
						<td width="50" class="noline"><input type=checkbox name=confirmyn value="${list.sno }"></td>
					</tr>
					<tr><td height=4 colspan=9></td></tr>
				</table>
				<div style="display:none;padding:5 10 10 130;"><font color=484848>${webUtil:simpleEncode(list.contents)}</font></div>
			</div>
		</c:if>
		
		<c:if test="${list.sno != list.parent }">
			<div style="border-top-width:1; border-top-style:dotted; border-top-color:#DCD8D6;">
				<table width=100% cellpadding=0 cellspacing=0 onclick="view_content(this, event);" class=hand>
					<tr><td height=4 colspan=9></td></tr>
					<tr height=25 align="center" onmouseover="this.style.background='#F7F7F7'" onmouseout="this.style.background=''">
						<td width="60"><font class=ver8 color=616161>${(goodsQnaVO.rowCount - vnum.index) - ( goodsQnaVO.rowStart ) }</td>
						<td width="70"><img src="/resources/shop/admin/img/btn_reply.gif"></td>
						<td align="left" style="line-height:17px"><font color=333333>${webUtil:simpleEncode(list.subject)}</font></td>
						<td width="100" align="center"></td>
						<td width="180">${list.name != null ? list.name : list.mid }</td>
						<td width="80"><font class=ver8 color=333333><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd"/></font></td>
						<td width="50"></td>
						<td width="50"><a href="javascript:popupLayer('../../board/goods_qna/register?mode=modify&sno=${list.sno}')"><img src="/resources/shop/admin/img/i_edit.gif"></a></td>
						<td width="50" class="noline"><input type=checkbox name=confirmyn value="${list.sno}"></td>
					</tr>
					<tr><td height=4 colspan=9></td></tr>
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
<tags:paginator currentPageNo="${goodsQnaVO.pageNo}" rowCount="${goodsQnaVO.rowCount}" pageSize="${goodsQnaVO.pageSize}"  pageGroupSize="${goodsQnaVO.pageGroupSize}" />
</b></font></div>

<div><input type="image" class="button_top" src="/resources/shop/admin/img/btn_allselect_s.gif" 	alt="전체선택" border="0" align='absmiddle' style="cursor:hand" 
		<c:if test="${fn:length(goodsQnaVO.goodsQnaList)!=0}">
			onclick="javascript:PubAllSordes( 'select', fmList['confirmyn'] );"
		</c:if>
		<c:if test="${fn:length(goodsQnaVO.goodsQnaList)!=0}">
			onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
		</c:if>
		>

<input type="image" class="button_top" src="/resources/shop/admin/img/btn_allreselect_s.gif" alt="선택반전" border="0" align='absmiddle' style="cursor:hand"
		<c:if test="${fn:length(goodsQnaVO.goodsQnaList)!=0}">
			onclick="javascript:PubAllSordes( 'reflect', fmList['confirmyn'] );"
		</c:if>
		<c:if test="${fn:length(goodsQnaVO.goodsQnaList)!=0}">
			onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
		</c:if>
		>

<input type="image" class="button_top" src="/resources/shop/admin/img/btn_alldeselect_s.gif" alt="선택해제" border="0" align='absmiddle' style="cursor:hand"
		<c:if test="${fn:length(goodsQnaVO.goodsQnaList)!=0}">
			onclick="javascript:PubAllSordes( 'deselect', fmList['confirmyn'] );"
		</c:if>
		<c:if test="${fn:length(goodsQnaVO.goodsQnaList)!=0}">
			onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
		</c:if>
		>
<input type="image" class="button_top" src="/resources/shop/admin/img/btn_alldelet_s.gif" 	alt="선택삭제" border="0" align='absmiddle' style="cursor:hand"
		<c:if test="${fn:length(goodsQnaVO.goodsQnaList)!=0}">
			onclick="javaScript:act_delete();"
		</c:if>
		<c:if test="${fn:length(goodsQnaVO.goodsQnaList)!=0}">
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
	$('.pageNo').val(page);
	$('.frmList').submit();
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
	fmList.action = "../../board/goods_qna/indb?mode=delete" ;
	fmList.submit() ;
}
</SCRIPT>

<script>window.onload = function(){ UNM.inner();};</script>

<script>
linecss();
table_design_load();
</script>



<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

			<%@ include file="../common/bottom.jsp"%>
		</td>
	</tr>
</table>