$(function(){
	// 글자 수 제한
	$('#frm01').on('submit', function(){
		var subject = $('#subject').val();
		var contents = $('#contents').val();
		var mode = '${mode}';
		var selected = $('select[name=category]').val();
		
		if (mode == 'modify') {
			if ($('input[name=attach0]').val() == '') {
				$('input[name=attach0]').val($('#cFile').text());
			}
		}
		
		if(subject == "") {
			alert("제목을 입력해주세요");
			$('#subject').focus();
		
			return false;
		}
		
		if(contents == "") {
			alert("내용을 입력해주세요");
			$('#contents').focus();
			
			return false;
		}
		
		if(contents.length > 10000) {
			alert("내용은 10000자 이하로만 입력이 가능합니다.");
			$('#contents').focus();
			
			return false;
		}
	});
	
	// 파일 첨부 용량 제한
	$('#attach').on('change', function(event){
		var nowFileSize = this.files[0].size;
		var maxSize = parseInt('${fileMaxSize}');
		console.log("현재 파일용량 : "+nowFileSize +", 최대 허용 가능 파일용량: "+ maxSize);
		if(nowFileSize > maxSize){
			alert('허용 가능한 파일 사이즈를 초과했습니다.');
			$('#attach').val('');
		}
		
	});
	// 리셋
	$('#resetCall').on('click', function(){
		location.reload();
	});
});