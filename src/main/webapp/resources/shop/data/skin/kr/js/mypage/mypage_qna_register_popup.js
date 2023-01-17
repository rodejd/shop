function mailSelect(){
	var email3 = $("#mailsel").val();
	$("input[name=email2]").val(email3);
}

function chkForm2(obj){
	if (chkForm(obj)){
			obj.email.value = obj.email1.value +"@"+ obj.email2.value;
		return true;
	}else {
		return false;
	}
}