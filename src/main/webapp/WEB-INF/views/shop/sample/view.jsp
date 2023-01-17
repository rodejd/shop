<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SAMPLE</title>
</head>
<body>
	<div>
		<h2>sample View</h2>
		<form name="form" method="post" action="${ctx }/shop/sample/mod">
			<ul>
					<li>${sampleVO.sampleObj.sampleno}</li>
					<li><input type="text" name="title" value="${sampleVO.sampleObj.title}" /></li>
					<li><input type="text" name="description" value="${sampleVO.sampleObj.description}" /></li>
			</ul>
			<input type="hidden" name="sample_no" value="${sampleVO.sampleObj.sampleno}" />
			<input type="submit" value="전송" />
		</form>
		<a href="${ctx }/shop/sample/main">리스트로</a>
	</div>


</body>
</html>