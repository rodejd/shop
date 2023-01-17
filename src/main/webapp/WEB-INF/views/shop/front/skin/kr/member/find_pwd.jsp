<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<form method=post name=fm action="/shop/member/find_pwd" id=form>
	<input type="hidden" name="act" value="Y">
	
	<style>
	input::placeholder {color:#b2b2b2;}
	</style>
	<div class="find_pw_wrap">
		<div class="stit01">FORGOTTEN YOUR PASSWORD?</div>

		<div class="in_bx">
			<dl>
				<dt>이메일 주소</dt>
				<dd>
					<input type="text" id="srchMail" name="srchMail" class="inp_ty01" size="29" placeholder="이메일 *" maxlength="50" required />
				</dd>
			</dl>
			<p><span>i</span>새 비밀번호를 만들 수있는 링크가 포함 된 이메일을 보내드립니다.</p>
			<div class="btm_btn">
				<input class="btn btn-primary btn-block active btnSubmit inp_email" type="button" value="이메일 받기" />
			</div>
		</div>
	</div>
</form>




<script type="text/javascript">
$(function(){
	<c:if test="${not empty msg}">
		var msg = "";
		<c:choose>
			<c:when test="${msg eq 'M01'}">msg = "메일로 임시 비밀번호를 전송했습니다."; </c:when>
			<c:when test="${msg eq 'M02'}">msg = "사용자정보가 존재하지 않습니다."; </c:when>
		</c:choose>
		alert(msg);
		<c:if test="${result == 'true'}">
			location.href = "/shop/member/login";
		</c:if>
	</c:if>
	
	$(".btnSubmit").on("click",function(){
		if( $.trim($("#srchMail").val()) == "" ){
			alert("이메일을 입력해주세요.");
			$("#srchMail").focus();
			return;
		}
		
		var regEmail = /^[^"'@]+@[._a-zA-Z0-9-]+\.[a-zA-Z]+$/;
		if (!regEmail.test($("#srchMail").val())){
			alert("잘못된 이메일 형식입니다");
			$("#srchMail").val("").focus();
			return;
		}
		
		$("#form").submit();
	});
});


</script>