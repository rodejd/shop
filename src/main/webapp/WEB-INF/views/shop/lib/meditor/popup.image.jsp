<%
/************************************************************************************
* 프로그램명 				: 
* 관련 SERVICE명 			: 
* 프로그램 내용 			: XMall>lib>meditor>popup.image.jsp
* 작성자	   		 		: 홍웅선
* 작성일자 				: 2011.01.10
* 수정자  				: 
* 수정 내용				: 
* 수정일자				: 
************************************************************************************/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/resources/js/jquery/jquery-1.10.2.min.js"></script>
<title>이미지 삽입</title>
<style>
body {background:buttonface}
body,td,input {font:9pt 굴림}
</style>
<script language="javascript">

var imgWidth = 300;
var imgHeight = 200;
var tmpImg = new Image();
var tmpCut;

function img_preview(src,cut)
{
	if ( document.getElementById('preview') != null ){
		tmpCut = cut;
		document.getElementById('preview').innerHTML = "<img id=prevImg onload='img_resize(this)' onerror=this.src='/resources/images/editor/preview.gif'>";
		tmpImg.src = src;
		tmpImg.onload = loadImgSize;
		document.getElementById('prevImg').src = tmpImg.src;
		document.fm.src.value = tmpImg.src;
	}
}

function loadImgSize()
{
	if (tmpCut==1) return;
	document.forms[0].imgWidth.value = tmpImg.width;
	document.forms[0].imgHeight.value = tmpImg.height;
}

function img_resize(obj)
{
	if (obj.width*2>obj.height*3){
		if (obj.width>imgWidth) obj.width = imgWidth;
	} else {
		if (obj.height>imgHeight) obj.height = imgHeight;
	}
}

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#prevImg').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
    img_preview(input.value);
}

function doSubmit(){
	if( $("#imgPc").val() != "" ){
		var ext = $('#imgPc').val().split('.').pop().toLowerCase();
		if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
			alert('gif,png,jpg,jpeg 파일만 업로드 할수 있습니다.');
			return false;
		}else{
			$("#fm").submit();
		}
	}
}
</script>    
<form name="fm" id="fm" method=post  action="/shop/admin/common/imgRegister" enctype="multipart/form-data">
<input type=hidden name=mode value="InsertImage">
<input type=hidden name=idx value="${imgFileVO.idx}">
<input type=hidden name=src>
	<table width=100%>
		<tr>
			<td style="font:bold 22px tahoma;padding:10 10 0 10">
				INSERT IMAGE
			</td>
		</tr>
		<tr>
			<td>
				<table width=100%>
					<tr>
						<td nowrap>- 미리보기</td>
						<td width=100%><hr></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align=center>
				<table>
					<tr>
						<td id=preview align=center style="width:304px;height:204px;background:#ffffff;border:1px solid #000000">&nbsp;</td>
					</tr>
				</table>		
			</td>
		</tr>
		<tr>
			<td>
				<table width=100%>
					<tr>
						<td nowrap>- 이미지삽입</td>
						<td width=100%><hr></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td style="padding:0 10">
				삽입할 이미지 선택 :
				<table width=100%>
					<tr>
						<td><input type=file name=imgPc id="imgPc" style="width:100%" onchange="readURL(this)"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width=100%>
					<tr>
						<td nowrap>- 이미지사이즈</td>
						<td width=100%><hr></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td style="padding:0 10">
				<table width=100%>
					<tr>
						<td>가로사이즈 : <input type=text name=imgWidth size=10></td>
						<td>세로사이즈 : <input type=text name=imgHeight size=10></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr><td><hr></td></tr>
		<tr>
			<td align=center style="padding:5">
				<input type=button  value="확인" style="width:100" onclick="doSubmit();">
				<input type=button value="취소" style="width:100" onclick="window.close()">
			</td>
		</tr>
	</table>   
</form>

<script>
if("${imgFileVO.name}"!==""){
	parent.opener.mini_set_html("${imgFileVO.idx}","<img src='${imgFileVO.name}' width='${imgFileVO.imgWidth}'  height='${imgFileVO.imgHeight}' >");
	img_preview("${imgFileVO.name}",1);
	window.close()
}else{
	img_preview("/resources/images/editor/preview.gif",1);
}

</script>
