//  탈퇴신청 체크
function chk_hackfm( fobj ){

	
	if ( fobj.password.value == ''){
		alert('비밀번호를 입력하여 주십시요.');
		fobj.password.focus();
		return false;
	}

	var outComplain = $('.js_outComplain');
	var cont = fobj.outComplain_text;
	var ckS = 0;

	for ( i = 0; i < outComplain.length; i++ ){

		if ( outComplain[i].checked == true ) break;
		else ckS++;
	}

	if ( ckS == outComplain.length ){

		alert('서비스불편사항을 1개이상 체크하여 주십시요. \n\n 해당사항은 개선사항에 반영되어집니다.');
		return false;
	}

	if ( !confirm ( '회원탈퇴를 하시면 회원님의 모든 데이타( 개인정보, 포인트 등 )가 삭제 되어집니다. \n 그래도 회원을 탈퇴하시겠습니까?' ) ){
		return false;
	} else {
		fobj.submit();
	}

}
//탈퇴신청 체크 sns
function sns_chk_hackfm( fobj ){

	
	var outComplain = $('.js_outComplain');
	var cont = fobj.outComplain_text;
	var ckS = 0;

	for ( i = 0; i < outComplain.length; i++ ){

		if ( outComplain[i].checked == true ) break;
		else ckS++;
	}

	if ( ckS == outComplain.length ){

		alert('서비스불편사항을 1개이상 체크하여 주십시요. \n\n 해당사항은 개선사항에 반영되어집니다.');
		return false;
	}

	if ( !confirm ( '회원탈퇴를 하시면 회원님의 모든 데이타( 개인정보, 포인트 등 )가 삭제 되어집니다. \n 그래도 회원을 탈퇴하시겠습니까?' ) ){
		return false;
	} else {
		fobj.submit();
	}

}