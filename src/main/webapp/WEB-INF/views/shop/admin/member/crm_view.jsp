<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<!-- Jquery Setting-->
<script type="text/javascript" src="/resources/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery.ajax.js"></script>
<!-- //Jquery Setting-->

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<html>
<head>
<title>'xMall ||| Shoppingmall 관리자모드'</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="styleSheet" href="/resources/shop/admin/style.css">
<script src="/resources/shop/admin/common.js"></script>
<script src="/resources/shop/admin/prototype.js"></script>
<script language="javascript">
if(window.addEventListener) 
{
	window.addEventListener('load',linecss,false); 
}
else 
{
	window.attachEvent('onload',linecss); 
}
</script>
<div id="dynamic"></div>
<div id="jsmotion"></div>
</head>
<body class="scroll">

	<!-------------------- 서브 본문 본격적으로 시작  ------------------------------->

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white" border="0">
	<tr>
		<td valign="top" style="padding-left:12px">
		
<script type="text/javascript">

</script>

<form name="frmMember" method="post" action="indb" onsubmit="return chkFormMember(this);">
	<input type="hidden" name="mode" value="modify" />
	<input type="hidden" name="m_id" value="${memberVO.memberObj.mid }" />
	<%-- <input type="hidden" name="returnUrl" value="<%= returnUrl%>" /> --%>

	<div class="title title_top">회원정보</div>

	<table class="tb">
		<col class="cellC" /><col style="padding-left:10px;width:250;" />
		<col class="cellC" /><col style="padding-left:10px" />
		<tr>
			<td>아이디</td>
			<td><b>${memberVO.memberObj.mid }</b></td>
			<td>승인</td>
			<td class="noline">
				<input type="radio" name="status" value="1" ${memberVO.memberObj.status == 1 ? 'checked' : '' } disabled/> 승인
				<input type="radio" name="status" value="0"  ${memberVO.memberObj.status != 1 ? 'checked' : '' } disabled/> 미승인
			</td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" value="${memberVO.memberObj.name}"" required label="이름" class="line" readonly/></td>
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
						<select name="klevel" disabled>
						<option value="" >↓그룹선택</option>
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
		
<!-- 		<tr>
			<td>비밀번호</td>
			<td colspan="3">
			<div style="float:left;" class="noline"><input type="checkbox" name="mod_pass" value="Y" onclick="openLayer('pass');" class="line" /> 변경</div>
			<div style="float:left;margin-left:10;display:none;" id="pass">
				새비밀번호 : <input type="password" name="password" class="line" /> &nbsp;&nbsp;
				비밀번호확인 : <input type="password" name="password2" class="line" />
			</div>
			</td>
		</tr> -->
		
		<tr>
			<td>성별</td>
			<td>
				<!-- <div style="float:left;" class="noline"><input type="checkbox" name="mod_sex" value="Y" onclick="openLayer('sex');" /> 변경</div> -->
				<div>${memberVO.memberObj.sex == 'm' ? '남자' : '여자'}</div>
				<!-- <div style="float:left;margin-left:10;display:none;" class="noline" id="sex">
					<input type="radio" name="sex" value="m" /> 남자
					<input type="radio" name="sex" value="w" /> 여자
				</div> -->
			</td>
			<td>생년월일</td>
			<td>
				<input type="text" name="birthyear" value="${memberVO.memberObj.birthyear}" size="4" maxlength="4" <%-- <%=required.get("birth")%> --%> class="line" readonly/>년
				<input type="text" name="birth" value="${fn:substring(memberVO.memberObj.birth , 0, 2)}" size="2" maxlength="2"  <%-- <%=required.get("birth")%> --%> class="line" readonly/>월
				<input type="text" name="birth" value="${fn:substring(memberVO.memberObj.birth , 2, 4)}" size="2" maxlength="2"  <%-- <%=required.get("birth")%> --%> class="line" readonly/>일
			</td>
		</tr>
		
		<tr>
			<td>이메일</td>
			<td colspan="3"><input type="text" name="email" value="${memberVO.memberObj.email}" size=50 <%-- <%=required.get("email")%> --%> label="이메일" class="line" maxlength="100" readonly />
			<span class="noline">( <input type="radio" name="mailling" value="y" ${memberVO.memberObj.mailling == 'y' ? 'checked' : '' } disabled /> 메일링 받음
			<input type="radio" name="mailling" id="mailling" value="n" ${memberVO.memberObj.mailling == 'n' ? 'checked' : '' } disabled /> 메일링 거부 )</span>
			</td>
		</tr>
		
	<tr>
	<td>주소</td>
	<td colspan="3">

	<table border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
		우편번호 <input type="text"  id="postcode" name="zipcode" size="6" readonly value="${memberVO.memberObj.zipcode}" class="line" />
		<!-- <a href="javascript:postcode();"><img src="../img/btn_zipcode.gif" align="absmiddle" /></a> -->
		</td>
	</tr>
	<tr>
		<td>
		<input type="text" id="address" name="address" value="${memberVO.memberObj.address}" size="50" <%-- <%=required.get("address")%> --%> label="주소" class="line" readonly/>
		<input type="text" id="address_sub" name="addresssub" value="${memberVO.memberObj.addresssub }" size="30" <%-- <%=required.get("address")%> --%> label="주소" class="line" maxlength="100" readonly/>
		</td>
	</tr>
	</table>

	</td>
</tr>
<tr>
	<td>전화번호</td>
	<td colspan="3">
	<c:set var="arrphone" value="${fn:split(memberVO.memberObj.phone, '-') }"/>
	<input type="text" name="phone" value="${arrphone[0]}" size="4" maxlength="4" <%-- <%=required.get("mobile")%> --%> label="핸드폰" class="line" maxlength="4" readonly/> -
	<input type="text" name="phone" value="${arrphone[1]}" size="4" maxlength="4" <%-- <%=required.get("mobile")%> --%> label="핸드폰" class="line" maxlength="4" readonly/> -
	<input type="text" name="phone" value="${arrphone[2]}" size="4" maxlength="4" <%-- <%=required.get("mobile")%> --%> label="핸드폰" class="line" maxlength="4" readonly/>
	</td>
</tr>
<tr>
	<td>핸드폰</td>
	<td colspan="3">
	<input type="text" name="mobile" value="${memberVO.memberObj.arrmobile[0]}" size="4" maxlength="4" <%-- <%=required.get("mobile")%> --%> label="핸드폰" class="line" maxlength="4" readonly/> -
	<input type="text" name="mobile" value="${memberVO.memberObj.arrmobile[1]}" size="4" maxlength="4" <%-- <%=required.get("mobile")%> --%> label="핸드폰" class="line" maxlength="4" readonly/> -
	<input type="text" name="mobile" value="${memberVO.memberObj.arrmobile[2]}" size="4" maxlength="4" <%-- <%=required.get("mobile")%> --%> label="핸드폰" class="line" maxlength="4" readonly/>
	<span class="noline">( <input type="radio" name="sms" value="y" ${memberVO.memberObj.mailling == 'y' ? 'checked' : '' }  class="line" disabled/> SMS 받음
	<input type="radio" name="sms" value="n" ${memberVO.memberObj.mailling == 'n' ? 'checked' : '' } class="line" disabled/> SMS 거부 )</span>
	</td>
</tr>


<tr>
	<td>결혼기념일</td>
	<td>
	<div style="float:left;" class="noline">
	<input type="radio" name="marriyn" value="n" ${memberVO.memberObj.marriyn == 'n' ? 'checked' : '' } onclick="openLayer('marri','none')" class="line" disabled/> 미혼
	<input type="radio" name="marriyn" value="y" ${memberVO.memberObj.marriyn == 'y' ? 'checked' : '' } onclick="openLayer('marri','block')" class="line" disabled/> 기혼
	</div>
	<div style="float:left;margin-left:5;display:none;" id="marri">
	<input type="text" name="marridate" value="${fn:substring(memberVO.memberObj.marridate, 0, 4)}" size="4" maxlength="4" readonly/>년
	<input type="text" name="marridate" value="${fn:substring(memberVO.memberObj.marridate, 4, 6)}" size="2" maxlength="2" readonly />월
	<input type="text" name="marridate" value="${fn:substring(memberVO.memberObj.marridate, 6, 8)}" size="2" maxlength="2" readonly />일
	</div>
	</td>
</tr>
<tr>
	<td>관심분야</td>
	<td colspan="3" class="noline">
	<table><tr>
	<td nowrap><input type="checkbox" name="interest" class="interest" value="0"  disabled>   의류/패션잡화 </td>
	<td nowrap><input type="checkbox" name="interest" class="interest" value="1"  disabled>   화장품/향수/미용품 </td>
	<td nowrap><input type="checkbox" name="interest" class="interest" value="2"  disabled>   컴퓨터/SW </td>
	<td nowrap><input type="checkbox" name="interest" class="interest" value="3"  disabled>   생활/주방욤품 </td>
	<td nowrap><input type="checkbox" name="interest" class="interest" value="4"  disabled>   보석/시계/악세사리 </td>
	</tr>
	<tr>
	<td nowrap><input type="checkbox" name="interest" class="interest" value="5"  disabled>   가전/카메라 </td>
	<td nowrap><input type="checkbox" name="interest" class="interest" value="6"  disabled>   서적/음반/비디오 </td>
	<td nowrap><input type="checkbox" name="interest" class="interest" value="7"  disabled>   스포츠/레져용품 </td>
	<td nowrap><input type="checkbox" name="interest" class="interest" value="8"  disabled>   꽃배달/케익서비스 </td>
	</tr></table>
	</td>
</tr>

<tr>
<td>남기는말씀</td>
<td colspan="3" class="noline">
${memberVO.memberObj.memo }
</td>
</tr>

<tr>
	<td>회원가입일</td>
	<td><font class="ver8"> <fmt:formatDate value="${memberVO.memberObj.regdt}" pattern="yyyy-MM-dd HH:mm"/> </font></td>
	<td>적립금</td>
	<td>₩ ${stringUtil:getMoneyFormatInteger(memberVO.memberObj.emoney)} &nbsp;&nbsp; <a href="javascript:popupLayer('popup.emoney?mno=${memberVO.memberObj.mno}',600,500)"><img src="/resources/shop/admin/img/btn_detailview.gif" align="absmiddle" /></a></td>
</tr>
<tr>
	<td>최종로그인</td>
	<td><font class="ver8"><fmt:formatDate value="${memberVO.memberObj.lastlogin}" pattern="yyyy-MM-dd HH:mm"/>
	  &nbsp;&nbsp; 방문 ${memberVO.memberObj.cntlogin} 회</font></td>
	<%-- <td>최종로그인IP</td>
	<td><%=rtList.get("last_login_ip")%></td> --%>
</tr>
<tr>
	<td>최종구매일</td>
	<td><font class="ver8"><fmt:formatDate value="${memberVO.memberObj.lastsale}" pattern="yyyy-MM-dd HH:mm"/></font></td>
	<td>구입금액</td>
	<td>₩ ${stringUtil:getMoneyFormatInteger(memberVO.memberObj.sumsale)} &nbsp;&nbsp; 주문 ${memberVO.memberObj.cntsale} 건 &nbsp;&nbsp; <a href="javascript:popup('orderlist?mno=${memberVO.memberObj.mno}',500,600)"><img src="/resources/shop/admin/img/btn_detailview.gif" align="absmiddle" /></a></td>
</tr>
</table>

<%-- 	<div class="button">
		<input type="image" src="../img/btn_modify.gif" />
		<a href='<%=listUrl%>'><img src="../img/btn_list.gif" /></a>
	</div>
 --%>
</form>

</body>

<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js?autoload=false"></script>
<script>

	function func(){
		
			var chkInterest = '${memberVO.memberObj.interest}';
			var checkboxCnt = document.getElementsByClassName("interest").length;
//				$("input:checkbox[name=interest]").length; // 체크박스 전체 갯수
			
			for(var i=0; i<checkboxCnt; i++) {
				if( (chkInterest & ( Math.pow(2, i))) > 0) {
					var interest = document.getElementsByClassName("interest");
					interest[i].checked = true;
//					$('input:checkbox[name=interest][value='+i+']').attr("checked", true);
				}
			}
		}
	func();
	
    function postcode() {
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

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('address').value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('address_sub').focus();
            }
        }).open();
    }
</script>