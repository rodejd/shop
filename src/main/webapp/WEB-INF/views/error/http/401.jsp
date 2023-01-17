<%@page language="java"  contentType="text/html; charset=UTF-8" %>
<%----------------------------------------------------------------------------------------------
* 파일명 : 401.jsp
* 생성일 : 2017. 11. 15
* 작성자 : 김 봉 진
* 설  명 : 400 페이지
* ==============================================
* 변경이력:
* DATE			AUTHOR				DESCRIPTION
* ---------------------------------------------------------------------------
* 20171115		김봉진				최초작성
 ----------------------------------------------------------------------------------------------%>
<%@ page isErrorPage="true" %>    
 <%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>ERROR</title>
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
<style>
	.error{border:1px solid #ccc;background:#f6f6f6;padding:10% 20%;margin-top:20px}
	.error .bx_type{font-size:36px;padding-bottom:7%;font-weight:bold}
	.error .fa{font-size:86px}
	.error .bx_type2{font-size:14px}
	.error .bx_type2 strong{display:block;margin-bottom:20px;font-size:16px}		
	.btn-danger{display:inline-block;padding:4px 8px;background-color:#cb1b4f;color:#fff;}
	

	@media (max-width: 991px){
		.error .bx_type{font-size:22px;}
		.error .fa{margin-bottom:30px;}
		.error .bx_type2{font-size:12px}
	}
</style>

<script>
function goHome() {
	if(window.opener != null) {
		self.close();	
	} else {
		var path_nm = location.pathname;
		if(path_nm.indexOf("/shop/admin") > -1){
			location.href = ctx+ '/shop/admin/basic/index';
		}
//			else if(path_nm.indexOf("/manager/seller") > -1){
//				location.href = '/shop/seller/main';
//			}
		else{
			location.href= ctx+ '/shop/main/index';
		}
	}
}
</script>
<div class="container">
	<div class="error">
		<div class="row">
			<div class="col-md-12 text-center bx_type">
				<p>401 ERROR</p>
			</div>
			<div class="col-md-3 text-center">
				<i class="fa fa-fw fa-desktop"></i>
			</div>
			<div class="col-md-9 bx_type2">
				<p><strong>접근 권한이 없습니다.</strong></p>
				<p>가능한 해결 방법<br>주소를 다시 입력하십시오.<br>이전 페이지로 돌아갑니다.<br>기본 사이트로 원하는 정보를 찾습니다.</p>
				<p><a href="#" onclick="goHome();">이전페이지로 가기</a></p>
			</div>
		</div>
	</div>
</div>
<!-- <div id="errorPage">
	<div class="body">
		<div class="inner">
			<div class="message">
				<strong>
					<em>401</em> ERROR
				</strong>

				<p>접근 권한이 없습니다.</p>
			</div>
			
			<div class="cont">
				<dl class="section">
					<dt>가능성이 높은 원인</dt>
					<dd>주소에 오타가 있을 수 있습니다.</dd>
					<dd>클릭한 링크가 만료인 것일 수도 있습니다. </dd>
				</dl>

				<dl class="section">
					<dt>가능한 해결 방법</dt>
					<dd>주소를 다시 입력하십시오.</dd>
					<dd>이전 페이지로 돌아갑니다.</dd>
					<dd>기본 사이트로 원하는 정보를 찾습니다.</dd>
				</dl>
			</div>
			<div class="bt"><button type="button" onClick="goHome();">확인</button></div>
		</div>
	</div>
</div> -->
</body>
</html>

