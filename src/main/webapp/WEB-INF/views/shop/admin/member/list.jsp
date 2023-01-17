<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp"%>
<%@ include file="../common/left.jsp"%>


<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language=javascript src="/resources/shop/admin/common.js"></script>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript" >
	// 선택 회원 삭제 후 알럿 메시지 출력 
	window.onload = ready();
	
	function ready(){
		var mode = "${memberVO.mode}";		
		if(mode =="delete"){			
			alert("요청하신 내용이 처리 완료되었습니다");
//			document.form02.mode.value = "";
		}
	}
/*
 * jQuery ready
 */
$(function(){
	selectMarriyn();
	
});			

function goPage(page){
	$('.pageNo').val(page);
	$('#form01').submit();
}

//2017-08-29수정 -  페이징 유지 추가
function goView(mid){	
	$('#mid').val(mid);
	$('#mode').val('modify');
	var a = $('#form01').serialize();
 	$('#form02').attr('action', 'register?'+ a);
	$('#form02').submit();
}

function selectMarriyn(){
	if($('select[name=smarriyn]').val() == 'n') {
		$('input[name=marridatemin]').attr('disabled', true);
		$('input[name=marridatemax]').attr('disabled', true);
	} else {
		$('input[name=marridatemin]').attr('disabled', false);
		$('input[name=marridatemax]').attr('disabled', false);
	}
}

/*-------------------------------------
삭제
-------------------------------------*/
function act_delete(){

	if ( PubChkSelect( form02['confirmyn'] ) == false ){
		alert( "삭제하실 내역을 선택하여 주십시요." );
		return;
	}

	if ( confirm( "선택한 아이템을 정말 삭제하시겠습니까?\n삭제 후 복구할 수 없습니다." ) == false ) return;

	var idx = 0;
	var codes = new Array();
	var count = form02['confirmyn'].length;

	if ( count == undefined ) codes[ idx++ ] = form02['confirmyn'].value;
	else {

		for ( i = 0; i < count ; i++ ){
			if ( form02['confirmyn'][i].checked ) codes[ idx++ ] = form02['confirmyn'][i].value;
		}
	}
	
	$('#mode').val('delete');

	form02.nolist.value = codes.join( ";" );
	form02.action = "indb";
	form02.submit() ;
}
/* 
			 
			 //선택 체크 (true or false)
			 var array = new Array();
			//선택 후 선택을 취소했을경우
			var memberarray = new Array();
			//선택 후 선택을 취소했을경우 회원아이디 빼는 변수
			var j =0;
			function iciSelect(obj,objId){				
						var row = obj.parentNode.parentNode;						
						row.style.background = (obj.checked) ? "#F0F4FF" :"#FFFFFF";
						var form02 = document.form02;							
						var chk = document.getElementsByName('chk[]');
						//체크가 되었을경우
						for(i=0; i<chk.length; i++){
							if(chk[i].checked){								
								if(!array[i] || array[i] == 'undefined'){									
									memberarray[j] = objId.value;
									array[i] = true;									
									j++;										
								}							
							}else{			//체크가 되지않았을경우
								//기존 체크가 된 회원들의 배열을 돌린다.
								for(var k=0; k<memberarray.length; k++){	
									//체크했다가 체크풀었을 경우										
									if(!chk[i].checked){
										if(array[i]){												
											if(objId.value == memberarray[k]){
												memberarray[k] = null;
												array[i] = false;
												j--;
											}
										}else{
											array[i] = false;
										}
									}									
								}	
									
							}
						}					
			}
			
			function delMember(fm){
				var form02 = document.form02;	
				form02.deleteMember.value="";
				for(i=0; i<memberarray.length; i++){					
					if(memberarray[i] != null){
						form02.deleteMember.value += memberarray[i]+"/";
					}
				}
				if (!isChked(document.getElementsByName('chk[]'))) return;
				if (!confirm('정말로 하시겠습니까?')) return;
				form02.target = "_self";
				form02.mode.value = "delete";
				form02.action = "list";
				form02.submit();						
			}		
		
		 */
		 
		
		/* function memInfoModify(num,obj){				
			//수정버튼클릭시 , 회원아이디 + 회원리스트선택한정보를 넘겨준다.
			var form02 = document.form02;			
			form02.mid.value = obj.value;			
			form02.action = "info";
			form02.submit();
		} */
		
		function go(){
			if(Boolean($("input[name='birthdatemin']").val())){
				if($("input[name='birthdatemin']").val().length < 8){
					alert("년도,월,일(8자)을 입력하셔야 합니다.");
					$("input[name='birthdatemin']").focus();
					return false;	
				
				}
			} 
			if(Boolean($("input[name='birthdatemax']").val())){
				if($("input[name='birthdatemax']").val().length < 8){
					alert("년도,월,일(8자)을 입력하셔야 합니다.");
					$("input[name='birthdatemax']").focus();
					return false;	
				
				}
			} 
			
			var form01 = document.form01;		
			form01.action = "list";
			form01.submit();
		}
		
		function setEachDate(objStart, objEnd, from, to){		
			var objStart = document.getElementsByName(objStart);
			var objEnd = document.getElementsByName(objEnd);		
			
			objStart[0].value = (from) ? from : "";
			objEnd[0].value=(from) ? to : "";			
		} 
</script>

<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<form name="form01" method="post" action="list" id="form01">
	<input type="hidden" class="pageNo" name="pageNo"  value="${pageNo != '' ? memberVO.pageNo : '1' }"/>
	
	<div class="title title_top">회원리스트<span>현재 내 쇼핑몰의 전체회원을 파악하고 관리하실 수 있습니다</span> <%-- <a href="javascript:popup('http://guide.godo.co.kr/season2/board/view.php?id=member&no=2',870,800)"><img src="../img/btn_q.gif" border="0" align="absmiddle" /></a> --%></div>
	
	<div id="searchDetail">
		<table class="tb">
			<col class="cellC" />
			<col class="cellL" style="width:250" />
			<col class="cellC" />
			<col class="cellL" />
			
			<tr>
				<td>키워드검색</td>
				<td>
					<select name="skey">
						<option value="all" ${stringUtil:selected(memberVO.skey, "all")}> 통합검색 </option>
						<option value="name" ${stringUtil:selected(memberVO.skey, "name")}> 이름 </option>
						<option value="email" ${stringUtil:selected(memberVO.skey, "email")}> E-mail </option>
						<option value="mobile" ${stringUtil:selected(memberVO.skey, "mobile")}> 전화번호 </option>
						<option value="company" ${stringUtil:selected(memberVO.skey, "company")}> 회사명 </option>	
						<option value="busino" ${stringUtil:selected(memberVO.skey, "company")}> 사업자번호 </option>
					</select> 
					<input type="text" name="sword" value="${memberVO.sword}" class="line" />
				</td>
				<td>스킨검색</td>
				<td>
					<select name="schSkin">
						<option value="">전체</option>
						${webUtil:makeSelectCodeItem((codeUtil:codeitem('skin')), memberVO.schSkin) }
					</select>
				</td>
			</tr>
			<tr>
				<td>승인여부</td>
				<td>
					<select name="sstatus">
						<option value="" ${stringUtil:selected(memberVO.sstatus, "")}<%-- <%=requestSet.selected("sstatus","")%> --%>> 전체 </option>
						<option value="1" ${stringUtil:selected(memberVO.sstatus, "1")}<%-- <%=requestSet.selected("sstatus","1")%> --%>> 승인 </option>
						<option value="0" ${stringUtil:selected(memberVO.sstatus, "0")}<%-- <%=requestSet.selected("sstatus","0")%> --%>> 미승인 </option>
					</select>
				</td>
				<td>그룹</td>
				<td>
					<select name="slevel">
						<option value="">==그룹선택==</option>
						<c:forEach items="${codeUtil:getMemberGrp()}" var="mglist" varStatus="st">
							<option value="${mglist.kLevel}" ${stringUtil:selected(memberVO.slevel, fn:trim(mglist.kLevel))}>${mglist.grpnm} - lv[${mglist.kLevel}]</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>구매기간</td>
				<td>
					<input type="text" name="sorderdt" value="${memberVO.sorderdt[0]}<%//=requestSet.getProperty("sregd_0")%>" size="10" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" onclick="calendar(event);" class="cline" maxlength="8"/> ~
					<input type="text" name="sorderdt" value="${memberVO.sorderdt[1]}<%//=requestSet.getProperty("sregd_1")%>" size="10" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" onclick="calendar(event);" class="cline" maxlength="8"/>
				</td>
				<td>구매액</td>
				<td>
					₩<input type="text" name="ssum_salemin" value="${memberVO.ssum_salemin}" size="10" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" class="rline" /> ~
					₩<input type="text" name="ssum_salemax" value="${memberVO.ssum_salemax}" size="10" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" class="rline" />
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
				<td>통관고유부호</td>
				<td><input type="text" name="scustomsnum" value="${memberVO.scustomsnum}" class="line" /> </td>
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
				<td>방문횟수</td>
				<td>
					<select name="scnt_login">
						<option value="tcnt" ${stringUtil:selected(memberVO.scnt_login, "tcnt")}> 전체 </option>
						<option value="wcnt" ${stringUtil:selected(memberVO.scnt_login, "wcnt")}> PC </option>
						<option value="mcnt" ${stringUtil:selected(memberVO.scnt_login, "mcnt")}> MOBILE </option>
						<option value="acnt" ${stringUtil:selected(memberVO.scnt_login, "acnt")}> APP </option>
					</select>
					<input type="text" name="scnt_loginmin" value="${memberVO.scnt_loginmin}<%-- <%=requestSet.getProperty( "scnt_loginmin","")%> --%>" size="10" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" class="rline" />회 ~
					<input type="text" name="scnt_loginmax" value="${memberVO.scnt_loginmax}<%-- <%=requestSet.getProperty( "scnt_loginmax","")%> --%>" size="10" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" class="rline" />회
				</td>
			</tr>
			<tr>
				<td>Push동의여부</td>
				<td class="noline">
					<input type="radio" name="spush" value="" ${stringUtil:checked(memberVO.spush, '')} />전체
					<input type="radio" name="spush" value="y" ${stringUtil:checked(memberVO.spush, 'y')} />동의
					<input type="radio" name="spush" value="n" ${stringUtil:checked(memberVO.spush, 'n')}/>미동의
				</td>
				<td>메일 수신여부</td>
				<td class="noline">
					<input type="radio" name="smailling" value="" ${stringUtil:checked(memberVO.smailling, '')}<%-- <%=requestSet.checked("mailing","")%> --%> checked="checked"  />전체
					<input type="radio" name="smailling" value="y" ${stringUtil:checked(memberVO.smailling, 'y')}<%-- <%=requestSet.checked("mailing","y")%> --%> />수신
					<input type="radio" name="smailling" value="n" ${stringUtil:checked(memberVO.smailling, 'n')}<%-- <%=requestSet.checked("mailing","n")%> --%> />수신거부
				</td>
			</tr>
			<tr>
				<td>휴면회원검색 </td>
				<td colspan="3">
					<input type="text" name="dormancy" value="${memberVO.dormancy}" size="8" maxlength="8" onkeydown="onlynumber(event);" onkeyup="removeHangul(event);" class="rline" /> 일 이상 미접속 회원검색
				</td>
			</tr>
		</table>
	</div>
	
	<div class="button_top">
		<a href="#none" onclick="go()"><input type="image" src="/resources/shop/admin/img/btn_search2.gif" /></a>
	</div>
	<table width="100%">
		<tr>
			<td class="pageInfo">
			<c:set var="pages" value="${(memberVO.rowCount*10) / (memberVO.pageSize*10)} " />
			<c:set var="pageCnt" value="${pages+(1-(pages%1))%1}" />
			<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
			총 <font class="ver8"><b>${memberVO.totalCount }</b>명, 검색 <b>${memberVO.rowCount }</b>명, <b>${memberVO.pageNo }</b> of ${var3 } Pages
			</font></td> 
			<td align="right">
			<select name="sort" onchange="this.form.submit();">
				<option value="regdt desc" ${stringUtil:selected(memberVO.sort, "regdt desc")}>- 가입일 정렬↑</option>
				<option value="regdt asc" ${stringUtil:selected(memberVO.sort, "regdt asc")}>- 가입일 정렬↓</option>
				<option value="last_login desc" ${stringUtil:selected(memberVO.sort, "last_login desc")}>- 최종로그인 정렬↑</option>
				<option value="last_login asc" ${stringUtil:selected(memberVO.sort, "last_login asc")}>- 최종로그인 정렬↓</option>
				<option value="cnt_login desc" ${stringUtil:selected(memberVO.sort, "cnt_login desc")}>- 방문수 정렬↑</option>
				<option value="cnt_login asc" ${stringUtil:selected(memberVO.sort, "cnt_login asc")}>- 방문수 정렬↓</option>
				<optgroup label="------------"></optgroup>
				<option value="name desc" ${stringUtil:selected(memberVO.sort, "name desc")}>- 이름 정렬↑</option>
				<option value="name asc" ${stringUtil:selected(memberVO.sort, "name asc")}>- 이름 정렬↓</option>
				<option value="m_id desc" ${stringUtil:selected(memberVO.sort, "m_id desc")}>- 아이디 정렬↑</option>
				<option value="m_id asc" ${stringUtil:selected(memberVO.sort, "m_id asc")}>- 아이디 정렬↓</option>
				<optgroup label="------------"></optgroup>
				<option value="emoney desc" ${memberVO.sort == 'emoney desc' ? "selected" : "" }>- 적립금 정렬↑</option>
				<option value="emoney asc" ${memberVO.sort == 'emoney asc' ? "selected" : "" }>- 적립금 정렬↓</option>
				<option value="sum_sale desc" ${memberVO.sort == 'sum_sale desc' ? "selected" : "" }>- 구매금액 정렬↑</option>
				<option value="sum_sale asc" ${memberVO.sort == 'sum_sale asc' ? "selected" : "" }>- 구매금액 정렬↓</option>
			</select>&nbsp;
			<select name="pageSize" onchange="this.form.submit();">
				<option value="10" ${stringUtil:selected(memberVO.pageSize, "10")}>10개 출력</option>
				<option value="20" ${stringUtil:selected(memberVO.pageSize, "20")}>20개 출력</option>
				<option value="40" ${stringUtil:selected(memberVO.pageSize, "40")}>40개 출력</option>
				<option value="60" ${stringUtil:selected(memberVO.pageSize, "60")}>60개 출력</option>
				<option value="100" ${stringUtil:selected(memberVO.pageSize, "100")}>100개 출력</option>
			</select>
			</td>
		</tr>
	</table>

</form>


<form class="form02" name="form02" method="post"  id="form02">

<!-- <input type="hidden" name="query" value="select * from gd_member where m_id != 'godomall'  order by regdt desc " /> -->
<input type="hidden" name="mode" id="mode"/>
<input type="hidden" name="swordSrch"  id="swordSrch"/>
<input type="hidden" name="nolist"  id="nolist"/>
<input type="hidden" name="mid"  id="mid" />

<table width="100%" cellpadding="0" cellspacing="0" border="0">
<tr><td class="rnd" colspan="16"></td></tr>
<tr class="rndbg">
	<th>선택</th>
	<th>번호</th>
	<th>스킨</th>
	<th>아이디</th>
	<th>이름</th>
	<th>통관고유부호</th>
	<th>그룹</th>
	<th>적립금</th>
	<th>구매금액</th>
	<th>구매건수</th>
	<th>PC/MO 방문수</th>
	<th>APP방문수</th>
	<th>메일링</th>
	<th>Push</th>
	<th>승인여부</th>
	<th>수정</th>
</tr>
<tr><td class="rnd" colspan="16"></td></tr>
<col width="30" align="center"><!-- 선택 -->
<col width="30" align="center"><!-- 번호 -->
<col width="30" align="center"><!-- 스킨 -->
<col width="80" align="center"><!-- 아이디 -->
<col width="80" align="center"><!-- 이름 -->
<col width="80" align="center"><!-- 통관고유부호 -->
<col width="80" align="center"><!-- 그룹 -->
<col width="80" align="right"> <!-- 적립금 -->
<col width="80" align="right"> <!-- 구매금액 -->
<col width="80" align="right"> <!-- 구매건수 -->
<col width="50" align="center"><!-- PC/MO 방문수 -->
<col width="50" align="center"><!-- APP방문수 -->
<col width="50" align="center"><!-- 메일링 -->
<col width="50" align="center"><!-- Push -->
<col width="30" align="center"><!-- 승인여부 -->
<col width="30" align="center"><!-- 수정 -->

<c:forEach items="${memberVO.gdMemberList }" var="list" varStatus="vnum">

<tr height=30 align="center">
	<td class="noline"><input type=checkbox name=confirmyn value="${list.mno }"></td><!-- 선택 -->
	<td><font class="ver81" color="#616161">${(memberVO.rowCount - vnum.index) - ( memberVO.rowStart ) }</font></td><!-- 번호 -->
	<td><font class="ver81" color="#616161">${codeUtil:getCodeName("skin", list.skin)}</font></td><!-- 스킨 -->
	<td><a href="javascript:popupLayer('../member/popup.emoney?mno=${list.mno}',600,500);"><span id="navig" name="navig" m_id="${list.mid}" m_no="${list.mno}"><font color="#0074ba"><b>${list.mid}</b></font></span></a></td><!-- 아이디 -->
	<td><font class="ver81" color="#616161">${list.name}</font></td><!-- 이름 -->
	<td><font class="ver81" color="#616161">${list.customsnum}</font></td><!-- 통관고유부호 -->
	<!-- 그룹 -->
	<td>
		<font class="def">
			<c:forEach items="${codeUtil:getMemberGrp()}" var="mglist" varStatus="st">
				<c:if test="${mglist.kLevel == list.klevel}">
					<font class="def">${mglist.grpnm}</font><br>
				</c:if>
			</c:forEach>
		</font>
	</td>
	<td align="center"><font class="ver81" color="#0074ba">₩<b>${stringUtil:getMoneyFormatInteger(list.emoney)}</b></font></a></td><!-- 적립금 -->
	<td align="center"><a href="javascript:popup('../member/orderlist?mno=${list.mno }',500,600);"><font class="ver81" color="#0074ba">₩<b>${stringUtil:getMoneyFormatInteger(list.sumsale)}</b></font></a></td><!-- 구매금액 -->
	<td><font class="ver81" color="#616161">${list.cntsale}</font></td><!-- 구매건수 -->
	<td><font class="ver81" color="#616161">${stringUtil:getMoneyFormatInteger(list.wcnt)} / ${stringUtil:getMoneyFormatInteger(list.mcnt)}</font></td><!-- PC/MO 방문수 -->
	<td><font class="ver81" color="#616161">${stringUtil:getMoneyFormatInteger(list.acnt)}</font></td><!-- APP방문수 -->
	<td><font class="small" color="#616161">${list.mailling == "y" ? "허용" : "거부" }</font></td><!-- 메일링 -->
	<td><font class="small" color="#616161">${list.push == "y" ? "허용" : "거부" }</font></td><!-- Push -->
	<td><font class="small" color="#616161">${list.status == "1" ? "승인" : "미승인" }</font></td><!-- 승인여부 -->
	<!-- 수정 -->
	<td>
		<input type="hidden" name="listmid"  value="${list.mid }"/>
		<a href="#" onclick="goView('${list.mid}')" ><img src="/resources/shop/admin/img/i_edit.gif" /></a>
	</td> 
</tr>
</c:forEach>


<tr><td colspan="16" class="rndline"></td></tr>
</table>
</form>

<table cellpadding="0" cellspacing="0" border="0" width="100%">
<tr>
	<td width="20%" height=35 style="padding-left:13px">
	<img src="/resources/shop/admin/img/btn_member_del.gif" border="0" 
	<c:if test="${fn:length(memberVO.gdMemberList)!=0}">
			onclick="javaScript:act_delete();"
		</c:if>
		<c:if test="${fn:length(memberVO.gdMemberList)!=0}">
			onclick="javascript:alert( '데이타가 존재하지 않습니다.' );"
		</c:if>
	/>
	<!-- <a href="javascript:delMember(document.form02);"><img src="../img/btn_member_del.gif" border="0" /></a> --></td>
	<!-- 페이징 -->
	<td width="60%" align="center"><font class="ver8">
	<tags:paginator currentPageNo="${memberVO.pageNo}" rowCount="${memberVO.rowCount}" pageSize="${memberVO.pageSize}"  pageGroupSize="${memberVO.pageGroupSize}" />
	</font></td>
	<td width="20%"></td>
</tr>
</table>




<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

			<%@ include file="../common/bottom.jsp"%>
		</td>
	</tr>
</table>