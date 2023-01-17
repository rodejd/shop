if (document.frmList)
{
	var frmList = document.frmList;
	var _subject	= frmList.elements['subject'];
	var _name		= frmList.elements['all'];
	var _contents	= frmList.elements['contents'];
	var _word		= frmList.elements['search[word]'];
	reCheck();
}

function frmSubmit(target)
{
	var form = document.frmList;
	form.action = target;
	form.submit();
}

function srch(El,mode,isClick)
{
	var obj = frmList2.elements['search[' + mode + ']'];
	if (!isClick) obj.checked = (obj.checked) ? false : true;
	El.src = eval("El.is_"+obj.checked);
}

function chkFormList(form)
{
	if (_subject){
		if (!(_name.checked || _subject.checked || _contents.checked)) return false;
	}
	return chkForm(form);
}

function reCheck()
{
	if (_subject && _word.value=='') _subject.checked = true;
	if (document.getElementById('srch_subject')){
		if (document.getElementById('srch_name')) srch(document.getElementById('srch_name'),'name',1);
		srch(document.getElementById('srch_subject'),'subject',1);
		srch(document.getElementById('srch_contents'),'contents',1);
	}
}

function del(index)
{
	var table = document.getElementById('table');
    for (i=0;i<table.rows.length;i++) if (index==table.rows[i].id) table.deleteRow(i);
	calcul();
}

function calcul()
{
	var table = document.getElementById('table');
	for (i=0;i<table.rows.length;i++){
		table.rows[i].cells[0].innerHTML = i+1;
	}
	countSet(table.rows.length);
}

//2010.12.30 홍웅선 추가 calcul()메소드를 이용해 얻은 값으로  count값을 히든으로 보냄
function countSet(fileCount)
{
	FILECOUNT.innerHTML = "<input type='hidden' name='count' value='"+fileCount+"'>";
	//alert("countSet:"+fileCount+"//");
}


function preview(El,no)
{
	var tmp = eval("document.getElementById('prvImg" + no +"')");
	tmp.innerHTML = "<a href='javascript:input(" + no + ")'><img src='" + El + "' width=50 onLoad=\"if(this.height>this.width){this.height=50}\" onError=\"this.style.display='none'\"></a>";
}

function input(index)
{
	var table = document.getElementById('table');
	for (i=0;i<table.rows.length;i++) if (index==table.rows[i].id){x = table.rows[i].cells[0].innerHTML;break}
	//document.frmWrite.contents.value += "\n[:이미지" + x + ":]";
	mini_set_html(0,"\n[:이미지" + x + ":]");
}

function emoticon(x,name)
{
	document.frmWrite.contents.value += "[:" + name + ":]";
	document.frmWrite.contents.focus();
}

function viewEmoticon(El)
{
	TrEmotion.style.display = (El.checked) ? "inline" : "none";
}

function chg_mode(mode)
{
	eMode = mode;

	if (eMode){
		oHTML.innerHTML = oTEXT.value;
		oHTML.style.display = "inline";
		oTEXT.style.display = "none";
		btn_chg_mode.innerHTML = "<a href='javascript:void(0)' onClick='chg_mode(0)'>소스편집</a>";
	} else {
		oTEXT.value = oHTML.innerHTML;
		oTEXT.style.display = "inline";
		oHTML.style.display = "none";
		btn_chg_mode.innerHTML = "<a href='javascript:void(0)' onClick='chg_mode(1)'>위지윅모드</a>";
	}
	for (i=0;i<toolbar.children.length;i++) toolbar.children[i].disabled = !mode;
}

function format(what,opt)
{
	if (eMode){
		if (opt==null) document.execCommand(what);
		else document.execCommand(what, false, opt);
	}
}

if (document.getElementById('table')) document.onLoad = calcul();

if (document.getElementById('oHTML_')){
	var eMode = 1;
	oHTML = document.getElementById('oHTML_');
	oTEXT = document.getElementById('oTEXT_');
	oHTML.innerHTML = oTEXT.value;
}