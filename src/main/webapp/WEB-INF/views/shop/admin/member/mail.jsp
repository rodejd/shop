<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>
<script type="text/javascript">
/*
 * jQuery ready
 */
$(function(){
	
});
	//선택 체크 (true or false)
	var array = new Array();
	//선택 후 선택을 취소했을경우
	var memberarray = new Array();
	//선택 후 선택을 취소했을경우 회원아이디 빼는 변수
	var memberEmail = new Array();
	var j =0;
	function iciSelect(obj,objId, m_no){				
				var row = obj.parentNode.parentNode;						
				row.style.background = (obj.checked) ? "#F0F4FF" :"#FFFFFF";
				var form02 = document.form02;							
				var chk = document.getElementsByName('chk[]');
				//체크가 되었을경우
				for(var i=0; i<chk.length; i++){
					if(chk[i].checked){								
						if(!array[i] || array[i] == 'undefined'){									
							memberarray[j] = objId.value;
							memberEmail[j] = document.getElementById("email_"+m_no).value;
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
										memberEmail[k] = null;
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
	function sendMail(fm)
	{
		if ($('#type').val() == "select" && !isChked(document.getElementsByName('chk[]'))) 
			return false;
		
		document.fmList.target = "ifrmEmail";
		document.fmList.action = "email?ifrmScroll=1";
		document.fmList.submit();
		openLayer('objEmail','block');

	}
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
	function goPage(page){
		$("#pageNo").val(page);
		document.form01.submit();
// 		window.location.href="list?type=${bannerVO.type}&pageNo="+page;
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

<br/>
<!-- <b>Warning</b>:  file(/home1/gnumall/shop/lib/../conf/mail.cnt.jsp) [<a href='function.file'>function.file</a>]: failed to open stream: Permission denied in <b>/home1/gnumall/shop/lib/library.jsp</b> on line <b>83</b><br /> -->
<form name="form01" id="form01" method="post" onsubmit="return false;">
<input type="hidden" name="m_id"  value=""/>
<input type="hidden" name="page_no"  value=""/>
<input type="hidden" name="gopage_num"  value=""/>
<input type="hidden" name="indicate" value="search" />
<input type="hidden" name="pageNo" id="pageNo" value="${memberVO.pageNo == '' ? 1 : memberVO.pageNo}" />
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">
	<div class="title title_top">개별/전체 메일보내기<span>검색한 회원에게 메일을 일괄발송할 수 있습니다.</span></div>

<%@ include file="./_listForm.jsp" %>
<br><br>

<div align="center">
	<a href="#none" onclick="go()"><input type="image" src="/resources/shop/admin/img/btn_search2.gif" /></a>
</div>
<div id=MSG01>

<table cellpadding=1 cellspacing=0 border=0 class=small_ex>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">개별/전체 메일보내기 서비스는 한달에 3,000건만 발송이 가능합니다.</td></tr>
<tr><td><img src="/resources/shop/admin/img/icon_list.gif" align="absmiddle">한달에 3,000건 발송후에는 <b>파워메일발송하기</b>를 이용해주세요!</td></tr>
</table>
</div>
<script>cssRound('MSG01')</script>
<div style="padding-top:5px"></div>
<div style="padding:10 0 5 5"><font class="def1" color="#000000"><b><font size="3">①</font> 먼저 아래에서 메일을 발송할 회원을 검색합니다</b></font></div>
<table width="100%">
<tr>
	<td class="pageInfo">
	<c:set var="pages" value="${(memberVO.rowCount*10) / (memberVO.pageSize*10)} " />
		<c:set var="pageCnt" value="${pages+(1-(pages%1))%1}" />
		<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
	총 <font class="ver8"><b>${memberVO.totalCount}</b>명, 검색 <b>${memberVO.rowCount}</b>명,<b>${memberVO.pageNo}</b> of ${var3} Pages <br>
	</td>
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

<form name="fmList" id="fmList" method="post" onsubmit="return chkFuncForm(this)">
<input type=hidden name=mode>
<input type=hidden name=query value="">

<table width="100%" cellpadding="0" cellspacing="0" border="0">
<tr><td class="rnd" colspan="14"></td></tr>
<tr class="rndbg">
	<th><a href="javascript:chkBox(document.getElementsByName('chk[]'),'rev');" class="white">선택</a></th>
	<th>번호</th>
	<th>이름</th>
	<th>아이디</th>
	<th>이메일</th>
	<th>그룹</th>
	<th>적립금</th>
	<th>구매금액</th>
	<th>방문수</th>
	<th>가입일</th>
	<th>최종로그인</th>
	<th>메일링</th>
	<th>승인여부</th>
	<!-- <th>수정</th> -->
</tr>
<tr>	
	<td class="rnd" colspan="14">
	</td>
</tr>
<col width="30" align="center">
<col width="30" align="center">
<col width="80" align="center" span="2">
<col width="30" align="center">
<col width="80" align="center"> 
<col width="80" align="right">
<col width="80" align="right">
<col width="50" align="center">
<col width="80" align="center" span="2">
<col width="50" align="center">
<col width="30" align="center">
<!-- <col width="30" align="center"> -->

<c:forEach items="${memberVO.gdMemberList}" var="list" varStatus="st">
<tr height=30 align="center">
	<td class="noline"><input type="checkbox" name="chk[]" id="chk[]" value="${list.mno}" onclick="iciSelect(this);"></td>
	<td><font class="ver81" color="#616161">${(memberVO.rowCount - st.index) - ( (memberVO.pageNo - 1)  *  10 ) }</font></td>
	<td>
	<span id="navig" name="navig" m_id="${list.mid}" m_no="${list.mno}"><font color="#0074ba"><b>${list.name}</b></font></span>
	
		</td>
	<td><span id="navig" name="navig" m_id="${list.mid}" m_no="${list.mid}"><font class="ver81" color="#0074ba"><b>${list.mid}</b></font></span></td>
	<td><font class="ver81" color="#0074ba"><b>${list.email}</b></font></td>
	<td>
		<c:forEach items="${codeUtil:getMemberGrp()}" var="mglist" varStatus="st">
			<c:if test="${mglist.kLevel == list.klevel}">
				<font class="def">${mglist.grpnm}</font><br>
			</c:if>
		</c:forEach>
	</td>
	<td align="center"><a href="javascript:popupLayer('${ctx}/shop/admin/member/popup.emoney?mno=${list.mno}',600,500);"><font class="ver81" color="#0074ba">₩<b>${stringUtil:getMoneyFormatInteger(list.emoney)}</b></font></a></td>
	<td align="center"><a href="javascript:popup('${ctx}/shop/admin/member/orderlist?mno=${list.mno}',500,600);"><font class="ver81" color="#0074ba">₩<b>${stringUtil:getMoneyFormatInteger(list.sumsale)}</b></font></a></td>
	<td><font class="ver81" color="#616161">${list.cntlogin}</font></td>
	<td><font class="ver81" color="#616161"><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd"/></font></td>
	<td><font class="ver81" color="#616161"><fmt:formatDate value="${list.lastlogin}" pattern="yyyy-MM-dd"/></font></td>
	<td><font class="small" color="#616161">${list.mailling == "y" ? "허용" : "거부" }</font></td>
	<td><font class="small" color="#616161">${list.status == "1" ? "승인" : "미승인" }</font></td>
</tr>
</c:forEach>

<tr><td colspan="14" class="rndline"></td></tr>
</table>

<!-- 페이징  -->
<table cellpadding="0" cellspacing="0" border="0" width="100%">
<tr>
	<td width="100%" align="center">
		<font class="ver8">
		<tags:paginator currentPageNo="${memberVO.pageNo}" rowCount="${memberVO.rowCount}" pageSize="${memberVO.pageSize}"  pageGroupSize="${memberVO.pageGroupSize}" />
		</font>
	</td>
	<td width="20%"></td>
</tr>
</table>

<table width=100% cellpadding=0 cellspacing=0 border=0>
<tr><td width=6% style="padding-left:7"><a href="javascript:chkBox(document.getElementsByName('chk[]'),'rev')"><img src="/resources/shop/admin/img/btn_allchoice.gif"></a></td>
<td width=88% align=center><div class=pageNavi><font class=ver8></font></div></td>
<td width=6%></td>
</tr></table><table bgcolor=F7F7F7 width=100%>
<tr>
	<td class=noline width=57% align=right>
	<select name="type" id="type">
	<option value="select">선택한 회원들에게
	<option value="query">현재 검색리스트에 있는 모든 회원에게
	<option value="direct">특정회원에게 (직접입력)
	</select>
	</td>
	<td width=43% style="padding-left:10px">
	<a href="javascript:void(0)" onClick="sendMail(document.fmList)"><img src="/resources/shop/admin/img/btn_mailtomember.gif" border=0></a>	
	</td>
</tr>
</table>
<p>
<div id=objEmail style="display:none">
<iframe name="ifrmEmail" id="ifrmEmail" style="width:100%;height:750px" frameborder=0></iframe>
</div>

</form>

<script>window.onload = function(){ UNM.inner();};</script>

<script>
linecss();
table_design_load();
</script>

<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

<%@ include file="../common/bottom.jsp" %>
	</td>
</tr>
</table>