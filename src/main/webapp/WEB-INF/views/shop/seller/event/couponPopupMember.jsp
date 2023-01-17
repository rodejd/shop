<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script type="text/javascript">

// 선택 회원 삭제 후 알럿 메시지 출력 
window.onload = ready();

function ready(){
	var mode = "${memberVO.mode}";		
	if(mode =="delete"){			
		alert("요청하신 내용이 처리 완료되었습니다");
//		document.form02.mode.value = "";
	}
}
/*
* jQuery ready
*/
$(function(){

});			

function goPage(page){
	//alert(page + ':' + $('#pageNo').val());
	$('#pageNo').val(page);
	$('#form01').submit();
}

/*-------------------------------------
추가
-------------------------------------*/
function exec_add()
{
	var ret;
	var str = new Array();
	var obj = document.getElementsByName('chk[]');
	var tbl = opener.document.getElementById('m_ids');
	ret = false;

	for (i=0;i<obj.length;i++){
		if (obj[i].checked){
			str = obj[i].value.split(':');
			oTr = tbl.insertRow();
			oTd = oTr.insertCell();
			oTd.id = "currPosition";
			oTd.innerHTML = str[1] + '(' + str[2] + ')';
			oTd = oTr.insertCell();
			oTd.innerHTML = "\<input type=text name=mids value='" + str[0] + "' style='display:none'>";
			oTd = oTr.insertCell();
			oTd.innerHTML = "<a href='javascript:void(0)' onClick='del_options(this.parentNode.parentNode)'><img src='/resources/shop/admin/img/i_del.gif' align=absmiddle></a>";
			ret = true;
		}

	}
	if (!ret){
		alert('회원을 선택해주세요');
		return;
	}
	//opener.calSmsCnt();
	self.close();
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

<div class="title title_top">회원검색</div>
<form id="form01" name="form01" method="post" >
	<input type="hidden" name="mid"  value=""/>
	<input type="hidden" id="pageNo" name="pageNo"  value="${pageNo != '' ? memberVO.pageNo : '1' }"/>
	
<%-- 	<tr>
		<td class="noline" colspan="4">
			<input type="checkbox" name="popupDetail" value="Y" onClick="javascript:popupDetailDiv();" <%=(!"".equals(requestSet.getProperty("popupDetail","")))?"checked":""%>> 상세 검색시 체크를 해주세요
		</td>
	</tr>
	 --%>
	 
	<div id="include_search">
		<jsp:include page="../member/_listForm.jsp" flush="true">
			<jsp:param name="popupSearch" value="Y"/>
		</jsp:include>
	</div>
	
	<div class="button_top"><input type="image" src="/resources/shop/admin/img/btn_search2.gif" /></div>
	<table width="100%">
		<tr>
			<td class="pageInfo">
				<c:set var="pages" value="${(memberVO.rowCount*10) / (memberVO.pageSize*10)} " />
				<c:set var="pageCnt" value="${pages+(1-(pages%1))%1}" />
				<fmt:parseNumber var="var3" value="${pageCnt}" integerOnly="true" />
				총 <font class="ver8"><b>${memberVO.totalCount }</b>명, 검색 <b>${memberVO.rowCount }</b>명, <b>${memberVO.pageNo }</b> of ${var3 } Pages
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


<form name="form02" method="post" >
<input type="hidden" name="swordSrch" />
<input type="hidden" name="nolist" />

<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td class="rnd" colspan="12"></td>
	</tr>
	<tr class="rndbg">
		<th><a href="javascript:chkBox(document.getElementsByName('chk[]'),'rev');" class="white">선택</a></th>
		<th>번호</th>
		<th>이름</th>
		<th>아이디</th>
<!-- 		<th>CRM</th> -->
		<th>그룹</th>
		<th>가입일</th>
		<th>최종로그인</th>
		<th>메일</th>
	</tr>
	<tr>
		<td class="rnd" colspan="12"></td>
	</tr>
	<col width="30" align="center">
	<col width="30" align="center">
	<col width="80" align="center" span="2">
	<col width="30" align="center">
	<col width="80" align="center">
	<col width="80" align="center" span="2">
	<col width="50" align="center">
	
	
	
<c:forEach items="${memberVO.gdMemberList }" var="list" varStatus="vnum">


	<tr height=30 align="center">
		<td class="noline"><input type="checkbox" name="chk[]" value="${list.mno }:${list.name}:${list.mid}"></td>
		<td><font class="ver81" color="#616161">${(memberVO.rowCount - vnum.index) - ( memberVO.rowStart ) }</font></td>
		<td>
			<font color="#0074ba"><b>${list.name}</b></font>
		<%-- nickname 삭제
			<br />
			<div style="padding-top:2"><img src="/resources/shop/admin/img/icon_nic.gif" align="absmiddle" /><font class="small1" color="#488d00"><%= rtList.get(i,"nickname") %></font></div>
		 --%>  
		</td>
		<td>
			<font class="ver81" color="#0074ba"><b>${list.mid}</b></font>
		</td>
		<%-- CRM 항목 삭제
		<td>
			<a href="javascript:popupLayer('../member/Crm_view.jsp?m_id=${list.mid}',780,600);"><img src="/resources/shop/admin/img/icon_crmlist<%=rtList.get(i, "sex") %>.gif" /></a>
		</td>
		 --%>
		<td>
			<font class="def">
				<c:forEach items="${codeUtil:getMemberGrp()}" var="mglist" varStatus="st">
					<c:if test="${mglist.kLevel == list.klevel}">
						<font class="def">${mglist.grpnm}</font><br>
					</c:if>
				</c:forEach>
			</font>
		</td>
		<td>
			<font class="ver81" color="#616161"><fmt:formatDate value="${list.regdt}" pattern="yyyy-MM-dd"/></font>
		</td>
		<td>
			<font class="ver81" color="#616161"><fmt:formatDate value="${list.lastlogin}" pattern="yyyy-MM-dd"/></font>
		</td>
		<td>
			<font class="small" color="#616161">${list.email}</font>
		</td>
	</tr>
	<tr>
		<td colspan="12" class="rndline"></td>
	</tr>
	
</c:forEach>	

</table>

</form>

<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td width="20%"></td>
		<td width="60%" align="center">
			<font class="ver8">
				<tags:paginator currentPageNo="${memberVO.pageNo}" rowCount="${memberVO.rowCount}" pageSize="${memberVO.pageSize}"  pageGroupSize="${memberVO.pageGroupSize}" />
			</font>
		</td>
		<td width="20%"></td>
	</tr>
</table>

<div align=center>
	<a href="javascript:exec_add();"><img src="/resources/shop/admin/img/btn_confirm_s.gif"></a>
</div>

	</td>
</tr>
</table>
