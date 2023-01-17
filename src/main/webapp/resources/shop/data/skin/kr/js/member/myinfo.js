function chkId() {
	var form = document.frmMember;
	if (!chkText(form.m_id, form.m_id.value, "아이디를 입력해주세요")){
		return;
	}
		
	if (!chkPatten(form.m_id, form.m_id.getAttribute('option'), "아이디는 4자 이상 10자 이하의 영문자,숫자 조합만 가능합니다")){
		return;
	}
		
	var url = "/shop/member/indb.jsp?mode=chkId&m_id="+form.m_id.value;
	
	go_ajax('POST', url, success_chkid);
	 
}

function success_chkid(response, status, error){
	var result = $(response).find("result").text();
	
	if(result == 9){
		alert('사용 불가능한 아이디입니다');
	}else if(result == 1){
		alert('이미 등록된 아이디입니다');
	}else{
		alert('사용이 가능합니다');
		$('#chk_id').val('1');
	}
}

function chkEmail() {
	var form = document.frmMember;
	if (!chkText(form.email, form.email.value, "이메일을 입력해주세요")){
		return;
	}
	if (!chkPatten(form.email, form.email.getAttribute('option'), "정상적인 이메일 주소를 입력해주세요")){
		return;
	}
	
	var url = "/shop/member/indb.jsp?mode=chkEmail&email="+ form.email.value + "&m_id=" + form.m_id.value;
	
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

function success_nickname(response, status, error){
	var result = $(response).find("result").text();
	
	if(result > 0){
		alert('이미 등록된 닉네임입니다. 다시 작성해 주십시요!');
	}else{
		alert('사용이 가능합니다');
		$('#chk_nickname').val('1');
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