<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<c:set var="b" value="false"></c:set>
<c:set var="vnum" value=""></c:set>
<c:set var="searchCnt" value=""></c:set>
<c:set var="strCategory" value="${priceVO.strCategory != null ? priceVO.strCategory : ''}"></c:set>
<c:set var="indicate" value="${priceVO.indicate !=null ? priceVO.indicate : ''}"></c:set>
<c:set var="skey" value="${priceVO.skey !=null ? priceVO.skey : ''}"></c:set>
<c:set var="open" value="${priceVO.open !=null ? priceVO.open : ''}"></c:set>
<c:set var="smain" value="${priceVO.smain !=null ? priceVO.smain : ''}"></c:set>
<c:set var="sevent" value="${priceVO.sevent !=null ? priceVO.sevent : ''}"></c:set>
<c:set var="dtprice" value="${priceVO.dtprice !=null ? priceVO.dtprice : ''}"></c:set>
<c:set var="list" value="${priceVO.goodsTypeList !=null ? priceVO.goodsTypeList : ''}"></c:set>
<c:set var="rtList" value="${priceVO.priceList !=null ? priceVO.priceList : ''}"></c:set>
<c:set var="tmp" value=""></c:set>
<c:set var="opt1Nm" value=""></c:set>


<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>
<!-- <script src="../prototype.js"></script> -->
<script language="javascript" src="/resources/shop/admin/common.js"></script>
<script>
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
	if (fObj['pmmark'].value == '유로' && chkPatten(fObj['pmnum'],'regNum') === false) return false;
	//if (fObj['isall'].checked === false && isChked(document.getElementsByName('chk')) === false){
	if (isChked(document.getElementsByName('chk')) === false){
		if (document.getElementsByName('chk').length) document.getElementsByName('chk')[0].focus();
		return false;
	}

	var msg = '';
	msg += fObj['dtprice'].options[fObj['dtprice'].selectedIndex].text;
	msg += '의 ' + fObj['pmnum'].value + fObj['pmmark'].options[fObj['pmmark'].selectedIndex].text;
	msg += '를 ' + fObj['pmtype'].options[fObj['pmtype'].selectedIndex].text;
	msg += '된 가격으로 ' + fObj['tgprice'].options[fObj['tgprice'].selectedIndex].text;
	msg += '을 ' + fObj['roundunit'].value;
	msg += '유로 단위로 ' + fObj['roundtype'].options[fObj['roundtype'].selectedIndex].text;
	msg += '하여 일괄적으로 수정하시겠습니까?';
	msg += "\n\n" + '[주의] 일괄적용 후에는 이전상태로 복원이 안되므로 신중하게 변경하시기 바랍니다.';
	if (!confirm(msg)) return false;
	
	fObj.mode.value = "price";
	fObj.target = "_self";
	fObj.action = "price/indb";
	
	return true;
}

/*** 이벤트목록 요청 ***/
function getEventList(sobj, selValue)
{

	if (sobj.options[sobj.selectedIndex].getAttribute("call") == null) return;
	function setcallopt(idx, text, value, defaultSelected, selected, call){
		if (idx == 0) for (var i = sobj.options.length; i > 0; i--) sobj.remove(i);
		sobj.options[idx] = new Option(text, value, defaultSelected, selected);
		if (call != null) sobj.options[idx].setAttribute('call', call);
	}
	//페이징 처리 ,리스트 출력
	/* var ajax = new Ajax.Request( "../goods/price/indb", 
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

//window.onload = function (){ getEventList(frm.sevent, '${sEvent}'); }

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
	getEventList(frm.sevent, '${sevent}');
});


/* 2017-08-30 추가 - 페이징 유지 */
function makeSearchParam(){
	$('#search').val($('#frm').serialize());
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

		<div class="title title_top">빠른 가격수정<span>등록하신 상품가격을 일괄 수정할 수 있습니다.</span> 
			<%//<a href="javascript:alert('준비중입니다..')"><!-- javascript:popup('http://guide.godo.co.kr/season2/board/view.jsp?id=product&no=13',870,800) --><img src="../img/btn_q.gif" border=0 align=absmiddle hspace=2></a>%>
		</div>

		<!-- 상품출력조건 : start -->
<form name=frm id=frm onsubmit="return chkForm(this)">
<input type=hidden id=pageNo name="pageNo" value="1"/>
		<div style="padding:10 0 5 5"><font class="def1" color="#000000"><b><font size="3">①</font> 먼저 아래에서 가격수정할 상품을 검색합니다. 
			<font class="extext">(아래 3가지 방식중 한가지를 선택하여 검색)</font></b></font></div>
		<div class="noline" style="padding:0 0 5 20">
			<input type="radio" name="indicate" value="search" required label="상품검색조건" checked><b>전체상품 검색</b> 
			<font class="extext">(전체상품을 대상으로 검색합니다. 특정 분류의 상품을 모두 보려면 분류 선택후 상품명을 공란으로 두고 검색)</font>
		</div>
		<table class="tb">
			<col class="cellC"><col class="cellL">
			<tr>
				<td>판매사명</td>
				<td>
					<input type="text" name="schSellerNm" id="schSellerNm" value="${priceVO.schSellerNm}" class="line" style="height:22px" />
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
						<option value="a.goodsno" ${skey eq 'a.goodsno'? 'selected' :''}>고유번호
						<option value="a.goodscd" ${skey eq 'a.goodscd'? 'selected' :''}>상품코드
						<option value="a.keyword" ${skey eq 'a.keyword'? 'selected' :''}>유사검색어
					</select>
					<input type="text" name="sword" class="lline" value="${priceVO.sword}" class="line">
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
			<input type="radio" name="indicate" value="event" required label="상품검색조건" ${indicate eq 'event' ? 'checked':''}><b>이벤트상품</b> 
			<font class="extext">(이벤트상품으로 선정해놓은 상품들을 출력합니다)</font>
		</div>
	<div style="display:none">
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
		<div class="button_top"><input type="image" src="/resources/shop/admin/img/btn_search2.gif"></div>

</form>
		<!-- 상품출력조건 : end -->

<form name="fmList" method="post" onsubmit="return chkFormList(this)">
<input type="hidden" name="mode" value="price">
<input type="hidden" name="search" id="search" value ="" />

		<div class="pageInfo ver8">
			<c:set var="pages" value="${(priceVO.rowCount*10) / (priceVO.pageSize*10)} " />
			<c:set var="pageCnt" value="${pages+(1-(pages%1))%1}" />
			<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
			총 <b>${priceVO.totalCnt}</b>개, 검색 <b>${priceVO.rowCount}</b>개, <b>${priceVO.pageNo }</b> of <b>${var3 }</b> Pages
		</div>

		<table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tr><td class="rnd" colspan="10"></td></tr>
			<tr class="rndbg">
				<th width="60"><a href="javascript:chkBox(document.getElementsByName('chk'),'rev')" class=white><font class=small1><b>전체선택</b></font></a></th>
				<th><font class="small1"><b>번호</b></font></th>
				<th><font class="small1"><b>상품명</b></font></th>
				<!-- <th><font class="small1"><b>옵션1</b></font></th>
				<th><font class="small1"><b>옵션2</b></font></th> -->
				<th><font class="small1"><b>판매자</b></font></th>
				<th><font class="small1"><b>정가</b></font></th>
				<th><font class="small1"><b>판매가</b></font></th>
				<th><font class="small1"><b>매입가</b></font></th>
				<th><font class="small1"><b>재고</b></font></th>
				<th><font class="small1"><b>출력여부</b></font></th>
			</tr>
			<tr><td class="rnd" colspan="12"></td></tr>
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
				<td align="center"><input type="checkbox" name="chk" value="${list.sno !=null ? list.sno:''}" onclick="iciSelect(this)">
					<input type="hidden" name="consumer_amt_${list.sno}" value="${list.consumer}"/>
					<input type="hidden" name="price_amt_${list.sno}"  value="${list.price}"/>
					<input type="hidden" name="supply_amt_${list.sno}" value="${list.supply}"/>
<%-- 					<input type="hidden" name="consumerAmt" value="${list.consumer}"/> --%>
<%-- 					<input type="hidden" name="priceAmt"  value="${list.price}"/> --%>
<%-- 					<input type="hidden" name="supplyAmt" value="${list.supply}"/> --%>
				</td>
					<td align="center"><font class="ver8" color="#616161">${(priceVO.rowCount - status.index) - ( (priceVO.pageNo - 1)  *  priceVO.pageSize ) }</font></td>
					<c:choose>			
					<c:when test="${list.link eq '1'}">
						<c:choose>
							<c:when test="${fn:substring(list.category,0,3) eq '001'}">
								<td><a href="${ctx}/shop/admin/goods/register?mode=modify&goodsno=${list.goodsno }" target="_blank"><font class=small1 color=0074BA>${list.goodsnm !=null ? list.goodsnm : ''}</font></a></td>
							</c:when>
							<c:otherwise>
								<td><a href="${ctx}/shop/admin/goods/register?mode=modify&goodsno=${list.goodsno }" target="_blank"><font class=small1 color=0074BA>${list.goodsnm !=null ? list.goodsnm : ''}</font></a></td>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<td><font class="small1"><%-- <%= StringUtil.nullConv(rtList.get(i, "goodsnm"), "") %> --%></font></td>
					</c:otherwise>
					</c:choose>
					<%-- <td align="center"><font class="small" color="555555">${opt1Nm}</font></td>
					<td align="center"><font class="small" color="555555">${list.opt2 !=null ? list.opt2 : '' }</font></td> --%>
					<td align="center">${list.sellerNm}</td>
					<td align="right" style="padding-right:10px" nowrap><font class="ver8" color="#0074BA"><b><fmt:formatNumber value="${list.consumer}" pattern="#,###,###"></fmt:formatNumber></b></font></td>
					<td align="right" style="padding-right:10px" nowrap><font class="ver8" color="#0074BA"><b><fmt:formatNumber value="${list.price}" pattern="#,###,###"></fmt:formatNumber></b></font></td>
					<td align="right" style="padding-right:10px" nowrap><font class="ver8" color="#444444"><fmt:formatNumber value="${list.supply}" pattern="#,###,###"></fmt:formatNumber></font></td>
					<td align="center"><font class="ver81" color="#444444"><fmt:formatNumber value="${list.stock}" pattern="#,###,###"></fmt:formatNumber></font></td>
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
		
		<tags:paginator currentPageNo="${priceVO.pageNo}" rowCount="${priceVO.rowCount}" pageSize="${priceVO.pageSize}"  pageGroupSize="${priceVO.pageGroupSize}" />

		<div style="padding:20 0 5 5">
			<font class="def1" color="#000000"><b><font size="3">②</font> 위 상품리스트에 있는 상품의 가격을, 아래 조건을 적용하여 일괄수정합니다. <font class="extext">(신중하게 설정하고 수정하세요)</font></b></font>
		</div>
		<table class="tb">
			<col class="cellC"><col class="cellL">
			<tr>
				<td>가격조건설정</td>
				<td>
					<div style="margin:5px 0">
						<select name="dtprice">
							<option value="price" selected>판매가</option>
							<option value="consumer" ${priceVO.dtprice eq 'consumer' ? 'selected':''}>정가</option>
							<option value="supply" ${priceVO.dtprice eq 'supply' ? 'selected' : ''}>매입가</option>
						</select> 에서
						<input type="text" name="pmnum" required label="계산수치" option="/^[0-9]+(\.[0-9]){0,1}$/" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" onblur="removeHangul(event);"  value="${priceVO.pmnum}" size="4" class="rline" maxlength="7" style="width:60px">
						<select name="pmmark">
							<option value="유로" selected>유로</option>
							<option value="%" ${priceVO.pmmark eq '%' ? 'selected':''}>%</option>
						</select> 을
						<select name="pmtype">
							<option value="down" selected>할인</option>
							<option value="up" ${priceVO.pmtype eq 'up' ? 'selected':''}>할증</option>
						</select>	된 가격으로
						<select name="tgprice">
							<option value="price" selected>판매가</option>
							<option value="consumer"${priceVO.tgprice eq 'consumer' ? 'selected' :'' }>정가</option>
						</select> 를 일괄적으로 수정합니다.
					</div>
					<div style="margin:5px 0">
					수정되는 가격의 단위는
					<select name="roundunit">
						<option value="1" selected>1</option>
						<option value="10" ${priceVO.roundunit eq '10'? 'selected':'' }>10</option>
						<option value="100" ${priceVO.roundunit eq '100' ? 'selected' :''}>100</option>
						<option value="1000" ${priceVO.roundunit eq '1000' ? 'selected' :''}>1000</option>
					</select> 유로 단위로
					<select name="roundtype">
						<option value="down" selected>내림</option>
						<option value="halfup" ${priceVO.roundtype eq 'halfup' ? 'selected' :''}>반올림</option>
						<option value="up" ${priceVO.roundtype eq'up' ? 'selected' : ''}>올림</option>
					</select> 하여 수정합니다.
					</div>
					
				</td>
			</tr>
		</table>

		<div class="button_top"><input type="image" src="/resources/shop/admin/img/btn_modify.gif" onclick="makeSearchParam()"></div>

		</form>

		<div id="MSG01">
			<table cellpadding="1" cellspacing="0" border="0" class="small_ex">
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">일괄수정 할 상품을 검색 후 상품가격을 일괄처리 조건에 맞춰 적용합니다.</td></tr>
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">[주의1] 일괄수정 이후에는 <b>이전상태로 복원이 안되므로 신중하게 변경하시기 바랍니다.</b></td></tr>
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">[주의2] 서버 부하등 안정적인 서비스를 위해서 수정할 상품이 많은 경우에는 검색된 상품 전체수정은 피하시기 바랍니다.</td></tr>
				<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">할인/할증율(%) : 소수점 첫째자리까지 입력하실 수 있습니다. [예] 5.5% 할인(가능), 5.5% 할증(가능), 5.55% 할인(불가능)</td></tr>
				<tr><td style="padding-top:4"><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle"><b>[가격수정 예제]</b></td></tr>
				<tr><td style="padding-left:10">판매가의 5.5% 할인된 가격으로 판매가를 일괄적으로 수정하고, 가격 단위는 100원 단위로 내림하여 수정한다면,</td></tr>
				<tr><td style="padding-left:10">판매가 10,000원인 상품의 계산식은 다음과 같습니다.</td></tr>
				<tr><td style="padding-left:10">⇒ 10,000 × (1 - (5.5 / 100)) = 9,450원이며,</td></tr>
				<tr><td style="padding-left:10">⇒ 100원 단위 내림하면 9,400원 으로 최종 가격수정이 됩니다.</td></tr>
			</table>
		</div>
		<script>cssRound('MSG01')</script>
<script type="text/javascript">
function goPage(page){
	$("#pageNo").val(page);
	document.frm.submit();
// 	window.location.href="price?pageNo="+page;
}
</script>
<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<%@ include file="../common/bottom.jsp" %>
	</td>
</tr>
</table>
