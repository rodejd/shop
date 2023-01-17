<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resources/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery/jquery.ajax.js"></script>
<title>SAMPLE</title>
</head>
<body>
	<div>
		<h2>sample List</h2>
		<ul>
			<c:forEach items="${sampleVO.sampleList }" var="sample" varStatus="status">
				<li>${sample.sampleno}</li>
				<li><a href="${ctx }/shop/sample/view?sample_no=${sample.sampleno}">${sample.title}</a></li>
			</c:forEach>
		</ul>
		<tags:frontPaginator currentPageNo="${sampleVO.pageNo}" rowCount="${sampleVO.rowCount}" pageSize="${sampleVO.pageSize}"  pageGroupSize="${sampleVO.pageGroupSize}" />
		<div>
			<strong>검색</strong><br/>
			POST
			<form name="form01" method="post">
				sample_no<input type="text" value="" name="sample_no">
				title<input type="text" value="" name="title">
				description<input type="text" value="" name="description">
				<input type="submit" value="전송" />
			</form>
			GET
			<form name="form02" method="get">
				sample_no<input type="text" value="" name="sample_no">
				title<input type="text" value="" name="title">
				descrption<input type="text" value="" name="description">
				<input type="submit" value="전송" />
			</form>
		</div>
		
		<script type="text/javascript">
			function goPage(page){
				window.location.href="/sample/index?pageNo="+page;
			}
		</script>
	</div>
	<br />
	<div>
		<h2>sample reg</h2>
		POST
		<form name="form03" method="post" action="${ctx }/shop/sample/reg">
			sample_no<input type="text" value="" name="sample_no">
			title<input type="text" value="" name="title">
			descrption<input type="text" value="" name="description">
			<input type="submit" value="전송" />
		</form>
		GET(Controller단에서 get방식 막음)
		<form name="form04" method="get" action="${ctx }/shop/sample/reg">
			sample_no<input type="text" value="" name="sample_no">
			title<input type="text" value="" name="title">
			descrption<input type="text" value="" name="description">
			<input type="submit" value="전송" />
		</form>
	</div>
	
	<div>
		<h2>sample reg</h2>
		POST
		<form name="form03" method="post" action="${ctx }/shop/sample/reg">
			sample_no<input type="text" value="" name="sample_no">
			title<input type="text" value="" name="title">
			descrption<input type="text" value="" name="description">
			<input type="submit" value="전송" />
		</form>
		GET(Controller단에서 get방식 막음)
		<form name="form04" method="get" action="${ctx }/shop/sample/reg">
			sample_no<input type="text" value="" name="sample_no">
			title<input type="text" value="" name="title">
			descrption<input type="text" value="" name="description">
			<input type="submit" value="전송" />
		</form>
	</div>
	
	<div>
		<h2>개발자 정의 Exception</h2>
		<input type="button" value="Exception1" id="btn_exception1"><br/>
		<input type="button" value="Exception2" id="btn_exception2"><br />
		Service에 사용!(controller에서 쓰면... DB 롤백 안됨.)<br/>
		error_message_ko.properties 파일과 연계.<br/>
		throw new BizException("sample.00000");							// sample.00000=테스트<br />
		throw new BizException("common.00001", new String[]{"1"});		// sample.00001=테스트 {0}<br />
		
		
		<script type="text/javascript">
			$(document).ready(function(){
				$("#btn_exception1").on("click", function(){
					ajaxCallJson("/shop/sample/bizExceptionTest1", {}, function(result){
						alert(JSON.stringify(result));
					});
				});			
				$("#btn_exception2").on("click", function(){
					ajaxCallJson("/shop/sample/bizExceptionTest2", {}, function(result){
						alert(JSON.stringify(result));
					});
				});			
			});
		</script>
		
	</div>
	<div>
		<h2>AJAX sample List</h2>
		<div>
			<strong>검색</strong><br/>
			Ajax JSON
			<form name="form01" id="form_json">
				sample_no<input type="text" value="" name="sample_no">
				title<input type="text" value="" name="title">
				description<input type="text" value="" name="description">
				<input type="button" id="btn_json" value="전송" />
			</form>
			<script type="text/javascript">
				$(document).ready(function(){
					$("#btn_json").on("click", function(){
						ajaxCallJsonPost("/shop/sample/sampleAjax.dojson", "form_json", function(result){
							alert(JSON.stringify(result));
						});
					});			
				});
			</script>
			Ajax HTML
			<form name="form02" id="form_html">
				sample_no<input type="text" value="" name="sample_no">
				title<input type="text" value="" name="title">
				descrption<input type="text" value="" name="description">
				<input type="button" id="btn_html" value="전송" />
			</form>
			
			<ul id="changeDIV"></ul>
			
			<script type="text/javascript">
				$(document).ready(function(){
					$("#btn_html").on("click", function(){
						// /shop/sample/main
						ajaxCallPost("/shop/sample/sampleAjax", "form_html", "changeDIV");
					});
				});
			</script>
		</div>
	</div>
</body>
</html>