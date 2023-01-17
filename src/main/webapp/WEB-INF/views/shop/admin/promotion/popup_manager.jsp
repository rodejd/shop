<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
* 업무별 거래 로직
================================================================================ --%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="styleSheet" href="/resources/shop/admin/style.css">
<script src="/resources/shop/admin/common.js"></script>

<div class="title title_top">메인팝업창 관리<span>메인 팝업창에 대한 설정을 추가 변경하실 수 있습니다</span></div>

<form action="list">

<table class="tb">
<col class="cellC"><col class="cellL" style="width:170px">
<col class="cellC"><col class="cellL">
<tr>
	<td>키워드검색</td>
	<td colspan="3">
	<select name="skey">
	<option value="sub"   ${popUpVO==null ? '' : popUpVO.skey eq 'sub' ? 'selected' : '' }> 팝업제목 </option>
	<option value="filename"   ${popUpVO==null ? '' : popUpVO.skey eq 'filename' ? 'selected' : '' }> 팝업화일명 </option>
	</select> <input type="text" NAME="sword" value="${popUpVO.sword}" class=line maxlength="50">
	</td>
</tr>
<tr>
	<td>출력기간</td>
	<td colspan="3">
	<input type=text name=sregdt id = "sregdt1" value='${popUpVO.sregdt[0]}' onclick="calendar(event)" class=line onkeydown="onlynumber(event)" onkeyup="removeChar(event)" maxlength="8"> -
	<input type=text name=sregdt id = "sregdt2" value='${popUpVO.sregdt[1]}' onclick="calendar(event)" class=line onkeydown="onlynumber(event)" onkeyup="removeChar(event)" maxlength="8">
	<a href="javascript:setDate('sregdt',${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align=absmiddle></a>
	<a href="javascript:setDate('sregdt',${dateUtil:getDateFrom('yyyyMMdd', -7)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align=absmiddle></a>
	<a href="javascript:setDate('sregdt',${dateUtil:getDateFrom('yyyyMMdd', -15)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align=absmiddle></a>
	<a href="javascript:setDate('sregdt',${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'), -1)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align=absmiddle></a>
	<a href="javascript:setDate('sregdt',${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'), -2)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align=absmiddle></a>
	<a href="javascript:setDate('sregdt')"><img src="/resources/shop/admin/img/sicon_all.gif" align=absmiddle></a>
	</td>
</tr>
<tr>
	<td>출력여부</td>
	<td class="noline">
	<input type="radio" name="use" value=""   checked ${popUpVO==null ? '' : popUpVO.use eq '' ? 'checked' : '' }/>전체
	<input type="radio" name="use" value="Y"  ${popUpVO==null ? '' : popUpVO.use eq 'Y' ? 'checked' : '' }/>출력
	<input type="radio" name="use" value="N" ${popUpVO==null ? '' : popUpVO.use eq 'N' ? 'checked' : '' }/>미출력
	</td>
	<td>창타입</td>
	<td class="noline" nowrap>
	<input type="radio" name="popUpType" value=""  checked ${popUpVO==null ? '' : popUpVO.popUpType eq '' ? 'checked' : '' }/>전체
	<input type="radio" name="popUpType" value="move" ${popUpVO==null ? '' : popUpVO.popUpType eq 'move' ? 'checked' : '' }/>이동레이어
	<input type="radio" name="popUpType" value="fix" ${popUpVO==null ? '' : popUpVO.popUpType eq 'fix' ? 'checked' : '' }/>고정레이어
	<input type="radio" name="popUpType" value="noname" ${popUpVO==null ? '' : popUpVO.popUpType eq 'noname' ? 'checked' : '' }/>일반팝업창
	</td>
</tr>
</table>

<div class="button_top"><input type="image" src="/resources/shop/admin/img/btn_search2.gif" onclick = "return chkDay();"/></div>

<table width="100%">
<tr>
	<td class="pageInfo">총(검색) <font class="ver8"><b>${popUpVO.rowCount }</b>개</font></td>	
</tr>
</table>
</form>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
<tr><td class="rnd" colspan="10"></td></tr>
<tr class="rndbg">
	<th>번호</th>
	<th>팝업제목</th>
	<th>출력기간/시간</th>
	<th>창위치</th>
	<th>창크기</th>
	<th>출력여부</th>
	<th>창타입</th>
	<th>보기</th>
	<th>수정</th>
	<th>삭제</th>
</tr>
<tr><td class="rnd" colspan="10"></td></tr>
<col width="30" align="center">
<col style="padding-left:20px;">
<col width="115" align="center">
<col width="65" align="center">
<col width="65" align="center">
<col width="55" align="center">
<col width="65" align="center">
<col width="55" align="center">
<col width="40" align="center">
<col width="40" align="center">
<c:forEach items="${popUpVO.popUpList}" var="popUpList" varStatus="status">
<tr height="30" align="center">
						<td><font class="ver81" color=616161>${(popUpVO.rowCount - status.index) - ( (1 - 1)  *  10 ) }</font></td>
						<td><font color="0074ba"><b>${popUpList.sub }</b></font></td>
						<td><font class="ver81">
							<c:choose>
								<c:when test="${popUpList.poppik eq 'term' && popUpList.repopsdt eq '00000000000000' && popUpList.repopedt eq '00000000000000'  }">
									제한없음
								</c:when>
								<c:when test="${popUpList.poppik eq 'time' && popUpList.repopsdate eq '00000000000000' && popUpList.repopedate eq '00000000000000'  }">
									제한없음
								</c:when>
								<c:when test="${popUpList.poppik eq 'term'}">
									${popUpList.popsdt} <br/>${popUpList.popedt}
								</c:when>
								<c:when test="${popUpList.poppik eq 'time'}">
									${popUpList.popsdate} <br/>${popUpList.popedate}
								</c:when>
								<c:otherwise>
									제한없음
								</c:otherwise>
							</c:choose>
						</font></td>
						<td><font class="ver81">${popUpList.popspotw } x ${popUpList.popspoth }</font></td>
						<td><font class="ver81">${popUpList.popsizew } x ${popUpList.popsizeh }</font></td>
						<td>
							<c:choose>
								<c:when test="${popUpList.popuse eq 'Y' }">
									<font color="0074ba">출력</font>
								</c:when>
								<c:otherwise>
									<font color="ff0000">미출력</font> 
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${popUpList.poptype eq 'noname' }">
									<font color="ff8000">일반팝업창</font>
								</c:when>
								<c:when test="${popUpList.poptype eq 'move' }">
									<font color="ff8000">이동레이어</font>
								</c:when>
								<c:otherwise>
									<font color="0074ba">고정레이어</font>
								</c:otherwise>
							</c:choose>
						</td>
<%-- 						<td><a href="javascript:popup_return('/shop/data/upload/popup/${popUpList.filename }.html','${popUpList.sub }' ,'${popUpList.popsizew }','${popUpList.popsizew }','${popUpList.popsizeh }','${popUpList.popspotw }','${popUpList.popspoth }','1')"> --%>
							<td><a href="javascript:popup_return('popUp?filename=${popUpList.filename  }' ,'${popUpList.popsizew }','${popUpList.popsizew }','${popUpList.popsizeh }','${popUpList.popspotw }','${popUpList.popspoth }','1')">
						<img src="/resources/shop/admin/img/i_view_popup.gif"></a></td>
						<td><a href="register?sno=${popUpList.sno }&mode=modify"><img src="/resources/shop/admin/img/i_edit.gif"></a></td>
						<td><a href="javascript:file_del(${popUpList.sno }, '${popUpList.filename }')"><img src="/resources/shop/admin/img/i_del.gif"></a></td>
					</tr>
					<tr><td colspan="10" class="rndline"></td></tr>
</c:forEach>
<tr><td colspan="10" class="rndline"></td></tr>
</table>

<table width="100%">
<tr><td height=10></td></tr>
<tr>
	<%-- [팝업관리] : %5B%ED%8C%9D%EC%97%85%EA%B4%80%EB%A6%AC%5D
		   팝업관리 : %ED%8C%9D%EC%97%85%EA%B4%80%EB%A6%AC --%>
	<td align=center><a href="register?mode=register&firstmenu=%5B%ED%8C%9D%EC%97%85%EA%B4%80%EB%A6%AC%5D&secondmenu=%ED%8C%9D%EC%97%85%EA%B4%80%EB%A6%AC"><img src="/resources/shop/admin/img/btn_popup_make.gif"></a></td>
</tr>
</table>


<div style="padding-top:20px"></div>

<div id="MSG01">
<table cellpadding="1" cellspacing="0" border="0" class="small_ex">
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle" />'팝업창만들기'를 클릭하면 팝업창을 새로 만들수 있습니다.</td></tr>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle" />'화면보기'를 클릭하면 팝업창 화면을 볼 수 있습니다.</td></tr>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle" />팝업창마다 노출출력기간을 설정 할 수 있습니다.</td></tr>
</table>
</div>
<script>
function file_del(pop_no, file_name)
{
	if (!confirm("파일을 삭제하시겠습니까?" )) return;

	var fobj = document.createElement("form");
	document.getElementById('jsmotion').appendChild(fobj);

	fobj.method = "post";
	fobj.action = "indb?mode=del&sno=" + pop_no+"&filename="+file_name;
	fobj.submit();
}

function chkDay(){
	
	var sregdt1 = $("#sregdt1").val();
	var sregdt2 = $("#sregdt2").val();
	
	if(sregdt1 > sregdt2){
		alert("시작날짜가 종료 날짜보다 늦을수 없습니다");
		return false;
	}
}
</script>
<script>cssRound('MSG01')</script>
<script>window.onload = function(){ UNM.inner();};</script>
<%@ include file="../common/bottom.jsp" %>