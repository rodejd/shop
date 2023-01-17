<%@page language="java"  contentType="text/html; charset=UTF-8" %>
<%----------------------------------------------------------------------------------------------
* 파일명 :404.jsp
* 생성일 : 2015. 01. 09
* 작성자 : 이 의 창
* 설  명 : 404 페이지
* ==============================================
* 변경이력:
* DATE			AUTHOR				DESCRIPTION
* ---------------------------------------------------------------------------
* 20150109		이의창				최초작성
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
			location.href = ctx+'/shop/admin/basic/index';
		}
//			else if(path_nm.indexOf("/manager/seller") > -1){
//				location.href = '/shop/seller/main';
//			}
		else{
			location.href=ctx+'/shop/main/index';
		}
	}
}
</script>
	
	<div class="container">
		<div class="error">
			<div class="row">
				<div class="col-md-12 text-center bx_type">
					<p>요청하신 페이지가 존재하지 않습니다.(404)</p>
				</div>
				<div class="col-md-3 text-center">
					<i class="fa fa-fw fa-desktop"></i>
				</div>
				<div class="col-md-9 bx_type2">
					<p><strong>이용에 불편을 드려 대단히 죄송합니다.</strong></p>
					<p>요청하신 페이지를 찾을 수가 없습니다. 찾으시려는 페이지의 주소가 잘못 입력되었거나, <br>페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다. <br>입력하신 주소가 정확한지 다시 확인해 주시기 바랍니다.</p>
					<p><a href="#" onclick="goHome();">이전페이지로 가기</a></p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

