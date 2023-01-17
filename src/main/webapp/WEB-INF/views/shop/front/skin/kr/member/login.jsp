<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/member/join_input.js"></script>
<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>
<%-- 로그인 --%>
<script type="text/javascript">
$(document).ready(function(){
	$('#m_id').focus();
	
	//로그인 처리
	$('#btn_login').click(function(){
		if($('#m_id').val() == ''){
			alert("아이디를 입력해 주세요.");
			$('#m_id').focus();
			return;
		}
		
		if($('#password').val() == ''){
			alert("비밀번호를 입력해 주세요.");
			$('#password').focus();
			return;
		}
		
		ajaxCallJsonPost("/shop/member/login_ok.dojson", "form", function(result){
			location.href = ctx + $("#returnUrl").val();
		});
	});
	$("#password").on("keydown", function(key){
		if(key.keyCode == 13){
			$('#btn_login').click();
		}
	}); 
});

function snslogin(type){
	var newWin = window.open("/shop/member/snslogin?type=" + type, '_blank', 'width=650, height=600, resizable=no, scrollbars=no, status=no;');
	if (newWin == null || typeof(newWin)=='undefined'){
		alert("팝업이 차단되었습니다.\n차단된 팝업을 허용해 주세요.");
	}	
}

function snsalertmss(){
	alert("URL등록후 sns별 api키 받아서 관리자에 등록하면 사용가능");
}

function init() {
	gapi.load('auth2', function() {
		auth2 = gapi.auth2.init({
			client_id: '696510921537-qrlg674pb58bo781qvi945fdjkul74mi.apps.googleusercontent.com'
		});
	});
}
</script>
<%-- //로그인 --%>

<%-- 회원가입 --%>
<script type="text/javascript">
$(function(){
	$("#btn_join").on("click",function(){
		if( $.trim($("#join_id").val()) == "" ){
			alert("이메일을 입력해주세요.");
			$("#join_id").focus();
			return;
		}
		
		var regEmail = /^[^"'@]+@[._a-zA-Z0-9-]+\.[a-zA-Z]+$/;
		if (!regEmail.test($("#join_id").val())){
			alert("잘못된 이메일 형식입니다.");
			return;
		}

		if( $.trim($("#join_pwd1").val()) == "" ){
			alert("비밀번호를 입력해주세요.");
			$("#join_pwd1").focus();
			return;
		}
		
		var pwdVal = pwdValidation.isValidPassword($("#join_pwd1").val(), $('#join_id').val());
		if(!pwdVal.rtnBoolean) {
			var alt = "비밀번호는 영문, 숫자, 특수문자를 사용하여 최소 8자 ~ 최대 20자 이내로 입력하세요.";
			alert(alt);
			$("#join_pwd1").val("").focus();
			$("#join_pwd2").val("");
			return;
		}
		
		if( $.trim($("#join_pwd2").val()) == "" ){
			alert("확인용 비밀번호를 입력해주세요.");
			$("#join_pwd2").focus();
			return;
		}
		
		if( $.trim($("#join_pwd1").val()) != $.trim($("#join_pwd2").val()) ){
			alert("비밀번호가 일치하지 않습니다.");
			$("#join_pwd1").focus();
			return;
		}
		
		if( $.trim($("#join_name1").val()) == "" ){
			alert("이름 입력해주세요.");
			$("#join_name1").focus();
			return;
		}
		
		if( $.trim($("#join_name2").val()) == "" ){
			alert("성을 입력해주세요.");
			$("#join_name2").focus();
			return;
		}
		
		if( !$("#agree01").is(":checked") ){
			alert("개인정보 보호 및 쿠키정책, 이용약관에 동의해주세요.");
			return;
		}
		
		$.ajax({
			data : {mid : $("#join_id").val()},
			dataType : 'json',
			type : 'POST',
			async: false,
			url : '/shop/member/chkId',
			success : function (result) {
				if(result == 9){
					alert('사용 불가능한 아이디입니다');
					return;
				}else if(result == 1){
					alert('이미 등록된 아이디입니다');
					return;
				}else if(result == 2){
					alert('잘못된 이메일 형식입니다');
					return;
				}else{
					$.ajax({
						data : $("#frmMember").serialize(),
						dataType : 'json',
						type : 'POST',
						async: false,
						url : '/shop/member/joinIndb',
						success : function (result) {
							if(!isNaN(result)) {
								if((result * 1) > 0) {
									alert('회원가입 쿠폰이 발급되었습니다.');
								}
								alert('회원 가입이 완료 되었습니다.');
								location.href = ctx + '/shop/main/index';
							} else {
								alert(result);
							}
						}
					});
				}
			}
		});
	});
});
</script>
<%-- //회원가입 --%>

<style>
.col-centered{
    float: none;
    margin: 0 auto;
}
</style>

<!-- <header class="page-header page-header-banner x-member-h">
    <div class="container">
        <div class="page-header-banner-inner">
            <h1 class="page-title">로그인</h1>
        </div>
    </div>
</header> -->

<div class="login_join_wrap">
	<div class="s_tit01">LOGIN</div>

	<div class="in_wrap">
		<div class="bx_l">
			<form method="post" id="form" name="form" onsubmit="return false;">
				<input type="hidden" name="returnUrl" id="returnUrl" value="${empty returnUrl ? '/shop/main/index' : returnUrl}">
				<input type="hidden" name="options_list" value="${options_list }">
				<input type="hidden" name="mode" value="${mode }">
				<div class="bx_inp">
					<ul>
						<li><input type="text" id="m_id" name="m_id" class="inp_ty01" placeholder="이메일*" maxlength="100" required /></li>
						<li><input type="password" id="password" name="password" class="inp_ty01" required maxlength="30" placeholder="비밀번호*" /></li>
					</ul>
				</div>
				<div class="bx_btns">
					<div class="tx01">
						<p>SNS계정으로 로그인하기</p>
						<a href="${ctx }/shop/member/find_pwd" class="btn_find">비밀번호 찾기</a>
						<!-- <a href="${ctx }/shop/member/join" class="btn btn-primary">회원가입</a>
						<a href="${ctx }/shop/member/find_id" class="btn btn-primary">아이디찾기</a> -->
					</div>
					<div class="bx_l_icons">
						<ul>
							<li><a href="javascript:snslogin('facebook');" class="ico-sns btn_facebook">페이스북으로 로그인</a></li>
							<li><a href="javascript:snslogin('google');" class="ico-sns btn_google">구글계정으로 로그인</a></li>
							<li><a href="javascript:snslogin('kakao');" class="ico-sns kakao btn_kakao">카카오계정으로 로그인</a></li>
							<li><a href="javascript:snslogin('apple');" class="ico-sns btn_apple">애플계정으로 로그인</a></li>
						</ul>
						<button id="btn_login" class="btn_login" type="button" value="로그인">로그인</button>
					</div>
				</div>
			</form>
		</div>
		<div class="bx_r">
			<form method="post" id="frmMember" name="frmMember" onsubmit="return false;">
				<div class="bx_tp_tx">
					<dl>
						<dt>회원가입</dt>
						<dd>
							회원 가입을 하시면 결제 및 배송 정보 저장, 주문 내역 조회, 반품 요청 및 배송 조회, 위시리스트 공유하기 등의 
							다양한 기능을 더욱 편리하게 이용하실 수 있습니다.
							<p>* 필수 입력정보</p>
						</dd>
					</dl>
				</div>
				<ul>
					<li><input type="text" id="join_id" name="join_id" class="inp_ty01" required maxlength="30" placeholder="이메일*" /></li>
					<li><input type="password" id="join_pwd1" name="join_pwd1" class="inp_ty01" required maxlength="30" placeholder="비밀번호*" /></li>
					<li><input type="password" id="join_pwd2" name="join_pwd2" class="inp_ty01" required maxlength="30" placeholder="비밀번호 확인*" /></li>
					<li><input type="text" id="join_name1" name="join_name1" class="inp_ty01" required maxlength="30" placeholder="이름*" /></li>
					<li><input type="text" id="join_name2" name="join_name2" class="inp_ty01" required maxlength="30" placeholder="성*" /></li>
				</ul>
				<div class="bx_bt_tx">
					<p><input type="checkbox" class="ck_ty01" id="agree01"><label for="agree01"><a href="/shop/service/privacyPolicy" style="color:#424242; text-decoration:underline;">개인정보 보호 및 쿠키정책</a> 과 <a href="/shop/service/agreement" style="color:#424242; text-decoration:underline;">이용약관</a>에 동의하고 회원가입을 하겠습니다.</label></p>
					<p><input type="checkbox" class="ck_ty01" id="agree02" name="mailling" value="y"><label for="agree02">앱PUSH/이메일 이벤트 알림 수신동의<!-- 이벤트 알림 및 이메일 수신 동의 --></label></p>
				</div>
				<div class="bt_btn_r">
					<button id="btn_join" class="btn_join" type="button" value="회원가입">회원가입</button>
				</div>
			</form>
		</div>
		<div style="clear:both;"></div>
	</div>
</div>
