function chkForm2(fm)
{
	if (typeof(goIDCheck) != "undefined"){
		if (goIDCheck(fm) === false) return false;
	}

	return chkForm(fm);
}

$(document).ready(function(){
	
	$('#pre_btn').on("click",function(event){
		event.preventDefault ? event.preventDefault() : event.returnValue = false;
		location.href = history.back();
	});
	
	$("#join_btn").on("click", function(event){
		event.preventDefault ? event.preventDefault() : event.returnValue = false;
		if(!($("input[name=chk1]").is(":checked"))){
			alert("이용약관에 동의하여 주세요.");
			$("input[name=chk1]").focus();
			
		}else if(!($("input[name=chk2]").is(":checked"))){
			alert("개인정보 수집에 동의하여 주세요.");
			$("input[name=chk2]").focus();
		}else{
			
			$("#chkForm").submit();
			
		}
		
	});
	
	$('.js_allCheck').on('click', function(event) {
		if(this.checked) {
			$('input[name=chk1]').prop('checked', true);
			$('input[name=chk2]').prop('checked', true);
			event.stopPropagation();
		} else {
			$('input[name=chk1]').prop('checked', false);
			$('input[name=chk2]').prop('checked', false);
			event.stopPropagation();
		}
		
	});
});

/**
$("#totalChk").on("click", function(){
	var chk = $("#totalChk").is(":checked");
	if(chk){
		$("input[name=chk1]").prop("checked",true);
		$("input[name=chk2]").prop("checked",true);
	}else{
		$("input[name=chk1]").prop("checked",false);
		$("input[name=chk2]").prop("checked",false);
	}
});
**/