function commentModify(fnum, nume, cmboolean, comleng)
	{
		var arrCMviewName = ["CMMODIFY_","CMMODIFYCANCEL_","CMMODIFYTXT_","CMMODIFYMEMO_"];
	
	if(cmboolean=="false")
	{
		for(i=0; i < comleng; i++)
		{
			if(i==nume){
				document.getElementById(arrCMviewName[0]+fnum+i).style.display = 'none';
				document.getElementById(arrCMviewName[1]+fnum+i).style.display = 'block';
				document.getElementById(arrCMviewName[2]+fnum+i).style.display = 'none';
				document.getElementById(arrCMviewName[3]+fnum+i).style.display = 'block';
			}else{
				document.getElementById(arrCMviewName[0]+fnum+i).style.display = 'block';
				document.getElementById(arrCMviewName[1]+fnum+i).style.display = 'none';
				document.getElementById(arrCMviewName[2]+fnum+i).style.display = 'block';
				document.getElementById(arrCMviewName[3]+fnum+i).style.display = 'none';
			}
		}
	}else{
		document.getElementById(arrCMviewName[0]+fnum+nume).style.display = 'block';
		document.getElementById(arrCMviewName[1]+fnum+nume).style.display = 'none';
		document.getElementById(arrCMviewName[2]+fnum+nume).style.display = 'block';
		document.getElementById(arrCMviewName[3]+fnum+nume).style.display = 'none';
	}
}
function imgReSize(img)
{
	var width = img.width;
	var maxWidth = 600;
	if(width > maxWidth)
	{
		 img.width = maxWidth;
	}	
}

window.onload = function(){
	scrollBanner();
}

//<![CDATA[
$('.grid').masonry({
	// options
	itemSelector: '.grid-item',
	columnWidth: 300
});
//]]>

//댓글 등록
function addComment(id, no) {
	var name = '';
	var password = '';
	var memo = '';
	
	// 이름 체크
	if ($('input[name=name]').val().trim() == '') {
		alert('이름을 입력해 주세요');
		$('input[name=name]').focus();
		
		return false;
	} else {
		name = $('input[name=name]').val().trim();
	}
	
	// 비밀번호 체크
	if ($('input[name=password]').val().trim() == '') {
		alert('비밀번호를 입력해 주세요');
		$('input[name=password]').focus();
		
		return false;
	} else {
		password = $('input[name=password]').val().trim();
	}
	
	// 메모 체크
	if ($('textarea[name=memo]').val().trim() == '') {
		alert('메모를 입력해 주세요');
		$('textarea[name=memo]').focus();
		
		return false;
	} else {
		memo = $('textarea[name=memo]').val().trim();
	}
	
	$.ajax({
		data : { 
			'id' : id,
			'no' : no,
			'name' : name,
			'memo' : memo,
			'password' : password,
			'order' : 'insertMemo'
		},
		dataType : 'json',
		type : 'POST',
		url : 'indbAjax.jsp',
		success : function(data) {
			window.location.reload();
		}, error : function(data){
			console.log(data);
		}
	});
}


var tmpFile = '';
var tmpPattern = '';
function doAction(id, no, mode) {
	if ($('input[name=confirmPassword]').val().trim() == '') {
		alert('비밀번호를 입력해 주세요');
		$('input[name=confirmPassword]').focus();
		
		return false;
	}

	var password =  $('input[name=confirmPassword]').val().trim();
	var pattern = $('#pattern').val();
	
	if (pattern == 'deleteComment') {
		var sno = $('#sno').val();
		
		if (confirm('삭제하시겠습니까?')) {
			// 비밀번호 체크
			$.ajax({
				data : {
					'sno' : sno,
					'password' : password,
					'order' : 'checkPasswordMemo'
				},
				dataType : 'json',
				type : 'POST',
				url : 'indbAjax.jsp',
				success : function (data) {
					if (data.result == 1) {
						$.magnificPopup.close();
						
						// 메모 삭제
						$.ajax({
							data : {
								'id' : id,
								'no' : no,
								'sno' : sno,
								'password' : password,
								'order' : 'deleteMemo'
							},
							dataType : 'json',
							type : 'POST',
							url : 'indbAjax.jsp',
							success : function (data) {
								window.location.reload();
							}
						});
					} else {
						alert('비밀번호가 틀렸습니다');
						$('input[name=confirmPassword]').val('');
						$('input[name=confirmPassword]').focus();
						
						return false;
					}
				}
			});
		}
	} else {
		tmpFile = $('#nfile').val();
		tmpPattern = pattern;
		
		// 비밀번호 체크
		$.ajax({
			data : {
				'id' : id,
				'no' : no,
				'password' : password,
				'order' : 'checkPasswordBoard'
			},
			dataType : 'json',
			type : 'POST',
			url : 'indbAjax.jsp',
			success : function (data) {
				if (data.result == 1) {
					$.magnificPopup.close();
					
					if (tmpPattern == 'updateBoard') {
						location.href='write.jsp?id=' + id + '&no=' + no + '&mode=modify&from=' + mode + '&old_file=' + tmpFile;
					} else if (tmpPattern == 'deleteBoard') {
						location.href='delete.jsp?id=' + id + '&no=' + no + '&mode=delete&old_file=' + tmpFile;
					}
				} else {
					alert('비밀번호가 틀렸습니다');
					$('input[name=confirmPassword]').val('');
					$('input[name=confirmPassword]').focus();
					
					return false;
				}
			}
		});
	}
}

function fileDownload(fileName) {
	if (fileName != '') {
		$('input[name=fileName]').val(fileName);
		$('#downloadForm').submit();
	}
}

$(function(){
	$('#new_comm').on('click', function(){
			if($('#new_comment').attr("readonly") == "readonly"){
			$("#nonmember_buy_1").attr('style','display:none');
			$("#nonmember_buy_2").attr('style','display:block');
			$(".btn-pop-login").trigger("click");
		};		
	});
}); 