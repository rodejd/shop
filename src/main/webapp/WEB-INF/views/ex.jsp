<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<div id="displayDiv">
</div>

<script id="template" type="text/x-handlebars-template">
	<span>{{name}}</span>	
<div>
	<span>{{userid}}</span>
	<span>{{addr}}</span>
</div>
</script>


<script>
//	var source = "<h1><p>{{name}}</p> <p>{{userid}}</p> <p>{{addr}}</p></h1>";
	
	var source = $("#template").html();
	
	var template = Handlebars.compile(source);
	var data = {name:"홍길동",userid:"user00",addr:"조선 한양"};
	
	$("#displayDiv").html(template(data));
</script>

</body>
</html>