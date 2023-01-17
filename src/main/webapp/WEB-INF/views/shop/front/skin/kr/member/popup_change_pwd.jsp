<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<form method=post name=fm action="/shop/member/find_pwd" id=form>
	<input type="hidden" name="token" value="${token}">
	
	<style>
	input::placeholder {color:#b2b2b2;}
	</style>
	<div style="padding:0 0 0 0; margin:0 auto;">
		<c:if test="${result == 'true'}">
			<div style="text-align:center; font-family: 'Raleway', sans-serif; font-weight:600; font-size:35px; color:#313131; line-height:35px; padding:95px 0 60px 0;">비밀번호 변경</div>
	
			<div style="width:630px; padding:0 0 150px 0; margin:0 auto;">
				<dl style="padding:0 0 35px 0; margin:0;">
					<dt style="font-weight:600; font-size:20px; color:#282828; line-height:20px; padding:0 0 25px 0;">비밀번호</dt>
					<dd>
						<input type="password" id="join_pwd1" name="join_pwd1" class="" size="29" placeholder="비밀번호 *" maxlength="50" required style="width:100%; height:44px; font-size:15px; padding:0 0 0 10px; border:1px solid #dbdbdb; border-radius:0; box-shadow:none;"/>
					</dd>
					<dt style="font-weight:600; font-size:20px; color:#282828; line-height:20px; padding:25px 0 25px 0;">비밀번호 확인</dt>
					<dd>
						<input type="password" id="join_pwd2" name="join_pwd2" class="" size="29" placeholder="비밀번호 확인 *" maxlength="50" required style="width:100%; height:44px; font-size:15px; padding:0 0 0 10px; border:1px solid #dbdbdb; border-radius:0; box-shadow:none;"/>
					</dd>
				</dl>
				
				<div style="text-align: center;">
					<a href="javascript:;">
						<input class="btn btn-primary btn-block active" id="btn_submit" type="button" value="비밀번호 변경" style="width:280px; height:60px; font-size:20px; color:#fff; line-height:60px; padding:0; margin:0 auto; background:#2d2d2d; border:0; border-radius:0; text-align:center;" />
					</a>
				</div>
			</div>
		</c:if>
	</div>
</form>


<script src="//code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="/resources${shopLibFunction:webSkin(pageContext.request.requestURL)}/js/member/join_input.js"></script>
<script type="text/javascript">
<c:choose>
	<c:when test="${result == 'true'}">
		$(function(){
			$("#btn_submit").on("click",function(){
				if( $.trim($("#join_pwd1").val()) == "" ){
					alert("비밀번호를 입력해주세요.");
					$("#join_pwd1").focus();
					return;
				}
				
				var pwdVal = pwdValidation.isValidPassword($("#join_pwd1").val(), '');
				if(!pwdVal.rtnBoolean) {
					var alt = "형식에 맞는 비밀번호를 입력하세요.\n";
						alt += "① 최소 8자 ~ 최대 20자 이내로 입력합니다.\n";
						alt += "② 반드시 영문, 숫자, 특수문자가 각 1자리 이상 포함되어야 합니다.\n";
						alt += "③ 특수문자 중 <, >, (, ), #, ', /, | 는 사용할수 없습니다.\n";
						alt += "④ 3자리 이상 연속되는 숫자 또는 문자열은 사용할 수 없습니다. (예:123, 321, 012, abc, cba)\n";
						alt += "⑤ 3자리 이상 동일한 숫자 또는 문자열은 사용할 수 없습니다. (예:000, 111, 222, ,aaa, bbb)\n";
						alt += "⑥ 아래와 같은 문자는 사용할 수 없습니다. (love, happy, qwer, asdf, zxcv, test, gpin, ipin)\n";
						alt += "⑦ 아이디와 연속한 3자리 이상 일치하는 비밀번호는 사용할 수 없습니다.";
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
				
				$.ajax({
					data : $("#form").serialize(),
					dataType : 'json',
					type : 'POST',
					async: false,
					url : '/shop/member/changePwdIndb.dojson',
					success : function (res) {
						if(res.result) {
							alert('비밀번호가 변경되었습니다.');
							window.open('', '_self', '');
							window.close();
						} else {
							var msg = "";
							if(res.msg == "M01")  msg = "비정상적인 접근입니다.";
							else if(res.msg == "M02")  msg = "회원정보가 존재하지 않습니다.";
							else if(res.msg == "M03")  msg = "이미 비밀번호가 변경되었습니다.";
							else if(res.msg == "M04")  msg = "비밀번호 변경 가능시간이 만료되었습니다.";
							else if(res.msg == "M05")  msg = "비밀번호가 일치하지 않습니다.";
							else if(res.msg == "M06")  msg = "오류가 발생했습니다.";
							alert(msg);
						}
					}
				});
			});
		});
	</c:when>
	<c:otherwise>
		$(function(){
			var msgCode = "${msg}";
			var msg = "";
			if(msgCode == "M01")  msg = "비정상적인 접근입니다.";
			else if(msgCode == "M02")  msg = "회원정보가 존재하지 않습니다.";
			else if(msgCode == "M03")  msg = "이미 비밀번호가 변경되었습니다.";
			else if(msgCode == "M04")  msg = "비밀번호 변경 가능시간이 만료되었습니다.";
			else if(msgCode == "M05")  msg = "오류가 발생했습니다.";
			else if(msgCode == "M06")  msg = "";
			alert(msg);
			window.open('', '_self', '');
			window.close();
		});
	</c:otherwise>
</c:choose>
</script>