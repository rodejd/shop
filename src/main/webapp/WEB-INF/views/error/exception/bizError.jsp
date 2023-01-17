<%@page language="java"  contentType="text/html; charset=UTF-8" %>
<%----------------------------------------------------------------------------------------------
* 파일명 : bizError.jsp
* 생성일 : 2015. 01. 09
* 작성자 : 이 의 창
* 설  명 : 공통 에러 페이지
* ==============================================
* 변경이력:
* DATE			AUTHOR				DESCRIPTION
* ---------------------------------------------------------------------------
* 20150109		이의창				최초작성
 ----------------------------------------------------------------------------------------------%>
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
	

	@media (max-width: 991px){
		.error .bx_type{font-size:22px;}
		.error .fa{margin-bottom:30px;}
		.error .bx_type2{font-size:12px}
	}
</style>

<style type="text/css">
/* 404 */
#errorPage{width:100%; height:100%; min-width:1000px; min-height:433px; position:relative;}
#errorPage .body{width:1000px; height:433px; margin:-217px 0 0 -500px; position:absolute; left:50%; top:50%; background:url('/common/images/error/error_bg.jpg') left top no-repeat;}
#errorPage .inner{padding:115px 0 0 340px;}
#errorPage .message{margin-bottom:17px;}
#errorPage .message strong{font:100 62px/65px 'Century Gothic'; color:#3b3f4a; letter-spacing:-1px}
#errorPage .message strong em{font-weight:bold; color:#f8433c;}
#errorPage .message p{font:100 30px 'Nanum Gothic'; color:#444;}
#errorPage .cont{overflow:hidden;}
#errorPage .cont .section{width:225px; margin-right:20px; float:left; background:url('/common/images/error/section_bg.png') top repeat-x; font:100 12px 'Nanum Gothic'; color:#767676;}
#errorPage .cont .section dt{padding:12px 0 5px; font-size:18px; color:#444;}
#errorPage .cont .section dd{padding:2px 0 2px 6px; background:url('/common/images/error/bullet.png') left center no-repeat;}
	.btn-danger{display:inline-block;padding:4px 8px;background-color:#cb1b4f;color:#fff;}
</style>
<script>
	function goHome(returnUrl) {
		if(window.opener != null) {
			self.close();	
		} else {
			//returnUrl이 있을 경우
			if(returnUrl !='' && returnUrl !='/'){
				location.href = returnUrl;
			}else{
				var path_nm = location.pathname;
				if(path_nm.indexOf("/shop/admin") > -1){
					location.href = ctx + '/shop/admin/basic/index';
				}
// 				else if(path_nm.indexOf("/manager/seller") > -1){
// 					location.href = '/manager/seller/main';
// 				}
				else{
					location.href= ctx + '/shop/main/index';
				}	
			}
			
		}
	}
</script>


<div class="container">
	<div class="error">
		<div class="row">
			<div class="col-md-12 text-center bx_type">
				<p>400 ERROR</p>
				<p><spring:message code="common.00001"/></p>
			</div>
			<div class="col-md-3 text-center">
				<i class="fa fa-fw fa-desktop"></i>
			</div>
			<div class="col-md-9 bx_type2">
				<p><strong><c:out value="${exception.errorMessage}"/></p>
				<p>가능성이 높은 원인<br>주소에 오타가 있을 수 있습니다.<br>클릭한 링크가 만료인 것일 수도 있습니다.</p>
				<p>가능한 해결 방법<br>주소를 다시 입력하십시오.<br>이전 페이지로 돌아갑니다.<br>기본 사이트로 원하는 정보를 찾습니다.</p>
				<p><a class="btn btn-danger" href="#" onclick="goHome();">이전페이지로 가기</a></p>
			</div>
		</div>
	</div>
</div>


<%-- 
<div id="errorPage">
	<div class="body">
		<div class="inner">
			<div class="message">
				<strong>
					<em></em> ERROR
				</strong>

				<p><spring:message code="common.00001"/></p>
			</div>
			
			<div class="cont">
				<dl class="section">
					<dt>오류원인</dt>
					<dd><c:out value="${exception.errorMessage}"/></dd>
				</dl>

<!-- 				<dl class="section"> -->
<!-- 					<dt>가능한 해결 방법</dt> -->
<!-- 					<dd>주소를 다시 입력하십시오.</dd> -->
<!-- 					<dd>이전 페이지로 돌아갑니다.</dd> -->
<!-- 					<dd>기본 사이트로 원하는 정보를 찾습니다.</dd> -->
<!-- 				</dl> -->
			</div>
			<div class="bt"><button type="button" onClick="goHome('<c:out value="${exception.returnUrl}"/>')">확인</button></div>
		</div>
	</div>
</div> --%>
</body>
</html>



<%
	 //}
%>
