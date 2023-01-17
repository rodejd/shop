
<%-- ================================================================================
* 공통 상단 include
* 공통 java class 지정 및 변수 설정
================================================================================ --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%-- ================================================================================
* 공통변수의 할당 부분
================================================================================ --%>

<c:set var="opt1Nm" value=""></c:set>
<c:set var="rtList" value="${linkVO.linkList}"></c:set>
<c:set var="b" value="false"></c:set>
<c:set var="vnum" value="${linkVO.vnum}"></c:set>
<c:set var="tmp" value=""></c:set>
<c:set var="strCategory" value="${linkVO.strCategory != null ? linkVO.strCategory : ''}"></c:set>
<c:set var="strCategorySub" value="${linkVO.strCategorySub != null ? linkVO.strCategorySub : ''}"></c:set>
<c:set var="indicate" value="${linkVO.indicate !=null ? linkVO.indicate : ''}"></c:set>
<c:set var="skey" value="${linkVO.skey !=null ? linkVO.skey : ''}"></c:set>
<c:set var="open" value="${linkVO.open !=null ? linkVO.open : ''}"></c:set>
<c:set var="sword" value="${linkVO.sword !=null ? linkVO.sword : ''}"></c:set>
<c:set var="i" value="0"></c:set>

<%-- ================================================================================
* 업무 시작 try 부분
================================================================================ --%>
<%-- ================================================================================
* 업무별 거래 로직
================================================================================ --%>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<!-- <script src="../prototype.js"></script> -->
<script language="javascript">
var selOption = null;

	/*
	 * php script
	 */
	function iciSelect(obj)
	{
		var row = obj.parentNode.parentNode;
		row.style.background = (obj.checked) ? "#F0F4FF" :"#FFFFFF";
	}

	function chkFormList(mode){
		$('#search').val($('#frmList').serialize());
		
		var fObj = document.forms['fmList'];
		if (inArray(mode, new Array('move','copyGoodses','unlink')) && fObj.strCategory.value == ''){
			if (mode == 'move') alert("분류이동는 분류로 검색했을 경우만 가능합니다.");
			else if (mode == 'copyGoodses') alert("상품복사는 분류로 검색했을 경우만 가능합니다.");
			else if (mode == 'unlink') alert("연결해제는 분류로 검색했을 경우만 가능합니다.");
			document.getElementsByName("cate")[0].focus();
			return;
		}
		if (isChked(document.getElementsByName('chk')) === false){
			if (document.getElementsByName('chk').length) document.getElementsByName('chk')[0].focus();
			return;
		}
		if (inArray(mode, new Array('link','move','copyGoodses')) && document.getElementsByName("cate")[0].value == ''){
			if (mode == 'link') alert("선택한 상품에 연결 할 분류를 선택해주세요.");
			else if (mode == 'move') alert("선택한 상품을 이동 할 분류를 선택해주세요.");
			else if (mode == 'copyGoodses') alert("선택한 상품을 복사 할 분류를 선택해주세요.");
			document.getElementsByName("cate")[0].focus();
			return;
		}

		var msg = '';
		if (mode == 'link') msg += '선택한 상품에 해당 분류를 연결하시겠습니까?';
		else if (mode == 'move') msg += '선택한 상품을 해당 분류로 이동하시겠습니까?';
		else if (mode == 'copyGoodses') msg += '선택한 상품을 해당 분류로 복사하시겠습니까?';
		else if (mode == 'unlink') msg += '선택한 상품의 분류를 해제하시겠습니까?';
		else if (mode == 'delGoodses') msg += '선택한 상품을 정말 삭제하시겠습니까?' + "\n\n" + '[주의] 삭제 후에는 복원이 안되므로 신중하게 삭제하시기 바랍니다.';
		if (!confirm(msg)) return;

		fObj.target = "_self";
		fObj.mode.value = mode;
		fObj.action = "link/indb";
		fObj.submit();
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
	
});
</script>

<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
		<div style="margin-top:0"><span style="color: red">※ 판매자의 상품등록/수정은 관리자에게 판매자에게 상품을 요청하면 관리자가 승인후 등록/수정이 가능합니다.<br></span></div>
		<div style="margin-top:0"><span style="color: red">하지만 상품 일괄관리 페이지는 마스터와 연결되어 있어 바로 수정이 가능합니다. 삭제해야할 페이지라고 판단됩니다.<br></span></div>
		
		<div class="title title_top">빠른 이동/복사/삭제<span>등록하신 상품을 편리하게 이동/복사/삭제 할 수 있습니다.</span> 
		<%//<a href="javascript:alert('준비중입니다..')"><!-- javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=product&no=15',870,800)--><img src="../img/btn_q.gif" border=0 align=absmiddle hspace=2></a>%></div>

<!-- 상품출력조건 : start -->
<form name=frmList id="frmList" onsubmit="return chkForm(this)">
<input type=hidden id=pageNo name="pageNo" value="1"/>
<input type="hidden" name="indicate" id="indicate" value="search"/>
<input type="hidden" name="unlink" id="unlink" value="${linkVO.unlink}"/>
 		<table class=tb>
			<col class=cellC><col class=cellL>
			<tr>
				<td>분류선택</td>
				<td>
					<script>new categoryBox('cate',4,'${strCategory}');</script>
					
					<!-- &nbsp;&nbsp;&nbsp;<a href="#" onclick="goPage2();"><img src="../img/btn_without_cate.gif" alt="미연결상품보기" align=absmiddle></a> -->
				</td>
			</tr>
			<tr>
				<td>검색어</td>
				<td>
					<select name=skey>
						<option value="a.goodsnm" selected>상품명
						<option value="a.goodsno" ${skey eq 'a.goodsno' ? 'selected' : ''}>고유번호
						<option value="a.goodscd" ${skey eq 'a.goodscd'? 'selected' :''}>상품코드
						<option value="a.keyword" ${skey eq 'a.keyword' ? 'selected' :''}>유사검색어
					</select>
					<input type=text name=sword class=lline value="${sword}" class="line">
				</td>
			</tr>
			<tr>
				<td>상품출력여부</td>
				<td class=noline>
					<input type=radio name=open value=""  ${open eq ''?'checked' :''}>전체
					<input type=radio name=open value="1" ${open eq '1'?'checked' :''}>출력상품
					<input type=radio name=open value="0" ${open eq '0'?'checked' :''}>미출력상품
				</td>
			</tr>
		</table>
		
		<div class="button_top">
			<span class="noline" style="margin-left:10px;"><input type=image src="/resources/shop/admin/img/btn_search_s.gif" align="absmiddle" onclick="goPage3();"></span>
		</div>

</form>
		<!-- 상품출력조건 : end -->

<form name="fmList" method="post" onsubmit="return false">
<input type=hidden name=mode>
<input type=hidden name=strCategory value="${strCategory}">
<input type="hidden" name="search" id="search" value="" />

		<div class="pageInfo ver8" style="margin-top:20px;">총 <b>${linkVO.totalCount}</b>개, 검색 <b>${linkVO.rowCount} 개, <b></b> ${linkVO.pageNo} </b> of <fmt:formatNumber value="${linkVO.rowCount/linkVO.pageSize}" pattern="0"></fmt:formatNumber> Pages</div>

		<table width=100% cellpadding=0 cellspacing=0 border=0>
			<col width=35>
			<col width=50>
			<col>
			<col width=150>
			<col width=150>
			<col width=150>
			<col width=150>
			<col width=110>
			<col width=80 span=2>
			<col width=55 span=2>
			<tr><td class=rnd colspan=12></td></tr>
			<tr class=rndbg>
				<th><a href="javascript:chkBox(document.getElementsByName('chk'),'rev')" class=white><font class=small1><b>선택</b></font></a></th>
				<th><font class=small1><b>번호</b></font></th>
				<th><font class=small1><b>상품명</b></font></th>
				<th><font class=small1><b>판매자</b></font></th>
				<th><font class=small1><b>등록일</b></font></th>
				<th><font class=small1><b>판매가</b></font></th>
				<th><font class=small1><b>재고</b></font></th>
				<th><font class=small1><b>진열</b></font></th>
			</tr>
			<tr><td class=rnd colspan=12></td></tr>
	<c:choose>
		<c:when test="${null != rtList and 0 < fn:length(rtList)}">
			<c:forEach items="${rtList}" var="list" varStatus="status">
					<c:set var="opt1Nm" value="${(list.opt1 !=null ? list.opt1 : '')}"></c:set>
					<c:choose>
						<c:when test="${tmp ne list.goodsno}">
							<c:set var="b" value="true"></c:set>
							<c:set var="tmp" value="${list.goodsno}"></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="b" value="false"></c:set>
						</c:otherwise>
					</c:choose>
		
				<tr><td height=4 colspan=12></td></tr>
				<tr>
					<td align=center class="noline"><input type=checkbox name=chk value="${list.goodsno}" onclick="iciSelect(this)"></td>
					<td align=center><font class="ver8" color="#616161">${(linkVO.rowCount - status.index) - ( linkVO.rowStart ) }</font></td>
				<c:choose>
					<c:when test="${list.link eq '1'}">
						<input type="hidden" name="category_${list.goodsno}" value="${list.category}"/>
						<td><a href="javascript:popup('${ctx }/shop/seller/goods/register?mode=modify&goodsno=${list.goodsno }&popView=Y',825,600);"><font class=small1 color=0074BA>${list.goodsnm !=null ? list.goodsnm :''}</font></a></td>
					</c:when>
					<c:otherwise>
						<td><font class=small1>${list.goodsnm}</font></td>
					</c:otherwise>
				</c:choose>
					<td align=center>${list.sellerNm}</td>
					<td align=center><font class="ver81" color="#444444"><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd HH:mm"/></font></td>
					<td align=right style="padding-right:10px" nowrap><font class="ver8" color="#444444"><b><fmt:formatNumber value="${list.price}" pattern="#,###"></fmt:formatNumber></b></font></td>
<%-- 					<td align=center><font class="ver81" color="#444444"><fmt:formatNumber value="${list.stock}" pattern="#,###"></fmt:formatNumber></font></td> --%>
					<td align=center><img src="/resources/shop/admin/img/icn_${list.open}.gif"></td>
				</tr>
				<tr><td height=4></td></tr>
				<tr><td colspan=12 class=rndline></td></tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan=10 align=center>조회내역이 없습니다.</td>
			</tr>	
			<tr><td colspan=10 class=rndline></td></tr>
		</c:otherwise>			
	</c:choose>
		</table>
		<br/>
		<a href="javascript:chkBox(document.getElementsByName('chk'),'rev')"><img src="/resources/shop/admin/img/btn_allchoice.gif"></a>

		<tags:paginator currentPageNo="${linkVO.pageNo}" rowCount="${linkVO.rowCount}" pageSize="${linkVO.pageSize}"  pageGroupSize="${linkVO.pageGroupSize}" />

		<table class=tb style="margin:30px 0;">
			<col class=cellC>
			<col class=cellL>
			<tr>
				<td>연결/이동/복사</td>
				<td>
					<div style="margin:5px 0">
					선택한 상품을 <script>new categoryBox('cateSub',4,'${strCategorySub}','','fmList');</script> 으로
					<a href="javascript:chkFormList('link')"><img src="/resources/shop/admin/img/btn_cate_connect.gif" align="absmiddle" alt="연결"></a>
					<a href="javascript:chkFormList('move')"><img src="/resources/shop/admin/img/btn_cate_move.gif" align="absmiddle" alt="이동"></a>
					<a href="javascript:chkFormList('copyGoodses')"><img src="/resources/shop/admin/img/btn_cate_copy.gif" align="absmiddle" alt="복사"></a>
					</div>
					<div style="margin:5px 0" class="noline">
					<input type="checkbox" name="isToday" value="Y" ${linkVO.isToday ne '' ? 'checked' :''}>해당 상품의 등록일을 현재 등록시간으로 변경합니다. <font class=extext>(복사의 경우에는 무조건 현재시간으로 변경됩니다)</font>
					</div>
				</td>
			</tr>
			<!-- <tr height=35>
				<td>분류해제</td>
				<td>선택한 상품의 분류(카테고리)를 <a href="javascript:chkFormList('unlink')"><img src="../img/btn_cate_unconnect.gif" align="absmiddle" alt="해제"></a> <font class=extext>(신중하게 진행하세요. 버튼클릭시 상품에 연결된 분류(카테고리)가 해제됩니다)</font></td>
				</tr> -->
			<tr height=35>
				<td>상품삭제</td>
				<td>선택한 상품을 <a href="javascript:chkFormList('delGoodses')"><img src="/resources/shop/admin/img/btn_cate_del.gif" align="absmiddle" alt="삭제"></a> <font class=extext>(신중하게 진행하세요. 버튼클릭시 선택한 상품들이 삭제됩니다. 삭제되면 복구되지 않습니다)</font></td>
			</tr>
		</table>

	</form>

		<!-- <div id=MSG01>
			<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
				<tr><td><img src="../img/icon_list.gif" align="absmiddle">분류연결 : 상품에 분류(카테고리)를 연결하는 기능입니다.(다중분류기능지원)</td></tr>
				<tr><td><img src="../img/icon_list.gif" align="absmiddle">분류이동 : 현재 연결된 분류에서 다른 분류로 이동하는 기능입니다.</td></tr>
				<tr><td><img src="../img/icon_list.gif" align="absmiddle">분류해제 : 현재 연결된 분류를 해제하는 기능입니다.</td></tr>
				<tr><td><img src="../img/icon_list.gif" align="absmiddle">상품복사 : 다른 분류로 똑같은 상품을 하나 더 복사(생성)하는 기능입니다.</td></tr>
				<tr><td><img src="../img/icon_list.gif" align="absmiddle">상품삭제 : 상품을 삭제하는 기능으로 삭제 후에는 복원이 안되므로 신중하게 삭제하시기 바랍니다.</td></tr>
				<tr><td><img src="../img/icon_list.gif" align="absmiddle">[주의] 위 상품검색시 상품에 연결된 하위분류까지 정확하게 선택한 후 검색하세요.</td></tr>
				<tr><td><img src="../img/icon_list.gif" align="absmiddle">[주의] 상품복사 경우 상품문의/상품후기는 복사되지 않습니다.</td></tr>
			</table>
		</div> -->
		<!-- <script>cssRound('MSG01')</script> -->
<script type="text/javascript">
function goPage3(){
	$("#unlink").val('');
	document.frmList.submit();
}
function goPage2(){
	$("#unlink").val('Y');
	document.frmList.submit();
}
function goPage(page){
	$("#pageNo").val(page);
	document.frmList.submit();
}
</script>
<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<%-- ================================================================================
* 공통 HTML CONTENT 종료
================================================================================ --%>

<%-- ================================================================================
=====================================================================================
* 화면 종료
=====================================================================================
================================================================================ --%>

<%-- ================================================================================
* 업무 시작 catch 부분
================================================================================ --%>
