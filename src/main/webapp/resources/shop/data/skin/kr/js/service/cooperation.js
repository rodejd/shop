function mailSelect(){
	var email3 = $("#mailsel").val();
	$("input[name=email2]").val(email3);
}

function chkForm2(obj){
	if (chkForm(obj)){
			obj.mail.value = obj.email1.value +"@"+ obj.email2.value;
		return true;
	}else {
		return false;
	}
}

function fm_save(){
	

	if ( $('#name').val() == "" ){
		alert( "이름을 입력하지 않았습니다.\n이름을 입력하세요!!" );
		$('#name').focus() ;
		return;
	}

	if ( $('#email1').val() == ""){
		alert( "메일 아이디를 입력하지 않았습니다.\n메일을 입력하세요!!" );
		$('#email1').focus() ;
		return;
	}
	
	if ( $('#email2').val() == ""){
		alert( "메일 도메인를 입력하지 않았습니다.\n메일을 입력하세요!!" );
		$('#email2').focus() ;
		return;
	}
	
	$("#email").val($('#email1').val()+'@'+$('#email2').val());

	if ( $('#itemcd').val() == "" ){
		alert( "문의분야를 선택하지 않았습니다.\n문의분야를 선택하세요!!" );
		$('#itemcd').focus() ;
		return;
	}

	if ( $('#title').val() == ""){
		alert( "제목을 입력하지 않았습니다.\n제목을 입력하세요!!" );
		$('#title').focus() ;
		return;
	}

	if ( $('#content').val() == ""){
		alert( "내용을 입력하지 않았습니다.\n내용을 입력하세요!!" );
		$('#title').focus() ;
		return;
	}

	$('#form01').submit();
}