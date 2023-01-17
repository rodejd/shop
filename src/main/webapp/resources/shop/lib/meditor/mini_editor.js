var mini_path = "";
var r_miniEditorTextarea = new Array();
var mini_color_mode = new Array();

function mini_editor(path)
{
	mini_path = path;

	var textareas = document.getElementsByTagName("textarea");
	for (var i=0;i<textareas.length;i++){
		if (textareas[i].getAttribute("type")=="editor"){
			r_miniEditorTextarea.push(textareas[i]);
		}
	}

	for (var i=0;i<r_miniEditorTextarea.length;i++){
		miniEditorFrame(r_miniEditorTextarea[i],i);
	}
}

function mini_command(idx,str,value)
{
	var miniEditorName = r_miniEditorTextarea[idx].getAttribute("name");
	var miniEditorId = "miniEditorIframe_" + miniEditorName; // miniEditorIframe_body
	$('#'+miniEditorId).focus();
	var mode = false;
	if (!value) value = null;
	switch (str){
		case "ForeColor": case "BackColor":
			var ready = true;
			if (document.getElementById('mini_color_box'+idx).style.display=="block" && str!=mini_color_mode[idx]) mini_vlayer(idx,'mini_color_box');
			break;
		case "CreateLink":
			mode = true;
			break;
		case "InsertTable":
			mini_vlayer(idx,'mini_table_box');
			return; break;
		case "InsertImage":
			window.open(ctx+"/shop/admin/common/" + "imgRegister?idx=" + idx,"","width=400px,height=510px");
			return; break;
	}
	mini_command_exec(idx,str,mode,value,0,ready);
}

function mini_command_exec(idx,str,mode,value,ff,ready)
{
	if (document.all && ff) return;

	var miniEditorName = r_miniEditorTextarea[idx].getAttribute("name");
	var miniEditorId = "miniEditorIframe_" + miniEditorName;
	var miniEditorIframe = document.getElementById(miniEditorId);
	var miniEditorContent = miniEditorIframe.contentWindow.document;

	if (typeof(ready)=="undefined"){
		if (str == 'CreateLink' && document.selection == null){ // Mozilla
			mode = false;
			value = prompt('URL : ','http://');
			if ( value == 'http://' ) value = '';
			if ( value == '' ) miniEditorContent.execCommand('UnLink', mode, '');
		}
		if (str == 'CreateLink' && mode == false && value == '' );
		else miniEditorContent.execCommand(str, mode, value);
	}
	mini_reset(idx,str);
	miniEditorIframe.contentWindow.focus();

	if (str=="ForeColor" || str=="BackColor"){
		mini_color_mode[idx] = str;
		mini_vlayer(idx,'mini_color_box');
	}
}

function mini_set_font(idx)
{
	var name = new Array("굴림","돋움","바탕","궁서","굴림체","Arial","Courier","Tahoma");
	var ret = "<select id=mini_btn" + idx + "FontName style='font:8pt tahoma; height:20px;' onchange=\"mini_command(" + idx + ",'FontName',this[this.selectedIndex].value)\"><option>Font";
	for (var i=0;i<name.length;i++){
		ret += "<option value='" + name[i] + "'>" + name[i];
	}
	ret += "</select>";
	return ret;
}

function mini_set_size(idx)
{
	var ret = "<select id=mini_btn" + idx + "FontSize style='font:8pt tahoma; height:20px;' onchange=\"mini_command(" + idx + ",'FontSize',this[this.selectedIndex].value)\"><option>Size";
	for (var i=1;i<=7;i++){
		ret += "<option value='" + i + "'>" + i;
	}
	ret += "</select>";
	return ret;
}

function mini_set_btn(idx,mode)
{
	return "<img id=mini_btn" + idx + mode + " src='" + mini_path + "img/btn_" + mode + ".gif' onClick=\"mini_command(" + idx + ",'" + mode + "')\" onmouseover=mini_btn_over(this) onmousedown=mini_btn_down(this) onmouseup=mini_btn_up(this) onmouseout=mini_btn_out2(this) style='border:1px solid #ffffff;cursor:pointer'>";
}

function mini_btn_onoff(obj, ret)
{
	obj = document.getElementById(obj);
	if (!obj.disabled){
		if (ret) mini_btn_down(obj);
		else mini_btn_out(obj);
	}
}

function mini_btn_down(obj){
	with (obj.style){
		borderBottom	= "buttonhighlight 1px solid";
		borderLeft		= "buttonshadow 1px solid";
		borderRight		= "buttonhighlight 1px solid";
		borderTop		= "buttonshadow 1px solid";
	}
}

function mini_btn_up(obj){
	with (obj.style){
		borderBottom	= "buttonshadow 1px solid";
		borderLeft		= "buttonhighlight 1px solid";
		borderRight		= "buttonshadow 1px solid";
		borderTop		= "buttonhighlight 1px solid";
	}
}

function mini_btn_over(obj)
{
	if (obj.style.borderBottom != "buttonhighlight 1px solid") mini_btn_up(obj);
}

function mini_btn_out(obj)
{
	obj.style.borderColor = "#ffffff";
}

function mini_btn_out2(obj){
	if (obj.style.borderBottom != "buttonhighlight 1px solid") obj.style.borderColor = "#ffffff";
}

function mini_color_box(idx)
{
	var ret = "";
	var arr = new Array(
			"#FFC0C0","#FFF000","#FFFFE0","#E0FFE0","#C0E0FF","#30C0FF","#F0C0FF","#FFFFFF",
			"#FF8080","#FFC000","#FFFF80","#80FFC0","#C0E0FF","#2080D0","#FF80FF","#C0C0C0",
			"#FF0000","#FF8000","#FFFF00","#00FF00","#00FFFF","#0000FF","#FF00FF","#808080",
			"#800000","#604800","#808000","#008000","#008080","#000080","#800080","#000000"
			);
	for (var i=0;i<arr.length;i++){
		if (i && i%8==0) ret += "</tr><tr>";
		ret += "<td style='width:15px;border-top:1px solid #000000;border-left:1px solid #000000;background:" + arr[i] + "' onClick=\"mini_command_exec(" + idx + ",mini_color_mode[" + idx + "], false, '" + arr[i] + "',1)\"><a href='javascript:void(0)' onClick=\"mini_command_exec(" + idx + ",mini_color_mode[" + idx + "], false, '" + arr[i] + "')\"><div style='width:100%;height:100%;cursor:pointer;'></div></a></td>";
	}
	ret = "<div id=mini_color_box" + idx + " style='position:absolute;display:none;border:2px solid #efefef;padding:3px;background:#f7f7f7'><table><tr>" + ret + "</tr></table></div>";
	return ret;
}


function mini_table_box(idx)
{
	var ret = "<table id=mini_table_inner" + idx + " border=1 bordercolor=#cccccc style='border-collapse:collapse'>";
	for (var i=0;i<10;i++){
		ret += "<tr>";
		for (var j=0;j<7;j++) ret += "<td style='width:20px;height:15px;font-size:0;' onmouseover='mini_chk_table(" + idx + "," + j + "," + i + ")' onclick='mini_set_table(" + idx + "," + j + "," + i + ",1)'><a href='javascript:mini_set_table(" + idx + "," + j + "," + i + ")'><span style='width:100%;height:100%;cursor:pointer'></span></a></td>";
		ret += "</tr>";
	}
	ret += "</table><div id=mini_table_status" + idx + " style='font:8pt tahoma;border:1px solid #cccccc;width:100%;height:20px;margin-top:3px;padding-top:2px;background:#f7f7f7' align=center></div>";
	ret = "<div id=mini_table_box" + idx + " style='position:absolute;display:none;border:2px solid #efefef;padding:3px;background:#ffffff'>" + ret + "</div>";
	return ret;
}

function mini_set_toolbar(idx)
{
	var ret = '\
	<table id=frame_toolbar' + idx + ' cellpadding=0 cellspacing=0>\
		<tr>\
			<td style="padding:5px;border:1px solid #ddd">\
				<table width=100% cellpadding=0 cellspacing=0>\
					<tr>\
						<td height="28px">\
							<a href="javascript:miniEditorMode(' + idx + ',\'editor\')"><img id=btn_editor' + idx + ' src="' + mini_path + 'img/btn_editor.gif" border=0 align=absmiddle style="display:none"></a>\
							<a href="javascript:miniEditorMode(' + idx + ',\'source\')"><img id=btn_source' + idx + ' src="' + mini_path + 'img/btn_source.gif" border=0 align=absmiddle></a>\
						</td>\
						<td id=toolbar' + idx + ' width=100%>\
							<table cellpadding=2 cellspacing=0>\
								<tr>\
									<td>' + mini_set_font(idx) + '</td>\
									<td>' + mini_set_size(idx) + '</td>\
									<td>' + mini_set_btn(idx,"Bold") + mini_set_btn(idx,"Italic") + mini_set_btn(idx,"Underline") + mini_set_btn(idx,"StrikeThrough") + '</td>\
									<td><img src="' + mini_path + 'img/seperator.gif"></td>\
									<td style="position:relative;">' + mini_set_btn(idx,"ForeColor") + mini_set_btn(idx,"BackColor") + '\
										<div style="position:absolute;left:0px;top:30px;width:172px;"> '+ mini_color_box(idx) + '</div>\
									<td><img src="' + mini_path + 'img/seperator.gif"></td>\
									<td>' + mini_set_btn(idx,"JustifyLeft") + mini_set_btn(idx,"JustifyCenter") + mini_set_btn(idx,"JustifyRight") + '</td>\
									<td style="position:relative;"><img src="' + mini_path + 'img/seperator.gif">\
										<div style="position:absolute;left:30px;top:30px;width:250px;"> '+ mini_table_box(idx) + '</div>\
									</td>\
									<td>' + mini_set_btn(idx,"InsertTable") + mini_set_btn(idx,"CreateLink") + mini_set_btn(idx,"InsertImage") + '</td>\
								</tr>\
							</table>\
						</td>\
					</tr>\
				</table>\
			</td>\
		</tr>\
	</table>\
	';
	return ret;
}
var mini_bHeader;
function miniEditorFrame(obj,idx)
{
	var miniEditorContent;
	var miniEditorTextarea = obj;
	mini_bHeader = "\
	<html>\
	<head>\
		<meta http-equiv='content-type' content='text/html; charset=utf-8'>\
		<style>body {margin:10px;} body,table {font-size:0.75em} p {margin:2px 0} td,th {border:1px #bfbfbf dotted}</style>\
	</head>\
	";

	with (miniEditorTextarea.style){
		padding = "10px";
		backgroundColor = "#fff";
		color = "#000000";
		display = "none";
		border = "1px solid #ddd";
	}

	var miniEditorName = miniEditorTextarea.getAttribute("name");
	var miniEditorId = "miniEditorIframe_" + miniEditorName;
	var miniEditorIframe = document.createElement('iframe');
	miniEditorIframe.setAttribute("scrolling","yes");
	miniEditorIframe.setAttribute("wrap","virtual");
	miniEditorIframe.setAttribute("frameBorder",0);
	miniEditorIframe.setAttribute("id",miniEditorId);
	miniEditorIframe.style.marginTop = "1px";
	miniEditorIframe.style.background = "#f9f9f9";
	miniEditorIframe.style.width = miniEditorTextarea.style.width;
	miniEditorIframe.style.height = (miniEditorTextarea.style.height.replace("px","") - 2) + "px";

	miniEditorTextarea.parentNode.insertBefore(miniEditorIframe,miniEditorTextarea);

	var objToolbar = document.createElement("div");
	objToolbar.innerHTML = mini_set_toolbar(idx);
	miniEditorIframe.parentNode.insertBefore(objToolbar,miniEditorIframe);
	document.getElementById('frame_toolbar'+idx).style.width = miniEditorTextarea.style.width + "px";

	miniEditorContent = document.getElementById(miniEditorId).contentWindow.document;
	miniEditorContent.designMode = "on";
	miniEditorContent.open();
	miniEditorContent.write(mini_bHeader);

	if (document.attachEvent){ // IE
		miniEditorContent.write(miniEditorTextarea.value);
		miniEditorContent.close();
		miniEditorContent.body.attachEvent("onclick",function(){mini_reset(idx)},false);
		document.getElementById(miniEditorId).attachEvent("onblur",function(){var rng = miniEditorContent.body.createTextRange(); miniEditorTextarea.value = rng.htmlText; if (miniEditorTextarea.value=="<P>&nbsp;</P>") miniEditorTextarea.value = "";},false);
	} else if (document.addEventListener){ // Mozilla
		miniEditorContent.close();
		miniEditorContent.body.innerHTML = miniEditorTextarea.value;
		miniEditorContent.addEventListener("click",function(){mini_reset(idx)},false);
		//miniEditorContent.addEventListener("blur",miniCopyToHtml,false);
		miniEditorIframe.contentWindow.addEventListener('blur',miniCopyToHtml,false);
	}
}

function miniCopyToHtml(){ // Mozilla
	for (var i=0;i<r_miniEditorTextarea.length;i++){
		var miniEditorName = r_miniEditorTextarea[i].getAttribute("name");
		var miniEditorId = "miniEditorIframe_" + miniEditorName;
		var miniEditorIframe = document.getElementById(miniEditorId);
		var miniEditorContent = miniEditorIframe.contentWindow.document;
		r_miniEditorTextarea[i].value = miniEditorContent.body.innerHTML;
		if (r_miniEditorTextarea[i].value=="<br>") r_miniEditorTextarea[i].value = "";
	}
}

function miniEditorMode(idx,mode)
{
	var miniEditorName = r_miniEditorTextarea[idx].getAttribute("name");
	var miniEditorId = "miniEditorIframe_" + miniEditorName;
	var miniEditorIframe = document.getElementById(miniEditorId);
	var miniEditorContent = miniEditorIframe.contentWindow.document;

	switch (mode){
		case "editor":
			r_miniEditorTextarea[idx].style.display = "none";
			miniEditorIframe.style.display = "block";
			if (document.attachEvent){ // IE
				miniEditorContent.open();
				miniEditorContent.write(mini_bHeader);
				miniEditorContent.write(r_miniEditorTextarea[idx].value);
				miniEditorContent.close();
				miniEditorContent.body.attachEvent("onclick",function(){mini_reset(idx)},false);
				document.getElementById(miniEditorId).attachEvent("onblur",function(){var rng = miniEditorContent.body.createTextRange(); r_miniEditorTextarea[idx].value = rng.htmlText; if (r_miniEditorTextarea[idx].value=="<P>&nbsp;</P>") r_miniEditorTextarea[idx].value = "";},false);
			} else if (document.addEventListener){ // Mozilla
				miniEditorContent.body.innerHTML = r_miniEditorTextarea[idx].value;
			}
			document.getElementById('btn_editor'+idx).style.display = "none";
			document.getElementById('btn_source'+idx).style.display = "block";
			document.getElementById('toolbar'+idx).style.display = "block";
			break;
		case "source":
			r_miniEditorTextarea[idx].style.display = "block";
			miniEditorIframe.style.display = "none";
			document.getElementById('btn_editor'+idx).style.display = "block";
			document.getElementById('btn_source'+idx).style.display = "none";
			document.getElementById('toolbar'+idx).style.display = "none";
			r_miniEditorTextarea[idx].focus();
			break;
	}
}

function mini_in_array(el,arr)
{
	var ret = false;
	for (var i=0;i<arr.length;i++){
		if (el==arr[i]){
			ret = true;
			break;
		}
	}
	return ret;
}

function mini_reset(idx,obj)
{
	var r_obj = new Array();
	var arr = new Array("Bold","Italic","Underline","StrikeThrough","FontName","FontSize");

	var miniEditorName = r_miniEditorTextarea[idx].getAttribute("name");
	var miniEditorId = "miniEditorIframe_" + miniEditorName;
	var miniEditorIframe = document.getElementById(miniEditorId);
	var miniEditorContent = miniEditorIframe.contentWindow.document;

	if (!obj) r_obj = arr;
	else if (mini_in_array(obj,arr)) r_obj[0] = obj;

	for (var i=0;i<r_obj.length;i++){
		switch (r_obj[i]){
			case "FontName": case "FontSize":
				mini_set_select('mini_btn' + idx + r_obj[i], miniEditorContent.queryCommandValue(r_obj[i]));
				break;
			default:
				mini_btn_onoff('mini_btn' + idx + r_obj[i], miniEditorContent.queryCommandValue(r_obj[i]));
				break;
		}
	}

	if (!obj){
//		if (document.getElementById('mini_table_box'+idx).style.display=="block") mini_vlayer(idx,'mini_table_box');
//		if (document.getElementById('mini_color_box'+idx).style.display=="block") mini_vlayer(idx,'mini_color_box');
	}
}

function mini_set_select(obj,ret)
{
	obj = document.getElementById(obj);
	for (var i=0;i<obj.length;i++){
		if (obj.options[i].value==ret){
			obj.selectedIndex = i;
			break;
		}
	}
}

function mini_vlayer(idx,obj)
{
	obj = document.getElementById(obj+idx);
	obj.style.display = (obj.style.display!="block") ? "block" : "none";
	if (obj.id=="mini_color_box"){
		var value = (obj.style.display=="block") ? true : false;
		mini_btn_onoff('mini_btn' + mini_color_mode, value);
	}
}

function mini_set_html(idx,str)
{
	
	var miniEditorName = r_miniEditorTextarea[idx].getAttribute("name");
	var miniEditorId = "miniEditorIframe_" + miniEditorName; // miniEditorIframe_body
	$('#'+miniEditorId).focus();
	var miniEditorIframe = document.getElementById(miniEditorId);
	var miniEditorContent = miniEditorIframe.contentWindow.document;
    if (miniEditorIframe.contentWindow.getSelection) {
        // IE9 and non-IE
    	
    	selObj = miniEditorIframe.contentWindow.getSelection();
        if (selObj.getRangeAt && selObj.rangeCount) {
            range = selObj.getRangeAt(0);
            range.deleteContents();

            // Range.createContextualFragment() would be useful here but is
            // only relatively recently standardized and is not supported in
            // some browsers (IE9, for one)
            var el = miniEditorIframe.contentDocument.createElement("p");
            el.innerHTML = str.replace(/<td><\/td>/gi,'<td><br><\/td>');
            
            var frag = miniEditorIframe.contentDocument.createDocumentFragment(), node, lastNode;
            while ( (node = el.firstChild) ) {
                lastNode = frag.appendChild(node);
            }
            var firstNode = frag.firstChild;
            range.insertNode(frag);

            // Preserve the selection
            if (lastNode) {
                range = range.cloneRange();
                range.setStartAfter(lastNode);

                range.collapse(true);
               
                selObj.removeAllRanges();
                selObj.addRange(range);
            }
        }
    } else if ( (sel = miniEditorContent.selection) && sel.type != "Control") {

    	var html="";
    	if ( str.match(/<IMG src=([^>].*)>/ig) != null ){
    		html = str.replace(/<IMG src='/ig, '').replace(/'/ig, '"');
    	}
    	
    	// IE < 9
        var originalRange = sel.createRange();
        originalRange.collapse(true);
        sel.createRange().pasteHTML(str);
        
            range = sel.createRange();
            range.setEndPoint("StartToStart", originalRange);
            range.select();
        
    }
	
}

function mini_chk_table(idx,x,y)
{
	var obj = document.getElementById('mini_table_inner'+idx);
	for (var i=0;i<10;i++){
		for (var j=0;j<7;j++){
			obj.rows[i].cells[j].style.background = (j<=x && i<=y) ? "#316AC5" : "#ffffff";
		}
	}
	document.getElementById('mini_table_status'+idx).innerHTML = "<b>" + (x+1) + "</b> cells X <b>" + (y+1) + "</b> rows Table";
}

function mini_set_table(idx,x,y,ff)
{
	if (document.all && ff) return;
	var ret = "<table width=99%>";
	for (var i=0;i<=y;i++){
		ret += "<tr>";
		for (var j=0;j<=x;j++) ret += "<td></td>";
		ret += "</tr>";
	}
	ret += "</table>";
	mini_vlayer(idx,'mini_table_box');
	mini_set_html(idx,ret);
}