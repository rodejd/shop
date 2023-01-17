function doCheck(id) {
	if ($('input[name=confirmPassword]').val().trim() == '') {
		alert('비밀번호를 입력해 주세요');
		$('input[name=confirmPassword]').focus();
		
		return false;
	}
	
	var password =  $('input[name=confirmPassword]').val().trim();
	
	// 비밀번호 체크
	$.ajax({
		data : {
			'id' : id,
			'no' : $('#rtlno').val(),
			'password' : password,
			'order' : 'checkPasswordBoard'
		},
		dataType : 'json',
		type : 'POST',
		url : '/shop/board/indbAjax.jsp',
		success : function (data) {
			if (data.result == 1) {
				$.magnificPopup.close();
				location.href='view.jsp?id=' + id + '&no=' + $('#rtlno').val();
			} else {
				alert('비밀번호가 틀렸습니다');
				$('input[name=confirmPassword]').val('');
				$('input[name=confirmPassword]').focus();
				
				return false;
			}
		},
		error : function (e) {
			alert(e);
		}
	});
}