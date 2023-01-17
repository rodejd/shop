<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/member/myinfo.js"></script>
<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/member/join_input.js"></script>

<%-- ================================================================================
* 업무 HTML CONTENT 시작
================================================================================ --%>
<input class="js_alertMessage" type="hidden" value="${indbVO.alertMessage}">
<input class="js_redirectUrl" type="hidden" value="${indbVO.redirectUrl}">
<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/member/join_input.js"></script>
<script>
	function isEmpty(s) {
	
	    if(typeof s == 'string')
	        s = $.trim(s);
	
	    if(s == undefined || s == null || s == '' || s.length == 0)
	        return true;
	
	    return false;
	}
	if(!isEmpty($('.js_alertMessage').val())) {
		alert($('.js_alertMessage').val());
		location.href = $('.js_redirectUrl ').val();
	}

	function snslogin(type){
		var newWin = window.open("/shop/member/snslogin?type=" + type, '_blank', 'width=650, height=600, resizable=no, scrollbars=no, status=no;');
		if (newWin == null || typeof(newWin)=='undefined'){
			alert("팝업이 차단되었습니다.\n차단된 팝업을 허용해 주세요.");
		}	
	}

	function snslogout(type){
		ajaxCallJson("/shop/member/snslogout.dojson", {
			'type': type
		}, function(data){
			alert(type.toUpperCase() + " 간편 로그인이 해제 되었습니다.");
			window.location.reload();
		}, function(e){
			console.log(e);
		});	
	}
</script>

<div class="top_tit_ty01">
	<div class="tit_dp01">MY PAGE</div>
</div>

<div class="container_mypage">
	<div class="">
		<!-- 고객센터 공통탭메뉴 처리 -->
		<jsp:include page="../mypage/mypage_tab_menu.jsp" flush="true">
			<jsp:param name="tab_order" value="1" />
		</jsp:include> 

		<div class="navi_my">회원정보 >  회원정보수정</div>
	
	<form name=frmMember id="frmMember" method=post action="${ctx}/shop/member/modindb" onsubmit="return chkForm(this)" >
		<div class="join-edit">
			<div class="">
				<div class="tab-pane fade in active">
					<div class="">
							<input type="hidden" name="oldProfile" value="${myInfoVO.gdMember.profile}">
							
							<table class="table tb_mem_modify">
								<colgroup> 
									<col class="bx_th">
									<col style="width:*;">
								</colgroup>
								<tbody>
								<tr>
									<th>로그인 계정</th>
									<td style="color:#b2b2b2;">${sessionScope.xmall_user.SHOPUSER.xkey.m_id}</td>
								</tr>
								<c:if test="${empty usedSns}">
								<!-- sns로그인이라면 지우기 -->
									<!-- <tr>
										<th><span class="required">* </span>아이디</th>
										<td><input type="text" name="mid" id="m_id" maxlength="20" placeholder="아이디" class="inp_ty01 " readOnly value="${myInfoVO.m_id}"/></td>
									</tr> -->
									
									<!-- <tr>
										<th></th>
					                    <td>
											<div class="row">
												<ul class="product-page-option-list">
													<li>① 최소 8자 ~ 최대 20자 이내로 입력합니다.</li>
													<li>② 반드시 영문, 숫자, 특수문자가 각 1자리 이상 포함되어야 합니다.</li>
													<li>③ 특수문자 중 <, >, (, ), #, ', /, | 는 사용할수 없습니다.</li>
													<li>④ 3자리 이상 연속되는 숫자 또는 문자열은 사용할 수 없습니다. (예:123, 321, 012, abc, cba)</li>
													<li>⑤ 3자리 이상 동일한 숫자 또는 문자열은 사용할 수 없습니다. (예:000, 111, 222, ,aaa, bbb)</li>
													<li>⑥ 아래와 같은 문자는 사용할 수 없습니다. (love, happy, qwer, asdf, zxcv, test, gpin, ipin)</li>
													<li>⑦ 아이디와 연속한 3자리 이상 일치하는 비밀번호는 사용할 수 없습니다.</li>
												</ul>
											</div>
					                    </td>
									</tr> -->
				                    
									<tr>
										<th>기존 비밀번호</th>
										<td><input type="password" name="password" class="inp_ty01" placeholder="비밀번호" required label="비밀번호" title="비밀번호"/></td>
									</tr>
				                    
									<tr>
										<th>신규 비밀번호</th>
										<td>
											<input type="password" name="newpassword" onkeyup="regPassword();" class="inp_ty01" placeholder="비밀번호 확인" option="regPass" label="비밀번호" title="비밀번호 확인" />
										</td>
				                    </tr>
				                    
									<tr>
										<th>신규 비밀번호 확인</th>
										<td>
											<input type="password" name="newpassword2" onkeyup="regPassword();" class="inp_ty01" placeholder="비밀번호 확인" option="regPass" label="비밀번호" title="비밀번호 확인" />
											<span id="msg" class="required"></span>
										</td>
				                    </tr>
				                </c:if>
									<tr>
				                        <th>이름</th>
				                        <td><input type="text" name="name1" maxlength="20" class="inp_ty03" placeholder="이름" required label="이름" title="이름" value="${myInfoVO.gdMember.name1}"/></td>
				                    </tr>
									<tr>
				                        <th>성</th>
				                        <td><input type="text" name="name2" maxlength="20" class="inp_ty03" placeholder="성" required label="성" title="성" value="${myInfoVO.gdMember.name2}"/></td>
				                    </tr>
				                	<tr>
				                        <th>전화번호</th>
				                        <td>
				                        	<div class="pr_ty01">
												<c:set var="mobilec" value="${wskin eq 'kr' ? '+82' : myInfoVO.gdMember.mobilec}" />
												<select class="sel_ty02" id="mobilec" name="mobilec" required>
													${webUtil:makeSelectCodeItem((codeUtil:codeitem('teltype')), mobilec)}
												</select>
												<input type="text" class="inp_ty03" style="margin-left: 25px;" name="mobile" title="전화번호" value="${myInfoVO.gdMember.mobile}" maxlength="20" <c:if test="${myInfoVO.mobileReq eq 'checked'}">required</c:if> />
					                        </div>
				                        </td>
				                    </tr>
									<tr>
				                        <th>개인통관고유부호</th>
				                        <td><input type="text" id="customsnum" name="customsnum" maxlength="13" class="inp_ty01" placeholder="개인통관고유부호" label="개인통관고유부호" title="개인통관고유부호" value="${myInfoVO.gdMember.customsnum}" /></td>
				                    </tr>
				                    <tr>
				                        <th>이메일</th>
				                        <td>
											<c:if test="${myInfoVO.maillingUse eq 'checked' }">
												<input type="checkbox" id="mailling" name="mailling" class="ck_my_ty01" title="이벤트 알림 및 이메일 수신동의" <c:if test="${myInfoVO.gdMember.mailling eq 'y' }">checked</c:if> <c:if test="${myInfoVO.maillingReq eq 'checked' }">required</c:if>/>
												<label for="mailling" style="line-height:22px;">이벤트 알림 및 이메일 수신동의</label>
											</c:if>
				                        </td>
				                    </tr>
				                    <tr>
				                        <th><c:if test="${myInfoVO.birthReq eq 'checked'}"><span class="required">* </span></c:if><c:if test="${myInfoVO.birthReq ne 'checked'}"><!-- <span>&nbsp; </span> --></c:if>생년월일</th>
				                        <td>
											<jsp:useBean id="now" class="java.util.Date" />
											<fmt:formatDate value="${now}" pattern="yyyy" var="year"/>
				                        	<div class="pr_ty01">
												<select id="birthyear"  name="birthyear"  class="js_birthYear sel_ty01" title="년도 선택" label="생년월일" <c:if test="${myInfoVO.birthReq eq 'checked'}">required</c:if> >
													<option value="">년도 선택</option>
													<c:forEach var="i" begin="1950" end="${year}">
														<option value="${i}" <c:out value="${myInfoVO.gdMember.birthyear eq i ? 'selected' : ''}" /> >${i }</option>
													</c:forEach>
												</select>
											</div>
					                        <div class="pr_ty01">
												<select id="birthMonth"  name="birthMonth" class="js_birthMonth sel_ty02" title="월 선택" label="생년월일" <c:if test="${myInfoVO.birthReq eq 'checked'}">required</c:if> >
													<fmt:parseNumber value = "${fn:substring(myInfoVO.gdMember.birth,0,2)}" var = "num"/>
													<option value="" selected="selected">월 선택</option>
													<c:forEach var="i" begin="1" end="12">
														<option value="${i}" <c:out value="${num eq i ? 'selected' : ''}" /> >${i }</option>
													</c:forEach>
												</select>
											</div>
					                        <div class="pr_ty01">
												<fmt:parseNumber value = "${fn:substring(myInfoVO.gdMember.birth,2,fn:length(myInfoVO.gdMember.birth))}" var = "num"/>
												<select id=""  name="birthDate"  class="js_birthDate sel_ty02" title="월 선택" label="생년월일" data-birth-date="${num}" <c:if test="${myInfoVO.birthReq eq 'checked'}">required</c:if> >
													<option value="" selected="selected">날짜 선택</option>
												</select>
											</div>
											<div style="clear:both;"></div>
											<p class="tx_tb01">*추가 정보 입력시 다양한 혜택을 받아 보실 수 있습니다.</p>
				                        </td>
				                    </tr>
									<tr>
				                        <th>성별</th>
				                        <td>
											<p style="position:relative; float:left; padding:0; margin:0;"><input type="radio" class="ck_my_rad01" id="gender01" name="sex" value="f" ${myInfoVO.gdMember.sex eq 'f' ? 'checked="checked"' : ''}/><label for="gender01" style="line-height:16px; padding:3px 0 0 0; margin:0;">여성</label></p>
											<p style="position:relative; float:left; padding:0 0 0 30px; margin:0;"><input type="radio" class="ck_my_rad01" id="gender02" name="sex"  value="m" ${myInfoVO.gdMember.sex eq 'm' ? 'checked="checked"' : ''}/><label for="gender02" style="line-height:16px; padding:3px 0 0 0;  margin:0;">남성</label></p>
										</td>
				                    </tr>
								</tbody>
				            </table>		
			            					
					</div>

					<div class="easy_login_wrap">
						<div class="s_tit">간편 로그인 관리</div>
						<div class="in_bx">
							<p class="_s_tit">SNS 계정 관리</p>
							<ul>
								<li>
									<c:choose>
										<c:when test="${empty myInfoVO.facebook}">
											<a href="javascript:snslogin('facebook');" class="ico-sns btn_facebook">페이스북 로그인 설정</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:snslogin('facebook');" class="ico-sns btn_facebook">페이스북 로그인 해제</a>
										</c:otherwise>
									</c:choose>
								</li>
								<li><a href="javascript:snslogin('google');" class="ico-sns btn_google">구글계정 로그인 설정</a></li>
								<li>
									<c:choose>
										<c:when test="${empty myInfoVO.kakao}">
											<a href="javascript:snslogin('kakao');" class="ico-sns kakao btn_kakao">카카오계정 로그인 설정</a>
										</c:when>
										<c:otherwise>
											<a href="#" onclick="snslogout('kakao');" class="ico-sns kakao btn_kakao">카카오계정 로그인 해제</a>
										</c:otherwise>
									</c:choose>
								</li>
								<li><a href="javascript:snslogin('apple');" class="ico-sns btn_apple">애플계정 로그인 설정</a></li>
							</ul>
							<div class="btns_r">
								<p class="btn01"><a href="${ctx}/shop/member/hack">회원 탈퇴</a></p>
								<input class="btn btn-primary btn02" type="submit" id="join_btn"  value="변경사항 저장하기"  onclick="return checkedPwd();" />
							</div>
							<%-- <input class="btn btn-primary" type="button" id="pre_btn"  value="이전으로" /> --%>
						</div>
					</div>

				</div>
			</div>
		</div>
		</form>
	</div>
</div>
	

<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js?autoload=false"></script>
<script>
	function regPassword(){
		 var check = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;
		
		if($("input[name=newpassword").val() == $("input[name=newpassword2]").val()){
			$("#msg").html('');
			if(!check.test($("input[name=newpassword").val()) && $("input[name=newpassword").val() != '' ){ 
		        alert('영문/숫자/특수문자를 조합한 8자 이상의 비밀번호를 입력하세요.');  
		        $("input[name=newpassword").val('');
		        $("input[name=newpassword2").val('');
		        $("input[name=newpassword").focus();
		    }
		}else{
			$("#msg").html('비밀번호가 일치 하지 않습니다.');
		}
		
	}

$("input[name=marriyn]").on("click", function(){
	
	<%-- 20160322 김경훈 - checkbox에서 radio버튼으로 변경 --%>
	// var chk = $("input[name=marriyn]").is(":checked");
	var chk = $(':radio[name="marriyn"]:checked').val();
	
	if(chk == 'n'){
		$("#marry_year option").attr("disabled", "disabled");
		$("#marry_month option").attr("disabled", "disabled");
		$("#marry_date option").attr("disabled", "disabled");
		
		$("#marry_year").removeAttr("required");
		$("#marry_month").removeAttr("required");
		$("#marry_date").removeAttr("required");
	}else{
		$("#marry_year option").removeAttr("disabled");
		$("#marry_month option").removeAttr("disabled");
		$("#marry_date option").removeAttr("disabled");
		
		$("#marry_year").prop("required",true);
		$("#marry_month").prop("required",true);
		$("#marry_date").prop("required",true);
	}
});

/* 2017-08-30 추가 - 패스워드 유효성검사 */
function checkedPwd(){
	if ($("input[name=newpassword").val().trim() != "" && $("input[name=newpassword2]").val().trim() != "") {
		var pwdVal = pwdValidation.isValidPassword($('#newpassword').val(), '');
		
		if(!pwdVal.rtnBoolean) {
			alert("형식에 맞는 비밀번호를 입력하세요.");
	        $("input[name=newpassword").val('');
	        $("input[name=newpassword2").val('');
	        $("input[name=newpassword").focus();
	        
	        return false;
		}
	}
	
	//2017-08-31 validation 검사 추가
	for(var i=0; i<$('input[type="text"]').length; i++){
		if($('input[type="text"]')[i].dataset.reg != null && $('input[type="text"]')[i].dataset.reg != "" && $('input[type="text"]')[i].value != "") {
			if(! (chkPatten($('input[type="text"]')[i], $('input[type="text"]')[i].dataset.reg)) ){
				return false;
			}
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

</script>


