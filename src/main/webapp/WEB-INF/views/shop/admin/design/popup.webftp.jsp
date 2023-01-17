<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<meta name="robots" content="noindex">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="pragma" content="no-cache">
		<meta charset="UTF-8">

		<style type="text/css">
			input.button {background-color: #c0c0c0; color: #666666;
			border: 1px solid #999999; margin: 5px 1px 5px 1px;}
			input.textfield {margin: 5px 1px 5px 1px;}
			input.button:Hover { color: #444444 }
			table.filelist {background-color:#666666; width:100%; border:0px none #ffffff}
/* 			.formular {margin: 1px; background-color:#ffffff; padding: 1em; border:1px solid #000000;}*/
 			.formular2 {margin: 1px;}
			th { background-color:#c0c0c0 }
			tr.mouseout { background-color:#ffffff; }
			tr.mousein  { background-color:#eeeeee; }
			tr.checked  { background-color:#cccccc }
			tr.mousechecked { background-color:#c0c0c0 }
			td { font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 8pt; color: #666666;}
			td.message { background-color: #FFFF00; color: #000000; text-align:center; font-weight:bold}
			td.error { background-color: #FF0000; color: #000000; text-align:center; font-weight:bold}
			A { text-decoration: none; }
			A:Hover { color : Red; text-decoration : underline; }
			BODY { font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 8pt; color: #666666;}
			
			.js_selectAll {
			    margin: 7px 5px;
			}
			.js_fileNameFilter {
				text-align: end;
			}
		</style>
		
		<script type="text/javascript">
			var check = false;
			
			function dis(){check = true;}
		
			var DOM = 0, MS = 0, OP = 0, b = 0;
			
			function CheckBrowser(){
				if (b == 0){
					if (window.opera) OP = 1;
					// Moz or Netscape
					if(document.getElementById) DOM = 1;
					// Micro$oft
					if(document.all && !OP) MS = 1;
					b = 1;
				}
			}
			
			function selrow (element, i){
				var erst;
				CheckBrowser();
				if ((OP==1)||(MS==1)) erst = element.firstChild.firstChild;
				else if (DOM==1) erst = element.firstChild.nextSibling.firstChild;
				
				if (i==0){
					if (erst.checked == true) element.className='mousechecked';
					else element.className='mousein';
				}
				
				else if (i==1){
					if (erst.checked == true) element.className='checked';
					else element.className='mouseout';
				}
				
				else if ((i==2)&&(!check)){
					if (erst.checked==true) element.className='mousein';
					else element.className='mousechecked';
					erst.click();
				}
				else check=false;
			}
			
			function filter (begriff){
				var suche = begriff.value.toLowerCase();
				var table = document.getElementById("filetable");
				var ele;
				for (var r = 1; r < table.rows.length; r++){
					ele = table.rows[r].cells[1].innerHTML.replace(/<[^>]+>/g,"");
					if (ele.toLowerCase().indexOf(suche)>=0 )
						table.rows[r].style.display = '';
					else table.rows[r].style.display = 'none';
		      	}
			}
				
			function AllFiles(){
				for(var x=0;x < document.FileList.elements.length;x++){
					var y = document.FileList.elements[x];
					var ytr = y.parentNode.parentNode;
					var check = document.FileList.selall.checked;
					if(y.name == 'selfile' && ytr.style.display != 'none'){
						if (y.disabled != true){
							y.checked = check;
							if (y.checked == true) ytr.className = 'checked';
							else ytr.className = 'mouseout';
						}
					}
				}
			}
			
			function shortKeyHandler(_event){
				if (!_event) _event = window.event;
				if (_event.which) {
					keycode = _event.which;
				} else if (_event.keyCode) {
					keycode = _event.keyCode;
				}
				var t = document.getElementById("text_Dir");
				//z
				if (keycode == 122){
					document.getElementById("but_Zip").click();
				}
				//r, F2
				else if (keycode == 113 || keycode == 114){
					var path = prompt("Please enter new filename", "");
					if (path == null) return;
					t.value = path;
					document.getElementById("but_Ren").click();
				}
				//c
				else if (keycode == 99){
					var path = prompt("Please enter filename", "");
					if (path == null) return;
					t.value = path;
					document.getElementById("but_NFi").click();
				}
				//d
				else if (keycode == 100){
					var path = prompt("Please enter directory name", "");
					if (path == null) return;
					t.value = path;
					document.getElementById("but_NDi").click();
				}
				//m
				else if (keycode == 109){
					var path = prompt("Please enter move destination", "");
					if (path == null) return;
					t.value = path;
					document.getElementById("but_Mov").click();
				}
				//y
				else if (keycode == 121){
					var path = prompt("Please enter copy destination", "");
					if (path == null) return;
					t.value = path;
					document.getElementById("but_Cop").click();
				}
				//l
				else if (keycode == 108){
					document.getElementById("but_Lau").click();
				}
				//Del
				else if (keycode == 46){
					document.getElementById("but_Del").click();
				}
			}
		
			function popUp(URL){
				fname = document.getElementsByName("myFile")[0].value;
				if (fname != "")
					window.open(URL+"?first&uplMonitor="+fname,"","width=400,height=150,resizable=yes,depend=yes")
		//			window.open(URL+"?first&uplMonitor="+encodeURIComponent(fname),"","width=400,height=150,resizable=yes,depend=yes")
			}
			
			document.onkeypress = shortKeyHandler;
	
		
		</script>
		<title>${ftoVO.dirDefault}</title>
		
		
		
		
		
		<SCRIPT LANGUAGE="JavaScript"> var curr_path = '/shop/admin/design/webftp/'; var webftpid = 'default'; </SCRIPT>
		<SCRIPT LANGUAGE="JavaScript" SRC="/shop/admin/design/webftp/webftp.js"></SCRIPT>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="styleSheet" href="/resources/shop/admin/style.css">
		<script src="/resources/shop/admin/common.js"></script>
		<script src="/resources/shop/admin/prototype.js"></script>
		<script language="javascript">
		if(window.addEventListener) 
		{
			window.addEventListener('load',linecss,false); 
		}
		else 
		{
			window.attachEvent('onload',linecss); 
		}
		</script>
	</head>
	<body>
	 	<jsp:include page="${ftpVO.pageOrder}"></jsp:include>
		<jsp:include page="directoryViewer.jsp"></jsp:include>
		</form>
		<br />
			
		<div>
			<c:if test="${boolsMap.nativeCommands }">
			    <form class="formular2" action="${browserName}" method="POST">
					<input type="hidden" name="dir" value="${ftpVO.dir}">
					<input type="hidden" name="sort" value="${sortMode}">
					<input type="hidden" name="command" value="">
				</form>
			</c:if>
	    </div>
		<hr>
		<center>
			<small>jsp File Browser version ${strMap.versionNR} by <a href="http://www.vonloesch.de">www.vonloesch.de</a></small>
		</center>
	</body>
</html>
<%--여기서 } 2개 없앴음--%>