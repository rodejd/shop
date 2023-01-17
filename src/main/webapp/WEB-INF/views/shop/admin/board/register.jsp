<%--
/************************************************************************************
* 프로그램명 			: 공통 에러 페이지 
* 관련 SERVICE명 		: 
* 프로그램 내용 		: XMall > 관리자 > 상품관리 > 리스트 
* 작성자	   		 	: 이균
* 작성일자 				: 2014-01-10
*************************************************************************************
* 수정자  	수정일자	수정내용
*************************************************************************************
* 이균		2014-01-10	화면스펙조정
* 최아름	2014-05-26	404 팝업/링크 주석 처리
************************************************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ================================================================================
* 공통 상단 include
* 공통 java class 지정 및 변수 설정
================================================================================ --%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<%-- ================================================================================
* 공통변수의 할당 부분
================================================================================ --%>
<%-- ================================================================================
* 업무 시작 try 부분
================================================================================ --%>
<%-- ================================================================================
* 업무별 거래 로직
================================================================================ --%>
<c:set var="id" value="${boardVO.id !=null and boardVO.id ne ''? boardVO.id : ''}"></c:set>
<c:set var="mode" value="${boardVO.mode !=null and boardVO.mode ne ''? boardVO.mode : 'register'}"></c:set>
<c:set var="rtList" value="${boardVO}"></c:set>


<c:if test="${boardVO.bdMaxSize > 2*1024000}">
	<script type="text/javascript">msg("최대 업로드 제한은 2Mbyte 입니다.");</script>
</c:if>

<%-- ===========================================ㄷ=====================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script language=javascript src="/resources/shop/admin/common.js"></script>
<script>
var skin = new Array();
skin[0] = "default"; 
skin[1] = "gallery"; 
skin[2] = "photo"; 
skin[3] = "webzine";

function createMenus()
{
	var idx	= 0;
	var tmp	= new Array();
	for ( i=0;i<skin.length;i++){
		tmp[i] = "<option value='" + skin[i] + "'>" + skin[i] + "</option>";
		if (skin[i]=='${rtList.bdSkin}') var idx = i;
	}
	SKIN.innerHTML = "<select name=\"bdSkin\" onChange=\"setDisabled(this.value);\">" + tmp.join() + "</select>"; // onChange='setSub(this.value)'
	document.forms[0].bdSkin.options[idx].selected = 1;
	//alert('mode >>>');
	
	if("register"=='${mode}')
	{
		BDID.innerHTML ="<input type='text' name='id' class='line' required label='게시판 ID' option='regAlpha' maxlength='12'/>";
	}
	else if("modify"=='${mode}')
	{
		//alert("[2]");
		BDID.innerHTML ="<input type='hidden' name='id'  value='${id}' /><b>${id}</b> ";
	}
	document.forms[0].bdName.value = '${rtList.bdName}';
	document.forms[0].bdWidth.value = '${rtList.bdWidth}';
	  
	if("on"=='${rtList.bdUseSubSpeech}')
	{
		document.getElementById("UseSubSpeech").checked = true;
		useSubSpeechChk();
		document.forms[0].bdSubSpeechTitle.value = '${rtList.bdSubSpeechTitle}';
		
	}
	//setSub(document.forms[0].bdSkin.value);
	if($('#alert').val() == '1'){
		alert("게시판 아이디가 중복되어 사용할 수 없습니다.");
		$('#alert').val('0');
		location.href = ctx+"/shop/admin/board/register"; 
	}
}

function setSub(skin)
{
	exec_script("sub.js?time=1291272012&skin=" + skin + "&tplSkin=season2&bdImg=");
}

function setDisabled(skin)
{
	var disabled1	= (inArray(skin, new Array('gallery', 'photo')) ? false : true);
	var disabled2	= (inArray(skin, new Array('gallery')) ? false : true);
	
	if(disabled1 == true){
		$('#ListImg').hide();
		$('#ListImgSize').hide();
// 		document.getElementById('ListImg').style.display = 'none';
// 		document.getElementById('ListImgSize').style.display = 'none';
	}else{
		$('#ListImg').show();
		$('#ListImgSize').show();
// 		document.getElementById('ListImg').style.display = 'block';
// 		document.getElementById('ListImgSize').style.display = 'block';
	}
	if(disabled2 == true){
		$('#ListImgCnt').hide();
// 		document.getElementById('ListImgCnt').style.display = 'none';
	}else{
		$('#ListImgCnt').show();
// 		document.getElementById('ListImgCnt').style.display = 'block';
	}
}

function useSubSpeechChk(){
	if( document.getElementById("UseSubSpeech").checked == true ){
		document.getElementById('subSpeechWrite').style.display = 'block';
	}else{
		document.getElementById('subSpeechWrite').style.display = 'none';
	}
}


</script>

<%-- ================================================================================
=====================================================================================
* 화면 시작
=====================================================================================
================================================================================ --%>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/left.jsp" %>

<%-- ================================================================================
* 업무 관련 로직 스크립트
================================================================================ --%>

<script>
$(document).ready(function() {
	if ($('input[name=bdIp]').attr('checked') == 'checked') {
		$('input[name=bdIpAsterisk]').removeAttr('disabled');
	}
});
</script>

<body onload="createMenus();setDisabled('${rtList.bdSkin}');">

<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
<tr>
	<td valign="top" style="padding-left:12px">

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>


	<!-- <form id="form" method="post" action="register" onsubmit="return chkForm(this);"> -->
	<form id="form" method="post" action="indb" onsubmit="return chkForm(this);">
	<input type="hidden" name="mode" value='${mode}' />
	<input type="hidden" name="start" value="1"/>
	<input type="hidden" id="alert" value='${boardVO.alert != null ? boardVO.alert:"0"}'/>
	<div class="title title_top">기본설정<span>커뮤니티 메뉴에서 서비스하는 게시판을 만듭니다</span> </div>
	
	<table class="tb">
		<col class="cellC"><col class="cellL">
		<tbody style="height:27">
		<tr>
			<td>게시판 ID</td>
			<td><span id="BDID"></span> <font class="extext">(영문입력 / 다른 게시판 ID와 중복불가)</font></td>
		</tr>
		<tr>
			<td>게시판 이름</td>
			<td><input type='text' name="bdName" value='' required class='line' maxlength="12"/> <font class="extext">한글입력</font></td>
		</tr>
		<tr>
			<td>스킨 선택<br><font class="small" color="6d6d6d">(게시판스타일)</font></td>
			<td>
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td><div style="position:relative;width:80px;" id="SKIN"></div></td>
					<td><div style="position:relative;" id="IMG"></div></td>
					<td style="padding-left:7"><font class="extext"></font></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr style="display:none;">
			<td>게시판 위치</td>
			<td>
				<select name="bdAlign">
				<option value="center" 	${rtList.bdAlign eq 'center'? 'selected':''}>가운데정렬
				<option value="left"  	${rtList.bdAlign eq 'left'? 'selected':''}>왼쪽정렬
				<option value="right"  	${rtList.bdAlign eq 'center'? 'selected':''}>오른쪽정렬
				</select> <font class="extext">가운데정렬 권장</font>
			</td>
		</tr>
		<tr style="display:none;">
			<td>게시판 넓이</td>
			<td>
				<input type="text" name="bdWidth" size="6" class="rline" value="" /> <font class="extext">% 단위 설정은 꼭 % 를 넣어주세요. 픽셀 단위 설정은 숫자만 입력하세요</font>
			</td>
		</tr>
		<tr style="display:none;">
			<td>작성자 표시방법</td>
			<td>
				<input type="radio" name="bdUserDsp" value="0" class="null" ${rtList.bdUserDsp eq '0'? 'checked' :''} /> 이름표시
				<input type="radio" name="bdUserDsp" value="1" class="null" ${rtList.bdUserDsp eq '1'? 'checked' :''} /> 아이디표시
				<input type="radio" name="bdUserDsp" value="2" class="null" ${rtList.bdUserDsp eq '2'? 'checked' :''} /> 닉네임표시 <font class="extext">(닉네임이 없는 경우에는 이름이 표시됩니다)</font>
			</td>
		</tr>
		<tr style="display:none;">
			<td>관리자 표시방법</td>
			<td>
				<input type="radio" name="bdAdminDsp" value="0" class="null" ${rtList.bdAdminDsp eq '0'? 'checked':'' } /> 이미지로 표시 <font class="extext">(이미지 등록은 <a href="${ctx}/shop/admin/board/list" target="_new"><font class="small1" color="0074ba">게시판리스트</font></a> 에서 등록가능)</font>
				<input type="radio" name="bdAdminDsp" value="1" class="null" ${rtList.bdAdminDsp eq '1'? 'checked' :''} /> 위 작성자 표시방법과 동일하게 표시
			</td>
		</tr>
		<tr>
			<td>말머리 기능</td>
			<td>
				<div class="noline"><input type="checkbox" name="bdUseSubSpeech" id="UseSubSpeech" onclick="useSubSpeechChk();"  /> 말머리 사용 <font class="extext">(글작성시 제목앞에 특정단어를 넣는 기능입니다)</font></div>
				<div id="subSpeechWrite" style="display:none;">
				<table align="left">
				<tr>
					<td>말머리 타이틀</td>
					<td><input type="text" name="bdSubSpeechTitle" size="30" class="line" value="" /></td>
					<td></td>
				</tr>
				<tr>
					<td>말머리 입력</td>
					<td><textarea name="bdSubSpeech" style="width:200px" rows=8>${fn:replace(rtList.bdSubSpeech,'|','\\r\\n')}</textarea></td>
					<td>
					<font class="extext">- 여러개의 말머리를 등록할 수 있습니다</font><br />
					<div style="padding-top:1"><font class="extext">- 글작성시 말머리를 선택할 수 있습니다</font></div>
					<div style="padding-top:1"><font class="extext">- 엔터로 구분을 해주세요</font></div>
					<div style="padding-top:1"><font class="extext">- 말머리명을 변경 또는 삭제시 기존게시판에는 적용이 되지 않습니다</font></div>
					</td>
				</tr>
				</table>
				</div>
			</td>
		</tr>
	</table>
	
	<div class="title">권한설정 및 스팸설정<span>커뮤니티 메뉴에서 서비스하는 게시판을 만듭니다 </span></div>
	
	<table class="tb" >
	<col class="cellC"><col class="cellL">
	<tbody style="height:27">
	<tr>
		<td>권한 설정</td>
		<td>
		<table align="left" border=0>
		<tr>
			<td align="center">리스트보기</td>
			<td align="center">글내용보기</td>
			<td align="center">코멘트달기</td>
			<td align="center">글쓰기</td>
		</tr>
		<tr>
			<td>
				<select name="bdLvlL">
				<option value='0' ${rtList.bdLvlL eq '' ? 'selected' :''}>제한없음</option>
				<%-- <option value="1"  style="background-color:#E9FFE9" ${rtList.bdLvlL eq '1' ? 'selected' :''}>일반회원 - lv[1]</option>
				<option value="2"  style="background-color:#E9FFE9" ${rtList.bdLvlL eq '2' ? 'selected' :''}>고급회원 - lv[2]</option>
				<option value="80"  style="background-color:#E9FFE9" ${rtList.bdLvlL eq '80' ? 'selected' :''}>부관리자 - lv[80]</option>
				<option value="99"  style="background-color:#E9FFE9" ${rtList.bdLvlL eq '99' ? 'selected' :''}>관리자 - lv[99]</option> --%>
				<c:forEach items="${codeUtil:getMemberGrp()}" var="mglist" varStatus="st">
					<option value="${mglist.kLevel}" ${stringUtil:selected(rtList.bdLvlL, fn:trim(mglist.kLevel))}>${mglist.grpnm} - lv[${mglist.kLevel}]</option>
				</c:forEach>
				</select>
			</td>
			<td>
				<select name="bdLvlR">
				<option value='0' ${rtList.bdLvlR eq '' ? 'selected':''}>제한없음</option>
				<c:forEach items="${codeUtil:getMemberGrp()}" var="mglist" varStatus="st">
					<option value="${mglist.kLevel}" ${stringUtil:selected(rtList.bdLvlR, fn:trim(mglist.kLevel))}>${mglist.grpnm} - lv[${mglist.kLevel}]</option>
				</c:forEach>
				</select>
			</td>
			<td>
				<select name="bdLvlC">
				<option value='0' ${rtList.bdLvlC eq''? 'selected' :''}>제한없음</option>
				<c:forEach items="${codeUtil:getMemberGrp()}" var="mglist" varStatus="st">
					<option value="${mglist.kLevel}" ${stringUtil:selected(rtList.bdLvlC, fn:trim(mglist.kLevel))}>${mglist.grpnm} - lv[${mglist.kLevel}]</option>
				</c:forEach>
				</select>
			</td>
			<td>
				<select name="bdLvlW">
				<option value='0' ${rtList.bdLvlW eq '' ? 'selected':''}>제한없음</option>
				<c:forEach items="${codeUtil:getMemberGrp()}" var="mglist" varStatus="st">
					<option value="${mglist.kLevel}" ${stringUtil:selected(rtList.bdLvlW, fn:trim(mglist.kLevel))}>${mglist.grpnm} - lv[${mglist.kLevel}]</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<div style="padding:3 0 6 0"><a href="${ctx}/shop/admin/member/group/list" target="_new"><font class="extext_l">[그룹관리]</font></a> <font class=extext>에서 그룹을 만드세요</font></div>
			<div><font class=extext>그룹권한시 설정 권한 보다 그룹 레벨이 높은 등급은 전부 권한이 있습니다.</font></div>
			</td>
		</tr>
		</table>
	
		</td>
	</tr>
	</table>
	
	<div class="title">리스트화면설정<span>커뮤니티 메뉴에서 서비스하는 게시판을 만듭니다</span></div>
	
	<table class="tb">
	<col class="cellC"><col class="cellL">
	<tbody style="height:27">
	<tr>
		<td>제목글수 제한</td> 
		<td>
			<input type="text" name="bdStrlen" size="6" class="rline" value='${rtList.bdStrlen}' onkeydown="onlynumber();" maxlength='6'/> 글자수 제한
			&nbsp;<font class="extext">(미작성시 제한 없음, 제한 글자수 이상의 경우는 이후 글씨는 보이지 않음)</font>
		</td>
	</tr>
	<tr>
		<td>페이지당 게시물수</td>
		<td>
			<input type="text" name="bdPageNum" size="6" class="rline" value='${rtList.bdPageNum}' onkeydown="onlynumber();" maxlength ='3'/> 개
			&nbsp;<font class="extext">(기본 20개 출력, gallery 스킨의 경우는 아래 이미지 갯수에서 설정 사용)</font>
		</td>
	</tr>
	<tr>
		<td>NEW아이콘 효력</td>
		<td>
			<input type="text" name="bdNew" size="6" class="rline" value='${rtList.bdNew}' onkeydown="onlynumber();" maxlength ='4' /> 시간
			&nbsp;<font class="extext">(미작성시 사용안함)</font>
		</td>
	</tr>
	<tr>
		<td>HOT아이콘 조건</td>
		<td>
			조회수 <input type="text" name="bdHot" class="rline" size="6" value='${rtList.bdHot}' onkeydown="onlynumber();" maxlength ='5'/> 회 이상 게시글
			&nbsp;<font class="extext">(미작성시 사용안함)</font>
		</td>
	</tr>
	<tr>
		<td>공지글 출력 설정</td>
		<td class="noline">
		<c:set var="noticlistChkStr" value="n"></c:set>
		<c:choose>
			<c:when test="${rtList.bdNoticeList eq'' }">
				<c:set var="noticlistChkStr" value="n"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="noticlistChkStr" value="c"></c:set>
			</c:otherwise>
		</c:choose>
			<input type="radio" name="bdNoticeList" value="" ${rtList.bdNoticeList eq '' ? 'checked' :''} /> 1페이지에만 출력
			<input type="radio" name="bdNoticeList" value="o" ${rtList.bdNoticeList eq 'o' ? 'checked' :''} /> 모든페이지 출력
		</td>
	</tr>
	<tr style="display:none;">
		<td>항목감추기</td>
		<td class="noline">
		<c:set var="field2" value="${rtList.bdField}"></c:set>
		<fmt:formatNumber value="${field}"/>
		<%-- <% 
			long field = 0;
			
			field = rtList.getInt("bdField");
		%> --%>
		<%-- <input type="checkbox" name="bdField" value="1" ${(field &amp 1) eq '1'? 'checked':''}/> 체크 
		<input type="checkbox" name="bdField" value="2" ${(field &amp 2) eq '2'? 'checked':''} /> 번호
		<input type="checkbox" name="bdField" value="4" ${(field &amp 4) eq '4'? 'checked':''} /> 제목
		<input type="checkbox" name="bdField" value="8" ${(field &amp 8) eq '8'? 'checked':''} /> 이름
		<input type="checkbox" name="bdField" value="16" ${(field &amp 16) eq '16'? 'checked':''} /> 날짜
		<input type="checkbox" name="bdField" value="32" ${(field &amp 32) eq '32'? 'checked':''} /> 조회수 --%>
	
		</td>
	</tr>
	<tr style="display:none;">
		<td>검색 모드</td>
		<td class="noline">
			<input type="radio" name="bdSearchMode" value="0" ${bdSearchMode eq '0'? 'checked':''} /> 일반 검색 (검색시 풀스캔)
			<input type="radio" name="bdSearchMode" value="1" ${bdSearchMode eq '1'?'checked':''} /> 권장 검색 (검색시 부하를 줄이기 위해 페이징 제한)
		</td>
	</tr>
	<tr style="display:none;">
		<td>리스트<br>
		출력정보 선택<br><font class="small" color="6d6d6d">(list)</font></td>
		<td class="noline">
			<div><input type="radio" name="bdPrnType" value="1" ${bdPrnType eq '1'?'checked':''} /> 기본정보 (제목,작성일,조회수,코멘트수,업로드파일1개)&nbsp;<font class="extext">(default, gallery, photo 스킨은 기본정보로 선택하세요)</font></div>
			<div><input type="radio" name="bdPrnType" value="2" ${bdPrnType eq '2'?'checked':''} /> 상세정보 (기본정보를 포함한 리스트출력에 필요한 모든 데이타)&nbsp;<font class="extext">(webzine 스킨은 상세정보로 선택하세요)</font></div>
		</td>
	</tr>
	<tr style="display:none;">
		<td>썸네일 이미지 갯수</td>
		<td>
			<input type="text" name="bdListImgCntW" size="6" class="rline" value='${rtList.bdListImgCntW}' onkeydown="onlynumber();" /> X
			<input type="text" name="bdListImgCntH" size="6" class="rline" value='${rtList.bdListImgCntH}' onkeydown="onlynumber();" /> 
			&nbsp;<font class="extext">(gallery 스킨만 사용. 갤러리타입 게시판 리스트에 나오는 썸네일이미지 갯수)</font>
		</td>
	</tr>
	<tr style="display:none;">
		<td>썸네일 이미지 크기</td>
		<td>
			<input type="text" name="bdListImgSizeW" id="ListImgSizeW" size="6" class="rline" value='${rtList.bdListImgSizeW}' onkeydown="onlynumber();" /> Pixel X
			<input type="text" name="bdListImgSizeH" id="ListImgSizeH" size="6" class="rline" value='${rtList.bdListImgSizeH}' onkeydown="onlynumber();" /> Pixel
			&nbsp;<font class="extext">(gallery, photo 스킨만 사용)</font>
		</td>
	</tr>
	<tr style="display:none;">
		<td>이미지 클릭설정</td>
		<td class="noline">
			<input type="radio" name="bdListImg" value="1" checked disabled /> 이미지 클릭시 팝업창이 뜹니다&nbsp;
			<input type="radio" name="bdListImg" value="2"  disabled /> 이미지 클릭시 글내용으로 이동&nbsp;
			&nbsp;<font class="extext">(gallery, photo 스킨만 사용)</font> 
		</td>
	</tr>
	</table>
	
	<div class="title">상세화면설정<span>커뮤니티 메뉴에서 서비스하는 게시판을 만듭니다 </span></div>
	
	<table class="tb">
	<col class="cellC"><col class="cellL">
	<tbody style="height:27">
	<tr style="display:none;">
		<td>View 타입</td>
		<td class="noline">
			<input type="radio" name="bdTypeView" value="0" checked class="noline" ${rtList.bdTypeView eq '0' ?'checked':''}/> 내용만 <input type="radio" name="bdTypeView" value="1"  class="noline" ${rtList.bdTypeView eq '1' ? 'checked':''}/> 관련글 <input type="radio" name="bdTypeView" value="2"  class="noline" ${rtList.bdTypeView eq '2' ? 'checked':''} /> 리스트 	</td>
	</tr>
	<tr>
		<td>IP 출력</td>
		<td class="noline">
			<input type="checkbox" name="bdIp"  onclick="this.form['bdIpAsterisk'].disabled = !this.checked" ${rtList.bdIp eq 'on' ?'checked':''}/> 글쓴이의 IP를 보여줍니다
			<div style="padding: 2px 0 3px 0"><input type="checkbox" name="bdIpAsterisk" ${rtList.bdIpAsterisk eq 'on' ?'checked':''} disabled  /> IP 끝자리 암호화표기 <font class=extext>예)</font> <font class="ver71" color="#627dce">123.213.139.***</font></div>
		</td>
	</tr>
	<tr>
		<td>링크/업로드</td>
		<td class="noline">
			<input type="checkbox" name="bdUseLink" ${rtList.bdUseLink eq 'on' ?'checked':''} /> 링크 &nbsp; &nbsp; &nbsp;
			<input type="checkbox" name="bdUseFile" ${rtList.bdUseFile eq 'on' ?'checked':''} /> 파일업로드 <font class="extext">(Gallery, Photo 스킨 사용시 꼭 체크하세요!)</font>
		</td>
	</tr>
	<tr>
		<td>코멘트(댓글)기능</td>
		<td class="noline"><input type="checkbox" name="bdUseComment" ${rtList.bdUseComment eq 'on' ?'checked':''} /> 사용</td>
	</tr>
	</table>
	
	<div class="title">작성화면설정<span>커뮤니티 메뉴에서 서비스하는 게시판을 만듭니다 </span></div>
	
	<table class="tb">
	<col class="cellC"><col class="cellL">
	<tbody style="height:27">
	<tr>
		<td>비밀글 설정</td>
		<td>
			<input type="radio" name="bdSecretChk" value="0" class="null" ${rtList.bdSecretChk eq '0' ?'checked':''}  /> 작성시 기본 일반글
			<input type="radio" name="bdSecretChk" value="1" class="null" ${rtList.bdSecretChk eq '1' ?'checked':''} /> 작성시 기본 비밀글
			<input type="radio" name="bdSecretChk" value="2" class="null" ${rtList.bdSecretChk eq '2' ?'checked':''} /> 무조건 일반글
			<input type="radio" name="bdSecretChk" value="3" class="null" ${rtList.bdSecretChk eq '3' ?'checked':''} /> 무조건 비밀글
		</td>
	</tr>
	<tr style="display:none;">
		<td>제목작성 설정</td>
		<td>
			<input type="checkbox" name="bdTitleCChk" class="null" ${rtList.bdTitleCChk eq 'on' ?'checked':''} /> 글자색 사용
			<input type="checkbox" name="bdTitleSChk" class="null" ${rtList.bdTitleSChk eq 'on' ?'checked':''} /> 글자크기 사용
			<input type="checkbox" name="bdTitleBChk" class="null" ${rtList.bdTitleBChk eq 'on' ?'checked':''} /> 글자굵기 사용
		</td>
	</tr>
	<tr>
		<td>업로드파일 Size</td>
		<td>
			<input type="text" name="bdMaxSize" size="6" class="rline" value='${rtList.bdMaxSize}' onkeydown="onlynumber();" maxlength='7'/> Byte
			<font class="extext">(파일 업로드시 파일크기를 제한합니다.)</font>
		</td>
	</tr>
	<tr>
		<td>이메일 작성</td>
		<td>
			<input type="checkbox" name="bdEmailNo" class="null" ${rtList.bdEmailNo eq 'on'? 'checked' :''} /> 이메일 작성 미사용
		</td>
	</tr>
	<tr>
		<td>홈페이지 작성</td>
		<td>
			<input type="checkbox" name="bdHomepageNo" class="null" ${rtList.bdHomepageNo eq 'on' ? 'checked' :''} /> 홈페이지 작성 미사용
		</td>
	</tr>
	
	</table>
	
	<div class="title" style="display:none;">HTML설정<span>커뮤니티 메뉴에서 서비스하는 게시판을 만듭니다</span></div>
	
	<table class="tb">
	<col class="cellC"><col class="cellL">
	<tbody style="height:27">
	<tr style="display:none;">
		<td>상단디자인<br>(Header)</td>
		<td>
			<textarea name="bdHeader" style="width:100%" rows=8 class=tline>${rtList.bdHeader}</textarea>
		</td>
	</tr>
	<tr style="display:none;">
		<td>하단디자인<br>(Footer)</td>
		<td>
			<textarea name="bdFooter" style="width:100%" rows=8 class=tline>${rtList.bdFooter}</textarea>
		</td>
	</tr>
	</table>
	
	
	<div style="padding:20px" align="center" class="noline">
		<div class="button">
		<input type="image" src="/resources/shop/admin/img/btn_${mode}.gif" />
		<a href="list"><img src="/resources/shop/admin/img/btn_list.gif" /></a>
		</div>
	</div>
	
	</form>

<script>useSubSpeechChk();</script>

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
