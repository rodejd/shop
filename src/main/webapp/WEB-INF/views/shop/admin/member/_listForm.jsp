<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%-- ================================================================================
* 공통 상단 include
* 공통 java class 지정 및 변수 설정
================================================================================ --%>
<%-- ================================================================================
* 공통변수의 할당 부분
================================================================================ --%>
<%-- ================================================================================
* 업무별 거래 로직
================================================================================ --%>

<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>
<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<%
	//팝업에서 호출했을 경우(Y:팝업호출)	
	String popupSearch = request.getParameter("popupSearch");	
%>
<c:set var="popupSearch" value="<%=popupSearch %>" />
  
<table class="tb">
	<col class="cellC" /><col class="cellL" style="width:250" />
	<col class="cellC" /><col class="cellL" />
	
	
<!-- //팝업에서 호출했을 경우 -->
<c:if test="${popupSearch == 'Y' }">

	<tr>
		<td class="noline" colspan="4">
			
			<input type="checkbox" name="popupDetail" value="Y" onClick="javascript:popupDetailDiv();" <c:if test="${memberVO.popupDetail == 'Y'}">checked</c:if>> 상세 검색시 체크를 해주세요
		</td>
	</tr>
	
</c:if>
	
	<tr>
		<td>키워드검색</td>
		<td>
			<select name="skey">	
				<option value="all" ${stringUtil:selected(memberVO.skey, "all")}> 통합검색 </option>
				<option value="name" ${stringUtil:selected(memberVO.skey, "name")}> 회원명 </option>
				<option value="nickname" ${stringUtil:selected(memberVO.skey, "nickname")}> 닉네임 </option>
				<option value="m_id" ${stringUtil:selected(memberVO.skey, "m_id")}> 아이디 </option>
				<option value="email" ${stringUtil:selected(memberVO.skey, "email")}> 이메일 </option>
				<option value="resno" ${stringUtil:selected(memberVO.skey, "resno")}> 주민번호 </option>
				<option value="phone" ${stringUtil:selected(memberVO.skey, "phone")}> 전화번호 </option>
				<option value="mobile" ${stringUtil:selected(memberVO.skey, "mobile")}> 핸폰번호 </option>
				<option value="recommid" ${stringUtil:selected(memberVO.skey, "recommid")}> 추천인 </option>
				<option value="company" ${stringUtil:selected(memberVO.skey, "company")}> 회사명 </option>	
			</select> 
			<input type="text" name="sword" value="${memberVO.sword}" class="line" />
		</td>
		<td>승인여부/그룹</td>
		<td>
			<select name="sstatus">
				<option value="" ${stringUtil:selected(memberVO.sstatus, "")}<%-- <%=requestSet.selected("sstatus","")%> --%>> 전체 </option>
				<option value="1" ${stringUtil:selected(memberVO.sstatus, "1")}<%-- <%=requestSet.selected("sstatus","1")%> --%>> 승인 </option>
				<option value="0" ${stringUtil:selected(memberVO.sstatus, "0")}<%-- <%=requestSet.selected("sstatus","0")%> --%>> 미승인 </option>
			</select>
		
			<select name="slevel">
				<option value="">==그룹선택==</option>
				<c:forEach items="${codeUtil:getMemberGrp()}" var="mglist" varStatus="st">
					<option value="${mglist.kLevel}" ${stringUtil:selected(memberVO.slevel, fn:trim(mglist.kLevel))}>${mglist.grpnm} - lv[${mglist.kLevel}]</option>
				</c:forEach>
			</select>
		</td>
	</tr>
</table>

	<div style="padding-top:3;;" id="searchDetail">
	<table class="tb">
		<col class="cellC" />
		<col class="cellL" style="width:250" />
		<col class="cellC" />
		<col class="cellL" />

		<tr>
			<td>구매액</td>
			<td>
				₩<input type="text" name="ssum_salemin" value="${memberVO.ssum_salemin}" size="10" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" class="rline" /> ~
				₩<input type="text" name="ssum_salemax" value="${memberVO.ssum_salemax}" size="10" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" class="rline" />
			</td>
			<td>적립금</td>
			<td>
				₩<input type="text" name="semoneymin" value="${memberVO.semoneymin}" size="10" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" class="rline" /> ~
				₩<input type="text" name="semoneymax" value="${memberVO.semoneymax}" size="10" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" class="rline" />
			</td>
		</tr>
		
		<tr>
			<td>가입일</td>
			<td colspan="3">
				<input type="text" name="sregdt" value="${memberVO.sregdt[0]}<%//=requestSet.getProperty("sregd_0")%>" size="10" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" onclick="calendar(event);" class="cline" maxlength="8"/> ~
				<input type="text" name="sregdt" value="${memberVO.sregdt[1]}<%//=requestSet.getProperty("sregd_1")%>" size="10" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" onclick="calendar(event);" class="cline" maxlength="8"/>
				<a href="javascript:setDate('sregdt', ${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align="absmiddle" /></a>
				<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFrom('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -7)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align="absmiddle" /></a>
				<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFrom('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -15)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align="absmiddle" /></a>
				<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'), -1)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align="absmiddle" /></a>
				<a href="javascript:setDate('sregdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -2)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align="absmiddle" /></a>
				<a href="javascript:setDate('sregdt')"><img src="/resources/shop/admin/img/sicon_all.gif" align="absmiddle" /></a>
			</td>
		</tr>
		 
		<tr>
			<td>최종로그인</td>
			<td colspan="3">
				<input type="text" name="slastdt" value="${memberVO.slastdt[0]}" size="10" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" onclick="calendar(event);" class="cline" maxlength="8"/> ~
				<input type="text" name="slastdt" value="${memberVO.slastdt[1]}" size="10" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" onclick="calendar(event);" class="cline" maxlength="8"/>
				<a href="javascript:setDate('slastdt', ${dateUtil:getDate('yyyyMMdd')},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_today.gif" align="absmiddle" /></a>
				<a href="javascript:setDate('slastdt', ${dateUtil:getDateFormatFrom('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -7)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_week.gif" align="absmiddle" /></a>
				<a href="javascript:setDate('slastdt', ${dateUtil:getDateFormatFrom('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -15)}, ${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align="absmiddle" /></a>
				<a href="javascript:setDate('slastdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd',dateUtil:getDate('yyyyMMdd'), -1)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_month.gif" align="absmiddle" /></a>
				<a href="javascript:setDate('slastdt', ${dateUtil:getDateFormatFromMonth('yyyyMMdd', dateUtil:getDate('yyyyMMdd'), -2)},${dateUtil:getDate('yyyyMMdd')})"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align="absmiddle" /></a>
				<a href="javascript:setDate('slastdt')"><img src="/resources/shop/admin/img/sicon_all.gif" align="absmiddle" /></a>
			</td>
		</tr>
		
		<tr>
			<td>성별</td>
			<td class="noline">
				<input type="radio" name="sex" value="" ${stringUtil:checked(memberVO.sex, '')} />전체
				<input type="radio" name="sex" value="m" ${stringUtil:checked(memberVO.sex, 'm')} />남자
				<input type="radio" name="sex" value="f" ${stringUtil:checked(memberVO.sex, 'f')}/>여자
			</td>
			<td>메일 수신여부</td>
			<td class="noline">
				<input type="radio" name="smailling" value="" ${stringUtil:checked(memberVO.smailling, '')}<%-- <%=requestSet.checked("mailing","")%> --%> checked="checked"  />전체
				<input type="radio" name="smailling" value="y" ${stringUtil:checked(memberVO.smailling, 'y')}<%-- <%=requestSet.checked("mailing","y")%> --%> />수신
				<input type="radio" name="smailling" value="n" ${stringUtil:checked(memberVO.smailling, 'n')}<%-- <%=requestSet.checked("mailing","n")%> --%> />수신거부
			</td>
		</tr>
		
		<tr>
			<td>방문횟수</td>
			<td>
			<input type="text" name="scnt_loginmin" value="${memberVO.scnt_loginmin}<%-- <%=requestSet.getProperty( "scnt_loginmin","")%> --%>" size="10" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" class="rline" />회 ~
			<input type="text" name="scnt_loginmax" value="${memberVO.scnt_loginmax}<%-- <%=requestSet.getProperty( "scnt_loginmax","")%> --%>" size="10" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" class="rline" />회
			</td>
			<td>휴면회원검색 </td>
			<td>
			<input type="text" name="dormancy" value="${memberVO.dormancy}" size="8" maxlength="8" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" class="rline" /> 일 이상 미접속 회원검색
			</td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td>
				<select name="birthtype">
					<option value="" ${stringUtil:selected(memberVO.birthtype, "")}<%-- <%=requestSet.selected("birthtype", "")%> --%>> 전체 </option>
					<option value="s" ${stringUtil:selected(memberVO.birthtype, "s")}<%-- <%=requestSet.selected("birthtype", "s")%> --%>> 양력 </option>
					<option value="l" ${stringUtil:selected(memberVO.birthtype, "l")}<%-- <%=requestSet.selected("birthtype", "l")%> --%>> 음력 </option>
				</select>
				<input type="text" name="birthdatemin" value="${memberVO.birthdatemin}<%-- <%=requestSet.getProperty( "birthdatemin","")%> --%>" size="8" maxlength="8" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" class="cline" /> ~
				<input type="text" name="birthdatemax" value="${memberVO.birthdatemax}<%-- <%=requestSet.getProperty( "birthdatemax","")%> --%>" size="8" maxlength="8" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" class="cline" />
				<div style="padding-left:53px"><font class="ver71" color="627dce">ex) 20080321(8자) 
			</td>
			<td>결혼여부/결혼기념일</td>
			<td>
				<select name="smarriyn" onclick="selectMarriyn()">
					<option value="" ${stringUtil:selected(memberVO.smarriyn, "")}> 전체 </option>
					<option value="n" ${stringUtil:selected(memberVO.smarriyn, "n")}> 미혼 </option>
					<option value="y" ${stringUtil:selected(memberVO.smarriyn, "y")}> 기혼 </option>
				</select>
				<input type="text" name="marridatemin" value="${memberVO.marridatemin}" size="8" maxlength="8" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" class="cline" /> ~
				<input type="text" name="marridatemax" value="${memberVO.marridatemax}" size="8" maxlength="8" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" class="cline" />
				<div style="padding-left:53px"><font class="ver71" color="627dce">ex) 20080321(8자) 
			</td>
		</tr>
	</table>

	</div>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>
<%-- ================================================================================
=====================================================================================
* 화면 종료
=====================================================================================
================================================================================ --%>

<script language="JavaScript" type="text/JavaScript">
function popupDetailDiv(){
	
	if($('input[name=popupDetail]').length > 0){
		if($('input[name=popupDetail]').is(':checked')){
			$('#searchDetail').show();
		}else{
			$('#searchDetail').hide();
		}
	}
	
	
}
popupDetailDiv();
</script>