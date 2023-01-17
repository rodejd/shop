<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>
<%-- ================================================================================
* 공통변수의 할당 부분
================================================================================ --%>
<%-- ================================================================================
* 업무 시작 try 부분
================================================================================ --%>
<%-- ================================================================================
* 업무별 거래 로직
================================================================================ --%>

<c:set var="opt1Nm" value=""></c:set>
<c:set var="rtList" value="${reserveVO.reserveList}"></c:set>
<c:set var="vnum" value="${reserveVO.vnum}"></c:set>
<c:set var="tmp" value=""></c:set>
<c:set var="opt1Nm" value=""></c:set>
<c:set var="strCategory" value="${reserveVO.strCategory != null ? reserveVO.strCategory : ''}"></c:set>
<c:set var="indicate" value="${reserveVO.indicate !=null ? reserveVO.indicate : ''}"></c:set>
<c:set var="skey" value="${reserveVO.skey !=null ? reserveVO.skey : ''}"></c:set>
<c:set var="open" value="${reserveVO.open !=null ? reserveVO.open : ''}"></c:set>
<c:set var="smain" value="${reserveVO.smain !=null ? reserveVO.smain : ''}"></c:set>
<c:set var="sevent" value="${reserveVO.sevent !=null ? reserveVO.sevent : ''}"></c:set>
<c:set var="list" value="${reserveVO.goodsTypeList !=null ? reserveVO.goodsTypeList : ''}"></c:set>
<c:set var="i" value="0"></c:set>

<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<!-- <script src="../prototype.js"></script> -->
<script language=javascript src="/resources/shop/admin/common.js"></script>
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

function chkFormList(fObj){
	if (chkForm(fObj) === false) return false;
	if (fObj['method'][0].checked === true){
		if (chkText(fObj['reserve'],fObj['reserve'].value,'') === false) return false;
		if (chkPatten(fObj['reserve'],'regNum') === false) return false;
	}
	//if (fObj['isall'].checked === false && isChked(document.getElementsByName('chk')) === false){
	if (isChked(document.getElementsByName('chk')) === false){
		if (document.getElementsByName('chk').length) document.getElementsByName('chk')[0].focus();
		return false;
	}

	var msg = '';
	if (fObj['method'][0].checked === true){
		msg += '적립금을 일괄 ' + fObj['reserve'].value + '원으로 수정하시겠습니까?';
	}
	else {
		msg += '적립금을 판매가의 ' + fObj['percent'].value;
		msg += '%를 ' + fObj['roundunit'].value;
		msg += '유로 단위로 ' + fObj['roundtype'].options[fObj['roundtype'].selectedIndex].text;
		msg += '하여 일괄적으로 수정하시겠습니까?';
	}
	msg += "\n\n" + '[주의] 일괄적용 후에는 이전상태로 복원이 안되므로 신중하게 변경하시기 바랍니다.';
	if (!confirm(msg)) return false;

	fObj.target = "_self";
	fObj.mode.value = "reserve";
	fObj.action = "reserve/indb";
	return true;
}

/*** 이벤트목록 요청 ***/
function getEventList(sobj, selValue)
{

	if (sobj.options[sobj.selectedIndex].getAttribute("call") == null) return;
	function setcallopt(idx, text, value, defaultSelected, selected, call){
		if (idx == 0) for (i = sobj.options.length; i > 0; i--) sobj.remove(i);
		sobj.options[idx] = new Option(text, value, defaultSelected, selected);
		if (call != null) sobj.options[idx].setAttribute('call', call);
	}
	//페이징 처리 
	/* var ajax = new Ajax.Request( "../goods/reserve/indb", 
		{
			method: "post",
			parameters: "mode=getEvent&page=" + sobj.options[sobj.selectedIndex].getAttribute("call") + "&selValue=" + (selValue != null ? selValue : ''),
			onLoading: function (){ setcallopt(0, '== 로 딩 중 ... ==', ''); },
			onComplete: function ()
			{
				var req = ajax.transport;
				if ( req.status == 200 )
				{
					var jsonData = jQuery.parseJSON(req.responseText);
					var lists = jsonData.lists;
					var page = jsonData.page;
					var idx = 0;
					
					if (page.prev != null) setcallopt(idx++, '☞ 처음목록보기', '', false, false, '1');
					if (page.prev != null) setcallopt(idx++, '☞ 이전목록보기', '', false, false, page.prev);
					if (lists.length == 0) setcallopt(idx++, '== 이벤트가 없습니다 ==', '', false, false);
					for (i = 0; i < lists.length; i++){
						if (i == 0 || (selValue != null && selValue == lists[i].sno)) selected = true; else selected = false;
						setcallopt(idx++, '[' + lists[i].sdate + ' ~ ' + lists[i].edate + '] ' + lists[i].subject, lists[i].sno, false, selected);
					}
					if (page.next != null) setcallopt(idx++, '☞ 다음목록보기', '', false, false, page.next);
					sobj.form['seventpage'].value = page.now;
				}
				else {
					setcallopt(0, '☞ 로딩 시작하기', '', false, false, '1');
					setcallopt(1, '[로딩실패] 재로딩하세요.', '', true, true);
				}
			}
		} 
	); */
}
//window.onload = function (){ getEventList(frmList.sevent, '${sevent}'); }
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
// 	getEventList(frmList.sevent, '${sevent}');
});

/* 2017-08-30 추가 - 페이징 유지 */
function makeSearchParam(){
	$('#search').val($('#frmList').serialize());
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
		
<div class="title title_top">빠른 적립금수정<span>상품적립금을 빠르게 일괄수정할 수 있습니다.</span> 
	<%//<a href="javascript:alert('준비중입니다..')"><!-- javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=product&no=14',870,800) --><img src="../img/btn_q.gif" border=0 align=absmiddle hspace=2></a>%>
</div>

<!-- 상품출력조건 : start -->
<form name="frmList" id="frmList" onsubmit="return chkForm(this)">
	<input type=hidden id=pageNo name="pageNo" value="1"/>
	<div style="padding:10 0 5 5">
		<font class="def1" color="#000000"><b><font size="3">①</font> 먼저 아래에서 적립금수정할 상품을 검색합니다. 
		<font class="extext">(아래 3가지 방식중 한가지를 선택하여 검색)</font></b></font>
	</div>
	<div class="noline" style="padding:0 0 5 20">
		<input type="radio" name="indicate" value="search"  required label="상품검색조건" checked><b>전체상품 검색</b> <font class="extext">(전체상품을 대상으로 검색합니다. 특정 분류의 상품을 모두 보려면 분류 선택후 상품명을 공란으로 두고 검색)</font>
	</div>
	<table class="tb">
		<col class="cellC"><col class="cellL">
		<tr>
			<td>판매사명</td>
			<td>
				<input type="text" name="schSellerNm" id="schSellerNm" value="${reserveVO.schSellerNm}" class="line" style="height:22px" />
				<input type="button" value="판매사 조회" onclick="popupLayer('${ctx}/shop/admin/common/sellerSearchPopup', 600, 500);" />
			</td>
		</tr>
		<tr>
			<td><font class="small1">분류선택</font></td>
			<td><script>new categoryBox('cate',4,'${strCategory}');</script></td>
		</tr>
		<tr>
			<td><font class="small1">검색어</font></td>
			<td>
				<select name="skey">
					<option value="a.goodsnm" selected>상품명
					<option value="a.goodsno" ${reserveVO.skey eq 'a.goodsno' ? 'selected' :''}>고유번호
					<option value="a.goodscd" ${skey eq 'a.goodscd'? 'selected' :''}>상품코드
						<option value="a.keyword" ${skey eq 'a.keyword'? 'selected' :''}>유사검색어
				</select>
					<input type="text" name="sword" class="lline" value="${reserveVO.sword}" class="line">
			</td>
		</tr>
		<tr>
			<td><font class="small1">상품출력여부</font></td>
			<td class="noline">
				<font class="small1" color="555555">
				<input type="radio" name="open" value=""  ${open eq '' ? 'checked' :''}>전체
						<input type="radio" name="open" value="1" ${open eq '1' ? 'checked' :''}>출력상품
						<input type="radio" name="open" value="0" ${open eq '0' ? 'checked' :''}>미출력상품
				</font>
			</td>
		</tr>
	</table>

	<div class="noline" style="padding:15 0 5 20;display:none">
	<input type="radio" name="indicate" value="main" required label="상품검색조건" ${indicate eq 'main' ?'checked':''}><b>메인상품</b>
		<font class="extext">(메인페이지에 진열된 모든 상품을 출력합니다)</font>
	</div>
	<div style="display:none">
	<table class="tb">
		<col class="cellC"><col class="cellL">
		<tr>
			<td><font class="small1">상품진열영역</font></td>
			<td>
				<select name="smain">
					<c:if test="${list != null and list ne '' }">
						<c:forEach items="${list}" var ="pList" varStatus="status">
							<fmt:parseNumber value="${smain eq '' ? '0': smain}" var ="smain"></fmt:parseNumber>
							<option value="${status.index}" ${(status.index == smain )? 'selected' :''}> ${pList.title}
						</c:forEach>
					</c:if>
				</select>
			</td>
		</tr>
	</table>
	</div>
	<div class="noline" style="padding:15 0 5 20;display:none">
		<input type="radio" name="indicate" value="event"  required label="상품검색조건"${indicate eq 'event' ? 'checked':''}><b>이벤트상품</b> <font class="extext">(이벤트상품으로 선정해놓은 상품들을 출력합니다)</font>
	<table class="tb">
		<col class="cellC"><col class="cellL">
		<tr>
			<td><font class="small1">이벤트</font></td>
			<td>
				<select name="sevent" onchange="getEventList(this)">
					<option value="" call="${seventpage}">☞ 로딩 시작하기</option>
				</select>
				<input type="hidden" name="seventpage">
			</td>
		</tr>
	</table>
	</div>
	<div class="button_top"><input type=image src="/resources/shop/admin/img/btn_search2.gif"></div>
</form>
		<!-- 상품출력조건 : end -->

<form name="fmList" method="post" onsubmit="return chkFormList(this)">
<input type="hidden" name="mode" value="reserve">
<input type="hidden" name="search" id="search" value="" />
<!-- <input type=hidden name=query value="" required msgR="일괄관리 할 상품을 먼저 검색하세요."> -->

	<div class="pageInfo ver8">총 <b>${reserveVO.rowCount}</b>개, 검색 <b>${reserveVO.rowCount}</b>개, <b></b> <fmt:formatNumber value="${reserveVO.rowCount/reserveVO.pageSize}" pattern="0"></fmt:formatNumber>of ${priceVO.pageNo} Pages</div>

	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr><td class="rnd" colspan="10"></td></tr>
		<tr class="rndbg">
			<th width="60"><a href="javascript:chkBox(document.getElementsByName('chk'),'rev')" class="white"><font class="small1"><b>전체선택</b></font></a></th>
			<th><font class="small1"><b>번호</b></font></th>
			<th><font class="small1"><b>상품명</b></font></th>
			<!-- <th><font class="small1"><b>옵션1</b></font></th>
			<th><font class="small1"><b>옵션2</b></font></th> -->
			<th><font class="small1"><b>판매자</b></font></th>
			<th><font class="small1"><b>정가</b></font></th>
			<th><font class="small1"><b>판매가</b></font></th>
			<th><font class="small1"><b>매입가</b></font></th>
			<th><font class="small1"><b>적립금</b></font></th>
			<th><font class="small1"><b>재고</b></font></th>
			<th><font class="small1"><b>진열</b></font></th>
		</tr>
		<tr>
			<td class="rnd" colspan="12"></td>
		</tr>
		<col width="35">
		<col width="50">
		<col>
		<col width="70" span="5">
		<col width="60">
		<col width="35" span="2">
	<c:if test="${rtList !=null}">
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
		<tr><td height="4" colspan="12"></td></tr>
		<tr>
			<td align="center"><input type="checkbox" name="chk" value="${list.sno !=null ? list.sno:''}" onclick="iciSelect(this)"></td>

			<input type="hidden" name="priceAmt" value="${list.price}"/>

			<td align="center"><font class="ver8" color="#616161">${(reserveVO.rowCount - status.index) - ( reserveVO.rowStart ) }</font></td>
			<c:choose>
				<c:when test="${list.link eq '1'}">
					<c:choose>
						<c:when test="${fn:substring(list.category,0,3) eq '001'}">
						<td><a href="${ctx}/shop/admin/goods/register?mode=modify&goodsno=${list.goodsno }"><font class=small1 color=0074BA>${list.goodsnm !=null ? list.goodsnm : ''}</a></td>
						</c:when>
						<c:otherwise>
						<td><a href="${ctx}/shop/admin/goods/register?mode=modify&goodsno=${list.goodsno }"><font class=small1 color=0074BA>${list.goodsnm !=null ? list.goodsnm : ''}</a></td>
						</c:otherwise>
						</c:choose>
				</c:when>
				<c:otherwise>
					<td><font class="small1"><%-- <%= StringUtil.nullConv(rtList.get(i, "goodsnm"), "") %> --%></font></td>
				</c:otherwise>
			</c:choose>
			<%-- <td align="center"><font class="small" color="555555">${list.opt1 !=null ? list.opt1 : '' }</font></td>
			<td align="center"><font class="small" color="555555">${list.opt2 !=null ? list.opt2 : '' }</font></td> --%>
			<td align="center">${list.sellerNm}</td>
			<td align="right" style="padding-right:10px" nowrap><font class="ver8" color="#0074BA"><b><fmt:formatNumber value="${list.consumer}" pattern="#,###"/></b></font></td>
			<td align="right" style="padding-right:10px" nowrap><font class="ver8" color="#0074BA"><b><fmt:formatNumber value="${list.price}" pattern="#,###"/></b></font></td>
			<td align="right" style="padding-right:10px" nowrap><font class="ver8" color="#444444"><fmt:formatNumber value="${list.supply}" pattern="#,###"/></font></td>
			<td align="right" style="padding-right:10px" nowrap><font class="ver8" color="#444444"><fmt:formatNumber value="${list.reserve}" pattern="#,###"/></font></td>
			<td align="center"><font class="ver81" color="#444444"><fmt:formatNumber value="${list.stock}" pattern="#,###,###" /></font></td>
			<td align="center"><img src="/resources/shop/admin/img/icn_${list.open !=null ? list.open : ''}.gif"></td>
		</tr>
		<tr><td height="4"></td></tr>
		<tr><td colspan="12" class="rndline"></td></tr>
		</c:forEach>
	</c:if>
	</table>
	<br/>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td width="6%" style="padding-left:12"><a href="javascript:chkBox(document.getElementsByName('chk'),'rev')"><img src="/resources/shop/admin/img/btn_allchoice.gif"></a></td>
				<%-- <td width="88%" align="center"><div class="pageNavi"><font class="ver8"><%= page_list %></font></div></td> --%>
				<td width="6%"></td>
			</tr>
	</table>

	<tags:paginator currentPageNo="${reserveVO.pageNo}" rowCount="${reserveVO.rowCount}" pageSize="${reserveVO.pageSize}" pageGroupSize="${reserveVO.pageGroupSize}"/>
	<div style="padding:20 0 5 5"><font class="def1" color="#000000"><b><font size="3">②</font> 위 상품리스트에 있는 상품의 적립금을, 아래 조건을 적용하여 일괄수정합니다. <font class=extext>(신중하게 설정하고 수정하세요)</font></b></font></div>
	<table class="tb">
		<col class="cellC"><col class="cellL">
		<tr>
			<td>적립금조건설정</td>
			<td>
				<div style="margin:5px 0">
					<span class="noline"><input type="radio" name="method" value="direct" required label="일괄적용방법" ${reserveVO.method eq 'direct' ? 'checked' : ''}></span>적립금을 일괄
					<input type="text" name="reserve" value="${reserveVO.reserve}" size="6" class="rline" label="적립금"> 원으로 수정합니다.
				</div>
				<div style="margin:5px 0">
					<span class="noline"><input type="radio" name="method" value="price" required label="일괄적용방법" ${reserveVO.method eq 'price' ? 'checked' : '' }></span>적립금을 판매가의
					<select name="percent">
					<c:set var="ii" value="0"></c:set>
					<c:set var="idx" value=""></c:set>
					<c:forEach begin="0" end="108" >
						<c:choose>
							<c:when test="${ii <= 0.9}">
							 	<c:set var="ii" value="${ii+0.1}"></c:set>
							 	<fmt:formatNumber value="${ii}" pattern="#.#" var = "iii"></fmt:formatNumber>
								<c:set var="ii" value="${iii}"></c:set>
								<c:set var="idx" value="${ii}"></c:set>
								
							</c:when>
							<c:otherwise>
								
								<fmt:formatNumber value="${ii}" pattern="#" var = "iii"></fmt:formatNumber>
								<c:set var="ii" value="${iii}"></c:set>
								<c:set var="ii" value="${ii+1}"></c:set>
								<c:set var="idx" value="${ii}"></c:set>
								
							</c:otherwise>
						</c:choose>
						<option value="${idx}" ${idx eq reserveVO.percent ? 'selected' : ''}> ${idx}</option>
					</c:forEach>
				<%-- <%
					double ii = 0;
					String idx = "";
					while( (ii += (ii <= 0.9 ? 0.1 : 1)) <= 100 ){
						if(ii <= 0.9){
							ii = Double.parseDouble(String.format("%.1f", ii));
							idx = Double.toString(ii);
						}else{
							i = (int)ii;
							idx = Integer.toString(i);
						}
						out.println("<option value=\""+idx+"\" " + ( idx.equals(requestSet.getProperty("percent", ""))  ? "selected" : "")+ ">"+idx+"</option>");
					}
				%> --%>
					</select>%를
					<select name="roundunit">
						<option value="1" selected>1</option>
						<option value="10" ${reserveVO.roundunit eq '10' ? 'selected' :''}>10</option>
						<option value="100" ${reserveVO.roundunit eq '100' ? 'selected' : ''}>100</option>
						<option value="1000" ${reserveVO.roundunit eq '1000' ? 'selected' : ''}>1000</option>
					</select>
					유로 단위로
					<select name="roundtype">
						<option value="down" selected>내림</option>
						<option value="halfup" ${reserveVO.roundtype eq 'halfup' ? 'selected' :''}>반올림</option>
						<option value="up" ${reserveVO.roundtype eq 'up' ? 'selected' :''}>올림</option>
					</select>
					하여 수정합니다.
				</div>
				<!-- <div style="margin:5px 0" class="noline">
					<input type="checkbox" name="isall" value="Y" <?=$checked[isall]['Y']?>>검색된 상품 전체<?=($pg->recode[total]?"({$pg->recode[total]}개)":"")?>를 수정합니다. <font class=extext>(상품수가 많은 경우 비권장합니다. 가능하면 한 페이지씩 선택하여 수정하세요)
				</div> -->
			</td>
		</tr>
	</table>

	<div class="button_top"><input type="image" src="/resources/shop/admin/img/btn_save.gif" onclick="makeSearchParam()"></div>

</form>

<div id="MSG01">
	<table cellpadding="1" cellspacing="0" border="0" class="small_ex">
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">일괄관리 할 상품을 검색 후 상품적립금을 일괄처리 조건에 맞춰 적용합니다.</td></tr>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">[주의1] 일괄적용 후에는 <b>이전상태로 복원이 안되므로 신중하게 변경하시기 바랍니다.</b></td></tr>
		<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">[주의2] 서버 부하등 안정적인 서비스를 위해서 검색결과가 많은 경우에는 검색결과 전체수정은 피하시기 바랍니다.</td></tr>
		<tr><td style="padding-top:4"><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle"><b>[적립금수정 예제]</b></td></tr>
		<tr><td style="padding-left:10">판매가의 5.5% 할인된 가격으로 적립금을 일괄적으로 수정하고, 가격 단위는 100원 단위로 내림하여 수정한다면,</td></tr>
		<tr><td style="padding-left:10">판매가 10,000원인 상품의 계산식은 다음과 같습니다.</td></tr>
		<tr><td style="padding-left:10">⇒ 10,000 × (5.5 / 100) = 550원이며,</td></tr>
		<tr><td style="padding-left:10">⇒ 100원 단위 내림하면 500원 으로 최종 적립금수정이 됩니다.</td></tr>
	</table>
</div>
<script>cssRound('MSG01')</script>
<script type="text/javascript">
function goPage(page){
	$("#pageNo").val(page);
	document.frmList.submit();
}
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

<%-- ================================================================================
* 업무 시작 catch 부분
================================================================================ --%>
