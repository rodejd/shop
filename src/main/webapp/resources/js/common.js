//키를 누룰때 숫자만 입력가능(onkeypress)
function checkRealTimeNumber(){		
	var str = String.fromCharCode(event.keyCode);
/*
	var f = obj.form;	
	var strValue = obj.value;		
	if(Number(strValue)+"" =="NaN"){				
		obj.value = strValue.substring(0, strValue.length-1);
	}			
*/
	if(str==""){
		return true;
	}
	if(str>"9"||str<"0"){
		event.returnValue=false;
	}
}

//키를 누룰때 숫자만 입력가능(onkeypress)
function checkRealTimeDouble(obj){		
	var str = String.fromCharCode(event.keyCode);
/*
	var f = obj.form;	
	var strValue = obj.value;		
	if(Number(strValue)+"" =="NaN"){				
		obj.value = strValue.substring(0, strValue.length-1);
	}			
*/
/*	if(str==""){
		return true;
	}*/
//	alert(Number(obj.value+str));
	if(Number(obj.value+str)+""=="NaN"){
		event.returnValue=false;
	}
}

 ///////////////// 폼체크를 위한 스크립트 /////////////////////////

/* 폼체크스크립트 함수 사용법
 * function checkInput(obj, field, type, flag, size){}
 * 파라미터: 객체/필드명/데이터유형/필수입력여부/제한입력크기	 
 * type vlaue: n 숫자, nf 실수, nd 날짜(yyyymmdd), nd1 날짜(yyyy/mm/dd), nd2 날짜(mm/dd)
 *             nt 시간(hhmm), nt1 시간(hh:mm), e 영문자
 * 실수의 사이즈는 정수부 입력크기만 체크
 */
function checkInput(obj, field, type, flag, size){

	if(flag){
		if(isEmpty(obj)){
			alert("입력: '"+field+"'는 필수 입력 사항 입니다. ");
			obj.focus();
			return false;
		}
	}
	if(type=="n"){		
		if(noNumber(obj)){
			alert("입력: '"+field+"'는 숫자만 입력가능 합니다 . ");
			obj.focus();
			return false;
		}
	}else if(type=="nf"){		
		if(noNumberFormat(obj)){
			alert("입력: '"+field+"'는 정수, 소수값만 입력가능 합니다 . ");
			obj.focus();
			return false;
		}
	}else if(type=="nd"){		
		if(noNumber(obj)||!chkDate(obj)){
			alert("입력: '"+field+"'는 올바른 날짜형식의 숫자만 입력가능 합니다 . ");
			obj.focus();
			return false;
		}
	}else if(type=="nd1"){		
		if(!checkDatend1(obj)){
			alert("입력: '"+field+"'는 올바른 날짜형식(YYYY/MM/DD)만 입력가능 합니다 . ");
			obj.focus();
			return false;
		}
	}else if(type=="nd2"){		
		if(!checkDatend2(obj)){
			alert("입력: '"+field+"'는 올바른 날짜형식(MM/DD)만 입력가능 합니다 . ");
			obj.focus();
			return false;
		}
	}else if(type=="e"){		
		if(noEnglish(obj)){
			alert("입력: '"+field+"'는 영문자만 입력가능 합니다 . ");
			obj.focus();
			return false;
		}
	}else if(type=="nt"){		
		if(noNumber(obj)||!checkTime(obj)){
			alert("입력: '"+field+"'는 올바른 시간형식의 숫자만 입력가능 합니다 . ");
			obj.focus();
			return false;
		}
	}else if(type=="nt1"){		
		if(!checkTiment1(obj)){
			alert("입력: '"+field+"'는 올바른 시간형식(HH:MM)만 입력가능 합니다 . ");
			obj.focus();
			return false;
		}
	}else if(type=="nt2"){		

		if(noNumber(obj)||parseInt(obj.value)>10||obj.value.length>1){					
			if(!checkTiment1(obj)){
				alert("입력: '"+field+"'는 올바른 시간형식(HH:MM)이나 정수 1자리만 입력가능 합니다 . ");
				obj.focus();
				return false;
			}
		}
	}

	if(size!=null){
		if(type=="nf"){		
			if(floatOverLength(obj, size)){
				alert("입력: '"+field+"'는 정수자리에는 "+ size +"자 만 입력가능 합니다 . ");
				obj.focus();
				return false;			
			}
		}else{		
			if(overLength(obj, size)){
				alert("입력: '"+field+"'는 "+ size +"자 만 입력가능 합니다 . ");
				obj.focus();
				return false;
			}
		}
	}
	return true;
}

function setAutoDate(obj){
	var strDate = obj.value;
	if(strDate.length==8&&event.keyCode!=37&&event.keyCode!=39){
		if(chkDate(obj)){
			setDateField(strDate);	
		}else{			
			alert("날짜가 잘못 되었습니다. 확인하세요. ");			
			obj.focus();
			obj.select();
			
		}
		
	}
}
//날짜체크
function chkDate(obj){
	var str =obj.value;
	if(str.length!=8){
		return false;
	}

  vDate = new Date(str.substring(0, 4),str.substring(4, 6)-1, str.substring(6));

  if( vDate.getFullYear() != str.substring(0, 4) ||
   vDate.getMonth()    != (str.substring(4, 6)-1) ||  /* -- -1을 해줌 --*/
   vDate.getDate()     != str.substring(6) ){
   return false
  }
  return true;
}

function chkDate2(value){
	var str =value;
	if(str.length!=8){
		return false;
	}

  vDate = new Date(str.substring(0, 4),str.substring(4, 6)-1, str.substring(6));

  if( vDate.getFullYear() != str.substring(0, 4) ||
   vDate.getMonth()    != (str.substring(4, 6)-1) ||  /* -- -1을 해줌 --*/
   vDate.getDate()     != str.substring(6) ){
   return false
  }
  return true;
}

//날짜체크(yyyy/mm/dd)
function checkDatend1(obj){
	var value = obj.value;
	var year = value.substring(0,4);
	var month = value.substring(5,7);
	var day = value.substring(8);
	if(value.charAt(4)!='/'||value.charAt(7)!='/'){
		return false;
	}
	if(value.length!=10){
		return false;
	}
	if(noNumber2(year)||noNumber2(month)||noNumber2(day)){
		return false;
	}
	if(!chkDate2(year+month+day)){
		return false;
	}
	return true;
}

//날짜체크(mm/dd)
function checkDatend2(obj){
	var value = obj.value;
	var year = (new Date()).getFullYear();
	var month = value.substring(0,2);
	var day = value.substring(3);
	if(value.charAt(2)!='/'){
		return false;
	}
	if(value.length!=5){
		return false;
	}
	if(noNumber2(year)||noNumber2(month)||noNumber2(day)){
		return false;
	}
	if(!chkDate2(year+month+day)){
		return false;
	}

	return true;
}

 //Select 박스의 값 선택 여부 확인
function checkSelect(obj, field){
	if(obj.value==""){
		alert("선택: '"+field+"'를 선택하세요. ");
		obj.focus();
		return false;
	}else{
		return true;
	}	
}

//실수크기 검사
function floatOverLength(obj, size1, size2){	
	var str = obj.value;	
	var idx = str.indexOf(".");			
	if(idx>-1){
		if(str.substring(0,idx).length>size1) return true;	
		if(size2!=null) return (str.substring(idx) > size2);
	}else{
		return (str.length>size1);
	}
	return false;
}

// 한글존재 여부 검사
function isHangulObj(str)	{
	var c;		

	for(i=0;i< str.value.length;i++){			
		c=str.value.charAt(i);
		if(((c<'!'||'~'<c))&&c!=' ')	return true;  //수정 20020312
	}
	return false;
}

//영문자가 아닌지
function noEnglish(str){
	var c;
	for(i=0;i<str.value.length;i++){
		c=str.value.charAt(i);		
		if(c>='A'&&c<='Z'){ //영문대문자
			continue; 
		}
		else if(c>='a'&&c<='z'){ //영문소문자
			continue; 
		}			
		else {
			return true;
		}
	}
	return false;
}

//수치값인지 여부
function noNumberFormat(obj){	
	if(Number(obj.value)+""=="NaN"){	
		return true;
	}
	return false;
}

//입력값에 숫자만 허용
function noNumber(obj){
	var c;
	for(var i=0;i<obj.value.length;i++){
		c=obj.value.charAt(i);
		if(c<'0'||c>'9'){
			obj.focus();
			return true;
		}
	}
	return false;
}

//입력값에 숫자만 허용
function noNumber2(value){
	var c;
	for(var i=0;i<value.length;i++){
		c=value.charAt(i);
		if(c<'0'||c>'9'){			
			return true;
		}
	}
	return false;
}



//시간체크
function checkTime(obj){	
	var value = obj.value;
	var hour = parseInt(value/100);
	var minute = value%100;
	if(obj.value.length!=4){
		return false;
	}	
	if(hour<=24&&minute<60){	
		return true;
	}
	return false;
}

//시간체크
function checkTiment1(obj){		
	var value = obj.value;
	if(value.length!=5){
		return false;
	}
	if(value.charAt(2)!=':'){
		return false;
	}
	value = value.substring(0,2)+value.substring(3);
	var hour = parseInt(value/100);
	var minute = value%100;	
	if(hour<=24&&minute<60){	
		return true;
	}
	return false;
}

// 길이 초과 여부 검사
function overLength(str, limitLen){

	var c;		
	var strlen=0;

	for(i=0;i< str.value.length; i++){			
		c=str.value.charAt(i);
		if(((c<'!'||'~'<c))&&c!=' ')	strlen++; //한글  //수정 20020312
		strlen++;	//수정 20020312
		if(strlen>limitLen){
		//	alert(strlen);
			return true;
		}		
	}		
//	alert(strlen);
	return false;
}

//첫문자가 빈값이 있는지 여부
function isEmpty(obj){	
	
	if(obj.value.length==0){
		return true;	
	}	
	else return false;	

}

//스페이스가 있는지 여부
function isSpace(str){
	if(str.value.indexOf(' ')>-1){
		return true;
	}
	return false;
}

//특수문자가 있는지 여부
function isSymbol(str){
	var c;
	for(i=0;i<str.value.length;i++){
		c=str.value.charAt(i);		
		if(c>=0&&c<=9){ //숫자
			continue; 
		}
		else if(c>='A'&&c<='Z'){ //영문대문자
			continue; 
		}
		else if(c>='a'&&c<='z'){ //영문소문자
			continue; 
		}
		else if(c==' '){
			alert("공백");
			continue;
		}
		else if(c<'!'||'~'<c){ //한글
			continue;
		}		
		else {
			return true;
		}
	}
	return false;
}


//해당길이가 되면 포커스 이동
function setFocusLength(obj, target, len){
	if(obj.value.length==len){
		target.focus();
	}
}

/**
 * 문자열 byte 길이 구하기
 * usage:
 *   var str = "한글english";
 *   if (str.byteLength() > 8) { ... }
 */
String.prototype.byteLength = function() {
	var i, j = 0;
	for(i = 0; i < this.length; i++) {
		val = escape(this.charAt(i)).length;
		if (val == 6) j++;
		j++;
	}
	return j;
}

/**
 * 전후 공백 제거
 * usage:
 *   var str = "    asdf ";
 *   document.write(str.trim());
 */
String.prototype.trim = function() { return this.replace(/(^\s*)|(\s*$)/g, ""); }

function goURL(url){
	location = url;
}


function go_ajax(l_type, l_url, success_callback){
	$.ajax({
		type: l_type,
		async : true ,
		url : l_url , 
		dataType : "xml" ,
		timeout : 30000 ,
		cache : false ,
		data : $("#frmMember").serialize() ,
		contentType : "application/x-www-form-urlendcoded; charset=EUC-KR" ,
		error : function(request, status, error){
			alert("error : " + request.status + "\n message" + request.responseText);
		},
		success : success_callback,
		complete : function(){
			//alert("complete : ");
		}
	})	
}

replaceAll = function(str, div){
	div = (null == div) ? "," : div; 
    str = (typeof(str) == 'object') ? str.value : str;
    str = (typeof(str) == 'number') ? str.NumToStr() : str;

    if(typeof(str) == 'string'){
        if(str.trim() == '') return str;
        var resultStr = "";

        for (var i=0 ; i < str.length; i++) {
            if (str.charAt(i) != div){
                resultStr += str.charAt(i);
            }
        }
        return resultStr;
    }
}