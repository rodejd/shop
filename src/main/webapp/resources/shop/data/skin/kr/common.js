function _ID(obj){return document.getElementById(obj)}

function popup_zipcode(form){
	window.open("../proc/popup_zipcode.jsp?form="+form,"","width=600,height=500");
}

/**
 * chkForm(form)
 *
 * 입력박스의 null 유무 체크와 패턴 체크
 *
 * @Usage	<form onSubmit="return chkForm(this)">
 */

function chkForm(form)
{
	//2017-09-20 수정 - validation 순서 수정
	
	// 전화번호 Validation
	if($("input:text[name='phone']").length != 0){
		if(
				!(($("input:text[name='phone']")[0].value != '' && 		// 모든값이 다 입력되었을 경우
				 $("input:text[name='phone']")[1].value != '' && 
				 $("input:text[name='phone']")[2].value != '')
				 ||
				($("input:text[name='phone']")[0].value == '' && 		// 값이 하나도 입력되지 않았을 경우
				 $("input:text[name='phone']")[1].value == '' && 
				 $("input:text[name='phone']")[2].value == ''))
		){
			if($("input:text[name='phone']")[0].value == ''){
				alert('전화번호 앞자리를 입력하세요.');
				$("input:text[name='phone']")[0].focus();
				return false;
			}
			if($("input:text[name='phone']")[1].value == ''){
				alert('전화번호 중간자리를 입력하세요.');
				$("input:text[name='phone']")[1].focus();
				return false;
			}
			if($("input:text[name='phone']")[2].value == ''){
				alert('전화번호 끝자리를 입력하세요.');
				$("input:text[name='phone']")[2].focus();
				return false;
			}
		}
	}
	
	// 핸드폰번호 Validation
	if($("input:text[name='mobile']").length != 0){
		if(
				!(($("#mobile0").val() != '' && 		// 모든값이 다 입력되었을 경우
				 $("#mobile1").val() != '' && 
				 $("#mobile2").val() != '')
				 ||
				($("#mobile0").val() == '' && 		// 값이 하나도 입력되지 않았을 경우
				 $("#mobile1").val() == '' && 
				 $("#mobile2").val() == ''))
		){
			if($("#mobile0").val() == ''){
				alert('핸드폰번호 앞자리를 입력하세요.');
				$("#mobile0").focus();
				return false;
			}
			if($("#mobile1").val() == ''){
				alert('핸드폰번호 중간자리를 입력하세요.');
				$("#mobile1").focus();
				return false;
			}
			if($("#mobile2").val() == ''){
				alert('핸드폰번호 끝자리를 입력하세요.');
				$("#mobile2").focus();
				return false;
			}
		}
	}
	
	//email 체크
	var usedEmail = $('#emailUse').val(); //1:사용, 2:필수항목
	var email = $("input[name=email1]").val() + "@" + $("input[name=email2]").val();
	var e_regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
	
	if ("1" == usedEmail) {
		var emailID = $("input[name=email1]").val();
		emailID = emailID.replace("　", "");
		emailID = emailID.replace(/\s*/, "");
		if ("" != emailID) {
			if(!($("input[name=email1]").val() == undefined)){
				if(e_regex.test(email)===false){
					 alert("잘못된 이메일 형식입니다.");
					 return false;
				}
			}		
		}
	} else if ("2" == usedEmail) {
		if(!($("input[name=email1]").val() == undefined)){
			if(e_regex.test(email)===false){
				 alert("잘못된 이메일 형식입니다.");
				 return false;
			}
		}
	}
	
	if (typeof(mini_obj)!="undefined" || document.getElementById('_mini_oHTML')) mini_editor_submit();

	var reschk = 0;
	for (i=0;i<form.elements.length;i++){
		currEl = form.elements[i];
		if (currEl.disabled) continue;
		if (currEl.getAttribute("required")!=null){
			if (currEl.type=="checkbox" || currEl.type=="radio"){
				if (!chkSelect(form,currEl,currEl.getAttribute("msgR"))) return false;
			} else {
				if (!chkText(currEl,currEl.value,currEl.getAttribute("msgR"))) return false;
			}
		}

		if (currEl.getAttribute("label")=='주민등록번호'  && currEl.getAttribute("name") == 'resno' && currEl.value.length>0){
			reschk = 1;

		}
		if (currEl.getAttribute("option")!=null && currEl.value.length>0){
			if (!chkPatten(currEl,currEl.getAttribute("option"),currEl.getAttribute("msgO"))) return false;
		}
		if (currEl.getAttribute("minlength")!=null){
			if (!chkLength(currEl,currEl.getAttribute("minlength"))) return false;
		}
	}
	if (form.password2){
		if (form.password.value!=form.password2.value){
			alert("비밀번호가 일치하지 않습니다");
			form.password.value = "";
			form.password2.value = "";
			return false;
		}
	}
	
	// 관심분야 Validation
	//  관심분야 필수사항인지 체크하여 검사
	if($('.js_interestReq').data('interestReq') == 'checked') {
		if($("input:checkbox[name='interest']").length != 0){
			if(!$("input:checkbox[name='interest']").is(":checked")) {
				alert('관심분야를 한개 이상 체크하세요.');
				return false;
			}
		}
	}
	
	if (reschk && !chkResno(form)) return false;

	if (form.chkSpamKey) form.chkSpamKey.value = 1;
	if (document.getElementById('avoidDbl')) document.getElementById('avoidDbl').innerHTML = "--- 데이타 입력중입니다 ---";
	return true;
}


function chkFormList(form)
{
	if (_subject){
		if (!(_name.checked || _subject.checked || _contents.checked)) return false;
	}
	return chkForm(form);
}

function frmSubmit(target)
{
	var form = document.frmList;
	form.action = target;
	form.submit();
}

function chkLength(field,len)
{
	text = field.value;
	if (text.trim().length<len){
		alert(len + "자 이상 입력하셔야 합니다");
		field.focus();
		return false;
	}
	return true;
}

function chkText(field,text,msg)
{
	text = text.trim();
	if (text==""){
		var caption = field.parentNode.parentNode.firstChild.innerText;
		if (!field.getAttribute("label")) field.setAttribute("label",(caption)?caption:field.name);
		if (!msg) msg = "[" + field.getAttribute("label") + "] 필수입력사항";
		//if (msg) msg2 += "\n\n" + msg;
		alert(msg);
		if (field.tagName!="SELECT") field.value = "";
		if (field.type!="hidden" && field.style.display!="none") field.focus();
		return false;
	}
	return true;
}

function chkSelect(form,field,msg)
{
	var ret = false;
	fieldname = eval("form.elements['"+field.name+"']");
	if (fieldname.length){
		for (j=0;j<fieldname.length;j++) if (fieldname[j].checked) ret = true;
	} else {
		if (fieldname.checked) ret = true;
	}
	if (!ret){
		//if (!field.getAttribute("label")) field.getAttribute("label") = field.name;
		var msg2 = "[" + field.getAttribute("label") + "] 필수선택사항";
		if (msg) msg2 += "\n\n" + msg;
		alert(msg2);
		field.focus();
		return false;
	}
	return true;
}

function chkPatten(field,patten,msg)
{
	var regNum			= /^[0-9]+$/;
	var regEmail		= /^[^"'@]+@[._a-zA-Z0-9-]+\.[a-zA-Z]+$/;
	var regUrl			= /^(http\:\/\/)*[.a-zA-Z0-9-]+\.[a-zA-Z]+$/;
	var regAlpha		= /^[a-zA-Z]+$/;
	var regHangul		= /[가-하]/;
	var regHangulEng	= /[가-하a-zA-Z]/;
	var regHangulOnly	= /^[가-하]*$/;
	var regId			= /^[a-zA-Z0-9]{1}[^"']{3,9}$/;
	//var regPass			= /^[a-zA-Z0-9_-]{4,12}$/;
	var regex			=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
	var regPass 		= /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;
	var regPNum			= /^[0-9]*(,[0-9]+)*$/;

	patten = eval(patten);
	if (!patten.test(field.value)){
		var caption = field.parentNode.parentNode.firstChild.innerText;
		if (!field.getAttribute("label")) field.setAttribute("label",(caption)?caption:field.name);
		var msg2 = "[" + field.getAttribute("label") + "] 입력형식오류";
		if (msg) msg2 += "\n\n" + msg;
		alert(msg2);
		field.focus();
		return false;
	}
	return true;
}

function formOnly(form){
	var i,idx = 0;
	var rForm = document.getElementsByTagName("form");
	for (i=0;i<rForm.length;i++) if (rForm[i].name==form.name) idx++;
	return (idx==1) ? form : form[0];
}

function chkResno(form)
{
	var resno = form['resno'][0].value + form['resno'][1].value;

	fmt = /^\d{6}[1234]\d{6}$/;
	if (!fmt.test(resno)) {
		alert('잘못된 주민등록번호입니다.'); return false;
	}

	birthYear = (resno.charAt(6) <= '2') ? '19' : '20';
	birthYear += resno.substr(0, 2);
	birthMonth = resno.substr(2, 2) - 1;
	birthDate = resno.substr(4, 2);
	birth = new Date(birthYear, birthMonth, birthDate);

	if ( birth.getYear()%100 != resno.substr(0, 2) || birth.getMonth() != birthMonth || birth.getDate() != birthDate) {
		alert('잘못된 주민등록번호입니다.');
		return false;
	}

	buf = new Array(13);
	for (i = 0; i < 13; i++) buf[i] = parseInt(resno.charAt(i));

	multipliers = [2,3,4,5,6,7,8,9,2,3,4,5];
	for (i = 0, sum = 0; i < 12; i++) sum += (buf[i] *= multipliers[i]);

	if ((11 - (sum % 11)) % 10 != buf[12]) {
		alert('잘못된 주민등록번호입니다.');
		return false;
	}
	return true;
}

/**
 * chkBox(El,mode)
 *
 * 동일한 이름의 체크박스의 체크 상황 컨트롤
 *
 * -mode	true	전체선택
 *			false	선택해제
 *			'rev'	선택반전
 * @Usage	<input type=checkbox name=chk[]>
 *			<a href="javascript:void(0)" onClick="chkBox(document.getElementsByName('chk[]'),true|false|'rev')">chk</a>
 */

function chkBox(El,mode)
{
	if (!El) return;
	if (typeof(El)!="object") El = document.getElementsByName(El);
	for (i=0;i<El.length;i++) El[i].checked = (mode=='rev') ? !El[i].checked : mode;
}

/**
 * isChked(El,msg)
 *
 * 체크박스의 체크 유무 판별
 *
 * -msg		null	바로 진행
 *			msg		confirm창을 띄어 실행 유무 체크 (msg - confirm창의 질의 내용)
 * @Usage	<input type=checkbox name=chk[]>
 *			<a href="javascript:void(0)" onClick="return isChked(document.formName.elements['chk[]'],null|msg)">del</a>
 */

function isChked(El,msg)
{
		
	if (!El) return;
	if (typeof(El)!="object") El = document.getElementsByName(El);
	if (El) for (i=0;i<El.length;i++) if (El[i].checked) var isChked = true;
	if (isChked){
		return (msg) ? confirm(msg) : true;
	} else {
		alert ("선택된 사항이 없습니다");
		return false;
	}
}

/**
 * comma(x), uncomma(x)
 *
 * 숫자 표시 (3자리마다 콤마찍기)
 *
 * @Usage	var money = 1000;
 *			money = comma(money);
 *			alert(money);
 *			alert(uncomma(money));
 */

function comma(x)
{
	var x = String(uncomma(x));
	if (x==0) return 0;
 
    var reg = /(^[+-]?\d+)(\d{3})/;
    var n = (x + '');
 
    while (reg.test(n)) n = n.replace(reg, '$1' + ',' + '$2');
 
    return n;
}

function uncomma(x)
{
	var reg = /(,)*/g;
	x = String(x).replace(reg,"");
	return (isNaN(x)) ? 0 : x;
}

/**
 * tab(El)
 *
 * textarea 입력 박스에서 tab키로 공백 띄우기 기능 추가
 *
 * @Usage	<textarea onkeydown="return tab(this)"></textarea>
 */

function tab(El)
{
	if ((document.all)&&(event.keyCode==9)){
		El.selection = document.selection.createRange();
		document.all[El.name].selection.text = String.fromCharCode(9)
		document.all[El.name].focus();
		return false;
	}
}

function enter()
{
    if (event.keyCode == 13){
        if (event.shiftKey == false){
            var sel = document.selection.createRange();
            sel.pasteHTML('<br>');
            event.cancelBubble = true;
            event.returnValue = false;
            sel.select();
            return false;
        } else {
            return event.keyCode = 13;
		}
    }
}

/**
 * miniResize(obj)
 *
 * 이미지 테이블 크기에 맞추어서 리사이즈
 */

function miniResize(obj)
{
	fix_w = obj.clientWidth;
	var imgs = obj.getElementsByTagName("img");
	for (i=0;i<imgs.length;i++){
		//document.write("["+i+"] "+imgs[i].width+" - "+imgs[i].height+"<br>");
		if (imgs[i].width > fix_w){
			imgs[i].width = fix_w;
			imgs[i].style.cursor = "pointer";
			imgs[i].title = "view original size";
			imgs[i].onclick = popupImg;
		}
	}
}

function miniSelfResize(contents,obj)
{
	fix_w = contents.clientWidth;
	if (obj.width > fix_w){
		obj.width = fix_w;
		obj.title = "popup original size Image";
	} else obj.title = "popup original Image";
	obj.style.cursor = "pointer";
	obj.onclick = popupImg;
}

function popupImg(src)
{
	if (typeof(src) != 'string') src = this.src;
	window.open('../board/viewImg.php?src='+escape(src),'','width=1,height=1');
}

/**
 * 문자열 Byte 체크 (한글 2byte)
 */
function chkByte(str)
{
	var length = 0;
	for(var i = 0; i < str.length; i++)
	{
		if(escape(str.charAt(i)).length >= 4)
			length += 2;
		else
			if(escape(str.charAt(i)) != "%0D")
				length++;
	}
	return length;
}

/**
 * 문자열 자르기 (한글 2byte)
 */
function strCut(str, max_length)
{
	var str, msg;
	var length = 0;
	var tmp;
	var count = 0;
	length = str.length;

	for (var i = 0; i < length; i++){
		tmp = str.charAt(i);
		if(escape(tmp).length > 4) count += 2;
		else if(escape(tmp) != "%0D") count++;
		if(count > max_length) break;
	}
	return str.substring(0, i);
}

/**
 * etc..
 */

function get_objectTop(obj){
	if (obj.offsetParent == document.body) return obj.offsetTop;
	else return obj.offsetTop + get_objectTop(obj.offsetParent);
}

function get_objectLeft(obj){
	if (obj.offsetParent == document.body) return obj.offsetLeft;
	else return obj.offsetLeft + get_objectLeft(obj.offsetParent);
}

function mv_focus(field,num,target)
{
	len = field.value.length;
	if (len==num && event.keyCode!=8) target.focus();
}

//숫자만 들어오는 경우를 체크
function onlynumber(e) {
	var input = e.target;
	var key = e.key;
	var keyCode = e.keyCode;
	
	if(!inputKeyNumberCheck(key, keyCode)) {
		e.preventDefault();
	}
}

//눌린 키가 숫자키와 화살표 좌우, 탭, 백스페이스, Delete 인 경우만 true 를 리턴.
function inputKeyNumberCheck(key, keyCode) {
	switch (true) {
	case /^[0-9]$/.test(key): 
	case keyCode === 39: // ArrowRight
	case keyCode === 37: // ArrowLeft
	case keyCode === 8:  // BackSpace
	case keyCode === 9:  // Tab
	case keyCode === 46: // Delete
		return true;
	default: 
		return false;
	}
}

// preventDefault 를 하여도 한글이 입력되는 문제때문에 추가
function removeChar(e) {
	var key = e.keyCode;
	var input = e.target;

	switch (true) {
	case key === 'ArrowRight':
	case key === 'ArrowLeft':
	case key === 'Backspace':
		return;
	default: 
		input.value = input.value.replace(/[^0-9.]/g, '');
	}
}

function explode(divstr,str)
{
	var temp = str;
	var i;
	temp = temp + divstr;
	i = -1;
	while(1){
		i++;
		this.length = i + 1;
		this[i] = temp.substring(0, temp.indexOf( divstr ) );
		temp = temp.substring(temp.indexOf( divstr ) + 1, temp.length);
		if (temp=="") break;
	}
}

function getCookie( name )
{
	var nameOfCookie = name + "=";
	var x = 0;
	while ( x <= document.cookie.length )
	{
	var y = (x+nameOfCookie.length);
	if ( document.cookie.substring( x, y ) == nameOfCookie ) {
	if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
	endOfCookie = document.cookie.length;
	return unescape( document.cookie.substring( y, endOfCookie ) );
	}
	x = document.cookie.indexOf( " ", x ) + 1;
	if ( x == 0 )
	break;
	}
	return "";
}

/// 스트링 객체에 메소드 추가 ///
String.prototype.trim = function(str) {
	str = this != window ? this : str;
	return str.replace(/^\s+/g,'').replace(/\s+$/g,'');
}

/**
 * chg_cart_ea(obj,str)
 *
 * 카트 수량 변경하기
 *
 * -obj		카드 수량 입력박스 아이디
 * -str		up|dn
 * -idx		인덱스 번호 (생략 가능)
 */

function chg_cart_ea(obj,str,idx)
{
	if (obj.length) obj = obj[idx];
	if (isNaN(obj.value)){
		alert ("구매수량은 숫자만 가능합니다");
		obj.value=1;
		obj.focus();
	} else {
		if (str=='up') obj.value++;
		else  obj.value--;
		if (obj.value==0) obj.value=1;
	}
}

function buttonX(str,action,width)
{
	if (!width) width	= 100;
	if (action) action	= " onClick=\"" + action + "\"";
	ret = "<button style='width:" + width + ";background-color:transparent;color:transparent;border:0;cursor:default' onfocus=this.blur()" + action + ">";
	ret += "<table width=" + (width-1) + " cellpadding=0 cellspacing=0>";
	ret += "<tr height=22><td><img src='../img/btn_l.gif'></td>";
	ret += "<td width=100% background='../img/btn_bg.gif' align=center style='font:8pt tahoma' nowrap>" + str + "</td>";
	ret += "<td><img src='../img/btn_r.gif'></td></tr></table></button>";
	document.write(ret);
}

/**
 * selectDisabled(oSelect)
 *
 * 셀렉트박스에 disabled 옵션추가
 */
function selectDisabled(oSelect){
	var isOptionDisabled = oSelect.options[oSelect.selectedIndex].disabled;
    if (isOptionDisabled){
        oSelect.selectedIndex = oSelect.preSelIndex;
        return false;
    } else oSelect.preSelIndex = oSelect.selectedIndex;
    return true;
}


/** 추가 스크립 **/

function viewSub(obj)
{
	//return;
	var obj = obj.parentNode.getElementsByTagName('td')[1].getElementsByTagName('div')[1];
	obj.style.display = "block";
}

function hiddenSub(obj)
{
	var obj = obj.parentNode.getElementsByTagName('td')[1].getElementsByTagName('div')[1];
	obj.style.display = "none";
}

function execSubLayer()
{
	var obj = document.getElementById('menuLayer');
	for (i=0;i<obj.rows.length;i++){
		if (typeof(obj.rows[i].cells[1].childNodes[0])!="undefined"){
			obj.rows[i].cells[0].onmouseover = function(){ viewSub(this) }
			obj.rows[i].cells[0].onmouseout = function(){ hiddenSub(this) }
			obj.rows[i].cells[1].style.position = "relative";
			obj.rows[i].cells[1].style.verticalAlign = "top";
			if (typeof(obj.rows[i].cells[1].getElementsByTagName('div')[1])!="undefined"){
				obj.rows[i].cells[1].getElementsByTagName('div')[1].onmouseover = function(){ viewSub(this.parentNode.parentNode.parentNode.getElementsByTagName('td')[0]) };
				obj.rows[i].cells[1].getElementsByTagName('div')[1].onmouseout = function(){ hiddenSub(this.parentNode.parentNode.parentNode.getElementsByTagName('td')[0]) };
			}
		}
	}
}

function execLayer( parent_layer, sun_layer )
{
	var parent_obj = document.getElementById( parent_layer );
	var sun_obj = document.getElementById( sun_layer );
	parent_obj.onmouseover = function(){ sun_obj.style.display = "block"; }
	parent_obj.onmouseout = function(){ sun_obj.style.display = "none"; }
	sun_obj.onmouseover = function(){ sun_obj.style.display = "block"; }
	sun_obj.onmouseout = function(){ sun_obj.style.display = "none"; }
}

function popup(src,width,height)
{
	window.open(src,'','width='+width+',height='+height+',scrollbars=1');
}

/*** 할인액 계산 ***/
function getDcprice(price,dc,po)
{
	if(!po)po=100;
	if (!dc) return 0;
	var ret = (dc.match(/%$/g)) ? price * parseInt(dc.substr(0,dc.length-1)) / 100 : parseInt(dc);
	return parseInt(ret / po) * po;
}

/*** 플래시 출력 ***/
function embed(src,width,height,vars)
{
	var codebase = "http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0";
	var pluginspage = "http://www.macromedia.com/go/getflashplayer";

	if(document.location.protocol=="https:"){
		codebase = codebase.replace(/http:/, "https:");
		pluginspage = pluginspage.replace(/http:/, "https:");
	}

	document.write('\
	<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="'+codebase+'" WIDTH="'+width+'" HEIGHT="'+height+'">\
	<PARAM NAME=movie VALUE="'+src+'">\
	<PARAM NAME=quality VALUE=high>\
	<PARAM NAME=wmode VALUE=transparent>\
	<PARAM NAME=bgcolor VALUE=#FFFFFF>\
	<param name=flashvars value="' + vars + '">\
	<EMBED src="'+src+'" quality=high bgcolor=#FFFFFF WIDTH="'+width+'" HEIGHT="'+height+'" TYPE="application/x-shockwave-flash" PLUGINSPAGE="'+pluginspage+'" flashvars="' + vars + '"></EMBED>\
	</OBJECT>\
	');
}

/*** 스크롤링(가로) ***/
if ( typeof( scrollingX ) == 'undefined' ){

	var scrollingX = function( objnm, elenm ){

		if ( objnm == '' ) return;
		if ( elenm == '' ) return;
		this.obj = objnm;
		this.ele = eval( "window.frames." + elenm );

		this.time = 20; // 이동시간
		this.drX = 1; // 이동간격
		this.scrollX = 0;
		this.is_move = 1;
		this.ele.scrollTo( 1, 0 );
	}

	scrollingX.prototype.stop = function(){ this.is_move = 0; }
	scrollingX.prototype.start = function(){ this.is_move = 1; }

	scrollingX.prototype.initScroll = function()
	{
		this.p_bound = this.ele.document.body.scrollWidth - this.ele.document.body.offsetWidth;
		this.p_moveRight = Math.floor( this.ele.document.body.scrollWidth / 2 );
		this.p_moveLeft = this.p_bound - this.p_moveRight;

		setInterval( this.obj + '.goScrolling()', this.time );
	}

	scrollingX.prototype.goMove = function( idx )
	{
		if ( idx == 0 ) this.drX = -Math.abs( this.drX );
		else this.drX = +Math.abs( this.drX );

		var pos = this.scrollX + idx;
		this.ele.scrollTo( pos, 0 );
	}

	scrollingX.prototype.goScrolling = function()
	{
		if ( !this.is_move ) return;
		this.scrollX = this.ele.document.body.scrollLeft + this.drX; // window.status = this.scrollX;
		if ( this.drX < 0 && this.scrollX < 1 ) this.scrollX = this.p_moveRight;
		if ( this.drX > 0 && this.scrollX > this.p_bound ) this.scrollX = this.p_moveLeft;
		this.ele.scrollTo( this.scrollX, 0 );
	}
}

/*** 스크롤링(세로) ***/
if ( typeof( scrollingY ) == 'undefined' ){

	var scrollingY = function( objnm, elenm ){

		if ( objnm == '' ) return;
		if ( elenm == '' ) return;
		this.obj = objnm;
		this.ele = eval( "window.frames." + elenm );

		this.time = 20; // 이동시간
		this.drY = 1; // 이동간격
		this.scrollY = 0;
		this.is_move = 1;
		this.ele.scrollTo( 0, 1 );
	}

	scrollingY.prototype.stop = function(){ this.is_move = 0; }
	scrollingY.prototype.start = function(){ this.is_move = 1; }

	scrollingY.prototype.initScroll = function()
	{
		this.p_bound = this.ele.document.body.scrollHeight - this.ele.document.body.offsetHeight;
		this.p_moveBot = Math.floor( this.ele.document.body.scrollHeight / 2 );
		this.p_moveTop = this.p_bound - this.p_moveBot;

		setInterval( this.obj + '.goScrolling()', this.time );
	}

	scrollingY.prototype.goMove = function( idx )
	{
		if ( idx == 0 ) this.drY = -Math.abs( this.drY );
		else this.drY = +Math.abs( this.drY );

		var pos = this.scrollY + idx;
		this.ele.scrollTo( 0, pos );
	}

	scrollingY.prototype.goScrolling = function()
	{
		if ( !this.is_move ) return;
		this.scrollY = this.ele.document.body.scrollTop + this.drY; // window.status = this.scrollY;
		if ( this.drY < 0 && this.scrollY < 1 ) this.scrollY = this.p_moveBot;
		if ( this.drY > 0 && this.scrollY > this.p_bound ) this.scrollY = this.p_moveTop;
		this.ele.scrollTo( 0, this.scrollY );
	}
}

/*** Cookie 생성 ***/
function setCookie( name, value, expires, path, domain, secure ){

	var curCookie = name + "=" + escape( value ) +
		( ( expires ) ? "; expires=" + expires.toGMTString() : "" ) +
		( ( path ) ? "; path=" + path : "" ) +
		( ( domain ) ? "; domain=" + domain : "" ) +
		( ( secure ) ? "; secure" : "" );

	document.cookie = curCookie;
}

/*** Cookie 제거 ***/
function clearCookie( name ){

    var today = new Date();
    var expire_date = new Date(today.getTime() - 60*60*24*1000);
    document.cookie = name + "= " + "; expires=" + expire_date.toGMTString();
}

/*** Cookie 체크 ***/
function getCookie( name ){

	var dc = document.cookie;

	var prefix = name + "="

	var begin = dc.indexOf("; " + prefix);

	if ( begin == -1 ){

		begin = dc.indexOf(prefix);
		if (begin != 0) return null;
	}
	else begin += 2

	var end = document.cookie.indexOf(";", begin);

	if (end == -1) end = dc.length;

	return unescape(dc.substring(begin + prefix.length, end));
}

/*** Cookie 컨트롤 ***/
function controlCookie( name, elemnt ){

	if ( elemnt.checked ){

	    var today = new Date()
	    var expire_date = new Date(today.getTime() + 60*60*6*1000)

		setCookie( name=name, value='true', expires=expire_date, path='/' );
		if (_ID(name) == null) setTimeout( "self.close()" );
		else setTimeout( "_ID('" + name + "').style.display='none'" );
	}
	else clearCookie( name );

	return
}

/*-------------------------------------
자바스크립트 동적 로딩
-------------------------------------*/
function exec_script(src)
{
	var scriptEl = document.createElement("script");
	scriptEl.src = src;
	_ID('dynamic').appendChild(scriptEl);
}

/*-------------------------------------
스크롤 배너
-------------------------------------*/
var bodyHeight = scrollobjHeight = 0;

function scrollBanner()
{
	if ( document.all ) window.attachEvent("onload", initSlide); // IE 경우
	else window.addEventListener("load", initSlide, false); // FF(모질라) 경우
}

function initSlide()
{
	var scroll = document.getElementById('scroll');
	var scrollTop = get_objectTop(document.getElementById('pos_scroll'));
	scroll.style.top = document.body.scrollTop + scrollTop;
	bodyHeight = document.body.scrollHeight;
	scrollobjHeight = scroll.clientHeight;
	movingSlide();
}

function movingSlide()
{
	var yMenuFrom, yMenuTo, yOffset, timeoutNextCheck;
	var scroll = document.getElementById('scroll');
	var scrollTop = get_objectTop(document.getElementById('pos_scroll'));

	yMenuFrom  = parseInt (scroll.style.top, 10);
	yMenuTo    = document.body.scrollTop + 10;
	if(yMenuTo<scrollTop) yMenuTo = scrollTop;
	timeoutNextCheck = 500;
	if (yMenuFrom != yMenuTo) {
		yOffset = Math.ceil(Math.abs(yMenuTo - yMenuFrom) / 10);
		if (yMenuTo < yMenuFrom) yOffset = -yOffset;
		scroll.style.top = parseInt (scroll.style.top, 10) + yOffset;
		timeoutNextCheck = 10;
	}
	if (scroll.style.pixelTop > bodyHeight - scrollobjHeight) scroll.style.top = bodyHeight - scrollobjHeight;

	setTimeout ("movingSlide()", timeoutNextCheck);
}

// 추가 스크롤베너
function rScrollBanner(mid,pid)
{

	if ( document.all ){
		window.attachEvent("onload", rInitSlide(mid,pid)); // IE 경우

	}else window.addEventListener("load", rInitSlide(mid,pid), false); // FF(모질라) 경우



}

function rInitSlide(mid,pid)
{

	var scroll = document.getElementById(mid);
	var scrollTop = get_objectTop(document.getElementById(pid));
	scroll.style.top = document.body.scrollTop + scrollTop;
	bodyHeight = document.body.scrollHeight;
	scrollobjHeight = scroll.clientHeight;
	rMovingSlide(mid,pid);
}

function rMovingSlide(mid,pid)
{
	var yMenuFrom, yMenuTo, yOffset, timeoutNextCheck;
	var scroll = document.getElementById(mid);
	var scrollTop = get_objectTop(document.getElementById(pid));

	yMenuFrom  = parseInt (scroll.style.top, 10);
	yMenuTo    = document.body.scrollTop + 10;
	if(yMenuTo<scrollTop) yMenuTo = scrollTop;
	timeoutNextCheck = 500;
	if (yMenuFrom != yMenuTo) {
		yOffset = Math.ceil(Math.abs(yMenuTo - yMenuFrom) / 10);
		if (yMenuTo < yMenuFrom) yOffset = -yOffset;
		scroll.style.top = parseInt (scroll.style.top, 10) + yOffset;
		timeoutNextCheck = 10;
	}
	if (scroll.style.pixelTop > bodyHeight - scrollobjHeight) scroll.style.top = bodyHeight - scrollobjHeight;

	setTimeout ("rMovingSlide('"+mid+"','"+pid+"')", timeoutNextCheck);
}

function gdscroll(gap)
{
	var gdscroll = document.getElementById('gdscroll');
	gdscroll.scrollTop += gap;
}

function eScroll()
{
	/*********************************
	 * eScroll ( eNamoo scroll script )
	 * by mirrh
	 * 2006.07.16
	 ********************************/

	var thisObj = this;
	this.timeObj = null;

	/*** 설정변수 ***/
	this.mode = "top";				// 스크롤 방향 (top|left)
	this.width = "100%";			// 라인당 가로값 (pixel)
	this.height = 20;				// 라인당 높이값 (pixel)
	this.line = 1;					// 출력 라인수
	this.delay = 150;				// 스크롤후 딜레이 시간
	this.speed = 1;					// 스크롤 속도 (작을수록 빠름)
	this.id = 'obj_eScroll';		// 객체 id (클래스 다중 사용시 id 다르게 지정 요망)
	this.contents = new Array();	// 출력 내용 (배열로 내용 지정 요망)
	this.align = "left";			// 내용 aligne
	this.valign = "middle";			// 내용 valigne

	/*** 내장변수 ***/
	this.gap = 0;
	this.direction = 1;

	this.add = add;
	this.exec = exec;
	this.start = start;
	this.stop = stop;
	this.scroll = scroll;
	this.direct = direct;
	this.go = go;

	function add(str)
	{
		this.contents[this.contents.length] = str;
	}

	function exec()
	{
		this.basis = (this.mode == "left") ? this.width : this.height;
		var outWidth = this.width * ((this.mode == "left") ? this.line : 1);
		var outHeight = this.height * ((this.mode == "top") ? this.line : 1);

		var outline = "<div id=" + this.id + " style='overflow:hidden;width:" + outWidth + ";height:" + outHeight + "'><table></table></div>";
		document.write(outline);
		this.obj = document.getElementById(this.id);

		var tb = this.obj.appendChild(document.createElement("table"));
		var tbody = tb.appendChild(document.createElement("tbody"));
		tb.cellPadding = 0 ;
		tb.cellSpacing = 0 ;
		tb.onmouseover = function(){thisObj.stop()};
		tb.onmouseout = function(){thisObj.start()};

		if (this.mode=="left") var tr = tbody.appendChild(document.createElement("tr"));
		for (var k=0; k < this.contents.length; k++){
			if (this.mode=="top") var tr = tbody.appendChild(document.createElement("tr"));
			var td = tr.appendChild(document.createElement("td"));
			td.noWrap = true;
			td.style.width = this.width;
			td.style.height = this.height;
			td.style.textAlign = this.align;
			td.style.verticalAlign = this.valign;
			td.innerHTML = this.contents[k];
		}

		var len = (this.contents.length<this.line) ? this.contents.length : this.line;
		for (i=0;i<len;i++){
			if (this.mode=="top") var tr = tbody.appendChild(document.createElement("tr"));
			td = tr.appendChild(document.createElement("td"));
			td.noWrap = true;
			td.style.width = this.width;
			td.style.height = this.height;
			td.style.textAlign = this.align;
			td.style.verticalAlign = this.valign;
			td.innerHTML = this.contents[i];
		}

		this.obj.parent = this;
		this.tpoint = this.basis * this.contents.length;
		this.start();
	}

	function scroll()
	{
		var out = (this.mode=="left") ? this.obj.scrollLeft : this.obj.scrollTop;
		if (out%this.basis==0){
			this.gap++;
			if (this.gap>=this.delay) this.gap = 0;
		}
		if (!this.gap){
			var ret = (out==this.tpoint) ? this.direction : out + this.direction;
			if (ret<0) ret = this.tpoint + ret;
			if (this.mode=="left") this.obj.scrollLeft = ret;
			else this.obj.scrollTop = ret;
		}
	}

	function start()
	{
		this.timeObj = window.setInterval("(document.getElementById('" + this.id + "')).parent.scroll()",this.speed);
	}

	function stop()
	{
		clearTimeout(this.timeObj);
	}

	function direct(d)
	{
		this.direction = d;
	}

	function go()
	{
		this.stop();
		var out = (this.mode=="left") ? this.obj.scrollLeft : this.obj.scrollTop;
		var ret = (parseInt(out / this.basis) + this.direction) * this.basis;
		if (ret<0) ret = this.tpoint + ret;
		if (ret>this.tpoint) ret = this.basis;
		if (this.mode=="left") this.obj.scrollLeft = ret;
		else this.obj.scrollTop = ret;
	}

}

/*** onLoad 이벤트에 함수 할당 ***/
function addOnloadEvent(fnc)
{
	if ( typeof window.addEventListener != "undefined" )
		window.addEventListener( "load", fnc, false );
	else if ( typeof window.attachEvent != "undefined" ) {
		window.attachEvent( "onload", fnc );
	}
	else {
		if ( window.onload != null ) {
			var oldOnload = window.onload;
			window.onload = function ( e ) {
				oldOnload( e );
				window[fnc]();
			};
		}
		else window.onload = fnc;
	}
}

function order_print(ordno)
{
	var ordertax = window.open("../mypage/popup_ordertax.php?ordno=" + ordno,"ordertax","width=750,height=600,scrollbars=yes");
	ordertax.focus();
}

function popupEgg(asMallId, asOrderId){
	//창을 화면의 중앙에 위치
	iXPos = (window.screen.width - 700) / 2;
	iYPos = (window.screen.height - 600) / 2;
	var egg = window.open("https://gateway.usafe.co.kr/esafe/InsuranceView.asp?mall_id="+asMallId + "&order_id=" + asOrderId, "egg", "width=700, height=600, scrollbars=yes, left=" + iXPos + ", top=" + iYPos);
	egg.focus();
}

/*** Taxbill 정보 출력 ***/
function getTaxbill(doc_number, taxapp)
{
	_ID('taxprint').style.display = 'none';
	var print = function(){
		if (_ID('taxstep3') && _ID('loadMsg') ) _ID('loadMsg').style.display = 'none';
		var req = ajax.transport;
		if (req.status == 200){
			var jsonData = eval( '(' + req.responseText + ')' );
			_ID('taxstep3').innerHTML += (jsonData.status_txt != null ? ' - ' + jsonData.status_txt : '');
			if (jsonData.tax_path != null){
				_ID('taxprint').getElementsByTagName('a')[0].href = "javascript:popup('" + jsonData.tax_path + "',750,600);";
				_ID('taxprint').style.display = 'block';
			}
			if (taxapp == 'Y' && (jsonData.status == 'CAN' || jsonData.status == 'ERR' || jsonData.status == 'CCR')){
				_ID('taxstep3').innerHTML += ' <FONT COLOR="EA0095">(세금계산서를 재신청할 수 있습니다.)</FONT>';
				_ID('taxapply').style.display = 'block';
			}
		}
		else {
			_ID('taxstep3').title = req.getResponseHeader("Status");
			_ID('taxstep3').innerHTML += '<font class=small color=444444> - 로딩중에러</font>';
		}
	}
	if (typeof(Ajax) == 'undefined') return;
	if (_ID('taxstep3') && !_ID('loadMsg') ){
		msgDiv = _ID('taxstep3').appendChild( document.createElement('span') );
		msgDiv.id = 'loadMsg';
		msgDiv.innerHTML = '&nbsp;- 데이타 로딩 중입니다. 잠시만 기다려주세요.';
	}
	var ajax = new Ajax.Request("../mypage/indb.php?mode=getTaxbill&doc_number=" + doc_number + "&dummy=" + new Date().getTime(), { method: "get", onComplete: print });
}

/*** 이동레이어 관련 : start ***/
var appname = navigator.appName.charAt(0);
var move_Type = false;
var targetElement = '';
var nowElem = '';
var MaxZindex = 10;

//뷰어창 이동!!
function nowElemOnFn(thisElem,thisID){
	nowElem = thisElem;
	targetElement = document.getElementById(thisID);
	targetElement.style.zIndex = MaxZindex;
	MaxZindex = eval(MaxZindex) + eval(1);
}

function Start_move(e){
	var event = e || window.event;
	if( appname == "M" ){
		//익스
		var evTagName = "BODY";
		var evElement = event.srcElement;
	}else{
		//익스 외
		var evTagName = "HTML";
		var evElement = event.target;
	}

  while( evElement.id != nowElem && evElement.tagName != evTagName ){
    evElement = evElement.parentNode;
  }

  if ( evElement.id == nowElem && nowElem != '' ){
	Move_x = event.clientX;
	Move_y = event.clientY;
	now_mx = parseInt(targetElement.style.left);
	now_my = parseInt(targetElement.style.top);
	Move_goType = true;
    document.onmousemove = Moveing;
  }
}

function Moveing(e){
	var event = e || window.event;
	if (!Move_goType) return false;
	targetElement.style.left = ( now_mx + event.clientX ) - Move_x;
	targetElement.style.top = ( now_my + event.clientY ) - Move_y;
	return false;
}

function int_n(cnt){
	if( isNaN(parseInt(cnt)) == true ) var re_cnt = 0;
	else var re_cnt = parseInt(cnt);
	return re_cnt;
}

document.onmousedown = Start_move;
document.onmouseup = Function("Move_goType = false");
/*** 이동레이어 관련 : end ***/


/**********************
 * categoryBox
 *
 * @name	category 폼객체명
 * @idx		category 박스 갯수
 */

function categoryBox(name,idx,val,type,formnm)
{
	if (!idx) idx = 1;
	
	if (type=="multiple") type = "multiple style='width:160px;height:96'";
	for (i=0;i<idx;i++) document.write("<select " + type + " idx=" + i + " name='" + name + "' onchange='categoryBox_request(this)' class='category-selections-select'></select>&nbsp;");
	
	oForm = eval("document.forms['" + formnm + "']");
	if ( oForm == null ){
		this.oCate = eval("document.forms[0]['" + name + "']");
	}
	else{ this.oCate = eval("document." + oForm.name + "['" + name + "']"); }
	
	if (idx==1) this.oCate = new Array(this.oCate);

	this.categoryBox_init = categoryBox_init;
	this.categoryBox_build = categoryBox_build;
	this.categoryBox_init();
	
	function categoryBox_init()
	{
		this.categoryBox_build();
		categoryBox_request(this.oCate[0],val);
	}

	function categoryBox_build()
	{
		for (i=0;i<4;i++){
			if (this.oCate[i]){
				this.oCate[i].options[0] = new Option("= "+(i+1)+"차 분류 =","");
			}
		}
	}
}

function categoryBox_request(obj,val)
{
	if (!val) val = "";
	var idx = obj.getAttribute('idx');
	
	if ( document.location.href.indexOf("/admin") == -1 ){
		exec_script("/shop/lib/_categoryBox.script.jsp?mode=user&idx=" + idx + "&obj=" + obj.name + "&formnm=" + obj.form.name + "&val=" + val + "&category=" + obj.value);
	}
	else {
		//alert("/shop/lib/_categoryBox.script.jsp?mode=admin&idx=" + idx + "&obj=" + obj.name + "&formnm=" + obj.form.name + "&val=" + val + "&category=" + obj.value);
		exec_script("/shop/lib/_categoryBox.script.jsp?mode=admin&idx=" + idx + "&obj=" + obj.name + "&formnm=" + obj.form.name + "&val=" + val + "&category=" + obj.value);
	}
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


function contains(source, value) {
	console.log(source);
	for(var i in source) {
		console.log('실행');
		if(source[i] == value) {
			return false;
		}
	}
	source.push(value);
}