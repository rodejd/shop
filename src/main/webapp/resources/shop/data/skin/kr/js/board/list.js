function fn_chk() {
  var check = true;
  var checked = 0;
  $("input[name='sel[]']").each(function(index){
     if($(this).is(':checked')){             
         checked = checked + 1;             
      }         
  });
  if(checked == 0){
      alert("선택된 내용이 없습니다.");   
      check = false;
      return false;
   }
  if(check){
     frmSubmit('view.jsp');
      }
}

function fn_submit() {
	$.post($('#frmList').submit());
}

function commentModify(fnum, nume, cmboolean, comleng) {
	var arrCMviewName = ["CMMODIFY_","CMMODIFYCANCEL_","CMMODIFYTXT_","CMMODIFYMEMO_"];
	
	if (cmboolean=="false") {
		for(i=0;i< comleng;i++) {
			//alert("//"+i);
			if (i==nume) {
				document.getElementById(arrCMviewName[0]+fnum+i).style.display = 'none';
				document.getElementById(arrCMviewName[1]+fnum+i).style.display = 'block';
				document.getElementById(arrCMviewName[2]+fnum+i).style.display = 'none';
				document.getElementById(arrCMviewName[3]+fnum+i).style.display = 'block';
			} else {
				document.getElementById(arrCMviewName[0]+fnum+i).style.display = 'block';
				document.getElementById(arrCMviewName[1]+fnum+i).style.display = 'none';
				document.getElementById(arrCMviewName[2]+fnum+i).style.display = 'block';
				document.getElementById(arrCMviewName[3]+fnum+i).style.display = 'none';
			}
		}
	} else {
		document.getElementById(arrCMviewName[0]+fnum+nume).style.display = 'block';
		document.getElementById(arrCMviewName[1]+fnum+nume).style.display = 'none';
		document.getElementById(arrCMviewName[2]+fnum+nume).style.display = 'block';
		document.getElementById(arrCMviewName[3]+fnum+nume).style.display = 'none';
	}
}