function cmtDel(no){
	document.getElementById("mode").value = "cmtDel";
	document.getElementById("eno").value = no;
	document.getElementById("fm01").submit();
}

function cmtMod(no){
	if(document.getElementById("modCk").value==""){
		document.getElementById("mod1_"+no).style.display = "none";
		document.getElementById("mod2_"+no).style.display = "block";
		document.getElementById("modCk").value="cmtMod";
		}else{
			document.getElementById("mode").value = "cmtMod";
			document.getElementById("eno").value = no;
			document.getElementById("fm01").submit();
		}
}

function chevn(){
	var evnCate = $("#evnCate").val();
	if(evnCate=="ing"){
		location.href="/mshop/event/eventList.jsp";
	}else{
		location.href="/mshop/event/eventEndList.jsp";
	}
}
