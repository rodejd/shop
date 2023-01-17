<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<%
	//# 팝업창에서의 검색이 있는경우 상세검색을 넣음
	if(popupSearch.equals("Y")){	
		
%>
<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>
<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<table class="tb">
	<col class="cellC" /><col class="cellL" style="width:250" />
	<col class="cellC" /><col class="cellL" />
	<tr>
		<td class="noline" colspan="4">
			<input type="checkbox" name="popupDetail" value="Y" onClick="javascript:popupDetailDiv();" <%=(!"".equals(requestSet.getProperty("popupDetail","")))?"checked":""%>> 상세 검색시 체크를 해주세요
		</td>
	</tr>
	<tr>
		<td>키워드검색</td>
		<td>
			<select name="skey">	
				<option value="all" <%=requestSet.selected("skey","")%>> 통합검색 </option>
				<option value="name" <%=requestSet.selected("skey","name")%>> 회원명 </option>
				<option value="nickname" <%=requestSet.selected("skey","nickname")%>> 닉네임 </option>
				<option value="m_id" <%=requestSet.selected("skey","m_id")%>> 아이디 </option>
				<option value="email" <%=requestSet.selected("skey","email")%>> 이메일 </option>
				<option value="resno" <%=requestSet.selected("skey","resno")%>> 주민번호 </option>
				<option value="phone" <%=requestSet.selected("skey","phone")%>> 전화번호 </option>
				<option value="mobile" <%=requestSet.selected("skey","mobile")%>> 핸폰번호 </option>
				<option value="recommid" <%=requestSet.selected("skey","recommid")%>> 추천인 </option>
				<option value="company" <%=requestSet.selected("skey","company")%>> 회사명 </option>	
			</select> 
			<input type="text" name="sword" value="<%=requestSet.getProperty("sword", "")%>" class="line" />
		</td>
		<td>승인여부/그룹</td>
		<td>
			<select name="sstatus">
				<option value="" <%=requestSet.selected("sstatus","")%>> 전체 </option>
				<option value="1" <%=requestSet.selected("sstatus","1")%>> 승인 </option>
				<option value="0" <%=requestSet.selected("sstatus","0")%>> 미승인 </option>
			</select>
		
			<select name="slevel">
				<option value="">==그룹선택==</option>
		<% for( Object level  : lhmGRP.keySet()){ %> 
				<option value="<%=level%>" <%=requestSet.selected("slevel", level.toString())%>><%=lhmGRP.get(level)%> - lv[<%=level%>]</option>
		<% } %>
			</select>
		</td>
	</tr>
</table>
<div style="padding-top:3;display:none;" id="searchDetail">
<%	
	}	//	if(popupSearch.equals("Y")){
%>

	<table class="tb">
		<col class="cellC" /><col class="cellL" style="width:250" />
		<col class="cellC" /><col class="cellL" />
<%if(!popupSearch.equals("Y")){%>
		<tr>
			<td>키워드검색</td>
			<td>
				<select name="skey">
							<option value="all" <%=requestSet.selected("skey","all")%>> 통합검색 </option>
							<option value="name" <%=requestSet.selected("skey","name")%>> 회원명 </option>
							<option value="nickname" <%=requestSet.selected("skey","nickname")%>> 닉네임 </option>
							<option value="m_id" <%=requestSet.selected("skey","m_id")%>> 아이디 </option>
							<option value="email" <%=requestSet.selected("skey","email")%>> 이메일 </option>
							<option value="resno" <%=requestSet.selected("skey","resno")%>> 주민번호 </option>
							<option value="phone" <%=requestSet.selected("skey","phone")%>> 전화번호 </option>
							<option value="mobile" <%=requestSet.selected("skey","mobile")%>> 핸폰번호 </option>
							<option value="recommid" <%=requestSet.selected("skey","recommid")%>> 추천인 </option>
							<option value="company" <%=requestSet.selected("skey","company")%>> 회사명 </option>
				</select> 
				<input type="text" name="sword" id="sword"  value="<%=requestSet.getProperty("sword", "")%>" class="line" />
			</td>
			<td>승인여부/그룹</td>
			<td>
				<select name="sstatus">
					<option value="" <%=requestSet.selected("sstatus","")%>> 전체 </option>
					<option value="1" <%=requestSet.selected("sstatus","1")%>> 승인 </option>
					<option value="0" <%=requestSet.selected("sstatus","0")%>> 미승인 </option>
				</select>
				<select name="slevel">
					<option value="">==그룹선택==</option>
	<% for( Object level  : lhmGRP.keySet()){ %> 
					<option value="<%=level%>" <%=requestSet.selected("slevel", level.toString())%>><%=lhmGRP.get(level)%> - lv[<%=level%>]</option>
	<% } %>
				</select>
			</td>
		</tr>
<%	
	}	//if(!popupSearch.equals("Y"))
%>
		<tr>
			<td>구매액</td>
			<td>
				₩<input type="text" name="ssum_salemin" value="<%=requestSet.getProperty( "ssum_salemin","")%>" size="10" onkeydown="onlynumber();" class="rline" /> ~
				₩<input type="text" name="ssum_salemax" value="<%=requestSet.getProperty( "ssum_salemax","")%>" size="10" onkeydown="onlynumber();" class="rline" />
			</td>
			<td>적립금</td>
			<td>
				₩<input type="text" name="semoneymin" value="<%=requestSet.getProperty( "semoneymin","")%>" size="10" onkeydown="onlynumber();" class="rline" /> ~
				₩<input type="text" name="semoneymax" value="<%=requestSet.getProperty( "semoneymax","")%>" size="10" onkeydown="onlynumber();" class="rline" />
			</td>
		</tr>
		<tr>
			<td>가입일</td>
			<td colspan="3">
			<input type="text" name="sregdt[]" value="<%=requestSet.getProperty("sregd_0", "")%>" size="10" onkeydown="onlynumber();" onclick="calendar(event);" class="cline" /> ~
			<input type="text" name="sregdt[]" value="<%=requestSet.getProperty("sregd_1", "")%>" size="10" onkeydown="onlynumber();" onclick="calendar(event);" class="cline" />
			<a href="javascript:setDate('sregdt[]',<%=DateUtil.getDate("yyyyMMdd")%>,<%=DateUtil.getDate("yyyyMMdd")%>)"><img src="/resources/shop/admin/img/sicon_today.gif" align="absmiddle" /></a>
			<a href="javascript:setDate('sregdt[]',<%=DateUtil.getDateFrom("yyyyMMdd", -7)%>,<%=DateUtil.getDate("yyyyMMdd")%>)"><img src="/resources/shop/admin/img/sicon_week.gif" align="absmiddle" /></a>
			<a href="javascript:setDate('sregdt[]',<%=DateUtil.getDateFrom("yyyyMMdd", -15)%>,<%=DateUtil.getDate("yyyyMMdd")%>)"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align="absmiddle" /></a>
			<a href="javascript:setDate('sregdt[]',<%=DateUtil.getDateFrom("yyyyMMdd", "-1m")%>,<%=DateUtil.getDate("yyyyMMdd")%>)"><img src="/resources/shop/admin/img/sicon_month.gif" align="absmiddle" /></a>
			<a href="javascript:setDate('sregdt[]',<%=DateUtil.getDateFrom("yyyyMMdd", "-2m")%>,<%=DateUtil.getDate("yyyyMMdd")%>)"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align="absmiddle" /></a>
			<a href="javascript:setDate('sregdt[]')"><img src="/resources/shop/admin/img/sicon_all.gif" align="absmiddle" /></a>
			</td>
		</tr>
		<tr>
			<td>최종로그인</td>
			<td colspan="3">
			<input type="text" name="slastdt[]" value="<%=requestSet.getProperty("slastdt_0", "")%>" size="10" onkeydown="onlynumber();" onclick="calendar(event);" class="cline" /> ~
			<input type="text" name="slastdt[]" value="<%=requestSet.getProperty("slastdt_1", "")%>" size="10" onkeydown="onlynumber();" onclick="calendar(event);" class="cline" />
			<a href="javascript:setDate('slastdt[]',<%=DateUtil.getDate("yyyyMMdd")%>,<%=DateUtil.getDate("yyyyMMdd")%>)"><img src="/resources/shop/admin/img/sicon_today.gif" align="absmiddle" /></a>
			<a href="javascript:setDate('slastdt[]',<%=DateUtil.getDateFrom("yyyyMMdd", -7)%>,<%=DateUtil.getDate("yyyyMMdd")%>)"><img src="/resources/shop/admin/img/sicon_week.gif" align="absmiddle" /></a>
			<a href="javascript:setDate('slastdt[]',<%=DateUtil.getDateFrom("yyyyMMdd", -15)%>,<%=DateUtil.getDate("yyyyMMdd")%>)"><img src="/resources/shop/admin/img/sicon_twoweek.gif" align="absmiddle" /></a>
			<a href="javascript:setDate('slastdt[]',<%=DateUtil.getDateFrom("yyyyMMdd", "-1m")%>,<%=DateUtil.getDate("yyyyMMdd")%>)"><img src="/resources/shop/admin/img/sicon_month.gif" align="absmiddle" /></a>
			<a href="javascript:setDate('slastdt[]',<%=DateUtil.getDateFrom("yyyyMMdd", "-2m")%>,<%=DateUtil.getDate("yyyyMMdd")%>)"><img src="/resources/shop/admin/img/sicon_twomonth.gif" align="absmiddle" /></a>
			<a href="javascript:setDate('slastdt[]')"><img src="/resources/shop/admin/img/sicon_all.gif" align="absmiddle" /></a>
			</td>
		</tr>
		<tr>
			<td>성별</td>
			<td class="noline">
				<input type="radio" name="sex" value="" <%=requestSet.checked("sex", "")%> checked  />전체
				<input type="radio" name="sex" value="m" <%=requestSet.checked("sex", "m")%> />남자
				<input type="radio" name="sex" value="f" <%=requestSet.checked("sex", "f")%> />여자
			</td>
			<%-- <td>연령층</td>
			<td>
				<select name="sage">
					<option value=""   <%=requestSet.selected("sage", "")%>> 전체 </option>
					<option value="10" <%=requestSet.selected("sage", "10")%>> 10대 </option>
					<option value="20" <%=requestSet.selected("sage", "20")%>> 20대 </option>
					<option value="30" <%=requestSet.selected("sage", "30")%>> 30대 </option>
					<option value="40" <%=requestSet.selected("sage", "40")%>> 40대 </option>
					<option value="50" <%=requestSet.selected("sage", "50")%>> 50대 </option>
					<option value="60" <%=requestSet.selected("sage", "60")%>> 60대이상 </option>
				</select>
			</td> --%>
			<td>메일,SMS 수신여부</td>
			<td class="noline">
				<input type="radio" name="mailing" value="" <%=requestSet.checked("mailing","")%> checked="checked"  />전체
				<input type="radio" name="mailing" value="y" <%=requestSet.checked("mailing","y")%> />수신
				<input type="radio" name="mailing" value="n" <%=requestSet.checked("mailing","n")%> />수신거부
			</td>
		</tr>
		<tr>
			<td>방문횟수</td>
			<td>
			<input type="text" name="scnt_loginmin" value="<%=requestSet.getProperty( "scnt_loginmin","")%>" size="10" onkeydown="onlynumber();" class="rline" />회 ~
			<input type="text" name="scnt_loginmax" value="<%=requestSet.getProperty( "scnt_loginmax","")%>" size="10" onkeydown="onlynumber();" class="rline" />회
			</td>
			<td>휴면회원검색</td>
			<td>
			<input type="text" name="dormancy" value="<%=requestSet.getProperty("dormancy", "")%>" size="8" maxlength="8" onkeydown="onlynumber();" class="rline" /> 일 이상 미접속 회원검색
			</td>
		</tr>
		<%-- <tr>
			<td>메일수신여부</td>
			<td class="noline">
				<input type="radio" name="mailing" value="" <%=requestSet.checked("mailing","")%> checked="checked"  />전체
				<input type="radio" name="mailing" value="y" <%=requestSet.checked("mailing","y")%> />수신
				<input type="radio" name="mailing" value="n" <%=requestSet.checked("mailing","n")%> />수신거부
			</td>
			<td>SMS수신여부</td>
			<td class="noline">
				<input type="radio" name="smsyn" value="" <%=requestSet.checked("smsyn","")%> checked="checked" />전체
				<input type="radio" name="smsyn" value="y" <%=requestSet.checked("smsyn","y")%> />수신
				<input type="radio" name="smsyn" value="n" <%=requestSet.checked("smsyn","n")%> />수신거부
			</td>
		</tr> --%>
		<tr>
			<td>생년월일</td>
			<td>
				<%-- <select name="birthtype">
					<option value="" <%=requestSet.selected("birthtype", "")%>> 전체 </option>
					<option value="s" <%=requestSet.selected("birthtype", "s")%>> 양력 </option>
					<option value="l" <%=requestSet.selected("birthtype", "l")%>> 음력 </option>
				</select> --%>
				<input type="text" name="birthdatemin" value="<%=requestSet.getProperty( "birthdatemin","")%>" size="8" maxlength="8" onkeydown="onlynumber();" class="cline" /> -
				<input type="text" name="birthdatemax" value="<%=requestSet.getProperty( "birthdatemax","")%>" size="8" maxlength="8" onkeydown="onlynumber();" class="cline" />
				<div style="padding-left:53px"><font class="ver71" color="627dce">ex) 20080321 
			</td>
			<td>결혼여부/결혼기념일</td>
			<td>
				<select name="marriyn">
					<option value="" <%=requestSet.selected("marriyn", "")%>> 전체 </option>
					<option value="n" <%=requestSet.selected("marriyn", "n")%>> 미혼 </option>
					<option value="y" <%=requestSet.selected("marriyn", "y")%>> 기혼 </option>
				</select>
				<input type="text" name="marridatemin" value="<%=requestSet.getProperty( "marridatemin","")%>" size="8" maxlength="8" onkeydown="onlynumber();" class="cline" /> -
				<input type="text" name="marridatemax" value="<%=requestSet.getProperty( "marridatemax","")%>" size="8" maxlength="8" onkeydown="onlynumber();" class="cline" />
				<div style="padding-left:53px"><font class="ver71" color="627dce">ex) 20080321 
			</td>
		</tr>
<%if (requestSet.getProperty("mobileYN", "").equals("y")){%>
		<tr>
			<td colspan="4">생일자 SMS 전송은 금일생일자, SMS 수신, 양력생일, 핸드폰번호 등록고객에 한해서만 전송이 됩니다.</td>
		</tr>
<%}%>
	</table>
<% 
	if(popupSearch.equals("Y")){	// 팝업창에서의 검색이 있는경우 
%>
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
	if(document.getElementsByName('popupDetail')[0].checked == true){
		document.getElementById('searchDetail').style.display = 'block';
	}else{
		document.getElementById('searchDetail').style.display = 'none';
	}
}
popupDetailDiv();
</script>
<% } %>