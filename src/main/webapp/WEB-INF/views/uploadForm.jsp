<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
iframe {
	width: 0px;
	height: 0px;
	border: 0px;
}
</style>

</head>
<body>

<form action="uploadForm" id="form" method="post" enctype="multipart/form-data" target="zeroFrame" name="upload">
	<input type='file' name='file'>
	<input type='file' name='file'>
	<input type='file' name='song'>
	<input type='file' name='song'>
	<input type="submit">
</form>

<iframe name="zeroFrame">

</iframe>



<script>
function addFilePath(msg) {
	alert(msg);
	document.getElementById("form").reset();
}
</script>

</body>
</html>