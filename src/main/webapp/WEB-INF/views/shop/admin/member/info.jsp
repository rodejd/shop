<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<%@ include file="../common/header.jsp"%>
<%@ include file="../common/left.jsp"%>


<%-- ================================================================================
* 공통 스크립트/CSS include
================================================================================ --%>
<script type="text/javascript">
	/*
	 * jQuery ready
	 */
	$(function(){
		
		var result = '${result}';
		if("success" == result){
			alert("요청하신 내용이 처리 완료되었습니다.");
			location.href="register?mode=modify&mid="+'${memberVO.mid}';
		}
	});	 
<!--
function chkFormMember( fobj ){

	if ( fobj['mod_pass'].checked ){

		if ( fobj['password'].value == '' ){
			alert( "[비밀번호] 필수입력사항" );
			fobj['password'].focus();
			return false;
		}

		if ( fobj['password'].value != fobj['password2'].value ){
			alert( "[새비밀번호]와 [비밀번호확인] 데이타가 다릅니다." );
			fobj['password'].value = fobj['password2'].value = '';
			fobj['password'].focus();
			return false;
		}
	}

	if ( fobj['mod_resno'].checked ){

		if ( fobj['n_resno[]'][0].value == '' ){
			alert( "[주민등록번호] 필수입력사항" );
			fobj['n_resno[]'][0].focus();
			return false;
		}

		if ( fobj['n_resno[]'][1].value == '' ){
			alert( "[주민등록번호] 필수입력사항" );
			fobj['n_resno[]'][1].focus();
			return false;
		}
	}

	if ( !chkForm(fobj) ) return false;
}
//-->

	
	function validation(){
		var frmMember = document.frmMember;		
		//alert($('input[name="mod_pass"]').is(':checked'));
		if($('input[name="modpass"]').is(':checked')){
			if(frmMember.password.value != frmMember.password2.value){
				alert("비밀번호가 일치하지 않습니다");
				frmMember.password.value = "";
				frmMember.password2.value = "";
				frmMember.password.focus();
				return false;
			}	 
			
			
			if($('#password').val() == "" || $('#password2').val() == ""){
				alert("비밀번호를 확인해주세요.");
				frmMember.password.value = "";
				frmMember.password2.value = "";
				frmMember.password.focus();
				return false;
			}
		}
		
		if( $("#customsnum").val() != "" ){
			if(!$("#customsnum").val().startsWith("P") || $("#customsnum").val().length != 13){
				alert("개인통관고유부호를 확인해주세요.");
				$("#customsnum").focus();
				return false;
			}
		}
		
		return true;
	}
	function modifyInfo(){		
		if(validation()){
			var frmMember = document.frmMember;
			frmMember.action = "indb";
			frmMember.submit();
		}		
	}
	function goList(){
		var frmMember = document.frmMember;
		frmMember.action = "list";
		frmMember.submit();
	}
</script>
<%-- ================================================================================
* 공통 HTML CONTENT 시작
================================================================================ --%>
<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<!-- <form name="frmMember" method="post" action="indb.jsp" onsubmit="return chkFormMember(this);"> -->

<form name="frmMember" id="frmMember" method="post" onsubmit="return false;">
<input type="hidden" name="mode" value="modify" />
<input type="hidden" name="mid" value="${memberVO.mid }" />
<input type="hidden" name="marriyn" value="n" />
<%-- <input type="hidden" name="returnUrl" value="<%=returnUrl%>" /> --%>

<!-- 2017-08-29 페이징 유지 추가 -->
<input type="hidden" name="sort" value="${memberVO.sort }" />
<input type="hidden" name="func" value="${memberVO.func }" />
<input type="hidden" name="skey" value="${memberVO.skey }" />
<input type="hidden" name="sword" value="${memberVO.sword }" />
<input type="hidden" name="srchSave" value="${memberVO.srchSave }" />
<input type="hidden" name="sstatus" value="${memberVO.sstatus }" />
<input type="hidden" name="slevel" value="${memberVO.slevel }" />
<input type="hidden" name="ssum_salemax" value="${memberVO.ssum_salemax }" />
<input type="hidden" name="ssum_salemin" value="${memberVO.ssum_salemin }" />
<input type="hidden" name="semoneymin" value="${memberVO.semoneymax }" />
<input type="hidden" name="sex" value="${memberVO.sex }" />
<input type="hidden" name="sage" value="${memberVO.sage }" />
<input type="hidden" name="scnt_loginmin" value="${memberVO.scnt_loginmin }" />
<input type="hidden" name="scnt_loginmax" value="${memberVO.scnt_loginmax }" />
<input type="hidden" name="dormancy" value="${memberVO.dormancy }" />
<input type="hidden" name="smailling" value="${memberVO.smailling }" />
<input type="hidden" name="smsyn" value="${memberVO.smsyn }" />
<input type="hidden" name="birthtype" value="${memberVO.birthtype }" />
<input type="hidden" name="birthdatemin" value="${memberVO.birthdatemin }" />
<input type="hidden" name="birthdatemax" value="${memberVO.birthdatemax }" />
<input type="hidden" name="marridatemin" value="${memberVO.marridatemax }" />
<input type="hidden" name="smarriyn" value="${memberVO.smarriyn }" />
<input type="hidden" name="mobileYN" value="${memberVO.mobileYN }" />
<input type="hidden" name="sregdt" value="${memberVO.sregdt_0 }" />
<input type="hidden" name="sregdt" value="${memberVO.sregdt_1 }" />
<input type="hidden" name="slastdt" value="${memberVO.slastdt_0 }" />
<input type="hidden" name="slastdt" value="${memberVO.slastdt_1 }" />
<input type="hidden" name="pageNo" value="${memberVO.pageNo }" />
<input type="hidden" name="pageSize" value="${memberVO.pageSize }" />

<div class="title title_top">회원정보</div>

<table class="tb">
<col class="cellC" /><col style="padding-left:10px;width:600;" />
<col class="cellC" /><col style="padding-left:10px" />
<tr>
	<td>아이디</td>
	<td><b>${memberVO.memberObj.mid}</b></td>
	<td>승인</td>
	<td class="noline">
	<input type="radio" name="status" value="1" ${memberVO.memberObj.status == 1 ? 'checked' : '' } /> 승인
	<input type="radio" name="status" value="0" ${memberVO.memberObj.status != 1 ? 'checked' : '' } /> 미승인
	</td>
</tr>
<tr>
	<td>이름</td>
	<td><input type="text" name="name" id="name" value="${memberVO.memberObj.name}" required label="이름" class="line" maxlength="20" /></td>
	<td>그룹</td>
	<td>
	
	<c:choose>
		<c:when test="${memberVO.memberObj.klevel > 79 && 1 < 100 }">
			<input type="hidden" name="level" value="${memberVO.memberObj.klevel }" />
			<c:forEach items="${codeUtil:getMemberGrp()}" var="mglist" varStatus="st">
					<c:if test="${mglist.kLevel == memberVO.memberObj.klevel}">
						<font class="def">${mglist.grpnm}</font><br>
					</c:if>
			</c:forEach>
		</c:when>
		
		<c:otherwise>
			<select name="klevel">
			<!-- <option value="">↓그룹선택</option> -->
				<c:forEach items="${codeUtil:getMemberGrp()}" var="mglist" varStatus="st">
				<c:if test="${mglist.kLevel < 80 }">
					<option value="${mglist.kLevel}" ${stringUtil:selected(memberVO.memberObj.klevel, fn:trim(mglist.kLevel))}>${mglist.grpnm} - lv[${mglist.kLevel}]</option>
				</c:if>
				</c:forEach>
			</select>
		</c:otherwise>
	</c:choose>

	</td>
</tr>
<!-- 2017-08-24 주석처리 - 비밀번호 변경란 삭제 -->
<!--  <tr>
	<td>비밀번호</td>
	<td colspan="3">
	<div style="float:left;" class="noline"><input type="checkbox" name="modpass" id="modpass" value="Y" onclick="openLayer('pass');" class="line" /> 변경</div>
	<div style="float:left;margin-left:10;display:none;" id="pass">
	새비밀번호 : <input type="password" name="password" id="password" class="line" maxlength="41"/> &nbsp;&nbsp;
	비밀번호확인 : <input type="password" name="password2" id="password2" class="line" maxlength="41"/>
	</div>
	</td>
</tr> -->
<!-- gd_member테이블에 주민등록번호 컬럼없어서 삭제  by.강중환 2014.01.16 -->
<!-- <tr>
	<td>주민등록번호</td>
	<td colspan="3">
	<div style="float:left;" class="noline"><input type="checkbox" name="mod_resno" value="Y" onclick="openLayer('resno');" class="line" /> 변경</div>
	<div style="float:left;margin-left:10;padding-top:5px;">※ 128bit 암호화 되있음</div>
	<div style="float:left;margin-left:10;display:none;" id="resno">
	<input type="text" name="n_resno[]" size="8" maxlength="6" onKeyDown="onlynumber();" class="line" /> -
	<input type="password" name="n_resno[]" size="8" maxlength="7" onKeyDown="onlynumber();" class="line" />
	</div>
	</td>
</tr> -->
<tr>
	<td>성별</td>
	<td>
	<div style="float:left;" class="noline"><input type="checkbox" name="modsex" value="Y" onclick="openLayer('sex');" /> 변경</div>
	<div style="float:left;margin-left:10;padding-top:3px;">${memberVO.memberObj.sex == 'm' ? '남자' : '여자'}</div>
	<div style="float:left;margin-left:10;display:none;" class="noline" id="sex">
	<input type="radio" name="chgsex" value="m"  ${memberVO.memberObj.sex == 'm' ? 'checked' : '' }/> 남자
	<input type="radio" name="chgsex" value="f"  ${memberVO.memberObj.sex == 'f' ? 'checked' : '' }/> 여자
	</div>
	</td>
	<td>생년월일</td>
	<td>
	<input type="text" name="birthyear" value="${memberVO.memberObj.birthyear}" size="4" maxlength="4"  onkeydown="onlynumber(event);" onkeyup="removeHangul(event);"<%-- <%=required.get("birth")%> --%> class="line" />년
	<input type="text" name="birth" value="${fn:substring(memberVO.memberObj.birth , 0, 2)}" size="2" maxlength="2"  onkeydown="onlynumber(event);" onkeyup="removeHangul(event);"<%-- <%=required.get("birth")%> --%> class="line" />월
	<input type="text" name="birth" value="${fn:substring(memberVO.memberObj.birth , 2, 4)}" size="2" maxlength="2"  onkeydown="onlynumber(event);" onkeyup="removeHangul(event);"<%-- <%=required.get("birth")%> --%> class="line" />일
	</td>
</tr>
<tr>
	<td>이메일</td>
	<td colspan="3"><input type="text" name="email" value="${memberVO.memberObj.email}" size=50 <%-- <%=required.get("email")%> --%> label="이메일" class="line" maxlength="100" />
	<span class="noline">( <input type="radio" name="mailling" value="y" ${memberVO.memberObj.mailling == 'y' ? 'checked' : '' }/> 메일링 받음
	<input type="radio" name="mailling" id="mailling" value="n" ${memberVO.memberObj.mailling == 'n' ? 'checked' : '' } /> 메일링 거부 )</span>
	</td>
</tr>
<tr>
	<td>연락처</td>
	<td colspan="3">
	<c:set var="mobilec" value="${empty memberVO.memberObj.mobilec ? '+82' : memberVO.memberObj.mobilec}" />
	<select class="sel_ty02" id="mobilec" name="mobilec" required>
		${webUtil:makeSelectCodeItem((codeUtil:codeitem('teltype')), mobilec)}
	</select>
	<input type="text" name="mobile" value="${memberVO.memberObj.mobile}" size="20" maxlength="20" <%-- <%=required.get("mobile")%> --%> label="핸드폰" onkeyup="removeHangul(event);" class="line" />
	<span class="noline">( <input type="radio" name="sms" value="y" ${memberVO.memberObj.sms == 'y' ? 'checked' : '' }  /> SMS 받음
	<input type="radio" name="sms" value="n" ${memberVO.memberObj.sms == 'n' ? 'checked' : '' } /> SMS 거부 )</span>
	</td>
</tr>
<tr>
	<td>주소</td>
	<td>

	<table border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
		<input type="text"  id="zipcode" name="zipcode" size="6" readonly value="${memberVO.memberObj.zipcode}" class="line" />
		<a href="javascript:postcode('zipcode','address','address_sub');"><img src="/resources/shop/admin/img/btn_zipcode.gif" align="absmiddle" /></a>
		</td>
	</tr>
	<tr>
		<td>
		<input type="text" id="address" name="address" value="${memberVO.memberObj.address}" readonly size="50" <%-- <%=required.get("address")%> --%> label="주소" class="line" />
		<input type="text" id="address_sub" name="addresssub" value="${memberVO.memberObj.addresssub }" size="30" <%-- <%=required.get("address")%> --%> label="주소" class="line" maxlength="100" />
		</td>
	</tr>
	</table>

	</td>
	<td>물류주소</td>
	<td>

	<table border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
		<input type="text"  id="diszipcode" name="diszipcode" size="6" readonly value="${memberVO.memberObj.diszipcode}" class="line" />
		<a href="javascript:postcode('diszipcode','disaddress','disaddress_sub');"><img src="/resources/shop/admin/img/btn_zipcode.gif" align="absmiddle" /></a>
		</td>
	</tr>
	<tr>
		<td>
		<input type="text" id="disaddress" name="disaddress" value="${memberVO.memberObj.disaddress}" readonly size="50" <%-- <%=required.get("address")%> --%> label="주소" class="line" />
		<input type="text" id="disaddress_sub" name="disaddresssub" value="${memberVO.memberObj.disaddresssub }" size="30" <%-- <%=required.get("address")%> --%> label="주소" class="line" maxlength="100" />
		</td>
	</tr>
	</table>

	</td>
</tr>
<tr>
	<td>통관고유부호</td>
	<td colspan="3">
		<input type="text" id="customsnum" name="customsnum" value="${memberVO.memberObj.customsnum }" size="30" label="통관고유부호" class="line" maxlength="13" />
	</td>
</tr>
<tr>
	<td>회원가입일</td>
	<td colspan="3"><font class="ver8"> <fmt:formatDate value="${memberVO.memberObj.regdt}" pattern="yyyy-MM-dd HH:mm"/> </font></td>
</tr>

<tr>
	<td>최종로그인</td>
	<td>
		<ul>
			<li><font class="ver8">PC : </font> ${memberVO.memberObj.lwdt}</li>
			<li><font class="ver8">MO : </font> ${memberVO.memberObj.lmdt}</li>
			<li><font class="ver8">APP :</font> ${memberVO.memberObj.ladt}</li>
		</ul>
	</td>
	<td>최종구매일</td>
	<td>
		<ul>
			<li><font class="ver8">PC : </font> ${memberVO.memberObj.owdt}</li>
			<li><font class="ver8">MO : </font> ${memberVO.memberObj.omdt}</li>
			<li><font class="ver8">APP :</font> ${memberVO.memberObj.oadt}</li>
		</ul>
	</td>
</tr>

<tr>
	<td>구입금액</td>
	<td>₩ ${stringUtil:getMoneyFormatInteger(memberVO.memberObj.sumsale)} &nbsp;&nbsp; 주문 ${memberVO.memberObj.cntsale} 건 &nbsp;&nbsp; <a href="javascript:popup('orderlist?mno=${memberVO.memberObj.mno}',500,600)"><img src="/resources/shop/admin/img/btn_detailview.gif" align="absmiddle" /></a></td>
	<td>적립금</td>
	<td>₩ ${stringUtil:getMoneyFormatInteger(memberVO.memberObj.emoney)} &nbsp;&nbsp; <a href="javascript:popupLayer('popup.emoney?mno=${memberVO.memberObj.mno}',600,500)"><img src="/resources/shop/admin/img/btn_detailview.gif" align="absmiddle" /></a></td>
</tr>
</table>

<div class="button">
<a href="#none" onclick="modifyInfo()"><input type="image" src="/resources/shop/admin/img/btn_modify.gif" /></a>
<!-- <input type="image" src="../img/btn_modify.gif" /> -->
<%-- <a href='<%=listUrl%>'><img src="../img/btn_list.gif" /></a> --%>
<a href="#"  onclick="goList()"><img src="/resources/shop/admin/img/btn_list.gif" style="vertical-align:top" /></a>
</div>

</form>


<%-- ================================================================================
* 업무 HTML CONTENT 종료
================================================================================ --%>

			<%@ include file="../common/bottom.jsp"%>
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
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js?autoload=false"></script>
<script>
    function postcode(_zip, _addr, _sub) {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }
                
                /*
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('address').value = fullAddr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('address_sub').focus();
                */
                $("#"+_zip).val(data.zonecode);
                $("#"+_addr).val(fullAddr);
                $("#"+_sub).focus();
            }
        }).open();
    }
</script>

