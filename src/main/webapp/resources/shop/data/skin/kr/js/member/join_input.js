/* 2017-09-06 체크박스 갯수체크 추가 */
$(function(){
	//관심분야 3개초과하여 선택 시
	$('input:checkbox[name="interest"]').on('click', function(){
		var count = 0;
		$('input:checkbox[name="interest"]').each(function(){
			if($(this).is(':checked')) {
				count += 1;   
			}
			if(count >3){
				alert("관심분야는 3개이하로 선택 가능합니다")
				$(this).attr("checked", false)
				return false;
			}
		});
	});
	
	//추천인 아이디확인버튼 클릭시
	$('#checkRecommidButton').on('click', function(){
		var recommidField = $('#recommid')[0];
		var recommid = $('#recommid').val();
		if(chkText( recommidField, recommid, "추천인 아이디를 입력해주세요") ){
			var url = ctx + "/shop/member/chkId";
			joinAjax(url, {mid : recommid}, successRecommid);
		}
	});
	
	//추천인 아이디 확인 이후 아이디 다시 입력 시 check값 초기화
	$('#recommid').keydown(function() {
		$('#checkRecommid').val('');
	});
	
	//2017-09-20 추가 - 닉네임 입력 시 check값 초기화
	$('#nickname').keydown(function() {
		$('#chk_nickname').val('');
	});
	
	//아이디 입력시 아이디 check값 초기화
	$('#m_id').keydown(function() {
		$('#chk_id').val('');
	});
});

//추천인 아이디 확인 
function successRecommid(result, status, error) {
	if (result == 1) {
		alert("확인되었습니다.");
		$('#checkRecommid').val('1');
	} else {
		alert("추천인 아이디가 존재하지 않습니다.");
		$('#checkRecommid').val('0');
	}
}

//추천인 아이디 유효성 검사
function isUsableRecommid() {
	var usedRecommid = $('#recommidUse').val();	// 1: 사용,2:필수
	var recommid = $('#recommid').val();
	
	if ("2" == usedRecommid) {	//가입항목 추천인 필수일 때
		//추천인 아이디확인여부 체크
		return isCheckedRecommid();
	} else if ("1" == usedRecommid) {	//가입항목 추천인 사용할 때
		recommid = recommid.replace("　", "");
		recommid = recommid.replace(/\s*/, "");
		if("" != recommid) {
			return isCheckedRecommid();
		} else {
			return true;
		}
	} else {
		return true;
	}
}

//추천인 아이디확인여부 체크
function isCheckedRecommid() {
	var checkedRecommid = $('#checkRecommid').val();
	
	if ("0" == checkedRecommid) {
		alert("추천인 아이디가 존재하지 않습니다.");
		$('#recommid').focus();
		return false;
	} else if ("1" == checkedRecommid) {
		return true;
	} else {
		alert("추천인 아이디를 확인해주세요.");
		$('#recommid').focus();
		return false;
	}	
}

function chkId() {
	var form = document.frmMember;
	if (!chkText(form.m_id, form.m_id.value, "아이디를 입력해주세요")){
		return;
	}
	
	if (!chkPatten(form.m_id, form.m_id.getAttribute('option'), "아이디는 4자 이상 10자 이하의 영문자,숫자 조합만 가능합니다")){
		return;
	}
	
	var id = $('.js_joinID').val();
	var url = ctx + "/shop/member/chkId";
	var param = {
			mid : id
	}
		
	joinAjax(url, param, success_chkid);
	 
}

function success_chkid(result, status, error){
	if(result == 9){
		alert('사용 불가능한 아이디입니다');
		$('#chk_id').val('0');
	}else if(result == 1){
		alert('이미 등록된 아이디입니다');
		$('#chk_id').val('0');
	}else{
		alert('사용이 가능합니다'); 
		$('#chk_id').val('1');
	}
}

function chkEmail() {
	var form = document.frmMember;
	if (!chkText(form.email1, form.email1.value, "이메일을 입력해주세요")){
		return;
	}else if(!chkText(form.email2, form.email2.value, "이메일을 입력해주세요")){
		return;
	}
	if (!chkPatten(form.email, form.email.getAttribute('option'), "정상적인 이메일 주소를 입력해주세요")){
		return;
	}
	var url = ctx + "/shop/member/indb?mode=chkEmail&email="+ form.email.value + "&m_id=" + form.m_id.value;
	
	go_ajax('POST', url, success_chkEmail);
}

function success_chkEmail(response, status, error){
	var result = $(response).find("result").text();
	
	if(result > 0){
		alert('이미 등록된 이메일입니다. 다시 작성해 주십시요!');						
	}else{
		alert('사용이 가능합니다');
		$('#chk_email').val('1');
	}
}

function chkNicknameJoin() {
	var form = document.frmMember;
	if (!chkText(form.nickname, form.nickname.value, "닉네임을 입력해주세요")){
		return;
	}

	var url		= ctx + "/shop/member/chkJoinNickName";
	var param 	=  {
			nickname : form.nickname.value,
	};
	joinAjax(url, param, success_nickname);
}

function chkNickname() {
	var form = document.frmMember;
	if (!chkText(form.nickname, form.nickname.value, "닉네임을 입력해주세요")){
		return;
	}

	var url		= ctx + "/shop/member/chkNickName";
	var param 	=  {
			nickname : form.nickname.value,
	};
	joinAjax(url, param, success_nickname);
}
	
function success_nickname(result, status, error){
	
	if(result > 0){
		alert('이미 등록된 닉네임입니다. 다시 작성해 주십시요!');
		$('#chk_nickname').val('0');
	}else{
		alert('사용이 가능합니다');
		$('#chk_nickname').val('1');
	}
}

//2017-09-20 수정 - nickname 관리자 가입항목 사용, 필수 여부에 따른 validation 체크여부 추가
//닉네임 항목 관리자 가입항목 사용,필수 여부에 따른 유효성 검사
function isUsableNickname() {
	var usedNickname = $('#nicknameUse').val();
	var nickname = $('#nickname').val();
	if ("2" == usedNickname) {	//가입항목 닉네임 필수일 때
		//유효한 닉네임인지 체크
		return isCheckedNickname();
	} else if ("1" == usedNickname) {	//가입항목 닉네임 사용할 때
		nickname = nickname.replace("　", "");
		nickname = nickname.replace(/\s*/, "");
		if("" != nickname) {
			//입력란에 값이 있을 때 유효한 닉네임인지 체크
			return isCheckedNickname();
		} else {
			return true;
		}
	} else {
		return true;
	}
}

//닉네임 중복확인 여부 체크
function isCheckedNickname() {
	var checkedNickname = $('#chk_nickname').val();		//닉네임중복확인 여부
	
	if ("0" == checkedNickname) {
		alert('이미 등록된 닉네임입니다. 다시 작성해 주십시요!');
		$('#nickname').focus();
		return false;
	} else if ("1" == checkedNickname) {
		return true;
	} else {
		alert("닉네임 중복확인을 해주세요.");
		$('#nickname').focus();
		return false;
	}
}

 function chkBirth(obj) {
	var birth = obj.value;
	var objBirth = document.getElementsByName('birth[]');
	obj.form.birth_year.value = (birth) ? "19" + birth.substring(0, 2) : "";
	objBirth[0].value = birth.substring(2, 4);
	objBirth[1].value = birth.substring(4, 6);
} 

function chkSex(obj) {
	var resno = obj.value;
	if (resno){
		obj.form.sex[1 - resno.substring(0, 1) % 2].checked = true;
	}
}

function chkBirthSex(obj) {
	//var birth = obj1.value;
	var objResno = document.getElementsByName('resno[]');
	var objBirth = document.getElementsByName('birth[]');
	var resno_1 = objResno[0].value;				// 080806
	var resno_2 = objResno[1].value;				// 3055912
	
	var _year = "19";

	if (resno_1 && resno_2){
		obj.form.sex[1 - resno_2.substring(0, 1) % 2].checked = true;
		
		if(resno_2.substring(0, 1) > 2){
			_year = "20";
		}
		
		obj.form.birth_year.value = (resno_1) ? _year + resno_1.substring(0, 2) : "";
		objBirth[0].value = resno_1.substring(2, 4);
		objBirth[1].value = resno_1.substring(4, 6);
	}
}

function regPassword(){	
	if($("input[name=password]").val() == $("input[name=password2]").val() && $("input[name=password]").val() != ""){
		$("#msg").html('');
		
		checkedPwd();
	}else{
		$("#msg").html('비밀번호가 일치 하지 않습니다.');
	}	
}

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

function mailSelect(){
	var email2 = $("#mailsel").val();
	$("input[name=email2]").val(email2);
}

function submitJoin() {
	
	//2017-09-20 수정 - 
	//validation 순서 수정 및  관리자 가입항목 사용, 필수 여부에 따른 validation 체크여부 추가
	
	//id 체크
	var form = document.frmMember;
	if (!chkText(form.m_id, form.m_id.value, "아이디를 입력해주세요")){
		$('#m_id').focus();
		return;
	}
	var checkedID = $('#chk_id').val();
	if ("0" == checkedID) {
		alert('사용 불가능한 아이디입니다');
		$('#m_id').focus();
		return false;
	} else if ("0" != checkedID &&"1" != checkedID) {
		alert("아이디 중복확인을 해주세요");
		$('#m_id').focus();
		return false;
	}
	
	//비밀번호 체크
	if(!checkedPwd) {
		return false;
	}
	
	//nickname 체크
	if(!isUsableNickname()) {
		return false;
	}
	
	//2017-08-31 validation 검사 추가
	for(var i=0; i<$('input[type="text"]').length; i++){
		if($('input[type="text"]')[i].dataset.reg != null && $('input[type="text"]')[i].dataset.reg != "" && $('input[type="text"]')[i].value != "") {
			if(! (chkPatten($('input[type="text"]')[i], $('input[type="text"]')[i].dataset.reg)) ){
				return false;
			}
		}
	}
	
	//추천인 아이디 유효성검사
	if (isUsableRecommid()) {
		var $form 	= $('.js_form'),
		url		= ctx + "/shop/member/joinIndb"
		data 	= $form.serialize(),
		cbf		= function(result) {
			if(!isNaN(result)) {
				if((result * 1) > 0) {
					alert('회원가입 쿠폰이 발급되었습니다.');
				}
				alert('회원 가입이 완료 되었습니다.');
				location.href = ctx + '/shop/main/index';
			} else {
				alert(result);
			}
		};
		if(checkForm($form[0])) {
			$.ajax({
				url : url,
				processData : false,
				contentType : false,
				data : new FormData($form[0]),
				type : 'POST',
				success : cbf
			});
		}
	}
}
function submitJoinSns() {
	//sns로 회원가입시 
	//2017-11-14 
	//validation 순서 수정 및  관리자 가입항목 사용, 필수 여부에 따른 validation 체크여부 추가
	
	//id 체크
	var form = document.frmMember;
		
	//nickname 체크
	if(!isUsableNickname()) {
		return false;
	}
	
	//2017-08-31 validation 검사 추가
	for(var i=0; i<$('input[type="text"]').length; i++){
		if($('input[type="text"]')[i].dataset.reg != null && $('input[type="text"]')[i].dataset.reg != "" && $('input[type="text"]')[i].value != "") {
			if(! (chkPatten($('input[type="text"]')[i], $('input[type="text"]')[i].dataset.reg)) ){
				return false;
			}
		}
	}
	
	//추천인 아이디 유효성검사
	if (isUsableRecommid()) {
		var $form 	= $('.js_form'),
		url		= ctx + "/shop/member/joinIndb"
		data 	= $form.serialize(),
		cbf		= function(result) {
			if(!isNaN(result)) {
				if((result * 1) > 0) {
					alert('회원가입 쿠폰이 발급되었습니다.');
				}
				alert('회원 가입이 완료 되었습니다.');
				location.href = ctx + '/shop/main/index';
			} else {
				alert(result);
			}
		};
		if(checkForm($form[0])) {
			$.ajax({
				url : url,
				processData : false,
				contentType : false,
				data : new FormData($form[0]),
				type : 'POST',
				success : cbf
			});
		}
	}
}

function checkForm(form)
{
	
	//2017-09-20 수정 - validation 순서 수정
	
	// 전화번호 Validation
	if($("input:text[name='phone']").length != 0){
		if(
				!(($("input:text[name='phone']")[0].value != '' && 		// 모든값이 다 입력되었을 경우
				 $("input:text[name='phone']")[1].value != '' && 
				 $("input:text[name='phone']")[2].value != '')
				 ||
				($("input:text[name='phone']")[0].value == '' && 		// 값이 하나도 입력되지 않았을 경우
				 $("input:text[name='phone']")[1].value == '' && 
				 $("input:text[name='phone']")[2].value == ''))
		){
			if($("input:text[name='phone']")[0].value == ''){
				alert('전화번호 앞자리를 입력하세요.');
				$("input:text[name='phone']")[0].focus();
				return false;
			}
			if($("input:text[name='phone']")[1].value == ''){
				alert('전화번호 중간자리를 입력하세요.');
				$("input:text[name='phone']")[1].focus();
				return false;
			}
			if($("input:text[name='phone']")[2].value == ''){
				alert('전화번호 끝자리를 입력하세요.');
				$("input:text[name='phone']")[2].focus();
				return false;
			}
		}
	}
	
	// 핸드폰번호 Validation
	if($("input:text[name='mobile']").length != 0){
		if(
				!(($("#mobile0").val() != '' && 		// 모든값이 다 입력되었을 경우
				 $("#mobile1").val() != '' && 
				 $("#mobile2").val() != '')
				 ||
				($("#mobile0").val() == '' && 		// 값이 하나도 입력되지 않았을 경우
				 $("#mobile1").val() == '' && 
				 $("#mobile2").val() == ''))
		){
			if($("#mobile0").val() == ''){
				alert('핸드폰번호 앞자리를 입력하세요.');
				$("#mobile0").focus();
				return false;
			}
			if($("#mobile1").val() == ''){
				alert('핸드폰번호 중간자리를 입력하세요.');
				$("#mobile1").focus();
				return false;
			}
			if($("#mobile2").val() == ''){
				alert('핸드폰번호 끝자리를 입력하세요.');
				$("#mobile2").focus();
				return false;
			}
		}
	}
	
	//email 체크
	var usedEmail = $('#emailUse').val(); //1:사용, 2:필수항목
	var email = $("input[name=email1]").val() + "@" + $("input[name=email2]").val();
	var e_regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
	
	if ("1" == usedEmail) {
		var emailID = $("input[name=email1]").val();
		emailID = emailID.replace("　", "");
		emailID = emailID.replace(/\s*/, "");
		if ("" != emailID) {
			if(!($("input[name=email1]").val() == undefined)){
				if(e_regex.test(email)===false){
					 alert("잘못된 이메일 형식입니다.");
					 return false;
				}
			}		
		}
	} else if ("2" == usedEmail) {
		if(!($("input[name=email1]").val() == undefined)){
			if(e_regex.test(email)===false){
				 alert("잘못된 이메일 형식입니다.");
				 return false;
			}
		}
	}
	
	//추천인 아이디 체크
	if(!isUsableRecommid()) {
		return false;
	}

	
	if (typeof(mini_obj)!="undefined" || document.getElementById('_mini_oHTML')) mini_editor_submit();

	var reschk = 0;
	for (i=0;i<form.elements.length;i++){
		currEl = form.elements[i];
		if (currEl.disabled) continue;
		if (currEl.getAttribute("required")!=null){
			if (currEl.type=="checkbox" || currEl.type=="radio"){
				if (!chkSelect(form,currEl,currEl.getAttribute("msgR"))) return false;
			} else {
				if (!chkText(currEl,currEl.value,currEl.getAttribute("msgR"))) return false;
			}
		}

		if (currEl.getAttribute("label")=='주민등록번호'  && currEl.getAttribute("name") == 'resno' && currEl.value.length>0){
			reschk = 1;

		}
		if (currEl.getAttribute("option")!=null && currEl.value.length>0){
			if (!chkPatten(currEl,currEl.getAttribute("option"),currEl.getAttribute("msgO"))) return false;
		}
		if (currEl.getAttribute("minlength")!=null){
			if (!chkLength(currEl,currEl.getAttribute("minlength"))) return false;
		}
	}
	if (form.password2){
		if (form.password.value!=form.password2.value){
			alert("비밀번호가 일치하지 않습니다");
			form.password.value = "";
			form.password2.value = "";
			return false;
		}
	}
	
	// 관심분야 Validation
	//  관심분야 필수사항인지 체크하여 검사
	if($('.js_interestReq').data('interestReq') == 'checked') {
		if($("input:checkbox[name='interest']").length != 0){
			if(!$("input:checkbox[name='interest']").is(":checked")) {
				alert('관심분야를 한개 이상 체크하세요.');
				return false;
			}
		}
	}
	
	if (reschk && !chkResno(form)) return false;

	if (form.chkSpamKey) form.chkSpamKey.value = 1;
	if (document.getElementById('avoidDbl')) document.getElementById('avoidDbl').innerHTML = "--- 데이타 입력중입니다 ---";
	return true;
}

function joinAjax(url, data, callBackFnc) {
	$.post(url, data, callBackFnc);
}

function setBirthDate(value) {
	var $birthDate = $('.js_birthDate'),
		birthYear = $('.js_birthYear').val(),
		birthMonth = $('.js_birthMonth').val();
		
	if(birthYear != "" && birthMonth != "") {
		var date = new Date();
		date.setYear(birthYear);
		date.setMonth(birthMonth);
		date.setDate(0);
		var birthMaxDate = date.getUTCDate();
	}
	
	$birthDate.find('.js_birthDateOption').remove();

	for(i = 1; i <= birthMaxDate; i++) {
		$birthDate.append('<option value="' + i + '" class="js_birthDateOption">' + i + '</option>');
	}		
	
	if(value == 'modify') {
		var userBirthDate = $('.js_birthDate').data('birthDate');
		$birthDate.val(userBirthDate).attr('selected', 'selected');
	}
}

$(document).ready(function(){
	$("input[name=marriyn]").on("click", function(){
		
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
	}),
	$('.js_birthYear').on("change", setBirthDate),
	$('.js_birthMonth').on("change", setBirthDate);
	setBirthDate('modify');
});


/* 2017-08-30 추가 - 패스워드 유효성검사 */
function checkedPwd(){
	var pwdVal = pwdValidation.isValidPassword($('#password').val(), $('#m_id').val());
	
	if(!pwdVal.rtnBoolean) {
		alert("형식에 맞는 비밀번호를 입력하세요.");
        $("input[name=password").val('');
        $("input[name=password2").val('');
        $("input[name=password").focus();
        
        return false;
	}
	
	return true;
}

var pwdValidation = {
	//-----------------------------------------------------------------
	isValidPassword : function(pwd, uid){
		var strPwd			= pwd.trim(),			// 비밀번호
			sumPass0		= 0,					// 연속 동일문자 사용 숫자
			sumPass1		= 0,					// 연속문자 사용 숫자
			sumPass2		= 0,					// 연속문자 사용 숫자
			sumPass3		= 0,					// 비밀번호 3자리 아이디와 같은 것은 사용할 경우 1
			pwdCharArray 	= [],// 지정 문자 
			charCheckBoolean	= false,			// 지정문자 사용시 체크
			rtnParamObj			= null
			;
		
 		// 문자열에 공백이 있는지 체크 space
		var retVal = isWithBlank(pwd);
		if( !retVal ){ 
			// 1 : 비밀번호에 탭, 공백 이 들어 갈수 없습니다.
			return rtnParamObj = {
						rtnBoolean	: false,
						rtnMsgType	: "1"
					};
		}
		
		// 비밀번호가 8자리 보다 작을 경우 false 처리
		if( strPwd.length < 8 || strPwd.length > 20 ) {
			//alert("비밀번호는 영문, 숫자, 특수문자(!,@,$,%,^,&,? 만 허용) 를 사용하여 8~20자리까지 구분합니다.");
			// 2 : 비밀번호는 영문, 숫자, 특수문자(!,@,$,%,^,&,? 만 허용) 를 사용하여 8~20자리까지 구분합니다.
			return rtnParamObj = {
					rtnBoolean	: false,
					rtnMsgType	: "2"
				};
		}
		
		// 사용할 수 없는 특수문자를 사용했는지 체크
		var arr = ['<','>','(',')','‘','/','|'];
		for ( var i=0 ; i < arr.length ; i++ ) {
			if ( -1 < strPwd.indexOf(arr[i]) ) {
				return rtnParamObj = {
						rtnBoolean	: false,
						rtnMsgType	: "3"
					};
			}
		}
		
		// 정규식 숫자, 문자, 특수문자 허용 범위
		var isPw = /(?=.*[a-zA-Z])(?=.*[~!@#$%^&*-_=+])(?=.*[0-9])/;
		if( !isPw.test(strPwd) ){
			// 2 : 비밀번호는 영문, 숫자, 특수문자(!,@,$,%,^,&,? 만 허용) 를 사용하여 10~18자리까지 구분합니다.
			return rtnParamObj = {
						rtnBoolean	: false,
						rtnMsgType	: "4"
					};
		}
		
		// 연속 문자 체크
		/*for( var i = 0; i < strPwd.length; i++ ){
			var charPwd0 = strPwd.charAt(i),
				charPwd1 = strPwd.charAt(i + 1),
				charPwd2 = strPwd.charAt(i + 2)
				;
			
			// 연속 동일 문자 체크
			if( charPwd0 == charPwd1 && charPwd0 == charPwd2 ){
				sumPass0++;
				break;
			}
			// 321 연속 문자 체크
			if( charPwd0.charCodeAt(0) - charPwd1.charCodeAt(0) == 1 && (charPwd1.charCodeAt(0) - charPwd2.charCodeAt(0)) == 1 ) {
				sumPass1++;
				break;
			}
			// 123 연속 문자 체크
			if( (charPwd0.charCodeAt(0) - charPwd1.charCodeAt(0)) == -1 && (charPwd1.charCodeAt(0) - charPwd2.charCodeAt(0)) == -1 ) {
				sumPass2++;
				break;
			}
		}*/
		
		// 지정한 문자열 아이디 체크
		/*charCheckBoolean = false;
		for(var i = 0; i < pwdCharArray.length; i++ ){
			if( strPwd.indexOf(pwdCharArray[i]) > -1) {
				charCheckBoolean = true;
				break;
			}
		}*/
		
		// 비밀번호중 아이디와 3자리이상 같은 것을 체크 
		/*for ( var i=0 ; i < strPwd.length ; i++ ) {
			var tmpPass3 = strPwd.substring(i, i+3);
			if ( 3 == tmpPass3.length && -1 < uid.indexOf(tmpPass3) ) {
				sumPass3++;
				break;
			}else if ( 3 > tmpPass3.length ){
				break;
			}
		}*/
		
		// 비밀번호중 아이디와 3자리이상 같은 것을 체크 
		if ( sumPass3 > 0 ) {
			return rtnParamObj = {
					rtnBoolean	: false,
					rtnMsgType	: "5"
				};
		}
		
		// 연속동일문자 체크 
		if( sumPass0 > 0 ){
			// 3 : 연속으로 동일문자를 3자리 이상 사용 할수 없습니다.
			return rtnParamObj = {
					rtnBoolean	: false,
					rtnMsgType	: "6"
				};
		}
		// 연속 문자 체크
		if( sumPass1 >= 1 || sumPass2 >= 1 ){
			//alert("연속된 문자(123, 321, abc, cba 등)을 \n 3자 이상 사용 할 수 없습니다.");
			
			// 4 : 연속된 문자(123, 321, abc, cba 등)을 \n 3자 이상 사용 할 수 없습니다.
			return rtnParamObj = {
					rtnBoolean	: false,
					rtnMsgType	: "7"
				};
		}
		
		// 지정 문자
		if( charCheckBoolean ) {
			//alert("비밀번호에 (love, happy, qwer, asdf, zxcv, test, gpin, ipin)은 사용 할수 없습니다.");
			// 5 : 비밀번호에 (love, happy, qwer, asdf, zxcv, test, gpin, ipin)은 사용 할수 없습니다.
			return rtnParamObj = {
					rtnBoolean	: false,
					rtnMsgType	: "8"
				};
		}
		
		return rtnParamObj = {
				rtnBoolean	: true,
				rtnMsgType	: ""
			};
	}
	//-----------------------------------------------------------------
}

function isWithBlank(pwd){
	if(pwd.search(/\s/) != -1){
		return false;
	} else {
		return true;
	}
}


